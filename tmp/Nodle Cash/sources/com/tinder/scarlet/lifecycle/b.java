package com.tinder.scarlet.lifecycle;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import kotlin.jvm.functions.Function1;
import org.reactivestreams.Publisher;

public final /* synthetic */ class b implements FlowableTransformer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function1 f7647a;

    public /* synthetic */ b(Function1 function1) {
        this.f7647a = function1;
    }

    public final Publisher apply(Flowable flowable) {
        return LifecycleRegistry._init_$lambda$1(this.f7647a, flowable);
    }
}
