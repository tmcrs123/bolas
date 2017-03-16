package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

        this.camera = new OrthographicCamera(10,5);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.sprite = new Sprite(texture);
        this.sprite.setSize(10f,10f);
        this.sprite.setPosition(0,-5f);
    }



    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        this.sprite.translate(0,dt);
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
