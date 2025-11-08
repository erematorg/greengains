package com.reown.sign.common.exceptions;

import com.reown.android.internal.common.exception.WalletConnectException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/common/exceptions/MissingSessionAuthenticateRequest;", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MissingSessionAuthenticateRequest extends WalletConnectException {
    public MissingSessionAuthenticateRequest() {
        super(MessagesKt.MISSING_SESSION_AUTHENTICATE_REQUEST);
    }
}
