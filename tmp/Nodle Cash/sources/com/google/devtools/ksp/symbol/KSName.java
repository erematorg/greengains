package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSName;", "", "asString", "", "getQualifier", "getShortName", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSName {
    @NotNull
    String asString();

    @NotNull
    String getQualifier();

    @NotNull
    String getShortName();
}
