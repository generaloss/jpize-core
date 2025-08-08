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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shader extends GLProgram {

    private final HashMap<CharSequence, Integer> uniforms;
    private int numSampler2D, numSamplerCube, numSampler2DArray, numUniformBuffer;

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
        super.checkValidateError();

        vertexShader.dispose();
        fragmentShader.dispose();

        this.detectUniforms(vertexCode);
        this.detectUniforms(fragmentCode);
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
        super.checkValidateError();

        geometryShader.dispose();
        vertexShader.dispose();
        fragmentShader.dispose();

        this.detectUniforms(vertexCode);
        this.detectUniforms(fragmentCode);
        this.detectUniforms(geometryCode);
        return this;
    }

    public Shader load(Resource resVertex, Resource resFragment, Resource resGeometry) {
        return this.load(resVertex.readString(), resFragment.readString(), resGeometry.readString());
    }


    private void detectUniforms(String code) {
        // remove comments
        code = code.replaceAll("//.*|/\\*(.|\\R)*?\\*/", "");

        // uniform <type> <name> [array optional]
        final Pattern pattern = Pattern.compile("\\buniform\\s+\\w+\\s+(\\w+)");
        final Matcher matcher = pattern.matcher(code);

        while(matcher.find()) {
            final String name = matcher.group(1);
            uniforms.put(name, super.getUniformLocation(name));
        }
    }

    protected int getCachedUniformLocation(CharSequence uniformName) {
        if(!uniforms.containsKey(uniformName))
            throw new IllegalArgumentException("No such uniform: " + uniformName);
        return uniforms.get(uniformName);
    }


    public Shader uniformMat4(CharSequence uniformName, float[] values) {
        super.uniformMat4(this.getCachedUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Matrix4f matrix4f) {
        this.uniformMat4(uniformName, matrix4f.values);
        return this;
    }

    public Shader uniformMat3(CharSequence uniformName, float[] values) {
        super.uniformMat3(this.getCachedUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Matrix3f matrix3f) {
        this.uniformMat3(uniformName, matrix3f.values);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec2f v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec3f v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec4f v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec2i v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec3i v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Vec4i v) {
        super.uniform(this.getCachedUniformLocation(uniformName), v.x, v.y, v.z, v.w);
        return this;
    }

    public Shader uniform(CharSequence uniformName, float x) {
        super.uniform(this.getCachedUniformLocation(uniformName), x);
        return this;
    }

    public Shader uniform(CharSequence uniformName, float x, float y) {
        super.uniform(this.getCachedUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniform(CharSequence uniformName, float x, float y, float z) {
        super.uniform(this.getCachedUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniform(CharSequence uniformName, float x, float y, float z, float w) {
        super.uniform(this.getCachedUniformLocation(uniformName), x, y, z, w);
        return this;
    }

    public Shader uniform(CharSequence uniformName, float[] array) {
        super.uniform(this.getCachedUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(CharSequence uniformName, int value) {
        super.uniform(this.getCachedUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniform(CharSequence uniformName, boolean value) {
        this.uniform(uniformName, GLBool.by(value));
        return this;
    }

    public Shader uniform(CharSequence uniformName, int[] array) {
        super.uniform(this.getCachedUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(CharSequence uniformName, AbstractColor color) {
        super.uniform(this.getCachedUniformLocation(uniformName), color);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Texture2D sampler2D) {
        super.uniform(this.getCachedUniformLocation(uniformName), sampler2D, numSampler2D);
        numSampler2D++;
        return this;
    }

    public Shader uniform(CharSequence uniformName, Texture2DArray sampler2DArray) {
        super.uniform(this.getCachedUniformLocation(uniformName), sampler2DArray, numSampler2DArray);
        numSampler2DArray++;
        return this;
    }

    public Shader uniform(CharSequence uniformName, TextureCubemap samplerCube) {
        super.uniform(this.getCachedUniformLocation(uniformName), samplerCube, numSamplerCube);
        numSamplerCube++;
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

    public Shader uniform(CharSequence uniformName, GLUniformBuffer uniformBuffer) {
        uniformBuffer.bind(numUniformBuffer);
        return this.uniformBlockBinding(uniformName, numUniformBuffer++);
    }

    
    public void bind() {
        numSampler2D = 0;
        numSamplerCube = 0;
        numSampler2DArray = 0;
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
