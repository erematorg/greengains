package com.sun.jna;

import android.support.v4.media.session.a;
import com.sun.jna.Structure;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function extends Pointer {
    public static final int ALT_CONVENTION = 63;
    public static final int C_CONVENTION = 0;
    static final Integer INTEGER_FALSE = 0;
    static final Integer INTEGER_TRUE = -1;
    private static final VarArgsChecker IS_VARARGS = VarArgsChecker.create();
    private static final int MASK_CC = 63;
    public static final int MAX_NARGS = 256;
    static final String OPTION_INVOKING_METHOD = "invoking-method";
    public static final int THROW_LAST_ERROR = 64;
    public static final int USE_VARARGS = 255;
    private static final int USE_VARARGS_SHIFT = 7;
    final int callFlags;
    final String encoding;
    private final String functionName;
    private NativeLibrary library;
    final Map<String, ?> options;

    public static class NativeMappedArray extends Memory implements PostCallRead {
        private final NativeMapped[] original;

        public NativeMappedArray(NativeMapped[] nativeMappedArr) {
            super((long) Native.getNativeSize(nativeMappedArr.getClass(), nativeMappedArr));
            this.original = nativeMappedArr;
            setValue(0, nativeMappedArr, nativeMappedArr.getClass());
        }

        public void read() {
            getValue(0, this.original.getClass(), this.original);
        }
    }

    public static class PointerArray extends Memory implements PostCallRead {
        private final Pointer[] original;

        public PointerArray(Pointer[] pointerArr) {
            super((long) ((pointerArr.length + 1) * Native.POINTER_SIZE));
            this.original = pointerArr;
            for (int i3 = 0; i3 < pointerArr.length; i3++) {
                setPointer((long) (Native.POINTER_SIZE * i3), pointerArr[i3]);
            }
            setPointer((long) (Native.POINTER_SIZE * pointerArr.length), (Pointer) null);
        }

        public void read() {
            Pointer[] pointerArr = this.original;
            read(0, pointerArr, 0, pointerArr.length);
        }
    }

    public interface PostCallRead {
        void read();
    }

    public Function(NativeLibrary nativeLibrary, String str, int i3, String str2) {
        checkCallingConvention(i3 & 63);
        if (str != null) {
            this.library = nativeLibrary;
            this.functionName = str;
            this.callFlags = i3;
            this.options = nativeLibrary.getOptions();
            this.encoding = str2 == null ? Native.getDefaultStringEncoding() : str2;
            try {
                this.peer = nativeLibrary.getSymbolAddress(str);
            } catch (UnsatisfiedLinkError e3) {
                StringBuilder w2 = a.w("Error looking up function '", str, "': ");
                w2.append(e3.getMessage());
                throw new UnsatisfiedLinkError(w2.toString());
            }
        } else {
            throw new NullPointerException("Function name must not be null");
        }
    }

    private void checkCallingConvention(int i3) throws IllegalArgumentException {
        if ((i3 & 63) != i3) {
            throw new IllegalArgumentException(A.a.k("Unrecognized calling convention: ", i3));
        }
    }

    public static Object[] concatenateVarArgs(Object[] objArr) {
        if (objArr == null || objArr.length <= 0) {
            return objArr;
        }
        Object[] objArr2 = objArr[objArr.length - 1];
        Class<?> cls = objArr2 != null ? objArr2.getClass() : null;
        if (cls == null || !cls.isArray()) {
            return objArr;
        }
        Object[] objArr3 = objArr2;
        for (int i3 = 0; i3 < objArr3.length; i3++) {
            Object obj = objArr3[i3];
            if (obj instanceof Float) {
                objArr3[i3] = Double.valueOf((double) ((Float) obj).floatValue());
            }
        }
        int length = objArr.length + objArr3.length;
        Object[] objArr4 = new Object[length];
        System.arraycopy(objArr, 0, objArr4, 0, objArr.length - 1);
        System.arraycopy(objArr3, 0, objArr4, objArr.length - 1, objArr3.length);
        objArr4[length - 1] = null;
        return objArr4;
    }

    private Object convertArgument(Object[] objArr, int i3, Method method, TypeMapper typeMapper, boolean z2, Class<?> cls) {
        Object obj = objArr[i3];
        if (obj != null) {
            Class<?> cls2 = obj.getClass();
            ToNativeConverter instance = NativeMapped.class.isAssignableFrom(cls2) ? NativeMappedConverter.getInstance(cls2) : typeMapper != null ? typeMapper.getToNativeConverter(cls2) : null;
            if (instance != null) {
                obj = instance.toNative(obj, method != null ? new MethodParameterContext(this, objArr, i3, method) : new FunctionParameterContext(this, objArr, i3));
            }
        }
        if (obj == null || isPrimitiveArray(obj.getClass())) {
            return obj;
        }
        Class cls3 = obj.getClass();
        if (obj instanceof Structure) {
            Structure structure = (Structure) obj;
            structure.autoWrite();
            if (structure instanceof Structure.ByValue) {
                Class<?> cls4 = structure.getClass();
                if (method != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (!IS_VARARGS.isVarArgs(method)) {
                        cls4 = parameterTypes[i3];
                    } else if (i3 < parameterTypes.length - 1) {
                        cls4 = parameterTypes[i3];
                    } else {
                        Class<?> componentType = parameterTypes[parameterTypes.length - 1].getComponentType();
                        if (componentType != Object.class) {
                            cls4 = componentType;
                        }
                    }
                }
                if (Structure.ByValue.class.isAssignableFrom(cls4)) {
                    return structure;
                }
            }
            return structure.getPointer();
        } else if (obj instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback) obj);
        } else {
            if (obj instanceof String) {
                return new NativeString((String) obj, this.encoding).getPointer();
            }
            if (obj instanceof WString) {
                return new NativeString(obj.toString(), true).getPointer();
            }
            if (obj instanceof Boolean) {
                return Boolean.TRUE.equals(obj) ? INTEGER_TRUE : INTEGER_FALSE;
            }
            if (String[].class == cls3) {
                return new StringArray((String[]) obj, this.encoding);
            }
            if (WString[].class == cls3) {
                return new StringArray((WString[]) obj);
            }
            if (Pointer[].class == cls3) {
                return new PointerArray((Pointer[]) obj);
            }
            if (NativeMapped[].class.isAssignableFrom(cls3)) {
                return new NativeMappedArray((NativeMapped[]) obj);
            }
            if (Structure[].class.isAssignableFrom(cls3)) {
                Structure[] structureArr = (Structure[]) obj;
                Class componentType2 = cls3.getComponentType();
                boolean isAssignableFrom = Structure.ByReference.class.isAssignableFrom(componentType2);
                if (cls != null && !Structure.ByReference[].class.isAssignableFrom(cls)) {
                    if (!isAssignableFrom) {
                        int i4 = 0;
                        while (i4 < structureArr.length) {
                            if (!(structureArr[i4] instanceof Structure.ByReference)) {
                                i4++;
                            } else {
                                StringBuilder sb = new StringBuilder("Function ");
                                sb.append(getName());
                                sb.append(" declared Structure[] at parameter ");
                                sb.append(i3);
                                sb.append(" but element ");
                                throw new IllegalArgumentException(A.a.m(sb, " is of Structure.ByReference type", i4));
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + i3 + " but array of " + componentType2 + " was passed");
                    }
                }
                if (isAssignableFrom) {
                    Structure.autoWrite(structureArr);
                    Pointer[] pointerArr = new Pointer[(structureArr.length + 1)];
                    for (int i5 = 0; i5 < structureArr.length; i5++) {
                        Structure structure2 = structureArr[i5];
                        pointerArr[i5] = structure2 != null ? structure2.getPointer() : null;
                    }
                    return new PointerArray(pointerArr);
                } else if (structureArr.length == 0) {
                    throw new IllegalArgumentException("Structure array must have non-zero length");
                } else if (structureArr[0] == null) {
                    Structure.newInstance(componentType2).toArray(structureArr);
                    return structureArr[0].getPointer();
                } else {
                    Structure.autoWrite(structureArr);
                    return structureArr[0].getPointer();
                }
            } else if (cls3.isArray()) {
                throw new IllegalArgumentException("Unsupported array argument type: " + cls3.getComponentType());
            } else if (z2 || Native.isSupportedNativeType(obj.getClass())) {
                return obj;
            } else {
                throw new IllegalArgumentException("Unsupported argument type " + obj.getClass().getName() + " at parameter " + i3 + " of function " + getName());
            }
        }
    }

    public static int fixedArgs(Method method) {
        return IS_VARARGS.fixedArgs(method);
    }

    public static Function getFunction(String str, String str2) {
        return NativeLibrary.getInstance(str).getFunction(str2);
    }

    private Pointer invokePointer(int i3, Object[] objArr) {
        long invokePointer = Native.invokePointer(this, this.peer, i3, objArr);
        if (invokePointer == 0) {
            return null;
        }
        return new Pointer(invokePointer);
    }

    private String invokeString(int i3, Object[] objArr, boolean z2) {
        Pointer invokePointer = invokePointer(i3, objArr);
        if (invokePointer == null) {
            return null;
        }
        if (z2) {
            return invokePointer.getWideString(0);
        }
        return invokePointer.getString(0, this.encoding);
    }

    private boolean isPrimitiveArray(Class<?> cls) {
        return cls.isArray() && cls.getComponentType().isPrimitive();
    }

    public static boolean isVarArgs(Method method) {
        return IS_VARARGS.isVarArgs(method);
    }

    public static Boolean valueOf(boolean z2) {
        return z2 ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Function function = (Function) obj;
        return function.callFlags == this.callFlags && function.options.equals(this.options) && function.peer == this.peer;
    }

    public int getCallingConvention() {
        return this.callFlags & 63;
    }

    public String getName() {
        return this.functionName;
    }

    public int hashCode() {
        return this.options.hashCode() + this.callFlags + super.hashCode();
    }

    public Object invoke(Class<?> cls, Object[] objArr) {
        return invoke(cls, objArr, this.options);
    }

    public double invokeDouble(Object[] objArr) {
        return ((Double) invoke(Double.class, objArr)).doubleValue();
    }

    public float invokeFloat(Object[] objArr) {
        return ((Float) invoke(Float.class, objArr)).floatValue();
    }

    public int invokeInt(Object[] objArr) {
        return ((Integer) invoke(Integer.class, objArr)).intValue();
    }

    public long invokeLong(Object[] objArr) {
        return ((Long) invoke(Long.class, objArr)).longValue();
    }

    public Object invokeObject(Object[] objArr) {
        return invoke(Object.class, objArr);
    }

    public void invokeVoid(Object[] objArr) {
        invoke(Void.class, objArr);
    }

    public String toString() {
        if (this.library != null) {
            return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer);
        }
        return "native function@0x" + Long.toHexString(this.peer);
    }

    public static Function getFunction(String str, String str2, int i3) {
        return NativeLibrary.getInstance(str).getFunction(str2, i3, (String) null);
    }

    public Object invoke(Class<?> cls, Object[] objArr, Map<String, ?> map) {
        Method method = (Method) map.get(OPTION_INVOKING_METHOD);
        return invoke(method, method != null ? method.getParameterTypes() : null, cls, objArr, map);
    }

    public static Function getFunction(String str, String str2, int i3, String str3) {
        return NativeLibrary.getInstance(str).getFunction(str2, i3, str3);
    }

    public Pointer invokePointer(Object[] objArr) {
        return (Pointer) invoke(Pointer.class, objArr);
    }

    public static Function getFunction(Pointer pointer) {
        return getFunction(pointer, 0, (String) null);
    }

    public String invokeString(Object[] objArr, boolean z2) {
        Object invoke = invoke(z2 ? WString.class : String.class, objArr);
        if (invoke != null) {
            return invoke.toString();
        }
        return null;
    }

    public static Function getFunction(Pointer pointer, int i3) {
        return getFunction(pointer, i3, (String) null);
    }

    public Object invoke(Method method, Class<?>[] clsArr, Class<?> cls, Object[] objArr, Map<String, ?> map) {
        Object[] objArr2;
        NativeMappedConverter nativeMappedConverter;
        NativeMappedConverter nativeMappedConverter2;
        Class<?> cls2;
        FromNativeContext fromNativeContext;
        Class<?> cls3;
        Class<?> cls4;
        Method method2 = method;
        Class<?>[] clsArr2 = clsArr;
        Class<?> cls5 = cls;
        Object[] objArr3 = objArr;
        Map<String, ?> map2 = map;
        Object[] objArr4 = new Object[0];
        if (objArr3 == null) {
            objArr2 = objArr4;
        } else if (objArr3.length <= 256) {
            int length = objArr3.length;
            Object[] objArr5 = new Object[length];
            System.arraycopy(objArr3, 0, objArr5, 0, length);
            objArr2 = objArr5;
        } else {
            throw new UnsupportedOperationException("Maximum argument count is 256");
        }
        TypeMapper typeMapper = (TypeMapper) map2.get(Library.OPTION_TYPE_MAPPER);
        boolean equals = Boolean.TRUE.equals(map2.get(Library.OPTION_ALLOW_OBJECTS));
        boolean isVarArgs = (objArr2.length <= 0 || method2 == null) ? false : isVarArgs(method);
        int fixedArgs = (objArr2.length <= 0 || method2 == null) ? 0 : fixedArgs(method);
        int i3 = 0;
        while (true) {
            nativeMappedConverter = null;
            if (i3 >= objArr2.length) {
                break;
            }
            if (method2 != null) {
                if (!isVarArgs || i3 < clsArr2.length - 1) {
                    cls4 = clsArr2[i3];
                } else {
                    cls4 = clsArr2[clsArr2.length - 1].getComponentType();
                }
                cls3 = cls4;
            } else {
                cls3 = null;
            }
            int i4 = i3;
            objArr2[i4] = convertArgument(objArr2, i3, method, typeMapper, equals, cls3);
            i3 = i4 + 1;
            fixedArgs = fixedArgs;
        }
        int i5 = fixedArgs;
        if (NativeMapped.class.isAssignableFrom(cls5)) {
            NativeMappedConverter instance = NativeMappedConverter.getInstance(cls);
            cls2 = instance.nativeType();
            nativeMappedConverter2 = instance;
        } else {
            if (typeMapper != null) {
                FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter(cls5);
                nativeMappedConverter = fromNativeConverter;
                if (fromNativeConverter != null) {
                    cls2 = fromNativeConverter.nativeType();
                    nativeMappedConverter2 = fromNativeConverter;
                }
            }
            cls2 = cls5;
            nativeMappedConverter2 = nativeMappedConverter;
        }
        Object invoke = invoke(objArr2, cls2, equals, i5);
        if (nativeMappedConverter2 != null) {
            if (method2 != null) {
                fromNativeContext = new MethodResultContext(cls5, this, objArr3, method2);
            } else {
                fromNativeContext = new FunctionResultContext(cls5, this, objArr3);
            }
            invoke = nativeMappedConverter2.fromNative(invoke, fromNativeContext);
        }
        if (objArr3 != null) {
            for (int i6 = 0; i6 < objArr3.length; i6++) {
                Object obj = objArr3[i6];
                if (obj != null) {
                    if (!(obj instanceof Structure)) {
                        Object obj2 = objArr2[i6];
                        if (obj2 instanceof PostCallRead) {
                            ((PostCallRead) obj2).read();
                            Object obj3 = objArr2[i6];
                            if (obj3 instanceof PointerArray) {
                                PointerArray pointerArray = (PointerArray) obj3;
                                if (Structure.ByReference[].class.isAssignableFrom(obj.getClass())) {
                                    Class<?> componentType = obj.getClass().getComponentType();
                                    Structure[] structureArr = (Structure[]) obj;
                                    for (int i7 = 0; i7 < structureArr.length; i7++) {
                                        structureArr[i7] = Structure.updateStructureByReference(componentType, structureArr[i7], pointerArray.getPointer((long) (Native.POINTER_SIZE * i7)));
                                    }
                                }
                            }
                        } else {
                            if (Structure[].class.isAssignableFrom(obj.getClass())) {
                                Structure.autoRead((Structure[]) obj);
                            }
                        }
                    } else if (!(obj instanceof Structure.ByValue)) {
                        ((Structure) obj).autoRead();
                    }
                }
            }
        }
        return invoke;
    }

    public static Function getFunction(Pointer pointer, int i3, String str) {
        return new Function(pointer, i3, str);
    }

    public Function(Pointer pointer, int i3, String str) {
        checkCallingConvention(i3 & 63);
        if (pointer == null || pointer.peer == 0) {
            throw new NullPointerException("Function address may not be null");
        }
        this.functionName = pointer.toString();
        this.callFlags = i3;
        this.peer = pointer.peer;
        this.options = Collections.EMPTY_MAP;
        this.encoding = str == null ? Native.getDefaultStringEncoding() : str;
    }

    public Object invoke(Object[] objArr, Class<?> cls, boolean z2) {
        return invoke(objArr, cls, z2, 0);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Class<?>, java.lang.Class, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(java.lang.Object[] r8, java.lang.Class<?> r9, boolean r10, int r11) {
        /*
            r7 = this;
            int r0 = r7.callFlags
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 7
            r4 = r0 | r11
            r11 = 0
            if (r9 == 0) goto L_0x01cc
            java.lang.Class r0 = java.lang.Void.TYPE
            if (r9 == r0) goto L_0x01cc
            java.lang.Class<java.lang.Void> r0 = java.lang.Void.class
            if (r9 != r0) goto L_0x0015
            goto L_0x01cc
        L_0x0015:
            java.lang.Class r0 = java.lang.Boolean.TYPE
            r1 = 1
            r2 = 0
            if (r9 == r0) goto L_0x01bd
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            if (r9 != r0) goto L_0x0021
            goto L_0x01bd
        L_0x0021:
            java.lang.Class r0 = java.lang.Byte.TYPE
            if (r9 == r0) goto L_0x01b1
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            if (r9 != r0) goto L_0x002b
            goto L_0x01b1
        L_0x002b:
            java.lang.Class r0 = java.lang.Short.TYPE
            if (r9 == r0) goto L_0x01a5
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            if (r9 != r0) goto L_0x0035
            goto L_0x01a5
        L_0x0035:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r9 == r0) goto L_0x0199
            java.lang.Class<java.lang.Character> r0 = java.lang.Character.class
            if (r9 != r0) goto L_0x003f
            goto L_0x0199
        L_0x003f:
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r9 == r0) goto L_0x018e
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            if (r9 != r0) goto L_0x0049
            goto L_0x018e
        L_0x0049:
            java.lang.Class r0 = java.lang.Long.TYPE
            if (r9 == r0) goto L_0x0183
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            if (r9 != r0) goto L_0x0053
            goto L_0x0183
        L_0x0053:
            java.lang.Class r0 = java.lang.Float.TYPE
            if (r9 == r0) goto L_0x0178
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            if (r9 != r0) goto L_0x005d
            goto L_0x0178
        L_0x005d:
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r9 == r0) goto L_0x016d
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            if (r9 != r0) goto L_0x0067
            goto L_0x016d
        L_0x0067:
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            if (r9 != r0) goto L_0x0071
            java.lang.String r11 = r7.invokeString(r4, r8, r2)
            goto L_0x01d1
        L_0x0071:
            java.lang.Class<com.sun.jna.WString> r0 = com.sun.jna.WString.class
            if (r9 != r0) goto L_0x0082
            java.lang.String r7 = r7.invokeString(r4, r8, r1)
            if (r7 == 0) goto L_0x01d1
            com.sun.jna.WString r11 = new com.sun.jna.WString
            r11.<init>(r7)
            goto L_0x01d1
        L_0x0082:
            java.lang.Class<com.sun.jna.Pointer> r0 = com.sun.jna.Pointer.class
            boolean r0 = r0.isAssignableFrom(r9)
            if (r0 == 0) goto L_0x008f
            com.sun.jna.Pointer r7 = r7.invokePointer(r4, r8)
            return r7
        L_0x008f:
            java.lang.Class<com.sun.jna.Structure> r0 = com.sun.jna.Structure.class
            boolean r0 = r0.isAssignableFrom(r9)
            if (r0 == 0) goto L_0x00bf
            java.lang.Class<com.sun.jna.Structure$ByValue> r10 = com.sun.jna.Structure.ByValue.class
            boolean r10 = r10.isAssignableFrom(r9)
            if (r10 == 0) goto L_0x00b0
            long r2 = r7.peer
            com.sun.jna.Structure r6 = com.sun.jna.Structure.newInstance(r9)
            r1 = r7
            r5 = r8
            com.sun.jna.Structure r11 = com.sun.jna.Native.invokeStructure(r1, r2, r4, r5, r6)
            r11.autoRead()
            goto L_0x01d1
        L_0x00b0:
            com.sun.jna.Pointer r11 = r7.invokePointer(r4, r8)
            if (r11 == 0) goto L_0x01d1
            com.sun.jna.Structure r11 = com.sun.jna.Structure.newInstance(r9, (com.sun.jna.Pointer) r11)
            r11.conditionalAutoRead()
            goto L_0x01d1
        L_0x00bf:
            java.lang.Class<com.sun.jna.Callback> r0 = com.sun.jna.Callback.class
            boolean r0 = r0.isAssignableFrom(r9)
            if (r0 == 0) goto L_0x00d3
            com.sun.jna.Pointer r11 = r7.invokePointer(r4, r8)
            if (r11 == 0) goto L_0x01d1
            com.sun.jna.Callback r11 = com.sun.jna.CallbackReference.getCallback(r9, r11)
            goto L_0x01d1
        L_0x00d3:
            java.lang.Class<java.lang.String[]> r0 = java.lang.String[].class
            r5 = 0
            if (r9 != r0) goto L_0x00e7
            com.sun.jna.Pointer r8 = r7.invokePointer(r4, r8)
            if (r8 == 0) goto L_0x01d1
            java.lang.String r7 = r7.encoding
            java.lang.String[] r11 = r8.getStringArray((long) r5, (java.lang.String) r7)
            goto L_0x01d1
        L_0x00e7:
            java.lang.Class<com.sun.jna.WString[]> r0 = com.sun.jna.WString[].class
            if (r9 != r0) goto L_0x0107
            com.sun.jna.Pointer r7 = r7.invokePointer(r4, r8)
            if (r7 == 0) goto L_0x01d1
            java.lang.String[] r7 = r7.getWideStringArray(r5)
            int r8 = r7.length
            com.sun.jna.WString[] r11 = new com.sun.jna.WString[r8]
        L_0x00f8:
            int r8 = r7.length
            if (r2 >= r8) goto L_0x01d1
            com.sun.jna.WString r8 = new com.sun.jna.WString
            r9 = r7[r2]
            r8.<init>(r9)
            r11[r2] = r8
            int r2 = r2 + 1
            goto L_0x00f8
        L_0x0107:
            java.lang.Class<com.sun.jna.Pointer[]> r0 = com.sun.jna.Pointer[].class
            if (r9 != r0) goto L_0x0117
            com.sun.jna.Pointer r7 = r7.invokePointer(r4, r8)
            if (r7 == 0) goto L_0x01d1
            com.sun.jna.Pointer[] r11 = r7.getPointerArray(r5)
            goto L_0x01d1
        L_0x0117:
            if (r10 == 0) goto L_0x014d
            long r10 = r7.peer
            java.lang.Object r11 = com.sun.jna.Native.invokeObject(r7, r10, r4, r8)
            if (r11 == 0) goto L_0x01d1
            java.lang.Class r7 = r11.getClass()
            boolean r7 = r9.isAssignableFrom(r7)
            if (r7 == 0) goto L_0x012d
            goto L_0x01d1
        L_0x012d:
            java.lang.ClassCastException r7 = new java.lang.ClassCastException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "Return type "
            r8.<init>(r10)
            r8.append(r9)
            java.lang.String r9 = " does not match result "
            r8.append(r9)
            java.lang.Class r9 = r11.getClass()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x014d:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Unsupported return type "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = " in function "
            r10.append(r9)
            java.lang.String r7 = r7.getName()
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            r8.<init>(r7)
            throw r8
        L_0x016d:
            long r9 = r7.peer
            double r7 = com.sun.jna.Native.invokeDouble(r7, r9, r4, r8)
            java.lang.Double r11 = java.lang.Double.valueOf(r7)
            goto L_0x01d1
        L_0x0178:
            long r9 = r7.peer
            float r7 = com.sun.jna.Native.invokeFloat(r7, r9, r4, r8)
            java.lang.Float r11 = java.lang.Float.valueOf(r7)
            goto L_0x01d1
        L_0x0183:
            long r9 = r7.peer
            long r7 = com.sun.jna.Native.invokeLong(r7, r9, r4, r8)
            java.lang.Long r11 = java.lang.Long.valueOf(r7)
            goto L_0x01d1
        L_0x018e:
            long r9 = r7.peer
            int r7 = com.sun.jna.Native.invokeInt(r7, r9, r4, r8)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            goto L_0x01d1
        L_0x0199:
            long r9 = r7.peer
            int r7 = com.sun.jna.Native.invokeInt(r7, r9, r4, r8)
            char r7 = (char) r7
            java.lang.Character r11 = java.lang.Character.valueOf(r7)
            goto L_0x01d1
        L_0x01a5:
            long r9 = r7.peer
            int r7 = com.sun.jna.Native.invokeInt(r7, r9, r4, r8)
            short r7 = (short) r7
            java.lang.Short r11 = java.lang.Short.valueOf(r7)
            goto L_0x01d1
        L_0x01b1:
            long r9 = r7.peer
            int r7 = com.sun.jna.Native.invokeInt(r7, r9, r4, r8)
            byte r7 = (byte) r7
            java.lang.Byte r11 = java.lang.Byte.valueOf(r7)
            goto L_0x01d1
        L_0x01bd:
            long r9 = r7.peer
            int r7 = com.sun.jna.Native.invokeInt(r7, r9, r4, r8)
            if (r7 == 0) goto L_0x01c6
            goto L_0x01c7
        L_0x01c6:
            r1 = r2
        L_0x01c7:
            java.lang.Boolean r11 = valueOf(r1)
            goto L_0x01d1
        L_0x01cc:
            long r9 = r7.peer
            com.sun.jna.Native.invokeVoid(r7, r9, r4, r8)
        L_0x01d1:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Function.invoke(java.lang.Object[], java.lang.Class, boolean, int):java.lang.Object");
    }

    public void invoke(Object[] objArr) {
        invoke(Void.class, objArr);
    }
}
