package com.phrase.android.sdk.inject;

import android.content.Context;
import android.content.res.Resources;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/phrase/android/sdk/inject/PhraseReflection;", "", "Landroid/content/Context;", "context", "", "injectResources$sdk_release", "(Landroid/content/Context;)V", "injectResources", "<init>", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseReflection {
    @NotNull
    public static final PhraseReflection INSTANCE = new PhraseReflection();

    public static Field a(Class cls) {
        try {
            Field declaredField = cls.getDeclaredField("mResources");
            Intrinsics.checkNotNullExpressionValue(declaredField, "clazz.getDeclaredField(fieldName)");
            return declaredField;
        } catch (NoSuchFieldException e3) {
            Class superclass = cls.getSuperclass();
            if (superclass != null) {
                return a(superclass);
            }
            throw e3;
        }
    }

    public final void injectResources$sdk_release(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
            PhraseResources phraseResources = new PhraseResources(resources);
            Field a2 = a(context.getClass());
            a2.setAccessible(true);
            a2.set(context, phraseResources);
            a2.setAccessible(false);
        } catch (Exception unused) {
        }
    }
}
