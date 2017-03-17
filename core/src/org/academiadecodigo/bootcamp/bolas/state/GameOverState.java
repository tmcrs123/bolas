package org.academiadecodigo.bootcamp.bolas.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.academiadecodigo.bootcamp.bolas.FileEditor;

import java.io.IOException;

/**
 * Created by codecadet on 3/17/17.
 */
public class GameOverState extends State implements Input.TextInputListener {

    private Sprite sprite;
    private Texture img;
    private Texture bg;
    private String name;
    private FileEditor fileEditor;
    private int score;


    public GameOverState(GameStateManager gameStateManager, int score){
        super(gameStateManager);
        fileEditor = new FileEditor();
        img = new Texture("/Users/codecadet/Documents/bolas/core/assets/images/gameover.png");
        bg = new Texture("/Users/codecadet/Documents/bolas/core/assets/images/bye.jpg");
        this.score = score;

        this.camera = new OrthographicCamera(20,20);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.sprite = new Sprite(img);
        this.sprite.setSize(30,30);
        this.sprite.setPosition(15,15);


        this.sprite = new Sprite(img);
        this.sprite.setSize(20,20);


        this.sprite = new Sprite(bg);
        this.sprite.setSize(30,30);
        this.sprite.setPosition(10,10);


        this.sprite = new Sprite(bg);
        this.sprite.setSize(20,20);

    }




    @Override
    public void handleInput() {

        //enter e só com enter

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            Gdx.input.getTextInput(this,"Let me save your score!","","");
        }


        while (name != null) {
            try {
                fileEditor.Writer(name,String.valueOf(score));
                System.out.println(fileEditor.Loader());
            } catch (IOException e) {
                e.printStackTrace();
            }

            gameStateManager.pop(this);
            gameStateManager.push(new PlayingState(gameStateManager));
            return;
        }

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        sprite.draw(batch);



    }

    @Override
    public void dispose() {

    }

    @Override
    public void input(String text) {
        this.name = text;

    }

    @Override
    public void canceled() {
        name = "anonymous";

    }
}
