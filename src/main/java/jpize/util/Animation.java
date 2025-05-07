package jpize.util;

import jpize.util.math.Maths;

public class Animation<T> {

    public enum Mode {
        NORMAL,
        REVERSED,
        LOOP,
        LOOP_REVERSED,
        LOOP_PINGPONG,
        LOOP_RANDOM,
    }

    private float frameDuration;
    private Mode mode;
    private T[] frames;

    private int lastFrameIndex;
    private int prevFrameIndex;
    private float prevTime;

    @SafeVarargs
    public Animation(float frameDuration, Mode mode, T... frames) {
        this.setFrameDuration(frameDuration);
        this.setType(mode);
        this.setFrames(frames);
    }


    public float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }


    public Mode getType() {
        return mode;
    }

    public void setType(Mode mode) {
        this.mode = mode;
    }


    public T[] getFrames() {
        return frames;
    }

    @SafeVarargs
    public final void setFrames(T... frames) {
        this.frames = frames;
        this.lastFrameIndex = (frames.length - 1);
    }

    public T getFrame(int index) {
        if(index > lastFrameIndex)
            return null;
        return frames[index];
    }

    public T getKeyFrame(float time) {
        return this.getFrame(this.getKeyFrameIndex(time));
    }

    public int getKeyFrameIndex(float time) {
        if(lastFrameIndex < 1)
            return 0;

        int frameIndex = (int) (time / frameDuration);
        frameIndex = switch(mode) {
            case NORMAL -> Math.min(frameIndex, lastFrameIndex);
            case LOOP -> (frameIndex % frames.length);
            case LOOP_PINGPONG -> {
                frameIndex = (frameIndex % (lastFrameIndex * 2));
                if(frameIndex > lastFrameIndex)
                    frameIndex = (lastFrameIndex * 2 - frameIndex);
                yield frameIndex;
            }
            case LOOP_RANDOM -> ((int) (prevTime / frameDuration) != frameIndex) ? Maths.random(lastFrameIndex) : prevFrameIndex;
            case REVERSED -> Math.max(0, lastFrameIndex - frameIndex);
            case LOOP_REVERSED -> (lastFrameIndex - (frameIndex % frames.length));
        };

        prevTime = time;
        prevFrameIndex = frameIndex;
        return frameIndex;
    }

}
