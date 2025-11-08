package com.reown.android.internal.common.exception;

import A.a;
import com.reown.android.internal.common.model.AccountId;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/android/internal/common/exception/InvalidAccountIdException;", "Lcom/reown/android/internal/common/exception/WalletConnectException;", "accountId", "Lcom/reown/android/internal/common/model/AccountId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InvalidAccountIdException extends WalletConnectException {
    public /* synthetic */ InvalidAccountIdException(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private InvalidAccountIdException(String str) {
        super(a.l("AccountId: ", AccountId.m8755toStringimpl(str), " is not CAIP-10 complaint"));
        Intrinsics.checkNotNullParameter(str, "accountId");
    }
}
