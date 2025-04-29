package jpize.android.context.input;
import jpize.context.input.Key;
import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static android.view.KeyEvent.*;

public class AndroidKey {

    private static final int[] ANDROID_KEYCODES = {
        KEYCODE_SPACE,           // 62
        KEYCODE_APOSTROPHE,      // 75
        KEYCODE_COMMA,           // 55
        KEYCODE_MINUS,           // 69
        KEYCODE_PERIOD,          // 56
        KEYCODE_SLASH,           // 76
        KEYCODE_0,               // 7
        KEYCODE_1,               // 8
        KEYCODE_2,               // 9
        KEYCODE_3,               // 10
        KEYCODE_4,               // 11
        KEYCODE_5,               // 12
        KEYCODE_6,               // 13
        KEYCODE_7,               // 14
        KEYCODE_8,               // 15
        KEYCODE_9,               // 16
        KEYCODE_SEMICOLON,       // 74
        KEYCODE_EQUALS,          // 70
        KEYCODE_A,               // 29
        KEYCODE_B,               // 30
        KEYCODE_C,               // 31
        KEYCODE_D,               // 32
        KEYCODE_E,               // 33
        KEYCODE_F,               // 34
        KEYCODE_G,               // 35
        KEYCODE_H,               // 36
        KEYCODE_I,               // 37
        KEYCODE_J,               // 38
        KEYCODE_K,               // 39
        KEYCODE_L,               // 40
        KEYCODE_M,               // 41
        KEYCODE_N,               // 42
        KEYCODE_O,               // 43
        KEYCODE_P,               // 44
        KEYCODE_Q,               // 45
        KEYCODE_R,               // 46
        KEYCODE_S,               // 47
        KEYCODE_T,               // 48
        KEYCODE_U,               // 49
        KEYCODE_V,               // 50
        KEYCODE_W,               // 51
        KEYCODE_X,               // 52
        KEYCODE_Y,               // 53
        KEYCODE_Z,               // 54
        KEYCODE_LEFT_BRACKET,    // 71
        KEYCODE_BACKSLASH,       // 73
        KEYCODE_RIGHT_BRACKET,   // 72
        KEYCODE_GRAVE,           // 68  (GRAVE_ACCENT)
        0,                       //     [no WORLD_1]
        0,                       //     [no WORLD_2]
        KEYCODE_ESCAPE,          // 111
        KEYCODE_ENTER,           // 66
        KEYCODE_TAB,             // 61
        KEYCODE_DEL,             // 67  (BACKSPACE)
        KEYCODE_INSERT,          // 124
        KEYCODE_FORWARD_DEL,     // 112 (DELETE)
        KEYCODE_DPAD_RIGHT,      // 22  (RIGHT)
        KEYCODE_DPAD_LEFT,       // 21  (LEFT)
        KEYCODE_DPAD_DOWN,       // 20  (DOWN)
        KEYCODE_DPAD_UP,         // 19  (UP)
        KEYCODE_PAGE_UP,         // 92
        KEYCODE_PAGE_DOWN,       // 93
        KEYCODE_MOVE_HOME,       // 122
        KEYCODE_MOVE_END,        // 123
        KEYCODE_CAPS_LOCK,       // 115
        KEYCODE_SCROLL_LOCK,     // 116
        KEYCODE_NUM_LOCK,        // 143
        KEYCODE_SYSRQ,           // 120 (PRINT_SCREEN)
        KEYCODE_MEDIA_PAUSE,     // 127
        KEYCODE_F1,              // 131
        KEYCODE_F2,              // 132
        KEYCODE_F3,              // 133
        KEYCODE_F4,              // 134
        KEYCODE_F5,              // 135
        KEYCODE_F6,              // 136
        KEYCODE_F7,              // 137
        KEYCODE_F8,              // 138
        KEYCODE_F9,              // 139
        KEYCODE_F10,             // 140
        KEYCODE_F11,             // 141
        KEYCODE_F12,             // 142
        0,                       //     [no F13]
        0,                       //     [no F14]
        0,                       //     [no F15]
        0,                       //     [no F16]
        0,                       //     [no F17]
        0,                       //     [no F18]
        0,                       //     [no F19]
        0,                       //     [no F20]
        0,                       //     [no F21]
        0,                       //     [no F22]
        0,                       //     [no F23]
        0,                       //     [no F24]
        0,                       //     [no F25]
        KEYCODE_NUMPAD_0,        // 144
        KEYCODE_NUMPAD_1,        // 145
        KEYCODE_NUMPAD_2,        // 146
        KEYCODE_NUMPAD_3,        // 147
        KEYCODE_NUMPAD_4,        // 148
        KEYCODE_NUMPAD_5,        // 149
        KEYCODE_NUMPAD_6,        // 150
        KEYCODE_NUMPAD_7,        // 151
        KEYCODE_NUMPAD_8,        // 152
        KEYCODE_NUMPAD_9,        // 153
        KEYCODE_NUMPAD_DOT,      // 158 (NUMPAD_DECIMAL) // also there is comma :/
        KEYCODE_NUMPAD_DIVIDE,   // 154
        KEYCODE_NUMPAD_MULTIPLY, // 155
        KEYCODE_NUMPAD_SUBTRACT, // 156
        KEYCODE_NUMPAD_ADD,      // 157
        KEYCODE_NUMPAD_ENTER,    // 160
        KEYCODE_NUMPAD_EQUALS,   // 161
        KEYCODE_SHIFT_LEFT,      // 59
        KEYCODE_CTRL_LEFT,       // 113
        KEYCODE_ALT_LEFT,        // 57
        KEYCODE_META_LEFT,       // 117 (LEFT_SUPER)
        KEYCODE_SHIFT_RIGHT,     // 60
        KEYCODE_CTRL_RIGHT,      // 114
        KEYCODE_ALT_RIGHT,       // 58
        KEYCODE_META_RIGHT,      // 118 (RIGHT_SUPER)
        KEYCODE_MENU,            // 82
    };

    public static int getAndroidKeycode(Key key) {
        return ANDROID_KEYCODES[key.ordinal()];
    }

    private static final Map<Integer, Key> BY_ANDROID_VALUE = Utils.make(new HashMap<>(), map -> {
        for(Key key: Key.values()) {
            final int androidKeyValue = getAndroidKeycode(key);
            map.put(androidKeyValue, key);
        }
    });

    public static Key byAndroidKeycode(int keycode) {
        if(keycode != 0)
            return BY_ANDROID_VALUE.get(keycode);
        return null;
    }


    public static int getScancode(Key key) {
        return 0; // no KeyEvent.getScancode(keycode) method
    }

    public static String getKeyName(Key key) {
        return key.toString(); // no KeyEvent.getKeyName(keycode) method
    }

}