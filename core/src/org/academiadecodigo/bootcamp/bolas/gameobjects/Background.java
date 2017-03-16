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
    private float position;
    private boolean start;

    public Background(float position) {

        texture = new Texture("core/assets/images/background.png");

        this.position = position;

        this.sprite = new Sprite(texture);
        this.sprite.setSize(position,position);
        this.sprite.setPosition(0,-position/2);

        this.spriteClone = new Sprite(texture);
        this.spriteClone.setSize(position,position);
        this.spriteClone.setPosition(0,-position);
    }


    public void move(float dt, int DELAY){

        if(start) {

            if (this.sprite.getY() < 0) {

                if (this.spriteClone.getY() + this.spriteClone.getHeight() > position) {

                    this.spriteClone.translate(0, dt / DELAY);

                }

                this.sprite.translate(0, dt / DELAY);

                if ((this.spriteClone.getY() + this.spriteClone.getHeight()) > position && this.sprite.getY() > 0) {
                    this.spriteClone.setPosition(0, -position);
                }


            } else if (this.spriteClone.getY() < 0) {
                if (this.sprite.getY() + this.sprite.getHeight() > position) {

                    this.sprite.translate(0, dt / DELAY);

                }

                this.spriteClone.translate(0, dt / DELAY);

                if ((this.sprite.getY() + this.sprite.getHeight()) > position && this.spriteClone.getY() > 0) {
                    this.sprite.setPosition(0, -position);
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
