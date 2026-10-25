package jpize.android.resources;

import android.content.res.AssetManager;
import generaloss.resourceflow.resource.InternalResourceSource;
import generaloss.resourceflow.resource.ResourceAccessException;
import generaloss.resourceflow.resource.ResourceNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AndroidAssetsSource extends InternalResourceSource {

    private final AssetManager assets;

    public AndroidAssetsSource(AssetManager assets) {
        super(-100);
        Objects.requireNonNull(assets, "Argument 'assets' cannot be null.");
        this.assets = assets;
    }

    @Override
    public InputStream inStream(Class<?> resourceClass, String path) throws ResourceAccessException {
        try {
            return assets.open(normalize(path));
        } catch (FileNotFoundException e) {
            throw new ResourceNotFoundException();
        } catch (IOException e) {
            throw new ResourceAccessException("Cannot open Android asset: " + path, e);
        }
    }

    @Override
    public boolean exists(Class<?> resourceClass, String path) {
        try (InputStream ignored = assets.open(normalize(path))) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String normalize(String path) {
        while(path.startsWith("/"))
            path = path.substring(1);
        return path;
    }

}