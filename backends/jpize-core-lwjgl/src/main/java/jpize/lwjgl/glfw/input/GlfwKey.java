package jpize.lwjgl.glfw.input;

import jpize.context.input.Key;
import org.lwjgl.glfw.GLFW;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwKey {

    private static final int[] GLFW_KEY_VALUES = {
        GLFW_KEY_SPACE,         // 32
        GLFW_KEY_APOSTROPHE,    // 39
        GLFW_KEY_COMMA,         // 44
        GLFW_KEY_MINUS,         // 45
        GLFW_KEY_PERIOD,        // 46
        GLFW_KEY_SLASH,         // 47
        GLFW_KEY_0,             // 48
        GLFW_KEY_1,             // 49
        GLFW_KEY_2,             // 50
        GLFW_KEY_3,             // 51
        GLFW_KEY_4,             // 52
        GLFW_KEY_5,             // 53
        GLFW_KEY_6,             // 54
        GLFW_KEY_7,             // 55
        GLFW_KEY_8,             // 56
        GLFW_KEY_9,             // 57
        GLFW_KEY_SEMICOLON,     // 59
        GLFW_KEY_EQUAL,         // 61
        GLFW_KEY_A,             // 65
        GLFW_KEY_B,             // 66
        GLFW_KEY_C,             // 67
        GLFW_KEY_D,             // 68
        GLFW_KEY_E,             // 69
        GLFW_KEY_F,             // 70
        GLFW_KEY_G,             // 71
        GLFW_KEY_H,             // 72
        GLFW_KEY_I,             // 73
        GLFW_KEY_J,             // 74
        GLFW_KEY_K,             // 75
        GLFW_KEY_L,             // 76
        GLFW_KEY_M,             // 77
        GLFW_KEY_N,             // 78
        GLFW_KEY_O,             // 79
        GLFW_KEY_P,             // 80
        GLFW_KEY_Q,             // 81
        GLFW_KEY_R,             // 82
        GLFW_KEY_S,             // 83
        GLFW_KEY_T,             // 84
        GLFW_KEY_U,             // 85
        GLFW_KEY_V,             // 86
        GLFW_KEY_W,             // 87
        GLFW_KEY_X,             // 88
        GLFW_KEY_Y,             // 89
        GLFW_KEY_Z,             // 90
        GLFW_KEY_LEFT_BRACKET,  // 91
        GLFW_KEY_BACKSLASH,     // 92
        GLFW_KEY_RIGHT_BRACKET, // 93
        GLFW_KEY_GRAVE_ACCENT,  // 96
        GLFW_KEY_WORLD_1,       // 161
        GLFW_KEY_WORLD_2,       // 162
        GLFW_KEY_ESCAPE,        // 256
        GLFW_KEY_ENTER,         // 257
        GLFW_KEY_TAB,           // 258
        GLFW_KEY_BACKSPACE,     // 259
        GLFW_KEY_INSERT,        // 260
        GLFW_KEY_DELETE,        // 261
        GLFW_KEY_RIGHT,         // 262
        GLFW_KEY_LEFT,          // 263
        GLFW_KEY_DOWN,          // 264
        GLFW_KEY_UP,            // 265
        GLFW_KEY_PAGE_UP,       // 266
        GLFW_KEY_PAGE_DOWN,     // 267
        GLFW_KEY_HOME,          // 268
        GLFW_KEY_END,           // 269
        GLFW_KEY_CAPS_LOCK,     // 280
        GLFW_KEY_SCROLL_LOCK,   // 281
        GLFW_KEY_NUM_LOCK,      // 282
        GLFW_KEY_PRINT_SCREEN,  // 283
        GLFW_KEY_PAUSE,         // 284
        GLFW_KEY_F1,            // 290
        GLFW_KEY_F2,            // 291
        GLFW_KEY_F3,            // 292
        GLFW_KEY_F4,            // 293
        GLFW_KEY_F5,            // 294
        GLFW_KEY_F6,            // 295
        GLFW_KEY_F7,            // 296
        GLFW_KEY_F8,            // 297
        GLFW_KEY_F9,            // 298
        GLFW_KEY_F10,           // 299
        GLFW_KEY_F11,           // 300
        GLFW_KEY_F12,           // 301
        GLFW_KEY_F13,           // 302
        GLFW_KEY_F14,           // 303
        GLFW_KEY_F15,           // 304
        GLFW_KEY_F16,           // 305
        GLFW_KEY_F17,           // 306
        GLFW_KEY_F18,           // 307
        GLFW_KEY_F19,           // 308
        GLFW_KEY_F20,           // 309
        GLFW_KEY_F21,           // 310
        GLFW_KEY_F22,           // 311
        GLFW_KEY_F23,           // 312
        GLFW_KEY_F24,           // 313
        GLFW_KEY_F25,           // 314
        GLFW_KEY_KP_0,          // 320
        GLFW_KEY_KP_1,          // 321
        GLFW_KEY_KP_2,          // 322
        GLFW_KEY_KP_3,          // 323
        GLFW_KEY_KP_4,          // 324
        GLFW_KEY_KP_5,          // 325
        GLFW_KEY_KP_6,          // 326
        GLFW_KEY_KP_7,          // 327
        GLFW_KEY_KP_8,          // 328
        GLFW_KEY_KP_9,          // 329
        GLFW_KEY_KP_DECIMAL,    // 330
        GLFW_KEY_KP_DIVIDE,     // 331
        GLFW_KEY_KP_MULTIPLY,   // 332
        GLFW_KEY_KP_SUBTRACT,   // 333
        GLFW_KEY_KP_ADD,        // 334
        GLFW_KEY_KP_ENTER,      // 335
        GLFW_KEY_KP_EQUAL,      // 336
        GLFW_KEY_LEFT_SHIFT,    // 340
        GLFW_KEY_LEFT_CONTROL,  // 341
        GLFW_KEY_LEFT_ALT,      // 342
        GLFW_KEY_LEFT_SUPER,    // 343
        GLFW_KEY_RIGHT_SHIFT,   // 344
        GLFW_KEY_RIGHT_CONTROL, // 345
        GLFW_KEY_RIGHT_ALT,     // 346
        GLFW_KEY_RIGHT_SUPER,   // 347
        GLFW_KEY_MENU,          // 348
    };

    public static Key byGlfwValue(int value) {
        final int index = Arrays.binarySearch(GLFW_KEY_VALUES, value);
        if(index < 0)
            return null;
        return Key.values()[index];
    }

    public static int getGlfwValue(Key key) {
        return GLFW_KEY_VALUES[key.ordinal()];
    }


    public static int getScancode(Key key) {
        final int glfwKeyValue = getGlfwValue(key);
        return GLFW.glfwGetKeyScancode(glfwKeyValue);
    }

    public static String getKeyName(Key key) {
        final int glfwKeyValue = getGlfwValue(key);
        final int scancode = getScancode(key);
        return GLFW.glfwGetKeyName(glfwKeyValue, scancode);
    }

}