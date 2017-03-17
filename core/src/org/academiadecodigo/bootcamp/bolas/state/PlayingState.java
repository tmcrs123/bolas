package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import org.academiadecodigo.bootcamp.bolas.gameobjects.*;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Physics.PlatformBallContactListener;
import org.academiadecodigo.bootcamp.bolas.state.testingstates.MainMenuState;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State {

    public static final Vector2 GRAVITY = new Vector2(0, -75);

    private static final float BALL_RADIUS = 0.5f;
    private static final float PLATFORMS_PER_HEIGHT = 4;
    private static final float INITIAL_BALL_SPAWN_X = 10;
    private static final float INITIAL_BALL_SPAWN_Y = 12;

    private static final float CAMERA_VIEWPOINT_WIDTH = 20;
    private static final float CAMERA_VIEWPOINT_HEIGHT = 20;

    private static final float PLATFORM_SPAWN_X = CAMERA_VIEWPOINT_WIDTH / 2;
    private static final float PLATFORM_WIDTH = CAMERA_VIEWPOINT_WIDTH;
    private static final float PLATFORM_HEIGHT = CAMERA_VIEWPOINT_HEIGHT / 20;

    private static final float BOUNDARY_HEIGHT = CAMERA_VIEWPOINT_HEIGHT / PLATFORMS_PER_HEIGHT;

    private static final float LIFE_COUNTER_Y = 19;
    private static final float LIFE_COUNTER_X = 19;

    private static final float LIFE_COUNTER_WIDTH = 1;

    private static final float BALL_JOLT_GRAV_MULT = 10;

    private Background background;
    private Deque<ComplexPlatform> platforms;
    private World world;
    private Ball ball;
    private LifeCounter lifeCounter;

    private int playerLives;

    private float ballSpeed;
    private float platformSpeed;

    private float holeSizeMultiplier;
    private int holeAmount;

    private float backgroundDelay;

    private PowerUp speedUp;

    Box2DDebugRenderer debugRenderer;
    private int score;

    private PlatformBallContactListener contactListener;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.initializeLifeCounter();

        this.camera = new OrthographicCamera(CAMERA_VIEWPOINT_WIDTH, CAMERA_VIEWPOINT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        this.camera.update();

        this.background = new Background(this.camera.viewportWidth, this.camera.viewportHeight);
        this.backgroundDelay = 1;

        this.world = new World(GRAVITY, true);

        this.initializeBall();

        this.platforms = new LinkedList<>();
        this.platformSpeed = 5;
        this.holeSizeMultiplier = 1.2f;
        this.holeAmount = 2;

        this.initializePlatformList();


        this.contactListener = new PlatformBallContactListener(this.platforms, this.ball);
        this.world.setContactListener(this.contactListener);


        debugRenderer = new Box2DDebugRenderer();

    }

    private void initializeLifeCounter() {
        this.playerLives = 0;
        this.lifeCounter = new LifeCounter(LIFE_COUNTER_X, LIFE_COUNTER_Y, LIFE_COUNTER_WIDTH, LIFE_COUNTER_WIDTH);
        this.lifeCounter.setLives(this.playerLives);
    }

    private void initializeBall() {
        this.ball = new Ball(INITIAL_BALL_SPAWN_X, INITIAL_BALL_SPAWN_Y, BALL_RADIUS, this.world);
        this.ballSpeed = 20;
        this.ball.setHorizontalJolt(this.ballSpeed);
        this.ball.setMaxHorizontalSpeed(20);
        this.ball.setVerticalJolt(GRAVITY.y * -BALL_JOLT_GRAV_MULT);
    }


    private void initializePlatformList() {

        for (int i = 0; i < PLATFORMS_PER_HEIGHT + 1; i++) {

            ComplexPlatform pla = new ComplexPlatform(PLATFORM_SPAWN_X, i * camera.viewportHeight / PLATFORMS_PER_HEIGHT, PLATFORM_WIDTH, PLATFORM_HEIGHT, BOUNDARY_HEIGHT);
            pla.setHoleWidth(this.ball.getRadius() * 2 * this.holeSizeMultiplier);
            pla.setHoleNumber(this.holeAmount);
            pla.constructPlatforms(world);

            pla.setSpeed(0, this.platformSpeed);

            this.platforms.add(pla);
        }


    }


    @Override
    public void handleInput() {

        if (!Gdx.input.isKeyJustPressed(Input.Keys.P) && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            this.background.start();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            this.background.stop();
        }

        this.ball.handleInput();

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);

        background.move(dt, (int) this.backgroundDelay);

    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        score++;

        background.render(batch);


        this.checkForPlatformDeletion();
        this.renderPlatforms(batch);

        this.renderBall(batch);

        this.renderLifeCounter(batch);


    }

    private void renderLifeCounter(SpriteBatch batch) {
        this.lifeCounter.render(batch);
    }


    private void checkForPlatformDeletion() {

        List<ComplexPlatform> copyPlats = new LinkedList<>(this.platforms);

        for (int i = 0; i < copyPlats.size(); i++) {

            if (copyPlats.get(i).getY() > camera.viewportHeight * (PLATFORMS_PER_HEIGHT + 1) / PLATFORMS_PER_HEIGHT) {
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

    private void renderPlatforms(SpriteBatch batch) {

        for (ComplexPlatform cp : this.platforms) {
            cp.render(batch);
        }
    }

    public ComplexPlatform constructSimilarComplexPlatform(ComplexPlatform cp) {
        ComplexPlatform newCp = new ComplexPlatform(cp.getX(), -cp.getHeight() / 2, cp.getWidth(), cp.getHeight(), cp.getBoundaryHeight());
        newCp.setHoleWidth(cp.getHoleWidth());

        newCp.setHoleNumber(cp.getHoleNumber());

        newCp.constructPlatforms(this.world);
        newCp.setSpeed(cp.getSpeedX(), cp.getSpeedY());
        return newCp;
    }


    private void renderBall(SpriteBatch batch) {

        if (this.ball == null) {
            return;
        }

        if (this.ball.getY() > camera.viewportHeight || this.ball.getY() < 0) {
            this.playerLives--;
            this.ball.dispose();

            this.initializeBall();

            if (this.playerLives < 0) {
                this.dispose();
                this.lostGame();
                return;
            }

            this.lifeCounter.setLives(this.playerLives);
        }


        this.ball.render(batch);

    }

    private void lostGame() {

        gameStateManager.pop(this);
        gameStateManager.push(new GameOverState(gameStateManager,score));
    }

    @Override
    public void dispose() {

        for (ComplexPlatform cp : this.platforms) {
            cp.dispose();
        }

        this.ball.dispose();
    }


}
