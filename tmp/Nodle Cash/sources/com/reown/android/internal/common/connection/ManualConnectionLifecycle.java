package com.reown.android.internal.common.connection;

import com.reown.foundation.network.ConnectionLifecycle;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\"\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0019\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u001a\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\u001bJ9\u0010\u001c\u001a\u00020\u00152.\u0010\u001d\u001a*\u0012\u000e\b\u0000\u0012\n  *\u0004\u0018\u00010\u001f0\u001f  *\u0014\u0012\u000e\b\u0000\u0012\n  *\u0004\u0018\u00010\u001f0\u001f\u0018\u00010\u001e0\u001eH\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/reown/android/internal/common/connection/ManualConnectionLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "Lcom/reown/foundation/network/ConnectionLifecycle;", "lifecycleRegistry", "Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "<init>", "(Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "job", "Lkotlinx/coroutines/CompletableJob;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "connectionMutex", "Lkotlinx/coroutines/sync/Mutex;", "_onResume", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "onResume", "Lkotlinx/coroutines/flow/StateFlow;", "getOnResume", "()Lkotlinx/coroutines/flow/StateFlow;", "connect", "", "disconnect", "reconnect", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "p0", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "kotlin.jvm.PlatformType", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ManualConnectionLifecycle implements Lifecycle, ConnectionLifecycle {
    /* access modifiers changed from: private */
    @NotNull
    public final MutableStateFlow<Boolean> _onResume;
    /* access modifiers changed from: private */
    @NotNull
    public final Mutex connectionMutex;
    @NotNull
    private final CompletableJob job;
    /* access modifiers changed from: private */
    @NotNull
    public final LifecycleRegistry lifecycleRegistry;
    @NotNull
    private final StateFlow<Boolean> onResume;
    @NotNull
    private CoroutineScope scope;

    public ManualConnectionLifecycle() {
        this((LifecycleRegistry) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.lifecycleRegistry.combineWith(lifecycleArr);
    }

    public final void connect() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ManualConnectionLifecycle$connect$1(this, (Continuation<? super ManualConnectionLifecycle$connect$1>) null), 3, (Object) null);
    }

    public final void disconnect() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ManualConnectionLifecycle$disconnect$1(this, (Continuation<? super ManualConnectionLifecycle$disconnect$1>) null), 3, (Object) null);
    }

    @NotNull
    public StateFlow<Boolean> getOnResume() {
        return this.onResume;
    }

    public void reconnect() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ManualConnectionLifecycle$reconnect$1(this, (Continuation<? super ManualConnectionLifecycle$reconnect$1>) null), 3, (Object) null);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.lifecycleRegistry.subscribe(subscriber);
    }

    public ManualConnectionLifecycle(@NotNull LifecycleRegistry lifecycleRegistry2) {
        Intrinsics.checkNotNullParameter(lifecycleRegistry2, "lifecycleRegistry");
        this.lifecycleRegistry = lifecycleRegistry2;
        CompletableJob SupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.job = SupervisorJob$default;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorJob$default.plus(Dispatchers.getDefault()));
        this.connectionMutex = MutexKt.Mutex$default(false, 1, (Object) null);
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._onResume = MutableStateFlow;
        this.onResume = FlowKt.asStateFlow(MutableStateFlow);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ManualConnectionLifecycle(LifecycleRegistry lifecycleRegistry2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? new LifecycleRegistry(0, 1, (DefaultConstructorMarker) null) : lifecycleRegistry2);
    }
}
