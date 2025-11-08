package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T> implements DataFetcher<T> {
    private static final String TAG = "LocalUriFetcher";
    private final ContentResolver contentResolver;
    private T data;
    private final Uri uri;

    public LocalUriFetcher(ContentResolver contentResolver2, Uri uri2) {
        this.contentResolver = contentResolver2;
        this.uri = uri2;
    }

    public void cancel() {
    }

    public void cleanup() {
        T t2 = this.data;
        if (t2 != null) {
            try {
                close(t2);
            } catch (IOException unused) {
            }
        }
    }

    public abstract void close(T t2) throws IOException;

    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }

    public final void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super T> dataCallback) {
        try {
            T loadResource = loadResource(this.uri, this.contentResolver);
            this.data = loadResource;
            dataCallback.onDataReady(loadResource);
        } catch (FileNotFoundException e3) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Failed to open Uri", e3);
            }
            dataCallback.onLoadFailed(e3);
        }
    }

    public abstract T loadResource(Uri uri2, ContentResolver contentResolver2) throws FileNotFoundException;
}
