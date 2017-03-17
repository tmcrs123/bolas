package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 3/16/17.
 */
public class PowerUp {

    public CircleShape circle;
    public Sprite sprite;
    public Body body;
    public BodyDef bodyDef;
    private SpriteBatch batch;



    public PowerUp(float startX , float startY, float width , float height, World world){
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startX, startY);

        Texture tex = new Texture("core/assets/images/speedUp.png"); // ASSET DO CARALHO

        this.sprite = new Sprite(tex);
        this.sprite.setSize(width, height);
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




    public void render(SpriteBatch batch){

        this.sprite.setPosition(this.body.getPosition().x - this.sprite.getWidth()/2, this.body.getPosition().y - sprite.getHeight()/2);
        this.sprite.draw(batch);

    }

}
