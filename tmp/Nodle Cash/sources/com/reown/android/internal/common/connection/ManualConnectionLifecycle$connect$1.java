package com.reown.android.internal.common.connection;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;

@SourceDebugExtension({"SMAP\nManualConnectionLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ManualConnectionLifecycle.kt\ncom/reown/android/internal/common/connection/ManualConnectionLifecycle$connect$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,54:1\n116#2,11:55\n*S KotlinDebug\n*F\n+ 1 ManualConnectionLifecycle.kt\ncom/reown/android/internal/common/connection/ManualConnectionLifecycle$connect$1\n*L\n30#1:55,11\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.connection.ManualConnectionLifecycle$connect$1", f = "ManualConnectionLifecycle.kt", i = {0, 0}, l = {60}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$i$f$withLock"}, s = {"L$0", "I$0"})
public final class ManualConnectionLifecycle$connect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ManualConnectionLifecycle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ManualConnectionLifecycle$connect$1(ManualConnectionLifecycle manualConnectionLifecycle, Continuation<? super ManualConnectionLifecycle$connect$1> continuation) {
        super(2, continuation);
        this.this$0 = manualConnectionLifecycle;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ManualConnectionLifecycle$connect$1(this.this$0, continuation);
    }

    /* JADX INFO: finally extract failed */
    public final Object invokeSuspend(Object obj) {
        Mutex mutex;
        ManualConnectionLifecycle manualConnectionLifecycle;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Mutex access$getConnectionMutex$p = this.this$0.connectionMutex;
            ManualConnectionLifecycle manualConnectionLifecycle2 = this.this$0;
            this.L$0 = access$getConnectionMutex$p;
            this.L$1 = manualConnectionLifecycle2;
            this.I$0 = 0;
            this.label = 1;
            if (access$getConnectionMutex$p.lock((Object) null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = access$getConnectionMutex$p;
            manualConnectionLifecycle = manualConnectionLifecycle2;
        } else if (i3 == 1) {
            manualConnectionLifecycle = (ManualConnectionLifecycle) this.L$1;
            mutex = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            manualConnectionLifecycle._onResume.setValue(Boxing.boxBoolean(true));
            Unit unit = Unit.INSTANCE;
            mutex.unlock((Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock((Object) null);
            throw th;
        }
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ManualConnectionLifecycle$connect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
