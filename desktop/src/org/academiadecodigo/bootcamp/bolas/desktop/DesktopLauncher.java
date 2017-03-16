package org.academiadecodigo.bootcamp.bolas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.bootcamp.bolas.BolaState;
import org.academiadecodigo.bootcamp.bolas.Bolas;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.height = 1078/2;
		config.width = 675;
		new LwjglApplication(new Bolas(), config);

	}
}
