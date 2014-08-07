package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class Button { // Clase abstracta que representa un botón cuyo comportamiento sera diferente dependiendo de los distintos tipos de botones que se tengan
	protected Texture texture; // Textura del botón. Se asigna en el hijo
	protected Rectangle bordes; // El rectangulo que establece la posición, altura y anchura del botón
	
	protected float xMinima; // Estos atributos sirven para poner las coordenadas para pulsar el botón.
	protected float yMinima;
	protected float xMaxima;
	protected float yMaxima;

	public Button(int x, int y) {
		Texture textura = new Texture(Gdx.files.internal("BotonExit.png")); // Para poner el ancho y alto de los botones. Suponemos que todos serán igual
		bordes = new Rectangle(x, y, textura.getWidth(), textura.getHeight());
		
		// Permite asignar los bordes del botón para su correcto funcionamiento.
		xMinima = bordes.x;
		yMaxima = Gdx.graphics.getHeight() - bordes.y;
		xMaxima = bordes.x + bordes.width;
		yMinima = Gdx.graphics.getHeight() - (bordes.y + bordes.height);
	}

	public void draw(SpriteBatch batch) {
		batch.draw(texture, bordes.x, bordes.y, bordes.width, bordes.height);
	}

	public void update() {
		if(sePulsaElBoton())
			funcionamiento();
	}
	private boolean sePulsaElBoton() { // Esta función privada sirve para comprobar si se pulsa el botón.
		return Gdx.input.isTouched() && Gdx.input.getX() >= xMinima && Gdx.input.getX() <= xMaxima && // Devuelve true si se pulsa dentro de los límites
			   Gdx.input.getY() >= yMinima && Gdx.input.getY() <= yMaxima;
	}

	protected abstract void funcionamiento(); // Método que implementarán las clases hijas y contendrá el comportamiento deseado

	// Getters and Setters ------------------------------------------------------------------------

	public Rectangle getBordes() {
		return bordes;
	}

	public void setBordes(Rectangle bordes) {
		this.bordes = bordes;
	}
}