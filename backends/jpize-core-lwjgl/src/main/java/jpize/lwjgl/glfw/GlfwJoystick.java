package jpize.lwjgl.glfw;

import jpize.context.input.ConnectEvent;
import jpize.lwjgl.glfw.callback.GlfwJoystickCallback;
import org.lwjgl.glfw.GLFWGamepadState;
import org.lwjgl.system.Callback;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class GlfwJoystick {

    private static final Map<Integer, GlfwJoystick> JOYSTICKS = new HashMap<>();

    private final int ID;

    public GlfwJoystick(int ID) {
        this.ID = ID;
    }


    public String getJoystickName() {
        return glfwGetJoystickName(ID);
    }

    public FloatBuffer getAxes() {
        return glfwGetJoystickAxes(ID);
    }

    public ByteBuffer getButtons() {
        return glfwGetJoystickButtons(ID);
    }

    public void getHats() {
        glfwGetJoystickHats(ID);
    }

    public String getGUID() {
        return glfwGetJoystickGUID(ID);
    }

    public boolean isPresent() {
        return glfwJoystickPresent(ID);
    }


    public boolean isGamepad() {
        return glfwJoystickIsGamepad(ID);
    }

    public String getGamepadName() {
        return glfwGetGamepadName(ID);
    }

    public GLFWGamepadState getGamepadState() {
        final GLFWGamepadState state = GLFWGamepadState.calloc();
        glfwGetGamepadState(ID, state);
        return state;
    }

    public void updateGamepadMappings(ByteBuffer string) {
        glfwUpdateGamepadMappings(string);
    }


    public static GlfwJoystick getJoystick(int ID) {
        if(JOYSTICKS.containsKey(ID))
            return JOYSTICKS.get(ID);

        final GlfwJoystick joystick = new GlfwJoystick(ID);
        if(joystick.isPresent())
            JOYSTICKS.put(ID, joystick);
        return joystick;
    }

    private static Callback callbackJoystick;
    private static final List<GlfwJoystickCallback> callbacksJoystick = new ArrayList<>();

    public static void setJoystickCallback(GlfwJoystickCallback callback) {
        callbacksJoystick.add(callback);
        if(callbackJoystick != null)
            return;

        callbackJoystick = glfwSetJoystickCallback((ID, eventCode) -> {
            final ConnectEvent event = GlfwConnectEvent.byGlfwValue(eventCode);
            final GlfwJoystick joystick;

            if(event.isConnected()){
                if(!JOYSTICKS.containsKey(ID))
                    JOYSTICKS.put(ID, new GlfwJoystick(ID));
                joystick = JOYSTICKS.get(ID);
            }else{
                joystick = JOYSTICKS.remove(ID);
            }
            callbacksJoystick.forEach(c -> c.invoke(joystick, event));
        });
    }

}
