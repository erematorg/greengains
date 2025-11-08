package com.sun.jna;

import androidx.compose.ui.autofill.a;
import com.sun.jna.internal.Cleaner;
import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memory extends Pointer implements Closeable {
    /* access modifiers changed from: private */
    public static final Map<Long, Reference<Memory>> allocatedMemory = new ConcurrentHashMap();
    private static final WeakMemoryHolder buffers = new WeakMemoryHolder();
    private final Cleaner.Cleanable cleanable;
    protected long size;

    public static final class MemoryDisposer implements Runnable {
        private long peer;

        public MemoryDisposer(long j2) {
            this.peer = j2;
        }

        public synchronized void run() {
            try {
                Memory.free(this.peer);
                Memory.allocatedMemory.remove(Long.valueOf(this.peer));
                this.peer = 0;
            } catch (Throwable th) {
                Memory.allocatedMemory.remove(Long.valueOf(this.peer));
                this.peer = 0;
                throw th;
            }
        }
    }

    public class SharedMemory extends Memory {
        public SharedMemory(long j2, long j3) {
            this.size = j3;
            this.peer = Memory.this.peer + j2;
        }

        public void boundsCheck(long j2, long j3) {
            Memory memory = Memory.this;
            memory.boundsCheck((this.peer - memory.peer) + j2, j3);
        }

        public synchronized void dispose() {
            this.peer = 0;
        }

        public String toString() {
            return Memory.super.toString() + " (shared from " + Memory.this.toString() + ")";
        }
    }

    public Memory(long j2) {
        this.size = j2;
        if (j2 > 0) {
            long malloc = malloc(j2);
            this.peer = malloc;
            if (malloc != 0) {
                allocatedMemory.put(Long.valueOf(malloc), new WeakReference(this));
                this.cleanable = Cleaner.getCleaner().register(this, new MemoryDisposer(this.peer));
                return;
            }
            throw new OutOfMemoryError(a.j("Cannot allocate ", j2, " bytes"));
        }
        throw new IllegalArgumentException("Allocation size must be greater than zero");
    }

    public static void disposeAll() {
        Iterator it = new ArrayList(allocatedMemory.values()).iterator();
        while (it.hasNext()) {
            Memory memory = (Memory) ((Reference) it.next()).get();
            if (memory != null) {
                memory.close();
            }
        }
    }

    public static void free(long j2) {
        if (j2 != 0) {
            Native.free(j2);
        }
    }

    public static long malloc(long j2) {
        return Native.malloc(j2);
    }

    public static void purge() {
        buffers.clean();
    }

    private Pointer shareReferenceIfInBounds(Pointer pointer) {
        if (pointer == null) {
            return null;
        }
        long j2 = pointer.peer - this.peer;
        return (j2 < 0 || j2 >= this.size) ? pointer : share(j2);
    }

    public Memory align(int i3) {
        if (i3 > 0) {
            for (int i4 = 0; i4 < 32; i4++) {
                if (i3 == (1 << i4)) {
                    long j2 = (long) i3;
                    long j3 = ~(j2 - 1);
                    long j4 = this.peer;
                    if ((j4 & j3) == j4) {
                        return this;
                    }
                    long j5 = ((j2 + j4) - 1) & j3;
                    long j6 = (this.size + j4) - j5;
                    if (j6 > 0) {
                        return (Memory) share(j5 - j4, j6);
                    }
                    throw new IllegalArgumentException("Insufficient memory to align to the requested boundary");
                }
            }
            throw new IllegalArgumentException("Byte boundary must be a power of two");
        }
        throw new IllegalArgumentException(A.a.k("Byte boundary must be positive: ", i3));
    }

    public void boundsCheck(long j2, long j3) {
        if (j2 >= 0) {
            long j4 = j2 + j3;
            if (j4 > this.size) {
                throw new IndexOutOfBoundsException("Bounds exceeds available space : size=" + this.size + ", offset=" + j4);
            }
            return;
        }
        throw new IndexOutOfBoundsException(androidx.compose.animation.core.a.s("Invalid offset: ", j2));
    }

    public void clear() {
        clear(this.size);
    }

    public void close() {
        this.peer = 0;
        Cleaner.Cleanable cleanable2 = this.cleanable;
        if (cleanable2 != null) {
            cleanable2.clean();
        }
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public String dump() {
        return dump(0, (int) size());
    }

    public byte getByte(long j2) {
        boundsCheck(j2, 1);
        return super.getByte(j2);
    }

    public ByteBuffer getByteBuffer(long j2, long j3) {
        boundsCheck(j2, j3);
        ByteBuffer byteBuffer = super.getByteBuffer(j2, j3);
        buffers.put(byteBuffer, this);
        return byteBuffer;
    }

    public char getChar(long j2) {
        boundsCheck(j2, (long) Native.WCHAR_SIZE);
        return super.getChar(j2);
    }

    public double getDouble(long j2) {
        boundsCheck(j2, 8);
        return super.getDouble(j2);
    }

    public float getFloat(long j2) {
        boundsCheck(j2, 4);
        return super.getFloat(j2);
    }

    public int getInt(long j2) {
        boundsCheck(j2, 4);
        return super.getInt(j2);
    }

    public long getLong(long j2) {
        boundsCheck(j2, 8);
        return super.getLong(j2);
    }

    public Pointer getPointer(long j2) {
        boundsCheck(j2, (long) Native.POINTER_SIZE);
        return shareReferenceIfInBounds(super.getPointer(j2));
    }

    public short getShort(long j2) {
        boundsCheck(j2, 2);
        return super.getShort(j2);
    }

    public String getString(long j2, String str) {
        boundsCheck(j2, 0);
        return super.getString(j2, str);
    }

    public String getWideString(long j2) {
        boundsCheck(j2, 0);
        return super.getWideString(j2);
    }

    public void read(long j2, byte[] bArr, int i3, int i4) {
        boundsCheck(j2, (long) i4);
        super.read(j2, bArr, i3, i4);
    }

    public void setByte(long j2, byte b3) {
        boundsCheck(j2, 1);
        super.setByte(j2, b3);
    }

    public void setChar(long j2, char c3) {
        boundsCheck(j2, (long) Native.WCHAR_SIZE);
        super.setChar(j2, c3);
    }

    public void setDouble(long j2, double d2) {
        boundsCheck(j2, 8);
        super.setDouble(j2, d2);
    }

    public void setFloat(long j2, float f2) {
        boundsCheck(j2, 4);
        super.setFloat(j2, f2);
    }

    public void setInt(long j2, int i3) {
        boundsCheck(j2, 4);
        super.setInt(j2, i3);
    }

    public void setLong(long j2, long j3) {
        boundsCheck(j2, 8);
        super.setLong(j2, j3);
    }

    public void setPointer(long j2, Pointer pointer) {
        boundsCheck(j2, (long) Native.POINTER_SIZE);
        super.setPointer(j2, pointer);
    }

    public void setShort(long j2, short s3) {
        boundsCheck(j2, 2);
        super.setShort(j2, s3);
    }

    public void setString(long j2, String str, String str2) {
        boundsCheck(j2, ((long) Native.getBytes(str, str2).length) + 1);
        super.setString(j2, str, str2);
    }

    public void setWideString(long j2, String str) {
        boundsCheck(j2, (((long) str.length()) + 1) * ((long) Native.WCHAR_SIZE));
        super.setWideString(j2, str);
    }

    public Pointer share(long j2) {
        return share(j2, size() - j2);
    }

    public long size() {
        return this.size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("allocated@0x");
        sb.append(Long.toHexString(this.peer));
        sb.append(" (");
        return android.support.v4.media.session.a.k(this.size, " bytes)", sb);
    }

    public boolean valid() {
        return this.peer != 0;
    }

    public void write(long j2, byte[] bArr, int i3, int i4) {
        boundsCheck(j2, (long) i4);
        super.write(j2, bArr, i3, i4);
    }

    public Pointer share(long j2, long j3) {
        boundsCheck(j2, j3);
        return new SharedMemory(j2, j3);
    }

    public void read(long j2, short[] sArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 2);
        super.read(j2, sArr, i3, i4);
    }

    public void write(long j2, short[] sArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 2);
        super.write(j2, sArr, i3, i4);
    }

    public void read(long j2, char[] cArr, int i3, int i4) {
        boundsCheck(j2, (long) (Native.WCHAR_SIZE * i4));
        super.read(j2, cArr, i3, i4);
    }

    public void write(long j2, char[] cArr, int i3, int i4) {
        boundsCheck(j2, (long) (Native.WCHAR_SIZE * i4));
        super.write(j2, cArr, i3, i4);
    }

    public void read(long j2, int[] iArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 4);
        super.read(j2, iArr, i3, i4);
    }

    public void write(long j2, int[] iArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 4);
        super.write(j2, iArr, i3, i4);
    }

    public void read(long j2, long[] jArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 8);
        super.read(j2, jArr, i3, i4);
    }

    public void write(long j2, long[] jArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 8);
        super.write(j2, jArr, i3, i4);
    }

    public void read(long j2, float[] fArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 4);
        super.read(j2, fArr, i3, i4);
    }

    public void write(long j2, float[] fArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 4);
        super.write(j2, fArr, i3, i4);
    }

    public void read(long j2, double[] dArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 8);
        super.read(j2, dArr, i3, i4);
    }

    public void write(long j2, double[] dArr, int i3, int i4) {
        boundsCheck(j2, ((long) i4) * 8);
        super.write(j2, dArr, i3, i4);
    }

    public Memory() {
        this.cleanable = null;
    }

    public void read(long j2, Pointer[] pointerArr, int i3, int i4) {
        boundsCheck(j2, (long) (Native.POINTER_SIZE * i4));
        super.read(j2, pointerArr, i3, i4);
    }

    public void write(long j2, Pointer[] pointerArr, int i3, int i4) {
        boundsCheck(j2, (long) (Native.POINTER_SIZE * i4));
        super.write(j2, pointerArr, i3, i4);
    }
}
