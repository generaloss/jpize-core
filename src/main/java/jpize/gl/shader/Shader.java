package jpize.gl.shader;

import jpize.gl.texture.TextureCubemap;
import jpize.gl.texture.Texture2D;
import jpize.gl.texture.Texture2DArray;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;
import jpize.util.res.Resource;

import java.util.HashMap;

public class Shader extends GlProgram {

    private final HashMap<String, Integer> uniforms;
    private int numSampler2D, numSamplerCube, numSampler2DArray;

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
        final GlShader vertexShader = new GlShader(vertexCode, GlShaderType.VERTEX);
        final GlShader fragmentShader = new GlShader(fragmentCode, GlShaderType.FRAGMENT);
        super.detachAll();
        super.attach(vertexShader);
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
        final GlShader geometryShader = new GlShader(geometryCode, GlShaderType.GEOMETRY);
        final GlShader vertexShader = new GlShader(vertexCode, GlShaderType.VERTEX);
        final GlShader fragmentShader = new GlShader(fragmentCode, GlShaderType.FRAGMENT);
        super.detachAll();
        super.attach(geometryShader);
        super.attach(vertexShader);
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
        uniforms.clear();
        final String[] uniformSplit = code.split("uniform");     // ' type name [n] ;\n..'
        for(int i = 1; i < uniformSplit.length; i++){
            String name = uniformSplit[i].split(";")[0];         // ' type name [n] '
            if(name.contains("["))
                name = name.substring(0, name.lastIndexOf("[")); // ' type name '
            name = name.strip();                                 // 'type name'
            name = name.substring(name.lastIndexOf(" ") + 1);    // 'name'

            uniforms.put(name, super.getUniformLoc(name));
        }
    }

    protected int getUniformLocation(String uniformName) {
        if(!uniforms.containsKey(uniformName))
            throw new IllegalArgumentException("No such uniform: " + uniformName);
        return uniforms.get(uniformName);
    }


    public Shader uniformMat4(String uniformName, float[] values) {
        super.uniformMat4(this.getUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(String uniformName, Matrix4f matrix4f) {
        uniformMat4(uniformName, matrix4f.val);
        return this;
    }

    public Shader uniformMat3(String uniformName, float[] values) {
        super.uniformMat3(this.getUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(String uniformName, Matrix3f matrix3f) {
        this.uniformMat3(uniformName, matrix3f.val);
        return this;
    }

    public Shader uniform(String uniformName, Vec2f v) {
        super.uniform(this.getUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniform(String uniformName, Vec3f v) {
        super.uniform(this.getUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniform(String uniformName, float x) {
        super.uniform(this.getUniformLocation(uniformName), x);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y) {
        super.uniform(this.getUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y, float z) {
        super.uniform(this.getUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y, float z, float w) {
        super.uniform(this.getUniformLocation(uniformName), x, y, z, w);
        return this;
    }

    public Shader uniform(String uniformName, float[] array) {
        super.uniform(this.getUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(String uniformName, int value) {
        super.uniform(this.getUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniform(String uniformName, boolean value) {
        this.uniform(uniformName, value ? 1 : 0);
        return this;
    }

    public Shader uniform(String uniformName, int[] array) {
        super.uniform(this.getUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(String uniformName, Color color) {
        super.uniform(this.getUniformLocation(uniformName), color.r, color.g, color.b, color.a);
        return this;
    }

    public Shader uniform(String uniformName, Texture2D texture) {
        texture.active(numSampler2D);
        super.uniform(this.getUniformLocation(uniformName), numSampler2D);
        numSampler2D++;
        return this;
    }

    public Shader uniform(String uniformName, Texture2DArray textureArray) {
        textureArray.active(numSampler2DArray);
        super.uniform(this.getUniformLocation(uniformName), numSampler2DArray);
        numSampler2DArray++;
        return this;
    }

    public Shader uniform(String uniformName, TextureCubemap cubeMap) {
        cubeMap.active(numSamplerCube);
        super.uniform(this.getUniformLocation(uniformName), numSamplerCube);
        numSamplerCube++;
        return this;
    }


    public Shader bindAttribute(int index, String name) {
        super.bindAttributeLocation(index, name);
        return this;
    }


    public Shader uniformBlockBinding(String uniformName, int bindingPoint) {
        final int index = super.getUniformBlockIndex(uniformName);
        if(index < 0)
            throw new RuntimeException("No uniform buffer called '" + uniformName + "'");
        super.uniformBlockBinding(index, bindingPoint);
        return this;
    }

    
    public void bind() {
        numSampler2D = 0;
        numSamplerCube = 0;
        numSampler2DArray = 0;
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
