package com.investigations.game.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class Joystick extends Touchpad {


    public Joystick(float deadzoneRadius, Skin skin) {
        super(deadzoneRadius, skin);
    }

    public Joystick(float deadzoneRadius, Skin skin, String styleName) {
        super(deadzoneRadius, skin, styleName);
    }

    public Joystick(float deadzoneRadius, TouchpadStyle style) {
        super(deadzoneRadius, style);
    }


}
