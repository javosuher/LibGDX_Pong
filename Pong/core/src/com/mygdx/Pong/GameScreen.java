package com.mygdx.Pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen { // Pantalla principal del juego.
	private SpriteBatch batch; // "Grupo de Sprites (imagenes)" nos permite dibujar rectagulos como referencias a texturas, es necesario para mostrar todo por pantalla.
	private Texture texture; // Una Texture es una clase que envuelve una textura estandar de OpenGL, se utiliza para imagenes simples
	private Paddle Lpaddle, Rpaddle; // Nos creamos las nuevas palas
	private Ball ball; // La pelota del pong
	private BitmapFont font; // Nos permite introducir texto en en el juego.
	private Preferences preferencias; // Permite almacenar información en un fichero xml, de manera que el juego pueda extraerlos y sobreescribirlos.
	private int puntuacion, puntuacionMaxima; // Las dos puntuaciones del juego.
	
	public GameScreen(Main main) {
		super(main);
		preferencias = Gdx.app.getPreferences("-_PreferenciasPong_-"); // Abrimos el fichero, y si no está creado se genera vacio automáticamente.
		puntuacionMaxima = preferencias.getInteger("puntuacionMaxima"); // Se extrae el valor del campo "puntuacionMaxima"
	}
	
	@Override
	public void show() { // Método que se llama cuando se establece esta pantalla como actual
		batch = main.getBatch();
		texture = new Texture(Gdx.files.internal("pongcampo.png"));
		Texture textureBola = new Texture(Gdx.files.internal("bola.png")); // Cogemos la textura para calcular el alto y ancho de la bola y centrarla en la pantalla
		ball = new Ball(Gdx.graphics.getWidth() / 2 - textureBola.getWidth() / 2, Gdx.graphics.getHeight() / 2 - textureBola.getHeight() / 2);
		Texture texturePala = new Texture(Gdx.files.internal("pala.png")); // Cogemos la textura para calcular el alto de la pala y centrarla en la pantalla
		Lpaddle = new LeftPaddle(80, Gdx.graphics.getHeight() / 2 - texturePala.getHeight() / 2);
		Rpaddle = new RightPaddle(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 2 - texturePala.getHeight() / 2, ball);
		font = new BitmapFont(Gdx.files.internal("ubuntu.fnt"), Gdx.files.internal("ubuntu.png"), false); // Creamos la fuente a partir de la imagen y el fichero de configuración de la font
		font.setColor(Color.ORANGE); // La ponemos a color naranja.
		puntuacion = 0;
	}
	
	@Override
	public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
		//Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
		// gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
		//En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo
		
		updatePuntuación(); // Actualizamos los valores de la puntuación.
		
		Lpaddle.update(); // Método que permitirá actualizar los valores de la pala, así como detectar si estamos pulsando el botón adecuado para para moverla.
		Rpaddle.update(); // Actualizamos los valores de la pala derecha, para que se mueva en la pantalla.
		ball.update(Lpaddle, Rpaddle); // Permite que la bola se mueva, y al pasarle las dos palas podemos detectar la colisión con ellas.
		
		salirMenu(); // Permite salir al menú principal
		
		batch.begin(); // Aqui se comienza a dibujar
		batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Pintamos el fondo de pantalla
		ball.draw(batch); // Pintamos la bola
		Lpaddle.draw(batch); // Pintamos pala izquierda
		Rpaddle.draw(batch); // Pintamos pala derecha
		font.draw(batch, "PM " + Integer.toString(puntuacionMaxima), Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4 , Gdx.graphics.getHeight() - 5);
		font.draw(batch, "P " + Integer.toString(puntuacion), Gdx.graphics.getWidth() / 4 , Gdx.graphics.getHeight() - 5);
		batch.end(); // Se termina de dibujar
	}
	
	private void updatePuntuación() {
		if(ball.getBordes().overlaps(Lpaddle.getBordes())) { // Si colisiona la bola con la pala izquierda
			puntuacion = puntuacion + 1;
			if(puntuacion > puntuacionMaxima) // Si la puntuación es mayor que la puntuacion máxima.
				puntuacionMaxima = puntuacion;
		}
		if(ball.getBordes().x <= 0) // Si se sale la pelota por el extremo izquierdo
			puntuacion = 0;
		
		ball.comprobarPosicionBola(); // Colocamos la bola en su posicion si se sale del escenario.
	}
	
	private void salirMenu() {
		if(Gdx.input.isKeyPressed(Keys.ESCAPE) || Gdx.input.isKeyPressed(Keys.BACK)) // Si se pulsa el botón "escape" en PC o "Back" en android
			Screens.juego.setScreen(Screens.MAINSCREEN); // Volvemos al menú principal
	}
	
	@Override
	public void hide() { // Método que se ejecuta cuando se oculta esta pantalla
		font.dispose();
		texture.dispose();
	}
	
	@Override
	public void dispose() { // Método para eliminar recursos
		preferencias.putInteger("puntuacionMaxima", puntuacionMaxima); // Guardamos en el fichero el valor de la puntuación máxima.
		preferencias.flush(); // Automaticamente guarda todo lo introducido en el fichero.
	}
}
