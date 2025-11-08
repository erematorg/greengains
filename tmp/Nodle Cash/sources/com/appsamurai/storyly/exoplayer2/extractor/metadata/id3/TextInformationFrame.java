package com.appsamurai.storyly.exoplayer2.extractor.metadata.id3;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.ArrayList;
import java.util.List;

public final class TextInformationFrame extends Id3Frame {
    public static final Parcelable.Creator<TextInformationFrame> CREATOR = new Parcelable.Creator<TextInformationFrame>() {
        public TextInformationFrame createFromParcel(Parcel parcel) {
            return new TextInformationFrame(parcel);
        }

        public TextInformationFrame[] newArray(int i3) {
            return new TextInformationFrame[i3];
        }
    };
    @Nullable
    public final String description;
    public final String value;

    public TextInformationFrame(String str, @Nullable String str2, String str3) {
        super(str);
        this.description = str2;
        this.value = str3;
    }

    private static List<Integer> parseId3v2point4TimestampFrameForDate(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (str.length() >= 10) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(8, 10))));
            } else if (str.length() >= 7) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(5, 7))));
            } else if (str.length() >= 4) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 4))));
            }
            return arrayList;
        } catch (NumberFormatException unused) {
            return new ArrayList();
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TextInformationFrame.class != obj.getClass()) {
            return false;
        }
        TextInformationFrame textInformationFrame = (TextInformationFrame) obj;
        return Util.areEqual(this.id, textInformationFrame.id) && Util.areEqual(this.description, textInformationFrame.description) && Util.areEqual(this.value, textInformationFrame.value);
    }

    public int hashCode() {
        int i3 = a.i(this.id, 527, 31);
        String str = this.description;
        int i4 = 0;
        int hashCode = (i3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.value;
        if (str2 != null) {
            i4 = str2.hashCode();
        }
        return hashCode + i4;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populateMediaMetadata(com.appsamurai.storyly.exoplayer2.common.MediaMetadata.Builder r9) {
        /*
            r8 = this;
            r0 = 4
            java.lang.String r1 = r8.id
            r1.getClass()
            r2 = 3
            r3 = 0
            r4 = 2
            r5 = 1
            r6 = -1
            int r7 = r1.hashCode()
            switch(r7) {
                case 82815: goto L_0x0125;
                case 82878: goto L_0x011a;
                case 82897: goto L_0x010f;
                case 83253: goto L_0x0104;
                case 83254: goto L_0x00f9;
                case 83255: goto L_0x00ee;
                case 83341: goto L_0x00e3;
                case 83378: goto L_0x00d8;
                case 83536: goto L_0x00ca;
                case 83552: goto L_0x00bc;
                case 2567331: goto L_0x00ae;
                case 2569357: goto L_0x00a0;
                case 2569891: goto L_0x0092;
                case 2570401: goto L_0x0084;
                case 2570410: goto L_0x0076;
                case 2571565: goto L_0x0068;
                case 2575251: goto L_0x005a;
                case 2581512: goto L_0x004c;
                case 2581513: goto L_0x003e;
                case 2581514: goto L_0x0030;
                case 2583398: goto L_0x0022;
                case 2590194: goto L_0x0014;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x012f
        L_0x0014:
            java.lang.String r7 = "TYER"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x001e
            goto L_0x012f
        L_0x001e:
            r6 = 21
            goto L_0x012f
        L_0x0022:
            java.lang.String r7 = "TRCK"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x002c
            goto L_0x012f
        L_0x002c:
            r6 = 20
            goto L_0x012f
        L_0x0030:
            java.lang.String r7 = "TPE3"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x003a
            goto L_0x012f
        L_0x003a:
            r6 = 19
            goto L_0x012f
        L_0x003e:
            java.lang.String r7 = "TPE2"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0048
            goto L_0x012f
        L_0x0048:
            r6 = 18
            goto L_0x012f
        L_0x004c:
            java.lang.String r7 = "TPE1"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0056
            goto L_0x012f
        L_0x0056:
            r6 = 17
            goto L_0x012f
        L_0x005a:
            java.lang.String r7 = "TIT2"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0064
            goto L_0x012f
        L_0x0064:
            r6 = 16
            goto L_0x012f
        L_0x0068:
            java.lang.String r7 = "TEXT"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0072
            goto L_0x012f
        L_0x0072:
            r6 = 15
            goto L_0x012f
        L_0x0076:
            java.lang.String r7 = "TDRL"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0080
            goto L_0x012f
        L_0x0080:
            r6 = 14
            goto L_0x012f
        L_0x0084:
            java.lang.String r7 = "TDRC"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x008e
            goto L_0x012f
        L_0x008e:
            r6 = 13
            goto L_0x012f
        L_0x0092:
            java.lang.String r7 = "TDAT"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x009c
            goto L_0x012f
        L_0x009c:
            r6 = 12
            goto L_0x012f
        L_0x00a0:
            java.lang.String r7 = "TCOM"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00aa
            goto L_0x012f
        L_0x00aa:
            r6 = 11
            goto L_0x012f
        L_0x00ae:
            java.lang.String r7 = "TALB"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00b8
            goto L_0x012f
        L_0x00b8:
            r6 = 10
            goto L_0x012f
        L_0x00bc:
            java.lang.String r7 = "TYE"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00c6
            goto L_0x012f
        L_0x00c6:
            r6 = 9
            goto L_0x012f
        L_0x00ca:
            java.lang.String r7 = "TXT"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00d4
            goto L_0x012f
        L_0x00d4:
            r6 = 8
            goto L_0x012f
        L_0x00d8:
            java.lang.String r7 = "TT2"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00e1
            goto L_0x012f
        L_0x00e1:
            r6 = 7
            goto L_0x012f
        L_0x00e3:
            java.lang.String r7 = "TRK"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00ec
            goto L_0x012f
        L_0x00ec:
            r6 = 6
            goto L_0x012f
        L_0x00ee:
            java.lang.String r7 = "TP3"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00f7
            goto L_0x012f
        L_0x00f7:
            r6 = 5
            goto L_0x012f
        L_0x00f9:
            java.lang.String r7 = "TP2"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0102
            goto L_0x012f
        L_0x0102:
            r6 = r0
            goto L_0x012f
        L_0x0104:
            java.lang.String r7 = "TP1"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x010d
            goto L_0x012f
        L_0x010d:
            r6 = r2
            goto L_0x012f
        L_0x010f:
            java.lang.String r7 = "TDA"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0118
            goto L_0x012f
        L_0x0118:
            r6 = r4
            goto L_0x012f
        L_0x011a:
            java.lang.String r7 = "TCM"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0123
            goto L_0x012f
        L_0x0123:
            r6 = r5
            goto L_0x012f
        L_0x0125:
            java.lang.String r7 = "TAL"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x012e
            goto L_0x012f
        L_0x012e:
            r6 = r3
        L_0x012f:
            switch(r6) {
                case 0: goto L_0x0213;
                case 1: goto L_0x020d;
                case 2: goto L_0x01e9;
                case 3: goto L_0x01e3;
                case 4: goto L_0x01dd;
                case 5: goto L_0x01d7;
                case 6: goto L_0x01ae;
                case 7: goto L_0x01a8;
                case 8: goto L_0x01a1;
                case 9: goto L_0x0192;
                case 10: goto L_0x0213;
                case 11: goto L_0x020d;
                case 12: goto L_0x01e9;
                case 13: goto L_0x0163;
                case 14: goto L_0x0134;
                case 15: goto L_0x01a1;
                case 16: goto L_0x01a8;
                case 17: goto L_0x01e3;
                case 18: goto L_0x01dd;
                case 19: goto L_0x01d7;
                case 20: goto L_0x01ae;
                case 21: goto L_0x0192;
                default: goto L_0x0132;
            }
        L_0x0132:
            goto L_0x0218
        L_0x0134:
            java.lang.String r8 = r8.value
            java.util.List r8 = parseId3v2point4TimestampFrameForDate(r8)
            int r0 = r8.size()
            if (r0 == r5) goto L_0x0158
            if (r0 == r4) goto L_0x014f
            if (r0 == r2) goto L_0x0146
            goto L_0x0218
        L_0x0146:
            java.lang.Object r0 = r8.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setReleaseDay(r0)
        L_0x014f:
            java.lang.Object r0 = r8.get(r5)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setReleaseMonth(r0)
        L_0x0158:
            java.lang.Object r8 = r8.get(r3)
            java.lang.Integer r8 = (java.lang.Integer) r8
            r9.setReleaseYear(r8)
            goto L_0x0218
        L_0x0163:
            java.lang.String r8 = r8.value
            java.util.List r8 = parseId3v2point4TimestampFrameForDate(r8)
            int r0 = r8.size()
            if (r0 == r5) goto L_0x0187
            if (r0 == r4) goto L_0x017e
            if (r0 == r2) goto L_0x0175
            goto L_0x0218
        L_0x0175:
            java.lang.Object r0 = r8.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setRecordingDay(r0)
        L_0x017e:
            java.lang.Object r0 = r8.get(r5)
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9.setRecordingMonth(r0)
        L_0x0187:
            java.lang.Object r8 = r8.get(r3)
            java.lang.Integer r8 = (java.lang.Integer) r8
            r9.setRecordingYear(r8)
            goto L_0x0218
        L_0x0192:
            java.lang.String r8 = r8.value     // Catch:{  }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{  }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{  }
            r9.setRecordingYear(r8)     // Catch:{  }
            goto L_0x0218
        L_0x01a1:
            java.lang.String r8 = r8.value
            r9.setWriter(r8)
            goto L_0x0218
        L_0x01a8:
            java.lang.String r8 = r8.value
            r9.setTitle(r8)
            goto L_0x0218
        L_0x01ae:
            java.lang.String r8 = r8.value
            java.lang.String r0 = "/"
            java.lang.String[] r8 = com.appsamurai.storyly.exoplayer2.common.util.Util.split(r8, r0)
            r0 = r8[r3]     // Catch:{  }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{  }
            int r1 = r8.length     // Catch:{  }
            if (r1 <= r5) goto L_0x01ca
            r8 = r8[r5]     // Catch:{  }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{  }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{  }
            goto L_0x01cb
        L_0x01ca:
            r8 = 0
        L_0x01cb:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{  }
            com.appsamurai.storyly.exoplayer2.common.MediaMetadata$Builder r9 = r9.setTrackNumber(r0)     // Catch:{  }
            r9.setTotalTrackCount(r8)     // Catch:{  }
            goto L_0x0218
        L_0x01d7:
            java.lang.String r8 = r8.value
            r9.setConductor(r8)
            goto L_0x0218
        L_0x01dd:
            java.lang.String r8 = r8.value
            r9.setAlbumArtist(r8)
            goto L_0x0218
        L_0x01e3:
            java.lang.String r8 = r8.value
            r9.setArtist(r8)
            goto L_0x0218
        L_0x01e9:
            java.lang.String r1 = r8.value     // Catch:{ NumberFormatException -> 0x0218 }
            java.lang.String r0 = r1.substring(r4, r0)     // Catch:{ NumberFormatException -> 0x0218 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0218 }
            java.lang.String r8 = r8.value     // Catch:{ NumberFormatException -> 0x0218 }
            java.lang.String r8 = r8.substring(r3, r4)     // Catch:{ NumberFormatException -> 0x0218 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x0218 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ NumberFormatException -> 0x0218 }
            com.appsamurai.storyly.exoplayer2.common.MediaMetadata$Builder r9 = r9.setRecordingMonth(r0)     // Catch:{ NumberFormatException -> 0x0218 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NumberFormatException -> 0x0218 }
            r9.setRecordingDay(r8)     // Catch:{ NumberFormatException -> 0x0218 }
            goto L_0x0218
        L_0x020d:
            java.lang.String r8 = r8.value
            r9.setComposer(r8)
            goto L_0x0218
        L_0x0213:
            java.lang.String r8 = r8.value
            r9.setAlbumTitle(r8)
        L_0x0218:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.metadata.id3.TextInformationFrame.populateMediaMetadata(com.appsamurai.storyly.exoplayer2.common.MediaMetadata$Builder):void");
    }

    public String toString() {
        return this.id + ": description=" + this.description + ": value=" + this.value;
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.id);
        parcel.writeString(this.description);
        parcel.writeString(this.value);
    }

    public TextInformationFrame(Parcel parcel) {
        super((String) Util.castNonNull(parcel.readString()));
        this.description = parcel.readString();
        this.value = (String) Util.castNonNull(parcel.readString());
    }
}
