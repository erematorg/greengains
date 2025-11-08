package com.reown.android.relay;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/android/relay/ConnectionType;", "", "<init>", "(Ljava/lang/String;I)V", "AUTOMATIC", "MANUAL", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum ConnectionType {
    AUTOMATIC,
    MANUAL;

    static {
        ConnectionType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    @NotNull
    public static EnumEntries<ConnectionType> getEntries() {
        return $ENTRIES;
    }
}
