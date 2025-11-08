package app.cash.sqldelight.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,111:1\n47#2,5:112\n*E\n"})
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@¢\u0006\u0002\u0010\u0006¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FlowQuery$mapToOneOrDefault$$inlined$map$1 implements Flow<T> {
    final /* synthetic */ CoroutineContext $context$inlined;
    final /* synthetic */ Object $defaultValue$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    public FlowQuery$mapToOneOrDefault$$inlined$map$1(Flow flow, CoroutineContext coroutineContext, Object obj) {
        this.$this_unsafeTransform$inlined = flow;
        this.$context$inlined = coroutineContext;
        this.$defaultValue$inlined = obj;
    }

    @Nullable
    public Object collect(@NotNull final FlowCollector flowCollector, @NotNull Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final CoroutineContext coroutineContext = this.$context$inlined;
        final Object obj = this.$defaultValue$inlined;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0065 A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    boolean r0 = r10 instanceof app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0013
                    r0 = r10
                    app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1$2$1 r0 = (app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L_0x0013
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L_0x0018
                L_0x0013:
                    app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1$2$1 r0 = new app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1$2$1
                    r0.<init>(r8, r10)
                L_0x0018:
                    java.lang.Object r10 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 0
                    r4 = 2
                    r5 = 1
                    if (r2 == 0) goto L_0x003d
                    if (r2 == r5) goto L_0x0035
                    if (r2 != r4) goto L_0x002d
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x0066
                L_0x002d:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r9)
                    throw r8
                L_0x0035:
                    java.lang.Object r8 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x005b
                L_0x003d:
                    kotlin.ResultKt.throwOnFailure(r10)
                    kotlinx.coroutines.flow.FlowCollector r10 = r4
                    app.cash.sqldelight.Query r9 = (app.cash.sqldelight.Query) r9
                    kotlin.coroutines.CoroutineContext r2 = r2
                    app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$1$1 r6 = new app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$1$1
                    java.lang.Object r8 = r3
                    r6.<init>(r9, r8, r3)
                    r0.L$0 = r10
                    r0.label = r5
                    java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                    if (r8 != r1) goto L_0x0058
                    return r1
                L_0x0058:
                    r7 = r10
                    r10 = r8
                    r8 = r7
                L_0x005b:
                    r0.L$0 = r3
                    r0.label = r4
                    java.lang.Object r8 = r8.emit(r10, r0)
                    if (r8 != r1) goto L_0x0066
                    return r1
                L_0x0066:
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.coroutines.FlowQuery$mapToOneOrDefault$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
