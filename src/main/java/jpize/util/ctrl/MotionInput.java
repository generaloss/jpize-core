package jpize.util.ctrl;

import jpize.util.math.vector.Vec3f;
import jpize.util.math.vector.Vec3i;
import jpize.glfw.input.Key;

public class MotionInput {

    private static final Vec3f FORWARD = new Vec3f(0, 0, 1);
    private static final Vec3f UP = new Vec3f(0, 1, 0);
    private static final Vec3f LEFT = new Vec3f(-1, 0, 0);

    private final Vec3i motion;
    private final Vec3f directedMotion;

    public MotionInput() {
        this.motion = new Vec3i();
        this.directedMotion = new Vec3f();
    }

    public void update(float yawDegrees) {
        motion.zero();

        if(Key.W.pressed()) motion.add(FORWARD);
        if(Key.S.pressed()) motion.sub(FORWARD);
        if(Key.A.pressed()) motion.add(LEFT);
        if(Key.D.pressed()) motion.sub(LEFT);
        if(Key.SPACE.pressed()) motion.add(UP);
        if(Key.LSHIFT.pressed()) motion.sub(UP);

        directedMotion.set(motion).rotateY(yawDegrees);
    }

    public Vec3i getMotion() {
        return motion;
    }

    public Vec3f getMotionDirected() {
        return directedMotion;
    }

}
