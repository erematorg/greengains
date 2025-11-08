package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

public final class ChaCha20Poly1305Parameters extends AeadParameters {
    private final Variant variant;

    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY = new Variant("CRUNCHY");
        public static final Variant NO_PREFIX = new Variant("NO_PREFIX");
        public static final Variant TINK = new Variant("TINK");
        private final String name;

        private Variant(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    private ChaCha20Poly1305Parameters(Variant variant2) {
        this.variant = variant2;
    }

    public static ChaCha20Poly1305Parameters create() {
        return new ChaCha20Poly1305Parameters(Variant.NO_PREFIX);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ChaCha20Poly1305Parameters) && ((ChaCha20Poly1305Parameters) obj).getVariant() == getVariant();
    }

    public Variant getVariant() {
        return this.variant;
    }

    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hashCode(this.variant);
    }

    public String toString() {
        return "ChaCha20Poly1305 Parameters (variant: " + this.variant + ")";
    }

    public static ChaCha20Poly1305Parameters create(Variant variant2) {
        return new ChaCha20Poly1305Parameters(variant2);
    }
}
