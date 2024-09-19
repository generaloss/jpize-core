package jpize.glfw.callback;

import jpize.glfw.GlfwEvent;
import jpize.glfw.GlfwJoystick;

public interface GlfwJoystickCallback {

    void invoke(GlfwJoystick joystick, GlfwEvent event);

}
