package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AccessorNamingStrategy _accessorNaming;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetterField;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMember> _anySetterField;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected boolean _collected;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected Map<PropertyName, PropertyName> _fieldRenameMappings;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMember> _jsonKeyAccessors;
    protected LinkedList<AnnotatedMember> _jsonValueAccessors;
    @Deprecated
    protected String _mutatorPrefix;
    protected LinkedHashMap<String, POJOPropertyBuilder> _properties;
    @Deprecated
    protected final boolean _stdBeanNaming;
    protected final JavaType _type;
    protected final boolean _useAnnotations;
    protected final VisibilityChecker<?> _visibilityChecker;

    public POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z2, JavaType javaType, AnnotatedClass annotatedClass, AccessorNamingStrategy accessorNamingStrategy) {
        this._mutatorPrefix = "set";
        this._config = mapperConfig;
        this._forSerialization = z2;
        this._type = javaType;
        this._classDef = annotatedClass;
        if (mapperConfig.isAnnotationProcessingEnabled()) {
            this._useAnnotations = true;
            this._annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        } else {
            this._useAnnotations = false;
            this._annotationIntrospector = AnnotationIntrospector.nopInstance();
        }
        this._visibilityChecker = mapperConfig.getDefaultVisibilityChecker(javaType.getRawClass(), annotatedClass);
        this._accessorNaming = accessorNamingStrategy;
        this._stdBeanNaming = mapperConfig.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
    }

    private static AccessorNamingStrategy _accessorNaming(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, String str) {
        if (str == null) {
            str = "set";
        }
        return new DefaultAccessorNamingStrategy.Provider().withSetterPrefix(str).forPOJO(mapperConfig, annotatedClass);
    }

    private boolean _anyIndexed(Collection<POJOPropertyBuilder> collection) {
        for (POJOPropertyBuilder metadata : collection) {
            if (metadata.getMetadata().hasIndex()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r0.get(_propNameFromSimple(r2));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String _checkRenameByField(java.lang.String r2) {
        /*
            r1 = this;
            java.util.Map<com.fasterxml.jackson.databind.PropertyName, com.fasterxml.jackson.databind.PropertyName> r0 = r1._fieldRenameMappings
            if (r0 == 0) goto L_0x0015
            com.fasterxml.jackson.databind.PropertyName r1 = r1._propNameFromSimple(r2)
            java.lang.Object r1 = r0.get(r1)
            com.fasterxml.jackson.databind.PropertyName r1 = (com.fasterxml.jackson.databind.PropertyName) r1
            if (r1 == 0) goto L_0x0015
            java.lang.String r1 = r1.getSimpleName()
            return r1
        L_0x0015:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector._checkRenameByField(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r1 = r1.namingStrategyInstance(r4._config, r4._classDef, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.fasterxml.jackson.databind.PropertyNamingStrategy _findNamingStrategy() {
        /*
            r4 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r4._annotationIntrospector
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r4._classDef
            java.lang.Object r0 = r0.findNamingStrategy(r1)
            if (r0 != 0) goto L_0x0011
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r4 = r4._config
            com.fasterxml.jackson.databind.PropertyNamingStrategy r4 = r4.getPropertyNamingStrategy()
            return r4
        L_0x0011:
            boolean r1 = r0 instanceof com.fasterxml.jackson.databind.PropertyNamingStrategy
            if (r1 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.PropertyNamingStrategy r0 = (com.fasterxml.jackson.databind.PropertyNamingStrategy) r0
            return r0
        L_0x0018:
            boolean r1 = r0 instanceof java.lang.Class
            if (r1 == 0) goto L_0x005d
            java.lang.Class r0 = (java.lang.Class) r0
            java.lang.Class<com.fasterxml.jackson.databind.PropertyNamingStrategy> r1 = com.fasterxml.jackson.databind.PropertyNamingStrategy.class
            if (r0 != r1) goto L_0x0024
            r4 = 0
            return r4
        L_0x0024:
            boolean r1 = r1.isAssignableFrom(r0)
            if (r1 == 0) goto L_0x004a
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r1 = r4._config
            com.fasterxml.jackson.databind.cfg.HandlerInstantiator r1 = r1.getHandlerInstantiator()
            if (r1 == 0) goto L_0x003d
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r2 = r4._config
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r4._classDef
            com.fasterxml.jackson.databind.PropertyNamingStrategy r1 = r1.namingStrategyInstance(r2, r3, r0)
            if (r1 == 0) goto L_0x003d
            return r1
        L_0x003d:
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r4 = r4._config
            boolean r4 = r4.canOverrideAccessModifiers()
            java.lang.Object r4 = com.fasterxml.jackson.databind.util.ClassUtil.createInstance(r0, r4)
            com.fasterxml.jackson.databind.PropertyNamingStrategy r4 = (com.fasterxml.jackson.databind.PropertyNamingStrategy) r4
            return r4
        L_0x004a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "AnnotationIntrospector returned Class "
            r1.<init>(r2)
            java.lang.String r2 = "; expected Class<PropertyNamingStrategy>"
            java.lang.String r0 = androidx.constraintlayout.core.state.b.g(r0, r1, r2)
            r4.<init>(r0)
            throw r4
        L_0x005d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "AnnotationIntrospector returned PropertyNamingStrategy definition of type "
            r1.<init>(r2)
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r1.append(r0)
            java.lang.String r0 = "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector._findNamingStrategy():com.fasterxml.jackson.databind.PropertyNamingStrategy");
    }

    private PropertyName _propNameFromSimple(String str) {
        return PropertyName.construct(str, (String) null);
    }

    public void _addCreatorParam(Map<String, POJOPropertyBuilder> map, AnnotatedParameter annotatedParameter) {
        JsonCreator.Mode findCreatorAnnotation;
        String findImplicitPropertyName = this._annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null) {
            findImplicitPropertyName = "";
        }
        PropertyName findNameForDeserialization = this._annotationIntrospector.findNameForDeserialization(annotatedParameter);
        boolean z2 = findNameForDeserialization != null && !findNameForDeserialization.isEmpty();
        if (!z2) {
            if (!findImplicitPropertyName.isEmpty() && (findCreatorAnnotation = this._annotationIntrospector.findCreatorAnnotation(this._config, annotatedParameter.getOwner())) != null && findCreatorAnnotation != JsonCreator.Mode.DISABLED) {
                findNameForDeserialization = PropertyName.construct(findImplicitPropertyName);
            } else {
                return;
            }
        }
        PropertyName propertyName = findNameForDeserialization;
        String _checkRenameByField = _checkRenameByField(findImplicitPropertyName);
        POJOPropertyBuilder _property = (!z2 || !_checkRenameByField.isEmpty()) ? _property(map, _checkRenameByField) : _property(map, propertyName);
        _property.addCtor(annotatedParameter, propertyName, z2, true, false);
        this._creatorProperties.add(_property);
    }

    public void _addCreators(Map<String, POJOPropertyBuilder> map) {
        if (this._useAnnotations) {
            Iterator<AnnotatedConstructor> it = this._classDef.getConstructors().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnnotatedConstructor next = it.next();
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount = next.getParameterCount();
                for (int i3 = 0; i3 < parameterCount; i3++) {
                    _addCreatorParam(map, next.getParameter(i3));
                }
            }
            for (AnnotatedMethod next2 : this._classDef.getFactoryMethods()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount2 = next2.getParameterCount();
                for (int i4 = 0; i4 < parameterCount2; i4++) {
                    _addCreatorParam(map, next2.getParameter(i4));
                }
            }
        }
    }

    public void _addFields(Map<String, POJOPropertyBuilder> map) {
        boolean z2;
        PropertyName propertyName;
        boolean z3;
        boolean z4;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        boolean z5 = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        boolean isEnabled = this._config.isEnabled(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
        for (AnnotatedField next : this._classDef.fields()) {
            Boolean bool = Boolean.TRUE;
            if (bool.equals(annotationIntrospector.hasAsKey(this._config, next))) {
                if (this._jsonKeyAccessors == null) {
                    this._jsonKeyAccessors = new LinkedList<>();
                }
                this._jsonKeyAccessors.add(next);
            }
            if (bool.equals(annotationIntrospector.hasAsValue(next))) {
                if (this._jsonValueAccessors == null) {
                    this._jsonValueAccessors = new LinkedList<>();
                }
                this._jsonValueAccessors.add(next);
            } else {
                boolean equals = bool.equals(annotationIntrospector.hasAnyGetter(next));
                boolean equals2 = bool.equals(annotationIntrospector.hasAnySetter(next));
                if (equals || equals2) {
                    if (equals) {
                        if (this._anyGetterField == null) {
                            this._anyGetterField = new LinkedList<>();
                        }
                        this._anyGetterField.add(next);
                    }
                    if (equals2) {
                        if (this._anySetterField == null) {
                            this._anySetterField = new LinkedList<>();
                        }
                        this._anySetterField.add(next);
                    }
                } else {
                    String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(next);
                    if (findImplicitPropertyName == null) {
                        findImplicitPropertyName = next.getName();
                    }
                    String modifyFieldName = this._accessorNaming.modifyFieldName(next, findImplicitPropertyName);
                    if (modifyFieldName != null) {
                        PropertyName _propNameFromSimple = _propNameFromSimple(modifyFieldName);
                        PropertyName findRenameByField = annotationIntrospector.findRenameByField(this._config, next, _propNameFromSimple);
                        if (findRenameByField != null && !findRenameByField.equals(_propNameFromSimple)) {
                            if (this._fieldRenameMappings == null) {
                                this._fieldRenameMappings = new HashMap();
                            }
                            this._fieldRenameMappings.put(findRenameByField, _propNameFromSimple);
                        }
                        PropertyName findNameForSerialization = this._forSerialization ? annotationIntrospector.findNameForSerialization(next) : annotationIntrospector.findNameForDeserialization(next);
                        boolean z6 = findNameForSerialization != null;
                        if (!z6 || !findNameForSerialization.isEmpty()) {
                            propertyName = findNameForSerialization;
                            z2 = z6;
                        } else {
                            z2 = false;
                            propertyName = _propNameFromSimple(modifyFieldName);
                        }
                        boolean z7 = propertyName != null;
                        if (!z7) {
                            z7 = this._visibilityChecker.isFieldVisible(next);
                        }
                        boolean hasIgnoreMarker = annotationIntrospector.hasIgnoreMarker(next);
                        if (!next.isTransient() || z6) {
                            z3 = hasIgnoreMarker;
                            z4 = z7;
                        } else {
                            z3 = isEnabled ? true : hasIgnoreMarker;
                            z4 = false;
                        }
                        if (!z5 || propertyName != null || z3 || !Modifier.isFinal(next.getModifiers())) {
                            _property(map, modifyFieldName).addField(next, propertyName, z2, z4, z3);
                        }
                    }
                }
            }
        }
    }

    public void _addGetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z2;
        boolean z3;
        PropertyName propertyName;
        String str;
        boolean isGetterVisible;
        Class<?> rawReturnType = annotatedMethod.getRawReturnType();
        if (rawReturnType == Void.TYPE) {
            return;
        }
        if (rawReturnType != Void.class || this._config.isEnabled(MapperFeature.ALLOW_VOID_VALUED_PROPERTIES)) {
            Boolean bool = Boolean.TRUE;
            if (bool.equals(annotationIntrospector.hasAnyGetter(annotatedMethod))) {
                if (this._anyGetters == null) {
                    this._anyGetters = new LinkedList<>();
                }
                this._anyGetters.add(annotatedMethod);
            } else if (bool.equals(annotationIntrospector.hasAsKey(this._config, annotatedMethod))) {
                if (this._jsonKeyAccessors == null) {
                    this._jsonKeyAccessors = new LinkedList<>();
                }
                this._jsonKeyAccessors.add(annotatedMethod);
            } else if (bool.equals(annotationIntrospector.hasAsValue(annotatedMethod))) {
                if (this._jsonValueAccessors == null) {
                    this._jsonValueAccessors = new LinkedList<>();
                }
                this._jsonValueAccessors.add(annotatedMethod);
            } else {
                PropertyName findNameForSerialization = annotationIntrospector.findNameForSerialization(annotatedMethod);
                boolean z4 = false;
                boolean z5 = findNameForSerialization != null;
                if (!z5) {
                    str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                    if (str == null) {
                        str = this._accessorNaming.findNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
                    }
                    if (str == null) {
                        str = this._accessorNaming.findNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                        if (str != null) {
                            isGetterVisible = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                        } else {
                            return;
                        }
                    } else {
                        isGetterVisible = this._visibilityChecker.isGetterVisible(annotatedMethod);
                    }
                    propertyName = findNameForSerialization;
                    z2 = isGetterVisible;
                    z3 = z5;
                } else {
                    String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
                    if (findImplicitPropertyName == null && (findImplicitPropertyName = this._accessorNaming.findNameForRegularGetter(annotatedMethod, annotatedMethod.getName())) == null) {
                        findImplicitPropertyName = this._accessorNaming.findNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                    }
                    if (findImplicitPropertyName == null) {
                        findImplicitPropertyName = annotatedMethod.getName();
                    }
                    if (findNameForSerialization.isEmpty()) {
                        findNameForSerialization = _propNameFromSimple(findImplicitPropertyName);
                    } else {
                        z4 = z5;
                    }
                    propertyName = findNameForSerialization;
                    z3 = z4;
                    z2 = true;
                    str = findImplicitPropertyName;
                }
                _property(map, _checkRenameByField(str)).addGetter(annotatedMethod, propertyName, z3, z2, annotationIntrospector.hasIgnoreMarker(annotatedMethod));
            }
        }
    }

    public void _addInjectables(Map<String, POJOPropertyBuilder> map) {
        for (AnnotatedField next : this._classDef.fields()) {
            _doAddInjectable(this._annotationIntrospector.findInjectableValue(next), next);
        }
        for (AnnotatedMethod next2 : this._classDef.memberMethods()) {
            if (next2.getParameterCount() == 1) {
                _doAddInjectable(this._annotationIntrospector.findInjectableValue(next2), next2);
            }
        }
    }

    public void _addMethods(Map<String, POJOPropertyBuilder> map) {
        for (AnnotatedMethod next : this._classDef.memberMethods()) {
            int parameterCount = next.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(map, next, this._annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(map, next, this._annotationIntrospector);
            } else if (parameterCount == 2 && Boolean.TRUE.equals(this._annotationIntrospector.hasAnySetter(next))) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(next);
            }
        }
    }

    public void _addSetterMethod(Map<String, POJOPropertyBuilder> map, AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z2;
        boolean z3;
        PropertyName propertyName;
        String str;
        PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedMethod);
        boolean z4 = false;
        boolean z5 = findNameForDeserialization != null;
        if (!z5) {
            str = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            if (str == null) {
                str = this._accessorNaming.findNameForMutator(annotatedMethod, annotatedMethod.getName());
            }
            if (str != null) {
                propertyName = findNameForDeserialization;
                z2 = this._visibilityChecker.isSetterVisible(annotatedMethod);
                z3 = z5;
            } else {
                return;
            }
        } else {
            String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedMethod);
            if (findImplicitPropertyName == null) {
                findImplicitPropertyName = this._accessorNaming.findNameForMutator(annotatedMethod, annotatedMethod.getName());
            }
            if (findImplicitPropertyName == null) {
                findImplicitPropertyName = annotatedMethod.getName();
            }
            if (findNameForDeserialization.isEmpty()) {
                findNameForDeserialization = _propNameFromSimple(findImplicitPropertyName);
            } else {
                z4 = z5;
            }
            propertyName = findNameForDeserialization;
            z3 = z4;
            z2 = true;
            str = findImplicitPropertyName;
        }
        _property(map, _checkRenameByField(str)).addSetter(annotatedMethod, propertyName, z3, z2, annotationIntrospector.hasIgnoreMarker(annotatedMethod));
    }

    public void _collectIgnorals(String str) {
        if (!this._forSerialization && str != null) {
            if (this._ignoredPropertyNames == null) {
                this._ignoredPropertyNames = new HashSet<>();
            }
            this._ignoredPropertyNames.add(str);
        }
    }

    public void _doAddInjectable(JacksonInject.Value value, AnnotatedMember annotatedMember) {
        if (value != null) {
            Object id = value.getId();
            if (this._injectables == null) {
                this._injectables = new LinkedHashMap<>();
            }
            AnnotatedMember put = this._injectables.put(id, annotatedMember);
            if (put != null && put.getClass() == annotatedMember.getClass()) {
                String name = id.getClass().getName();
                throw new IllegalArgumentException("Duplicate injectable value with id '" + id + "' (of type " + name + ")");
            }
        }
    }

    public POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, PropertyName propertyName) {
        String simpleName = propertyName.getSimpleName();
        POJOPropertyBuilder pOJOPropertyBuilder = map.get(simpleName);
        if (pOJOPropertyBuilder != null) {
            return pOJOPropertyBuilder;
        }
        POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, propertyName);
        map.put(simpleName, pOJOPropertyBuilder2);
        return pOJOPropertyBuilder2;
    }

    public void _removeUnwantedAccessor(Map<String, POJOPropertyBuilder> map) {
        boolean isEnabled = this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        for (POJOPropertyBuilder removeNonVisible : map.values()) {
            removeNonVisible.removeNonVisible(isEnabled, this._forSerialization ? null : this);
        }
    }

    public void _removeUnwantedProperties(Map<String, POJOPropertyBuilder> map) {
        Iterator<POJOPropertyBuilder> it = map.values().iterator();
        while (it.hasNext()) {
            POJOPropertyBuilder next = it.next();
            if (!next.anyVisible()) {
                it.remove();
            } else if (next.anyIgnorals()) {
                if (!next.anyExplicitsWithoutIgnoral()) {
                    it.remove();
                    _collectIgnorals(next.getName());
                } else {
                    next.removeIgnored();
                    if (!next.couldDeserialize()) {
                        _collectIgnorals(next.getName());
                    }
                }
            }
        }
    }

    public void _renameProperties(Map<String, POJOPropertyBuilder> map) {
        HashSet<String> hashSet;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next().getValue();
            Set<PropertyName> findExplicitNames = pOJOPropertyBuilder.findExplicitNames();
            if (!findExplicitNames.isEmpty()) {
                it.remove();
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                if (findExplicitNames.size() == 1) {
                    linkedList.add(pOJOPropertyBuilder.withName(findExplicitNames.iterator().next()));
                } else {
                    linkedList.addAll(pOJOPropertyBuilder.explode(findExplicitNames));
                }
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = map.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    map.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
                if (_replaceCreatorProperty(pOJOPropertyBuilder2, this._creatorProperties) && (hashSet = this._ignoredPropertyNames) != null) {
                    hashSet.remove(name);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _renameUsing(java.util.Map<java.lang.String, com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder> r9, com.fasterxml.jackson.databind.PropertyNamingStrategy r10) {
        /*
            r8 = this;
            java.util.Collection r0 = r9.values()
            int r1 = r9.size()
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder[] r1 = new com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder[] r0 = (com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder[]) r0
            r9.clear()
            int r1 = r0.length
            r2 = 0
        L_0x0015:
            if (r2 >= r1) goto L_0x00d9
            r3 = r0[r2]
            com.fasterxml.jackson.databind.PropertyName r4 = r3.getFullName()
            boolean r5 = r3.isExplicitlyNamed()
            if (r5 == 0) goto L_0x002d
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.MapperFeature r6 = com.fasterxml.jackson.databind.MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING
            boolean r5 = r5.isEnabled(r6)
            if (r5 == 0) goto L_0x00af
        L_0x002d:
            boolean r5 = r8._forSerialization
            if (r5 == 0) goto L_0x005b
            boolean r5 = r3.hasGetter()
            if (r5 == 0) goto L_0x0046
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r6 = r3.getGetter()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForGetterMethod(r5, r6, r7)
            goto L_0x00b0
        L_0x0046:
            boolean r5 = r3.hasField()
            if (r5 == 0) goto L_0x00af
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedField r6 = r3.getField()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForField(r5, r6, r7)
            goto L_0x00b0
        L_0x005b:
            boolean r5 = r3.hasSetter()
            if (r5 == 0) goto L_0x0070
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r6 = r3.getSetterUnchecked()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForSetterMethod(r5, r6, r7)
            goto L_0x00b0
        L_0x0070:
            boolean r5 = r3.hasConstructorParameter()
            if (r5 == 0) goto L_0x0085
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedParameter r6 = r3.getConstructorParameter()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForConstructorParameter(r5, r6, r7)
            goto L_0x00b0
        L_0x0085:
            boolean r5 = r3.hasField()
            if (r5 == 0) goto L_0x009a
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedField r6 = r3.getFieldUnchecked()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForField(r5, r6, r7)
            goto L_0x00b0
        L_0x009a:
            boolean r5 = r3.hasGetter()
            if (r5 == 0) goto L_0x00af
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r8._config
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r6 = r3.getGetterUnchecked()
            java.lang.String r7 = r4.getSimpleName()
            java.lang.String r5 = r10.nameForGetterMethod(r5, r6, r7)
            goto L_0x00b0
        L_0x00af:
            r5 = 0
        L_0x00b0:
            if (r5 == 0) goto L_0x00bd
            boolean r6 = r4.hasSimpleName(r5)
            if (r6 != 0) goto L_0x00bd
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder r3 = r3.withSimpleName((java.lang.String) r5)
            goto L_0x00c1
        L_0x00bd:
            java.lang.String r5 = r4.getSimpleName()
        L_0x00c1:
            java.lang.Object r4 = r9.get(r5)
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder r4 = (com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder) r4
            if (r4 != 0) goto L_0x00cd
            r9.put(r5, r3)
            goto L_0x00d0
        L_0x00cd:
            r4.addAll(r3)
        L_0x00d0:
            java.util.LinkedList<com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder> r4 = r8._creatorProperties
            r8._replaceCreatorProperty(r3, r4)
            int r2 = r2 + 1
            goto L_0x0015
        L_0x00d9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertiesCollector._renameUsing(java.util.Map, com.fasterxml.jackson.databind.PropertyNamingStrategy):void");
    }

    public void _renameWithWrappers(Map<String, POJOPropertyBuilder> map) {
        PropertyName findWrapperName;
        Iterator<Map.Entry<String, POJOPropertyBuilder>> it = map.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) it.next().getValue();
            AnnotatedMember primaryMember = pOJOPropertyBuilder.getPrimaryMember();
            if (primaryMember != null && (findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember)) != null && findWrapperName.hasSimpleName() && !findWrapperName.equals(pOJOPropertyBuilder.getFullName())) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(pOJOPropertyBuilder.withName(findWrapperName));
                it.remove();
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = map.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    map.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
            }
        }
    }

    public boolean _replaceCreatorProperty(POJOPropertyBuilder pOJOPropertyBuilder, List<POJOPropertyBuilder> list) {
        if (list != null) {
            String internalName = pOJOPropertyBuilder.getInternalName();
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (list.get(i3).getInternalName().equals(internalName)) {
                    list.set(i3, pOJOPropertyBuilder);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean _resolveFieldVsGetter(List<AnnotatedMember> list) {
        do {
            AnnotatedMember annotatedMember = list.get(0);
            AnnotatedMember annotatedMember2 = list.get(1);
            if (!(annotatedMember instanceof AnnotatedField)) {
                if ((annotatedMember instanceof AnnotatedMethod) && (annotatedMember2 instanceof AnnotatedField)) {
                    list.remove(1);
                }
                return false;
            } else if (!(annotatedMember2 instanceof AnnotatedMethod)) {
                return false;
            } else {
                list.remove(0);
            }
        } while (list.size() > 1);
        return true;
    }

    public void _sortProperties(Map<String, POJOPropertyBuilder> map) {
        Collection<POJOPropertyBuilder> collection;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        boolean shouldSortPropertiesAlphabetically = findSerializationSortAlphabetically == null ? this._config.shouldSortPropertiesAlphabetically() : findSerializationSortAlphabetically.booleanValue();
        boolean _anyIndexed = _anyIndexed(map.values());
        String[] findSerializationPropertyOrder = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        if (shouldSortPropertiesAlphabetically || _anyIndexed || this._creatorProperties != null || findSerializationPropertyOrder != null) {
            int size = map.size();
            Map treeMap = shouldSortPropertiesAlphabetically ? new TreeMap() : new LinkedHashMap(size + size);
            for (POJOPropertyBuilder next : map.values()) {
                treeMap.put(next.getName(), next);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(size + size);
            if (findSerializationPropertyOrder != null) {
                for (String str : findSerializationPropertyOrder) {
                    POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) treeMap.remove(str);
                    if (pOJOPropertyBuilder == null) {
                        Iterator<POJOPropertyBuilder> it = map.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            POJOPropertyBuilder next2 = it.next();
                            if (str.equals(next2.getInternalName())) {
                                str = next2.getName();
                                pOJOPropertyBuilder = next2;
                                break;
                            }
                        }
                    }
                    if (pOJOPropertyBuilder != null) {
                        linkedHashMap.put(str, pOJOPropertyBuilder);
                    }
                }
            }
            if (_anyIndexed) {
                TreeMap treeMap2 = new TreeMap();
                Iterator it2 = treeMap.entrySet().iterator();
                while (it2.hasNext()) {
                    POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) ((Map.Entry) it2.next()).getValue();
                    Integer index = pOJOPropertyBuilder2.getMetadata().getIndex();
                    if (index != null) {
                        treeMap2.put(index, pOJOPropertyBuilder2);
                        it2.remove();
                    }
                }
                for (POJOPropertyBuilder pOJOPropertyBuilder3 : treeMap2.values()) {
                    linkedHashMap.put(pOJOPropertyBuilder3.getName(), pOJOPropertyBuilder3);
                }
            }
            if (this._creatorProperties != null && (!shouldSortPropertiesAlphabetically || this._config.isEnabled(MapperFeature.SORT_CREATOR_PROPERTIES_FIRST))) {
                if (shouldSortPropertiesAlphabetically) {
                    TreeMap treeMap3 = new TreeMap();
                    Iterator<POJOPropertyBuilder> it3 = this._creatorProperties.iterator();
                    while (it3.hasNext()) {
                        POJOPropertyBuilder next3 = it3.next();
                        treeMap3.put(next3.getName(), next3);
                    }
                    collection = treeMap3.values();
                } else {
                    collection = this._creatorProperties;
                }
                for (POJOPropertyBuilder pOJOPropertyBuilder4 : collection) {
                    String name = pOJOPropertyBuilder4.getName();
                    if (treeMap.containsKey(name)) {
                        linkedHashMap.put(name, pOJOPropertyBuilder4);
                    }
                }
            }
            linkedHashMap.putAll(treeMap);
            map.clear();
            map.putAll(linkedHashMap);
        }
    }

    @Deprecated
    public void _updateCreatorProperty(POJOPropertyBuilder pOJOPropertyBuilder, List<POJOPropertyBuilder> list) {
        _replaceCreatorProperty(pOJOPropertyBuilder, list);
    }

    public void collectAll() {
        LinkedHashMap<String, POJOPropertyBuilder> linkedHashMap = new LinkedHashMap<>();
        _addFields(linkedHashMap);
        _addMethods(linkedHashMap);
        if (!this._classDef.isNonStaticInnerClass()) {
            _addCreators(linkedHashMap);
        }
        _removeUnwantedProperties(linkedHashMap);
        _removeUnwantedAccessor(linkedHashMap);
        _renameProperties(linkedHashMap);
        _addInjectables(linkedHashMap);
        for (POJOPropertyBuilder mergeAnnotations : linkedHashMap.values()) {
            mergeAnnotations.mergeAnnotations(this._forSerialization);
        }
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(linkedHashMap, _findNamingStrategy);
        }
        for (POJOPropertyBuilder trimByVisibility : linkedHashMap.values()) {
            trimByVisibility.trimByVisibility();
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers(linkedHashMap);
        }
        _sortProperties(linkedHashMap);
        this._properties = linkedHashMap;
        this._collected = true;
    }

    @Deprecated
    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    @Deprecated
    public AnnotatedMember getAnyGetter() {
        return getAnyGetterMethod();
    }

    public AnnotatedMember getAnyGetterField() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anyGetterField;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-getter' fields defined (%s vs %s)", this._anyGetterField.get(0), this._anyGetterField.get(1));
        }
        return this._anyGetterField.getFirst();
    }

    public AnnotatedMember getAnyGetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anyGetters;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-getter' methods defined (%s vs %s)", this._anyGetters.get(0), this._anyGetters.get(1));
        }
        return this._anyGetters.getFirst();
    }

    public AnnotatedMember getAnySetterField() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._anySetterField;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-setter' fields defined (%s vs %s)", this._anySetterField.get(0), this._anySetterField.get(1));
        }
        return this._anySetterField.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMethod> linkedList = this._anySetters;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1) {
            reportProblem("Multiple 'any-setter' methods defined (%s vs %s)", this._anySetters.get(0), this._anySetters.get(1));
        }
        return this._anySetters.getFirst();
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        if (!this._collected) {
            collectAll();
        }
        return this._injectables;
    }

    public AnnotatedMember getJsonKeyAccessor() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._jsonKeyAccessors;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1 && !_resolveFieldVsGetter(this._jsonKeyAccessors)) {
            reportProblem("Multiple 'as-key' properties defined (%s vs %s)", this._jsonKeyAccessors.get(0), this._jsonKeyAccessors.get(1));
        }
        return this._jsonKeyAccessors.get(0);
    }

    public AnnotatedMember getJsonValueAccessor() {
        if (!this._collected) {
            collectAll();
        }
        LinkedList<AnnotatedMember> linkedList = this._jsonValueAccessors;
        if (linkedList == null) {
            return null;
        }
        if (linkedList.size() > 1 && !_resolveFieldVsGetter(this._jsonValueAccessors)) {
            reportProblem("Multiple 'as-value' properties defined (%s vs %s)", this._jsonValueAccessors.get(0), this._jsonValueAccessors.get(1));
        }
        return this._jsonValueAccessors.get(0);
    }

    @Deprecated
    public AnnotatedMethod getJsonValueMethod() {
        AnnotatedMember jsonValueAccessor = getJsonValueAccessor();
        if (jsonValueAccessor instanceof AnnotatedMethod) {
            return (AnnotatedMethod) jsonValueAccessor;
        }
        return null;
    }

    public ObjectIdInfo getObjectIdInfo() {
        ObjectIdInfo findObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        return findObjectIdInfo != null ? this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo) : findObjectIdInfo;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(getPropertyMap().values());
    }

    public Map<String, POJOPropertyBuilder> getPropertyMap() {
        if (!this._collected) {
            collectAll();
        }
        return this._properties;
    }

    public JavaType getType() {
        return this._type;
    }

    public void reportProblem(String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + str);
    }

    public POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> map, String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = map.get(str);
        if (pOJOPropertyBuilder != null) {
            return pOJOPropertyBuilder;
        }
        POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, PropertyName.construct(str));
        map.put(str, pOJOPropertyBuilder2);
        return pOJOPropertyBuilder2;
    }

    @Deprecated
    public POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z2, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        this(mapperConfig, z2, javaType, annotatedClass, _accessorNaming(mapperConfig, annotatedClass, str));
        this._mutatorPrefix = str;
    }
}
