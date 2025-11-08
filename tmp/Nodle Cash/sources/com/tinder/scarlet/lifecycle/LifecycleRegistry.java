package com.tinder.scarlet.lifecycle;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.tinder.scarlet.Lifecycle;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u001fB\u0019\b\u0010\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0011\b\u0016\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nB3\b\u0000\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\u00020\u00012\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0012\"\u00020\u0001H\u0001¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0019\u0010\u0019\u001a\u00020\u00152\u000e\u0010\u001a\u001a\n \u001b*\u0004\u0018\u00010\u00030\u0003H\u0001J\u0019\u0010\u001c\u001a\u00020\u00152\u000e\u0010\u001a\u001a\n \u001b*\u0004\u0018\u00010\u001d0\u001dH\u0001J9\u0010\u001e\u001a\u00020\u00152.\u0010\u001a\u001a*\u0012\u000e\b\u0000\u0012\n \u001b*\u0004\u0018\u00010\u00030\u0003 \u001b*\u0014\u0012\u000e\b\u0000\u0012\n \u001b*\u0004\u0018\u00010\u00030\u0003\u0018\u00010\u00020\u0002H\u0001R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fX\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;", "Lcom/tinder/scarlet/Lifecycle;", "Lorg/reactivestreams/Subscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "throttleTimeoutMillis", "", "scheduler", "Lio/reactivex/Scheduler;", "(JLio/reactivex/Scheduler;)V", "throttleDurationMillis", "(J)V", "upstreamProcessor", "Lio/reactivex/processors/FlowableProcessor;", "downstreamProcessor", "throttleScheduler", "(Lio/reactivex/processors/FlowableProcessor;Lio/reactivex/processors/FlowableProcessor;JLio/reactivex/Scheduler;)V", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "onComplete", "", "onError", "t", "", "onNext", "p0", "kotlin.jvm.PlatformType", "onSubscribe", "Lorg/reactivestreams/Subscription;", "subscribe", "LifecycleStateSubscriber", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LifecycleRegistry implements Lifecycle, Subscriber<Lifecycle.State> {
    private final /* synthetic */ FlowableLifecycle $$delegate_0;
    /* access modifiers changed from: private */
    @NotNull
    public final FlowableProcessor<Lifecycle.State> downstreamProcessor;
    @NotNull
    private final FlowableProcessor<Lifecycle.State> upstreamProcessor;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/lifecycle/LifecycleRegistry$LifecycleStateSubscriber;", "Lio/reactivex/subscribers/DisposableSubscriber;", "Lcom/tinder/scarlet/Lifecycle$State;", "(Lcom/tinder/scarlet/lifecycle/LifecycleRegistry;)V", "onComplete", "", "onError", "throwable", "", "onNext", "state", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class LifecycleStateSubscriber extends DisposableSubscriber<Lifecycle.State> {
        public LifecycleStateSubscriber() {
        }

        public void onComplete() {
            throw new IllegalStateException("Stream is terminated");
        }

        public void onError(@NotNull Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            throw new IllegalStateException("Stream is terminated", th);
        }

        public void onNext(@NotNull Lifecycle.State state) {
            Intrinsics.checkNotNullParameter(state, RemoteConfigConstants.ResponseFieldKey.STATE);
            LifecycleRegistry.this.downstreamProcessor.onNext(state);
            if (Intrinsics.areEqual((Object) state, (Object) Lifecycle.State.Destroyed.INSTANCE)) {
                LifecycleRegistry.this.downstreamProcessor.onComplete();
                dispose();
            }
        }
    }

    public LifecycleRegistry(@NotNull FlowableProcessor<Lifecycle.State> flowableProcessor, @NotNull FlowableProcessor<Lifecycle.State> flowableProcessor2, final long j2, @NotNull final Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(flowableProcessor, "upstreamProcessor");
        Intrinsics.checkNotNullParameter(flowableProcessor2, "downstreamProcessor");
        Intrinsics.checkNotNullParameter(scheduler, "throttleScheduler");
        this.upstreamProcessor = flowableProcessor;
        this.downstreamProcessor = flowableProcessor2;
        Flowable<Lifecycle.State> onBackpressureLatest = flowableProcessor2.onBackpressureLatest();
        Intrinsics.checkNotNullExpressionValue(onBackpressureLatest, "downstreamProcessor.onBackpressureLatest()");
        this.$$delegate_0 = new FlowableLifecycle(onBackpressureLatest, scheduler);
        flowableProcessor.onBackpressureLatest().distinctUntilChanged(new a(AnonymousClass1.INSTANCE, 0)).compose(new b(new Function1<Flowable<Lifecycle.State>, Publisher<Lifecycle.State>>() {
            @NotNull
            public final Publisher<Lifecycle.State> invoke(@NotNull Flowable<Lifecycle.State> flowable) {
                Intrinsics.checkNotNullParameter(flowable, "it");
                long j2 = j2;
                return j2 != 0 ? flowable.throttleWithTimeout(j2, TimeUnit.MILLISECONDS, scheduler) : flowable;
            }
        })).distinctUntilChanged(new a(AnonymousClass3.INSTANCE, 1)).subscribe(new LifecycleStateSubscriber());
    }

    /* access modifiers changed from: private */
    public static final boolean _init_$lambda$0(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "$tmp0");
        return ((Boolean) function2.invoke(obj, obj2)).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final Publisher _init_$lambda$1(Function1 function1, Flowable flowable) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        Intrinsics.checkNotNullParameter(flowable, "p0");
        return (Publisher) function1.invoke(flowable);
    }

    /* access modifiers changed from: private */
    public static final boolean _init_$lambda$2(Function2 function2, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(function2, "$tmp0");
        return ((Boolean) function2.invoke(obj, obj2)).booleanValue();
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        return this.$$delegate_0.combineWith(lifecycleArr);
    }

    public void onComplete() {
        this.upstreamProcessor.onNext(Lifecycle.State.Destroyed.INSTANCE);
    }

    public void onError(@Nullable Throwable th) {
        this.upstreamProcessor.onNext(Lifecycle.State.Destroyed.INSTANCE);
    }

    public void onNext(Lifecycle.State state) {
        this.upstreamProcessor.onNext(state);
    }

    public void onSubscribe(Subscription subscription) {
        this.upstreamProcessor.onSubscribe(subscription);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.$$delegate_0.subscribe(subscriber);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LifecycleRegistry(long j2, Scheduler scheduler, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : j2, scheduler);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LifecycleRegistry(long r8, @org.jetbrains.annotations.NotNull io.reactivex.Scheduler r10) {
        /*
            r7 = this;
            java.lang.String r0 = "scheduler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            io.reactivex.processors.PublishProcessor r2 = io.reactivex.processors.PublishProcessor.create()
            java.lang.String r0 = "create()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            io.reactivex.processors.BehaviorProcessor r3 = io.reactivex.processors.BehaviorProcessor.create()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
            r1 = r7
            r4 = r8
            r6 = r10
            r1.<init>((io.reactivex.processors.FlowableProcessor<com.tinder.scarlet.Lifecycle.State>) r2, (io.reactivex.processors.FlowableProcessor<com.tinder.scarlet.Lifecycle.State>) r3, (long) r4, (io.reactivex.Scheduler) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.lifecycle.LifecycleRegistry.<init>(long, io.reactivex.Scheduler):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LifecycleRegistry(long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : j2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LifecycleRegistry(long r3) {
        /*
            r2 = this;
            io.reactivex.Scheduler r0 = io.reactivex.schedulers.Schedulers.computation()
            java.lang.String r1 = "computation()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r2.<init>(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.lifecycle.LifecycleRegistry.<init>(long):void");
    }
}
