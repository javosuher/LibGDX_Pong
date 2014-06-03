package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen { // Pantalla principal del juego.
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	private Texture texture; // Una Texture es una clase que envuelve una textura estandar de OpenGL, se utiliza para imagenes simples
	private Paddle Lpaddle, Rpaddle; // Nos creamos las nuevas palas
	
	public GameScreen(Main main) {
		super(main);
	}
	
	@Override
	public void show() { // Método que se llama cuando se establece esta pantalla como actual
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("pongcampo.png"));
		Texture texturePala = new Texture(Gdx.files.internal("pala.png")); // Cogemos la textura para calcular el alto de la pala y centrarla en la pantalla
		Lpaddle = new LeftPaddle(80, Gdx.graphics.getHeight() / 2 - texturePala.getHeight() / 2);
		Rpaddle = new RightPaddle(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 2 - texturePala.getHeight() / 2);
	}
	
	@Override
	public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
		//Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		Lpaddle.update(); // Método que permitirá actualizar los valores de la pala, así como detectar si estamos pulsando el botón adecuado para para moverla.
		
		batch.begin(); // Aqui se comienza a dibujar
		batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Pintamos el fondo de pantalla
		Lpaddle.draw(batch); // Pintamos pala izquierda
		Rpaddle.draw(batch); // Pintamos pala derecha
		batch.end(); // Se termina de dibujar
	}
}
