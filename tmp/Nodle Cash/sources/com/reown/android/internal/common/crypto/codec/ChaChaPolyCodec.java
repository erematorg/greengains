package com.reown.android.internal.common.crypto.codec;

import A.a;
import com.reown.android.internal.common.crypto.kmr.KeyManagementRepository;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.MissingKeyException;
import com.reown.android.internal.common.model.MissingParticipantsException;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.UnknownEnvelopeTypeException;
import com.reown.android.internal.utils.ContextKt;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.util.UtilFunctionsKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.crypto.modes.ChaCha20Poly1305;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\tH\u0002J!\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\tH\u0002J(\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J*\u0010\u001f\u001a\u00020\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010 \u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J'\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0002¢\u0006\u0004\b%\u0010&J'\u0010'\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0002¢\u0006\u0004\b(\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/reown/android/internal/common/crypto/codec/ChaChaPolyCodec;", "Lcom/reown/android/internal/common/crypto/codec/Codec;", "keyManagementRepository", "Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;", "<init>", "(Lcom/reown/android/internal/common/crypto/kmr/KeyManagementRepository;)V", "cha20Poly1305", "Lorg/bouncycastle/crypto/modes/ChaCha20Poly1305;", "encrypt", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "payload", "", "envelopeType", "Lcom/reown/android/internal/common/model/EnvelopeType;", "participants", "Lcom/reown/android/internal/common/model/Participants;", "decrypt", "cipherText", "decryptType0", "encryptedPayloadBytes", "decryptType1", "receiverPublicKey", "Lcom/reown/foundation/common/model/PublicKey;", "decryptType1-LkTxK_4", "([BLjava/lang/String;)Ljava/lang/String;", "decryptType2", "encryptEnvelopeType0", "nonceBytes", "input", "encryptEnvelopeType1", "encryptEnvelopeType2", "encryptPayload", "key", "Lcom/reown/android/internal/common/model/SymmetricKey;", "nonce", "encryptPayload-QyW3hOA", "(Ljava/lang/String;[B[B)[B", "decryptPayload", "decryptPayload-QyW3hOA", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChaChaPolyCodec implements Codec {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final int ENVELOPE_TYPE_SIZE = 1;
    @Deprecated
    public static final int KEY_SIZE = 32;
    @Deprecated
    public static final int NONCE_SIZE = 12;
    @NotNull
    private final ChaCha20Poly1305 cha20Poly1305 = new ChaCha20Poly1305();
    @NotNull
    private final KeyManagementRepository keyManagementRepository;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/reown/android/internal/common/crypto/codec/ChaChaPolyCodec$Companion;", "", "<init>", "()V", "NONCE_SIZE", "", "KEY_SIZE", "ENVELOPE_TYPE_SIZE", "envelopeType", "", "", "getEnvelopeType", "([B)B", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte getEnvelopeType(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            return bArr[0];
        }

        private Companion() {
        }
    }

    public ChaChaPolyCodec(@NotNull KeyManagementRepository keyManagementRepository2) {
        Intrinsics.checkNotNullParameter(keyManagementRepository2, "keyManagementRepository");
        this.keyManagementRepository = keyManagementRepository2;
    }

    /* renamed from: decryptPayload-QyW3hOA  reason: not valid java name */
    private final byte[] m8706decryptPayloadQyW3hOA(String str, byte[] bArr, byte[] bArr2) {
        this.cha20Poly1305.init(false, new ParametersWithIV(new KeyParameter(UtilFunctionsKt.hexToBytes(str)), bArr));
        byte[] bArr3 = new byte[this.cha20Poly1305.getOutputSize(bArr2.length)];
        this.cha20Poly1305.doFinal(bArr3, this.cha20Poly1305.processBytes(bArr2, 0, bArr2.length, bArr3, 0));
        return bArr3;
    }

    private final String decryptType0(Topic topic, byte[] bArr) {
        byte[] bArr2 = new byte[12];
        byte[] bArr3 = new byte[(bArr.length - 13)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(...)");
        wrap.get(new byte[1]);
        wrap.get(bArr2);
        wrap.get(bArr3);
        return new String(m8706decryptPayloadQyW3hOA(this.keyManagementRepository.m8730getSymmetricKeyp84wnz8(topic.getValue()), bArr2, bArr3), Charsets.UTF_8);
    }

    /* renamed from: decryptType1-LkTxK_4  reason: not valid java name */
    private final String m8707decryptType1LkTxK_4(byte[] bArr, String str) {
        if (str != null) {
            byte[] bArr2 = new byte[12];
            byte[] bArr3 = new byte[32];
            byte[] bArr4 = new byte[(bArr.length - 45)];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            Intrinsics.checkNotNullExpressionValue(wrap, "wrap(...)");
            wrap.get(new byte[1]);
            wrap.get(bArr3);
            wrap.get(bArr2);
            wrap.get(bArr4);
            return new String(m8706decryptPayloadQyW3hOA(this.keyManagementRepository.m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(str, PublicKey.m8856constructorimpl(UtilFunctionsKt.bytesToHex(bArr3))), bArr2, bArr4), Charsets.UTF_8);
        }
        throw new MissingKeyException("Missing receiver public key");
    }

    private final String decryptType2(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length - 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(...)");
        wrap.get(new byte[1]);
        wrap.get(bArr2);
        return new String(bArr2, Charsets.UTF_8);
    }

    private final byte[] encryptEnvelopeType0(Topic topic, byte[] bArr, byte[] bArr2, EnvelopeType envelopeType) {
        byte[] r12 = m8708encryptPayloadQyW3hOA(this.keyManagementRepository.m8730getSymmetricKeyp84wnz8(topic.getValue()), bArr, bArr2);
        byte[] array = ByteBuffer.allocate(r12.length + 13).put(envelopeType.getId()).put(bArr).put(r12).array();
        Intrinsics.checkNotNull(array);
        return array;
    }

    private final byte[] encryptEnvelopeType1(Participants participants, byte[] bArr, byte[] bArr2, EnvelopeType envelopeType) {
        if (participants != null) {
            String r02 = participants.m8776getSenderPublicKeyuN_RPug();
            byte[] hexToBytes = UtilFunctionsKt.hexToBytes(r02);
            byte[] r3 = m8708encryptPayloadQyW3hOA(this.keyManagementRepository.m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(r02, participants.m8775getReceiverPublicKeyuN_RPug()), bArr, bArr2);
            byte[] array = ByteBuffer.allocate(r3.length + 13 + hexToBytes.length).put(envelopeType.getId()).put(hexToBytes).put(bArr).put(r3).array();
            Intrinsics.checkNotNull(array);
            return array;
        }
        throw new MissingParticipantsException("Missing participants when encrypting envelope type 1");
    }

    private final byte[] encryptEnvelopeType2(byte[] bArr, EnvelopeType envelopeType) {
        byte[] array = ByteBuffer.allocate(bArr.length + 1).put(envelopeType.getId()).put(bArr).array();
        Intrinsics.checkNotNull(array);
        return array;
    }

    /* renamed from: encryptPayload-QyW3hOA  reason: not valid java name */
    private final byte[] m8708encryptPayloadQyW3hOA(String str, byte[] bArr, byte[] bArr2) {
        this.cha20Poly1305.init(true, new ParametersWithIV(new KeyParameter(UtilFunctionsKt.hexToBytes(str)), bArr));
        byte[] bArr3 = new byte[this.cha20Poly1305.getOutputSize(bArr2.length)];
        this.cha20Poly1305.doFinal(bArr3, this.cha20Poly1305.processBytes(bArr2, 0, bArr2.length, bArr3, 0));
        return bArr3;
    }

    @NotNull
    public String decrypt(@NotNull Topic topic, @NotNull byte[] bArr) throws UnknownEnvelopeTypeException, MissingKeyException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(bArr, "cipherText");
        byte envelopeType = Companion.getEnvelopeType(bArr);
        if (envelopeType == EnvelopeType.ZERO.getId()) {
            return decryptType0(topic, bArr);
        }
        if (envelopeType == EnvelopeType.ONE.getId()) {
            return m8707decryptType1LkTxK_4(bArr, this.keyManagementRepository.m8728getPublicKeyp9DwDrs(ContextKt.getParticipantTag(topic)));
        }
        if (envelopeType == EnvelopeType.TWO.getId()) {
            return decryptType2(bArr);
        }
        throw new UnknownEnvelopeTypeException(a.k("Decrypt; Unknown envelope type: ", envelopeType));
    }

    @NotNull
    public byte[] encrypt(@NotNull Topic topic, @NotNull String str, @NotNull EnvelopeType envelopeType, @Nullable Participants participants) throws UnknownEnvelopeTypeException, MissingParticipantsException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, "payload");
        Intrinsics.checkNotNullParameter(envelopeType, "envelopeType");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] randomBytes = UtilFunctionsKt.randomBytes(12);
        byte id = envelopeType.getId();
        if (id == EnvelopeType.ZERO.getId()) {
            return encryptEnvelopeType0(topic, randomBytes, bytes, envelopeType);
        }
        if (id == EnvelopeType.ONE.getId()) {
            return encryptEnvelopeType1(participants, randomBytes, bytes, envelopeType);
        }
        if (id == EnvelopeType.TWO.getId()) {
            return encryptEnvelopeType2(bytes, envelopeType);
        }
        throw new UnknownEnvelopeTypeException(a.k("Encrypt; Unknown envelope type: ", envelopeType.getId()));
    }
}
