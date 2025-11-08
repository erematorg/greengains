package com.appsamurai.storyly.data.managers.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f4048a;

    public static final class a extends Lambda implements Function0<SharedPreferences> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4049a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ String f4050b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ int f4051c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context, String str, int i3) {
            super(0);
            this.f4049a = context;
            this.f4050b = str;
            this.f4051c = i3;
        }

        public Object invoke() {
            return this.f4049a.getSharedPreferences(this.f4050b, this.f4051c);
        }
    }

    public e(@NotNull Context context, @NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "statusKey");
        this.f4048a = LazyKt.lazy(new a(context, str, i3));
    }

    public final SharedPreferences a() {
        return (SharedPreferences) this.f4048a.getValue();
    }

    public final void b() {
        Map<String, ?> all = a().getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAllStates()");
        for (Map.Entry next : all.entrySet()) {
            Object value = next.getValue();
            Long l2 = value instanceof Long ? (Long) value : null;
            if (l2 != null && l2.longValue() <= System.currentTimeMillis()) {
                Object key = next.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "entry.key");
                if (StringsKt__StringsJVMKt.startsWith$default((String) key, "ttl", false, 2, (Object) null)) {
                    Object key2 = next.getKey();
                    Intrinsics.checkNotNullExpressionValue(key2, "entry.key");
                    a((String) key2, true);
                }
            }
        }
        SharedPreferences a2 = a();
        Intrinsics.checkNotNullExpressionValue(a2, "sharedPreferences");
        SharedPreferences.Editor edit = a2.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.apply();
        edit.apply();
    }

    public final void a(@NotNull String str, long j2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        SharedPreferences a2 = a();
        Intrinsics.checkNotNullExpressionValue(a2, "sharedPreferences");
        SharedPreferences.Editor edit = a2.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.putLong(str, j2);
        edit.apply();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(Context context, String str, int i3, int i4) {
        this(context, str, (i4 & 4) != 0 ? 0 : i3);
    }

    public final void a(@NotNull String str, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        SharedPreferences a2 = a();
        Intrinsics.checkNotNullExpressionValue(a2, "sharedPreferences");
        SharedPreferences.Editor edit = a2.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Number) obj).longValue());
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Number) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Number) obj).floatValue());
        }
        edit.apply();
    }

    public final void a(@NotNull String str, boolean z2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        SharedPreferences a2 = a();
        Intrinsics.checkNotNullExpressionValue(a2, "sharedPreferences");
        SharedPreferences.Editor edit = a2.edit();
        Intrinsics.checkExpressionValueIsNotNull(edit, "editor");
        edit.remove(str);
        if (z2) {
            edit.commit();
        } else {
            edit.apply();
        }
    }

    @Nullable
    public final Object a(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return a().getAll().get(str);
    }
}
