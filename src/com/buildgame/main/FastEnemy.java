package com.buildgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	private Handler handler;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velY = 2;
		velX = 9;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//cuando llega al final de la ventana regresa cambiando
		//el sentido de Y
		if( y <= 0 || y >= Game.HEIGHT - 32 ) velY *= -1;
		//hace lo mismo en el eje X
		if( x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	/**
	 * Posici�n exacta del objeto
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}
