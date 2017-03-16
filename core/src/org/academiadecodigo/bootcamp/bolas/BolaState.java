package org.academiadecodigo.bootcamp.bolas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
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

    public static final float CAMERA_VIEWFINDER_WIDTH_METERS = 40;
    public static final float CAMERA_VIEWFINDER_HEIGHT_METERS = 20;


    public BolaState(GameStateManager gameStateManager){
        super(gameStateManager);
        this.gameStateManager = new GameStateManager();
        world = new World(new Vector2(0,0),true);
        ball = new Ball(world);
        camera = new OrthographicCamera(CAMERA_VIEWFINDER_WIDTH_METERS, CAMERA_VIEWFINDER_HEIGHT_METERS);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        float xdelta = 0;
        float ydelta = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xdelta = ball.BALL_SPEED_PHYSICS * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            xdelta = - ball.BALL_SPEED_PHYSICS * Gdx.graphics.getDeltaTime();
        }

        float xposO = ball.circle.getPosition().x;
        float ypos0 = ball.circle.getPosition().y;

        ball.body.applyForceToCenter(xdelta,ydelta, true);
        world.step(1/60f, 6, 2);
        float xpos = ball.circle.getPosition().x;
        float ypos = ball.circle.getPosition().y;


        ball.sprite.translate((xpos - xposO) ,  (ypos - ypos0));

//        debugRenderer.render(world, camera.combined);
        System.out.println(ball.circle.getPosition());
        System.out.println(ball.sprite.getX() + " " + ball.sprite.getY());

        //batch.begin();
        ball.sprite.draw(batch);
        //batch.end();

    }

    @Override
    public void dispose() {

    }
}
