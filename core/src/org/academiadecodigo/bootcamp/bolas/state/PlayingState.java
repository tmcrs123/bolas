package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Background;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State {


    private float x;
    private float y;
    private boolean start;
    private float delay = 1;
    private int speed = 0;
    private Background background;

    private Platform platform;
    private World world;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(10,5);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth);

        this.world = new World(new Vector2(0, 0), true);
        this.platform = new Platform(5, 0.5f, 10, 1, world);

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
        world.step(dt, 6, 2);

        background.move(dt, (int) this.delay);

    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        background.render(batch);
        this.platform.render(batch);

    }

    @Override
    public void dispose() {

    }

    public void setDelay(float dt) {
        this.delay = dt;
    }
}
