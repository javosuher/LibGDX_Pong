package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;

public class RightPaddle extends Paddle { // Pala derecha del juego
	private Ball ball; // Tenemos la bola del juego para que automáticamente la siga.

	public RightPaddle(float x, float y, Ball bola) {
		super(x, y);
		ball = bola;
	}

	@Override
	public void update() { // Método donde se define el comportamiento de la pala derecha.
		float delta = Gdx.graphics.getDeltaTime(); // Número de segundos desde el anterior fotograma.
		float coordenadaPaddle = bordes.y + texture.getHeight() / 2; // Coordenada de la pala
		float coordenadaBall = ball.getBordes().y + ball.getBordes().getHeight() / 2; // Coordenada de la bola
		if(coordenadaPaddle >= coordenadaBall - 10 && coordenadaPaddle <= coordenadaBall + 10) // Si solo hay 20 de diferencia
			coordenadaBall = coordenadaPaddle; // Dejamos la pala quieta y evitamos saltos
		if(coordenadaBall < coordenadaPaddle) { // Si la coordenada de la bola es menor a la coordenada de la pala
			if(choqueAbajo()) // Si se choca con el borde de abajo de la pantalla
				bordes.y = 0;
			else
				bordes.y = bordes.y - SPEED * delta; // Hacemos que se mueva hacia abajo
		}
		if(coordenadaBall > coordenadaPaddle) { // Si la coordenada de la bola es mayor a la coordenada de la pala
			if(choqueArriba()) // Si se choca con el borde de arriba de la pantalla
				bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
			else
				bordes.y = bordes.y + SPEED * delta; // Hacemos que se mueva hacia arriba
		}
	}
}
