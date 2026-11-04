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
        super.uniformFloat(this.requireUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniformInt(CharSequence uniformName, int value) {
        super.uniformInt(this.requireUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniformVec2f(CharSequence uniformName, float x, float y) {
        super.uniformVec2f(this.requireUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniformVec2i(CharSequence uniformName, int x, int y) {
        super.uniformVec2i(this.requireUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniformVec3f(CharSequence uniformName, float x, float y, float z) {
        super.uniformVec3f(this.requireUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniformVec3i(CharSequence uniformName, int x, int y, int z) {
        super.uniformVec3i(this.requireUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniformVec4f(CharSequence uniformName, float x, float y, float z, float w) {
        super.uniformVec4f(this.requireUniformLocation(uniformName), x, y, z, w);
        return this;
    }

    public Shader uniformVec4i(CharSequence uniformName, int x, int y, int z, int w) {
        super.uniformVec4i(this.requireUniformLocation(uniformName), x, y, z, w);
        return this;
    }


    public Shader uniformBool(CharSequence uniformName, boolean value) {
        this.uniformInt(uniformName, GLBool.by(value));
        return this;
    }

    public Shader uniformVec2f(CharSequence uniformName, Vec2f v) {
        super.uniformVec2f(this.requireUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniformVec3f(CharSequence uniformName, Vec3f v) {
        super.uniformVec3f(this.requireUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniformVec4f(CharSequence uniformName, Vec4f v) {
        super.uniformVec4f(this.requireUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniformVec2i(CharSequence uniformName, Vec2i v) {
        super.uniformVec2i(this.requireUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniformVec3i(CharSequence uniformName, Vec3i v) {
        super.uniformVec3i(this.requireUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniformVec4i(CharSequence uniformName, Vec4i v) {
        super.uniformVec4i(this.requireUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniformVec4f(CharSequence uniformName, AbstractColor color) {
        super.uniformVec4f(this.requireUniformLocation(uniformName), color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        return this;
    }

    public Shader uniformTex2(CharSequence uniformName, Texture2D sampler2D) {
        sampler2D.active(numSampler);
        super.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }

    public Shader uniformTex2Arr(CharSequence uniformName, Texture2DArray sampler2DArray) {
        sampler2DArray.active(numSampler);
        super.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }

    public Shader uniformCubemap(CharSequence uniformName, TextureCubemap samplerCube) {
        samplerCube.active(numSampler);
        super.uniformInt(this.requireUniformLocation(uniformName), numSampler);
        numSampler++;
        return this;
    }


    public Shader uniformFloats(CharSequence uniformName, float[] array) {
        super.uniformFloats(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformInts(CharSequence uniformName, int[] array) {
        super.uniformInts(this.requireUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniformVecs2f(CharSequence uniformName, float[] values) {
        super.uniformVecs2f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs2i(CharSequence uniformName, int[] values) {
        super.uniformVecs2i(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3f(CharSequence uniformName, float[] values) {
        super.uniformVecs3f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs3i(CharSequence uniformName, int[] values) {
        super.uniformVecs3i(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4f(CharSequence uniformName, float[] values) {
        super.uniformVecs4f(this.requireUniformLocation(uniformName), values);
        return this;
    }

    public Shader uniformVecs4i(CharSequence uniformName, int[] values) {
        super.uniformVecs4i(this.requireUniformLocation(uniformName), values);
        return this;
    }


    public Shader uniformMat4(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat4(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4x3(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat4x3(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat4x2(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat4x2(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3x4(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat3x4(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat3(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat3x2(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat3x2(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x4(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat2x4(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2x3(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat2x3(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }

    public Shader uniformMat2(CharSequence uniformName, boolean transpose, float[] values) {
        super.uniformMat2(this.requireUniformLocation(uniformName), transpose, values);
        return this;
    }


    public Shader uniformMat4(CharSequence uniformName, Matrix4f matrix4f) {
        this.uniformMat4(uniformName, false, matrix4f.values);
        return this;
    }

    public Shader uniformMat3(CharSequence uniformName, Matrix3f matrix3f) {
        this.uniformMat3(uniformName, false, matrix3f.values);
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
