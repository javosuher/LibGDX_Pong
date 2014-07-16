package com.mygdx.Pong;

import com.badlogic.gdx.Game;

/*AplicationListener es una interfaz que proporciona metodos que se llaman cada vez que es necesario
* crear, pausar, continuar, renderizar o destruir una aplicacion, nos permite ademas manejar los graficos
*/

/* Game es una clase que implementa de AplicationListener y que permite delegar en una Screen,
* es decir, que permite a la alicacion tener y manejar facilmente varias ventanas
*/

// Main es la clase principal de nuestro juego, es decir, es la primera que se llama cuando se ejecuta.
public class Main extends Game {
	public AbstractScreen GAMESCREEN; // Pantalla principal del juego

	@Override
	public void create () { // Método que se llama cuando se ejecuta el juego
		GAMESCREEN = new GameScreen(this);
		setScreen(GAMESCREEN); // Establecemos GAMESCREEN como nuestra pantalla principal
	}
	
	@Override
	public void dispose() { // Método para eliminar recursos.
		GAMESCREEN.dispose();
	}
}
