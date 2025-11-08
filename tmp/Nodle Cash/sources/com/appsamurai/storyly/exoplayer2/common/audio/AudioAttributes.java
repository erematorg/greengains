package com.appsamurai.storyly.exoplayer2.common.audio;

import android.media.AudioAttributes;
import android.os.Bundle;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.Bundleable;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class AudioAttributes implements Bundleable {
    public static final Bundleable.Creator<AudioAttributes> CREATOR = new a(15);
    public static final AudioAttributes DEFAULT = new Builder().build();
    private static final int FIELD_ALLOWED_CAPTURE_POLICY = 3;
    private static final int FIELD_CONTENT_TYPE = 0;
    private static final int FIELD_FLAGS = 1;
    private static final int FIELD_SPATIALIZATION_BEHAVIOR = 4;
    private static final int FIELD_USAGE = 2;
    public final int allowedCapturePolicy;
    @Nullable
    private AudioAttributesV21 audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final int spatializationBehavior;
    public final int usage;

    @RequiresApi(29)
    public static final class Api29 {
        private Api29() {
        }

        @DoNotInline
        public static void setAllowedCapturePolicy(AudioAttributes.Builder builder, int i3) {
            builder.setAllowedCapturePolicy(i3);
        }
    }

    @RequiresApi(32)
    public static final class Api32 {
        private Api32() {
        }

        @DoNotInline
        public static void setSpatializationBehavior(AudioAttributes.Builder builder, int i3) {
            builder.setSpatializationBehavior(i3);
        }
    }

    @RequiresApi(21)
    public static final class AudioAttributesV21 {
        public final android.media.AudioAttributes audioAttributes;

        private AudioAttributesV21(AudioAttributes audioAttributes2) {
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(audioAttributes2.contentType).setFlags(audioAttributes2.flags).setUsage(audioAttributes2.usage);
            int i3 = Util.SDK_INT;
            if (i3 >= 29) {
                Api29.setAllowedCapturePolicy(usage, audioAttributes2.allowedCapturePolicy);
            }
            if (i3 >= 32) {
                Api32.setSpatializationBehavior(usage, audioAttributes2.spatializationBehavior);
            }
            this.audioAttributes = usage.build();
        }
    }

    public static final class Builder {
        private int allowedCapturePolicy = 1;
        private int contentType = 0;
        private int flags = 0;
        private int spatializationBehavior = 0;
        private int usage = 1;

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, this.allowedCapturePolicy, this.spatializationBehavior);
        }

        public Builder setAllowedCapturePolicy(int i3) {
            this.allowedCapturePolicy = i3;
            return this;
        }

        public Builder setContentType(int i3) {
            this.contentType = i3;
            return this;
        }

        public Builder setFlags(int i3) {
            this.flags = i3;
            return this;
        }

        public Builder setSpatializationBehavior(int i3) {
            this.spatializationBehavior = i3;
            return this;
        }

        public Builder setUsage(int i3) {
            this.usage = i3;
            return this;
        }
    }

    private static String keyForField(int i3) {
        return Integer.toString(i3, 36);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AudioAttributes lambda$static$0(Bundle bundle) {
        Builder builder = new Builder();
        if (bundle.containsKey(keyForField(0))) {
            builder.setContentType(bundle.getInt(keyForField(0)));
        }
        if (bundle.containsKey(keyForField(1))) {
            builder.setFlags(bundle.getInt(keyForField(1)));
        }
        if (bundle.containsKey(keyForField(2))) {
            builder.setUsage(bundle.getInt(keyForField(2)));
        }
        if (bundle.containsKey(keyForField(3))) {
            builder.setAllowedCapturePolicy(bundle.getInt(keyForField(3)));
        }
        if (bundle.containsKey(keyForField(4))) {
            builder.setSpatializationBehavior(bundle.getInt(keyForField(4)));
        }
        return builder.build();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AudioAttributes.class != obj.getClass()) {
            return false;
        }
        AudioAttributes audioAttributes = (AudioAttributes) obj;
        return this.contentType == audioAttributes.contentType && this.flags == audioAttributes.flags && this.usage == audioAttributes.usage && this.allowedCapturePolicy == audioAttributes.allowedCapturePolicy && this.spatializationBehavior == audioAttributes.spatializationBehavior;
    }

    @RequiresApi(21)
    public AudioAttributesV21 getAudioAttributesV21() {
        if (this.audioAttributesV21 == null) {
            this.audioAttributesV21 = new AudioAttributesV21();
        }
        return this.audioAttributesV21;
    }

    public int hashCode() {
        return ((((((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage) * 31) + this.allowedCapturePolicy) * 31) + this.spatializationBehavior;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(keyForField(0), this.contentType);
        bundle.putInt(keyForField(1), this.flags);
        bundle.putInt(keyForField(2), this.usage);
        bundle.putInt(keyForField(3), this.allowedCapturePolicy);
        bundle.putInt(keyForField(4), this.spatializationBehavior);
        return bundle;
    }

    private AudioAttributes(int i3, int i4, int i5, int i6, int i7) {
        this.contentType = i3;
        this.flags = i4;
        this.usage = i5;
        this.allowedCapturePolicy = i6;
        this.spatializationBehavior = i7;
    }
}
