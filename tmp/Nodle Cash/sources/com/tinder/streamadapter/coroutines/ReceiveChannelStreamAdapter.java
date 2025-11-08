package com.tinder.streamadapter.coroutines;

import com.tinder.scarlet.Stream;
import com.tinder.scarlet.StreamAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.reactive.ChannelKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/tinder/streamadapter/coroutines/ReceiveChannelStreamAdapter;", "T", "Lcom/tinder/scarlet/StreamAdapter;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "()V", "adapt", "stream", "Lcom/tinder/scarlet/Stream;", "scarlet-stream-adapter-coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReceiveChannelStreamAdapter<T> implements StreamAdapter<T, ReceiveChannel<? extends T>> {
    @NotNull
    public ReceiveChannel<T> adapt(@NotNull Stream<T> stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        return ChannelKt.openSubscription$default(stream, 0, 1, (Object) null);
    }
}
