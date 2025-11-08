package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class StdValueInstantiator extends ValueInstantiator implements Serializable {
    private static final long serialVersionUID = 1;
    protected SettableBeanProperty[] _arrayDelegateArguments;
    protected AnnotatedWithParams _arrayDelegateCreator;
    protected JavaType _arrayDelegateType;
    protected SettableBeanProperty[] _constructorArguments;
    protected AnnotatedWithParams _defaultCreator;
    protected SettableBeanProperty[] _delegateArguments;
    protected AnnotatedWithParams _delegateCreator;
    protected JavaType _delegateType;
    protected AnnotatedWithParams _fromBigDecimalCreator;
    protected AnnotatedWithParams _fromBigIntegerCreator;
    protected AnnotatedWithParams _fromBooleanCreator;
    protected AnnotatedWithParams _fromDoubleCreator;
    protected AnnotatedWithParams _fromIntCreator;
    protected AnnotatedWithParams _fromLongCreator;
    protected AnnotatedWithParams _fromStringCreator;
    protected final Class<?> _valueClass;
    protected final String _valueTypeDesc;
    protected AnnotatedWithParams _withArgsCreator;

    @Deprecated
    public StdValueInstantiator(DeserializationConfig deserializationConfig, Class<?> cls) {
        this._valueTypeDesc = ClassUtil.nameOf(cls);
        this._valueClass = cls == null ? Object.class : cls;
    }

    private Object _createUsingDelegate(AnnotatedWithParams annotatedWithParams, SettableBeanProperty[] settableBeanPropertyArr, DeserializationContext deserializationContext, Object obj) throws IOException {
        if (annotatedWithParams == null) {
            throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc());
        } else if (settableBeanPropertyArr == null) {
            try {
                return annotatedWithParams.call1(obj);
            } catch (Throwable th) {
                throw rewrapCtorProblem(deserializationContext, th);
            }
        } else {
            int length = settableBeanPropertyArr.length;
            Object[] objArr = new Object[length];
            for (int i3 = 0; i3 < length; i3++) {
                SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i3];
                if (settableBeanProperty == null) {
                    objArr[i3] = obj;
                } else {
                    objArr[i3] = deserializationContext.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, (Object) null);
                }
            }
            return annotatedWithParams.call(objArr);
        }
    }

    public static Double tryConvertToDouble(BigDecimal bigDecimal) {
        double doubleValue = bigDecimal.doubleValue();
        if (Double.isInfinite(doubleValue)) {
            return null;
        }
        return Double.valueOf(doubleValue);
    }

    public boolean canCreateFromBigDecimal() {
        return this._fromBigDecimalCreator != null;
    }

    public boolean canCreateFromBigInteger() {
        return this._fromBigIntegerCreator != null;
    }

    public boolean canCreateFromBoolean() {
        return this._fromBooleanCreator != null;
    }

    public boolean canCreateFromDouble() {
        return this._fromDoubleCreator != null;
    }

    public boolean canCreateFromInt() {
        return this._fromIntCreator != null;
    }

    public boolean canCreateFromLong() {
        return this._fromLongCreator != null;
    }

    public boolean canCreateFromObjectWith() {
        return this._withArgsCreator != null;
    }

    public boolean canCreateFromString() {
        return this._fromStringCreator != null;
    }

    public boolean canCreateUsingArrayDelegate() {
        return this._arrayDelegateType != null;
    }

    public boolean canCreateUsingDefault() {
        return this._defaultCreator != null;
    }

    public boolean canCreateUsingDelegate() {
        return this._delegateType != null;
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateUsingArrayDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public void configureFromArraySettings(AnnotatedWithParams annotatedWithParams, JavaType javaType, SettableBeanProperty[] settableBeanPropertyArr) {
        this._arrayDelegateCreator = annotatedWithParams;
        this._arrayDelegateType = javaType;
        this._arrayDelegateArguments = settableBeanPropertyArr;
    }

    public void configureFromBigDecimalCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromBigDecimalCreator = annotatedWithParams;
    }

    public void configureFromBigIntegerCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromBigIntegerCreator = annotatedWithParams;
    }

    public void configureFromBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromBooleanCreator = annotatedWithParams;
    }

    public void configureFromDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromDoubleCreator = annotatedWithParams;
    }

    public void configureFromIntCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromIntCreator = annotatedWithParams;
    }

    public void configureFromLongCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromLongCreator = annotatedWithParams;
    }

    public void configureFromObjectSettings(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, JavaType javaType, SettableBeanProperty[] settableBeanPropertyArr, AnnotatedWithParams annotatedWithParams3, SettableBeanProperty[] settableBeanPropertyArr2) {
        this._defaultCreator = annotatedWithParams;
        this._delegateCreator = annotatedWithParams2;
        this._delegateType = javaType;
        this._delegateArguments = settableBeanPropertyArr;
        this._withArgsCreator = annotatedWithParams3;
        this._constructorArguments = settableBeanPropertyArr2;
    }

    public void configureFromStringCreator(AnnotatedWithParams annotatedWithParams) {
        this._fromStringCreator = annotatedWithParams;
    }

    public Object createFromBigDecimal(DeserializationContext deserializationContext, BigDecimal bigDecimal) throws IOException {
        Double tryConvertToDouble;
        AnnotatedWithParams annotatedWithParams = this._fromBigDecimalCreator;
        if (annotatedWithParams != null) {
            try {
                return annotatedWithParams.call1(bigDecimal);
            } catch (Throwable th) {
                return deserializationContext.handleInstantiationProblem(this._fromBigDecimalCreator.getDeclaringClass(), bigDecimal, rewrapCtorProblem(deserializationContext, th));
            }
        } else if (this._fromDoubleCreator == null || (tryConvertToDouble = tryConvertToDouble(bigDecimal)) == null) {
            return super.createFromBigDecimal(deserializationContext, bigDecimal);
        } else {
            try {
                return this._fromDoubleCreator.call1(tryConvertToDouble);
            } catch (Throwable th2) {
                return deserializationContext.handleInstantiationProblem(this._fromDoubleCreator.getDeclaringClass(), tryConvertToDouble, rewrapCtorProblem(deserializationContext, th2));
            }
        }
    }

    public Object createFromBigInteger(DeserializationContext deserializationContext, BigInteger bigInteger) throws IOException {
        AnnotatedWithParams annotatedWithParams = this._fromBigIntegerCreator;
        if (annotatedWithParams == null) {
            return super.createFromBigInteger(deserializationContext, bigInteger);
        }
        try {
            return annotatedWithParams.call1(bigInteger);
        } catch (Throwable th) {
            return deserializationContext.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), bigInteger, rewrapCtorProblem(deserializationContext, th));
        }
    }

    public Object createFromBoolean(DeserializationContext deserializationContext, boolean z2) throws IOException {
        if (this._fromBooleanCreator == null) {
            return super.createFromBoolean(deserializationContext, z2);
        }
        Boolean valueOf = Boolean.valueOf(z2);
        try {
            return this._fromBooleanCreator.call1(valueOf);
        } catch (Throwable th) {
            return deserializationContext.handleInstantiationProblem(this._fromBooleanCreator.getDeclaringClass(), valueOf, rewrapCtorProblem(deserializationContext, th));
        }
    }

    public Object createFromDouble(DeserializationContext deserializationContext, double d2) throws IOException {
        if (this._fromDoubleCreator != null) {
            Double valueOf = Double.valueOf(d2);
            try {
                return this._fromDoubleCreator.call1(valueOf);
            } catch (Throwable th) {
                return deserializationContext.handleInstantiationProblem(this._fromDoubleCreator.getDeclaringClass(), valueOf, rewrapCtorProblem(deserializationContext, th));
            }
        } else if (this._fromBigDecimalCreator == null) {
            return super.createFromDouble(deserializationContext, d2);
        } else {
            BigDecimal valueOf2 = BigDecimal.valueOf(d2);
            try {
                return this._fromBigDecimalCreator.call1(valueOf2);
            } catch (Throwable th2) {
                return deserializationContext.handleInstantiationProblem(this._fromBigDecimalCreator.getDeclaringClass(), valueOf2, rewrapCtorProblem(deserializationContext, th2));
            }
        }
    }

    public Object createFromInt(DeserializationContext deserializationContext, int i3) throws IOException {
        if (this._fromIntCreator != null) {
            Integer valueOf = Integer.valueOf(i3);
            try {
                return this._fromIntCreator.call1(valueOf);
            } catch (Throwable th) {
                return deserializationContext.handleInstantiationProblem(this._fromIntCreator.getDeclaringClass(), valueOf, rewrapCtorProblem(deserializationContext, th));
            }
        } else if (this._fromLongCreator != null) {
            Long valueOf2 = Long.valueOf((long) i3);
            try {
                return this._fromLongCreator.call1(valueOf2);
            } catch (Throwable th2) {
                return deserializationContext.handleInstantiationProblem(this._fromLongCreator.getDeclaringClass(), valueOf2, rewrapCtorProblem(deserializationContext, th2));
            }
        } else if (this._fromBigIntegerCreator == null) {
            return super.createFromInt(deserializationContext, i3);
        } else {
            BigInteger valueOf3 = BigInteger.valueOf((long) i3);
            try {
                return this._fromBigIntegerCreator.call1(valueOf3);
            } catch (Throwable th3) {
                return deserializationContext.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), valueOf3, rewrapCtorProblem(deserializationContext, th3));
            }
        }
    }

    public Object createFromLong(DeserializationContext deserializationContext, long j2) throws IOException {
        if (this._fromLongCreator != null) {
            Long valueOf = Long.valueOf(j2);
            try {
                return this._fromLongCreator.call1(valueOf);
            } catch (Throwable th) {
                return deserializationContext.handleInstantiationProblem(this._fromLongCreator.getDeclaringClass(), valueOf, rewrapCtorProblem(deserializationContext, th));
            }
        } else if (this._fromBigIntegerCreator == null) {
            return super.createFromLong(deserializationContext, j2);
        } else {
            BigInteger valueOf2 = BigInteger.valueOf(j2);
            try {
                return this._fromBigIntegerCreator.call1(valueOf2);
            } catch (Throwable th2) {
                return deserializationContext.handleInstantiationProblem(this._fromBigIntegerCreator.getDeclaringClass(), valueOf2, rewrapCtorProblem(deserializationContext, th2));
            }
        }
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) throws IOException {
        AnnotatedWithParams annotatedWithParams = this._withArgsCreator;
        if (annotatedWithParams == null) {
            return super.createFromObjectWith(deserializationContext, objArr);
        }
        try {
            return annotatedWithParams.call(objArr);
        } catch (Exception e3) {
            return deserializationContext.handleInstantiationProblem(this._valueClass, objArr, rewrapCtorProblem(deserializationContext, e3));
        }
    }

    public Object createFromString(DeserializationContext deserializationContext, String str) throws IOException {
        AnnotatedWithParams annotatedWithParams = this._fromStringCreator;
        if (annotatedWithParams == null) {
            return super.createFromString(deserializationContext, str);
        }
        try {
            return annotatedWithParams.call1(str);
        } catch (Throwable th) {
            return deserializationContext.handleInstantiationProblem(this._fromStringCreator.getDeclaringClass(), str, rewrapCtorProblem(deserializationContext, th));
        }
    }

    public Object createUsingArrayDelegate(DeserializationContext deserializationContext, Object obj) throws IOException {
        AnnotatedWithParams annotatedWithParams = this._arrayDelegateCreator;
        return (annotatedWithParams != null || this._delegateCreator == null) ? _createUsingDelegate(annotatedWithParams, this._arrayDelegateArguments, deserializationContext, obj) : createUsingDelegate(deserializationContext, obj);
    }

    public Object createUsingDefault(DeserializationContext deserializationContext) throws IOException {
        AnnotatedWithParams annotatedWithParams = this._defaultCreator;
        if (annotatedWithParams == null) {
            return super.createUsingDefault(deserializationContext);
        }
        try {
            return annotatedWithParams.call();
        } catch (Exception e3) {
            return deserializationContext.handleInstantiationProblem(this._valueClass, (Object) null, rewrapCtorProblem(deserializationContext, e3));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2._arrayDelegateCreator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object createUsingDelegate(com.fasterxml.jackson.databind.DeserializationContext r3, java.lang.Object r4) throws java.io.IOException {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r0 = r2._delegateCreator
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r1 = r2._arrayDelegateCreator
            if (r1 == 0) goto L_0x000f
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r0 = r2._arrayDelegateArguments
            java.lang.Object r2 = r2._createUsingDelegate(r1, r0, r3, r4)
            return r2
        L_0x000f:
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r1 = r2._delegateArguments
            java.lang.Object r2 = r2._createUsingDelegate(r0, r1, r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StdValueInstantiator.createUsingDelegate(com.fasterxml.jackson.databind.DeserializationContext, java.lang.Object):java.lang.Object");
    }

    public AnnotatedWithParams getArrayDelegateCreator() {
        return this._arrayDelegateCreator;
    }

    public JavaType getArrayDelegateType(DeserializationConfig deserializationConfig) {
        return this._arrayDelegateType;
    }

    public AnnotatedWithParams getDefaultCreator() {
        return this._defaultCreator;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return this._delegateCreator;
    }

    public JavaType getDelegateType(DeserializationConfig deserializationConfig) {
        return this._delegateType;
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        return this._constructorArguments;
    }

    public Class<?> getValueClass() {
        return this._valueClass;
    }

    public String getValueTypeDesc() {
        return this._valueTypeDesc;
    }

    public AnnotatedWithParams getWithArgsCreator() {
        return this._withArgsCreator;
    }

    public JsonMappingException rewrapCtorProblem(DeserializationContext deserializationContext, Throwable th) {
        Throwable cause;
        if (((th instanceof ExceptionInInitializerError) || (th instanceof InvocationTargetException)) && (cause = th.getCause()) != null) {
            th = cause;
        }
        return wrapAsJsonMappingException(deserializationContext, th);
    }

    @Deprecated
    public JsonMappingException unwrapAndWrapException(DeserializationContext deserializationContext, Throwable th) {
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof JsonMappingException) {
                return (JsonMappingException) th2;
            }
        }
        return deserializationContext.instantiationException(getValueClass(), th);
    }

    public JsonMappingException wrapAsJsonMappingException(DeserializationContext deserializationContext, Throwable th) {
        return th instanceof JsonMappingException ? (JsonMappingException) th : deserializationContext.instantiationException(getValueClass(), th);
    }

    @Deprecated
    public JsonMappingException wrapException(Throwable th) {
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof JsonMappingException) {
                return (JsonMappingException) th2;
            }
        }
        return new JsonMappingException((Closeable) null, "Instantiation of " + getValueTypeDesc() + " value failed: " + ClassUtil.exceptionMessage(th), th);
    }

    public StdValueInstantiator(DeserializationConfig deserializationConfig, JavaType javaType) {
        this._valueTypeDesc = javaType == null ? "UNKNOWN TYPE" : javaType.toString();
        this._valueClass = javaType == null ? Object.class : javaType.getRawClass();
    }

    public StdValueInstantiator(StdValueInstantiator stdValueInstantiator) {
        this._valueTypeDesc = stdValueInstantiator._valueTypeDesc;
        this._valueClass = stdValueInstantiator._valueClass;
        this._defaultCreator = stdValueInstantiator._defaultCreator;
        this._constructorArguments = stdValueInstantiator._constructorArguments;
        this._withArgsCreator = stdValueInstantiator._withArgsCreator;
        this._delegateType = stdValueInstantiator._delegateType;
        this._delegateCreator = stdValueInstantiator._delegateCreator;
        this._delegateArguments = stdValueInstantiator._delegateArguments;
        this._arrayDelegateType = stdValueInstantiator._arrayDelegateType;
        this._arrayDelegateCreator = stdValueInstantiator._arrayDelegateCreator;
        this._arrayDelegateArguments = stdValueInstantiator._arrayDelegateArguments;
        this._fromStringCreator = stdValueInstantiator._fromStringCreator;
        this._fromIntCreator = stdValueInstantiator._fromIntCreator;
        this._fromLongCreator = stdValueInstantiator._fromLongCreator;
        this._fromBigIntegerCreator = stdValueInstantiator._fromBigIntegerCreator;
        this._fromDoubleCreator = stdValueInstantiator._fromDoubleCreator;
        this._fromBigDecimalCreator = stdValueInstantiator._fromBigDecimalCreator;
        this._fromBooleanCreator = stdValueInstantiator._fromBooleanCreator;
    }
}
