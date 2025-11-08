package com.sun.jna;

import A.a;
import B1.C0221a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.text.StringSubstitutor;
import org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class Structure {
    public static final int ALIGN_DEFAULT = 0;
    public static final int ALIGN_GNUC = 2;
    public static final int ALIGN_MSVC = 3;
    public static final int ALIGN_NONE = 1;
    protected static final int CALCULATE_SIZE = -1;
    private static final Logger LOG = Logger.getLogger(Structure.class.getName());
    /* access modifiers changed from: private */
    public static final Pointer PLACEHOLDER_MEMORY = new Pointer(0) {
        public Pointer share(long j2, long j3) {
            return this;
        }
    };
    private static final ThreadLocal<Set<Structure>> busy = new ThreadLocal<Set<Structure>>() {
        public synchronized Set<Structure> initialValue() {
            return new StructureSet();
        }
    };
    static final Map<Class<?>, List<Field>> fieldList = new WeakHashMap();
    static final ReentrantReadWriteLock fieldListLock = new ReentrantReadWriteLock();
    static final Map<Class<?>, List<String>> fieldOrder = new WeakHashMap();
    static final ReentrantReadWriteLock fieldOrderLock = new ReentrantReadWriteLock();
    static final Map<Class<?>, LayoutInfo> layoutInfo = new WeakHashMap();
    static final ReentrantReadWriteLock layoutInfoLock = new ReentrantReadWriteLock();
    private static final ThreadLocal<Map<Pointer, Structure>> reads = new ThreadLocal<Map<Pointer, Structure>>() {
        public synchronized Map<Pointer, Structure> initialValue() {
            return new HashMap();
        }
    };
    static final ReentrantReadWriteLock validationLock = new ReentrantReadWriteLock();
    static final Map<Class<?>, Boolean> validationMap = new WeakHashMap();
    private int actualAlignType;
    private int alignType;
    private Structure[] array;
    private boolean autoRead;
    private boolean autoWrite;
    private String encoding;
    private Pointer memory;
    private final Map<String, NativeStringTracking> nativeStrings;
    private boolean readCalled;
    private int size;
    private int structAlignment;
    private Map<String, StructField> structFields;
    private long typeInfo;
    private TypeMapper typeMapper;

    public static class AutoAllocated extends Memory {
        public AutoAllocated(int i3) {
            super((long) i3);
            super.clear();
        }

        public String toString() {
            return "auto-" + super.toString();
        }
    }

    public interface ByReference {
    }

    public interface ByValue {
    }

    @FieldOrder({"size", "alignment", "type", "elements"})
    public static class FFIType extends Structure {
        private static final int FFI_TYPE_STRUCT = 13;
        private static final Map<Pointer, FFIType> ffiTypeInfo;
        private static final Map<Class, Map<Integer, FFIType>> typeInfoMap = new WeakHashMap();
        private static final Map<Class, FFIType> unionHelper = new WeakHashMap();
        public short alignment;
        public Pointer elements;
        public size_t size;
        public short type = 13;

        public static class FFITypes {
            /* access modifiers changed from: private */
            public static Pointer ffi_type_double;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_float;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_longdouble;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_pointer;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_sint16;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_sint32;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_sint64;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_sint8;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_uint16;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_uint32;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_uint64;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_uint8;
            /* access modifiers changed from: private */
            public static Pointer ffi_type_void;

            private FFITypes() {
            }
        }

        public static class size_t extends IntegerType {
            private static final long serialVersionUID = 1;

            public size_t() {
                this(0);
            }

            public size_t(long j2) {
                super(Native.SIZE_T_SIZE, j2);
            }
        }

        static {
            HashMap hashMap = new HashMap();
            ffiTypeInfo = hashMap;
            if (Native.POINTER_SIZE == 0) {
                throw new Error("Native library not initialized");
            } else if (FFITypes.ffi_type_void != null) {
                Class cls = FFIType.class;
                hashMap.put(FFITypes.ffi_type_void, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_void));
                hashMap.put(FFITypes.ffi_type_float, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_float));
                hashMap.put(FFITypes.ffi_type_double, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_double));
                hashMap.put(FFITypes.ffi_type_longdouble, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_longdouble));
                hashMap.put(FFITypes.ffi_type_uint8, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_uint8));
                hashMap.put(FFITypes.ffi_type_sint8, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_sint8));
                hashMap.put(FFITypes.ffi_type_uint16, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_uint16));
                hashMap.put(FFITypes.ffi_type_sint16, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_sint16));
                hashMap.put(FFITypes.ffi_type_uint32, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_uint32));
                hashMap.put(FFITypes.ffi_type_sint32, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_sint32));
                hashMap.put(FFITypes.ffi_type_uint64, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_uint64));
                hashMap.put(FFITypes.ffi_type_sint64, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_sint64));
                hashMap.put(FFITypes.ffi_type_pointer, (FFIType) Structure.newInstance(cls, FFITypes.ffi_type_pointer));
                for (FFIType read : hashMap.values()) {
                    read.read();
                }
                Class cls2 = Void.TYPE;
                Map<Pointer, FFIType> map = ffiTypeInfo;
                storeTypeInfo(cls2, map.get(FFITypes.ffi_type_void));
                storeTypeInfo(Void.class, map.get(FFITypes.ffi_type_void));
                storeTypeInfo(Float.TYPE, map.get(FFITypes.ffi_type_float));
                storeTypeInfo(Float.class, map.get(FFITypes.ffi_type_float));
                storeTypeInfo(Double.TYPE, map.get(FFITypes.ffi_type_double));
                storeTypeInfo(Double.class, map.get(FFITypes.ffi_type_double));
                storeTypeInfo(Long.TYPE, map.get(FFITypes.ffi_type_sint64));
                storeTypeInfo(Long.class, map.get(FFITypes.ffi_type_sint64));
                storeTypeInfo(Integer.TYPE, map.get(FFITypes.ffi_type_sint32));
                storeTypeInfo(Integer.class, map.get(FFITypes.ffi_type_sint32));
                storeTypeInfo(Short.TYPE, map.get(FFITypes.ffi_type_sint16));
                storeTypeInfo(Short.class, map.get(FFITypes.ffi_type_sint16));
                FFIType fFIType = map.get(Native.WCHAR_SIZE == 2 ? FFITypes.ffi_type_uint16 : FFITypes.ffi_type_uint32);
                storeTypeInfo(Character.TYPE, fFIType);
                storeTypeInfo(Character.class, fFIType);
                storeTypeInfo(Byte.TYPE, map.get(FFITypes.ffi_type_sint8));
                storeTypeInfo(Byte.class, map.get(FFITypes.ffi_type_sint8));
                storeTypeInfo(Pointer.class, map.get(FFITypes.ffi_type_pointer));
                storeTypeInfo(String.class, map.get(FFITypes.ffi_type_pointer));
                storeTypeInfo(WString.class, map.get(FFITypes.ffi_type_pointer));
                storeTypeInfo(Boolean.TYPE, map.get(FFITypes.ffi_type_uint32));
                storeTypeInfo(Boolean.class, map.get(FFITypes.ffi_type_uint32));
            } else {
                throw new Error("FFI types not initialized");
            }
        }

        public FFIType(FFIType fFIType) {
            this.size = fFIType.size;
            this.alignment = fFIType.alignment;
            this.type = fFIType.type;
            this.elements = fFIType.elements;
        }

        public static FFIType get(Object obj) {
            FFIType typeInfo;
            if (obj == null) {
                synchronized (typeInfoMap) {
                    typeInfo = getTypeInfo(Pointer.class, 0);
                }
                return typeInfo;
            } else if (obj instanceof Class) {
                return get((Object) null, (Class) obj);
            } else {
                return get(obj, obj.getClass());
            }
        }

        private static FFIType getTypeInfo(Class cls, int i3) {
            Map map = typeInfoMap.get(cls);
            if (map != null) {
                return (FFIType) map.get(Integer.valueOf(i3));
            }
            return null;
        }

        private void init(Pointer[] pointerArr) {
            Memory memory = new Memory((long) (Native.POINTER_SIZE * pointerArr.length));
            this.elements = memory;
            memory.write(0, pointerArr, 0, pointerArr.length);
            write();
        }

        private static boolean isFloatType(FFIType fFIType) {
            Pointer pointer = fFIType.getPointer();
            return pointer.equals(FFITypes.ffi_type_float) || pointer.equals(FFITypes.ffi_type_double);
        }

        private static boolean isIntegerType(FFIType fFIType) {
            Pointer pointer = fFIType.getPointer();
            return pointer.equals(FFITypes.ffi_type_uint8) || pointer.equals(FFITypes.ffi_type_sint8) || pointer.equals(FFITypes.ffi_type_uint16) || pointer.equals(FFITypes.ffi_type_sint16) || pointer.equals(FFITypes.ffi_type_uint32) || pointer.equals(FFITypes.ffi_type_sint32) || pointer.equals(FFITypes.ffi_type_uint64) || pointer.equals(FFITypes.ffi_type_sint64) || pointer.equals(FFITypes.ffi_type_pointer);
        }

        private static void storeTypeInfo(Class cls, FFIType fFIType) {
            storeTypeInfo(cls, 0, fFIType);
        }

        private static void storeTypeInfo(Class cls, int i3, FFIType fFIType) {
            Map<Class, Map<Integer, FFIType>> map = typeInfoMap;
            synchronized (map) {
                try {
                    Map map2 = map.get(cls);
                    if (map2 == null) {
                        map2 = new HashMap();
                        map.put(cls, map2);
                    }
                    map2.put(Integer.valueOf(i3), fFIType);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public FFIType() {
        }

        /* access modifiers changed from: private */
        public static FFIType get(Object obj, Class<?> cls) {
            ToNativeConverter toNativeConverter;
            TypeMapper typeMapper = Native.getTypeMapper(cls);
            if (!(typeMapper == null || (toNativeConverter = typeMapper.getToNativeConverter(cls)) == null)) {
                cls = toNativeConverter.nativeType();
            }
            Map<Class, Map<Integer, FFIType>> map = typeInfoMap;
            synchronized (map) {
                try {
                    FFIType typeInfo = getTypeInfo(cls, cls.isArray() ? Array.getLength(obj) : 0);
                    if (typeInfo != null) {
                        return typeInfo;
                    }
                    if ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) || Callback.class.isAssignableFrom(cls)) {
                        map.put(cls, map.get(Pointer.class));
                        FFIType fFIType = (FFIType) map.get(Pointer.class).get(0);
                        return fFIType;
                    } else if (Structure.class.isAssignableFrom(cls)) {
                        if (obj == null) {
                            obj = Structure.newInstance(cls, Structure.PLACEHOLDER_MEMORY);
                        }
                        if (ByReference.class.isAssignableFrom(cls)) {
                            map.put(cls, map.get(Pointer.class));
                            FFIType fFIType2 = (FFIType) map.get(Pointer.class).get(0);
                            return fFIType2;
                        }
                        FFIType fFIType3 = new FFIType((Structure) obj);
                        storeTypeInfo(cls, fFIType3);
                        return fFIType3;
                    } else if (NativeMapped.class.isAssignableFrom(cls)) {
                        NativeMappedConverter instance = NativeMappedConverter.getInstance(cls);
                        FFIType fFIType4 = get(instance.toNative(obj, new ToNativeContext()), instance.nativeType());
                        return fFIType4;
                    } else if (cls.isArray()) {
                        FFIType fFIType5 = new FFIType(obj, cls);
                        storeTypeInfo(cls, Array.getLength(obj), fFIType5);
                        return fFIType5;
                    } else {
                        throw new IllegalArgumentException("Unsupported type " + cls);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public FFIType(Structure structure) {
            Pointer[] pointerArr;
            int i3;
            structure.ensureAllocated(true);
            int i4 = 0;
            if (structure instanceof Union) {
                int i5 = 0;
                boolean z2 = false;
                FFIType fFIType = null;
                for (StructField next : structure.fields().values()) {
                    FFIType fieldTypeInfo = structure.getFieldTypeInfo(next);
                    z2 = isIntegerType(fieldTypeInfo) ? true : z2;
                    if (fFIType == null || i5 < (i3 = next.size) || (i5 == i3 && Structure.class.isAssignableFrom(next.type))) {
                        i5 = next.size;
                        fFIType = fieldTypeInfo;
                    }
                }
                if (((Platform.isIntel() && Platform.is64Bit() && !Platform.isWindows()) || Platform.isARM() || Platform.isLoongArch()) && z2 && isFloatType(fFIType)) {
                    FFIType fFIType2 = new FFIType(fFIType);
                    if (fFIType2.size.intValue() == 4) {
                        fFIType2.type = ffiTypeInfo.get(FFITypes.ffi_type_uint32).type;
                    } else if (fFIType2.size.intValue() == 8) {
                        fFIType2.type = ffiTypeInfo.get(FFITypes.ffi_type_uint64).type;
                    }
                    fFIType2.write();
                    fFIType = fFIType2;
                }
                pointerArr = new Pointer[]{fFIType.getPointer(), null};
                unionHelper.put(structure.getClass(), fFIType);
            } else {
                pointerArr = new Pointer[(structure.fields().size() + 1)];
                for (StructField fieldTypeInfo2 : structure.fields().values()) {
                    pointerArr[i4] = structure.getFieldTypeInfo(fieldTypeInfo2).getPointer();
                    i4++;
                }
            }
            init(pointerArr);
            write();
        }

        public FFIType(Object obj, Class<?> cls) {
            int length = Array.getLength(obj);
            Pointer[] pointerArr = new Pointer[(length + 1)];
            Pointer pointer = get((Object) null, cls.getComponentType()).getPointer();
            for (int i3 = 0; i3 < length; i3++) {
                pointerArr[i3] = pointer;
            }
            init(pointerArr);
            write();
        }
    }

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldOrder {
        String[] value();
    }

    public static class LayoutInfo {
        /* access modifiers changed from: private */
        public int alignType;
        /* access modifiers changed from: private */
        public int alignment;
        /* access modifiers changed from: private */
        public final Map<String, StructField> fields;
        /* access modifiers changed from: private */
        public int size;
        /* access modifiers changed from: private */
        public TypeMapper typeMapper;
        /* access modifiers changed from: private */
        public boolean variable;

        private LayoutInfo() {
            this.size = -1;
            this.alignment = 1;
            this.fields = Collections.synchronizedMap(new LinkedHashMap());
            this.alignType = 0;
        }
    }

    public static class NativeStringTracking {
        /* access modifiers changed from: private */
        public NativeString peer;
        /* access modifiers changed from: private */
        public final Object value;

        public NativeStringTracking(Object obj) {
            this.value = obj;
        }
    }

    public static class StructField {
        public FromNativeContext context;
        public Field field;
        public boolean isReadOnly;
        public boolean isVolatile;
        public String name;
        public int offset = -1;
        public FromNativeConverter readConverter;
        public int size = -1;
        public Class<?> type;
        public ToNativeConverter writeConverter;

        public String toString() {
            return this.name + "@" + this.offset + "[" + this.size + "] (" + this.type + ")";
        }
    }

    public static class StructureSet extends AbstractCollection<Structure> implements Set<Structure> {
        private int count;
        Structure[] elements;

        private void ensureCapacity(int i3) {
            Structure[] structureArr = this.elements;
            if (structureArr == null) {
                this.elements = new Structure[((i3 * 3) / 2)];
            } else if (structureArr.length < i3) {
                Structure[] structureArr2 = new Structure[((i3 * 3) / 2)];
                System.arraycopy(structureArr, 0, structureArr2, 0, structureArr.length);
                this.elements = structureArr2;
            }
        }

        private int indexOf(Structure structure) {
            for (int i3 = 0; i3 < this.count; i3++) {
                Structure structure2 = this.elements[i3];
                if (structure == structure2 || (structure.getClass() == structure2.getClass() && structure.size() == structure2.size() && structure.getPointer().equals(structure2.getPointer()))) {
                    return i3;
                }
            }
            return -1;
        }

        public boolean contains(Object obj) {
            return indexOf((Structure) obj) != -1;
        }

        public Structure[] getElements() {
            return this.elements;
        }

        public Iterator<Structure> iterator() {
            int i3 = this.count;
            Structure[] structureArr = new Structure[i3];
            if (i3 > 0) {
                System.arraycopy(this.elements, 0, structureArr, 0, i3);
            }
            return Arrays.asList(structureArr).iterator();
        }

        public boolean remove(Object obj) {
            int indexOf = indexOf((Structure) obj);
            if (indexOf == -1) {
                return false;
            }
            int i3 = this.count - 1;
            this.count = i3;
            if (i3 >= 0) {
                Structure[] structureArr = this.elements;
                structureArr[indexOf] = structureArr[i3];
                structureArr[i3] = null;
            }
            return true;
        }

        public int size() {
            return this.count;
        }

        public boolean add(Structure structure) {
            if (contains(structure)) {
                return false;
            }
            ensureCapacity(this.count + 1);
            Structure[] structureArr = this.elements;
            int i3 = this.count;
            this.count = i3 + 1;
            structureArr[i3] = structure;
            return true;
        }
    }

    public Structure() {
        this(0);
    }

    private int addPadding(int i3) {
        return addPadding(i3, this.structAlignment);
    }

    public static void autoRead(Structure[] structureArr) {
        structureArrayCheck(structureArr);
        Structure structure = structureArr[0];
        if (structure.array == structureArr) {
            structure.autoRead();
            return;
        }
        for (Structure structure2 : structureArr) {
            if (structure2 != null) {
                structure2.autoRead();
            }
        }
    }

    public static void autoWrite(Structure[] structureArr) {
        structureArrayCheck(structureArr);
        Structure structure = structureArr[0];
        if (structure.array == structureArr) {
            structure.autoWrite();
            return;
        }
        for (Structure structure2 : structureArr) {
            if (structure2 != null) {
                structure2.autoWrite();
            }
        }
    }

    private Class<?> baseClass() {
        if ((this instanceof ByReference) || (this instanceof ByValue)) {
            if (Structure.class.isAssignableFrom(getClass().getSuperclass())) {
                return getClass().getSuperclass();
            }
        }
        return getClass();
    }

    public static Set<Structure> busy() {
        return busy.get();
    }

    public static List<String> createFieldsOrder(List<String> list, String... strArr) {
        return createFieldsOrder(list, (List<String>) Arrays.asList(strArr));
    }

    private LayoutInfo deriveLayout(boolean z2, boolean z3) {
        Class cls;
        List<Field> fields = getFields(z2);
        if (fields == null) {
            return null;
        }
        LayoutInfo layoutInfo2 = new LayoutInfo();
        int unused = layoutInfo2.alignType = this.alignType;
        TypeMapper unused2 = layoutInfo2.typeMapper = this.typeMapper;
        boolean z4 = true;
        boolean z5 = true;
        int i3 = 0;
        for (Field next : fields) {
            int modifiers = next.getModifiers();
            Class type = next.getType();
            if (type.isArray()) {
                boolean unused3 = layoutInfo2.variable = z4;
            }
            StructField structField = new StructField();
            structField.isVolatile = Modifier.isVolatile(modifiers);
            boolean isFinal = Modifier.isFinal(modifiers);
            structField.isReadOnly = isFinal;
            if (isFinal) {
                if (Platform.RO_FIELDS) {
                    next.setAccessible(z4);
                } else {
                    throw new IllegalArgumentException("This VM does not support read-only fields (field '" + next.getName() + "' within " + getClass() + ")");
                }
            }
            structField.field = next;
            structField.name = next.getName();
            structField.type = type;
            if (Callback.class.isAssignableFrom(type) && !type.isInterface()) {
                throw new IllegalArgumentException("Structure Callback field '" + next.getName() + "' must be an interface");
            } else if (!type.isArray() || !Structure.class.equals(type.getComponentType())) {
                if (Modifier.isPublic(next.getModifiers())) {
                    Object fieldValue = getFieldValue(structField.field);
                    if (fieldValue != null || !type.isArray()) {
                        if (NativeMapped.class.isAssignableFrom(type)) {
                            NativeMappedConverter instance = NativeMappedConverter.getInstance(type);
                            cls = instance.nativeType();
                            structField.writeConverter = instance;
                            structField.readConverter = instance;
                            structField.context = new StructureReadContext(this, next);
                        } else {
                            TypeMapper typeMapper2 = this.typeMapper;
                            if (typeMapper2 != null) {
                                ToNativeConverter toNativeConverter = typeMapper2.getToNativeConverter(type);
                                FromNativeConverter fromNativeConverter = this.typeMapper.getFromNativeConverter(type);
                                if (toNativeConverter != null && fromNativeConverter != null) {
                                    fieldValue = toNativeConverter.toNative(fieldValue, new StructureWriteContext(this, structField.field));
                                    Class cls2 = fieldValue != null ? fieldValue.getClass() : Pointer.class;
                                    structField.writeConverter = toNativeConverter;
                                    structField.readConverter = fromNativeConverter;
                                    structField.context = new StructureReadContext(this, next);
                                    cls = cls2;
                                } else if (!(toNativeConverter == null && fromNativeConverter == null)) {
                                    throw new IllegalArgumentException(b.k("Structures require bidirectional type conversion for ", type));
                                }
                            }
                            cls = type;
                        }
                        if (fieldValue == null) {
                            fieldValue = initializeField(structField.field, type);
                        }
                        try {
                            structField.size = getNativeSize(cls, fieldValue);
                            int nativeAlignment = getNativeAlignment(cls, fieldValue, z5);
                            if (nativeAlignment != 0) {
                                int unused4 = layoutInfo2.alignment = Math.max(layoutInfo2.alignment, nativeAlignment);
                                int i4 = i3 % nativeAlignment;
                                if (i4 != 0) {
                                    i3 += nativeAlignment - i4;
                                }
                                if (this instanceof Union) {
                                    structField.offset = 0;
                                    i3 = Math.max(i3, structField.size);
                                } else {
                                    structField.offset = i3;
                                    i3 += structField.size;
                                }
                                layoutInfo2.fields.put(structField.name, structField);
                            } else {
                                throw new Error("Field alignment is zero for field '" + structField.name + "' within " + getClass());
                            }
                        } catch (IllegalArgumentException e3) {
                            if (!z2 && this.typeMapper == null) {
                                return null;
                            }
                            throw new IllegalArgumentException("Invalid Structure field in " + getClass() + ", field name '" + structField.name + "' (" + structField.type + "): " + e3.getMessage(), e3);
                        }
                    } else if (!z2) {
                        return null;
                    } else {
                        throw new IllegalStateException("Array fields must be initialized");
                    }
                }
                z5 = false;
                z4 = true;
            } else {
                throw new IllegalArgumentException("Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined");
            }
        }
        if (i3 > 0) {
            int addPadding = addPadding(i3, layoutInfo2.alignment);
            if ((this instanceof ByValue) && !z3) {
                getTypeInfo();
            }
            int unused5 = layoutInfo2.size = addPadding;
            return layoutInfo2;
        }
        throw new IllegalArgumentException("Structure " + getClass() + " has unknown or zero size (ensure all fields are public)");
    }

    private List<String> fieldOrder() {
        Class<?> cls = getClass();
        ReentrantReadWriteLock reentrantReadWriteLock = fieldOrderLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            Map<Class<?>, List<String>> map = fieldOrder;
            List<String> list = map.get(cls);
            if (list != null) {
                reentrantReadWriteLock.readLock().unlock();
                return list;
            }
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();
            try {
                List<String> computeIfAbsent = map.computeIfAbsent(cls, new a(this, 0));
                reentrantReadWriteLock.writeLock().unlock();
                return computeIfAbsent;
            } catch (Throwable th) {
                fieldOrderLock.writeLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            fieldOrderLock.readLock().unlock();
            throw th2;
        }
    }

    private String format(Class<?> cls) {
        String name = cls.getName();
        return name.substring(name.lastIndexOf(JwtUtilsKt.JWT_DELIMITER) + 1);
    }

    private static <T> Constructor<T> getPointerConstructor(Class<T> cls) {
        for (Constructor<T> constructor : cls.getConstructors()) {
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(Pointer.class)) {
                return constructor;
            }
        }
        return null;
    }

    private Object initializeField(Field field, Class<?> cls) {
        if (Structure.class.isAssignableFrom(cls) && !ByReference.class.isAssignableFrom(cls)) {
            try {
                Structure newInstance = newInstance(cls, PLACEHOLDER_MEMORY);
                setFieldValue(field, newInstance);
                return newInstance;
            } catch (IllegalArgumentException e3) {
                throw new IllegalArgumentException("Can't determine size of nested structure", e3);
            }
        } else if (!NativeMapped.class.isAssignableFrom(cls)) {
            return null;
        } else {
            NativeMapped defaultValue = NativeMappedConverter.getInstance(cls).defaultValue();
            setFieldValue(field, defaultValue);
            return defaultValue;
        }
    }

    private void initializeFields() {
        for (Field next : getFieldList()) {
            try {
                if (next.get(this) == null) {
                    initializeField(next, next.getType());
                }
            } catch (Exception e3) {
                throw new Error("Exception reading field '" + next.getName() + "' in " + getClass(), e3);
            }
        }
    }

    private void initializeTypeMapper(TypeMapper typeMapper2) {
        if (typeMapper2 == null) {
            typeMapper2 = Native.getTypeMapper(getClass());
        }
        this.typeMapper = typeMapper2;
        layoutChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$fieldOrder$1(Class cls) {
        return getFieldOrder();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$getFieldList$0(Class cls, Class cls2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (!cls.equals(Structure.class)) {
            for (Field field : cls.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                    arrayList2.add(field);
                }
            }
            arrayList.addAll(0, arrayList2);
            arrayList2.clear();
            cls = cls.getSuperclass();
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$validateFields$2(Class cls) {
        for (Field next : getFieldList()) {
            validateField(next.getName(), next.getType());
        }
        return Boolean.TRUE;
    }

    private void layoutChanged() {
        if (this.size != -1) {
            this.size = -1;
            if (this.memory instanceof AutoAllocated) {
                this.memory = null;
            }
            ensureAllocated();
        }
    }

    private static <T extends Structure> T newInstance(Class<T> cls, long j2) {
        Pointer pointer;
        int i3 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i3 == 0) {
            try {
                pointer = PLACEHOLDER_MEMORY;
            } catch (Throwable th) {
                LOG.log(Level.WARNING, "JNA: Error creating structure", th);
                return null;
            }
        } else {
            pointer = new Pointer(j2);
        }
        T newInstance = newInstance(cls, pointer);
        if (i3 != 0) {
            newInstance.conditionalAutoRead();
        }
        return newInstance;
    }

    public static Map<Pointer, Structure> reading() {
        return reads.get();
    }

    private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> collection) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void structureArrayCheck(Structure[] structureArr) {
        if (!ByReference[].class.isAssignableFrom(structureArr.getClass())) {
            Pointer pointer = structureArr[0].getPointer();
            int size2 = structureArr[0].size();
            int i3 = 1;
            while (i3 < structureArr.length) {
                if (structureArr[i3].getPointer().peer == pointer.peer + ((long) (size2 * i3))) {
                    i3++;
                } else {
                    throw new IllegalArgumentException(C0118y.c(i3, "Structure array elements must use contiguous memory (bad backing address at Structure array index ", ")"));
                }
            }
        }
    }

    public static <T extends Structure> T updateStructureByReference(Class<T> cls, T t2, Pointer pointer) {
        if (pointer == null) {
            return null;
        }
        if (t2 == null || !pointer.equals(t2.getPointer())) {
            T t3 = (Structure) reading().get(pointer);
            if (t3 == null || !cls.equals(t3.getClass())) {
                T newInstance = newInstance(cls, pointer);
                newInstance.conditionalAutoRead();
                return newInstance;
            }
            t3.autoRead();
            return t3;
        }
        t2.autoRead();
        return t2;
    }

    public static void validate(Class<? extends Structure> cls) {
        try {
            cls.getConstructor((Class[]) null);
        } catch (NoSuchMethodException | SecurityException unused) {
            throw new IllegalArgumentException("No suitable constructor found for class: ".concat(cls.getName()));
        }
    }

    private void validateField(String str, Class<?> cls) {
        ToNativeConverter toNativeConverter;
        TypeMapper typeMapper2 = this.typeMapper;
        if (typeMapper2 != null && (toNativeConverter = typeMapper2.getToNativeConverter(cls)) != null) {
            validateField(str, toNativeConverter.nativeType());
        } else if (cls.isArray()) {
            validateField(str, cls.getComponentType());
        } else {
            try {
                getNativeSize(cls);
            } catch (IllegalArgumentException e3) {
                throw new IllegalArgumentException("Invalid Structure field in " + getClass() + ", field name '" + str + "' (" + cls + "): " + e3.getMessage(), e3);
            }
        }
    }

    private void validateFields() {
        ReentrantReadWriteLock reentrantReadWriteLock = validationLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            Map<Class<?>, Boolean> map = validationMap;
            if (map.containsKey(getClass())) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();
            try {
                map.computeIfAbsent(getClass(), new a(this, 1));
                reentrantReadWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                validationLock.writeLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            validationLock.readLock().unlock();
            throw th2;
        }
    }

    public void allocateMemory() {
        allocateMemory(false);
    }

    public Memory autoAllocate(int i3) {
        return new AutoAllocated(i3);
    }

    public void cacheTypeInfo(Pointer pointer) {
        this.typeInfo = pointer.peer;
    }

    public int calculateSize(boolean z2) {
        return calculateSize(z2, false);
    }

    public void clear() {
        ensureAllocated();
        this.nativeStrings.clear();
        this.memory.clear((long) size());
    }

    public void conditionalAutoRead() {
        if (!this.readCalled) {
            autoRead();
        }
    }

    public boolean dataEquals(Structure structure) {
        return dataEquals(structure, false);
    }

    public void ensureAllocated() {
        ensureAllocated(false);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Structure) && obj.getClass() == getClass() && ((Structure) obj).getPointer().equals(getPointer());
    }

    public int fieldOffset(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            return structField.offset;
        }
        throw new IllegalArgumentException(c.a("No such field: ", str));
    }

    public Map<String, StructField> fields() {
        return this.structFields;
    }

    public boolean getAutoRead() {
        return this.autoRead;
    }

    public boolean getAutoWrite() {
        return this.autoWrite;
    }

    public List<Field> getFieldList() {
        Class<?> cls = getClass();
        ReentrantReadWriteLock reentrantReadWriteLock = fieldListLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            Map<Class<?>, List<Field>> map = fieldList;
            List<Field> list = map.get(cls);
            if (list != null) {
                reentrantReadWriteLock.readLock().unlock();
                return list;
            }
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();
            try {
                List<Field> computeIfAbsent = map.computeIfAbsent(cls, new C0221a(cls, 5));
                reentrantReadWriteLock.writeLock().unlock();
                return computeIfAbsent;
            } catch (Throwable th) {
                fieldListLock.writeLock().unlock();
                throw th;
            }
        } catch (Throwable th2) {
            fieldListLock.readLock().unlock();
            throw th2;
        }
    }

    public List<String> getFieldOrder() {
        LinkedList linkedList = new LinkedList();
        for (Class cls = getClass(); cls != Structure.class; cls = cls.getSuperclass()) {
            FieldOrder fieldOrder2 = (FieldOrder) cls.getAnnotation(FieldOrder.class);
            if (fieldOrder2 != null) {
                linkedList.addAll(0, Arrays.asList(fieldOrder2.value()));
            }
        }
        return Collections.unmodifiableList(linkedList);
    }

    public FFIType getFieldTypeInfo(StructField structField) {
        ToNativeConverter toNativeConverter;
        Class<?> cls = structField.type;
        Object fieldValue = getFieldValue(structField.field);
        TypeMapper typeMapper2 = this.typeMapper;
        if (!(typeMapper2 == null || (toNativeConverter = typeMapper2.getToNativeConverter(cls)) == null)) {
            cls = toNativeConverter.nativeType();
            fieldValue = toNativeConverter.toNative(fieldValue, new ToNativeContext());
        }
        return FFIType.get(fieldValue, cls);
    }

    public Object getFieldValue(Field field) {
        try {
            return field.get(this);
        } catch (Exception e3) {
            throw new Error("Exception reading field '" + field.getName() + "' in " + getClass(), e3);
        }
    }

    public List<Field> getFields(boolean z2) {
        List<Field> fieldList2 = getFieldList();
        HashSet hashSet = new HashSet();
        for (Field name : fieldList2) {
            hashSet.add(name.getName());
        }
        List<String> fieldOrder2 = fieldOrder();
        if (fieldOrder2.size() == fieldList2.size() || fieldList2.size() <= 1) {
            if (new HashSet(fieldOrder2).equals(hashSet)) {
                sortFields(fieldList2, fieldOrder2);
                return fieldList2;
            }
            StringBuilder sb = new StringBuilder("Structure.getFieldOrder() on ");
            sb.append(getClass());
            sb.append(" returns names (");
            sb.append(sort(fieldOrder2));
            sb.append(") which do not match declared field names (");
            throw new Error(C0118y.h(")", sort(hashSet), sb));
        } else if (!z2) {
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder("Structure.getFieldOrder() on ");
            sb2.append(getClass());
            sb2.append(fieldOrder2.size() < fieldList2.size() ? " does not provide enough" : " provides too many");
            sb2.append(" names [");
            sb2.append(fieldOrder2.size());
            sb2.append("] (");
            sb2.append(sort(fieldOrder2));
            sb2.append(") to match declared fields [");
            sb2.append(fieldList2.size());
            sb2.append("] (");
            throw new Error(C0118y.h(")", sort(hashSet), sb2));
        }
    }

    public int getNativeAlignment(Class<?> cls, Object obj, boolean z2) {
        if (NativeMapped.class.isAssignableFrom(cls)) {
            NativeMappedConverter instance = NativeMappedConverter.getInstance(cls);
            Class<?> nativeType = instance.nativeType();
            obj = instance.toNative(obj, new ToNativeContext());
            cls = nativeType;
        }
        int nativeSize = Native.getNativeSize(cls, obj);
        Class<Double> cls2 = Double.class;
        if (!(cls.isPrimitive() || Long.class == cls || Integer.class == cls || Short.class == cls || Character.class == cls || Byte.class == cls || Boolean.class == cls || Float.class == cls || cls2 == cls)) {
            if ((Pointer.class.isAssignableFrom(cls) && !Function.class.isAssignableFrom(cls)) || ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) || Callback.class.isAssignableFrom(cls) || WString.class == cls || String.class == cls)) {
                nativeSize = Native.POINTER_SIZE;
            } else if (Structure.class.isAssignableFrom(cls)) {
                if (ByReference.class.isAssignableFrom(cls)) {
                    nativeSize = Native.POINTER_SIZE;
                } else {
                    if (obj == null) {
                        obj = newInstance(cls, PLACEHOLDER_MEMORY);
                    }
                    nativeSize = ((Structure) obj).getStructAlignment();
                }
            } else if (cls.isArray()) {
                nativeSize = getNativeAlignment(cls.getComponentType(), (Object) null, z2);
            } else {
                throw new IllegalArgumentException(b.l("Type ", cls, " has unknown native alignment"));
            }
        }
        int i3 = this.actualAlignType;
        if (i3 == 1) {
            return 1;
        }
        if (i3 == 3) {
            return Math.min(8, nativeSize);
        }
        if (i3 != 2) {
            return nativeSize;
        }
        if (!z2 || !Platform.isMac() || !Platform.isPPC()) {
            nativeSize = Math.min(Native.MAX_ALIGNMENT, nativeSize);
        }
        if (z2 || !Platform.isAIX()) {
            return nativeSize;
        }
        if (cls == Double.TYPE || cls == cls2) {
            return 4;
        }
        return nativeSize;
    }

    public int getNativeSize(Class<?> cls) {
        return getNativeSize(cls, (Object) null);
    }

    public Pointer getPointer() {
        ensureAllocated();
        return this.memory;
    }

    public String getStringEncoding() {
        return this.encoding;
    }

    public int getStructAlignment() {
        if (this.size == -1) {
            calculateSize(true);
        }
        return this.structAlignment;
    }

    public Pointer getTypeInfo() {
        Pointer pointer = getTypeInfo(this).getPointer();
        cacheTypeInfo(pointer);
        return pointer;
    }

    public TypeMapper getTypeMapper() {
        return this.typeMapper;
    }

    public int hashCode() {
        return getPointer() != null ? getPointer().hashCode() : getClass().hashCode();
    }

    public void read() {
        if (this.memory != PLACEHOLDER_MEMORY) {
            this.readCalled = true;
            ensureAllocated();
            if (busy().add(this)) {
                boolean z2 = this instanceof ByReference;
                if (z2) {
                    reading().put(getPointer(), this);
                }
                try {
                    for (StructField readField : fields().values()) {
                        readField(readField);
                    }
                } finally {
                    busy().remove(this);
                    if (z2 && reading().get(getPointer()) == this) {
                        reading().remove(getPointer());
                    }
                }
            }
        }
    }

    public Object readField(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            return readField(structField);
        }
        throw new IllegalArgumentException(c.a("No such field: ", str));
    }

    public void setAlignType(int i3) {
        this.alignType = i3;
        if (i3 == 0 && (i3 = Native.getStructureAlignment(getClass())) == 0) {
            i3 = Platform.isWindows() ? 3 : 2;
        }
        this.actualAlignType = i3;
        layoutChanged();
    }

    public void setAutoRead(boolean z2) {
        this.autoRead = z2;
    }

    public void setAutoSynch(boolean z2) {
        setAutoRead(z2);
        setAutoWrite(z2);
    }

    public void setAutoWrite(boolean z2) {
        this.autoWrite = z2;
    }

    public void setFieldValue(Field field, Object obj) {
        setFieldValue(field, obj, false);
    }

    public void setStringEncoding(String str) {
        this.encoding = str;
    }

    public int size() {
        ensureAllocated();
        return this.size;
    }

    public void sortFields(List<Field> list, List<String> list2) {
        for (int i3 = 0; i3 < list2.size(); i3++) {
            String str = list2.get(i3);
            int i4 = 0;
            while (true) {
                if (i4 >= list.size()) {
                    break;
                } else if (str.equals(list.get(i4).getName())) {
                    Collections.swap(list, i3, i4);
                    break;
                } else {
                    i4++;
                }
            }
        }
    }

    public Structure[] toArray(Structure[] structureArr) {
        ensureAllocated();
        Pointer pointer = this.memory;
        if (pointer instanceof AutoAllocated) {
            int size2 = size() * structureArr.length;
            if (((Memory) pointer).size() < ((long) size2)) {
                useMemory(autoAllocate(size2));
            }
        }
        structureArr[0] = this;
        int size3 = size();
        for (int i3 = 1; i3 < structureArr.length; i3++) {
            Structure newInstance = newInstance(getClass(), this.memory.share((long) (i3 * size3), (long) size3));
            structureArr[i3] = newInstance;
            newInstance.conditionalAutoRead();
        }
        if (!(this instanceof ByValue)) {
            this.array = structureArr;
        }
        return structureArr;
    }

    public String toString() {
        return toString(Boolean.getBoolean("jna.dump_memory"));
    }

    public void useMemory(Pointer pointer) {
        useMemory(pointer, 0);
    }

    public void write() {
        if (this.memory != PLACEHOLDER_MEMORY) {
            ensureAllocated();
            if (this instanceof ByValue) {
                getTypeInfo();
            }
            if (busy().add(this)) {
                try {
                    for (StructField next : fields().values()) {
                        if (!next.isVolatile) {
                            writeField(next);
                        }
                    }
                } finally {
                    busy().remove(this);
                }
            }
        }
    }

    public void writeField(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            writeField(structField);
            return;
        }
        throw new IllegalArgumentException(c.a("No such field: ", str));
    }

    public Structure(TypeMapper typeMapper2) {
        this((Pointer) null, 0, typeMapper2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r1 = r2 % r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int addPadding(int r2, int r3) {
        /*
            r1 = this;
            int r1 = r1.actualAlignType
            r0 = 1
            if (r1 == r0) goto L_0x000b
            int r1 = r2 % r3
            if (r1 == 0) goto L_0x000b
            int r3 = r3 - r1
            int r2 = r2 + r3
        L_0x000b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Structure.addPadding(int, int):int");
    }

    private void allocateMemory(boolean z2) {
        allocateMemory(calculateSize(true, z2));
    }

    public static List<String> createFieldsOrder(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list2.size() + list.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: private */
    public void ensureAllocated(boolean z2) {
        if (this.memory == null) {
            allocateMemory(z2);
        } else if (this.size == -1) {
            int calculateSize = calculateSize(true, z2);
            this.size = calculateSize;
            Pointer pointer = this.memory;
            if (!(pointer instanceof AutoAllocated)) {
                try {
                    this.memory = pointer.share(0, (long) calculateSize);
                } catch (IndexOutOfBoundsException e3) {
                    throw new IllegalArgumentException("Structure exceeds provided memory bounds", e3);
                }
            }
        }
    }

    private void setFieldValue(Field field, Object obj, boolean z2) {
        try {
            field.set(this, obj);
        } catch (IllegalAccessException e3) {
            if (!Modifier.isFinal(field.getModifiers())) {
                throw new Error("Unexpectedly unable to write to field '" + field.getName() + "' within " + getClass(), e3);
            } else if (z2) {
                throw new UnsupportedOperationException("This VM does not support Structures with final fields (field '" + field.getName() + "' within " + getClass() + ")", e3);
            } else {
                throw new UnsupportedOperationException("Attempt to write to read-only field '" + field.getName() + "' within " + getClass(), e3);
            }
        }
    }

    public int calculateSize(boolean z2, boolean z3) {
        Class<?> cls = getClass();
        ReentrantReadWriteLock reentrantReadWriteLock = layoutInfoLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            Map<Class<?>, LayoutInfo> map = layoutInfo;
            LayoutInfo layoutInfo2 = map.get(cls);
            reentrantReadWriteLock.readLock().unlock();
            if (!(layoutInfo2 != null && this.alignType == layoutInfo2.alignType && this.typeMapper == layoutInfo2.typeMapper)) {
                layoutInfo2 = deriveLayout(z2, z3);
            }
            if (layoutInfo2 == null) {
                return -1;
            }
            this.structAlignment = layoutInfo2.alignment;
            this.structFields = layoutInfo2.fields;
            if (!layoutInfo2.variable) {
                reentrantReadWriteLock.readLock().lock();
                try {
                    if (map.containsKey(cls) && this.alignType == 0) {
                        if (this.typeMapper != null) {
                        }
                        reentrantReadWriteLock.readLock().unlock();
                    }
                    reentrantReadWriteLock.readLock().unlock();
                    reentrantReadWriteLock.writeLock().lock();
                    map.put(cls, layoutInfo2);
                    reentrantReadWriteLock.readLock().lock();
                    reentrantReadWriteLock.writeLock().unlock();
                    reentrantReadWriteLock.readLock().unlock();
                } catch (Throwable th) {
                    throw th;
                }
            }
            return layoutInfo2.size;
        } finally {
            layoutInfoLock.readLock().unlock();
        }
    }

    public boolean dataEquals(Structure structure, boolean z2) {
        if (z2) {
            structure.getPointer().clear((long) structure.size());
            structure.write();
            getPointer().clear((long) size());
            write();
        }
        byte[] byteArray = structure.getPointer().getByteArray(0, structure.size());
        byte[] byteArray2 = getPointer().getByteArray(0, size());
        if (byteArray.length != byteArray2.length) {
            return false;
        }
        for (int i3 = 0; i3 < byteArray.length; i3++) {
            if (byteArray[i3] != byteArray2[i3]) {
                return false;
            }
        }
        return true;
    }

    public int getNativeSize(Class<?> cls, Object obj) {
        return Native.getNativeSize(cls, obj);
    }

    public String toString(boolean z2) {
        return toString(0, true, z2);
    }

    public void useMemory(Pointer pointer, int i3) {
        useMemory(pointer, i3, false);
    }

    public Structure(int i3) {
        this((Pointer) null, i3);
    }

    public static FFIType getTypeInfo(Object obj) {
        return FFIType.get(obj);
    }

    public static int size(Class<? extends Structure> cls) {
        return size(cls, (Structure) null);
    }

    private String toString(int i3, boolean z2, boolean z3) {
        String str;
        String str2;
        String str3;
        int i4 = i3;
        boolean z4 = z3;
        ensureAllocated();
        String lineSeparator = System.lineSeparator();
        String str4 = format(getClass()) + "(" + getPointer() + ")";
        if (!(getPointer() instanceof Memory)) {
            StringBuilder q2 = a.q(str4, " (");
            q2.append(size());
            q2.append(" bytes)");
            str4 = q2.toString();
        }
        String str5 = "";
        for (int i5 = 0; i5 < i4; i5++) {
            str5 = android.support.v4.media.session.a.m(str5, "  ");
        }
        if (!z2) {
            str = "...}";
        } else {
            Iterator<StructField> it = fields().values().iterator();
            String str6 = lineSeparator;
            while (it.hasNext()) {
                StructField next = it.next();
                Object fieldValue = getFieldValue(next.field);
                String format = format(next.type);
                String m3 = android.support.v4.media.session.a.m(str6, str5);
                if (!next.type.isArray() || fieldValue == null) {
                    str2 = "";
                } else {
                    format = format(next.type.getComponentType());
                    str2 = "[" + Array.getLength(fieldValue) + "]";
                }
                StringBuilder p2 = a.p(m3);
                p2.append(String.format("  %s %s%s@0x%X", new Object[]{format, next.name, str2, Integer.valueOf(next.offset)}));
                String sb = p2.toString();
                if (fieldValue instanceof Structure) {
                    fieldValue = ((Structure) fieldValue).toString(i4 + 1, !(fieldValue instanceof ByReference), z4);
                }
                String m4 = android.support.v4.media.session.a.m(sb, StickyVariantProvider.KEY_VALUE_DELIMITER);
                if (fieldValue instanceof Long) {
                    StringBuilder p3 = a.p(m4);
                    p3.append(String.format("0x%08X", new Object[]{(Long) fieldValue}));
                    str3 = p3.toString();
                } else if (fieldValue instanceof Integer) {
                    StringBuilder p4 = a.p(m4);
                    p4.append(String.format("0x%04X", new Object[]{(Integer) fieldValue}));
                    str3 = p4.toString();
                } else if (fieldValue instanceof Short) {
                    StringBuilder p5 = a.p(m4);
                    p5.append(String.format("0x%02X", new Object[]{(Short) fieldValue}));
                    str3 = p5.toString();
                } else if (fieldValue instanceof Byte) {
                    StringBuilder p6 = a.p(m4);
                    p6.append(String.format("0x%01X", new Object[]{(Byte) fieldValue}));
                    str3 = p6.toString();
                } else {
                    StringBuilder p7 = a.p(m4);
                    p7.append(String.valueOf(fieldValue).trim());
                    str3 = p7.toString();
                }
                String m5 = android.support.v4.media.session.a.m(str3, lineSeparator);
                if (!it.hasNext()) {
                    m5 = android.support.v4.media.session.a.n(m5, str5, StringSubstitutor.DEFAULT_VAR_END);
                }
                str6 = m5;
            }
            str = str6;
        }
        if (i4 == 0 && z4) {
            String m6 = b.m(str, lineSeparator, "memory dump", lineSeparator);
            byte[] byteArray = getPointer().getByteArray(0, size());
            for (int i6 = 0; i6 < byteArray.length; i6++) {
                int i7 = i6 % 4;
                if (i7 == 0) {
                    m6 = android.support.v4.media.session.a.m(m6, "[");
                }
                byte b3 = byteArray[i6];
                if (b3 >= 0 && b3 < 16) {
                    m6 = android.support.v4.media.session.a.m(m6, SchemaSymbols.ATTVAL_FALSE_0);
                }
                m6 = android.support.v4.media.session.a.j(byteArray[i6] & 255, a.p(m6));
                if (i7 == 3 && i6 < byteArray.length - 1) {
                    m6 = android.support.v4.media.session.a.n(m6, "]", lineSeparator);
                }
            }
            str = android.support.v4.media.session.a.m(m6, "]");
        }
        return android.support.v4.media.session.a.n(str4, " {", str);
    }

    public void allocateMemory(int i3) {
        if (i3 == -1) {
            i3 = calculateSize(false);
        } else if (i3 <= 0) {
            throw new IllegalArgumentException(a.k("Structure size must be greater than zero: ", i3));
        }
        if (i3 != -1) {
            Pointer pointer = this.memory;
            if (pointer == null || (pointer instanceof AutoAllocated)) {
                this.memory = autoAllocate(i3);
            }
            this.size = i3;
        }
    }

    public void useMemory(Pointer pointer, int i3, boolean z2) {
        try {
            this.nativeStrings.clear();
            if (!(this instanceof ByValue) || z2) {
                if (this.size == -1) {
                    this.size = calculateSize(false);
                }
                int i4 = this.size;
                if (i4 != -1) {
                    this.memory = pointer.share((long) i3, (long) i4);
                } else {
                    this.memory = pointer.share((long) i3);
                }
            } else {
                int size2 = size();
                byte[] bArr = new byte[size2];
                pointer.read(0, bArr, 0, size2);
                this.memory.write(0, bArr, 0, size2);
            }
            this.array = null;
            this.readCalled = false;
        } catch (IndexOutOfBoundsException e3) {
            throw new IllegalArgumentException("Structure exceeds provided memory bounds", e3);
        }
    }

    public Structure(int i3, TypeMapper typeMapper2) {
        this((Pointer) null, i3, typeMapper2);
    }

    public static <T extends Structure> T newInstance(Class<T> cls, Pointer pointer) throws IllegalArgumentException {
        try {
            Constructor<T> pointerConstructor = getPointerConstructor(cls);
            if (pointerConstructor != null) {
                return (Structure) pointerConstructor.newInstance(new Object[]{pointer});
            }
        } catch (SecurityException unused) {
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException(b.k("Can't instantiate ", cls), e3);
        } catch (IllegalAccessException e4) {
            throw new IllegalArgumentException(b.l("Instantiation of ", cls, " (Pointer) not allowed, is it public?"), e4);
        } catch (InvocationTargetException e5) {
            throw new IllegalArgumentException(b.k("Exception thrown while instantiating an instance of ", cls), e5);
        }
        T newInstance = newInstance(cls);
        if (pointer != PLACEHOLDER_MEMORY) {
            newInstance.useMemory(pointer);
        }
        return newInstance;
    }

    public static <T extends Structure> int size(Class<T> cls, T t2) {
        ReentrantReadWriteLock reentrantReadWriteLock = layoutInfoLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            LayoutInfo layoutInfo2 = layoutInfo.get(cls);
            reentrantReadWriteLock.readLock().unlock();
            int access$300 = (layoutInfo2 == null || layoutInfo2.variable) ? -1 : layoutInfo2.size;
            if (access$300 != -1) {
                return access$300;
            }
            if (t2 == null) {
                t2 = newInstance(cls, PLACEHOLDER_MEMORY);
            }
            return t2.size();
        } catch (Throwable th) {
            layoutInfoLock.readLock().unlock();
            throw th;
        }
    }

    public Structure(Pointer pointer) {
        this(pointer, 0);
    }

    public Structure(Pointer pointer, int i3) {
        this(pointer, i3, (TypeMapper) null);
    }

    public static List<String> createFieldsOrder(String str) {
        return Collections.unmodifiableList(Collections.singletonList(str));
    }

    public Structure(Pointer pointer, int i3, TypeMapper typeMapper2) {
        this.size = -1;
        this.nativeStrings = new HashMap(8);
        this.autoRead = true;
        this.autoWrite = true;
        setAlignType(i3);
        setStringEncoding(Native.getStringEncoding(getClass()));
        initializeTypeMapper(typeMapper2);
        validateFields();
        if (pointer != null) {
            useMemory(pointer, 0, true);
        } else {
            allocateMemory(-1);
        }
        initializeFields();
    }

    public static List<String> createFieldsOrder(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    public void autoRead() {
        if (getAutoRead()) {
            read();
            if (this.array != null) {
                int i3 = 1;
                while (true) {
                    Structure[] structureArr = this.array;
                    if (i3 < structureArr.length) {
                        structureArr[i3].autoRead();
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void autoWrite() {
        if (getAutoWrite()) {
            write();
            if (this.array != null) {
                int i3 = 1;
                while (true) {
                    Structure[] structureArr = this.array;
                    if (i3 < structureArr.length) {
                        structureArr[i3].autoWrite();
                        i3++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public Object readField(StructField structField) {
        Object obj;
        int i3 = structField.offset;
        Class<?> cls = structField.type;
        FromNativeConverter fromNativeConverter = structField.readConverter;
        if (fromNativeConverter != null) {
            cls = fromNativeConverter.nativeType();
        }
        Object obj2 = null;
        if (Structure.class.isAssignableFrom(cls) || Callback.class.isAssignableFrom(cls) || ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) || Pointer.class.isAssignableFrom(cls) || NativeMapped.class.isAssignableFrom(cls) || cls.isArray())) {
            obj = getFieldValue(structField.field);
        } else {
            obj = null;
        }
        Class<String> cls2 = String.class;
        if (cls == cls2) {
            Pointer pointer = this.memory.getPointer((long) i3);
            if (pointer != null) {
                obj2 = pointer.getString(0, this.encoding);
            }
        } else {
            obj2 = this.memory.getValue((long) i3, cls, obj);
        }
        if (fromNativeConverter != null) {
            Object fromNative = fromNativeConverter.fromNative(obj2, structField.context);
            if (obj == null || !obj.equals(fromNative)) {
                obj = fromNative;
            }
        } else {
            obj = obj2;
        }
        if (cls.equals(cls2) || cls.equals(WString.class)) {
            if (obj != null) {
                NativeStringTracking nativeStringTracking = new NativeStringTracking(obj);
                NativeStringTracking put = this.nativeStrings.put(structField.name, nativeStringTracking);
                if (put != null) {
                    NativeString unused = nativeStringTracking.peer = put.peer;
                }
            } else {
                this.nativeStrings.remove(structField.name);
            }
        }
        setFieldValue(structField.field, obj, true);
        return obj;
    }

    public void writeField(String str, Object obj) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            setFieldValue(structField.field, obj);
            writeField(structField, obj);
            return;
        }
        throw new IllegalArgumentException(c.a("No such field: ", str));
    }

    public Structure[] toArray(int i3) {
        return toArray((Structure[]) Array.newInstance(getClass(), i3));
    }

    public void writeField(StructField structField) {
        if (!structField.isReadOnly) {
            writeField(structField, getFieldValue(structField.field));
        }
    }

    private void writeField(StructField structField, Object obj) {
        String str;
        NativeString nativeString;
        int i3 = structField.offset;
        Class<?> cls = structField.type;
        ToNativeConverter toNativeConverter = structField.writeConverter;
        if (toNativeConverter != null) {
            obj = toNativeConverter.toNative(obj, new StructureWriteContext(this, structField.field));
            cls = toNativeConverter.nativeType();
        }
        Class<WString> cls2 = WString.class;
        if (String.class == cls || cls2 == cls) {
            if (obj != null) {
                NativeStringTracking nativeStringTracking = new NativeStringTracking(obj);
                NativeStringTracking put = this.nativeStrings.put(structField.name, nativeStringTracking);
                if (put == null || !obj.equals(put.value)) {
                    if (cls == cls2) {
                        nativeString = new NativeString(obj.toString(), true);
                    } else {
                        nativeString = new NativeString(obj.toString(), this.encoding);
                    }
                    NativeString unused = nativeStringTracking.peer = nativeString;
                    obj = nativeString.getPointer();
                } else {
                    NativeString unused2 = nativeStringTracking.peer = put.peer;
                    return;
                }
            } else {
                this.nativeStrings.remove(structField.name);
            }
        }
        try {
            this.memory.setValue((long) i3, obj, cls);
        } catch (IllegalArgumentException e3) {
            StringBuilder sb = new StringBuilder("Structure field \"");
            sb.append(structField.name);
            sb.append("\" was declared as ");
            sb.append(structField.type);
            if (structField.type == cls) {
                str = "";
            } else {
                str = b.l(" (native type ", cls, ")");
            }
            throw new IllegalArgumentException(a.n(sb, str, ", which is not supported within a Structure"), e3);
        }
    }

    public static <T extends Structure> T newInstance(Class<T> cls) throws IllegalArgumentException {
        T t2 = (Structure) Klass.newInstance(cls);
        if (t2 instanceof ByValue) {
            t2.allocateMemory();
        }
        return t2;
    }
}
