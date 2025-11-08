package com.reown.android.pulse.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/reown/android/pulse/model/SDKType;", "", "type", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "APPKIT", "EVENTS", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum SDKType {
    APPKIT("appkit"),
    EVENTS("events_sdk");
    
    @NotNull
    private final String type;

    static {
        SDKType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    private SDKType(String str) {
        this.type = str;
    }

    @NotNull
    public static EnumEntries<SDKType> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }
}
