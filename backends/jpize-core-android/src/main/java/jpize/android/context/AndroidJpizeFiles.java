package jpize.android.context;

import android.content.Context;
import jpize.context.AbstractJpizeFiles;

import java.io.File;

public class AndroidJpizeFiles extends AbstractJpizeFiles {

    private final Context context;

    public AndroidJpizeFiles(Context context) {
        this.context = context;
    }

    @Override
    public File getAppFolder() {
        return context.getFilesDir();
    }

}
