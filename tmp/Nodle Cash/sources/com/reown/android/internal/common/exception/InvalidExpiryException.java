package com.reown.android.internal.common.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/exception/InvalidExpiryException;", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InvalidExpiryException extends WalletConnectException {
    @Nullable
    private final String message;

    public InvalidExpiryException() {
        this((String) null, 1, (DefaultConstructorMarker) null);
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InvalidExpiryException(String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "Request expiry validation failed. Expiry must be between current timestamp + MIN_INTERVAL and current timestamp + MAX_INTERVAL (MIN_INTERVAL: 300, MAX_INTERVAL: 604800)" : str);
    }

    public InvalidExpiryException(@Nullable String str) {
        super(str);
        this.message = str;
    }
}
