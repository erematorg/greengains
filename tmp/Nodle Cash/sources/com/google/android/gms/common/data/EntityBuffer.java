package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa = false;
    private ArrayList zab;

    @KeepForSdk
    public EntityBuffer(@NonNull DataHolder dataHolder) {
        super(dataHolder);
    }

    private final void zab() {
        synchronized (this) {
            try {
                if (!this.zaa) {
                    int count = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                    ArrayList arrayList = new ArrayList();
                    this.zab = arrayList;
                    if (count > 0) {
                        arrayList.add(0);
                        String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                        String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                        int i3 = 1;
                        while (i3 < count) {
                            int windowIndex = this.mDataHolder.getWindowIndex(i3);
                            String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i3, windowIndex);
                            if (string2 != null) {
                                if (!string2.equals(string)) {
                                    this.zab.add(Integer.valueOf(i3));
                                    string = string2;
                                }
                                i3++;
                            } else {
                                throw new NullPointerException("Missing value for markerColumn: " + primaryDataMarkerColumn + ", at row: " + i3 + ", for window: " + windowIndex);
                            }
                        }
                    }
                    this.zaa = true;
                }
            } finally {
            }
        }
    }

    @ResultIgnorabilityUnspecified
    @NonNull
    @KeepForSdk
    public final T get(int i3) {
        int intValue;
        int intValue2;
        zab();
        int zaa2 = zaa(i3);
        int i4 = 0;
        if (i3 >= 0 && i3 != this.zab.size()) {
            if (i3 == this.zab.size() - 1) {
                intValue = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getCount();
                intValue2 = ((Integer) this.zab.get(i3)).intValue();
            } else {
                intValue = ((Integer) this.zab.get(i3 + 1)).intValue();
                intValue2 = ((Integer) this.zab.get(i3)).intValue();
            }
            int i5 = intValue - intValue2;
            if (i5 == 1) {
                int zaa3 = zaa(i3);
                int windowIndex = ((DataHolder) Preconditions.checkNotNull(this.mDataHolder)).getWindowIndex(zaa3);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn == null || this.mDataHolder.getString(childDataMarkerColumn, zaa3, windowIndex) != null) {
                    i4 = 1;
                }
            } else {
                i4 = i5;
            }
        }
        return getEntry(zaa2, i4);
    }

    @KeepForSdk
    @Nullable
    public String getChildDataMarkerColumn() {
        return null;
    }

    @KeepForSdk
    public int getCount() {
        zab();
        return this.zab.size();
    }

    @NonNull
    @KeepForSdk
    public abstract T getEntry(int i3, int i4);

    @NonNull
    @KeepForSdk
    public abstract String getPrimaryDataMarkerColumn();

    public final int zaa(int i3) {
        if (i3 >= 0 && i3 < this.zab.size()) {
            return ((Integer) this.zab.get(i3)).intValue();
        }
        throw new IllegalArgumentException(C0118y.c(i3, "Position ", " is out of bounds for this buffer"));
    }
}
