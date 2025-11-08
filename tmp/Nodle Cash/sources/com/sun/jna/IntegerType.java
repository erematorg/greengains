package com.sun.jna;

public abstract class IntegerType extends Number implements NativeMapped {
    private static final long serialVersionUID = 1;
    private Number number;
    private int size;
    private boolean unsigned;
    private long value;

    public IntegerType(int i3) {
        this(i3, 0, false);
    }

    public static final int compare(long j2, long j3) {
        int i3 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i3 == 0) {
            return 0;
        }
        return i3 < 0 ? -1 : 1;
    }

    public double doubleValue() {
        return this.number.doubleValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof IntegerType) && this.number.equals(((IntegerType) obj).number);
    }

    public float floatValue() {
        return this.number.floatValue();
    }

    public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
        long longValue = obj == null ? 0 : ((Number) obj).longValue();
        IntegerType integerType = (IntegerType) Klass.newInstance(getClass());
        integerType.setValue(longValue);
        return integerType;
    }

    public int hashCode() {
        return this.number.hashCode();
    }

    public int intValue() {
        return (int) this.value;
    }

    public long longValue() {
        return this.value;
    }

    public Class<?> nativeType() {
        return this.number.getClass();
    }

    public void setValue(long j2) {
        long j3;
        this.value = j2;
        int i3 = this.size;
        if (i3 == 1) {
            if (this.unsigned) {
                this.value = 255 & j2;
            }
            byte b3 = (byte) ((int) j2);
            j3 = (long) b3;
            this.number = Byte.valueOf(b3);
        } else if (i3 == 2) {
            if (this.unsigned) {
                this.value = 65535 & j2;
            }
            short s3 = (short) ((int) j2);
            j3 = (long) s3;
            this.number = Short.valueOf(s3);
        } else if (i3 == 4) {
            if (this.unsigned) {
                this.value = 4294967295L & j2;
            }
            int i4 = (int) j2;
            j3 = (long) i4;
            this.number = Integer.valueOf(i4);
        } else if (i3 == 8) {
            this.number = Long.valueOf(j2);
            j3 = j2;
        } else {
            throw new IllegalArgumentException("Unsupported size: " + this.size);
        }
        int i5 = this.size;
        if (i5 < 8) {
            long j4 = ~((1 << (i5 * 8)) - 1);
            int i6 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            if ((i6 < 0 && j3 != j2) || (i6 >= 0 && (j4 & j2) != 0)) {
                throw new IllegalArgumentException("Argument value 0x" + Long.toHexString(j2) + " exceeds native capacity (" + this.size + " bytes) mask=0x" + Long.toHexString(j4));
            }
        }
    }

    public Object toNative() {
        return this.number;
    }

    public String toString() {
        return this.number.toString();
    }

    public IntegerType(int i3, boolean z2) {
        this(i3, 0, z2);
    }

    public static <T extends IntegerType> int compare(T t2, T t3) {
        if (t2 == t3) {
            return 0;
        }
        if (t2 == null) {
            return 1;
        }
        if (t3 == null) {
            return -1;
        }
        return compare(t2.longValue(), t3.longValue());
    }

    public IntegerType(int i3, long j2) {
        this(i3, j2, false);
    }

    public static int compare(IntegerType integerType, long j2) {
        if (integerType == null) {
            return 1;
        }
        return compare(integerType.longValue(), j2);
    }

    public IntegerType(int i3, long j2, boolean z2) {
        this.size = i3;
        this.unsigned = z2;
        setValue(j2);
    }
}
