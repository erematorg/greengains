package com.fasterxml.jackson.databind.deser;

import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
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
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;
import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_SEQUENCE = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_MAP_ENTRY = Map.Entry.class;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_SERIALIZABLE = Serializable.class;
    private static final Class<?> CLASS_STRING = String.class;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    protected final DeserializerFactoryConfig _factoryConfig;

    /* renamed from: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004e */
        static {
            /*
                com.fasterxml.jackson.databind.cfg.ConstructorDetector$SingleArgConstructor[] r0 = com.fasterxml.jackson.databind.cfg.ConstructorDetector.SingleArgConstructor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor = r0
                r1 = 1
                com.fasterxml.jackson.databind.cfg.ConstructorDetector$SingleArgConstructor r2 = com.fasterxml.jackson.databind.cfg.ConstructorDetector.SingleArgConstructor.DELEGATING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.databind.cfg.ConstructorDetector$SingleArgConstructor r3 = com.fasterxml.jackson.databind.cfg.ConstructorDetector.SingleArgConstructor.PROPERTIES     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.databind.cfg.ConstructorDetector$SingleArgConstructor r4 = com.fasterxml.jackson.databind.cfg.ConstructorDetector.SingleArgConstructor.REQUIRE_MODE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.databind.cfg.ConstructorDetector$SingleArgConstructor r4 = com.fasterxml.jackson.databind.cfg.ConstructorDetector.SingleArgConstructor.HEURISTIC     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.fasterxml.jackson.annotation.JsonCreator$Mode[] r3 = com.fasterxml.jackson.annotation.JsonCreator.Mode.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode = r3
                com.fasterxml.jackson.annotation.JsonCreator$Mode r4 = com.fasterxml.jackson.annotation.JsonCreator.Mode.DELEGATING     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode     // Catch:{ NoSuchFieldError -> 0x004e }
                com.fasterxml.jackson.annotation.JsonCreator$Mode r3 = com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES     // Catch:{ NoSuchFieldError -> 0x004e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.fasterxml.jackson.annotation.JsonCreator$Mode r1 = com.fasterxml.jackson.annotation.JsonCreator.Mode.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.AnonymousClass1.<clinit>():void");
        }
    }

    public static class ContainerDefaultMappings {
        static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
        static final HashMap<String, Class<? extends Map>> _mapFallbacks;

        static {
            HashMap<String, Class<? extends Collection>> hashMap = new HashMap<>();
            Class<ArrayList> cls = ArrayList.class;
            hashMap.put(Collection.class.getName(), cls);
            hashMap.put(List.class.getName(), cls);
            Class<HashSet> cls2 = HashSet.class;
            hashMap.put(Set.class.getName(), cls2);
            Class<TreeSet> cls3 = TreeSet.class;
            hashMap.put(SortedSet.class.getName(), cls3);
            Class<LinkedList> cls4 = LinkedList.class;
            hashMap.put(Queue.class.getName(), cls4);
            hashMap.put(AbstractList.class.getName(), cls);
            hashMap.put(AbstractSet.class.getName(), cls2);
            hashMap.put(Deque.class.getName(), cls4);
            hashMap.put(NavigableSet.class.getName(), cls3);
            _collectionFallbacks = hashMap;
            HashMap<String, Class<? extends Map>> hashMap2 = new HashMap<>();
            Class<LinkedHashMap> cls5 = LinkedHashMap.class;
            hashMap2.put(Map.class.getName(), cls5);
            hashMap2.put(AbstractMap.class.getName(), cls5);
            hashMap2.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
            Class<TreeMap> cls6 = TreeMap.class;
            hashMap2.put(SortedMap.class.getName(), cls6);
            hashMap2.put(NavigableMap.class.getName(), cls6);
            hashMap2.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
            _mapFallbacks = hashMap2;
        }

        public static Class<?> findCollectionFallback(JavaType javaType) {
            return _collectionFallbacks.get(javaType.getRawClass().getName());
        }

        public static Class<?> findMapFallback(JavaType javaType) {
            return _mapFallbacks.get(javaType.getRawClass().getName());
        }
    }

    public static class CreatorCollectionState {
        private int _explicitConstructorCount;
        private int _explicitFactoryCount;
        private List<CreatorCandidate> _implicitConstructorCandidates;
        private List<CreatorCandidate> _implicitFactoryCandidates;
        public final BeanDescription beanDesc;
        public final DeserializationContext context;
        public final Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams;
        public final CreatorCollector creators;
        public final VisibilityChecker<?> vchecker;

        public CreatorCollectionState(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, CreatorCollector creatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> map) {
            this.context = deserializationContext;
            this.beanDesc = beanDescription;
            this.vchecker = visibilityChecker;
            this.creators = creatorCollector;
            this.creatorParams = map;
        }

        public void addImplicitConstructorCandidate(CreatorCandidate creatorCandidate) {
            if (this._implicitConstructorCandidates == null) {
                this._implicitConstructorCandidates = new LinkedList();
            }
            this._implicitConstructorCandidates.add(creatorCandidate);
        }

        public void addImplicitFactoryCandidate(CreatorCandidate creatorCandidate) {
            if (this._implicitFactoryCandidates == null) {
                this._implicitFactoryCandidates = new LinkedList();
            }
            this._implicitFactoryCandidates.add(creatorCandidate);
        }

        public AnnotationIntrospector annotationIntrospector() {
            return this.context.getAnnotationIntrospector();
        }

        public boolean hasExplicitConstructors() {
            return this._explicitConstructorCount > 0;
        }

        public boolean hasExplicitFactories() {
            return this._explicitFactoryCount > 0;
        }

        public boolean hasImplicitConstructorCandidates() {
            return this._implicitConstructorCandidates != null;
        }

        public boolean hasImplicitFactoryCandidates() {
            return this._implicitFactoryCandidates != null;
        }

        public List<CreatorCandidate> implicitConstructorCandidates() {
            return this._implicitConstructorCandidates;
        }

        public List<CreatorCandidate> implicitFactoryCandidates() {
            return this._implicitFactoryCandidates;
        }

        public void increaseExplicitConstructorCount() {
            this._explicitConstructorCount++;
        }

        public void increaseExplicitFactoryCount() {
            this._explicitFactoryCount++;
        }
    }

    public BasicDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        this._factoryConfig = deserializerFactoryConfig;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = r4.getName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean _checkIfCreatorPropertyBased(com.fasterxml.jackson.databind.AnnotationIntrospector r2, com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r3, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r4) {
        /*
            r1 = this;
            r1 = 1
            if (r4 == 0) goto L_0x0009
            boolean r0 = r4.isExplicitlyNamed()
            if (r0 != 0) goto L_0x0014
        L_0x0009:
            r0 = 0
            com.fasterxml.jackson.databind.introspect.AnnotatedParameter r3 = r3.getParameter(r0)
            com.fasterxml.jackson.annotation.JacksonInject$Value r2 = r2.findInjectableValue(r3)
            if (r2 == 0) goto L_0x0015
        L_0x0014:
            return r1
        L_0x0015:
            if (r4 == 0) goto L_0x002a
            java.lang.String r2 = r4.getName()
            if (r2 == 0) goto L_0x002a
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x002a
            boolean r2 = r4.couldSerialize()
            if (r2 == 0) goto L_0x002a
            return r1
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory._checkIfCreatorPropertyBased(com.fasterxml.jackson.databind.AnnotationIntrospector, com.fasterxml.jackson.databind.introspect.AnnotatedWithParams, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition):boolean");
    }

    private void _checkImplicitlyNamedConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, List<AnnotatedWithParams> list) throws JsonMappingException {
        int i3;
        Iterator<AnnotatedWithParams> it = list.iterator();
        AnnotatedWithParams annotatedWithParams = null;
        AnnotatedWithParams annotatedWithParams2 = null;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        while (true) {
            if (!it.hasNext()) {
                annotatedWithParams = annotatedWithParams2;
                break;
            }
            AnnotatedWithParams next = it.next();
            if (visibilityChecker.isCreatorVisible((AnnotatedMember) next)) {
                int parameterCount = next.getParameterCount();
                SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[parameterCount];
                int i4 = 0;
                while (true) {
                    if (i4 < parameterCount) {
                        AnnotatedParameter parameter = next.getParameter(i4);
                        PropertyName _findParamName = _findParamName(parameter, annotationIntrospector);
                        if (_findParamName == null || _findParamName.isEmpty()) {
                            break;
                        }
                        settableBeanPropertyArr2[i4] = constructCreatorProperty(deserializationContext, beanDescription, _findParamName, parameter.getIndex(), parameter, (JacksonInject.Value) null);
                        i4++;
                    } else if (annotatedWithParams2 != null) {
                        break;
                    } else {
                        annotatedWithParams2 = next;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                    }
                }
            }
        }
        if (annotatedWithParams != null) {
            creatorCollector.addPropertyCreator(annotatedWithParams, false, settableBeanPropertyArr);
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) beanDescription;
            for (SettableBeanProperty settableBeanProperty : settableBeanPropertyArr) {
                PropertyName fullName = settableBeanProperty.getFullName();
                if (!basicBeanDescription.hasProperty(fullName)) {
                    basicBeanDescription.addProperty(SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), settableBeanProperty.getMember(), fullName));
                }
            }
        }
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Class<?> rawClass = javaType.getRawClass();
        BeanDescription introspect = config.introspect(javaType);
        KeyDeserializer findKeyDeserializerFromAnnotation = findKeyDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findKeyDeserializerFromAnnotation != null) {
            return findKeyDeserializerFromAnnotation;
        }
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, introspect);
        if (_findCustomEnumDeserializer != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, _findCustomEnumDeserializer);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, findDeserializerFromAnnotation);
        }
        EnumResolver constructEnumResolver = constructEnumResolver(rawClass, config, introspect.findJsonValueAccessor());
        for (AnnotatedMethod next : introspect.getFactoryMethods()) {
            if (_hasCreatorAnnotation(deserializationContext, next)) {
                if (next.getParameterCount() != 1 || !next.getRawReturnType().isAssignableFrom(rawClass)) {
                    StringBuilder sb = new StringBuilder("Unsuitable method (");
                    sb.append(next);
                    sb.append(") decorated with @JsonCreator (for Enum type ");
                    throw new IllegalArgumentException(b.g(rawClass, sb, ")"));
                } else if (next.getRawParameterType(0) == String.class) {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(next.getMember(), deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, next);
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    private PropertyName _findParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        if (annotationIntrospector == null) {
            return null;
        }
        PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedParameter);
        if (findNameForDeserialization != null && !findNameForDeserialization.isEmpty()) {
            return findNameForDeserialization;
        }
        String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null || findImplicitPropertyName.isEmpty()) {
            return null;
        }
        return PropertyName.construct(findImplicitPropertyName);
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (!this._factoryConfig.hasAbstractTypeResolvers()) {
            return null;
        }
        for (AbstractTypeResolver findTypeMapping : this._factoryConfig.abstractTypeResolvers()) {
            JavaType findTypeMapping2 = findTypeMapping.findTypeMapping(deserializationConfig, javaType);
            if (findTypeMapping2 != null && !findTypeMapping2.hasRawClass(rawClass)) {
                return findTypeMapping2;
            }
        }
        return null;
    }

    @Deprecated
    public void _addExplicitAnyCreator(DeserializationContext deserializationContext, BeanDescription beanDescription, CreatorCollector creatorCollector, CreatorCandidate creatorCandidate) throws JsonMappingException {
        _addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate, deserializationContext.getConfig().getConstructorDetector());
    }

    public void _addExplicitConstructorCreators(DeserializationContext deserializationContext, CreatorCollectionState creatorCollectionState, boolean z2) throws JsonMappingException {
        BeanDescription beanDescription = creatorCollectionState.beanDesc;
        CreatorCollector creatorCollector = creatorCollectionState.creators;
        AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker = creatorCollectionState.vchecker;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map = creatorCollectionState.creatorParams;
        AnnotatedConstructor findDefaultConstructor = beanDescription.findDefaultConstructor();
        if (findDefaultConstructor != null && (!creatorCollector.hasDefaultCreator() || _hasCreatorAnnotation(deserializationContext, findDefaultConstructor))) {
            creatorCollector.setDefaultCreator(findDefaultConstructor);
        }
        for (AnnotatedConstructor next : beanDescription.getConstructors()) {
            JsonCreator.Mode findCreatorAnnotation = annotationIntrospector.findCreatorAnnotation(deserializationContext.getConfig(), next);
            if (JsonCreator.Mode.DISABLED != findCreatorAnnotation) {
                if (findCreatorAnnotation != null) {
                    int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode[findCreatorAnnotation.ordinal()];
                    if (i3 == 1) {
                        _addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, (BeanPropertyDefinition[]) null));
                    } else if (i3 != 2) {
                        _addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, map.get(next)), deserializationContext.getConfig().getConstructorDetector());
                    } else {
                        _addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, map.get(next)));
                    }
                    creatorCollectionState.increaseExplicitConstructorCount();
                } else if (z2 && visibilityChecker.isCreatorVisible((AnnotatedMember) next)) {
                    creatorCollectionState.addImplicitConstructorCandidate(CreatorCandidate.construct(annotationIntrospector, next, map.get(next)));
                }
            }
        }
    }

    public void _addExplicitDelegatingCreator(DeserializationContext deserializationContext, BeanDescription beanDescription, CreatorCollector creatorCollector, CreatorCandidate creatorCandidate) throws JsonMappingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        CreatorCollector creatorCollector2 = creatorCollector;
        CreatorCandidate creatorCandidate2 = creatorCandidate;
        int paramCount = creatorCandidate.paramCount();
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[paramCount];
        int i3 = -1;
        for (int i4 = 0; i4 < paramCount; i4++) {
            AnnotatedParameter parameter = creatorCandidate2.parameter(i4);
            JacksonInject.Value injection = creatorCandidate2.injection(i4);
            if (injection != null) {
                settableBeanPropertyArr[i4] = constructCreatorProperty(deserializationContext, beanDescription, (PropertyName) null, i4, parameter, injection);
            } else if (i3 < 0) {
                i3 = i4;
            } else {
                deserializationContext2.reportBadTypeDefinition(beanDescription2, "More than one argument (#%d and #%d) left as delegating for Creator %s: only one allowed", Integer.valueOf(i3), Integer.valueOf(i4), creatorCandidate2);
            }
        }
        if (i3 < 0) {
            deserializationContext2.reportBadTypeDefinition(beanDescription2, "No argument left as delegating for Creator %s: exactly one required", creatorCandidate);
        }
        if (paramCount == 1) {
            _handleSingleArgumentCreator(creatorCollector2, creatorCandidate.creator(), true, true);
            BeanPropertyDefinition propertyDef = creatorCandidate2.propertyDef(0);
            if (propertyDef != null) {
                ((POJOPropertyBuilder) propertyDef).removeConstructors();
                return;
            }
            return;
        }
        creatorCollector2.addDelegatingCreator(creatorCandidate.creator(), true, settableBeanPropertyArr, i3);
    }

    public void _addExplicitFactoryCreators(DeserializationContext deserializationContext, CreatorCollectionState creatorCollectionState, boolean z2) throws JsonMappingException {
        BeanDescription beanDescription = creatorCollectionState.beanDesc;
        CreatorCollector creatorCollector = creatorCollectionState.creators;
        AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker = creatorCollectionState.vchecker;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map = creatorCollectionState.creatorParams;
        for (AnnotatedMethod next : beanDescription.getFactoryMethods()) {
            JsonCreator.Mode findCreatorAnnotation = annotationIntrospector.findCreatorAnnotation(deserializationContext.getConfig(), next);
            int parameterCount = next.getParameterCount();
            if (findCreatorAnnotation == null) {
                if (z2 && parameterCount == 1 && visibilityChecker.isCreatorVisible((AnnotatedMember) next)) {
                    creatorCollectionState.addImplicitFactoryCandidate(CreatorCandidate.construct(annotationIntrospector, next, (BeanPropertyDefinition[]) null));
                }
            } else if (findCreatorAnnotation != JsonCreator.Mode.DISABLED) {
                if (parameterCount == 0) {
                    creatorCollector.setDefaultCreator(next);
                } else {
                    int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonCreator$Mode[findCreatorAnnotation.ordinal()];
                    if (i3 == 1) {
                        _addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, (BeanPropertyDefinition[]) null));
                    } else if (i3 != 2) {
                        _addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, map.get(next)), ConstructorDetector.DEFAULT);
                    } else {
                        _addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, next, map.get(next)));
                    }
                    creatorCollectionState.increaseExplicitFactoryCount();
                }
            }
        }
    }

    public void _addExplicitPropertyCreator(DeserializationContext deserializationContext, BeanDescription beanDescription, CreatorCollector creatorCollector, CreatorCandidate creatorCandidate) throws JsonMappingException {
        CreatorCandidate creatorCandidate2 = creatorCandidate;
        int paramCount = creatorCandidate.paramCount();
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[paramCount];
        int i3 = 0;
        while (i3 < paramCount) {
            JacksonInject.Value injection = creatorCandidate2.injection(i3);
            AnnotatedParameter parameter = creatorCandidate2.parameter(i3);
            PropertyName paramName = creatorCandidate2.paramName(i3);
            if (paramName == null) {
                DeserializationContext deserializationContext2 = deserializationContext;
                BeanDescription beanDescription2 = beanDescription;
                if (deserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer(parameter) != null) {
                    _reportUnwrappedCreatorProperty(deserializationContext2, beanDescription2, parameter);
                }
                PropertyName findImplicitParamName = creatorCandidate2.findImplicitParamName(i3);
                _validateNamedPropertyParameter(deserializationContext, beanDescription, creatorCandidate, i3, findImplicitParamName, injection);
                paramName = findImplicitParamName;
            } else {
                DeserializationContext deserializationContext3 = deserializationContext;
                BeanDescription beanDescription3 = beanDescription;
            }
            int i4 = i3;
            settableBeanPropertyArr[i4] = constructCreatorProperty(deserializationContext, beanDescription, paramName, i3, parameter, injection);
            i3 = i4 + 1;
        }
        creatorCollector.addPropertyCreator(creatorCandidate.creator(), true, settableBeanPropertyArr);
    }

    public void _addImplicitConstructorCreators(DeserializationContext deserializationContext, CreatorCollectionState creatorCollectionState, List<CreatorCandidate> list) throws JsonMappingException {
        VisibilityChecker<?> visibilityChecker;
        Iterator<CreatorCandidate> it;
        boolean z2;
        CreatorCandidate creatorCandidate;
        VisibilityChecker<?> visibilityChecker2;
        Iterator<CreatorCandidate> it2;
        boolean z3;
        int i3;
        AnnotatedWithParams annotatedWithParams;
        SettableBeanProperty[] settableBeanPropertyArr;
        int i4;
        int i5;
        DeserializationContext deserializationContext2 = deserializationContext;
        CreatorCollectionState creatorCollectionState2 = creatorCollectionState;
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDescription beanDescription = creatorCollectionState2.beanDesc;
        CreatorCollector creatorCollector = creatorCollectionState2.creators;
        AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker3 = creatorCollectionState2.vchecker;
        boolean singleArgCreatorDefaultsToProperties = config.getConstructorDetector().singleArgCreatorDefaultsToProperties();
        Iterator<CreatorCandidate> it3 = list.iterator();
        LinkedList linkedList = null;
        while (it3.hasNext()) {
            CreatorCandidate next = it3.next();
            int paramCount = next.paramCount();
            AnnotatedWithParams creator = next.creator();
            if (paramCount == 1) {
                BeanPropertyDefinition propertyDef = next.propertyDef(0);
                if (singleArgCreatorDefaultsToProperties || _checkIfCreatorPropertyBased(annotationIntrospector, creator, propertyDef)) {
                    JacksonInject.Value injection = next.injection(0);
                    PropertyName paramName = next.paramName(0);
                    if (!(paramName == null && (paramName = next.findImplicitParamName(0)) == null && injection == null)) {
                        creatorCollector.addPropertyCreator(creator, false, new SettableBeanProperty[]{constructCreatorProperty(deserializationContext, beanDescription, paramName, 0, next.parameter(0), injection)});
                    }
                } else {
                    _handleSingleArgumentCreator(creatorCollector, creator, false, visibilityChecker3.isCreatorVisible((AnnotatedMember) creator));
                    if (propertyDef != null) {
                        ((POJOPropertyBuilder) propertyDef).removeConstructors();
                    }
                }
                visibilityChecker = visibilityChecker3;
                z2 = singleArgCreatorDefaultsToProperties;
                it = it3;
            } else {
                SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[paramCount];
                int i6 = -1;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                while (i7 < paramCount) {
                    AnnotatedParameter parameter = creator.getParameter(i7);
                    BeanPropertyDefinition propertyDef2 = next.propertyDef(i7);
                    JacksonInject.Value findInjectableValue = annotationIntrospector.findInjectableValue(parameter);
                    PropertyName fullName = propertyDef2 == null ? null : propertyDef2.getFullName();
                    if (propertyDef2 == null || !propertyDef2.isExplicitlyNamed()) {
                        AnnotatedParameter annotatedParameter = parameter;
                        i3 = i7;
                        i5 = i6;
                        creatorCandidate = next;
                        visibilityChecker2 = visibilityChecker3;
                        z3 = singleArgCreatorDefaultsToProperties;
                        it2 = it3;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                        annotatedWithParams = creator;
                        i4 = paramCount;
                        if (findInjectableValue != null) {
                            i9++;
                            settableBeanPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, fullName, i3, annotatedParameter, findInjectableValue);
                        } else {
                            AnnotatedParameter annotatedParameter2 = annotatedParameter;
                            if (annotationIntrospector.findUnwrappingNameTransformer(annotatedParameter2) != null) {
                                _reportUnwrappedCreatorProperty(deserializationContext2, beanDescription, annotatedParameter2);
                            } else if (i5 < 0) {
                                i6 = i3;
                                i7 = i3 + 1;
                                paramCount = i4;
                                settableBeanPropertyArr2 = settableBeanPropertyArr;
                                creator = annotatedWithParams;
                                singleArgCreatorDefaultsToProperties = z3;
                                it3 = it2;
                                visibilityChecker3 = visibilityChecker2;
                                next = creatorCandidate;
                            }
                        }
                    } else {
                        i8++;
                        i3 = i7;
                        i5 = i6;
                        z3 = singleArgCreatorDefaultsToProperties;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                        it2 = it3;
                        annotatedWithParams = creator;
                        visibilityChecker2 = visibilityChecker3;
                        i4 = paramCount;
                        creatorCandidate = next;
                        settableBeanPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, fullName, i3, parameter, findInjectableValue);
                    }
                    i6 = i5;
                    i7 = i3 + 1;
                    paramCount = i4;
                    settableBeanPropertyArr2 = settableBeanPropertyArr;
                    creator = annotatedWithParams;
                    singleArgCreatorDefaultsToProperties = z3;
                    it3 = it2;
                    visibilityChecker3 = visibilityChecker2;
                    next = creatorCandidate;
                }
                int i10 = i6;
                CreatorCandidate creatorCandidate2 = next;
                visibilityChecker = visibilityChecker3;
                z2 = singleArgCreatorDefaultsToProperties;
                it = it3;
                SettableBeanProperty[] settableBeanPropertyArr3 = settableBeanPropertyArr2;
                AnnotatedWithParams annotatedWithParams2 = creator;
                int i11 = paramCount;
                if (i8 > 0 || i9 > 0) {
                    if (i8 + i9 == i11) {
                        creatorCollector.addPropertyCreator(annotatedWithParams2, false, settableBeanPropertyArr3);
                    } else if (i8 == 0 && i9 + 1 == i11) {
                        creatorCollector.addDelegatingCreator(annotatedWithParams2, false, settableBeanPropertyArr3, 0);
                    } else {
                        PropertyName findImplicitParamName = creatorCandidate2.findImplicitParamName(i10);
                        if (findImplicitParamName == null || findImplicitParamName.isEmpty()) {
                            deserializationContext2.reportBadTypeDefinition(beanDescription, "Argument #%d of constructor %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", Integer.valueOf(i10), annotatedWithParams2);
                        }
                    }
                }
                if (!creatorCollector.hasDefaultCreator()) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    LinkedList linkedList2 = linkedList;
                    linkedList2.add(annotatedWithParams2);
                    linkedList = linkedList2;
                }
            }
            singleArgCreatorDefaultsToProperties = z2;
            it3 = it;
            visibilityChecker3 = visibilityChecker;
        }
        VisibilityChecker<?> visibilityChecker4 = visibilityChecker3;
        if (linkedList != null && !creatorCollector.hasDelegatingCreator() && !creatorCollector.hasPropertyBasedCreator()) {
            _checkImplicitlyNamedConstructors(deserializationContext, beanDescription, visibilityChecker4, annotationIntrospector, creatorCollector, linkedList);
        }
    }

    public void _addImplicitFactoryCreators(DeserializationContext deserializationContext, CreatorCollectionState creatorCollectionState, List<CreatorCandidate> list) throws JsonMappingException {
        Iterator<CreatorCandidate> it;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map;
        VisibilityChecker<?> visibilityChecker;
        int i3;
        AnnotatedWithParams annotatedWithParams;
        boolean z2;
        SettableBeanProperty[] settableBeanPropertyArr;
        DeserializationContext deserializationContext2 = deserializationContext;
        CreatorCollectionState creatorCollectionState2 = creatorCollectionState;
        BeanDescription beanDescription = creatorCollectionState2.beanDesc;
        CreatorCollector creatorCollector = creatorCollectionState2.creators;
        AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker2 = creatorCollectionState2.vchecker;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map2 = creatorCollectionState2.creatorParams;
        Iterator<CreatorCandidate> it2 = list.iterator();
        while (it2.hasNext()) {
            CreatorCandidate next = it2.next();
            int paramCount = next.paramCount();
            AnnotatedWithParams creator = next.creator();
            BeanPropertyDefinition[] beanPropertyDefinitionArr = map2.get(creator);
            if (paramCount == 1) {
                boolean z3 = false;
                BeanPropertyDefinition propertyDef = next.propertyDef(0);
                if (!_checkIfCreatorPropertyBased(annotationIntrospector, creator, propertyDef)) {
                    _handleSingleArgumentCreator(creatorCollector, creator, false, visibilityChecker2.isCreatorVisible((AnnotatedMember) creator));
                    if (propertyDef != null) {
                        ((POJOPropertyBuilder) propertyDef).removeConstructors();
                    }
                } else {
                    SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[paramCount];
                    int i4 = 0;
                    int i5 = 0;
                    int i6 = 0;
                    AnnotatedParameter annotatedParameter = null;
                    while (i4 < paramCount) {
                        AnnotatedParameter parameter = creator.getParameter(i4);
                        BeanPropertyDefinition beanPropertyDefinition = beanPropertyDefinitionArr == null ? null : beanPropertyDefinitionArr[i4];
                        JacksonInject.Value findInjectableValue = annotationIntrospector.findInjectableValue(parameter);
                        PropertyName fullName = beanPropertyDefinition == null ? null : beanPropertyDefinition.getFullName();
                        if (beanPropertyDefinition == null || !beanPropertyDefinition.isExplicitlyNamed()) {
                            AnnotatedParameter annotatedParameter2 = parameter;
                            i3 = i4;
                            visibilityChecker = visibilityChecker2;
                            map = map2;
                            it = it2;
                            settableBeanPropertyArr = settableBeanPropertyArr2;
                            z2 = z3;
                            annotatedWithParams = creator;
                            if (findInjectableValue != null) {
                                i6++;
                                settableBeanPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, fullName, i3, annotatedParameter2, findInjectableValue);
                            } else {
                                AnnotatedParameter annotatedParameter3 = annotatedParameter2;
                                if (annotationIntrospector.findUnwrappingNameTransformer(annotatedParameter3) != null) {
                                    _reportUnwrappedCreatorProperty(deserializationContext2, beanDescription, annotatedParameter3);
                                } else if (annotatedParameter == null) {
                                    annotatedParameter = annotatedParameter3;
                                }
                            }
                        } else {
                            i5++;
                            i3 = i4;
                            visibilityChecker = visibilityChecker2;
                            settableBeanPropertyArr = settableBeanPropertyArr2;
                            map = map2;
                            z2 = z3;
                            it = it2;
                            annotatedWithParams = creator;
                            settableBeanPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, fullName, i3, parameter, findInjectableValue);
                        }
                        i4 = i3 + 1;
                        settableBeanPropertyArr2 = settableBeanPropertyArr;
                        z3 = z2;
                        creator = annotatedWithParams;
                        visibilityChecker2 = visibilityChecker;
                        map2 = map;
                        it2 = it;
                    }
                    VisibilityChecker<?> visibilityChecker3 = visibilityChecker2;
                    Map<AnnotatedWithParams, BeanPropertyDefinition[]> map3 = map2;
                    Iterator<CreatorCandidate> it3 = it2;
                    SettableBeanProperty[] settableBeanPropertyArr3 = settableBeanPropertyArr2;
                    boolean z4 = z3;
                    AnnotatedWithParams annotatedWithParams2 = creator;
                    if (i5 > 0 || i6 > 0) {
                        if (i5 + i6 == paramCount) {
                            creatorCollector.addPropertyCreator(annotatedWithParams2, z4, settableBeanPropertyArr3);
                        } else if (i5 == 0 && i6 + 1 == paramCount) {
                            creatorCollector.addDelegatingCreator(annotatedWithParams2, z4, settableBeanPropertyArr3, z4 ? 1 : 0);
                        } else {
                            deserializationContext2.reportBadTypeDefinition(beanDescription, "Argument #%d of factory method %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", Integer.valueOf(annotatedParameter == null ? -1 : annotatedParameter.getIndex()), annotatedWithParams2);
                        }
                    }
                    it2 = it3;
                    visibilityChecker2 = visibilityChecker3;
                    map2 = map3;
                }
            }
        }
    }

    public void _addRecordConstructor(DeserializationContext deserializationContext, CreatorCollectionState creatorCollectionState, AnnotatedConstructor annotatedConstructor, List<String> list) throws JsonMappingException {
        CreatorCollectionState creatorCollectionState2 = creatorCollectionState;
        AnnotatedConstructor annotatedConstructor2 = annotatedConstructor;
        int parameterCount = annotatedConstructor.getParameterCount();
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
        for (int i3 = 0; i3 < parameterCount; i3++) {
            AnnotatedParameter parameter = annotatedConstructor2.getParameter(i3);
            JacksonInject.Value findInjectableValue = annotationIntrospector.findInjectableValue(parameter);
            PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(parameter);
            if (findNameForDeserialization == null || findNameForDeserialization.isEmpty()) {
                findNameForDeserialization = PropertyName.construct(list.get(i3));
            } else {
                List<String> list2 = list;
            }
            settableBeanPropertyArr[i3] = constructCreatorProperty(deserializationContext, creatorCollectionState2.beanDesc, findNameForDeserialization, i3, parameter, findInjectableValue);
        }
        creatorCollectionState2.creators.addPropertyCreator(annotatedConstructor2, false, settableBeanPropertyArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0042, code lost:
        r2 = new java.util.ArrayList();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.ValueInstantiator _constructDefaultValueInstantiator(com.fasterxml.jackson.databind.DeserializationContext r10, com.fasterxml.jackson.databind.BeanDescription r11) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.DeserializationConfig r0 = r10.getConfig()
            java.lang.Class r1 = r11.getBeanClass()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r11.getClassInfo()
            com.fasterxml.jackson.databind.introspect.VisibilityChecker r6 = r0.getDefaultVisibilityChecker(r1, r2)
            com.fasterxml.jackson.databind.cfg.ConstructorDetector r1 = r0.getConstructorDetector()
            com.fasterxml.jackson.databind.deser.impl.CreatorCollector r7 = new com.fasterxml.jackson.databind.deser.impl.CreatorCollector
            r7.<init>(r11, r0)
            java.util.Map r8 = r9._findCreatorsFromProperties(r10, r11)
            com.fasterxml.jackson.databind.deser.BasicDeserializerFactory$CreatorCollectionState r0 = new com.fasterxml.jackson.databind.deser.BasicDeserializerFactory$CreatorCollectionState
            r3 = r0
            r4 = r10
            r5 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            boolean r2 = r1.requireCtorAnnotation()
            r2 = r2 ^ 1
            r9._addExplicitFactoryCreators(r10, r0, r2)
            com.fasterxml.jackson.databind.JavaType r2 = r11.getType()
            boolean r2 = r2.isConcrete()
            if (r2 == 0) goto L_0x007c
            com.fasterxml.jackson.databind.JavaType r2 = r11.getType()
            boolean r2 = r2.isRecordType()
            if (r2 == 0) goto L_0x0057
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r3 = com.fasterxml.jackson.databind.jdk14.JDK14Util.findRecordConstructor(r10, r11, r2)
            if (r3 == 0) goto L_0x0057
            r9._addRecordConstructor(r10, r0, r3, r2)
            com.fasterxml.jackson.databind.deser.impl.CreatorCollector r9 = r0.creators
            com.fasterxml.jackson.databind.deser.ValueInstantiator r9 = r9.constructValueInstantiator(r10)
            return r9
        L_0x0057:
            boolean r2 = r11.isNonStaticInnerClass()
            if (r2 == 0) goto L_0x005e
            goto L_0x007c
        L_0x005e:
            java.lang.Class r11 = r11.getBeanClass()
            boolean r11 = r1.shouldIntrospectorImplicitConstructors(r11)
            r9._addExplicitConstructorCreators(r10, r0, r11)
            boolean r11 = r0.hasImplicitConstructorCandidates()
            if (r11 == 0) goto L_0x007c
            boolean r11 = r0.hasExplicitConstructors()
            if (r11 != 0) goto L_0x007c
            java.util.List r11 = r0.implicitConstructorCandidates()
            r9._addImplicitConstructorCreators(r10, r0, r11)
        L_0x007c:
            boolean r11 = r0.hasImplicitFactoryCandidates()
            if (r11 == 0) goto L_0x0095
            boolean r11 = r0.hasExplicitFactories()
            if (r11 != 0) goto L_0x0095
            boolean r11 = r0.hasExplicitConstructors()
            if (r11 != 0) goto L_0x0095
            java.util.List r11 = r0.implicitFactoryCandidates()
            r9._addImplicitFactoryCreators(r10, r0, r11)
        L_0x0095:
            com.fasterxml.jackson.databind.deser.impl.CreatorCollector r9 = r0.creators
            com.fasterxml.jackson.databind.deser.ValueInstantiator r9 = r9.constructValueInstantiator(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory._constructDefaultValueInstantiator(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription):com.fasterxml.jackson.databind.deser.ValueInstantiator");
    }

    public Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> emptyMap = Collections.emptyMap();
        for (BeanPropertyDefinition next : beanDescription.findProperties()) {
            Iterator<AnnotatedParameter> constructorParameters = next.getConstructorParameters();
            while (constructorParameters.hasNext()) {
                AnnotatedParameter next2 = constructorParameters.next();
                AnnotatedWithParams owner = next2.getOwner();
                BeanPropertyDefinition[] beanPropertyDefinitionArr = emptyMap.get(owner);
                int index = next2.getIndex();
                if (beanPropertyDefinitionArr == null) {
                    if (emptyMap.isEmpty()) {
                        emptyMap = new LinkedHashMap<>();
                    }
                    beanPropertyDefinitionArr = new BeanPropertyDefinition[owner.getParameterCount()];
                    emptyMap.put(owner, beanPropertyDefinitionArr);
                } else if (beanPropertyDefinitionArr[index] != null) {
                    deserializationContext.reportBadTypeDefinition(beanDescription, "Conflict: parameter #%d of %s bound to more than one property; %s vs %s", Integer.valueOf(index), owner, beanPropertyDefinitionArr[index], next);
                }
                beanPropertyDefinitionArr[index] = next;
            }
        }
        return emptyMap;
    }

    public JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findArrayDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer2 = findArrayDeserializer.findArrayDeserializer(arrayType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer2 != null) {
                return findArrayDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findBeanDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findBeanDeserializer2 = findBeanDeserializer.findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            if (findBeanDeserializer2 != null) {
                return findBeanDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer2 = findCollectionDeserializer.findCollectionDeserializer(collectionType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer2 != null) {
                return findCollectionDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer2 = findCollectionLikeDeserializer.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer2 != null) {
                return findCollectionLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findEnumDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer2 = findEnumDeserializer.findEnumDeserializer(cls, deserializationConfig, beanDescription);
            if (findEnumDeserializer2 != null) {
                return findEnumDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer2 = findMapDeserializer.findMapDeserializer(mapType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer2 != null) {
                return findMapDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer2 = findMapLikeDeserializer.findMapLikeDeserializer(mapLikeType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer2 != null) {
                return findMapLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType referenceType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findReferenceDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findReferenceDeserializer2 = findReferenceDeserializer.findReferenceDeserializer(referenceType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findReferenceDeserializer2 != null) {
                return findReferenceDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findTreeNodeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer2 = findTreeNodeDeserializer.findTreeNodeDeserializer(cls, deserializationConfig, beanDescription);
            if (findTreeNodeDeserializer2 != null) {
                return findTreeNodeDeserializer2;
            }
        }
        return null;
    }

    @Deprecated
    public AnnotatedMethod _findJsonValueFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        if (javaType == null) {
            return null;
        }
        return deserializationConfig.introspect(javaType).findJsonValueMethod();
    }

    public JavaType _findRemappedType(DeserializationConfig deserializationConfig, Class<?> cls) throws JsonMappingException {
        JavaType mapAbstractType = mapAbstractType(deserializationConfig, deserializationConfig.constructType(cls));
        if (mapAbstractType == null || mapAbstractType.hasRawClass(cls)) {
            return null;
        }
        return mapAbstractType;
    }

    public PropertyMetadata _getSetterInfo(DeserializationContext deserializationContext, BeanProperty beanProperty, PropertyMetadata propertyMetadata) {
        Nulls nulls;
        JsonSetter.Value findSetterInfo;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotatedMember member = beanProperty.getMember();
        Nulls nulls2 = null;
        if (member != null) {
            if (annotationIntrospector == null || (findSetterInfo = annotationIntrospector.findSetterInfo(member)) == null) {
                nulls = null;
            } else {
                nulls2 = findSetterInfo.nonDefaultValueNulls();
                nulls = findSetterInfo.nonDefaultContentNulls();
            }
            JsonSetter.Value setterInfo = config.getConfigOverride(beanProperty.getType().getRawClass()).getSetterInfo();
            if (setterInfo != null) {
                if (nulls2 == null) {
                    nulls2 = setterInfo.nonDefaultValueNulls();
                }
                if (nulls == null) {
                    nulls = setterInfo.nonDefaultContentNulls();
                }
            }
        } else {
            nulls = null;
        }
        JsonSetter.Value defaultSetterInfo = config.getDefaultSetterInfo();
        if (nulls2 == null) {
            nulls2 = defaultSetterInfo.nonDefaultValueNulls();
        }
        if (nulls == null) {
            nulls = defaultSetterInfo.nonDefaultContentNulls();
        }
        return (nulls2 == null && nulls == null) ? propertyMetadata : propertyMetadata.withNulls(nulls2, nulls);
    }

    public boolean _handleSingleArgumentCreator(CreatorCollector creatorCollector, AnnotatedWithParams annotatedWithParams, boolean z2, boolean z3) {
        Class<?> rawParameterType = annotatedWithParams.getRawParameterType(0);
        if (rawParameterType == String.class || rawParameterType == CLASS_CHAR_SEQUENCE) {
            if (z2 || z3) {
                creatorCollector.addStringCreator(annotatedWithParams, z2);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z2 || z3) {
                creatorCollector.addIntCreator(annotatedWithParams, z2);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z2 || z3) {
                creatorCollector.addLongCreator(annotatedWithParams, z2);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z2 || z3) {
                creatorCollector.addDoubleCreator(annotatedWithParams, z2);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z2 || z3) {
                creatorCollector.addBooleanCreator(annotatedWithParams, z2);
            }
            return true;
        } else {
            if (rawParameterType == BigInteger.class && (z2 || z3)) {
                creatorCollector.addBigIntegerCreator(annotatedWithParams, z2);
            }
            if (rawParameterType == BigDecimal.class && (z2 || z3)) {
                creatorCollector.addBigDecimalCreator(annotatedWithParams, z2);
            }
            if (!z2) {
                return false;
            }
            creatorCollector.addDelegatingCreator(annotatedWithParams, z2, (SettableBeanProperty[]) null, 0);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r1 = r1.findCreatorAnnotation(r2.getConfig(), r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean _hasCreatorAnnotation(com.fasterxml.jackson.databind.DeserializationContext r2, com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r1 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2.getAnnotationIntrospector()
            r0 = 0
            if (r1 == 0) goto L_0x0016
            com.fasterxml.jackson.databind.DeserializationConfig r2 = r2.getConfig()
            com.fasterxml.jackson.annotation.JsonCreator$Mode r1 = r1.findCreatorAnnotation(r2, r3)
            if (r1 == 0) goto L_0x0016
            com.fasterxml.jackson.annotation.JsonCreator$Mode r2 = com.fasterxml.jackson.annotation.JsonCreator.Mode.DISABLED
            if (r1 == r2) goto L_0x0016
            r0 = 1
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory._hasCreatorAnnotation(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.introspect.Annotated):boolean");
    }

    public CollectionType _mapAbstractCollectionType(JavaType javaType, DeserializationConfig deserializationConfig) {
        Class<?> findCollectionFallback = ContainerDefaultMappings.findCollectionFallback(javaType);
        if (findCollectionFallback != null) {
            return (CollectionType) deserializationConfig.getTypeFactory().constructSpecializedType(javaType, findCollectionFallback, true);
        }
        return null;
    }

    public MapType _mapAbstractMapType(JavaType javaType, DeserializationConfig deserializationConfig) {
        Class<?> findMapFallback = ContainerDefaultMappings.findMapFallback(javaType);
        if (findMapFallback != null) {
            return (MapType) deserializationConfig.getTypeFactory().constructSpecializedType(javaType, findMapFallback, true);
        }
        return null;
    }

    public void _reportUnwrappedCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedParameter annotatedParameter) throws JsonMappingException {
        deserializationContext.reportBadTypeDefinition(beanDescription, "Cannot define Creator parameter %d as `@JsonUnwrapped`: combination not yet supported", Integer.valueOf(annotatedParameter.getIndex()));
    }

    public void _validateNamedPropertyParameter(DeserializationContext deserializationContext, BeanDescription beanDescription, CreatorCandidate creatorCandidate, int i3, PropertyName propertyName, JacksonInject.Value value) throws JsonMappingException {
        if (propertyName == null && value == null) {
            deserializationContext.reportBadTypeDefinition(beanDescription, "Argument #%d of constructor %s has no property name (and is not Injectable): can not use as property-based Creator", Integer.valueOf(i3), creatorCandidate);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r1 = r1.valueInstantiatorInstance(r2, r3, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.ValueInstantiator _valueInstantiatorInstance(com.fasterxml.jackson.databind.DeserializationConfig r2, com.fasterxml.jackson.databind.introspect.Annotated r3, java.lang.Object r4) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r1 = this;
            r1 = 0
            if (r4 != 0) goto L_0x0004
            return r1
        L_0x0004:
            boolean r0 = r4 instanceof com.fasterxml.jackson.databind.deser.ValueInstantiator
            if (r0 == 0) goto L_0x000b
            com.fasterxml.jackson.databind.deser.ValueInstantiator r4 = (com.fasterxml.jackson.databind.deser.ValueInstantiator) r4
            return r4
        L_0x000b:
            boolean r0 = r4 instanceof java.lang.Class
            if (r0 == 0) goto L_0x004b
            java.lang.Class r4 = (java.lang.Class) r4
            boolean r0 = com.fasterxml.jackson.databind.util.ClassUtil.isBogusClass(r4)
            if (r0 == 0) goto L_0x0018
            return r1
        L_0x0018:
            java.lang.Class<com.fasterxml.jackson.databind.deser.ValueInstantiator> r1 = com.fasterxml.jackson.databind.deser.ValueInstantiator.class
            boolean r1 = r1.isAssignableFrom(r4)
            if (r1 == 0) goto L_0x0038
            com.fasterxml.jackson.databind.cfg.HandlerInstantiator r1 = r2.getHandlerInstantiator()
            if (r1 == 0) goto L_0x002d
            com.fasterxml.jackson.databind.deser.ValueInstantiator r1 = r1.valueInstantiatorInstance(r2, r3, r4)
            if (r1 == 0) goto L_0x002d
            return r1
        L_0x002d:
            boolean r1 = r2.canOverrideAccessModifiers()
            java.lang.Object r1 = com.fasterxml.jackson.databind.util.ClassUtil.createInstance(r4, r1)
            com.fasterxml.jackson.databind.deser.ValueInstantiator r1 = (com.fasterxml.jackson.databind.deser.ValueInstantiator) r1
            return r1
        L_0x0038:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "AnnotationIntrospector returned Class "
            r2.<init>(r3)
            java.lang.String r3 = "; expected Class<ValueInstantiator>"
            java.lang.String r2 = androidx.constraintlayout.core.state.b.g(r4, r2, r3)
            r1.<init>(r2)
            throw r1
        L_0x004b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "AnnotationIntrospector returned key deserializer definition of type "
            r2.<init>(r3)
            java.lang.Class r3 = r4.getClass()
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r3 = "; expected type KeyDeserializer or Class<KeyDeserializer> instead"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory._valueInstantiatorInstance(com.fasterxml.jackson.databind.DeserializationConfig, com.fasterxml.jackson.databind.introspect.Annotated, java.lang.Object):com.fasterxml.jackson.databind.deser.ValueInstantiator");
    }

    public SettableBeanProperty constructCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, PropertyName propertyName, int i3, AnnotatedParameter annotatedParameter, JacksonInject.Value value) throws JsonMappingException {
        PropertyMetadata propertyMetadata;
        PropertyName propertyName2;
        DeserializationContext deserializationContext2 = deserializationContext;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            propertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
            propertyName2 = null;
        } else {
            PropertyMetadata construct = PropertyMetadata.construct(annotationIntrospector.hasRequiredMarker(annotatedParameter2), annotationIntrospector.findPropertyDescription(annotatedParameter2), annotationIntrospector.findPropertyIndex(annotatedParameter2), annotationIntrospector.findPropertyDefaultValue(annotatedParameter2));
            propertyName2 = annotationIntrospector.findWrapperName(annotatedParameter2);
            propertyMetadata = construct;
        }
        JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, annotatedParameter2, annotatedParameter.getType());
        BeanProperty.Std std = new BeanProperty.Std(propertyName, resolveMemberAndTypeAnnotations, propertyName2, annotatedParameter, propertyMetadata);
        TypeDeserializer typeDeserializer = (TypeDeserializer) resolveMemberAndTypeAnnotations.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, resolveMemberAndTypeAnnotations);
        }
        CreatorProperty construct2 = CreatorProperty.construct(propertyName, resolveMemberAndTypeAnnotations, std.getWrapperName(), typeDeserializer, beanDescription.getClassAnnotations(), annotatedParameter, i3, value, _getSetterInfo(deserializationContext, std, propertyMetadata));
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, annotatedParameter2);
        if (findDeserializerFromAnnotation == null) {
            findDeserializerFromAnnotation = (JsonDeserializer) resolveMemberAndTypeAnnotations.getValueHandler();
        }
        return findDeserializerFromAnnotation != null ? construct2.withValueDeserializer(deserializationContext.handlePrimaryContextualization(findDeserializerFromAnnotation, construct2, resolveMemberAndTypeAnnotations)) : construct2;
    }

    public EnumResolver constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig, AnnotatedMember annotatedMember) {
        if (annotatedMember == null) {
            return EnumResolver.constructFor(deserializationConfig, cls);
        }
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMember.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return EnumResolver.constructUsingMethod(deserializationConfig, cls, annotatedMember);
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext deserializationContext, ArrayType arrayType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomArrayDeserializer == null) {
            if (jsonDeserializer == null) {
                Class<?> rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (rawClass == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            _findCustomArrayDeserializer = new ObjectArrayDeserializer(arrayType, jsonDeserializer, typeDeserializer2);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyArrayDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomArrayDeserializer = modifyArrayDeserializer.modifyArrayDeserializer(config, arrayType, beanDescription, _findCustomArrayDeserializer);
            }
        }
        return _findCustomArrayDeserializer;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = collectionType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomCollectionDeserializer == null) {
            Class<?> rawClass = collectionType.getRawClass();
            if (jsonDeserializer == null && EnumSet.class.isAssignableFrom(rawClass)) {
                _findCustomCollectionDeserializer = new EnumSetDeserializer(contentType, (JsonDeserializer<?>) null);
            }
        }
        if (_findCustomCollectionDeserializer == null) {
            if (collectionType.isInterface() || collectionType.isAbstract()) {
                CollectionType _mapAbstractCollectionType = _mapAbstractCollectionType(collectionType, config);
                if (_mapAbstractCollectionType != null) {
                    beanDescription = config.introspectForCreation(_mapAbstractCollectionType);
                    collectionType = _mapAbstractCollectionType;
                } else if (collectionType.getTypeHandler() != null) {
                    _findCustomCollectionDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                } else {
                    throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Collection type " + collectionType);
                }
            }
            if (_findCustomCollectionDeserializer == null) {
                ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
                if (!findValueInstantiator.canCreateUsingDefault()) {
                    if (collectionType.hasRawClass(ArrayBlockingQueue.class)) {
                        return new ArrayBlockingQueueDeserializer(collectionType, jsonDeserializer, typeDeserializer2, findValueInstantiator);
                    }
                    JsonDeserializer<?> findForCollection = JavaUtilCollectionsDeserializers.findForCollection(deserializationContext, collectionType);
                    if (findForCollection != null) {
                        return findForCollection;
                    }
                }
                _findCustomCollectionDeserializer = contentType.hasRawClass(String.class) ? new StringCollectionDeserializer(collectionType, jsonDeserializer, findValueInstantiator) : new CollectionDeserializer(collectionType, jsonDeserializer, typeDeserializer2, findValueInstantiator);
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionDeserializer = modifyCollectionDeserializer.modifyCollectionDeserializer(config, collectionType, beanDescription, _findCustomCollectionDeserializer);
            }
        }
        return _findCustomCollectionDeserializer;
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext deserializationContext, CollectionLikeType collectionLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = collectionLikeType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        JsonDeserializer<?> _findCustomCollectionLikeDeserializer = _findCustomCollectionLikeDeserializer(collectionLikeType, config, beanDescription, typeDeserializer == null ? findTypeDeserializer(config, contentType) : typeDeserializer, jsonDeserializer);
        if (_findCustomCollectionLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionLikeDeserializer = modifyCollectionLikeDeserializer.modifyCollectionLikeDeserializer(config, collectionLikeType, beanDescription, _findCustomCollectionLikeDeserializer);
            }
        }
        return _findCustomCollectionLikeDeserializer;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, beanDescription);
        if (_findCustomEnumDeserializer == null) {
            if (rawClass == Enum.class) {
                return AbstractDeserializer.constructForNonPOJO(beanDescription);
            }
            ValueInstantiator _constructDefaultValueInstantiator = _constructDefaultValueInstantiator(deserializationContext, beanDescription);
            SettableBeanProperty[] fromObjectArguments = _constructDefaultValueInstantiator == null ? null : _constructDefaultValueInstantiator.getFromObjectArguments(deserializationContext.getConfig());
            Iterator<AnnotatedMethod> it = beanDescription.getFactoryMethods().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnnotatedMethod next = it.next();
                if (_hasCreatorAnnotation(deserializationContext, next)) {
                    if (next.getParameterCount() == 0) {
                        _findCustomEnumDeserializer = EnumDeserializer.deserializerForNoArgsCreator(config, rawClass, next);
                    } else {
                        if (!next.getRawReturnType().isAssignableFrom(rawClass)) {
                            String annotatedMethod = next.toString();
                            deserializationContext.reportBadDefinition(javaType, "Invalid `@JsonCreator` annotated Enum factory method [" + annotatedMethod + "]: needs to return compatible type");
                        }
                        _findCustomEnumDeserializer = EnumDeserializer.deserializerForCreator(config, rawClass, next, _constructDefaultValueInstantiator, fromObjectArguments);
                    }
                }
            }
            if (_findCustomEnumDeserializer == null) {
                _findCustomEnumDeserializer = new EnumDeserializer(constructEnumResolver(rawClass, config, beanDescription.findJsonValueAccessor()), Boolean.valueOf(config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)));
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyEnumDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomEnumDeserializer = modifyEnumDeserializer.modifyEnumDeserializer(config, javaType, beanDescription, _findCustomEnumDeserializer);
            }
        }
        return _findCustomEnumDeserializer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001b A[LOOP:0: B:3:0x001b->B:6:0x002b, LOOP_START, PHI: r2 
      PHI: (r2v9 com.fasterxml.jackson.databind.KeyDeserializer) = (r2v0 com.fasterxml.jackson.databind.KeyDeserializer), (r2v12 com.fasterxml.jackson.databind.KeyDeserializer) binds: [B:2:0x000d, B:6:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.KeyDeserializer createKeyDeserializer(com.fasterxml.jackson.databind.DeserializationContext r6, com.fasterxml.jackson.databind.JavaType r7) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r5 = this;
            com.fasterxml.jackson.databind.DeserializationConfig r0 = r6.getConfig()
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r5._factoryConfig
            boolean r1 = r1.hasKeyDeserializers()
            r2 = 0
            if (r1 == 0) goto L_0x002e
            com.fasterxml.jackson.databind.BeanDescription r1 = r0.introspectClassAnnotations((com.fasterxml.jackson.databind.JavaType) r7)
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r3 = r5._factoryConfig
            java.lang.Iterable r3 = r3.keyDeserializers()
            java.util.Iterator r3 = r3.iterator()
        L_0x001b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002f
            java.lang.Object r2 = r3.next()
            com.fasterxml.jackson.databind.deser.KeyDeserializers r2 = (com.fasterxml.jackson.databind.deser.KeyDeserializers) r2
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r2.findKeyDeserializer(r7, r0, r1)
            if (r2 == 0) goto L_0x001b
            goto L_0x002f
        L_0x002e:
            r1 = r2
        L_0x002f:
            if (r2 != 0) goto L_0x0054
            if (r1 != 0) goto L_0x003b
            java.lang.Class r1 = r7.getRawClass()
            com.fasterxml.jackson.databind.BeanDescription r1 = r0.introspectClassAnnotations((java.lang.Class<?>) r1)
        L_0x003b:
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r1.getClassInfo()
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r5.findKeyDeserializerFromAnnotation(r6, r1)
            if (r2 != 0) goto L_0x0054
            boolean r1 = r7.isEnumType()
            if (r1 == 0) goto L_0x0050
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r5._createEnumKeyDeserializer(r6, r7)
            goto L_0x0054
        L_0x0050:
            com.fasterxml.jackson.databind.KeyDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers.findStringBasedKeyDeserializer(r0, r7)
        L_0x0054:
            if (r2 == 0) goto L_0x0079
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r6 = r5._factoryConfig
            boolean r6 = r6.hasDeserializerModifiers()
            if (r6 == 0) goto L_0x0079
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r5 = r5._factoryConfig
            java.lang.Iterable r5 = r5.deserializerModifiers()
            java.util.Iterator r5 = r5.iterator()
        L_0x0068:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0079
            java.lang.Object r6 = r5.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r6 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r6
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r6.modifyKeyDeserializer(r0, r7, r2)
            goto L_0x0068
        L_0x0079:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createKeyDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.KeyDeserializer");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.fasterxml.jackson.databind.deser.std.MapDeserializer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: com.fasterxml.jackson.databind.deser.std.MapDeserializer} */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0111  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext r20, com.fasterxml.jackson.databind.type.MapType r21, com.fasterxml.jackson.databind.BeanDescription r22) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r9 = r21
            com.fasterxml.jackson.databind.DeserializationConfig r10 = r20.getConfig()
            com.fasterxml.jackson.databind.JavaType r11 = r21.getKeyType()
            com.fasterxml.jackson.databind.JavaType r0 = r21.getContentType()
            java.lang.Object r1 = r0.getValueHandler()
            r16 = r1
            com.fasterxml.jackson.databind.JsonDeserializer r16 = (com.fasterxml.jackson.databind.JsonDeserializer) r16
            java.lang.Object r1 = r11.getValueHandler()
            r15 = r1
            com.fasterxml.jackson.databind.KeyDeserializer r15 = (com.fasterxml.jackson.databind.KeyDeserializer) r15
            java.lang.Object r1 = r0.getTypeHandler()
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r1 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r1
            if (r1 != 0) goto L_0x0030
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r0 = r7.findTypeDeserializer(r10, r0)
            r17 = r0
            goto L_0x0032
        L_0x0030:
            r17 = r1
        L_0x0032:
            r0 = r19
            r1 = r21
            r2 = r10
            r3 = r22
            r4 = r15
            r5 = r17
            r6 = r16
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r0._findCustomMapDeserializer(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x0106
            java.lang.Class r1 = r21.getRawClass()
            java.lang.Class<java.util.EnumMap> r2 = java.util.EnumMap.class
            boolean r3 = r2.isAssignableFrom(r1)
            r18 = 0
            if (r3 == 0) goto L_0x007d
            if (r1 != r2) goto L_0x0059
            r12 = r22
            r2 = r18
            goto L_0x0060
        L_0x0059:
            r12 = r22
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7.findValueInstantiator(r8, r12)
            r2 = r0
        L_0x0060:
            boolean r0 = r11.isEnumImplType()
            if (r0 == 0) goto L_0x0075
            com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer r11 = new com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer
            r3 = 0
            r6 = 0
            r0 = r11
            r1 = r21
            r4 = r16
            r5 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6)
            goto L_0x007f
        L_0x0075:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot construct EnumMap; generic (key) type not available"
            r0.<init>(r1)
            throw r0
        L_0x007d:
            r12 = r22
        L_0x007f:
            if (r0 != 0) goto L_0x0104
            boolean r1 = r21.isInterface()
            if (r1 != 0) goto L_0x0099
            boolean r1 = r21.isAbstract()
            if (r1 == 0) goto L_0x008e
            goto L_0x0099
        L_0x008e:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers.findForMap(r20, r21)
            if (r0 == 0) goto L_0x0095
            return r0
        L_0x0095:
            r2 = r0
            r1 = r9
        L_0x0097:
            r0 = r12
            goto L_0x00b5
        L_0x0099:
            com.fasterxml.jackson.databind.type.MapType r1 = r7._mapAbstractMapType(r9, r10)
            if (r1 == 0) goto L_0x00a8
            r1.getRawClass()
            com.fasterxml.jackson.databind.BeanDescription r2 = r10.introspectForCreation(r1)
            r12 = r2
            goto L_0x00b3
        L_0x00a8:
            java.lang.Object r0 = r21.getTypeHandler()
            if (r0 == 0) goto L_0x00f0
            com.fasterxml.jackson.databind.deser.AbstractDeserializer r0 = com.fasterxml.jackson.databind.deser.AbstractDeserializer.constructForNonPOJO(r22)
            r1 = r9
        L_0x00b3:
            r2 = r0
            goto L_0x0097
        L_0x00b5:
            if (r2 != 0) goto L_0x00ed
            com.fasterxml.jackson.databind.deser.ValueInstantiator r14 = r7.findValueInstantiator(r8, r0)
            com.fasterxml.jackson.databind.deser.std.MapDeserializer r2 = new com.fasterxml.jackson.databind.deser.std.MapDeserializer
            r12 = r2
            r13 = r1
            r12.<init>(r13, r14, r15, r16, r17)
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r0.getClassInfo()
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            com.fasterxml.jackson.annotation.JsonIgnoreProperties$Value r3 = r10.getDefaultPropertyIgnorals(r4, r3)
            if (r3 != 0) goto L_0x00d1
            r3 = r18
            goto L_0x00d5
        L_0x00d1:
            java.util.Set r3 = r3.findIgnoredForDeserialization()
        L_0x00d5:
            r2.setIgnorableProperties((java.util.Set<java.lang.String>) r3)
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r0.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIncludeProperties$Value r3 = r10.getDefaultPropertyInclusions(r4, r3)
            if (r3 != 0) goto L_0x00e5
        L_0x00e2:
            r3 = r18
            goto L_0x00ea
        L_0x00e5:
            java.util.Set r18 = r3.getIncluded()
            goto L_0x00e2
        L_0x00ea:
            r2.setIncludableProperties(r3)
        L_0x00ed:
            r12 = r0
            r0 = r2
            goto L_0x0109
        L_0x00f0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Cannot find a deserializer for non-concrete Map type "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0104:
            r1 = r9
            goto L_0x0109
        L_0x0106:
            r12 = r22
            goto L_0x0104
        L_0x0109:
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r2 = r7._factoryConfig
            boolean r2 = r2.hasDeserializerModifiers()
            if (r2 == 0) goto L_0x012c
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r2 = r7._factoryConfig
            java.lang.Iterable r2 = r2.deserializerModifiers()
            java.util.Iterator r2 = r2.iterator()
        L_0x011b:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x012c
            java.lang.Object r3 = r2.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r3 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r3
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3.modifyMapDeserializer(r10, r1, r12, r0)
            goto L_0x011b
        L_0x012c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext deserializationContext, MapLikeType mapLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType keyType = mapLikeType.getKeyType();
        JavaType contentType = mapLikeType.getContentType();
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomMapLikeDeserializer = _findCustomMapLikeDeserializer(mapLikeType, config, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
        if (_findCustomMapLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyMapLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomMapLikeDeserializer = modifyMapLikeDeserializer.modifyMapLikeDeserializer(config, mapLikeType, beanDescription, _findCustomMapLikeDeserializer);
            }
        }
        return _findCustomMapLikeDeserializer;
    }

    public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext deserializationContext, ReferenceType referenceType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = referenceType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomReferenceDeserializer = _findCustomReferenceDeserializer(referenceType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomReferenceDeserializer == null) {
            Class<AtomicReference> cls = AtomicReference.class;
            if (referenceType.isTypeOrSubTypeOf(cls)) {
                return new AtomicReferenceDeserializer(referenceType, referenceType.getRawClass() == cls ? null : findValueInstantiator(deserializationContext, beanDescription), typeDeserializer2, jsonDeserializer);
            }
        }
        if (_findCustomReferenceDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyReferenceDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomReferenceDeserializer = modifyReferenceDeserializer.modifyReferenceDeserializer(config, referenceType, beanDescription, _findCustomReferenceDeserializer);
            }
        }
        return _findCustomReferenceDeserializer;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanDescription);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : JsonNodeDeserializer.getDeserializer(rawClass);
    }

    public JsonDeserializer<Object> findContentDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findContentDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated)) == null) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, findContentDeserializer);
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2;
        JavaType javaType3;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == CLASS_OBJECT || rawClass == CLASS_SERIALIZABLE) {
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasAbstractTypeResolvers()) {
                javaType2 = _findRemappedType(config, List.class);
                javaType3 = _findRemappedType(config, Map.class);
            } else {
                javaType2 = null;
                javaType3 = null;
            }
            return new UntypedObjectDeserializer(javaType2, javaType3);
        } else if (rawClass == CLASS_STRING || rawClass == CLASS_CHAR_SEQUENCE) {
            return StringDeserializer.instance;
        } else {
            Class<?> cls = CLASS_ITERABLE;
            if (rawClass == cls) {
                TypeFactory typeFactory = deserializationContext.getTypeFactory();
                JavaType[] findTypeParameters = typeFactory.findTypeParameters(javaType, cls);
                return createCollectionDeserializer(deserializationContext, typeFactory.constructCollectionType((Class<? extends Collection>) Collection.class, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]), beanDescription);
            } else if (rawClass == CLASS_MAP_ENTRY) {
                JavaType containedTypeOrUnknown = javaType.containedTypeOrUnknown(0);
                JavaType containedTypeOrUnknown2 = javaType.containedTypeOrUnknown(1);
                TypeDeserializer typeDeserializer = (TypeDeserializer) containedTypeOrUnknown2.getTypeHandler();
                if (typeDeserializer == null) {
                    typeDeserializer = findTypeDeserializer(deserializationContext.getConfig(), containedTypeOrUnknown2);
                }
                return new MapEntryDeserializer(javaType, (KeyDeserializer) containedTypeOrUnknown.getValueHandler(), (JsonDeserializer<Object>) (JsonDeserializer) containedTypeOrUnknown2.getValueHandler(), typeDeserializer);
            } else {
                String name = rawClass.getName();
                if (rawClass.isPrimitive() || name.startsWith("java.")) {
                    JsonDeserializer<?> find = NumberDeserializers.find(rawClass, name);
                    if (find == null) {
                        find = DateDeserializers.find(rawClass, name);
                    }
                    if (find != null) {
                        return find;
                    }
                }
                if (rawClass == TokenBuffer.class) {
                    return new TokenBufferDeserializer();
                }
                JsonDeserializer<?> findOptionalStdDeserializer = findOptionalStdDeserializer(deserializationContext, javaType, beanDescription);
                return findOptionalStdDeserializer != null ? findOptionalStdDeserializer : JdkDeserializers.find(deserializationContext, rawClass, name);
            }
        }
    }

    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findDeserializer = annotationIntrospector.findDeserializer(annotated)) == null) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, findDeserializer);
    }

    public KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findKeyDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) == null) {
            return null;
        }
        return deserializationContext.keyDeserializerInstance(annotated, findKeyDeserializer);
    }

    public JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeResolverBuilder<?> findPropertyContentTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        return findPropertyContentTypeResolver == null ? findTypeDeserializer(deserializationConfig, contentType) : findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, contentType));
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeResolverBuilder<?> findPropertyTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType);
        }
        try {
            return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, javaType));
        } catch (IllegalArgumentException | IllegalStateException e3) {
            throw InvalidDefinitionException.from((JsonParser) null, ClassUtil.exceptionMessage(e3), javaType).withCause(e3);
        }
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType mapAbstractType;
        AnnotatedClass classInfo = deserializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder findTypeResolver = deserializationConfig.getAnnotationIntrospector().findTypeResolver(deserializationConfig, classInfo, javaType);
        if (findTypeResolver == null && (findTypeResolver = deserializationConfig.getDefaultTyper(javaType)) == null) {
            return null;
        }
        Collection<NamedType> collectAndResolveSubtypesByTypeId = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, classInfo);
        if (findTypeResolver.getDefaultImpl() == null && javaType.isAbstract() && (mapAbstractType = mapAbstractType(deserializationConfig, javaType)) != null && !mapAbstractType.hasRawClass(javaType.getRawClass())) {
            findTypeResolver = findTypeResolver.withDefaultImpl(mapAbstractType.getRawClass());
        }
        try {
            return findTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, collectAndResolveSubtypesByTypeId);
        } catch (IllegalArgumentException | IllegalStateException e3) {
            throw InvalidDefinitionException.from((JsonParser) null, ClassUtil.exceptionMessage(e3), javaType).withCause(e3);
        }
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotatedClass classInfo = beanDescription.getClassInfo();
        Object findValueInstantiator = deserializationContext.getAnnotationIntrospector().findValueInstantiator(classInfo);
        ValueInstantiator _valueInstantiatorInstance = findValueInstantiator != null ? _valueInstantiatorInstance(config, classInfo, findValueInstantiator) : null;
        if (_valueInstantiatorInstance == null && (_valueInstantiatorInstance = JDKValueInstantiators.findStdValueInstantiator(config, beanDescription.getBeanClass())) == null) {
            _valueInstantiatorInstance = _constructDefaultValueInstantiator(deserializationContext, beanDescription);
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators next : this._factoryConfig.valueInstantiators()) {
                _valueInstantiatorInstance = next.findValueInstantiator(config, beanDescription, _valueInstantiatorInstance);
                if (_valueInstantiatorInstance == null) {
                    deserializationContext.reportBadTypeDefinition(beanDescription, "Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", next.getClass().getName());
                }
            }
        }
        return _valueInstantiatorInstance != null ? _valueInstantiatorInstance.createContextual(deserializationContext, beanDescription) : _valueInstantiatorInstance;
    }

    public DeserializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public boolean hasExplicitDeserializerFor(DeserializationConfig deserializationConfig, Class<?> cls) {
        while (cls.isArray()) {
            cls = cls.getComponentType();
        }
        if (Enum.class.isAssignableFrom(cls)) {
            return true;
        }
        String name = cls.getName();
        if (!name.startsWith("java.")) {
            return name.startsWith("com.fasterxml.") ? JsonNode.class.isAssignableFrom(cls) || cls == TokenBuffer.class : OptionalHandlerFactory.instance.hasDeserializerFor(cls);
        }
        if (!Collection.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
            return Number.class.isAssignableFrom(cls) ? NumberDeserializers.find(cls, name) != null : JdkDeserializers.hasDeserializerFor(cls) || cls == CLASS_STRING || cls == Boolean.class || cls == EnumMap.class || cls == AtomicReference.class || DateDeserializers.hasDeserializerFor(cls);
        }
        return true;
    }

    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType _mapAbstractType2;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (_mapAbstractType2 == null) {
                return javaType;
            }
            Class<?> rawClass = javaType.getRawClass();
            Class<?> rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
            } else {
                javaType = _mapAbstractType2;
            }
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + _mapAbstractType2 + ": latter is not a subtype of former");
    }

    @Deprecated
    public JavaType modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, JavaType javaType) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        return annotationIntrospector == null ? javaType : annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotated, javaType);
    }

    public JavaType resolveMemberAndTypeAnnotations(DeserializationContext deserializationContext, AnnotatedMember annotatedMember, JavaType javaType) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return javaType;
        }
        boolean isMapLikeType = javaType.isMapLikeType();
        MapLikeType mapLikeType = javaType;
        if (isMapLikeType) {
            JavaType keyType = javaType.getKeyType();
            mapLikeType = javaType;
            if (keyType != null) {
                KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotatedMember, annotationIntrospector.findKeyDeserializer(annotatedMember));
                mapLikeType = javaType;
                if (keyDeserializerInstance != null) {
                    MapLikeType withKeyValueHandler = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerInstance);
                    withKeyValueHandler.getKeyType();
                    mapLikeType = withKeyValueHandler;
                }
            }
        }
        boolean hasContentType = mapLikeType.hasContentType();
        JavaType javaType2 = mapLikeType;
        if (hasContentType) {
            JsonDeserializer<Object> deserializerInstance = deserializationContext.deserializerInstance(annotatedMember, annotationIntrospector.findContentDeserializer(annotatedMember));
            JavaType javaType3 = mapLikeType;
            if (deserializerInstance != null) {
                javaType3 = mapLikeType.withContentValueHandler(deserializerInstance);
            }
            TypeDeserializer findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationContext.getConfig(), javaType3, annotatedMember);
            javaType2 = javaType3;
            if (findPropertyContentTypeDeserializer != null) {
                javaType2 = javaType3.withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        TypeDeserializer findPropertyTypeDeserializer = findPropertyTypeDeserializer(deserializationContext.getConfig(), javaType2, annotatedMember);
        JavaType javaType4 = javaType2;
        if (findPropertyTypeDeserializer != null) {
            javaType4 = javaType2.withTypeHandler(findPropertyTypeDeserializer);
        }
        return annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotatedMember, javaType4);
    }

    @Deprecated
    public JavaType resolveType(DeserializationContext deserializationContext, BeanDescription beanDescription, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        return resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, javaType);
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withConfig(this._factoryConfig.withAbstractTypeResolver(abstractTypeResolver));
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers) {
        return withConfig(this._factoryConfig.withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(keyDeserializers));
    }

    public abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withConfig(this._factoryConfig.withDeserializerModifier(beanDeserializerModifier));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withConfig(this._factoryConfig.withValueInstantiators(valueInstantiators));
    }

    public void _addExplicitAnyCreator(DeserializationContext deserializationContext, BeanDescription beanDescription, CreatorCollector creatorCollector, CreatorCandidate creatorCandidate, ConstructorDetector constructorDetector) throws JsonMappingException {
        PropertyName propertyName;
        boolean z2;
        int findOnlyParamWithoutInjection;
        CreatorCollector creatorCollector2 = creatorCollector;
        CreatorCandidate creatorCandidate2 = creatorCandidate;
        if (1 == creatorCandidate.paramCount()) {
            AnnotatedParameter parameter = creatorCandidate2.parameter(0);
            JacksonInject.Value injection = creatorCandidate2.injection(0);
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$databind$cfg$ConstructorDetector$SingleArgConstructor[constructorDetector.singleArgMode().ordinal()];
            if (i3 == 1) {
                DeserializationContext deserializationContext2 = deserializationContext;
                BeanDescription beanDescription2 = beanDescription;
                propertyName = null;
                z2 = false;
            } else if (i3 == 2) {
                DeserializationContext deserializationContext3 = deserializationContext;
                BeanDescription beanDescription3 = beanDescription;
                PropertyName paramName = creatorCandidate2.paramName(0);
                if (paramName == null) {
                    _validateNamedPropertyParameter(deserializationContext, beanDescription, creatorCandidate, 0, paramName, injection);
                }
                z2 = true;
                propertyName = paramName;
            } else if (i3 != 3) {
                BeanPropertyDefinition propertyDef = creatorCandidate2.propertyDef(0);
                PropertyName explicitParamName = creatorCandidate2.explicitParamName(0);
                z2 = (explicitParamName == null && injection == null) ? false : true;
                if (!z2 && propertyDef != null) {
                    explicitParamName = creatorCandidate2.paramName(0);
                    z2 = explicitParamName != null && propertyDef.couldSerialize();
                }
                DeserializationContext deserializationContext4 = deserializationContext;
                BeanDescription beanDescription4 = beanDescription;
                propertyName = explicitParamName;
            } else {
                deserializationContext.reportBadTypeDefinition(beanDescription, "Single-argument constructor (%s) is annotated but no 'mode' defined; `CreatorDetector`configured with `SingleArgConstructor.REQUIRE_MODE`", creatorCandidate.creator());
                return;
            }
            if (z2) {
                creatorCollector2.addPropertyCreator(creatorCandidate.creator(), true, new SettableBeanProperty[]{constructCreatorProperty(deserializationContext, beanDescription, propertyName, 0, parameter, injection)});
                return;
            }
            _handleSingleArgumentCreator(creatorCollector2, creatorCandidate.creator(), true, true);
            BeanPropertyDefinition propertyDef2 = creatorCandidate2.propertyDef(0);
            if (propertyDef2 != null) {
                ((POJOPropertyBuilder) propertyDef2).removeConstructors();
            }
        } else if (constructorDetector.singleArgCreatorDefaultsToProperties() || (findOnlyParamWithoutInjection = creatorCandidate.findOnlyParamWithoutInjection()) < 0 || (!constructorDetector.singleArgCreatorDefaultsToDelegating() && creatorCandidate2.paramName(findOnlyParamWithoutInjection) != null)) {
            _addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate);
        } else {
            _addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate);
        }
    }
}
