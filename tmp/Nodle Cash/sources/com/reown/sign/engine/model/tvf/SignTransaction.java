package com.reown.sign.engine.model.tvf;

import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignTransaction;", "", "<init>", "()V", "calculateTransactionDigest", "", "txBytesBase64", "blake2b", "", "data", "outputLength", "", "SignatureResult", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignTransaction {
    @NotNull
    public static final SignTransaction INSTANCE = new SignTransaction();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignTransaction$SignatureResult;", "", "signature", "", "transactionBytes", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getSignature", "()Ljava/lang/String;", "getTransactionBytes", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SignatureResult {
        @Nullable
        private final String signature;
        @NotNull
        private final String transactionBytes;

        public SignatureResult(@Nullable String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str2, "transactionBytes");
            this.signature = str;
            this.transactionBytes = str2;
        }

        public static /* synthetic */ SignatureResult copy$default(SignatureResult signatureResult, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = signatureResult.signature;
            }
            if ((i3 & 2) != 0) {
                str2 = signatureResult.transactionBytes;
            }
            return signatureResult.copy(str, str2);
        }

        @Nullable
        public final String component1() {
            return this.signature;
        }

        @NotNull
        public final String component2() {
            return this.transactionBytes;
        }

        @NotNull
        public final SignatureResult copy(@Nullable String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str2, "transactionBytes");
            return new SignatureResult(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureResult)) {
                return false;
            }
            SignatureResult signatureResult = (SignatureResult) obj;
            return Intrinsics.areEqual((Object) this.signature, (Object) signatureResult.signature) && Intrinsics.areEqual((Object) this.transactionBytes, (Object) signatureResult.transactionBytes);
        }

        @Nullable
        public final String getSignature() {
            return this.signature;
        }

        @NotNull
        public final String getTransactionBytes() {
            return this.transactionBytes;
        }

        public int hashCode() {
            String str = this.signature;
            return this.transactionBytes.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("SignatureResult(signature=", this.signature, ", transactionBytes=", this.transactionBytes, ")");
        }
    }

    private SignTransaction() {
    }

    private final byte[] blake2b(byte[] bArr, int i3) {
        Blake2bDigest blake2bDigest = new Blake2bDigest(i3 * 8);
        blake2bDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[i3];
        blake2bDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    @NotNull
    public final String calculateTransactionDigest(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "txBytesBase64");
        try {
            byte[] decode = Base64.decode(str);
            byte[] bytes = "TransactionData::".getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bArr = new byte[(bytes.length + decode.length)];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            System.arraycopy(decode, 0, bArr, bytes.length, decode.length);
            return TVF.Companion.toBase58(blake2b(bArr, 32));
        } catch (IllegalArgumentException e3) {
            throw new IllegalArgumentException(c.a("Invalid base64 string: ", e3.getMessage()));
        }
    }
}
