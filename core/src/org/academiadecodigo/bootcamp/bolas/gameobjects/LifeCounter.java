package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by codecadet on 3/16/17.
 */
public class LifeCounter {

    private Sprite leftSprite;
    private Sprite rightSprite;

    private Sprite heartSprite;

    private float x;
    private float y;
    private float width;
    private float height;
    private int leftDecimal;
    private int rightDecimal;


    public LifeCounter(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.prepareSprites();
    }

    private void prepareSprites() {

        if (this.leftSprite != null) {
            this.leftSprite.getTexture().dispose();
        }

        if (this.rightSprite != null) {
            this.rightSprite.getTexture().dispose();
        }

        String leftNumeralString = "core/assets/images/numeral" + this.leftDecimal + ".png";
        String rightNumeralString = "core/assets/images/numeral" + this.rightDecimal + ".png";

        Texture leftTex = new Texture(leftNumeralString); // ASSET DO CARALHO
        Texture rightTex = new Texture(rightNumeralString); // ASSET DO CARALHO

        this.leftSprite = new Sprite(leftTex);
        this.rightSprite = new Sprite(rightTex);

        this.leftSprite.setSize(this.width/2, this.height);
        this.rightSprite.setSize(this.width/2, this.height);

        this.leftSprite.setPosition(this.x,this.y);
        this.rightSprite.setPosition(this.leftSprite.getX() + this.width / 2, this.y);

        Texture heartTexture = new Texture("core/assets/images/heart.png");

        this.heartSprite = new Sprite(heartTexture);
        this.heartSprite.setPosition(this.x + 1, this.y);
        this.heartSprite.setSize(this.width, this.height);

    }


    public void render(SpriteBatch batch) {

        this.leftSprite.draw(batch);
        this.rightSprite.draw(batch);
        this.heartSprite.draw(batch);

    }

    public void setLives(int lives) {
        String liveString = Integer.toString(lives);

        if (liveString.length() > 2) {
            this.leftDecimal = 9;
            this.rightDecimal = 9;
        } else if (liveString.length() == 1) {
            this.leftDecimal = 0;
            this.rightDecimal = Integer.parseInt(liveString.charAt(0) + "");
        } else {
            this.leftDecimal = Integer.parseInt(liveString.charAt(0) + "");
            this.rightDecimal = Integer.parseInt(liveString.charAt(1) + "");
        }

        this.prepareSprites();

    }


    public void dispose() {
        this.leftSprite.getTexture().dispose();
        this.rightSprite.getTexture().dispose();
        this.heartSprite.getTexture().dispose();
    }

}

