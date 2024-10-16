package jpize.util.camera;

import jpize.util.math.EulerAngles;
import jpize.util.math.Mathc;
import jpize.util.math.Maths;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec3f;

public class Quaternion {

    public float x, y, z, w;

    public Quaternion() {
        this.w = 1F;
    }

    public Quaternion(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Quaternion(double x, double y, double z, double w) {
        this.set(x, y, z, w);
    }

    public Quaternion(Quaternion quaternion) {
        this.set(quaternion);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }


    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion set(double x, double y, double z, double w) {
        return this.set((float) x, (float) y, (float) z, (float) w);
    }

    public Quaternion set(Quaternion quaternion) {
        return this.set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }


    public Quaternion add(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    public Quaternion add(double x, double y, double z, double w) {
        return this.add((float) x, (float) y, (float) z, (float) w);
    }

    public Quaternion add(Quaternion quaternion) {
        return this.add(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }


    public Quaternion mul(float x, float y, float z, float w) {
        this.x = this.w * x + this.x * w + this.y * z - this.z * y;
        this.y = this.w * y + this.y * w + this.z * x - this.x * z;
        this.z = this.w * z + this.z * w + this.x * y - this.y * x;
        this.w = this.w * w - this.x * x - this.y * y - this.z * z;
        return this;
    }

    public Quaternion mul(double x, double y, double z, double w) {
        return this.mul((float) x, (float) y, (float) z, (float) w);
    }

    public Quaternion mul(Quaternion quaternion) {
        return this.mul(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }


    public Quaternion reset() {
        return this.set(0F, 0F, 0F, 1F);
    }


    public float len2() {
        return x * x + y * y + z * z + w * w;
    }

    public float len() {
        return Mathc.sqrt(this.len2());
    }


    public Quaternion nor() {
        float len = this.len2();
        if(len == 0 || len == 1)
            return this;

        len = 1F / Mathc.sqrt(len);
        w *= len;
        x *= len;
        y *= len;
        z *= len;
        return this;
    }


    public Quaternion conjugate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }


    public static Quaternion lerp(Quaternion dst, float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return dst.set(
            Maths.lerp(startX, endX, t),
            Maths.lerp(startY, endY, t),
            Maths.lerp(startZ, endZ, t),
            Maths.lerp(startW, endW, t)
        );
    }

    public static Quaternion lerp(Quaternion dst, Quaternion start, Quaternion end, float t) {
        return lerp(dst, start.x, start.y, start.z, start.w, end.x, end.y, end.z, end.w, t);
    }

    public Quaternion lerp(float startX, float startY, float startZ, float startW, float endX, float endY, float endZ, float endW, float t) {
        return lerp(this, startX, startY, startZ, startW, endX, endY, endZ, endW, t);
    }

    public Quaternion lerp(Quaternion start, Quaternion end, float t) {
        return lerp(this, start, end, t);
    }


    public Quaternion setRotation(Vec3f axis, double angleRad) {
        final float sin = Mathc.sin(angleRad * 0.5);
        x = axis.x * sin;
        y = axis.y * sin;
        z = axis.z * sin;
        w = Mathc.cos(angleRad * 0.5);

        return this;
    }

    public Quaternion setRotation(Vec3f axis, double angle, boolean degrees) {
        return this.setRotation(axis, (degrees ? (angle * Maths.toRad) : angle));
    }

    public Quaternion setRotation(double yaw, double pitch, double roll) {
        final double hy = 0.5 * yaw;
        final double hp = 0.5 * pitch;
        final double hr = 0.5 * roll;

        final float chr = Mathc.cos(hr);
        final float shr = Mathc.sin(hr);
        final float chp = Mathc.cos(hp);
        final float shp = Mathc.sin(hp);
        final float chy = Mathc.cos(hy);
        final float shy = Mathc.sin(hy);

        final float chy_shp = chy * shp;
        final float shy_chp = shy * chp;
        final float chy_chp = chy * chp;
        final float shy_shp = shy * shp;

        x = chy_shp * chr + shy_chp * shr;
        y = shy_chp * chr - chy_shp * shr;
        z = chy_chp * shr - shy_shp * chr;
        w = chy_chp * chr + shy_shp * shr;
        return this;
    }

    public Quaternion setRotation(EulerAngles eulerAngles) {
        return this.setRotation(
            Maths.toRad * eulerAngles.yaw,
            Maths.toRad * eulerAngles.pitch,
            Maths.toRad * eulerAngles.roll
        );
    }


    public int getGimbalPole() {
        final float t = x * y + z * w;
        return (t > 0.499) ? 1 : (t < -0.499 ? -1 : 0);
    }

    public float getRollRad() {
        final int pole = this.getGimbalPole();
        return pole == 0 ? Mathc.atan2(2 * (w * z + y * x), 1 - 2 * (x * x + z * z)) : pole * 2 * Mathc.atan2(y, w);
    }

    public float getRoll() {
        return this.getRollRad() * Maths.toDeg;
    }

    public float getPitchRad() {
        final int pole = this.getGimbalPole();
        return pole == 0 ? Mathc.asin(Maths.clamp(2 * (w * x - z * y), -1, 1)) : pole * Maths.pi * 0.5F;
    }

    public float getPitch() {
        return this.getPitchRad() * Maths.toDeg;
    }

    public float getYawRad() {
        return this.getGimbalPole() == 0 ? Mathc.atan2(2 * (y * w + x * z), 1 - 2 * (y * y + x * x)) : 0;
    }

    public float getYaw() {
        return this.getYawRad() * Maths.toDeg;
    }


    public Matrix4f getMatrix(Matrix4f dst) {
        final float xx = (x * x);
        final float xy = (x * y);
        final float xz = (x * z);
        final float xw = (x * w);
        final float yy = (y * y);
        final float yz = (y * z);
        final float yw = (y * w);
        final float zz = (z * z);
        final float zw = (z * w);

        dst.val[Matrix4f.m00] = 1F - 2F * (yy + zz);
        dst.val[Matrix4f.m01] = 2F * (xy - zw);
        dst.val[Matrix4f.m02] = 2F * (xz + yw);
        dst.val[Matrix4f.m03] = 0F;
        dst.val[Matrix4f.m10] = 2F * (xy + zw);
        dst.val[Matrix4f.m11] = 1F - 2F * (xx + zz);
        dst.val[Matrix4f.m12] = 2F * (yz - xw);
        dst.val[Matrix4f.m13] = 0F;
        dst.val[Matrix4f.m20] = 2F * (xz - yw);
        dst.val[Matrix4f.m21] = 2F * (yz + xw);
        dst.val[Matrix4f.m22] = 1F - 2F * (xx + yy);
        dst.val[Matrix4f.m23] = 0F;
        dst.val[Matrix4f.m30] = 0F;
        dst.val[Matrix4f.m31] = 0F;
        dst.val[Matrix4f.m32] = 0F;
        dst.val[Matrix4f.m33] = 1F;

        return dst;
    }


    public Quaternion copy() {
        return new Quaternion(this);
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z + ", " + w;
    }


    public static Quaternion fromRotation(float x, float y, float z) {
        final Quaternion quaternionernion = new Quaternion();
        quaternionernion.mul(new Quaternion(Mathc.sin(x / 2), 0, 0, Mathc.cos(x / 2)));
        quaternionernion.mul(new Quaternion(0, Mathc.sin(y / 2), 0, Mathc.cos(y / 2)));
        quaternionernion.mul(new Quaternion(0, 0, Mathc.sin(z / 2), Mathc.cos(z / 2)));
        return quaternionernion;
    }

}
