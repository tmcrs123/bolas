package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/17/17.
 */
public class ScoreCounter {


    private float x;
    private float y;
    private float width;
    private float height;
    private int nDigits;

//    private Sprite zero;
//    private Sprite one;
//    private Sprite two;
//    private Sprite three;
//    private Sprite four;
//    private Sprite uuu;
//    private Sprite zero;
//    private Sprite zero;
//    private Sprite zero;
//    private Sprite zero;

    private Sprite[] scoreSprites;
    private int score;

    public ScoreCounter(float x, float y, float width, float height, int nDigits) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.nDigits = nDigits;
        this.prepareSprites();
    }

    public void prepareSprites() {
        this.scoreSprites = new Sprite[10];


        for (int i = 0; i < 10; i++) {
            Texture t = new Texture("core/assets/images/numeral" + i + ".png");
            this.scoreSprites[i] = new Sprite(t);
            this.scoreSprites[i].setSize(this.width, this.height);
            this.scoreSprites[i].setPosition(this.x,this.y);

        }
    }


    public void render(SpriteBatch batch) {

        String scoreString = Integer.toString(this.score).trim();

        for (int i = 0; i < scoreString.length(); i++) {

            int digit = Integer.parseInt(scoreString.charAt(i) + "");

            Sprite s = this.scoreSprites[digit];
            s.setPosition(this.x + this.width/this.nDigits * i, this.y);

            s.draw(batch);

        }

    }

    public void setScore(int score) {
        this.score = score;
    }

    public void dispose() {
        for (Sprite s : this.scoreSprites) {
            s.getTexture().dispose();
        }
    }
}

