package org.academiadecodigo.bootcamp.bolas.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 3/16/17.
 */
public class ComplexPlatform {

    public static final float WALL_THICKNESS = 0.5f;

    private List<Platform> platforms;

    private float x;
    private float y;

    private float width;
    private float height;

    private float speed;


    public ComplexPlatform(float x, float y, float width, float height, float limitHeight, World world) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.platforms = new LinkedList<>();

        this.platforms.add(new Platform( this.x - this.width/2 - WALL_THICKNESS/2 , this.y + limitHeight * 0.5f + this.height/2, WALL_THICKNESS, limitHeight, world));
        this.platforms.add(new Platform( this.x, this.y, this.width, this.height, world));
        this.platforms.add(new Platform( this.x + this.width/2 + WALL_THICKNESS/2 , this.y + limitHeight * 0.5f + this.height/2, WALL_THICKNESS, limitHeight, world));


    }


    public void render(SpriteBatch batch) {

        for (Platform p : this.platforms) {
            p.render(batch);
        }

    }

    public boolean isOutsideBoundaries(int x0, int y0, int xf, int yf) {
        return this.x > xf || this.y > yf ||
                this.x < x0 || this.y < y0;
    }

    public void setSpeed(float newSpeedX, float newSpeedY) {
        for (Platform p : this.platforms) {
            p.setSpeed(newSpeedX, newSpeedY);
        }
    }

    public void dispose() {
        for (Platform p : this.platforms) {
            p.dispose();
        }
    }


}
