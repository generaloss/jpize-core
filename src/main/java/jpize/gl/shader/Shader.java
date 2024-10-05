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


    public void uniformMat4(String uniformName, float[] values) {
        program.uniformMat4(getUniformLocation(uniformName), false, values);
    }

    public void uniform(String uniformName, Matrix4f matrix4f) {
        uniformMat4(uniformName, matrix4f.val);
    }

    public void uniformMat3(String uniformName, float[] values) {
        program.uniformMat3(getUniformLocation(uniformName), false, values);
    }

    public void uniform(String uniformName, Matrix3f matrix3f) {
        uniformMat3(uniformName, matrix3f.val);
    }

    public void uniform(String uniformName, Vec2f v) {
        program.uniform(getUniformLocation(uniformName), v.x, v.y);
    }

    public void uniform(String uniformName, Vec3f v) {
        program.uniform(getUniformLocation(uniformName), v.x, v.y, v.z);
    }

    public void uniform(String uniformName, float x) {
        program.uniform(getUniformLocation(uniformName), x);
    }

    public void uniform(String uniformName, float x, float y) {
        program.uniform(getUniformLocation(uniformName), x, y);
    }

    public void uniform(String uniformName, float x, float y, float z) {
        program.uniform(getUniformLocation(uniformName), x, y, z);
    }

    public void uniform(String uniformName, float x, float y, float z, float w) {
        program.uniform(getUniformLocation(uniformName), x, y, z, w);
    }

    public void uniform(String uniformName, float[] array) {
        program.uniform(getUniformLocation(uniformName), array);
    }

    public void uniform(String uniformName, int value) {
        program.uniform(getUniformLocation(uniformName), value);
    }

    public void uniform(String uniformName, boolean value) {
        uniform(uniformName, value ? 1 : 0);
    }

    public void uniform(String uniformName, int[] array) {
        program.uniform(getUniformLocation(uniformName), array);
    }

    public void uniform(String uniformName, Color color) {
        program.uniform(getUniformLocation(uniformName), color.r, color.g, color.b, color.a);
    }

    public void uniform(String uniformName, Texture2D texture) {
        texture.active(num_sampler2D);
        program.uniform(getUniformLocation(uniformName), num_sampler2D);
        num_sampler2D++;
    }

    public void uniform(String uniformName, Texture2DArray textureArray) {
        textureArray.active(num_sampler2DArray);
        program.uniform(getUniformLocation(uniformName), num_sampler2DArray);
        num_sampler2DArray++;
    }

    public void uniform(String uniformName, TextureCubeMap cubeMap) {
        cubeMap.active(num_samplerCube);
        program.uniform(getUniformLocation(uniformName), num_samplerCube);
        num_samplerCube++;
    }


    public void bindAttribute(int index, String name) {
        program.bindAttributeLocation(index, name);
    }

    public void bindFragDataLocation(int index, String name) {
        program.bindFragDataLocation(index, name);
    }


    public void uniformBlockBinding(String uniformName, int bindingPoint) {
        final int index = program.getUniformBlockIndex(uniformName);
        if(index < 0)
            throw new RuntimeException("No uniform buffer called '" + uniformName + "'");
        program.uniformBlockBinding(index, bindingPoint);
    }

    
    public void bind() {
        program.bind();

        num_sampler2D = 0;
        num_samplerCube = 0;
        num_sampler2DArray = 0;
    }

    public static void unbind() {
        GlProgram.unbind();
    }


    @Override
    public void dispose() {
        program.dispose();
    }

}
