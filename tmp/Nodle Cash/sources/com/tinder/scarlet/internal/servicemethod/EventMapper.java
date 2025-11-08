package com.tinder.scarlet.internal.servicemethod;

import T.a;
import androidx.core.app.NotificationCompat;
import com.tinder.scarlet.Deserialization;
import com.tinder.scarlet.Event;
import com.tinder.scarlet.Lifecycle;
import com.tinder.scarlet.Message;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.State;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.utils.TypeUtils;
import io.reactivex.Maybe;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \b*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\t\b\t\n\u000b\f\r\u000e\u000f\u0010B\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u0001\u0007\u0011\u0012\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "T", "", "()V", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "Companion", "Factory", "FilterEventType", "NoOp", "ToDeserialization", "ToDeserializedValue", "ToLifecycleState", "ToState", "ToWebSocketEvent", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$FilterEventType;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$NoOp;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserialization;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserializedValue;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToLifecycleState;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToState;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToWebSocketEvent;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class EventMapper<T> {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$Companion;", "", "()V", "getFirstTypeArgument", "Ljava/lang/reflect/Type;", "Ljava/lang/reflect/ParameterizedType;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final Type getFirstTypeArgument(ParameterizedType parameterizedType) {
            return TypeUtils.getParameterUpperBound(parameterizedType, 0);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0002J)\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$Factory;", "", "messageAdapterResolver", "Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;", "(Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;)V", "toDeserializationCache", "", "Lcom/tinder/scarlet/MessageAdapter;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserialization;", "create", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "returnType", "Ljava/lang/reflect/ParameterizedType;", "annotations", "", "", "(Ljava/lang/reflect/ParameterizedType;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "createToDeserializationIfNeeded", "messageAdapter", "resolveMessageAdapter", "(Ljava/lang/reflect/ParameterizedType;[Ljava/lang/annotation/Annotation;)Lcom/tinder/scarlet/MessageAdapter;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory {
        @NotNull
        private final MessageAdapterResolver messageAdapterResolver;
        @NotNull
        private final Map<MessageAdapter<Object>, ToDeserialization<?>> toDeserializationCache = new LinkedHashMap();

        public Factory(@NotNull MessageAdapterResolver messageAdapterResolver2) {
            Intrinsics.checkNotNullParameter(messageAdapterResolver2, "messageAdapterResolver");
            this.messageAdapterResolver = messageAdapterResolver2;
        }

        private final ToDeserialization<?> createToDeserializationIfNeeded(MessageAdapter<Object> messageAdapter) {
            if (this.toDeserializationCache.containsKey(messageAdapter)) {
                ToDeserialization<?> toDeserialization = this.toDeserializationCache.get(messageAdapter);
                Intrinsics.checkNotNull(toDeserialization);
                return toDeserialization;
            }
            ToDeserialization<?> toDeserialization2 = new ToDeserialization<>(messageAdapter);
            this.toDeserializationCache.put(messageAdapter, toDeserialization2);
            return toDeserialization2;
        }

        private final MessageAdapter<Object> resolveMessageAdapter(ParameterizedType parameterizedType, Annotation[] annotationArr) {
            Companion companion = EventMapper.Companion;
            Type access$getFirstTypeArgument = companion.getFirstTypeArgument(parameterizedType);
            if (Intrinsics.areEqual((Object) TypeUtils.getRawType(access$getFirstTypeArgument), (Object) Deserialization.class)) {
                Intrinsics.checkNotNull(access$getFirstTypeArgument, "null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
                access$getFirstTypeArgument = companion.getFirstTypeArgument((ParameterizedType) access$getFirstTypeArgument);
            }
            return this.messageAdapterResolver.resolve(access$getFirstTypeArgument, annotationArr);
        }

        @NotNull
        public final EventMapper<?> create(@NotNull ParameterizedType parameterizedType, @NotNull Annotation[] annotationArr) {
            Intrinsics.checkNotNullParameter(parameterizedType, "returnType");
            Intrinsics.checkNotNullParameter(annotationArr, "annotations");
            Class<?> rawType = TypeUtils.getRawType(EventMapper.Companion.getFirstTypeArgument(parameterizedType));
            Class<Event> cls = Event.class;
            if (Intrinsics.areEqual((Object) rawType, (Object) cls)) {
                return NoOp.INSTANCE;
            }
            if (!cls.isAssignableFrom(rawType)) {
                Class<Lifecycle.State> cls2 = Lifecycle.State.class;
                if (Intrinsics.areEqual((Object) cls2, (Object) rawType)) {
                    return ToLifecycleState.INSTANCE;
                }
                if (!cls2.isAssignableFrom(rawType)) {
                    Class<WebSocket.Event> cls3 = WebSocket.Event.class;
                    if (Intrinsics.areEqual((Object) cls3, (Object) rawType)) {
                        return ToWebSocketEvent.INSTANCE;
                    }
                    if (!cls3.isAssignableFrom(rawType)) {
                        Class<State> cls4 = State.class;
                        if (Intrinsics.areEqual((Object) cls4, (Object) rawType)) {
                            return ToState.INSTANCE;
                        }
                        if (!cls4.isAssignableFrom(rawType)) {
                            ToDeserialization<?> createToDeserializationIfNeeded = createToDeserializationIfNeeded(resolveMessageAdapter(parameterizedType, annotationArr));
                            return Intrinsics.areEqual((Object) rawType, (Object) Deserialization.class) ? createToDeserializationIfNeeded : new ToDeserializedValue(createToDeserializationIfNeeded);
                        }
                        throw new IllegalArgumentException("Subclasses of State is not supported");
                    }
                    throw new IllegalArgumentException("Subclasses of WebSocket.Event is not supported");
                }
                throw new IllegalArgumentException("Subclasses of Lifecycle.Event is not supported");
            }
            throw new IllegalArgumentException("Subclasses of Event is not supported");
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$FilterEventType;", "E", "Lcom/tinder/scarlet/Event;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "mapToData", "Lio/reactivex/Maybe;", "event", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FilterEventType<E extends Event> extends EventMapper<E> {
        @NotNull
        private final Class<E> clazz;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FilterEventType(@NotNull Class<E> cls) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(cls, "clazz");
            this.clazz = cls;
        }

        @NotNull
        public Maybe<E> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            if (this.clazz.isInstance(event)) {
                Maybe<E> just = Maybe.just(event);
                Intrinsics.checkNotNullExpressionValue(just, "{\n            @Suppress(…ust(event as E)\n        }");
                return just;
            }
            Maybe<E> empty = Maybe.empty();
            Intrinsics.checkNotNullExpressionValue(empty, "{\n            Maybe.empty()\n        }");
            return empty;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$NoOp;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "", "()V", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NoOp extends EventMapper<Object> {
        @NotNull
        public static final NoOp INSTANCE = new NoOp();

        private NoOp() {
            super((DefaultConstructorMarker) null);
        }

        @NotNull
        public Maybe<Object> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<Object> just = Maybe.just(event);
            Intrinsics.checkNotNullExpressionValue(just, "just(event)");
            return just;
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\u0007J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00040\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004*\u00020\u000fH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserialization;", "T", "", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "Lcom/tinder/scarlet/Deserialization;", "messageAdapter", "Lcom/tinder/scarlet/MessageAdapter;", "(Lcom/tinder/scarlet/MessageAdapter;)V", "toWebSocketEvent", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToWebSocketEvent;", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "deserialize", "Lcom/tinder/scarlet/Message;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ToDeserialization<T> extends EventMapper<Deserialization<T>> {
        @NotNull
        private final MessageAdapter<T> messageAdapter;
        @NotNull
        private final ToWebSocketEvent toWebSocketEvent = ToWebSocketEvent.INSTANCE;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ToDeserialization(@NotNull MessageAdapter<T> messageAdapter2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(messageAdapter2, "messageAdapter");
            this.messageAdapter = messageAdapter2;
        }

        /* access modifiers changed from: private */
        public final Deserialization<T> deserialize(Message message) {
            try {
                return new Deserialization.Success(this.messageAdapter.fromMessage(message));
            } catch (Throwable th) {
                return new Deserialization.Error(th);
            }
        }

        /* access modifiers changed from: private */
        public static final boolean mapToData$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final Deserialization mapToData$lambda$1(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (Deserialization) function1.invoke(obj);
        }

        @NotNull
        public Maybe<Deserialization<T>> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<R> map = this.toWebSocketEvent.mapToData(event).filter(new a(EventMapper$ToDeserialization$mapToData$1.INSTANCE, 8)).map(new a(new EventMapper$ToDeserialization$mapToData$2(this), 0));
            Intrinsics.checkNotNullExpressionValue(map, "override fun mapToData(e…).message.deserialize() }");
            return map;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserializedValue;", "T", "", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "toDeserialization", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserialization;", "(Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToDeserialization;)V", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ToDeserializedValue<T> extends EventMapper<T> {
        @NotNull
        private final ToDeserialization<T> toDeserialization;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ToDeserializedValue(@NotNull ToDeserialization<T> toDeserialization2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(toDeserialization2, "toDeserialization");
            this.toDeserialization = toDeserialization2;
        }

        /* access modifiers changed from: private */
        public static final boolean mapToData$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return ((Boolean) function1.invoke(obj)).booleanValue();
        }

        /* access modifiers changed from: private */
        public static final Object mapToData$lambda$1(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return function1.invoke(obj);
        }

        @NotNull
        public Maybe<T> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<R> map = this.toDeserialization.mapToData(event).filter(new a(EventMapper$ToDeserializedValue$mapToData$1.INSTANCE, 9)).map(new a(EventMapper$ToDeserializedValue$mapToData$2.INSTANCE, 1));
            Intrinsics.checkNotNullExpressionValue(map, "toDeserialization.mapToD…lization.Success).value }");
            return map;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToLifecycleState;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "Lcom/tinder/scarlet/Lifecycle$State;", "()V", "filterEventType", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$FilterEventType;", "Lcom/tinder/scarlet/Event$OnLifecycle$StateChange;", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ToLifecycleState extends EventMapper<Lifecycle.State> {
        @NotNull
        public static final ToLifecycleState INSTANCE = new ToLifecycleState();
        @NotNull
        private static final FilterEventType<Event.OnLifecycle.StateChange<?>> filterEventType = new FilterEventType<>(Event.OnLifecycle.StateChange.class);

        private ToLifecycleState() {
            super((DefaultConstructorMarker) null);
        }

        /* access modifiers changed from: private */
        public static final Lifecycle.State mapToData$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (Lifecycle.State) function1.invoke(obj);
        }

        @NotNull
        public Maybe<Lifecycle.State> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<R> map = filterEventType.mapToData(event).map(new a(EventMapper$ToLifecycleState$mapToData$1.INSTANCE, 2));
            Intrinsics.checkNotNullExpressionValue(map, "filterEventType.mapToData(event).map { it.state }");
            return map;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToState;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "Lcom/tinder/scarlet/State;", "()V", "filterEventType", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$FilterEventType;", "Lcom/tinder/scarlet/Event$OnStateChange;", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ToState extends EventMapper<State> {
        @NotNull
        public static final ToState INSTANCE = new ToState();
        @NotNull
        private static final FilterEventType<Event.OnStateChange<?>> filterEventType = new FilterEventType<>(Event.OnStateChange.class);

        private ToState() {
            super((DefaultConstructorMarker) null);
        }

        /* access modifiers changed from: private */
        public static final State mapToData$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (State) function1.invoke(obj);
        }

        @NotNull
        public Maybe<State> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<R> map = filterEventType.mapToData(event).map(new a(EventMapper$ToState$mapToData$1.INSTANCE, 3));
            Intrinsics.checkNotNullExpressionValue(map, "filterEventType.mapToData(event).map { it.state }");
            return map;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/EventMapper$ToWebSocketEvent;", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "Lcom/tinder/scarlet/WebSocket$Event;", "()V", "filterEventType", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$FilterEventType;", "Lcom/tinder/scarlet/Event$OnWebSocket$Event;", "mapToData", "Lio/reactivex/Maybe;", "event", "Lcom/tinder/scarlet/Event;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ToWebSocketEvent extends EventMapper<WebSocket.Event> {
        @NotNull
        public static final ToWebSocketEvent INSTANCE = new ToWebSocketEvent();
        @NotNull
        private static final FilterEventType<Event.OnWebSocket.C0066Event<?>> filterEventType = new FilterEventType<>(Event.OnWebSocket.C0066Event.class);

        private ToWebSocketEvent() {
            super((DefaultConstructorMarker) null);
        }

        /* access modifiers changed from: private */
        public static final WebSocket.Event mapToData$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (WebSocket.Event) function1.invoke(obj);
        }

        @NotNull
        public Maybe<WebSocket.Event> mapToData(@NotNull Event event) {
            Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
            Maybe<R> map = filterEventType.mapToData(event).map(new a(EventMapper$ToWebSocketEvent$mapToData$1.INSTANCE, 4));
            Intrinsics.checkNotNullExpressionValue(map, "filterEventType.mapToData(event).map { it.event }");
            return map;
        }
    }

    public /* synthetic */ EventMapper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public abstract Maybe<T> mapToData(@NotNull Event event);

    private EventMapper() {
    }
}
