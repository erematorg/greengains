package com.appsamurai.storyly.exoplayer2.core.source;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.source.TrackGroup;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.SeekParameters;
import com.appsamurai.storyly.exoplayer2.core.source.MediaPeriod;
import com.appsamurai.storyly.exoplayer2.core.source.MediaSourceEventListener;
import com.appsamurai.storyly.exoplayer2.core.trackselection.ExoTrackSelection;
import com.appsamurai.storyly.exoplayer2.core.upstream.LoadErrorHandlingPolicy;
import com.appsamurai.storyly.exoplayer2.core.upstream.Loader;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSourceUtil;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSpec;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.StatsDataSource;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.TransferListener;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

final class SingleSampleMediaPeriod implements MediaPeriod, Loader.Callback<SourceLoadable> {
    private static final int INITIAL_SAMPLE_SIZE = 1024;
    private static final String TAG = "SingleSampleMediaPeriod";
    private final DataSource.Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    /* access modifiers changed from: private */
    public final MediaSourceEventListener.EventDispatcher eventDispatcher;
    final Format format;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    final Loader loader = new Loader(TAG);
    boolean loadingFinished;
    byte[] sampleData;
    int sampleSize;
    private final ArrayList<SampleStreamImpl> sampleStreams = new ArrayList<>();
    private final TrackGroupArray tracks;
    @Nullable
    private final TransferListener transferListener;
    final boolean treatLoadErrorsAsEndOfStream;

    public final class SampleStreamImpl implements SampleStream {
        private static final int STREAM_STATE_END_OF_STREAM = 2;
        private static final int STREAM_STATE_SEND_FORMAT = 0;
        private static final int STREAM_STATE_SEND_SAMPLE = 1;
        private boolean notifiedDownstreamFormat;
        private int streamState;

        private SampleStreamImpl() {
        }

        private void maybeNotifyDownstreamFormat() {
            if (!this.notifiedDownstreamFormat) {
                SingleSampleMediaPeriod.this.eventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(SingleSampleMediaPeriod.this.format.sampleMimeType), SingleSampleMediaPeriod.this.format, 0, (Object) null, 0);
                this.notifiedDownstreamFormat = true;
            }
        }

        public boolean isReady() {
            return SingleSampleMediaPeriod.this.loadingFinished;
        }

        public void maybeThrowError() throws IOException {
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            if (!singleSampleMediaPeriod.treatLoadErrorsAsEndOfStream) {
                singleSampleMediaPeriod.loader.maybeThrowError();
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i3) {
            maybeNotifyDownstreamFormat();
            SingleSampleMediaPeriod singleSampleMediaPeriod = SingleSampleMediaPeriod.this;
            boolean z2 = singleSampleMediaPeriod.loadingFinished;
            if (z2 && singleSampleMediaPeriod.sampleData == null) {
                this.streamState = 2;
            }
            int i4 = this.streamState;
            if (i4 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            } else if ((i3 & 2) != 0 || i4 == 0) {
                formatHolder.format = singleSampleMediaPeriod.format;
                this.streamState = 1;
                return -5;
            } else if (!z2) {
                return -3;
            } else {
                Assertions.checkNotNull(singleSampleMediaPeriod.sampleData);
                decoderInputBuffer.addFlag(1);
                decoderInputBuffer.timeUs = 0;
                if ((i3 & 4) == 0) {
                    decoderInputBuffer.ensureSpaceForWrite(SingleSampleMediaPeriod.this.sampleSize);
                    ByteBuffer byteBuffer = decoderInputBuffer.data;
                    SingleSampleMediaPeriod singleSampleMediaPeriod2 = SingleSampleMediaPeriod.this;
                    byteBuffer.put(singleSampleMediaPeriod2.sampleData, 0, singleSampleMediaPeriod2.sampleSize);
                }
                if ((i3 & 1) == 0) {
                    this.streamState = 2;
                }
                return -4;
            }
        }

        public void reset() {
            if (this.streamState == 2) {
                this.streamState = 1;
            }
        }

        public int skipData(long j2) {
            maybeNotifyDownstreamFormat();
            if (j2 <= 0 || this.streamState == 2) {
                return 0;
            }
            this.streamState = 2;
            return 1;
        }
    }

    public static final class SourceLoadable implements Loader.Loadable {
        /* access modifiers changed from: private */
        public final StatsDataSource dataSource;
        public final DataSpec dataSpec;
        public final long loadTaskId = LoadEventInfo.getNewId();
        /* access modifiers changed from: private */
        @Nullable
        public byte[] sampleData;

        public SourceLoadable(DataSpec dataSpec2, DataSource dataSource2) {
            this.dataSpec = dataSpec2;
            this.dataSource = new StatsDataSource(dataSource2);
        }

        public void cancelLoad() {
        }

        public void load() throws IOException {
            this.dataSource.resetBytesRead();
            try {
                this.dataSource.open(this.dataSpec);
                int i3 = 0;
                while (i3 != -1) {
                    int bytesRead = (int) this.dataSource.getBytesRead();
                    byte[] bArr = this.sampleData;
                    if (bArr == null) {
                        this.sampleData = new byte[1024];
                    } else if (bytesRead == bArr.length) {
                        this.sampleData = Arrays.copyOf(bArr, bArr.length * 2);
                    }
                    StatsDataSource statsDataSource = this.dataSource;
                    byte[] bArr2 = this.sampleData;
                    i3 = statsDataSource.read(bArr2, bytesRead, bArr2.length - bytesRead);
                }
                DataSourceUtil.closeQuietly(this.dataSource);
            } catch (Throwable th) {
                DataSourceUtil.closeQuietly(this.dataSource);
                throw th;
            }
        }
    }

    public SingleSampleMediaPeriod(DataSpec dataSpec2, DataSource.Factory factory, @Nullable TransferListener transferListener2, Format format2, long j2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, boolean z2) {
        this.dataSpec = dataSpec2;
        this.dataSourceFactory = factory;
        this.transferListener = transferListener2;
        this.format = format2;
        this.durationUs = j2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher2;
        this.treatLoadErrorsAsEndOfStream = z2;
        this.tracks = new TrackGroupArray(new TrackGroup(format2));
    }

    public boolean continueLoading(long j2) {
        if (this.loadingFinished || this.loader.isLoading() || this.loader.hasFatalError()) {
            return false;
        }
        DataSource createDataSource = this.dataSourceFactory.createDataSource();
        TransferListener transferListener2 = this.transferListener;
        if (transferListener2 != null) {
            createDataSource.addTransferListener(transferListener2);
        }
        SourceLoadable sourceLoadable = new SourceLoadable(this.dataSpec, createDataSource);
        this.eventDispatcher.loadStarted(new LoadEventInfo(sourceLoadable.loadTaskId, this.dataSpec, this.loader.startLoading(sourceLoadable, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1))), 1, -1, this.format, 0, (Object) null, 0, this.durationUs);
        return true;
    }

    public void discardBuffer(long j2, boolean z2) {
    }

    public long getAdjustedSeekPositionUs(long j2, SeekParameters seekParameters) {
        return j2;
    }

    public long getBufferedPositionUs() {
        return this.loadingFinished ? Long.MIN_VALUE : 0;
    }

    public long getNextLoadPositionUs() {
        return (this.loadingFinished || this.loader.isLoading()) ? Long.MIN_VALUE : 0;
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public void maybeThrowPrepareError() {
    }

    public void prepare(MediaPeriod.Callback callback, long j2) {
        callback.onPrepared(this);
    }

    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public void reevaluateBuffer(long j2) {
    }

    public void release() {
        this.loader.release();
    }

    public long seekToUs(long j2) {
        for (int i3 = 0; i3 < this.sampleStreams.size(); i3++) {
            this.sampleStreams.get(i3).reset();
        }
        return j2;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j2) {
        for (int i3 = 0; i3 < exoTrackSelectionArr.length; i3++) {
            SampleStream sampleStream = sampleStreamArr[i3];
            if (sampleStream != null && (exoTrackSelectionArr[i3] == null || !zArr[i3])) {
                this.sampleStreams.remove(sampleStream);
                sampleStreamArr[i3] = null;
            }
            if (sampleStreamArr[i3] == null && exoTrackSelectionArr[i3] != null) {
                SampleStreamImpl sampleStreamImpl = new SampleStreamImpl();
                this.sampleStreams.add(sampleStreamImpl);
                sampleStreamArr[i3] = sampleStreamImpl;
                zArr2[i3] = true;
            }
        }
        return j2;
    }

    public void onLoadCanceled(SourceLoadable sourceLoadable, long j2, long j3, boolean z2) {
        SourceLoadable sourceLoadable2 = sourceLoadable;
        StatsDataSource access$100 = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable2.loadTaskId);
        this.eventDispatcher.loadCanceled(loadEventInfo, 1, -1, (Format) null, 0, (Object) null, 0, this.durationUs);
    }

    public void onLoadCompleted(SourceLoadable sourceLoadable, long j2, long j3) {
        SourceLoadable sourceLoadable2 = sourceLoadable;
        this.sampleSize = (int) sourceLoadable.dataSource.getBytesRead();
        this.sampleData = (byte[]) Assertions.checkNotNull(sourceLoadable.sampleData);
        this.loadingFinished = true;
        StatsDataSource access$100 = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, (long) this.sampleSize);
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable2.loadTaskId);
        this.eventDispatcher.loadCompleted(loadEventInfo, 1, -1, this.format, 0, (Object) null, 0, this.durationUs);
    }

    public Loader.LoadErrorAction onLoadError(SourceLoadable sourceLoadable, long j2, long j3, IOException iOException, int i3) {
        Loader.LoadErrorAction loadErrorAction;
        SourceLoadable sourceLoadable2 = sourceLoadable;
        IOException iOException2 = iOException;
        int i4 = i3;
        StatsDataSource access$100 = sourceLoadable.dataSource;
        LoadEventInfo loadEventInfo = new LoadEventInfo(sourceLoadable2.loadTaskId, sourceLoadable2.dataSpec, access$100.getLastOpenedUri(), access$100.getLastResponseHeaders(), j2, j3, access$100.getBytesRead());
        long retryDelayMsFor = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, new MediaLoadData(1, -1, this.format, 0, (Object) null, 0, Util.usToMs(this.durationUs)), iOException2, i4));
        int i5 = (retryDelayMsFor > C.TIME_UNSET ? 1 : (retryDelayMsFor == C.TIME_UNSET ? 0 : -1));
        boolean z2 = i5 == 0 || i4 >= this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(1);
        if (this.treatLoadErrorsAsEndOfStream && z2) {
            Log.w(TAG, "Loading failed, treating as end-of-stream.", iOException2);
            this.loadingFinished = true;
            loadErrorAction = Loader.DONT_RETRY;
        } else if (i5 != 0) {
            loadErrorAction = Loader.createRetryAction(false, retryDelayMsFor);
        } else {
            loadErrorAction = Loader.DONT_RETRY_FATAL;
        }
        Loader.LoadErrorAction loadErrorAction2 = loadErrorAction;
        boolean isRetry = loadErrorAction2.isRetry();
        this.eventDispatcher.loadError(loadEventInfo, 1, -1, this.format, 0, (Object) null, 0, this.durationUs, iOException, !isRetry);
        if (!isRetry) {
            this.loadErrorHandlingPolicy.onLoadTaskConcluded(sourceLoadable2.loadTaskId);
        }
        return loadErrorAction2;
    }
}
