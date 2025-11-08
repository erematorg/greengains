package com.appsamurai.storyly.exoplayer2.core;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.a;
import android.text.TextUtils;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.source.MediaPeriodId;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class ExoPlaybackException extends PlaybackException {
    public static final Bundleable.Creator<ExoPlaybackException> CREATOR = new r(2);
    private static final int FIELD_IS_RECOVERABLE = 1006;
    private static final int FIELD_RENDERER_FORMAT = 1004;
    private static final int FIELD_RENDERER_FORMAT_SUPPORT = 1005;
    private static final int FIELD_RENDERER_INDEX = 1003;
    private static final int FIELD_RENDERER_NAME = 1002;
    private static final int FIELD_TYPE = 1001;
    public static final int TYPE_REMOTE = 3;
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    final boolean isRecoverable;
    @Nullable
    public final MediaPeriodId mediaPeriodId;
    @Nullable
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    @Nullable
    public final String rendererName;
    public final int type;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private ExoPlaybackException(int i3, Throwable th, int i4) {
        this(i3, th, (String) null, i4, (String) null, -1, (Format) null, 4, false);
    }

    public static /* synthetic */ ExoPlaybackException a(Bundle bundle) {
        return new ExoPlaybackException(bundle);
    }

    public static ExoPlaybackException createForRemote(String str) {
        return new ExoPlaybackException(3, (Throwable) null, str, 1001, (String) null, -1, (Format) null, 4, false);
    }

    public static ExoPlaybackException createForRenderer(Throwable th, String str, int i3, @Nullable Format format, int i4, boolean z2, int i5) {
        return new ExoPlaybackException(1, th, (String) null, i5, str, i3, format, format == null ? 4 : i4, z2);
    }

    public static ExoPlaybackException createForSource(IOException iOException, int i3) {
        return new ExoPlaybackException(0, iOException, i3);
    }

    @Deprecated
    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return createForUnexpected(runtimeException, 1000);
    }

    private static String deriveMessage(int i3, @Nullable String str, @Nullable String str2, int i4, @Nullable Format format, int i5) {
        String str3;
        if (i3 == 0) {
            str3 = "Source error";
        } else if (i3 != 1) {
            str3 = i3 != 3 ? "Unexpected runtime error" : "Remote error";
        } else {
            str3 = str2 + " error, index=" + i4 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(i5);
        }
        return !TextUtils.isEmpty(str) ? a.n(str3, ": ", str) : str3;
    }

    @CheckResult
    public ExoPlaybackException copyWithMediaPeriodId(@Nullable MediaPeriodId mediaPeriodId2) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId2, this.timestampMs, this.isRecoverable);
    }

    public boolean errorInfoEquals(@Nullable PlaybackException playbackException) {
        if (!super.errorInfoEquals(playbackException)) {
            return false;
        }
        ExoPlaybackException exoPlaybackException = (ExoPlaybackException) Util.castNonNull(playbackException);
        return this.type == exoPlaybackException.type && Util.areEqual(this.rendererName, exoPlaybackException.rendererName) && this.rendererIndex == exoPlaybackException.rendererIndex && Util.areEqual(this.rendererFormat, exoPlaybackException.rendererFormat) && this.rendererFormatSupport == exoPlaybackException.rendererFormatSupport && Util.areEqual(this.mediaPeriodId, exoPlaybackException.mediaPeriodId) && this.isRecoverable == exoPlaybackException.isRecoverable;
    }

    public Exception getRendererException() {
        boolean z2 = true;
        if (this.type != 1) {
            z2 = false;
        }
        Assertions.checkState(z2);
        return (Exception) Assertions.checkNotNull(getCause());
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        return (IOException) Assertions.checkNotNull(getCause());
    }

    public RuntimeException getUnexpectedException() {
        Assertions.checkState(this.type == 2);
        return (RuntimeException) Assertions.checkNotNull(getCause());
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putInt(PlaybackException.keyForField(1001), this.type);
        bundle.putString(PlaybackException.keyForField(1002), this.rendererName);
        bundle.putInt(PlaybackException.keyForField(1003), this.rendererIndex);
        if (this.rendererFormat != null) {
            bundle.putBundle(PlaybackException.keyForField(1004), this.rendererFormat.toBundle());
        }
        bundle.putInt(PlaybackException.keyForField(1005), this.rendererFormatSupport);
        bundle.putBoolean(PlaybackException.keyForField(1006), this.isRecoverable);
        return bundle;
    }

    private ExoPlaybackException(int i3, @Nullable Throwable th, @Nullable String str, int i4, @Nullable String str2, int i5, @Nullable Format format, int i6, boolean z2) {
        this(deriveMessage(i3, str, str2, i5, format, i6), th, i4, i3, str2, i5, format, i6, (MediaPeriodId) null, SystemClock.elapsedRealtime(), z2);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int i3) {
        return new ExoPlaybackException(2, runtimeException, i3);
    }

    private ExoPlaybackException(Bundle bundle) {
        super(bundle);
        Format format;
        this.type = bundle.getInt(PlaybackException.keyForField(1001), 2);
        this.rendererName = bundle.getString(PlaybackException.keyForField(1002));
        this.rendererIndex = bundle.getInt(PlaybackException.keyForField(1003), -1);
        Bundle bundle2 = bundle.getBundle(PlaybackException.keyForField(1004));
        if (bundle2 == null) {
            format = null;
        } else {
            format = Format.CREATOR.fromBundle(bundle2);
        }
        this.rendererFormat = format;
        this.rendererFormatSupport = bundle.getInt(PlaybackException.keyForField(1005), 4);
        this.isRecoverable = bundle.getBoolean(PlaybackException.keyForField(1006), false);
        this.mediaPeriodId = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ExoPlaybackException(String str, @Nullable Throwable th, int i3, int i4, @Nullable String str2, int i5, @Nullable Format format, int i6, @Nullable MediaPeriodId mediaPeriodId2, long j2, boolean z2) {
        super(str, th, i3, j2);
        int i7 = i4;
        boolean z3 = z2;
        boolean z4 = false;
        Assertions.checkArgument(!z3 || i7 == 1);
        Assertions.checkArgument((th != null || i7 == 3) ? true : z4);
        this.type = i7;
        this.rendererName = str2;
        this.rendererIndex = i5;
        this.rendererFormat = format;
        this.rendererFormatSupport = i6;
        this.mediaPeriodId = mediaPeriodId2;
        this.isRecoverable = z3;
    }
}
