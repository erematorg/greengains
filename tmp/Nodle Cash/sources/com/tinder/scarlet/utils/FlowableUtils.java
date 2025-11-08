package com.tinder.scarlet.utils;

import io.reactivex.Flowable;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"toStream", "Lcom/tinder/scarlet/utils/FlowableStream;", "T", "Lio/reactivex/Flowable;", "scarlet-core-internal"}, k = 2, mv = {1, 8, 0}, xi = 48)
@JvmName(name = "FlowableUtils")
public final class FlowableUtils {
    @NotNull
    public static final <T> FlowableStream<T> toStream(@NotNull Flowable<T> flowable) {
        Intrinsics.checkNotNullParameter(flowable, "<this>");
        return new FlowableStream<>(flowable);
    }
}
