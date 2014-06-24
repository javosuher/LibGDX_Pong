package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Paddle { // Clase abstracta que modelará las partes comunes de las dos palas que tendremos en la pantalla
	public static final float SPEED = 400;
	
	protected Texture texture;
	protected Rectangle bordes; // Objeto que nos determinará la posición de la pala y permite detectar colisiones con otros rectangulos.
	
	public Paddle(float x, float y) { // Constructor que le pasamos en la posición que está situado (Eje x e y).
		texture = new Texture(Gdx.files.internal("pala.png"));
		bordes = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
	}
	
	public void draw(SpriteBatch batch) { // Permite dibujar la pala para que la visualice el usuario.
		batch.draw(texture, bordes.x, bordes.y, texture.getWidth(), texture.getHeight());
	}
	
	public abstract void update(); // Método abstracto que definirá el comportamiento especifico de cada pala.

	public Rectangle getBordes() { // Observador del rectangulo.
		return bordes;
	}
	
	protected boolean choqueArriba() { // Método que detecta si la pala se ha chocado con el borde de arriba
		return bordes.y + texture.getHeight() >= Gdx.graphics.getHeight();
	}
	protected boolean choqueAbajo() { // Método que detecta si la pala se ha chocado con el borde de abajo
		return bordes.y <= 0;
	}
}
