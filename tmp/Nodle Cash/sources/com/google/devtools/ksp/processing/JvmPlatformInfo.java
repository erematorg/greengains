package com.google.devtools.ksp.processing;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/JvmPlatformInfo;", "Lcom/google/devtools/ksp/processing/PlatformInfo;", "jvmDefaultMode", "", "getJvmDefaultMode", "()Ljava/lang/String;", "jvmTarget", "getJvmTarget", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface JvmPlatformInfo extends PlatformInfo {
    @NotNull
    String getJvmDefaultMode();

    @NotNull
    String getJvmTarget();
}
