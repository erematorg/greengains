package com.tinder.scarlet.internal.servicemethod;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.tinder.scarlet.internal.connection.Connection;
import com.tinder.scarlet.internal.servicemethod.ServiceMethod;
import com.tinder.scarlet.internal.utils.RuntimePlatform;
import com.tinder.scarlet.ws.Receive;
import com.tinder.scarlet.ws.Send;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000eB\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J!\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\u0010\rR \u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor;", "", "serviceMethods", "", "Ljava/lang/reflect/Method;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "(Ljava/util/Map;)V", "getServiceMethods$scarlet", "()Ljava/util/Map;", "execute", "method", "args", "", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "Factory", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nServiceMethodExecutor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceMethodExecutor.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n1#2:59\n*E\n"})
public final class ServiceMethodExecutor {
    @NotNull
    private final Map<Method, ServiceMethod> serviceMethods;

    @SourceDebugExtension({"SMAP\nServiceMethodExecutor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServiceMethodExecutor.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,58:1\n4117#2:59\n4217#2,2:60\n11653#2,9:66\n13579#2:75\n13580#2:77\n11662#2:78\n1549#3:62\n1620#3,3:63\n1#4:76\n*S KotlinDebug\n*F\n+ 1 ServiceMethodExecutor.kt\ncom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory\n*L\n37#1:59\n37#1:60,2\n43#1:66,9\n43#1:75\n43#1:77\n43#1:78\n38#1:62\n38#1:63,3\n43#1:76\n*E\n"})
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u0011H\u0002J$\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u0016\u001a\u00020\u0015*\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor$Factory;", "", "runtimePlatform", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "sendServiceMethodFactory", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send$Factory;", "receiveServiceMethodFactory", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive$Factory;", "(Lcom/tinder/scarlet/internal/utils/RuntimePlatform;Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Send$Factory;Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Receive$Factory;)V", "create", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethodExecutor;", "serviceInterface", "Ljava/lang/Class;", "connection", "Lcom/tinder/scarlet/internal/connection/Connection;", "findServiceMethodFactory", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod$Factory;", "", "findServiceMethods", "", "Ljava/lang/reflect/Method;", "Lcom/tinder/scarlet/internal/servicemethod/ServiceMethod;", "toServiceMethod", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Factory {
        @NotNull
        private final ServiceMethod.Receive.Factory receiveServiceMethodFactory;
        @NotNull
        private final RuntimePlatform runtimePlatform;
        @NotNull
        private final ServiceMethod.Send.Factory sendServiceMethodFactory;

        public Factory(@NotNull RuntimePlatform runtimePlatform2, @NotNull ServiceMethod.Send.Factory factory, @NotNull ServiceMethod.Receive.Factory factory2) {
            Intrinsics.checkNotNullParameter(runtimePlatform2, "runtimePlatform");
            Intrinsics.checkNotNullParameter(factory, "sendServiceMethodFactory");
            Intrinsics.checkNotNullParameter(factory2, "receiveServiceMethodFactory");
            this.runtimePlatform = runtimePlatform2;
            this.sendServiceMethodFactory = factory;
            this.receiveServiceMethodFactory = factory2;
        }

        private final ServiceMethod.Factory findServiceMethodFactory(Annotation annotation) {
            if (annotation instanceof Send) {
                return this.sendServiceMethodFactory;
            }
            if (annotation instanceof Receive) {
                return this.receiveServiceMethodFactory;
            }
            return null;
        }

        private final Map<Method, ServiceMethod> findServiceMethods(Class<?> cls, Connection connection) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(declaredMethods, "declaredMethods");
            ArrayList arrayList = new ArrayList();
            for (Method method : declaredMethods) {
                RuntimePlatform runtimePlatform2 = this.runtimePlatform;
                Intrinsics.checkNotNullExpressionValue(method, "it");
                if (!runtimePlatform2.isDefaultMethod(method)) {
                    arrayList.add(method);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Method method2 = (Method) it.next();
                Intrinsics.checkNotNullExpressionValue(method2, "it");
                arrayList2.add(toServiceMethod(method2, connection));
            }
            return MapsKt.toMap(CollectionsKt.zip(arrayList, arrayList2));
        }

        private final ServiceMethod toServiceMethod(Method method, Connection connection) {
            Annotation[] annotations = method.getAnnotations();
            Intrinsics.checkNotNullExpressionValue(annotations, "annotations");
            ArrayList arrayList = new ArrayList();
            for (Annotation annotation : annotations) {
                Intrinsics.checkNotNullExpressionValue(annotation, "it");
                ServiceMethod.Factory findServiceMethodFactory = findServiceMethodFactory(annotation);
                if (findServiceMethodFactory != null) {
                    arrayList.add(findServiceMethodFactory);
                }
            }
            if (arrayList.size() == 1) {
                return ((ServiceMethod.Factory) CollectionsKt.first(arrayList)).create(connection, method);
            }
            throw new IllegalArgumentException(("A method must have one and only one service method annotation: " + method).toString());
        }

        @NotNull
        public final ServiceMethodExecutor create(@NotNull Class<?> cls, @NotNull Connection connection) {
            Intrinsics.checkNotNullParameter(cls, "serviceInterface");
            Intrinsics.checkNotNullParameter(connection, "connection");
            return new ServiceMethodExecutor(findServiceMethods(cls, connection));
        }
    }

    public ServiceMethodExecutor(@NotNull Map<Method, ? extends ServiceMethod> map) {
        Intrinsics.checkNotNullParameter(map, "serviceMethods");
        this.serviceMethods = map;
    }

    @NotNull
    public final Object execute(@NotNull Method method, @NotNull Object[] objArr) {
        Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(objArr, "args");
        ServiceMethod serviceMethod = this.serviceMethods.get(method);
        if (serviceMethod != null) {
            ServiceMethod serviceMethod2 = serviceMethod;
            if (serviceMethod2 instanceof ServiceMethod.Send) {
                return ((ServiceMethod.Send) serviceMethod2).execute(objArr[0]);
            }
            if (serviceMethod2 instanceof ServiceMethod.Receive) {
                return ((ServiceMethod.Receive) serviceMethod2).execute();
            }
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalStateException("Service method not found");
    }

    @NotNull
    public final Map<Method, ServiceMethod> getServiceMethods$scarlet() {
        return this.serviceMethods;
    }
}
