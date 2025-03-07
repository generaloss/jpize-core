package jpize.util.shader;

import jpize.opengl.shader.Shader;

import java.util.ArrayList;
import java.util.List;

public class ShaderBuilder {

    private int version = 330;
    private final List<Var> attributes;
    private final List<Var> uniforms;
    private String[] mainVert, mainFrag;

    private ShaderBuilder() {
        this.attributes = new ArrayList<>();
        this.uniforms = new ArrayList<>();
    }


    public ShaderBuilder version(int version) {
        this.version = version;
        return this;
    }

    public ShaderBuilder attribute(String type, String name) {
        attributes.add(new Var(type, name));
        return this;
    }

    public ShaderBuilder uniform(String type, String name) {
        uniforms.add(new Var(type, name));
        return this;
    }

    public ShaderBuilder mainVert(String... code) {
        this.mainVert = code;
        return this;
    }

    public ShaderBuilder mainFrag(String... code) {
        this.mainFrag = code;
        return this;
    }


    public Shader build() {
        final StringBuilder vert = new StringBuilder();
        final StringBuilder frag = new StringBuilder();

        { // vertex
            // version
            vert.append("#version ")
                .append(version)
                .append("\n\n");

            // input attributes
            for(Var attribute: attributes) {
                vert.append("in ")
                    .append(attribute.type)
                    .append(" v_")
                    .append(attribute.name)
                    .append(";\n");
            }
            vert.append("\n");
            // output attributes
            for(Var attribute: attributes) {
                vert.append("out ")
                    .append(attribute.type)
                    .append(" f_")
                    .append(attribute.name)
                    .append(";\n");
            }
            vert.append("\n");
            // uniforms
            for(Var uniform: uniforms) {
                vert.append("uniform ")
                    .append(uniform.type)
                    .append(" ")
                    .append(uniform.name)
                    .append(";\n");
            }
            vert.append("\n");
            // main
            vert.append("void main() {\n");
            for(Var attribute: attributes){
                vert.append("   f_")
                    .append(attribute.name)
                    .append(" = v_")
                    .append(attribute.name)
                    .append(";\n");
            }
            for(String line: mainVert) {
                vert.append("   ")
                    .append(line)
                    .append("\n");
            }
            vert.append("}\n");
        }

        { // fragment
            frag.append("#version ")
                .append(version)
                .append("\n\n");

            // input attributes
            for(Var attribute: attributes) {
                frag.append("in ")
                    .append(attribute.type)
                    .append(" f_")
                    .append(attribute.name)
                    .append(";\n");
            }
            frag.append("\n");
            // uniforms
            for(Var uniform: uniforms) {
                frag.append("uniform ")
                    .append(uniform.type)
                    .append(" ")
                    .append(uniform.name)
                    .append(";\n");
            }
            frag.append("\n");
            // main
            frag.append("void main() {\n");
            for(String line: mainFrag) {
                frag.append("   ")
                    .append(line)
                    .append("\n");
            }
            frag.append("}\n");
        }

        return new Shader(vert.toString(), frag.toString());
    }


    public static ShaderBuilder create() {
        return new ShaderBuilder();
    }


    private static class Var {
        public Var(String type, String name) {
            this.type = type;
            this.name = name;
        }
        public final String type;
        public final String name;
    }

}
