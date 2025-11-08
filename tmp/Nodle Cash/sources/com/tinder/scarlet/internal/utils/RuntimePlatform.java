package com.tinder.scarlet.internal.utils;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u000e2\u00020\u0001:\u0003\u000e\u000f\u0010B\u0007\b\u0004¢\u0006\u0002\u0010\u0002JQ\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\u00012&\u0010\t\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\n0\n\"\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016\u0001\u0002\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "", "()V", "invokeDefaultMethod", "method", "Ljava/lang/reflect/Method;", "declaringClass", "Ljava/lang/Class;", "proxy", "args", "", "(Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Object;[[Ljava/lang/Object;)Ljava/lang/Object;", "isDefaultMethod", "", "Companion", "Default", "Java8", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform$Default;", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform$Java8;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RuntimePlatform {
    @NotNull
    public static final Companion Companion;
    /* access modifiers changed from: private */
    @NotNull
    public static final RuntimePlatform PLATFORM;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\u0006\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/tinder/scarlet/internal/utils/RuntimePlatform$Companion;", "", "()V", "PLATFORM", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "findPlatform", "get", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final RuntimePlatform findPlatform() {
            try {
                Class.forName("java.util.Optional");
                return new Java8();
            } catch (ClassNotFoundException unused) {
                return new Default();
            }
        }

        @NotNull
        public final RuntimePlatform get() {
            return RuntimePlatform.PLATFORM;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/tinder/scarlet/internal/utils/RuntimePlatform$Default;", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "()V", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Default extends RuntimePlatform {
        public Default() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JQ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\u00042&\u0010\n\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u000b0\u000b\"\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/tinder/scarlet/internal/utils/RuntimePlatform$Java8;", "Lcom/tinder/scarlet/internal/utils/RuntimePlatform;", "()V", "invokeDefaultMethod", "", "method", "Ljava/lang/reflect/Method;", "declaringClass", "Ljava/lang/Class;", "proxy", "args", "", "(Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Object;[[Ljava/lang/Object;)Ljava/lang/Object;", "isDefaultMethod", "", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Java8 extends RuntimePlatform {
        public Java8() {
            super((DefaultConstructorMarker) null);
        }

        @NotNull
        public Object invokeDefaultMethod(@NotNull Method method, @NotNull Class<?> cls, @NotNull Object obj, @NotNull Object[]... objArr) {
            Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(cls, "declaringClass");
            Intrinsics.checkNotNullParameter(obj, "proxy");
            Intrinsics.checkNotNullParameter(objArr, "args");
            Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            Object invokeWithArguments = declaredConstructor.newInstance(new Object[]{cls, -1}).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(new Object[]{objArr});
            Intrinsics.checkNotNullExpressionValue(invokeWithArguments, "constructor.newInstance(…invokeWithArguments(args)");
            return invokeWithArguments;
        }

        public boolean isDefaultMethod(@NotNull Method method) {
            Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
            return method.isDefault();
        }
    }

    static {
        Companion companion = new Companion((DefaultConstructorMarker) null);
        Companion = companion;
        PLATFORM = companion.findPlatform();
    }

    public /* synthetic */ RuntimePlatform(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public Object invokeDefaultMethod(@NotNull Method method, @NotNull Class<?> cls, @NotNull Object obj, @NotNull Object[]... objArr) {
        Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(cls, "declaringClass");
        Intrinsics.checkNotNullParameter(obj, "proxy");
        Intrinsics.checkNotNullParameter(objArr, "args");
        throw new UnsupportedOperationException();
    }

    public boolean isDefaultMethod(@NotNull Method method) {
        Intrinsics.checkNotNullParameter(method, FirebaseAnalytics.Param.METHOD);
        return false;
    }

    private RuntimePlatform() {
    }
}
