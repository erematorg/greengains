package com.google.common.reflect;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.CheckForNull;
import kotlin.text.Typography;

@ElementTypesAreNonnullByDefault
public final class TypeResolver {
    private final TypeTable typeTable;

    public static final class TypeMappingIntrospector extends TypeVisitor {
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        public static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type type) {
            Preconditions.checkNotNull(type);
            TypeMappingIntrospector typeMappingIntrospector = new TypeMappingIntrospector();
            typeMappingIntrospector.visit(type);
            return ImmutableMap.copyOf(typeMappingIntrospector.mappings);
        }

        private void map(TypeVariableKey typeVariableKey, Type type) {
            if (!this.mappings.containsKey(typeVariableKey)) {
                Type type2 = type;
                while (type2 != null) {
                    if (typeVariableKey.equalsType(type2)) {
                        while (type != null) {
                            type = this.mappings.remove(TypeVariableKey.forLookup(type));
                        }
                        return;
                    }
                    type2 = this.mappings.get(TypeVariableKey.forLookup(type2));
                }
                this.mappings.put(typeVariableKey, type);
            }
        }

        public void visitClass(Class<?> cls) {
            visit(cls.getGenericSuperclass());
            visit(cls.getGenericInterfaces());
        }

        public void visitParameterizedType(ParameterizedType parameterizedType) {
            Class cls = (Class) parameterizedType.getRawType();
            TypeVariable[] typeParameters = cls.getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(typeParameters.length == actualTypeArguments.length);
            for (int i3 = 0; i3 < typeParameters.length; i3++) {
                map(new TypeVariableKey(typeParameters[i3]), actualTypeArguments[i3]);
            }
            visit(cls);
            visit(parameterizedType.getOwnerType());
        }

        public void visitTypeVariable(TypeVariable<?> typeVariable) {
            visit(typeVariable.getBounds());
        }

        public void visitWildcardType(WildcardType wildcardType) {
            visit(wildcardType.getUpperBounds());
        }
    }

    public static final class TypeVariableKey {
        private final TypeVariable<?> var;

        public TypeVariableKey(TypeVariable<?> typeVariable) {
            this.var = (TypeVariable) Preconditions.checkNotNull(typeVariable);
        }

        private boolean equalsTypeVariable(TypeVariable<?> typeVariable) {
            return this.var.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) && this.var.getName().equals(typeVariable.getName());
        }

        @CheckForNull
        public static TypeVariableKey forLookup(Type type) {
            if (type instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) type);
            }
            return null;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public String toString() {
            return this.var.toString();
        }
    }

    public static class WildcardCapturer {
        static final WildcardCapturer INSTANCE = new WildcardCapturer();
        private final AtomicInteger id;

        @CheckForNull
        private Type captureNullable(@CheckForNull Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }

        private WildcardCapturer forTypeVariable(final TypeVariable<?> typeVariable) {
            return new WildcardCapturer(this, this.id) {
                public TypeVariable<?> captureAsTypeVariable(Type[] typeArr) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(typeArr));
                    linkedHashSet.addAll(Arrays.asList(typeVariable.getBounds()));
                    if (linkedHashSet.size() > 1) {
                        linkedHashSet.remove(Object.class);
                    }
                    return super.captureAsTypeVariable((Type[]) linkedHashSet.toArray(new Type[0]));
                }
            };
        }

        private WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.id);
        }

        public final Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(notForTypeVariable().capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class cls = (Class) parameterizedType.getRawType();
                TypeVariable[] typeParameters = cls.getTypeParameters();
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                for (int i3 = 0; i3 < actualTypeArguments.length; i3++) {
                    actualTypeArguments[i3] = forTypeVariable(typeParameters[i3]).capture(actualTypeArguments[i3]);
                }
                return Types.newParameterizedTypeWithOwner(notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), cls, actualTypeArguments);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return wildcardType.getLowerBounds().length == 0 ? captureAsTypeVariable(wildcardType.getUpperBounds()) : type;
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        public TypeVariable<?> captureAsTypeVariable(Type[] typeArr) {
            return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.id.incrementAndGet() + "-of ? extends " + Joiner.on((char) Typography.amp).join((Object[]) typeArr), typeArr);
        }

        private WildcardCapturer() {
            this(new AtomicInteger());
        }

        private WildcardCapturer(AtomicInteger atomicInteger) {
            this.id = atomicInteger;
        }
    }

    public static TypeResolver covariantly(Type type) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(type));
    }

    /* access modifiers changed from: private */
    public static <T> T expectArgument(Class<T> cls, Object obj) {
        try {
            return cls.cast(obj);
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException(obj + " is not a " + cls.getSimpleName());
        }
    }

    public static TypeResolver invariantly(Type type) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(WildcardCapturer.INSTANCE.capture(type)));
    }

    /* access modifiers changed from: private */
    public static void populateTypeMappings(final Map<TypeVariableKey, Type> map, Type type, final Type type2) {
        if (!type.equals(type2)) {
            new TypeVisitor() {
                public void visitClass(Class<?> cls) {
                    if (!(type2 instanceof WildcardType)) {
                        throw new IllegalArgumentException("No type mapping from " + cls + " to " + type2);
                    }
                }

                public void visitGenericArrayType(GenericArrayType genericArrayType) {
                    Type type = type2;
                    if (!(type instanceof WildcardType)) {
                        Type componentType = Types.getComponentType(type);
                        Preconditions.checkArgument(componentType != null, "%s is not an array type.", (Object) type2);
                        TypeResolver.populateTypeMappings(map, genericArrayType.getGenericComponentType(), componentType);
                    }
                }

                public void visitParameterizedType(ParameterizedType parameterizedType) {
                    Type type = type2;
                    if (!(type instanceof WildcardType)) {
                        ParameterizedType parameterizedType2 = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, type);
                        if (!(parameterizedType.getOwnerType() == null || parameterizedType2.getOwnerType() == null)) {
                            TypeResolver.populateTypeMappings(map, parameterizedType.getOwnerType(), parameterizedType2.getOwnerType());
                        }
                        Preconditions.checkArgument(parameterizedType.getRawType().equals(parameterizedType2.getRawType()), "Inconsistent raw type: %s vs. %s", (Object) parameterizedType, (Object) type2);
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        Type[] actualTypeArguments2 = parameterizedType2.getActualTypeArguments();
                        Preconditions.checkArgument(actualTypeArguments.length == actualTypeArguments2.length, "%s not compatible with %s", (Object) parameterizedType, (Object) parameterizedType2);
                        for (int i3 = 0; i3 < actualTypeArguments.length; i3++) {
                            TypeResolver.populateTypeMappings(map, actualTypeArguments[i3], actualTypeArguments2[i3]);
                        }
                    }
                }

                public void visitTypeVariable(TypeVariable<?> typeVariable) {
                    map.put(new TypeVariableKey(typeVariable), type2);
                }

                public void visitWildcardType(WildcardType wildcardType) {
                    Type type = type2;
                    if (type instanceof WildcardType) {
                        WildcardType wildcardType2 = (WildcardType) type;
                        Type[] upperBounds = wildcardType.getUpperBounds();
                        Type[] upperBounds2 = wildcardType2.getUpperBounds();
                        Type[] lowerBounds = wildcardType.getLowerBounds();
                        Type[] lowerBounds2 = wildcardType2.getLowerBounds();
                        Preconditions.checkArgument(upperBounds.length == upperBounds2.length && lowerBounds.length == lowerBounds2.length, "Incompatible type: %s vs. %s", (Object) wildcardType, (Object) type2);
                        for (int i3 = 0; i3 < upperBounds.length; i3++) {
                            TypeResolver.populateTypeMappings(map, upperBounds[i3], upperBounds2[i3]);
                        }
                        for (int i4 = 0; i4 < lowerBounds.length; i4++) {
                            TypeResolver.populateTypeMappings(map, lowerBounds[i4], lowerBounds2[i4]);
                        }
                    }
                }
            }.visit(type);
        }
    }

    private Type resolveGenericArrayType(GenericArrayType genericArrayType) {
        return Types.newArrayType(resolveType(genericArrayType.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType parameterizedType) {
        Type ownerType = parameterizedType.getOwnerType();
        return Types.newParameterizedTypeWithOwner(ownerType == null ? null : resolveType(ownerType), (Class) resolveType(parameterizedType.getRawType()), resolveTypes(parameterizedType.getActualTypeArguments()));
    }

    /* access modifiers changed from: private */
    public Type[] resolveTypes(Type[] typeArr) {
        Type[] typeArr2 = new Type[typeArr.length];
        for (int i3 = 0; i3 < typeArr.length; i3++) {
            typeArr2[i3] = resolveType(typeArr[i3]);
        }
        return typeArr2;
    }

    private WildcardType resolveWildcardType(WildcardType wildcardType) {
        return new Types.WildcardTypeImpl(resolveTypes(wildcardType.getLowerBounds()), resolveTypes(wildcardType.getUpperBounds()));
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        return type instanceof TypeVariable ? this.typeTable.resolve((TypeVariable) type) : type instanceof ParameterizedType ? resolveParameterizedType((ParameterizedType) type) : type instanceof GenericArrayType ? resolveGenericArrayType((GenericArrayType) type) : type instanceof WildcardType ? resolveWildcardType((WildcardType) type) : type;
    }

    public Type[] resolveTypesInPlace(Type[] typeArr) {
        for (int i3 = 0; i3 < typeArr.length; i3++) {
            typeArr[i3] = resolveType(typeArr[i3]);
        }
        return typeArr;
    }

    public TypeResolver where(Type type, Type type2) {
        HashMap newHashMap = Maps.newHashMap();
        populateTypeMappings(newHashMap, (Type) Preconditions.checkNotNull(type), (Type) Preconditions.checkNotNull(type2));
        return where(newHashMap);
    }

    public static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        public TypeTable() {
            this.map = ImmutableMap.of();
        }

        public final Type resolve(final TypeVariable<?> typeVariable) {
            return resolveInternal(typeVariable, new TypeTable(this) {
                public Type resolveInternal(TypeVariable<?> typeVariable, TypeTable typeTable) {
                    return typeVariable.getGenericDeclaration().equals(typeVariable.getGenericDeclaration()) ? typeVariable : this.resolveInternal(typeVariable, typeTable);
                }
            });
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.reflect.Type, java.lang.reflect.TypeVariable, java.lang.reflect.TypeVariable<?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.reflect.Type resolveInternal(java.lang.reflect.TypeVariable<?> r3, com.google.common.reflect.TypeResolver.TypeTable r4) {
            /*
                r2 = this;
                com.google.common.collect.ImmutableMap<com.google.common.reflect.TypeResolver$TypeVariableKey, java.lang.reflect.Type> r2 = r2.map
                com.google.common.reflect.TypeResolver$TypeVariableKey r0 = new com.google.common.reflect.TypeResolver$TypeVariableKey
                r0.<init>(r3)
                java.lang.Object r2 = r2.get(r0)
                java.lang.reflect.Type r2 = (java.lang.reflect.Type) r2
                r0 = 0
                if (r2 != 0) goto L_0x0039
                java.lang.reflect.Type[] r2 = r3.getBounds()
                int r1 = r2.length
                if (r1 != 0) goto L_0x0018
                return r3
            L_0x0018:
                com.google.common.reflect.TypeResolver r1 = new com.google.common.reflect.TypeResolver
                r1.<init>(r4)
                java.lang.reflect.Type[] r4 = r1.resolveTypes(r2)
                boolean r0 = com.google.common.reflect.Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY
                if (r0 == 0) goto L_0x002c
                boolean r2 = java.util.Arrays.equals(r2, r4)
                if (r2 == 0) goto L_0x002c
                return r3
            L_0x002c:
                java.lang.reflect.GenericDeclaration r2 = r3.getGenericDeclaration()
                java.lang.String r3 = r3.getName()
                java.lang.reflect.TypeVariable r2 = com.google.common.reflect.Types.newArtificialTypeVariable(r2, r3, r4)
                return r2
            L_0x0039:
                com.google.common.reflect.TypeResolver r3 = new com.google.common.reflect.TypeResolver
                r3.<init>(r4)
                java.lang.reflect.Type r2 = r3.resolveType(r2)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.reflect.TypeResolver.TypeTable.resolveInternal(java.lang.reflect.TypeVariable, com.google.common.reflect.TypeResolver$TypeTable):java.lang.reflect.Type");
        }

        public final TypeTable where(Map<TypeVariableKey, ? extends Type> map2) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Map.Entry next : map2.entrySet()) {
                TypeVariableKey typeVariableKey = (TypeVariableKey) next.getKey();
                Type type = (Type) next.getValue();
                Preconditions.checkArgument(!typeVariableKey.equalsType(type), "Type variable %s bound to itself", (Object) typeVariableKey);
                builder.put(typeVariableKey, type);
            }
            return new TypeTable(builder.buildOrThrow());
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> immutableMap) {
            this.map = immutableMap;
        }
    }

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable2) {
        this.typeTable = typeTable2;
    }

    public TypeResolver where(Map<TypeVariableKey, ? extends Type> map) {
        return new TypeResolver(this.typeTable.where(map));
    }
}
