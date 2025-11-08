package com.phrase.android.sdk.repo;

import android.content.Context;
import android.content.SharedPreferences;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u0002J\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0002J\u0006\u0010\f\u001a\u00020\u0004R/\u0010\u0013\u001a\u0004\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u00028B@BX\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0010\u0010\u0012R/\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u00028B@BX\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u000e\u0010\u0011\"\u0004\b\u000e\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/phrase/android/sdk/repo/PhrasePrefs;", "", "", "getLastUpdate", "", "setLastUpdate", "getUniqueID", "localeHash", "getVersion", "version", "setVersion", "clearVersion", "clearLastUpdate", "<set-?>", "b", "Lcom/phrase/android/sdk/repo/PhrasePrefs$a;", "a", "()Ljava/lang/String;", "(Ljava/lang/String;)V", "_lastUpdate", "c", "_uniqueId", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhrasePrefs {

    /* renamed from: d  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f7290d;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f7291a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final a f7292b = new a(this, "PREF_LAST_UPDATE");
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final a f7293c = new a(this, "PREF_UNIQUE_ID");

    public final class a implements ReadWriteProperty<Object, String> {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f7294a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        public final String f7295b = null;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ PhrasePrefs f7296c;

        public a(PhrasePrefs phrasePrefs, @NotNull String str) {
            Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
            this.f7296c = phrasePrefs;
            this.f7294a = str;
        }

        @Nullable
        public final String a(@NotNull KProperty kProperty) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            return this.f7296c.f7291a.getString(this.f7294a, this.f7295b);
        }

        public final /* bridge */ /* synthetic */ Object getValue(Object obj, KProperty kProperty) {
            return a(kProperty);
        }

        public final /* bridge */ /* synthetic */ void setValue(Object obj, KProperty kProperty, Object obj2) {
            a(kProperty, (String) obj2);
        }

        public final void a(@NotNull KProperty kProperty, @Nullable String str) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            this.f7296c.f7291a.edit().putString(this.f7294a, str).apply();
        }
    }

    static {
        Class<PhrasePrefs> cls = PhrasePrefs.class;
        f7290d = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "_lastUpdate", "get_lastUpdate()Ljava/lang/String;", 0), androidx.compose.ui.autofill.a.m(cls, "_uniqueId", "get_uniqueId()Ljava/lang/String;", 0)};
    }

    public PhrasePrefs(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_UNIQUE_ID", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…D\", Context.MODE_PRIVATE)");
        this.f7291a = sharedPreferences;
    }

    public final String a() {
        return this.f7292b.a(f7290d[0]);
    }

    public final String b() {
        return this.f7293c.a(f7290d[1]);
    }

    public final void clearLastUpdate() {
        a((String) null);
    }

    public final void clearVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "localeHash");
        this.f7291a.edit().remove(str);
    }

    @Nullable
    public final String getLastUpdate() {
        return a();
    }

    @NotNull
    public final String getUniqueID() {
        String b3 = b();
        if (b3 != null) {
            return b3;
        }
        String uuid = UUID.randomUUID().toString();
        b(uuid);
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString().also { _uniqueId = it }");
        return uuid;
    }

    @Nullable
    public final String getVersion(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "localeHash");
        return this.f7291a.getString(str, (String) null);
    }

    public final void setLastUpdate() {
        a(String.valueOf(System.currentTimeMillis() / 1000));
    }

    public final void setVersion(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "localeHash");
        Intrinsics.checkNotNullParameter(str2, "version");
        this.f7291a.edit().putString(str, str2).apply();
    }

    public final void a(String str) {
        this.f7292b.a(f7290d[0], str);
    }

    public final void b(String str) {
        this.f7293c.a(f7290d[1], str);
    }
}
