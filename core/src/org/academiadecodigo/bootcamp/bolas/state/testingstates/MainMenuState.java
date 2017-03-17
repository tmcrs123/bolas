package org.academiadecodigo.bootcamp.bolas.state.testingstates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
    private String name;


    public MainMenuState(GameStateManager manager) {

        super(manager);


        texture = new Texture("core/assets/images/background.png");
        text = new Texture("core/assets/images/logo.png");
        tex = new Texture("core/assets/images/start.png");

        this.camera = new OrthographicCamera(20, 20);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        this.camera.update();

        this.sprite = new Sprite(texture);
        this.sprite.setSize(20, 20);
        this.sprite.setPosition(0, 0);

        this.logo = new Sprite(text);
        this.logo.setSize(14, 10);
        this.logo.setPosition(sprite.getWidth() / 6, sprite.getHeight() / 2);

        this.start = new Sprite(tex);
        this.start.setSize(18, 2);
        this.start.setPosition(sprite.getX() + 1.2f, sprite.getY() + sprite.getHeight() / 5);


    }

    @Override
    public void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            gameStateManager.pop(this);
            gameStateManager.push(new PlayingState(gameStateManager));

        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {


        System.out.println(name);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
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

