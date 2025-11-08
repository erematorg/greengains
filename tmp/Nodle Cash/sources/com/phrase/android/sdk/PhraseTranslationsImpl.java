package com.phrase.android.sdk;

import android.icu.text.PluralRules;
import androidx.annotation.RequiresApi;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u000f"}, d2 = {"Lcom/phrase/android/sdk/PhraseTranslationsImpl;", "Lcom/phrase/android/sdk/PhraseTranslations;", "()V", "getQuantityString", "", "key", "quantity", "", "getQuantityText", "", "getString", "getStringArray", "", "getText", "getTextArray", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PhraseTranslationsImpl implements PhraseTranslations {
    @RequiresApi(24)
    @Nullable
    public String getQuantityString(@NotNull String str, int i3) {
        Locale locale;
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
        if (repository$sdk_release == null || (locale = repository$sdk_release.getLocale()) == null) {
            locale = Locale.getDefault();
        }
        String select = PluralRules.forLocale(locale).select((double) i3);
        if (repository$sdk_release == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(select, "quantityName");
        return repository$sdk_release.getPlural(str, select);
    }

    @RequiresApi(24)
    @Nullable
    public CharSequence getQuantityText(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        String quantityString = getQuantityString(str, i3);
        if (quantityString != null) {
            return UtilsKt.fromHtml(quantityString);
        }
        return null;
    }

    @Nullable
    public String getString(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
        if (repository$sdk_release != null) {
            return repository$sdk_release.getString(str);
        }
        return null;
    }

    @Nullable
    public List<String> getStringArray(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        TranslateRepository repository$sdk_release = Phrase.INSTANCE.getRepository$sdk_release();
        if (repository$sdk_release != null) {
            return repository$sdk_release.getArray(str);
        }
        return null;
    }

    @Nullable
    public CharSequence getText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        String string = getString(str);
        if (string != null) {
            return UtilsKt.fromHtml(string);
        }
        return null;
    }

    @Nullable
    public List<CharSequence> getTextArray(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        List<String> stringArray = getStringArray(str);
        if (stringArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(stringArray, 10));
        for (String fromHtml : stringArray) {
            arrayList.add(UtilsKt.fromHtml(fromHtml));
        }
        return arrayList;
    }
}
