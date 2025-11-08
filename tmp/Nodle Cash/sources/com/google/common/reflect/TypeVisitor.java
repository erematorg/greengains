package com.google.common.reflect;

import com.google.common.collect.Sets;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Set;

@ElementTypesAreNonnullByDefault
abstract class TypeVisitor {
    private final Set<Type> visited = Sets.newHashSet();

    public final void visit(Type... typeArr) {
        for (TypeVariable typeVariable : typeArr) {
            if (typeVariable != null && this.visited.add(typeVariable)) {
                try {
                    if (typeVariable instanceof TypeVariable) {
                        visitTypeVariable(typeVariable);
                    } else if (typeVariable instanceof WildcardType) {
                        visitWildcardType((WildcardType) typeVariable);
                    } else if (typeVariable instanceof ParameterizedType) {
                        visitParameterizedType(typeVariable);
                    } else if (typeVariable instanceof Class) {
                        visitClass(typeVariable);
                    } else if (typeVariable instanceof GenericArrayType) {
                        visitGenericArrayType(typeVariable);
                    } else {
                        throw new AssertionError("Unknown type: " + typeVariable);
                    }
                } catch (Throwable th) {
                    this.visited.remove(typeVariable);
                    throw th;
                }
            }
        }
    }

    public void visitClass(Class<?> cls) {
    }

    public void visitGenericArrayType(GenericArrayType genericArrayType) {
    }

    public void visitParameterizedType(ParameterizedType parameterizedType) {
    }

    public void visitTypeVariable(TypeVariable<?> typeVariable) {
    }

    public void visitWildcardType(WildcardType wildcardType) {
    }
}
