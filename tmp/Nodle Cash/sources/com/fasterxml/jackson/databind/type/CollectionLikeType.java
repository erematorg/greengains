package com.fasterxml.jackson.databind.type;

import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import kotlin.text.Typography;

public class CollectionLikeType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected final JavaType _elementType;

    public CollectionLikeType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, Object obj, Object obj2, boolean z2) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode(), obj, obj2, z2);
        this._elementType = javaType2;
    }

    public static CollectionLikeType construct(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2) {
        return new CollectionLikeType(cls, typeBindings, javaType, javaTypeArr, javaType2, (Object) null, (Object) null, false);
    }

    public static CollectionLikeType upgradeFrom(JavaType javaType, JavaType javaType2) {
        if (javaType instanceof TypeBase) {
            return new CollectionLikeType((TypeBase) javaType, javaType2);
        }
        throw new IllegalArgumentException("Cannot upgrade from an instance of " + javaType.getClass());
    }

    @Deprecated
    public JavaType _narrow(Class<?> cls) {
        return new CollectionLikeType(cls, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._elementType != null && _hasNTypeParameters(1)) {
            sb.append(Typography.less);
            sb.append(this._elementType.toCanonical());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
        return this._class == collectionLikeType._class && this._elementType.equals(collectionLikeType._elementType);
    }

    public Object getContentTypeHandler() {
        return this._elementType.getTypeHandler();
    }

    public Object getContentValueHandler() {
        return this._elementType.getValueHandler();
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        sb.append(Typography.less);
        this._elementType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    public boolean hasHandlers() {
        return super.hasHandlers() || this._elementType.hasHandlers();
    }

    public boolean isCollectionLikeType() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    @Deprecated
    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new CollectionLikeType(cls, typeBindings, javaType, javaTypeArr, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[collection-like type; class ");
        b.t(this._class, sb, ", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }

    public JavaType withContentType(JavaType javaType) {
        if (this._elementType == javaType) {
            return this;
        }
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r3 = r2._elementType.withHandlersFrom(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType withHandlersFrom(com.fasterxml.jackson.databind.JavaType r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.JavaType r0 = super.withHandlersFrom(r3)
            com.fasterxml.jackson.databind.JavaType r3 = r3.getContentType()
            if (r3 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.JavaType r1 = r2._elementType
            com.fasterxml.jackson.databind.JavaType r3 = r1.withHandlersFrom(r3)
            com.fasterxml.jackson.databind.JavaType r2 = r2._elementType
            if (r3 == r2) goto L_0x0018
            com.fasterxml.jackson.databind.JavaType r0 = r0.withContentType(r3)
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.type.CollectionLikeType.withHandlersFrom(com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JavaType");
    }

    @Deprecated
    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        TypeBindings emptyBindings;
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length != 1) {
            emptyBindings = TypeBindings.emptyBindings();
        } else {
            emptyBindings = TypeBindings.create(cls, javaType);
        }
        return new CollectionLikeType(cls, emptyBindings, TypeBase._bogusSuperClass(cls), (JavaType[]) null, javaType, (Object) null, (Object) null, false);
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public CollectionLikeType withContentTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public CollectionLikeType withContentValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public CollectionLikeType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    public CollectionLikeType withTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    public CollectionLikeType withValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    public CollectionLikeType(TypeBase typeBase, JavaType javaType) {
        super(typeBase);
        this._elementType = javaType;
    }
}
