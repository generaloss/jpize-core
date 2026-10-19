
package jpize.android.context.input;

import android.view.MotionEvent;
import jpize.android.context.AndroidCallbacks;
import jpize.context.input.Action;
import jpize.context.input.MouseBtn;

import java.util.Arrays;

public final class AndroidTouchHandler {

    private final AndroidCallbacks callbacks;
    private final int[] pointerIds;

    public AndroidTouchHandler(AndroidCallbacks callbacks, int maxTouches) {
        if(maxTouches <= 0)
            throw new IllegalArgumentException("Argument 'maxTouches' must be positive");

        this.callbacks = callbacks;
        this.pointerIds = new int[maxTouches];

        Arrays.fill(pointerIds, -1);
    }

    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();

        switch(action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                final int pointerId = event.getPointerId(actionIndex);
                final int slot = this.allocateSlot(pointerId);

                if(slot >= 0) {
                    this.updatePointer(event, actionIndex, slot);
                    this.invokeMouseButton(slot, Action.DOWN);
                }

                this.updateAllPointers(event);
                return true;
            }

            case MotionEvent.ACTION_MOVE: {
                this.updateAllPointers(event);

                for(int slot = 0; slot < pointerIds.length; slot++)
                    if(pointerIds[slot] != -1)
                        this.invokeMouseButton(slot, Action.PRESSED);

                return true;
            }

            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP: {
                this.updateAllPointers(event);

                final int pointerId = event.getPointerId(actionIndex);
                final int slot = this.findSlot(pointerId);

                if(slot >= 0) {
                    this.invokeMouseButton(slot, Action.UP);
                    this.releasePointer(pointerId);
                }

                return true;
            }

            case MotionEvent.ACTION_CANCEL: {
                for(int slot = 0; slot < pointerIds.length; slot++)
                    if(pointerIds[slot] != -1)
                        this.invokeMouseButton(slot, Action.UP);

                Arrays.fill(pointerIds, -1);
                return true;
            }

            default:
                return false;
        }
    }

    private int allocateSlot(int pointerId) {
        final int existingSlot = this.findSlot(pointerId);
        if(existingSlot >= 0)
            return existingSlot;

        for(int i = 0; i < pointerIds.length; i++) {
            if(pointerIds[i] == -1) {
                pointerIds[i] = pointerId;
                return i;
            }
        }

        return -1;
    }

    private int findSlot(int pointerId) {
        for(int i = 0; i < pointerIds.length; i++) {
            if(pointerIds[i] == pointerId)
                return i;
        }

        return -1;
    }

    private void updateAllPointers(MotionEvent event) {
        for(int i = 0; i < event.getPointerCount(); i++) {
            final int pointerId = event.getPointerId(i);
            final int slot = this.findSlot(pointerId);

            if(slot >= 0)
                this.updatePointer(event, i, slot);
        }
    }

    private void updatePointer(MotionEvent event, int pointerIndex, int slot) {
        callbacks.invokeCursorPos(
            slot,
            event.getX(pointerIndex),
            event.getY(pointerIndex)
        );
    }

    private void releasePointer(int pointerId) {
        final int slot = this.findSlot(pointerId);

        if(slot >= 0)
            pointerIds[slot] = -1;
    }

    private void invokeMouseButton(int cursorIndex, Action action) {
        callbacks.invokeMouseButton(
            cursorIndex,
            MouseBtn.LEFT,
            action,
            new AndroidMods(0)
        );
    }
}
