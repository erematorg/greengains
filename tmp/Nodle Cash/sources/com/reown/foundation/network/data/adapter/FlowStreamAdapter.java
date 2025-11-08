package com.reown.foundation.network.data.adapter;

import com.tinder.scarlet.Stream;
import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.utils.TypeUtils;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002:\u0001\tB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¨\u0006\n"}, d2 = {"Lcom/reown/foundation/network/data/adapter/FlowStreamAdapter;", "T", "Lcom/tinder/scarlet/StreamAdapter;", "Lkotlinx/coroutines/flow/Flow;", "<init>", "()V", "adapt", "stream", "Lcom/tinder/scarlet/Stream;", "Factory", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FlowStreamAdapter<T> implements StreamAdapter<T, Flow<? extends T>> {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/reown/foundation/network/data/adapter/FlowStreamAdapter$Factory;", "Lcom/tinder/scarlet/StreamAdapter$Factory;", "<init>", "()V", "create", "Lcom/tinder/scarlet/StreamAdapter;", "", "type", "Ljava/lang/reflect/Type;", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory implements StreamAdapter.Factory {
        @NotNull
        public StreamAdapter<Object, Object> create(@NotNull Type type) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (Intrinsics.areEqual((Object) TypeUtils.getRawType(type), (Object) Flow.class)) {
                return new FlowStreamAdapter();
            }
            throw new IllegalArgumentException();
        }
    }

    @NotNull
    public Flow<T> adapt(@NotNull Stream<T> stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        return FlowKt.flow(new FlowStreamAdapter$adapt$1(stream, (Continuation<? super FlowStreamAdapter$adapt$1>) null));
    }
}
