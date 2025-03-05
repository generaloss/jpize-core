package jpize.lwjgl.glfw.callback;

import jpize.io.input.ConnectEvent;

public interface GlfwJoystickCallback {

    void invoke(ConnectEvent event);

}
