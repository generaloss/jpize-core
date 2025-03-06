package jpize.util.input;

import jpize.io.input.Key;
import jpize.util.math.vector.Vec3f;
import jpize.util.math.vector.Vec3i;
public class MotionInput {

    private static final Vec3i FORWARD = new Vec3i(1, 0, 0);
    private static final Vec3i UP = new Vec3i(0, 1, 0);
    private static final Vec3i LEFT = new Vec3i(0, 0, 1);

    private final Vec3i motion;
    private final Vec3f directedMotion;

    public MotionInput() {
        this.motion = new Vec3i();
        this.directedMotion = new Vec3f();
    }

    public void update(double yawDegrees) {
        motion.zero();

        if(Key.W.pressed()) motion.add(FORWARD);
        if(Key.S.pressed()) motion.sub(FORWARD);
        if(Key.A.pressed()) motion.add(LEFT);
        if(Key.D.pressed()) motion.sub(LEFT);
        if(Key.SPACE.pressed())  motion.add(UP);
        if(Key.LSHIFT.pressed()) motion.sub(UP);

        directedMotion.set(motion).rotateY(yawDegrees).nor();
    }

    public Vec3i getMotion() {
        return motion;
    }
    
    public Vec3f getMotionDirected() {
        return directedMotion;
    }

}
