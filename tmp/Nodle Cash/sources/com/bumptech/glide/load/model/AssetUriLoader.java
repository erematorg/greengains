package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;
import org.apache.commons.text.lookup.StringLookupFactory;

public class AssetUriLoader<Data> implements ModelLoader<Uri, Data> {
    private static final String ASSET_PATH_SEGMENT = "android_asset";
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private static final int ASSET_PREFIX_LENGTH = 22;
    private final AssetManager assetManager;
    private final AssetFetcherFactory<Data> factory;

    public interface AssetFetcherFactory<Data> {
        DataFetcher<Data> buildFetcher(AssetManager assetManager, String str);
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<Uri, AssetFileDescriptor>, AssetFetcherFactory<AssetFileDescriptor> {
        private final AssetManager assetManager;

        public FileDescriptorFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        @NonNull
        public ModelLoader<Uri, AssetFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public DataFetcher<AssetFileDescriptor> buildFetcher(AssetManager assetManager2, String str) {
            return new FileDescriptorAssetPathFetcher(assetManager2, str);
        }

        public void teardown() {
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream>, AssetFetcherFactory<InputStream> {
        private final AssetManager assetManager;

        public StreamFactory(AssetManager assetManager2) {
            this.assetManager = assetManager2;
        }

        @NonNull
        public ModelLoader<Uri, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.assetManager, this);
        }

        public DataFetcher<InputStream> buildFetcher(AssetManager assetManager2, String str) {
            return new StreamAssetPathFetcher(assetManager2, str);
        }

        public void teardown() {
        }
    }

    public AssetUriLoader(AssetManager assetManager2, AssetFetcherFactory<Data> assetFetcherFactory) {
        this.assetManager = assetManager2;
        this.factory = assetFetcherFactory;
    }

    public ModelLoader.LoadData<Data> buildLoadData(@NonNull Uri uri, int i3, int i4, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), this.factory.buildFetcher(this.assetManager, uri.toString().substring(ASSET_PREFIX_LENGTH)));
    }

    public boolean handles(@NonNull Uri uri) {
        if (!StringLookupFactory.KEY_FILE.equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !"android_asset".equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }
}
