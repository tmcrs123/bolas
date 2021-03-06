package org.academiadecodigo.bootcamp.bolas.gameobjects.Physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Ball;
import org.academiadecodigo.bootcamp.bolas.gameobjects.ComplexPlatform;
import org.academiadecodigo.bootcamp.bolas.gameobjects.Platform;

import java.util.Deque;

/**
 * Created by codecadet on 3/17/17.
 */
public class PlatformBallContactListener implements ContactListener {

    private Deque<ComplexPlatform> platforms;
    private Ball ball;
    private Sound fallSound;


    public PlatformBallContactListener() {
        fallSound = Gdx.audio.newSound(Gdx.files.internal("core/assets/sound/sfx_twoTone.ogg"));
    }

    @Override
    public void beginContact(Contact contact) {

        if (this.theProperBodiesForBallJumping(contact)) {
            ball.setCanJump(true);
            fallSound.play(5f);
            //System.out.println("aaaaaaaaaaa");
        }

    }


    public boolean theProperBodiesForBallJumping(Contact contact) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        Body bodyA = a.getBody();
        Body bodyB = b.getBody();

        Ball ball = null;
        Platform p = null;

        if (bodyB.getUserData() instanceof Ball) {
            ball = (Ball) bodyB.getUserData();
        }

        if (bodyA.getUserData() instanceof Ball) {
            ball = (Ball) bodyA.getUserData();
        }

        if (bodyA.getUserData() instanceof Platform) {
            p = (Platform) bodyA.getUserData();
        }

        if (bodyB.getUserData() instanceof Platform) {
            p = (Platform) bodyB.getUserData();
        }

        if (ball == null) {
            return false;
        }

        if (p == null) {
            return false;
        }

        if (p.getHeight() > p.getWidth()) {
            return false;
        }

        return true;
    }



    @Override
    public void endContact(Contact contact) {

        if (this.theProperBodiesForBallJumping(contact)) {
            ball.setCanJump(false);
            //System.out.println("aaaaaaaaaaa");
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setPlatforms(Deque<ComplexPlatform> platforms) {
        this.platforms = platforms;
    }
}
