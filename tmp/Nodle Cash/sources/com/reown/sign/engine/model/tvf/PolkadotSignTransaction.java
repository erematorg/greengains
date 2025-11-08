package com.reown.sign.engine.model.tvf;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import androidx.work.impl.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.sign.engine.model.tvf.TVF;
import com.reown.util.UtilFunctionsKt;
import com.squareup.moshi.JsonClass;
import io.ipfs.multibase.Base58;
import io.zksync.wrappers.ZkSyncContract;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ByteSpreadBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u001f !B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0005H\u0002J \u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0012\u0010\u0019\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u0001H\u0002J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001dH\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001dH\u0002¨\u0006\""}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction;", "", "<init>", "()V", "calculatePolkadotHash", "", "signatureResponse", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$SignatureResponse;", "requestParams", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$RequestParams;", "deriveExtrinsicHash", "signed", "", "ss58AddressToPublicKey", "ss58Address", "addSignatureToExtrinsic", "publicKey", "hexSignature", "transactionPayload", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "guessSignatureTypeFromAddress", "", "address", "normalizeHex", "input", "parseHex", "compactEncodeInt", "value", "compactEncodeBigInt", "Ljava/math/BigInteger;", "bigIntToLEBytes", "SignatureResponse", "TransactionPayload", "RequestParams", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PolkadotSignTransaction {
    @NotNull
    public static final PolkadotSignTransaction INSTANCE = new PolkadotSignTransaction();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$RequestParams;", "", "address", "", "transactionPayload", "Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "<init>", "(Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;)V", "getAddress", "()Ljava/lang/String;", "getTransactionPayload", "()Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RequestParams {
        @Nullable
        private final String address;
        @NotNull
        private final TransactionPayload transactionPayload;

        public RequestParams(@Nullable String str, @NotNull TransactionPayload transactionPayload2) {
            Intrinsics.checkNotNullParameter(transactionPayload2, "transactionPayload");
            this.address = str;
            this.transactionPayload = transactionPayload2;
        }

        public static /* synthetic */ RequestParams copy$default(RequestParams requestParams, String str, TransactionPayload transactionPayload2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = requestParams.address;
            }
            if ((i3 & 2) != 0) {
                transactionPayload2 = requestParams.transactionPayload;
            }
            return requestParams.copy(str, transactionPayload2);
        }

        @Nullable
        public final String component1() {
            return this.address;
        }

        @NotNull
        public final TransactionPayload component2() {
            return this.transactionPayload;
        }

        @NotNull
        public final RequestParams copy(@Nullable String str, @NotNull TransactionPayload transactionPayload2) {
            Intrinsics.checkNotNullParameter(transactionPayload2, "transactionPayload");
            return new RequestParams(str, transactionPayload2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RequestParams)) {
                return false;
            }
            RequestParams requestParams = (RequestParams) obj;
            return Intrinsics.areEqual((Object) this.address, (Object) requestParams.address) && Intrinsics.areEqual((Object) this.transactionPayload, (Object) requestParams.transactionPayload);
        }

        @Nullable
        public final String getAddress() {
            return this.address;
        }

        @NotNull
        public final TransactionPayload getTransactionPayload() {
            return this.transactionPayload;
        }

        public int hashCode() {
            String str = this.address;
            return this.transactionPayload.hashCode() + ((str == null ? 0 : str.hashCode()) * 31);
        }

        @NotNull
        public String toString() {
            String str = this.address;
            TransactionPayload transactionPayload2 = this.transactionPayload;
            return "RequestParams(address=" + str + ", transactionPayload=" + transactionPayload2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$SignatureResponse;", "", "id", "", "signature", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getSignature", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SignatureResponse {
        private final long id;
        @NotNull
        private final String signature;

        public SignatureResponse(long j2, @NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "signature");
            this.id = j2;
            this.signature = str;
        }

        public static /* synthetic */ SignatureResponse copy$default(SignatureResponse signatureResponse, long j2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                j2 = signatureResponse.id;
            }
            if ((i3 & 2) != 0) {
                str = signatureResponse.signature;
            }
            return signatureResponse.copy(j2, str);
        }

        public final long component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.signature;
        }

        @NotNull
        public final SignatureResponse copy(long j2, @NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "signature");
            return new SignatureResponse(j2, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureResponse)) {
                return false;
            }
            SignatureResponse signatureResponse = (SignatureResponse) obj;
            return this.id == signatureResponse.id && Intrinsics.areEqual((Object) this.signature, (Object) signatureResponse.signature);
        }

        public final long getId() {
            return this.id;
        }

        @NotNull
        public final String getSignature() {
            return this.signature;
        }

        public int hashCode() {
            return this.signature.hashCode() + (Long.hashCode(this.id) * 31);
        }

        @NotNull
        public String toString() {
            StringBuilder v2 = a.v(this.id, "SignatureResponse(id=", ", signature=", this.signature);
            v2.append(")");
            return v2.toString();
        }
    }

    private PolkadotSignTransaction() {
    }

    private final byte[] addSignatureToExtrinsic(byte[] bArr, String str, TransactionPayload transactionPayload) {
        byte[] hexToBytes = UtilFunctionsKt.hexToBytes(transactionPayload.getMethod());
        byte[] hexToBytes2 = UtilFunctionsKt.hexToBytes(str);
        int parseInt = Integer.parseInt(String.valueOf(transactionPayload.getVersion())) | 128;
        String address = transactionPayload.getAddress();
        String str2 = "";
        if (address == null) {
            address = str2;
        }
        int guessSignatureTypeFromAddress = guessSignatureTypeFromAddress(address);
        String era = transactionPayload.getEra();
        if (era != null) {
            str2 = era;
        }
        String normalizeHex = normalizeHex(str2);
        byte[] hexToBytes3 = (normalizeHex.length() != 0 && !Intrinsics.areEqual((Object) normalizeHex, (Object) "00")) ? UtilFunctionsKt.hexToBytes(normalizeHex) : new byte[]{0};
        byte[] bArr2 = {(byte) parseHex(transactionPayload.getNonce())};
        String tip = transactionPayload.getTip();
        if (tip == null) {
            tip = SchemaSymbols.ATTVAL_FALSE_0;
        }
        byte[] compactEncodeBigInt = compactEncodeBigInt(new BigInteger(normalizeHex(tip), 16));
        if (hexToBytes.length >= 3 && hexToBytes[0] == 5 && hexToBytes[1] == 3 && hexToBytes[2] != 0) {
            ByteBuffer allocate = ByteBuffer.allocate(hexToBytes.length + 1);
            allocate.put(hexToBytes[0]);
            allocate.put(hexToBytes[1]);
            allocate.put((byte) 0);
            allocate.put(ArraysKt.sliceArray(hexToBytes, RangesKt.until(2, hexToBytes.length)));
            hexToBytes = allocate.array();
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(1024);
        allocate2.put((byte) 0);
        allocate2.put(bArr);
        allocate2.put((byte) guessSignatureTypeFromAddress);
        allocate2.put(hexToBytes2);
        allocate2.put(hexToBytes3);
        allocate2.put(bArr2);
        allocate2.put(compactEncodeBigInt);
        allocate2.put((byte) 0);
        allocate2.put(hexToBytes);
        byte[] array = allocate2.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        byte[] sliceArray = ArraysKt.sliceArray(array, RangesKt.until(0, allocate2.position()));
        byte[] compactEncodeInt = compactEncodeInt(Integer.valueOf(sliceArray.length + 1));
        ByteBuffer allocate3 = ByteBuffer.allocate(compactEncodeInt.length + 1 + sliceArray.length);
        allocate3.put(compactEncodeInt);
        allocate3.put((byte) parseInt);
        allocate3.put(sliceArray);
        byte[] array2 = allocate3.array();
        Intrinsics.checkNotNullExpressionValue(array2, "array(...)");
        return ArraysKt.sliceArray(array2, RangesKt.until(0, allocate3.position()));
    }

    private final byte[] bigIntToLEBytes(BigInteger bigInteger) {
        ArrayList arrayList = new ArrayList();
        while (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            BigInteger valueOf = BigInteger.valueOf(255);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            BigInteger and = bigInteger.and(valueOf);
            Intrinsics.checkNotNullExpressionValue(and, "and(...)");
            arrayList.add(Byte.valueOf(and.byteValue()));
            bigInteger = bigInteger.shiftRight(8);
            Intrinsics.checkNotNullExpressionValue(bigInteger, "shiftRight(...)");
        }
        return CollectionsKt.toByteArray(arrayList);
    }

    private final byte[] compactEncodeBigInt(BigInteger bigInteger) {
        byte[] bArr;
        if (bigInteger.compareTo(BigInteger.valueOf(64)) < 0) {
            return new byte[]{(byte) (bigInteger.intValue() << 2)};
        }
        if (bigInteger.compareTo(BigInteger.valueOf(16384)) < 0) {
            int intValue = (bigInteger.intValue() << 2) | 1;
            bArr = new byte[]{(byte) (intValue & 255), (byte) ((intValue >> 8) & 255)};
        } else if (bigInteger.compareTo(BigInteger.valueOf(1073741824)) < 0) {
            int intValue2 = (bigInteger.intValue() << 2) | 2;
            bArr = new byte[]{(byte) (intValue2 & 255), (byte) ((intValue2 >> 8) & 255), (byte) ((intValue2 >> 16) & 255), (byte) ((intValue2 >> 24) & 255)};
        } else {
            byte[] bigIntToLEBytes = bigIntToLEBytes(bigInteger);
            int length = bigIntToLEBytes.length;
            if (length <= 67) {
                ByteSpreadBuilder byteSpreadBuilder = new ByteSpreadBuilder(2);
                byteSpreadBuilder.add((byte) (((length - 4) << 2) | 3));
                byteSpreadBuilder.addSpread(bigIntToLEBytes);
                return byteSpreadBuilder.toArray();
            }
            throw new IllegalArgumentException("Compact encoding supports max 2^536-1");
        }
        return bArr;
    }

    private final byte[] compactEncodeInt(Object obj) {
        BigInteger valueOf = obj instanceof BigInteger ? (BigInteger) obj : BigInteger.valueOf(Long.parseLong(obj.toString()));
        if (valueOf.compareTo(BigInteger.valueOf(64)) < 0) {
            return new byte[]{(byte) (valueOf.intValue() << 2)};
        } else if (valueOf.compareTo(BigInteger.valueOf(16384)) < 0) {
            return new byte[]{(byte) (((valueOf.intValue() << 2) | 1) & 255), (byte) ((valueOf.intValue() >> 6) & 255)};
        } else if (valueOf.compareTo(BigInteger.valueOf(1073741824)) < 0) {
            int intValue = (valueOf.intValue() << 2) | 2;
            return new byte[]{(byte) (intValue & 255), (byte) ((intValue >> 8) & 255), (byte) ((intValue >> 16) & 255), (byte) ((intValue >> 24) & 255)};
        } else {
            Intrinsics.checkNotNull(valueOf);
            byte[] bigIntToLEBytes = bigIntToLEBytes(valueOf);
            int length = bigIntToLEBytes.length;
            if (length <= 67) {
                ByteSpreadBuilder byteSpreadBuilder = new ByteSpreadBuilder(2);
                byteSpreadBuilder.add((byte) (((length - 4) << 2) | 3));
                byteSpreadBuilder.addSpread(bigIntToLEBytes);
                return byteSpreadBuilder.toArray();
            }
            throw new IllegalArgumentException("Compact encoding supports max 2^536-1");
        }
    }

    private final String deriveExtrinsicHash(byte[] bArr) {
        try {
            Blake2bDigest blake2bDigest = new Blake2bDigest((byte[]) null, 32, (byte[]) null, (byte[]) null);
            blake2bDigest.reset();
            blake2bDigest.update(bArr, 0, bArr.length);
            byte[] bArr2 = new byte[blake2bDigest.getDigestSize()];
            blake2bDigest.doFinal(bArr2, 0);
            return UtilFunctionsKt.bytesToHex(bArr2);
        } catch (Exception unused) {
            return Constants.IPC_BUNDLE_KEY_SEND_ERROR;
        }
    }

    private final int guessSignatureTypeFromAddress(String str) {
        try {
            TVF.Companion companion = TVF.Companion;
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String base58 = companion.toBase58(bytes);
            if (base58.length() == 0) {
                return 1;
            }
            char charAt = base58.charAt(0) & 255;
            if (charAt == '*') {
                return 0;
            }
            if (charAt == '<') {
                return 2;
            }
            return 1;
        } catch (Exception unused) {
            if (StringsKt__StringsJVMKt.startsWith$default(str, ZkSyncContract.BINARY, false, 2, (Object) null)) {
                return 2;
            }
        }
    }

    private final String normalizeHex(String str) {
        return StringsKt__StringsJVMKt.startsWith$default(str, ZkSyncContract.BINARY, false, 2, (Object) null) ? StringsKt__StringsKt.removePrefix(str, (CharSequence) ZkSyncContract.BINARY) : str;
    }

    private final int parseHex(Object obj) {
        return Integer.parseInt(normalizeHex(String.valueOf(obj)), CharsKt.checkRadix(16));
    }

    private final byte[] ss58AddressToPublicKey(String str) {
        byte[] decode = Base58.decode(str);
        if (decode.length >= 33) {
            Intrinsics.checkNotNull(decode);
            return ArraysKt.sliceArray(decode, new IntRange(1, 32));
        }
        throw new IllegalArgumentException("Too short to contain a public key");
    }

    @Nullable
    public final String calculatePolkadotHash(@NotNull SignatureResponse signatureResponse, @NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(signatureResponse, "signatureResponse");
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        try {
            String address = requestParams.getAddress();
            if (address == null && (address = requestParams.getTransactionPayload().getAddress()) == null) {
                address = "";
            }
            return deriveExtrinsicHash(addSignatureToExtrinsic(ss58AddressToPublicKey(address), signatureResponse.getSignature(), requestParams.getTransactionPayload()));
        } catch (Exception unused) {
            return null;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u000fHÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001f¨\u00064"}, d2 = {"Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "", "method", "", "specVersion", "transactionVersion", "genesisHash", "blockHash", "era", "nonce", "tip", "mode", "metadataHash", "address", "version", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getMethod", "()Ljava/lang/String;", "getSpecVersion", "getTransactionVersion", "getGenesisHash", "getBlockHash", "getEra", "getNonce", "getTip", "getMode", "getMetadataHash", "getAddress", "getVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/reown/sign/engine/model/tvf/PolkadotSignTransaction$TransactionPayload;", "equals", "", "other", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TransactionPayload {
        @Nullable
        private final String address;
        @Nullable
        private final String blockHash;
        @Nullable
        private final String era;
        @Nullable
        private final String genesisHash;
        @Nullable
        private final String metadataHash;
        @NotNull
        private final String method;
        @Nullable
        private final String mode;
        @Nullable
        private final String nonce;
        @Nullable
        private final String specVersion;
        @Nullable
        private final String tip;
        @Nullable
        private final String transactionVersion;
        @Nullable
        private final Integer version;

        public TransactionPayload(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Integer num) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            this.method = str;
            this.specVersion = str2;
            this.transactionVersion = str3;
            this.genesisHash = str4;
            this.blockHash = str5;
            this.era = str6;
            this.nonce = str7;
            this.tip = str8;
            this.mode = str9;
            this.metadataHash = str10;
            this.address = str11;
            this.version = num;
        }

        public static /* synthetic */ TransactionPayload copy$default(TransactionPayload transactionPayload, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Integer num, int i3, Object obj) {
            TransactionPayload transactionPayload2 = transactionPayload;
            int i4 = i3;
            return transactionPayload.copy((i4 & 1) != 0 ? transactionPayload2.method : str, (i4 & 2) != 0 ? transactionPayload2.specVersion : str2, (i4 & 4) != 0 ? transactionPayload2.transactionVersion : str3, (i4 & 8) != 0 ? transactionPayload2.genesisHash : str4, (i4 & 16) != 0 ? transactionPayload2.blockHash : str5, (i4 & 32) != 0 ? transactionPayload2.era : str6, (i4 & 64) != 0 ? transactionPayload2.nonce : str7, (i4 & 128) != 0 ? transactionPayload2.tip : str8, (i4 & 256) != 0 ? transactionPayload2.mode : str9, (i4 & 512) != 0 ? transactionPayload2.metadataHash : str10, (i4 & 1024) != 0 ? transactionPayload2.address : str11, (i4 & 2048) != 0 ? transactionPayload2.version : num);
        }

        @NotNull
        public final String component1() {
            return this.method;
        }

        @Nullable
        public final String component10() {
            return this.metadataHash;
        }

        @Nullable
        public final String component11() {
            return this.address;
        }

        @Nullable
        public final Integer component12() {
            return this.version;
        }

        @Nullable
        public final String component2() {
            return this.specVersion;
        }

        @Nullable
        public final String component3() {
            return this.transactionVersion;
        }

        @Nullable
        public final String component4() {
            return this.genesisHash;
        }

        @Nullable
        public final String component5() {
            return this.blockHash;
        }

        @Nullable
        public final String component6() {
            return this.era;
        }

        @Nullable
        public final String component7() {
            return this.nonce;
        }

        @Nullable
        public final String component8() {
            return this.tip;
        }

        @Nullable
        public final String component9() {
            return this.mode;
        }

        @NotNull
        public final TransactionPayload copy(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Integer num) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            return new TransactionPayload(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, num);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TransactionPayload)) {
                return false;
            }
            TransactionPayload transactionPayload = (TransactionPayload) obj;
            return Intrinsics.areEqual((Object) this.method, (Object) transactionPayload.method) && Intrinsics.areEqual((Object) this.specVersion, (Object) transactionPayload.specVersion) && Intrinsics.areEqual((Object) this.transactionVersion, (Object) transactionPayload.transactionVersion) && Intrinsics.areEqual((Object) this.genesisHash, (Object) transactionPayload.genesisHash) && Intrinsics.areEqual((Object) this.blockHash, (Object) transactionPayload.blockHash) && Intrinsics.areEqual((Object) this.era, (Object) transactionPayload.era) && Intrinsics.areEqual((Object) this.nonce, (Object) transactionPayload.nonce) && Intrinsics.areEqual((Object) this.tip, (Object) transactionPayload.tip) && Intrinsics.areEqual((Object) this.mode, (Object) transactionPayload.mode) && Intrinsics.areEqual((Object) this.metadataHash, (Object) transactionPayload.metadataHash) && Intrinsics.areEqual((Object) this.address, (Object) transactionPayload.address) && Intrinsics.areEqual((Object) this.version, (Object) transactionPayload.version);
        }

        @Nullable
        public final String getAddress() {
            return this.address;
        }

        @Nullable
        public final String getBlockHash() {
            return this.blockHash;
        }

        @Nullable
        public final String getEra() {
            return this.era;
        }

        @Nullable
        public final String getGenesisHash() {
            return this.genesisHash;
        }

        @Nullable
        public final String getMetadataHash() {
            return this.metadataHash;
        }

        @NotNull
        public final String getMethod() {
            return this.method;
        }

        @Nullable
        public final String getMode() {
            return this.mode;
        }

        @Nullable
        public final String getNonce() {
            return this.nonce;
        }

        @Nullable
        public final String getSpecVersion() {
            return this.specVersion;
        }

        @Nullable
        public final String getTip() {
            return this.tip;
        }

        @Nullable
        public final String getTransactionVersion() {
            return this.transactionVersion;
        }

        @Nullable
        public final Integer getVersion() {
            return this.version;
        }

        public int hashCode() {
            int hashCode = this.method.hashCode() * 31;
            String str = this.specVersion;
            int i3 = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.transactionVersion;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.genesisHash;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.blockHash;
            int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.era;
            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.nonce;
            int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.tip;
            int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.mode;
            int hashCode9 = (hashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.metadataHash;
            int hashCode10 = (hashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.address;
            int hashCode11 = (hashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
            Integer num = this.version;
            if (num != null) {
                i3 = num.hashCode();
            }
            return hashCode11 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.method;
            String str2 = this.specVersion;
            String str3 = this.transactionVersion;
            String str4 = this.genesisHash;
            String str5 = this.blockHash;
            String str6 = this.era;
            String str7 = this.nonce;
            String str8 = this.tip;
            String str9 = this.mode;
            String str10 = this.metadataHash;
            String str11 = this.address;
            Integer num = this.version;
            StringBuilder l2 = C0118y.l("TransactionPayload(method=", str, ", specVersion=", str2, ", transactionVersion=");
            b.w(l2, str3, ", genesisHash=", str4, ", blockHash=");
            b.w(l2, str5, ", era=", str6, ", nonce=");
            b.w(l2, str7, ", tip=", str8, ", mode=");
            b.w(l2, str9, ", metadataHash=", str10, ", address=");
            l2.append(str11);
            l2.append(", version=");
            l2.append(num);
            l2.append(")");
            return l2.toString();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ TransactionPayload(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, str5, (i3 & 32) != 0 ? "" : str6, str7, str8, str9, str10, str11, num);
        }
    }
}
