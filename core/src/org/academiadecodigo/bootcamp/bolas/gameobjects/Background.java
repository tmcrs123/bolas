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
    private float positionX;
    private float positionY;
    private float cameraWidth;
    private float cameraHeight;
    private boolean start;

    public Background(float cameraWidth, float cameraHeight) {

        texture = new Texture("core/assets/images/background.png");

        this.cameraWidth = cameraWidth;
        this.cameraHeight = cameraHeight;

        this.sprite = new Sprite(texture);
        this.sprite.setSize(10,cameraHeight*2);
        this.sprite.setPosition(0,-cameraHeight);

        this.spriteClone = new Sprite(texture);
        this.spriteClone.setSize(cameraWidth,cameraHeight*2);
        this.spriteClone.setPosition(0,(-cameraHeight*2)+0.05f);
    }


    public void move(float dt, int DELAY){

        if(start) {

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

    public void render(SpriteBatch batch){

        this.sprite.draw(batch);
        this.spriteClone.draw(batch);

    }

    public void start(){
        this.start = true;
    }

    public void stop(){
        this.start = false;
    }
}
