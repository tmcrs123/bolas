package org.academiadecodigo.bootcamp.bolas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.bootcamp.bolas.Bolas;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.width = 675;
		config.height = 1078/2;
		new LwjglApplication(new Bolas(), config);

	}
}
