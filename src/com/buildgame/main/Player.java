package com.buildgame.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * El cuadrado que representa al jugador.
 * @author Tadeo
 *
 */
public class Player extends GameObject{
	Random r = new Random();
	Handler handler;

	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick() {
		y += velY;
		x += velX;

		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
			
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.08f, handler));
		collision();
	}

	private void collision(){
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObjet = handler.object.get(i);
			
			if( tempObjet.getId() == ID.BasicEnemy || tempObjet.getId() == ID.FastEnemy || tempObjet.getId() == ID.SmartEnemy){
				if(getBounds().intersects(tempObjet.getBounds())){
					//collision code
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	public void render(Graphics g) {
		if( id == ID.Player ) g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	/**
	 * Posición exacta del objeto
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

}
