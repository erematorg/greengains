package com.fasterxml.jackson.databind.deser;

import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.impl.UnsupportedTypeDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    private boolean _isSetterlessType(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }

    public JsonDeserializer<Object> _findUnsupportedTypeDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        String checkUnsupportedType = BeanUtil.checkUnsupportedType(javaType);
        if (checkUnsupportedType == null || deserializationContext.getConfig().findMixInClassFor(javaType.getRawClass()) != null) {
            return null;
        }
        return new UnsupportedTypeDeserializer(javaType, checkUnsupportedType);
    }

    public void _validateSubType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SubTypeValidator.instance().validateSubType(deserializationContext, javaType, beanDescription);
    }

    public void addBackReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        List<BeanPropertyDefinition> findBackReferences = beanDescription.findBackReferences();
        if (findBackReferences != null) {
            for (BeanPropertyDefinition next : findBackReferences) {
                beanDeserializerBuilder.addBackReferenceProperty(next.findReferenceName(), constructSettableProperty(deserializationContext, beanDescription, next, next.getPrimaryType()));
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v10, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addBeanProps(com.fasterxml.jackson.databind.DeserializationContext r17, com.fasterxml.jackson.databind.BeanDescription r18, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder r19) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r16 = this;
            r7 = r16
            r8 = r17
            r9 = r18
            r10 = r19
            com.fasterxml.jackson.databind.JavaType r0 = r18.getType()
            boolean r0 = r0.isAbstract()
            if (r0 != 0) goto L_0x0020
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r19.getValueInstantiator()
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r17.getConfig()
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r0 = r0.getFromObjectArguments(r1)
            r12 = r0
            goto L_0x0021
        L_0x0020:
            r12 = 0
        L_0x0021:
            r0 = 1
            r13 = 0
            if (r12 == 0) goto L_0x0027
            r14 = r0
            goto L_0x0028
        L_0x0027:
            r14 = r13
        L_0x0028:
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r17.getConfig()
            java.lang.Class r2 = r18.getBeanClass()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r18.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIgnoreProperties$Value r1 = r1.getDefaultPropertyIgnorals(r2, r3)
            if (r1 == 0) goto L_0x005b
            boolean r2 = r1.getIgnoreUnknown()
            r10.setIgnoreUnknownProperties(r2)
            java.util.Set r1 = r1.findIgnoredForDeserialization()
            java.util.Iterator r2 = r1.iterator()
        L_0x0049:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0059
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r10.addIgnorable(r3)
            goto L_0x0049
        L_0x0059:
            r5 = r1
            goto L_0x0060
        L_0x005b:
            java.util.Set r1 = java.util.Collections.emptySet()
            goto L_0x0059
        L_0x0060:
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r17.getConfig()
            java.lang.Class r2 = r18.getBeanClass()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r18.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIncludeProperties$Value r1 = r1.getDefaultPropertyInclusions(r2, r3)
            if (r1 == 0) goto L_0x008e
            java.util.Set r1 = r1.getIncluded()
            if (r1 == 0) goto L_0x008c
            java.util.Iterator r2 = r1.iterator()
        L_0x007c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x008c
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r10.addIncludable(r3)
            goto L_0x007c
        L_0x008c:
            r6 = r1
            goto L_0x008f
        L_0x008e:
            r6 = 0
        L_0x008f:
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r1 = r18.findAnySetterAccessor()
            if (r1 == 0) goto L_0x009d
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r1 = r7.constructAnySetter(r8, r9, r1)
            r10.setAnySetter(r1)
            goto L_0x00b7
        L_0x009d:
            java.util.Set r1 = r18.getIgnoredPropertyNames()
            if (r1 == 0) goto L_0x00b7
            java.util.Iterator r1 = r1.iterator()
        L_0x00a7:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00b7
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            r10.addIgnorable(r2)
            goto L_0x00a7
        L_0x00b7:
            com.fasterxml.jackson.databind.MapperFeature r1 = com.fasterxml.jackson.databind.MapperFeature.USE_GETTERS_AS_SETTERS
            boolean r1 = r8.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r1)
            if (r1 == 0) goto L_0x00c9
            com.fasterxml.jackson.databind.MapperFeature r1 = com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS
            boolean r1 = r8.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r1)
            if (r1 == 0) goto L_0x00c9
            r15 = r0
            goto L_0x00ca
        L_0x00c9:
            r15 = r13
        L_0x00ca:
            java.util.List r4 = r18.findProperties()
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            java.util.List r0 = r0.filterBeanProps(r1, r2, r3, r4, r5, r6)
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r7._factoryConfig
            boolean r1 = r1.hasDeserializerModifiers()
            if (r1 == 0) goto L_0x0101
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r7._factoryConfig
            java.lang.Iterable r1 = r1.deserializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x00ec:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0101
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r2 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r2
            com.fasterxml.jackson.databind.DeserializationConfig r3 = r17.getConfig()
            java.util.List r0 = r2.updateProperties(r3, r9, r0)
            goto L_0x00ec
        L_0x0101:
            java.util.Iterator r0 = r0.iterator()
        L_0x0105:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01e7
            java.lang.Object r1 = r0.next()
            com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r1 = (com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition) r1
            boolean r2 = r1.hasSetter()
            if (r2 == 0) goto L_0x0124
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r2 = r1.getSetter()
            com.fasterxml.jackson.databind.JavaType r2 = r2.getParameterType(r13)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r7.constructSettableProperty(r8, r9, r1, r2)
            goto L_0x016f
        L_0x0124:
            boolean r2 = r1.hasField()
            if (r2 == 0) goto L_0x0137
            com.fasterxml.jackson.databind.introspect.AnnotatedField r2 = r1.getField()
            com.fasterxml.jackson.databind.JavaType r2 = r2.getType()
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r7.constructSettableProperty(r8, r9, r1, r2)
            goto L_0x016f
        L_0x0137:
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r2 = r1.getGetter()
            if (r2 == 0) goto L_0x016e
            if (r15 == 0) goto L_0x0159
            java.lang.Class r2 = r2.getRawType()
            boolean r2 = r7._isSetterlessType(r2)
            if (r2 == 0) goto L_0x0159
            java.lang.String r2 = r1.getName()
            boolean r2 = r10.hasIgnorable(r2)
            if (r2 == 0) goto L_0x0154
            goto L_0x016e
        L_0x0154:
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r7.constructSetterlessProperty(r8, r9, r1)
            goto L_0x016f
        L_0x0159:
            boolean r2 = r1.hasConstructorParameter()
            if (r2 != 0) goto L_0x016e
            com.fasterxml.jackson.databind.PropertyMetadata r2 = r1.getMetadata()
            com.fasterxml.jackson.databind.PropertyMetadata$MergeInfo r2 = r2.getMergeInfo()
            if (r2 == 0) goto L_0x016e
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r7.constructSetterlessProperty(r8, r9, r1)
            goto L_0x016f
        L_0x016e:
            r2 = 0
        L_0x016f:
            if (r14 == 0) goto L_0x01d3
            boolean r3 = r1.hasConstructorParameter()
            if (r3 == 0) goto L_0x01d3
            java.lang.String r3 = r1.getName()
            int r4 = r12.length
            r5 = r13
        L_0x017d:
            if (r5 >= r4) goto L_0x0195
            r6 = r12[r5]
            java.lang.String r11 = r6.getName()
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L_0x0192
            boolean r11 = r6 instanceof com.fasterxml.jackson.databind.deser.CreatorProperty
            if (r11 == 0) goto L_0x0192
            com.fasterxml.jackson.databind.deser.CreatorProperty r6 = (com.fasterxml.jackson.databind.deser.CreatorProperty) r6
            goto L_0x0196
        L_0x0192:
            int r5 = r5 + 1
            goto L_0x017d
        L_0x0195:
            r6 = 0
        L_0x0196:
            if (r6 != 0) goto L_0x01bc
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r4 = r12.length
            r5 = r13
        L_0x019f:
            if (r5 >= r4) goto L_0x01ad
            r6 = r12[r5]
            java.lang.String r6 = r6.getName()
            r2.add(r6)
            int r5 = r5 + 1
            goto L_0x019f
        L_0x01ad:
            java.lang.String r3 = com.fasterxml.jackson.databind.util.ClassUtil.name((java.lang.String) r3)
            java.lang.Object[] r2 = new java.lang.Object[]{r3, r2}
            java.lang.String r3 = "Could not find creator property with name %s (known Creator properties: %s)"
            r8.reportBadPropertyDefinition(r9, r1, r3, r2)
            goto L_0x0105
        L_0x01bc:
            if (r2 == 0) goto L_0x01c1
            r6.setFallbackSetter(r2)
        L_0x01c1:
            java.lang.Class[] r1 = r1.findViews()
            if (r1 != 0) goto L_0x01cb
            java.lang.Class[] r1 = r18.findDefaultViews()
        L_0x01cb:
            r6.setViews(r1)
            r10.addCreatorProperty(r6)
            goto L_0x0105
        L_0x01d3:
            if (r2 == 0) goto L_0x0105
            java.lang.Class[] r1 = r1.findViews()
            if (r1 != 0) goto L_0x01df
            java.lang.Class[] r1 = r18.findDefaultViews()
        L_0x01df:
            r2.setViews(r1)
            r10.addProperty(r2)
            goto L_0x0105
        L_0x01e7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.addBeanProps(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder):void");
    }

    public void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map<Object, AnnotatedMember> findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            for (Map.Entry next : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) next.getValue();
                beanDeserializerBuilder.addInjectable(PropertyName.construct(annotatedMember.getName()), annotatedMember.getType(), beanDescription.getClassAnnotations(), annotatedMember, next.getKey());
            }
        }
    }

    public void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        SettableBeanProperty settableBeanProperty;
        PropertyBasedObjectIdGenerator objectIdGeneratorInstance;
        JavaType javaType;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo != null) {
            Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
            ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
            if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                PropertyName propertyName = objectIdInfo.getPropertyName();
                settableBeanProperty = beanDeserializerBuilder.findProperty(propertyName);
                if (settableBeanProperty != null) {
                    JavaType type = settableBeanProperty.getType();
                    javaType = type;
                    objectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
                } else {
                    throw new IllegalArgumentException(C0118y.f("Invalid Object Id definition for ", ClassUtil.getTypeDescription(beanDescription.getType()), ": cannot find property with name ", ClassUtil.name(propertyName)));
                }
            } else {
                JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                settableBeanProperty = null;
                objectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
                javaType = javaType2;
            }
            SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
            beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty2, objectIdResolverInstance));
        }
    }

    @Deprecated
    public void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilder);
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                    constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> build = (!javaType.isAbstract() || findValueInstantiator.canInstantiate()) ? constructBeanDeserializerBuilder.build() : constructBeanDeserializerBuilder.buildAbstract();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                    build = modifyDeserializer.modifyDeserializer(config, beanDescription, build);
                }
            }
            return build;
        } catch (NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        } catch (IllegalArgumentException e4) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e4), beanDescription, (BeanPropertyDefinition) null).withCause(e4);
        }
    }

    public JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            DeserializationConfig config = deserializationContext.getConfig();
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addBackReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            JsonPOJOBuilder.Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
            String str = findPOJOBuilderConfig == null ? JsonPOJOBuilder.DEFAULT_BUILD_METHOD : findPOJOBuilderConfig.buildMethodName;
            AnnotatedMethod findMethod = beanDescription.findMethod(str, (Class<?>[]) null);
            if (findMethod != null && config.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(findMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                    constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                    buildBuilderBased = modifyDeserializer.modifyDeserializer(config, beanDescription, buildBuilderBased);
                }
            }
            return buildBuilderBased;
        } catch (NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        } catch (IllegalArgumentException e4) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e4), beanDescription, (BeanPropertyDefinition) null);
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        Iterator<SettableBeanProperty> properties = constructBeanDeserializerBuilder.getProperties();
        while (true) {
            if (properties.hasNext()) {
                if ("setCause".equals(properties.next().getMember().getName())) {
                    properties.remove();
                    break;
                }
            } else {
                break;
            }
        }
        AnnotatedMethod findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null) {
            PropertyNamingStrategy propertyNamingStrategy = config.getPropertyNamingStrategy();
            String str = "cause";
            if (propertyNamingStrategy != null) {
                str = propertyNamingStrategy.nameForSetterMethod(config, findMethod, str);
            }
            SettableBeanProperty constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), findMethod, new PropertyName(str)), findMethod.getParameterType(0));
            if (constructSettableProperty != null) {
                constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = ThrowableDeserializer.construct(deserializationContext, (BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                build = modifyDeserializer.modifyDeserializer(config, beanDescription, build);
            }
        }
        return build;
    }

    public SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMember annotatedMember) throws JsonMappingException {
        JavaType keyType;
        JavaType contentType;
        BeanProperty.Std std;
        DeserializationContext deserializationContext2 = deserializationContext;
        AnnotatedMember annotatedMember2 = annotatedMember;
        boolean z2 = annotatedMember2 instanceof AnnotatedField;
        if (annotatedMember2 instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember2;
            keyType = annotatedMethod.getParameterType(0);
            contentType = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember2, annotatedMethod.getParameterType(1));
            std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), contentType, (PropertyName) null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
        } else if (z2) {
            JavaType type = ((AnnotatedField) annotatedMember2).getType();
            if (type.isMapLikeType()) {
                JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember2, type);
                keyType = resolveMemberAndTypeAnnotations.getKeyType();
                contentType = resolveMemberAndTypeAnnotations.getContentType();
                std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), resolveMemberAndTypeAnnotations, (PropertyName) null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
            } else {
                Class<JsonNode> cls = JsonNode.class;
                if (type.hasRawClass(cls) || type.hasRawClass(ObjectNode.class)) {
                    JavaType resolveMemberAndTypeAnnotations2 = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember2, type);
                    JavaType constructType = deserializationContext.constructType(cls);
                    return SettableAnyProperty.constructForJsonNodeField(deserializationContext, new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), resolveMemberAndTypeAnnotations2, (PropertyName) null, annotatedMember, PropertyMetadata.STD_OPTIONAL), annotatedMember2, constructType, deserializationContext.findRootValueDeserializer(constructType));
                }
                JavaType type2 = beanDescription.getType();
                String typeDescription = ClassUtil.getTypeDescription(type);
                return (SettableAnyProperty) deserializationContext.reportBadDefinition(type2, "Unsupported type for any-setter: " + typeDescription + " -- only support `Map`s, `JsonNode` and `ObjectNode` ");
            }
        } else {
            JavaType type3 = beanDescription.getType();
            String nameOf = ClassUtil.nameOf(annotatedMember.getClass());
            return (SettableAnyProperty) deserializationContext.reportBadDefinition(type3, "Unrecognized mutator type for any-setter: " + nameOf);
        }
        JavaType javaType = contentType;
        KeyDeserializer findKeyDeserializerFromAnnotation = findKeyDeserializerFromAnnotation(deserializationContext, annotatedMember2);
        if (findKeyDeserializerFromAnnotation == null) {
            findKeyDeserializerFromAnnotation = (KeyDeserializer) keyType.getValueHandler();
        }
        if (findKeyDeserializerFromAnnotation == null) {
            findKeyDeserializerFromAnnotation = deserializationContext.findKeyDeserializer(keyType, std);
        } else if (findKeyDeserializerFromAnnotation instanceof ContextualKeyDeserializer) {
            findKeyDeserializerFromAnnotation = ((ContextualKeyDeserializer) findKeyDeserializerFromAnnotation).createContextual(deserializationContext, std);
        }
        KeyDeserializer keyDeserializer = findKeyDeserializerFromAnnotation;
        JsonDeserializer findContentDeserializerFromAnnotation = findContentDeserializerFromAnnotation(deserializationContext, annotatedMember2);
        if (findContentDeserializerFromAnnotation == null) {
            findContentDeserializerFromAnnotation = (JsonDeserializer) javaType.getValueHandler();
        }
        if (findContentDeserializerFromAnnotation != null) {
            findContentDeserializerFromAnnotation = deserializationContext.handlePrimaryContextualization(findContentDeserializerFromAnnotation, std, javaType);
        }
        JsonDeserializer jsonDeserializer = findContentDeserializerFromAnnotation;
        TypeDeserializer typeDeserializer = (TypeDeserializer) javaType.getTypeHandler();
        return z2 ? SettableAnyProperty.constructForMapField(deserializationContext, std, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer) : SettableAnyProperty.constructForMethod(deserializationContext, std, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    public BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.fasterxml.jackson.databind.deser.SettableBeanProperty} */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.fasterxml.jackson.databind.BeanProperty, com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r3v2, types: [com.fasterxml.jackson.databind.deser.impl.FieldProperty] */
    /* JADX WARNING: type inference failed for: r3v3, types: [com.fasterxml.jackson.databind.deser.impl.MethodProperty] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext r10, com.fasterxml.jackson.databind.BeanDescription r11, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r12, com.fasterxml.jackson.databind.JavaType r13) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r12.getNonConstructorMutator()
            if (r0 != 0) goto L_0x000e
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "No non-constructor mutator available"
            r10.reportBadPropertyDefinition(r11, r12, r2, r1)
        L_0x000e:
            com.fasterxml.jackson.databind.JavaType r13 = r9.resolveMemberAndTypeAnnotations(r10, r0, r13)
            java.lang.Object r1 = r13.getTypeHandler()
            r6 = r1
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r6 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r6
            boolean r1 = r0 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            if (r1 == 0) goto L_0x002d
            com.fasterxml.jackson.databind.deser.impl.MethodProperty r1 = new com.fasterxml.jackson.databind.deser.impl.MethodProperty
            com.fasterxml.jackson.databind.util.Annotations r7 = r11.getClassAnnotations()
            r8 = r0
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r8 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r8
            r3 = r1
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x003c
        L_0x002d:
            com.fasterxml.jackson.databind.deser.impl.FieldProperty r1 = new com.fasterxml.jackson.databind.deser.impl.FieldProperty
            com.fasterxml.jackson.databind.util.Annotations r7 = r11.getClassAnnotations()
            r8 = r0
            com.fasterxml.jackson.databind.introspect.AnnotatedField r8 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r8
            r3 = r1
            r4 = r12
            r5 = r13
            r3.<init>(r4, r5, r6, r7, r8)
        L_0x003c:
            com.fasterxml.jackson.databind.JsonDeserializer r9 = r9.findDeserializerFromAnnotation(r10, r0)
            if (r9 != 0) goto L_0x0048
            java.lang.Object r9 = r13.getValueHandler()
            com.fasterxml.jackson.databind.JsonDeserializer r9 = (com.fasterxml.jackson.databind.JsonDeserializer) r9
        L_0x0048:
            if (r9 == 0) goto L_0x0052
            com.fasterxml.jackson.databind.JsonDeserializer r9 = r10.handlePrimaryContextualization(r9, r1, r13)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r1 = r1.withValueDeserializer(r9)
        L_0x0052:
            com.fasterxml.jackson.databind.AnnotationIntrospector$ReferenceProperty r9 = r12.findReferenceType()
            if (r9 == 0) goto L_0x0065
            boolean r10 = r9.isManagedReference()
            if (r10 == 0) goto L_0x0065
            java.lang.String r9 = r9.getName()
            r1.setManagedReferenceName(r9)
        L_0x0065:
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r9 = r12.findObjectIdInfo()
            if (r9 == 0) goto L_0x006e
            r1.setObjectIdInfo(r9)
        L_0x006e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    public SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, getter, getter.getType());
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, resolveMemberAndTypeAnnotations, (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
        }
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, setterlessProperty, resolveMemberAndTypeAnnotations)) : setterlessProperty;
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType materializeAbstractType;
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                    _findCustomBeanDeserializer = modifyDeserializer.modifyDeserializer(deserializationContext.getConfig(), beanDescription, _findCustomBeanDeserializer);
                }
            }
            return _findCustomBeanDeserializer;
        } else if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        } else {
            if (javaType.isAbstract() && !javaType.isPrimitive() && !javaType.isEnumType() && (materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription)) != null) {
                return buildBeanDeserializer(deserializationContext, materializeAbstractType, config.introspect(materializeAbstractType));
            }
            JsonDeserializer<?> findStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
            if (findStdDeserializer != null) {
                return findStdDeserializer;
            }
            if (!isPotentialBeanType(javaType.getRawClass())) {
                return null;
            }
            _validateSubType(deserializationContext, javaType, beanDescription);
            JsonDeserializer<Object> _findUnsupportedTypeDeserializer = _findUnsupportedTypeDeserializer(deserializationContext, javaType, beanDescription);
            return _findUnsupportedTypeDeserializer != null ? _findUnsupportedTypeDeserializer : buildBeanDeserializer(deserializationContext, javaType, beanDescription);
        }
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.isEnabled(MapperFeature.INFER_BUILDER_TYPE_BINDINGS) ? deserializationContext.getTypeFactory().constructParametricType(cls, javaType.getBindings()) : deserializationContext.constructType(cls), beanDescription));
    }

    @Deprecated
    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        return filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, list, set, (Set<String>) null);
    }

    public JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                findDefaultDeserializer = modifyDeserializer.modifyDeserializer(deserializationContext.getConfig(), beanDescription, findDefaultDeserializer);
            }
        }
        return findDefaultDeserializer;
    }

    public boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanPropertyDefinition beanPropertyDefinition, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool;
        Boolean bool2 = map.get(cls);
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (cls == String.class || cls.isPrimitive()) {
            bool = Boolean.FALSE;
        } else {
            bool = deserializationConfig.getConfigOverride(cls).getIsIgnoredType();
            if (bool == null) {
                bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
                if (bool == null) {
                    bool = Boolean.FALSE;
                }
            }
        }
        map.put(cls, bool);
        return bool.booleanValue();
    }

    public boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType != null) {
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
        } else if (!ClassUtil.isProxyType(cls)) {
            String isLocalType = ClassUtil.isLocalType(cls, true);
            if (isLocalType == null) {
                return true;
            }
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + isLocalType + ") as a Bean");
        } else {
            throw new IllegalArgumentException(b.g(cls, new StringBuilder("Cannot deserialize Proxy class "), " as a Bean"));
        }
    }

    public JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        for (AbstractTypeResolver resolveAbstractType : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType2 = resolveAbstractType.resolveAbstractType(deserializationContext.getConfig(), beanDescription);
            if (resolveAbstractType2 != null) {
                return resolveAbstractType2;
            }
        }
        return null;
    }

    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        if (this._factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
        return new BeanDeserializerFactory(deserializerFactoryConfig);
    }

    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set, Set<String> set2) {
        Class<?> rawPrimaryType;
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition next : list) {
            String name = next.getName();
            if (!IgnorePropertiesUtil.shouldIgnore(name, set, set2)) {
                if (next.hasConstructorParameter() || (rawPrimaryType = next.getRawPrimaryType()) == null || !isIgnorableType(deserializationContext.getConfig(), next, rawPrimaryType, hashMap)) {
                    arrayList.add(next);
                } else {
                    beanDeserializerBuilder.addIgnorable(name);
                }
            }
        }
        return arrayList;
    }
}
