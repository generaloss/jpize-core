package jpize.lwjgl.glfw.callback;

import jpize.io.input.ConnectEvent;
import jpize.lwjgl.glfw.GlfwJoystick;

public interface GlfwJoystickCallback {

    void invoke(GlfwJoystick joystick, ConnectEvent event);

}
