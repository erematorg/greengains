package app.cash.sqldelight.coroutines;

import app.cash.sqldelight.Query;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@"}, d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lapp/cash/sqldelight/Query;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "app.cash.sqldelight.coroutines.FlowQuery$asFlow$1", f = "FlowExtensions.kt", i = {0, 0, 1, 1}, l = {47, 48}, m = "invokeSuspend", n = {"$this$flow", "listener", "$this$flow", "listener"}, s = {"L$0", "L$1", "L$0", "L$1"})
public final class FlowQuery$asFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super Query<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Query<T> $this_asFlow;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlowQuery$asFlow$1(Query<? extends T> query, Continuation<? super FlowQuery$asFlow$1> continuation) {
        super(2, continuation);
        this.$this_asFlow = query;
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(Channel channel) {
        channel.m10510trySendJP2dKIU(Unit.INSTANCE);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        FlowQuery$asFlow$1 flowQuery$asFlow$1 = new FlowQuery$asFlow$1(this.$this_asFlow, continuation);
        flowQuery$asFlow$1.L$0 = obj;
        return flowQuery$asFlow$1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0069 A[Catch:{ all -> 0x001f }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074 A[Catch:{ all -> 0x001f }] */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 == r3) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r7.L$1
            app.cash.sqldelight.Query$Listener r4 = (app.cash.sqldelight.Query.Listener) r4
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x001f }
        L_0x001d:
            r8 = r5
            goto L_0x005a
        L_0x001f:
            r8 = move-exception
            goto L_0x0090
        L_0x0021:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0029:
            java.lang.Object r1 = r7.L$2
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r7.L$1
            app.cash.sqldelight.Query$Listener r4 = (app.cash.sqldelight.Query.Listener) r4
            java.lang.Object r5 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r5 = (kotlinx.coroutines.flow.FlowCollector) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x001f }
            goto L_0x006c
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            r1 = 6
            r4 = 0
            r5 = -1
            kotlinx.coroutines.channels.Channel r1 = kotlinx.coroutines.channels.ChannelKt.Channel$default(r5, r4, r4, r1, r4)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r1.m10510trySendJP2dKIU(r4)
            app.cash.sqldelight.coroutines.a r4 = new app.cash.sqldelight.coroutines.a
            r4.<init>(r1)
            app.cash.sqldelight.Query<T> r5 = r7.$this_asFlow
            r5.addListener(r4)
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()     // Catch:{ all -> 0x001f }
        L_0x005a:
            r7.L$0 = r8     // Catch:{ all -> 0x001f }
            r7.L$1 = r4     // Catch:{ all -> 0x001f }
            r7.L$2 = r1     // Catch:{ all -> 0x001f }
            r7.label = r3     // Catch:{ all -> 0x001f }
            java.lang.Object r5 = r1.hasNext(r7)     // Catch:{ all -> 0x001f }
            if (r5 != r0) goto L_0x0069
            return r0
        L_0x0069:
            r6 = r5
            r5 = r8
            r8 = r6
        L_0x006c:
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x001f }
            boolean r8 = r8.booleanValue()     // Catch:{ all -> 0x001f }
            if (r8 == 0) goto L_0x0088
            r1.next()     // Catch:{ all -> 0x001f }
            app.cash.sqldelight.Query<T> r8 = r7.$this_asFlow     // Catch:{ all -> 0x001f }
            r7.L$0 = r5     // Catch:{ all -> 0x001f }
            r7.L$1 = r4     // Catch:{ all -> 0x001f }
            r7.L$2 = r1     // Catch:{ all -> 0x001f }
            r7.label = r2     // Catch:{ all -> 0x001f }
            java.lang.Object r8 = r5.emit(r8, r7)     // Catch:{ all -> 0x001f }
            if (r8 != r0) goto L_0x001d
            return r0
        L_0x0088:
            app.cash.sqldelight.Query<T> r7 = r7.$this_asFlow
            r7.removeListener(r4)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0090:
            app.cash.sqldelight.Query<T> r7 = r7.$this_asFlow
            r7.removeListener(r4)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: app.cash.sqldelight.coroutines.FlowQuery$asFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super Query<? extends T>> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((FlowQuery$asFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
