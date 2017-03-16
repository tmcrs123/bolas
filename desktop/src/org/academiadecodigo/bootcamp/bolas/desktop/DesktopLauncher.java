package org.academiadecodigo.bootcamp.bolas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.bootcamp.bolas.BolaState;
import org.academiadecodigo.bootcamp.bolas.Bolas;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 400;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		new LwjglApplication(new Bolas(), config);

	}
}
