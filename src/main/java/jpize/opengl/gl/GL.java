package jpize.opengl.gl;

import jpize.context.Jpize;
import jpize.opengl.GLError;
import jpize.opengl.glenum.*;
import jpize.opengl.tesselation.GLFace;
import jpize.opengl.type.GLType;
import jpize.opengl.tesselation.GLPolygonMode;
import jpize.opengl.texture.GLBlendFactor;
import jpize.opengl.texture.GLInternalFormat;
import jpize.util.color.Color;
import jpize.util.color.AbstractColor;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GL {

    public static GLError getError() {
        return GLError.byValue(Jpize.GL11.glGetError());
    }

    public static void checkError(){
        final GLError error = getError();
        if(error != GLError.NO_ERROR)
            System.err.println("[GL] Error: " + error.description);
    }


    public static boolean isEnabled(GLTarget target) {
        return Jpize.GL11.glIsEnabled(target.value);
    }

    public static boolean isEnabled(GLTarget target, int index) {
        return Jpize.GL30.glIsEnabledi(target.value, index);
    }

    public static void enable(GLTarget target) {
        Jpize.GL11.glEnable(target.value);
    }

    public static void enable(GLTarget target, int index) {
        Jpize.GL30.glEnablei(target.value, index);
    }

    public static void enable(GLTarget... targets) {
        for(GLTarget target: targets)
            enable(target);
    }

    public static void disable(GLTarget target) {
        Jpize.GL11.glDisable(target.value);
    }

    public static void disable(GLTarget target, int index) {
        Jpize.GL30.glDisablei(target.value, index);
    }

    public static void disable(GLTarget... targets) {
        for(GLTarget target: targets)
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
        clearColor(AbstractColor.rgbaRed(color), AbstractColor.rgbaGreen(color), AbstractColor.rgbaBlue(color));
    }

    public static void clearColorRGBA(int color) {
        clearColor(AbstractColor.rgbaRed(color), AbstractColor.rgbaGreen(color), AbstractColor.rgbaBlue(color), AbstractColor.rgbaAlpha(color));
    }


    public static void clearColorBuffer() {
        Jpize.GL11.glClear(GL11I.GL_COLOR_BUFFER_BIT);
    }

    public static void clearDepthBuffer() {
        Jpize.GL11.glClear(GL11I.GL_DEPTH_BUFFER_BIT);
    }

    public static void clearStencilBuffer() {
        Jpize.GL11.glClear(GL11I.GL_STENCIL_BUFFER_BIT);
    }

    public static void clearColorDepthBuffers() {
        Jpize.GL11.glClear(GL11I.GL_COLOR_BUFFER_BIT | GL11I.GL_DEPTH_BUFFER_BIT);
    }

    public static void clearAllBuffers() {
        Jpize.GL11.glClear(GL11I.GL_COLOR_BUFFER_BIT | GL11I.GL_DEPTH_BUFFER_BIT | GL11I.GL_STENCIL_BUFFER_BIT);
    }


    public static void clearDepth(float depth) {
        Jpize.GL11.glClearDepth(depth);
    }


    public static void blendFunc(GLBlendFactor sFactor, GLBlendFactor dFactor) {
        Jpize.GL11.glBlendFunc(sFactor.value, dFactor.value);
    }

    public static void depthFunc(GLCompareFunc func) {
        Jpize.GL11.glDepthFunc(func.value);
    }


    public static void depthMask(boolean flag) {
        Jpize.GL11.glDepthMask(flag);
    }

    public static void cullFace(GLFace mode) {
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


    public static void polygonMode(GLFace face, GLPolygonMode mode) {
        Jpize.GL11.glPolygonMode(face.value, mode.value);
    }

    public static void polygonMode(GLPolygonMode mode) {
        polygonMode(GLFace.FRONT_AND_BACK, mode);
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


    public static void hint(GLHint hint, GLMode mode) {
        Jpize.GL11.glHint(hint.value, mode.value);
    }


    public static void fog(GLFog fog, int i) {
        Jpize.GL11.glFogi(fog.value, i);
    }

    public static void fog(GLFog fog, float v) {
        Jpize.GL11.glFogf(fog.value, v);
    }

    public static void fog(Color color) {
        Jpize.GL11.glFogfv(GLFog.COLOR.value, color.toArray());
    }


    public static void drawPixels(int width, int height, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL11.glDrawPixels(width, height, format.value, type.value, pixels);
    }

    public static void readPixels(int x, int y, int width, int height, GLInternalFormat format, GLType type, ByteBuffer pixels) {
        Jpize.GL11.glReadPixels(x, y, width, height, format.value, type.value, pixels);
    }


    public static void blendFuncSeparate(int srcFactorRGB, int dstFactorRGB, int srcFactorAlpha, int dstFactorAlpha) {
        Jpize.GL14.glBlendFuncSeparate(srcFactorRGB, dstFactorRGB, srcFactorAlpha, dstFactorAlpha);
    }


    public static float getMaxTextureLodBias() {
        return Jpize.GL11.glGetFloat(GL14I.GL_MAX_TEXTURE_LOD_BIAS);
    }

    public static float getMaxAnisotropy() {
        return Jpize.GL11.glGetFloat(GL46I.GL_MAX_TEXTURE_MAX_ANISOTROPY);
    }

}
