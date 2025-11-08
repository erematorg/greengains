package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.model.WCResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,108:1\n47#2,5:109\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionRequestUseCase$collectResponse$$inlined$filter$1 implements Flow<WCResponse> {
    final /* synthetic */ long $id$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public SessionRequestUseCase$collectResponse$$inlined$filter$1(Flow flow, long j2) {
        this.$this_unsafeTransform$inlined = flow;
        this.$id$inlined = j2;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final long j2 = this.$id$inlined;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    boolean r0 = r10 instanceof com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r10
                    com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1$2$1 r0 = (com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1$2$1 r0 = new com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1$2$1
                    r0.<init>(r8, r10)
                L_0x0018:
                    java.lang.Object r10 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L_0x0039
                    if (r2 != r3) goto L_0x0031
                    java.lang.Object r8 = r0.L$3
                    kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                    java.lang.Object r8 = r0.L$1
                    com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1$2$1 r8 = (com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r8
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x0073
                L_0x0031:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L_0x0039:
                    kotlin.ResultKt.throwOnFailure(r10)
                    kotlinx.coroutines.flow.FlowCollector r10 = r5
                    r2 = r9
                    com.reown.android.internal.common.model.WCResponse r2 = (com.reown.android.internal.common.model.WCResponse) r2
                    com.reown.android.internal.common.JsonRpcResponse r2 = r2.getResponse()
                    long r4 = r2.getId()
                    long r6 = r2
                    int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                    if (r8 != 0) goto L_0x0073
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r0.L$0 = r8
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                    r0.L$1 = r8
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                    r0.L$2 = r8
                    java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
                    r0.L$3 = r8
                    r8 = 0
                    r0.I$0 = r8
                    r0.label = r3
                    java.lang.Object r8 = r10.emit(r9, r0)
                    if (r8 != r1) goto L_0x0073
                    return r1
                L_0x0073:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.SessionRequestUseCase$collectResponse$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
