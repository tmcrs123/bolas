package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.bootcamp.bolas.FileEditor;

import java.io.IOException;

/**
 * Created by codecadet on 3/17/17.
 */
public class GameOverState extends State{

    private Sprite sprite;
    private Texture img;


    public GameOverState(GameStateManager gameStateManager,String playerName, int score){
        super(gameStateManager);
        FileEditor fileEditor = new FileEditor();
        img = new Texture("core/assets/images/logo.png");

        this.camera = new OrthographicCamera(20,20);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.sprite = new Sprite(img);
        this.sprite.setSize(30,30);
        this.sprite.setPosition(0,0);


        this.sprite = new Sprite(img);
        this.sprite.setSize(20,20);


        try {
            fileEditor.Writer(playerName,score);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sprite.draw(batch);

    }

    @Override
    public void dispose() {

    }
}
