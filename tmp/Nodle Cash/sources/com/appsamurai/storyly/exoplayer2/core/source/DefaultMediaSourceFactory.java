package com.appsamurai.storyly.exoplayer2.core.source;

import android.content.Context;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.ui.AdViewProvider;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSessionManagerProvider;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ProgressiveMediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.SingleSampleMediaSource;
import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsLoader;
import com.appsamurai.storyly.exoplayer2.core.source.ads.AdsMediaSource;
import com.appsamurai.storyly.exoplayer2.core.text.SubtitleDecoderFactory;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DefaultDataSource;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.DefaultExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.Extractor;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorInput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorOutput;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.ExtractorsFactory;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.PositionHolder;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.SeekMap;
import com.appsamurai.storyly.exoplayer2.extractor.extractor.TrackOutput;
import com.appsamurai.storyly.exoplayer2.extractor.text.SubtitleExtractor;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class DefaultMediaSourceFactory implements MediaSourceFactory {
    private static final String TAG = "DMediaSourceFactory";
    @Nullable
    private AdViewProvider adViewProvider;
    @Nullable
    private AdsLoader.Provider adsLoaderProvider;
    private DataSource.Factory dataSourceFactory;
    private final DelegateFactoryLoader delegateFactoryLoader;
    private long liveMaxOffsetMs;
    private float liveMaxSpeed;
    private long liveMinOffsetMs;
    private float liveMinSpeed;
    private long liveTargetOffsetMs;
    @Nullable
    private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    @Nullable
    private MediaSource.Factory serverSideAdInsertionMediaSourceFactory;
    private boolean useProgressiveMediaSourceForSubtitles;

    @Deprecated
    public interface AdsLoaderProvider extends AdsLoader.Provider {
    }

    public static final class DelegateFactoryLoader {
        private DataSource.Factory dataSourceFactory;
        @Nullable
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private final ExtractorsFactory extractorsFactory;
        @Nullable
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private final Map<Integer, MediaSource.Factory> mediaSourceFactories = new HashMap();
        private final Map<Integer, Supplier<MediaSource.Factory>> mediaSourceFactorySuppliers = new HashMap();
        private final Set<Integer> supportedTypes = new HashSet();

        public DelegateFactoryLoader(ExtractorsFactory extractorsFactory2) {
            this.extractorsFactory = extractorsFactory2;
        }

        private void ensureAllSuppliersAreLoaded() {
            maybeLoadSupplier(0);
            maybeLoadSupplier(1);
            maybeLoadSupplier(2);
            maybeLoadSupplier(3);
            maybeLoadSupplier(4);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$maybeLoadSupplier$0(Class cls) {
            return DefaultMediaSourceFactory.newInstance(cls, (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$maybeLoadSupplier$1(Class cls) {
            return DefaultMediaSourceFactory.newInstance(cls, (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$maybeLoadSupplier$2(Class cls) {
            return DefaultMediaSourceFactory.newInstance(cls, (DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ MediaSource.Factory lambda$maybeLoadSupplier$4() {
            return new ProgressiveMediaSource.Factory((DataSource.Factory) Assertions.checkNotNull(this.dataSourceFactory), this.extractorsFactory);
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0080  */
        @androidx.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.source.MediaSource.Factory> maybeLoadSupplier(int r6) {
            /*
                r5 = this;
                r0 = 0
                r1 = 2
                r2 = 1
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory>> r3 = r5.mediaSourceFactorySuppliers
                java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
                boolean r3 = r3.containsKey(r4)
                if (r3 == 0) goto L_0x001c
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory>> r5 = r5.mediaSourceFactorySuppliers
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.Object r5 = r5.get(r6)
                com.google.common.base.Supplier r5 = (com.google.common.base.Supplier) r5
                return r5
            L_0x001c:
                java.lang.Class<com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory> r3 = com.appsamurai.storyly.exoplayer2.core.source.MediaSource.Factory.class
                r4 = 0
                if (r6 == 0) goto L_0x0065
                if (r6 == r2) goto L_0x0054
                if (r6 == r1) goto L_0x0044
                r1 = 3
                if (r6 == r1) goto L_0x0033
                r0 = 4
                if (r6 == r0) goto L_0x002c
                goto L_0x0075
            L_0x002c:
                com.appsamurai.storyly.exoplayer2.core.source.e r0 = new com.appsamurai.storyly.exoplayer2.core.source.e     // Catch:{ ClassNotFoundException -> 0x0075 }
                r0.<init>(r5, r2)     // Catch:{ ClassNotFoundException -> 0x0075 }
                r4 = r0
                goto L_0x0075
            L_0x0033:
                java.lang.String r1 = "com.google.android.exoplayer2.source.rtsp.RtspMediaSource$Factory"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0075 }
                java.lang.Class r1 = r1.asSubclass(r3)     // Catch:{ ClassNotFoundException -> 0x0075 }
                com.appsamurai.storyly.exoplayer2.core.source.e r2 = new com.appsamurai.storyly.exoplayer2.core.source.e     // Catch:{ ClassNotFoundException -> 0x0075 }
                r2.<init>(r1, r0)     // Catch:{ ClassNotFoundException -> 0x0075 }
            L_0x0042:
                r4 = r2
                goto L_0x0075
            L_0x0044:
                java.lang.String r0 = "com.google.android.exoplayer2.source.hls.HlsMediaSource$Factory"
                java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0075 }
                java.lang.Class r0 = r0.asSubclass(r3)     // Catch:{ ClassNotFoundException -> 0x0075 }
                com.appsamurai.storyly.exoplayer2.core.source.d r2 = new com.appsamurai.storyly.exoplayer2.core.source.d     // Catch:{ ClassNotFoundException -> 0x0075 }
                r2.<init>(r5, r0, r1)     // Catch:{ ClassNotFoundException -> 0x0075 }
                goto L_0x0042
            L_0x0054:
                java.lang.String r0 = "com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource$Factory"
                java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0075 }
                java.lang.Class r0 = r0.asSubclass(r3)     // Catch:{ ClassNotFoundException -> 0x0075 }
                com.appsamurai.storyly.exoplayer2.core.source.d r1 = new com.appsamurai.storyly.exoplayer2.core.source.d     // Catch:{ ClassNotFoundException -> 0x0075 }
                r1.<init>(r5, r0, r2)     // Catch:{ ClassNotFoundException -> 0x0075 }
                r4 = r1
                goto L_0x0075
            L_0x0065:
                java.lang.String r1 = "com.google.android.exoplayer2.source.dash.DashMediaSource$Factory"
                java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x0075 }
                java.lang.Class r1 = r1.asSubclass(r3)     // Catch:{ ClassNotFoundException -> 0x0075 }
                com.appsamurai.storyly.exoplayer2.core.source.d r2 = new com.appsamurai.storyly.exoplayer2.core.source.d     // Catch:{ ClassNotFoundException -> 0x0075 }
                r2.<init>(r5, r1, r0)     // Catch:{ ClassNotFoundException -> 0x0075 }
                goto L_0x0042
            L_0x0075:
                java.util.Map<java.lang.Integer, com.google.common.base.Supplier<com.appsamurai.storyly.exoplayer2.core.source.MediaSource$Factory>> r0 = r5.mediaSourceFactorySuppliers
                java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
                r0.put(r1, r4)
                if (r4 == 0) goto L_0x0089
                java.util.Set<java.lang.Integer> r5 = r5.supportedTypes
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                r5.add(r6)
            L_0x0089:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.source.DefaultMediaSourceFactory.DelegateFactoryLoader.maybeLoadSupplier(int):com.google.common.base.Supplier");
        }

        @Nullable
        public MediaSource.Factory getMediaSourceFactory(int i3) {
            MediaSource.Factory factory = this.mediaSourceFactories.get(Integer.valueOf(i3));
            if (factory != null) {
                return factory;
            }
            Supplier<MediaSource.Factory> maybeLoadSupplier = maybeLoadSupplier(i3);
            if (maybeLoadSupplier == null) {
                return null;
            }
            MediaSource.Factory factory2 = maybeLoadSupplier.get();
            DrmSessionManagerProvider drmSessionManagerProvider2 = this.drmSessionManagerProvider;
            if (drmSessionManagerProvider2 != null) {
                factory2.setDrmSessionManagerProvider(drmSessionManagerProvider2);
            }
            LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
            if (loadErrorHandlingPolicy2 != null) {
                factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
            }
            this.mediaSourceFactories.put(Integer.valueOf(i3), factory2);
            return factory2;
        }

        public int[] getSupportedTypes() {
            ensureAllSuppliersAreLoaded();
            return Ints.toArray(this.supportedTypes);
        }

        public void setDataSourceFactory(DataSource.Factory factory) {
            if (factory != this.dataSourceFactory) {
                this.dataSourceFactory = factory;
                this.mediaSourceFactories.clear();
            }
        }

        public void setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider2) {
            this.drmSessionManagerProvider = drmSessionManagerProvider2;
            for (MediaSource.Factory drmSessionManagerProvider3 : this.mediaSourceFactories.values()) {
                drmSessionManagerProvider3.setDrmSessionManagerProvider(drmSessionManagerProvider2);
            }
        }

        public void setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
            for (MediaSource.Factory loadErrorHandlingPolicy3 : this.mediaSourceFactories.values()) {
                loadErrorHandlingPolicy3.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
            }
        }
    }

    public static final class UnknownSubtitlesExtractor implements Extractor {
        private final Format format;

        public UnknownSubtitlesExtractor(Format format2) {
            this.format = format2;
        }

        public void init(ExtractorOutput extractorOutput) {
            TrackOutput track = extractorOutput.track(0, 3);
            extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
            extractorOutput.endTracks();
            track.format(this.format.buildUpon().setSampleMimeType(MimeTypes.TEXT_UNKNOWN).setCodecs(this.format.sampleMimeType).build());
        }

        public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
            return extractorInput.skip(Integer.MAX_VALUE) == -1 ? -1 : 0;
        }

        public void release() {
        }

        public void seek(long j2, long j3) {
        }

        public boolean sniff(ExtractorInput extractorInput) {
            return true;
        }
    }

    public DefaultMediaSourceFactory(Context context) {
        this((DataSource.Factory) new DefaultDataSource.Factory(context));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Extractor[] lambda$createMediaSource$0(Format format) {
        SubtitleDecoderFactory subtitleDecoderFactory = SubtitleDecoderFactory.DEFAULT;
        return new Extractor[]{subtitleDecoderFactory.supportsFormat(format) ? new SubtitleExtractor(subtitleDecoderFactory.createDecoder(format), format) : new UnknownSubtitlesExtractor(format)};
    }

    private static MediaSource maybeClipMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        MediaItem.ClippingConfiguration clippingConfiguration = mediaItem.clippingConfiguration;
        long j2 = clippingConfiguration.startPositionMs;
        if (j2 == 0 && clippingConfiguration.endPositionMs == Long.MIN_VALUE && !clippingConfiguration.relativeToDefaultPosition) {
            return mediaSource;
        }
        long msToUs = Util.msToUs(j2);
        long msToUs2 = Util.msToUs(mediaItem.clippingConfiguration.endPositionMs);
        MediaItem.ClippingConfiguration clippingConfiguration2 = mediaItem.clippingConfiguration;
        return new ClippingMediaSource(mediaSource, msToUs, msToUs2, !clippingConfiguration2.startsAtKeyFrame, clippingConfiguration2.relativeToLiveWindow, clippingConfiguration2.relativeToDefaultPosition);
    }

    private MediaSource maybeWrapWithAdsMediaSource(MediaItem mediaItem, MediaSource mediaSource) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        MediaItem.AdsConfiguration adsConfiguration = mediaItem.localConfiguration.adsConfiguration;
        if (adsConfiguration == null) {
            return mediaSource;
        }
        AdsLoader.Provider provider = this.adsLoaderProvider;
        AdViewProvider adViewProvider2 = this.adViewProvider;
        if (provider == null || adViewProvider2 == null) {
            Log.w(TAG, "Playing media without ads. Configure ad support by calling setAdsLoaderProvider and setAdViewProvider.");
            return mediaSource;
        }
        AdsLoader adsLoader = provider.getAdsLoader(adsConfiguration);
        if (adsLoader == null) {
            Log.w(TAG, "Playing media without ads, as no AdsLoader was provided.");
            return mediaSource;
        }
        DataSpec dataSpec = new DataSpec(adsConfiguration.adTagUri);
        Object obj = adsConfiguration.adsId;
        return new AdsMediaSource(mediaSource, dataSpec, obj != null ? obj : ImmutableList.of(mediaItem.mediaId, mediaItem.localConfiguration.uri, adsConfiguration.adTagUri), this, adsLoader, adViewProvider2);
    }

    /* access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls, DataSource.Factory factory) {
        try {
            return (MediaSource.Factory) cls.getConstructor(new Class[]{DataSource.Factory.class}).newInstance(new Object[]{factory});
        } catch (Exception e3) {
            throw new IllegalStateException(e3);
        }
    }

    public DefaultMediaSourceFactory clearLocalAdInsertionComponents() {
        this.adsLoaderProvider = null;
        this.adViewProvider = null;
        return this;
    }

    public MediaSource createMediaSource(MediaItem mediaItem) {
        Assertions.checkNotNull(mediaItem.localConfiguration);
        String scheme = mediaItem.localConfiguration.uri.getScheme();
        if (scheme != null && scheme.equals(C.SSAI_SCHEME)) {
            return ((MediaSource.Factory) Assertions.checkNotNull(this.serverSideAdInsertionMediaSourceFactory)).createMediaSource(mediaItem);
        }
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(localConfiguration.uri, localConfiguration.mimeType);
        MediaSource.Factory mediaSourceFactory = this.delegateFactoryLoader.getMediaSourceFactory(inferContentTypeForUriAndMimeType);
        Assertions.checkStateNotNull(mediaSourceFactory, "No suitable media source factory found for content type: " + inferContentTypeForUriAndMimeType);
        MediaItem.LiveConfiguration.Builder buildUpon = mediaItem.liveConfiguration.buildUpon();
        if (mediaItem.liveConfiguration.targetOffsetMs == C.TIME_UNSET) {
            buildUpon.setTargetOffsetMs(this.liveTargetOffsetMs);
        }
        if (mediaItem.liveConfiguration.minPlaybackSpeed == -3.4028235E38f) {
            buildUpon.setMinPlaybackSpeed(this.liveMinSpeed);
        }
        if (mediaItem.liveConfiguration.maxPlaybackSpeed == -3.4028235E38f) {
            buildUpon.setMaxPlaybackSpeed(this.liveMaxSpeed);
        }
        if (mediaItem.liveConfiguration.minOffsetMs == C.TIME_UNSET) {
            buildUpon.setMinOffsetMs(this.liveMinOffsetMs);
        }
        if (mediaItem.liveConfiguration.maxOffsetMs == C.TIME_UNSET) {
            buildUpon.setMaxOffsetMs(this.liveMaxOffsetMs);
        }
        MediaItem.LiveConfiguration build = buildUpon.build();
        if (!build.equals(mediaItem.liveConfiguration)) {
            mediaItem = mediaItem.buildUpon().setLiveConfiguration(build).build();
        }
        MediaSource createMediaSource = mediaSourceFactory.createMediaSource(mediaItem);
        ImmutableList<MediaItem.SubtitleConfiguration> immutableList = ((MediaItem.LocalConfiguration) Util.castNonNull(mediaItem.localConfiguration)).subtitleConfigurations;
        if (!immutableList.isEmpty()) {
            MediaSource[] mediaSourceArr = new MediaSource[(immutableList.size() + 1)];
            mediaSourceArr[0] = createMediaSource;
            for (int i3 = 0; i3 < immutableList.size(); i3++) {
                if (this.useProgressiveMediaSourceForSubtitles) {
                    ProgressiveMediaSource.Factory factory = new ProgressiveMediaSource.Factory(this.dataSourceFactory, (ExtractorsFactory) new c(new Format.Builder().setSampleMimeType(immutableList.get(i3).mimeType).setLanguage(immutableList.get(i3).language).setSelectionFlags(immutableList.get(i3).selectionFlags).setRoleFlags(immutableList.get(i3).roleFlags).setLabel(immutableList.get(i3).label).setId(immutableList.get(i3).id).build()));
                    LoadErrorHandlingPolicy loadErrorHandlingPolicy2 = this.loadErrorHandlingPolicy;
                    if (loadErrorHandlingPolicy2 != null) {
                        factory.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
                    }
                    mediaSourceArr[i3 + 1] = factory.createMediaSource(MediaItem.fromUri(immutableList.get(i3).uri.toString()));
                } else {
                    SingleSampleMediaSource.Factory factory2 = new SingleSampleMediaSource.Factory(this.dataSourceFactory);
                    LoadErrorHandlingPolicy loadErrorHandlingPolicy3 = this.loadErrorHandlingPolicy;
                    if (loadErrorHandlingPolicy3 != null) {
                        factory2.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy3);
                    }
                    mediaSourceArr[i3 + 1] = factory2.createMediaSource(immutableList.get(i3), C.TIME_UNSET);
                }
            }
            createMediaSource = new MergingMediaSource(mediaSourceArr);
        }
        return maybeWrapWithAdsMediaSource(mediaItem, maybeClipMediaSource(mediaItem, createMediaSource));
    }

    public DefaultMediaSourceFactory experimentalUseProgressiveMediaSourceForSubtitles(boolean z2) {
        this.useProgressiveMediaSourceForSubtitles = z2;
        return this;
    }

    public int[] getSupportedTypes() {
        return this.delegateFactoryLoader.getSupportedTypes();
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdViewProvider(@Nullable AdViewProvider adViewProvider2) {
        this.adViewProvider = adViewProvider2;
        return this;
    }

    @Deprecated
    public DefaultMediaSourceFactory setAdsLoaderProvider(@Nullable AdsLoader.Provider provider) {
        this.adsLoaderProvider = provider;
        return this;
    }

    public DefaultMediaSourceFactory setDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxOffsetMs(long j2) {
        this.liveMaxOffsetMs = j2;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMaxSpeed(float f2) {
        this.liveMaxSpeed = f2;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinOffsetMs(long j2) {
        this.liveMinOffsetMs = j2;
        return this;
    }

    public DefaultMediaSourceFactory setLiveMinSpeed(float f2) {
        this.liveMinSpeed = f2;
        return this;
    }

    public DefaultMediaSourceFactory setLiveTargetOffsetMs(long j2) {
        this.liveTargetOffsetMs = j2;
        return this;
    }

    public DefaultMediaSourceFactory setLocalAdInsertionComponents(AdsLoader.Provider provider, AdViewProvider adViewProvider2) {
        this.adsLoaderProvider = (AdsLoader.Provider) Assertions.checkNotNull(provider);
        this.adViewProvider = (AdViewProvider) Assertions.checkNotNull(adViewProvider2);
        return this;
    }

    public DefaultMediaSourceFactory setServerSideAdInsertionMediaSourceFactory(@Nullable MediaSource.Factory factory) {
        this.serverSideAdInsertionMediaSourceFactory = factory;
        return this;
    }

    public DefaultMediaSourceFactory(Context context, ExtractorsFactory extractorsFactory) {
        this((DataSource.Factory) new DefaultDataSource.Factory(context), extractorsFactory);
    }

    public DefaultMediaSourceFactory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
        this.delegateFactoryLoader.setDrmSessionManagerProvider((DrmSessionManagerProvider) Assertions.checkNotNull(drmSessionManagerProvider, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior."));
        return this;
    }

    public DefaultMediaSourceFactory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
        this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
        this.delegateFactoryLoader.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy2);
        return this;
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory) {
        this(factory, (ExtractorsFactory) new DefaultExtractorsFactory());
    }

    /* access modifiers changed from: private */
    public static MediaSource.Factory newInstance(Class<? extends MediaSource.Factory> cls) {
        try {
            return (MediaSource.Factory) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception e3) {
            throw new IllegalStateException(e3);
        }
    }

    public DefaultMediaSourceFactory(DataSource.Factory factory, ExtractorsFactory extractorsFactory) {
        this.dataSourceFactory = factory;
        DelegateFactoryLoader delegateFactoryLoader2 = new DelegateFactoryLoader(extractorsFactory);
        this.delegateFactoryLoader = delegateFactoryLoader2;
        delegateFactoryLoader2.setDataSourceFactory(factory);
        this.liveTargetOffsetMs = C.TIME_UNSET;
        this.liveMinOffsetMs = C.TIME_UNSET;
        this.liveMaxOffsetMs = C.TIME_UNSET;
        this.liveMinSpeed = -3.4028235E38f;
        this.liveMaxSpeed = -3.4028235E38f;
    }
}
