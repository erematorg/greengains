package com.phrase.android.sdk;

import androidx.compose.animation.core.a;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0007\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\b\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0004R\u0017\u0010\u0016\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0004¨\u0006\u001c"}, d2 = {"Lcom/phrase/android/sdk/LocaleBundle;", "", "", "component1$sdk_release", "()Ljava/lang/String;", "component1", "customLocaleCode", "copy", "toString", "", "hashCode", "other", "", "equals", "a", "Ljava/lang/String;", "getCustomLocaleCode$sdk_release", "Ljava/util/Locale;", "b", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "locale", "c", "getLocaleCode", "localeCode", "<init>", "(Ljava/lang/String;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class LocaleBundle {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final String f7236a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Locale f7237b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final String f7238c;

    public LocaleBundle() {
        this((String) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ LocaleBundle copy$default(LocaleBundle localeBundle, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = localeBundle.f7236a;
        }
        return localeBundle.copy(str);
    }

    @Nullable
    public final String component1$sdk_release() {
        return this.f7236a;
    }

    @NotNull
    public final LocaleBundle copy(@Nullable String str) {
        return new LocaleBundle(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LocaleBundle) && Intrinsics.areEqual((Object) this.f7236a, (Object) ((LocaleBundle) obj).f7236a);
    }

    @Nullable
    public final String getCustomLocaleCode$sdk_release() {
        return this.f7236a;
    }

    @NotNull
    public final Locale getLocale() {
        return this.f7237b;
    }

    @NotNull
    public final String getLocaleCode() {
        return this.f7238c;
    }

    public int hashCode() {
        String str = this.f7236a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    @NotNull
    public String toString() {
        return a.o(')', this.f7236a, new StringBuilder("LocaleBundle(customLocaleCode="));
    }

    public LocaleBundle(@Nullable String str) {
        Locale locale;
        this.f7236a = str;
        if (str == null || (locale = UtilsKt.localeFromString(str)) == null) {
            locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        }
        this.f7237b = locale;
        if (str == null) {
            String locale2 = locale.toString();
            Intrinsics.checkNotNullExpressionValue(locale2, "locale.toString()");
            str = UtilsKt.normalizeLocaleStringForApi(locale2);
        }
        this.f7238c = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LocaleBundle(String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str);
    }
}
