package com.tinder.scarlet.internal.servicemethod;

import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.work.impl.utils.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.internal.connection.Connection;
import com.tinder.scarlet.internal.servicemethod.EventMapper;
import com.tinder.scarlet.utils.FlowableUtils;
import com.tinder.scarlet.utils.TypeUtils;
import io.reactivex.Flowable;
import io.reactivex.MaybeSource;
import io.reactivex.Scheduler;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00032\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "", "()V", "Companion", "Factory", "Receive", "Send", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ServiceMethod {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @SourceDebugExtension({"SMAP\nServiceMethod.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,110:1\n1726#2,3:111\n12744#3,2:114\n*S KotlinDebug\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Companion\n*L\n96#1:111,3\n100#1:114,2\n*E\n"})
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007J\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0002J<\u0010\n\u001a\u00020\u000b*\u00020\u00062\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\u0004\"\u0006\u0012\u0002\b\u00030\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\b¢\u0006\u0002\u0010\u0010J<\u0010\u0011\u001a\u00020\u000b*\u00020\u00062\u001a\u0010\f\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\r0\u0004\"\u0006\u0012\u0002\b\u00030\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\b¢\u0006\u0002\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u000b*\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\b¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Companion;", "", "()V", "getFirstParameterAnnotations", "", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)[Ljava/lang/annotation/Annotation;", "getFirstParameterType", "Ljava/lang/reflect/Type;", "requireParameterTypes", "", "types", "Ljava/lang/Class;", "lazyMessage", "Lkotlin/Function0;", "(Ljava/lang/reflect/Method;[Ljava/lang/Class;Lkotlin/jvm/functions/Function0;)V", "requireReturnTypeIsOneOf", "requireReturnTypeIsResolvable", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final Annotation[] getFirstParameterAnnotations(Method method) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Intrinsics.checkNotNullExpressionValue(parameterAnnotations, "parameterAnnotations");
            Object first = ArraysKt.first((T[]) parameterAnnotations);
            Intrinsics.checkNotNullExpressionValue(first, "parameterAnnotations.first()");
            return (Annotation[]) first;
        }

        /* access modifiers changed from: private */
        public final Type getFirstParameterType(Method method) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "genericParameterTypes");
            Object first = ArraysKt.first((T[]) genericParameterTypes);
            Intrinsics.checkNotNullExpressionValue(first, "genericParameterTypes.first()");
            return (Type) first;
        }

        private final void requireParameterTypes(Method method, Class<?>[] clsArr, Function0<? extends Object> function0) {
            if (method.getGenericParameterTypes().length == clsArr.length) {
                Type[] genericParameterTypes = method.getGenericParameterTypes();
                Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "genericParameterTypes");
                Iterable<Pair> zip = ArraysKt.zip((T[]) genericParameterTypes, (R[]) clsArr);
                if (!(zip instanceof Collection) || !((Collection) zip).isEmpty()) {
                    for (Pair pair : zip) {
                        Type type = (Type) pair.component1();
                        Class cls = (Class) pair.component2();
                        if (cls != type && !cls.isInstance(type)) {
                            throw new IllegalArgumentException(function0.invoke().toString());
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalArgumentException(function0.invoke().toString());
        }

        private final void requireReturnTypeIsOneOf(Method method, Class<?>[] clsArr, Function0<? extends Object> function0) {
            int length = clsArr.length;
            int i3 = 0;
            while (i3 < length) {
                Class<?> cls = clsArr[i3];
                if (cls != method.getGenericReturnType() && !cls.isInstance(method.getGenericReturnType())) {
                    i3++;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException(function0.invoke().toString());
        }

        private final void requireReturnTypeIsResolvable(Method method, Function0<? extends Object> function0) {
            Type genericReturnType = method.getGenericReturnType();
            Intrinsics.checkNotNullExpressionValue(genericReturnType, "genericReturnType");
            if (TypeUtils.hasUnresolvableType(genericReturnType)) {
                throw new IllegalArgumentException(function0.invoke().toString());
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Factory;", "", "create", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "method", "Ljava/lang/reflect/Method;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Factory {
        @NotNull
        ServiceMethod create(@NotNull Connection connection, @NotNull Method method);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u000fB5\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "eventMapper", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "scheduler", "Lio/reactivex/Scheduler;", "streamAdapter", "Lcom/tinder/scarlet/StreamAdapter;", "", "(Lcom/tinder/scarlet/internal/servicemethod/EventMapper;Lcom/tinder/scarlet/internal/connection/Connection;Lio/reactivex/Scheduler;Lcom/tinder/scarlet/StreamAdapter;)V", "getEventMapper$scarlet", "()Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "execute", "Factory", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Receive extends ServiceMethod {
        @NotNull
        private final Connection connection;
        @NotNull
        private final EventMapper<?> eventMapper;
        @NotNull
        private final Scheduler scheduler;
        @NotNull
        private final StreamAdapter<Object, Object> streamAdapter;

        @SourceDebugExtension({"SMAP\nServiceMethod.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive$Factory\n+ 2 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Companion\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,110:1\n95#2,2:111\n97#2:116\n100#2:117\n103#2:120\n1726#3,3:113\n12744#4,2:118\n*S KotlinDebug\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive$Factory\n*L\n72#1:111,2\n72#1:116\n73#1:117\n76#1:120\n72#1:113,3\n73#1:118,2\n*E\n"})
        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive$Factory;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Factory;", "scheduler", "Lio/reactivex/Scheduler;", "eventMapperFactory", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper$Factory;", "streamAdapterResolver", "Lcom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver;", "(Lio/reactivex/Scheduler;Lcom/tinder/scarlet/internal/servicemethod/EventMapper$Factory;Lcom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver;)V", "create", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "method", "Ljava/lang/reflect/Method;", "createEventMapper", "Lcom/tinder/scarlet/internal/servicemethod/EventMapper;", "createStreamAdapter", "Lcom/tinder/scarlet/StreamAdapter;", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Factory implements Factory {
            @NotNull
            private final EventMapper.Factory eventMapperFactory;
            @NotNull
            private final Scheduler scheduler;
            @NotNull
            private final StreamAdapterResolver streamAdapterResolver;

            public Factory(@NotNull Scheduler scheduler2, @NotNull EventMapper.Factory factory, @NotNull StreamAdapterResolver streamAdapterResolver2) {
                Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
                Intrinsics.checkNotNullParameter(factory, "eventMapperFactory");
                Intrinsics.checkNotNullParameter(streamAdapterResolver2, "streamAdapterResolver");
                this.scheduler = scheduler2;
                this.eventMapperFactory = factory;
                this.streamAdapterResolver = streamAdapterResolver2;
            }

            private final EventMapper<?> createEventMapper(Method method) {
                EventMapper.Factory factory = this.eventMapperFactory;
                Type genericReturnType = method.getGenericReturnType();
                Intrinsics.checkNotNull(genericReturnType, "null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
                Annotation[] annotations = method.getAnnotations();
                Intrinsics.checkNotNullExpressionValue(annotations, "method.annotations");
                return factory.create((ParameterizedType) genericReturnType, annotations);
            }

            private final StreamAdapter<Object, Object> createStreamAdapter(Method method) {
                StreamAdapterResolver streamAdapterResolver2 = this.streamAdapterResolver;
                Type genericReturnType = method.getGenericReturnType();
                Intrinsics.checkNotNullExpressionValue(genericReturnType, "method.genericReturnType");
                return streamAdapterResolver2.resolve(genericReturnType);
            }

            @NotNull
            public Receive create(@NotNull Connection connection, @NotNull Method method) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
                Companion companion = ServiceMethod.Companion;
                Class[] clsArr = new Class[0];
                if (method.getGenericParameterTypes().length == 0) {
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "genericParameterTypes");
                    Iterable<Pair> zip = ArraysKt.zip((T[]) genericParameterTypes, (R[]) clsArr);
                    if (!(zip instanceof Collection) || !((Collection) zip).isEmpty()) {
                        for (Pair pair : zip) {
                            Type type = (Type) pair.component1();
                            Class cls = (Class) pair.component2();
                            if (cls != type && !cls.isInstance(type)) {
                                throw new IllegalArgumentException(("Receive method must have zero parameter: " + method).toString());
                            }
                        }
                    }
                    Companion companion2 = ServiceMethod.Companion;
                    Class cls2 = new Class[]{ParameterizedType.class}[0];
                    if (cls2 == method.getGenericReturnType() || cls2.isInstance(method.getGenericReturnType())) {
                        Type genericReturnType = method.getGenericReturnType();
                        Intrinsics.checkNotNullExpressionValue(genericReturnType, "genericReturnType");
                        if (!TypeUtils.hasUnresolvableType(genericReturnType)) {
                            return new Receive(createEventMapper(method), connection, this.scheduler, createStreamAdapter(method));
                        }
                        throw new IllegalArgumentException(("Method return type must not include a type variable or wildcard: " + method.getGenericReturnType()).toString());
                    }
                    throw new IllegalArgumentException(("Receive method must return ParameterizedType: " + method).toString());
                }
                throw new IllegalArgumentException(("Receive method must have zero parameter: " + method).toString());
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Receive(@NotNull EventMapper<?> eventMapper2, @NotNull Connection connection2, @NotNull Scheduler scheduler2, @NotNull StreamAdapter<Object, ? extends Object> streamAdapter2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(eventMapper2, "eventMapper");
            Intrinsics.checkNotNullParameter(connection2, "connection");
            Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
            Intrinsics.checkNotNullParameter(streamAdapter2, "streamAdapter");
            this.eventMapper = eventMapper2;
            this.connection = connection2;
            this.scheduler = scheduler2;
            this.streamAdapter = streamAdapter2;
        }

        /* access modifiers changed from: private */
        public static final Publisher execute$lambda$0(Receive receive) {
            Intrinsics.checkNotNullParameter(receive, "this$0");
            return receive.connection.observeEvent();
        }

        /* access modifiers changed from: private */
        public static final MaybeSource execute$lambda$1(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (MaybeSource) function1.invoke(obj);
        }

        @NotNull
        public final Object execute() {
            Flowable flatMapMaybe = Flowable.defer(new a(this, 5)).observeOn(this.scheduler).flatMapMaybe(new a(new ServiceMethod$Receive$execute$stream$2(this.eventMapper), 5));
            Intrinsics.checkNotNullExpressionValue(flatMapMaybe, "defer { connection.obser…e(eventMapper::mapToData)");
            return this.streamAdapter.adapt(FlowableUtils.toStream(flatMapMaybe));
        }

        @NotNull
        public final EventMapper<?> getEventMapper$scarlet() {
            return this.eventMapper;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\nB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "messageAdapter", "Lcom/tinder/scarlet/MessageAdapter;", "", "(Lcom/tinder/scarlet/internal/connection/Connection;Lcom/tinder/scarlet/MessageAdapter;)V", "execute", "data", "Factory", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Send extends ServiceMethod {
        @NotNull
        private final Connection connection;
        @NotNull
        private final MessageAdapter<Object> messageAdapter;

        @SourceDebugExtension({"SMAP\nServiceMethod.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send$Factory\n+ 2 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Companion\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,110:1\n95#2,2:111\n97#2:116\n100#2:117\n1726#3,3:113\n12744#4,2:118\n*S KotlinDebug\n*F\n+ 1 ServiceMethod.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send$Factory\n*L\n36#1:111,2\n36#1:116\n39#1:117\n36#1:113,3\n39#1:118,2\n*E\n"})
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send$Factory;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Factory;", "messageAdapterResolver", "Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;", "(Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;)V", "create", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "method", "Ljava/lang/reflect/Method;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Factory implements Factory {
            @NotNull
            private final MessageAdapterResolver messageAdapterResolver;

            public Factory(@NotNull MessageAdapterResolver messageAdapterResolver2) {
                Intrinsics.checkNotNullParameter(messageAdapterResolver2, "messageAdapterResolver");
                this.messageAdapterResolver = messageAdapterResolver2;
            }

            @NotNull
            public Send create(@NotNull Connection connection, @NotNull Method method) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
                Companion companion = ServiceMethod.Companion;
                Class[] clsArr = {Object.class};
                if (method.getGenericParameterTypes().length == 1) {
                    Type[] genericParameterTypes = method.getGenericParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(genericParameterTypes, "genericParameterTypes");
                    Iterable<Pair> zip = ArraysKt.zip((T[]) genericParameterTypes, (R[]) clsArr);
                    if (!(zip instanceof Collection) || !((Collection) zip).isEmpty()) {
                        for (Pair pair : zip) {
                            Type type = (Type) pair.component1();
                            Class cls = (Class) pair.component2();
                            if (cls != type && !cls.isInstance(type)) {
                                throw new IllegalArgumentException(("Send method must have one and only one parameter: " + method).toString());
                            }
                        }
                    }
                    Companion companion2 = ServiceMethod.Companion;
                    Class cls2 = Boolean.TYPE;
                    Class cls3 = Void.TYPE;
                    Intrinsics.checkNotNullExpressionValue(cls3, CredentialProviderBaseController.TYPE_TAG);
                    Class[] clsArr2 = {cls2, cls3};
                    for (int i3 = 0; i3 < 2; i3++) {
                        Class cls4 = clsArr2[i3];
                        if (cls4 == method.getGenericReturnType() || cls4.isInstance(method.getGenericReturnType())) {
                            Companion companion3 = ServiceMethod.Companion;
                            return new Send(connection, this.messageAdapterResolver.resolve(companion3.getFirstParameterType(method), companion3.getFirstParameterAnnotations(method)));
                        }
                    }
                    throw new IllegalArgumentException(("Send method must return Boolean or Void: " + method).toString());
                }
                throw new IllegalArgumentException(("Send method must have one and only one parameter: " + method).toString());
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Send(@NotNull Connection connection2, @NotNull MessageAdapter<Object> messageAdapter2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(connection2, "connection");
            Intrinsics.checkNotNullParameter(messageAdapter2, "messageAdapter");
            this.connection = connection2;
            this.messageAdapter = messageAdapter2;
        }

        @NotNull
        public final Object execute(@NotNull Object obj) {
            Intrinsics.checkNotNullParameter(obj, "data");
            return Boolean.valueOf(this.connection.send(this.messageAdapter.toMessage(obj)));
        }
    }

    public /* synthetic */ ServiceMethod(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ServiceMethod() {
    }
}
