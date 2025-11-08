package com.appsamurai.storyly.exoplayer2.common.source;

import androidx.annotation.Nullable;

public class MediaPeriodId {
    public final int adGroupIndex;
    public final int adIndexInAdGroup;
    public final int nextAdGroupIndex;
    public final Object periodUid;
    public final long windowSequenceNumber;

    public MediaPeriodId(Object obj) {
        this(obj, -1);
    }

    public MediaPeriodId copyWithPeriodUid(Object obj) {
        if (this.periodUid.equals(obj)) {
            return this;
        }
        return new MediaPeriodId(obj, this.adGroupIndex, this.adIndexInAdGroup, this.windowSequenceNumber, this.nextAdGroupIndex);
    }

    public MediaPeriodId copyWithWindowSequenceNumber(long j2) {
        if (this.windowSequenceNumber == j2) {
            return this;
        }
        return new MediaPeriodId(this.periodUid, this.adGroupIndex, this.adIndexInAdGroup, j2, this.nextAdGroupIndex);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPeriodId)) {
            return false;
        }
        MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
        return this.periodUid.equals(mediaPeriodId.periodUid) && this.adGroupIndex == mediaPeriodId.adGroupIndex && this.adIndexInAdGroup == mediaPeriodId.adIndexInAdGroup && this.windowSequenceNumber == mediaPeriodId.windowSequenceNumber && this.nextAdGroupIndex == mediaPeriodId.nextAdGroupIndex;
    }

    public int hashCode() {
        return ((((((((this.periodUid.hashCode() + 527) * 31) + this.adGroupIndex) * 31) + this.adIndexInAdGroup) * 31) + ((int) this.windowSequenceNumber)) * 31) + this.nextAdGroupIndex;
    }

    public boolean isAd() {
        return this.adGroupIndex != -1;
    }

    public MediaPeriodId(Object obj, long j2) {
        this(obj, -1, -1, j2, -1);
    }

    public MediaPeriodId(Object obj, long j2, int i3) {
        this(obj, -1, -1, j2, i3);
    }

    public MediaPeriodId(Object obj, int i3, int i4, long j2) {
        this(obj, i3, i4, j2, -1);
    }

    public MediaPeriodId(MediaPeriodId mediaPeriodId) {
        this.periodUid = mediaPeriodId.periodUid;
        this.adGroupIndex = mediaPeriodId.adGroupIndex;
        this.adIndexInAdGroup = mediaPeriodId.adIndexInAdGroup;
        this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
        this.nextAdGroupIndex = mediaPeriodId.nextAdGroupIndex;
    }

    private MediaPeriodId(Object obj, int i3, int i4, long j2, int i5) {
        this.periodUid = obj;
        this.adGroupIndex = i3;
        this.adIndexInAdGroup = i4;
        this.windowSequenceNumber = j2;
        this.nextAdGroupIndex = i5;
    }
}
