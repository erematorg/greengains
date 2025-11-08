package com.reown.android.internal.common.crypto;

import com.reown.util.UtilFunctionsKt;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\u001a\u000e\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"SHA_256", "", "sha256", "input", "", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class UtilsKt {
    @NotNull
    private static final String SHA_256 = "SHA-256";

    @NotNull
    public static final String sha256(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "input");
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        byte[] digest = instance.digest(bArr);
        Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
        return UtilFunctionsKt.bytesToHex(digest);
    }
}
