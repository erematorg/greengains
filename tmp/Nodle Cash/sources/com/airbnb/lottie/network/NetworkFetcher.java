package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class NetworkFetcher {
    @NonNull
    private final LottieNetworkFetcher fetcher;
    @Nullable
    private final NetworkCache networkCache;

    public NetworkFetcher(@Nullable NetworkCache networkCache2, @NonNull LottieNetworkFetcher lottieNetworkFetcher) {
        this.networkCache = networkCache2;
        this.fetcher = lottieNetworkFetcher;
    }

    @WorkerThread
    @Nullable
    private LottieComposition fetchFromCache(Context context, @NonNull String str, @Nullable String str2) {
        NetworkCache networkCache2;
        Pair<FileExtension, InputStream> fetch;
        if (str2 == null || (networkCache2 = this.networkCache) == null || (fetch = networkCache2.fetch(str)) == null) {
            return null;
        }
        FileExtension fileExtension = (FileExtension) fetch.first;
        InputStream inputStream = (InputStream) fetch.second;
        LottieResult<LottieComposition> fromZipStreamSync = fileExtension == FileExtension.ZIP ? LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), str2) : LottieCompositionFactory.fromJsonInputStreamSync(inputStream, str2);
        if (fromZipStreamSync.getValue() != null) {
            return fromZipStreamSync.getValue();
        }
        return null;
    }

    @WorkerThread
    @NonNull
    private LottieResult<LottieComposition> fetchFromNetwork(Context context, @NonNull String str, @Nullable String str2) {
        Logger.debug("Fetching " + str);
        LottieFetchResult lottieFetchResult = null;
        try {
            lottieFetchResult = this.fetcher.fetchSync(str);
            if (lottieFetchResult.isSuccessful()) {
                LottieResult<LottieComposition> fromInputStream = fromInputStream(context, str, lottieFetchResult.bodyByteStream(), lottieFetchResult.contentType(), str2);
                StringBuilder sb = new StringBuilder("Completed fetch from network. Success: ");
                sb.append(fromInputStream.getValue() != null);
                Logger.debug(sb.toString());
                try {
                    lottieFetchResult.close();
                } catch (IOException e3) {
                    Logger.warning("LottieFetchResult close failed ", e3);
                }
                return fromInputStream;
            }
            LottieResult<LottieComposition> lottieResult = new LottieResult<>((Throwable) new IllegalArgumentException(lottieFetchResult.error()));
            try {
                lottieFetchResult.close();
            } catch (IOException e4) {
                Logger.warning("LottieFetchResult close failed ", e4);
            }
            return lottieResult;
        } catch (Exception e5) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>((Throwable) e5);
            if (lottieFetchResult != null) {
                try {
                    lottieFetchResult.close();
                } catch (IOException e6) {
                    Logger.warning("LottieFetchResult close failed ", e6);
                }
            }
            return lottieResult2;
        } catch (Throwable th) {
            if (lottieFetchResult != null) {
                try {
                    lottieFetchResult.close();
                } catch (IOException e7) {
                    Logger.warning("LottieFetchResult close failed ", e7);
                }
            }
            throw th;
        }
    }

    @NonNull
    private LottieResult<LottieComposition> fromInputStream(Context context, @NonNull String str, @NonNull InputStream inputStream, @Nullable String str2, @Nullable String str3) throws IOException {
        LottieResult<LottieComposition> lottieResult;
        FileExtension fileExtension;
        NetworkCache networkCache2;
        if (str2 == null) {
            str2 = "application/json";
        }
        if (str2.contains("application/zip") || str2.contains("application/x-zip") || str2.contains("application/x-zip-compressed") || str.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Handling zip response.");
            FileExtension fileExtension2 = FileExtension.ZIP;
            lottieResult = fromZipStream(context, str, inputStream, str3);
            fileExtension = fileExtension2;
        } else {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            lottieResult = fromJsonStream(str, inputStream, str3);
        }
        if (!(str3 == null || lottieResult.getValue() == null || (networkCache2 = this.networkCache) == null)) {
            networkCache2.renameTempFile(str, fileExtension);
        }
        return lottieResult;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.networkCache;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.airbnb.lottie.LottieResult<com.airbnb.lottie.LottieComposition> fromJsonStream(@androidx.annotation.NonNull java.lang.String r1, @androidx.annotation.NonNull java.io.InputStream r2, @androidx.annotation.Nullable java.lang.String r3) throws java.io.IOException {
        /*
            r0 = this;
            if (r3 == 0) goto L_0x001b
            com.airbnb.lottie.network.NetworkCache r0 = r0.networkCache
            if (r0 != 0) goto L_0x0007
            goto L_0x001b
        L_0x0007:
            com.airbnb.lottie.network.FileExtension r3 = com.airbnb.lottie.network.FileExtension.JSON
            java.io.File r0 = r0.writeTempCacheFile(r1, r2, r3)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.lang.String r0 = r0.getAbsolutePath()
            r2.<init>(r0)
            com.airbnb.lottie.LottieResult r0 = com.airbnb.lottie.LottieCompositionFactory.fromJsonInputStreamSync(r2, r1)
            return r0
        L_0x001b:
            r0 = 0
            com.airbnb.lottie.LottieResult r0 = com.airbnb.lottie.LottieCompositionFactory.fromJsonInputStreamSync(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkFetcher.fromJsonStream(java.lang.String, java.io.InputStream, java.lang.String):com.airbnb.lottie.LottieResult");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r0.networkCache;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.airbnb.lottie.LottieResult<com.airbnb.lottie.LottieComposition> fromZipStream(android.content.Context r1, @androidx.annotation.NonNull java.lang.String r2, @androidx.annotation.NonNull java.io.InputStream r3, @androidx.annotation.Nullable java.lang.String r4) throws java.io.IOException {
        /*
            r0 = this;
            if (r4 == 0) goto L_0x001c
            com.airbnb.lottie.network.NetworkCache r0 = r0.networkCache
            if (r0 != 0) goto L_0x0007
            goto L_0x001c
        L_0x0007:
            com.airbnb.lottie.network.FileExtension r4 = com.airbnb.lottie.network.FileExtension.ZIP
            java.io.File r0 = r0.writeTempCacheFile(r2, r3, r4)
            java.util.zip.ZipInputStream r3 = new java.util.zip.ZipInputStream
            java.io.FileInputStream r4 = new java.io.FileInputStream
            r4.<init>(r0)
            r3.<init>(r4)
            com.airbnb.lottie.LottieResult r0 = com.airbnb.lottie.LottieCompositionFactory.fromZipStreamSync((android.content.Context) r1, (java.util.zip.ZipInputStream) r3, (java.lang.String) r2)
            return r0
        L_0x001c:
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            r0.<init>(r3)
            r2 = 0
            com.airbnb.lottie.LottieResult r0 = com.airbnb.lottie.LottieCompositionFactory.fromZipStreamSync((android.content.Context) r1, (java.util.zip.ZipInputStream) r0, (java.lang.String) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.network.NetworkFetcher.fromZipStream(android.content.Context, java.lang.String, java.io.InputStream, java.lang.String):com.airbnb.lottie.LottieResult");
    }

    @WorkerThread
    @NonNull
    public LottieResult<LottieComposition> fetchSync(Context context, @NonNull String str, @Nullable String str2) {
        LottieComposition fetchFromCache = fetchFromCache(context, str, str2);
        if (fetchFromCache != null) {
            return new LottieResult<>(fetchFromCache);
        }
        Logger.debug("Animation for " + str + " not found in cache. Fetching from network.");
        return fetchFromNetwork(context, str, str2);
    }
}
