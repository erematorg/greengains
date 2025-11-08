package com.sun.jna;

import androidx.constraintlayout.core.state.b;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class Pointer {
    public static final Pointer NULL = null;
    protected long peer;

    public static class Opaque extends Pointer {
        private final String MSG;

        public void clear(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public String dump(long j2, int i3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public byte getByte(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public ByteBuffer getByteBuffer(long j2, long j3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public char getChar(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public double getDouble(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public float getFloat(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public int getInt(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public long getLong(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public Pointer getPointer(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public short getShort(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public String getString(long j2, String str) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public String getWideString(long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public long indexOf(long j2, byte b3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, byte[] bArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setByte(long j2, byte b3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setChar(long j2, char c3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setDouble(long j2, double d2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setFloat(long j2, float f2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setInt(long j2, int i3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setLong(long j2, long j3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setMemory(long j2, long j3, byte b3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setPointer(long j2, Pointer pointer) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setShort(long j2, short s3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setString(long j2, String str, String str2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void setWideString(long j2, String str) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public Pointer share(long j2, long j3) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public String toString() {
            return "const@0x" + Long.toHexString(this.peer);
        }

        public void write(long j2, byte[] bArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        private Opaque(long j2) {
            super(j2);
            this.MSG = "This pointer is opaque: " + this;
        }

        public void read(long j2, char[] cArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, char[] cArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, short[] sArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, short[] sArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, int[] iArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, int[] iArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, long[] jArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, long[] jArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, float[] fArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, float[] fArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, double[] dArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, double[] dArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void read(long j2, Pointer[] pointerArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }

        public void write(long j2, Pointer[] pointerArr, int i3, int i4) {
            throw new UnsupportedOperationException(this.MSG);
        }
    }

    public Pointer() {
    }

    public static final Pointer createConstant(long j2) {
        return new Opaque(j2);
    }

    public static long nativeValue(Pointer pointer) {
        if (pointer == null) {
            return 0;
        }
        return pointer.peer;
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readArray(long r8, java.lang.Object r10, java.lang.Class<?> r11) {
        /*
            r7 = this;
            int r5 = java.lang.reflect.Array.getLength(r10)
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r11 != r0) goto L_0x0013
            r3 = r10
            byte[] r3 = (byte[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (byte[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x0013:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r11 != r0) goto L_0x0022
            r3 = r10
            short[] r3 = (short[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (short[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x0022:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r11 != r0) goto L_0x0031
            r3 = r10
            char[] r3 = (char[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (char[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x0031:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r11 != r0) goto L_0x0040
            r3 = r10
            int[] r3 = (int[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (int[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x0040:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r11 != r0) goto L_0x004f
            r3 = r10
            long[] r3 = (long[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (long[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x004f:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r11 != r0) goto L_0x005e
            r3 = r10
            float[] r3 = (float[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (float[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x005e:
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r11 != r0) goto L_0x006d
            r3 = r10
            double[] r3 = (double[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (double[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x006d:
            java.lang.Class<com.sun.jna.Pointer> r0 = com.sun.jna.Pointer.class
            boolean r0 = r0.isAssignableFrom(r11)
            if (r0 == 0) goto L_0x0080
            r3 = r10
            com.sun.jna.Pointer[] r3 = (com.sun.jna.Pointer[]) r3
            r4 = 0
            r0 = r7
            r1 = r8
            r0.read((long) r1, (com.sun.jna.Pointer[]) r3, (int) r4, (int) r5)
            goto L_0x0121
        L_0x0080:
            java.lang.Class<com.sun.jna.Structure> r0 = com.sun.jna.Structure.class
            boolean r0 = r0.isAssignableFrom(r11)
            r1 = 0
            if (r0 == 0) goto L_0x00e7
            com.sun.jna.Structure[] r10 = (com.sun.jna.Structure[]) r10
            java.lang.Class<com.sun.jna.Structure$ByReference> r0 = com.sun.jna.Structure.ByReference.class
            boolean r0 = r0.isAssignableFrom(r11)
            if (r0 == 0) goto L_0x00a8
            int r0 = r10.length
            com.sun.jna.Pointer[] r7 = r7.getPointerArray(r8, r0)
        L_0x0098:
            int r8 = r10.length
            if (r1 >= r8) goto L_0x0121
            r8 = r10[r1]
            r9 = r7[r1]
            com.sun.jna.Structure r8 = com.sun.jna.Structure.updateStructureByReference(r11, r8, r9)
            r10[r1] = r8
            int r1 = r1 + 1
            goto L_0x0098
        L_0x00a8:
            r0 = r10[r1]
            r2 = 1
            if (r0 != 0) goto L_0x00bb
            com.sun.jna.Pointer r0 = r7.share(r8)
            com.sun.jna.Structure r0 = com.sun.jna.Structure.newInstance(r11, (com.sun.jna.Pointer) r0)
            r0.conditionalAutoRead()
            r10[r1] = r0
            goto L_0x00c2
        L_0x00bb:
            int r11 = (int) r8
            r0.useMemory(r7, r11, r2)
            r0.read()
        L_0x00c2:
            int r11 = r10.length
            com.sun.jna.Structure[] r11 = r0.toArray((int) r11)
            r0 = r2
        L_0x00c8:
            int r1 = r10.length
            if (r0 >= r1) goto L_0x0121
            r1 = r10[r0]
            if (r1 != 0) goto L_0x00d4
            r1 = r11[r0]
            r10[r0] = r1
            goto L_0x00e4
        L_0x00d4:
            int r3 = r1.size()
            int r3 = r3 * r0
            long r3 = (long) r3
            long r3 = r3 + r8
            int r3 = (int) r3
            r1.useMemory(r7, r3, r2)
            r1 = r10[r0]
            r1.read()
        L_0x00e4:
            int r0 = r0 + 1
            goto L_0x00c8
        L_0x00e7:
            java.lang.Class<com.sun.jna.NativeMapped> r0 = com.sun.jna.NativeMapped.class
            boolean r0 = r0.isAssignableFrom(r11)
            if (r0 == 0) goto L_0x0122
            r0 = r10
            com.sun.jna.NativeMapped[] r0 = (com.sun.jna.NativeMapped[]) r0
            com.sun.jna.NativeMappedConverter r2 = com.sun.jna.NativeMappedConverter.getInstance(r11)
            java.lang.Class r3 = r10.getClass()
            int r10 = com.sun.jna.Native.getNativeSize(r3, r10)
            int r3 = r0.length
            int r10 = r10 / r3
        L_0x0100:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x0121
            int r3 = r10 * r1
            long r3 = (long) r3
            long r3 = r3 + r8
            java.lang.Class r5 = r2.nativeType()
            r6 = r0[r1]
            java.lang.Object r3 = r7.getValue(r3, r5, r6)
            com.sun.jna.FromNativeContext r4 = new com.sun.jna.FromNativeContext
            r4.<init>(r11)
            java.lang.Object r3 = r2.fromNative(r3, r4)
            com.sun.jna.NativeMapped r3 = (com.sun.jna.NativeMapped) r3
            r0[r1] = r3
            int r1 = r1 + 1
            goto L_0x0100
        L_0x0121:
            return
        L_0x0122:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Reading array of "
            java.lang.String r9 = " from memory not supported"
            java.lang.String r8 = androidx.constraintlayout.core.state.b.l(r8, r11, r9)
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Pointer.readArray(long, java.lang.Object, java.lang.Class):void");
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeArray(long r9, java.lang.Object r11, java.lang.Class<?> r12) {
        /*
            r8 = this;
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r12 != r0) goto L_0x0010
            r4 = r11
            byte[] r4 = (byte[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (byte[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0010:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r12 != r0) goto L_0x0020
            r4 = r11
            short[] r4 = (short[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (short[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0020:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r12 != r0) goto L_0x0030
            r4 = r11
            char[] r4 = (char[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (char[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0030:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r12 != r0) goto L_0x0040
            r4 = r11
            int[] r4 = (int[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (int[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0040:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r12 != r0) goto L_0x0050
            r4 = r11
            long[] r4 = (long[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (long[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0050:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r12 != r0) goto L_0x0060
            r4 = r11
            float[] r4 = (float[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (float[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0060:
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r12 != r0) goto L_0x0070
            r4 = r11
            double[] r4 = (double[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (double[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0070:
            java.lang.Class<com.sun.jna.Pointer> r0 = com.sun.jna.Pointer.class
            boolean r0 = r0.isAssignableFrom(r12)
            if (r0 == 0) goto L_0x0084
            r4 = r11
            com.sun.jna.Pointer[] r4 = (com.sun.jna.Pointer[]) r4
            int r6 = r4.length
            r5 = 0
            r1 = r8
            r2 = r9
            r1.write((long) r2, (com.sun.jna.Pointer[]) r4, (int) r5, (int) r6)
            goto L_0x012c
        L_0x0084:
            java.lang.Class<com.sun.jna.Structure> r0 = com.sun.jna.Structure.class
            boolean r0 = r0.isAssignableFrom(r12)
            r1 = 0
            if (r0 == 0) goto L_0x00f7
            com.sun.jna.Structure[] r11 = (com.sun.jna.Structure[]) r11
            java.lang.Class<com.sun.jna.Structure$ByReference> r0 = com.sun.jna.Structure.ByReference.class
            boolean r0 = r0.isAssignableFrom(r12)
            if (r0 == 0) goto L_0x00bb
            int r7 = r11.length
            com.sun.jna.Pointer[] r5 = new com.sun.jna.Pointer[r7]
        L_0x009a:
            int r12 = r11.length
            if (r1 >= r12) goto L_0x00b3
            r12 = r11[r1]
            if (r12 != 0) goto L_0x00a5
            r12 = 0
            r5[r1] = r12
            goto L_0x00b0
        L_0x00a5:
            com.sun.jna.Pointer r12 = r12.getPointer()
            r5[r1] = r12
            r12 = r11[r1]
            r12.write()
        L_0x00b0:
            int r1 = r1 + 1
            goto L_0x009a
        L_0x00b3:
            r6 = 0
            r2 = r8
            r3 = r9
            r2.write((long) r3, (com.sun.jna.Pointer[]) r5, (int) r6, (int) r7)
            goto L_0x012c
        L_0x00bb:
            r0 = r11[r1]
            r2 = 1
            if (r0 != 0) goto L_0x00cb
            com.sun.jna.Pointer r0 = r8.share(r9)
            com.sun.jna.Structure r0 = com.sun.jna.Structure.newInstance(r12, (com.sun.jna.Pointer) r0)
            r11[r1] = r0
            goto L_0x00cf
        L_0x00cb:
            int r12 = (int) r9
            r0.useMemory(r8, r12, r2)
        L_0x00cf:
            r0.write()
            int r12 = r11.length
            com.sun.jna.Structure[] r12 = r0.toArray((int) r12)
            r0 = r2
        L_0x00d8:
            int r1 = r11.length
            if (r0 >= r1) goto L_0x012c
            r1 = r11[r0]
            if (r1 != 0) goto L_0x00e4
            r1 = r12[r0]
            r11[r0] = r1
            goto L_0x00ef
        L_0x00e4:
            int r3 = r1.size()
            int r3 = r3 * r0
            long r3 = (long) r3
            long r3 = r3 + r9
            int r3 = (int) r3
            r1.useMemory(r8, r3, r2)
        L_0x00ef:
            r1 = r11[r0]
            r1.write()
            int r0 = r0 + 1
            goto L_0x00d8
        L_0x00f7:
            java.lang.Class<com.sun.jna.NativeMapped> r0 = com.sun.jna.NativeMapped.class
            boolean r0 = r0.isAssignableFrom(r12)
            if (r0 == 0) goto L_0x012d
            r0 = r11
            com.sun.jna.NativeMapped[] r0 = (com.sun.jna.NativeMapped[]) r0
            com.sun.jna.NativeMappedConverter r12 = com.sun.jna.NativeMappedConverter.getInstance(r12)
            java.lang.Class r2 = r12.nativeType()
            java.lang.Class r3 = r11.getClass()
            int r11 = com.sun.jna.Native.getNativeSize(r3, r11)
            int r3 = r0.length
            int r11 = r11 / r3
        L_0x0114:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x012c
            r3 = r0[r1]
            com.sun.jna.ToNativeContext r4 = new com.sun.jna.ToNativeContext
            r4.<init>()
            java.lang.Object r3 = r12.toNative(r3, r4)
            int r4 = r1 * r11
            long r4 = (long) r4
            long r4 = r4 + r9
            r8.setValue(r4, r3, r2)
            int r1 = r1 + 1
            goto L_0x0114
        L_0x012c:
            return
        L_0x012d:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Writing array of "
            java.lang.String r10 = " to memory not supported"
            java.lang.String r9 = androidx.constraintlayout.core.state.b.l(r9, r12, r10)
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Pointer.writeArray(long, java.lang.Object, java.lang.Class):void");
    }

    public void clear(long j2) {
        setMemory(0, j2, (byte) 0);
    }

    public String dump(long j2, int i3) {
        StringWriter stringWriter = new StringWriter(b.z(i3, 4, 4, (i3 * 2) + 13));
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("memory dump");
        for (int i4 = 0; i4 < i3; i4++) {
            byte b3 = getByte(((long) i4) + j2);
            int i5 = i4 % 4;
            if (i5 == 0) {
                printWriter.print("[");
            }
            if (b3 >= 0 && b3 < 16) {
                printWriter.print(SchemaSymbols.ATTVAL_FALSE_0);
            }
            printWriter.print(Integer.toHexString(b3 & 255));
            if (i5 == 3 && i4 < i3 - 1) {
                printWriter.println("]");
            }
        }
        if (stringWriter.getBuffer().charAt(stringWriter.getBuffer().length() - 2) != ']') {
            printWriter.println("]");
        }
        return stringWriter.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return (obj instanceof Pointer) && ((Pointer) obj).peer == this.peer;
    }

    public byte getByte(long j2) {
        return Native.getByte(this, this.peer, j2);
    }

    public byte[] getByteArray(long j2, int i3) {
        byte[] bArr = new byte[i3];
        read(j2, bArr, 0, i3);
        return bArr;
    }

    public ByteBuffer getByteBuffer(long j2, long j3) {
        return Native.getDirectByteBuffer(this, this.peer, j2, j3).order(ByteOrder.nativeOrder());
    }

    public char getChar(long j2) {
        return Native.getChar(this, this.peer, j2);
    }

    public char[] getCharArray(long j2, int i3) {
        char[] cArr = new char[i3];
        read(j2, cArr, 0, i3);
        return cArr;
    }

    public double getDouble(long j2) {
        return Native.getDouble(this, this.peer, j2);
    }

    public double[] getDoubleArray(long j2, int i3) {
        double[] dArr = new double[i3];
        read(j2, dArr, 0, i3);
        return dArr;
    }

    public float getFloat(long j2) {
        return Native.getFloat(this, this.peer, j2);
    }

    public float[] getFloatArray(long j2, int i3) {
        float[] fArr = new float[i3];
        read(j2, fArr, 0, i3);
        return fArr;
    }

    public int getInt(long j2) {
        return Native.getInt(this, this.peer, j2);
    }

    public int[] getIntArray(long j2, int i3) {
        int[] iArr = new int[i3];
        read(j2, iArr, 0, i3);
        return iArr;
    }

    public long getLong(long j2) {
        return Native.getLong(this, this.peer, j2);
    }

    public long[] getLongArray(long j2, int i3) {
        long[] jArr = new long[i3];
        read(j2, jArr, 0, i3);
        return jArr;
    }

    public NativeLong getNativeLong(long j2) {
        return new NativeLong(NativeLong.SIZE == 8 ? getLong(j2) : (long) getInt(j2));
    }

    public Pointer getPointer(long j2) {
        return Native.getPointer(this.peer + j2);
    }

    public Pointer[] getPointerArray(long j2) {
        ArrayList arrayList = new ArrayList();
        Pointer pointer = getPointer(j2);
        int i3 = 0;
        while (pointer != null) {
            arrayList.add(pointer);
            i3 += Native.POINTER_SIZE;
            pointer = getPointer(((long) i3) + j2);
        }
        return (Pointer[]) arrayList.toArray(new Pointer[0]);
    }

    public short getShort(long j2) {
        return Native.getShort(this, this.peer, j2);
    }

    public short[] getShortArray(long j2, int i3) {
        short[] sArr = new short[i3];
        read(j2, sArr, 0, i3);
        return sArr;
    }

    public String getString(long j2) {
        return getString(j2, Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long j2) {
        return getStringArray(j2, -1, Native.getDefaultStringEncoding());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v23, resolved type: com.sun.jna.Pointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v26, resolved type: com.sun.jna.Structure} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: com.sun.jna.Pointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v28, resolved type: com.sun.jna.Pointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v29, resolved type: com.sun.jna.Pointer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v30, resolved type: com.sun.jna.Pointer} */
    /* JADX WARNING: type inference failed for: r4v24 */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0095, code lost:
        if (r4.peer == r1.peer) goto L_0x0098;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getValue(long r5, java.lang.Class<?> r7, java.lang.Object r8) {
        /*
            r4 = this;
            java.lang.Class<com.sun.jna.Structure> r0 = com.sun.jna.Structure.class
            boolean r0 = r0.isAssignableFrom(r7)
            r1 = 1
            if (r0 == 0) goto L_0x0027
            com.sun.jna.Structure r8 = (com.sun.jna.Structure) r8
            java.lang.Class<com.sun.jna.Structure$ByReference> r0 = com.sun.jna.Structure.ByReference.class
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x001e
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            com.sun.jna.Structure r4 = com.sun.jna.Structure.updateStructureByReference(r7, r8, r4)
        L_0x001b:
            r8 = r4
            goto L_0x01c0
        L_0x001e:
            int r5 = (int) r5
            r8.useMemory(r4, r5, r1)
            r8.read()
            goto L_0x01c0
        L_0x0027:
            java.lang.Class r0 = java.lang.Boolean.TYPE
            if (r7 == r0) goto L_0x01b4
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            if (r7 != r0) goto L_0x0031
            goto L_0x01b4
        L_0x0031:
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r7 == r0) goto L_0x01ab
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            if (r7 != r0) goto L_0x003b
            goto L_0x01ab
        L_0x003b:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r7 == r0) goto L_0x01a2
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            if (r7 != r0) goto L_0x0045
            goto L_0x01a2
        L_0x0045:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r7 == r0) goto L_0x0199
            java.lang.Class<java.lang.Character> r0 = java.lang.Character.class
            if (r7 != r0) goto L_0x004f
            goto L_0x0199
        L_0x004f:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r7 == r0) goto L_0x0190
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            if (r7 != r0) goto L_0x0059
            goto L_0x0190
        L_0x0059:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r7 == r0) goto L_0x0187
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            if (r7 != r0) goto L_0x0063
            goto L_0x0187
        L_0x0063:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r7 == r0) goto L_0x017e
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            if (r7 != r0) goto L_0x006d
            goto L_0x017e
        L_0x006d:
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r7 == r0) goto L_0x0175
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            if (r7 != r0) goto L_0x0077
            goto L_0x0175
        L_0x0077:
            java.lang.Class<com.sun.jna.Pointer> r0 = com.sun.jna.Pointer.class
            boolean r0 = r0.isAssignableFrom(r7)
            r1 = 0
            if (r0 == 0) goto L_0x009b
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            if (r4 == 0) goto L_0x0098
            boolean r5 = r8 instanceof com.sun.jna.Pointer
            if (r5 == 0) goto L_0x008d
            r1 = r8
            com.sun.jna.Pointer r1 = (com.sun.jna.Pointer) r1
        L_0x008d:
            if (r1 == 0) goto L_0x001b
            long r5 = r4.peer
            long r7 = r1.peer
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0098
            goto L_0x001b
        L_0x0098:
            r8 = r1
            goto L_0x01c0
        L_0x009b:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            r2 = 0
            if (r7 != r0) goto L_0x00ad
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            if (r4 == 0) goto L_0x0098
            java.lang.String r4 = r4.getString(r2)
            goto L_0x001b
        L_0x00ad:
            java.lang.Class<com.sun.jna.WString> r0 = com.sun.jna.WString.class
            if (r7 != r0) goto L_0x00c3
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            if (r4 == 0) goto L_0x0098
            com.sun.jna.WString r5 = new com.sun.jna.WString
            java.lang.String r4 = r4.getWideString(r2)
            r5.<init>(r4)
            r8 = r5
            goto L_0x01c0
        L_0x00c3:
            java.lang.Class<com.sun.jna.Callback> r0 = com.sun.jna.Callback.class
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x00e4
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            if (r4 != 0) goto L_0x00d2
            goto L_0x0098
        L_0x00d2:
            com.sun.jna.Callback r8 = (com.sun.jna.Callback) r8
            com.sun.jna.Pointer r5 = com.sun.jna.CallbackReference.getFunctionPointer(r8)
            boolean r5 = r4.equals(r5)
            if (r5 != 0) goto L_0x01c0
            com.sun.jna.Callback r8 = com.sun.jna.CallbackReference.getCallback(r7, r4)
            goto L_0x01c0
        L_0x00e4:
            boolean r0 = com.sun.jna.Platform.HAS_BUFFERS
            if (r0 == 0) goto L_0x0113
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x0113
            com.sun.jna.Pointer r4 = r4.getPointer(r5)
            if (r4 != 0) goto L_0x00f7
            goto L_0x0098
        L_0x00f7:
            if (r8 != 0) goto L_0x00fa
            goto L_0x0101
        L_0x00fa:
            r5 = r8
            java.nio.Buffer r5 = (java.nio.Buffer) r5
            com.sun.jna.Pointer r1 = com.sun.jna.Native.getDirectBufferPointer(r5)
        L_0x0101:
            if (r1 == 0) goto L_0x010b
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x010b
            goto L_0x01c0
        L_0x010b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Can't autogenerate a direct buffer on memory read"
            r4.<init>(r5)
            throw r4
        L_0x0113:
            java.lang.Class<com.sun.jna.NativeMapped> r0 = com.sun.jna.NativeMapped.class
            boolean r0 = r0.isAssignableFrom(r7)
            if (r0 == 0) goto L_0x014f
            com.sun.jna.NativeMapped r8 = (com.sun.jna.NativeMapped) r8
            if (r8 == 0) goto L_0x0138
            java.lang.Class r0 = r8.nativeType()
            java.lang.Object r4 = r4.getValue(r5, r0, r1)
            com.sun.jna.FromNativeContext r5 = new com.sun.jna.FromNativeContext
            r5.<init>(r7)
            java.lang.Object r4 = r8.fromNative(r4, r5)
            boolean r5 = r8.equals(r4)
            if (r5 == 0) goto L_0x001b
            goto L_0x01c0
        L_0x0138:
            com.sun.jna.NativeMappedConverter r8 = com.sun.jna.NativeMappedConverter.getInstance(r7)
            java.lang.Class r0 = r8.nativeType()
            java.lang.Object r4 = r4.getValue(r5, r0, r1)
            com.sun.jna.FromNativeContext r5 = new com.sun.jna.FromNativeContext
            r5.<init>(r7)
            java.lang.Object r4 = r8.fromNative(r4, r5)
            goto L_0x001b
        L_0x014f:
            boolean r0 = r7.isArray()
            if (r0 == 0) goto L_0x0167
            if (r8 == 0) goto L_0x015f
            java.lang.Class r7 = r7.getComponentType()
            r4.readArray(r5, r8, r7)
            goto L_0x01c0
        L_0x015f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Need an initialized array"
            r4.<init>(r5)
            throw r4
        L_0x0167:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Reading \""
            java.lang.String r6 = "\" from memory is not supported"
            java.lang.String r5 = androidx.constraintlayout.core.state.b.l(r5, r7, r6)
            r4.<init>(r5)
            throw r4
        L_0x0175:
            double r4 = r4.getDouble(r5)
            java.lang.Double r8 = java.lang.Double.valueOf(r4)
            goto L_0x01c0
        L_0x017e:
            float r4 = r4.getFloat(r5)
            java.lang.Float r8 = java.lang.Float.valueOf(r4)
            goto L_0x01c0
        L_0x0187:
            long r4 = r4.getLong(r5)
            java.lang.Long r8 = java.lang.Long.valueOf(r4)
            goto L_0x01c0
        L_0x0190:
            int r4 = r4.getInt(r5)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            goto L_0x01c0
        L_0x0199:
            char r4 = r4.getChar(r5)
            java.lang.Character r8 = java.lang.Character.valueOf(r4)
            goto L_0x01c0
        L_0x01a2:
            short r4 = r4.getShort(r5)
            java.lang.Short r8 = java.lang.Short.valueOf(r4)
            goto L_0x01c0
        L_0x01ab:
            byte r4 = r4.getByte(r5)
            java.lang.Byte r8 = java.lang.Byte.valueOf(r4)
            goto L_0x01c0
        L_0x01b4:
            int r4 = r4.getInt(r5)
            if (r4 == 0) goto L_0x01bb
            goto L_0x01bc
        L_0x01bb:
            r1 = 0
        L_0x01bc:
            java.lang.Boolean r8 = com.sun.jna.Function.valueOf(r1)
        L_0x01c0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Pointer.getValue(long, java.lang.Class, java.lang.Object):java.lang.Object");
    }

    public String getWideString(long j2) {
        return Native.getWideString(this, this.peer, j2);
    }

    public String[] getWideStringArray(long j2) {
        return getWideStringArray(j2, -1);
    }

    public int hashCode() {
        long j2 = this.peer;
        return (int) ((j2 >>> 32) + (j2 & 4294967295L));
    }

    public long indexOf(long j2, byte b3) {
        return Native.indexOf(this, this.peer, j2, b3);
    }

    public void read(long j2, byte[] bArr, int i3, int i4) {
        Native.read(this, this.peer, j2, bArr, i3, i4);
    }

    public void setByte(long j2, byte b3) {
        Native.setByte(this, this.peer, j2, b3);
    }

    public void setChar(long j2, char c3) {
        Native.setChar(this, this.peer, j2, c3);
    }

    public void setDouble(long j2, double d2) {
        Native.setDouble(this, this.peer, j2, d2);
    }

    public void setFloat(long j2, float f2) {
        Native.setFloat(this, this.peer, j2, f2);
    }

    public void setInt(long j2, int i3) {
        Native.setInt(this, this.peer, j2, i3);
    }

    public void setLong(long j2, long j3) {
        Native.setLong(this, this.peer, j2, j3);
    }

    public void setMemory(long j2, long j3, byte b3) {
        Native.setMemory(this, this.peer, j2, j3, b3);
    }

    public void setNativeLong(long j2, NativeLong nativeLong) {
        if (NativeLong.SIZE == 8) {
            setLong(j2, nativeLong.longValue());
        } else {
            setInt(j2, nativeLong.intValue());
        }
    }

    public void setPointer(long j2, Pointer pointer) {
        Native.setPointer(this, this.peer, j2, pointer != null ? pointer.peer : 0);
    }

    public void setShort(long j2, short s3) {
        Native.setShort(this, this.peer, j2, s3);
    }

    public void setString(long j2, WString wString) {
        setWideString(j2, wString == null ? null : wString.toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: char} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(long r3, java.lang.Object r5, java.lang.Class<?> r6) {
        /*
            r2 = this;
            java.lang.Class r0 = java.lang.Boolean.TYPE
            r1 = 0
            if (r6 == r0) goto L_0x0168
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            if (r6 != r0) goto L_0x000b
            goto L_0x0168
        L_0x000b:
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r6 == r0) goto L_0x015b
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            if (r6 != r0) goto L_0x0015
            goto L_0x015b
        L_0x0015:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r6 == r0) goto L_0x014e
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            if (r6 != r0) goto L_0x001f
            goto L_0x014e
        L_0x001f:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r6 == r0) goto L_0x0141
            java.lang.Class<java.lang.Character> r0 = java.lang.Character.class
            if (r6 != r0) goto L_0x0029
            goto L_0x0141
        L_0x0029:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r6 == r0) goto L_0x0134
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            if (r6 != r0) goto L_0x0033
            goto L_0x0134
        L_0x0033:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r6 == r0) goto L_0x0125
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            if (r6 != r0) goto L_0x003d
            goto L_0x0125
        L_0x003d:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r6 == r0) goto L_0x0117
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            if (r6 != r0) goto L_0x0047
            goto L_0x0117
        L_0x0047:
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r6 == r0) goto L_0x0108
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            if (r6 != r0) goto L_0x0051
            goto L_0x0108
        L_0x0051:
            java.lang.Class<com.sun.jna.Pointer> r0 = com.sun.jna.Pointer.class
            if (r6 != r0) goto L_0x005c
            com.sun.jna.Pointer r5 = (com.sun.jna.Pointer) r5
            r2.setPointer(r3, r5)
            goto L_0x0174
        L_0x005c:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r6 != r0) goto L_0x0067
            com.sun.jna.Pointer r5 = (com.sun.jna.Pointer) r5
            r2.setPointer(r3, r5)
            goto L_0x0174
        L_0x0067:
            java.lang.Class<com.sun.jna.WString> r0 = com.sun.jna.WString.class
            if (r6 != r0) goto L_0x0072
            com.sun.jna.Pointer r5 = (com.sun.jna.Pointer) r5
            r2.setPointer(r3, r5)
            goto L_0x0174
        L_0x0072:
            java.lang.Class<com.sun.jna.Structure> r0 = com.sun.jna.Structure.class
            boolean r0 = r0.isAssignableFrom(r6)
            r1 = 0
            if (r0 == 0) goto L_0x00a0
            com.sun.jna.Structure r5 = (com.sun.jna.Structure) r5
            java.lang.Class<com.sun.jna.Structure$ByReference> r0 = com.sun.jna.Structure.ByReference.class
            boolean r6 = r0.isAssignableFrom(r6)
            if (r6 == 0) goto L_0x0096
            if (r5 != 0) goto L_0x0088
            goto L_0x008c
        L_0x0088:
            com.sun.jna.Pointer r1 = r5.getPointer()
        L_0x008c:
            r2.setPointer(r3, r1)
            if (r5 == 0) goto L_0x0174
            r5.autoWrite()
            goto L_0x0174
        L_0x0096:
            int r3 = (int) r3
            r4 = 1
            r5.useMemory(r2, r3, r4)
            r5.write()
            goto L_0x0174
        L_0x00a0:
            java.lang.Class<com.sun.jna.Callback> r0 = com.sun.jna.Callback.class
            boolean r0 = r0.isAssignableFrom(r6)
            if (r0 == 0) goto L_0x00b3
            com.sun.jna.Callback r5 = (com.sun.jna.Callback) r5
            com.sun.jna.Pointer r5 = com.sun.jna.CallbackReference.getFunctionPointer(r5)
            r2.setPointer(r3, r5)
            goto L_0x0174
        L_0x00b3:
            boolean r0 = com.sun.jna.Platform.HAS_BUFFERS
            if (r0 == 0) goto L_0x00cd
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            boolean r0 = r0.isAssignableFrom(r6)
            if (r0 == 0) goto L_0x00cd
            if (r5 != 0) goto L_0x00c2
            goto L_0x00c8
        L_0x00c2:
            java.nio.Buffer r5 = (java.nio.Buffer) r5
            com.sun.jna.Pointer r1 = com.sun.jna.Native.getDirectBufferPointer(r5)
        L_0x00c8:
            r2.setPointer(r3, r1)
            goto L_0x0174
        L_0x00cd:
            java.lang.Class<com.sun.jna.NativeMapped> r0 = com.sun.jna.NativeMapped.class
            boolean r0 = r0.isAssignableFrom(r6)
            if (r0 == 0) goto L_0x00eb
            com.sun.jna.NativeMappedConverter r6 = com.sun.jna.NativeMappedConverter.getInstance(r6)
            java.lang.Class r0 = r6.nativeType()
            com.sun.jna.ToNativeContext r1 = new com.sun.jna.ToNativeContext
            r1.<init>()
            java.lang.Object r5 = r6.toNative(r5, r1)
            r2.setValue(r3, r5, r0)
            goto L_0x0174
        L_0x00eb:
            boolean r0 = r6.isArray()
            if (r0 == 0) goto L_0x00fa
            java.lang.Class r6 = r6.getComponentType()
            r2.writeArray(r3, r5, r6)
            goto L_0x0174
        L_0x00fa:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Writing "
            java.lang.String r4 = " to memory is not supported"
            java.lang.String r3 = androidx.constraintlayout.core.state.b.l(r3, r6, r4)
            r2.<init>(r3)
            throw r2
        L_0x0108:
            if (r5 != 0) goto L_0x010d
            r5 = 0
            goto L_0x0113
        L_0x010d:
            java.lang.Double r5 = (java.lang.Double) r5
            double r5 = r5.doubleValue()
        L_0x0113:
            r2.setDouble(r3, r5)
            goto L_0x0174
        L_0x0117:
            if (r5 != 0) goto L_0x011b
            r5 = 0
            goto L_0x0121
        L_0x011b:
            java.lang.Float r5 = (java.lang.Float) r5
            float r5 = r5.floatValue()
        L_0x0121:
            r2.setFloat(r3, r5)
            goto L_0x0174
        L_0x0125:
            if (r5 != 0) goto L_0x012a
            r5 = 0
            goto L_0x0130
        L_0x012a:
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
        L_0x0130:
            r2.setLong(r3, r5)
            goto L_0x0174
        L_0x0134:
            if (r5 != 0) goto L_0x0137
            goto L_0x013d
        L_0x0137:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r1 = r5.intValue()
        L_0x013d:
            r2.setInt(r3, r1)
            goto L_0x0174
        L_0x0141:
            if (r5 != 0) goto L_0x0144
            goto L_0x014a
        L_0x0144:
            java.lang.Character r5 = (java.lang.Character) r5
            char r1 = r5.charValue()
        L_0x014a:
            r2.setChar(r3, r1)
            goto L_0x0174
        L_0x014e:
            if (r5 != 0) goto L_0x0151
            goto L_0x0157
        L_0x0151:
            java.lang.Short r5 = (java.lang.Short) r5
            short r1 = r5.shortValue()
        L_0x0157:
            r2.setShort(r3, r1)
            goto L_0x0174
        L_0x015b:
            if (r5 != 0) goto L_0x015e
            goto L_0x0164
        L_0x015e:
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r1 = r5.byteValue()
        L_0x0164:
            r2.setByte(r3, r1)
            goto L_0x0174
        L_0x0168:
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x0171
            r1 = -1
        L_0x0171:
            r2.setInt(r3, r1)
        L_0x0174:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Pointer.setValue(long, java.lang.Object, java.lang.Class):void");
    }

    public void setWideString(long j2, String str) {
        Native.setWideString(this, this.peer, j2, str);
    }

    public Pointer share(long j2) {
        return share(j2, 0);
    }

    public String toString() {
        return "native@0x" + Long.toHexString(this.peer);
    }

    public void write(long j2, byte[] bArr, int i3, int i4) {
        Native.write(this, this.peer, j2, bArr, i3, i4);
    }

    public Pointer(long j2) {
        this.peer = j2;
    }

    public static final Pointer createConstant(int i3) {
        return new Opaque(((long) i3) & 4294967295L);
    }

    public static void nativeValue(Pointer pointer, long j2) {
        pointer.peer = j2;
    }

    public String getString(long j2, String str) {
        return Native.getString(this, j2, str);
    }

    public String[] getStringArray(long j2, String str) {
        return getStringArray(j2, -1, str);
    }

    public String[] getWideStringArray(long j2, int i3) {
        return getStringArray(j2, i3, "--WIDE-STRING--");
    }

    public void read(long j2, short[] sArr, int i3, int i4) {
        Native.read(this, this.peer, j2, sArr, i3, i4);
    }

    public void setString(long j2, String str) {
        setString(j2, str, Native.getDefaultStringEncoding());
    }

    public Pointer share(long j2, long j3) {
        return j2 == 0 ? this : new Pointer(this.peer + j2);
    }

    public void write(long j2, short[] sArr, int i3, int i4) {
        Native.write(this, this.peer, j2, sArr, i3, i4);
    }

    public String[] getStringArray(long j2, int i3) {
        return getStringArray(j2, i3, Native.getDefaultStringEncoding());
    }

    public void read(long j2, char[] cArr, int i3, int i4) {
        Native.read(this, this.peer, j2, cArr, i3, i4);
    }

    public void setString(long j2, String str, String str2) {
        byte[] bytes = Native.getBytes(str, str2);
        write(j2, bytes, 0, bytes.length);
        setByte(j2 + ((long) bytes.length), (byte) 0);
    }

    public void write(long j2, char[] cArr, int i3, int i4) {
        Native.write(this, this.peer, j2, cArr, i3, i4);
    }

    public String[] getStringArray(long j2, int i3, String str) {
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        if (i3 == -1) {
            int i4 = 0;
            while (true) {
                Pointer pointer = getPointer(((long) i4) + j2);
                if (pointer == null) {
                    break;
                }
                if ("--WIDE-STRING--".equals(str)) {
                    str2 = pointer.getWideString(0);
                } else {
                    str2 = pointer.getString(0, str);
                }
                arrayList.add(str2);
                i4 += Native.POINTER_SIZE;
            }
        } else {
            Pointer pointer2 = getPointer(((long) 0) + j2);
            int i5 = 0;
            int i6 = 0;
            while (true) {
                int i7 = i5 + 1;
                if (i5 >= i3) {
                    break;
                }
                if (pointer2 == null) {
                    str3 = null;
                } else {
                    str3 = "--WIDE-STRING--".equals(str) ? pointer2.getWideString(0) : pointer2.getString(0, str);
                }
                arrayList.add(str3);
                if (i7 < i3) {
                    i6 += Native.POINTER_SIZE;
                    pointer2 = getPointer(((long) i6) + j2);
                }
                i5 = i7;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void read(long j2, int[] iArr, int i3, int i4) {
        Native.read(this, this.peer, j2, iArr, i3, i4);
    }

    public void write(long j2, int[] iArr, int i3, int i4) {
        Native.write(this, this.peer, j2, iArr, i3, i4);
    }

    public void read(long j2, long[] jArr, int i3, int i4) {
        Native.read(this, this.peer, j2, jArr, i3, i4);
    }

    public void write(long j2, long[] jArr, int i3, int i4) {
        Native.write(this, this.peer, j2, jArr, i3, i4);
    }

    public void read(long j2, float[] fArr, int i3, int i4) {
        Native.read(this, this.peer, j2, fArr, i3, i4);
    }

    public void write(long j2, float[] fArr, int i3, int i4) {
        Native.write(this, this.peer, j2, fArr, i3, i4);
    }

    public Pointer[] getPointerArray(long j2, int i3) {
        Pointer[] pointerArr = new Pointer[i3];
        read(j2, pointerArr, 0, i3);
        return pointerArr;
    }

    public void read(long j2, double[] dArr, int i3, int i4) {
        Native.read(this, this.peer, j2, dArr, i3, i4);
    }

    public void write(long j2, double[] dArr, int i3, int i4) {
        Native.write(this, this.peer, j2, dArr, i3, i4);
    }

    public void read(long j2, Pointer[] pointerArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            Pointer pointer = getPointer(((long) (Native.POINTER_SIZE * i5)) + j2);
            int i6 = i5 + i3;
            Pointer pointer2 = pointerArr[i6];
            if (pointer2 == null || pointer == null || pointer.peer != pointer2.peer) {
                pointerArr[i6] = pointer;
            }
        }
    }

    public void write(long j2, Pointer[] pointerArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            setPointer(((long) (Native.POINTER_SIZE * i5)) + j2, pointerArr[i3 + i5]);
        }
    }
}
