package jpize.util.input;

import jpize.glfw.Glfw;
import jpize.glfw.callback.GlfwCursorPosCallback;
import jpize.glfw.input.GlfwCursorMode;
import jpize.glfw.input.GlfwInput;
import jpize.glfw.monitor.GlfwMonitor;
import jpize.glfw.window.GlfwAttrib;
import jpize.glfw.window.GlfwWindow;
import jpize.util.math.EulerAngles;
import jpize.util.math.Maths;

public class RotationInput {

    private static final float SPEED_MULTIPLIER = 10F / GlfwMonitor.getPrimaryMonitor().getVerticalDPI();

    private EulerAngles target;
    private float yaw, pitch;
    private boolean lockNextInput;

    private boolean mirrorHorizontal, mirrorVertical;
    private float speed, smoothness;
    private boolean clampPitch;
    private boolean enabled;
    private final GlfwCursorPosCallback posCallback;

    private float prevX, prevY;

    public RotationInput(EulerAngles target, boolean enabled) {
        this.target = target;
        this.lockNextInput = false;
        this.speed = 1F;
        this.smoothness = 0F;
        this.clampPitch = true;
        this.posCallback = this::onCursorPos;
        this.setEnabled(enabled);
    }

    public RotationInput(EulerAngles target) {
        this(target, true);
    }


    public EulerAngles getTarget() {
        return target;
    }

    public void setTarget(EulerAngles target) {
        this.target = target;
    }


    private void onCursorPos(GlfwWindow window, float x, float y) {
        final float dx = (x - prevX);
        final float dy = (y - prevY);

        final GlfwInput input = window.getInput();
        this.setCursorPosCentered(window, input);
        prevX = input.getCursorX();
        prevY = input.getCursorY();

        if(GlfwWindow.getCurrentContext().getAttrib(GlfwAttrib.FOCUSED) && enabled){
            if(!lockNextInput){
                final float invSmoothness = (1F - smoothness);

                yaw   -= SPEED_MULTIPLIER * speed * Maths.sigFlag(mirrorHorizontal) * dx;
                pitch -= SPEED_MULTIPLIER * speed * Maths.sigFlag(mirrorVertical  ) * dy;
                if(clampPitch)
                    pitch = Maths.clamp(pitch, -90F, 90F);

                target.yaw   += (  yaw - target.yaw  ) * invSmoothness;
                target.pitch += (pitch - target.pitch) * invSmoothness;
                if(clampPitch)
                    target.clampPitch();
            }
            lockNextInput = false;
        }
    }

    private void setCursorPosCentered(GlfwWindow window, GlfwInput input) {
        input.setCursorPos(window.getWidth() * 0.5, window.getHeight() * 0.5);
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        final GlfwWindow window = GlfwWindow.getCurrentContext();
        final GlfwInput input = window.getInput();
        // input mode
        input.setInputModeCursor(enabled ? GlfwCursorMode.HIDDEN : GlfwCursorMode.NORMAL);
        if(Glfw.rawMouseMotionSupported())
            input.setInputModeRawMouseMotion(enabled);
        this.setCursorPosCentered(window, input);
        // callback
        if(enabled) window.getCallbacks().addCursorPosCallback(posCallback);
        else window.getCallbacks().removeCursorPosCallback(posCallback);
    }

    public void toggleEnabled() {
        this.setEnabled(!enabled);
    }

    public void enable() {
        this.setEnabled(true);
    }

    public void disable() {
        this.setEnabled(false);
    }

    public boolean isEnabled() {
        return enabled;
    }


    public boolean isMirrorHorizontal() {
        return mirrorHorizontal;
    }

    public boolean isMirrorVertical() {
        return mirrorVertical;
    }

    public void setMirrorHorizontal(boolean mirrorHorizontal) {
        this.mirrorHorizontal = mirrorHorizontal;
    }

    public void setMirrorVertical(boolean mirrorVertical) {
        this.mirrorVertical = mirrorVertical;
    }
    
    
    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public float getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(float smoothness) {
        this.smoothness = smoothness;
    }


    public boolean isClampPitch() {
        return clampPitch;
    }

    public void setClampPitch(boolean clampPitch) {
        this.clampPitch = clampPitch;
    }


    public void lockNextInput() {
        lockNextInput = true;
    }
    
}
