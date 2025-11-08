package com.reown.android.internal.common.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/reown/android/internal/common/model/Validation;", "", "<init>", "(Ljava/lang/String;I)V", "VALID", "INVALID", "UNKNOWN", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum Validation {
    VALID,
    INVALID,
    UNKNOWN;

    static {
        Validation[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    @NotNull
    public static EnumEntries<Validation> getEntries() {
        return $ENTRIES;
    }
}
