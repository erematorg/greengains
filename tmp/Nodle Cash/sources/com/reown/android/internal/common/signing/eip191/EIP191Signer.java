package com.reown.android.internal.common.signing.eip191;

import com.reown.android.internal.common.signing.signature.Signature;
import com.reown.android.internal.common.signing.signature.SignatureKt;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u001d\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u001d\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/signing/eip191/EIP191Signer;", "", "<init>", "()V", "sign", "Lcom/reown/android/internal/common/signing/signature/Signature;", "message", "", "privateKey", "", "signHex", "Lcom/reown/android/internal/common/signing/model/HexString;", "signHex-twYc3iQ", "(Ljava/lang/String;[B)Lcom/reown/android/internal/common/signing/signature/Signature;", "signNoPrefix", "signHexNoPrefix", "signHexNoPrefix-twYc3iQ", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EIP191Signer {
    @NotNull
    public static final EIP191Signer INSTANCE = new EIP191Signer();

    private EIP191Signer() {
    }

    @NotNull
    public final Signature sign(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr2, "privateKey");
        Sign.SignatureData signPrefixedMessage = Sign.signPrefixedMessage(bArr, ECKeyPair.create(bArr2));
        Intrinsics.checkNotNullExpressionValue(signPrefixedMessage, "signPrefixedMessage(...)");
        return SignatureKt.toSignature(signPrefixedMessage);
    }

    @NotNull
    /* renamed from: signHex-twYc3iQ  reason: not valid java name */
    public final Signature m8796signHextwYc3iQ(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
        Intrinsics.checkNotNullExpressionValue(hexStringToByteArray, "hexStringToByteArray(...)");
        return sign(hexStringToByteArray, bArr);
    }

    @NotNull
    /* renamed from: signHexNoPrefix-twYc3iQ  reason: not valid java name */
    public final Signature m8797signHexNoPrefixtwYc3iQ(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        byte[] hexStringToByteArray = Numeric.hexStringToByteArray(str);
        Intrinsics.checkNotNullExpressionValue(hexStringToByteArray, "hexStringToByteArray(...)");
        return signNoPrefix(hexStringToByteArray, bArr);
    }

    @NotNull
    public final Signature signNoPrefix(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr2, "privateKey");
        Sign.SignatureData signMessage = Sign.signMessage(bArr, ECKeyPair.create(bArr2));
        Intrinsics.checkNotNullExpressionValue(signMessage, "signMessage(...)");
        return SignatureKt.toSignature(signMessage);
    }

    @NotNull
    public final Signature sign(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return sign(bytes, bArr);
    }

    @NotNull
    public final Signature signNoPrefix(@NotNull String str, @NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        Intrinsics.checkNotNullParameter(bArr, "privateKey");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return signNoPrefix(bytes, bArr);
    }
}
