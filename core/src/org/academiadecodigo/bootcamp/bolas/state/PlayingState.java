package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.bullet.collision.Collision;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Background;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Ball;
import org.academiadecodigo.bootcamp.bolas.gameobjects.ComplexPlatform;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;
import org.academiadecodigo.bootcamp.bolas.gameobjects.PowerUp;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State{

    public static final Vector2 GRAVITY = new Vector2(0, -100);

    private float x;
    private float y;
    private boolean start;
    private float delay = 1;
    private int speed = 0;
    private Background background;

    private ComplexPlatform platform;
    private World world;
    private Ball ball;
    private PowerUp speedUp;
    Box2DDebugRenderer debugRenderer;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(20,10);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth);

        this.world = new World(GRAVITY, true);

        this.platform = new ComplexPlatform(10, 0.25f, 4, 0.5f,  world);
        this.platform.setHoleWidth(0.1f);
        this.platform.setHoleNumber(4);
        this.platform.constructPlatforms(world);
        this.platform.setSpeed(0,1f);


        this.speedUp = new PowerUp(5.0f, 2.5f, 2f, 2f, world);
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
//        this.speedUp.render(batch);
        this.debugRenderer.render(world, camera.combined);

    }

    @Override
    public void dispose() {
        this.platform.dispose();
    }

    public void setDelay(float dt) {
        this.delay = dt;
    }

}
