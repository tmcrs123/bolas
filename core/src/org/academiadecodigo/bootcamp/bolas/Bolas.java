package org.academiadecodigo.bootcamp.bolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.PlayingState;
import org.academiadecodigo.bootcamp.bolas.state.testingstates.PlatformTestingState;

public class Bolas extends Game {
	SpriteBatch batch;
	Texture img;
	GameStateManager manager;
	World world;

	
	@Override
	public void create () {

		Box2D.init();

		batch = new SpriteBatch();

		img = new Texture("core/assets/images/ball.png");
		this.manager = new GameStateManager();
		this.manager.push(new BolaState(manager));
	}

	@Override
	public void render () {

		this.clearScreen();

		batch.begin();
        this.manager.update(Gdx.graphics.getDeltaTime());
        this.manager.render(batch);

		batch.end();
	}


	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

}
