package com.reown.android.verify.client;

import com.reown.android.verify.client.VerifyClient$initialize$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.client.VerifyClient$initialize$1$1", f = "VerifyClient.kt", i = {0}, l = {26}, m = "emit", n = {"it"}, s = {"L$0"})
public final class VerifyClient$initialize$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VerifyClient$initialize$1.AnonymousClass1<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyClient$initialize$1$1$emit$1(VerifyClient$initialize$1.AnonymousClass1<? super T> r12, Continuation<? super VerifyClient$initialize$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r12;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((Unit) null, (Continuation<? super Unit>) this);
    }
}
