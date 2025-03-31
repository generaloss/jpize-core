package jpize.opengl.gl;

import jpize.context.Jpize;
import jpize.opengl.GlError;
import jpize.opengl.glenum.*;
import jpize.opengl.tesselation.GlFace;
import jpize.opengl.type.GlType;
import jpize.opengl.tesselation.GlPolygonMode;
import jpize.opengl.texture.GlBlendFactor;
import jpize.opengl.texture.GlInternalFormat;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Gl {

    public static GlError getError() {
        return GlError.byValue(Jpize.GL11.glGetError());
    }

    public static void checkError(){
        final GlError error = getError();
        if(error != GlError.NO_ERROR)
            System.err.println("[GL] Error: " + error.description);
    }


    public static boolean isEnabled(GlTarget target) {
        return Jpize.GL11.glIsEnabled(target.value);
    }

    public static boolean isEnabled(GlTarget target, int index) {
        return Jpize.GL30.glIsEnabledi(target.value, index);
    }

    public static void enable(GlTarget target) {
        Jpize.GL11.glEnable(target.value);
    }

    public static void enable(GlTarget target, int index) {
        Jpize.GL30.glEnablei(target.value, index);
    }

    public static void enable(GlTarget... targets) {
        for(GlTarget target: targets)
            enable(target);
    }

    public static void disable(GlTarget target) {
        Jpize.GL11.glDisable(target.value);
    }

    public static void disable(GlTarget target, int index) {
        Jpize.GL30.glDisablei(target.value, index);
    }

    public static void disable(GlTarget... targets) {
        for(GlTarget target: targets)
            disable(target);
    }


    public static void clearColor(float r, float g, float b, float a) {
        Jpize.GL11.glClearColor(r, g, b, a);
    }

    public static void clearColor(float r, float g, float b) {
        clearColor(r, g, b, 1F);
    }

    public static void clearColor(float grayscale, float alpha) {
        clearColor(grayscale, grayscale, grayscale, alpha);
    }

    public static void clearColor(float grayscale) {
        clearColor(grayscale, 1F);
    }

    public static void clearColor(double r, double g, double b, double a) {
        Jpize.GL11.glClearColor((float) r, (float) g, (float) b, (float) a);
    }

    public static void clearColor(double r, double g, double b) {
        clearColor(r, g, b, 1D);
    }

    public static void clearColor(double grayscale, double alpha) {
        clearColor(grayscale, grayscale, grayscale, alpha);
    }

    public static void clearColor(double grayscale) {
        clearColor(grayscale, 1D);
    }

    public static void clearColor(AbstractColor color) {
        clearColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static void clearColorRGB(int color) {
        // TODO: clearColor();
    }

    public static void clearColorRGBA(int color) {
        // TODO: clearColor();
    }


    public static void clearColorBuffer() {
        Jpize.GL11.glClear(GLI11.GL_COLOR_BUFFER_BIT);
    }

    public static void clearDepthBuffer() {
        Jpize.GL11.glClear(GLI11.GL_DEPTH_BUFFER_BIT);
    }

    public static void clearStencilBuffer() {
        Jpize.GL11.glClear(GLI11.GL_STENCIL_BUFFER_BIT);
    }

    public static void clearColorDepthBuffers() {
        Jpize.GL11.glClear(GLI11.GL_COLOR_BUFFER_BIT | GLI11.GL_DEPTH_BUFFER_BIT);
    }

    public static void clearAllBuffers() {
        Jpize.GL11.glClear(GLI11.GL_COLOR_BUFFER_BIT | GLI11.GL_DEPTH_BUFFER_BIT | GLI11.GL_STENCIL_BUFFER_BIT);
    }


    public static void clearDepth(float depth) {
        Jpize.GL11.glClearDepth(depth);
    }


    public static void blendFunc(GlBlendFactor sFactor, GlBlendFactor dFactor) {
        Jpize.GL11.glBlendFunc(sFactor.value, dFactor.value);
    }

    public static void depthFunc(GlCompareFunc func) {
        Jpize.GL11.glDepthFunc(func.value);
    }


    public static void depthMask(boolean flag) {
        Jpize.GL11.glDepthMask(flag);
    }

    public static void cullFace(GlFace mode) {
        Jpize.GL11.glCullFace(mode.value);
    }


    public static void pointSize(float size) {
        Jpize.GL11.glPointSize(size);
    }

    public static void lineWidth(float width) {
        Jpize.GL11.glLineWidth(width);
    }


    public static void viewport(int x, int y, int width, int height) {
        Jpize.GL11.glViewport(x, y, width, height);
    }

    public static void viewport(int width, int height) {
        viewport(0, 0, width, height);
    }

    public static void viewportIndexed(int index, float x, float y, float width, float height) {
        Jpize.GL41.glViewportIndexedf(index, x, y, width, height);
    }

    public static void viewportIndexed(int index, float... array) {
        Jpize.GL41.glViewportIndexedfv(index, array);
    }

    public static void viewportIndexed(int index, FloatBuffer buffer) {
        Jpize.GL41.glViewportIndexedfv(index, buffer);
    }

    public static void viewportArray(int first, float... array) {
        Jpize.GL41.glViewportArrayv(first, array);
    }

    public static void viewportArray(int first, FloatBuffer buffer) {
        Jpize.GL41.glViewportArrayv(first, buffer);
    }


    public static void polygonMode(GlFace face, GlPolygonMode mode) {
        Jpize.GL11.glPolygonMode(face.value, mode.value);
    }

    public static void polygonMode(GlPolygonMode mode) {
        polygonMode(GlFace.FRONT_AND_BACK, mode);
    }

    public static void polygonOffset(float factor, float units) {
        Jpize.GL11.glPolygonOffset(factor, units);
    }


    public static void scissor(int x, int y, int width, int height) {
        Jpize.GL11.glScissor(x, y, width, height);
    }

    public static void scissorIndexed(int index, int x, int y, int width, int height) {
        Jpize.GL41.glScissorIndexed(index, x, y, width, height);
    }

    public static void scissorArray(int first, int... array) {
        Jpize.GL41.glScissorArrayv(first, array);
    }

    public static void scissorArray(int first, IntBuffer buffer) {
        Jpize.GL41.glScissorArrayv(first, buffer);
    }


    public static void hint(GlHint hint, GlMode mode) {
        Jpize.GL11.glHint(hint.value, mode.value);
    }


    public static void fog(GlFog fog, int i) {
        Jpize.GL11.glFogi(fog.value, i);
    }

    public static void fog(GlFog fog, float v) {
        Jpize.GL11.glFogf(fog.value, v);
    }

    public static void fog(Color color) {
        Jpize.GL11.glFogfv(GlFog.COLOR.value, color.toArray());
    }


    public static void drawPixels(int width, int height, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL11.glDrawPixels(width, height, format.value, type.value, pixels);
    }

    public static void readPixels(int x, int y, int width, int height, GlInternalFormat format, GlType type, ByteBuffer pixels) {
        Jpize.GL11.glReadPixels(x, y, width, height, format.value, type.value, pixels);
    }


    public static void blendFuncSeparate(int srcFactorRGB, int dstFactorRGB, int srcFactorAlpha, int dstFactorAlpha) {
        Jpize.GL14.glBlendFuncSeparate(srcFactorRGB, dstFactorRGB, srcFactorAlpha, dstFactorAlpha);
    }


    public static float getMaxTextureLodBias() {
        return Jpize.GL11.glGetFloat(GLI14.GL_MAX_TEXTURE_LOD_BIAS);
    }

    public static float getMaxAnisotropy() {
        return Jpize.GL11.glGetFloat(GLI46.GL_MAX_TEXTURE_MAX_ANISOTROPY);
    }

}
