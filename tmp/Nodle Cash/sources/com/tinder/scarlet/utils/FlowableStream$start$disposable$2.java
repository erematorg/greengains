package com.tinder.scarlet.utils;

import com.tinder.scarlet.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class FlowableStream$start$disposable$2 extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
    public FlowableStream$start$disposable$2(Object obj) {
        super(1, obj, Stream.Observer.class, "onError", "onError(Ljava/lang/Throwable;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "p0");
        ((Stream.Observer) this.receiver).onError(th);
    }
}
