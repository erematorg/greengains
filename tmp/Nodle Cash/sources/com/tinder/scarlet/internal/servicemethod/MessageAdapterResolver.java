package com.tinder.scarlet.internal.servicemethod;

import androidx.compose.animation.core.a;
import com.tinder.scarlet.MessageAdapter;
import io.reactivex.exceptions.CompositeException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J)\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0002\u0010\u0010J'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\u0010\u0010R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;", "", "messageAdapterFactories", "", "Lcom/tinder/scarlet/MessageAdapter$Factory;", "(Ljava/util/List;)V", "messageAdapterCache", "", "Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver$MessageAdapterKey;", "Lcom/tinder/scarlet/MessageAdapter;", "findMessageAdapter", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/MessageAdapter;", "resolve", "MessageAdapterKey", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMessageAdapterResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MessageAdapterResolver.kt\ncom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,66:1\n37#2,2:67\n*S KotlinDebug\n*F\n+ 1 MessageAdapterResolver.kt\ncom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver\n*L\n39#1:67,2\n*E\n"})
public final class MessageAdapterResolver {
    @NotNull
    private final Map<MessageAdapterKey, MessageAdapter<Object>> messageAdapterCache = new LinkedHashMap();
    @NotNull
    private final List<MessageAdapter.Factory> messageAdapterFactories;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\tJ(\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver$MessageAdapterKey;", "", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)V", "getAnnotations", "()[Ljava/lang/annotation/Annotation;", "[Ljava/lang/annotation/Annotation;", "getType", "()Ljava/lang/reflect/Type;", "component1", "component2", "copy", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver$MessageAdapterKey;", "equals", "", "other", "hashCode", "", "toString", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MessageAdapterKey {
        @NotNull
        private final Annotation[] annotations;
        @NotNull
        private final Type type;

        public MessageAdapterKey(@NotNull Type type2, @NotNull Annotation[] annotationArr) {
            Intrinsics.checkNotNullParameter(type2, "type");
            Intrinsics.checkNotNullParameter(annotationArr, "annotations");
            this.type = type2;
            this.annotations = annotationArr;
        }

        public static /* synthetic */ MessageAdapterKey copy$default(MessageAdapterKey messageAdapterKey, Type type2, Annotation[] annotationArr, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                type2 = messageAdapterKey.type;
            }
            if ((i3 & 2) != 0) {
                annotationArr = messageAdapterKey.annotations;
            }
            return messageAdapterKey.copy(type2, annotationArr);
        }

        @NotNull
        public final Type component1() {
            return this.type;
        }

        @NotNull
        public final Annotation[] component2() {
            return this.annotations;
        }

        @NotNull
        public final MessageAdapterKey copy(@NotNull Type type2, @NotNull Annotation[] annotationArr) {
            Intrinsics.checkNotNullParameter(type2, "type");
            Intrinsics.checkNotNullParameter(annotationArr, "annotations");
            return new MessageAdapterKey(type2, annotationArr);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!Intrinsics.areEqual((Object) MessageAdapterKey.class, (Object) obj != null ? obj.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.tinder.scarlet.internal.servicemethod.MessageAdapterResolver.MessageAdapterKey");
            MessageAdapterKey messageAdapterKey = (MessageAdapterKey) obj;
            return Intrinsics.areEqual((Object) this.type, (Object) messageAdapterKey.type) && Arrays.equals(this.annotations, messageAdapterKey.annotations);
        }

        @NotNull
        public final Annotation[] getAnnotations() {
            return this.annotations;
        }

        @NotNull
        public final Type getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + Arrays.hashCode(this.annotations);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("MessageAdapterKey(type=");
            sb.append(this.type);
            sb.append(", annotations=");
            return a.o(')', Arrays.toString(this.annotations), sb);
        }
    }

    public MessageAdapterResolver(@NotNull List<? extends MessageAdapter.Factory> list) {
        Intrinsics.checkNotNullParameter(list, "messageAdapterFactories");
        this.messageAdapterFactories = list;
    }

    private final MessageAdapter<Object> findMessageAdapter(Type type, Annotation[] annotationArr) {
        ArrayList arrayList = new ArrayList();
        for (MessageAdapter.Factory create : this.messageAdapterFactories) {
            try {
                MessageAdapter<?> create2 = create.create(type, annotationArr);
                Intrinsics.checkNotNull(create2, "null cannot be cast to non-null type com.tinder.scarlet.MessageAdapter<kotlin.Any>");
                return create2;
            } catch (Throwable th) {
                arrayList.add(th);
            }
        }
        Throwable[] thArr = (Throwable[]) arrayList.toArray(new Throwable[0]);
        CompositeException compositeException = new CompositeException((Throwable[]) Arrays.copyOf(thArr, thArr.length));
        throw new IllegalStateException("Cannot resolve message adapter for type: " + type + ", annotations: " + annotationArr + ClassUtils.PACKAGE_SEPARATOR_CHAR, compositeException);
    }

    @NotNull
    public final MessageAdapter<Object> resolve(@NotNull Type type, @NotNull Annotation[] annotationArr) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(annotationArr, "annotations");
        MessageAdapterKey messageAdapterKey = new MessageAdapterKey(type, annotationArr);
        if (this.messageAdapterCache.containsKey(messageAdapterKey)) {
            MessageAdapter<Object> messageAdapter = this.messageAdapterCache.get(messageAdapterKey);
            Intrinsics.checkNotNull(messageAdapter);
            return messageAdapter;
        }
        MessageAdapter<Object> findMessageAdapter = findMessageAdapter(type, annotationArr);
        this.messageAdapterCache.put(messageAdapterKey, findMessageAdapter);
        return findMessageAdapter;
    }
}
