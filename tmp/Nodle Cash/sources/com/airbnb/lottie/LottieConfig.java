package com.airbnb.lottie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import java.io.File;

public class LottieConfig {
    @Nullable
    final LottieNetworkCacheProvider cacheProvider;
    final AsyncUpdates defaultAsyncUpdates;
    final boolean disablePathInterpolatorCache;
    final boolean enableNetworkCache;
    final boolean enableSystraceMarkers;
    @Nullable
    final LottieNetworkFetcher networkFetcher;

    public static final class Builder {
        @Nullable
        private LottieNetworkCacheProvider cacheProvider;
        private AsyncUpdates defaultAsyncUpdates = AsyncUpdates.AUTOMATIC;
        private boolean disablePathInterpolatorCache = true;
        private boolean enableNetworkCache = true;
        private boolean enableSystraceMarkers = false;
        @Nullable
        private LottieNetworkFetcher networkFetcher;

        @NonNull
        public LottieConfig build() {
            return new LottieConfig(this.networkFetcher, this.cacheProvider, this.enableSystraceMarkers, this.enableNetworkCache, this.disablePathInterpolatorCache, this.defaultAsyncUpdates);
        }

        @NonNull
        public Builder setDefaultAsyncUpdates(AsyncUpdates asyncUpdates) {
            this.defaultAsyncUpdates = asyncUpdates;
            return this;
        }

        @NonNull
        public Builder setDisablePathInterpolatorCache(boolean z2) {
            this.disablePathInterpolatorCache = z2;
            return this;
        }

        @NonNull
        public Builder setEnableNetworkCache(boolean z2) {
            this.enableNetworkCache = z2;
            return this;
        }

        @NonNull
        public Builder setEnableSystraceMarkers(boolean z2) {
            this.enableSystraceMarkers = z2;
            return this;
        }

        @NonNull
        public Builder setNetworkCacheDir(@NonNull final File file) {
            if (this.cacheProvider == null) {
                this.cacheProvider = new LottieNetworkCacheProvider() {
                    @NonNull
                    public File getCacheDir() {
                        if (file.isDirectory()) {
                            return file;
                        }
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                };
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        @NonNull
        public Builder setNetworkCacheProvider(@NonNull final LottieNetworkCacheProvider lottieNetworkCacheProvider) {
            if (this.cacheProvider == null) {
                this.cacheProvider = new LottieNetworkCacheProvider() {
                    @NonNull
                    public File getCacheDir() {
                        File cacheDir = lottieNetworkCacheProvider.getCacheDir();
                        if (cacheDir.isDirectory()) {
                            return cacheDir;
                        }
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                };
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        @NonNull
        public Builder setNetworkFetcher(@NonNull LottieNetworkFetcher lottieNetworkFetcher) {
            this.networkFetcher = lottieNetworkFetcher;
            return this;
        }
    }

    private LottieConfig(@Nullable LottieNetworkFetcher lottieNetworkFetcher, @Nullable LottieNetworkCacheProvider lottieNetworkCacheProvider, boolean z2, boolean z3, boolean z4, AsyncUpdates asyncUpdates) {
        this.networkFetcher = lottieNetworkFetcher;
        this.cacheProvider = lottieNetworkCacheProvider;
        this.enableSystraceMarkers = z2;
        this.enableNetworkCache = z3;
        this.disablePathInterpolatorCache = z4;
        this.defaultAsyncUpdates = asyncUpdates;
    }
}
