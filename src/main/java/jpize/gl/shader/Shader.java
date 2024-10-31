package jpize.gl.shader;

import jpize.gl.texture.TextureCubeMap;
import jpize.gl.texture.Texture2D;
import jpize.gl.texture.Texture2DArray;
import jpize.util.Disposable;
import jpize.util.color.Color;
import jpize.util.math.matrix.Matrix3f;
import jpize.util.math.matrix.Matrix4f;
import jpize.util.math.vector.Vec2f;
import jpize.util.math.vector.Vec3f;
import jpize.util.res.Resource;

import java.util.HashMap;

public class Shader implements Disposable {

    private final GlProgram program;
    private final HashMap<String, Integer> uniforms;
    private int num_sampler2D, num_samplerCube, num_sampler2DArray;

    public Shader(String vertexCode, String fragmentCode) {
        this.program = new GlProgram();

        final GlShader vertexShader = new GlShader(vertexCode, GlShaderType.VERTEX);
        final GlShader fragmentShader = new GlShader(fragmentCode, GlShaderType.FRAGMENT);

        this.program.attach(vertexShader);
        this.program.attach(fragmentShader);
        this.program.link();

        vertexShader.dispose();
        fragmentShader.dispose();

        this.uniforms = new HashMap<>();
        this.detectUniforms(vertexCode);
        this.detectUniforms(fragmentCode);
        this.bindFragDataLocation(0, "");
    }

    public Shader(Resource resVertex, Resource resFragment) {
        this(resVertex.readString(), resFragment.readString());
    }

    public Shader(String vertexCode, String fragmentCode, String geometryCode) {
        this.program = new GlProgram();

        final GlShader geometryShader = new GlShader(geometryCode, GlShaderType.GEOMETRY);
        final GlShader vertexShader = new GlShader(vertexCode, GlShaderType.VERTEX);
        final GlShader fragmentShader = new GlShader(fragmentCode, GlShaderType.FRAGMENT);

        this.program.attach(geometryShader);
        this.program.attach(vertexShader);
        this.program.attach(fragmentShader);
        this.program.link();

        geometryShader.dispose();
        vertexShader.dispose();
        fragmentShader.dispose();

        this.uniforms = new HashMap<>();
        this.detectUniforms(vertexCode);
        this.detectUniforms(fragmentCode);
        this.detectUniforms(geometryCode);
    }

    public Shader(Resource resVertex, Resource resFragment, Resource resGeometry) {
        this(resVertex.readString(), resFragment.readString(), resGeometry.readString());
    }

    private void detectUniforms(String code) {                   // '..\nuniform type name [n] ;\n..'
        final String[] uniformSplit = code.split("uniform");     // ' type name [n] ;\n..'
        for(int i = 1; i < uniformSplit.length; i++){
            String name = uniformSplit[i].split(";")[0];         // ' type name [n] '
            if(name.contains("["))
                name = name.substring(0, name.lastIndexOf("[")); // ' type name '
            name = name.strip();                                 // 'type name'
            name = name.substring(name.lastIndexOf(" ") + 1);    // 'name'

            uniforms.put(name, program.getUniformLoc(name));
        }
    }

    protected int getUniformLocation(String uniformName) {
        if(!uniforms.containsKey(uniformName))
            throw new IllegalArgumentException("No such uniform: " + uniformName);
        return uniforms.get(uniformName);
    }


    public GlProgram getProgram() {
        return program;
    }


    public Shader uniformMat4(String uniformName, float[] values) {
        program.uniformMat4(getUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(String uniformName, Matrix4f matrix4f) {
        uniformMat4(uniformName, matrix4f.val);
        return this;
    }

    public Shader uniformMat3(String uniformName, float[] values) {
        program.uniformMat3(getUniformLocation(uniformName), false, values);
        return this;
    }

    public Shader uniform(String uniformName, Matrix3f matrix3f) {
        uniformMat3(uniformName, matrix3f.val);
        return this;
    }

    public Shader uniform(String uniformName, Vec2f v) {
        program.uniform(getUniformLocation(uniformName), v.x, v.y);
        return this;
    }

    public Shader uniform(String uniformName, Vec3f v) {
        program.uniform(getUniformLocation(uniformName), v.x, v.y, v.z);
        return this;
    }

    public Shader uniform(String uniformName, float x) {
        program.uniform(getUniformLocation(uniformName), x);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y) {
        program.uniform(getUniformLocation(uniformName), x, y);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y, float z) {
        program.uniform(getUniformLocation(uniformName), x, y, z);
        return this;
    }

    public Shader uniform(String uniformName, float x, float y, float z, float w) {
        program.uniform(getUniformLocation(uniformName), x, y, z, w);
        return this;
    }

    public Shader uniform(String uniformName, float[] array) {
        program.uniform(getUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(String uniformName, int value) {
        program.uniform(getUniformLocation(uniformName), value);
        return this;
    }

    public Shader uniform(String uniformName, boolean value) {
        uniform(uniformName, value ? 1 : 0);
        return this;
    }

    public Shader uniform(String uniformName, int[] array) {
        program.uniform(getUniformLocation(uniformName), array);
        return this;
    }

    public Shader uniform(String uniformName, Color color) {
        program.uniform(getUniformLocation(uniformName), color.r, color.g, color.b, color.a);
        return this;
    }

    public Shader uniform(String uniformName, Texture2D texture) {
        texture.active(num_sampler2D);
        program.uniform(getUniformLocation(uniformName), num_sampler2D);
        num_sampler2D++;
        return this;
    }

    public Shader uniform(String uniformName, Texture2DArray textureArray) {
        textureArray.active(num_sampler2DArray);
        program.uniform(getUniformLocation(uniformName), num_sampler2DArray);
        num_sampler2DArray++;
        return this;
    }

    public Shader uniform(String uniformName, TextureCubeMap cubeMap) {
        cubeMap.active(num_samplerCube);
        program.uniform(getUniformLocation(uniformName), num_samplerCube);
        num_samplerCube++;
        return this;
    }


    public Shader bindAttribute(int index, String name) {
        program.bindAttributeLocation(index, name);
        return this;
    }

    public Shader bindFragDataLocation(int index, String name) {
        program.bindFragDataLocation(index, name);
        return this;
    }


    public Shader uniformBlockBinding(String uniformName, int bindingPoint) {
        final int index = program.getUniformBlockIndex(uniformName);
        if(index < 0)
            throw new RuntimeException("No uniform buffer called '" + uniformName + "'");
        program.uniformBlockBinding(index, bindingPoint);
        return this;
    }

    
    public Shader bind() {
        num_sampler2D = 0;
        num_samplerCube = 0;
        num_sampler2DArray = 0;

        program.bind();
        return this;
    }

    public static void unbind() {
        GlProgram.unbind();
    }


    @Override
    public void dispose() {
        program.dispose();
    }

}
