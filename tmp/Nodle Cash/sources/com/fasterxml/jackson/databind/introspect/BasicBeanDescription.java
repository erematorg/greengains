package com.fasterxml.jackson.databind.introspect;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
    private static final Class<?>[] NO_VIEWS = new Class[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;
    protected Class<?>[] _defaultViews;
    protected boolean _defaultViewsResolved;
    protected ObjectIdInfo _objectIdInfo;
    protected final POJOPropertiesCollector _propCollector;
    protected List<BeanPropertyDefinition> _properties;

    public BasicBeanDescription(POJOPropertiesCollector pOJOPropertiesCollector, JavaType javaType, AnnotatedClass annotatedClass) {
        super(javaType);
        this._propCollector = pOJOPropertiesCollector;
        MapperConfig<?> config = pOJOPropertiesCollector.getConfig();
        this._config = config;
        if (config == null) {
            this._annotationIntrospector = null;
        } else {
            this._annotationIntrospector = config.getAnnotationIntrospector();
        }
        this._classInfo = annotatedClass;
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        return new BasicBeanDescription(pOJOPropertiesCollector);
    }

    public static BasicBeanDescription forOtherUse(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        return new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        return new BasicBeanDescription(pOJOPropertiesCollector);
    }

    public Converter<Object, Object> _createConverter(Object obj) {
        Converter<?, ?> converter = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (obj instanceof Class) {
            Class<Converter.None> cls = (Class) obj;
            if (cls == Converter.None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (Converter.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    converter = handlerInstantiator.converterInstance(this._config, this._classInfo, cls);
                }
                return converter == null ? (Converter) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : converter;
            }
            throw new IllegalStateException(b.g(cls, new StringBuilder("AnnotationIntrospector returned Class "), "; expected Class<Converter>"));
        }
        throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r2 = r2._annotationIntrospector.findImplicitPropertyName(r3);
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName _findCreatorPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForDeserialization(r3)
            if (r0 == 0) goto L_0x000e
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0020
        L_0x000e:
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._annotationIntrospector
            java.lang.String r2 = r2.findImplicitPropertyName(r3)
            if (r2 == 0) goto L_0x0020
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0020
            com.fasterxml.jackson.databind.PropertyName r0 = com.fasterxml.jackson.databind.PropertyName.construct(r2)
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.BasicBeanDescription._findCreatorPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter):com.fasterxml.jackson.databind.PropertyName");
    }

    @Deprecated
    public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> collection, boolean z2) {
        LinkedHashMap<String, AnnotatedField> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition next : _properties()) {
            AnnotatedField field = next.getField();
            if (field != null) {
                String name = next.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, field);
                }
            }
        }
        return linkedHashMap;
    }

    public List<BeanPropertyDefinition> _properties() {
        if (this._properties == null) {
            this._properties = this._propCollector.getProperties();
        }
        return this._properties;
    }

    public boolean addProperty(BeanPropertyDefinition beanPropertyDefinition) {
        if (hasProperty(beanPropertyDefinition.getFullName())) {
            return false;
        }
        _properties().add(beanPropertyDefinition);
        return true;
    }

    @Deprecated
    public TypeBindings bindingsForBeanType() {
        return this._type.getBindings();
    }

    public AnnotatedMember findAnyGetter() throws IllegalArgumentException {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        AnnotatedMember anyGetterMethod = pOJOPropertiesCollector.getAnyGetterMethod();
        Class<Map> cls = Map.class;
        if (anyGetterMethod == null) {
            AnnotatedMember anyGetterField = this._propCollector.getAnyGetterField();
            if (anyGetterField == null) {
                return null;
            }
            if (cls.isAssignableFrom(anyGetterField.getRawType())) {
                return anyGetterField;
            }
            throw new IllegalArgumentException(a.l("Invalid 'any-getter' annotation on field '", anyGetterField.getName(), "': type is not instance of java.util.Map"));
        } else if (cls.isAssignableFrom(anyGetterMethod.getRawType())) {
            return anyGetterMethod;
        } else {
            throw new IllegalArgumentException(a.l("Invalid 'any-getter' annotation on method ", anyGetterMethod.getName(), "(): return type is not instance of java.util.Map"));
        }
    }

    public AnnotatedMember findAnySetterAccessor() throws IllegalArgumentException {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        AnnotatedMethod anySetterMethod = pOJOPropertiesCollector.getAnySetterMethod();
        if (anySetterMethod != null) {
            Class<?> rawParameterType = anySetterMethod.getRawParameterType(0);
            if (rawParameterType == String.class || rawParameterType == Object.class) {
                return anySetterMethod;
            }
            throw new IllegalArgumentException(C0118y.f("Invalid 'any-setter' annotation on method '", anySetterMethod.getName(), "()': first argument not of type String or Object, but ", rawParameterType.getName()));
        }
        AnnotatedMember anySetterField = this._propCollector.getAnySetterField();
        if (anySetterField == null) {
            return null;
        }
        Class<?> rawType = anySetterField.getRawType();
        if (Map.class.isAssignableFrom(rawType) || JsonNode.class.isAssignableFrom(rawType)) {
            return anySetterField;
        }
        throw new IllegalArgumentException(a.l("Invalid 'any-setter' annotation on field '", anySetterField.getName(), "': type is not instance of `java.util.Map` or `JsonNode`"));
    }

    @Deprecated
    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        List<BeanPropertyDefinition> findBackReferences = findBackReferences();
        if (findBackReferences == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition next : findBackReferences) {
            hashMap.put(next.getName(), next.getMutator());
        }
        return hashMap;
    }

    public List<BeanPropertyDefinition> findBackReferences() {
        ArrayList arrayList = null;
        HashSet hashSet = null;
        for (BeanPropertyDefinition next : _properties()) {
            AnnotationIntrospector.ReferenceProperty findReferenceType = next.findReferenceType();
            if (findReferenceType != null && findReferenceType.isBackReference()) {
                String name = findReferenceType.getName();
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    hashSet = new HashSet();
                    hashSet.add(name);
                } else if (!hashSet.add(name)) {
                    throw new IllegalArgumentException("Multiple back-reference properties with name " + ClassUtil.name(name));
                }
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public String findClassDescription() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findClassDescription(this._classInfo);
    }

    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    public Class<?>[] findDefaultViews() {
        if (!this._defaultViewsResolved) {
            this._defaultViewsResolved = true;
            AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
            Class<?>[] findViews = annotationIntrospector == null ? null : annotationIntrospector.findViews(this._classInfo);
            if (findViews == null && !this._config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION)) {
                findViews = NO_VIEWS;
            }
            this._defaultViews = findViews;
        }
        return this._defaultViews;
    }

    public Converter<Object, Object> findDeserializationConverter() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return _createConverter(annotationIntrospector.findDeserializationConverter(this._classInfo));
    }

    public JsonFormat.Value findExpectedFormat(JsonFormat.Value value) {
        JsonFormat.Value findFormat;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (!(annotationIntrospector == null || (findFormat = annotationIntrospector.findFormat(this._classInfo)) == null)) {
            value = value == null ? findFormat : value.withOverrides(findFormat);
        }
        JsonFormat.Value defaultPropertyFormat = this._config.getDefaultPropertyFormat(this._classInfo.getRawType());
        return defaultPropertyFormat != null ? value == null ? defaultPropertyFormat : value.withOverrides(defaultPropertyFormat) : value;
    }

    @Deprecated
    public Method findFactoryMethod(Class<?>... clsArr) {
        for (AnnotatedMethod next : this._classInfo.getFactoryMethods()) {
            if (isFactoryMethod(next) && next.getParameterCount() == 1) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                for (Class<?> isAssignableFrom : clsArr) {
                    if (rawParameterType.isAssignableFrom(isAssignableFrom)) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode> findFactoryMethodMetadata(AnnotatedMethod annotatedMethod) {
        Class<?> rawParameterType;
        if (!getBeanClass().isAssignableFrom(annotatedMethod.getRawReturnType())) {
            return null;
        }
        JsonCreator.Mode findCreatorAnnotation = this._annotationIntrospector.findCreatorAnnotation(this._config, annotatedMethod);
        if (findCreatorAnnotation == null) {
            String name = annotatedMethod.getName();
            if ("valueOf".equals(name) && annotatedMethod.getParameterCount() == 1) {
                return AnnotatedAndMetadata.of(annotatedMethod, findCreatorAnnotation);
            }
            if (!"fromString".equals(name) || annotatedMethod.getParameterCount() != 1 || ((rawParameterType = annotatedMethod.getRawParameterType(0)) != String.class && !CharSequence.class.isAssignableFrom(rawParameterType))) {
                return null;
            }
            return AnnotatedAndMetadata.of(annotatedMethod, findCreatorAnnotation);
        } else if (findCreatorAnnotation == JsonCreator.Mode.DISABLED) {
            return null;
        } else {
            return AnnotatedAndMetadata.of(annotatedMethod, findCreatorAnnotation);
        }
    }

    public Map<Object, AnnotatedMember> findInjectables() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        return pOJOPropertiesCollector != null ? pOJOPropertiesCollector.getInjectables() : Collections.emptyMap();
    }

    public AnnotatedMember findJsonKeyAccessor() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        return pOJOPropertiesCollector.getJsonKeyAccessor();
    }

    public AnnotatedMember findJsonValueAccessor() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        return pOJOPropertiesCollector.getJsonValueAccessor();
    }

    @Deprecated
    public AnnotatedMethod findJsonValueMethod() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        return pOJOPropertiesCollector.getJsonValueMethod();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    public Class<?> findPOJOBuilder() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findPOJOBuilder(this._classInfo);
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findPOJOBuilderConfig(this._classInfo);
    }

    public List<BeanPropertyDefinition> findProperties() {
        return _properties();
    }

    public BeanPropertyDefinition findProperty(PropertyName propertyName) {
        for (BeanPropertyDefinition next : _properties()) {
            if (next.hasName(propertyName)) {
                return next;
            }
        }
        return null;
    }

    public JsonInclude.Value findPropertyInclusion(JsonInclude.Value value) {
        JsonInclude.Value findPropertyInclusion;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        return (annotationIntrospector == null || (findPropertyInclusion = annotationIntrospector.findPropertyInclusion(this._classInfo)) == null) ? value : value == null ? findPropertyInclusion : value.withOverrides(findPropertyInclusion);
    }

    public Converter<Object, Object> findSerializationConverter() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return _createConverter(annotationIntrospector.findSerializationConverter(this._classInfo));
    }

    @Deprecated
    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        for (AnnotatedConstructor next : this._classInfo.getConstructors()) {
            if (next.getParameterCount() == 1) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                for (Class<?> cls : clsArr) {
                    if (cls == rawParameterType) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public List<AnnotatedAndMetadata<AnnotatedConstructor, JsonCreator.Mode>> getConstructorsWithMode() {
        List<AnnotatedConstructor> constructors = this._classInfo.getConstructors();
        if (constructors.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedConstructor next : constructors) {
            JsonCreator.Mode findCreatorAnnotation = this._annotationIntrospector.findCreatorAnnotation(this._config, next);
            if (findCreatorAnnotation != JsonCreator.Mode.DISABLED) {
                arrayList.add(AnnotatedAndMetadata.of(next, findCreatorAnnotation));
            }
        }
        return arrayList;
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        List<AnnotatedMethod> factoryMethods = this._classInfo.getFactoryMethods();
        if (factoryMethods.isEmpty()) {
            return factoryMethods;
        }
        ArrayList arrayList = null;
        for (AnnotatedMethod next : factoryMethods) {
            if (isFactoryMethod(next)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(next);
            }
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    public List<AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode>> getFactoryMethodsWithMode() {
        List<AnnotatedMethod> factoryMethods = this._classInfo.getFactoryMethods();
        if (factoryMethods.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = null;
        for (AnnotatedMethod findFactoryMethodMetadata : factoryMethods) {
            AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode> findFactoryMethodMetadata2 = findFactoryMethodMetadata(findFactoryMethodMetadata);
            if (findFactoryMethodMetadata2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(findFactoryMethodMetadata2);
            }
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    public Set<String> getIgnoredPropertyNames() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        Set<String> ignoredPropertyNames = pOJOPropertiesCollector == null ? null : pOJOPropertiesCollector.getIgnoredPropertyNames();
        return ignoredPropertyNames == null ? Collections.emptySet() : ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public boolean hasProperty(PropertyName propertyName) {
        return findProperty(propertyName) != null;
    }

    public Object instantiateBean(boolean z2) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z2) {
            defaultConstructor.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        try {
            return defaultConstructor.call();
        } catch (Exception e3) {
            e = e3;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            ClassUtil.throwIfError(e);
            ClassUtil.throwIfRTE(e);
            throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + e.getClass().getName() + ") " + ClassUtil.exceptionMessage(e), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r3 = r4.getRawParameterType(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isFactoryMethod(com.fasterxml.jackson.databind.introspect.AnnotatedMethod r4) {
        /*
            r3 = this;
            java.lang.Class r0 = r4.getRawReturnType()
            java.lang.Class r1 = r3.getBeanClass()
            boolean r0 = r1.isAssignableFrom(r0)
            r1 = 0
            if (r0 != 0) goto L_0x0010
            return r1
        L_0x0010:
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r3._annotationIntrospector
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r3 = r3._config
            com.fasterxml.jackson.annotation.JsonCreator$Mode r3 = r0.findCreatorAnnotation(r3, r4)
            r0 = 1
            if (r3 == 0) goto L_0x0020
            com.fasterxml.jackson.annotation.JsonCreator$Mode r2 = com.fasterxml.jackson.annotation.JsonCreator.Mode.DISABLED
            if (r3 == r2) goto L_0x0020
            return r0
        L_0x0020:
            java.lang.String r3 = r4.getName()
            java.lang.String r2 = "valueOf"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0034
            int r2 = r4.getParameterCount()
            if (r2 != r0) goto L_0x0034
            return r0
        L_0x0034:
            java.lang.String r2 = "fromString"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x0053
            int r3 = r4.getParameterCount()
            if (r3 != r0) goto L_0x0053
            java.lang.Class r3 = r4.getRawParameterType(r1)
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            if (r3 == r4) goto L_0x0052
            java.lang.Class<java.lang.CharSequence> r4 = java.lang.CharSequence.class
            boolean r3 = r4.isAssignableFrom(r3)
            if (r3 == 0) goto L_0x0053
        L_0x0052:
            return r0
        L_0x0053:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.BasicBeanDescription.isFactoryMethod(com.fasterxml.jackson.databind.introspect.AnnotatedMethod):boolean");
    }

    public boolean removeProperty(String str) {
        Iterator<BeanPropertyDefinition> it = _properties().iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(str)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public JavaType resolveType(Type type) {
        return this._config.getTypeFactory().resolveMemberType(type, this._type.getBindings());
    }

    public BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List<BeanPropertyDefinition> list) {
        super(javaType);
        this._propCollector = null;
        this._config = mapperConfig;
        if (mapperConfig == null) {
            this._annotationIntrospector = null;
        } else {
            this._annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        }
        this._classInfo = annotatedClass;
        this._properties = list;
    }

    public BasicBeanDescription(POJOPropertiesCollector pOJOPropertiesCollector) {
        this(pOJOPropertiesCollector, pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef());
        this._objectIdInfo = pOJOPropertiesCollector.getObjectIdInfo();
    }
}
