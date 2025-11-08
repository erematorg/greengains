package com.google.crypto.tink.prf;

import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;

public final class PrfKeyTemplates {
    public static final KeyTemplate AES_CMAC_PRF = createAes256CmacTemplate();
    public static final KeyTemplate HKDF_SHA256 = createHkdfKeyTemplate();
    public static final KeyTemplate HMAC_SHA256_PRF = createHmacTemplate(32, HashType.SHA256);
    public static final KeyTemplate HMAC_SHA512_PRF = createHmacTemplate(64, HashType.SHA512);

    private PrfKeyTemplates() {
    }

    private static KeyTemplate createAes256CmacTemplate() {
        return (KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(new AesCmacPrfKeyManager().getKeyType()).setValue(((AesCmacPrfKeyFormat) AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build()).toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    private static KeyTemplate createHkdfKeyTemplate() {
        return (KeyTemplate) KeyTemplate.newBuilder().setValue(((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build()).toByteString()).setTypeUrl(HkdfPrfKeyManager.staticKeyType()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }

    private static KeyTemplate createHmacTemplate(int i3, HashType hashType) {
        return (KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(new HmacPrfKeyManager().getKeyType()).setValue(((HmacPrfKeyFormat) HmacPrfKeyFormat.newBuilder().setParams((HmacPrfParams) HmacPrfParams.newBuilder().setHash(hashType).build()).setKeySize(i3).build()).toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build();
    }
}
