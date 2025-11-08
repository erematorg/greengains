package com.reown.android.internal.common.connection;

import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.ShutdownReason;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.sync.Mutex;

@SourceDebugExtension({"SMAP\nManualConnectionLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ManualConnectionLifecycle.kt\ncom/reown/android/internal/common/connection/ManualConnectionLifecycle$reconnect$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,54:1\n116#2,11:55\n*S KotlinDebug\n*F\n+ 1 ManualConnectionLifecycle.kt\ncom/reown/android/internal/common/connection/ManualConnectionLifecycle$reconnect$1\n*L\n47#1:55,11\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.connection.ManualConnectionLifecycle$reconnect$1", f = "ManualConnectionLifecycle.kt", i = {0, 0, 1, 1, 1}, l = {60, 49}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv", "$i$f$withLock", "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-ManualConnectionLifecycle$reconnect$1$1"}, s = {"L$0", "I$0", "L$0", "I$0", "I$1"})
public final class ManualConnectionLifecycle$reconnect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ManualConnectionLifecycle this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ManualConnectionLifecycle$reconnect$1(ManualConnectionLifecycle manualConnectionLifecycle, Continuation<? super ManualConnectionLifecycle$reconnect$1> continuation) {
        super(2, continuation);
        this.this$0 = manualConnectionLifecycle;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ManualConnectionLifecycle$reconnect$1(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th;
        Mutex mutex;
        ManualConnectionLifecycle manualConnectionLifecycle;
        Mutex mutex2;
        int i3;
        ManualConnectionLifecycle manualConnectionLifecycle2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            mutex2 = this.this$0.connectionMutex;
            manualConnectionLifecycle2 = this.this$0;
            this.L$0 = mutex2;
            this.L$1 = manualConnectionLifecycle2;
            this.I$0 = 0;
            this.label = 1;
            if (mutex2.lock((Object) null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i3 = 0;
        } else if (i4 == 1) {
            int i5 = this.I$0;
            ResultKt.throwOnFailure(obj);
            mutex2 = (Mutex) this.L$0;
            i3 = i5;
            manualConnectionLifecycle2 = (ManualConnectionLifecycle) this.L$1;
        } else if (i4 == 2) {
            manualConnectionLifecycle = (ManualConnectionLifecycle) this.L$1;
            mutex = (Mutex) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                manualConnectionLifecycle.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
                Unit unit = Unit.INSTANCE;
                mutex.unlock((Object) null);
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            manualConnectionLifecycle2.lifecycleRegistry.onNext((Lifecycle.State) new Lifecycle.State.Stopped.WithReason((ShutdownReason) null, 1, (DefaultConstructorMarker) null));
            this.L$0 = mutex2;
            this.L$1 = manualConnectionLifecycle2;
            this.I$0 = i3;
            this.I$1 = 0;
            this.label = 2;
            if (DelayKt.delay(100, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutex = mutex2;
            manualConnectionLifecycle = manualConnectionLifecycle2;
            manualConnectionLifecycle.lifecycleRegistry.onNext((Lifecycle.State) Lifecycle.State.Started.INSTANCE);
            Unit unit2 = Unit.INSTANCE;
            mutex.unlock((Object) null);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            Mutex mutex3 = mutex2;
            th = th3;
            mutex = mutex3;
            mutex.unlock((Object) null);
            throw th;
        }
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ManualConnectionLifecycle$reconnect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
