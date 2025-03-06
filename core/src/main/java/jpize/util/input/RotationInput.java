package jpize.util.input;

import jpize.app.Jpize;
import jpize.io.callback.CursorPosCallback;
import jpize.io.input.CursorMode;
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
    private final CursorPosCallback cursorCallback;

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


    private void onCursorCallback(float x, float y) {
        final float dx = (prevX - Jpize.input.getCursorX());
        final float dy = (prevY - Jpize.input.getCursorNativeY());
        this.centerCursor();
        prevX = Jpize.input.getCursorX();
        prevY = Jpize.input.getCursorNativeY();

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

    private void centerCursor() {
        Jpize.input.setCursorPos(Jpize.getWidth() * 0.5D, Jpize.getHeight() * 0.5D);
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        Jpize.input.setCursorMode(enabled ? CursorMode.HIDDEN : CursorMode.NORMAL);
        if(Jpize.input.isRawMouseMotionSupported())
            Jpize.input.setRawMouseMotion(enabled);

        if(enabled){
            this.centerCursor();
            Jpize.callbacks.addCursorPosCallback(cursorCallback);
        }else{
            Jpize.callbacks.removeCursorPosCallback(cursorCallback);
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
