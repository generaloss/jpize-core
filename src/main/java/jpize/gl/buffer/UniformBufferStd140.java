package jpize.gl.buffer;

import jpize.util.math.Mathc;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.*;

public class UniformBufferStd140 extends GlUniformBuffer {

    private int offset;

    public void reset() {
        offset = 0;
    }

    private void offset(int value) {
        offset += value;
        offset = Mathc.ceil(offset / 16F) * 16;
    }


    public void put(Matrix4f matrix) {
        super.setSubData(offset, matrix.val);
        this.offset(64);
    }


    public void put(Vec2f vec) {
        super.setSubData(offset, vec.x, vec.y);
        this.offset(8);
    }

    public void put(Vec3f vec) {
        super.setSubData(offset, vec.x, vec.y, vec.z);
        this.offset(16);
    }

    public void put(Vec4f vec) {
        super.setSubData(offset, vec.x, vec.y, vec.z, vec.w);
        this.offset(16);
    }

    public void put(Vec2f... vecs) {
        for(Vec2f vec: vecs){
            super.setSubData(offset, vec.x, vec.y);
            this.offset(16);
        }
    }

    public void put(Vec3f... vecs) {
        for(Vec3f vec: vecs){
            super.setSubData(offset, vec.x, vec.y, vec.z);
            this.offset(16);
        }
    }

    public void put(Vec4f... vecs) {
        for(Vec4f vec: vecs){
            super.setSubData(offset, vec.x, vec.y, vec.z, vec.w);
            this.offset(16);
        }
    }


    public void put(int value) {
        super.setSubData(offset, value);
        this.offset(4);
    }

    public void put(long value) {
        super.setSubData(offset, value);
        this.offset(8);
    }

    public void put(float value) {
        super.setSubData(offset, value);
        this.offset(4);
    }

    public void put(short value) {
        super.setSubData(offset, value);
        this.offset(2);
    }

    public void put(double value) {
        super.setSubData(offset, value);
        this.offset(8);
    }


    public void put(int... values) {
        for(int value: values){
            super.setSubData(offset, value);
            this.offset(16);
        }
    }

    public void put(long... values) {
        for(long value: values){
            super.setSubData(offset, value);
            this.offset(16);
        }
    }

    public void put(float... values) {
        for(float value: values){
            super.setSubData(offset, value);
            this.offset(16);
        }
    }

    public void put(short... values) {
        for(short value: values){
            super.setSubData(offset, value);
            this.offset(16);
        }
    }

    public void put(double... values) {
        for(double value: values){
            super.setSubData(offset, value);
            this.offset(16);
        }
    }

}
