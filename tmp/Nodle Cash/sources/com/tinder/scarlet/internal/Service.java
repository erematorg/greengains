package com.tinder.scarlet.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tinder.scarlet.internal.connection.Connection;
import com.tinder.scarlet.internal.servicemethod.ServiceMethodExecutor;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/tinder/scarlet/internal/Service;", "", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "serviceMethodExecutor", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor;", "(Lcom/tinder/scarlet/internal/connection/Connection;Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor;)V", "execute", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "startForever", "", "Factory", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Service {
    @NotNull
    private final Connection connection;
    @NotNull
    private final ServiceMethodExecutor serviceMethodExecutor;

    @SourceDebugExtension({"SMAP\nService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Service.kt\ncom/tinder/scarlet/internal/Service$Factory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,42:1\n1#2:43\n*E\n"})
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nJ\u0014\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/tinder/scarlet/internal/Service$Factory;", "", "connectionFactory", "Lcom/tinder/scarlet/internal/connection/Connection$Factory;", "serviceMethodExecutorFactory", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory;", "(Lcom/tinder/scarlet/internal/connection/Connection$Factory;Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory;)V", "create", "Lcom/tinder/scarlet/internal/Service;", "serviceInterface", "Ljava/lang/Class;", "validateService", "", "service", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory {
        @NotNull
        private final Connection.Factory connectionFactory;
        @NotNull
        private final ServiceMethodExecutor.Factory serviceMethodExecutorFactory;

        public Factory(@NotNull Connection.Factory factory, @NotNull ServiceMethodExecutor.Factory factory2) {
            Intrinsics.checkNotNullParameter(factory, "connectionFactory");
            Intrinsics.checkNotNullParameter(factory2, "serviceMethodExecutorFactory");
            this.connectionFactory = factory;
            this.serviceMethodExecutorFactory = factory2;
        }

        private final void validateService(Class<?> cls) {
            if (cls.isInterface()) {
                Class[] interfaces = cls.getInterfaces();
                Intrinsics.checkNotNullExpressionValue(interfaces, "service.interfaces");
                if (interfaces.length != 0) {
                    throw new IllegalArgumentException("Service interfaces must not extend other interfaces.");
                }
                return;
            }
            throw new IllegalArgumentException("Service declarations must be interfaces.");
        }

        @NotNull
        public final Service create(@NotNull Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "serviceInterface");
            validateService(cls);
            Connection create = this.connectionFactory.create();
            return new Service(create, this.serviceMethodExecutorFactory.create(cls, create));
        }
    }

    public Service(@NotNull Connection connection2, @NotNull ServiceMethodExecutor serviceMethodExecutor2) {
        Intrinsics.checkNotNullParameter(connection2, "connection");
        Intrinsics.checkNotNullParameter(serviceMethodExecutor2, "serviceMethodExecutor");
        this.connection = connection2;
        this.serviceMethodExecutor = serviceMethodExecutor2;
    }

    @NotNull
    public final Object execute(@NotNull Method method, @NotNull Object[] objArr) {
        Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(objArr, "args");
        return this.serviceMethodExecutor.execute(method, objArr);
    }

    public final void startForever() {
        this.connection.startForever();
    }
}
