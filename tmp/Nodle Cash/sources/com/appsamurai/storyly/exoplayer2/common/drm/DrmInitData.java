package com.appsamurai.storyly.exoplayer2.common.drm;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import androidx.compose.animation.core.a;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public final class DrmInitData implements Comparator<SchemeData>, Parcelable {
    public static final Parcelable.Creator<DrmInitData> CREATOR = new Parcelable.Creator<DrmInitData>() {
        public DrmInitData createFromParcel(Parcel parcel) {
            return new DrmInitData(parcel);
        }

        public DrmInitData[] newArray(int i3) {
            return new DrmInitData[i3];
        }
    };
    private int hashCode;
    public final int schemeDataCount;
    private final SchemeData[] schemeDatas;
    @Nullable
    public final String schemeType;

    public static final class SchemeData implements Parcelable {
        public static final Parcelable.Creator<SchemeData> CREATOR = new Parcelable.Creator<SchemeData>() {
            public SchemeData createFromParcel(Parcel parcel) {
                return new SchemeData(parcel);
            }

            public SchemeData[] newArray(int i3) {
                return new SchemeData[i3];
            }
        };
        @Nullable
        public final byte[] data;
        private int hashCode;
        @Nullable
        public final String licenseServerUrl;
        public final String mimeType;
        public final UUID uuid;

        public SchemeData(UUID uuid2, String str, @Nullable byte[] bArr) {
            this(uuid2, (String) null, str, bArr);
        }

        public boolean canReplace(SchemeData schemeData) {
            return hasData() && !schemeData.hasData() && matches(schemeData.uuid);
        }

        public SchemeData copyWithData(@Nullable byte[] bArr) {
            return new SchemeData(this.uuid, this.licenseServerUrl, this.mimeType, bArr);
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof SchemeData)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            SchemeData schemeData = (SchemeData) obj;
            return Util.areEqual(this.licenseServerUrl, schemeData.licenseServerUrl) && Util.areEqual(this.mimeType, schemeData.mimeType) && Util.areEqual(this.uuid, schemeData.uuid) && Arrays.equals(this.data, schemeData.data);
        }

        public boolean hasData() {
            return this.data != null;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int hashCode2 = this.uuid.hashCode() * 31;
                String str = this.licenseServerUrl;
                this.hashCode = Arrays.hashCode(this.data) + a.i(this.mimeType, (hashCode2 + (str == null ? 0 : str.hashCode())) * 31, 31);
            }
            return this.hashCode;
        }

        public boolean matches(UUID uuid2) {
            return C.UUID_NIL.equals(this.uuid) || uuid2.equals(this.uuid);
        }

        public void writeToParcel(Parcel parcel, int i3) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.licenseServerUrl);
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
        }

        public SchemeData(UUID uuid2, @Nullable String str, String str2, @Nullable byte[] bArr) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid2);
            this.licenseServerUrl = str;
            this.mimeType = (String) Assertions.checkNotNull(str2);
            this.data = bArr;
        }

        public SchemeData(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.licenseServerUrl = parcel.readString();
            this.mimeType = (String) Util.castNonNull(parcel.readString());
            this.data = parcel.createByteArray();
        }
    }

    public DrmInitData(List<SchemeData> list) {
        this((String) null, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    private static boolean containsSchemeDataWithUuid(ArrayList<SchemeData> arrayList, int i3, UUID uuid) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (arrayList.get(i4).uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static DrmInitData createSessionCreationData(@Nullable DrmInitData drmInitData, @Nullable DrmInitData drmInitData2) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (drmInitData != null) {
            str = drmInitData.schemeType;
            for (SchemeData schemeData : drmInitData.schemeDatas) {
                if (schemeData.hasData()) {
                    arrayList.add(schemeData);
                }
            }
        } else {
            str = null;
        }
        if (drmInitData2 != null) {
            if (str == null) {
                str = drmInitData2.schemeType;
            }
            int size = arrayList.size();
            for (SchemeData schemeData2 : drmInitData2.schemeDatas) {
                if (schemeData2.hasData() && !containsSchemeDataWithUuid(arrayList, size, schemeData2.uuid)) {
                    arrayList.add(schemeData2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new DrmInitData(str, (List<SchemeData>) arrayList);
    }

    public DrmInitData copyWithSchemeType(@Nullable String str) {
        return Util.areEqual(this.schemeType, str) ? this : new DrmInitData(str, false, this.schemeDatas);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DrmInitData.class != obj.getClass()) {
            return false;
        }
        DrmInitData drmInitData = (DrmInitData) obj;
        return Util.areEqual(this.schemeType, drmInitData.schemeType) && Arrays.equals(this.schemeDatas, drmInitData.schemeDatas);
    }

    public SchemeData get(int i3) {
        return this.schemeDatas[i3];
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.schemeType;
            this.hashCode = ((str == null ? 0 : str.hashCode()) * 31) + Arrays.hashCode(this.schemeDatas);
        }
        return this.hashCode;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r3.schemeType;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData merge(com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData r3) {
        /*
            r2 = this;
            java.lang.String r0 = r2.schemeType
            if (r0 == 0) goto L_0x0011
            java.lang.String r1 = r3.schemeType
            if (r1 == 0) goto L_0x0011
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 == 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            r0 = 0
            goto L_0x0012
        L_0x0011:
            r0 = 1
        L_0x0012:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r0)
            java.lang.String r0 = r2.schemeType
            if (r0 == 0) goto L_0x001a
            goto L_0x001c
        L_0x001a:
            java.lang.String r0 = r3.schemeType
        L_0x001c:
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[] r2 = r2.schemeDatas
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[] r3 = r3.schemeDatas
            java.lang.Object[] r2 = com.appsamurai.storyly.exoplayer2.common.util.Util.nullSafeArrayConcatenation(r2, r3)
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[] r2 = (com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.SchemeData[]) r2
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData r3 = new com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData
            r3.<init>((java.lang.String) r0, (com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.SchemeData[]) r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.merge(com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData):com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData");
    }

    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.schemeType);
        parcel.writeTypedArray(this.schemeDatas, 0);
    }

    public DrmInitData(@Nullable String str, List<SchemeData> list) {
        this(str, false, (SchemeData[]) list.toArray(new SchemeData[0]));
    }

    public int compare(SchemeData schemeData, SchemeData schemeData2) {
        UUID uuid = C.UUID_NIL;
        if (uuid.equals(schemeData.uuid)) {
            return uuid.equals(schemeData2.uuid) ? 0 : 1;
        }
        return schemeData.uuid.compareTo(schemeData2.uuid);
    }

    public DrmInitData(SchemeData... schemeDataArr) {
        this((String) null, schemeDataArr);
    }

    public DrmInitData(@Nullable String str, SchemeData... schemeDataArr) {
        this(str, true, schemeDataArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DrmInitData(@androidx.annotation.Nullable java.lang.String r1, boolean r2, com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.SchemeData... r3) {
        /*
            r0 = this;
            r0.<init>()
            r0.schemeType = r1
            if (r2 == 0) goto L_0x000e
            java.lang.Object r1 = r3.clone()
            r3 = r1
            com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[] r3 = (com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.SchemeData[]) r3
        L_0x000e:
            r0.schemeDatas = r3
            int r1 = r3.length
            r0.schemeDataCount = r1
            java.util.Arrays.sort(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData.<init>(java.lang.String, boolean, com.appsamurai.storyly.exoplayer2.common.drm.DrmInitData$SchemeData[]):void");
    }

    public DrmInitData(Parcel parcel) {
        this.schemeType = parcel.readString();
        SchemeData[] schemeDataArr = (SchemeData[]) Util.castNonNull((SchemeData[]) parcel.createTypedArray(SchemeData.CREATOR));
        this.schemeDatas = schemeDataArr;
        this.schemeDataCount = schemeDataArr.length;
    }
}
