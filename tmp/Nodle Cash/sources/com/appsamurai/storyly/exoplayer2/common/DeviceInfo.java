package com.appsamurai.storyly.exoplayer2.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.work.impl.a;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class DeviceInfo implements Bundleable {
    public static final Bundleable.Creator<DeviceInfo> CREATOR = new a(24);
    private static final int FIELD_MAX_VOLUME = 2;
    private static final int FIELD_MIN_VOLUME = 1;
    private static final int FIELD_PLAYBACK_TYPE = 0;
    public static final int PLAYBACK_TYPE_LOCAL = 0;
    public static final int PLAYBACK_TYPE_REMOTE = 1;
    public static final DeviceInfo UNKNOWN = new DeviceInfo(0, 0, 0);
    public final int maxVolume;
    public final int minVolume;
    public final int playbackType;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaybackType {
    }

    public DeviceInfo(int i3, int i4, int i5) {
        this.playbackType = i3;
        this.minVolume = i4;
        this.maxVolume = i5;
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DeviceInfo lambda$static$0(Bundle bundle) {
        return new DeviceInfo(bundle.getInt(keyForField(0), 0), bundle.getInt(keyForField(1), 0), bundle.getInt(keyForField(2), 0));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.playbackType == deviceInfo.playbackType && this.minVolume == deviceInfo.minVolume && this.maxVolume == deviceInfo.maxVolume;
    }

    public int hashCode() {
        return ((((527 + this.playbackType) * 31) + this.minVolume) * 31) + this.maxVolume;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(0), this.playbackType);
        bundle.putInt(keyForField(1), this.minVolume);
        bundle.putInt(keyForField(2), this.maxVolume);
        return bundle;
    }
}
