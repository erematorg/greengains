package com.google.crypto.tink.mac;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChunkedMacWrapper implements PrimitiveWrapper<ChunkedMac, ChunkedMac> {
    private static final ChunkedMacWrapper WRAPPER = new ChunkedMacWrapper();

    @Immutable
    public static class WrappedChunkedMac implements ChunkedMac {
        private final PrimitiveSet<ChunkedMac> primitives;

        private ChunkedMac getChunkedMac(PrimitiveSet.Entry<ChunkedMac> entry) {
            return entry.getFullPrimitive();
        }

        public ChunkedMacComputation createComputation() throws GeneralSecurityException {
            return getChunkedMac(this.primitives.getPrimary()).createComputation();
        }

        public ChunkedMacVerification createVerification(byte[] bArr) throws GeneralSecurityException {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            ArrayList arrayList = new ArrayList();
            for (PrimitiveSet.Entry<ChunkedMac> chunkedMac : this.primitives.getPrimitive(copyOf)) {
                arrayList.add(getChunkedMac(chunkedMac).createVerification(bArr));
            }
            for (PrimitiveSet.Entry<ChunkedMac> chunkedMac2 : this.primitives.getRawPrimitives()) {
                arrayList.add(getChunkedMac(chunkedMac2).createVerification(bArr));
            }
            return new WrappedChunkedMacVerification(arrayList);
        }

        private WrappedChunkedMac(PrimitiveSet<ChunkedMac> primitiveSet) {
            this.primitives = primitiveSet;
        }
    }

    public static class WrappedChunkedMacVerification implements ChunkedMacVerification {
        private final List<ChunkedMacVerification> verifications;

        public void update(ByteBuffer byteBuffer) throws GeneralSecurityException {
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.mark();
            for (ChunkedMacVerification update : this.verifications) {
                duplicate.reset();
                update.update(duplicate);
            }
            byteBuffer.position(byteBuffer.limit());
        }

        public void verifyMac() throws GeneralSecurityException {
            GeneralSecurityException generalSecurityException = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");
            for (ChunkedMacVerification verifyMac : this.verifications) {
                try {
                    verifyMac.verifyMac();
                    return;
                } catch (GeneralSecurityException e3) {
                    generalSecurityException.addSuppressed(e3);
                }
            }
            throw generalSecurityException;
        }

        private WrappedChunkedMacVerification(List<ChunkedMacVerification> list) {
            this.verifications = list;
        }
    }

    private ChunkedMacWrapper() {
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    public Class<ChunkedMac> getInputPrimitiveClass() {
        return ChunkedMac.class;
    }

    public Class<ChunkedMac> getPrimitiveClass() {
        return ChunkedMac.class;
    }

    public ChunkedMac wrap(PrimitiveSet<ChunkedMac> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        } else if (primitiveSet.getPrimary() != null) {
            for (List<PrimitiveSet.Entry<ChunkedMac>> it : primitiveSet.getAll()) {
                for (PrimitiveSet.Entry fullPrimitive : it) {
                    ChunkedMac chunkedMac = (ChunkedMac) fullPrimitive.getFullPrimitive();
                }
            }
            return new WrappedChunkedMac(primitiveSet);
        } else {
            throw new GeneralSecurityException("no primary in primitive set");
        }
    }
}
