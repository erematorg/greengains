package com.appsamurai.storyly.exoplayer2.extractor.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;

public final class TimeSignalCommand extends SpliceCommand {
    public static final Parcelable.Creator<TimeSignalCommand> CREATOR = new Parcelable.Creator<TimeSignalCommand>() {
        public TimeSignalCommand createFromParcel(Parcel parcel) {
            return new TimeSignalCommand(parcel.readLong(), parcel.readLong());
        }

        public TimeSignalCommand[] newArray(int i3) {
            return new TimeSignalCommand[i3];
        }
    };
    public final long playbackPositionUs;
    public final long ptsTime;

    public static TimeSignalCommand parseFromSection(ParsableByteArray parsableByteArray, long j2, TimestampAdjuster timestampAdjuster) {
        long parseSpliceTime = parseSpliceTime(parsableByteArray, j2);
        return new TimeSignalCommand(parseSpliceTime, timestampAdjuster.adjustTsTimestamp(parseSpliceTime));
    }

    public static long parseSpliceTime(ParsableByteArray parsableByteArray, long j2) {
        long readUnsignedByte = (long) parsableByteArray.readUnsignedByte();
        return (128 & readUnsignedByte) != 0 ? 8589934591L & ((((readUnsignedByte & 1) << 32) | parsableByteArray.readUnsignedInt()) + j2) : C.TIME_UNSET;
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeLong(this.ptsTime);
        parcel.writeLong(this.playbackPositionUs);
    }

    private TimeSignalCommand(long j2, long j3) {
        this.ptsTime = j2;
        this.playbackPositionUs = j3;
    }
}
