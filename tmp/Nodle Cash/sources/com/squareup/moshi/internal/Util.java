package com.squareup.moshi.internal;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonQualifier;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Types;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

public final class Util {
    @Nullable
    public static final Class<?> DEFAULT_CONSTRUCTOR_MARKER = DefaultConstructorMarker.class;
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
    @Nullable
    private static final Class<? extends Annotation> METADATA;
    public static final Set<Annotation> NO_ANNOTATIONS = Collections.emptySet();
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_TYPE;

    public static final class GenericArrayTypeImpl implements GenericArrayType {
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = Util.canonicalize(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && Types.equals(this, (GenericArrayType) obj);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return Util.typeToString(this.componentType) + _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    public static final class ParameterizedTypeImpl implements ParameterizedType {
        @Nullable
        private final Type ownerType;
        private final Type rawType;
        public final Type[] typeArguments;

        public ParameterizedTypeImpl(@Nullable Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class<?> enclosingClass = ((Class) type2).getEnclosingClass();
                if (type != null) {
                    if (enclosingClass == null || Types.getRawType(type) != enclosingClass) {
                        throw new IllegalArgumentException("unexpected owner type for " + type2 + ": " + type);
                    }
                } else if (enclosingClass != null) {
                    throw new IllegalArgumentException("unexpected owner type for " + type2 + ": null");
                }
            }
            this.ownerType = type == null ? null : Util.canonicalize(type);
            this.rawType = Util.canonicalize(type2);
            this.typeArguments = (Type[]) typeArr.clone();
            int i3 = 0;
            while (true) {
                Type[] typeArr2 = this.typeArguments;
                if (i3 < typeArr2.length) {
                    typeArr2[i3].getClass();
                    Util.checkNotPrimitive(this.typeArguments[i3]);
                    Type[] typeArr3 = this.typeArguments;
                    typeArr3[i3] = Util.canonicalize(typeArr3[i3]);
                    i3++;
                } else {
                    return;
                }
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && Types.equals(this, (ParameterizedType) obj);
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Nullable
        public Type getOwnerType() {
            return this.ownerType;
        }

        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return Util.hashCodeOrZero(this.ownerType) ^ (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder((this.typeArguments.length + 1) * 30);
            sb.append(Util.typeToString(this.rawType));
            if (this.typeArguments.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(Util.typeToString(this.typeArguments[0]));
            for (int i3 = 1; i3 < this.typeArguments.length; i3++) {
                sb.append(", ");
                sb.append(Util.typeToString(this.typeArguments[i3]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    public static final class WildcardTypeImpl implements WildcardType {
        @Nullable
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length > 1) {
                throw new IllegalArgumentException();
            } else if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            } else if (typeArr2.length == 1) {
                typeArr2[0].getClass();
                Util.checkNotPrimitive(typeArr2[0]);
                Class<Object> cls = Object.class;
                if (typeArr[0] == cls) {
                    this.lowerBound = Util.canonicalize(typeArr2[0]);
                    this.upperBound = cls;
                    return;
                }
                throw new IllegalArgumentException();
            } else {
                typeArr[0].getClass();
                Util.checkNotPrimitive(typeArr[0]);
                this.lowerBound = null;
                this.upperBound = Util.canonicalize(typeArr[0]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && Types.equals(this, (WildcardType) obj);
        }

        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            if (type == null) {
                return Util.EMPTY_TYPE_ARRAY;
            }
            return new Type[]{type};
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (this.upperBound.hashCode() + 31) ^ (type != null ? type.hashCode() + 31 : 1);
        }

        public String toString() {
            if (this.lowerBound != null) {
                return "? super " + Util.typeToString(this.lowerBound);
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                return "? extends " + Util.typeToString(this.upperBound);
            }
        }
    }

    static {
        Class<?> cls;
        try {
            cls = Class.forName(getKotlinMetadataClassName());
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        METADATA = cls;
        LinkedHashMap linkedHashMap = new LinkedHashMap(16);
        linkedHashMap.put(Boolean.TYPE, Boolean.class);
        linkedHashMap.put(Byte.TYPE, Byte.class);
        linkedHashMap.put(Character.TYPE, Character.class);
        linkedHashMap.put(Double.TYPE, Double.class);
        linkedHashMap.put(Float.TYPE, Float.class);
        linkedHashMap.put(Integer.TYPE, Integer.class);
        linkedHashMap.put(Long.TYPE, Long.class);
        linkedHashMap.put(Short.TYPE, Short.class);
        linkedHashMap.put(Void.TYPE, Void.class);
        PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(linkedHashMap);
    }

    private Util() {
    }

    public static <T> Class<T> boxIfPrimitive(Class<T> cls) {
        Class<T> cls2 = PRIMITIVE_TO_WRAPPER_TYPE.get(cls);
        return cls2 == null ? cls : cls2;
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new GenericArrayTypeImpl(canonicalize(cls.getComponentType())) : cls;
        } else if (type instanceof ParameterizedType) {
            if (type instanceof ParameterizedTypeImpl) {
                return type;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return type instanceof GenericArrayTypeImpl ? type : new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType) || (type instanceof WildcardTypeImpl)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static void checkNotPrimitive(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            throw new IllegalArgumentException("Unexpected primitive " + type + ". Use the boxed type.");
        }
    }

    @Nullable
    public static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    private static <T> Constructor<T> findConstructor(Class<T> cls) {
        for (Constructor<T> constructor : cls.getDeclaredConstructors()) {
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != 0 && parameterTypes[parameterTypes.length - 1].equals(DEFAULT_CONSTRUCTOR_MARKER)) {
                return constructor;
            }
        }
        throw new IllegalStateException(b.k("No defaults constructor found for ", cls));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2 = r7.getDeclaredConstructor(new java.lang.Class[]{r0});
        r5 = new java.lang.Object[]{r1};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        r5 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2 = r7.getDeclaredConstructor((java.lang.Class[]) null);
        r5 = new java.lang.Object[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0082, code lost:
        throw rethrowCause(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008e, code lost:
        throw new java.lang.RuntimeException(androidx.constraintlayout.core.state.b.n("Failed to instantiate the generated JsonAdapter for ", r6), r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009a, code lost:
        throw new java.lang.RuntimeException(androidx.constraintlayout.core.state.b.n("Failed to access the generated JsonAdapter for ", r6), r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ca, code lost:
        throw new java.lang.RuntimeException("Failed to find the generated JsonAdapter constructor for '" + r6 + "'. Suspiciously, the type was not parameterized but the target class '" + r2.getCanonicalName() + "' is generic. Consider using Types#newParameterizedType() to define these missing type variables.", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d6, code lost:
        throw new java.lang.RuntimeException(androidx.constraintlayout.core.state.b.n("Failed to find the generated JsonAdapter constructor for ", r6), r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e2, code lost:
        throw new java.lang.RuntimeException(androidx.constraintlayout.core.state.b.n("Failed to find the generated JsonAdapter class for ", r6), r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0049 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0067 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040 A[ExcHandler: InvocationTargetException (r5v6 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:5:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[ExcHandler: InstantiationException (r5v5 'e' java.lang.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:5:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[ExcHandler: IllegalAccessException (r5v4 'e' java.lang.IllegalAccessException A[CUSTOM_DECLARE]), Splitter:B:5:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0046 A[ExcHandler: ClassNotFoundException (r5v3 'e' java.lang.ClassNotFoundException A[CUSTOM_DECLARE]), Splitter:B:5:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cb  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x0033=Splitter:B:14:0x0033, B:24:0x0059=Splitter:B:24:0x0059} */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.squareup.moshi.JsonAdapter<?> generatedAdapter(com.squareup.moshi.Moshi r5, java.lang.reflect.Type r6, java.lang.Class<?> r7) {
        /*
            java.lang.Class<java.lang.reflect.Type[]> r0 = java.lang.reflect.Type[].class
            java.lang.Class<com.squareup.moshi.JsonClass> r1 = com.squareup.moshi.JsonClass.class
            java.lang.annotation.Annotation r1 = r7.getAnnotation(r1)
            com.squareup.moshi.JsonClass r1 = (com.squareup.moshi.JsonClass) r1
            r2 = 0
            if (r1 == 0) goto L_0x00e3
            boolean r1 = r1.generateAdapter()
            if (r1 != 0) goto L_0x0015
            goto L_0x00e3
        L_0x0015:
            java.lang.String r1 = r7.getName()
            java.lang.String r1 = com.squareup.moshi.Types.generatedJsonAdapterName((java.lang.String) r1)
            java.lang.ClassLoader r7 = r7.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            r3 = 1
            java.lang.Class r7 = java.lang.Class.forName(r1, r3, r7)     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            boolean r1 = r6 instanceof java.lang.reflect.ParameterizedType     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Class<com.squareup.moshi.Moshi> r4 = com.squareup.moshi.Moshi.class
            if (r1 == 0) goto L_0x0059
            r1 = r6
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Class[] r2 = new java.lang.Class[]{r4, r0}     // Catch:{ NoSuchMethodException -> 0x0049, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.reflect.Constructor r2 = r7.getDeclaredConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0049, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r1}     // Catch:{ NoSuchMethodException -> 0x0049, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            goto L_0x006e
        L_0x0040:
            r5 = move-exception
            goto L_0x007e
        L_0x0042:
            r5 = move-exception
            goto L_0x0083
        L_0x0044:
            r5 = move-exception
            goto L_0x008f
        L_0x0046:
            r5 = move-exception
            goto L_0x00d7
        L_0x0049:
            java.lang.Class[] r5 = new java.lang.Class[]{r0}     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.reflect.Constructor r2 = r7.getDeclaredConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Object[] r5 = new java.lang.Object[]{r1}     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            goto L_0x006e
        L_0x0056:
            r5 = move-exception
            r2 = r7
            goto L_0x009b
        L_0x0059:
            java.lang.Class[] r0 = new java.lang.Class[]{r4}     // Catch:{ NoSuchMethodException -> 0x0067, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.reflect.Constructor r0 = r7.getDeclaredConstructor(r0)     // Catch:{ NoSuchMethodException -> 0x0067, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch:{ NoSuchMethodException -> 0x0067, ClassNotFoundException -> 0x0046, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            r2 = r0
            goto L_0x006e
        L_0x0067:
            java.lang.reflect.Constructor r2 = r7.getDeclaredConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
        L_0x006e:
            r2.setAccessible(r3)     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            java.lang.Object r5 = r2.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            com.squareup.moshi.JsonAdapter r5 = (com.squareup.moshi.JsonAdapter) r5     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            com.squareup.moshi.JsonAdapter r5 = r5.nullSafe()     // Catch:{ ClassNotFoundException -> 0x0046, NoSuchMethodException -> 0x0056, IllegalAccessException -> 0x0044, InstantiationException -> 0x0042, InvocationTargetException -> 0x0040 }
            return r5
        L_0x007c:
            r5 = move-exception
            goto L_0x009b
        L_0x007e:
            java.lang.RuntimeException r5 = rethrowCause(r5)
            throw r5
        L_0x0083:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r0 = "Failed to instantiate the generated JsonAdapter for "
            java.lang.String r6 = androidx.constraintlayout.core.state.b.n(r0, r6)
            r7.<init>(r6, r5)
            throw r7
        L_0x008f:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r0 = "Failed to access the generated JsonAdapter for "
            java.lang.String r6 = androidx.constraintlayout.core.state.b.n(r0, r6)
            r7.<init>(r6, r5)
            throw r7
        L_0x009b:
            boolean r7 = r6 instanceof java.lang.reflect.ParameterizedType
            if (r7 != 0) goto L_0x00cb
            java.lang.reflect.TypeVariable[] r7 = r2.getTypeParameters()
            int r7 = r7.length
            if (r7 == 0) goto L_0x00cb
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Failed to find the generated JsonAdapter constructor for '"
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = "'. Suspiciously, the type was not parameterized but the target class '"
            r0.append(r6)
            java.lang.String r6 = r2.getCanonicalName()
            r0.append(r6)
            java.lang.String r6 = "' is generic. Consider using Types#newParameterizedType() to define these missing type variables."
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r7.<init>(r6, r5)
            throw r7
        L_0x00cb:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r0 = "Failed to find the generated JsonAdapter constructor for "
            java.lang.String r6 = androidx.constraintlayout.core.state.b.n(r0, r6)
            r7.<init>(r6, r5)
            throw r7
        L_0x00d7:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException
            java.lang.String r0 = "Failed to find the generated JsonAdapter class for "
            java.lang.String r6 = androidx.constraintlayout.core.state.b.n(r0, r6)
            r7.<init>(r6, r5)
            throw r7
        L_0x00e3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.internal.Util.generatedAdapter(com.squareup.moshi.Moshi, java.lang.reflect.Type, java.lang.Class):com.squareup.moshi.JsonAdapter");
    }

    public static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i3 = 0; i3 < length; i3++) {
                Class<?> cls3 = interfaces[i3];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i3];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return getGenericSupertype(cls.getGenericInterfaces()[i3], interfaces[i3], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    private static String getKotlinMetadataClassName() {
        return "kotlin.Metadata";
    }

    public static boolean hasNullable(Annotation[] annotationArr) {
        for (Annotation annotationType : annotationArr) {
            if (annotationType.annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }

    public static int hashCodeOrZero(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static int indexOf(Object[] objArr, Object obj) {
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (obj.equals(objArr[i3])) {
                return i3;
            }
        }
        throw new NoSuchElementException();
    }

    public static boolean isAnnotationPresent(Set<? extends Annotation> set, Class<? extends Annotation> cls) {
        if (set.isEmpty()) {
            return false;
        }
        for (Annotation annotationType : set) {
            if (annotationType.annotationType() == cls) {
                return true;
            }
        }
        return false;
    }

    public static boolean isKotlin(Class<?> cls) {
        Class<? extends Annotation> cls2 = METADATA;
        return cls2 != null && cls.isAnnotationPresent(cls2);
    }

    public static boolean isPlatformType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("android.") || name.startsWith("androidx.") || name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("kotlinx.") || name.startsWith("scala.");
    }

    public static Set<? extends Annotation> jsonAnnotations(AnnotatedElement annotatedElement) {
        return jsonAnnotations(annotatedElement.getAnnotations());
    }

    public static String jsonName(String str, AnnotatedElement annotatedElement) {
        return jsonName(str, (Json) annotatedElement.getAnnotation(Json.class));
    }

    public static <T> Constructor<T> lookupDefaultsConstructor(Class<T> cls) {
        if (DEFAULT_CONSTRUCTOR_MARKER != null) {
            Constructor<T> findConstructor = findConstructor(cls);
            findConstructor.setAccessible(true);
            return findConstructor;
        }
        throw new IllegalStateException("DefaultConstructorMarker not on classpath. Make sure the Kotlin stdlib is on the classpath.");
    }

    public static JsonDataException missingProperty(String str, String str2, JsonReader jsonReader) {
        String str3;
        String path = jsonReader.getPath();
        if (str2.equals(str)) {
            str3 = C0118y.f("Required value '", str, "' missing at ", path);
        } else {
            StringBuilder l2 = C0118y.l("Required value '", str, "' (JSON name '", str2, "') missing at ");
            l2.append(path);
            str3 = l2.toString();
        }
        return new JsonDataException(str3);
    }

    public static Type removeSubtypeWildcard(Type type) {
        if (!(type instanceof WildcardType)) {
            return type;
        }
        WildcardType wildcardType = (WildcardType) type;
        if (wildcardType.getLowerBounds().length != 0) {
            return type;
        }
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length == 1) {
            return upperBounds[0];
        }
        throw new IllegalArgumentException();
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        return resolve(type, cls, type2, new LinkedHashSet());
    }

    public static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type genericSupertype = getGenericSupertype(type, cls, declaringClassOf);
        if (!(genericSupertype instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) genericSupertype).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
    }

    public static RuntimeException rethrowCause(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException instanceof RuntimeException) {
            throw ((RuntimeException) targetException);
        } else if (targetException instanceof Error) {
            throw ((Error) targetException);
        } else {
            throw new RuntimeException(targetException);
        }
    }

    public static String typeAnnotatedWithAnnotations(Type type, Set<? extends Annotation> set) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        if (set.isEmpty()) {
            str = " (with no annotations)";
        } else {
            str = " annotated " + set;
        }
        sb.append(str);
        return sb.toString();
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static boolean typesMatch(Type type, Type type2) {
        return Types.equals(type, type2);
    }

    public static JsonDataException unexpectedNull(String str, String str2, JsonReader jsonReader) {
        String str3;
        String path = jsonReader.getPath();
        if (str2.equals(str)) {
            str3 = C0118y.f("Non-null value '", str, "' was null at ", path);
        } else {
            StringBuilder l2 = C0118y.l("Non-null value '", str, "' (JSON name '", str2, "') was null at ");
            l2.append(path);
            str3 = l2.toString();
        }
        return new JsonDataException(str3);
    }

    public static Set<? extends Annotation> jsonAnnotations(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : NO_ANNOTATIONS;
    }

    public static String jsonName(String str, @Nullable Json json) {
        if (json == null) {
            return str;
        }
        String name = json.name();
        return Json.UNSET_NAME.equals(name) ? str : name;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.reflect.Type[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v14, resolved type: java.lang.reflect.WildcardType} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.reflect.Type resolve(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10, java.util.Collection<java.lang.reflect.TypeVariable<?>> r11) {
        /*
        L_0x0000:
            boolean r0 = r10 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L_0x0018
            r0 = r10
            java.lang.reflect.TypeVariable r0 = (java.lang.reflect.TypeVariable) r0
            boolean r1 = r11.contains(r0)
            if (r1 == 0) goto L_0x000e
            return r10
        L_0x000e:
            r11.add(r0)
            java.lang.reflect.Type r10 = resolveTypeVariable(r8, r9, r0)
            if (r10 != r0) goto L_0x0000
            return r10
        L_0x0018:
            boolean r0 = r10 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0035
            r0 = r10
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r1 = r0.isArray()
            if (r1 == 0) goto L_0x0035
            java.lang.Class r10 = r0.getComponentType()
            java.lang.reflect.Type r8 = resolve(r8, r9, r10, r11)
            if (r10 != r8) goto L_0x0030
            goto L_0x0034
        L_0x0030:
            java.lang.reflect.GenericArrayType r0 = com.squareup.moshi.Types.arrayOf(r8)
        L_0x0034:
            return r0
        L_0x0035:
            boolean r0 = r10 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x004b
            java.lang.reflect.GenericArrayType r10 = (java.lang.reflect.GenericArrayType) r10
            java.lang.reflect.Type r0 = r10.getGenericComponentType()
            java.lang.reflect.Type r8 = resolve(r8, r9, r0, r11)
            if (r0 != r8) goto L_0x0046
            goto L_0x004a
        L_0x0046:
            java.lang.reflect.GenericArrayType r10 = com.squareup.moshi.Types.arrayOf(r8)
        L_0x004a:
            return r10
        L_0x004b:
            boolean r0 = r10 instanceof java.lang.reflect.ParameterizedType
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x008d
            java.lang.reflect.ParameterizedType r10 = (java.lang.reflect.ParameterizedType) r10
            java.lang.reflect.Type r0 = r10.getOwnerType()
            java.lang.reflect.Type r3 = resolve(r8, r9, r0, r11)
            if (r3 == r0) goto L_0x005f
            r0 = r1
            goto L_0x0060
        L_0x005f:
            r0 = r2
        L_0x0060:
            java.lang.reflect.Type[] r4 = r10.getActualTypeArguments()
            int r5 = r4.length
        L_0x0065:
            if (r2 >= r5) goto L_0x0080
            r6 = r4[r2]
            java.lang.reflect.Type r6 = resolve(r8, r9, r6, r11)
            r7 = r4[r2]
            if (r6 == r7) goto L_0x007d
            if (r0 != 0) goto L_0x007b
            java.lang.Object r0 = r4.clone()
            r4 = r0
            java.lang.reflect.Type[] r4 = (java.lang.reflect.Type[]) r4
            r0 = r1
        L_0x007b:
            r4[r2] = r6
        L_0x007d:
            int r2 = r2 + 1
            goto L_0x0065
        L_0x0080:
            if (r0 == 0) goto L_0x008c
            com.squareup.moshi.internal.Util$ParameterizedTypeImpl r8 = new com.squareup.moshi.internal.Util$ParameterizedTypeImpl
            java.lang.reflect.Type r9 = r10.getRawType()
            r8.<init>(r3, r9, r4)
            r10 = r8
        L_0x008c:
            return r10
        L_0x008d:
            boolean r0 = r10 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x00bf
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r0 = r10.getLowerBounds()
            java.lang.reflect.Type[] r3 = r10.getUpperBounds()
            int r4 = r0.length
            if (r4 != r1) goto L_0x00ad
            r1 = r0[r2]
            java.lang.reflect.Type r8 = resolve(r8, r9, r1, r11)
            r9 = r0[r2]
            if (r8 == r9) goto L_0x00bf
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.Types.supertypeOf(r8)
            return r8
        L_0x00ad:
            int r0 = r3.length
            if (r0 != r1) goto L_0x00bf
            r0 = r3[r2]
            java.lang.reflect.Type r8 = resolve(r8, r9, r0, r11)
            r9 = r3[r2]
            if (r8 == r9) goto L_0x00bf
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.Types.subtypeOf(r8)
            return r8
        L_0x00bf:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.internal.Util.resolve(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Collection):java.lang.reflect.Type");
    }
}
