package com.google.android.gms.common.data;

import androidx.annotation.NonNull;

public interface DataBufferObserver {

    public interface Observable {
        void addObserver(@NonNull DataBufferObserver dataBufferObserver);

        void removeObserver(@NonNull DataBufferObserver dataBufferObserver);
    }

    void onDataChanged();

    void onDataRangeChanged(int i3, int i4);

    void onDataRangeInserted(int i3, int i4);

    void onDataRangeMoved(int i3, int i4, int i5);

    void onDataRangeRemoved(int i3, int i4);
}
