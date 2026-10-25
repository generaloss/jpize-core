package com.example.app;

import jpize.lwjgl.glfw.context.GlfwContextBuilder;
import jpize.lwjgl.glfw.context.GlfwContextManager;

public class Main {

    public static void main(String[] args) {
        GlfwContextBuilder.create(1280, 1080, "Jpize Application")
            .icon("/icon.png")
            .appFolderName("jpize-application") // you need it when using Jpize.files.getAppFolder()
            .multisample(4)
            .build()
            .setApp(new Application());

        GlfwContextManager.run();
    }

}
