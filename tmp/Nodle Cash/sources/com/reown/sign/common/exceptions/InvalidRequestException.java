package com.reown.sign.common.exceptions;

import com.reown.android.internal.common.exception.WalletConnectException;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/common/exceptions/InvalidRequestException;", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InvalidRequestException extends WalletConnectException {
    @Nullable
    private final String message;

    public InvalidRequestException(@Nullable String str) {
        super(str);
        this.message = str;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }
}
