package jpize.util;

import jpize.util.shader.Shader;

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
            vert.append("#version " + version + "\n\n");
            // input attributes
            for(Var attribute: attributes)
                vert.append("in " + attribute.type + " v_" + attribute.name + ";\n");
            vert.append("\n");
            // output attributes
            for(Var attribute: attributes)
                vert.append("out " + attribute.type + " f_" + attribute.name + ";\n");
            vert.append("\n");
            // uniforms
            for(Var uniform: uniforms)
                vert.append("uniform " + uniform.type + " " + uniform.name + ";\n");
            vert.append("\n");
            // main
            vert.append("void main() {\n");
            for(Var attribute: attributes)
                vert.append("   f_" + attribute.name + " = v_" + attribute.name + ";\n");
            for(String line: mainVert)
                vert.append("   " + line + "\n");
            vert.append("}\n");
        }

        { // fragment
            frag.append("#version " + version + "\n\n");
            // input attributes
            for(Var attribute: attributes)
                frag.append("in " + attribute.type + " f_" + attribute.name + ";\n");
            frag.append("\n");
            // uniforms
            for(Var uniform: uniforms)
                frag.append("uniform " + uniform.type + " " + uniform.name + ";\n");
            frag.append("\n");
            // main
            frag.append("void main() {\n");
            for(String line: mainFrag)
                frag.append("   " + line + "\n");
            frag.append("}\n");
        }

        System.out.println("[vertex]:");
        System.out.println(vert);
        System.out.println("[fragment]:");
        System.out.println(frag);

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
