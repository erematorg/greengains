package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
@KeepForSdk
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @NonNull
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR = new zaf();
    private static final Builder zaf = new zab(new String[0], (String) null);
    @SafeParcelable.VersionField(id = 1000)
    final int zaa;
    Bundle zab;
    int[] zac;
    int zad;
    boolean zae;
    @SafeParcelable.Field(getter = "getColumns", id = 1)
    private final String[] zag;
    @SafeParcelable.Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zah;
    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    private final int zai;
    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    @Nullable
    private final Bundle zaj;
    private boolean zak;

    @KeepForSdk
    public static class Builder {
        /* access modifiers changed from: private */
        public final String[] zaa;
        /* access modifiers changed from: private */
        public final ArrayList zab = new ArrayList();
        private final HashMap zac = new HashMap();

        public /* synthetic */ Builder(String[] strArr, String str, zac zac2) {
            this.zaa = (String[]) Preconditions.checkNotNull(strArr);
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i3) {
            return new DataHolder(this, i3);
        }

        @NonNull
        @KeepForSdk
        @CanIgnoreReturnValue
        public Builder withRow(@NonNull ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Map.Entry next : contentValues.valueSet()) {
                hashMap.put((String) next.getKey(), next.getValue());
            }
            return zaa(hashMap);
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder zaa(@NonNull HashMap hashMap) {
            Asserts.checkNotNull(hashMap);
            this.zab.add(hashMap);
            return this;
        }

        @NonNull
        @KeepForSdk
        public DataHolder build(int i3, @NonNull Bundle bundle) {
            return new DataHolder(this, i3, bundle);
        }
    }

    @NonNull
    @KeepForSdk
    public static Builder builder(@NonNull String[] strArr) {
        return new Builder(strArr, (String) null, (zac) null);
    }

    @NonNull
    @KeepForSdk
    public static DataHolder empty(int i3) {
        return new DataHolder(zaf, i3, (Bundle) null);
    }

    private final void zae(String str, int i3) {
        Bundle bundle = this.zab;
        if (bundle == null || !bundle.containsKey(str)) {
            throw new IllegalArgumentException("No such column: ".concat(String.valueOf(str)));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i3 < 0 || i3 >= this.zad) {
            throw new CursorIndexOutOfBoundsException(i3, this.zad);
        }
    }

    private static CursorWindow[] zaf(Builder builder, int i3) {
        if (builder.zaa.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zab2 = builder.zab;
        int size = zab2.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zaa.length);
        int i4 = 0;
        boolean z2 = false;
        while (i4 < size) {
            try {
                if (!cursorWindow.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i4 + ")");
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i4);
                    cursorWindow.setNumColumns(builder.zaa.length);
                    arrayList.add(cursorWindow);
                    if (!cursorWindow.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zab2.get(i4);
                int i5 = 0;
                boolean z3 = true;
                while (true) {
                    if (i5 < builder.zaa.length) {
                        if (!z3) {
                            break;
                        }
                        String str = builder.zaa[i5];
                        Object obj = map.get(str);
                        if (obj == null) {
                            z3 = cursorWindow.putNull(i4, i5);
                        } else if (obj instanceof String) {
                            z3 = cursorWindow.putString((String) obj, i4, i5);
                        } else if (obj instanceof Long) {
                            z3 = cursorWindow.putLong(((Long) obj).longValue(), i4, i5);
                        } else if (obj instanceof Integer) {
                            z3 = cursorWindow.putLong((long) ((Integer) obj).intValue(), i4, i5);
                        } else if (obj instanceof Boolean) {
                            z3 = cursorWindow.putLong(true != ((Boolean) obj).booleanValue() ? 0 : 1, i4, i5);
                        } else if (obj instanceof byte[]) {
                            z3 = cursorWindow.putBlob((byte[]) obj, i4, i5);
                        } else if (obj instanceof Double) {
                            z3 = cursorWindow.putDouble(((Double) obj).doubleValue(), i4, i5);
                        } else if (obj instanceof Float) {
                            z3 = cursorWindow.putDouble((double) ((Float) obj).floatValue(), i4, i5);
                        } else {
                            throw new IllegalArgumentException("Unsupported object for column " + str + ": " + obj.toString());
                        }
                        i5++;
                    } else if (z3) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i4 + " - allocating new window.");
                    cursorWindow.freeLastRow();
                    cursorWindow = new CursorWindow(false);
                    cursorWindow.setStartPosition(i4);
                    cursorWindow.setNumColumns(builder.zaa.length);
                    arrayList.add(cursorWindow);
                    i4--;
                    z2 = true;
                    i4++;
                } else {
                    throw new zad("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                }
            } catch (RuntimeException e3) {
                int size2 = arrayList.size();
                for (int i6 = 0; i6 < size2; i6++) {
                    ((CursorWindow) arrayList.get(i6)).close();
                }
                throw e3;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    @KeepForSdk
    public void close() {
        synchronized (this) {
            try {
                if (!this.zae) {
                    this.zae = true;
                    int i3 = 0;
                    while (true) {
                        CursorWindow[] cursorWindowArr = this.zah;
                        if (i3 >= cursorWindowArr.length) {
                            break;
                        }
                        cursorWindowArr[i3].close();
                        i3++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void finalize() throws Throwable {
        try {
            if (this.zak && this.zah.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: " + obj + ")");
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public boolean getBoolean(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getLong(i3, this.zab.getInt(str)) == 1;
    }

    @NonNull
    @KeepForSdk
    public byte[] getByteArray(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getBlob(i3, this.zab.getInt(str));
    }

    @KeepForSdk
    public int getCount() {
        return this.zad;
    }

    @KeepForSdk
    public int getInteger(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getInt(i3, this.zab.getInt(str));
    }

    @KeepForSdk
    public long getLong(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getLong(i3, this.zab.getInt(str));
    }

    @KeepForSdk
    @Nullable
    public Bundle getMetadata() {
        return this.zaj;
    }

    @KeepForSdk
    public int getStatusCode() {
        return this.zai;
    }

    @NonNull
    @KeepForSdk
    public String getString(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getString(i3, this.zab.getInt(str));
    }

    @KeepForSdk
    public int getWindowIndex(int i3) {
        int length;
        int i4 = 0;
        Preconditions.checkState(i3 >= 0 && i3 < this.zad);
        while (true) {
            int[] iArr = this.zac;
            length = iArr.length;
            if (i4 >= length) {
                break;
            } else if (i3 < iArr[i4]) {
                i4--;
                break;
            } else {
                i4++;
            }
        }
        return i4 == length ? i4 - 1 : i4;
    }

    @KeepForSdk
    public boolean hasColumn(@NonNull String str) {
        return this.zab.containsKey(str);
    }

    @KeepForSdk
    public boolean hasNull(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].isNull(i3, this.zab.getInt(str));
    }

    @KeepForSdk
    public boolean isClosed() {
        boolean z2;
        synchronized (this) {
            z2 = this.zae;
        }
        return z2;
    }

    public final void writeToParcel(@NonNull Parcel parcel, int i3) {
        String[] strArr = this.zag;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, strArr, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zah, i3, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zaa);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i3 & 1) != 0) {
            close();
        }
    }

    public final double zaa(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getDouble(i3, this.zab.getInt(str));
    }

    public final float zab(@NonNull String str, int i3, int i4) {
        zae(str, i3);
        return this.zah[i4].getFloat(i3, this.zab.getInt(str));
    }

    public final void zac(@NonNull String str, int i3, int i4, @NonNull CharArrayBuffer charArrayBuffer) {
        zae(str, i3);
        this.zah[i4].copyStringToBuffer(i3, this.zab.getInt(str), charArrayBuffer);
    }

    public final void zad() {
        this.zab = new Bundle();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            String[] strArr = this.zag;
            if (i4 >= strArr.length) {
                break;
            }
            this.zab.putInt(strArr[i4], i4);
            i4++;
        }
        this.zac = new int[this.zah.length];
        int i5 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zah;
            if (i3 < cursorWindowArr.length) {
                this.zac[i3] = i5;
                i5 += this.zah[i3].getNumRows() - (i5 - cursorWindowArr[i3].getStartPosition());
                i3++;
            } else {
                this.zad = i5;
                return;
            }
        }
    }

    @SafeParcelable.Constructor
    public DataHolder(@SafeParcelable.Param(id = 1000) int i3, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i4, @SafeParcelable.Param(id = 4) @Nullable Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = i3;
        this.zag = strArr;
        this.zah = cursorWindowArr;
        this.zai = i4;
        this.zaj = bundle;
    }

    @KeepForSdk
    public DataHolder(@NonNull String[] strArr, @NonNull CursorWindow[] cursorWindowArr, int i3, @Nullable Bundle bundle) {
        this.zae = false;
        this.zak = true;
        this.zaa = 1;
        this.zag = (String[]) Preconditions.checkNotNull(strArr);
        this.zah = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zai = i3;
        this.zaj = bundle;
        zad();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataHolder(@androidx.annotation.NonNull android.database.Cursor r8, int r9, @androidx.annotation.Nullable android.os.Bundle r10) {
        /*
            r7 = this;
            com.google.android.gms.common.sqlite.CursorWrapper r0 = new com.google.android.gms.common.sqlite.CursorWrapper
            r0.<init>(r8)
            java.lang.String[] r8 = r0.getColumnNames()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r0.getCount()     // Catch:{ all -> 0x002e }
            android.database.CursorWindow r3 = r0.getWindow()     // Catch:{ all -> 0x002e }
            r4 = 0
            r5 = 0
            if (r3 == 0) goto L_0x0030
            int r6 = r3.getStartPosition()     // Catch:{ all -> 0x002e }
            if (r6 != 0) goto L_0x0030
            r3.acquireReference()     // Catch:{ all -> 0x002e }
            r0.setWindow(r4)     // Catch:{ all -> 0x002e }
            r1.add(r3)     // Catch:{ all -> 0x002e }
            int r3 = r3.getNumRows()     // Catch:{ all -> 0x002e }
            goto L_0x0031
        L_0x002e:
            r7 = move-exception
            goto L_0x0078
        L_0x0030:
            r3 = r5
        L_0x0031:
            if (r3 >= r2) goto L_0x0065
            boolean r6 = r0.moveToPosition(r3)     // Catch:{ all -> 0x002e }
            if (r6 == 0) goto L_0x0065
            android.database.CursorWindow r6 = r0.getWindow()     // Catch:{ all -> 0x002e }
            if (r6 == 0) goto L_0x0046
            r6.acquireReference()     // Catch:{ all -> 0x002e }
            r0.setWindow(r4)     // Catch:{ all -> 0x002e }
            goto L_0x0051
        L_0x0046:
            android.database.CursorWindow r6 = new android.database.CursorWindow     // Catch:{ all -> 0x002e }
            r6.<init>(r5)     // Catch:{ all -> 0x002e }
            r6.setStartPosition(r3)     // Catch:{ all -> 0x002e }
            r0.fillWindow(r3, r6)     // Catch:{ all -> 0x002e }
        L_0x0051:
            int r3 = r6.getNumRows()     // Catch:{ all -> 0x002e }
            if (r3 != 0) goto L_0x0058
            goto L_0x0065
        L_0x0058:
            r1.add(r6)     // Catch:{ all -> 0x002e }
            int r3 = r6.getStartPosition()     // Catch:{ all -> 0x002e }
            int r6 = r6.getNumRows()     // Catch:{ all -> 0x002e }
            int r3 = r3 + r6
            goto L_0x0031
        L_0x0065:
            r0.close()
            int r0 = r1.size()
            android.database.CursorWindow[] r0 = new android.database.CursorWindow[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            android.database.CursorWindow[] r0 = (android.database.CursorWindow[]) r0
            r7.<init>((java.lang.String[]) r8, (android.database.CursorWindow[]) r0, (int) r9, (android.os.Bundle) r10)
            return
        L_0x0078:
            r0.close()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.<init>(android.database.Cursor, int, android.os.Bundle):void");
    }

    private DataHolder(Builder builder, int i3, @Nullable Bundle bundle) {
        this(builder.zaa, zaf(builder, -1), i3, (Bundle) null);
    }
}
