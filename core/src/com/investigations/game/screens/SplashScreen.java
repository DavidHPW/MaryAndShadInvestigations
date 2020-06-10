package com.investigations.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.investigations.game.camera.AndroidCamera;

public class SplashScreen implements Screen{
        private float WIDTH, HEIGHT;
        private Texture splashtexture;
        private Image splashimage;
        private Stage splashstage;

    @Override
    public void show() {
        WIDTH = 1280;
        HEIGHT = 720;

        splashtexture = new Texture(Gdx.files.internal("splashScreen/splashDavidHPW.png"));
        splashtexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        splashimage = new Image(splashtexture);
        splashimage.setPosition(WIDTH/6,HEIGHT/4);

        splashstage = new Stage(new FitViewport(WIDTH,HEIGHT, new AndroidCamera(WIDTH,HEIGHT)));
        splashstage.addActor(splashimage);

        splashimage.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(0.75F),Actions.delay(1F), Actions.fadeOut(0.75F),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TitleScreen());
            }
        })));
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        splashstage.act();
        splashstage.draw();

    }

    @Override
    public void resize(int width, int height) {
        splashstage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splashtexture.dispose();
        splashstage.dispose();
    }
}
