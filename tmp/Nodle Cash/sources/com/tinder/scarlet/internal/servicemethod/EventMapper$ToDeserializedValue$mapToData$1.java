package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.Deserialization;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "", "it", "Lcom/tinder/scarlet/Deserialization;", "invoke", "(Lcom/tinder/scarlet/Deserialization;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EventMapper$ToDeserializedValue$mapToData$1 extends Lambda implements Function1<Deserialization<T>, Boolean> {
    public static final EventMapper$ToDeserializedValue$mapToData$1 INSTANCE = new EventMapper$ToDeserializedValue$mapToData$1();

    public EventMapper$ToDeserializedValue$mapToData$1() {
        super(1);
    }

    @NotNull
    public final Boolean invoke(@NotNull Deserialization<T> deserialization) {
        Intrinsics.checkNotNullParameter(deserialization, "it");
        return Boolean.valueOf(deserialization instanceof Deserialization.Success);
    }
}
