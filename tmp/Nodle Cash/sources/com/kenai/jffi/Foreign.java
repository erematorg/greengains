package com.kenai.jffi;

import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

final class Foreign {
    public static final int F_DEFAULT = 0;
    public static final int F_NOERRNO = 2;
    public static final int F_PROTECT = 4;
    public static final int F_STDCALL = 1;
    public static final int JNI_EDETACHED = -2;
    public static final int JNI_EEXIST = -5;
    public static final int JNI_EINVAL = -6;
    public static final int JNI_ENOMEM = -4;
    public static final int JNI_ERR = -1;
    public static final int JNI_EVERSION = -3;
    public static final int JNI_OK = 0;
    public static final int MAP_ALIGN = 512;
    public static final int MAP_ANON = 256;
    public static final int MAP_FIXED = 16;
    public static final int MAP_NORESERVE = 64;
    public static final int MAP_PRIVATE = 2;
    public static final int MAP_SHARED = 1;
    public static final int MAP_TEXT = 1024;
    public static final int MEM_4MB_PAGES = Integer.MIN_VALUE;
    public static final int MEM_COMMIT = 4096;
    public static final int MEM_DECOMMIT = 16384;
    public static final int MEM_FREE = 65536;
    public static final int MEM_MAPPED = 262144;
    public static final int MEM_PHYSICAL = 4194304;
    public static final int MEM_PRIVATE = 131072;
    public static final int MEM_RELEASE = 32768;
    public static final int MEM_RESERVE = 8192;
    public static final int MEM_RESET = 524288;
    public static final int MEM_TOP_DOWN = 1048576;
    public static final int PAGE_EXECUTE = 16;
    public static final int PAGE_EXECUTE_READ = 32;
    public static final int PAGE_EXECUTE_READWRITE = 64;
    public static final int PAGE_EXECUTE_WRITECOPY = 128;
    public static final int PAGE_NOACCESS = 1;
    public static final int PAGE_READONLY = 2;
    public static final int PAGE_READWRITE = 4;
    public static final int PAGE_WRITECOPY = 8;
    public static final int PROT_EXEC = 4;
    public static final int PROT_NONE = 0;
    public static final int PROT_READ = 1;
    public static final int PROT_WRITE = 2;
    public static final int RTLD_GLOBAL = 8;
    public static final int RTLD_LAZY = 1;
    public static final int RTLD_LOCAL = 4;
    public static final int RTLD_NOW = 2;
    public static final int TYPE_DOUBLE = 3;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_LONGDOUBLE = 4;
    public static final int TYPE_POINTER = 14;
    public static final int TYPE_SCHAR = 102;
    public static final int TYPE_SINT = 106;
    public static final int TYPE_SINT16 = 8;
    public static final int TYPE_SINT32 = 10;
    public static final int TYPE_SINT64 = 12;
    public static final int TYPE_SINT8 = 6;
    public static final int TYPE_SLONG = 108;
    public static final int TYPE_SSHORT = 104;
    public static final int TYPE_STRUCT = 13;
    public static final int TYPE_UCHAR = 101;
    public static final int TYPE_UINT = 105;
    public static final int TYPE_UINT16 = 7;
    public static final int TYPE_UINT32 = 9;
    public static final int TYPE_UINT64 = 11;
    public static final int TYPE_UINT8 = 5;
    public static final int TYPE_ULONG = 107;
    public static final int TYPE_USHORT = 103;
    public static final int TYPE_VOID = 0;
    public static final int VERSION_MAJOR = getVersionField("MAJOR");
    public static final int VERSION_MICRO = getVersionField("MICRO");
    public static final int VERSION_MINOR = getVersionField("MINOR");

    public static final class InValidInstanceHolder extends InstanceHolder {
        private final Throwable cause;

        public InValidInstanceHolder(Throwable th) {
            super();
            this.cause = th;
        }

        public final Foreign getForeign() {
            throw Foreign.newLoadError(this.cause);
        }
    }

    public static abstract class InstanceHolder {
        static final InstanceHolder INSTANCE = getInstanceHolder();

        private InstanceHolder() {
        }

        private static InstanceHolder getInstanceHolder() {
            try {
                Init.load();
                Foreign foreign = new Foreign();
                int version = foreign.getVersion() & 16776960;
                int i3 = Foreign.VERSION_MAJOR;
                int i4 = Foreign.VERSION_MINOR;
                if (version != ((i3 << 16) | (i4 << 8))) {
                    return new InValidInstanceHolder(new UnsatisfiedLinkError(String.format("incorrect native library version %d.%d, expected %d.%d", new Object[]{Integer.valueOf((foreign.getVersion() >> 16) & 255), Integer.valueOf((foreign.getVersion() >> 8) & 255), Integer.valueOf(i3), Integer.valueOf(i4)})));
                }
                foreign.init();
                return new ValidInstanceHolder(foreign);
            } catch (Throwable th) {
                return new InValidInstanceHolder(th);
            }
        }

        public abstract Foreign getForeign();
    }

    public static final class ValidInstanceHolder extends InstanceHolder {
        final Foreign foreign;

        public ValidInstanceHolder(Foreign foreign2) {
            super();
            this.foreign = foreign2;
        }

        public final Foreign getForeign() {
            return this.foreign;
        }
    }

    public static native long VirtualAlloc(long j2, int i3, int i4, int i5);

    public static native boolean VirtualFree(long j2, int i3, int i4);

    public static native boolean VirtualProtect(long j2, int i3, int i4);

    public static native long allocateMemory(long j2, boolean z2);

    public static native void copyMemory(long j2, long j3, long j4);

    public static native void copyMemoryChecked(long j2, long j3, long j4);

    public static native void dlclose(long j2);

    public static native String dlerror();

    public static native long dlopen(String str, int i3);

    public static native long dlsym(long j2, String str);

    public static native void freeMemory(long j2);

    public static native long getAddress(long j2);

    public static native long getAddressChecked(long j2);

    public static native byte getByte(long j2);

    public static native void getByteArray(long j2, byte[] bArr, int i3, int i4);

    public static native void getByteArrayChecked(long j2, byte[] bArr, int i3, int i4);

    public static native byte getByteChecked(long j2);

    public static native void getCharArray(long j2, char[] cArr, int i3, int i4);

    public static native void getCharArrayChecked(long j2, char[] cArr, int i3, int i4);

    public static native double getDouble(long j2);

    public static native void getDoubleArray(long j2, double[] dArr, int i3, int i4);

    public static native void getDoubleArrayChecked(long j2, double[] dArr, int i3, int i4);

    public static native double getDoubleChecked(long j2);

    public static native float getFloat(long j2);

    public static native void getFloatArray(long j2, float[] fArr, int i3, int i4);

    public static native void getFloatArrayChecked(long j2, float[] fArr, int i3, int i4);

    public static native float getFloatChecked(long j2);

    public static Foreign getInstance() {
        return InstanceHolder.INSTANCE.getForeign();
    }

    public static native int getInt(long j2);

    public static native void getIntArray(long j2, int[] iArr, int i3, int i4);

    public static native void getIntArrayChecked(long j2, int[] iArr, int i3, int i4);

    public static native int getIntChecked(long j2);

    public static native int getLastError();

    public static native long getLong(long j2);

    public static native void getLongArray(long j2, long[] jArr, int i3, int i4);

    public static native void getLongArrayChecked(long j2, long[] jArr, int i3, int i4);

    public static native long getLongChecked(long j2);

    public static native short getShort(long j2);

    public static native void getShortArray(long j2, short[] sArr, int i3, int i4);

    public static native void getShortArrayChecked(long j2, short[] sArr, int i3, int i4);

    public static native short getShortChecked(long j2);

    private static int getVersionField(String str) {
        try {
            Class<?> cls = Class.forName(Foreign.class.getPackage().getName() + ".Version");
            return ((Integer) cls.getField(str).get(cls)).intValue();
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public static native byte[] getZeroTerminatedByteArray(long j2);

    public static native byte[] getZeroTerminatedByteArray(long j2, int i3);

    public static native byte[] getZeroTerminatedByteArrayChecked(long j2);

    public static native byte[] getZeroTerminatedByteArrayChecked(long j2, int i3);

    /* access modifiers changed from: private */
    public native void init();

    public static native int invokeArrayO1Int32(long j2, long j3, byte[] bArr, Object obj, int i3, int i4, int i5);

    public static native long invokeArrayO1Int64(long j2, long j3, byte[] bArr, Object obj, int i3, int i4, int i5);

    public static native int invokeArrayO2Int32(long j2, long j3, byte[] bArr, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeArrayO2Int64(long j2, long j3, byte[] bArr, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native double invokeArrayReturnDouble(long j2, long j3, byte[] bArr);

    public static native float invokeArrayReturnFloat(long j2, long j3, byte[] bArr);

    public static native int invokeArrayReturnInt(long j2, long j3, byte[] bArr);

    public static native long invokeArrayReturnLong(long j2, long j3, byte[] bArr);

    public static native void invokeArrayReturnStruct(long j2, long j3, byte[] bArr, byte[] bArr2, int i3);

    public static native double invokeArrayWithObjectsDouble(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr);

    public static native float invokeArrayWithObjectsFloat(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr);

    public static native int invokeArrayWithObjectsInt32(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr);

    public static native long invokeArrayWithObjectsInt64(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr);

    public static native Object invokeArrayWithObjectsReturnObject(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr);

    public static native void invokeArrayWithObjectsReturnStruct(long j2, long j3, byte[] bArr, int i3, int[] iArr, Object[] objArr, byte[] bArr2, int i4);

    public static native int invokeI0(long j2, long j3);

    public static native int invokeI0NoErrno(long j2, long j3);

    public static native int invokeI1(long j2, long j3, int i3);

    public static native int invokeI1NoErrno(long j2, long j3, int i3);

    public static native int invokeI2(long j2, long j3, int i3, int i4);

    public static native int invokeI2NoErrno(long j2, long j3, int i3, int i4);

    public static native int invokeI3(long j2, long j3, int i3, int i4, int i5);

    public static native int invokeI3NoErrno(long j2, long j3, int i3, int i4, int i5);

    public static native int invokeI4(long j2, long j3, int i3, int i4, int i5, int i6);

    public static native int invokeI4NoErrno(long j2, long j3, int i3, int i4, int i5, int i6);

    public static native int invokeI5(long j2, long j3, int i3, int i4, int i5, int i6, int i7);

    public static native int invokeI5NoErrno(long j2, long j3, int i3, int i4, int i5, int i6, int i7);

    public static native int invokeI6(long j2, long j3, int i3, int i4, int i5, int i6, int i7, int i8);

    public static native int invokeI6NoErrno(long j2, long j3, int i3, int i4, int i5, int i6, int i7, int i8);

    public static native long invokeL0(long j2, long j3);

    public static native long invokeL0NoErrno(long j2, long j3);

    public static native long invokeL1(long j2, long j3, long j4);

    public static native long invokeL1NoErrno(long j2, long j3, long j4);

    public static native long invokeL2(long j2, long j3, long j4, long j5);

    public static native long invokeL2NoErrno(long j2, long j3, long j4, long j5);

    public static native long invokeL3(long j2, long j3, long j4, long j5, long j6);

    public static native long invokeL3NoErrno(long j2, long j3, long j4, long j5, long j6);

    public static native long invokeL4(long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokeL4NoErrno(long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokeL5(long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native long invokeL5NoErrno(long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native long invokeL6(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long invokeL6NoErrno(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long invokeN0(long j2, long j3);

    public static native long invokeN1(long j2, long j3, long j4);

    public static native long invokeN1O1(long j2, long j3, long j4, Object obj, int i3, int i4, int i5);

    public static native long invokeN2(long j2, long j3, long j4, long j5);

    public static native long invokeN2O1(long j2, long j3, long j4, long j5, Object obj, int i3, int i4, int i5);

    public static native long invokeN2O2(long j2, long j3, long j4, long j5, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeN3(long j2, long j3, long j4, long j5, long j6);

    public static native long invokeN3O1(long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, int i5);

    public static native long invokeN3O2(long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeN3O3(long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11);

    public static native long invokeN4(long j2, long j3, long j4, long j5, long j6, long j7);

    public static native long invokeN4O1(long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, int i5);

    public static native long invokeN4O2(long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeN4O3(long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11);

    public static native long invokeN4O4(long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14);

    public static native long invokeN5(long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static native long invokeN5O1(long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, int i3, int i4, int i5);

    public static native long invokeN5O2(long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeN5O3(long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11);

    public static native long invokeN5O4(long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14);

    public static native long invokeN5O5(long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14, Object obj5, int i15, int i16, int i17);

    public static native long invokeN6(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static native long invokeN6O1(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5);

    public static native long invokeN6O2(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8);

    public static native long invokeN6O3(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11);

    public static native long invokeN6O4(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14);

    public static native long invokeN6O5(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14, Object obj5, int i15, int i16, int i17);

    public static native long invokeN6O6(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, Object obj, int i3, int i4, int i5, Object obj2, int i6, int i7, int i8, Object obj3, int i9, int i10, int i11, Object obj4, int i12, int i13, int i14, Object obj5, int i15, int i16, int i17, Object obj6, int i18, int i19, int i20);

    public static native void invokePointerParameterArray(long j2, long j3, long j4, long[] jArr);

    private static native boolean isFaultProtectionEnabled();

    public static boolean isMemoryProtectionEnabled() {
        try {
            return isFaultProtectionEnabled();
        } catch (UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public static native long memchr(long j2, int i3, long j3);

    public static native long memchrChecked(long j2, int i3, long j3);

    public static native void memcpy(long j2, long j3, long j4);

    public static native void memcpyChecked(long j2, long j3, long j4);

    public static native void memmove(long j2, long j3, long j4);

    public static native void memmoveChecked(long j2, long j3, long j4);

    public static native long mmap(long j2, long j3, int i3, int i4, int i5, long j4);

    public static native int mprotect(long j2, long j3, int i3);

    public static native int munmap(long j2, long j3);

    /* access modifiers changed from: private */
    public static UnsatisfiedLinkError newLoadError(Throwable th) {
        UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(th.getMessage());
        unsatisfiedLinkError.initCause(th);
        return unsatisfiedLinkError;
    }

    public static native long pageSize();

    public static native void putAddress(long j2, long j3);

    public static native void putAddressChecked(long j2, long j3);

    public static native void putByte(long j2, byte b3);

    public static native void putByteArray(long j2, byte[] bArr, int i3, int i4);

    public static native void putByteArrayChecked(long j2, byte[] bArr, int i3, int i4);

    public static native void putByteChecked(long j2, byte b3);

    public static native void putCharArray(long j2, char[] cArr, int i3, int i4);

    public static native void putCharArrayChecked(long j2, char[] cArr, int i3, int i4);

    public static native void putDouble(long j2, double d2);

    public static native void putDoubleArray(long j2, double[] dArr, int i3, int i4);

    public static native void putDoubleArrayChecked(long j2, double[] dArr, int i3, int i4);

    public static native void putDoubleChecked(long j2, double d2);

    public static native void putFloat(long j2, float f2);

    public static native void putFloatArray(long j2, float[] fArr, int i3, int i4);

    public static native void putFloatArrayChecked(long j2, float[] fArr, int i3, int i4);

    public static native void putFloatChecked(long j2, float f2);

    public static native void putInt(long j2, int i3);

    public static native void putIntArray(long j2, int[] iArr, int i3, int i4);

    public static native void putIntArrayChecked(long j2, int[] iArr, int i3, int i4);

    public static native void putIntChecked(long j2, int i3);

    public static native void putLong(long j2, long j3);

    public static native void putLongArray(long j2, long[] jArr, int i3, int i4);

    public static native void putLongArrayChecked(long j2, long[] jArr, int i3, int i4);

    public static native void putLongChecked(long j2, long j3);

    public static native void putShort(long j2, short s3);

    public static native void putShortArray(long j2, short[] sArr, int i3, int i4);

    public static native void putShortArrayChecked(long j2, short[] sArr, int i3, int i4);

    public static native void putShortChecked(long j2, short s3);

    public static native void putZeroTerminatedByteArray(long j2, byte[] bArr, int i3, int i4);

    public static native void putZeroTerminatedByteArrayChecked(long j2, byte[] bArr, int i3, int i4);

    public static native void setLastError(int i3);

    public static native void setMemory(long j2, long j3, byte b3);

    public static native void setMemoryChecked(long j2, long j3, byte b3);

    public static native long strlen(long j2);

    public static native long strlenChecked(long j2);

    public final native Object allocObject(Class cls);

    public final native long closureMagazineGet(long j2, Object obj);

    public final native long compileNativeMethods(long[] jArr);

    public final native Class defineClass(String str, Object obj, ByteBuffer byteBuffer);

    public final native Class defineClass(String str, Object obj, byte[] bArr, int i3, int i4);

    public final native void fatalError(String str);

    public final native void freeAggregate(long j2);

    public final native void freeCallContext(long j2);

    public final native void freeClosureMagazine(long j2);

    public final native void freeCompiledMethods(long j2);

    public final native void freeNativeMethod(long j2);

    public final native String getArch();

    public final native int getCallContextRawParameterSize(long j2);

    public final native long getDirectBufferAddress(Buffer buffer);

    public final native int getJNIVersion();

    public final native long getJavaVM();

    public final native long getSaveErrnoCtxFunction();

    public final native long getSaveErrnoFunction();

    public final native int getTypeAlign(long j2);

    public final native int getTypeSize(long j2);

    public final native int getTypeType(long j2);

    public final native int getVersion();

    public final native boolean isRawParameterPackingEnabled();

    public final native void longDoubleFromDouble(double d2, byte[] bArr, int i3, int i4);

    public final native void longDoubleFromString(String str, byte[] bArr, int i3, int i4);

    public final native double longDoubleToDouble(byte[] bArr, int i3, int i4);

    public final native String longDoubleToEngineeringString(byte[] bArr, int i3, int i4);

    public final native String longDoubleToPlainString(byte[] bArr, int i3, int i4);

    public final native String longDoubleToString(byte[] bArr, int i3, int i4);

    public final native long lookupBuiltinType(int i3);

    public final native long newArray(long j2, int i3);

    public final native long newCallContext(long j2, long[] jArr, int i3);

    public final native long newClosureMagazine(long j2, Method method, boolean z2);

    public final native ByteBuffer newDirectByteBuffer(long j2, int i3);

    public final native long newNativeMethod(String str, String str2, long j2);

    public final native long newStruct(long[] jArr, boolean z2);

    public final native boolean registerNativeMethods(Class cls, long j2);

    public final native int registerNatives(Class cls, long j2, int i3);

    public final native void setCallContextErrorFunction(long j2, long j3);

    public final native void unregisterNativeMethods(Class cls);

    public final native int unregisterNatives(Class cls);

    private Foreign() {
    }
}
