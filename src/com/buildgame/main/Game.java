package com.buildgame.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12*9;
	private Thread thread;
	private boolean running = true;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawn;
	public int edad = 32;

	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "dfgsdfgdfg", this);
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		r = new Random();

		handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler) );

//		handler.addObject(new BossEnemy((Game.WIDTH / 2) -48 , -120, ID.BossEnemy, handler));
		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler) );
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try{
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Game loop, buscar más info
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while( running ){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while( delta >= 1 ){
				tick(); //provoca una modificación
				delta--;
			}
			
			if( running )
				render();
			
			if( System.currentTimeMillis() - timer > 1000){
				timer +=1000;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
		hud.tick();
		spawn.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if( bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);

		g.dispose();
		bs.show();
	}
	
	/**
	 * Si alcanza la posición máxima debe fijar la variable
	 * a ese mismo valor.
	 * @param var
	 * @param min
	 * @param max
	 * @return
	 */
	public static float clamp(float var, float min, float max) {
		if( var >= max )
			return max;
		else if( var <= min )
			return min;
		
		return var;
	}
	
}
