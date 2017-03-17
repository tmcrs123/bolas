package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by codecadet on 3/16/17.
 */
public class Background {

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
    private Sprite spriteScore1;
    private Sprite spriteScore2;
    private Sprite spriteScore3;
    private int score;

    public Background(float cameraWidth, float cameraHeight) {

        score = 0;

        texture = new Texture("core/assets/images/background.png");
        textureScore1 = new Texture("core/assets/images/numeral0.png");
        textureScore2 = new Texture("core/assets/images/numeral0.png");
        textureScore3 = new Texture("core/assets/images/numeral0.png");

        /*
        textureScore4 = new Texture("core/assets/images/numeral4.png");
        textureScore5 = new Texture("core/assets/images/numeral5.png");
        textureScore6 = new Texture("core/assets/images/numeral6.png");
        textureScore7 = new Texture("core/assets/images/numeral7.png");
        textureScore8 = new Texture("core/assets/images/numeral8.png");
        textureScore9 = new Texture("core/assets/images/numeral9.png");
        textureScore0 = new Texture("core/assets/images/numeral0.png");
        */



        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;

        this.sprite = new Sprite(texture);
        this.sprite.setSize(cameraWidth,cameraHeight*2);
        this.sprite.setPosition(0,-cameraHeight);

        this.spriteClone = new Sprite(texture);
        this.spriteClone.setSize(cameraWidth,cameraHeight*2);
        this.spriteClone.setPosition(0,(-cameraHeight*2)+0.05f);


    }


    public void move(float dt, int DELAY){

        if(start) {
            score++;

            System.out.println(this.sprite.getY());

            if ( this.sprite.getY() < 0 ) {

                if ( this.spriteClone.getY() + this.spriteClone.getHeight() > cameraHeight ) {

                    this.spriteClone.translate(0, dt / DELAY);

                }

                this.sprite.translate(0, dt / DELAY);

                if ( ( this.spriteClone.getY() + this.spriteClone.getHeight() ) >= cameraHeight && this.sprite.getY() >= 0 ) {
                    this.spriteClone.setPosition(0, (-cameraHeight*2)+0.05f);
                }


            } else if ( this.spriteClone.getY() <= 0 ) {
                if ( this.sprite.getY() + this.sprite.getHeight() >= cameraHeight ) {

                    this.sprite.translate(0, dt / DELAY);

                }

                this.spriteClone.translate(0, dt / DELAY);

                if ( ( this.sprite.getY() + this.sprite.getHeight() ) > cameraHeight && this.spriteClone.getY() > 0 ) {
                    this.sprite.setPosition(0, (-cameraHeight*2)+0.05f);
                }
            }
        }
    }

    public void setScore(){

    }

    public void render(SpriteBatch batch) {

        int scoretemp = score;
        int modScore = 0;

        while ( (scoretemp) > 9 ) {
            modScore = modScore % 10;
            scoretemp = scoretemp/10;
        }

/*
        if (score == 0){
            this.spriteScore = new Sprite(textureScoreX);
            this.spriteScore.setScale(1, 1);
            this.spriteScore.setPosition(17*score/10 , 15);

        }

        if (score  == 1){
            this.spriteScore = new Sprite(textureScore1);
            this.spriteScore.setSize(1, 1);
            this.spriteScore.setPosition(17*score/10 , 15);
        }

        if (score == 2){
            this.spriteScore = new Sprite(textureScore2);
            this.spriteScore.setSize(1, 1);
            this.spriteScore.setPosition(17*score/10 , 15);
        }
*/

// falta resolver isto
        this.textureScore1 = new Texture("core/assets/images/numeral" + scoretemp + ".png");
        this.spriteScore1 = new Sprite(textureScore1);


        this.spriteScore1.setSize(1, 1);
        this.spriteScore1.setPosition(18 , 15);

        this.textureScore2 = new Texture("core/assets/images/numeral" + scoretemp + ".png");
        this.spriteScore2 = new Sprite(textureScore2);

        this.spriteScore2.setSize(1, 1);
        this.spriteScore2.setPosition(17 , 15);

        this.textureScore3 = new Texture("core/assets/images/numeral" + scoretemp + ".png");
        this.spriteScore3 = new Sprite(textureScore3);

        this.spriteScore3.setSize(1, 1);
        this.spriteScore3.setPosition(16 , 15);


        this.sprite.draw(batch);
        this.spriteClone.draw(batch);
        this.spriteScore1.draw(batch);
        this.spriteScore2.draw(batch);
        this.spriteScore3.draw(batch);

    }

    public void start(){
        this.start = true;
    }

    public void stop(){
        this.start = false;
    }
}
