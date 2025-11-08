package com.fasterxml.jackson.databind.introspect;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    private static final long serialVersionUID = 1;
    protected final transient Method _method;
    protected Class<?>[] _paramClasses;
    protected Serialization _serialization;

    public static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        protected Class<?>[] args;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Method method) {
            this.clazz = method.getDeclaringClass();
            this.name = method.getName();
            this.args = method.getParameterTypes();
        }
    }

    public AnnotatedMethod(TypeResolutionContext typeResolutionContext, Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(typeResolutionContext, annotationMap, annotationMapArr);
        if (method != null) {
            this._method = method;
            return;
        }
        throw new IllegalArgumentException("Cannot construct AnnotatedMethod with null Method");
    }

    public final Object call() throws Exception {
        return this._method.invoke((Object) null, (Object[]) null);
    }

    public final Object call1(Object obj) throws Exception {
        return this._method.invoke((Object) null, new Object[]{obj});
    }

    public final Object callOn(Object obj) throws Exception {
        return this._method.invoke(obj, (Object[]) null);
    }

    public final Object callOnWith(Object obj, Object... objArr) throws Exception {
        return this._method.invoke(obj, objArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!ClassUtil.hasClass(obj, AnnotatedMethod.class)) {
            return false;
        }
        Method method = ((AnnotatedMethod) obj)._method;
        return method == null ? this._method == null : method.equals(this._method);
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public String getFullName() {
        String fullName = super.getFullName();
        int parameterCount = getParameterCount();
        if (parameterCount == 0) {
            return a.m(fullName, "()");
        }
        if (parameterCount != 1) {
            return String.format("%s(%d params)", new Object[]{super.getFullName(), Integer.valueOf(getParameterCount())});
        }
        StringBuilder q2 = A.a.q(fullName, "(");
        q2.append(getRawParameterType(0).getName());
        q2.append(")");
        return q2.toString();
    }

    @Deprecated
    public Type getGenericParameterType(int i3) {
        Type[] genericParameterTypes = getGenericParameterTypes();
        if (i3 >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i3];
    }

    @Deprecated
    public Type[] getGenericParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    public int getModifiers() {
        return this._method.getModifiers();
    }

    public String getName() {
        return this._method.getName();
    }

    public int getParameterCount() {
        return this._method.getParameterCount();
    }

    public JavaType getParameterType(int i3) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        if (i3 >= genericParameterTypes.length) {
            return null;
        }
        return this._typeContext.resolveType(genericParameterTypes[i3]);
    }

    public Class<?> getRawParameterType(int i3) {
        Class<?>[] rawParameterTypes = getRawParameterTypes();
        if (i3 >= rawParameterTypes.length) {
            return null;
        }
        return rawParameterTypes[i3];
    }

    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }

    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType() {
        return this._typeContext.resolveType(this._method.getGenericReturnType());
    }

    public Object getValue(Object obj) throws IllegalArgumentException {
        try {
            return this._method.invoke(obj, (Object[]) null);
        } catch (IllegalAccessException | InvocationTargetException e3) {
            throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + ClassUtil.exceptionMessage(e3), e3);
        }
    }

    @Deprecated
    public boolean hasReturnType() {
        return getRawReturnType() != Void.TYPE;
    }

    public int hashCode() {
        return this._method.getName().hashCode();
    }

    public Object readResolve() {
        Serialization serialization = this._serialization;
        Class<?> cls = serialization.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(serialization.name, serialization.args);
            if (!declaredMethod.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredMethod, false);
            }
            return new AnnotatedMethod((TypeResolutionContext) null, declaredMethod, (AnnotationMap) null, (AnnotationMap[]) null);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + cls.getName());
        }
    }

    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._method.invoke(obj, new Object[]{obj2});
        } catch (IllegalAccessException | InvocationTargetException e3) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + ClassUtil.exceptionMessage(e3), e3);
        }
    }

    public String toString() {
        return "[method " + getFullName() + "]";
    }

    public Object writeReplace() {
        return new AnnotatedMethod(new Serialization(this._method));
    }

    public final Object call(Object[] objArr) throws Exception {
        return this._method.invoke((Object) null, objArr);
    }

    public Method getAnnotated() {
        return this._method;
    }

    public Method getMember() {
        return this._method;
    }

    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._typeContext, this._method, annotationMap, this._paramAnnotations);
    }

    public AnnotatedMethod(Serialization serialization) {
        super((TypeResolutionContext) null, (AnnotationMap) null, (AnnotationMap[]) null);
        this._method = null;
        this._serialization = serialization;
    }
}
