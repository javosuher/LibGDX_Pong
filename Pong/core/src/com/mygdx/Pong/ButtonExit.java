package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ButtonExit extends Button { // Botón que permitirá salir del juego

	public ButtonExit(int x, int y) {
		super(x, y);
		texture = new Texture(Gdx.files.internal("BotonExit.png")); // Se asigna textura. Muy importante!!
	}

	@Override
	protected void funcionamiento() {
		Gdx.app.exit(); // Cierra la aplicación
	}
}