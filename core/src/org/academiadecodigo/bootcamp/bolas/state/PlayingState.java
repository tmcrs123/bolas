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
        this.sprite.setSize(1,1);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        this.x += 1 * dt;
        System.out.println(this.x);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.sprite, this.x , this.y );
    }

    @Override
    public void dispose() {

    }
}
