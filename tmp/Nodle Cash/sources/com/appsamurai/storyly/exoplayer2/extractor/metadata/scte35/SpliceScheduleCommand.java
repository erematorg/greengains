package com.appsamurai.storyly.exoplayer2.extractor.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceScheduleCommand extends SpliceCommand {
    public static final Parcelable.Creator<SpliceScheduleCommand> CREATOR = new Parcelable.Creator<SpliceScheduleCommand>() {
        public SpliceScheduleCommand createFromParcel(Parcel parcel) {
            return new SpliceScheduleCommand(parcel);
        }

        public SpliceScheduleCommand[] newArray(int i3) {
            return new SpliceScheduleCommand[i3];
        }
    };
    public final List<Event> events;

    public static final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        /* access modifiers changed from: private */
        public static ComponentSplice createFromParcel(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong());
        }

        /* access modifiers changed from: private */
        public void writeToParcel(Parcel parcel) {
            parcel.writeInt(this.componentTag);
            parcel.writeLong(this.utcSpliceTime);
        }

        private ComponentSplice(int i3, long j2) {
            this.componentTag = i3;
            this.utcSpliceTime = j2;
        }
    }

    public static SpliceScheduleCommand parseFromSection(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        ArrayList arrayList = new ArrayList(readUnsignedByte);
        for (int i3 = 0; i3 < readUnsignedByte; i3++) {
            arrayList.add(Event.parseFromSection(parsableByteArray));
        }
        return new SpliceScheduleCommand((List<Event>) arrayList);
    }

    public void writeToParcel(Parcel parcel, int i3) {
        int size = this.events.size();
        parcel.writeInt(size);
        for (int i4 = 0; i4 < size; i4++) {
            this.events.get(i4).writeToParcel(parcel);
        }
    }

    private SpliceScheduleCommand(List<Event> list) {
        this.events = Collections.unmodifiableList(list);
    }

    private SpliceScheduleCommand(Parcel parcel) {
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            arrayList.add(Event.createFromParcel(parcel));
        }
        this.events = Collections.unmodifiableList(arrayList);
    }

    public static final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List<ComponentSplice> componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long j2, boolean z2, boolean z3, boolean z4, List<ComponentSplice> list, long j3, boolean z5, long j4, int i3, int i4, int i5) {
            this.spliceEventId = j2;
            this.spliceEventCancelIndicator = z2;
            this.outOfNetworkIndicator = z3;
            this.programSpliceFlag = z4;
            this.componentSpliceList = Collections.unmodifiableList(list);
            this.utcSpliceTime = j3;
            this.autoReturn = z5;
            this.breakDurationUs = j4;
            this.uniqueProgramId = i3;
            this.availNum = i4;
            this.availsExpected = i5;
        }

        /* access modifiers changed from: private */
        public static Event createFromParcel(Parcel parcel) {
            return new Event(parcel);
        }

        /* access modifiers changed from: private */
        public static Event parseFromSection(ParsableByteArray parsableByteArray) {
            boolean z2;
            int i3;
            int i4;
            int i5;
            long j2;
            boolean z3;
            long j3;
            ArrayList arrayList;
            boolean z4;
            long j4;
            boolean z5;
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            boolean z6 = (parsableByteArray.readUnsignedByte() & 128) != 0;
            ArrayList arrayList2 = new ArrayList();
            if (!z6) {
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                boolean z7 = (readUnsignedByte & 128) != 0;
                boolean z8 = (readUnsignedByte & 64) != 0;
                boolean z9 = (readUnsignedByte & 32) != 0;
                long readUnsignedInt2 = z8 ? parsableByteArray.readUnsignedInt() : C.TIME_UNSET;
                if (!z8) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    ArrayList arrayList3 = new ArrayList(readUnsignedByte2);
                    for (int i6 = 0; i6 < readUnsignedByte2; i6++) {
                        arrayList3.add(new ComponentSplice(parsableByteArray.readUnsignedByte(), parsableByteArray.readUnsignedInt()));
                    }
                    arrayList2 = arrayList3;
                }
                if (z9) {
                    long readUnsignedByte3 = (long) parsableByteArray.readUnsignedByte();
                    boolean z10 = (128 & readUnsignedByte3) != 0;
                    j4 = ((((readUnsignedByte3 & 1) << 32) | parsableByteArray.readUnsignedInt()) * 1000) / 90;
                    z5 = z10;
                } else {
                    z5 = false;
                    j4 = C.TIME_UNSET;
                }
                int readUnsignedShort = parsableByteArray.readUnsignedShort();
                int readUnsignedByte4 = parsableByteArray.readUnsignedByte();
                z2 = z8;
                i3 = parsableByteArray.readUnsignedByte();
                j2 = j4;
                arrayList = arrayList2;
                long j5 = readUnsignedInt2;
                i5 = readUnsignedShort;
                i4 = readUnsignedByte4;
                j3 = j5;
                boolean z11 = z7;
                z3 = z5;
                z4 = z11;
            } else {
                arrayList = arrayList2;
                z4 = false;
                j3 = C.TIME_UNSET;
                z3 = false;
                j2 = C.TIME_UNSET;
                i5 = 0;
                i4 = 0;
                i3 = 0;
                z2 = false;
            }
            return new Event(readUnsignedInt, z6, z4, z2, arrayList, j3, z3, j2, i5, i4, i3);
        }

        /* access modifiers changed from: private */
        public void writeToParcel(Parcel parcel) {
            parcel.writeLong(this.spliceEventId);
            parcel.writeByte(this.spliceEventCancelIndicator ? (byte) 1 : 0);
            parcel.writeByte(this.outOfNetworkIndicator ? (byte) 1 : 0);
            parcel.writeByte(this.programSpliceFlag ? (byte) 1 : 0);
            int size = this.componentSpliceList.size();
            parcel.writeInt(size);
            for (int i3 = 0; i3 < size; i3++) {
                this.componentSpliceList.get(i3).writeToParcel(parcel);
            }
            parcel.writeLong(this.utcSpliceTime);
            parcel.writeByte(this.autoReturn ? (byte) 1 : 0);
            parcel.writeLong(this.breakDurationUs);
            parcel.writeInt(this.uniqueProgramId);
            parcel.writeInt(this.availNum);
            parcel.writeInt(this.availsExpected);
        }

        private Event(Parcel parcel) {
            this.spliceEventId = parcel.readLong();
            boolean z2 = false;
            this.spliceEventCancelIndicator = parcel.readByte() == 1;
            this.outOfNetworkIndicator = parcel.readByte() == 1;
            this.programSpliceFlag = parcel.readByte() == 1;
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i3 = 0; i3 < readInt; i3++) {
                arrayList.add(ComponentSplice.createFromParcel(parcel));
            }
            this.componentSpliceList = Collections.unmodifiableList(arrayList);
            this.utcSpliceTime = parcel.readLong();
            this.autoReturn = parcel.readByte() == 1 ? true : z2;
            this.breakDurationUs = parcel.readLong();
            this.uniqueProgramId = parcel.readInt();
            this.availNum = parcel.readInt();
            this.availsExpected = parcel.readInt();
        }
    }
}
