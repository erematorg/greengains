package com.google.gson.reflect;

import androidx.constraintlayout.core.state.b;
import com.google.gson.internal.GsonTypes;
import com.google.gson.internal.TroubleshootingGuide;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TypeToken<T> {
    private final int hashCode;
    private final Class<? super T> rawType;
    private final Type type;

    public TypeToken() {
        Type typeTokenTypeArgument = getTypeTokenTypeArgument();
        this.type = typeTokenTypeArgument;
        this.rawType = GsonTypes.getRawType(typeTokenTypeArgument);
        this.hashCode = typeTokenTypeArgument.hashCode();
    }

    private static IllegalArgumentException buildUnsupportedTypeException(Type type2, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder("Unsupported type, expected one of: ");
        for (Class<?> name : clsArr) {
            sb.append(name.getName());
            sb.append(", ");
        }
        sb.append("but got: ");
        sb.append(type2.getClass().getName());
        sb.append(", for type token: ");
        sb.append(type2.toString());
        return new IllegalArgumentException(sb.toString());
    }

    public static TypeToken<?> get(Type type2) {
        return new TypeToken<>(type2);
    }

    public static TypeToken<?> getArray(Type type2) {
        return new TypeToken<>(GsonTypes.arrayOf(type2));
    }

    public static TypeToken<?> getParameterized(Type type2, Type... typeArr) {
        Objects.requireNonNull(type2);
        Objects.requireNonNull(typeArr);
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            TypeVariable[] typeParameters = cls.getTypeParameters();
            int length = typeParameters.length;
            int length2 = typeArr.length;
            if (length2 != length) {
                throw new IllegalArgumentException(cls.getName() + " requires " + length + " type arguments, but got " + length2);
            } else if (typeArr.length == 0) {
                return get(cls);
            } else {
                if (!GsonTypes.requiresOwnerType(type2)) {
                    for (int i3 = 0; i3 < length; i3++) {
                        Type type3 = typeArr[i3];
                        Objects.requireNonNull(type3, "Type argument must not be null");
                        Type type4 = type3;
                        Class<?> rawType2 = GsonTypes.getRawType(type4);
                        TypeVariable typeVariable = typeParameters[i3];
                        Type[] bounds = typeVariable.getBounds();
                        int length3 = bounds.length;
                        int i4 = 0;
                        while (i4 < length3) {
                            if (GsonTypes.getRawType(bounds[i4]).isAssignableFrom(rawType2)) {
                                i4++;
                            } else {
                                throw new IllegalArgumentException("Type argument " + type4 + " does not satisfy bounds for type variable " + typeVariable + " declared by " + type2);
                            }
                        }
                    }
                    return new TypeToken<>(GsonTypes.newParameterizedTypeWithOwner((Type) null, cls, typeArr));
                }
                throw new IllegalArgumentException(b.g(cls, new StringBuilder("Raw type "), " is not supported because it requires specifying an owner type"));
            }
        } else {
            throw new IllegalArgumentException(b.n("rawType must be of type Class, but was ", type2));
        }
    }

    private Type getTypeTokenTypeArgument() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Class<TypeToken> cls = TypeToken.class;
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType() == cls) {
                Type canonicalize = GsonTypes.canonicalize(parameterizedType.getActualTypeArguments()[0]);
                if (isCapturingTypeVariablesForbidden()) {
                    verifyNoTypeVariable(canonicalize);
                }
                return canonicalize;
            }
        } else if (genericSuperclass == cls) {
            throw new IllegalStateException("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.\nSee " + TroubleshootingGuide.createUrl("type-token-raw"));
        }
        throw new IllegalStateException("Must only create direct subclasses of TypeToken");
    }

    private static boolean isCapturingTypeVariablesForbidden() {
        return !Objects.equals(System.getProperty("gson.allowCapturingTypeVariables"), "true");
    }

    private static boolean matches(Type type2, Type type3, Map<String, Type> map) {
        return type3.equals(type2) || ((type2 instanceof TypeVariable) && type3.equals(map.get(((TypeVariable) type2).getName())));
    }

    private static boolean typeEquals(ParameterizedType parameterizedType, ParameterizedType parameterizedType2, Map<String, Type> map) {
        if (!parameterizedType.getRawType().equals(parameterizedType2.getRawType())) {
            return false;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
        for (int i3 = 0; i3 < actualTypeArguments.length; i3++) {
            if (!matches(actualTypeArguments[i3], actualTypeArguments2[i3], map)) {
                return false;
            }
        }
        return true;
    }

    private static void verifyNoTypeVariable(Type type2) {
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            throw new IllegalArgumentException("TypeToken type argument must not contain a type variable; captured type variable " + typeVariable.getName() + " declared by " + typeVariable.getGenericDeclaration() + "\nSee " + TroubleshootingGuide.createUrl("typetoken-type-variable"));
        } else if (type2 instanceof GenericArrayType) {
            verifyNoTypeVariable(((GenericArrayType) type2).getGenericComponentType());
        } else {
            int i3 = 0;
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type2;
                Type ownerType = parameterizedType.getOwnerType();
                if (ownerType != null) {
                    verifyNoTypeVariable(ownerType);
                }
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                int length = actualTypeArguments.length;
                while (i3 < length) {
                    verifyNoTypeVariable(actualTypeArguments[i3]);
                    i3++;
                }
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                for (Type verifyNoTypeVariable : wildcardType.getLowerBounds()) {
                    verifyNoTypeVariable(verifyNoTypeVariable);
                }
                Type[] upperBounds = wildcardType.getUpperBounds();
                int length2 = upperBounds.length;
                while (i3 < length2) {
                    verifyNoTypeVariable(upperBounds[i3]);
                    i3++;
                }
            } else if (type2 == null) {
                throw new IllegalArgumentException("TypeToken captured `null` as type argument; probably a compiler / runtime bug");
            }
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof TypeToken) && GsonTypes.equals(this.type, ((TypeToken) obj).type);
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return isAssignableFrom((Type) cls);
    }

    public final String toString() {
        return GsonTypes.typeToString(this.type);
    }

    public static <T> TypeToken<T> get(Class<T> cls) {
        return new TypeToken<>(cls);
    }

    @Deprecated
    public boolean isAssignableFrom(Type type2) {
        if (type2 == null) {
            return false;
        }
        if (this.type.equals(type2)) {
            return true;
        }
        Type type3 = this.type;
        if (type3 instanceof Class) {
            return this.rawType.isAssignableFrom(GsonTypes.getRawType(type2));
        }
        if (type3 instanceof ParameterizedType) {
            return isAssignableFrom(type2, (ParameterizedType) type3, new HashMap());
        }
        if (!(type3 instanceof GenericArrayType)) {
            throw buildUnsupportedTypeException(type3, Class.class, ParameterizedType.class, GenericArrayType.class);
        } else if (!this.rawType.isAssignableFrom(GsonTypes.getRawType(type2)) || !isAssignableFrom(type2, (GenericArrayType) this.type)) {
            return false;
        } else {
            return true;
        }
    }

    private TypeToken(Type type2) {
        Objects.requireNonNull(type2);
        Type canonicalize = GsonTypes.canonicalize(type2);
        this.type = canonicalize;
        this.rawType = GsonTypes.getRawType(canonicalize);
        this.hashCode = canonicalize.hashCode();
    }

    @Deprecated
    public boolean isAssignableFrom(TypeToken<?> typeToken) {
        return isAssignableFrom(typeToken.getType());
    }

    private static boolean isAssignableFrom(Type type2, GenericArrayType genericArrayType) {
        Class<?> cls;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (!(genericComponentType instanceof ParameterizedType)) {
            return true;
        }
        if (type2 instanceof GenericArrayType) {
            cls = ((GenericArrayType) type2).getGenericComponentType();
        } else {
            boolean z2 = type2 instanceof Class;
            cls = type2;
            if (z2) {
                Class<?> cls2 = (Class) type2;
                while (cls2.isArray()) {
                    cls2 = cls2.getComponentType();
                }
                cls = cls2;
            }
        }
        return isAssignableFrom(cls, (ParameterizedType) genericComponentType, new HashMap());
    }

    private static boolean isAssignableFrom(Type type2, ParameterizedType parameterizedType, Map<String, Type> map) {
        if (type2 == null) {
            return false;
        }
        if (parameterizedType.equals(type2)) {
            return true;
        }
        Class<?> rawType2 = GsonTypes.getRawType(type2);
        ParameterizedType parameterizedType2 = type2 instanceof ParameterizedType ? (ParameterizedType) type2 : null;
        if (parameterizedType2 != null) {
            Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
            TypeVariable[] typeParameters = rawType2.getTypeParameters();
            for (int i3 = 0; i3 < actualTypeArguments.length; i3++) {
                Type type3 = actualTypeArguments[i3];
                TypeVariable typeVariable = typeParameters[i3];
                while (type3 instanceof TypeVariable) {
                    type3 = map.get(((TypeVariable) type3).getName());
                }
                map.put(typeVariable.getName(), type3);
            }
            if (typeEquals(parameterizedType2, parameterizedType, map)) {
                return true;
            }
        }
        for (Type isAssignableFrom : rawType2.getGenericInterfaces()) {
            if (isAssignableFrom(isAssignableFrom, parameterizedType, new HashMap(map))) {
                return true;
            }
        }
        return isAssignableFrom(rawType2.getGenericSuperclass(), parameterizedType, new HashMap(map));
    }
}
