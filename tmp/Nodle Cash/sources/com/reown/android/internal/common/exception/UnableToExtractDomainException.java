package com.reown.android.internal.common.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/android/internal/common/exception/UnableToExtractDomainException;", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "keyserverUrl", "", "<init>", "(Ljava/lang/String;)V", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnableToExtractDomainException extends WalletConnectException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnableToExtractDomainException(@NotNull String str) {
        super("Unable to extract domain from: " + str);
        Intrinsics.checkNotNullParameter(str, "keyserverUrl");
    }
}
