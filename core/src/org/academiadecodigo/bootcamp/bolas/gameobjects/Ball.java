package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by codecadet on 3/16/17.
 */
public class Ball {

    public CircleShape circle;
    public Sprite sprite;
    public Body body;
    public BodyDef bodyDef;
    private SpriteBatch batch;

    // Create a circle shape and set its radius to 6


    // Create our fixture and attach it to the body

    public Ball(World world){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(30, 30);

        Texture tex = new Texture("core/assets/images/ball.png"); // ASSET DO CARALHO

        this.sprite = new Sprite(tex);
        this.sprite.setSize(1f, 1f);
        this.sprite.setPosition(30,30);

        this.body = world.createBody(bodyDef);

        circle = new CircleShape();
        circle.setRadius(1f);

        batch = new SpriteBatch();


        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit
        body.createFixture(fixtureDef);
    }

    public static final float BALL_SPEED_PHYSICS = 10000;

    private int x;
    private int y;

    //radius
    private int width;
    private int height;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

