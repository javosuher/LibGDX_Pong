package com.mygdx.Pong;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class LeftPaddle extends Paddle { // Pala izquierda del juego

	public LeftPaddle(float x, float y) {
		super(x, y);
	}

	@Override
	public void update() { // Método donde se define el comportamiento de la pala izquierda.
		float delta = Gdx.graphics.getDeltaTime(); // Número de segundos desde el anterior fotograma.
		if(Gdx.app.getType() == ApplicationType.Desktop) // Si el juego se está ejecutando en escritorio
			inputDesktop(delta);
		else if(Gdx.app.getType() == ApplicationType.Android) // Si el juego se está ejecutando en android 
			inputAndroid(delta);
	}
	
	private void inputDesktop(float delta) { // Las entradas especificas para que el usuario pueda interactuar con el juego, ejecutandose en escritorio. Permite mover la pala.
		if(Gdx.input.isKeyPressed(Keys.W)) { // Si estamos pulsando la tecla W
			if(choqueArriba()) // Si se choca con el borde de arriba de la pantalla
				bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
			else
				bordes.y = bordes.y + SPEED * delta; // Hacemos que se mueva hacia arriba
		}
		if(Gdx.input.isKeyPressed(Keys.S)) { // Si estamos pulsando la tecla S
			if(choqueAbajo()) // Si se choca con el borde de abajo de la pantalla
				bordes.y = 0;
			else
				bordes.y = bordes.y - SPEED * delta; // Hacemos que se mueva hacia abajo
		}
	}
	
	private void inputAndroid(float delta) { // Las entradas especificas para que el usuario pueda interactuar con el juego, ejecutandose en android. Permite mover la pala.
		float coordenadaPaddle = bordes.y + texture.getHeight() / 2; // Cogemos la posición centrada de la pala en el eje y
		if(Gdx.input.isTouched()) { // Si estamos tocando la pantalla
			float coordenadaY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Cogemos la coordenada en el eje y donde estamos pulsando. Hay que tener en cuenta que el eje y es inverso cuando pulsamos el dedo.
			if(coordenadaPaddle >= coordenadaY - 5 && coordenadaPaddle <= coordenadaY + 5) // Si solo hay 10 de diferencia
				coordenadaY = coordenadaPaddle; // Dejamos la pala quieta y evitamos saltos
			if(coordenadaY < coordenadaPaddle) { // Si la coordenada donde se pulsa es menor a la coordenada de la pala
				if(choqueAbajo()) // Si se choca con el borde de abajo de la pantalla
					bordes.y = 0;
				else
					bordes.y = bordes.y - SPEED * delta; // Hacemos que se mueva hacia abajo
			}
			if(coordenadaY > coordenadaPaddle) { // Si la coordenada donde se pulsa es mayor a la coordenada de la pala
				if(choqueArriba()) // Si se choca con el borde de arriba de la pantalla
					bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
				else
					bordes.y = bordes.y + SPEED * delta; // Hacemos que se mueva hacia arriba
			}
		}
	}
}
