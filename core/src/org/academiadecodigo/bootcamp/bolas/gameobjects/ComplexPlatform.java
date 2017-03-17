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

    private static float boundaryHeight = 3;

    private float x;
    private float y;

    private float width;
    private float height;

    private int holeNumber = 0;
    private float holeWidth = 0;

    private float speedX;
    private float speedY;


    public ComplexPlatform(float x, float y, float width, float height,  World world) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.platforms = new LinkedList<>();

    }

    public ComplexPlatform(float x, float y, float width, float height) {
        this(x, y, width, height, null);

    }

    public void constructPlatforms(World world) {
        this.platforms.add(new Platform(this.x - this.width / 2 - WALL_THICKNESS / 2, this.y + boundaryHeight/2 + this.height/2, WALL_THICKNESS, boundaryHeight, world));
        this.createMiddlePlatforms(world);
        this.platforms.add(new Platform( this.x + this.width/2 + WALL_THICKNESS/2 , this.y + boundaryHeight/2 + this.height/2, WALL_THICKNESS, boundaryHeight , world));

    }

    private void createMiddlePlatforms(World world) {


        if (holeWidth > this.width || this.holeNumber == 0) {
            this.platforms.add(new Platform( this.x, this.y, this.width, this.height, world));
            return;
        }

        if (holeWidth * holeNumber >= this.width) {
            throw new IllegalStateException();
        }

        float remainingWidth = this.width - this.holeNumber * this.holeWidth;

        int nPlatforms = this.holeNumber + 1;

        float[] weights = new float[nPlatforms];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) Math.ceil( Math.random() * 10 );
            System.out.println(weights[i]);
        }

        double weightSum = 0;

        for (float f : weights) {
            weightSum += f;
        }
        System.out.println(weightSum);

        double debSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weights[i] /= weightSum;
            debSum += weights[i];
            System.out.println(weights[i]);
        }

        for (int i = 0; i < weights.length; i++) {
            weights[i] *= remainingWidth;
            debSum += weights[i];
        }

        System.out.println(debSum);
        System.out.println(" " );


        float cumSum = 0;
        float cumNHoles = 0;

        for (int i = 0; i < weights.length; i++) {

            System.out.println(weights[i]);
            if (i == 0) {
                this.platforms.add(new Platform( this.x - this.width/2 + weights[0]/2 , this.y, weights[0], this.height, world));
                System.out.println(weights[0]/2);
                cumSum = weights[0];
                cumNHoles = 1;
                continue;
            }


            float pos = 0;
            while (pos < cumNHoles * this.holeWidth + cumSum + weights[i]/2) {
                pos += 0.01;
            }

            cumSum += weights[i];
            cumNHoles += 1;

            System.out.println(pos);
            this.platforms.add(new Platform(this.x - this.width/2 + pos , this.y, weights[i], this.height, world));
        }


    }

    public void render(SpriteBatch batch) {


        for (Platform p : this.platforms) {
            p.render(batch);
        }

        this.y = this.platforms.get(1).getY();
    }

    public boolean isOutsideBoundaries(float x0, float y0, float xf, float yf) {
         boolean b1 = this.x + this.width / 2 > xf;
        boolean b2 = this.y + this.height/2 > yf;
        boolean b3 = this.x - this.width/2 < x0;
        boolean b4 = this.y - this.height/2 < y0;

        return b1 || b2 || b3 || b4;
    }

    public void setSpeed(float newSpeedX, float newSpeedY) {
        this.speedX = newSpeedX;
        this.speedY = newSpeedY;
        for (Platform p : this.platforms) {
            p.setSpeed(this.speedX, this.speedY);
        }
    }

    public void setHoleWidth(float newWidth) {
        this.holeWidth = newWidth;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    public void dispose() {
        for (Platform p : this.platforms) {
            p.dispose();
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.platforms.get(1).getY();
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public int getHoleNumber() {
        return holeNumber;
    }

    public float getHoleWidth() {
        return holeWidth;
    }

    public float getSpeedX() {
        return this.speedX;
    }

    public float getSpeedY() {
        return this.speedY;
    }
}
