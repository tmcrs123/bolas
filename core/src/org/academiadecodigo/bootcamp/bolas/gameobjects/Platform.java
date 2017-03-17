package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.bootcamp.bolas.state.testingstates.PlatformCollisionTestingState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class Platform {


    private Sprite sprite;
    private Body body;

    private float height;
    private float width;
    private float x;
    private float y;

    private float rotation;

    private float speed;

    public Platform(float x, float y, float width, float height, World world ) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        this.sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/images/bar.png")));
        this.sprite.setPosition(this.x , this.y );
        this.sprite.setSize(this.width , this.height );
        this.initializePhysics(world);
    }

    private void initializePhysics(World world) {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(this.x, this.y);


        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f * this.width, 0.5f * this.height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 300000000f;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);



    }

    public void render(SpriteBatch batch) {


        this.sprite.setPosition(this.x  - this.width * 0.5f , this.y - this.height * 0.5f );
        System.out.println(this.rotation);
        this.sprite.setRotation(this.rotation);
        this.sprite.draw(batch);

        this.x = this.body.getPosition().x;
        this.y = this.body.getPosition().y;
        this.rotation = this.body.getAngle();

        System.out.println(this.body.getPosition());

    }


    public void setSpeed(float newSpeedX, float newSpeedY) {
        this.body.setLinearVelocity(newSpeedX,newSpeedY);
    }

//    public boolean isOutsideBoundaries(float x0, float y0, float xf, float yf) {
//        return this.body.getPosition().x > xf || this.body.getPosition().y > yf ||
//                this.body.getPosition().x < x0 || this.body.getPosition().y < y0;
//    }

    public void dispose() {
        this.sprite.getTexture().dispose();
        this.body.destroyFixture(this.body.getFixtureList().get(0));
    }


    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public float getX() {
        return this.body.getPosition().x;
    }

    public float getY() {
        return this.body.getPosition().y;


    }
}
