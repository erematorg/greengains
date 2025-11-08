package com.phrase.android.sdk;

import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u000b"}, d2 = {"Lcom/phrase/android/sdk/TranslateRepository;", "", "getArray", "", "", "key", "getLocale", "Ljava/util/Locale;", "getPlural", "quantityName", "getString", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface TranslateRepository {
    @Nullable
    List<String> getArray(@NotNull String str);

    @NotNull
    Locale getLocale();

    @Nullable
    String getPlural(@NotNull String str, @NotNull String str2);

    @Nullable
    String getString(@NotNull String str);
}
