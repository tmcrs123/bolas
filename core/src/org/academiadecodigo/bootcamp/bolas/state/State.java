package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by codecadet on 3/16/17.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 vector3;
    protected GameStateManager gameStateManager;

    public State(GameStateManager manager) {
        this.gameStateManager = manager;
        this.vector3 = new Vector3();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}
