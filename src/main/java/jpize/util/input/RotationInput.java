package jpize.util.input;

import jpize.glfw.Glfw;
import jpize.glfw.callback.GlfwCursorPosCallback;
import jpize.glfw.input.GlfwCursorMode;
import jpize.glfw.input.GlfwInput;
import jpize.glfw.window.GlfwWindow;
import jpize.util.math.EulerAngles;
import jpize.util.math.Maths;

public class RotationInput {

    private EulerAngles target;
    private float yaw, pitch;
    private int lockInputs;

    private boolean mirrorHorizontal, mirrorVertical;
    private float speed, smoothness;
    private boolean clampPitch;
    private boolean enabled;
    private final GlfwCursorPosCallback cursorCallback;

    private float prevX, prevY;

    public RotationInput(EulerAngles target, boolean enabled) {
        this.cursorCallback = this::onCursorCallback;
        this.setTarget(target);
        this.lockInputs();
        this.setSpeed(1F);
        this.setClampPitch(true);
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


    private void onCursorCallback(GlfwWindow window, float x, float y) {
        final GlfwInput input = window.getInput();
        final float dx = (input.getCursorX() - prevX);
        final float dy = (input.getCursorNativeY() - prevY);
        this.centerCursor(window, input);
        prevX = input.getCursorX();
        prevY = input.getCursorNativeY();

        if(lockInputs < 1){
            final float invSmoothness = (1F - smoothness);

            yaw   += dx * speed * Maths.sigFlag(mirrorHorizontal);
            pitch += dy * speed * Maths.sigFlag(mirrorVertical  );
            if(clampPitch)
                pitch = Maths.clamp(pitch, -90F, 90F);

            target.yaw   += (  yaw - target.yaw  ) * invSmoothness;
            target.pitch += (pitch - target.pitch) * invSmoothness;
            if(clampPitch)
                target.clampPitch();
        }else{
            lockInputs--;
        }
    }

    private void centerCursor(GlfwWindow window, GlfwInput input) {
        input.setCursorPos(window.getWidth() * 0.5D, window.getHeight() * 0.5D);
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        final GlfwWindow window = GlfwWindow.getCurrentContext();
        final GlfwInput input = window.getInput();

        input.setInputModeCursor(enabled ? GlfwCursorMode.HIDDEN : GlfwCursorMode.NORMAL);
        if(Glfw.rawMouseMotionSupported())
            input.setInputModeRawMouseMotion(enabled);

        if(enabled){
            this.centerCursor(window, input);
            window.getCallbacks().addCursorPosCallback(cursorCallback);
        }else{
            window.getCallbacks().removeCursorPosCallback(cursorCallback);
        }
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


    public void lockInputs(int count) {
        lockInputs = count;
    }

    public void lockInputs() {
        this.lockInputs(10);
    }
    
}
