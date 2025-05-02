package jpize.opengl.buffer;

import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.*;

public class UniformBufferStd140 extends GLUniformBuffer {

    private int offset;

    public void reset() {
        offset = 0;
    }

    private void alignOffset(int alignment) {
        offset = (offset + (alignment - 1)) & -alignment;
    }


    public void put(Matrix4f mat) {
        this.alignOffset(16);
        for(int i = 0; i < 4; i++) {
            final int j = (i * 4);
            super.setSubData(offset, mat.values[j], mat.values[j + 1], mat.values[j + 2], mat.values[j + 3]);
            offset += 16;
        }
    }


    public void put(Vec2f vec) {
        this.alignOffset(8);
        super.setSubData(offset, vec.x, vec.y);
        offset += 8;
    }

    public void put(Vec3f vec) {
        this.alignOffset(16);
        super.setSubData(offset, vec.x, vec.y, vec.z);
        offset += 16;
    }

    public void put(Vec4f vec) {
        this.alignOffset(16);
        super.setSubData(offset, vec.x, vec.y, vec.z, vec.w);
        offset += 16;
    }


    public void put(Vec2f... vecs) {
        this.alignOffset(16);
        for(Vec2f vec: vecs){
            super.setSubData(offset, vec.x, vec.y);
            offset += 16;
        }
    }

    public void put(Vec3f... vecs) {
        this.alignOffset(16);
        for(Vec3f vec: vecs){
            super.setSubData(offset, vec.x, vec.y, vec.z);
            offset += 16;
        }
    }

    public void put(Vec4f... vecs) {
        this.alignOffset(16);
        for(Vec4f vec: vecs){
            super.setSubData(offset, vec.x, vec.y, vec.z, vec.w);
            offset += 16;
        }
    }


    public void put(int value) {
        this.alignOffset(4);
        super.setSubData(offset, value);
        offset += 4;
    }

    public void put(long value) {
        this.alignOffset(8);
        super.setSubData(offset, value);
        offset += 8;
    }

    public void put(float value) {
        this.alignOffset(4);
        super.setSubData(offset, value);
        offset += 4;
    }

    public void put(short value) {
        this.alignOffset(4);
        super.setSubData(offset, (int) value);
        offset += 4;
    }

    public void put(double value) {
        this.alignOffset(8);
        super.setSubData(offset, value);
        offset += 8;
    }


    public void put(int... values) {
        this.alignOffset(16);
        for(int value: values){
            super.setSubData(offset, value);
            offset += 16;
        }
    }

    public void put(long... values) {
        this.alignOffset(16);
        for(long value: values){
            super.setSubData(offset, value);
            offset += 16;
        }
    }

    public void put(float... values) {
        this.alignOffset(16);
        for(float value: values){
            super.setSubData(offset, value);
            offset += 16;
        }
    }

    public void put(short... values) {
        this.alignOffset(16);
        for(short value: values){
            super.setSubData(offset, value);
            offset += 16;
        }
    }

    public void put(double... values) {
        this.alignOffset(16);
        for(double value: values){
            super.setSubData(offset, value);
            offset += 16;
        }
    }

}
