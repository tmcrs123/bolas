package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class Background {

    private static float DIGIT_WIDTH = 0.1f;
    private static float DIGIT_HEIGHT = 0.1f;

    private static float DIGIT_Y = 1;
    private static float DIGIT_X = 0.5f;

    private Sprite sprite;
    private Sprite spriteClone;
    private Texture texture;
    private Texture textureScoreX;
    private Texture textureScore1;
    private Texture textureScore2;
    private Texture textureScore3;

    private float cameraWidth;
    private float cameraHeight;
    private boolean start;

    private List<Sprite> scoreSprites;

    private int score;

    public Background(float cameraWidth, float cameraHeight) {

        score = 0;

        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;

        this.texture = new Texture("core/assets/images/background.png");

        this.sprite = new Sprite(texture);
        this.sprite.setSize(cameraWidth, cameraHeight * 2);
        this.sprite.setPosition(0, -cameraHeight);

        this.spriteClone = new Sprite(texture);
        this.spriteClone.setSize(cameraWidth, cameraHeight * 2);
        this.spriteClone.setPosition(0, (-cameraHeight * 2) + 0.05f);

        this.initializeSpriteArray();

    }

    public void initializeSpriteArray() {
        this.scoreSprites = new LinkedList<>();


        for (int i = 0; i < 10; i++) {
            Texture t = new Texture("core/assets/images/numeral" + i + ".png");
            Sprite s = new Sprite(t);
            s.setSize(1, 1);

            this.scoreSprites.add(new Sprite(t));
        }

    }


    public void move(float dt, int DELAY) {

        if (start) {
            score++;


            if (this.sprite.getY() < 0) {

                if (this.spriteClone.getY() + this.spriteClone.getHeight() > cameraHeight) {

                    this.spriteClone.translate(0, dt / DELAY);

                }

                this.sprite.translate(0, dt / DELAY);

                if ((this.spriteClone.getY() + this.spriteClone.getHeight()) >= cameraHeight && this.sprite.getY() >= 0) {
                    this.spriteClone.setPosition(0, (-cameraHeight * 2) + 0.05f);
                }


            } else if (this.spriteClone.getY() <= 0) {
                if (this.sprite.getY() + this.sprite.getHeight() >= cameraHeight) {

                    this.sprite.translate(0, dt / DELAY);

                }

                this.spriteClone.translate(0, dt / DELAY);

                if ((this.sprite.getY() + this.sprite.getHeight()) > cameraHeight && this.spriteClone.getY() > 0) {
                    this.sprite.setPosition(0, (-cameraHeight * 2) + 0.05f);
                }
            }
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void render(SpriteBatch batch) {

        String scoreString = Integer.toString(this.score).trim();

        for (int i = 0; i < scoreString.length(); i++) {

            int digit = Integer.parseInt(scoreString.charAt(i) + "");

            Sprite s = this.scoreSprites.get(digit);
            s.setPosition(DIGIT_X + DIGIT_WIDTH * i, DIGIT_Y);

            s.draw(batch);

        }



    }

    public void start() {
        this.start = true;
    }

    public void stop() {
        this.start = false;
    }

    public void dispose() {

        this.sprite.getTexture().dispose();
        this.spriteClone.getTexture().dispose();

        for (Sprite s : this.scoreSprites) {
            s.getTexture().dispose();
        }

    }


}
