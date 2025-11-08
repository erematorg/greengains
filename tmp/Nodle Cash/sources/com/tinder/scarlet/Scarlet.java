package com.tinder.scarlet;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.devtools.ksp.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tinder.scarlet.MessageAdapter;
import com.tinder.scarlet.StreamAdapter;
import com.tinder.scarlet.WebSocket;
import com.tinder.scarlet.internal.Service;
import com.tinder.scarlet.internal.connection.Connection;
import com.tinder.scarlet.internal.servicemethod.EventMapper;
import com.tinder.scarlet.internal.servicemethod.MessageAdapterResolver;
import com.tinder.scarlet.internal.servicemethod.ServiceMethod;
import com.tinder.scarlet.internal.servicemethod.ServiceMethodExecutor;
import com.tinder.scarlet.internal.servicemethod.StreamAdapterResolver;
import com.tinder.scarlet.internal.utils.RuntimePlatform;
import com.tinder.scarlet.lifecycle.DefaultLifecycle;
import com.tinder.scarlet.lifecycle.LifecycleRegistry;
import com.tinder.scarlet.messageadapter.builtin.BuiltInMessageAdapterFactory;
import com.tinder.scarlet.retry.BackoffStrategy;
import com.tinder.scarlet.retry.ExponentialBackoffStrategy;
import com.tinder.scarlet.streamadapter.builtin.BuiltInStreamAdapterFactory;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001fB\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u0001H\b¢\u0006\u0002\u0010\tJ\u001f\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000b¢\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002JA\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0015\u001a\u00020\u00012\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0002¢\u0006\u0002\u0010\fJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/tinder/scarlet/Scarlet;", "", "runtimePlatform", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "serviceFactory", "Lcom/tinder/scarlet/internal/Service$Factory;", "(Lcom/tinder/scarlet/internal/utils/RuntimePlatform;Lcom/tinder/scarlet/internal/Service$Factory;)V", "create", "T", "()Ljava/lang/Object;", "service", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "createInvocationHandler", "Ljava/lang/reflect/InvocationHandler;", "serviceInterface", "serviceInstance", "Lcom/tinder/scarlet/internal/Service;", "handleJavaObjectMethod", "method", "Ljava/lang/reflect/Method;", "proxy", "args", "", "(Ljava/lang/reflect/Method;Lcom/tinder/scarlet/internal/Service;Ljava/lang/Class;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", "implementService", "isEquals", "", "isHashCode", "isJavaObjectMethod", "isToString", "Builder", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Scarlet {
    @NotNull
    private final RuntimePlatform runtimePlatform;
    @NotNull
    private final Service.Factory serviceFactory;

    @SourceDebugExtension({"SMAP\nScarlet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Scarlet.kt\ncom/tinder/scarlet/Scarlet$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,222:1\n1#2:223\n*E\n"})
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\rJ\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/tinder/scarlet/Scarlet$Builder;", "", "()V", "backoffStrategy", "Lcom/tinder/scarlet/retry/BackoffStrategy;", "lifecycle", "Lcom/tinder/scarlet/Lifecycle;", "messageAdapterFactories", "", "Lcom/tinder/scarlet/MessageAdapter$Factory;", "platform", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "streamAdapterFactories", "Lcom/tinder/scarlet/StreamAdapter$Factory;", "webSocketFactory", "Lcom/tinder/scarlet/WebSocket$Factory;", "addMessageAdapterFactory", "factory", "addStreamAdapterFactory", "build", "Lcom/tinder/scarlet/Scarlet;", "createConnectionFactory", "Lcom/tinder/scarlet/internal/connection/Connection$Factory;", "createMessageAdapterResolver", "Lcom/tinder/scarlet/internal/servicemethod/MessageAdapterResolver;", "createServiceFactory", "Lcom/tinder/scarlet/internal/Service$Factory;", "createServiceMethodExecutorFactory", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory;", "createStreamAdapterResolver", "Lcom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver;", "Companion", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        @NotNull
        private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        @NotNull
        private static final DefaultLifecycle DEFAULT_LIFECYCLE = new DefaultLifecycle((LifecycleRegistry) null, 1, (DefaultConstructorMarker) null);
        @NotNull
        private static final ExponentialBackoffStrategy DEFAULT_RETRY_STRATEGY = new ExponentialBackoffStrategy(1000, 10000);
        @NotNull
        private static final Scheduler DEFAULT_SCHEDULER;
        private static final long RETRY_BASE_DURATION = 1000;
        private static final long RETRY_MAX_DURATION = 10000;
        @NotNull
        private BackoffStrategy backoffStrategy = DEFAULT_RETRY_STRATEGY;
        @NotNull
        private Lifecycle lifecycle = DEFAULT_LIFECYCLE;
        @NotNull
        private final List<MessageAdapter.Factory> messageAdapterFactories = new ArrayList();
        @NotNull
        private final RuntimePlatform platform = RuntimePlatform.Companion.get();
        @NotNull
        private final List<StreamAdapter.Factory> streamAdapterFactories = new ArrayList();
        @Nullable
        private WebSocket.Factory webSocketFactory;

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXD¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/tinder/scarlet/Scarlet$Builder$Companion;", "", "()V", "DEFAULT_LIFECYCLE", "Lcom/tinder/scarlet/lifecycle/DefaultLifecycle;", "DEFAULT_RETRY_STRATEGY", "Lcom/tinder/scarlet/retry/ExponentialBackoffStrategy;", "DEFAULT_SCHEDULER", "Lio/reactivex/Scheduler;", "RETRY_BASE_DURATION", "", "RETRY_MAX_DURATION", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        static {
            Scheduler computation = Schedulers.computation();
            Intrinsics.checkNotNullExpressionValue(computation, "computation()");
            DEFAULT_SCHEDULER = computation;
        }

        private final Connection.Factory createConnectionFactory() {
            Lifecycle lifecycle2 = this.lifecycle;
            WebSocket.Factory factory = this.webSocketFactory;
            if (factory != null) {
                return new Connection.Factory(lifecycle2, factory, this.backoffStrategy, DEFAULT_SCHEDULER);
            }
            throw new IllegalStateException("Required value was null.");
        }

        private final MessageAdapterResolver createMessageAdapterResolver() {
            List<MessageAdapter.Factory> list = this.messageAdapterFactories;
            list.add(new BuiltInMessageAdapterFactory());
            return new MessageAdapterResolver(CollectionsKt.toList(list));
        }

        private final Service.Factory createServiceFactory() {
            return new Service.Factory(createConnectionFactory(), createServiceMethodExecutorFactory());
        }

        private final ServiceMethodExecutor.Factory createServiceMethodExecutorFactory() {
            MessageAdapterResolver createMessageAdapterResolver = createMessageAdapterResolver();
            StreamAdapterResolver createStreamAdapterResolver = createStreamAdapterResolver();
            EventMapper.Factory factory = new EventMapper.Factory(createMessageAdapterResolver);
            return new ServiceMethodExecutor.Factory(this.platform, new ServiceMethod.Send.Factory(createMessageAdapterResolver), new ServiceMethod.Receive.Factory(DEFAULT_SCHEDULER, factory, createStreamAdapterResolver));
        }

        private final StreamAdapterResolver createStreamAdapterResolver() {
            List<StreamAdapter.Factory> list = this.streamAdapterFactories;
            list.add(new BuiltInStreamAdapterFactory());
            return new StreamAdapterResolver(CollectionsKt.toList(list));
        }

        @NotNull
        public final Builder addMessageAdapterFactory(@NotNull MessageAdapter.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.messageAdapterFactories.add(factory);
            return this;
        }

        @NotNull
        public final Builder addStreamAdapterFactory(@NotNull StreamAdapter.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.streamAdapterFactories.add(factory);
            return this;
        }

        @NotNull
        public final Builder backoffStrategy(@NotNull BackoffStrategy backoffStrategy2) {
            Intrinsics.checkNotNullParameter(backoffStrategy2, "backoffStrategy");
            this.backoffStrategy = backoffStrategy2;
            return this;
        }

        @NotNull
        public final Scarlet build() {
            return new Scarlet(this.platform, createServiceFactory());
        }

        @NotNull
        public final Builder lifecycle(@NotNull Lifecycle lifecycle2) {
            Intrinsics.checkNotNullParameter(lifecycle2, "lifecycle");
            this.lifecycle = lifecycle2;
            return this;
        }

        @NotNull
        public final Builder webSocketFactory(@NotNull WebSocket.Factory factory) {
            Intrinsics.checkNotNullParameter(factory, "factory");
            this.webSocketFactory = factory;
            return this;
        }
    }

    public Scarlet(@NotNull RuntimePlatform runtimePlatform2, @NotNull Service.Factory factory) {
        Intrinsics.checkNotNullParameter(runtimePlatform2, "runtimePlatform");
        Intrinsics.checkNotNullParameter(factory, "serviceFactory");
        this.runtimePlatform = runtimePlatform2;
        this.serviceFactory = factory;
    }

    private final InvocationHandler createInvocationHandler(Class<?> cls, Service service) {
        return new a(this, 1, service, cls);
    }

    /* access modifiers changed from: private */
    public static final Object createInvocationHandler$lambda$0(Scarlet scarlet, Class cls, Service service, Object obj, Method method, Object[] objArr) {
        Intrinsics.checkNotNullParameter(scarlet, "this$0");
        Intrinsics.checkNotNullParameter(cls, "$serviceInterface");
        Intrinsics.checkNotNullParameter(service, "$serviceInstance");
        if (objArr == null) {
            objArr = new Object[0];
        }
        Object[] objArr2 = objArr;
        RuntimePlatform runtimePlatform2 = scarlet.runtimePlatform;
        Intrinsics.checkNotNullExpressionValue(method, FirebaseAnalytics.Param.METHOD);
        if (runtimePlatform2.isDefaultMethod(method)) {
            RuntimePlatform runtimePlatform3 = scarlet.runtimePlatform;
            Intrinsics.checkNotNullExpressionValue(obj, "proxy");
            return runtimePlatform3.invokeDefaultMethod(method, cls, obj, objArr2);
        } else if (!scarlet.isJavaObjectMethod(method)) {
            return service.execute(method, objArr2);
        } else {
            Intrinsics.checkNotNullExpressionValue(obj, "proxy");
            return scarlet.handleJavaObjectMethod(method, service, cls, obj, objArr2);
        }
    }

    private final Object handleJavaObjectMethod(Method method, Service service, Class<?> cls, Object obj, Object[] objArr) {
        if (isEquals(method)) {
            boolean z2 = false;
            if (obj == objArr[0]) {
                z2 = true;
            }
            return Boolean.valueOf(z2);
        } else if (isToString(method)) {
            return "Scarlet service implementation for ".concat(cls.getName());
        } else {
            if (isHashCode(method)) {
                return Integer.valueOf(service.hashCode());
            }
            throw new IllegalStateException("Cannot execute " + method);
        }
    }

    private final <T> T implementService(Class<T> cls) {
        Service create = this.serviceFactory.create(cls);
        create.startForever();
        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, createInvocationHandler(cls, create)));
    }

    private final boolean isEquals(Method method) {
        return Intrinsics.areEqual((Object) method.getName(), (Object) "equals") && Arrays.equals(new Class[]{Object.class}, method.getParameterTypes());
    }

    private final boolean isHashCode(Method method) {
        if (Intrinsics.areEqual((Object) method.getName(), (Object) "hashCode")) {
            Class[] parameterTypes = method.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "method.parameterTypes");
            if (parameterTypes.length == 0) {
                return true;
            }
        }
        return false;
    }

    private final boolean isJavaObjectMethod(Method method) {
        return Intrinsics.areEqual((Object) method.getDeclaringClass(), (Object) Object.class);
    }

    private final boolean isToString(Method method) {
        if (Intrinsics.areEqual((Object) method.getName(), (Object) "toString")) {
            Class[] parameterTypes = method.getParameterTypes();
            Intrinsics.checkNotNullExpressionValue(parameterTypes, "method.parameterTypes");
            if (parameterTypes.length == 0) {
                return true;
            }
        }
        return false;
    }

    public final <T> T create(@NotNull Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, NotificationCompat.CATEGORY_SERVICE);
        return implementService(cls);
    }

    public final /* synthetic */ <T> T create() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return create(Object.class);
    }
}
