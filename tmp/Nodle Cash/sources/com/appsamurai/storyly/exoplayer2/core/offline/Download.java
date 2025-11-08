package com.appsamurai.storyly.exoplayer2.core.offline;

import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class Download {
    public static final int FAILURE_REASON_NONE = 0;
    public static final int FAILURE_REASON_UNKNOWN = 1;
    public static final int STATE_COMPLETED = 3;
    public static final int STATE_DOWNLOADING = 2;
    public static final int STATE_FAILED = 4;
    public static final int STATE_QUEUED = 0;
    public static final int STATE_REMOVING = 5;
    public static final int STATE_RESTARTING = 7;
    public static final int STATE_STOPPED = 1;
    public static final int STOP_REASON_NONE = 0;
    public final long contentLength;
    public final int failureReason;
    final DownloadProgress progress;
    public final DownloadRequest request;
    public final long startTimeMs;
    public final int state;
    public final int stopReason;
    public final long updateTimeMs;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FailureReason {
    }

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public Download(DownloadRequest downloadRequest, int i3, long j2, long j3, long j4, int i4, int i5) {
        this(downloadRequest, i3, j2, j3, j4, i4, i5, new DownloadProgress());
    }

    public long getBytesDownloaded() {
        return this.progress.bytesDownloaded;
    }

    public float getPercentDownloaded() {
        return this.progress.percentDownloaded;
    }

    public boolean isTerminalState() {
        int i3 = this.state;
        return i3 == 3 || i3 == 4;
    }

    public Download(DownloadRequest downloadRequest, int i3, long j2, long j3, long j4, int i4, int i5, DownloadProgress downloadProgress) {
        Assertions.checkNotNull(downloadProgress);
        boolean z2 = false;
        Assertions.checkArgument((i5 == 0) == (i3 != 4));
        if (i4 != 0) {
            if (!(i3 == 2 || i3 == 0)) {
                z2 = true;
            }
            Assertions.checkArgument(z2);
        }
        this.request = downloadRequest;
        this.state = i3;
        this.startTimeMs = j2;
        this.updateTimeMs = j3;
        this.contentLength = j4;
        this.stopReason = i4;
        this.failureReason = i5;
        this.progress = downloadProgress;
    }
}
