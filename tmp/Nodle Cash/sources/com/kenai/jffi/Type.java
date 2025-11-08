package com.kenai.jffi;

import java.util.List;

public abstract class Type {
    public static final Type DOUBLE = builtin(NativeType.DOUBLE);
    public static final Type FLOAT = builtin(NativeType.FLOAT);
    public static final Type LONGDOUBLE = builtin(NativeType.LONGDOUBLE);
    public static final Type POINTER = builtin(NativeType.POINTER);
    public static final Type SCHAR;
    public static final Type SINT;
    public static final Type SINT16;
    public static final Type SINT32;
    public static final Type SINT64;
    public static final Type SINT8;
    public static final Type SLONG = builtin(NativeType.SLONG);
    public static final Type SLONG_LONG;
    public static final Type SSHORT;
    public static final Type UCHAR;
    public static final Type UINT;
    public static final Type UINT16;
    public static final Type UINT32;
    public static final Type UINT64;
    public static final Type UINT8;
    public static final Type ULONG = builtin(NativeType.ULONG);
    public static final Type ULONG_LONG;
    public static final Type USHORT;
    public static final Type VOID = builtin(NativeType.VOID);
    private int alignment = 0;
    private volatile long handle = 0;
    private int size = 0;
    private int type = 0;

    public static final class Builtin extends Type {
        private final NativeType nativeType;
        private TypeInfo typeInfo;

        private TypeInfo lookupTypeInfo() {
            try {
                Foreign instance = Foreign.getInstance();
                long lookupBuiltinType = instance.lookupBuiltinType(this.nativeType.ffiType);
                if (lookupBuiltinType != 0) {
                    TypeInfo typeInfo2 = new TypeInfo(lookupBuiltinType, instance.getTypeType(lookupBuiltinType), instance.getTypeSize(lookupBuiltinType), instance.getTypeAlign(lookupBuiltinType));
                    this.typeInfo = typeInfo2;
                    return typeInfo2;
                }
                throw new NullPointerException("invalid handle for native type " + this.nativeType);
            } catch (Throwable th) {
                throw new UnsatisfiedLinkError("could not get native definition for type `" + this.nativeType + "`, original error message follows: " + th.getLocalizedMessage());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && Builtin.class == obj.getClass() && Type.super.equals(obj) && this.nativeType == ((Builtin) obj).nativeType;
        }

        public TypeInfo getTypeInfo() {
            TypeInfo typeInfo2 = this.typeInfo;
            return typeInfo2 != null ? typeInfo2 : lookupTypeInfo();
        }

        public int hashCode() {
            return this.nativeType.hashCode() + (Type.super.hashCode() * 31);
        }

        private Builtin(NativeType nativeType2) {
            this.nativeType = nativeType2;
        }
    }

    public static final class TypeInfo {
        final int alignment;
        final long handle;
        final int size;
        final int type;

        public TypeInfo(long j2, int i3, int i4, int i5) {
            this.handle = j2;
            this.type = i3;
            this.size = i4;
            this.alignment = i5;
        }
    }

    static {
        Type builtin = builtin(NativeType.UINT8);
        UINT8 = builtin;
        Type builtin2 = builtin(NativeType.SINT8);
        SINT8 = builtin2;
        Type builtin3 = builtin(NativeType.UINT16);
        UINT16 = builtin3;
        Type builtin4 = builtin(NativeType.SINT16);
        SINT16 = builtin4;
        Type builtin5 = builtin(NativeType.UINT32);
        UINT32 = builtin5;
        Type builtin6 = builtin(NativeType.SINT32);
        SINT32 = builtin6;
        Type builtin7 = builtin(NativeType.UINT64);
        UINT64 = builtin7;
        Type builtin8 = builtin(NativeType.SINT64);
        SINT64 = builtin8;
        UCHAR = builtin;
        SCHAR = builtin2;
        USHORT = builtin3;
        SSHORT = builtin4;
        UINT = builtin5;
        SINT = builtin6;
        ULONG_LONG = builtin7;
        SLONG_LONG = builtin8;
    }

    private static Type builtin(NativeType nativeType) {
        return new Builtin(nativeType);
    }

    public static long[] nativeHandles(Type[] typeArr) {
        long[] jArr = new long[typeArr.length];
        for (int i3 = 0; i3 < typeArr.length; i3++) {
            jArr[i3] = typeArr[i3].handle();
        }
        return jArr;
    }

    private int resolveAlignment() {
        int i3 = getTypeInfo().alignment;
        this.alignment = i3;
        return i3;
    }

    private long resolveHandle() {
        long j2 = getTypeInfo().handle;
        this.handle = j2;
        return j2;
    }

    private int resolveSize() {
        int i3 = getTypeInfo().size;
        this.size = i3;
        return i3;
    }

    private int resolveType() {
        int i3 = getTypeInfo().type;
        this.type = i3;
        return i3;
    }

    public final int alignment() {
        int i3 = this.alignment;
        return i3 != 0 ? i3 : resolveAlignment();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Type) && ((Type) obj).handle() == handle();
    }

    public abstract TypeInfo getTypeInfo();

    public final long handle() {
        return this.handle != 0 ? this.handle : resolveHandle();
    }

    public int hashCode() {
        return 201 + ((int) (handle() ^ (handle() >>> 32)));
    }

    public final int size() {
        int i3 = this.size;
        return i3 != 0 ? i3 : resolveSize();
    }

    public final int type() {
        int i3 = this.type;
        return i3 != 0 ? i3 : resolveType();
    }

    public static long[] nativeHandles(List<Type> list) {
        int size2 = list.size();
        long[] jArr = new long[size2];
        for (int i3 = 0; i3 < size2; i3++) {
            jArr[i3] = list.get(i3).handle();
        }
        return jArr;
    }
}
