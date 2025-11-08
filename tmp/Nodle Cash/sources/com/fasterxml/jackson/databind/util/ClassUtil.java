package com.fasterxml.jackson.databind.util;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import androidx.core.os.EnvironmentCompat;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.NoClass;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import okhttp3.internal.url._UrlKt;

public final class ClassUtil {
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Iterator<?> EMPTY_ITERATOR = Collections.emptyIterator();
    private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
    private static final Ctor[] NO_CTORS = new Ctor[0];

    public static final class Ctor {
        private transient Annotation[] _annotations;
        public final Constructor<?> _ctor;
        private transient Annotation[][] _paramAnnotations;
        private int _paramCount = -1;

        public Ctor(Constructor<?> constructor) {
            this._ctor = constructor;
        }

        public Constructor<?> getConstructor() {
            return this._ctor;
        }

        public Annotation[] getDeclaredAnnotations() {
            Annotation[] annotationArr = this._annotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            Annotation[] declaredAnnotations = this._ctor.getDeclaredAnnotations();
            this._annotations = declaredAnnotations;
            return declaredAnnotations;
        }

        public Class<?> getDeclaringClass() {
            return this._ctor.getDeclaringClass();
        }

        public int getParamCount() {
            int i3 = this._paramCount;
            if (i3 >= 0) {
                return i3;
            }
            int parameterCount = this._ctor.getParameterCount();
            this._paramCount = parameterCount;
            return parameterCount;
        }

        public Annotation[][] getParameterAnnotations() {
            Annotation[][] annotationArr = this._paramAnnotations;
            if (annotationArr != null) {
                return annotationArr;
            }
            Annotation[][] parameterAnnotations = this._ctor.getParameterAnnotations();
            this._paramAnnotations = parameterAnnotations;
            return parameterAnnotations;
        }
    }

    private static void _addRawSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z2) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z2) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addRawSuperTypes : _interfaces(cls)) {
                _addRawSuperTypes(_addRawSuperTypes, cls2, collection, true);
            }
            _addRawSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    private static void _addSuperTypes(JavaType javaType, Class<?> cls, Collection<JavaType> collection, boolean z2) {
        Class<?> rawClass;
        if (javaType != null && (rawClass = javaType.getRawClass()) != cls && rawClass != Object.class) {
            if (z2) {
                if (!collection.contains(javaType)) {
                    collection.add(javaType);
                } else {
                    return;
                }
            }
            for (JavaType _addSuperTypes : javaType.getInterfaces()) {
                _addSuperTypes(_addSuperTypes, cls, collection, true);
            }
            _addSuperTypes(javaType.getSuperClass(), cls, collection, true);
        }
    }

    private static Method[] _failGetClassMethods(Class<?> cls, Throwable th) throws IllegalArgumentException {
        String name = cls.getName();
        String name2 = th.getClass().getName();
        String message = th.getMessage();
        StringBuilder l2 = C0118y.l("Failed on call to `getDeclaredMethods()` on class `", name, "`, problem: (", name2, ") ");
        l2.append(message);
        throw new IllegalArgumentException(l2.toString(), th);
    }

    private static Class<?>[] _interfaces(Class<?> cls) {
        return cls.getInterfaces();
    }

    public static String apostrophed(String str) {
        if (str == null) {
            return "[null]";
        }
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('\'');
        sb.append(str);
        sb.append('\'');
        return sb.toString();
    }

    public static String backticked(String str) {
        if (str == null) {
            return "[null]";
        }
        StringBuilder sb = new StringBuilder(str.length() + 2);
        sb.append('`');
        sb.append(str);
        sb.append('`');
        return sb.toString();
    }

    public static String canBeABeanType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (Enum.class.isAssignableFrom(cls)) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return TreeJsonEncoderKt.PRIMITIVE_TAG;
        }
        return null;
    }

    @Deprecated
    public static void checkAndFixAccess(Member member) {
        checkAndFixAccess(member, false);
    }

    public static String classNameOf(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return nameOf(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static Class<?> classOf(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass();
    }

    public static void closeOnFailAndThrowAsIOE(JsonGenerator jsonGenerator, Exception exc) throws IOException {
        jsonGenerator.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
        try {
            jsonGenerator.close();
        } catch (Exception e3) {
            exc.addSuppressed(e3);
        }
        throwIfIOE(exc);
        throwIfRTE(exc);
        throw new RuntimeException(exc);
    }

    public static <T> T createInstance(Class<T> cls, boolean z2) throws IllegalArgumentException {
        Constructor<T> findConstructor = findConstructor(cls, z2);
        if (findConstructor != null) {
            try {
                return findConstructor.newInstance((Object[]) null);
            } catch (Exception e3) {
                unwrapAndThrowAsIAE(e3, "Failed to instantiate class " + cls.getName() + ", problem: " + e3.getMessage());
                return null;
            }
        } else {
            throw new IllegalArgumentException(b.g(cls, new StringBuilder("Class "), " has no default (no arg) constructor"));
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return 0;
        }
        if (cls == Long.TYPE) {
            return 0L;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (short) 0;
        }
        if (cls == Character.TYPE) {
            return 0;
        }
        throw new IllegalArgumentException(b.g(cls, new StringBuilder("Class "), " is not a primitive type"));
    }

    public static <T> Iterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public static String exceptionMessage(Throwable th) {
        return th instanceof JacksonException ? ((JacksonException) th).getOriginalMessage() : (!(th instanceof InvocationTargetException) || th.getCause() == null) ? th.getMessage() : th.getCause().getMessage();
    }

    public static Annotation[] findClassAnnotations(Class<?> cls) {
        return isObjectOrPrimitive(cls) ? NO_ANNOTATIONS : cls.getDeclaredAnnotations();
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z2) throws IllegalArgumentException {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
            if (z2) {
                checkAndFixAccess(declaredConstructor, z2);
            } else if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: cannot instantiate type");
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (Exception e3) {
            unwrapAndThrowAsIAE(e3, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e3.getMessage());
            return null;
        }
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        if (!enumSet.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumSet.iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumSet);
    }

    public static <T extends Annotation> Enum<?> findFirstAnnotatedEnumValue(Class<Enum<?>> cls, Class<T> cls2) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.isEnumConstant() && field.getAnnotation(cls2) != null) {
                String name = field.getName();
                for (Enum<?> enumR : (Enum[]) cls.getEnumConstants()) {
                    if (name.equals(enumR.name())) {
                        return enumR;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static List<Class<?>> findRawSuperTypes(Class<?> cls, Class<?> cls2, boolean z2) {
        if (cls == null || cls == cls2 || cls == Object.class) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        _addRawSuperTypes(cls, cls2, arrayList, z2);
        return arrayList;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Class<?>, code=java.lang.Class, for r2v0, types: [java.lang.Class<?>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.Class<?>> findSuperClasses(java.lang.Class r2, java.lang.Class<?> r3, boolean r4) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 8
            r0.<init>(r1)
            if (r2 == 0) goto L_0x001d
            if (r2 == r3) goto L_0x001d
            if (r4 == 0) goto L_0x0010
            r0.add(r2)
        L_0x0010:
            java.lang.Class r2 = r2.getSuperclass()
            if (r2 == 0) goto L_0x001d
            if (r2 != r3) goto L_0x0019
            goto L_0x001d
        L_0x0019:
            r0.add(r2)
            goto L_0x0010
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.ClassUtil.findSuperClasses(java.lang.Class, java.lang.Class, boolean):java.util.List");
    }

    public static List<JavaType> findSuperTypes(JavaType javaType, Class<?> cls, boolean z2) {
        if (javaType == null || javaType.hasRawClass(cls) || javaType.hasRawClass(Object.class)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        _addSuperTypes(javaType, cls, arrayList, z2);
        return arrayList;
    }

    public static String getClassDescription(Object obj) {
        if (obj == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return nameOf(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static Method[] getClassMethods(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e3) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader == null) {
                return _failGetClassMethods(cls, e3);
            }
            try {
                return contextClassLoader.loadClass(cls.getName()).getDeclaredMethods();
            } catch (ClassNotFoundException e4) {
                e3.addSuppressed(e4);
                return _failGetClassMethods(cls, e3);
            }
        } catch (Throwable th) {
            return _failGetClassMethods(cls, th);
        }
    }

    public static Ctor[] getConstructors(Class<?> cls) {
        if (cls.isInterface() || isObjectOrPrimitive(cls)) {
            return NO_CTORS;
        }
        Constructor[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        Ctor[] ctorArr = new Ctor[length];
        for (int i3 = 0; i3 < length; i3++) {
            ctorArr[i3] = new Ctor(declaredConstructors[i3]);
        }
        return ctorArr;
    }

    @Deprecated
    public static Field[] getDeclaredFields(Class<?> cls) {
        return cls.getDeclaredFields();
    }

    @Deprecated
    public static Method[] getDeclaredMethods(Class<?> cls) {
        return cls.getDeclaredMethods();
    }

    public static Class<?> getDeclaringClass(Class<?> cls) {
        if (isObjectOrPrimitive(cls)) {
            return null;
        }
        return cls.getDeclaringClass();
    }

    public static Class<?> getEnclosingClass(Class<?> cls) {
        if (isObjectOrPrimitive(cls)) {
            return null;
        }
        return cls.getEnclosingClass();
    }

    public static Type[] getGenericInterfaces(Class<?> cls) {
        return cls.getGenericInterfaces();
    }

    public static Type getGenericSuperclass(Class<?> cls) {
        return cls.getGenericSuperclass();
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        if (!Modifier.isStatic(cls.getModifiers())) {
            try {
                if (hasEnclosingMethod(cls)) {
                    return null;
                }
                return getEnclosingClass(cls);
            } catch (SecurityException unused) {
            }
        }
        return null;
    }

    @Deprecated
    public static String getPackageName(Class<?> cls) {
        Package packageR = cls.getPackage();
        if (packageR == null) {
            return null;
        }
        return packageR.getName();
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static String getTypeDescription(JavaType javaType) {
        if (javaType == null) {
            return "[null]";
        }
        StringBuilder sb = new StringBuilder(80);
        sb.append('`');
        sb.append(javaType.toCanonical());
        sb.append('`');
        return sb.toString();
    }

    public static boolean hasClass(Object obj, Class<?> cls) {
        return obj != null && obj.getClass() == cls;
    }

    public static boolean hasEnclosingMethod(Class<?> cls) {
        return !isObjectOrPrimitive(cls) && cls.getEnclosingMethod() != null;
    }

    @Deprecated
    public static boolean hasGetterSignature(Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        return (parameterTypes == null || parameterTypes.length == 0) && Void.TYPE != method.getReturnType();
    }

    public static boolean isBogusClass(Class<?> cls) {
        return cls == Void.class || cls == Void.TYPE || cls == NoClass.class;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        return cls.isArray() || Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isEnumType(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    public static boolean isJDKClass(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("java.") || name.startsWith("javax.");
    }

    public static boolean isJacksonStdImpl(Object obj) {
        return obj == null || isJacksonStdImpl(obj.getClass());
    }

    public static String isLocalType(Class<?> cls, boolean z2) {
        try {
            boolean isStatic = Modifier.isStatic(cls.getModifiers());
            if (!isStatic && hasEnclosingMethod(cls)) {
                return "local/anonymous";
            }
            if (z2 || isStatic || getEnclosingClass(cls) == null) {
                return null;
            }
            return "non-static member class";
        } catch (NullPointerException | SecurityException unused) {
            return null;
        }
    }

    public static boolean isNonStaticInnerClass(Class<?> cls) {
        return !Modifier.isStatic(cls.getModifiers()) && getEnclosingClass(cls) != null;
    }

    public static boolean isObjectOrPrimitive(Class<?> cls) {
        return cls == CLS_OBJECT || cls.isPrimitive();
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.");
    }

    public static boolean isRecordType(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        return superclass != null && "com.android.tools.r8.RecordTag".equals(superclass.getName());
    }

    public static String name(String str) {
        if (str == null) {
            return "[null]";
        }
        return apostrophed(str);
    }

    public static String nameOf(Class<?> cls) {
        if (cls == null) {
            return "[null]";
        }
        int i3 = 0;
        while (cls.isArray()) {
            i3++;
            cls = cls.getComponentType();
        }
        String simpleName = cls.isPrimitive() ? cls.getSimpleName() : cls.getName();
        if (i3 > 0) {
            StringBuilder sb = new StringBuilder(simpleName);
            do {
                sb.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
                i3--;
            } while (i3 > 0);
            simpleName = sb.toString();
        }
        return backticked(simpleName);
    }

    public static <T> T nonNull(T t2, T t3) {
        return t2 == null ? t3 : t2;
    }

    public static String nonNullString(String str) {
        return str == null ? "" : str;
    }

    public static String nullOrToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Class<?> primitiveType(Class<?> cls) {
        if (cls.isPrimitive()) {
            return cls;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Character.class) {
            return Character.TYPE;
        }
        return null;
    }

    public static String quotedOr(Object obj, String str) {
        return obj == null ? str : String.format("\"%s\"", new Object[]{obj});
    }

    public static Class<?> rawClass(JavaType javaType) {
        if (javaType == null) {
            return null;
        }
        return javaType.getRawClass();
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static <T> T throwAsMappingException(DeserializationContext deserializationContext, IOException iOException) throws JsonMappingException {
        if (iOException instanceof JsonMappingException) {
            throw ((JsonMappingException) iOException);
        }
        throw JsonMappingException.from(deserializationContext, iOException.getMessage()).withCause(iOException);
    }

    public static Throwable throwIfError(Throwable th) {
        if (!(th instanceof Error)) {
            return th;
        }
        throw ((Error) th);
    }

    public static Throwable throwIfIOE(Throwable th) throws IOException {
        if (!(th instanceof IOException)) {
            return th;
        }
        throw ((IOException) th);
    }

    public static Throwable throwIfRTE(Throwable th) {
        if (!(th instanceof RuntimeException)) {
            return th;
        }
        throw ((RuntimeException) th);
    }

    public static Throwable throwRootCauseIfIOE(Throwable th) throws IOException {
        return throwIfIOE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void verifyMustOverride(Class<?> cls, Object obj, String str) {
        if (obj.getClass() != cls) {
            throw new IllegalStateException(a.n(C0118y.l("Sub-class ", obj.getClass().getName(), " (of class ", cls.getName(), ") must override method '"), str, "'"));
        }
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException(b.g(cls, new StringBuilder("Class "), " is not a primitive type"));
    }

    public static void checkAndFixAccess(Member member, boolean z2) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            Class<?> declaringClass = member.getDeclaringClass();
            if (Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(declaringClass.getModifiers())) {
                if (!z2 || isJDKClass(declaringClass)) {
                    return;
                }
            }
            accessibleObject.setAccessible(true);
        } catch (SecurityException e3) {
            if (!accessibleObject.isAccessible()) {
                Class<?> declaringClass2 = member.getDeclaringClass();
                throw new IllegalArgumentException("Cannot access " + member + " (from class " + declaringClass2.getName() + "; failed to set access: " + e3.getMessage());
            }
        } catch (RuntimeException e4) {
            if ("InaccessibleObjectException".equals(e4.getClass().getSimpleName())) {
                String simpleName = member.getClass().getSimpleName();
                String name = member.getName();
                String nameOf = nameOf(member.getDeclaringClass());
                String name2 = e4.getClass().getName();
                String message = e4.getMessage();
                StringBuilder l2 = C0118y.l("Failed to call `setAccess()` on ", simpleName, " '", name, "' (of class ");
                b.w(l2, nameOf, ") due to `", name2, "`, problem: ");
                l2.append(message);
                throw new IllegalArgumentException(l2.toString(), e4);
            }
            throw e4;
        }
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }

    public static void throwAsIAE(Throwable th, String str) {
        throwIfRTE(th);
        throwIfError(th);
        throw new IllegalArgumentException(str, th);
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static class EnumTypeLocator {
        static final EnumTypeLocator instance = new EnumTypeLocator();
        private final Field enumMapTypeField;
        private final Field enumSetTypeField;
        private final String failForEnumMap;
        private final String failForEnumSet;

        private EnumTypeLocator() {
            String str;
            Field field;
            String str2;
            Class<Class> cls = Class.class;
            Field field2 = null;
            try {
                field = locateField(EnumSet.class, "elementType", cls);
                str = null;
            } catch (Exception e3) {
                str = e3.toString();
                field = null;
            }
            this.enumSetTypeField = field;
            this.failForEnumSet = str;
            try {
                field2 = locateField(EnumMap.class, "keyType", cls);
                str2 = null;
            } catch (Exception e4) {
                str2 = e4.toString();
            }
            this.enumMapTypeField = field2;
            this.failForEnumMap = str2;
        }

        private Object get(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Exception e3) {
                throw new IllegalArgumentException(e3);
            }
        }

        private static Field locateField(Class<?> cls, String str, Class<?> cls2) throws Exception {
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            int i3 = 0;
            while (i3 < length) {
                Field field = declaredFields[i3];
                if (!str.equals(field.getName()) || field.getType() != cls2) {
                    i3++;
                } else {
                    field.setAccessible(true);
                    return field;
                }
            }
            throw new IllegalStateException(C0118y.g("No field named '", str, "' in class '", cls.getName(), "'"));
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> enumSet) {
            Field field = this.enumSetTypeField;
            if (field != null) {
                return (Class) get(enumSet, field);
            }
            throw new IllegalStateException("Cannot figure out type parameter for `EnumSet` (odd JDK platform?), problem: " + this.failForEnumSet);
        }

        public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> enumMap) {
            Field field = this.enumMapTypeField;
            if (field != null) {
                return (Class) get(enumMap, field);
            }
            throw new IllegalStateException("Cannot figure out type parameter for `EnumMap` (odd JDK platform?), problem: " + this.failForEnumMap);
        }
    }

    public static String name(PropertyName propertyName) {
        if (propertyName == null) {
            return "[null]";
        }
        return apostrophed(propertyName.getSimpleName());
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        if (!enumMap.isEmpty()) {
            return findEnumType((Enum<?>) (Enum) enumMap.keySet().iterator().next());
        }
        return EnumTypeLocator.instance.enumTypeFor(enumMap);
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, (List<Class<?>>) new ArrayList(8));
    }

    @Deprecated
    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addRawSuperTypes(cls, cls2, list, false);
        return list;
    }

    public static void closeOnFailAndThrowAsIOE(JsonGenerator jsonGenerator, Closeable closeable, Exception exc) throws IOException {
        if (jsonGenerator != null) {
            jsonGenerator.disable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
            try {
                jsonGenerator.close();
            } catch (Exception e3) {
                exc.addSuppressed(e3);
            }
        }
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e4) {
                exc.addSuppressed(e4);
            }
        }
        throwIfIOE(exc);
        throwIfRTE(exc);
        throw new RuntimeException(exc);
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        return enumR.getDeclaringClass();
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static String nameOf(Named named) {
        if (named == null) {
            return "[null]";
        }
        return apostrophed(named.getName());
    }
}
