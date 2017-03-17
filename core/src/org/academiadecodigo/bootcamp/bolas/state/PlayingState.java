package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Background;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Ball;
import org.academiadecodigo.bootcamp.bolas.gameobjects.ComplexPlatform;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State{

    public static final Vector2 GRAVITY = new Vector2(0, -100);

    private float delay = 1;
    private Background background;

    private ComplexPlatform platform;
    private World world;
    private Ball ball;
    Box2DDebugRenderer debugRenderer;

    private Deque<ComplexPlatform> platforms;

    private boolean add;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(20,20);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth);

        this.world = new World(GRAVITY, true);
        this.ball = new Ball(this.world);
        this.ball.setXSpeed(10);
        this.ball.setYSpeed(10);

        this.platform = new ComplexPlatform(10, 2.25f, 10, 0.5f,  world);
        this.platform.setHoleWidth(this.ball.getRadius() * 2 * 1.1f);
        this.platform.setHoleNumber(3);
        this.platform.constructPlatforms(world);


        this.platform.setSpeed(0,5f);

        this.platforms = new LinkedList<>();
        this.platforms.add(this.platform);

        debugRenderer = new Box2DDebugRenderer();
        this.add = true;

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

        this.checkForPlatformDeletion();
        this.renderComplex(batch);

//        background.render(batch);

        this.ball.render(batch);

//        this.debugRenderer.render(world,camera.combined);

    }

    private void renderComplex(SpriteBatch batch) {

        for (ComplexPlatform cp : this.platforms) {
            cp.render(batch);
        }
    }

    private void checkForPlatformDeletion() {

        List<ComplexPlatform> copyPlats = new LinkedList<>(this.platforms);

        for (int i = 0; i < copyPlats.size(); i++) {
            System.out.println(copyPlats.get(i).getY());

            if (copyPlats.get(i).getY() > camera.viewportHeight) {
                copyPlats.get(i).dispose();
                this.platforms.remove(copyPlats.get(i));
                continue;
            }

            if (copyPlats.get(i).getY() > camera.viewportHeight / 4) {

                ComplexPlatform platform = null;

                for (ComplexPlatform d : copyPlats) {
                    if (d.getY() < copyPlats.get(i).getY()) {
                        platform = d;
                    }
                }

                if (platform != null) {
                    continue;
                }

                ComplexPlatform newCp = this.constructSimilarComplexPlatform(copyPlats.get(i));
                this.platforms.add(newCp);

            }

        }
    }


    public ComplexPlatform constructSimilarComplexPlatform(ComplexPlatform cp) {
        ComplexPlatform newCp = new ComplexPlatform(cp.getX(), -cp.getHeight()/2, cp.getWidth(), cp.getHeight());
        newCp.setHoleWidth(cp.getHoleWidth());

        newCp.setHoleNumber( cp.getHoleNumber() );

        newCp.constructPlatforms(this.world);
        newCp.setSpeed(cp.getSpeedX(), cp.getSpeedY());
        return newCp;
    }

    @Override
    public void dispose() {
        this.platform.dispose();

        for (ComplexPlatform cp : this.platforms) {
            cp.dispose();
        }
    }

    public void setDelay(float dt) {
        this.delay = dt;
    }

}
