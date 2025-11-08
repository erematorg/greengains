package com.tinder.scarlet.utils;

import com.tinder.scarlet.Stream;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0010B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J9\u0010\u000b\u001a\u00020\f2.\u0010\r\u001a*\u0012\u000e\b\u0000\u0012\n \u000f*\u0004\u0018\u00018\u00008\u0000 \u000f*\u0014\u0012\u000e\b\u0000\u0012\n \u000f*\u0004\u0018\u00018\u00008\u0000\u0018\u00010\u000e0\u000eH\u0001R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/tinder/scarlet/utils/FlowableStream;", "T", "Lcom/tinder/scarlet/Stream;", "Lorg/reactivestreams/Publisher;", "flowable", "Lio/reactivex/Flowable;", "(Lio/reactivex/Flowable;)V", "start", "Lcom/tinder/scarlet/Stream$Disposable;", "observer", "Lcom/tinder/scarlet/Stream$Observer;", "subscribe", "", "p0", "Lorg/reactivestreams/Subscriber;", "kotlin.jvm.PlatformType", "FlowableStreamDisposable", "scarlet-core-internal"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FlowableStream<T> implements Stream<T>, Publisher<T> {
    @NotNull
    private final Flowable<T> flowable;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/utils/FlowableStream$FlowableStreamDisposable;", "Lcom/tinder/scarlet/Stream$Disposable;", "disposable", "Lio/reactivex/disposables/Disposable;", "(Lio/reactivex/disposables/Disposable;)V", "dispose", "", "isDisposed", "", "scarlet-core-internal"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FlowableStreamDisposable implements Stream.Disposable {
        @NotNull
        private final Disposable disposable;

        public FlowableStreamDisposable(@NotNull Disposable disposable2) {
            Intrinsics.checkNotNullParameter(disposable2, "disposable");
            this.disposable = disposable2;
        }

        public void dispose() {
            this.disposable.dispose();
        }

        public boolean isDisposed() {
            return this.disposable.isDisposed();
        }
    }

    public FlowableStream(@NotNull Flowable<T> flowable2) {
        Intrinsics.checkNotNullParameter(flowable2, "flowable");
        this.flowable = flowable2;
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void start$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    @NotNull
    public Stream.Disposable start(@NotNull Stream.Observer<? super T> observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        Disposable subscribe = this.flowable.subscribe(new a(new FlowableStream$start$disposable$1(observer), 0), new a(new FlowableStream$start$disposable$2(observer), 1), new b(observer));
        Intrinsics.checkNotNullExpressionValue(subscribe, "disposable");
        return new FlowableStreamDisposable(subscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber) {
        this.flowable.subscribe(subscriber);
    }
}
