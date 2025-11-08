package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.IOException;
import java.util.List;

class SourceGenerator implements DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {
    private static final String TAG = "SourceGenerator";
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private volatile Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private volatile int loadDataListIndex;
    private volatile DataCacheKey originalKey;
    private volatile DataCacheGenerator sourceCacheGenerator;

    public SourceGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean cacheData(java.lang.Object r13) throws java.io.IOException {
        /*
            r12 = this;
            java.lang.String r0 = "SourceGenerator"
            java.lang.String r1 = "Attempt to write: "
            java.lang.String r2 = "Finished encoding source to cache, key: "
            long r3 = com.bumptech.glide.util.LogTime.getLogTime()
            r5 = 0
            com.bumptech.glide.load.engine.DecodeHelper<?> r6 = r12.helper     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.data.DataRewinder r6 = r6.getRewinder(r13)     // Catch:{ all -> 0x0071 }
            java.lang.Object r7 = r6.rewindAndGet()     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DecodeHelper<?> r8 = r12.helper     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.Encoder r8 = r8.getSourceEncoder(r7)     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DataCacheWriter r9 = new com.bumptech.glide.load.engine.DataCacheWriter     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DecodeHelper<?> r10 = r12.helper     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.Options r10 = r10.getOptions()     // Catch:{ all -> 0x0071 }
            r9.<init>(r8, r7, r10)     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DataCacheKey r7 = new com.bumptech.glide.load.engine.DataCacheKey     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r10 = r12.loadData     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.Key r10 = r10.sourceKey     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DecodeHelper<?> r11 = r12.helper     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.Key r11 = r11.getSignature()     // Catch:{ all -> 0x0071 }
            r7.<init>(r10, r11)     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DecodeHelper<?> r10 = r12.helper     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.cache.DiskCache r10 = r10.getDiskCache()     // Catch:{ all -> 0x0071 }
            r10.put(r7, r9)     // Catch:{ all -> 0x0071 }
            r9 = 2
            boolean r9 = android.util.Log.isLoggable(r0, r9)     // Catch:{ all -> 0x0071 }
            java.lang.String r11 = ", data: "
            if (r9 == 0) goto L_0x0073
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r9.<init>(r2)     // Catch:{ all -> 0x0071 }
            r9.append(r7)     // Catch:{ all -> 0x0071 }
            r9.append(r11)     // Catch:{ all -> 0x0071 }
            r9.append(r13)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = ", encoder: "
            r9.append(r2)     // Catch:{ all -> 0x0071 }
            r9.append(r8)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = ", duration: "
            r9.append(r2)     // Catch:{ all -> 0x0071 }
            double r2 = com.bumptech.glide.util.LogTime.getElapsedMillis(r3)     // Catch:{ all -> 0x0071 }
            r9.append(r2)     // Catch:{ all -> 0x0071 }
            java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x0071 }
            android.util.Log.v(r0, r2)     // Catch:{ all -> 0x0071 }
            goto L_0x0073
        L_0x0071:
            r13 = move-exception
            goto L_0x00d9
        L_0x0073:
            java.io.File r2 = r10.get(r7)     // Catch:{ all -> 0x0071 }
            r3 = 1
            if (r2 == 0) goto L_0x0095
            r12.originalKey = r7     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DataCacheGenerator r13 = new com.bumptech.glide.load.engine.DataCacheGenerator     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r12.loadData     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.Key r0 = r0.sourceKey     // Catch:{ all -> 0x0071 }
            java.util.List r0 = java.util.Collections.singletonList(r0)     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DecodeHelper<?> r1 = r12.helper     // Catch:{ all -> 0x0071 }
            r13.<init>(r0, r1, r12)     // Catch:{ all -> 0x0071 }
            r12.sourceCacheGenerator = r13     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r12 = r12.loadData
            com.bumptech.glide.load.data.DataFetcher<Data> r12 = r12.fetcher
            r12.cleanup()
            return r3
        L_0x0095:
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch:{ all -> 0x0071 }
            if (r2 == 0) goto L_0x00b8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r2.<init>(r1)     // Catch:{ all -> 0x0071 }
            com.bumptech.glide.load.engine.DataCacheKey r1 = r12.originalKey     // Catch:{ all -> 0x0071 }
            r2.append(r1)     // Catch:{ all -> 0x0071 }
            r2.append(r11)     // Catch:{ all -> 0x0071 }
            r2.append(r13)     // Catch:{ all -> 0x0071 }
            java.lang.String r13 = " to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly..."
            r2.append(r13)     // Catch:{ all -> 0x0071 }
            java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x0071 }
            android.util.Log.d(r0, r13)     // Catch:{ all -> 0x0071 }
        L_0x00b8:
            com.bumptech.glide.load.engine.DataFetcherGenerator$FetcherReadyCallback r13 = r12.cb     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r12.loadData     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.Key r7 = r0.sourceKey     // Catch:{ all -> 0x00d7 }
            java.lang.Object r8 = r6.rewindAndGet()     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r12.loadData     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.data.DataFetcher<Data> r9 = r0.fetcher     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r12.loadData     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.data.DataFetcher<Data> r0 = r0.fetcher     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.DataSource r10 = r0.getDataSource()     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r0 = r12.loadData     // Catch:{ all -> 0x00d7 }
            com.bumptech.glide.load.Key r11 = r0.sourceKey     // Catch:{ all -> 0x00d7 }
            r6 = r13
            r6.onDataFetcherReady(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00d7 }
            return r5
        L_0x00d7:
            r13 = move-exception
            r5 = r3
        L_0x00d9:
            if (r5 != 0) goto L_0x00e2
            com.bumptech.glide.load.model.ModelLoader$LoadData<?> r12 = r12.loadData
            com.bumptech.glide.load.data.DataFetcher<Data> r12 = r12.fetcher
            r12.cleanup()
        L_0x00e2:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.SourceGenerator.cacheData(java.lang.Object):boolean");
    }

    private boolean hasNextModelLoader() {
        return this.loadDataListIndex < this.helper.getLoadData().size();
    }

    private void startNextLoad(final ModelLoader.LoadData<?> loadData2) {
        this.loadData.fetcher.loadData(this.helper.getPriority(), new DataFetcher.DataCallback<Object>() {
            public void onDataReady(@Nullable Object obj) {
                if (SourceGenerator.this.isCurrentRequest(loadData2)) {
                    SourceGenerator.this.onDataReadyInternal(loadData2, obj);
                }
            }

            public void onLoadFailed(@NonNull Exception exc) {
                if (SourceGenerator.this.isCurrentRequest(loadData2)) {
                    SourceGenerator.this.onLoadFailedInternal(loadData2, exc);
                }
            }
        });
    }

    public void cancel() {
        ModelLoader.LoadData<?> loadData2 = this.loadData;
        if (loadData2 != null) {
            loadData2.fetcher.cancel();
        }
    }

    public boolean isCurrentRequest(ModelLoader.LoadData<?> loadData2) {
        ModelLoader.LoadData<?> loadData3 = this.loadData;
        return loadData3 != null && loadData3 == loadData2;
    }

    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.cb.onDataFetcherFailed(key, exc, dataFetcher, this.loadData.fetcher.getDataSource());
    }

    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.cb.onDataFetcherReady(key, obj, dataFetcher, this.loadData.fetcher.getDataSource(), key);
    }

    public void onDataReadyInternal(ModelLoader.LoadData<?> loadData2, Object obj) {
        DiskCacheStrategy diskCacheStrategy = this.helper.getDiskCacheStrategy();
        if (obj == null || !diskCacheStrategy.isDataCacheable(loadData2.fetcher.getDataSource())) {
            DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.cb;
            Key key = loadData2.sourceKey;
            DataFetcher<Data> dataFetcher = loadData2.fetcher;
            fetcherReadyCallback.onDataFetcherReady(key, obj, dataFetcher, dataFetcher.getDataSource(), this.originalKey);
            return;
        }
        this.dataToCache = obj;
        this.cb.reschedule();
    }

    public void onLoadFailedInternal(ModelLoader.LoadData<?> loadData2, @NonNull Exception exc) {
        DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.cb;
        DataCacheKey dataCacheKey = this.originalKey;
        DataFetcher<Data> dataFetcher = loadData2.fetcher;
        fetcherReadyCallback.onDataFetcherFailed(dataCacheKey, exc, dataFetcher, dataFetcher.getDataSource());
    }

    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    public boolean startNext() {
        if (this.dataToCache != null) {
            Object obj = this.dataToCache;
            this.dataToCache = null;
            try {
                if (!cacheData(obj)) {
                    return true;
                }
            } catch (IOException e3) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Failed to properly rewind or write data to cache", e3);
                }
            }
        }
        if (this.sourceCacheGenerator != null && this.sourceCacheGenerator.startNext()) {
            return true;
        }
        this.sourceCacheGenerator = null;
        this.loadData = null;
        boolean z2 = false;
        while (!z2 && hasNextModelLoader()) {
            List<ModelLoader.LoadData<?>> loadData2 = this.helper.getLoadData();
            int i3 = this.loadDataListIndex;
            this.loadDataListIndex = i3 + 1;
            this.loadData = loadData2.get(i3);
            if (this.loadData != null && (this.helper.getDiskCacheStrategy().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.hasLoadPath(this.loadData.fetcher.getDataClass()))) {
                startNextLoad(this.loadData);
                z2 = true;
            }
        }
        return z2;
    }
}
