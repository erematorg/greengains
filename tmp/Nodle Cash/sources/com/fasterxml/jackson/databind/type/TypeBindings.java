package com.fasterxml.jackson.databind.type;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class TypeBindings implements Serializable {
    private static final TypeBindings EMPTY;
    private static final String[] NO_STRINGS;
    private static final JavaType[] NO_TYPES;
    private static final long serialVersionUID = 1;
    private final int _hashCode;
    private final String[] _names;
    private final JavaType[] _types;
    private final String[] _unboundVariables;

    public static final class AsKey {
        private final int _hash;
        private final JavaType[] _params;
        private final Class<?> _raw;

        public AsKey(Class<?> cls, JavaType[] javaTypeArr, int i3) {
            this._raw = cls;
            this._params = javaTypeArr;
            this._hash = i3;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != AsKey.class) {
                return false;
            }
            AsKey asKey = (AsKey) obj;
            if (this._hash == asKey._hash && this._raw == asKey._raw) {
                JavaType[] javaTypeArr = asKey._params;
                int length = this._params.length;
                if (length == javaTypeArr.length) {
                    for (int i3 = 0; i3 < length; i3++) {
                        if (!this._params[i3].equals(javaTypeArr[i3])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this._hash;
        }

        public String toString() {
            return this._raw.getName().concat("<>");
        }
    }

    public static class TypeParamStash {
        private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = AbstractList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ARRAY_LIST = ArrayList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_COLLECTION = Collection.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_HASH_MAP = HashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ITERABLE = Iterable.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = LinkedHashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LIST = List.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_MAP = Map.class.getTypeParameters();

        public static TypeVariable<?>[] paramsFor1(Class<?> cls) {
            return cls == Collection.class ? VARS_COLLECTION : cls == List.class ? VARS_LIST : cls == ArrayList.class ? VARS_ARRAY_LIST : cls == AbstractList.class ? VARS_ABSTRACT_LIST : cls == Iterable.class ? VARS_ITERABLE : cls.getTypeParameters();
        }

        public static TypeVariable<?>[] paramsFor2(Class<?> cls) {
            return cls == Map.class ? VARS_MAP : cls == HashMap.class ? VARS_HASH_MAP : cls == LinkedHashMap.class ? VARS_LINKED_HASH_MAP : cls.getTypeParameters();
        }
    }

    static {
        String[] strArr = new String[0];
        NO_STRINGS = strArr;
        JavaType[] javaTypeArr = new JavaType[0];
        NO_TYPES = javaTypeArr;
        EMPTY = new TypeBindings(strArr, javaTypeArr, (String[]) null);
    }

    private TypeBindings(String[] strArr, JavaType[] javaTypeArr, String[] strArr2) {
        strArr = strArr == null ? NO_STRINGS : strArr;
        this._names = strArr;
        javaTypeArr = javaTypeArr == null ? NO_TYPES : javaTypeArr;
        this._types = javaTypeArr;
        if (strArr.length == javaTypeArr.length) {
            int length = javaTypeArr.length;
            int i3 = 1;
            for (int i4 = 0; i4 < length; i4++) {
                i3 += this._types[i4].hashCode();
            }
            this._unboundVariables = strArr2;
            this._hashCode = i3;
            return;
        }
        StringBuilder sb = new StringBuilder("Mismatching names (");
        sb.append(strArr.length);
        sb.append("), types (");
        throw new IllegalArgumentException(a.m(sb, ")", javaTypeArr.length));
    }

    public static TypeBindings create(Class<?> cls, List<JavaType> list) {
        JavaType[] javaTypeArr;
        if (list == null || list.isEmpty()) {
            javaTypeArr = NO_TYPES;
        } else {
            javaTypeArr = (JavaType[]) list.toArray(NO_TYPES);
        }
        return create(cls, javaTypeArr);
    }

    public static TypeBindings createIfNeeded(Class<?> cls, JavaType javaType) {
        int i3;
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null) {
            i3 = 0;
        } else {
            i3 = typeParameters.length;
        }
        if (i3 == 0) {
            return EMPTY;
        }
        if (i3 == 1) {
            return new TypeBindings(new String[]{typeParameters[0].getName()}, new JavaType[]{javaType}, (String[]) null);
        }
        throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i3);
    }

    public static TypeBindings emptyBindings() {
        return EMPTY;
    }

    public Object asKey(Class<?> cls) {
        return new AsKey(cls, this._types, this._hashCode);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!ClassUtil.hasClass(obj, getClass())) {
            return false;
        }
        TypeBindings typeBindings = (TypeBindings) obj;
        int length = this._types.length;
        if (length != typeBindings.size()) {
            return false;
        }
        JavaType[] javaTypeArr = typeBindings._types;
        for (int i3 = 0; i3 < length; i3++) {
            if (!javaTypeArr[i3].equals(this._types[i3])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0018, code lost:
        r4 = ((com.fasterxml.jackson.databind.type.ResolvedRecursiveType) r3).getSelfReferencedType();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType findBoundType(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String[] r0 = r3._names
            int r0 = r0.length
            r1 = 0
        L_0x0004:
            if (r1 >= r0) goto L_0x0026
            java.lang.String[] r2 = r3._names
            r2 = r2[r1]
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x0023
            com.fasterxml.jackson.databind.JavaType[] r3 = r3._types
            r3 = r3[r1]
            boolean r4 = r3 instanceof com.fasterxml.jackson.databind.type.ResolvedRecursiveType
            if (r4 == 0) goto L_0x0022
            r4 = r3
            com.fasterxml.jackson.databind.type.ResolvedRecursiveType r4 = (com.fasterxml.jackson.databind.type.ResolvedRecursiveType) r4
            com.fasterxml.jackson.databind.JavaType r4 = r4.getSelfReferencedType()
            if (r4 == 0) goto L_0x0022
            r3 = r4
        L_0x0022:
            return r3
        L_0x0023:
            int r1 = r1 + 1
            goto L_0x0004
        L_0x0026:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.TypeBindings.findBoundType(java.lang.String):com.fasterxml.jackson.databind.JavaType");
    }

    public String getBoundName(int i3) {
        if (i3 < 0) {
            return null;
        }
        String[] strArr = this._names;
        if (i3 >= strArr.length) {
            return null;
        }
        return strArr[i3];
    }

    public JavaType getBoundType(int i3) {
        if (i3 < 0) {
            return null;
        }
        JavaType[] javaTypeArr = this._types;
        if (i3 >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i3];
    }

    public List<JavaType> getTypeParameters() {
        JavaType[] javaTypeArr = this._types;
        return javaTypeArr.length == 0 ? Collections.emptyList() : Arrays.asList(javaTypeArr);
    }

    public boolean hasUnbound(String str) {
        String[] strArr = this._unboundVariables;
        if (strArr == null) {
            return false;
        }
        int length = strArr.length;
        do {
            length--;
            if (length < 0) {
                return false;
            }
        } while (!str.equals(this._unboundVariables[length]));
        return true;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public boolean isEmpty() {
        return this._types.length == 0;
    }

    public Object readResolve() {
        String[] strArr = this._names;
        return (strArr == null || strArr.length == 0) ? EMPTY : this;
    }

    public int size() {
        return this._types.length;
    }

    public String toString() {
        if (this._types.length == 0) {
            return "<>";
        }
        StringBuilder sb = new StringBuilder("<");
        int length = this._types.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 > 0) {
                sb.append(AbstractJsonLexerKt.COMMA);
            }
            sb.append(this._types[i3].getGenericSignature());
        }
        sb.append(Typography.greater);
        return sb.toString();
    }

    public JavaType[] typeParameterArray() {
        return this._types;
    }

    public TypeBindings withUnboundVariable(String str) {
        String[] strArr = this._unboundVariables;
        int length = strArr == null ? 0 : strArr.length;
        String[] strArr2 = length == 0 ? new String[1] : (String[]) Arrays.copyOf(strArr, length + 1);
        strArr2[length] = str;
        return new TypeBindings(this._names, this._types, strArr2);
    }

    public static TypeBindings create(Class<?> cls, JavaType[] javaTypeArr) {
        String[] strArr;
        if (javaTypeArr == null) {
            javaTypeArr = NO_TYPES;
        } else {
            int length = javaTypeArr.length;
            if (length == 1) {
                return create(cls, javaTypeArr[0]);
            }
            if (length == 2) {
                return create(cls, javaTypeArr[0], javaTypeArr[1]);
            }
        }
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            strArr = NO_STRINGS;
        } else {
            int length2 = typeParameters.length;
            strArr = new String[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                strArr[i3] = typeParameters[i3].getName();
            }
        }
        if (strArr.length == javaTypeArr.length) {
            return new TypeBindings(strArr, javaTypeArr, (String[]) null);
        }
        StringBuilder sb = new StringBuilder("Cannot create TypeBindings for class ");
        b.t(cls, sb, " with ");
        sb.append(javaTypeArr.length);
        sb.append(" type parameter");
        sb.append(javaTypeArr.length == 1 ? "" : "s");
        sb.append(": class expects ");
        sb.append(strArr.length);
        throw new IllegalArgumentException(sb.toString());
    }

    public static TypeBindings createIfNeeded(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            return EMPTY;
        }
        if (javaTypeArr == null) {
            javaTypeArr = NO_TYPES;
        }
        int length = typeParameters.length;
        String[] strArr = new String[length];
        for (int i3 = 0; i3 < length; i3++) {
            strArr[i3] = typeParameters[i3].getName();
        }
        if (length == javaTypeArr.length) {
            return new TypeBindings(strArr, javaTypeArr, (String[]) null);
        }
        StringBuilder sb = new StringBuilder("Cannot create TypeBindings for class ");
        b.t(cls, sb, " with ");
        sb.append(javaTypeArr.length);
        sb.append(" type parameter");
        sb.append(javaTypeArr.length == 1 ? "" : "s");
        sb.append(": class expects ");
        sb.append(length);
        throw new IllegalArgumentException(sb.toString());
    }

    public static TypeBindings create(Class<?> cls, JavaType javaType) {
        int i3;
        TypeVariable[] paramsFor1 = TypeParamStash.paramsFor1(cls);
        if (paramsFor1 == null) {
            i3 = 0;
        } else {
            i3 = paramsFor1.length;
        }
        if (i3 == 1) {
            return new TypeBindings(new String[]{paramsFor1[0].getName()}, new JavaType[]{javaType}, (String[]) null);
        }
        throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i3);
    }

    public static TypeBindings create(Class<?> cls, JavaType javaType, JavaType javaType2) {
        int i3;
        TypeVariable[] paramsFor2 = TypeParamStash.paramsFor2(cls);
        if (paramsFor2 == null) {
            i3 = 0;
        } else {
            i3 = paramsFor2.length;
        }
        if (i3 == 2) {
            return new TypeBindings(new String[]{paramsFor2[0].getName(), paramsFor2[1].getName()}, new JavaType[]{javaType, javaType2}, (String[]) null);
        }
        throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 2 type parameters: class expects " + i3);
    }

    public static TypeBindings create(List<String> list, List<JavaType> list2) {
        if (list == null || list.isEmpty() || list2 == null || list2.isEmpty()) {
            return EMPTY;
        }
        return new TypeBindings((String[]) list.toArray(NO_STRINGS), (JavaType[]) list2.toArray(NO_TYPES), (String[]) null);
    }
}
