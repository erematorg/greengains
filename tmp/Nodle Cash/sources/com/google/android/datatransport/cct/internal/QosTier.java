package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;
import androidx.annotation.Nullable;

public enum QosTier {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    
    private static final SparseArray<QosTier> valueMap = null;
    private final int value;

    static {
        QosTier qosTier;
        QosTier qosTier2;
        QosTier qosTier3;
        QosTier qosTier4;
        QosTier qosTier5;
        QosTier qosTier6;
        SparseArray<QosTier> sparseArray = new SparseArray<>();
        valueMap = sparseArray;
        sparseArray.put(0, qosTier);
        sparseArray.put(1, qosTier2);
        sparseArray.put(2, qosTier3);
        sparseArray.put(3, qosTier4);
        sparseArray.put(4, qosTier5);
        sparseArray.put(-1, qosTier6);
    }

    private QosTier(int i3) {
        this.value = i3;
    }

    @Nullable
    public static QosTier forNumber(int i3) {
        if (i3 == 0) {
            return DEFAULT;
        }
        if (i3 == 1) {
            return UNMETERED_ONLY;
        }
        if (i3 == 2) {
            return UNMETERED_OR_DAILY;
        }
        if (i3 == 3) {
            return FAST_IF_RADIO_AWAKE;
        }
        if (i3 != 4) {
            return null;
        }
        return NEVER;
    }

    public final int getNumber() {
        return this.value;
    }
}
