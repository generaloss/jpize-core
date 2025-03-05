package jpize.io.input;

import jpize.app.Jpize;
import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

public enum Key {

    SPACE,         //
    APOSTROPHE,    // '
    COMMA,         // ,
    MINUS,         // -
    PERIOD,        // .
    SLASH,         // /
    NUM_0,         //
    NUM_1,         //
    NUM_2,         //
    NUM_3,         //
    NUM_4,         //
    NUM_5,         //
    NUM_6,         //
    NUM_7,         //
    NUM_8,         //
    NUM_9,         //
    SEMICOLON,     // ;
    EQUAL,         // =
    A,             //
    B,             //
    C,             //
    D,             //
    E,             //
    F,             //
    G,             //
    H,             //
    I,             //
    J,             //
    K,             //
    L,             //
    M,             //
    N,             //
    O,             //
    P,             //
    Q,             //
    R,             //
    S,             //
    T,             //
    U,             //
    V,             //
    W,             //
    X,             //
    Y,             //
    Z,             //
    LEFT_BRACKET,  // [
    BACKSLASH,     // \
    RIGHT_BRACKET, // ]
    GRAVE_ACCENT,  // `
    WORLD_1,       // non-US #1
    WORLD_2,       // non-US #2
    ESCAPE,        //
    ENTER,         //
    TAB,           //
    BACKSPACE,     //
    INSERT,        //
    DELETE,        //
    RIGHT,         //
    LEFT,          //
    DOWN,          //
    UP,            //
    PAGE_UP,       //
    PAGE_DOWN,     //
    HOME,          //
    END,           //
    CAPS_LOCK,     //
    SCROLL_LOCK,   //
    NUM_LOCK,      //
    PRINT_SCREEN,  //
    PAUSE,         //
    F1,            //
    F2,            //
    F3,            //
    F4,            //
    F5,            //
    F6,            //
    F7,            //
    F8,            //
    F9,            //
    F10,           //
    F11,           //
    F12,           //
    F13,           //
    F14,           //
    F15,           //
    F16,           //
    F17,           //
    F18,           //
    F19,           //
    F20,           //
    F21,           //
    F22,           //
    F23,           //
    F24,           //
    F25,           //
    KP_0,          //
    KP_1,          //
    KP_2,          //
    KP_3,          //
    KP_4,          //
    KP_5,          //
    KP_6,          //
    KP_7,          //
    KP_8,          //
    KP_9,          //
    KP_DECIMAL,    //
    KP_DIVIDE,     //
    KP_MULTIPLY,   //
    KP_SUBTRACT,   //
    KP_ADD,        //
    KP_ENTER,      //
    KP_EQUAL,      //
    LSHIFT,        //
    LCTRL,         //
    LALT,          //
    LSUPER,        //
    RSHIFT,        //
    RCTRL,         //
    RALT,          //
    RSUPER,        //
    MENU;          //

    GlfwKey(int value) {
        this.value = value;
        this.scancode =
    }


    @Override
    public int getScancode() {
        return scancode;
    }

    @Override
    public String getName() {
        return Glfw.getKeyName(this);
    }


    @Override
    public boolean pressed() {
        return Jpize.input.isKeyPressed(this);
    }

    @Override
    public boolean down() {
        return Jpize.input.isKeyDown(this);
    }

    @Override
    public boolean up() {
        return Jpize.input.isKeyUp(this);
    }


    public static GlfwKey byValue(int value) {
        return BY_VALUE.get(value);
    }

    private static final Map<Integer, GlfwKey> BY_VALUE = Utils.make(new HashMap<>(), (map) -> {
        for(GlfwKey key: values())
            map.put(key.value, key);
    });


    public static boolean downAny(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(key.down())
                return true;
        return false;
    }

    public static boolean pressedAny(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(key.pressed())
                return true;
        return false;
    }

    public static boolean upAny(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(key.up())
                return true;
        return false;
    }


    public static boolean downAll(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(!key.down())
                return false;
        return true;
    }

    public static boolean pressedAll(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(!key.pressed())
                return false;
        return true;
    }

    public static boolean upAll(GlfwKey... buttons) {
        for(GlfwKey key: buttons)
            if(!key.up())
                return false;
        return true;
    }

}
