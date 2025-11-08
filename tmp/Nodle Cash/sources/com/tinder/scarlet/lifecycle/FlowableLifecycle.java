package com.tinder.scarlet.lifecycle;

import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.internal.servicemethod.a;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\u00012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\"\u00020\u0001H\u0016¢\u0006\u0002\u0010\fJ9\u0010\r\u001a\u00020\u000e2.\u0010\u000f\u001a*\u0012\u000e\b\u0000\u0012\n \u0011*\u0004\u0018\u00010\u00030\u0003 \u0011*\u0014\u0012\u000e\b\u0000\u0012\n \u0011*\u0004\u0018\u00010\u00030\u0003\u0018\u00010\u00100\u0010H\u0001R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tinder/scarlet/lifecycle/FlowableLifecycle;", "Lcom/tinder/scarlet/Lifecycle;", "Lorg/reactivestreams/Publisher;", "Lcom/tinder/scarlet/Lifecycle$State;", "flowable", "Lio/reactivex/Flowable;", "scheduler", "Lio/reactivex/Scheduler;", "(Lio/reactivex/Flowable;Lio/reactivex/Scheduler;)V", "combineWith", "others", "", "([Lcom/tinder/scarlet/Lifecycle;)Lcom/tinder/scarlet/Lifecycle;", "subscribe", "", "p0", "Lorg/reactivestreams/Subscriber;", "kotlin.jvm.PlatformType", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFlowableLifecycle.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowableLifecycle.kt\ncom/tinder/scarlet/lifecycle/FlowableLifecycle\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,30:1\n1549#2:31\n1620#2,3:32\n*S KotlinDebug\n*F\n+ 1 FlowableLifecycle.kt\ncom/tinder/scarlet/lifecycle/FlowableLifecycle\n*L\n20#1:31\n20#1:32,3\n*E\n"})
public final class FlowableLifecycle implements Lifecycle, Publisher<Lifecycle.State> {
    @NotNull
    private final Flowable<Lifecycle.State> flowable;
    @NotNull
    private final Scheduler scheduler;

    public FlowableLifecycle(@NotNull Flowable<Lifecycle.State> flowable2, @NotNull Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(flowable2, "flowable");
        Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
        this.flowable = flowable2;
        this.scheduler = scheduler2;
    }

    /* access modifiers changed from: private */
    public static final List combineWith$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return (List) function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final Lifecycle.State combineWith$lambda$2(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return (Lifecycle.State) function1.invoke(obj);
    }

    @NotNull
    public Lifecycle combineWith(@NotNull Lifecycle... lifecycleArr) {
        Intrinsics.checkNotNullParameter(lifecycleArr, "others");
        Iterable<Lifecycle> plus = CollectionsKt.plus(CollectionsKt.listOf(this), (T[]) lifecycleArr);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(plus, 10));
        for (Lifecycle fromPublisher : plus) {
            arrayList.add(Flowable.fromPublisher(fromPublisher).timestamp(this.scheduler));
        }
        Flowable map = Flowable.combineLatest(arrayList, new a(FlowableLifecycle$combineWith$flowable$1.INSTANCE, 6)).map(new a(FlowableLifecycle$combineWith$flowable$2.INSTANCE, 7));
        Intrinsics.checkNotNullExpressionValue(map, "flowable");
        return new FlowableLifecycle(map, this.scheduler);
    }

    public void subscribe(Subscriber<? super Lifecycle.State> subscriber) {
        this.flowable.subscribe(subscriber);
    }
}
