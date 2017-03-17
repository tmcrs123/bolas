package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        bodyDef.position.set(5, 2);

        Texture tex = new Texture("core/assets/images/ball.png"); // ASSET DO CARALHO

        this.sprite = new Sprite(tex);
        this.sprite.setSize(1f, 1f);
        this.sprite.setPosition(bodyDef.position.x,bodyDef.position.y);
        this.sprite.setOrigin(sprite.getX()/2,sprite.getY()/2);
        this.sprite.setOriginCenter();

        this.body = world.createBody(bodyDef);

        circle = new CircleShape();
        circle.setRadius(sprite.getHeight()/2);

        batch = new SpriteBatch();


        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        body.createFixture(fixtureDef);
    }


    public void handleInput() {

        float xdelta = 0;
        float ydelta = 0f;

        this.body.setLinearVelocity(0, ydelta);


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xdelta = 2f;
            System.out.println("xdelta" + xdelta);
            this.body.setLinearVelocity(xdelta, ydelta);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xdelta = -2f;
            System.out.println("xdelta" + xdelta);
            this.body.setLinearVelocity(xdelta, ydelta);

        }
    }

    public void render(SpriteBatch batch) {

        if ((this.body.getPosition().y ) < ((this.circle.getRadius()))){
            this.body.setLinearVelocity(0,0);
            //this.sprite.setPosition(0, 0);
            //return;
        } else {
            System.out.println(this.body.getPosition().y);


//            this.sprite.setPosition(this.body.getPosition().x, this.body.getPosition().y);
            this.sprite.setPosition(this.body.getPosition().x - this.sprite.getWidth()/2, this.body.getPosition().y - sprite.getHeight()/2);
            this.sprite.rotate(this.body.getAngle());
        }
        //debugRenderer.render(world, camera.combined);
        this.sprite.draw(batch);
    }

}

