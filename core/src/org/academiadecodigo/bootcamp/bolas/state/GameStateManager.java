package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by codecadet on 3/16/17.
 */
public class GameStateManager {


    private Deque<State> stateStack;

    public GameStateManager() {
        this.stateStack = new LinkedList<>();
    }

    public void push(State state) {
        stateStack.push(state);
    }


    public void pop(State state) {
        this.stateStack.pop();
    }


    public void update(float dt) {
        stateStack.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        stateStack.peek().render(batch);
    }


}
