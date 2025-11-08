package com.reown.sign.engine.model.tvf;

import io.ipfs.multibase.Base58;
import io.nodle.cash.ui.feature.chat.ConversationActivity;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\u0003"}, d2 = {"extractSignature", "", "transaction", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class SolanaKt {
    @NotNull
    public static final String extractSignature(@NotNull String str) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(str, ConversationActivity.MODE_TRANSACTION);
        try {
            bArr = Base64.decode(str);
        } catch (Exception unused) {
            bArr = Base58.decode(str);
        }
        Intrinsics.checkNotNull(bArr);
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Transaction buffer is empty");
        } else if ((bArr[0] & 255) <= 0 || bArr.length < 65) {
            throw new IllegalArgumentException("No signatures found in transaction");
        } else {
            String encode = Base58.encode(ArraysKt.copyOfRange(bArr, 1, 65));
            Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
            return encode;
        }
    }
}
