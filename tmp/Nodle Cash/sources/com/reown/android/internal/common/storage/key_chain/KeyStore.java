package com.reown.android.internal.common.storage.key_chain;

import com.reown.foundation.common.model.Key;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u001e\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0003H&J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH&J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/storage/key_chain/KeyStore;", "", "getKey", "", "tag", "setKey", "", "key", "Lcom/reown/foundation/common/model/Key;", "getKeys", "Lkotlin/Pair;", "setKeys", "key1", "key2", "deleteKeys", "checkKeys", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface KeyStore {
    boolean checkKeys(@NotNull String str);

    void deleteKeys(@NotNull String str);

    @Nullable
    String getKey(@NotNull String str);

    @Nullable
    Pair<String, String> getKeys(@NotNull String str);

    void setKey(@NotNull String str, @NotNull Key key);

    void setKeys(@NotNull String str, @NotNull Key key, @NotNull Key key2);
}
