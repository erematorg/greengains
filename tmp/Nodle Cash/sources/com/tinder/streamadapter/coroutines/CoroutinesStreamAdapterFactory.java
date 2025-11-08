package com.tinder.streamadapter.coroutines;

import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.utils.TypeUtils;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/tinder/streamadapter/coroutines/CoroutinesStreamAdapterFactory;", "Lcom/tinder/scarlet/StreamAdapter$Factory;", "()V", "create", "Lcom/tinder/scarlet/StreamAdapter;", "", "type", "Ljava/lang/reflect/Type;", "scarlet-stream-adapter-coroutines"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CoroutinesStreamAdapterFactory implements StreamAdapter.Factory {
    @NotNull
    public StreamAdapter<Object, Object> create(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (Intrinsics.areEqual((Object) TypeUtils.getRawType(type), (Object) ReceiveChannel.class)) {
            return new ReceiveChannelStreamAdapter();
        }
        throw new IllegalArgumentException();
    }
}
