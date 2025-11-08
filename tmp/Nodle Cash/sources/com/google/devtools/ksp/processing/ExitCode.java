package com.google.devtools.ksp.processing;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/devtools/ksp/processing/ExitCode;", "", "code", "", "(Ljava/lang/String;II)V", "OK", "PROCESSING_ERROR", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public enum ExitCode {
    OK(0),
    PROCESSING_ERROR(1);

    static {
        ExitCode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
    }

    private ExitCode(int i3) {
    }

    @NotNull
    public static EnumEntries<ExitCode> getEntries() {
        return $ENTRIES;
    }
}
