package com.phrase.android.sdk;

import com.phrase.android.sdk.repo.PhraseData;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\b\u0010\n\u001a\u00020\tH\u0016¨\u0006\u0011"}, d2 = {"Lcom/phrase/android/sdk/TranslateRepositoryImpl;", "Lcom/phrase/android/sdk/TranslateRepository;", "", "key", "getString", "", "getArray", "quantityName", "getPlural", "Ljava/util/Locale;", "getLocale", "Lcom/phrase/android/sdk/repo/PhraseData;", "data", "Lcom/phrase/android/sdk/LocaleBundle;", "localeBundle", "<init>", "(Lcom/phrase/android/sdk/repo/PhraseData;Lcom/phrase/android/sdk/LocaleBundle;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class TranslateRepositoryImpl implements TranslateRepository {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final PhraseData f7257a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final LocaleBundle f7258b;

    public TranslateRepositoryImpl(@NotNull PhraseData phraseData, @NotNull LocaleBundle localeBundle) {
        Intrinsics.checkNotNullParameter(phraseData, "data");
        Intrinsics.checkNotNullParameter(localeBundle, "localeBundle");
        this.f7257a = phraseData;
        this.f7258b = localeBundle;
    }

    @Nullable
    public List<String> getArray(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return this.f7257a.getArrays().get(str);
    }

    @NotNull
    public Locale getLocale() {
        return this.f7258b.getLocale();
    }

    @Nullable
    public String getPlural(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        Intrinsics.checkNotNullParameter(str2, "quantityName");
        Map map = this.f7257a.getPlurals().get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    @Nullable
    public String getString(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return this.f7257a.getStrings().get(str);
    }
}
