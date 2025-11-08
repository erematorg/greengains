package com.reown.foundation.di;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/reown/foundation/di/FoundationDITags;", "", "<init>", "(Ljava/lang/String;I)V", "MOSHI", "INTERCEPTOR", "OK_HTTP", "RELAY_SERVICE", "SCARLET", "MSG_ADAPTER", "MANUAL_CONNECTION_CONTROLLER", "MANUAL_LIFECYCLE", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum FoundationDITags {
    MOSHI,
    INTERCEPTOR,
    OK_HTTP,
    RELAY_SERVICE,
    SCARLET,
    MSG_ADAPTER,
    MANUAL_CONNECTION_CONTROLLER,
    MANUAL_LIFECYCLE;

    static {
        FoundationDITags[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    @NotNull
    public static EnumEntries<FoundationDITags> getEntries() {
        return $ENTRIES;
    }
}
