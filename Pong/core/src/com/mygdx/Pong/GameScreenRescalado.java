package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreenRescalado extends AbstractScreen { // Pantalla principal del juego.
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	private Texture texture; // Una Texture es una clase que envuelve una textura estandar de OpenGL, se utiliza para imagenes simples
	private float escala; // Valor de escala de los elementos de la pantalla.

	public GameScreenRescalado(Main main) {
		super(main);
	}
	
	@Override
	public void show() { // Método que se llama cuando se establece esta pantalla como actual
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("pongcampo.png"));
	}
	
	@Override
	public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
		//Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		batch.begin(); // Aqui se comienza a dibujar
		batch.draw(texture, 0, 0, texture.getWidth() / escala, texture.getHeight() / escala);
		batch.end(); // Se termina de dibujar
	}
	
	@Override
	public void resize(int width, int height) { // Método que sirve para redimensionar la pantalla del juego, en este caso, el fondo de pantalla.
		float widthImage = texture.getWidth();
		float heightImage = texture.getHeight();
		float r = heightImage / widthImage;
		if(heightImage > height) { // Si el fondo es mas alto que el alto de pantalla
			heightImage = height;
			widthImage = heightImage / r;
		}
		if(widthImage > width) { // Si el fondo es mas ancho que el ancho de la pantalla
			widthImage = width;
			heightImage = widthImage * r;
		}
		escala = width / widthImage; // Guardamos la escala
	}
}
