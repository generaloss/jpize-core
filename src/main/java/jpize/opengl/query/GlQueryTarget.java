package jpize.opengl.query;

import static jpize.opengl.gl.GL43I.*;

public enum GlQueryTarget {

    TIME_ELAPSED                          (GL_TIME_ELAPSED                         ), // 35007
    SAMPLES_PASSED                        (GL_SAMPLES_PASSED                       ), // 35092
    ANY_SAMPLES_PASSED                    (GL_ANY_SAMPLES_PASSED                   ), // 35887
    PRIMITIVES_GENERATED                  (GL_PRIMITIVES_GENERATED                 ), // 35975
    TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN (GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN), // 35976
    ANY_SAMPLES_PASSED_CONSERVATIVE       (GL_ANY_SAMPLES_PASSED_CONSERVATIVE      ); // 36202

    public final int value;
    
    GlQueryTarget(int value) {
        this.value = value;
    }
    
}
