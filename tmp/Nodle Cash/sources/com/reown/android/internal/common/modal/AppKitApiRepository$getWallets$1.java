package com.reown.android.internal.common.modal;

import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.modal.AppKitApiRepository", f = "AppKitApiRepository.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {37}, m = "getWallets-hUnOzRk", n = {"sdkType", "search", "excludeIds", "includeWallets", "$this$getWallets_hUnOzRk_u24lambda_u245", "page", "$i$a$-runCatching-AppKitApiRepository$getWallets$2"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"})
public final class AppKitApiRepository$getWallets$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppKitApiRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppKitApiRepository$getWallets$1(AppKitApiRepository appKitApiRepository, Continuation<? super AppKitApiRepository$getWallets$1> continuation) {
        super(continuation);
        this.this$0 = appKitApiRepository;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r7 = this.this$0.m8747getWalletshUnOzRk((String) null, 0, (String) null, (List<String>) null, (List<String>) null, this);
        return r7 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r7 : Result.m8978boximpl(r7);
    }
}
