package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.Event;
import io.reactivex.Maybe;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class ServiceMethod$Receive$execute$stream$2 extends FunctionReferenceImpl implements Function1<Event, Maybe<? extends Object>> {
    public ServiceMethod$Receive$execute$stream$2(Object obj) {
        super(1, obj, EventMapper.class, "mapToData", "mapToData(Lcom/tinder/scarlet/Event;)Lio/reactivex/Maybe;", 0);
    }

    @NotNull
    public final Maybe<? extends Object> invoke(@NotNull Event event) {
        Intrinsics.checkNotNullParameter(event, "p0");
        return ((EventMapper) this.receiver).mapToData(event);
    }
}
