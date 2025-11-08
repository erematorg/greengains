package com.fasterxml.jackson.databind.type;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class TypeFactory implements Serializable {
    private static final Class<?> CLS_BOOL;
    private static final Class<?> CLS_COMPARABLE;
    private static final Class<?> CLS_ENUM;
    private static final Class<?> CLS_INT;
    private static final Class<?> CLS_JSON_NODE;
    private static final Class<?> CLS_LONG;
    private static final Class<?> CLS_OBJECT;
    private static final Class<?> CLS_STRING;
    protected static final SimpleType CORE_TYPE_BOOL;
    protected static final SimpleType CORE_TYPE_COMPARABLE;
    protected static final SimpleType CORE_TYPE_ENUM;
    protected static final SimpleType CORE_TYPE_INT;
    protected static final SimpleType CORE_TYPE_JSON_NODE;
    protected static final SimpleType CORE_TYPE_LONG;
    protected static final SimpleType CORE_TYPE_OBJECT;
    protected static final SimpleType CORE_TYPE_STRING;
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    private static final long serialVersionUID = 1;
    protected final ClassLoader _classLoader;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LookupCache<Object, JavaType> _typeCache;

    static {
        Class<String> cls = String.class;
        CLS_STRING = cls;
        Class<Object> cls2 = Object.class;
        CLS_OBJECT = cls2;
        Class<Comparable> cls3 = Comparable.class;
        CLS_COMPARABLE = cls3;
        Class<Enum> cls4 = Enum.class;
        CLS_ENUM = cls4;
        Class<JsonNode> cls5 = JsonNode.class;
        CLS_JSON_NODE = cls5;
        Class<?> cls6 = Boolean.TYPE;
        CLS_BOOL = cls6;
        Class<?> cls7 = Integer.TYPE;
        CLS_INT = cls7;
        Class<?> cls8 = Long.TYPE;
        CLS_LONG = cls8;
        CORE_TYPE_BOOL = new SimpleType(cls6);
        CORE_TYPE_INT = new SimpleType(cls7);
        CORE_TYPE_LONG = new SimpleType(cls8);
        CORE_TYPE_STRING = new SimpleType((Class<?>) cls);
        CORE_TYPE_OBJECT = new SimpleType((Class<?>) cls2);
        CORE_TYPE_COMPARABLE = new SimpleType((Class<?>) cls3);
        CORE_TYPE_ENUM = new SimpleType((Class<?>) cls4);
        CORE_TYPE_JSON_NODE = new SimpleType((Class<?>) cls5);
    }

    private TypeFactory() {
        this((LookupCache<Object, JavaType>) null);
    }

    private TypeBindings _bindingsForSubtype(JavaType javaType, int i3, Class<?> cls, boolean z2) {
        PlaceholderForType[] placeholderForTypeArr = new PlaceholderForType[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            placeholderForTypeArr[i4] = new PlaceholderForType(i4);
        }
        JavaType findSuperType = _fromClass((ClassStack) null, cls, TypeBindings.create(cls, (JavaType[]) placeholderForTypeArr)).findSuperType(javaType.getRawClass());
        if (findSuperType != null) {
            String _resolveTypePlaceholders = _resolveTypePlaceholders(javaType, findSuperType);
            if (_resolveTypePlaceholders == null || z2) {
                JavaType[] javaTypeArr = new JavaType[i3];
                for (int i5 = 0; i5 < i3; i5++) {
                    JavaType actualType = placeholderForTypeArr[i5].actualType();
                    if (actualType == null) {
                        actualType = unknownType();
                    }
                    javaTypeArr[i5] = actualType;
                }
                return TypeBindings.create(cls, javaTypeArr);
            }
            throw new IllegalArgumentException("Failed to specialize base type " + javaType.toCanonical() + " as " + cls.getName() + ", problem: " + _resolveTypePlaceholders);
        }
        throw new IllegalArgumentException(C0118y.f("Internal error: unable to locate supertype (", javaType.getRawClass().getName(), ") from resolved subtype ", cls.getName()));
    }

    private JavaType _collectionType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType2;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType2 = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType2 = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException(b.g(cls, new StringBuilder("Strange Collection type "), ": cannot determine type parameters"));
        }
        return CollectionType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2);
    }

    private JavaType _mapType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType2;
        JavaType javaType3;
        JavaType _unknownType;
        if (cls == Properties.class) {
            _unknownType = CORE_TYPE_STRING;
        } else {
            List<JavaType> typeParameters = typeBindings.getTypeParameters();
            int size = typeParameters.size();
            if (size == 0) {
                _unknownType = _unknownType();
            } else if (size != 2) {
                throw new IllegalArgumentException(String.format("Strange Map type %s with %d type parameter%s (%s), can not resolve", new Object[]{ClassUtil.nameOf(cls), Integer.valueOf(size), size == 1 ? "" : "s", typeBindings}));
            } else {
                javaType3 = typeParameters.get(0);
                javaType2 = typeParameters.get(1);
                return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
            }
        }
        javaType3 = _unknownType;
        javaType2 = javaType3;
        return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
    }

    private JavaType _referenceType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        JavaType javaType2;
        List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType2 = _unknownType();
        } else if (typeParameters.size() == 1) {
            javaType2 = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException(b.g(cls, new StringBuilder("Strange Reference type "), ": cannot determine type parameters"));
        }
        return ReferenceType.construct(cls, typeBindings, javaType, javaTypeArr, javaType2);
    }

    private String _resolveTypePlaceholders(JavaType javaType, JavaType javaType2) throws IllegalArgumentException {
        List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        int size = typeParameters2.size();
        int size2 = typeParameters.size();
        int i3 = 0;
        while (i3 < size2) {
            JavaType javaType3 = typeParameters.get(i3);
            JavaType unknownType = i3 < size ? typeParameters2.get(i3) : unknownType();
            if (!_verifyAndResolvePlaceholders(javaType3, unknownType)) {
                Class<Object> cls = Object.class;
                if (!javaType3.hasRawClass(cls) && ((i3 != 0 || !javaType.isMapLikeType() || !unknownType.hasRawClass(cls)) && (!javaType3.isInterface() || !javaType3.isTypeOrSuperTypeOf(unknownType.getRawClass())))) {
                    return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", new Object[]{Integer.valueOf(i3 + 1), Integer.valueOf(size2), javaType3.toCanonical(), unknownType.toCanonical()});
                }
            }
            i3++;
        }
        return null;
    }

    private boolean _verifyAndResolvePlaceholders(JavaType javaType, JavaType javaType2) {
        if (javaType2 instanceof PlaceholderForType) {
            ((PlaceholderForType) javaType2).actualType(javaType);
            return true;
        } else if (javaType.getRawClass() != javaType2.getRawClass()) {
            return false;
        } else {
            List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
            List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
            int size = typeParameters.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (!_verifyAndResolvePlaceholders(typeParameters.get(i3), typeParameters2.get(i3))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public static Class<?> rawClass(Type type) {
        return type instanceof Class ? (Class) type : defaultInstance().constructType(type).getRawClass();
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public JavaType _applyModifiers(Type type, JavaType javaType) {
        if (this._modifiers == null) {
            return javaType;
        }
        TypeBindings bindings = javaType.getBindings();
        if (bindings == null) {
            bindings = EMPTY_BINDINGS;
        }
        TypeModifier[] typeModifierArr = this._modifiers;
        int length = typeModifierArr.length;
        int i3 = 0;
        while (i3 < length) {
            TypeModifier typeModifier = typeModifierArr[i3];
            JavaType modifyType = typeModifier.modifyType(javaType, type, bindings, this);
            if (modifyType != null) {
                i3++;
                javaType = modifyType;
            } else {
                throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", new Object[]{typeModifier, typeModifier.getClass().getName(), javaType}));
            }
        }
        return javaType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = _findWellKnownSimple(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType _constructSimple(java.lang.Class<?> r2, com.fasterxml.jackson.databind.type.TypeBindings r3, com.fasterxml.jackson.databind.JavaType r4, com.fasterxml.jackson.databind.JavaType[] r5) {
        /*
            r1 = this;
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x000d
            com.fasterxml.jackson.databind.JavaType r0 = r1._findWellKnownSimple(r2)
            if (r0 == 0) goto L_0x000d
            return r0
        L_0x000d:
            com.fasterxml.jackson.databind.JavaType r1 = r1._newSimpleType(r2, r3, r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.TypeFactory._constructSimple(java.lang.Class, com.fasterxml.jackson.databind.type.TypeBindings, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JavaType[]):com.fasterxml.jackson.databind.JavaType");
    }

    public Class<?> _findPrimitive(String str) {
        if ("int".equals(str)) {
            return Integer.TYPE;
        }
        if ("long".equals(str)) {
            return Long.TYPE;
        }
        if ("float".equals(str)) {
            return Float.TYPE;
        }
        if (SchemaSymbols.ATTVAL_DOUBLE.equals(str)) {
            return Double.TYPE;
        }
        if ("boolean".equals(str)) {
            return Boolean.TYPE;
        }
        if (SchemaSymbols.ATTVAL_BYTE.equals(str)) {
            return Byte.TYPE;
        }
        if ("char".equals(str)) {
            return Character.TYPE;
        }
        if (SchemaSymbols.ATTVAL_SHORT.equals(str)) {
            return Short.TYPE;
        }
        if ("void".equals(str)) {
            return Void.TYPE;
        }
        return null;
    }

    public JavaType _findWellKnownSimple(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == CLS_BOOL) {
                return CORE_TYPE_BOOL;
            }
            if (cls == CLS_INT) {
                return CORE_TYPE_INT;
            }
            if (cls == CLS_LONG) {
                return CORE_TYPE_LONG;
            }
            return null;
        } else if (cls == CLS_STRING) {
            return CORE_TYPE_STRING;
        } else {
            if (cls == CLS_OBJECT) {
                return CORE_TYPE_OBJECT;
            }
            if (cls == CLS_JSON_NODE) {
                return CORE_TYPE_JSON_NODE;
            }
            return null;
        }
    }

    public JavaType _fromAny(ClassStack classStack, Type type, TypeBindings typeBindings) {
        JavaType javaType;
        if (type instanceof Class) {
            javaType = _fromClass(classStack, (Class) type, EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            javaType = _fromParamType(classStack, (ParameterizedType) type, typeBindings);
        } else if (type instanceof JavaType) {
            return (JavaType) type;
        } else {
            if (type instanceof GenericArrayType) {
                javaType = _fromArrayType(classStack, (GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                javaType = _fromVariable(classStack, (TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                javaType = _fromWildcard(classStack, (WildcardType) type, typeBindings);
            } else {
                StringBuilder sb = new StringBuilder("Unrecognized Type: ");
                sb.append(type == null ? "[null]" : type.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return _applyModifiers(type, javaType);
    }

    public JavaType _fromArrayType(ClassStack classStack, GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_fromAny(classStack, genericArrayType.getGenericComponentType(), typeBindings), typeBindings);
    }

    public JavaType _fromClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        ClassStack classStack2;
        JavaType javaType;
        JavaType _resolveSuperClass;
        JavaType[] _resolveSuperInterfaces;
        JavaType _findWellKnownSimple = _findWellKnownSimple(cls);
        if (_findWellKnownSimple != null) {
            return _findWellKnownSimple;
        }
        Object asKey = (typeBindings == null || typeBindings.isEmpty()) ? cls : typeBindings.asKey(cls);
        JavaType javaType2 = this._typeCache.get(asKey);
        if (javaType2 != null) {
            return javaType2;
        }
        if (classStack == null) {
            classStack2 = new ClassStack(cls);
        } else {
            ClassStack find = classStack.find(cls);
            if (find != null) {
                ResolvedRecursiveType resolvedRecursiveType = new ResolvedRecursiveType(cls, EMPTY_BINDINGS);
                find.addSelfReference(resolvedRecursiveType);
                return resolvedRecursiveType;
            }
            classStack2 = classStack.child(cls);
        }
        if (cls.isArray()) {
            javaType = ArrayType.construct(_fromAny(classStack2, cls.getComponentType(), typeBindings), typeBindings);
        } else {
            if (cls.isInterface()) {
                _resolveSuperInterfaces = _resolveSuperInterfaces(classStack2, cls, typeBindings);
                _resolveSuperClass = null;
            } else {
                _resolveSuperClass = _resolveSuperClass(classStack2, cls, typeBindings);
                _resolveSuperInterfaces = _resolveSuperInterfaces(classStack2, cls, typeBindings);
            }
            JavaType[] javaTypeArr = _resolveSuperInterfaces;
            JavaType javaType3 = _resolveSuperClass;
            if (cls == Properties.class) {
                SimpleType simpleType = CORE_TYPE_STRING;
                javaType2 = MapType.construct(cls, typeBindings, javaType3, javaTypeArr, simpleType, simpleType);
            } else if (javaType3 != null) {
                javaType2 = javaType3.refine(cls, typeBindings, javaType3, javaTypeArr);
            }
            javaType = (javaType2 == null && (javaType2 = _fromWellKnownClass(classStack2, cls, typeBindings, javaType3, javaTypeArr)) == null && (javaType2 = _fromWellKnownInterface(classStack2, cls, typeBindings, javaType3, javaTypeArr)) == null) ? _newSimpleType(cls, typeBindings, javaType3, javaTypeArr) : javaType2;
        }
        classStack2.resolveSelfReferences(javaType);
        if (!javaType.hasHandlers()) {
            this._typeCache.putIfAbsent(asKey, javaType);
        }
        return javaType;
    }

    public JavaType _fromParamType(ClassStack classStack, ParameterizedType parameterizedType, TypeBindings typeBindings) {
        TypeBindings typeBindings2;
        Class<?> cls = (Class) parameterizedType.getRawType();
        if (cls == CLS_ENUM) {
            return CORE_TYPE_ENUM;
        }
        if (cls == CLS_COMPARABLE) {
            return CORE_TYPE_COMPARABLE;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            typeBindings2 = EMPTY_BINDINGS;
        } else {
            JavaType[] javaTypeArr = new JavaType[length];
            for (int i3 = 0; i3 < length; i3++) {
                javaTypeArr[i3] = _fromAny(classStack, actualTypeArguments[i3], typeBindings);
            }
            typeBindings2 = TypeBindings.create(cls, javaTypeArr);
        }
        return _fromClass(classStack, cls, typeBindings2);
    }

    public JavaType _fromVariable(ClassStack classStack, TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        Type[] bounds;
        String name = typeVariable.getName();
        if (typeBindings != null) {
            JavaType findBoundType = typeBindings.findBoundType(name);
            if (findBoundType != null) {
                return findBoundType;
            }
            if (typeBindings.hasUnbound(name)) {
                return CORE_TYPE_OBJECT;
            }
            TypeBindings withUnboundVariable = typeBindings.withUnboundVariable(name);
            synchronized (typeVariable) {
                bounds = typeVariable.getBounds();
            }
            return _fromAny(classStack, bounds[0], withUnboundVariable);
        }
        throw new IllegalArgumentException(a.l("Null `bindings` passed (type variable \"", name, "\")"));
    }

    public JavaType _fromWellKnownClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        if (typeBindings == null) {
            typeBindings = EMPTY_BINDINGS;
        }
        if (cls == Map.class) {
            return _mapType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls == Collection.class) {
            return _collectionType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (cls == AtomicReference.class) {
            return _referenceType(cls, typeBindings, javaType, javaTypeArr);
        }
        return null;
    }

    public JavaType _fromWellKnownInterface(ClassStack classStack, Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        for (JavaType refine : javaTypeArr) {
            JavaType refine2 = refine.refine(cls, typeBindings, javaType, javaTypeArr);
            if (refine2 != null) {
                return refine2;
            }
        }
        return null;
    }

    public JavaType _fromWildcard(ClassStack classStack, WildcardType wildcardType, TypeBindings typeBindings) {
        return _fromAny(classStack, wildcardType.getUpperBounds()[0], typeBindings);
    }

    public JavaType _newSimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new SimpleType(cls, typeBindings, javaType, javaTypeArr);
    }

    public JavaType _resolveSuperClass(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type genericSuperclass = ClassUtil.getGenericSuperclass(cls);
        if (genericSuperclass == null) {
            return null;
        }
        return _fromAny(classStack, genericSuperclass, typeBindings);
    }

    public JavaType[] _resolveSuperInterfaces(ClassStack classStack, Class<?> cls, TypeBindings typeBindings) {
        Type[] genericInterfaces = ClassUtil.getGenericInterfaces(cls);
        if (genericInterfaces == null || genericInterfaces.length == 0) {
            return NO_TYPES;
        }
        int length = genericInterfaces.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i3 = 0; i3 < length; i3++) {
            javaTypeArr[i3] = _fromAny(classStack, genericInterfaces[i3], typeBindings);
        }
        return javaTypeArr;
    }

    public JavaType _unknownType() {
        return CORE_TYPE_OBJECT;
    }

    public Class<?> classForName(String str, boolean z2, ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(str, true, classLoader);
    }

    public void clearCache() {
        this._typeCache.clear();
    }

    public ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_fromAny((ClassStack) null, cls, (TypeBindings) null), (TypeBindings) null);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return constructCollectionLikeType(cls, _fromClass((ClassStack) null, cls2, EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return constructCollectionType(cls, _fromClass((ClassStack) null, cls2, EMPTY_BINDINGS));
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public JavaType constructGeneralizedType(JavaType javaType, Class<?> cls) {
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        JavaType findSuperType = javaType.findSuperType(cls);
        if (findSuperType != null) {
            return findSuperType;
        }
        if (!cls.isAssignableFrom(rawClass)) {
            throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", new Object[]{cls.getName(), javaType}));
        }
        throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", new Object[]{cls.getName(), javaType}));
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        TypeBindings typeBindings = EMPTY_BINDINGS;
        return constructMapLikeType(cls, _fromClass((ClassStack) null, cls2, typeBindings), _fromClass((ClassStack) null, cls3, typeBindings));
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        JavaType javaType;
        JavaType javaType2;
        if (cls == Properties.class) {
            javaType2 = CORE_TYPE_STRING;
            javaType = javaType2;
        } else {
            TypeBindings typeBindings = EMPTY_BINDINGS;
            javaType2 = _fromClass((ClassStack) null, cls2, typeBindings);
            javaType = _fromClass((ClassStack) null, cls3, typeBindings);
        }
        return constructMapType(cls, javaType2, javaType);
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        int length = clsArr.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i3 = 0; i3 < length; i3++) {
            javaTypeArr[i3] = _fromClass((ClassStack) null, clsArr[i3], EMPTY_BINDINGS);
        }
        return constructParametricType(cls, javaTypeArr);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> cls, Class<?> cls2, JavaType... javaTypeArr) {
        return constructParametricType(cls, javaTypeArr);
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> cls) {
        return constructCollectionLikeType(cls, unknownType());
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> cls) {
        return constructCollectionType(cls, unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> cls) {
        return constructMapLikeType(cls, unknownType(), unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> cls) {
        return constructMapType(cls, unknownType(), unknownType());
    }

    public JavaType constructReferenceType(Class<?> cls, JavaType javaType) {
        return ReferenceType.construct(cls, TypeBindings.create(cls, javaType), (JavaType) null, (JavaType[]) null, javaType);
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        return _fromClass((ClassStack) null, cls, TypeBindings.create(cls, javaTypeArr));
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) throws IllegalArgumentException {
        return constructSpecializedType(javaType, cls, false);
    }

    public JavaType constructType(Type type) {
        return _fromAny((ClassStack) null, type, EMPTY_BINDINGS);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        Throwable th;
        Class<?> _findPrimitive;
        if (str.indexOf(46) < 0 && (_findPrimitive = _findPrimitive(str)) != null) {
            return _findPrimitive;
        }
        ClassLoader classLoader = getClassLoader();
        if (classLoader == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        if (classLoader != null) {
            try {
                return classForName(str, true, classLoader);
            } catch (Exception e3) {
                th = ClassUtil.getRootCause(e3);
            }
        } else {
            th = null;
            try {
                return classForName(str);
            } catch (Exception e4) {
                if (th == null) {
                    th = ClassUtil.getRootCause(e4);
                }
                ClassUtil.throwIfRTE(th);
                throw new ClassNotFoundException(th.getMessage(), th);
            }
        }
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        JavaType findSuperType = javaType.findSuperType(cls);
        if (findSuperType == null) {
            return NO_TYPES;
        }
        return findSuperType.getBindings().typeParameterArray();
    }

    public ClassLoader getClassLoader() {
        return this._classLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0006, code lost:
        r1 = r2.getRawClass();
        r0 = r3.getRawClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType moreSpecificType(com.fasterxml.jackson.databind.JavaType r2, com.fasterxml.jackson.databind.JavaType r3) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0003
            return r3
        L_0x0003:
            if (r3 != 0) goto L_0x0006
            return r2
        L_0x0006:
            java.lang.Class r1 = r2.getRawClass()
            java.lang.Class r0 = r3.getRawClass()
            if (r1 != r0) goto L_0x0011
            return r2
        L_0x0011:
            boolean r1 = r1.isAssignableFrom(r0)
            if (r1 == 0) goto L_0x0018
            return r3
        L_0x0018:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.TypeFactory.moreSpecificType(com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JavaType");
    }

    public JavaType resolveMemberType(Type type, TypeBindings typeBindings) {
        return _fromAny((ClassStack) null, type, typeBindings);
    }

    @Deprecated
    public JavaType uncheckedSimpleType(Class<?> cls) {
        return _constructSimple(cls, EMPTY_BINDINGS, (JavaType) null, (JavaType[]) null);
    }

    @Deprecated
    public TypeFactory withCache(LRUMap<Object, JavaType> lRUMap) {
        return new TypeFactory(lRUMap, this._parser, this._modifiers, this._classLoader);
    }

    public TypeFactory withClassLoader(ClassLoader classLoader) {
        return new TypeFactory(this._typeCache, this._parser, this._modifiers, classLoader);
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.type.TypeFactory withModifier(com.fasterxml.jackson.databind.type.TypeModifier r5) {
        /*
            r4 = this;
            com.fasterxml.jackson.databind.util.LookupCache<java.lang.Object, com.fasterxml.jackson.databind.JavaType> r0 = r4._typeCache
            r1 = 0
            if (r5 != 0) goto L_0x0007
            r0 = r1
            goto L_0x001c
        L_0x0007:
            com.fasterxml.jackson.databind.type.TypeModifier[] r2 = r4._modifiers
            if (r2 != 0) goto L_0x0015
            r0 = 1
            com.fasterxml.jackson.databind.type.TypeModifier[] r0 = new com.fasterxml.jackson.databind.type.TypeModifier[r0]
            r2 = 0
            r0[r2] = r5
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x001c
        L_0x0015:
            java.lang.Object[] r5 = com.fasterxml.jackson.databind.util.ArrayBuilders.insertInListNoDup(r2, r5)
            r1 = r5
            com.fasterxml.jackson.databind.type.TypeModifier[] r1 = (com.fasterxml.jackson.databind.type.TypeModifier[]) r1
        L_0x001c:
            com.fasterxml.jackson.databind.type.TypeFactory r5 = new com.fasterxml.jackson.databind.type.TypeFactory
            com.fasterxml.jackson.databind.type.TypeParser r2 = r4._parser
            java.lang.ClassLoader r4 = r4._classLoader
            r5.<init>((com.fasterxml.jackson.databind.util.LookupCache<java.lang.Object, com.fasterxml.jackson.databind.JavaType>) r0, (com.fasterxml.jackson.databind.type.TypeParser) r2, (com.fasterxml.jackson.databind.type.TypeModifier[]) r1, (java.lang.ClassLoader) r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.TypeFactory.withModifier(com.fasterxml.jackson.databind.type.TypeModifier):com.fasterxml.jackson.databind.type.TypeFactory");
    }

    @Deprecated
    public TypeFactory(LRUMap<Object, JavaType> lRUMap) {
        this((LookupCache<Object, JavaType>) lRUMap);
    }

    public Class<?> classForName(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    public ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType, (TypeBindings) null);
    }

    @Deprecated
    public JavaType constructParametrizedType(Class<?> cls, Class<?> cls2, Class<?>... clsArr) {
        return constructParametricType(cls, clsArr);
    }

    @Deprecated
    public JavaType constructSimpleType(Class<?> cls, Class<?> cls2, JavaType[] javaTypeArr) {
        return constructSimpleType(cls, javaTypeArr);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls, boolean z2) throws IllegalArgumentException {
        JavaType javaType2;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        if (rawClass == Object.class) {
            javaType2 = _fromClass((ClassStack) null, cls, EMPTY_BINDINGS);
        } else if (rawClass.isAssignableFrom(cls)) {
            if (javaType.isContainerType()) {
                if (javaType.isMapLikeType()) {
                    if (cls == HashMap.class || cls == LinkedHashMap.class || cls == EnumMap.class || cls == TreeMap.class) {
                        javaType2 = _fromClass((ClassStack) null, cls, TypeBindings.create(cls, javaType.getKeyType(), javaType.getContentType()));
                    }
                } else if (javaType.isCollectionLikeType()) {
                    if (cls == ArrayList.class || cls == LinkedList.class || cls == HashSet.class || cls == TreeSet.class) {
                        javaType2 = _fromClass((ClassStack) null, cls, TypeBindings.create(cls, javaType.getContentType()));
                    } else if (rawClass == EnumSet.class) {
                        return javaType;
                    }
                }
            }
            if (javaType.getBindings().isEmpty()) {
                javaType2 = _fromClass((ClassStack) null, cls, EMPTY_BINDINGS);
            } else {
                int length = cls.getTypeParameters().length;
                if (length == 0) {
                    javaType2 = _fromClass((ClassStack) null, cls, EMPTY_BINDINGS);
                } else {
                    javaType2 = _fromClass((ClassStack) null, cls, _bindingsForSubtype(javaType, length, cls, z2));
                }
            }
        } else {
            throw new IllegalArgumentException(C0118y.f("Class ", ClassUtil.nameOf(cls), " not subtype of ", ClassUtil.getTypeDescription(javaType)));
        }
        return javaType2.withHandlersFrom(javaType);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _fromAny((ClassStack) null, typeReference.getType(), EMPTY_BINDINGS);
    }

    public TypeFactory withCache(LookupCache<Object, JavaType> lookupCache) {
        return new TypeFactory(lookupCache, this._parser, this._modifiers, this._classLoader);
    }

    public TypeFactory(LookupCache<Object, JavaType> lookupCache) {
        this._typeCache = lookupCache == null ? new LRUMap<>(16, 200) : lookupCache;
        this._parser = new TypeParser(this);
        this._modifiers = null;
        this._classLoader = null;
    }

    @Deprecated
    public JavaType constructType(Type type, TypeBindings typeBindings) {
        if (type instanceof Class) {
            return _applyModifiers(type, _fromClass((ClassStack) null, (Class) type, typeBindings));
        }
        return _fromAny((ClassStack) null, type, typeBindings);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        JavaType _fromClass = _fromClass((ClassStack) null, cls, TypeBindings.createIfNeeded(cls, javaType));
        if (_fromClass instanceof CollectionLikeType) {
            return (CollectionLikeType) _fromClass;
        }
        return CollectionLikeType.upgradeFrom(_fromClass, javaType);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        TypeBindings createIfNeeded = TypeBindings.createIfNeeded((Class<?>) cls, javaType);
        CollectionType collectionType = (CollectionType) _fromClass((ClassStack) null, cls, createIfNeeded);
        if (createIfNeeded.isEmpty() && javaType != null) {
            JavaType contentType = collectionType.findSuperType(Collection.class).getContentType();
            if (!contentType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", new Object[]{ClassUtil.nameOf((Class<?>) cls), javaType, contentType}));
            }
        }
        return collectionType;
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        return findTypeParameters(constructType((Type) cls, typeBindings), cls2);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        JavaType _fromClass = _fromClass((ClassStack) null, cls, TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2}));
        if (_fromClass instanceof MapLikeType) {
            return (MapLikeType) _fromClass;
        }
        return MapLikeType.upgradeFrom(_fromClass, javaType, javaType2);
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        return constructParametricType(cls, TypeBindings.create(cls, javaTypeArr));
    }

    @Deprecated
    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        return findTypeParameters(constructType((Type) cls), cls2);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        TypeBindings createIfNeeded = TypeBindings.createIfNeeded((Class<?>) cls, new JavaType[]{javaType, javaType2});
        MapType mapType = (MapType) _fromClass((ClassStack) null, cls, createIfNeeded);
        if (createIfNeeded.isEmpty()) {
            JavaType findSuperType = mapType.findSuperType(Map.class);
            JavaType keyType = findSuperType.getKeyType();
            if (keyType.equals(javaType)) {
                JavaType contentType = findSuperType.getContentType();
                if (!contentType.equals(javaType2)) {
                    throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", new Object[]{ClassUtil.nameOf((Class<?>) cls), javaType2, contentType}));
                }
            } else {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", new Object[]{ClassUtil.nameOf((Class<?>) cls), javaType, keyType}));
            }
        }
        return mapType;
    }

    public JavaType constructParametricType(Class<?> cls, TypeBindings typeBindings) {
        return _applyModifiers(cls, _fromClass((ClassStack) null, cls, typeBindings));
    }

    @Deprecated
    public JavaType constructType(Type type, Class<?> cls) {
        return constructType(type, cls == null ? null : constructType((Type) cls));
    }

    @Deprecated
    public TypeFactory(LRUMap<Object, JavaType> lRUMap, TypeParser typeParser, TypeModifier[] typeModifierArr, ClassLoader classLoader) {
        this((LookupCache<Object, JavaType>) lRUMap, typeParser, typeModifierArr, classLoader);
    }

    @Deprecated
    public JavaType constructType(Type type, JavaType javaType) {
        TypeBindings typeBindings;
        if (javaType == null) {
            typeBindings = EMPTY_BINDINGS;
        } else {
            TypeBindings bindings = javaType.getBindings();
            if (type.getClass() != Class.class) {
                TypeBindings typeBindings2 = bindings;
                JavaType javaType2 = javaType;
                typeBindings = typeBindings2;
                while (typeBindings.isEmpty() && (javaType2 = javaType2.getSuperClass()) != null) {
                    typeBindings = javaType2.getBindings();
                }
            } else {
                typeBindings = bindings;
            }
        }
        return _fromAny((ClassStack) null, type, typeBindings);
    }

    public TypeFactory(LookupCache<Object, JavaType> lookupCache, TypeParser typeParser, TypeModifier[] typeModifierArr, ClassLoader classLoader) {
        this._typeCache = lookupCache == null ? new LRUMap<>(16, 200) : lookupCache;
        this._parser = typeParser.withFactory(this);
        this._modifiers = typeModifierArr;
        this._classLoader = classLoader;
    }
}
