package com.vmadalin.easypermissions.utils;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002JA\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00040\rH\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/vmadalin/easypermissions/utils/AnnotationsUtils;", "", "()V", "isUsingAndroidAnnotations", "", "receiver", "notifyAnnotatedMethods", "", "T", "", "annotationClass", "Lkotlin/reflect/KClass;", "predicate", "Lkotlin/Function1;", "notifyAnnotatedMethods$easypermissions_ktx_release", "easypermissions-ktx_release"}, k = 1, mv = {1, 4, 2})
public final class AnnotationsUtils {
    @NotNull
    public static final AnnotationsUtils INSTANCE = new AnnotationsUtils();

    private AnnotationsUtils() {
    }

    private final boolean isUsingAndroidAnnotations(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "receiver.javaClass.simpleName");
        if (!StringsKt__StringsJVMKt.endsWith$default(simpleName, "_", false, 2, (Object) null)) {
            return false;
        }
        try {
            return Class.forName("org.androidannotations.api.view.HasViews").isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final <T extends Annotation> void notifyAnnotatedMethods$easypermissions_ktx_release(@NotNull Object obj, @NotNull KClass<T> kClass, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(obj, "receiver");
        Intrinsics.checkNotNullParameter(kClass, "annotationClass");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Class cls = obj.getClass();
        if (isUsingAndroidAnnotations(obj)) {
            cls = cls.getSuperclass();
        }
        while (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                T annotation = method.getAnnotation(JvmClassMappingKt.getJavaClass(kClass));
                if (annotation != null && function1.invoke(annotation).booleanValue()) {
                    try {
                        Intrinsics.checkNotNullExpressionValue(method, FirebaseAnalytics.Param.METHOD);
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(obj, (Object[]) null);
                    } catch (IllegalAccessException e3) {
                        Log.e("AnnotationsUtils", "runDefaultMethod:IllegalAccessException", e3);
                    } catch (InvocationTargetException e4) {
                        Log.e("AnnotationsUtils", "runDefaultMethod:InvocationTargetException", e4);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }
}
