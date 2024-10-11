package jpize.util.ctrl;

import jpize.glfw.Glfw;
import jpize.glfw.callback.GlfwCursorPosCallback;
import jpize.glfw.input.GlfwCursorMode;
import jpize.glfw.input.GlfwInput;
import jpize.glfw.monitor.GlfwMonitor;
import jpize.glfw.window.GlfwAttrib;
import jpize.glfw.window.GlfwWindow;
import jpize.util.math.EulerAngles;
import jpize.util.math.Maths;

public class EulerRotCtrl {

    private static final float SPEED_MULTIPLIER = 10F / GlfwMonitor.getPrimaryMonitor().getVerticalDPI();

    private EulerAngles target;
    private float yaw, pitch;
    private boolean lockNextFrame;

    private boolean mirrorHorizontal, mirrorVertical;
    private float speed, smoothness;
    private boolean clampPitch;
    private boolean enabled;
    private final GlfwCursorPosCallback posCallback;

    public EulerRotCtrl(EulerAngles target, boolean enabled) {
        this.target  = target;
        this.lockNextFrame = false;
        this.speed = 1F;
        this.smoothness = 0F;
        this.clampPitch = true;
        this.posCallback = this::onCursorPos;
        this.setEnabled(enabled);
    }

    public EulerRotCtrl(EulerAngles target) {
        this(target, true);
    }


    public EulerAngles getTarget() {
        return target;
    }

    public void setTarget(EulerAngles target) {
        this.target = target;
    }


    public void setEnabled(boolean enabled) {
        final GlfwWindow window = GlfwWindow.getCurrentContext();
        final GlfwInput input = window.getInput();

        this.enabled = enabled;
        // raw mouse motion
        if(Glfw.rawMouseMotionSupported())
            input.setInputModeRawMouseMotion(enabled);
        // input mode
        input.setInputModeCursor(enabled ? GlfwCursorMode.DISABLED : GlfwCursorMode.NORMAL);
        // mouse position callback
        if(enabled) window.getCallbacks().addCursorPosCallback(posCallback);
        else window.getCallbacks().removeCursorPosCallback(posCallback);
    }

    public void toggleEnabled() {
        setEnabled(!enabled);
    }

    public boolean isEnabled() {
        return enabled;
    }


    private float oldX, oldY;

    private void onCursorPos(GlfwWindow window, float x, float y) {
        final float dx = (x - oldX);
        final float dy = (y - oldY);
        oldX = x;
        oldY = y;

        if(GlfwWindow.getCurrentContext().getAttrib(GlfwAttrib.FOCUSED) && enabled){

            if(!lockNextFrame){
                final float invSmoothness = (1 - smoothness);

                yaw   -= SPEED_MULTIPLIER * speed * Maths.sigFlag(mirrorHorizontal) * dx;
                pitch -= SPEED_MULTIPLIER * speed * Maths.sigFlag(mirrorVertical  ) * dy;
                if(clampPitch)
                    pitch = Maths.clamp(pitch, -90, 90);

                target.yaw   += (  yaw - target.yaw  ) * invSmoothness;
                target.pitch += (pitch - target.pitch) * invSmoothness;
                if(clampPitch)
                    target.clampPitch();
            }
            lockNextFrame = false;
        }
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


    public void lockNextFrame() {
        lockNextFrame = true;
    }
    
}
