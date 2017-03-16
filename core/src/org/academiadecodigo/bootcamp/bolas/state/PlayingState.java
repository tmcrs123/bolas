package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State {


    private Sprite sprite;
    private float x;
    private float y;

    public PlayingState(GameStateManager manager, Texture texture) {
        super(manager);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(5f,5f);
        this.sprite.setPosition(1,1);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        this.sprite.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
