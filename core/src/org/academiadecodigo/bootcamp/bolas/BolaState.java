package org.academiadecodigo.bootcamp.bolas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Ball;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.State;

/**
 * Created by codecadet on 3/16/17.
 */
public class BolaState extends State{

    Ball ball;
    private OrthographicCamera camera;
    World world;
    GameStateManager stateManager;
    Box2DDebugRenderer debugRenderer;



    public static final float CAMERA_VIEWFINDER_WIDTH_METERS = 40;
    public static final float CAMERA_VIEWFINDER_HEIGHT_METERS = 40;


    public BolaState(GameStateManager gameStateManager){
        super(gameStateManager);
        this.gameStateManager = new GameStateManager();
        world = new World(new Vector2(0,0),true);
        ball = new Ball(world);
        camera = new OrthographicCamera(CAMERA_VIEWFINDER_WIDTH_METERS, CAMERA_VIEWFINDER_HEIGHT_METERS);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        debugRenderer = new Box2DDebugRenderer();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(1/60f, 6, 2);

    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if ((ball.body.getPosition().y ) < ((ball.circle.getRadius()))){
            ball.body.setLinearVelocity(0,0);
            //ball.sprite.setPosition(0, 0);
            //return;
        } else {
            System.out.println(ball.body.getPosition().y);

            float xdelta = 0;
            float ydelta = -4f;

            ball.body.setLinearVelocity(0, ydelta);


            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                xdelta = 4f;
                System.out.println("xdelta" + xdelta);
                ball.body.setLinearVelocity(xdelta, ydelta);

            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                xdelta = -4f;
                System.out.println("xdelta" + xdelta);
                ball.body.setLinearVelocity(xdelta, ydelta);

            }

            ball.sprite.setPosition(ball.body.getPosition().x, ball.body.getPosition().y);
            //sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
        }
        //debugRenderer.render(world, camera.combined);
        ball.sprite.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
