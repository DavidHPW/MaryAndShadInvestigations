package com.investigations.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.investigations.game.camera.AndroidCamera;
import com.investigations.game.controllers.Joystick;

public class GameScreen implements Screen {

    private float WIDTH, HEIGHT;
    private Stage gameStage;
    private Texture ground;
    private Texture wall;
    private Image groundImage;
    private Image wallImage;

    private Joystick joystick;
    private Joystick.TouchpadStyle joystickStyle;
    private Skin joystickSkin;
    private Drawable joystickBackground;
    private Drawable joystickKnob;

    private Texture charaTexture;
    private Sprite  charaSprite;
    private float charaspeed;
    private SpriteBatch batch;


    public GameScreen(){

    }

    @Override
    public void show() {
        //Résolution de l'écran
        WIDTH = 1080;
        HEIGHT = 1920;

        //sol
        ground = new Texture("classicMAP/ground_test.png");
        ground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        groundImage = new Image(ground);
        groundImage.setSize(1000*3,377*3);


        //Joystick

        joystickSkin = new Skin();
        joystickSkin.add("VJbackground", new Texture("gameUI/virtual_joystick/VJbackground.png"));
        joystickSkin.add("VJknob", new Texture("gameUI/virtual_joystick/VJknob.png"));

        joystickStyle = new Joystick.TouchpadStyle();

        joystickBackground = joystickSkin.getDrawable("VJbackground");
        joystickKnob = joystickSkin.getDrawable("VJknob");

        joystickStyle.background = joystickBackground;
        joystickStyle.knob = joystickKnob;

        joystick = new Joystick(10, joystickStyle);
        joystick.setBounds(WIDTH/2,0,200,200);

        //Personage
        batch = new SpriteBatch();
        charaTexture = new Texture(Gdx.files.internal("gameUI/virtual_joystick/Mary1.png"));
        charaSprite = new Sprite(charaTexture);

        charaSprite.setPosition(WIDTH/2,HEIGHT/2);


        charaspeed = 5;



        //Stage
        gameStage = new Stage(new FitViewport(WIDTH, HEIGHT, new AndroidCamera(WIDTH, HEIGHT))  , batch);
       // gameStage.addActor(groundImage);
        gameStage.addActor(joystick);
        Gdx.input.setInputProcessor(gameStage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.draw();

        charaSprite.setX(charaSprite.getX() + joystick.getKnobPercentX()*charaspeed);
        charaSprite.setY(charaSprite.getY() + joystick.getKnobPercentY()*charaspeed);

        batch.begin();
        charaSprite.draw(batch);
        batch.end();
            gameStage.act(Gdx.graphics.getDeltaTime());
            gameStage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
