package com.appsamurai.storyly.exoplayer2.core.source.ads;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.Player;
import com.appsamurai.storyly.exoplayer2.common.source.ads.AdPlaybackState;
import com.appsamurai.storyly.exoplayer2.common.ui.AdViewProvider;
import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsMediaSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import java.io.IOException;

public interface AdsLoader {

    public interface EventListener {
        void onAdClicked() {
        }

        void onAdLoadError(AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec) {
        }

        void onAdPlaybackState(AdPlaybackState adPlaybackState) {
        }

        void onAdTapped() {
        }
    }

    public interface Provider {
        @Nullable
        AdsLoader getAdsLoader(MediaItem.AdsConfiguration adsConfiguration);
    }

    void handlePrepareComplete(AdsMediaSource adsMediaSource, int i3, int i4);

    void handlePrepareError(AdsMediaSource adsMediaSource, int i3, int i4, IOException iOException);

    void release();

    void setPlayer(@Nullable Player player);

    void setSupportedContentTypes(int... iArr);

    void start(AdsMediaSource adsMediaSource, DataSpec dataSpec, Object obj, AdViewProvider adViewProvider, EventListener eventListener);

    void stop(AdsMediaSource adsMediaSource, EventListener eventListener);
}
