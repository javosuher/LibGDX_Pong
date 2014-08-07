package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball { // Esta clase representa la bola del pong
	private static final float SPEED = 200;
	
	private Texture texture;
	private Rectangle bordes; // Objeto que nos determinará la posición de la bola y permite detectar colisiones con otros rectangulos.
	private int direccionX, direccionY; // Permite invertir la dirección de la bola en el eje x e y respectivamente
	private float posicionOriginalX, posicionOriginalY; // Guarda la posición original de la bola cuando esta se crea.
	private Sound sonido; // Sonido cuando colisiona con las palas
	
	public Ball(float x, float y) {
		texture = new Texture(Gdx.files.internal("bola.png"));
		bordes = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
		direccionX = direccionY = 1; // Lo ponemos a 1 los dos para que se mueva al inicio hacia arriba a la derecha
		posicionOriginalX = x;
		posicionOriginalY = y;
		sonido = Gdx.audio.newSound(Gdx.files.internal("golpe.ogg"));
	}
	
	public void draw(SpriteBatch batch) { // Permite dibujar la bola para que la visualice el usuario.
		batch.draw(texture, bordes.x, bordes.y, texture.getWidth(), texture.getHeight());
	}
	
	public void update(Paddle Lpaddle, Paddle Rpaddle) {
		float delta = Gdx.graphics.getDeltaTime(); // Número de segundos desde el anterior fotograma.
		if(choqueConParedes())
			direccionY = direccionY * -1; // Cambiamos la dirección en el eje y
		if(choqueConPalas(Lpaddle.getBordes(), Rpaddle.getBordes())) {
			direccionX = direccionX * -1; // Cambiamos la dirección en el eje x
			sonido.play(); // Reproducimos el sonido
		}
		
		bordes.x = bordes.x + SPEED * delta * direccionX; 
		bordes.y = bordes.y + SPEED * delta * direccionY;
	}
	
	private boolean choqueConParedes() { // Método que permite detectar si se choca arriba o abajo de la pantalla y ajusta la posición de la bola
		if(bordes.y + texture.getHeight() >= Gdx.graphics.getHeight()) { // Si choca arriba
			bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
			return true;
		}
		else if(bordes.y <= 0) { // Si choca abajo
			bordes.y = 0;
			return true;
		}
		else return false;
	}
	private boolean choqueConPalas(Rectangle Lpaddle, Rectangle Rpaddle) { // Método que permite detectar si se choca con las palas y ajusta la posición de la bola
		if(bordes.overlaps(Lpaddle)) { // Si se solapa con la pala izquierda
			bordes.x = Lpaddle.x + Lpaddle.getWidth();
			return true;
		}
		else if(bordes.overlaps(Rpaddle)) { // Si se solapa con la pala derecha
			bordes.x = Rpaddle.x - bordes.getWidth();
			return true;
		}
		else return false;
	}
	
	public void comprobarPosicionBola() { // Método que pone la bola en el centro si se sale de la pantalla por la izquierda o la derecha
		if(bordes.x < 0 || bordes.x > Gdx.graphics.getWidth()) {
			bordes.x = posicionOriginalX;
			bordes.y = posicionOriginalY;
		}
	}
	
	public Rectangle getBordes() { // Observador del rectangulo.
		return bordes;
	}
}
