package com.kisman.cc.module.render;

import com.kisman.cc.module.Category;
import com.kisman.cc.module.Module;

public class Spin extends Module {
    public static Spin instance;

    public Spin() {
        super("Spin", "RotatePlayer", Category.RENDER);

        instance = this;
    }
}
