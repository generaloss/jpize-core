package jpize.lwjgl.glfw.input;

import jpize.app.Jpize;

import static org.lwjgl.glfw.GLFW.*;

public enum MouseBtn {

    LEFT   (GLFW_MOUSE_BUTTON_1),  // 0
    RIGHT  (GLFW_MOUSE_BUTTON_2),  // 1
    MIDDLE (GLFW_MOUSE_BUTTON_3),  // 2
    B4     (GLFW_MOUSE_BUTTON_4),  // 3
    B5     (GLFW_MOUSE_BUTTON_5),  // 4
    B6     (GLFW_MOUSE_BUTTON_6),  // 5
    B7     (GLFW_MOUSE_BUTTON_7),  // 6
    B8     (GLFW_MOUSE_BUTTON_8);  // 7

    public final int value;

    MouseBtn(int value) {
        this.value = value;
    }


    public boolean pressed() {
        return Jpize.input().isButtonPressed(this);
    }

    public boolean down() {
        return Jpize.input().isButtonDown(this);
    }

    public boolean up() {
        return Jpize.input().isButtonUp(this);
    }


    public static MouseBtn byValue(int value) {
        return values()[value];
    }


    public static boolean downAny(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.down())
                return true;
        return false;
    }

    public static boolean pressedAny(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.pressed())
                return true;
        return false;
    }

    public static boolean upAny(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.up())
                return true;
        return false;
    }


    public static boolean downAll(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.down())
                return false;
        return true;
    }

    public static boolean pressedAll(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.pressed())
                return false;
        return true;
    }

    public static boolean upAll(MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.up())
                return false;
        return true;
    }

}
