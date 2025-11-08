package com.reown.sign.engine.model.tvf;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.squareup.moshi.JsonClass;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uniffi.xmtpv3.j;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\r\u000e\u000f\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect;", "", "<init>", "()V", "computeTxHash", "", "bodyBytesBase64", "authInfoBytesBase64", "signatureBase64", "encodeVarint", "", "value", "", "SignatureData", "Signature", "PubKey", "Signed", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CosmosSignDirect {
    @NotNull
    public static final CosmosSignDirect INSTANCE = new CosmosSignDirect();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$PubKey;", "", "type", "", "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getValue", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PubKey {
        @NotNull
        private final String type;
        @NotNull
        private final String value;

        public PubKey(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "type");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.type = str;
            this.value = str2;
        }

        public static /* synthetic */ PubKey copy$default(PubKey pubKey, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = pubKey.type;
            }
            if ((i3 & 2) != 0) {
                str2 = pubKey.value;
            }
            return pubKey.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.type;
        }

        @NotNull
        public final String component2() {
            return this.value;
        }

        @NotNull
        public final PubKey copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "type");
            Intrinsics.checkNotNullParameter(str2, "value");
            return new PubKey(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PubKey)) {
                return false;
            }
            PubKey pubKey = (PubKey) obj;
            return Intrinsics.areEqual((Object) this.type, (Object) pubKey.type) && Intrinsics.areEqual((Object) this.value, (Object) pubKey.value);
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode() + (this.type.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("PubKey(type=", this.type, ", value=", this.value, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signature;", "", "pub_key", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$PubKey;", "signature", "", "<init>", "(Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$PubKey;Ljava/lang/String;)V", "getPub_key", "()Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$PubKey;", "getSignature", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Signature {
        @NotNull
        private final PubKey pub_key;
        @NotNull
        private final String signature;

        public Signature(@NotNull PubKey pubKey, @NotNull String str) {
            Intrinsics.checkNotNullParameter(pubKey, "pub_key");
            Intrinsics.checkNotNullParameter(str, "signature");
            this.pub_key = pubKey;
            this.signature = str;
        }

        public static /* synthetic */ Signature copy$default(Signature signature2, PubKey pubKey, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                pubKey = signature2.pub_key;
            }
            if ((i3 & 2) != 0) {
                str = signature2.signature;
            }
            return signature2.copy(pubKey, str);
        }

        @NotNull
        public final PubKey component1() {
            return this.pub_key;
        }

        @NotNull
        public final String component2() {
            return this.signature;
        }

        @NotNull
        public final Signature copy(@NotNull PubKey pubKey, @NotNull String str) {
            Intrinsics.checkNotNullParameter(pubKey, "pub_key");
            Intrinsics.checkNotNullParameter(str, "signature");
            return new Signature(pubKey, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Signature)) {
                return false;
            }
            Signature signature2 = (Signature) obj;
            return Intrinsics.areEqual((Object) this.pub_key, (Object) signature2.pub_key) && Intrinsics.areEqual((Object) this.signature, (Object) signature2.signature);
        }

        @NotNull
        public final PubKey getPub_key() {
            return this.pub_key;
        }

        @NotNull
        public final String getSignature() {
            return this.signature;
        }

        public int hashCode() {
            return this.signature.hashCode() + (this.pub_key.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            PubKey pubKey = this.pub_key;
            String str = this.signature;
            return "Signature(pub_key=" + pubKey + ", signature=" + str + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$SignatureData;", "", "signature", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signature;", "signed", "Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signed;", "<init>", "(Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signature;Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signed;)V", "getSignature", "()Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signature;", "getSigned", "()Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signed;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SignatureData {
        @NotNull
        private final Signature signature;
        @NotNull
        private final Signed signed;

        public SignatureData(@NotNull Signature signature2, @NotNull Signed signed2) {
            Intrinsics.checkNotNullParameter(signature2, "signature");
            Intrinsics.checkNotNullParameter(signed2, "signed");
            this.signature = signature2;
            this.signed = signed2;
        }

        public static /* synthetic */ SignatureData copy$default(SignatureData signatureData, Signature signature2, Signed signed2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                signature2 = signatureData.signature;
            }
            if ((i3 & 2) != 0) {
                signed2 = signatureData.signed;
            }
            return signatureData.copy(signature2, signed2);
        }

        @NotNull
        public final Signature component1() {
            return this.signature;
        }

        @NotNull
        public final Signed component2() {
            return this.signed;
        }

        @NotNull
        public final SignatureData copy(@NotNull Signature signature2, @NotNull Signed signed2) {
            Intrinsics.checkNotNullParameter(signature2, "signature");
            Intrinsics.checkNotNullParameter(signed2, "signed");
            return new SignatureData(signature2, signed2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureData)) {
                return false;
            }
            SignatureData signatureData = (SignatureData) obj;
            return Intrinsics.areEqual((Object) this.signature, (Object) signatureData.signature) && Intrinsics.areEqual((Object) this.signed, (Object) signatureData.signed);
        }

        @NotNull
        public final Signature getSignature() {
            return this.signature;
        }

        @NotNull
        public final Signed getSigned() {
            return this.signed;
        }

        public int hashCode() {
            return this.signed.hashCode() + (this.signature.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            Signature signature2 = this.signature;
            Signed signed2 = this.signed;
            return "SignatureData(signature=" + signature2 + ", signed=" + signed2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/tvf/CosmosSignDirect$Signed;", "", "chainId", "", "accountNumber", "authInfoBytes", "bodyBytes", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChainId", "()Ljava/lang/String;", "getAccountNumber", "getAuthInfoBytes", "getBodyBytes", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Signed {
        @NotNull
        private final String accountNumber;
        @NotNull
        private final String authInfoBytes;
        @NotNull
        private final String bodyBytes;
        @NotNull
        private final String chainId;

        public Signed(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, "chainId");
            Intrinsics.checkNotNullParameter(str2, "accountNumber");
            Intrinsics.checkNotNullParameter(str3, "authInfoBytes");
            Intrinsics.checkNotNullParameter(str4, "bodyBytes");
            this.chainId = str;
            this.accountNumber = str2;
            this.authInfoBytes = str3;
            this.bodyBytes = str4;
        }

        public static /* synthetic */ Signed copy$default(Signed signed, String str, String str2, String str3, String str4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = signed.chainId;
            }
            if ((i3 & 2) != 0) {
                str2 = signed.accountNumber;
            }
            if ((i3 & 4) != 0) {
                str3 = signed.authInfoBytes;
            }
            if ((i3 & 8) != 0) {
                str4 = signed.bodyBytes;
            }
            return signed.copy(str, str2, str3, str4);
        }

        @NotNull
        public final String component1() {
            return this.chainId;
        }

        @NotNull
        public final String component2() {
            return this.accountNumber;
        }

        @NotNull
        public final String component3() {
            return this.authInfoBytes;
        }

        @NotNull
        public final String component4() {
            return this.bodyBytes;
        }

        @NotNull
        public final Signed copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, "chainId");
            Intrinsics.checkNotNullParameter(str2, "accountNumber");
            Intrinsics.checkNotNullParameter(str3, "authInfoBytes");
            Intrinsics.checkNotNullParameter(str4, "bodyBytes");
            return new Signed(str, str2, str3, str4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Signed)) {
                return false;
            }
            Signed signed = (Signed) obj;
            return Intrinsics.areEqual((Object) this.chainId, (Object) signed.chainId) && Intrinsics.areEqual((Object) this.accountNumber, (Object) signed.accountNumber) && Intrinsics.areEqual((Object) this.authInfoBytes, (Object) signed.authInfoBytes) && Intrinsics.areEqual((Object) this.bodyBytes, (Object) signed.bodyBytes);
        }

        @NotNull
        public final String getAccountNumber() {
            return this.accountNumber;
        }

        @NotNull
        public final String getAuthInfoBytes() {
            return this.authInfoBytes;
        }

        @NotNull
        public final String getBodyBytes() {
            return this.bodyBytes;
        }

        @NotNull
        public final String getChainId() {
            return this.chainId;
        }

        public int hashCode() {
            return this.bodyBytes.hashCode() + a.i(this.authInfoBytes, a.i(this.accountNumber, this.chainId.hashCode() * 31, 31), 31);
        }

        @NotNull
        public String toString() {
            String str = this.chainId;
            String str2 = this.accountNumber;
            return android.support.v4.media.session.a.r(C0118y.l("Signed(chainId=", str, ", accountNumber=", str2, ", authInfoBytes="), this.authInfoBytes, ", bodyBytes=", this.bodyBytes, ")");
        }
    }

    private CosmosSignDirect() {
    }

    /* access modifiers changed from: private */
    public static final CharSequence computeTxHash$lambda$0(byte b3) {
        return a.t(new Object[]{Byte.valueOf(b3)}, 1, "%02X", "format(...)");
    }

    private final byte[] encodeVarint(long j2) {
        ArrayList arrayList = new ArrayList();
        while (j2 > 127) {
            arrayList.add(Byte.valueOf((byte) ((int) ((127 & j2) | 128))));
            j2 >>>= 7;
        }
        arrayList.add(Byte.valueOf((byte) ((int) j2)));
        return CollectionsKt.toByteArray(arrayList);
    }

    @NotNull
    public final String computeTxHash(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, "bodyBytesBase64");
        Intrinsics.checkNotNullParameter(str2, "authInfoBytesBase64");
        Intrinsics.checkNotNullParameter(str3, "signatureBase64");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] decode = Base64.decode(str);
            byte[] decode2 = Base64.decode(str2);
            byte[] decode3 = Base64.decode(str3);
            byteArrayOutputStream.write(10);
            byteArrayOutputStream.write(encodeVarint((long) decode.length));
            byteArrayOutputStream.write(decode);
            byteArrayOutputStream.write(18);
            byteArrayOutputStream.write(encodeVarint((long) decode2.length));
            byteArrayOutputStream.write(decode2);
            byteArrayOutputStream.write(26);
            byteArrayOutputStream.write(encodeVarint((long) decode3.length));
            byteArrayOutputStream.write(decode3);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            SHA256Digest sHA256Digest = new SHA256Digest();
            sHA256Digest.update(byteArray, 0, byteArray.length);
            byte[] bArr = new byte[sHA256Digest.getDigestSize()];
            sHA256Digest.doFinal(bArr, 0);
            return ArraysKt___ArraysKt.joinToString$default(bArr, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new j(7), 30, (Object) null);
        } catch (Exception unused) {
            return "";
        }
    }
}
