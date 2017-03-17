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
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State implements com.badlogic.gdx.physics.box2d.ContactListener{

    public static final Vector2 GRAVITY = new Vector2(0, -100);

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

        this.world = new World(GRAVITY, true);
        this.world.setContactListener(this);

        this.platform = new Platform(5, 0.25f, 10, 0.5f, world);
        this.platform.setSpeed(0,1f);

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

        background.render(batch);
        this.platform.render(batch);
        this.ball.render(batch);
//        this.debugRenderer.render(world,camera.combined);

    }

    @Override
    public void dispose() {

    }

    public void setDelay(float dt) {
        this.delay = dt;
    }

    @Override
    public void beginContact(Contact contact) {

//        Fixture fixA = contact.getFixtureA();
//        Fixture fixB = contact.getFixtureB();
//
//        System.out.println(fixB.getFriction());
//        fixB.getBody().setLinearVelocity(fixA.getBody().getLinearVelocity());

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
