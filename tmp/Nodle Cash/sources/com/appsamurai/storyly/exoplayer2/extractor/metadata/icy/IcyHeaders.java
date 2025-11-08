package com.appsamurai.storyly.exoplayer2.extractor.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.MediaMetadata;
import com.appsamurai.storyly.exoplayer2.common.metadata.Metadata;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;

public final class IcyHeaders implements Metadata.Entry {
    public static final Parcelable.Creator<IcyHeaders> CREATOR = new Parcelable.Creator<IcyHeaders>() {
        public IcyHeaders createFromParcel(Parcel parcel) {
            return new IcyHeaders(parcel);
        }

        public IcyHeaders[] newArray(int i3) {
            return new IcyHeaders[i3];
        }
    };
    public static final String REQUEST_HEADER_ENABLE_METADATA_NAME = "Icy-MetaData";
    public static final String REQUEST_HEADER_ENABLE_METADATA_VALUE = "1";
    private static final String RESPONSE_HEADER_BITRATE = "icy-br";
    private static final String RESPONSE_HEADER_GENRE = "icy-genre";
    private static final String RESPONSE_HEADER_METADATA_INTERVAL = "icy-metaint";
    private static final String RESPONSE_HEADER_NAME = "icy-name";
    private static final String RESPONSE_HEADER_PUB = "icy-pub";
    private static final String RESPONSE_HEADER_URL = "icy-url";
    private static final String TAG = "IcyHeaders";
    public final int bitrate;
    @Nullable
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    @Nullable
    public final String name;
    @Nullable
    public final String url;

    public IcyHeaders(int i3, @Nullable String str, @Nullable String str2, @Nullable String str3, boolean z2, int i4) {
        Assertions.checkArgument(i4 == -1 || i4 > 0);
        this.bitrate = i3;
        this.genre = str;
        this.name = str2;
        this.url = str3;
        this.isPublic = z2;
        this.metadataInterval = i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders parse(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r14) {
        /*
            java.lang.String r0 = "Invalid metadata interval: "
            java.lang.String r1 = "Invalid bitrate: "
            java.lang.String r2 = "icy-br"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r3 = "IcyHeaders"
            r4 = 1
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x003e
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            int r7 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            int r7 = r7 * 1000
            if (r7 <= 0) goto L_0x0023
            r1 = r4
            goto L_0x0034
        L_0x0023:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0037 }
            r8.<init>(r1)     // Catch:{ NumberFormatException -> 0x0037 }
            r8.append(r2)     // Catch:{ NumberFormatException -> 0x0037 }
            java.lang.String r1 = r8.toString()     // Catch:{ NumberFormatException -> 0x0037 }
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r1)     // Catch:{ NumberFormatException -> 0x0037 }
            r1 = r5
            r7 = r6
        L_0x0034:
            r8 = r7
            goto L_0x0040
        L_0x0036:
            r7 = r6
        L_0x0037:
            java.lang.String r1 = "Invalid bitrate header: "
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r1, r2, r3)
            r1 = r5
            goto L_0x0034
        L_0x003e:
            r1 = r5
            r8 = r6
        L_0x0040:
            java.lang.String r2 = "icy-genre"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            r7 = 0
            if (r2 == 0) goto L_0x0054
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r9 = r1
            r1 = r4
            goto L_0x0055
        L_0x0054:
            r9 = r7
        L_0x0055:
            java.lang.String r2 = "icy-name"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0068
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r10 = r1
            r1 = r4
            goto L_0x0069
        L_0x0068:
            r10 = r7
        L_0x0069:
            java.lang.String r2 = "icy-url"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x007c
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r11 = r1
            r1 = r4
            goto L_0x007d
        L_0x007c:
            r11 = r7
        L_0x007d:
            java.lang.String r2 = "icy-pub"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0096
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            r12 = r1
            r1 = r4
            goto L_0x0097
        L_0x0096:
            r12 = r5
        L_0x0097:
            java.lang.String r2 = "icy-metaint"
            java.lang.Object r14 = r14.get(r2)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x00c0
            java.lang.Object r14 = r14.get(r5)
            java.lang.String r14 = (java.lang.String) r14
            int r2 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00c3 }
            if (r2 <= 0) goto L_0x00af
            r6 = r2
            goto L_0x00bf
        L_0x00af:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00c2 }
            r4.<init>(r0)     // Catch:{ NumberFormatException -> 0x00c2 }
            r4.append(r14)     // Catch:{ NumberFormatException -> 0x00c2 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x00c2 }
            com.appsamurai.storyly.exoplayer2.common.util.Log.w(r3, r4)     // Catch:{ NumberFormatException -> 0x00c2 }
            r4 = r1
        L_0x00bf:
            r1 = r4
        L_0x00c0:
            r13 = r6
            goto L_0x00c7
        L_0x00c2:
            r6 = r2
        L_0x00c3:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.h.a(r0, r14, r3)
            goto L_0x00c0
        L_0x00c7:
            if (r1 == 0) goto L_0x00cf
            com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders r14 = new com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders
            r7 = r14
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x00cf:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders.parse(java.util.Map):com.appsamurai.storyly.exoplayer2.extractor.metadata.icy.IcyHeaders");
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IcyHeaders.class != obj.getClass()) {
            return false;
        }
        IcyHeaders icyHeaders = (IcyHeaders) obj;
        return this.bitrate == icyHeaders.bitrate && Util.areEqual(this.genre, icyHeaders.genre) && Util.areEqual(this.name, icyHeaders.name) && Util.areEqual(this.url, icyHeaders.url) && this.isPublic == icyHeaders.isPublic && this.metadataInterval == icyHeaders.metadataInterval;
    }

    public int hashCode() {
        int i3 = (527 + this.bitrate) * 31;
        String str = this.genre;
        int i4 = 0;
        int hashCode = (i3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        if (str3 != null) {
            i4 = str3.hashCode();
        }
        return ((((hashCode2 + i4) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.name;
        if (str != null) {
            builder.setStation(str);
        }
        String str2 = this.genre;
        if (str2 != null) {
            builder.setGenre(str2);
        }
    }

    public String toString() {
        return "IcyHeaders: name=\"" + this.name + "\", genre=\"" + this.genre + "\", bitrate=" + this.bitrate + ", metadataInterval=" + this.metadataInterval;
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.genre);
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        Util.writeBoolean(parcel, this.isPublic);
        parcel.writeInt(this.metadataInterval);
    }

    public IcyHeaders(Parcel parcel) {
        this.bitrate = parcel.readInt();
        this.genre = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.isPublic = Util.readBoolean(parcel);
        this.metadataInterval = parcel.readInt();
    }
}
