package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/google/devtools/ksp/symbol/Origin;", "", "(Ljava/lang/String;I)V", "KOTLIN", "KOTLIN_LIB", "JAVA", "JAVA_LIB", "SYNTHETIC", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public enum Origin {
    KOTLIN,
    KOTLIN_LIB,
    JAVA,
    JAVA_LIB,
    SYNTHETIC;

    static {
        Origin[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    @NotNull
    public static EnumEntries<Origin> getEntries() {
        return $ENTRIES;
    }
}
