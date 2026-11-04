package jpize.opengl.shader;

import jpize.opengl.buffer.GLUniformBuffer;
import jpize.opengl.texture.TextureCubemap;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.texture.Texture2DArray;
import jpize.opengl.type.GLBool;
import jpize.util.color.AbstractColor;
import generaloss.spatialmath.matrix.Matrix3f;
import generaloss.spatialmath.matrix.Matrix4f;
import generaloss.spatialmath.vector.*;
import generaloss.resourceflow.resource.Resource;

import java.awt.*;
import java.util.HashMap;

public class Shader extends GLProgram {

    private final HashMap<CharSequence, Integer> uniforms;
    private int numSampler, numUniformBuffer;

    public Shader() {
        this.uniforms = new HashMap<>();
    }

    public Shader(String vertexCode, String fragmentCode) {
        this();
        this.load(vertexCode, fragmentCode);
    }

    public Shader(Resource resVertex, Resource resFragment) {
        this();
        this.load(resVertex, resFragment);
    }

    public Shader(String vertexCode, String fragmentCode, String geometryCode) {
        this();
        this.load(vertexCode, fragmentCode, geometryCode);
    }

    public Shader(Resource resVertex, Resource resFragment, Resource resGeometry) {
        this();
        this.load(resVertex, resFragment, resGeometry);
    }


    private static GLShader createAndCompileShader(GLShaderType type, String code) {
        final GLShader shader = new GLShader(type);
        shader.setSource(code);
        shader.compile();
        shader.checkCompileError();
        return shader;
    }

    public Shader load(String vertexCode, String fragmentCode) {
        uniforms.clear();
        super.detachAll(3);

        final GLShader vertexShader = createAndCompileShader(GLShaderType.VERTEX, vertexCode);
        final GLShader fragmentShader = createAndCompileShader(GLShaderType.FRAGMENT, fragmentCode);

        super.attach(vertexShader);
        super.attach(fragmentShader);

        super.link();
        super.checkLinkError();

        super.validate();
        try {
            super.checkValidateError();

            this.detectUniforms();
        } finally {
            vertexShader.dispose();
            fragmentShader.dispose();
        }
        return this;
    }

    public Shader load(Resource resVertex, Resource resFragment) {
        return this.load(resVertex.readString(), resFragment.readString());
    }

    public Shader load(String vertexCode, String fragmentCode, String geometryCode) {
        uniforms.clear();
        super.detachAll(3);

        final GLShader geometryShader = createAndCompileShader(GLShaderType.GEOMETRY, geometryCode);
        final GLShader vertexShader = createAndCompileShader(GLShaderType.VERTEX, vertexCode);
        final GLShader fragmentShader = createAndCompileShader(GLShaderType.FRAGMENT, fragmentCode);

        super.attach(geometryShader);
        super.attach(vertexShader);
        super.attach(fragmentShader);

        super.link();
        super.checkLinkError();

        super.validate();
        try {
            super.checkValidateError();

            this.detectUniforms();
        } finally {
            geometryShader.dispose();
            vertexShader.dispose();
            fragmentShader.dispose();
        }
        return this;
    }

    public Shader load(Resource resVertex, Resource resFragment, Resource resGeometry) {
        return this.load(resVertex.readString(), resFragment.readString(), resGeometry.readString());
    }


    private void detectUniforms() {
        final int uniformCount = super.getActiveUniforms();

        for(int i = 0; i < uniformCount; i++) {
            String name = super.getActiveUniformName(i);
            final int location = super.getUniformLocation(name);
            uniforms.put(name, location);
        }
    }

    protected int getCachedUniformLocation(CharSequence uniformName) {
        if(!uniforms.containsKey(uniformName))
            return -1;
        return uniforms.get(uniformName);
    }

    protected int requireUniformLocation(CharSequence name) {
        final int location = this.getCachedUniformLocation(name);
        if(location < 0)
            throw new IllegalStateException("Uniform not found: " + name);

        return location;
    }


    public Shader uniformFloat(CharSequence uniformName, float value) {
        GLProgram.uniformFloat(this.requireUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniformInt(CharSequence uniformName, int value) {
        GLProgram.uniformInt(this.requireUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniformVec2f(CharSequence uniformName, float x, float y) {
        GLProgram.uniformVec2f(this.requireUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniformVec2i(CharSequence uniformName, int x, int y) {
        GLProgram.uniformVec2i(this.requireUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniformVec3f(CharSequence uniformName, float x, float y, float z) {
        GLProgram.uniformVec3f(this.requireUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniformVec3i(CharSequence uniformName, int x, int y, int z) {
        GLProgram.uniformVec3i(this.requireUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniformVec4f(CharSequence uniformName, float x, float y, float z, float w) {
        GLProgram.uniformVec4f(this.requireUniformLocation(uniformName), x, y, z, w);
        return this;
    }

    public Shader uniformVec4i(CharSequence uniformName, int x, int y, int z, int w) {
        GLProgram.uniformVec4i(this.requireUniformLocation(uniformName), x, y, z, w);
        return this;
    }


    public Shader uniformBool(CharSequence uniformName, boolean value) {
        this.uniformInt(uniformName, GLBool.by(value));
        return this;
    }

    public Shader uniformVec2f(CharSequence uniformName, Vec2f v) {
        GLProgram.uniformVec2f(this.requireUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniformVec3f(CharSequence uniformName, Vec3f v) {
        GLProgram.uniformVec3f(this.requireUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniformVec4f(CharSequence uniformName, Vec4f v) {
        GLProgram.uniformVec4f(this.requireUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniformVec2i(CharSequence uniformName, Vec2i v) {
        GLProgram.uniformVec2i(this.requireUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniformVec3i(CharSequence uniformName, Vec3i v) {
        GLProgram.uniformVec3i(this.requireUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniformVec4i(CharSequence uniformName, Vec4i v) {
        GLProgram.uniformVec4i(this.requireUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniformColor(CharSequence uniformName, AbstractColor color) {
        GLProgram.uniformVec4f(this.requireUniformLocation(uniformName), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        return this;
    }

    public Shader uniformColor(CharSequence uniformName, Color color) {
        GLProgram.uniformVec4f(this.requireUniformLocation(uniformName), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        return this;
    }

    public Shader uniformTex2(CharSequence uniformName, Texture2D sampler2D) {
        sampler2D.active(numSampler);
        GLProgram.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }

    public Shader uniformTex2Arr(CharSequence uniformName, Texture2DArray sampler2DArray) {
        sampler2DArray.active(numSampler);
        GLProgram.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }

    public Shader uniformCubemap(CharSequence uniformName, TextureCubemap samplerCube) {
        samplerCube.active(numSampler);
        GLProgram.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }


    public Shader uniformFloats(CharSequence uniformName, float[] array) {
        GLProgram.uniformFloats(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformDoubles(CharSequence uniformName, double[] array) {
        GLProgram.uniformDoubles(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformInts(CharSequence uniformName, int[] array) {
        GLProgram.uniformInts(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformUints(CharSequence uniformName, int[] array) {
        GLProgram.uniformUints(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformVecs2f(CharSequence uniformName, float[] values) {
        GLProgram.uniformVecs2f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs2d(CharSequence uniformName, double[] values) {
        GLProgram.uniformVecs2d(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs2i(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs2i(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs2ui(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs2ui(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3f(CharSequence uniformName, float[] values) {
        GLProgram.uniformVecs3f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3d(CharSequence uniformName, double[] values) {
        GLProgram.uniformVecs3d(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3i(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs3i(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3ui(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs3ui(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4f(CharSequence uniformName, float[] values) {
        GLProgram.uniformVecs4f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4d(CharSequence uniformName, double[] values) {
        GLProgram.uniformVecs4d(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4i(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs4i(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4ui(CharSequence uniformName, int[] values) {
        GLProgram.uniformVecs4ui(this.requireUniformLocation(uniformName), values);
        return this;
    }


    public Shader uniformMat2f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat2f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat2d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x3f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat2x3f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x3d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat2x3d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x4f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat2x4f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x4d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat2x4d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }


    public Shader uniformMat3x2f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat3x2f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3x2d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat3x2d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat3f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat3d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3x4f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat3x4f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3x4d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat3x4d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }


    public Shader uniformMat4x2f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat4x2f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4x2d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat4x2d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4x3f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat4x3f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4x3d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat4x3d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4f(CharSequence uniformName, boolean transpose, float[] values) {
        GLProgram.uniformMat4f(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4d(CharSequence uniformName, boolean transpose, double[] values) {
        GLProgram.uniformMat4d(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }


    public Shader uniformMat4f(CharSequence uniformName, Matrix4f matrix4f) {
        this.uniformMat4f(uniformName, false, matrix4f.values);
        return this;
    }

    public Shader uniformMat3f(CharSequence uniformName, Matrix3f matrix3f) {
        this.uniformMat3f(uniformName, false, matrix3f.values);
        return this;
    }


    public Shader bindAttribute(int index, String name) {
        super.bindAttributeLocation(index, name);
        return this;
    }


    public Shader uniformBlockBinding(CharSequence uniformBlockName, int blockBindingPoint) {
        final int index = super.getUniformBlockIndex(uniformBlockName);
        if(index < 0)
            return this;
        super.uniformBlockBinding(index, blockBindingPoint);
        return this;
    }

    public Shader uniformBuffer(CharSequence uniformName, GLUniformBuffer uniformBuffer) {
        uniformBuffer.bind(numUniformBuffer);
        return this.uniformBlockBinding(uniformName, numUniformBuffer++);
    }

    
    public void bind() {
        numSampler = 0;
        numUniformBuffer = 0;
        super.bind();
    }

    public static void unbind() {
        GLProgram.unbind();
    }


    @Override
    public void dispose() {
        super.dispose();
        uniforms.clear();
    }

}
