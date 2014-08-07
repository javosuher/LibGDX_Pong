package com.mygdx.Pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*AplicationListener es una interfaz que proporciona metodos que se llaman cada vez que es necesario
* crear, pausar, continuar, renderizar o destruir una aplicacion, nos permite ademas manejar los graficos
*/

/* Game es una clase que implementa de AplicationListener y que permite delegar en una Screen,
* es decir, que permite a la alicacion tener y manejar facilmente varias ventanas
*/

// Main es la clase principal de nuestro juego, es decir, es la primera que se llama cuando se ejecuta.
public class Main extends Game {
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.

	@Override
	public void create () { // Método que se llama cuando se ejecuta el juego
		batch = new SpriteBatch();
		Gdx.input.setCatchBackKey(true); // Bloquea el boton "Back" de android para que se tenga que salir del juego usando el boton "Exit"
		Screens.juego = this;
		Screens.GAMESCREEN = new GameScreen(this); // Se inicializan las pantallas
		Screens.MAINSCREEN = new MainScreen(this);
		setScreen(Screens.MAINSCREEN); // Establecemos MAINSCREEN como nuestra pantalla principal
	}
	
	@Override
	public void dispose() { // Método para eliminar recursos.
		batch.dispose();
		Screens.GAMESCREEN.dispose();
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
}
