package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Marker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/google/devtools/ksp/symbol/Variance;", "", "label", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "STAR", "INVARIANT", "COVARIANT", "CONTRAVARIANT", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public enum Variance {
    STAR(Marker.ANY_MARKER),
    INVARIANT(""),
    COVARIANT("out"),
    CONTRAVARIANT("in");
    
    @NotNull
    private final String label;

    static {
        Variance[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    private Variance(String str) {
        this.label = str;
    }

    @NotNull
    public static EnumEntries<Variance> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }
}
