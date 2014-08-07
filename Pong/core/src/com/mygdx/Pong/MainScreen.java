package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends AbstractScreen { // Pantalla del menú principal del juego.
	public SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	private Button exit;
	private Button play;

	public MainScreen(Main main) {
		super(main);
	}
	
	@Override
	public void show() { // Método que se llama cuando se establece esta pantalla como actual
		batch = main.getBatch();
		Texture texture = new Texture(Gdx.files.internal("BotonExit.png")); // Cogemos la textura del botón para usar su ancho y alto
		int centroY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2; // Centro en el eje x de la pantalla centrando el botón
		int centroX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2; // Centro en el eje y de la pantalla centrando el botón
		exit = new ButtonExit(centroX, centroY - 50);
		play = new ButtonPlay(centroX, centroY + 50);
	}
	
	@Override
	public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
		//Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		exit.update(); // Comprobamos que se pulsan los botones
		play.update();
		
		batch.begin();
		exit.draw(batch); // Dibujamos el botón exit
		play.draw(batch); // Dibujamos el botón play
		batch.end();
	}

}
