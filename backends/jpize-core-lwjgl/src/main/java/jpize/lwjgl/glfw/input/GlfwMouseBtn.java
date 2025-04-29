package jpize.lwjgl.glfw.input;

import jpize.context.input.MouseBtn;

public class GlfwMouseBtn {

    private static final MouseBtn[] BY_GLFW_VALUE = {
        MouseBtn.LEFT,   // GLFW_MOUSE_BUTTON_1 = 0
        MouseBtn.RIGHT,  // GLFW_MOUSE_BUTTON_2 = 1
        MouseBtn.MIDDLE, // GLFW_MOUSE_BUTTON_3 = 2
        MouseBtn.B4,     // GLFW_MOUSE_BUTTON_4 = 3
        MouseBtn.B5,     // GLFW_MOUSE_BUTTON_5 = 4
        MouseBtn.B6,     // GLFW_MOUSE_BUTTON_6 = 5
        MouseBtn.B7,     // GLFW_MOUSE_BUTTON_7 = 6
        MouseBtn.B8,     // GLFW_MOUSE_BUTTON_8 = 7
    };

    public static MouseBtn byGlfwValue(int value) {
        return BY_GLFW_VALUE[value];
    }

    public static int getGlfwValue(MouseBtn button) {
        for(int i = 0; i < BY_GLFW_VALUE.length; i++)
            if(BY_GLFW_VALUE[i] == button)
                return i;
        return -1;
    }

}
