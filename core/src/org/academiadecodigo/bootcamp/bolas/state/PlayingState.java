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

    private static final float BALL_RADIUS = 0.5f ;
    private static final float PLATFORMS_PER_HEIGHT = 4;
    private static final float BOUNDARY_THICKNESS = 0.1f;
    private static final float INITIAL_BALL_SPAWN_X = 10;
    private static final float INITIAL_BALL_SPAWN_Y = 18;

    private float delay = 1;
    private Background background;

    private ComplexPlatform platform;
    private ComplexPlatform platform2;
    private World world;
    private Ball ball;
    private float ballSpeed;

    Box2DDebugRenderer debugRenderer;

    private Deque<ComplexPlatform> platforms;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.camera = new OrthographicCamera(20,20);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth, this.camera.viewportHeight);

        this.world = new World(GRAVITY, true);
        this.ball = new Ball(INITIAL_BALL_SPAWN_X, INITIAL_BALL_SPAWN_Y, BALL_RADIUS, this.world);

        this.ballSpeed = 10;

        this.ball.setSpeed(this.ballSpeed, this.ballSpeed);


        this.platforms = new LinkedList<>();
        this.initializePlatformList();


        debugRenderer = new Box2DDebugRenderer();

    }


    private void initializePlatformList() {

        for (int i = 1;  i <= PLATFORMS_PER_HEIGHT-1 ; i++ ) {

            ComplexPlatform pla= new ComplexPlatform(10, i * camera.viewportHeight / PLATFORMS_PER_HEIGHT, 10, BOUNDARY_THICKNESS, camera.viewportHeight/ PLATFORMS_PER_HEIGHT);
            pla.setHoleWidth(this.ball.getRadius() * 2 * 1.1f);
            pla.setHoleNumber(3);
            pla.constructPlatforms(world);

            pla.setSpeed(0,5f);

            this.platforms.add(pla);
        }


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

        this.checkForPlatformDeletion();
        this.renderPlatforms(batch);

        this.renderBall(batch);

//        this.debugRenderer.render (world,camera.combined);

    }

    private void renderPlatforms(SpriteBatch batch) {

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

            if (copyPlats.get(i).getY() > camera.viewportHeight / PLATFORMS_PER_HEIGHT) {

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
        ComplexPlatform newCp = new ComplexPlatform(cp.getX(), -cp.getHeight()/2, cp.getWidth(), cp.getHeight(), camera.viewportHeight/4);
        newCp.setHoleWidth(cp.getHoleWidth());

        newCp.setHoleNumber( cp.getHoleNumber() );

        newCp.constructPlatforms(this.world);
        newCp.setSpeed(cp.getSpeedX(), cp.getSpeedY());
        return newCp;
    }


    private void renderBall(SpriteBatch batch) {

        if (this.ball == null) {
            return;
        }

        if (this.ball.getY() > camera.viewportHeight) {
            this.dispose();
            this.lostGame();
            return;
        }


        this.ball.render(batch);
    }

    private void lostGame() {
       System.exit(0);
    }

    @Override
    public void dispose() {

        for (ComplexPlatform cp : this.platforms) {
            cp.dispose();
        }

        this.ball.dispose();
    }

    public void setDelay(float dt) {
        this.delay = dt;
    }

}
