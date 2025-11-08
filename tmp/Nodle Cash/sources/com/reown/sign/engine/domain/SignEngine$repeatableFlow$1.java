package com.reown.sign.engine.domain;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$repeatableFlow$1", f = "SignEngine.kt", i = {0, 1}, l = {514, 515}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
public final class SignEngine$repeatableFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    public SignEngine$repeatableFlow$1(Continuation<? super SignEngine$repeatableFlow$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$repeatableFlow$1 signEngine$repeatableFlow$1 = new SignEngine$repeatableFlow$1(continuation);
        signEngine$repeatableFlow$1.L$0 = obj;
        return signEngine$repeatableFlow$1;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b A[RETURN] */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x001f
            if (r2 == r4) goto L_0x001b
            if (r2 != r3) goto L_0x0013
            goto L_0x001f
        L_0x0013:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x002f
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r8)
        L_0x0022:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            r7.L$0 = r0
            r7.label = r4
            java.lang.Object r8 = r0.emit(r8, r7)
            if (r8 != r1) goto L_0x002f
            return r1
        L_0x002f:
            r7.L$0 = r0
            r7.label = r3
            r5 = 30000(0x7530, double:1.4822E-319)
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
            if (r8 != r1) goto L_0x0022
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.domain.SignEngine$repeatableFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(FlowCollector<? super Unit> flowCollector, Continuation<? super Unit> continuation) {
        return ((SignEngine$repeatableFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
