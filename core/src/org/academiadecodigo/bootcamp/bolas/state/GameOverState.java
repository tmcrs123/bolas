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

    private Sprite pressEnter;
    private Sprite background;
    private Sprite gameOver;
    private Texture pressEnter_texture;
    private Texture background_texture;
    private Texture gameOver_texture;
    private String name;
    private FileEditor fileEditor;
    private int score;


    public GameOverState(GameStateManager gameStateManager, int score){
        super(gameStateManager);
        fileEditor = new FileEditor();
        pressEnter_texture= new Texture("core/assets/images/gameover.png");
        background_texture = new Texture("core/assets/images/bye.jpg");
        gameOver_texture = new Texture("core/assets/images/over.png");
        this.score = score;

        this.camera = new OrthographicCamera(20,20);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        this.camera.update();

        this.background = new Sprite(background_texture);
        this.background.setSize(20,20);
        this.background.setPosition(0,0);

        this.pressEnter = new Sprite(pressEnter_texture);
        this.pressEnter.setSize(6,5);
        this.pressEnter.setPosition(7,4);

        this.gameOver = new Sprite(gameOver_texture);
        this.gameOver.setSize(10,10);
        this.gameOver.setPosition(5,7);


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
        background.draw(batch);
        pressEnter.draw(batch);
        gameOver.draw(batch);




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
