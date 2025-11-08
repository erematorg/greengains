package com.reown.android.verify.client;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.client.VerifyClient$initialize$1", f = "VerifyClient.kt", i = {}, l = {25}, m = "invokeSuspend", n = {}, s = {})
public final class VerifyClient$initialize$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VerifyClient this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyClient$initialize$1(VerifyClient verifyClient, Continuation<? super VerifyClient$initialize$1> continuation) {
        super(2, continuation);
        this.this$0 = verifyClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VerifyClient$initialize$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow<Unit> checkVerifyKeyFlow = this.this$0.getPairingController().getCheckVerifyKeyFlow();
            final VerifyClient verifyClient = this.this$0;
            AnonymousClass1 r12 = new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(kotlin.Unit r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.reown.android.verify.client.VerifyClient$initialize$1$1$emit$1
                        if (r0 == 0) goto L_0x0013
                        r0 = r6
                        com.reown.android.verify.client.VerifyClient$initialize$1$1$emit$1 r0 = (com.reown.android.verify.client.VerifyClient$initialize$1$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L_0x0013
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L_0x0018
                    L_0x0013:
                        com.reown.android.verify.client.VerifyClient$initialize$1$1$emit$1 r0 = new com.reown.android.verify.client.VerifyClient$initialize$1$1$emit$1
                        r0.<init>(r4, r6)
                    L_0x0018:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x003b
                        if (r2 != r3) goto L_0x0033
                        java.lang.Object r4 = r0.L$0
                        kotlin.Unit r4 = (kotlin.Unit) r4
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Result r6 = (kotlin.Result) r6
                        java.lang.Object r4 = r6.m8988unboximpl()
                        goto L_0x0053
                    L_0x0033:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L_0x003b:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.reown.android.verify.client.VerifyClient r4 = r3
                        com.reown.android.verify.domain.VerifyRepository r4 = r4.getVerifyRepository()
                        java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
                        r0.L$0 = r5
                        r0.label = r3
                        java.lang.Object r4 = r4.m8846getVerifyPublicKeyIoAF18A(r0)
                        if (r4 != r1) goto L_0x0053
                        return r1
                    L_0x0053:
                        java.lang.Throwable r4 = kotlin.Result.m8982exceptionOrNullimpl(r4)
                        if (r4 == 0) goto L_0x0068
                        java.lang.String r4 = r4.getMessage()
                        java.lang.String r5 = "Error fetching a key: "
                        java.lang.String r4 = androidx.browser.trusted.c.a(r5, r4)
                        java.io.PrintStream r5 = java.lang.System.out
                        r5.println(r4)
                    L_0x0068:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.reown.android.verify.client.VerifyClient$initialize$1.AnonymousClass1.emit(kotlin.Unit, kotlin.coroutines.Continuation):java.lang.Object");
                }
            };
            this.label = 1;
            if (checkVerifyKeyFlow.collect(r12, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VerifyClient$initialize$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
