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
import org.academiadecodigo.bootcamp.bolas.gameobjects.PowerUp;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.State;

/**
 * Created by codecadet on 3/16/17.
 */
public class BolaState extends State {


    public static final Vector2 GRAVITY = new Vector2(0, -100);
    private PowerUp speedUp;
    private OrthographicCamera camera;
    World world;


    public static final float CAMERA_VIEWFINDER_WIDTH_METERS = 40;
    public static final float CAMERA_VIEWFINDER_HEIGHT_METERS = 40;


    public BolaState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.gameStateManager = new GameStateManager();
        World world = new World(GRAVITY,true);
        speedUp = new PowerUp(10,10,2,2,world);
        this.camera = new OrthographicCamera(40,40);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
//        world.step(1 / 60f, 6, 2);

    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);



        speedUp.render(batch);


//        this.debugRenderer.render(world,camera.combined);


    }

    @Override
    public void dispose() {

    }
}
