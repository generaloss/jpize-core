package jpize.android.context.input;

import jpize.android.context.JpizeAndroidContext;
import spatialmath.vector.Vec2f;

public class TouchMonitor {

    private final JpizeAndroidContext context;
    private Vec2f[] positions;

    public TouchMonitor(JpizeAndroidContext context) {
        this.context = context;
        context.getCallbacks().addInit(this::onInit);
    }


    private void onInit() {
        final int maxTouchesCount = context.getInput().getMaxMousesCount();
        this.positions = new Vec2f[maxTouchesCount];
        for(int i = 0; i < positions.length; i++)
            positions[i] = new Vec2f();

        context.getCallbacks().addCursorPos(this::onCursorPos);
    }

    private void onCursorPos(int cursorIndex, float x, float y) {
        positions[cursorIndex].set(x, y);
    }


    public Vec2f getCursorPos(int cursorIndex) {
        return positions[cursorIndex];
    }

}
