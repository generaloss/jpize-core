package jpize.android.context.input;

import jpize.context.input.Mods;

import static android.view.KeyEvent.*;

public class AndroidMods extends Mods {

    public AndroidMods(int bits) {
        super(bits);
    }

    @Override
    public boolean hasShift() {
        return ((bits & META_SHIFT_ON) != 0); // 1
    }

    public boolean hasShiftLeft() {
        return ((bits & META_SHIFT_LEFT_ON) != 0); // 64
    }

    public boolean hasShiftRight() {
        return ((bits & META_SHIFT_RIGHT_ON) != 0); // 128
    }


    @Override
    public boolean hasAlt() {
        return ((bits & META_ALT_ON) != 0); // 2
    }

    public boolean hasAltLeft() {
        return ((bits & META_ALT_LEFT_ON) != 0); // 16
    }

    public boolean hasAltRight() {
        return ((bits & META_ALT_RIGHT_ON) != 0); // 32
    }


    @Override
    public boolean hasCtrl() {
        return ((bits & META_CTRL_ON) != 0); // 4096
    }

    public boolean hasCtrlLeft() {
        return ((bits & META_CTRL_LEFT_ON) != 0); // 8192
    }

    public boolean hasCtrlRight() {
        return ((bits & META_CTRL_RIGHT_ON) != 0); // 16384
    }


    @Override
    public boolean hasSuper() {
        return ((bits & META_META_ON) != 0); // 65536
    }

    public boolean hasSuperLeft() {
        return ((bits & META_META_LEFT_ON) != 0); // 131072
    }

    public boolean hasSuperRight() {
        return ((bits & META_META_RIGHT_ON) != 0); // 262144
    }


    @Override
    public boolean hasCapsLock() {
        return ((bits & META_CAPS_LOCK_ON) != 0); // 1048576
    }

    @Override
    public boolean hasNumLock() {
        return ((bits & META_NUM_LOCK_ON) != 0); // 2097152
    }

    public boolean hasScrollLock() {
        return ((bits & META_SCROLL_LOCK_ON) != 0); // 4194304
    }


    public boolean hasSym() {
        return ((bits & META_SYM_ON) != 0); // 4
    }

    public boolean hasMetaFunction() {
        return ((bits & META_FUNCTION_ON) != 0); // 8
    }

}