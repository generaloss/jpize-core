package io.gvicst.summoner.utils;

import jpize.opengl.texture.Texture2D;
import jpize.util.region.TextureRegion;

public class Animation extends jpize.util.Animation<TextureRegion> {

    private final Texture2D atlas;
    private final int frame_width;
    private final int frame_height;
    private final int frames_amount;

    public Animation(float frameDuration, Mode mode, String path, int columns_amount, int rows_amount, int frames_amount) {
        super(frameDuration, mode);
        this.atlas = new Texture2D(path);
        this.frames_amount = frames_amount;
        this.frame_width = atlas.getWidth()/columns_amount;
        this.frame_height = atlas.getHeight()/rows_amount;

        TextureRegion[] textures = new TextureRegion[frames_amount];
        int frame = 0;
        for (int row = 0; row < rows_amount; row++) {
            for (int column = 0; column < columns_amount; column++) {
                if(frame<frames_amount)
                    textures[frame] = new TextureRegion(atlas, column*frame_width, row*frame_height, frame_width, frame_height);
                frame++;
            }
        }
        this.setFrames(textures);
    }

    public int getFrameWidth() {
        return frame_width;
    }

    public int getFrameHeight() {
        return frame_height;
    }

    public int getFramesAmount() {
        return frames_amount;
    }
}

