package jpize.opengl.shader;

import jpize.opengl.buffer.GlUniformBuffer;
import jpize.opengl.texture.TextureCubemap;
import jpize.opengl.texture.Texture2D;
import jpize.opengl.texture.Texture2DArray;
import jpize.util.color.AbstractColor;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;
import jpize.util.res.Resource;

import java.util.HashMap;

public class Shader extends GlProgram {

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


    public Shader load(String vertexCode, String fragmentCode) {
        uniforms.clear();
        super.detachAll();

        final GlShader vertexShader = new GlShader(GlShaderType.VERTEX);
        vertexShader.setSource(vertexCode);
        vertexShader.compileAndCheckError();
        super.attach(vertexShader);

        final GlShader fragmentShader = new GlShader(GlShaderType.FRAGMENT);
        fragmentShader.setSource(fragmentCode);
        fragmentShader.compileAndCheckError();
        super.attach(fragmentShader);

        super.link();
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
        super.detachAll();

        final GlShader geometryShader = new GlShader(GlShaderType.GEOMETRY);
        geometryShader.setSource(geometryCode);
        geometryShader.compileAndCheckError();
        super.attach(geometryShader);

        final GlShader vertexShader = new GlShader(GlShaderType.VERTEX);
        vertexShader.setSource(vertexCode);
        vertexShader.compileAndCheckError();
        super.attach(vertexShader);

        final GlShader fragmentShader = new GlShader(GlShaderType.FRAGMENT);
        fragmentShader.setSource(fragmentCode);
        fragmentShader.compileAndCheckError();
        super.attach(fragmentShader);

        super.link();
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


    private void detectUniforms(String code) {                   // '..\nuniform type name [n] ;\n..'
        final String[] uniformSplit = code.split("uniform");     // ' type name [n] ;\n..'
        for(int i = 1; i < uniformSplit.length; i++){
            String name = uniformSplit[i].split(";")[0];         // ' type name [n] '
            if(name.contains("["))
                name = name.substring(0, name.lastIndexOf("[")); // ' type name '
            name = name.strip();                                 // 'type name'
            name = name.substring(name.lastIndexOf(" ") + 1);    // 'name'

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
        uniformMat4(uniformName, matrix4f.val);
        return this;
    }

    public Shader uniformMat3(CharSequence uniformName, float[] values) {
        super.uniformMat3(this.getCachedUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(CharSequence uniformName, Matrix3f matrix3f) {
        this.uniformMat3(uniformName, matrix3f.val);
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
        this.uniform(uniformName, value ? 1 : 0);
        return this;
    }

    public Shader uniform(CharSequence uniformName, int[] array) {
        super.uniform(this.getCachedUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(CharSequence uniformName, AbstractColor color) {
        super.uniform(this.getCachedUniformLocation(uniformName),
                color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        return this;
    }

    public Shader uniform(CharSequence uniformName, Texture2D texture) {
        texture.active(numSampler2D);
        super.uniform(this.getCachedUniformLocation(uniformName), numSampler2D);
        numSampler2D++;
        return this;
    }

    public Shader uniform(CharSequence uniformName, Texture2DArray textureArray) {
        textureArray.active(numSampler2DArray);
        super.uniform(this.getCachedUniformLocation(uniformName), numSampler2DArray);
        numSampler2DArray++;
        return this;
    }

    public Shader uniform(CharSequence uniformName, TextureCubemap cubeMap) {
        cubeMap.active(numSamplerCube);
        super.uniform(this.getCachedUniformLocation(uniformName), numSamplerCube);
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
            throw new RuntimeException("No uniform block called '" + uniformBlockName + "'");
        super.uniformBlockBinding(index, blockBindingPoint);
        return this;
    }

    public Shader uniform(CharSequence uniformName, GlUniformBuffer uniformBuffer) {
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
        GlProgram.unbind();
    }


    @Override
    public void dispose() {
        super.dispose();
    }

}
