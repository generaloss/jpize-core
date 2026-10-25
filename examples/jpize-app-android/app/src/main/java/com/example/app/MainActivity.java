package com.example.app;

import android.os.Bundle;

import jpize.android.context.JpizeAndroidActivity;
import jpize.context.Jpize;

public class MainActivity extends JpizeAndroidActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onJpizeInitialized() {
        Jpize.context.setApp(new Application());
    }

}
