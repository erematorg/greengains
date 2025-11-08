package com.appsamurai.storyly.exoplayer2.extractor.metadata.dvbsi;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;

public final class AppInfoTable implements Metadata.Entry {
    public static final int CONTROL_CODE_AUTOSTART = 1;
    public static final int CONTROL_CODE_PRESENT = 2;
    public static final Parcelable.Creator<AppInfoTable> CREATOR = new Parcelable.Creator<AppInfoTable>() {
        public AppInfoTable createFromParcel(Parcel parcel) {
            return new AppInfoTable(parcel.readInt(), (String) Assertions.checkNotNull(parcel.readString()));
        }

        public AppInfoTable[] newArray(int i3) {
            return new AppInfoTable[i3];
        }
    };
    public final int controlCode;
    public final String url;

    public AppInfoTable(int i3, String str) {
        this.controlCode = i3;
        this.url = str;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Ait(controlCode=");
        sb.append(this.controlCode);
        sb.append(",url=");
        return a.n(sb, this.url, ")");
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.url);
        parcel.writeInt(this.controlCode);
    }
}
