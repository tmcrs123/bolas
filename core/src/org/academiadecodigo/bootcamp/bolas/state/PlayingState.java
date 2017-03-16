package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Background;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State {


    private float x;
    private float y;
    private boolean start;
    private final int DELAY = 10;
    private int speed = 0;
    private Background background;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(10,5);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(10f);


    }



    @Override
    public void handleInput() {

        if(!Gdx.input.isKeyJustPressed(Input.Keys.P) && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            this.background.start();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            this.background.stop();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        background.move(dt, DELAY);

    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        background.render(batch);

    }

    @Override
    public void dispose() {

    }
}
