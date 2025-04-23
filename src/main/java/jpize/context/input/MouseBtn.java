package jpize.context.input;

import jpize.context.Jpize;

public enum MouseBtn {

    LEFT,
    RIGHT,
    MIDDLE,
    B4,
    B5,
    B6,
    B7,
    B8;

    public boolean pressed(int mouseIndex) {
        return Jpize.input.isButtonPressed(mouseIndex, this);
    }

    public boolean down(int mouseIndex) {
        return Jpize.input.isButtonDown(mouseIndex, this);
    }

    public boolean up(int mouseIndex) {
        return Jpize.input.isButtonUp(mouseIndex, this);
    }

    public Action action(int mouseIndex) {
        return Jpize.input.getMouseButton(mouseIndex, this);
    }


    public boolean pressed() {
        return pressed(0);
    }

    public boolean down() {
        return down(0);
    }

    public boolean up() {
        return up(0);
    }

    public Action action() {
        return action(0);
    }


    public static boolean downAny(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.down())
                return true;
        return false;
    }

    public static boolean pressedAny(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.pressed())
                return true;
        return false;
    }

    public static boolean upAny(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(button.up())
                return true;
        return false;
    }


    public static boolean downAny(MouseBtn... buttons) {
        return downAny(0, buttons);
    }

    public static boolean pressedAny(MouseBtn... buttons) {
        return pressedAny(0, buttons);
    }

    public static boolean upAny(MouseBtn... buttons) {
        return upAny(0, buttons);
    }


    public static boolean downAll(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.down())
                return false;
        return true;
    }

    public static boolean pressedAll(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.pressed())
                return false;
        return true;
    }

    public static boolean upAll(int mouseIndex, MouseBtn... buttons) {
        for(MouseBtn button: buttons)
            if(!button.up())
                return false;
        return true;
    }


    public static boolean downAll(MouseBtn... buttons) {
        return downAll(0, buttons);
    }

    public static boolean pressedAll(MouseBtn... buttons) {
        return pressedAll(0, buttons);
    }

    public static boolean upAll(MouseBtn... buttons) {
        return upAll(0, buttons);
    }

}
