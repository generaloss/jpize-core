package jpize.lwjgl.glfw.callback;

import jpize.lwjgl.glfw.GlfwEvent;
import jpize.lwjgl.glfw.GlfwJoystick;

public interface GlfwJoystickCallback {

    void invoke(GlfwJoystick joystick, GlfwEvent event);

}
