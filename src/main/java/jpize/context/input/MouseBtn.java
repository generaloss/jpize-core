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

    public boolean pressed() {
        return Jpize.input.isButtonPressed(this);
    }

    public boolean down() {
        return Jpize.input.isButtonDown(this);
    }

    public boolean up() {
        return Jpize.input.isButtonUp(this);
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
