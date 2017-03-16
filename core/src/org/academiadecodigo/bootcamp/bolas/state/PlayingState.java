package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Background;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Ball;
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
    private Ball ball;
    Box2DDebugRenderer debugRenderer;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(10,5);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth);

        this.world = new World(new Vector2(0, -2f), true);

        this.platform = new Platform(5, 0.25f, 10, 0.5f, world);
//        this.platform.setSpeed(0,1);

        this.ball = new Ball(this.world);

        debugRenderer = new Box2DDebugRenderer();

    }



    @Override
    public void handleInput() {

        if(!Gdx.input.isKeyJustPressed(Input.Keys.P) && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            this.background.start();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            this.background.stop();
        }

        this.ball.handleInput();

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

//        background.render(batch);
//        this.platform.render(batch);
//        this.ball.render(batch);
        this.debugRenderer.render(world,camera.combined);

    }

    @Override
    public void dispose() {

    }

    public void setDelay(float dt) {
        this.delay = dt;
    }
}
