package com.appsamurai.storyly.exoplayer2.extractor.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import com.appsamurai.storyly.exoplayer2.common.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceInsertCommand extends SpliceCommand {
    public static final Parcelable.Creator<SpliceInsertCommand> CREATOR = new Parcelable.Creator<SpliceInsertCommand>() {
        public SpliceInsertCommand createFromParcel(Parcel parcel) {
            return new SpliceInsertCommand(parcel);
        }

        public SpliceInsertCommand[] newArray(int i3) {
            return new SpliceInsertCommand[i3];
        }
    };
    public final boolean autoReturn;
    public final int availNum;
    public final int availsExpected;
    public final long breakDurationUs;
    public final List<ComponentSplice> componentSpliceList;
    public final boolean outOfNetworkIndicator;
    public final boolean programSpliceFlag;
    public final long programSplicePlaybackPositionUs;
    public final long programSplicePts;
    public final boolean spliceEventCancelIndicator;
    public final long spliceEventId;
    public final boolean spliceImmediateFlag;
    public final int uniqueProgramId;

    public static final class ComponentSplice {
        public final long componentSplicePlaybackPositionUs;
        public final long componentSplicePts;
        public final int componentTag;

        public static ComponentSplice createFromParcel(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong(), parcel.readLong());
        }

        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.componentTag);
            parcel.writeLong(this.componentSplicePts);
            parcel.writeLong(this.componentSplicePlaybackPositionUs);
        }

        private ComponentSplice(int i3, long j2, long j3) {
            this.componentTag = i3;
            this.componentSplicePts = j2;
            this.componentSplicePlaybackPositionUs = j3;
        }
    }

    public static SpliceInsertCommand parseFromSection(ParsableByteArray parsableByteArray, long j2, TimestampAdjuster timestampAdjuster) {
        boolean z2;
        int i3;
        int i4;
        int i5;
        long j3;
        boolean z3;
        List list;
        long j4;
        boolean z4;
        boolean z5;
        long j5;
        boolean z6;
        TimestampAdjuster timestampAdjuster2 = timestampAdjuster;
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        boolean z7 = (parsableByteArray.readUnsignedByte() & 128) != 0;
        List emptyList = Collections.emptyList();
        if (!z7) {
            int readUnsignedByte = parsableByteArray.readUnsignedByte();
            boolean z8 = (readUnsignedByte & 128) != 0;
            boolean z9 = (readUnsignedByte & 64) != 0;
            boolean z10 = (readUnsignedByte & 32) != 0;
            boolean z11 = (readUnsignedByte & 16) != 0;
            long parseSpliceTime = (!z9 || z11) ? C.TIME_UNSET : TimeSignalCommand.parseSpliceTime(parsableByteArray, j2);
            if (!z9) {
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                ArrayList arrayList = new ArrayList(readUnsignedByte2);
                for (int i6 = 0; i6 < readUnsignedByte2; i6++) {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    long parseSpliceTime2 = !z11 ? TimeSignalCommand.parseSpliceTime(parsableByteArray, j2) : C.TIME_UNSET;
                    arrayList.add(new ComponentSplice(readUnsignedByte3, parseSpliceTime2, timestampAdjuster2.adjustTsTimestamp(parseSpliceTime2)));
                }
                emptyList = arrayList;
            }
            if (z10) {
                long readUnsignedByte4 = (long) parsableByteArray.readUnsignedByte();
                boolean z12 = (128 & readUnsignedByte4) != 0;
                j5 = ((((readUnsignedByte4 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                z6 = z12;
            } else {
                z6 = false;
                j5 = C.TIME_UNSET;
            }
            i5 = parsableByteArray.readUnsignedShort();
            z2 = z9;
            i4 = parsableByteArray.readUnsignedByte();
            i3 = parsableByteArray.readUnsignedByte();
            list = emptyList;
            long j6 = parseSpliceTime;
            z3 = z6;
            j3 = j5;
            z4 = z11;
            z5 = z8;
            j4 = j6;
        } else {
            list = emptyList;
            z5 = false;
            z4 = false;
            j4 = C.TIME_UNSET;
            z3 = false;
            j3 = C.TIME_UNSET;
            i5 = 0;
            i4 = 0;
            i3 = 0;
            z2 = false;
        }
        return new SpliceInsertCommand(readUnsignedInt, z7, z5, z2, z4, j4, timestampAdjuster2.adjustTsTimestamp(j4), list, z3, j3, i5, i4, i3);
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeLong(this.spliceEventId);
        parcel.writeByte(this.spliceEventCancelIndicator ? (byte) 1 : 0);
        parcel.writeByte(this.outOfNetworkIndicator ? (byte) 1 : 0);
        parcel.writeByte(this.programSpliceFlag ? (byte) 1 : 0);
        parcel.writeByte(this.spliceImmediateFlag ? (byte) 1 : 0);
        parcel.writeLong(this.programSplicePts);
        parcel.writeLong(this.programSplicePlaybackPositionUs);
        int size = this.componentSpliceList.size();
        parcel.writeInt(size);
        for (int i4 = 0; i4 < size; i4++) {
            this.componentSpliceList.get(i4).writeToParcel(parcel);
        }
        parcel.writeByte(this.autoReturn ? (byte) 1 : 0);
        parcel.writeLong(this.breakDurationUs);
        parcel.writeInt(this.uniqueProgramId);
        parcel.writeInt(this.availNum);
        parcel.writeInt(this.availsExpected);
    }

    private SpliceInsertCommand(long j2, boolean z2, boolean z3, boolean z4, boolean z5, long j3, long j4, List<ComponentSplice> list, boolean z6, long j5, int i3, int i4, int i5) {
        this.spliceEventId = j2;
        this.spliceEventCancelIndicator = z2;
        this.outOfNetworkIndicator = z3;
        this.programSpliceFlag = z4;
        this.spliceImmediateFlag = z5;
        this.programSplicePts = j3;
        this.programSplicePlaybackPositionUs = j4;
        this.componentSpliceList = Collections.unmodifiableList(list);
        this.autoReturn = z6;
        this.breakDurationUs = j5;
        this.uniqueProgramId = i3;
        this.availNum = i4;
        this.availsExpected = i5;
    }

    private SpliceInsertCommand(Parcel parcel) {
        this.spliceEventId = parcel.readLong();
        boolean z2 = false;
        this.spliceEventCancelIndicator = parcel.readByte() == 1;
        this.outOfNetworkIndicator = parcel.readByte() == 1;
        this.programSpliceFlag = parcel.readByte() == 1;
        this.spliceImmediateFlag = parcel.readByte() == 1;
        this.programSplicePts = parcel.readLong();
        this.programSplicePlaybackPositionUs = parcel.readLong();
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            arrayList.add(ComponentSplice.createFromParcel(parcel));
        }
        this.componentSpliceList = Collections.unmodifiableList(arrayList);
        this.autoReturn = parcel.readByte() == 1 ? true : z2;
        this.breakDurationUs = parcel.readLong();
        this.uniqueProgramId = parcel.readInt();
        this.availNum = parcel.readInt();
        this.availsExpected = parcel.readInt();
    }
}
