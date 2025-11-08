package com.reown.android.internal.common.signing.eip191;

import com.reown.android.internal.common.signing.signature.Signature;
import java.security.SignatureException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/signing/eip191/EIP191Verifier;", "", "<init>", "()V", "verify", "", "signature", "Lcom/reown/android/internal/common/signing/signature/Signature;", "originalMessage", "", "address", "", "verifyNoPrefix", "getAddressUsedToSignPrefixedMessage", "signedHash", "Lorg/web3j/crypto/Sign$SignatureData;", "getAddressUsedToSignMessage", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EIP191Verifier {
    @NotNull
    public static final EIP191Verifier INSTANCE = new EIP191Verifier();

    private EIP191Verifier() {
    }

    private final String getAddressUsedToSignMessage(Sign.SignatureData signatureData, byte[] bArr) throws SignatureException {
        String address = Keys.getAddress(Sign.signedMessageToKey(bArr, signatureData).toString(16));
        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
        return address;
    }

    private final String getAddressUsedToSignPrefixedMessage(Sign.SignatureData signatureData, byte[] bArr) throws SignatureException {
        String address = Keys.getAddress(Sign.signedPrefixedMessageToKey(bArr, signatureData).toString(16));
        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
        return address;
    }

    public final boolean verify(@NotNull Signature signature, @NotNull byte[] bArr, @NotNull String str) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(bArr, "originalMessage");
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        return StringsKt__StringsJVMKt.equals(getAddressUsedToSignPrefixedMessage(Intrinsics.checkNotNullParameter(signature, "<this>"), bArr), Intrinsics.checkNotNullParameter(str, "<this>"), true);
    }

    public final boolean verifyNoPrefix(@NotNull Signature signature, @NotNull byte[] bArr, @NotNull String str) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(bArr, "originalMessage");
        Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
        return StringsKt__StringsJVMKt.equals(getAddressUsedToSignMessage(Intrinsics.checkNotNullParameter(signature, "<this>"), bArr), Intrinsics.checkNotNullParameter(str, "<this>"), true);
    }

    public final boolean verifyNoPrefix(@NotNull Signature signature, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(str, "originalMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return verifyNoPrefix(signature, bytes, str2);
    }

    public final boolean verify(@NotNull Signature signature, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(str, "originalMessage");
        Intrinsics.checkNotNullParameter(str2, Address.TYPE_NAME);
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return verify(signature, bytes, str2);
    }
}
