import jpize.util.res.FileResource;
import jpize.util.res.Resource;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class LwjglGlClassesGenerator {

    private static final Map<String, String> extensions = new HashMap<>() {{
        put("12", "11");
        put("13", "12");
        put("14", "13");
        put("15", "14");
        put("20", "15");
        put("21", "20");
        put("30", "21");
        put("31", "30");
        put("32", "31");
        put("33", "32");
        put("40", "33");
        put("41", "40");
        put("42", "41");
        put("43", "42");
        put("44", "43");
        put("45", "44");
        put("46", "45");
    }};

    public static void main(String[] args) {
        generate("11");
        generate("12");
        generate("13");
        generate("14");
        generate("15");
        generate("20");
        generate("21");
        generate("30");
        generate("31");
        generate("32");
        generate("33");
        generate("40");
        generate("41");
        generate("42");
        generate("43");
        generate("44");
        generate("45");
        generate("46");
    }

    public static void generate(String version) {
        final String classNamePrefix = "LwjglGL";
        final String className = (classNamePrefix + version);

        final String srcFilePath = "src/main/java/jpize/opengl/gl/GL" + version + "I.java";
        final String dstFilePath = "jpize-core-lwjgl/src/main/java/jpize/lwjgl/opengl/";

        final FileResource srcRes = Resource.file(srcFilePath);

        String extension = "";
        if(!version.equals("11"))
            extension = "extends " + classNamePrefix + extensions.get(version) + " ";

        final ClassWriter w = new ClassWriter("jpize.lwjgl.opengl", className, "", extension + "implements GL" + version + "I");
        w.addImport("jpize.opengl.gl.GL" + version + "I");
        w.addImport("org.lwjgl.opengl.GL" + version);
        w.addImport("java.nio.*");

        final String lwjglGL = "GL" + version;

        final Scanner reader = new Scanner(srcRes.inStream());
        while(reader.hasNext()) {
            String line = reader.nextLine();
            if(line.isBlank() || !line.startsWith("    ") || line.startsWith("    int GL_") || line.startsWith("    //"))
                continue;
            line = line.trim();
            line = line.substring(0, line.length() - 1);
            System.out.println(line);

            final String[] spaceParts = line.split(" ");
            if(spaceParts.length == 1)
                continue;

            final String type = spaceParts[0];
            final String[] spaceBraceParts = spaceParts[1].split("\\(");
            final String methodName = spaceBraceParts[0];
            final String[] braceParts = line.split("\\(");
            if(braceParts.length == 1)
                continue;

            final String[] braceSpaceParts = braceParts[1].split(" ");
            final int lastBracePartIdx = (braceSpaceParts.length - 1);
            braceSpaceParts[lastBracePartIdx] = braceSpaceParts[lastBracePartIdx].substring(0, braceSpaceParts[lastBracePartIdx].length() - 1);
            final StringJoiner args = new StringJoiner(", ");
            for(int i = 1; i < braceSpaceParts.length; i += 2){
                String arg = braceSpaceParts[i];
                if(arg.endsWith(","))
                    arg = arg.substring(0, arg.length() - 1);
                args.add(arg);
            }

            System.out.println(type + " | " + methodName + " | " + args);

            if(type.equals("void")) {
                w.addAnnotatedMethod("@Override", "public " + line,
                    lwjglGL + "." + methodName + "(" + args + ");"
                );
            }else{
                w.addAnnotatedMethod("@Override", "public " + line,
                    "return " + lwjglGL + "." + methodName + "(" + args + ");"
                );
            }
        }

        w.write(dstFilePath);
    }

}
