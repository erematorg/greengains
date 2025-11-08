package com.appsamurai.storyly.exoplayer2.extractor.extractor.mp4;

import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.Format;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class Track {
    public static final int TRANSFORMATION_CEA608_CDAT = 1;
    public static final int TRANSFORMATION_NONE = 0;
    public final long durationUs;
    @Nullable
    public final long[] editListDurations;
    @Nullable
    public final long[] editListMediaTimes;
    public final Format format;
    public final int id;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    @Nullable
    private final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final int sampleTransformation;
    public final long timescale;
    public final int type;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Transformation {
    }

    public Track(int i3, int i4, long j2, long j3, long j4, Format format2, int i5, @Nullable TrackEncryptionBox[] trackEncryptionBoxArr, int i6, @Nullable long[] jArr, @Nullable long[] jArr2) {
        this.id = i3;
        this.type = i4;
        this.timescale = j2;
        this.movieTimescale = j3;
        this.durationUs = j4;
        this.format = format2;
        this.sampleTransformation = i5;
        this.sampleDescriptionEncryptionBoxes = trackEncryptionBoxArr;
        this.nalUnitLengthFieldLength = i6;
        this.editListDurations = jArr;
        this.editListMediaTimes = jArr2;
    }

    public Track copyWithFormat(Format format2) {
        return new Track(this.id, this.type, this.timescale, this.movieTimescale, this.durationUs, format2, this.sampleTransformation, this.sampleDescriptionEncryptionBoxes, this.nalUnitLengthFieldLength, this.editListDurations, this.editListMediaTimes);
    }

    @Nullable
    public TrackEncryptionBox getSampleDescriptionEncryptionBox(int i3) {
        TrackEncryptionBox[] trackEncryptionBoxArr = this.sampleDescriptionEncryptionBoxes;
        if (trackEncryptionBoxArr == null) {
            return null;
        }
        return trackEncryptionBoxArr[i3];
    }
}
