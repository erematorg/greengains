package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

class JwtPublicKeySignWrapper implements PrimitiveWrapper<JwtPublicKeySignInternal, JwtPublicKeySign> {
    private static final JwtPublicKeySignWrapper WRAPPER = new JwtPublicKeySignWrapper();

    @Immutable
    public static class WrappedJwtPublicKeySign implements JwtPublicKeySign {
        private final PrimitiveSet<JwtPublicKeySignInternal> primitives;

        public WrappedJwtPublicKeySign(PrimitiveSet<JwtPublicKeySignInternal> primitiveSet) {
            this.primitives = primitiveSet;
        }

        public String signAndEncode(RawJwt rawJwt) throws GeneralSecurityException {
            PrimitiveSet.Entry<JwtPublicKeySignInternal> primary = this.primitives.getPrimary();
            return this.primitives.getPrimary().getPrimitive().signAndEncodeWithKid(rawJwt, JwtFormat.getKid(primary.getKeyId(), primary.getOutputPrefixType()));
        }
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    private static void validate(PrimitiveSet<JwtPublicKeySignInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() != null) {
            for (List<PrimitiveSet.Entry<JwtPublicKeySignInternal>> it : primitiveSet.getAll()) {
                Iterator it2 = it.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        PrimitiveSet.Entry entry = (PrimitiveSet.Entry) it2.next();
                        if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                            throw new GeneralSecurityException("unsupported OutputPrefixType");
                        }
                    }
                }
            }
            return;
        }
        throw new GeneralSecurityException("Primitive set has no primary.");
    }

    public Class<JwtPublicKeySignInternal> getInputPrimitiveClass() {
        return JwtPublicKeySignInternal.class;
    }

    public Class<JwtPublicKeySign> getPrimitiveClass() {
        return JwtPublicKeySign.class;
    }

    public JwtPublicKeySign wrap(PrimitiveSet<JwtPublicKeySignInternal> primitiveSet) throws GeneralSecurityException {
        validate(primitiveSet);
        return new WrappedJwtPublicKeySign(primitiveSet);
    }
}
