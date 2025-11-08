package com.phrase.android.sdk.inject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J \u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¨\u0006\u000f"}, d2 = {"Lcom/phrase/android/sdk/inject/LegacyLayoutInflater;", "Landroid/view/LayoutInflater;", "Landroid/content/Context;", "newContext", "cloneInContext", "viewContext", "", "name", "Landroid/util/AttributeSet;", "attrs", "Landroid/view/View;", "createViewLegacy", "<init>", "(Landroid/content/Context;)V", "b", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class LegacyLayoutInflater extends LayoutInflater {
    @NotNull
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public static final Lazy<Field> f7259a = LazyKt.lazy(a.f7260a);

    public static final class a extends Lambda implements Function0<Field> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f7260a = new a();

        public a() {
            super(0);
        }

        public final Object invoke() {
            Field field;
            Field[] declaredFields = LayoutInflater.class.getDeclaredFields();
            Intrinsics.checkNotNullExpressionValue(declaredFields, "LayoutInflater::class.java.declaredFields");
            int length = declaredFields.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    field = null;
                    break;
                }
                field = declaredFields[i3];
                if (Intrinsics.areEqual((Object) field.getName(), (Object) "mConstructorArgs")) {
                    break;
                }
                i3++;
            }
            if (field != null) {
                field.setAccessible(true);
                return field;
            }
            throw new IllegalArgumentException("No constructor arguments field found in LayoutInflater!");
        }
    }

    public static final class b {
        public /* synthetic */ b(int i3) {
            this();
        }

        public static final Field a() {
            return (Field) LegacyLayoutInflater.f7259a.getValue();
        }

        public b() {
        }
    }

    static {
        new b(0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegacyLayoutInflater(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "newContext");
    }

    @NotNull
    public LayoutInflater cloneInContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "newContext");
        return new LegacyLayoutInflater(context);
    }

    @Nullable
    public final View createViewLegacy(@NotNull Context context, @NotNull String str, @NotNull AttributeSet attributeSet) {
        Intrinsics.checkNotNullParameter(context, "viewContext");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        Object obj = b.a().get(this);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.Any>");
        Object[] objArr = (Object[]) obj;
        objArr[0] = context;
        b.a().set(this, objArr);
        return createView(str, (String) null, attributeSet);
    }
}
