package jpize.lwjgl.glfw.input;

import jpize.app.Jpize;
import jpize.lwjgl.glfw.Glfw;
import jpize.util.Utils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public enum GlfwKey {

    SPACE         (GLFW_KEY_SPACE        ), // 32
    APOSTROPHE    (GLFW_KEY_APOSTROPHE   ), // 39  // '
    COMMA         (GLFW_KEY_COMMA        ), // 44  // ,
    MINUS         (GLFW_KEY_MINUS        ), // 45  // -
    PERIOD        (GLFW_KEY_PERIOD       ), // 46  // .
    SLASH         (GLFW_KEY_SLASH        ), // 47  // /
    NUM_0         (GLFW_KEY_0            ), // 48
    NUM_1         (GLFW_KEY_1            ), // 49
    NUM_2         (GLFW_KEY_2            ), // 50
    NUM_3         (GLFW_KEY_3            ), // 51
    NUM_4         (GLFW_KEY_4            ), // 52
    NUM_5         (GLFW_KEY_5            ), // 53
    NUM_6         (GLFW_KEY_6            ), // 54
    NUM_7         (GLFW_KEY_7            ), // 55
    NUM_8         (GLFW_KEY_8            ), // 56
    NUM_9         (GLFW_KEY_9            ), // 57
    SEMICOLON     (GLFW_KEY_SEMICOLON    ), // 59  // ;
    EQUAL         (GLFW_KEY_EQUAL        ), // 61  // =
    A             (GLFW_KEY_A            ), // 65
    B             (GLFW_KEY_B            ), // 66
    C             (GLFW_KEY_C            ), // 67
    D             (GLFW_KEY_D            ), // 68
    E             (GLFW_KEY_E            ), // 69
    F             (GLFW_KEY_F            ), // 70
    G             (GLFW_KEY_G            ), // 71
    H             (GLFW_KEY_H            ), // 72
    I             (GLFW_KEY_I            ), // 73
    J             (GLFW_KEY_J            ), // 74
    K             (GLFW_KEY_K            ), // 75
    L             (GLFW_KEY_L            ), // 76
    M             (GLFW_KEY_M            ), // 77
    N             (GLFW_KEY_N            ), // 78
    O             (GLFW_KEY_O            ), // 79
    P             (GLFW_KEY_P            ), // 80
    Q             (GLFW_KEY_Q            ), // 81
    R             (GLFW_KEY_R            ), // 82
    S             (GLFW_KEY_S            ), // 83
    T             (GLFW_KEY_T            ), // 84
    U             (GLFW_KEY_U            ), // 85
    V             (GLFW_KEY_V            ), // 86
    W             (GLFW_KEY_W            ), // 87
    X             (GLFW_KEY_X            ), // 88
    Y             (GLFW_KEY_Y            ), // 89
    Z             (GLFW_KEY_Z            ), // 90
    LEFT_BRACKET  (GLFW_KEY_LEFT_BRACKET ), // 91  // [
    BACKSLASH     (GLFW_KEY_BACKSLASH    ), // 92  // \
    RIGHT_BRACKET (GLFW_KEY_RIGHT_BRACKET), // 93  // ]
    GRAVE_ACCENT  (GLFW_KEY_GRAVE_ACCENT ), // 96  // `
    WORLD_1       (GLFW_KEY_WORLD_1      ), // 161 // non-US #1
    WORLD_2       (GLFW_KEY_WORLD_2      ), // 162 // non-US #2
    ESCAPE        (GLFW_KEY_ESCAPE       ), // 256
    ENTER         (GLFW_KEY_ENTER        ), // 257
    TAB           (GLFW_KEY_TAB          ), // 258
    BACKSPACE     (GLFW_KEY_BACKSPACE    ), // 259
    INSERT        (GLFW_KEY_INSERT       ), // 260
    DELETE        (GLFW_KEY_DELETE       ), // 261
    RIGHT         (GLFW_KEY_RIGHT        ), // 262
    LEFT          (GLFW_KEY_LEFT         ), // 263
    DOWN          (GLFW_KEY_DOWN         ), // 264
    UP            (GLFW_KEY_UP           ), // 265
    PAGE_UP       (GLFW_KEY_PAGE_UP      ), // 266
    PAGE_DOWN     (GLFW_KEY_PAGE_DOWN    ), // 267
    HOME          (GLFW_KEY_HOME         ), // 268
    END           (GLFW_KEY_END          ), // 269
    CAPS_LOCK     (GLFW_KEY_CAPS_LOCK    ), // 280
    SCROLL_LOCK   (GLFW_KEY_SCROLL_LOCK  ), // 281
    NUM_LOCK      (GLFW_KEY_NUM_LOCK     ), // 282
    PRINT_SCREEN  (GLFW_KEY_PRINT_SCREEN ), // 283
    PAUSE         (GLFW_KEY_PAUSE        ), // 284
    F1            (GLFW_KEY_F1           ), // 290
    F2            (GLFW_KEY_F2           ), // 291
    F3            (GLFW_KEY_F3           ), // 292
    F4            (GLFW_KEY_F4           ), // 293
    F5            (GLFW_KEY_F5           ), // 294
    F6            (GLFW_KEY_F6           ), // 295
    F7            (GLFW_KEY_F7           ), // 296
    F8            (GLFW_KEY_F8           ), // 297
    F9            (GLFW_KEY_F9           ), // 298
    F10           (GLFW_KEY_F10          ), // 299
    F11           (GLFW_KEY_F11          ), // 300
    F12           (GLFW_KEY_F12          ), // 301
    F13           (GLFW_KEY_F13          ), // 302
    F14           (GLFW_KEY_F14          ), // 303
    F15           (GLFW_KEY_F15          ), // 304
    F16           (GLFW_KEY_F16          ), // 305
    F17           (GLFW_KEY_F17          ), // 306
    F18           (GLFW_KEY_F18          ), // 307
    F19           (GLFW_KEY_F19          ), // 308
    F20           (GLFW_KEY_F20          ), // 309
    F21           (GLFW_KEY_F21          ), // 310
    F22           (GLFW_KEY_F22          ), // 311
    F23           (GLFW_KEY_F23          ), // 312
    F24           (GLFW_KEY_F24          ), // 313
    F25           (GLFW_KEY_F25          ), // 314
    KP_0          (GLFW_KEY_KP_0         ), // 320
    KP_1          (GLFW_KEY_KP_1         ), // 321
    KP_2          (GLFW_KEY_KP_2         ), // 322
    KP_3          (GLFW_KEY_KP_3         ), // 323
    KP_4          (GLFW_KEY_KP_4         ), // 324
    KP_5          (GLFW_KEY_KP_5         ), // 325
    KP_6          (GLFW_KEY_KP_6         ), // 326
    KP_7          (GLFW_KEY_KP_7         ), // 327
    KP_8          (GLFW_KEY_KP_8         ), // 328
    KP_9          (GLFW_KEY_KP_9         ), // 329
    KP_DECIMAL    (GLFW_KEY_KP_DECIMAL   ), // 330
    KP_DIVIDE     (GLFW_KEY_KP_DIVIDE    ), // 331
    KP_MULTIPLY   (GLFW_KEY_KP_MULTIPLY  ), // 332
    KP_SUBTRACT   (GLFW_KEY_KP_SUBTRACT  ), // 333
    KP_ADD        (GLFW_KEY_KP_ADD       ), // 334
    KP_ENTER      (GLFW_KEY_KP_ENTER     ), // 335
    KP_EQUAL      (GLFW_KEY_KP_EQUAL     ), // 336
    LSHIFT        (GLFW_KEY_LEFT_SHIFT   ), // 340
    LCTRL         (GLFW_KEY_LEFT_CONTROL ), // 341
    LALT          (GLFW_KEY_LEFT_ALT     ), // 342
    LSUPER        (GLFW_KEY_LEFT_SUPER   ), // 343
    RSHIFT        (GLFW_KEY_RIGHT_SHIFT  ), // 344
    RCTRL         (GLFW_KEY_RIGHT_CONTROL), // 345
    RALT          (GLFW_KEY_RIGHT_ALT    ), // 346
    RSUPER        (GLFW_KEY_RIGHT_SUPER  ), // 347
    MENU          (GLFW_KEY_MENU         ); // 348

    public final int value;
    public final int scancode;

    GlfwKey(int value) {
        this.value = value;
        this.scancode = Glfw.getKeyScancode(this);
    }

    public int getScancode() {
        return scancode;
    }

    public String getName() {
        return Glfw.getKeyName(this);
    }


    public boolean pressed() {
        return Jpize.input().isKeyPressed(this);
    }

    public boolean down() {
        return Jpize.input().isKeyDown(this);
    }

    public boolean up() {
        return Jpize.input().isKeyUp(this);
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
