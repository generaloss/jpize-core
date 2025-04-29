package jpize.lwjgl.opengl;

import jpize.opengl.gl.GL11I;
import org.lwjgl.opengl.GL11;
import java.nio.*;

public class LwjglGL11 implements GL11I {

    @Override
    public void glEnable(int target) {
        GL11.glEnable(target);
    }

    @Override
    public void glDisable(int target) {
        GL11.glDisable(target);
    }

    @Override
    public void glAccum(int var0, float var1) {
        GL11.glAccum(var0, var1);
    }

    @Override
    public void glAlphaFunc(int var0, float var1) {
        GL11.glAlphaFunc(var0, var1);
    }

    @Override
    public boolean glAreTexturesResident(IntBuffer textures, ByteBuffer residences) {
        return GL11.glAreTexturesResident(textures, residences);
    }

    @Override
    public boolean glAreTexturesResident(int texture, ByteBuffer residences) {
        return GL11.glAreTexturesResident(texture, residences);
    }

    @Override
    public void glArrayElement(int var0) {
        GL11.glArrayElement(var0);
    }

    @Override
    public void glBegin(int var0) {
        GL11.glBegin(var0);
    }

    @Override
    public void glBindTexture(int target, int texture) {
        GL11.glBindTexture(target, texture);
    }

    @Override
    public void glBitmap(int w, int h, float xOrig, float yOrig, float xInc, float yInc, ByteBuffer data) {
        GL11.glBitmap(w, h, xOrig, yOrig, xInc, yInc, data);
    }

    @Override
    public void glBitmap(int w, int h, float xOrig, float yOrig, float xInc, float yInc, long data) {
        GL11.glBitmap(w, h, xOrig, yOrig, xInc, yInc, data);
    }

    @Override
    public void glBlendFunc(int sfactor, int dfactor) {
        GL11.glBlendFunc(sfactor, dfactor);
    }

    @Override
    public void glCallList(int var0) {
        GL11.glCallList(var0);
    }

    @Override
    public void glCallLists(int type, ByteBuffer lists) {
        GL11.glCallLists(type, lists);
    }

    @Override
    public void glCallLists(ByteBuffer lists) {
        GL11.glCallLists(lists);
    }

    @Override
    public void glCallLists(ShortBuffer lists) {
        GL11.glCallLists(lists);
    }

    @Override
    public void glCallLists(IntBuffer lists) {
        GL11.glCallLists(lists);
    }

    @Override
    public void glClear(int mask) {
        GL11.glClear(mask);
    }

    @Override
    public void glClearAccum(float var0, float var1, float var2, float var3) {
        GL11.glClearAccum(var0, var1, var2, var3);
    }

    @Override
    public void glClearColor(float red, float green, float blue, float alpha) {
        GL11.glClearColor(red, green, blue, alpha);
    }

    @Override
    public void glClearDepth(double depth) {
        GL11.glClearDepth(depth);
    }

    @Override
    public void glClearIndex(float var0) {
        GL11.glClearIndex(var0);
    }

    @Override
    public void glClearStencil(int s) {
        GL11.glClearStencil(s);
    }

    @Override
    public void glClipPlane(int plane, DoubleBuffer equation) {
        GL11.glClipPlane(plane, equation);
    }

    @Override
    public void glColor3b(byte var0, byte var1, byte var2) {
        GL11.glColor3b(var0, var1, var2);
    }

    @Override
    public void glColor3s(short var0, short var1, short var2) {
        GL11.glColor3s(var0, var1, var2);
    }

    @Override
    public void glColor3i(int var0, int var1, int var2) {
        GL11.glColor3i(var0, var1, var2);
    }

    @Override
    public void glColor3f(float var0, float var1, float var2) {
        GL11.glColor3f(var0, var1, var2);
    }

    @Override
    public void glColor3d(double var0, double var2, double var4) {
        GL11.glColor3d(var0, var2, var4);
    }

    @Override
    public void glColor3ub(byte var0, byte var1, byte var2) {
        GL11.glColor3ub(var0, var1, var2);
    }

    @Override
    public void glColor3us(short var0, short var1, short var2) {
        GL11.glColor3us(var0, var1, var2);
    }

    @Override
    public void glColor3ui(int var0, int var1, int var2) {
        GL11.glColor3ui(var0, var1, var2);
    }

    @Override
    public void glColor3bv(ByteBuffer v) {
        GL11.glColor3bv(v);
    }

    @Override
    public void glColor3sv(ShortBuffer v) {
        GL11.glColor3sv(v);
    }

    @Override
    public void glColor3iv(IntBuffer v) {
        GL11.glColor3iv(v);
    }

    @Override
    public void glColor3fv(FloatBuffer v) {
        GL11.glColor3fv(v);
    }

    @Override
    public void glColor3dv(DoubleBuffer v) {
        GL11.glColor3dv(v);
    }

    @Override
    public void glColor3ubv(ByteBuffer v) {
        GL11.glColor3ubv(v);
    }

    @Override
    public void glColor3usv(ShortBuffer v) {
        GL11.glColor3usv(v);
    }

    @Override
    public void glColor3uiv(IntBuffer v) {
        GL11.glColor3uiv(v);
    }

    @Override
    public void glColor4b(byte var0, byte var1, byte var2, byte var3) {
        GL11.glColor4b(var0, var1, var2, var3);
    }

    @Override
    public void glColor4s(short var0, short var1, short var2, short var3) {
        GL11.glColor4s(var0, var1, var2, var3);
    }

    @Override
    public void glColor4i(int var0, int var1, int var2, int var3) {
        GL11.glColor4i(var0, var1, var2, var3);
    }

    @Override
    public void glColor4f(float var0, float var1, float var2, float var3) {
        GL11.glColor4f(var0, var1, var2, var3);
    }

    @Override
    public void glColor4d(double var0, double var2, double var4, double var6) {
        GL11.glColor4d(var0, var2, var4, var6);
    }

    @Override
    public void glColor4ub(byte var0, byte var1, byte var2, byte var3) {
        GL11.glColor4ub(var0, var1, var2, var3);
    }

    @Override
    public void glColor4us(short var0, short var1, short var2, short var3) {
        GL11.glColor4us(var0, var1, var2, var3);
    }

    @Override
    public void glColor4ui(int var0, int var1, int var2, int var3) {
        GL11.glColor4ui(var0, var1, var2, var3);
    }

    @Override
    public void glColor4bv(ByteBuffer v) {
        GL11.glColor4bv(v);
    }

    @Override
    public void glColor4sv(ShortBuffer v) {
        GL11.glColor4sv(v);
    }

    @Override
    public void glColor4iv(IntBuffer v) {
        GL11.glColor4iv(v);
    }

    @Override
    public void glColor4fv(FloatBuffer v) {
        GL11.glColor4fv(v);
    }

    @Override
    public void glColor4dv(DoubleBuffer v) {
        GL11.glColor4dv(v);
    }

    @Override
    public void glColor4ubv(ByteBuffer v) {
        GL11.glColor4ubv(v);
    }

    @Override
    public void glColor4usv(ShortBuffer v) {
        GL11.glColor4usv(v);
    }

    @Override
    public void glColor4uiv(IntBuffer v) {
        GL11.glColor4uiv(v);
    }

    @Override
    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        GL11.glColorMask(red, green, blue, alpha);
    }

    @Override
    public void glColorMaterial(int var0, int var1) {
        GL11.glColorMaterial(var0, var1);
    }

    @Override
    public void glColorPointer(int size, int type, int stride, ByteBuffer pointer) {
        GL11.glColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glColorPointer(int size, int type, int stride, long pointer) {
        GL11.glColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glColorPointer(int size, int type, int stride, ShortBuffer pointer) {
        GL11.glColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glColorPointer(int size, int type, int stride, IntBuffer pointer) {
        GL11.glColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glColorPointer(int size, int type, int stride, FloatBuffer pointer) {
        GL11.glColorPointer(size, type, stride, pointer);
    }

    @Override
    public void glCopyPixels(int var0, int var1, int var2, int var3, int var4) {
        GL11.glCopyPixels(var0, var1, var2, var3, var4);
    }

    @Override
    public void glCullFace(int mode) {
        GL11.glCullFace(mode);
    }

    @Override
    public void glDeleteLists(int var0, int var1) {
        GL11.glDeleteLists(var0, var1);
    }

    @Override
    public void glDepthFunc(int func) {
        GL11.glDepthFunc(func);
    }

    @Override
    public void glDepthMask(boolean flag) {
        GL11.glDepthMask(flag);
    }

    @Override
    public void glDepthRange(double zNear, double zFar) {
        GL11.glDepthRange(zNear, zFar);
    }

    @Override
    public void glDisableClientState(int var0) {
        GL11.glDisableClientState(var0);
    }

    @Override
    public void glDrawArrays(int mode, int first, int count) {
        GL11.glDrawArrays(mode, first, count);
    }

    @Override
    public void glDrawBuffer(int buf) {
        GL11.glDrawBuffer(buf);
    }

    @Override
    public void glDrawElements(int mode, int count, int type, long indices) {
        GL11.glDrawElements(mode, count, type, indices);
    }

    @Override
    public void glDrawElements(int mode, int type, ByteBuffer indices) {
        GL11.glDrawElements(mode, type, indices);
    }

    @Override
    public void glDrawElements(int mode, ByteBuffer indices) {
        GL11.glDrawElements(mode, indices);
    }

    @Override
    public void glDrawElements(int mode, ShortBuffer indices) {
        GL11.glDrawElements(mode, indices);
    }

    @Override
    public void glDrawElements(int mode, IntBuffer indices) {
        GL11.glDrawElements(mode, indices);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, ByteBuffer pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, long pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, ShortBuffer pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, IntBuffer pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, FloatBuffer pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glEdgeFlag(boolean var0) {
        GL11.glEdgeFlag(var0);
    }

    @Override
    public void glEdgeFlagv(ByteBuffer flag) {
        GL11.glEdgeFlagv(flag);
    }

    @Override
    public void glEdgeFlagPointer(int stride, ByteBuffer pointer) {
        GL11.glEdgeFlagPointer(stride, pointer);
    }

    @Override
    public void glEdgeFlagPointer(int stride, long pointer) {
        GL11.glEdgeFlagPointer(stride, pointer);
    }

    @Override
    public void glEnableClientState(int var0) {
        GL11.glEnableClientState(var0);
    }

    @Override
    public void glEnd() {
        GL11.glEnd();
    }

    @Override
    public void glEvalCoord1f(float var0) {
        GL11.glEvalCoord1f(var0);
    }

    @Override
    public void glEvalCoord1fv(FloatBuffer u) {
        GL11.glEvalCoord1fv(u);
    }

    @Override
    public void glEvalCoord1d(double var0) {
        GL11.glEvalCoord1d(var0);
    }

    @Override
    public void glEvalCoord1dv(DoubleBuffer u) {
        GL11.glEvalCoord1dv(u);
    }

    @Override
    public void glEvalCoord2f(float var0, float var1) {
        GL11.glEvalCoord2f(var0, var1);
    }

    @Override
    public void glEvalCoord2fv(FloatBuffer u) {
        GL11.glEvalCoord2fv(u);
    }

    @Override
    public void glEvalCoord2d(double var0, double var2) {
        GL11.glEvalCoord2d(var0, var2);
    }

    @Override
    public void glEvalCoord2dv(DoubleBuffer u) {
        GL11.glEvalCoord2dv(u);
    }

    @Override
    public void glEvalMesh1(int var0, int var1, int var2) {
        GL11.glEvalMesh1(var0, var1, var2);
    }

    @Override
    public void glEvalMesh2(int var0, int var1, int var2, int var3, int var4) {
        GL11.glEvalMesh2(var0, var1, var2, var3, var4);
    }

    @Override
    public void glEvalPoint1(int var0) {
        GL11.glEvalPoint1(var0);
    }

    @Override
    public void glEvalPoint2(int var0, int var1) {
        GL11.glEvalPoint2(var0, var1);
    }

    @Override
    public void glFeedbackBuffer(int type, FloatBuffer buffer) {
        GL11.glFeedbackBuffer(type, buffer);
    }

    @Override
    public void glFinish() {
        GL11.glFinish();
    }

    @Override
    public void glFlush() {
        GL11.glFlush();
    }

    @Override
    public void glFogi(int var0, int var1) {
        GL11.glFogi(var0, var1);
    }

    @Override
    public void glFogiv(int pname, IntBuffer params) {
        GL11.glFogiv(pname, params);
    }

    @Override
    public void glFogf(int var0, float var1) {
        GL11.glFogf(var0, var1);
    }

    @Override
    public void glFogfv(int pname, FloatBuffer params) {
        GL11.glFogfv(pname, params);
    }

    @Override
    public void glFrontFace(int dir) {
        GL11.glFrontFace(dir);
    }

    @Override
    public int glGenLists(int var0) {
        return GL11.glGenLists(var0);
    }

    @Override
    public void glGenTextures(IntBuffer textures) {
        GL11.glGenTextures(textures);
    }

    @Override
    public int glGenTextures() {
        return GL11.glGenTextures();
    }

    @Override
    public void glDeleteTextures(IntBuffer textures) {
        GL11.glDeleteTextures(textures);
    }

    @Override
    public void glDeleteTextures(int texture) {
        GL11.glDeleteTextures(texture);
    }

    @Override
    public void glGetClipPlane(int plane, DoubleBuffer equation) {
        GL11.glGetClipPlane(plane, equation);
    }

    @Override
    public void glGetBooleanv(int pname, ByteBuffer params) {
        GL11.glGetBooleanv(pname, params);
    }

    @Override
    public boolean glGetBoolean(int pname) {
        return GL11.glGetBoolean(pname);
    }

    @Override
    public void glGetFloatv(int pname, FloatBuffer params) {
        GL11.glGetFloatv(pname, params);
    }

    @Override
    public float glGetFloat(int pname) {
        return GL11.glGetFloat(pname);
    }

    @Override
    public void glGetIntegerv(int pname, IntBuffer params) {
        GL11.glGetIntegerv(pname, params);
    }

    @Override
    public int glGetInteger(int pname) {
        return GL11.glGetInteger(pname);
    }

    @Override
    public void glGetDoublev(int pname, DoubleBuffer params) {
        GL11.glGetDoublev(pname, params);
    }

    @Override
    public double glGetDouble(int pname) {
        return GL11.glGetDouble(pname);
    }

    @Override
    public int glGetError() {
        return GL11.glGetError();
    }

    @Override
    public void glGetLightiv(int light, int pname, IntBuffer data) {
        GL11.glGetLightiv(light, pname, data);
    }

    @Override
    public int glGetLighti(int light, int pname) {
        return GL11.glGetLighti(light, pname);
    }

    @Override
    public void glGetLightfv(int light, int pname, FloatBuffer data) {
        GL11.glGetLightfv(light, pname, data);
    }

    @Override
    public float glGetLightf(int light, int pname) {
        return GL11.glGetLightf(light, pname);
    }

    @Override
    public void glGetMapiv(int target, int query, IntBuffer data) {
        GL11.glGetMapiv(target, query, data);
    }

    @Override
    public int glGetMapi(int target, int query) {
        return GL11.glGetMapi(target, query);
    }

    @Override
    public void glGetMapfv(int target, int query, FloatBuffer data) {
        GL11.glGetMapfv(target, query, data);
    }

    @Override
    public float glGetMapf(int target, int query) {
        return GL11.glGetMapf(target, query);
    }

    @Override
    public void glGetMapdv(int target, int query, DoubleBuffer data) {
        GL11.glGetMapdv(target, query, data);
    }

    @Override
    public double glGetMapd(int target, int query) {
        return GL11.glGetMapd(target, query);
    }

    @Override
    public void glGetMaterialiv(int face, int pname, IntBuffer data) {
        GL11.glGetMaterialiv(face, pname, data);
    }

    @Override
    public void glGetMaterialfv(int face, int pname, FloatBuffer data) {
        GL11.glGetMaterialfv(face, pname, data);
    }

    @Override
    public void glGetPixelMapfv(int map, FloatBuffer data) {
        GL11.glGetPixelMapfv(map, data);
    }

    @Override
    public void glGetPixelMapfv(int map, long data) {
        GL11.glGetPixelMapfv(map, data);
    }

    @Override
    public void glGetPixelMapusv(int map, ShortBuffer data) {
        GL11.glGetPixelMapusv(map, data);
    }

    @Override
    public void glGetPixelMapusv(int map, long data) {
        GL11.glGetPixelMapusv(map, data);
    }

    @Override
    public void glGetPixelMapuiv(int map, IntBuffer data) {
        GL11.glGetPixelMapuiv(map, data);
    }

    @Override
    public void glGetPixelMapuiv(int map, long data) {
        GL11.glGetPixelMapuiv(map, data);
    }

    @Override
    public long glGetPointer(int pname) {
        return GL11.glGetPointer(pname);
    }

    @Override
    public void glGetPolygonStipple(ByteBuffer pattern) {
        GL11.glGetPolygonStipple(pattern);
    }

    @Override
    public void glGetPolygonStipple(long pattern) {
        GL11.glGetPolygonStipple(pattern);
    }

    @Override
    public String glGetString(int name) {
        return GL11.glGetString(name);
    }

    @Override
    public void glGetTexEnviv(int env, int pname, IntBuffer data) {
        GL11.glGetTexEnviv(env, pname, data);
    }

    @Override
    public int glGetTexEnvi(int env, int pname) {
        return GL11.glGetTexEnvi(env, pname);
    }

    @Override
    public void glGetTexEnvfv(int env, int pname, FloatBuffer data) {
        GL11.glGetTexEnvfv(env, pname, data);
    }

    @Override
    public float glGetTexEnvf(int env, int pname) {
        return GL11.glGetTexEnvf(env, pname);
    }

    @Override
    public void glGetTexGeniv(int coord, int pname, IntBuffer data) {
        GL11.glGetTexGeniv(coord, pname, data);
    }

    @Override
    public int glGetTexGeni(int coord, int pname) {
        return GL11.glGetTexGeni(coord, pname);
    }

    @Override
    public void glGetTexGenfv(int coord, int pname, FloatBuffer data) {
        GL11.glGetTexGenfv(coord, pname, data);
    }

    @Override
    public float glGetTexGenf(int coord, int pname) {
        return GL11.glGetTexGenf(coord, pname);
    }

    @Override
    public void glGetTexGendv(int coord, int pname, DoubleBuffer data) {
        GL11.glGetTexGendv(coord, pname, data);
    }

    @Override
    public double glGetTexGend(int coord, int pname) {
        return GL11.glGetTexGend(coord, pname);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, ByteBuffer pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, long pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, ShortBuffer pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, IntBuffer pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, FloatBuffer pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, DoubleBuffer pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexLevelParameteriv(int target, int level, int pname, IntBuffer params) {
        GL11.glGetTexLevelParameteriv(target, level, pname, params);
    }

    @Override
    public int glGetTexLevelParameteri(int target, int level, int pname) {
        return GL11.glGetTexLevelParameteri(target, level, pname);
    }

    @Override
    public void glGetTexLevelParameterfv(int target, int level, int pname, FloatBuffer params) {
        GL11.glGetTexLevelParameterfv(target, level, pname, params);
    }

    @Override
    public float glGetTexLevelParameterf(int target, int level, int pname) {
        return GL11.glGetTexLevelParameterf(target, level, pname);
    }

    @Override
    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        GL11.glGetTexParameteriv(target, pname, params);
    }

    @Override
    public int glGetTexParameteri(int target, int pname) {
        return GL11.glGetTexParameteri(target, pname);
    }

    @Override
    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        GL11.glGetTexParameterfv(target, pname, params);
    }

    @Override
    public float glGetTexParameterf(int target, int pname) {
        return GL11.glGetTexParameterf(target, pname);
    }

    @Override
    public void glHint(int target, int hint) {
        GL11.glHint(target, hint);
    }

    @Override
    public void glIndexi(int var0) {
        GL11.glIndexi(var0);
    }

    @Override
    public void glIndexub(byte var0) {
        GL11.glIndexub(var0);
    }

    @Override
    public void glIndexs(short var0) {
        GL11.glIndexs(var0);
    }

    @Override
    public void glIndexf(float var0) {
        GL11.glIndexf(var0);
    }

    @Override
    public void glIndexd(double var0) {
        GL11.glIndexd(var0);
    }

    @Override
    public void glIndexiv(IntBuffer index) {
        GL11.glIndexiv(index);
    }

    @Override
    public void glIndexubv(ByteBuffer index) {
        GL11.glIndexubv(index);
    }

    @Override
    public void glIndexsv(ShortBuffer index) {
        GL11.glIndexsv(index);
    }

    @Override
    public void glIndexfv(FloatBuffer index) {
        GL11.glIndexfv(index);
    }

    @Override
    public void glIndexdv(DoubleBuffer index) {
        GL11.glIndexdv(index);
    }

    @Override
    public void glIndexMask(int var0) {
        GL11.glIndexMask(var0);
    }

    @Override
    public void glIndexPointer(int type, int stride, ByteBuffer pointer) {
        GL11.glIndexPointer(type, stride, pointer);
    }

    @Override
    public void glIndexPointer(int type, int stride, long pointer) {
        GL11.glIndexPointer(type, stride, pointer);
    }

    @Override
    public void glIndexPointer(int stride, ByteBuffer pointer) {
        GL11.glIndexPointer(stride, pointer);
    }

    @Override
    public void glIndexPointer(int stride, ShortBuffer pointer) {
        GL11.glIndexPointer(stride, pointer);
    }

    @Override
    public void glIndexPointer(int stride, IntBuffer pointer) {
        GL11.glIndexPointer(stride, pointer);
    }

    @Override
    public void glInitNames() {
        GL11.glInitNames();
    }

    @Override
    public void glInterleavedArrays(int format, int stride, ByteBuffer pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, long pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, ShortBuffer pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, IntBuffer pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, FloatBuffer pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, DoubleBuffer pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public boolean glIsEnabled(int cap) {
        return GL11.glIsEnabled(cap);
    }

    @Override
    public boolean glIsList(int var0) {
        return GL11.glIsList(var0);
    }

    @Override
    public boolean glIsTexture(int texture) {
        return GL11.glIsTexture(texture);
    }

    @Override
    public void glLightModeli(int var0, int var1) {
        GL11.glLightModeli(var0, var1);
    }

    @Override
    public void glLightModelf(int var0, float var1) {
        GL11.glLightModelf(var0, var1);
    }

    @Override
    public void glLightModeliv(int pname, IntBuffer params) {
        GL11.glLightModeliv(pname, params);
    }

    @Override
    public void glLightModelfv(int pname, FloatBuffer params) {
        GL11.glLightModelfv(pname, params);
    }

    @Override
    public void glLighti(int var0, int var1, int var2) {
        GL11.glLighti(var0, var1, var2);
    }

    @Override
    public void glLightf(int var0, int var1, float var2) {
        GL11.glLightf(var0, var1, var2);
    }

    @Override
    public void glLightiv(int light, int pname, IntBuffer params) {
        GL11.glLightiv(light, pname, params);
    }

    @Override
    public void glLightfv(int light, int pname, FloatBuffer params) {
        GL11.glLightfv(light, pname, params);
    }

    @Override
    public void glLineStipple(int var0, short var1) {
        GL11.glLineStipple(var0, var1);
    }

    @Override
    public void glLineWidth(float width) {
        GL11.glLineWidth(width);
    }

    @Override
    public void glListBase(int var0) {
        GL11.glListBase(var0);
    }

    @Override
    public void glLoadMatrixf(FloatBuffer m) {
        GL11.glLoadMatrixf(m);
    }

    @Override
    public void glLoadMatrixd(DoubleBuffer m) {
        GL11.glLoadMatrixd(m);
    }

    @Override
    public void glLoadIdentity() {
        GL11.glLoadIdentity();
    }

    @Override
    public void glLoadName(int var0) {
        GL11.glLoadName(var0);
    }

    @Override
    public void glLogicOp(int op) {
        GL11.glLogicOp(op);
    }

    @Override
    public void glMap1f(int target, float u1, float u2, int stride, int order, FloatBuffer points) {
        GL11.glMap1f(target, u1, u2, stride, order, points);
    }

    @Override
    public void glMap1d(int target, double u1, double u2, int stride, int order, DoubleBuffer points) {
        GL11.glMap1d(target, u1, u2, stride, order, points);
    }

    @Override
    public void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, FloatBuffer points) {
        GL11.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    @Override
    public void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, DoubleBuffer points) {
        GL11.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    @Override
    public void glMapGrid1f(int var0, float var1, float var2) {
        GL11.glMapGrid1f(var0, var1, var2);
    }

    @Override
    public void glMapGrid1d(int var0, double var1, double var3) {
        GL11.glMapGrid1d(var0, var1, var3);
    }

    @Override
    public void glMapGrid2f(int var0, float var1, float var2, int var3, float var4, float var5) {
        GL11.glMapGrid2f(var0, var1, var2, var3, var4, var5);
    }

    @Override
    public void glMapGrid2d(int var0, double var1, double var3, int var5, double var6, double var8) {
        GL11.glMapGrid2d(var0, var1, var3, var5, var6, var8);
    }

    @Override
    public void glMateriali(int var0, int var1, int var2) {
        GL11.glMateriali(var0, var1, var2);
    }

    @Override
    public void glMaterialf(int var0, int var1, float var2) {
        GL11.glMaterialf(var0, var1, var2);
    }

    @Override
    public void glMaterialiv(int face, int pname, IntBuffer params) {
        GL11.glMaterialiv(face, pname, params);
    }

    @Override
    public void glMaterialfv(int face, int pname, FloatBuffer params) {
        GL11.glMaterialfv(face, pname, params);
    }

    @Override
    public void glMatrixMode(int var0) {
        GL11.glMatrixMode(var0);
    }

    @Override
    public void glMultMatrixf(FloatBuffer m) {
        GL11.glMultMatrixf(m);
    }

    @Override
    public void glMultMatrixd(DoubleBuffer m) {
        GL11.glMultMatrixd(m);
    }

    @Override
    public void glFrustum(double var0, double var2, double var4, double var6, double var8, double var10) {
        GL11.glFrustum(var0, var2, var4, var6, var8, var10);
    }

    @Override
    public void glNewList(int var0, int var1) {
        GL11.glNewList(var0, var1);
    }

    @Override
    public void glEndList() {
        GL11.glEndList();
    }

    @Override
    public void glNormal3f(float var0, float var1, float var2) {
        GL11.glNormal3f(var0, var1, var2);
    }

    @Override
    public void glNormal3b(byte var0, byte var1, byte var2) {
        GL11.glNormal3b(var0, var1, var2);
    }

    @Override
    public void glNormal3s(short var0, short var1, short var2) {
        GL11.glNormal3s(var0, var1, var2);
    }

    @Override
    public void glNormal3i(int var0, int var1, int var2) {
        GL11.glNormal3i(var0, var1, var2);
    }

    @Override
    public void glNormal3d(double var0, double var2, double var4) {
        GL11.glNormal3d(var0, var2, var4);
    }

    @Override
    public void glNormal3fv(FloatBuffer v) {
        GL11.glNormal3fv(v);
    }

    @Override
    public void glNormal3bv(ByteBuffer v) {
        GL11.glNormal3bv(v);
    }

    @Override
    public void glNormal3sv(ShortBuffer v) {
        GL11.glNormal3sv(v);
    }

    @Override
    public void glNormal3iv(IntBuffer v) {
        GL11.glNormal3iv(v);
    }

    @Override
    public void glNormal3dv(DoubleBuffer v) {
        GL11.glNormal3dv(v);
    }

    @Override
    public void glNormalPointer(int type, int stride, ByteBuffer pointer) {
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void glNormalPointer(int type, int stride, long pointer) {
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void glNormalPointer(int type, int stride, ShortBuffer pointer) {
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void glNormalPointer(int type, int stride, IntBuffer pointer) {
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void glNormalPointer(int type, int stride, FloatBuffer pointer) {
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void glOrtho(double var0, double var2, double var4, double var6, double var8, double var10) {
        GL11.glOrtho(var0, var2, var4, var6, var8, var10);
    }

    @Override
    public void glPassThrough(float var0) {
        GL11.glPassThrough(var0);
    }

    @Override
    public void glPixelMapfv(int map, int size, long values) {
        GL11.glPixelMapfv(map, size, values);
    }

    @Override
    public void glPixelMapfv(int map, FloatBuffer values) {
        GL11.glPixelMapfv(map, values);
    }

    @Override
    public void glPixelMapusv(int map, int size, long values) {
        GL11.glPixelMapusv(map, size, values);
    }

    @Override
    public void glPixelMapusv(int map, ShortBuffer values) {
        GL11.glPixelMapusv(map, values);
    }

    @Override
    public void glPixelMapuiv(int map, int size, long values) {
        GL11.glPixelMapuiv(map, size, values);
    }

    @Override
    public void glPixelMapuiv(int map, IntBuffer values) {
        GL11.glPixelMapuiv(map, values);
    }

    @Override
    public void glPixelStorei(int pname, int param) {
        GL11.glPixelStorei(pname, param);
    }

    @Override
    public void glPixelStoref(int pname, float param) {
        GL11.glPixelStoref(pname, param);
    }

    @Override
    public void glPixelTransferi(int var0, int var1) {
        GL11.glPixelTransferi(var0, var1);
    }

    @Override
    public void glPixelTransferf(int var0, float var1) {
        GL11.glPixelTransferf(var0, var1);
    }

    @Override
    public void glPixelZoom(float var0, float var1) {
        GL11.glPixelZoom(var0, var1);
    }

    @Override
    public void glPointSize(float size) {
        GL11.glPointSize(size);
    }

    @Override
    public void glPolygonMode(int face, int mode) {
        GL11.glPolygonMode(face, mode);
    }

    @Override
    public void glPolygonOffset(float factor, float units) {
        GL11.glPolygonOffset(factor, units);
    }

    @Override
    public void glPolygonStipple(ByteBuffer pattern) {
        GL11.glPolygonStipple(pattern);
    }

    @Override
    public void glPolygonStipple(long pattern) {
        GL11.glPolygonStipple(pattern);
    }

    @Override
    public void glPushAttrib(int var0) {
        GL11.glPushAttrib(var0);
    }

    @Override
    public void glPushClientAttrib(int var0) {
        GL11.glPushClientAttrib(var0);
    }

    @Override
    public void glPopAttrib() {
        GL11.glPopAttrib();
    }

    @Override
    public void glPopClientAttrib() {
        GL11.glPopClientAttrib();
    }

    @Override
    public void glPopMatrix() {
        GL11.glPopMatrix();
    }

    @Override
    public void glPopName() {
        GL11.glPopName();
    }

    @Override
    public void glPrioritizeTextures(IntBuffer textures, FloatBuffer priorities) {
        GL11.glPrioritizeTextures(textures, priorities);
    }

    @Override
    public void glPushMatrix() {
        GL11.glPushMatrix();
    }

    @Override
    public void glPushName(int var0) {
        GL11.glPushName(var0);
    }

    @Override
    public void glRasterPos2i(int var0, int var1) {
        GL11.glRasterPos2i(var0, var1);
    }

    @Override
    public void glRasterPos2s(short var0, short var1) {
        GL11.glRasterPos2s(var0, var1);
    }

    @Override
    public void glRasterPos2f(float var0, float var1) {
        GL11.glRasterPos2f(var0, var1);
    }

    @Override
    public void glRasterPos2d(double var0, double var2) {
        GL11.glRasterPos2d(var0, var2);
    }

    @Override
    public void glRasterPos2iv(IntBuffer coords) {
        GL11.glRasterPos2iv(coords);
    }

    @Override
    public void glRasterPos2sv(ShortBuffer coords) {
        GL11.glRasterPos2sv(coords);
    }

    @Override
    public void glRasterPos2fv(FloatBuffer coords) {
        GL11.glRasterPos2fv(coords);
    }

    @Override
    public void glRasterPos2dv(DoubleBuffer coords) {
        GL11.glRasterPos2dv(coords);
    }

    @Override
    public void glRasterPos3i(int var0, int var1, int var2) {
        GL11.glRasterPos3i(var0, var1, var2);
    }

    @Override
    public void glRasterPos3s(short var0, short var1, short var2) {
        GL11.glRasterPos3s(var0, var1, var2);
    }

    @Override
    public void glRasterPos3f(float var0, float var1, float var2) {
        GL11.glRasterPos3f(var0, var1, var2);
    }

    @Override
    public void glRasterPos3d(double var0, double var2, double var4) {
        GL11.glRasterPos3d(var0, var2, var4);
    }

    @Override
    public void glRasterPos3iv(IntBuffer coords) {
        GL11.glRasterPos3iv(coords);
    }

    @Override
    public void glRasterPos3sv(ShortBuffer coords) {
        GL11.glRasterPos3sv(coords);
    }

    @Override
    public void glRasterPos3fv(FloatBuffer coords) {
        GL11.glRasterPos3fv(coords);
    }

    @Override
    public void glRasterPos3dv(DoubleBuffer coords) {
        GL11.glRasterPos3dv(coords);
    }

    @Override
    public void glRasterPos4i(int var0, int var1, int var2, int var3) {
        GL11.glRasterPos4i(var0, var1, var2, var3);
    }

    @Override
    public void glRasterPos4s(short var0, short var1, short var2, short var3) {
        GL11.glRasterPos4s(var0, var1, var2, var3);
    }

    @Override
    public void glRasterPos4f(float var0, float var1, float var2, float var3) {
        GL11.glRasterPos4f(var0, var1, var2, var3);
    }

    @Override
    public void glRasterPos4d(double var0, double var2, double var4, double var6) {
        GL11.glRasterPos4d(var0, var2, var4, var6);
    }

    @Override
    public void glRasterPos4iv(IntBuffer coords) {
        GL11.glRasterPos4iv(coords);
    }

    @Override
    public void glRasterPos4sv(ShortBuffer coords) {
        GL11.glRasterPos4sv(coords);
    }

    @Override
    public void glRasterPos4fv(FloatBuffer coords) {
        GL11.glRasterPos4fv(coords);
    }

    @Override
    public void glRasterPos4dv(DoubleBuffer coords) {
        GL11.glRasterPos4dv(coords);
    }

    @Override
    public void glReadBuffer(int src) {
        GL11.glReadBuffer(src);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, long pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, ShortBuffer pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, IntBuffer pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, FloatBuffer pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glRecti(int var0, int var1, int var2, int var3) {
        GL11.glRecti(var0, var1, var2, var3);
    }

    @Override
    public void glRects(short var0, short var1, short var2, short var3) {
        GL11.glRects(var0, var1, var2, var3);
    }

    @Override
    public void glRectf(float var0, float var1, float var2, float var3) {
        GL11.glRectf(var0, var1, var2, var3);
    }

    @Override
    public void glRectd(double var0, double var2, double var4, double var6) {
        GL11.glRectd(var0, var2, var4, var6);
    }

    @Override
    public void glRectiv(IntBuffer v1, IntBuffer v2) {
        GL11.glRectiv(v1, v2);
    }

    @Override
    public void glRectsv(ShortBuffer v1, ShortBuffer v2) {
        GL11.glRectsv(v1, v2);
    }

    @Override
    public void glRectfv(FloatBuffer v1, FloatBuffer v2) {
        GL11.glRectfv(v1, v2);
    }

    @Override
    public void glRectdv(DoubleBuffer v1, DoubleBuffer v2) {
        GL11.glRectdv(v1, v2);
    }

    @Override
    public int glRenderMode(int var0) {
        return GL11.glRenderMode(var0);
    }

    @Override
    public void glRotatef(float var0, float var1, float var2, float var3) {
        GL11.glRotatef(var0, var1, var2, var3);
    }

    @Override
    public void glRotated(double var0, double var2, double var4, double var6) {
        GL11.glRotated(var0, var2, var4, var6);
    }

    @Override
    public void glScalef(float var0, float var1, float var2) {
        GL11.glScalef(var0, var1, var2);
    }

    @Override
    public void glScaled(double var0, double var2, double var4) {
        GL11.glScaled(var0, var2, var4);
    }

    @Override
    public void glScissor(int x, int y, int width, int height) {
        GL11.glScissor(x, y, width, height);
    }

    @Override
    public void glSelectBuffer(IntBuffer buffer) {
        GL11.glSelectBuffer(buffer);
    }

    @Override
    public void glShadeModel(int var0) {
        GL11.glShadeModel(var0);
    }

    @Override
    public void glStencilFunc(int func, int ref, int mask) {
        GL11.glStencilFunc(func, ref, mask);
    }

    @Override
    public void glStencilMask(int mask) {
        GL11.glStencilMask(mask);
    }

    @Override
    public void glStencilOp(int sfail, int dpfail, int dppass) {
        GL11.glStencilOp(sfail, dpfail, dppass);
    }

    @Override
    public void glTexCoord1f(float var0) {
        GL11.glTexCoord1f(var0);
    }

    @Override
    public void glTexCoord1s(short var0) {
        GL11.glTexCoord1s(var0);
    }

    @Override
    public void glTexCoord1i(int var0) {
        GL11.glTexCoord1i(var0);
    }

    @Override
    public void glTexCoord1d(double var0) {
        GL11.glTexCoord1d(var0);
    }

    @Override
    public void glTexCoord1fv(FloatBuffer v) {
        GL11.glTexCoord1fv(v);
    }

    @Override
    public void glTexCoord1sv(ShortBuffer v) {
        GL11.glTexCoord1sv(v);
    }

    @Override
    public void glTexCoord1iv(IntBuffer v) {
        GL11.glTexCoord1iv(v);
    }

    @Override
    public void glTexCoord1dv(DoubleBuffer v) {
        GL11.glTexCoord1dv(v);
    }

    @Override
    public void glTexCoord2f(float var0, float var1) {
        GL11.glTexCoord2f(var0, var1);
    }

    @Override
    public void glTexCoord2s(short var0, short var1) {
        GL11.glTexCoord2s(var0, var1);
    }

    @Override
    public void glTexCoord2i(int var0, int var1) {
        GL11.glTexCoord2i(var0, var1);
    }

    @Override
    public void glTexCoord2d(double var0, double var2) {
        GL11.glTexCoord2d(var0, var2);
    }

    @Override
    public void glTexCoord2fv(FloatBuffer v) {
        GL11.glTexCoord2fv(v);
    }

    @Override
    public void glTexCoord2sv(ShortBuffer v) {
        GL11.glTexCoord2sv(v);
    }

    @Override
    public void glTexCoord2iv(IntBuffer v) {
        GL11.glTexCoord2iv(v);
    }

    @Override
    public void glTexCoord2dv(DoubleBuffer v) {
        GL11.glTexCoord2dv(v);
    }

    @Override
    public void glTexCoord3f(float var0, float var1, float var2) {
        GL11.glTexCoord3f(var0, var1, var2);
    }

    @Override
    public void glTexCoord3s(short var0, short var1, short var2) {
        GL11.glTexCoord3s(var0, var1, var2);
    }

    @Override
    public void glTexCoord3i(int var0, int var1, int var2) {
        GL11.glTexCoord3i(var0, var1, var2);
    }

    @Override
    public void glTexCoord3d(double var0, double var2, double var4) {
        GL11.glTexCoord3d(var0, var2, var4);
    }

    @Override
    public void glTexCoord3fv(FloatBuffer v) {
        GL11.glTexCoord3fv(v);
    }

    @Override
    public void glTexCoord3sv(ShortBuffer v) {
        GL11.glTexCoord3sv(v);
    }

    @Override
    public void glTexCoord3iv(IntBuffer v) {
        GL11.glTexCoord3iv(v);
    }

    @Override
    public void glTexCoord3dv(DoubleBuffer v) {
        GL11.glTexCoord3dv(v);
    }

    @Override
    public void glTexCoord4f(float var0, float var1, float var2, float var3) {
        GL11.glTexCoord4f(var0, var1, var2, var3);
    }

    @Override
    public void glTexCoord4s(short var0, short var1, short var2, short var3) {
        GL11.glTexCoord4s(var0, var1, var2, var3);
    }

    @Override
    public void glTexCoord4i(int var0, int var1, int var2, int var3) {
        GL11.glTexCoord4i(var0, var1, var2, var3);
    }

    @Override
    public void glTexCoord4d(double var0, double var2, double var4, double var6) {
        GL11.glTexCoord4d(var0, var2, var4, var6);
    }

    @Override
    public void glTexCoord4fv(FloatBuffer v) {
        GL11.glTexCoord4fv(v);
    }

    @Override
    public void glTexCoord4sv(ShortBuffer v) {
        GL11.glTexCoord4sv(v);
    }

    @Override
    public void glTexCoord4iv(IntBuffer v) {
        GL11.glTexCoord4iv(v);
    }

    @Override
    public void glTexCoord4dv(DoubleBuffer v) {
        GL11.glTexCoord4dv(v);
    }

    @Override
    public void glTexCoordPointer(int size, int type, int stride, ByteBuffer pointer) {
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void glTexCoordPointer(int size, int type, int stride, long pointer) {
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void glTexCoordPointer(int size, int type, int stride, ShortBuffer pointer) {
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void glTexCoordPointer(int size, int type, int stride, IntBuffer pointer) {
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void glTexCoordPointer(int size, int type, int stride, FloatBuffer pointer) {
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void glTexEnvi(int var0, int var1, int var2) {
        GL11.glTexEnvi(var0, var1, var2);
    }

    @Override
    public void glTexEnviv(int target, int pname, IntBuffer params) {
        GL11.glTexEnviv(target, pname, params);
    }

    @Override
    public void glTexEnvf(int var0, int var1, float var2) {
        GL11.glTexEnvf(var0, var1, var2);
    }

    @Override
    public void glTexEnvfv(int target, int pname, FloatBuffer params) {
        GL11.glTexEnvfv(target, pname, params);
    }

    @Override
    public void glTexGeni(int var0, int var1, int var2) {
        GL11.glTexGeni(var0, var1, var2);
    }

    @Override
    public void glTexGeniv(int coord, int pname, IntBuffer params) {
        GL11.glTexGeniv(coord, pname, params);
    }

    @Override
    public void glTexGenf(int var0, int var1, float var2) {
        GL11.glTexGenf(var0, var1, var2);
    }

    @Override
    public void glTexGenfv(int coord, int pname, FloatBuffer params) {
        GL11.glTexGenfv(coord, pname, params);
    }

    @Override
    public void glTexGend(int var0, int var1, double var2) {
        GL11.glTexGend(var0, var1, var2);
    }

    @Override
    public void glTexGendv(int coord, int pname, DoubleBuffer params) {
        GL11.glTexGendv(coord, pname, params);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ByteBuffer pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, long pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, ShortBuffer pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, IntBuffer pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, FloatBuffer pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, DoubleBuffer pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, long pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, ShortBuffer pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, IntBuffer pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, FloatBuffer pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, DoubleBuffer pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glCopyTexImage1D(int target, int level, int internalFormat, int x, int y, int width, int border) {
        GL11.glCopyTexImage1D(target, level, internalFormat, x, y, width, border);
    }

    @Override
    public void glCopyTexImage2D(int target, int level, int internalFormat, int x, int y, int width, int height, int border) {
        GL11.glCopyTexImage2D(target, level, internalFormat, x, y, width, height, border);
    }

    @Override
    public void glCopyTexSubImage1D(int target, int level, int xoffset, int x, int y, int width) {
        GL11.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
    }

    @Override
    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    @Override
    public void glTexParameteri(int target, int pname, int param) {
        GL11.glTexParameteri(target, pname, param);
    }

    @Override
    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        GL11.glTexParameteriv(target, pname, params);
    }

    @Override
    public void glTexParameterf(int target, int pname, float param) {
        GL11.glTexParameterf(target, pname, param);
    }

    @Override
    public void glTexParameterfv(int target, int pname, FloatBuffer params) {
        GL11.glTexParameterfv(target, pname, params);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, long pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, long pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ShortBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, IntBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, FloatBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, DoubleBuffer pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTranslatef(float var0, float var1, float var2) {
        GL11.glTranslatef(var0, var1, var2);
    }

    @Override
    public void glTranslated(double var0, double var2, double var4) {
        GL11.glTranslated(var0, var2, var4);
    }

    @Override
    public void glVertex2f(float var0, float var1) {
        GL11.glVertex2f(var0, var1);
    }

    @Override
    public void glVertex2s(short var0, short var1) {
        GL11.glVertex2s(var0, var1);
    }

    @Override
    public void glVertex2i(int var0, int var1) {
        GL11.glVertex2i(var0, var1);
    }

    @Override
    public void glVertex2d(double var0, double var2) {
        GL11.glVertex2d(var0, var2);
    }

    @Override
    public void glVertex2fv(FloatBuffer coords) {
        GL11.glVertex2fv(coords);
    }

    @Override
    public void glVertex2sv(ShortBuffer coords) {
        GL11.glVertex2sv(coords);
    }

    @Override
    public void glVertex2iv(IntBuffer coords) {
        GL11.glVertex2iv(coords);
    }

    @Override
    public void glVertex2dv(DoubleBuffer coords) {
        GL11.glVertex2dv(coords);
    }

    @Override
    public void glVertex3f(float var0, float var1, float var2) {
        GL11.glVertex3f(var0, var1, var2);
    }

    @Override
    public void glVertex3s(short var0, short var1, short var2) {
        GL11.glVertex3s(var0, var1, var2);
    }

    @Override
    public void glVertex3i(int var0, int var1, int var2) {
        GL11.glVertex3i(var0, var1, var2);
    }

    @Override
    public void glVertex3d(double var0, double var2, double var4) {
        GL11.glVertex3d(var0, var2, var4);
    }

    @Override
    public void glVertex3fv(FloatBuffer coords) {
        GL11.glVertex3fv(coords);
    }

    @Override
    public void glVertex3sv(ShortBuffer coords) {
        GL11.glVertex3sv(coords);
    }

    @Override
    public void glVertex3iv(IntBuffer coords) {
        GL11.glVertex3iv(coords);
    }

    @Override
    public void glVertex3dv(DoubleBuffer coords) {
        GL11.glVertex3dv(coords);
    }

    @Override
    public void glVertex4f(float var0, float var1, float var2, float var3) {
        GL11.glVertex4f(var0, var1, var2, var3);
    }

    @Override
    public void glVertex4s(short var0, short var1, short var2, short var3) {
        GL11.glVertex4s(var0, var1, var2, var3);
    }

    @Override
    public void glVertex4i(int var0, int var1, int var2, int var3) {
        GL11.glVertex4i(var0, var1, var2, var3);
    }

    @Override
    public void glVertex4d(double var0, double var2, double var4, double var6) {
        GL11.glVertex4d(var0, var2, var4, var6);
    }

    @Override
    public void glVertex4fv(FloatBuffer coords) {
        GL11.glVertex4fv(coords);
    }

    @Override
    public void glVertex4sv(ShortBuffer coords) {
        GL11.glVertex4sv(coords);
    }

    @Override
    public void glVertex4iv(IntBuffer coords) {
        GL11.glVertex4iv(coords);
    }

    @Override
    public void glVertex4dv(DoubleBuffer coords) {
        GL11.glVertex4dv(coords);
    }

    @Override
    public void glVertexPointer(int size, int type, int stride, ByteBuffer pointer) {
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void glVertexPointer(int size, int type, int stride, long pointer) {
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void glVertexPointer(int size, int type, int stride, ShortBuffer pointer) {
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void glVertexPointer(int size, int type, int stride, IntBuffer pointer) {
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void glVertexPointer(int size, int type, int stride, FloatBuffer pointer) {
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void glViewport(int x, int y, int w, int h) {
        GL11.glViewport(x, y, w, h);
    }

    @Override
    public boolean glAreTexturesResident(int[] textures, ByteBuffer residences) {
        return GL11.glAreTexturesResident(textures, residences);
    }

    @Override
    public void glClipPlane(int plane, double[] equation) {
        GL11.glClipPlane(plane, equation);
    }

    @Override
    public void glColor3sv(short[] v) {
        GL11.glColor3sv(v);
    }

    @Override
    public void glColor3iv(int[] v) {
        GL11.glColor3iv(v);
    }

    @Override
    public void glColor3fv(float[] v) {
        GL11.glColor3fv(v);
    }

    @Override
    public void glColor3dv(double[] v) {
        GL11.glColor3dv(v);
    }

    @Override
    public void glColor3usv(short[] v) {
        GL11.glColor3usv(v);
    }

    @Override
    public void glColor3uiv(int[] v) {
        GL11.glColor3uiv(v);
    }

    @Override
    public void glColor4sv(short[] v) {
        GL11.glColor4sv(v);
    }

    @Override
    public void glColor4iv(int[] v) {
        GL11.glColor4iv(v);
    }

    @Override
    public void glColor4fv(float[] v) {
        GL11.glColor4fv(v);
    }

    @Override
    public void glColor4dv(double[] v) {
        GL11.glColor4dv(v);
    }

    @Override
    public void glColor4usv(short[] v) {
        GL11.glColor4usv(v);
    }

    @Override
    public void glColor4uiv(int[] v) {
        GL11.glColor4uiv(v);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, short[] pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, int[] pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glDrawPixels(int width, int height, int format, int type, float[] pixels) {
        GL11.glDrawPixels(width, height, format, type, pixels);
    }

    @Override
    public void glEvalCoord1fv(float[] u) {
        GL11.glEvalCoord1fv(u);
    }

    @Override
    public void glEvalCoord1dv(double[] u) {
        GL11.glEvalCoord1dv(u);
    }

    @Override
    public void glEvalCoord2fv(float[] u) {
        GL11.glEvalCoord2fv(u);
    }

    @Override
    public void glEvalCoord2dv(double[] u) {
        GL11.glEvalCoord2dv(u);
    }

    @Override
    public void glFeedbackBuffer(int type, float[] buffer) {
        GL11.glFeedbackBuffer(type, buffer);
    }

    @Override
    public void glFogiv(int pname, int[] params) {
        GL11.glFogiv(pname, params);
    }

    @Override
    public void glFogfv(int pname, float[] params) {
        GL11.glFogfv(pname, params);
    }

    @Override
    public void glGenTextures(int[] textures) {
        GL11.glGenTextures(textures);
    }

    @Override
    public void glDeleteTextures(int[] textures) {
        GL11.glDeleteTextures(textures);
    }

    @Override
    public void glGetClipPlane(int plane, double[] equation) {
        GL11.glGetClipPlane(plane, equation);
    }

    @Override
    public void glGetFloatv(int pname, float[] params) {
        GL11.glGetFloatv(pname, params);
    }

    @Override
    public void glGetIntegerv(int pname, int[] params) {
        GL11.glGetIntegerv(pname, params);
    }

    @Override
    public void glGetDoublev(int pname, double[] params) {
        GL11.glGetDoublev(pname, params);
    }

    @Override
    public void glGetLightiv(int light, int pname, int[] data) {
        GL11.glGetLightiv(light, pname, data);
    }

    @Override
    public void glGetLightfv(int light, int pname, float[] data) {
        GL11.glGetLightfv(light, pname, data);
    }

    @Override
    public void glGetMapiv(int target, int query, int[] data) {
        GL11.glGetMapiv(target, query, data);
    }

    @Override
    public void glGetMapfv(int target, int query, float[] data) {
        GL11.glGetMapfv(target, query, data);
    }

    @Override
    public void glGetMapdv(int target, int query, double[] data) {
        GL11.glGetMapdv(target, query, data);
    }

    @Override
    public void glGetMaterialiv(int face, int pname, int[] data) {
        GL11.glGetMaterialiv(face, pname, data);
    }

    @Override
    public void glGetMaterialfv(int face, int pname, float[] data) {
        GL11.glGetMaterialfv(face, pname, data);
    }

    @Override
    public void glGetPixelMapfv(int map, float[] data) {
        GL11.glGetPixelMapfv(map, data);
    }

    @Override
    public void glGetPixelMapusv(int map, short[] data) {
        GL11.glGetPixelMapusv(map, data);
    }

    @Override
    public void glGetPixelMapuiv(int map, int[] data) {
        GL11.glGetPixelMapuiv(map, data);
    }

    @Override
    public void glGetTexEnviv(int env, int pname, int[] data) {
        GL11.glGetTexEnviv(env, pname, data);
    }

    @Override
    public void glGetTexEnvfv(int env, int pname, float[] data) {
        GL11.glGetTexEnvfv(env, pname, data);
    }

    @Override
    public void glGetTexGeniv(int coord, int pname, int[] data) {
        GL11.glGetTexGeniv(coord, pname, data);
    }

    @Override
    public void glGetTexGenfv(int coord, int pname, float[] data) {
        GL11.glGetTexGenfv(coord, pname, data);
    }

    @Override
    public void glGetTexGendv(int coord, int pname, double[] data) {
        GL11.glGetTexGendv(coord, pname, data);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, short[] pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, int[] pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, float[] pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexImage(int tex, int level, int format, int type, double[] pixels) {
        GL11.glGetTexImage(tex, level, format, type, pixels);
    }

    @Override
    public void glGetTexLevelParameteriv(int target, int level, int pname, int[] params) {
        GL11.glGetTexLevelParameteriv(target, level, pname, params);
    }

    @Override
    public void glGetTexLevelParameterfv(int target, int level, int pname, float[] params) {
        GL11.glGetTexLevelParameterfv(target, level, pname, params);
    }

    @Override
    public void glGetTexParameteriv(int target, int pname, int[] params) {
        GL11.glGetTexParameteriv(target, pname, params);
    }

    @Override
    public void glGetTexParameterfv(int target, int pname, float[] params) {
        GL11.glGetTexParameterfv(target, pname, params);
    }

    @Override
    public void glIndexiv(int[] index) {
        GL11.glIndexiv(index);
    }

    @Override
    public void glIndexsv(short[] index) {
        GL11.glIndexsv(index);
    }

    @Override
    public void glIndexfv(float[] index) {
        GL11.glIndexfv(index);
    }

    @Override
    public void glIndexdv(double[] index) {
        GL11.glIndexdv(index);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, short[] pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, int[] pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, float[] pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glInterleavedArrays(int format, int stride, double[] pointer) {
        GL11.glInterleavedArrays(format, stride, pointer);
    }

    @Override
    public void glLightModeliv(int pname, int[] params) {
        GL11.glLightModeliv(pname, params);
    }

    @Override
    public void glLightModelfv(int pname, float[] params) {
        GL11.glLightModelfv(pname, params);
    }

    @Override
    public void glLightiv(int light, int pname, int[] params) {
        GL11.glLightiv(light, pname, params);
    }

    @Override
    public void glLightfv(int light, int pname, float[] params) {
        GL11.glLightfv(light, pname, params);
    }

    @Override
    public void glLoadMatrixf(float[] m) {
        GL11.glLoadMatrixf(m);
    }

    @Override
    public void glLoadMatrixd(double[] m) {
        GL11.glLoadMatrixd(m);
    }

    @Override
    public void glMap1f(int target, float u1, float u2, int stride, int order, float[] points) {
        GL11.glMap1f(target, u1, u2, stride, order, points);
    }

    @Override
    public void glMap1d(int target, double u1, double u2, int stride, int order, double[] points) {
        GL11.glMap1d(target, u1, u2, stride, order, points);
    }

    @Override
    public void glMap2f(int target, float u1, float u2, int ustride, int uorder, float v1, float v2, int vstride, int vorder, float[] points) {
        GL11.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    @Override
    public void glMap2d(int target, double u1, double u2, int ustride, int uorder, double v1, double v2, int vstride, int vorder, double[] points) {
        GL11.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride, vorder, points);
    }

    @Override
    public void glMaterialiv(int face, int pname, int[] params) {
        GL11.glMaterialiv(face, pname, params);
    }

    @Override
    public void glMaterialfv(int face, int pname, float[] params) {
        GL11.glMaterialfv(face, pname, params);
    }

    @Override
    public void glMultMatrixf(float[] m) {
        GL11.glMultMatrixf(m);
    }

    @Override
    public void glMultMatrixd(double[] m) {
        GL11.glMultMatrixd(m);
    }

    @Override
    public void glNormal3fv(float[] v) {
        GL11.glNormal3fv(v);
    }

    @Override
    public void glNormal3sv(short[] v) {
        GL11.glNormal3sv(v);
    }

    @Override
    public void glNormal3iv(int[] v) {
        GL11.glNormal3iv(v);
    }

    @Override
    public void glNormal3dv(double[] v) {
        GL11.glNormal3dv(v);
    }

    @Override
    public void glPixelMapfv(int map, float[] values) {
        GL11.glPixelMapfv(map, values);
    }

    @Override
    public void glPixelMapusv(int map, short[] values) {
        GL11.glPixelMapusv(map, values);
    }

    @Override
    public void glPixelMapuiv(int map, int[] values) {
        GL11.glPixelMapuiv(map, values);
    }

    @Override
    public void glPrioritizeTextures(int[] textures, float[] priorities) {
        GL11.glPrioritizeTextures(textures, priorities);
    }

    @Override
    public void glRasterPos2iv(int[] coords) {
        GL11.glRasterPos2iv(coords);
    }

    @Override
    public void glRasterPos2sv(short[] coords) {
        GL11.glRasterPos2sv(coords);
    }

    @Override
    public void glRasterPos2fv(float[] coords) {
        GL11.glRasterPos2fv(coords);
    }

    @Override
    public void glRasterPos2dv(double[] coords) {
        GL11.glRasterPos2dv(coords);
    }

    @Override
    public void glRasterPos3iv(int[] coords) {
        GL11.glRasterPos3iv(coords);
    }

    @Override
    public void glRasterPos3sv(short[] coords) {
        GL11.glRasterPos3sv(coords);
    }

    @Override
    public void glRasterPos3fv(float[] coords) {
        GL11.glRasterPos3fv(coords);
    }

    @Override
    public void glRasterPos3dv(double[] coords) {
        GL11.glRasterPos3dv(coords);
    }

    @Override
    public void glRasterPos4iv(int[] coords) {
        GL11.glRasterPos4iv(coords);
    }

    @Override
    public void glRasterPos4sv(short[] coords) {
        GL11.glRasterPos4sv(coords);
    }

    @Override
    public void glRasterPos4fv(float[] coords) {
        GL11.glRasterPos4fv(coords);
    }

    @Override
    public void glRasterPos4dv(double[] coords) {
        GL11.glRasterPos4dv(coords);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, short[] pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, int[] pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, float[] pixels) {
        GL11.glReadPixels(x, y, width, height, format, type, pixels);
    }

    @Override
    public void glRectiv(int[] v1, int[] v2) {
        GL11.glRectiv(v1, v2);
    }

    @Override
    public void glRectsv(short[] v1, short[] v2) {
        GL11.glRectsv(v1, v2);
    }

    @Override
    public void glRectfv(float[] v1, float[] v2) {
        GL11.glRectfv(v1, v2);
    }

    @Override
    public void glRectdv(double[] v1, double[] v2) {
        GL11.glRectdv(v1, v2);
    }

    @Override
    public void glSelectBuffer(int[] buffer) {
        GL11.glSelectBuffer(buffer);
    }

    @Override
    public void glTexCoord1fv(float[] v) {
        GL11.glTexCoord1fv(v);
    }

    @Override
    public void glTexCoord1sv(short[] v) {
        GL11.glTexCoord1sv(v);
    }

    @Override
    public void glTexCoord1iv(int[] v) {
        GL11.glTexCoord1iv(v);
    }

    @Override
    public void glTexCoord1dv(double[] v) {
        GL11.glTexCoord1dv(v);
    }

    @Override
    public void glTexCoord2fv(float[] v) {
        GL11.glTexCoord2fv(v);
    }

    @Override
    public void glTexCoord2sv(short[] v) {
        GL11.glTexCoord2sv(v);
    }

    @Override
    public void glTexCoord2iv(int[] v) {
        GL11.glTexCoord2iv(v);
    }

    @Override
    public void glTexCoord2dv(double[] v) {
        GL11.glTexCoord2dv(v);
    }

    @Override
    public void glTexCoord3fv(float[] v) {
        GL11.glTexCoord3fv(v);
    }

    @Override
    public void glTexCoord3sv(short[] v) {
        GL11.glTexCoord3sv(v);
    }

    @Override
    public void glTexCoord3iv(int[] v) {
        GL11.glTexCoord3iv(v);
    }

    @Override
    public void glTexCoord3dv(double[] v) {
        GL11.glTexCoord3dv(v);
    }

    @Override
    public void glTexCoord4fv(float[] v) {
        GL11.glTexCoord4fv(v);
    }

    @Override
    public void glTexCoord4sv(short[] v) {
        GL11.glTexCoord4sv(v);
    }

    @Override
    public void glTexCoord4iv(int[] v) {
        GL11.glTexCoord4iv(v);
    }

    @Override
    public void glTexCoord4dv(double[] v) {
        GL11.glTexCoord4dv(v);
    }

    @Override
    public void glTexEnviv(int target, int pname, int[] params) {
        GL11.glTexEnviv(target, pname, params);
    }

    @Override
    public void glTexEnvfv(int target, int pname, float[] params) {
        GL11.glTexEnvfv(target, pname, params);
    }

    @Override
    public void glTexGeniv(int coord, int pname, int[] params) {
        GL11.glTexGeniv(coord, pname, params);
    }

    @Override
    public void glTexGenfv(int coord, int pname, float[] params) {
        GL11.glTexGenfv(coord, pname, params);
    }

    @Override
    public void glTexGendv(int coord, int pname, double[] params) {
        GL11.glTexGendv(coord, pname, params);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, short[] pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, int[] pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, float[] pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage1D(int target, int level, int internalformat, int width, int border, int format, int type, double[] pixels) {
        GL11.glTexImage1D(target, level, internalformat, width, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, short[] pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, int[] pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, float[] pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, double[] pixels) {
        GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    @Override
    public void glTexParameteriv(int target, int pname, int[] params) {
        GL11.glTexParameteriv(target, pname, params);
    }

    @Override
    public void glTexParameterfv(int target, int pname, float[] params) {
        GL11.glTexParameterfv(target, pname, params);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, short[] pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, int[] pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, float[] pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage1D(int target, int level, int xoffset, int width, int format, int type, double[] pixels) {
        GL11.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, short[] pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, int[] pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, float[] pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, double[] pixels) {
        GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    @Override
    public void glVertex2fv(float[] coords) {
        GL11.glVertex2fv(coords);
    }

    @Override
    public void glVertex2sv(short[] coords) {
        GL11.glVertex2sv(coords);
    }

    @Override
    public void glVertex2iv(int[] coords) {
        GL11.glVertex2iv(coords);
    }

    @Override
    public void glVertex2dv(double[] coords) {
        GL11.glVertex2dv(coords);
    }

    @Override
    public void glVertex3fv(float[] coords) {
        GL11.glVertex3fv(coords);
    }

    @Override
    public void glVertex3sv(short[] coords) {
        GL11.glVertex3sv(coords);
    }

    @Override
    public void glVertex3iv(int[] coords) {
        GL11.glVertex3iv(coords);
    }

    @Override
    public void glVertex3dv(double[] coords) {
        GL11.glVertex3dv(coords);
    }

    @Override
    public void glVertex4fv(float[] coords) {
        GL11.glVertex4fv(coords);
    }

    @Override
    public void glVertex4sv(short[] coords) {
        GL11.glVertex4sv(coords);
    }

    @Override
    public void glVertex4iv(int[] coords) {
        GL11.glVertex4iv(coords);
    }

    @Override
    public void glVertex4dv(double[] coords) {
        GL11.glVertex4dv(coords);
    }

}