package com.phrase.android.sdk.inject;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.PluralRules;
import androidx.compose.animation.core.a;
import com.phrase.android.sdk.Phrase;
import com.phrase.android.sdk.TranslateRepository;
import com.phrase.android.sdk.UtilsKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0016\u001a\u00020\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J/\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0016\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u0006\"\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\u0005\u0010\tJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0015\u001a\u00020\u0014H\u0016¨\u0006\u0019"}, d2 = {"Lcom/phrase/android/sdk/inject/PhraseResources;", "Landroid/content/res/Resources;", "", "id", "", "getString", "", "", "formatArgs", "(I[Ljava/lang/Object;)Ljava/lang/String;", "", "getText", "def", "quantity", "getQuantityString", "getQuantityText", "getStringArray", "(I)[Ljava/lang/String;", "getTextArray", "(I)[Ljava/lang/CharSequence;", "Landroid/content/res/Configuration;", "getConfiguration", "baseResources", "<init>", "(Landroid/content/res/Resources;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseResources extends Resources {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Resources f7270a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Resources f7271b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PhraseResources(@NotNull Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        Intrinsics.checkNotNullParameter(resources, "baseResources");
        this.f7270a = resources;
    }

    public final Resources a() {
        String customLocaleCode$sdk_release = Phrase.INSTANCE.getCustomLocaleCode$sdk_release();
        if (customLocaleCode$sdk_release == null) {
            this.f7271b = null;
            return this.f7270a;
        }
        Locale localeFromString = UtilsKt.localeFromString(customLocaleCode$sdk_release);
        Resources resources = this.f7271b;
        if (resources == null) {
            resources = this.f7270a;
        }
        Configuration configuration = resources.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "currentResources.configuration");
        if (Intrinsics.areEqual((Object) configuration.getLocales().get(0), (Object) localeFromString)) {
            return resources;
        }
        Configuration configuration2 = new Configuration(resources.getConfiguration());
        configuration2.setLocale(localeFromString);
        Resources resources2 = new Resources(this.f7270a.getAssets(), this.f7270a.getDisplayMetrics(), configuration2);
        this.f7271b = resources2;
        return resources2;
    }

    @NotNull
    public Configuration getConfiguration() {
        Configuration configuration = a().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getFallbackResources().configuration");
        return configuration;
    }

    @NotNull
    public String getQuantityString(int i3, int i4) throws Resources.NotFoundException {
        return getQuantityText(i3, i4).toString();
    }

    @NotNull
    public CharSequence getQuantityText(int i3, int i4) throws Resources.NotFoundException {
        Locale locale;
        Object obj;
        CharSequence fromHtml;
        String str;
        Phrase phrase = Phrase.INSTANCE;
        TranslateRepository repository$sdk_release = phrase.getRepository$sdk_release();
        if (repository$sdk_release == null || (locale = repository$sdk_release.getLocale()) == null) {
            locale = Locale.getDefault();
        }
        String select = PluralRules.forLocale(locale).select((double) i4);
        Intrinsics.checkNotNullExpressionValue(select, "quantityName");
        String str2 = null;
        try {
            Result.Companion companion = Result.Companion;
            String resourceEntryName = getResourceEntryName(i3);
            TranslateRepository repository$sdk_release2 = phrase.getRepository$sdk_release();
            if (repository$sdk_release2 != null) {
                Intrinsics.checkNotNullExpressionValue(resourceEntryName, "pluralKey");
                str = repository$sdk_release2.getPlural(resourceEntryName, select);
            } else {
                str = null;
            }
            obj = Result.m8979constructorimpl(str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m8985isFailureimpl(obj)) {
            str2 = obj;
        }
        String str3 = str2;
        if (str3 != null && (fromHtml = UtilsKt.fromHtml(str3)) != null) {
            return fromHtml;
        }
        CharSequence quantityText = a().getQuantityText(i3, i4);
        Intrinsics.checkNotNullExpressionValue(quantityText, "getFallbackResources().g…uantityText(id, quantity)");
        return quantityText;
    }

    @NotNull
    public String getString(int i3) throws Resources.NotFoundException {
        return getText(i3).toString();
    }

    @NotNull
    public String[] getStringArray(int i3) throws Resources.NotFoundException {
        Object obj;
        List<String> list;
        String[] strArr = null;
        try {
            Result.Companion companion = Result.Companion;
            String resourceEntryName = getResourceEntryName(i3);
            TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
            if (repository$sdk_release != null) {
                Intrinsics.checkNotNullExpressionValue(resourceEntryName, "arrayKey");
                list = repository$sdk_release.getArray(resourceEntryName);
            } else {
                list = null;
            }
            obj = Result.m8979constructorimpl(list);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(obj)) {
            obj = null;
        }
        List list2 = (List) obj;
        if (list2 != null) {
            Object[] array = list2.toArray(new String[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            strArr = (String[]) array;
        }
        if (strArr != null) {
            return strArr;
        }
        String[] stringArray = a().getStringArray(i3);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getFallbackResources().getStringArray(id)");
        return stringArray;
    }

    @NotNull
    public CharSequence getText(int i3) throws Resources.NotFoundException {
        Object obj;
        CharSequence fromHtml;
        String str;
        String str2 = null;
        try {
            Result.Companion companion = Result.Companion;
            String resourceEntryName = getResourceEntryName(i3);
            TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
            if (repository$sdk_release != null) {
                Intrinsics.checkNotNullExpressionValue(resourceEntryName, "stringKey");
                str = repository$sdk_release.getString(resourceEntryName);
            } else {
                str = null;
            }
            obj = Result.m8979constructorimpl(str);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m8985isFailureimpl(obj)) {
            str2 = obj;
        }
        String str3 = str2;
        if (str3 != null && (fromHtml = UtilsKt.fromHtml(str3)) != null) {
            return fromHtml;
        }
        CharSequence text = a().getText(i3);
        Intrinsics.checkNotNullExpressionValue(text, "getFallbackResources().getText(id)");
        return text;
    }

    @NotNull
    public CharSequence[] getTextArray(int i3) throws Resources.NotFoundException {
        Object obj;
        List<String> list;
        CharSequence[] charSequenceArr = null;
        try {
            Result.Companion companion = Result.Companion;
            String resourceEntryName = getResourceEntryName(i3);
            TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
            if (repository$sdk_release != null) {
                Intrinsics.checkNotNullExpressionValue(resourceEntryName, "arrayKey");
                list = repository$sdk_release.getArray(resourceEntryName);
            } else {
                list = null;
            }
            obj = Result.m8979constructorimpl(list);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8985isFailureimpl(obj)) {
            obj = null;
        }
        List<String> list2 = (List) obj;
        if (list2 != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
            for (String fromHtml : list2) {
                arrayList.add(UtilsKt.fromHtml(fromHtml));
            }
            Object[] array = arrayList.toArray(new CharSequence[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            charSequenceArr = (CharSequence[]) array;
        }
        if (charSequenceArr != null) {
            return charSequenceArr;
        }
        CharSequence[] textArray = a().getTextArray(i3);
        Intrinsics.checkNotNullExpressionValue(textArray, "getFallbackResources().getTextArray(id)");
        return textArray;
    }

    @NotNull
    public String getString(int i3, @NotNull Object... objArr) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(objArr, "formatArgs");
        String string = getString(i3);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        return a.t(copyOf, copyOf.length, string, "format(format, *args)");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r0 = com.phrase.android.sdk.UtilsKt.fromHtml(r0);
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence getText(int r5, @org.jetbrains.annotations.Nullable java.lang.CharSequence r6) throws android.content.res.Resources.NotFoundException {
        /*
            r4 = this;
            r0 = 0
            kotlin.Result$Companion r1 = kotlin.Result.Companion     // Catch:{ all -> 0x0019 }
            java.lang.String r1 = r4.getResourceEntryName(r5)     // Catch:{ all -> 0x0019 }
            com.phrase.android.sdk.Phrase r2 = com.phrase.android.sdk.Phrase.INSTANCE     // Catch:{ all -> 0x0019 }
            com.phrase.android.sdk.TranslateRepository r2 = r2.getRepository$sdk_release()     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x001b
            java.lang.String r3 = "stringKey"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)     // Catch:{ all -> 0x0019 }
            java.lang.String r1 = r2.getString(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x0019:
            r1 = move-exception
            goto L_0x0021
        L_0x001b:
            r1 = r0
        L_0x001c:
            java.lang.Object r1 = kotlin.Result.m8979constructorimpl(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x002b
        L_0x0021:
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m8979constructorimpl(r1)
        L_0x002b:
            boolean r2 = kotlin.Result.m8985isFailureimpl(r1)
            if (r2 == 0) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            r0 = r1
        L_0x0033:
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x003d
            java.lang.CharSequence r0 = com.phrase.android.sdk.UtilsKt.fromHtml(r0)
            if (r0 != 0) goto L_0x0045
        L_0x003d:
            android.content.res.Resources r4 = r4.a()
            java.lang.CharSequence r0 = r4.getText(r5, r6)
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.phrase.android.sdk.inject.PhraseResources.getText(int, java.lang.CharSequence):java.lang.CharSequence");
    }
}
