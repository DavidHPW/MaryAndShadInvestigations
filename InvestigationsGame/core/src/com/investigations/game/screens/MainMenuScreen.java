package com.investigations.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.investigations.game.camera.AndroidCamera;

public class MainMenuScreen implements Screen {

    private float WIDTH, HEIGHT;
    private Texture mainmenuTexture;
    private Image mainmenuImage;
    private Stage mainmenuStage;
    private Skin mainmenuPlayContinue;
    private Skin mainmenuPlayNewGame;
    private Skin mainmenuPlaySettings;
    private Skin mainmenuPlayQuit;
    private ImageButton mainmenuimagebuttonPlayContinue;
    private ImageButton mainmenuimagebuttonPlayNewGame;
    private ImageButton mainmenuimagebuttonPlaySettings;
    private ImageButton mainmenuimagebuttonPlayQuit;
    private Table mainmenuTablePlayContinue;
    private Table mainmenuTablePlayNewGame;
    private Table mainmenuTablePlaySettings;
    private Table mainmenuTablePlayQuit;


    public MainMenuScreen() {
    }

    @Override
    public void show() {
        //Résolution de l'écran
        WIDTH = 1080;
        HEIGHT = 1920;

        //Background du menu
        mainmenuTexture = new Texture("mainMenu/background_accueil.png");
        mainmenuTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        mainmenuImage = new Image(mainmenuTexture);
        mainmenuImage.setSize(1080, 1920);

        mainmenuStage = new Stage(new FitViewport(WIDTH, HEIGHT, new AndroidCamera(WIDTH, HEIGHT)));

/////////////////////Table qui va contenir tout ce qui concerne le bouton "Continuer"
        mainmenuTablePlayContinue = new Table();
        //on récupère le fichier json et le pack atlas qui groupe les images du bouton en mode normal (NORM) et appuyé (PUSH)
        mainmenuPlayContinue = new Skin(Gdx.files.internal("mainMenu/playContinue.json"), new TextureAtlas(Gdx.files.internal("mainMenu/continueMenuPack.atlas")));

        //concrétisation du bouton "Continuer" avec son dimensionnement et placement
        mainmenuimagebuttonPlayContinue = new ImageButton(mainmenuPlayContinue);
        mainmenuTablePlayContinue.bottom().add(mainmenuimagebuttonPlayContinue).size(429, 541).padBottom(-1000);


/////////////////////Table qui va contenir tout ce qui concerne le bouton "Nouvelle Partie"
        mainmenuTablePlayNewGame = new Table();
        mainmenuPlayNewGame = new Skin(Gdx.files.internal("mainMenu/playNewGame.json"), new TextureAtlas(Gdx.files.internal("mainMenu/newGameMenuPack.atlas")));

        //concrétisation du bouton "Nouvelle Partie" avec son dimensionnement et placement
        mainmenuimagebuttonPlayNewGame = new ImageButton(mainmenuPlayNewGame);
        mainmenuTablePlayNewGame.bottom().add(mainmenuimagebuttonPlayNewGame).size(429, 541).padBottom(-1000);

/////////////////////Table qui va contenir tout ce qui concerne le bouton "Quitter"
        mainmenuTablePlayQuit = new Table();
        mainmenuPlayQuit = new Skin(Gdx.files.internal("mainMenu/playQuit.json"), new TextureAtlas(Gdx.files.internal("mainMenu/quitMenuPack.atlas")));

        //concrétisation du bouton "Quitter" avec son dimensionnement et placement
        mainmenuimagebuttonPlayQuit = new ImageButton(mainmenuPlayQuit);
        mainmenuTablePlayQuit.padRight(-800F).padTop(1500F).add(mainmenuimagebuttonPlayQuit).size(148,269);

/////////////////////Table qui va contenir tout ce qui concerne le bouton "Paramètres"
        mainmenuTablePlaySettings = new Table();
        mainmenuPlaySettings = new Skin(Gdx.files.internal("mainMenu/playSettings.json"), new TextureAtlas(Gdx.files.internal("mainMenu/settingMenuPack.atlas")));

        //concrétisation du bouton "Paramètres" avec son dimensionnement et placement
        mainmenuimagebuttonPlaySettings = new ImageButton(mainmenuPlaySettings);
        mainmenuTablePlaySettings.padLeft(-800F).padTop(1500F).add(mainmenuimagebuttonPlaySettings).size(218,251);

        //Ajout des éléments dans le mainmenuStage
        mainmenuStage.addActor(mainmenuImage);
        mainmenuStage.addActor(mainmenuTablePlayContinue);
        mainmenuStage.addActor(mainmenuTablePlayNewGame);
        mainmenuStage.addActor(mainmenuTablePlayQuit);
        mainmenuStage.addActor(mainmenuTablePlaySettings);

        Gdx.input.setInputProcessor(mainmenuStage);

        //animations des tables
        mainmenuImage.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(1.0F)));

        mainmenuTablePlayContinue.addAction(Actions.sequence(Actions.moveBy(250F, -0F), Actions.delay(1.0F), Actions.moveBy(0.0F, 1300F, 1.0F, Interpolation.smooth)));
        mainmenuTablePlayNewGame.addAction(Actions.sequence(Actions.moveBy(-250F, -0F), Actions.delay(1.0F), Actions.moveBy(0.0F, 1300F, 1.0F, Interpolation.smooth)));
        mainmenuTablePlayQuit.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(1.0F)));
        mainmenuTablePlaySettings.addAction(Actions.sequence(Actions.alpha(0.0F), Actions.fadeIn(1.0F)));


        mainmenuimagebuttonPlayContinue.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SplashScreen());

            }
        });

        mainmenuimagebuttonPlayNewGame.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            }
        });

        mainmenuimagebuttonPlayQuit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SplashScreen());
            }
        });

        mainmenuimagebuttonPlaySettings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SplashScreen());
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainmenuStage.act();
        mainmenuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainmenuStage.getViewport().update(width, height, true);


        mainmenuTablePlayContinue.invalidateHierarchy();
        mainmenuTablePlayContinue.setSize(WIDTH, HEIGHT);

        mainmenuTablePlayNewGame.invalidateHierarchy();
        mainmenuTablePlayNewGame.setSize(WIDTH, HEIGHT);

        mainmenuTablePlayQuit.invalidateHierarchy();
        mainmenuTablePlayQuit.setSize(WIDTH, HEIGHT);

        mainmenuTablePlaySettings.invalidateHierarchy();
        mainmenuTablePlaySettings.setSize(WIDTH, HEIGHT);
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
        mainmenuStage.dispose();
        mainmenuTexture.dispose();
        mainmenuPlayContinue.dispose();
        mainmenuPlayNewGame.dispose();
        mainmenuPlayQuit.dispose();
    }
}
