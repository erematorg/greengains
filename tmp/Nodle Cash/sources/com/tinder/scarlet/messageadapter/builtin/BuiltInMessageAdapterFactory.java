package com.tinder.scarlet.messageadapter.builtin;

import androidx.constraintlayout.core.state.b;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.utils.TypeUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/messageadapter/builtin/BuiltInMessageAdapterFactory;", "Lcom/tinder/scarlet/MessageAdapter$Factory;", "()V", "create", "Lcom/tinder/scarlet/MessageAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/MessageAdapter;", "scarlet-message-adapter-builtin"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BuiltInMessageAdapterFactory implements MessageAdapter.Factory {
    @NotNull
    public MessageAdapter<?> create(@NotNull Type type, @NotNull Annotation[] annotationArr) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(annotationArr, "annotations");
        Class<?> rawType = TypeUtils.getRawType(type);
        if (Intrinsics.areEqual((Object) rawType, (Object) String.class)) {
            return new TextMessageAdapter();
        }
        if (Intrinsics.areEqual((Object) rawType, (Object) byte[].class)) {
            return new ByteArrayMessageAdapter();
        }
        throw new IllegalArgumentException(b.n("Type is not supported by this MessageAdapterFactory: ", type));
    }
}
