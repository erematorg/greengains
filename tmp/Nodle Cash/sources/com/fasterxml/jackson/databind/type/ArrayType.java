package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class ArrayType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    public ArrayType(JavaType javaType, TypeBindings typeBindings, Object obj, Object obj2, Object obj3, boolean z2) {
        super(obj.getClass(), typeBindings, (JavaType) null, (JavaType[]) null, javaType.hashCode(), obj2, obj3, z2);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    private JavaType _reportUnsupported() {
        throw new UnsupportedOperationException("Cannot narrow or widen array types");
    }

    public static ArrayType construct(JavaType javaType, TypeBindings typeBindings) {
        return construct(javaType, typeBindings, (Object) null, (Object) null);
    }

    @Deprecated
    public JavaType _narrow(Class<?> cls) {
        return _reportUnsupported();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == ArrayType.class) {
            return this._componentType.equals(((ArrayType) obj)._componentType);
        }
        return false;
    }

    public Object getContentTypeHandler() {
        return this._componentType.getTypeHandler();
    }

    public Object getContentValueHandler() {
        return this._componentType.getValueHandler();
    }

    public Object[] getEmptyArray() {
        return (Object[]) this._emptyArray;
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        return this._componentType.getErasedSignature(sb);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        return this._componentType.getGenericSignature(sb);
    }

    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    public boolean hasHandlers() {
        return super.hasHandlers() || this._componentType.hasHandlers();
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isArrayType() {
        return true;
    }

    public boolean isConcrete() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    public JavaType withContentType(JavaType javaType) {
        return new ArrayType(javaType, this._bindings, Array.newInstance(javaType.getRawClass(), 0), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public static ArrayType construct(JavaType javaType, TypeBindings typeBindings, Object obj, Object obj2) {
        return new ArrayType(javaType, typeBindings, Array.newInstance(javaType.getRawClass(), 0), obj, obj2, false);
    }

    public JavaType getContentType() {
        return this._componentType;
    }

    public ArrayType withContentTypeHandler(Object obj) {
        if (obj == this._componentType.getTypeHandler()) {
            return this;
        }
        return new ArrayType(this._componentType.withTypeHandler(obj), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ArrayType withContentValueHandler(Object obj) {
        if (obj == this._componentType.getValueHandler()) {
            return this;
        }
        return new ArrayType(this._componentType.withValueHandler(obj), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ArrayType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        return new ArrayType(this._componentType.withStaticTyping(), this._bindings, this._emptyArray, this._valueHandler, this._typeHandler, true);
    }

    public ArrayType withTypeHandler(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new ArrayType(this._componentType, this._bindings, this._emptyArray, this._valueHandler, obj, this._asStatic);
    }

    public ArrayType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new ArrayType(this._componentType, this._bindings, this._emptyArray, obj, this._typeHandler, this._asStatic);
    }
}
