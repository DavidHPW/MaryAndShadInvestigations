package com.investigations.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.investigations.game.camera.AndroidCamera;

public class GameScreen implements Screen {

    private float WIDTH, HEIGHT;
    private Stage gameStage;
    private Texture ground;
    private Texture wall;
    private Image groundImage;
    private Image wallImage;

    private Table joystickTable;
    private Touchpad joystick;
    private Touchpad.TouchpadStyle joystickStyle;
    private Skin joystickSkin;
    private Drawable vj_background;
    private Drawable vj_knob;


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


        joystickSkin = new Skin();
        joystickSkin.add("vj_background", new Texture("gameUI/virtual_joystick/VJbackground.png"));
        joystickSkin.add("vj_knob", new Texture("gameUI/virtual_joystick/VJknob.png"));
        joystickStyle = new Touchpad.TouchpadStyle();
        vj_background = joystickSkin.getDrawable("vj_background");
        vj_knob = joystickSkin.getDrawable("vj_knob");
        joystickStyle.background = vj_background;
        joystickStyle.knob = vj_knob;

        joystick = new Touchpad(50,joystickStyle);
        joystick.setBounds(15, 15, 250, 250);
        joystick.setPosition(415F,50F);

        joystickTable = new Table();


        gameStage = new Stage(new FitViewport(WIDTH, HEIGHT, new AndroidCamera(WIDTH, HEIGHT)));

        gameStage.addActor(groundImage);
        gameStage.addActor(joystick);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
