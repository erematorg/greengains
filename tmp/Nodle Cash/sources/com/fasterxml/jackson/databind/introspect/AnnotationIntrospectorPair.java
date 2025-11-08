package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair extends AnnotationIntrospector implements Serializable {
    private static final long serialVersionUID = 1;
    protected final AnnotationIntrospector _primary;
    protected final AnnotationIntrospector _secondary;

    public AnnotationIntrospectorPair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._primary = annotationIntrospector;
        this._secondary = annotationIntrospector2;
    }

    public static AnnotationIntrospector create(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        return annotationIntrospector == null ? annotationIntrospector2 : annotationIntrospector2 == null ? annotationIntrospector : new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
    }

    public Object _explicitClassOrOb(Object obj, Class<?> cls) {
        if (obj == null || obj == cls) {
            return null;
        }
        if (!(obj instanceof Class) || !ClassUtil.isBogusClass((Class) obj)) {
            return obj;
        }
        return null;
    }

    public boolean _isExplicitClassOrOb(Object obj, Class<?> cls) {
        if (obj == null || obj == cls) {
            return false;
        }
        if (obj instanceof Class) {
            return !ClassUtil.isBogusClass((Class) obj);
        }
        return true;
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return allIntrospectors(new ArrayList());
    }

    public void findAndAddVirtualProperties(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, List<BeanPropertyWriter> list) {
        this._primary.findAndAddVirtualProperties(mapperConfig, annotatedClass, list);
        this._secondary.findAndAddVirtualProperties(mapperConfig, annotatedClass, list);
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return this._primary.findAutoDetectVisibility(annotatedClass, this._secondary.findAutoDetectVisibility(annotatedClass, visibilityChecker));
    }

    public String findClassDescription(AnnotatedClass annotatedClass) {
        String findClassDescription = this._primary.findClassDescription(annotatedClass);
        return (findClassDescription == null || findClassDescription.isEmpty()) ? this._secondary.findClassDescription(annotatedClass) : findClassDescription;
    }

    public Object findContentDeserializer(Annotated annotated) {
        Object findContentDeserializer = this._primary.findContentDeserializer(annotated);
        Class<JsonDeserializer.None> cls = JsonDeserializer.None.class;
        return _isExplicitClassOrOb(findContentDeserializer, cls) ? findContentDeserializer : _explicitClassOrOb(this._secondary.findContentDeserializer(annotated), cls);
    }

    public Object findContentSerializer(Annotated annotated) {
        Object findContentSerializer = this._primary.findContentSerializer(annotated);
        Class<JsonSerializer.None> cls = JsonSerializer.None.class;
        return _isExplicitClassOrOb(findContentSerializer, cls) ? findContentSerializer : _explicitClassOrOb(this._secondary.findContentSerializer(annotated), cls);
    }

    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> mapperConfig, Annotated annotated) {
        JsonCreator.Mode findCreatorAnnotation = this._primary.findCreatorAnnotation(mapperConfig, annotated);
        return findCreatorAnnotation == null ? this._secondary.findCreatorAnnotation(mapperConfig, annotated) : findCreatorAnnotation;
    }

    @Deprecated
    public JsonCreator.Mode findCreatorBinding(Annotated annotated) {
        JsonCreator.Mode findCreatorBinding = this._primary.findCreatorBinding(annotated);
        return findCreatorBinding != null ? findCreatorBinding : this._secondary.findCreatorBinding(annotated);
    }

    public Enum<?> findDefaultEnumValue(Class<Enum<?>> cls) {
        Enum<?> findDefaultEnumValue = this._primary.findDefaultEnumValue(cls);
        return findDefaultEnumValue == null ? this._secondary.findDefaultEnumValue(cls) : findDefaultEnumValue;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        Object findDeserializationContentConverter = this._primary.findDeserializationContentConverter(annotatedMember);
        return findDeserializationContentConverter == null ? this._secondary.findDeserializationContentConverter(annotatedMember) : findDeserializationContentConverter;
    }

    @Deprecated
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationContentType = this._primary.findDeserializationContentType(annotated, javaType);
        return findDeserializationContentType == null ? this._secondary.findDeserializationContentType(annotated, javaType) : findDeserializationContentType;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Object findDeserializationConverter = this._primary.findDeserializationConverter(annotated);
        return findDeserializationConverter == null ? this._secondary.findDeserializationConverter(annotated) : findDeserializationConverter;
    }

    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationKeyType = this._primary.findDeserializationKeyType(annotated, javaType);
        return findDeserializationKeyType == null ? this._secondary.findDeserializationKeyType(annotated, javaType) : findDeserializationKeyType;
    }

    @Deprecated
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationType = this._primary.findDeserializationType(annotated, javaType);
        return findDeserializationType != null ? findDeserializationType : this._secondary.findDeserializationType(annotated, javaType);
    }

    public Object findDeserializer(Annotated annotated) {
        Object findDeserializer = this._primary.findDeserializer(annotated);
        Class<JsonDeserializer.None> cls = JsonDeserializer.None.class;
        return _isExplicitClassOrOb(findDeserializer, cls) ? findDeserializer : _explicitClassOrOb(this._secondary.findDeserializer(annotated), cls);
    }

    public void findEnumAliases(Class<?> cls, Enum<?>[] enumArr, String[][] strArr) {
        this._secondary.findEnumAliases(cls, enumArr, strArr);
        this._primary.findEnumAliases(cls, enumArr, strArr);
    }

    @Deprecated
    public String findEnumValue(Enum<?> enumR) {
        String findEnumValue = this._primary.findEnumValue(enumR);
        return findEnumValue == null ? this._secondary.findEnumValue(enumR) : findEnumValue;
    }

    public String[] findEnumValues(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        return this._primary.findEnumValues(cls, enumArr, this._secondary.findEnumValues(cls, enumArr, strArr));
    }

    public Object findFilterId(Annotated annotated) {
        Object findFilterId = this._primary.findFilterId(annotated);
        return findFilterId == null ? this._secondary.findFilterId(annotated) : findFilterId;
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        JsonFormat.Value findFormat = this._primary.findFormat(annotated);
        JsonFormat.Value findFormat2 = this._secondary.findFormat(annotated);
        return findFormat2 == null ? findFormat : findFormat2.withOverrides(findFormat);
    }

    @Deprecated
    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        Boolean findIgnoreUnknownProperties = this._primary.findIgnoreUnknownProperties(annotatedClass);
        return findIgnoreUnknownProperties == null ? this._secondary.findIgnoreUnknownProperties(annotatedClass) : findIgnoreUnknownProperties;
    }

    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        String findImplicitPropertyName = this._primary.findImplicitPropertyName(annotatedMember);
        return findImplicitPropertyName == null ? this._secondary.findImplicitPropertyName(annotatedMember) : findImplicitPropertyName;
    }

    public JacksonInject.Value findInjectableValue(AnnotatedMember annotatedMember) {
        JacksonInject.Value findInjectableValue;
        JacksonInject.Value findInjectableValue2 = this._primary.findInjectableValue(annotatedMember);
        if ((findInjectableValue2 != null && findInjectableValue2.getUseInput() != null) || (findInjectableValue = this._secondary.findInjectableValue(annotatedMember)) == null) {
            return findInjectableValue2;
        }
        if (findInjectableValue2 != null) {
            findInjectableValue = findInjectableValue2.withUseInput(findInjectableValue.getUseInput());
        }
        return findInjectableValue;
    }

    @Deprecated
    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        Object findInjectableValueId = this._primary.findInjectableValueId(annotatedMember);
        return findInjectableValueId == null ? this._secondary.findInjectableValueId(annotatedMember) : findInjectableValueId;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        Object findKeyDeserializer = this._primary.findKeyDeserializer(annotated);
        Class<KeyDeserializer.None> cls = KeyDeserializer.None.class;
        return _isExplicitClassOrOb(findKeyDeserializer, cls) ? findKeyDeserializer : _explicitClassOrOb(this._secondary.findKeyDeserializer(annotated), cls);
    }

    public Object findKeySerializer(Annotated annotated) {
        Object findKeySerializer = this._primary.findKeySerializer(annotated);
        Class<JsonSerializer.None> cls = JsonSerializer.None.class;
        return _isExplicitClassOrOb(findKeySerializer, cls) ? findKeySerializer : _explicitClassOrOb(this._secondary.findKeySerializer(annotated), cls);
    }

    public Boolean findMergeInfo(Annotated annotated) {
        Boolean findMergeInfo = this._primary.findMergeInfo(annotated);
        return findMergeInfo == null ? this._secondary.findMergeInfo(annotated) : findMergeInfo;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r2 = r2._secondary.findNameForDeserialization(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findNameForDeserialization(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForDeserialization(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r2.findNameForDeserialization(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r2 = r2.findNameForDeserialization(r3)
            if (r2 == 0) goto L_0x001c
            r0 = r2
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findNameForDeserialization(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r2 = r2._secondary.findNameForSerialization(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findNameForSerialization(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForSerialization(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r2.findNameForSerialization(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r2 = r2.findNameForSerialization(r3)
            if (r2 == 0) goto L_0x001c
            r0 = r2
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findNameForSerialization(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        Object findNamingStrategy = this._primary.findNamingStrategy(annotatedClass);
        return findNamingStrategy == null ? this._secondary.findNamingStrategy(annotatedClass) : findNamingStrategy;
    }

    public Object findNullSerializer(Annotated annotated) {
        Object findNullSerializer = this._primary.findNullSerializer(annotated);
        Class<JsonSerializer.None> cls = JsonSerializer.None.class;
        return _isExplicitClassOrOb(findNullSerializer, cls) ? findNullSerializer : _explicitClassOrOb(this._secondary.findNullSerializer(annotated), cls);
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        ObjectIdInfo findObjectIdInfo = this._primary.findObjectIdInfo(annotated);
        return findObjectIdInfo == null ? this._secondary.findObjectIdInfo(annotated) : findObjectIdInfo;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        return this._primary.findObjectReferenceInfo(annotated, this._secondary.findObjectReferenceInfo(annotated, objectIdInfo));
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        Class<?> findPOJOBuilder = this._primary.findPOJOBuilder(annotatedClass);
        return findPOJOBuilder == null ? this._secondary.findPOJOBuilder(annotatedClass) : findPOJOBuilder;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        JsonPOJOBuilder.Value findPOJOBuilderConfig = this._primary.findPOJOBuilderConfig(annotatedClass);
        return findPOJOBuilderConfig == null ? this._secondary.findPOJOBuilderConfig(annotatedClass) : findPOJOBuilderConfig;
    }

    @Deprecated
    public String[] findPropertiesToIgnore(Annotated annotated, boolean z2) {
        String[] findPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotated, z2);
        return findPropertiesToIgnore == null ? this._secondary.findPropertiesToIgnore(annotated, z2) : findPropertiesToIgnore;
    }

    public JsonProperty.Access findPropertyAccess(Annotated annotated) {
        JsonProperty.Access findPropertyAccess = this._primary.findPropertyAccess(annotated);
        if (findPropertyAccess != null && findPropertyAccess != JsonProperty.Access.AUTO) {
            return findPropertyAccess;
        }
        JsonProperty.Access findPropertyAccess2 = this._secondary.findPropertyAccess(annotated);
        return findPropertyAccess2 != null ? findPropertyAccess2 : JsonProperty.Access.AUTO;
    }

    public List<PropertyName> findPropertyAliases(Annotated annotated) {
        List<PropertyName> findPropertyAliases = this._primary.findPropertyAliases(annotated);
        return findPropertyAliases == null ? this._secondary.findPropertyAliases(annotated) : findPropertyAliases;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyContentTypeResolver = this._primary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
        return findPropertyContentTypeResolver == null ? this._secondary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType) : findPropertyContentTypeResolver;
    }

    public String findPropertyDefaultValue(Annotated annotated) {
        String findPropertyDefaultValue = this._primary.findPropertyDefaultValue(annotated);
        return (findPropertyDefaultValue == null || findPropertyDefaultValue.isEmpty()) ? this._secondary.findPropertyDefaultValue(annotated) : findPropertyDefaultValue;
    }

    public String findPropertyDescription(Annotated annotated) {
        String findPropertyDescription = this._primary.findPropertyDescription(annotated);
        return findPropertyDescription == null ? this._secondary.findPropertyDescription(annotated) : findPropertyDescription;
    }

    public JsonIgnoreProperties.Value findPropertyIgnoralByName(MapperConfig<?> mapperConfig, Annotated annotated) {
        JsonIgnoreProperties.Value findPropertyIgnoralByName = this._secondary.findPropertyIgnoralByName(mapperConfig, annotated);
        JsonIgnoreProperties.Value findPropertyIgnoralByName2 = this._primary.findPropertyIgnoralByName(mapperConfig, annotated);
        return findPropertyIgnoralByName == null ? findPropertyIgnoralByName2 : findPropertyIgnoralByName.withOverrides(findPropertyIgnoralByName2);
    }

    @Deprecated
    public JsonIgnoreProperties.Value findPropertyIgnorals(Annotated annotated) {
        JsonIgnoreProperties.Value findPropertyIgnorals = this._secondary.findPropertyIgnorals(annotated);
        JsonIgnoreProperties.Value findPropertyIgnorals2 = this._primary.findPropertyIgnorals(annotated);
        return findPropertyIgnorals == null ? findPropertyIgnorals2 : findPropertyIgnorals.withOverrides(findPropertyIgnorals2);
    }

    public JsonInclude.Value findPropertyInclusion(Annotated annotated) {
        JsonInclude.Value findPropertyInclusion = this._secondary.findPropertyInclusion(annotated);
        JsonInclude.Value findPropertyInclusion2 = this._primary.findPropertyInclusion(annotated);
        return findPropertyInclusion == null ? findPropertyInclusion2 : findPropertyInclusion.withOverrides(findPropertyInclusion2);
    }

    public JsonIncludeProperties.Value findPropertyInclusionByName(MapperConfig<?> mapperConfig, Annotated annotated) {
        JsonIncludeProperties.Value findPropertyInclusionByName = this._secondary.findPropertyInclusionByName(mapperConfig, annotated);
        JsonIncludeProperties.Value findPropertyInclusionByName2 = this._primary.findPropertyInclusionByName(mapperConfig, annotated);
        return findPropertyInclusionByName == null ? findPropertyInclusionByName2 : findPropertyInclusionByName.withOverrides(findPropertyInclusionByName2);
    }

    public Integer findPropertyIndex(Annotated annotated) {
        Integer findPropertyIndex = this._primary.findPropertyIndex(annotated);
        return findPropertyIndex == null ? this._secondary.findPropertyIndex(annotated) : findPropertyIndex;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyTypeResolver = this._primary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? this._secondary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType) : findPropertyTypeResolver;
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotationIntrospector.ReferenceProperty findReferenceType = this._primary.findReferenceType(annotatedMember);
        return findReferenceType == null ? this._secondary.findReferenceType(annotatedMember) : findReferenceType;
    }

    public PropertyName findRenameByField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, PropertyName propertyName) {
        PropertyName findRenameByField = this._secondary.findRenameByField(mapperConfig, annotatedField, propertyName);
        return findRenameByField == null ? this._primary.findRenameByField(mapperConfig, annotatedField, propertyName) : findRenameByField;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        r2 = r2._secondary.findRootName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findRootName(com.fasterxml.jackson.databind.introspect.AnnotatedClass r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findRootName(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r2 = r2.findRootName(r3)
            return r2
        L_0x000f:
            boolean r1 = r0.hasSimpleName()
            if (r1 == 0) goto L_0x0016
            return r0
        L_0x0016:
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r2 = r2.findRootName(r3)
            if (r2 != 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r0 = r2
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findRootName(com.fasterxml.jackson.databind.introspect.AnnotatedClass):com.fasterxml.jackson.databind.PropertyName");
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        Object findSerializationContentConverter = this._primary.findSerializationContentConverter(annotatedMember);
        return findSerializationContentConverter == null ? this._secondary.findSerializationContentConverter(annotatedMember) : findSerializationContentConverter;
    }

    @Deprecated
    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationContentType = this._primary.findSerializationContentType(annotated, javaType);
        return findSerializationContentType == null ? this._secondary.findSerializationContentType(annotated, javaType) : findSerializationContentType;
    }

    public Object findSerializationConverter(Annotated annotated) {
        Object findSerializationConverter = this._primary.findSerializationConverter(annotated);
        return findSerializationConverter == null ? this._secondary.findSerializationConverter(annotated) : findSerializationConverter;
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        return this._primary.findSerializationInclusion(annotated, this._secondary.findSerializationInclusion(annotated, include));
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusionForContent(Annotated annotated, JsonInclude.Include include) {
        return this._primary.findSerializationInclusionForContent(annotated, this._secondary.findSerializationInclusionForContent(annotated, include));
    }

    @Deprecated
    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationKeyType = this._primary.findSerializationKeyType(annotated, javaType);
        return findSerializationKeyType == null ? this._secondary.findSerializationKeyType(annotated, javaType) : findSerializationKeyType;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        String[] findSerializationPropertyOrder = this._primary.findSerializationPropertyOrder(annotatedClass);
        return findSerializationPropertyOrder == null ? this._secondary.findSerializationPropertyOrder(annotatedClass) : findSerializationPropertyOrder;
    }

    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        Boolean findSerializationSortAlphabetically = this._primary.findSerializationSortAlphabetically(annotated);
        return findSerializationSortAlphabetically == null ? this._secondary.findSerializationSortAlphabetically(annotated) : findSerializationSortAlphabetically;
    }

    @Deprecated
    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> findSerializationType = this._primary.findSerializationType(annotated);
        return findSerializationType == null ? this._secondary.findSerializationType(annotated) : findSerializationType;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize.Typing findSerializationTyping = this._primary.findSerializationTyping(annotated);
        return findSerializationTyping == null ? this._secondary.findSerializationTyping(annotated) : findSerializationTyping;
    }

    public Object findSerializer(Annotated annotated) {
        Object findSerializer = this._primary.findSerializer(annotated);
        Class<JsonSerializer.None> cls = JsonSerializer.None.class;
        return _isExplicitClassOrOb(findSerializer, cls) ? findSerializer : _explicitClassOrOb(this._secondary.findSerializer(annotated), cls);
    }

    public JsonSetter.Value findSetterInfo(Annotated annotated) {
        JsonSetter.Value findSetterInfo = this._secondary.findSetterInfo(annotated);
        JsonSetter.Value findSetterInfo2 = this._primary.findSetterInfo(annotated);
        return findSetterInfo == null ? findSetterInfo2 : findSetterInfo.withOverrides(findSetterInfo2);
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        List<NamedType> findSubtypes = this._primary.findSubtypes(annotated);
        List<NamedType> findSubtypes2 = this._secondary.findSubtypes(annotated);
        if (findSubtypes == null || findSubtypes.isEmpty()) {
            return findSubtypes2;
        }
        if (findSubtypes2 == null || findSubtypes2.isEmpty()) {
            return findSubtypes;
        }
        ArrayList arrayList = new ArrayList(findSubtypes2.size() + findSubtypes.size());
        arrayList.addAll(findSubtypes);
        arrayList.addAll(findSubtypes2);
        return arrayList;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        String findTypeName = this._primary.findTypeName(annotatedClass);
        return (findTypeName == null || findTypeName.isEmpty()) ? this._secondary.findTypeName(annotatedClass) : findTypeName;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        TypeResolverBuilder<?> findTypeResolver = this._primary.findTypeResolver(mapperConfig, annotatedClass, javaType);
        return findTypeResolver == null ? this._secondary.findTypeResolver(mapperConfig, annotatedClass, javaType) : findTypeResolver;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        NameTransformer findUnwrappingNameTransformer = this._primary.findUnwrappingNameTransformer(annotatedMember);
        return findUnwrappingNameTransformer == null ? this._secondary.findUnwrappingNameTransformer(annotatedMember) : findUnwrappingNameTransformer;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        Object findValueInstantiator = this._primary.findValueInstantiator(annotatedClass);
        return findValueInstantiator == null ? this._secondary.findValueInstantiator(annotatedClass) : findValueInstantiator;
    }

    public Class<?>[] findViews(Annotated annotated) {
        Class<?>[] findViews = this._primary.findViews(annotated);
        return findViews == null ? this._secondary.findViews(annotated) : findViews;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r2 = r2._secondary.findWrapperName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findWrapperName(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findWrapperName(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r2.findWrapperName(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r2 = r2.findWrapperName(r3)
            if (r2 == 0) goto L_0x001c
            r0 = r2
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findWrapperName(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    public Boolean hasAnyGetter(Annotated annotated) {
        Boolean hasAnyGetter = this._primary.hasAnyGetter(annotated);
        return hasAnyGetter == null ? this._secondary.hasAnyGetter(annotated) : hasAnyGetter;
    }

    @Deprecated
    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnyGetterAnnotation(annotatedMethod) || this._secondary.hasAnyGetterAnnotation(annotatedMethod);
    }

    public Boolean hasAnySetter(Annotated annotated) {
        Boolean hasAnySetter = this._primary.hasAnySetter(annotated);
        return hasAnySetter == null ? this._secondary.hasAnySetter(annotated) : hasAnySetter;
    }

    @Deprecated
    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnySetterAnnotation(annotatedMethod) || this._secondary.hasAnySetterAnnotation(annotatedMethod);
    }

    public Boolean hasAsKey(MapperConfig<?> mapperConfig, Annotated annotated) {
        Boolean hasAsKey = this._primary.hasAsKey(mapperConfig, annotated);
        return hasAsKey == null ? this._secondary.hasAsKey(mapperConfig, annotated) : hasAsKey;
    }

    public Boolean hasAsValue(Annotated annotated) {
        Boolean hasAsValue = this._primary.hasAsValue(annotated);
        return hasAsValue == null ? this._secondary.hasAsValue(annotated) : hasAsValue;
    }

    @Deprecated
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAsValueAnnotation(annotatedMethod) || this._secondary.hasAsValueAnnotation(annotatedMethod);
    }

    @Deprecated
    public boolean hasCreatorAnnotation(Annotated annotated) {
        return this._primary.hasCreatorAnnotation(annotated) || this._secondary.hasCreatorAnnotation(annotated);
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return this._primary.hasIgnoreMarker(annotatedMember) || this._secondary.hasIgnoreMarker(annotatedMember);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        Boolean hasRequiredMarker = this._primary.hasRequiredMarker(annotatedMember);
        return hasRequiredMarker == null ? this._secondary.hasRequiredMarker(annotatedMember) : hasRequiredMarker;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return this._primary.isAnnotationBundle(annotation) || this._secondary.isAnnotationBundle(annotation);
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        Boolean isIgnorableType = this._primary.isIgnorableType(annotatedClass);
        return isIgnorableType == null ? this._secondary.isIgnorableType(annotatedClass) : isIgnorableType;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        Boolean isTypeId = this._primary.isTypeId(annotatedMember);
        return isTypeId == null ? this._secondary.isTypeId(annotatedMember) : isTypeId;
    }

    public JavaType refineDeserializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        return this._primary.refineDeserializationType(mapperConfig, annotated, this._secondary.refineDeserializationType(mapperConfig, annotated, javaType));
    }

    public JavaType refineSerializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        return this._primary.refineSerializationType(mapperConfig, annotated, this._secondary.refineSerializationType(mapperConfig, annotated, javaType));
    }

    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2) {
        AnnotatedMethod resolveSetterConflict = this._primary.resolveSetterConflict(mapperConfig, annotatedMethod, annotatedMethod2);
        return resolveSetterConflict == null ? this._secondary.resolveSetterConflict(mapperConfig, annotatedMethod, annotatedMethod2) : resolveSetterConflict;
    }

    public Version version() {
        return this._primary.version();
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        this._primary.allIntrospectors(collection);
        this._secondary.allIntrospectors(collection);
        return collection;
    }
}
