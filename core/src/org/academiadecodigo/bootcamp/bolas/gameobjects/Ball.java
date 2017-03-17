package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 3/16/17.
 */
public class Ball {

    public static final float BALL_DENSITY = 1;
    public static final float BALL_FRICTION = 1;


    private CircleShape circle;
    private Sprite sprite;
    private Body body;
    private BodyDef bodyDef;


    private float joltX;

    private float radius;

    private float x;
    private float y;

    private boolean canJump = true;
    private float joltY;
    private float maxHorizontalSpeed;

    private Sound oggSound;

    // Create a circle shape and set its radius to 6


    // Create our fixture and attach it to the body


    public Ball(float x, float y, float radius, World world){
        this.x = x;
        this.y = y;
        this.radius = radius;

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.x, this.y);

        Texture tex = new Texture("core/assets/images/ball.png"); // ASSET DO CARALHO

        this.sprite = new Sprite(tex);


        this.sprite.setSize(this.radius*2, this.radius*2);
        this.sprite.setPosition(bodyDef.position.x,bodyDef.position.y);
        this.sprite.setOrigin(sprite.getX()/2,sprite.getY()/2);
        this.sprite.setOriginCenter();

        this.body = world.createBody(bodyDef);

        circle = new CircleShape();

        circle.setRadius(this.radius);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = BALL_DENSITY;
        fixtureDef.friction = BALL_FRICTION;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        body.setUserData(this);

        oggSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sound/sfx_laser1.ogg"));

    }


    public void handleInput() {

        float xdelta = 0;
        float ydelta = 0f;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xdelta = this.joltX;
//            System.out.println("xdelta" + xdelta);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (this.canJump) {
                oggSound.play();
                ydelta = this.joltY;
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            xdelta = - this.joltX;

            //System.out.println("xdelta" + xdelta);
        }

        this.body.applyForceToCenter(0, ydelta, true);

        if (this.body.getLinearVelocity().x > this.maxHorizontalSpeed) {
            return;
        }

        this.body.applyForceToCenter(xdelta, 0, true);
    }

    public void render(SpriteBatch batch) {


        this.x = this.body.getPosition().x - this.sprite.getWidth() / 2;
        this.y = this.body.getPosition().y - this.sprite.getHeight() / 2;

        this.sprite.setPosition(this.x, this.y);
        this.sprite.rotate(this.body.getAngle());

        this.sprite.draw(batch);


    }


    public void setHorizontalJolt(float jolt) {
        this.joltX = jolt;
    }

    public void setVerticalJolt(float jolt) {
        this.joltY = jolt;
    }

    public void setMaxHorizontalSpeed(float speed){
        this.maxHorizontalSpeed = speed;
    }


    public float getRadius() {
        return radius;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void dispose() {
        this.sprite.getTexture().dispose();
    }

    public void setCanJump(boolean input) {
        this.canJump = input;
    }

    public Body getBody() {
        return this.body;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

}

