package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.util.List;

class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private ResourceCacheKey currentKey;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int resourceClassIndex = -1;
    private int sourceIdIndex;
    private Key sourceKey;

    public ResourceCacheGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    public void cancel() {
        ModelLoader.LoadData<?> loadData2 = this.loadData;
        if (loadData2 != null) {
            loadData2.fetcher.cancel();
        }
    }

    public void onDataReady(Object obj) {
        this.cb.onDataFetcherReady(this.sourceKey, obj, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    public void onLoadFailed(@NonNull Exception exc) {
        this.cb.onDataFetcherFailed(this.currentKey, exc, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }

    public boolean startNext() {
        GlideTrace.beginSection("ResourceCacheGenerator.startNext");
        try {
            List<Key> cacheKeys = this.helper.getCacheKeys();
            boolean z2 = false;
            if (cacheKeys.isEmpty()) {
                GlideTrace.endSection();
                return false;
            }
            List<Class<?>> registeredResourceClasses = this.helper.getRegisteredResourceClasses();
            if (!registeredResourceClasses.isEmpty()) {
                while (true) {
                    if (this.modelLoaders != null) {
                        if (hasNextModelLoader()) {
                            this.loadData = null;
                            while (!z2 && hasNextModelLoader()) {
                                List<ModelLoader<File, ?>> list = this.modelLoaders;
                                int i3 = this.modelLoaderIndex;
                                this.modelLoaderIndex = i3 + 1;
                                this.loadData = list.get(i3).buildLoadData(this.cacheFile, this.helper.getWidth(), this.helper.getHeight(), this.helper.getOptions());
                                if (this.loadData != null && this.helper.hasLoadPath(this.loadData.fetcher.getDataClass())) {
                                    this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                                    z2 = true;
                                }
                            }
                            GlideTrace.endSection();
                            return z2;
                        }
                    }
                    int i4 = this.resourceClassIndex + 1;
                    this.resourceClassIndex = i4;
                    if (i4 >= registeredResourceClasses.size()) {
                        int i5 = this.sourceIdIndex + 1;
                        this.sourceIdIndex = i5;
                        if (i5 >= cacheKeys.size()) {
                            GlideTrace.endSection();
                            return false;
                        }
                        this.resourceClassIndex = 0;
                    }
                    Key key = cacheKeys.get(this.sourceIdIndex);
                    Class cls = registeredResourceClasses.get(this.resourceClassIndex);
                    this.currentKey = new ResourceCacheKey(this.helper.getArrayPool(), key, this.helper.getSignature(), this.helper.getWidth(), this.helper.getHeight(), this.helper.getTransformation(cls), cls, this.helper.getOptions());
                    File file = this.helper.getDiskCache().get(this.currentKey);
                    this.cacheFile = file;
                    if (file != null) {
                        this.sourceKey = key;
                        this.modelLoaders = this.helper.getModelLoaders(file);
                        this.modelLoaderIndex = 0;
                    }
                }
            } else if (File.class.equals(this.helper.getTranscodeClass())) {
                GlideTrace.endSection();
                return false;
            } else {
                throw new IllegalStateException("Failed to find any load path from " + this.helper.getModelClass() + " to " + this.helper.getTranscodeClass());
            }
        } catch (Throwable th) {
            GlideTrace.endSection();
            throw th;
        }
    }
}
