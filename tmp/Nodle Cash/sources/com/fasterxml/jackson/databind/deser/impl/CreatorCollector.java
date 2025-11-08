package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Member;
import java.util.HashMap;

public class CreatorCollector {
    protected static final int C_ARRAY_DELEGATE = 10;
    protected static final int C_BIG_DECIMAL = 6;
    protected static final int C_BIG_INTEGER = 4;
    protected static final int C_BOOLEAN = 7;
    protected static final int C_DEFAULT = 0;
    protected static final int C_DELEGATE = 8;
    protected static final int C_DOUBLE = 5;
    protected static final int C_INT = 2;
    protected static final int C_LONG = 3;
    protected static final int C_PROPS = 9;
    protected static final int C_STRING = 1;
    protected static final String[] TYPE_DESCS = {"default", "from-String", "from-int", "from-long", "from-big-integer", "from-double", "from-big-decimal", "from-boolean", "delegate", "property-based", "array-delegate"};
    protected SettableBeanProperty[] _arrayDelegateArgs;
    protected final BeanDescription _beanDesc;
    protected final boolean _canFixAccess;
    protected final AnnotatedWithParams[] _creators = new AnnotatedWithParams[11];
    protected SettableBeanProperty[] _delegateArgs;
    protected int _explicitCreators = 0;
    protected final boolean _forceAccess;
    protected boolean _hasNonDefaultCreator = false;
    protected SettableBeanProperty[] _propertyBasedArgs;

    public CreatorCollector(BeanDescription beanDescription, MapperConfig<?> mapperConfig) {
        this._beanDesc = beanDescription;
        this._canFixAccess = mapperConfig.canOverrideAccessModifiers();
        this._forceAccess = mapperConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
    }

    private JavaType _computeDelegateType(DeserializationContext deserializationContext, AnnotatedWithParams annotatedWithParams, SettableBeanProperty[] settableBeanPropertyArr) throws JsonMappingException {
        if (!this._hasNonDefaultCreator || annotatedWithParams == null) {
            return null;
        }
        int i3 = 0;
        if (settableBeanPropertyArr != null) {
            int length = settableBeanPropertyArr.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    break;
                } else if (settableBeanPropertyArr[i4] == null) {
                    i3 = i4;
                    break;
                } else {
                    i4++;
                }
            }
        }
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType parameterType = annotatedWithParams.getParameterType(i3);
        AnnotationIntrospector annotationIntrospector = config.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return parameterType;
        }
        AnnotatedParameter parameter = annotatedWithParams.getParameter(i3);
        Object findDeserializer = annotationIntrospector.findDeserializer(parameter);
        return findDeserializer != null ? parameterType.withValueHandler(deserializationContext.deserializerInstance(parameter, findDeserializer)) : annotationIntrospector.refineDeserializationType(config, parameter, parameterType);
    }

    private <T extends AnnotatedMember> T _fixAccess(T t2) {
        if (t2 != null && this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member) t2.getAnnotated(), this._forceAccess);
        }
        return t2;
    }

    public boolean _isEnumValueOf(AnnotatedWithParams annotatedWithParams) {
        return ClassUtil.isEnumType(annotatedWithParams.getDeclaringClass()) && "valueOf".equals(annotatedWithParams.getName());
    }

    public void _reportDuplicateCreator(int i3, boolean z2, AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2) {
        throw new IllegalArgumentException(String.format("Conflicting %s creators: already had %s creator %s, encountered another: %s", new Object[]{TYPE_DESCS[i3], z2 ? "explicitly marked" : "implicitly discovered", annotatedWithParams, annotatedWithParams2}));
    }

    public void addBigDecimalCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 6, z2);
    }

    public void addBigIntegerCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 4, z2);
    }

    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 7, z2);
    }

    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams, boolean z2, SettableBeanProperty[] settableBeanPropertyArr, int i3) {
        if (annotatedWithParams.getParameterType(i3).isCollectionLikeType()) {
            if (verifyNonDup(annotatedWithParams, 10, z2)) {
                this._arrayDelegateArgs = settableBeanPropertyArr;
            }
        } else if (verifyNonDup(annotatedWithParams, 8, z2)) {
            this._delegateArgs = settableBeanPropertyArr;
        }
    }

    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 5, z2);
    }

    public void addIntCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 2, z2);
    }

    public void addLongCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 3, z2);
    }

    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, boolean z2, SettableBeanProperty[] settableBeanPropertyArr) {
        Integer num;
        if (verifyNonDup(annotatedWithParams, 9, z2)) {
            if (settableBeanPropertyArr.length > 1) {
                HashMap hashMap = new HashMap();
                int length = settableBeanPropertyArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    String name = settableBeanPropertyArr[i3].getName();
                    if ((!name.isEmpty() || settableBeanPropertyArr[i3].getInjectableValueId() == null) && (num = (Integer) hashMap.put(name, Integer.valueOf(i3))) != null) {
                        throw new IllegalArgumentException(String.format("Duplicate creator property \"%s\" (index %s vs %d) for type %s ", new Object[]{name, num, Integer.valueOf(i3), ClassUtil.nameOf(this._beanDesc.getBeanClass())}));
                    }
                }
            }
            this._propertyBasedArgs = settableBeanPropertyArr;
        }
    }

    public void addStringCreator(AnnotatedWithParams annotatedWithParams, boolean z2) {
        verifyNonDup(annotatedWithParams, 1, z2);
    }

    public ValueInstantiator constructValueInstantiator(DeserializationContext deserializationContext) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType _computeDelegateType = _computeDelegateType(deserializationContext, this._creators[8], this._delegateArgs);
        JavaType _computeDelegateType2 = _computeDelegateType(deserializationContext, this._creators[10], this._arrayDelegateArgs);
        StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(config, this._beanDesc.getType());
        AnnotatedWithParams[] annotatedWithParamsArr = this._creators;
        stdValueInstantiator.configureFromObjectSettings(annotatedWithParamsArr[0], annotatedWithParamsArr[8], _computeDelegateType, this._delegateArgs, annotatedWithParamsArr[9], this._propertyBasedArgs);
        stdValueInstantiator.configureFromArraySettings(this._creators[10], _computeDelegateType2, this._arrayDelegateArgs);
        stdValueInstantiator.configureFromStringCreator(this._creators[1]);
        stdValueInstantiator.configureFromIntCreator(this._creators[2]);
        stdValueInstantiator.configureFromLongCreator(this._creators[3]);
        stdValueInstantiator.configureFromBigIntegerCreator(this._creators[4]);
        stdValueInstantiator.configureFromDoubleCreator(this._creators[5]);
        stdValueInstantiator.configureFromBigDecimalCreator(this._creators[6]);
        stdValueInstantiator.configureFromBooleanCreator(this._creators[7]);
        return stdValueInstantiator;
    }

    public boolean hasDefaultCreator() {
        return this._creators[0] != null;
    }

    public boolean hasDelegatingCreator() {
        return this._creators[8] != null;
    }

    public boolean hasPropertyBasedCreator() {
        return this._creators[9] != null;
    }

    public void setDefaultCreator(AnnotatedWithParams annotatedWithParams) {
        this._creators[0] = (AnnotatedWithParams) _fixAccess(annotatedWithParams);
    }

    public boolean verifyNonDup(AnnotatedWithParams annotatedWithParams, int i3, boolean z2) {
        boolean z3;
        int i4 = 1 << i3;
        this._hasNonDefaultCreator = true;
        AnnotatedWithParams annotatedWithParams2 = this._creators[i3];
        if (annotatedWithParams2 != null) {
            if ((this._explicitCreators & i4) == 0) {
                z3 = !z2;
            } else if (!z2) {
                return false;
            } else {
                z3 = true;
            }
            if (z3 && annotatedWithParams2.getClass() == annotatedWithParams.getClass()) {
                Class<?> rawParameterType = annotatedWithParams2.getRawParameterType(0);
                Class<?> rawParameterType2 = annotatedWithParams.getRawParameterType(0);
                if (rawParameterType == rawParameterType2) {
                    if (_isEnumValueOf(annotatedWithParams)) {
                        return false;
                    }
                    if (!_isEnumValueOf(annotatedWithParams2)) {
                        _reportDuplicateCreator(i3, z2, annotatedWithParams2, annotatedWithParams);
                    }
                } else if (rawParameterType2.isAssignableFrom(rawParameterType)) {
                    return false;
                } else {
                    if (!rawParameterType.isAssignableFrom(rawParameterType2)) {
                        if (rawParameterType.isPrimitive() == rawParameterType2.isPrimitive()) {
                            _reportDuplicateCreator(i3, z2, annotatedWithParams2, annotatedWithParams);
                        } else if (rawParameterType.isPrimitive()) {
                            return false;
                        }
                    }
                }
            }
        }
        if (z2) {
            this._explicitCreators |= i4;
        }
        this._creators[i3] = (AnnotatedWithParams) _fixAccess(annotatedWithParams);
        return true;
    }
}
