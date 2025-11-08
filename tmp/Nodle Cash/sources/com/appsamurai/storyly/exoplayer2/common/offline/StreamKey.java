package com.appsamurai.storyly.exoplayer2.common.offline;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.reown.foundation.util.jwt.JwtUtilsKt;

public final class StreamKey implements Comparable<StreamKey>, Parcelable {
    public static final Parcelable.Creator<StreamKey> CREATOR = new Parcelable.Creator<StreamKey>() {
        public StreamKey createFromParcel(Parcel parcel) {
            return new StreamKey(parcel);
        }

        public StreamKey[] newArray(int i3) {
            return new StreamKey[i3];
        }
    };
    public final int groupIndex;
    public final int periodIndex;
    public final int streamIndex;
    @Deprecated
    public final int trackIndex;

    public StreamKey(int i3, int i4) {
        this(0, i3, i4);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StreamKey.class != obj.getClass()) {
            return false;
        }
        StreamKey streamKey = (StreamKey) obj;
        return this.periodIndex == streamKey.periodIndex && this.groupIndex == streamKey.groupIndex && this.streamIndex == streamKey.streamIndex;
    }

    public int hashCode() {
        return (((this.periodIndex * 31) + this.groupIndex) * 31) + this.streamIndex;
    }

    public String toString() {
        return this.periodIndex + JwtUtilsKt.JWT_DELIMITER + this.groupIndex + JwtUtilsKt.JWT_DELIMITER + this.streamIndex;
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeInt(this.periodIndex);
        parcel.writeInt(this.groupIndex);
        parcel.writeInt(this.streamIndex);
    }

    public StreamKey(int i3, int i4, int i5) {
        this.periodIndex = i3;
        this.groupIndex = i4;
        this.streamIndex = i5;
        this.trackIndex = i5;
    }

    public int compareTo(StreamKey streamKey) {
        int i3 = this.periodIndex - streamKey.periodIndex;
        if (i3 != 0) {
            return i3;
        }
        int i4 = this.groupIndex - streamKey.groupIndex;
        return i4 == 0 ? this.streamIndex - streamKey.streamIndex : i4;
    }

    public StreamKey(Parcel parcel) {
        this.periodIndex = parcel.readInt();
        this.groupIndex = parcel.readInt();
        int readInt = parcel.readInt();
        this.streamIndex = readInt;
        this.trackIndex = readInt;
    }
}
