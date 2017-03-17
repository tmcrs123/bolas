package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import org.academiadecodigo.bootcamp.bolas.gameobjects.*;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Physics.PlatformBallContactListener;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class PlayingState extends State {

    public static final Vector2 GRAVITY = new Vector2(0, -200);

    private static final float BALL_RADIUS = 0.5f;
    private static final float PLATFORMS_PER_HEIGHT = 4;
    private static final float INITIAL_BALL_SPAWN_X = 10;
    private static final float INITIAL_BALL_SPAWN_Y = 12;

    private static final float CAMERA_VIEWPOINT_WIDTH = 20;
    private static final float CAMERA_VIEWPOINT_HEIGHT = 20;

    private static final float PLATFORM_SPAWN_X = CAMERA_VIEWPOINT_WIDTH / 2;
    private static final float PLATFORM_WIDTH = CAMERA_VIEWPOINT_WIDTH;
    private static final float PLATFORM_HEIGHT = CAMERA_VIEWPOINT_HEIGHT / 20;

    private static final float PLATFORM_SPEEDUP_UNIT = 0.00001f;

    private static final float BOUNDARY_HEIGHT = CAMERA_VIEWPOINT_HEIGHT / PLATFORMS_PER_HEIGHT;

    private static final float LIFE_COUNTER_Y = CAMERA_VIEWPOINT_HEIGHT-1;
    private static final float LIFE_COUNTER_X = 0;

    private static final float LIFE_COUNTER_WIDTH = 1;

    private static final float BALL_HORIZ_JOLT = 20;
    private static final float BALL_VERT_JOLT_GRAV_MULT = 10;

    private static final float INITIAL_PLATFORM_VELOCITY = 5;
    private static final float INITIAL_HOLE_RADIUS_MULT = 1.5f;
    private static final int INITIAL_HOLE_AMOUNT = 2;

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

    Box2DDebugRenderer debugRenderer;
    private int score;

    private PlatformBallContactListener contactListener;

    private Music oggMusic;
    private Sound oggSound;


    public PlayingState(GameStateManager manager) {

        super(manager);

        this.contactListener = new PlatformBallContactListener();

        this.initializeLifeCounter();

        this.initializeCamera();

        this.background = new Background(this.camera.viewportWidth, this.camera.viewportHeight);
        this.backgroundDelay = 1;

        this.world = new World(GRAVITY, true);

        this.ballSpeed = 10;
        this.initializeBall();

        this.platforms = new LinkedList<>();

        this.initializePlatformList();

        this.world.setContactListener(this.contactListener);


        debugRenderer = new Box2DDebugRenderer();

        oggMusic = Gdx.audio.newMusic(Gdx.files.internal("core/assets/sound/Undertale - Megalovania.ogg"));
        oggMusic.play();

        oggSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sound/sfx_lose.ogg"));


    }

    private void initializeCamera() {
        this.camera = new OrthographicCamera(CAMERA_VIEWPOINT_WIDTH, CAMERA_VIEWPOINT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        this.camera.update();
    }

    private void initializeLifeCounter() {
        this.playerLives = 3;
        this.lifeCounter = new LifeCounter(LIFE_COUNTER_X, LIFE_COUNTER_Y, LIFE_COUNTER_WIDTH, LIFE_COUNTER_WIDTH);
        this.lifeCounter.setLives(this.playerLives);
    }

    private void initializeBall() {
        this.ball = new Ball(INITIAL_BALL_SPAWN_X, INITIAL_BALL_SPAWN_Y, BALL_RADIUS, this.world);
        this.ball.setHorizontalJolt(BALL_HORIZ_JOLT);
        this.ball.setMaxHorizontalSpeed(this.ballSpeed);
        this.ball.setVerticalJolt(GRAVITY.y * -BALL_VERT_JOLT_GRAV_MULT);
        this.contactListener.setBall(this.ball);

    }


    private void initializePlatformList() {

        this.platformSpeed = INITIAL_PLATFORM_VELOCITY;
        this.holeSizeMultiplier = INITIAL_HOLE_RADIUS_MULT;
        this.holeAmount = INITIAL_HOLE_AMOUNT;

        for (int i = 0; i < PLATFORMS_PER_HEIGHT + 1; i++) {

            ComplexPlatform pla = new ComplexPlatform(PLATFORM_SPAWN_X, i * camera.viewportHeight / PLATFORMS_PER_HEIGHT, PLATFORM_WIDTH, PLATFORM_HEIGHT, BOUNDARY_HEIGHT);
            pla.setHoleWidth(this.ball.getRadius() * 2 * this.holeSizeMultiplier);
            pla.setHoleNumber(this.holeAmount);
            pla.constructPlatforms(world);

            pla.setSpeed(0, this.platformSpeed);

            this.platforms.add(pla);
        }

        this.contactListener.setPlatforms(this.platforms);

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

//        if( ((int) Math.log10(score)) % 3 == 0 ){
//            System.out.println(((int) Math.log10(score)));
            platformSpeed += PLATFORM_SPEEDUP_UNIT * score;
//            System.out.println("SPEED UP YOU FUCK");
            for(ComplexPlatform plat : platforms){
                plat.setSpeed(0,platformSpeed);
            }
//        }

        score++;


    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        this.background.setScore(this.score);
        this.background.render(batch);

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

        int random = (int) Math.floor(Math.random() * 3)+1;

        newCp.setHoleNumber(random);

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
            oggSound.play();
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

        oggMusic.stop();
        oggSound.play(5f);
        gameStateManager.pop(this);
        gameStateManager.push(new GameOverState(gameStateManager,"Carlos",score));
    }

    @Override
    public void dispose() {

        for (ComplexPlatform cp : this.platforms) {
            cp.dispose();
        }

        this.lifeCounter.dispose();
        this.ball.dispose();
    }


}
