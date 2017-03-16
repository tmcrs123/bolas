package org.academiadecodigo.bootcamp.bolas.state.testingstates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.State;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlatformCollisionTestingState extends State {

    private Platform platform;
    private Platform platform2;
    private World world;

    public static int CONV = 1;

    private Box2DDebugRenderer debugRenderer;

    public PlatformCollisionTestingState(GameStateManager manager ) {
        super(manager);

        this.camera = new OrthographicCamera(10 * CONV, 10 * CONV);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.debugRenderer = new Box2DDebugRenderer();

        this.platform = new Platform(5, 1, 4, 1, world);
        this.platform2 = new Platform(2, 6, 4, 1, world);
        this.platform2.setSpeed(0,-3);

    }


    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

        world.step(dt, 6, 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        this.platform.render(batch);
//        this.debugRenderer.render(world,batch.getProjectionMatrix());
    }

    @Override
    public void dispose() {

    }

}
