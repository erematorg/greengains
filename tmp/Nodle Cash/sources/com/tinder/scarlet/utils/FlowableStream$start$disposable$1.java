package com.tinder.scarlet.utils;

import com.tinder.scarlet.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class FlowableStream$start$disposable$1 extends FunctionReferenceImpl implements Function1<T, Unit> {
    public FlowableStream$start$disposable$1(Object obj) {
        super(1, obj, Stream.Observer.class, "onNext", "onNext(Ljava/lang/Object;)V", 0);
    }

    public final void invoke(T t2) {
        ((Stream.Observer) this.receiver).onNext(t2);
    }
}
