package com.reown.android.internal.common.storage.key_chain;

import android.content.SharedPreferences;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Key;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.reown.util.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u001e\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\u00112\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\u0015\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/internal/common/storage/key_chain/KeyChain;", "Lcom/reown/android/internal/common/storage/key_chain/KeyStore;", "sharedPreferences", "Landroid/content/SharedPreferences;", "<init>", "(Landroid/content/SharedPreferences;)V", "setKey", "", "tag", "", "key", "Lcom/reown/foundation/common/model/Key;", "getKey", "setKeys", "key1", "key2", "getKeys", "Lkotlin/Pair;", "deleteKeys", "checkKeys", "", "concatKeys", "keyA", "keyB", "splitKeys", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KeyChain implements KeyStore {
    @NotNull
    private final SharedPreferences sharedPreferences;

    public KeyChain(@NotNull SharedPreferences sharedPreferences2) {
        Intrinsics.checkNotNullParameter(sharedPreferences2, "sharedPreferences");
        this.sharedPreferences = sharedPreferences2;
    }

    private final String concatKeys(Key key, Key key2) {
        return UtilFunctionsKt.bytesToHex(ArraysKt.plus(UtilFunctionsKt.hexToBytes(key.getKeyAsHex()), UtilFunctionsKt.hexToBytes(key2.getKeyAsHex())));
    }

    private final Pair<String, String> splitKeys(String str) {
        byte[] hexToBytes = UtilFunctionsKt.hexToBytes(str);
        return TuplesKt.to(UtilFunctionsKt.bytesToHex(ArraysKt.sliceArray(hexToBytes, RangesKt.until(0, hexToBytes.length / 2))), UtilFunctionsKt.bytesToHex(ArraysKt.sliceArray(hexToBytes, RangesKt.until(hexToBytes.length / 2, hexToBytes.length))));
    }

    public boolean checkKeys(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        return this.sharedPreferences.contains(str);
    }

    public void deleteKeys(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        if (checkKeys(str)) {
            this.sharedPreferences.edit().remove(str).apply();
        }
    }

    @Nullable
    public String getKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        return this.sharedPreferences.getString(str, (String) null);
    }

    @Nullable
    public Pair<String, String> getKeys(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        String string = this.sharedPreferences.getString(str, (String) null);
        if (string == null) {
            return null;
        }
        return splitKeys(string);
    }

    public void setKey(@NotNull String str, @NotNull Key key) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        Intrinsics.checkNotNullParameter(key, JwtUtilsKt.DID_METHOD_KEY);
        this.sharedPreferences.edit().putString(str, key.getKeyAsHex()).apply();
    }

    public void setKeys(@NotNull String str, @NotNull Key key, @NotNull Key key2) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TAG);
        Intrinsics.checkNotNullParameter(key, "key1");
        Intrinsics.checkNotNullParameter(key2, "key2");
        this.sharedPreferences.edit().putString(str, concatKeys(key, key2)).apply();
    }
}
