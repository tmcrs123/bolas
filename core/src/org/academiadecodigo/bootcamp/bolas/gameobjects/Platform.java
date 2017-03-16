package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

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

    private float speed;

    public Platform(float x, float y, float width, float height, World world ) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;

        this.sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/images/bar.png")));
        this.sprite.setSize(this.width, this.height);
        this.sprite.setPosition(this.x, this.y);
        this.initializePhysics(world);
    }

    private void initializePhysics(World world) {
        // First we create a body definition
        BodyDef bodyDef = new BodyDef();

        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Set our body's starting position in the world
        bodyDef.position.set(this.x, this.y);

        this.body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width, this.height, new Vector2(this.x, this.y), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
    }

    public void render(SpriteBatch batch) {
        this.sprite.translate(this.body.getPosition().x - this.x, this.body.getPosition().y - this.y);
        sprite.draw(batch);
        this.x = this.body.getPosition().x;
        this.y = this.body.getPosition().y;
        System.out.println(this.body.getPosition());
    }


    public void setSpeed(float newSpeedX, float newSpeedY) {
        this.body.setLinearVelocity(newSpeedX,newSpeedY);
    }

    public boolean isOutsideBoundaries(int x0, int y0, int xf, int yf) {
        return this.body.getPosition().x > xf || this.body.getPosition().y > yf ||
                this.body.getPosition().x < x0 || this.body.getPosition().y < y0;
    }


    public void dispose() {
        //TODO ????
    }



}
