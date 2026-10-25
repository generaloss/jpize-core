package jpize.lwjgl.glfw.context;

import jpize.context.AbstractJpizeFiles;

import java.io.File;

public class DesktopJpizeFiles extends AbstractJpizeFiles {

    private final String appID;

    public DesktopJpizeFiles(String appID) {
        this.appID = appID;
    }

    @Override
    public File getAppFolder() {
        final String basePath = this.getBasePath();

        final File directory = new File(basePath, appID);
        if(!directory.isDirectory() && !directory.mkdirs())
            throw new IllegalStateException("Cannot create app directory '" + directory + "'.");

        return directory;
    }

    private String getBasePath() {
        final String os = System.getProperty("os.name").toLowerCase(java.util.Locale.ROOT);

        if(os.contains("win")) {
            final String basePath = System.getenv("LOCALAPPDATA");
            if(basePath == null || basePath.isEmpty())
                return System.getProperty("user.home") + "/AppData/Local";
            return basePath;

        } else if(os.contains("mac")) {
            return System.getProperty("user.home") + "/Library/Application Support";

        } else {
            final String basePath = System.getenv("XDG_DATA_HOME");
            if(basePath == null || basePath.isEmpty() || !new File(basePath).isAbsolute())
                return System.getProperty("user.home") + "/.local/share";
            return basePath;
        }
    }

}
