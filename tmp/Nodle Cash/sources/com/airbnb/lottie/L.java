package com.airbnb.lottie;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.utils.LottieTrace;
import java.io.File;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public class L {
    public static boolean DBG = false;
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider = null;
    private static AsyncUpdates defaultAsyncUpdates = AsyncUpdates.AUTOMATIC;
    private static boolean disablePathInterpolatorCache = true;
    private static LottieNetworkFetcher fetcher = null;
    private static ThreadLocal<LottieTrace> lottieTrace = null;
    private static volatile NetworkCache networkCache = null;
    private static boolean networkCacheEnabled = true;
    private static volatile NetworkFetcher networkFetcher = null;
    private static boolean traceEnabled = false;

    private L() {
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            getTrace().beginSection(str);
        }
    }

    public static float endSection(String str) {
        if (!traceEnabled) {
            return 0.0f;
        }
        return getTrace().endSection(str);
    }

    public static AsyncUpdates getDefaultAsyncUpdates() {
        return defaultAsyncUpdates;
    }

    public static boolean getDisablePathInterpolatorCache() {
        return disablePathInterpolatorCache;
    }

    private static LottieTrace getTrace() {
        LottieTrace lottieTrace2 = lottieTrace.get();
        if (lottieTrace2 != null) {
            return lottieTrace2;
        }
        LottieTrace lottieTrace3 = new LottieTrace();
        lottieTrace.set(lottieTrace3);
        return lottieTrace3;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ File lambda$networkCache$0(Context context) {
        return new File(context.getCacheDir(), "lottie_network_cache");
    }

    @Nullable
    public static NetworkCache networkCache(@NonNull Context context) {
        if (!networkCacheEnabled) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        NetworkCache networkCache2 = networkCache;
        if (networkCache2 == null) {
            synchronized (NetworkCache.class) {
                try {
                    networkCache2 = networkCache;
                    if (networkCache2 == null) {
                        LottieNetworkCacheProvider lottieNetworkCacheProvider = cacheProvider;
                        if (lottieNetworkCacheProvider == null) {
                            lottieNetworkCacheProvider = new a(applicationContext);
                        }
                        networkCache2 = new NetworkCache(lottieNetworkCacheProvider);
                        networkCache = networkCache2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return networkCache2;
    }

    @NonNull
    public static NetworkFetcher networkFetcher(@NonNull Context context) {
        NetworkFetcher networkFetcher2 = networkFetcher;
        if (networkFetcher2 == null) {
            synchronized (NetworkFetcher.class) {
                try {
                    networkFetcher2 = networkFetcher;
                    if (networkFetcher2 == null) {
                        NetworkCache networkCache2 = networkCache(context);
                        LottieNetworkFetcher lottieNetworkFetcher = fetcher;
                        if (lottieNetworkFetcher == null) {
                            lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                        }
                        networkFetcher2 = new NetworkFetcher(networkCache2, lottieNetworkFetcher);
                        networkFetcher = networkFetcher2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return networkFetcher2;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        LottieNetworkCacheProvider lottieNetworkCacheProvider2 = cacheProvider;
        if (lottieNetworkCacheProvider2 != null || lottieNetworkCacheProvider != null) {
            if (lottieNetworkCacheProvider2 == null || !lottieNetworkCacheProvider2.equals(lottieNetworkCacheProvider)) {
                cacheProvider = lottieNetworkCacheProvider;
                networkCache = null;
            }
        }
    }

    public static void setDefaultAsyncUpdates(AsyncUpdates asyncUpdates) {
        defaultAsyncUpdates = asyncUpdates;
    }

    public static void setDisablePathInterpolatorCache(boolean z2) {
        disablePathInterpolatorCache = z2;
    }

    public static void setFetcher(LottieNetworkFetcher lottieNetworkFetcher) {
        LottieNetworkFetcher lottieNetworkFetcher2 = fetcher;
        if (lottieNetworkFetcher2 != null || lottieNetworkFetcher != null) {
            if (lottieNetworkFetcher2 == null || !lottieNetworkFetcher2.equals(lottieNetworkFetcher)) {
                fetcher = lottieNetworkFetcher;
                networkFetcher = null;
            }
        }
    }

    public static void setNetworkCacheEnabled(boolean z2) {
        networkCacheEnabled = z2;
    }

    public static void setTraceEnabled(boolean z2) {
        if (traceEnabled != z2) {
            traceEnabled = z2;
            if (z2 && lottieTrace == null) {
                lottieTrace = new ThreadLocal<>();
            }
        }
    }
}
