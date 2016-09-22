package com.buildgame.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Pinta el cuadro de vida y lleva el conteo de puntos además del nivel.
 * El nivel depende de la cantidad de puntos. Además del comportamiento
 * de la barra de vida irá cambiando de color según sea alcanzado 
 * por enemigos.
 * @author Tadeo
 *
 */
public class HUD {

	public static float HEALTH = 100;
	private float greenValue = 255; //RGB color
	private int level = 0;
	private int score = 0;

	public void tick(){
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH * 2;
		
		score++;
	}
	
	/**
	 * Renderiza la barra de vida
	 * @param g
	 */
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 22);

		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(10, 10, (int)HEALTH * 2, 22);

		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 22);

		g.drawString("Puntos: " + score, 10, 64);
		g.drawString("Nivel: " + level, 10, 84);

	}
	
	public void score(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
