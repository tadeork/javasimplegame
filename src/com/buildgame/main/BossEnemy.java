package com.buildgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends GameObject{
	
	private Handler handler;
	private int timer = 80;
	private int timer2 = 50;

	public BossEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		timer--;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		 
		//cuando llega al final de la ventana regresa cambiando
		//el sentido de Y
//		if( y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
		//hace lo mismo en el eje X
//		if( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.09f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
	}
	
	/**
	 * Posición exacta del objeto
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,96,96);
	}

}
