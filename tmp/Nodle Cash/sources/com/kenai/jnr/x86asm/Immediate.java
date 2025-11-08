package com.kenai.jnr.x86asm;

@Deprecated
public final class Immediate extends Operand {
    private final boolean isUnsigned;
    private final RELOC_MODE relocMode = RELOC_MODE.RELOC_NONE;
    private final long value;

    public static final class Cache {
        static final Immediate[] cache = new Immediate[256];

        static {
            int i3 = 0;
            while (true) {
                Immediate[] immediateArr = cache;
                if (i3 < immediateArr.length) {
                    immediateArr[i3] = new Immediate((long) (i3 - 128), false);
                    i3++;
                } else {
                    return;
                }
            }
        }

        private Cache() {
        }
    }

    public Immediate(long j2, boolean z2) {
        super(3, 0);
        this.value = j2;
        this.isUnsigned = z2;
    }

    public static final Immediate imm(long j2) {
        return (j2 < -128 || j2 > 127) ? new Immediate(j2, false) : Cache.cache[((int) j2) + 128];
    }

    public static final Immediate uimm(long j2) {
        return new Immediate(j2, true);
    }

    public final byte byteValue() {
        return (byte) ((int) this.value);
    }

    public final int intValue() {
        return (int) this.value;
    }

    public final boolean isUnsigned() {
        return this.isUnsigned;
    }

    public final long longValue() {
        return this.value;
    }

    public RELOC_MODE relocMode() {
        return this.relocMode;
    }

    public final short shortValue() {
        return (short) ((int) this.value);
    }

    public long value() {
        return this.value;
    }
}
