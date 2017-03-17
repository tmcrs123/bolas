package org.academiadecodigo.bootcamp.bolas.state.testingstates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;
import org.academiadecodigo.bootcamp.bolas.state.GameStateManager;
import org.academiadecodigo.bootcamp.bolas.state.State;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlatformTestingState extends State{

    private Platform platform;
    private World world;


    public PlatformTestingState(GameStateManager manager) {
        super(manager);

        this.camera = new OrthographicCamera(10,10);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.world = new World(new Vector2(0, 0), true);

        this.platform = new Platform(1, 9, 1, 1, world);
        this.platform.setSpeed((float) 0, (float) -2);
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

//        if (this.platform.isOutsideBoundaries(0, -1, 10, 10)) {
//            this.platform.dispose();
//            this.platform = new Platform(1, 10, 1, 1, world);
//            this.platform.setSpeed((float) 0, (float) -2);
//        }

        world.step(1/60f, 6, 2);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        this.platform.render(batch);
    }

    @Override
    public void dispose() {

    }

}
