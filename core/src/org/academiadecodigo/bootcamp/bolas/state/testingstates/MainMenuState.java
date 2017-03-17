package org.academiadecodigo.bootcamp.bolas.state.testingstates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.PlayingState;
import org.academiadecodigo.bootcamp.bolas.state.State;

/**
 * Created by codecadet on 3/17/17.
 */
public class MainMenuState extends State {


    private Texture texture;
    private Sprite sprite;
    private Texture text;
    private Sprite logo;
    private Texture tex;
    private Sprite start;


    public MainMenuState(GameStateManager manager) {
        super(manager);

        texture = new Texture("core/assets/images/background.png");
        text = new Texture("core/assets/images/logo.png");
        tex = new Texture("core/assets/images/start.png");

        this.sprite = new Sprite(texture);
        this.sprite.setSize(675,1078/2);
        this.sprite.setPosition(0,0);

        this.logo = new Sprite(text);
        this.logo.setSize(text.getWidth(),text.getHeight());
        this.logo.setPosition(sprite.getWidth()/6, sprite.getHeight()/2);

        this.start = new Sprite(tex);
        this.start.setSize(tex.getWidth(), tex.getHeight());
        this.start.setPosition(10,140);


    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            gameStateManager.pop(this);
            gameStateManager.push(new PlayingState(gameStateManager));
            dispose();
        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        logo.draw(batch);
        start.draw(batch);
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        logo.getTexture().dispose();
        start.getTexture().dispose();
    }
}
