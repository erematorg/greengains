package com.phrase.android.sdk;

import androidx.annotation.RequiresApi;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lcom/phrase/android/sdk/PhraseTranslations;", "", "getQuantityString", "", "key", "quantity", "", "getQuantityText", "", "getString", "getStringArray", "", "getText", "getTextArray", "sdk_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface PhraseTranslations {
    @RequiresApi(24)
    @Nullable
    String getQuantityString(@NotNull String str, int i3);

    @RequiresApi(24)
    @Nullable
    CharSequence getQuantityText(@NotNull String str, int i3);

    @Nullable
    String getString(@NotNull String str);

    @Nullable
    List<String> getStringArray(@NotNull String str);

    @Nullable
    CharSequence getText(@NotNull String str);

    @Nullable
    List<CharSequence> getTextArray(@NotNull String str);
}
