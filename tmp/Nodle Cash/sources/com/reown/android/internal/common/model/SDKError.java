package com.reown.android.internal.common.model;

import com.reown.android.internal.common.model.type.EngineEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/model/SDKError;", "Lcom/reown/android/internal/common/model/type/EngineEvent;", "exception", "", "<init>", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SDKError implements EngineEvent {
    @NotNull
    private final Throwable exception;

    public SDKError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "exception");
        this.exception = th;
    }

    @NotNull
    public final Throwable getException() {
        return this.exception;
    }
}
