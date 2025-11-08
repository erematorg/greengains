package com.fasterxml.jackson.databind.introspect;

import A.a;
import U1.g;
import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    private static final AnnotationIntrospector.ReferenceProperty NOT_REFEFERENCE_PROP = AnnotationIntrospector.ReferenceProperty.managed("");
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final MapperConfig<?> _config;
    protected Linked<AnnotatedParameter> _ctorParameters;
    protected Linked<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected Linked<AnnotatedMethod> _getters;
    protected final PropertyName _internalName;
    protected transient PropertyMetadata _metadata;
    protected final PropertyName _name;
    protected transient AnnotationIntrospector.ReferenceProperty _referenceInfo;
    protected Linked<AnnotatedMethod> _setters;

    /* renamed from: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$6  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonProperty$Access[] r0 = com.fasterxml.jackson.annotation.JsonProperty.Access.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access = r0
                com.fasterxml.jackson.annotation.JsonProperty$Access r1 = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonProperty$Access r1 = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_WRITE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonProperty$Access r1 = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.annotation.JsonProperty$Access r1 = com.fasterxml.jackson.annotation.JsonProperty.Access.AUTO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.AnonymousClass6.<clinit>():void");
        }
    }

    public static final class Linked<T> {
        public final boolean isMarkedIgnored;
        public final boolean isNameExplicit;
        public final boolean isVisible;
        public final PropertyName name;
        public final Linked<T> next;
        public final T value;

        public Linked(T t2, Linked<T> linked, PropertyName propertyName, boolean z2, boolean z3, boolean z4) {
            this.value = t2;
            this.next = linked;
            PropertyName propertyName2 = (propertyName == null || propertyName.isEmpty()) ? null : propertyName;
            this.name = propertyName2;
            if (z2) {
                if (propertyName2 == null) {
                    throw new IllegalArgumentException("Cannot pass true for 'explName' if name is null/empty");
                } else if (!propertyName.hasSimpleName()) {
                    z2 = false;
                }
            }
            this.isNameExplicit = z2;
            this.isVisible = z3;
            this.isMarkedIgnored = z4;
        }

        public Linked<T> append(Linked<T> linked) {
            Linked<T> linked2 = this.next;
            return linked2 == null ? withNext(linked) : withNext(linked2.append(linked));
        }

        public String toString() {
            String str = this.value.toString() + "[visible=" + this.isVisible + ",ignore=" + this.isMarkedIgnored + ",explicitName=" + this.isNameExplicit + "]";
            if (this.next == null) {
                return str;
            }
            StringBuilder q2 = a.q(str, ", ");
            q2.append(this.next.toString());
            return q2.toString();
        }

        public Linked<T> trimByVisibility() {
            Linked<T> linked = this.next;
            if (linked == null) {
                return this;
            }
            Linked<T> trimByVisibility = linked.trimByVisibility();
            if (this.name != null) {
                return trimByVisibility.name == null ? withNext((Linked) null) : withNext(trimByVisibility);
            }
            if (trimByVisibility.name != null) {
                return trimByVisibility;
            }
            boolean z2 = this.isVisible;
            return z2 == trimByVisibility.isVisible ? withNext(trimByVisibility) : z2 ? withNext((Linked) null) : trimByVisibility;
        }

        public Linked<T> withNext(Linked<T> linked) {
            if (linked == this.next) {
                return this;
            }
            return new Linked(this.value, linked, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withValue(T t2) {
            if (t2 == this.value) {
                return this;
            }
            return new Linked(t2, this.next, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
            r0 = r0.withoutIgnored();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked<T> withoutIgnored() {
            /*
                r2 = this;
                boolean r0 = r2.isMarkedIgnored
                if (r0 == 0) goto L_0x000f
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<T> r2 = r2.next
                if (r2 != 0) goto L_0x000a
                r2 = 0
                goto L_0x000e
            L_0x000a:
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked r2 = r2.withoutIgnored()
            L_0x000e:
                return r2
            L_0x000f:
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<T> r0 = r2.next
                if (r0 == 0) goto L_0x001f
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked r0 = r0.withoutIgnored()
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<T> r1 = r2.next
                if (r0 == r1) goto L_0x001f
                com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked r2 = r2.withNext(r0)
            L_0x001f:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked.withoutIgnored():com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked");
        }

        public Linked<T> withoutNext() {
            return this.next == null ? this : new Linked(this.value, (Linked) null, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
        }

        public Linked<T> withoutNonVisible() {
            Linked<T> linked = this.next;
            Linked<T> withoutNonVisible = linked == null ? null : linked.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }
    }

    public static class MemberIterator<T extends AnnotatedMember> implements Iterator<T> {
        private Linked<T> next;

        public MemberIterator(Linked<T> linked) {
            this.next = linked;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            Linked<T> linked = this.next;
            if (linked != null) {
                T t2 = (AnnotatedMember) linked.value;
                this.next = linked.next;
                return t2;
            }
            throw new NoSuchElementException();
        }
    }

    public interface WithMember<T> {
        T withMember(AnnotatedMember annotatedMember);
    }

    public POJOPropertyBuilder(MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, boolean z2, PropertyName propertyName) {
        this(mapperConfig, annotationIntrospector, z2, propertyName, propertyName);
    }

    private <T> boolean _anyExplicitNames(Linked<T> linked) {
        while (linked != null) {
            if (linked.name != null && linked.isNameExplicit) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyExplicitNamesWithoutIgnoral(Linked<T> linked) {
        while (linked != null) {
            if (!linked.isMarkedIgnored && linked.name != null && linked.isNameExplicit) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyExplicits(Linked<T> linked) {
        while (linked != null) {
            PropertyName propertyName = linked.name;
            if (propertyName != null && propertyName.hasSimpleName()) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyExplicitsWithoutIgnoral(Linked<T> linked) {
        PropertyName propertyName;
        while (linked != null) {
            if (!linked.isMarkedIgnored && (propertyName = linked.name) != null && propertyName.hasSimpleName()) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyIgnorals(Linked<T> linked) {
        while (linked != null) {
            if (linked.isMarkedIgnored) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T> boolean _anyVisible(Linked<T> linked) {
        while (linked != null) {
            if (linked.isVisible) {
                return true;
            }
            linked = linked.next;
        }
        return false;
    }

    private <T extends AnnotatedMember> Linked<T> _applyAnnotations(Linked<T> linked, AnnotationMap annotationMap) {
        AnnotatedMember annotatedMember = (AnnotatedMember) ((AnnotatedMember) linked.value).withAnnotations(annotationMap);
        Linked<T> linked2 = linked.next;
        if (linked2 != null) {
            linked = linked.withNext(_applyAnnotations(linked2, annotationMap));
        }
        return linked.withValue(annotatedMember);
    }

    private void _explode(Collection<PropertyName> collection, Map<PropertyName, POJOPropertyBuilder> map, Linked<?> linked) {
        for (Linked linked2 = linked; linked2 != null; linked2 = linked2.next) {
            PropertyName propertyName = linked2.name;
            if (linked2.isNameExplicit && propertyName != null) {
                POJOPropertyBuilder pOJOPropertyBuilder = map.get(propertyName);
                if (pOJOPropertyBuilder == null) {
                    pOJOPropertyBuilder = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, this._internalName, propertyName);
                    map.put(propertyName, pOJOPropertyBuilder);
                }
                if (linked == this._fields) {
                    pOJOPropertyBuilder._fields = linked2.withNext(pOJOPropertyBuilder._fields);
                } else if (linked == this._getters) {
                    pOJOPropertyBuilder._getters = linked2.withNext(pOJOPropertyBuilder._getters);
                } else if (linked == this._setters) {
                    pOJOPropertyBuilder._setters = linked2.withNext(pOJOPropertyBuilder._setters);
                } else if (linked == this._ctorParameters) {
                    pOJOPropertyBuilder._ctorParameters = linked2.withNext(pOJOPropertyBuilder._ctorParameters);
                } else {
                    throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
                }
            } else if (linked2.isVisible) {
                throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name " + ClassUtil.name(this._name) + "): found multiple explicit names: " + collection + ", but also implicit accessor: " + linked2);
            }
        }
    }

    private Set<PropertyName> _findExplicitNames(Linked<? extends AnnotatedMember> linked, Set<PropertyName> set) {
        Linked<T> linked2;
        while (linked2 != null) {
            if (linked2.isNameExplicit && linked2.name != null) {
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(linked2.name);
            }
            Linked<T> linked3 = linked2.next;
            linked2 = linked;
            linked2 = linked3;
        }
        return set;
    }

    private <T extends AnnotatedMember> AnnotationMap _getAllAnnotations(Linked<T> linked) {
        AnnotationMap allAnnotations = ((AnnotatedMember) linked.value).getAllAnnotations();
        Linked<T> linked2 = linked.next;
        return linked2 != null ? AnnotationMap.merge(allAnnotations, _getAllAnnotations(linked2)) : allAnnotations;
    }

    private AnnotationMap _mergeAnnotations(int i3, Linked<? extends AnnotatedMember>... linkedArr) {
        AnnotationMap _getAllAnnotations = _getAllAnnotations(linkedArr[i3]);
        do {
            i3++;
            if (i3 >= linkedArr.length) {
                return _getAllAnnotations;
            }
        } while (linkedArr[i3] == null);
        return AnnotationMap.merge(_getAllAnnotations, _mergeAnnotations(i3, linkedArr));
    }

    private <T> Linked<T> _removeIgnored(Linked<T> linked) {
        return linked == null ? linked : linked.withoutIgnored();
    }

    private <T> Linked<T> _removeNonVisible(Linked<T> linked) {
        return linked == null ? linked : linked.withoutNonVisible();
    }

    private <T> Linked<T> _trimByVisibility(Linked<T> linked) {
        return linked == null ? linked : linked.trimByVisibility();
    }

    private static <T> Linked<T> merge(Linked<T> linked, Linked<T> linked2) {
        return linked == null ? linked2 : linked2 == null ? linked : linked.append(linked2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyMetadata _getSetterInfo(com.fasterxml.jackson.databind.PropertyMetadata r7, com.fasterxml.jackson.databind.introspect.AnnotatedMember r8) {
        /*
            r6 = this;
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r6.getAccessor()
            r1 = 1
            r2 = 0
            if (r8 == 0) goto L_0x0072
            com.fasterxml.jackson.databind.AnnotationIntrospector r3 = r6._annotationIntrospector
            r4 = 0
            if (r3 == 0) goto L_0x0035
            if (r0 == 0) goto L_0x0024
            java.lang.Boolean r3 = r3.findMergeInfo(r8)
            if (r3 == 0) goto L_0x0024
            boolean r1 = r3.booleanValue()
            if (r1 == 0) goto L_0x0023
            com.fasterxml.jackson.databind.PropertyMetadata$MergeInfo r1 = com.fasterxml.jackson.databind.PropertyMetadata.MergeInfo.createForPropertyOverride(r0)
            com.fasterxml.jackson.databind.PropertyMetadata r7 = r7.withMergeInfo(r1)
        L_0x0023:
            r1 = r4
        L_0x0024:
            com.fasterxml.jackson.databind.AnnotationIntrospector r3 = r6._annotationIntrospector
            com.fasterxml.jackson.annotation.JsonSetter$Value r3 = r3.findSetterInfo(r8)
            if (r3 == 0) goto L_0x0035
            com.fasterxml.jackson.annotation.Nulls r2 = r3.nonDefaultValueNulls()
            com.fasterxml.jackson.annotation.Nulls r3 = r3.nonDefaultContentNulls()
            goto L_0x0036
        L_0x0035:
            r3 = r2
        L_0x0036:
            if (r1 != 0) goto L_0x003c
            if (r2 == 0) goto L_0x003c
            if (r3 != 0) goto L_0x0073
        L_0x003c:
            java.lang.Class r8 = r6._rawTypeOf(r8)
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r5 = r6._config
            com.fasterxml.jackson.databind.cfg.ConfigOverride r8 = r5.getConfigOverride(r8)
            com.fasterxml.jackson.annotation.JsonSetter$Value r5 = r8.getSetterInfo()
            if (r5 == 0) goto L_0x0058
            if (r2 != 0) goto L_0x0052
            com.fasterxml.jackson.annotation.Nulls r2 = r5.nonDefaultValueNulls()
        L_0x0052:
            if (r3 != 0) goto L_0x0058
            com.fasterxml.jackson.annotation.Nulls r3 = r5.nonDefaultContentNulls()
        L_0x0058:
            if (r1 == 0) goto L_0x0073
            if (r0 == 0) goto L_0x0073
            java.lang.Boolean r8 = r8.getMergeable()
            if (r8 == 0) goto L_0x0073
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x0070
            com.fasterxml.jackson.databind.PropertyMetadata$MergeInfo r8 = com.fasterxml.jackson.databind.PropertyMetadata.MergeInfo.createForTypeOverride(r0)
            com.fasterxml.jackson.databind.PropertyMetadata r7 = r7.withMergeInfo(r8)
        L_0x0070:
            r1 = r4
            goto L_0x0073
        L_0x0072:
            r3 = r2
        L_0x0073:
            if (r1 != 0) goto L_0x0079
            if (r2 == 0) goto L_0x0079
            if (r3 != 0) goto L_0x00a5
        L_0x0079:
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r8 = r6._config
            com.fasterxml.jackson.annotation.JsonSetter$Value r8 = r8.getDefaultSetterInfo()
            if (r2 != 0) goto L_0x0085
            com.fasterxml.jackson.annotation.Nulls r2 = r8.nonDefaultValueNulls()
        L_0x0085:
            if (r3 != 0) goto L_0x008b
            com.fasterxml.jackson.annotation.Nulls r3 = r8.nonDefaultContentNulls()
        L_0x008b:
            if (r1 == 0) goto L_0x00a5
            com.fasterxml.jackson.databind.cfg.MapperConfig<?> r6 = r6._config
            java.lang.Boolean r6 = r6.getDefaultMergeable()
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00a5
            if (r0 == 0) goto L_0x00a5
            com.fasterxml.jackson.databind.PropertyMetadata$MergeInfo r6 = com.fasterxml.jackson.databind.PropertyMetadata.MergeInfo.createForDefaults(r0)
            com.fasterxml.jackson.databind.PropertyMetadata r7 = r7.withMergeInfo(r6)
        L_0x00a5:
            if (r2 != 0) goto L_0x00a9
            if (r3 == 0) goto L_0x00ad
        L_0x00a9:
            com.fasterxml.jackson.databind.PropertyMetadata r7 = r7.withNulls(r2, r3)
        L_0x00ad:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder._getSetterInfo(com.fasterxml.jackson.databind.PropertyMetadata, com.fasterxml.jackson.databind.introspect.AnnotatedMember):com.fasterxml.jackson.databind.PropertyMetadata");
    }

    public int _getterPriority(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        if (!name.startsWith("get") || name.length() <= 3) {
            return (!name.startsWith("is") || name.length() <= 2) ? 3 : 2;
        }
        return 1;
    }

    public Class<?> _rawTypeOf(AnnotatedMember annotatedMember) {
        if (annotatedMember instanceof AnnotatedMethod) {
            AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
            if (annotatedMethod.getParameterCount() > 0) {
                return annotatedMethod.getParameterType(0).getRawClass();
            }
        }
        return annotatedMember.getType().getRawClass();
    }

    public AnnotatedMethod _selectSetter(AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2) {
        Class<?> declaringClass = annotatedMethod.getDeclaringClass();
        Class<?> declaringClass2 = annotatedMethod2.getDeclaringClass();
        if (declaringClass != declaringClass2) {
            if (declaringClass.isAssignableFrom(declaringClass2)) {
                return annotatedMethod2;
            }
            if (declaringClass2.isAssignableFrom(declaringClass)) {
                return annotatedMethod;
            }
        }
        int _setterPriority = _setterPriority(annotatedMethod2);
        int _setterPriority2 = _setterPriority(annotatedMethod);
        if (_setterPriority != _setterPriority2) {
            return _setterPriority < _setterPriority2 ? annotatedMethod2 : annotatedMethod;
        }
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.resolveSetterConflict(this._config, annotatedMethod, annotatedMethod2);
    }

    public AnnotatedMethod _selectSetterFromMultiple(Linked<AnnotatedMethod> linked, Linked<AnnotatedMethod> linked2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(linked.value);
        arrayList.add(linked2.value);
        for (Linked<T> linked3 = linked2.next; linked3 != null; linked3 = linked3.next) {
            T _selectSetter = _selectSetter((AnnotatedMethod) linked.value, (AnnotatedMethod) linked3.value);
            if (_selectSetter != linked.value) {
                T t2 = linked3.value;
                if (_selectSetter == t2) {
                    arrayList.clear();
                    linked = linked3;
                } else {
                    arrayList.add(t2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            this._setters = linked.withoutNext();
            return (AnnotatedMethod) linked.value;
        }
        throw new IllegalArgumentException(C0118y.f("Conflicting setter definitions for property \"", getName(), "\": ", (String) arrayList.stream().map(new g(5)).collect(Collectors.joining(" vs "))));
    }

    public int _setterPriority(AnnotatedMethod annotatedMethod) {
        String name = annotatedMethod.getName();
        return (!name.startsWith("set") || name.length() <= 3) ? 2 : 1;
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, PropertyName propertyName, boolean z2, boolean z3, boolean z4) {
        this._ctorParameters = new Linked(annotatedParameter, this._ctorParameters, propertyName, z2, z3, z4);
    }

    public void addField(AnnotatedField annotatedField, PropertyName propertyName, boolean z2, boolean z3, boolean z4) {
        this._fields = new Linked(annotatedField, this._fields, propertyName, z2, z3, z4);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, PropertyName propertyName, boolean z2, boolean z3, boolean z4) {
        this._getters = new Linked(annotatedMethod, this._getters, propertyName, z2, z3, z4);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, PropertyName propertyName, boolean z2, boolean z3, boolean z4) {
        this._setters = new Linked(annotatedMethod, this._setters, propertyName, z2, z3, z4);
    }

    public boolean anyExplicitsWithoutIgnoral() {
        return _anyExplicitsWithoutIgnoral(this._fields) || _anyExplicitsWithoutIgnoral(this._getters) || _anyExplicitsWithoutIgnoral(this._setters) || _anyExplicitNamesWithoutIgnoral(this._ctorParameters);
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    public boolean couldDeserialize() {
        return (this._ctorParameters == null && this._setters == null && this._fields == null) ? false : true;
    }

    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public Collection<POJOPropertyBuilder> explode(Collection<PropertyName> collection) {
        HashMap hashMap = new HashMap();
        _explode(collection, hashMap, this._fields);
        _explode(collection, hashMap, this._getters);
        _explode(collection, hashMap, this._setters);
        _explode(collection, hashMap, this._ctorParameters);
        return hashMap.values();
    }

    public JsonProperty.Access findAccess() {
        return (JsonProperty.Access) fromMemberAnnotationsExcept(new WithMember<JsonProperty.Access>() {
            public JsonProperty.Access withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findPropertyAccess(annotatedMember);
            }
        }, JsonProperty.Access.AUTO);
    }

    public Set<PropertyName> findExplicitNames() {
        Set<PropertyName> _findExplicitNames = _findExplicitNames(this._ctorParameters, _findExplicitNames(this._setters, _findExplicitNames(this._getters, _findExplicitNames(this._fields, (Set<PropertyName>) null))));
        return _findExplicitNames == null ? Collections.emptySet() : _findExplicitNames;
    }

    public JsonInclude.Value findInclusion() {
        AnnotatedMember accessor = getAccessor();
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        JsonInclude.Value findPropertyInclusion = annotationIntrospector == null ? null : annotationIntrospector.findPropertyInclusion(accessor);
        return findPropertyInclusion == null ? JsonInclude.Value.empty() : findPropertyInclusion;
    }

    public ObjectIdInfo findObjectIdInfo() {
        return (ObjectIdInfo) fromMemberAnnotations(new WithMember<ObjectIdInfo>() {
            public ObjectIdInfo withMember(AnnotatedMember annotatedMember) {
                ObjectIdInfo findObjectIdInfo = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(annotatedMember);
                return findObjectIdInfo != null ? POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(annotatedMember, findObjectIdInfo) : findObjectIdInfo;
            }
        });
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType() {
        AnnotationIntrospector.ReferenceProperty referenceProperty = this._referenceInfo;
        if (referenceProperty == null) {
            AnnotationIntrospector.ReferenceProperty referenceProperty2 = (AnnotationIntrospector.ReferenceProperty) fromMemberAnnotations(new WithMember<AnnotationIntrospector.ReferenceProperty>() {
                public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember annotatedMember) {
                    return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(annotatedMember);
                }
            });
            this._referenceInfo = referenceProperty2 == null ? NOT_REFEFERENCE_PROP : referenceProperty2;
            return referenceProperty2;
        } else if (referenceProperty == NOT_REFEFERENCE_PROP) {
            return null;
        } else {
            return referenceProperty;
        }
    }

    public Class<?>[] findViews() {
        return (Class[]) fromMemberAnnotations(new WithMember<Class<?>[]>() {
            public Class<?>[] withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.findViews(annotatedMember);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r2 = r2._fields;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T fromMemberAnnotations(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.WithMember<T> r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            r1 = 0
            if (r0 == 0) goto L_0x003e
            boolean r0 = r2._forSerialization
            if (r0 == 0) goto L_0x0016
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._getters
            if (r0 == 0) goto L_0x0030
            T r0 = r0.value
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r1 = r3.withMember(r0)
            goto L_0x0030
        L_0x0016:
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<com.fasterxml.jackson.databind.introspect.AnnotatedParameter> r0 = r2._ctorParameters
            if (r0 == 0) goto L_0x0022
            T r0 = r0.value
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r1 = r3.withMember(r0)
        L_0x0022:
            if (r1 != 0) goto L_0x0030
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<com.fasterxml.jackson.databind.introspect.AnnotatedMethod> r0 = r2._setters
            if (r0 == 0) goto L_0x0030
            T r0 = r0.value
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r0
            java.lang.Object r1 = r3.withMember(r0)
        L_0x0030:
            if (r1 != 0) goto L_0x003e
            com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<com.fasterxml.jackson.databind.introspect.AnnotatedField> r2 = r2._fields
            if (r2 == 0) goto L_0x003e
            T r2 = r2.value
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r2
            java.lang.Object r1 = r3.withMember(r2)
        L_0x003e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.fromMemberAnnotations(com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$WithMember):java.lang.Object");
    }

    public <T> T fromMemberAnnotationsExcept(WithMember<T> withMember, T t2) {
        T withMember2;
        T withMember3;
        T withMember4;
        T withMember5;
        T withMember6;
        T withMember7;
        T withMember8;
        T withMember9;
        if (this._annotationIntrospector == null) {
            return null;
        }
        if (this._forSerialization) {
            Linked<AnnotatedMethod> linked = this._getters;
            if (linked != null && (withMember9 = withMember.withMember((AnnotatedMember) linked.value)) != null && withMember9 != t2) {
                return withMember9;
            }
            Linked<AnnotatedField> linked2 = this._fields;
            if (linked2 != null && (withMember8 = withMember.withMember((AnnotatedMember) linked2.value)) != null && withMember8 != t2) {
                return withMember8;
            }
            Linked<AnnotatedParameter> linked3 = this._ctorParameters;
            if (linked3 != null && (withMember7 = withMember.withMember((AnnotatedMember) linked3.value)) != null && withMember7 != t2) {
                return withMember7;
            }
            Linked<AnnotatedMethod> linked4 = this._setters;
            if (linked4 == null || (withMember6 = withMember.withMember((AnnotatedMember) linked4.value)) == null || withMember6 == t2) {
                return null;
            }
            return withMember6;
        }
        Linked<AnnotatedParameter> linked5 = this._ctorParameters;
        if (linked5 != null && (withMember5 = withMember.withMember((AnnotatedMember) linked5.value)) != null && withMember5 != t2) {
            return withMember5;
        }
        Linked<AnnotatedMethod> linked6 = this._setters;
        if (linked6 != null && (withMember4 = withMember.withMember((AnnotatedMember) linked6.value)) != null && withMember4 != t2) {
            return withMember4;
        }
        Linked<AnnotatedField> linked7 = this._fields;
        if (linked7 != null && (withMember3 = withMember.withMember((AnnotatedMember) linked7.value)) != null && withMember3 != t2) {
            return withMember3;
        }
        Linked<AnnotatedMethod> linked8 = this._getters;
        if (linked8 == null || (withMember2 = withMember.withMember((AnnotatedMember) linked8.value)) == null || withMember2 == t2) {
            return null;
        }
        return withMember2;
    }

    public AnnotatedParameter getConstructorParameter() {
        Linked linked = this._ctorParameters;
        if (linked == null) {
            return null;
        }
        while (!(((AnnotatedParameter) linked.value).getOwner() instanceof AnnotatedConstructor)) {
            linked = linked.next;
            if (linked == null) {
                return (AnnotatedParameter) this._ctorParameters.value;
            }
        }
        return (AnnotatedParameter) linked.value;
    }

    public Iterator<AnnotatedParameter> getConstructorParameters() {
        Linked<AnnotatedParameter> linked = this._ctorParameters;
        return linked == null ? ClassUtil.emptyIterator() : new MemberIterator(linked);
    }

    public AnnotatedField getField() {
        Linked<AnnotatedField> linked = this._fields;
        if (linked == null) {
            return null;
        }
        AnnotatedField annotatedField = (AnnotatedField) linked.value;
        Linked<T> linked2 = linked.next;
        while (linked2 != null) {
            AnnotatedField annotatedField2 = (AnnotatedField) linked2.value;
            Class<?> declaringClass = annotatedField.getDeclaringClass();
            Class<?> declaringClass2 = annotatedField2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedField = annotatedField2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
                linked2 = linked2.next;
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField.getFullName() + " vs " + annotatedField2.getFullName());
        }
        return annotatedField;
    }

    public AnnotatedField getFieldUnchecked() {
        Linked<AnnotatedField> linked = this._fields;
        if (linked == null) {
            return null;
        }
        return (AnnotatedField) linked.value;
    }

    public PropertyName getFullName() {
        return this._name;
    }

    public AnnotatedMethod getGetter() {
        Linked<AnnotatedMethod> linked = this._getters;
        if (linked == null) {
            return null;
        }
        Linked<T> linked2 = linked.next;
        if (linked2 == null) {
            return (AnnotatedMethod) linked.value;
        }
        while (linked2 != null) {
            Class<?> declaringClass = ((AnnotatedMethod) linked.value).getDeclaringClass();
            Class<?> declaringClass2 = ((AnnotatedMethod) linked2.value).getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        continue;
                        linked2 = linked2.next;
                    }
                }
                linked = linked2;
                linked2 = linked2.next;
            }
            int _getterPriority = _getterPriority((AnnotatedMethod) linked2.value);
            int _getterPriority2 = _getterPriority((AnnotatedMethod) linked.value);
            if (_getterPriority != _getterPriority2) {
                if (_getterPriority >= _getterPriority2) {
                    linked2 = linked2.next;
                }
                linked = linked2;
                linked2 = linked2.next;
            } else {
                throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod) linked.value).getFullName() + " vs " + ((AnnotatedMethod) linked2.value).getFullName());
            }
        }
        this._getters = linked.withoutNext();
        return (AnnotatedMethod) linked.value;
    }

    public AnnotatedMethod getGetterUnchecked() {
        Linked<AnnotatedMethod> linked = this._getters;
        if (linked == null) {
            return null;
        }
        return (AnnotatedMethod) linked.value;
    }

    public String getInternalName() {
        return this._internalName.getSimpleName();
    }

    public PropertyMetadata getMetadata() {
        if (this._metadata == null) {
            AnnotatedMember primaryMemberUnchecked = getPrimaryMemberUnchecked();
            if (primaryMemberUnchecked == null) {
                this._metadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
            } else {
                Boolean hasRequiredMarker = this._annotationIntrospector.hasRequiredMarker(primaryMemberUnchecked);
                String findPropertyDescription = this._annotationIntrospector.findPropertyDescription(primaryMemberUnchecked);
                Integer findPropertyIndex = this._annotationIntrospector.findPropertyIndex(primaryMemberUnchecked);
                String findPropertyDefaultValue = this._annotationIntrospector.findPropertyDefaultValue(primaryMemberUnchecked);
                if (hasRequiredMarker == null && findPropertyIndex == null && findPropertyDefaultValue == null) {
                    PropertyMetadata propertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
                    if (findPropertyDescription != null) {
                        propertyMetadata = propertyMetadata.withDescription(findPropertyDescription);
                    }
                    this._metadata = propertyMetadata;
                } else {
                    this._metadata = PropertyMetadata.construct(hasRequiredMarker, findPropertyDescription, findPropertyIndex, findPropertyDefaultValue);
                }
                if (!this._forSerialization) {
                    this._metadata = _getSetterInfo(this._metadata, primaryMemberUnchecked);
                }
            }
        }
        return this._metadata;
    }

    public String getName() {
        PropertyName propertyName = this._name;
        if (propertyName == null) {
            return null;
        }
        return propertyName.getSimpleName();
    }

    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        AnnotatedMember mutator = getMutator();
        return mutator == null ? getAccessor() : mutator;
    }

    public AnnotatedMember getPrimaryMemberUnchecked() {
        if (this._forSerialization) {
            Linked<AnnotatedMethod> linked = this._getters;
            if (linked != null) {
                return (AnnotatedMember) linked.value;
            }
            Linked<AnnotatedField> linked2 = this._fields;
            if (linked2 != null) {
                return (AnnotatedMember) linked2.value;
            }
            return null;
        }
        Linked<AnnotatedParameter> linked3 = this._ctorParameters;
        if (linked3 != null) {
            return (AnnotatedMember) linked3.value;
        }
        Linked<AnnotatedMethod> linked4 = this._setters;
        if (linked4 != null) {
            return (AnnotatedMember) linked4.value;
        }
        Linked<AnnotatedField> linked5 = this._fields;
        if (linked5 != null) {
            return (AnnotatedMember) linked5.value;
        }
        Linked<AnnotatedMethod> linked6 = this._getters;
        if (linked6 != null) {
            return (AnnotatedMember) linked6.value;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = getField();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType getPrimaryType() {
        /*
            r1 = this;
            boolean r0 = r1._forSerialization
            if (r0 == 0) goto L_0x001a
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = r1.getGetter()
            if (r0 != 0) goto L_0x0015
            com.fasterxml.jackson.databind.introspect.AnnotatedField r0 = r1.getField()
            if (r0 != 0) goto L_0x0015
            com.fasterxml.jackson.databind.JavaType r1 = com.fasterxml.jackson.databind.type.TypeFactory.unknownType()
            return r1
        L_0x0015:
            com.fasterxml.jackson.databind.JavaType r1 = r0.getType()
            return r1
        L_0x001a:
            com.fasterxml.jackson.databind.introspect.AnnotatedParameter r0 = r1.getConstructorParameter()
            if (r0 != 0) goto L_0x0030
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = r1.getSetter()
            if (r0 == 0) goto L_0x002c
            r1 = 0
            com.fasterxml.jackson.databind.JavaType r1 = r0.getParameterType(r1)
            return r1
        L_0x002c:
            com.fasterxml.jackson.databind.introspect.AnnotatedField r0 = r1.getField()
        L_0x0030:
            if (r0 != 0) goto L_0x003d
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = r1.getGetter()
            if (r0 != 0) goto L_0x003d
            com.fasterxml.jackson.databind.JavaType r1 = com.fasterxml.jackson.databind.type.TypeFactory.unknownType()
            return r1
        L_0x003d:
            com.fasterxml.jackson.databind.JavaType r1 = r0.getType()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.getPrimaryType():com.fasterxml.jackson.databind.JavaType");
    }

    public Class<?> getRawPrimaryType() {
        return getPrimaryType().getRawClass();
    }

    public AnnotatedMethod getSetter() {
        Linked<AnnotatedMethod> linked = this._setters;
        if (linked == null) {
            return null;
        }
        Linked<T> linked2 = linked.next;
        if (linked2 == null) {
            return (AnnotatedMethod) linked.value;
        }
        while (linked2 != null) {
            T _selectSetter = _selectSetter((AnnotatedMethod) linked.value, (AnnotatedMethod) linked2.value);
            if (_selectSetter != linked.value) {
                if (_selectSetter != linked2.value) {
                    return _selectSetterFromMultiple(linked, linked2);
                }
                linked = linked2;
            }
            linked2 = linked2.next;
        }
        this._setters = linked.withoutNext();
        return (AnnotatedMethod) linked.value;
    }

    public AnnotatedMethod getSetterUnchecked() {
        Linked<AnnotatedMethod> linked = this._setters;
        if (linked == null) {
            return null;
        }
        return (AnnotatedMethod) linked.value;
    }

    public PropertyName getWrapperName() {
        AnnotationIntrospector annotationIntrospector;
        AnnotatedMember primaryMember = getPrimaryMember();
        if (primaryMember == null || (annotationIntrospector = this._annotationIntrospector) == null) {
            return null;
        }
        return annotationIntrospector.findWrapperName(primaryMember);
    }

    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    public boolean hasField() {
        return this._fields != null;
    }

    public boolean hasGetter() {
        return this._getters != null;
    }

    public boolean hasName(PropertyName propertyName) {
        return this._name.equals(propertyName);
    }

    public boolean hasSetter() {
        return this._setters != null;
    }

    public boolean isExplicitlyIncluded() {
        return _anyExplicits(this._fields) || _anyExplicits(this._getters) || _anyExplicits(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean isExplicitlyNamed() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean isTypeId() {
        Boolean bool = (Boolean) fromMemberAnnotations(new WithMember<Boolean>() {
            public Boolean withMember(AnnotatedMember annotatedMember) {
                return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(annotatedMember);
            }
        });
        return bool != null && bool.booleanValue();
    }

    public void mergeAnnotations(boolean z2) {
        if (z2) {
            Linked<AnnotatedMethod> linked = this._getters;
            if (linked != null) {
                this._getters = _applyAnnotations(this._getters, _mergeAnnotations(0, linked, this._fields, this._ctorParameters, this._setters));
                return;
            }
            Linked<AnnotatedField> linked2 = this._fields;
            if (linked2 != null) {
                this._fields = _applyAnnotations(this._fields, _mergeAnnotations(0, linked2, this._ctorParameters, this._setters));
                return;
            }
            return;
        }
        Linked<AnnotatedParameter> linked3 = this._ctorParameters;
        if (linked3 != null) {
            this._ctorParameters = _applyAnnotations(this._ctorParameters, _mergeAnnotations(0, linked3, this._setters, this._fields, this._getters));
            return;
        }
        Linked<AnnotatedMethod> linked4 = this._setters;
        if (linked4 != null) {
            this._setters = _applyAnnotations(this._setters, _mergeAnnotations(0, linked4, this._fields, this._getters));
            return;
        }
        Linked<AnnotatedField> linked5 = this._fields;
        if (linked5 != null) {
            this._fields = _applyAnnotations(this._fields, _mergeAnnotations(0, linked5, this._getters));
        }
    }

    public void removeConstructors() {
        this._ctorParameters = null;
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    @Deprecated
    public JsonProperty.Access removeNonVisible(boolean z2) {
        return removeNonVisible(z2, (POJOPropertiesCollector) null);
    }

    public String toString() {
        return "[Property '" + this._name + "'; ctors: " + this._ctorParameters + ", field(s): " + this._fields + ", getter(s): " + this._getters + ", setter(s): " + this._setters + "]";
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public POJOPropertyBuilder(MapperConfig<?> mapperConfig, AnnotationIntrospector annotationIntrospector, boolean z2, PropertyName propertyName, PropertyName propertyName2) {
        this._config = mapperConfig;
        this._annotationIntrospector = annotationIntrospector;
        this._internalName = propertyName;
        this._name = propertyName2;
        this._forSerialization = z2;
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    public JsonProperty.Access removeNonVisible(boolean z2, POJOPropertiesCollector pOJOPropertiesCollector) {
        JsonProperty.Access findAccess = findAccess();
        if (findAccess == null) {
            findAccess = JsonProperty.Access.AUTO;
        }
        int i3 = AnonymousClass6.$SwitchMap$com$fasterxml$jackson$annotation$JsonProperty$Access[findAccess.ordinal()];
        if (i3 == 1) {
            if (pOJOPropertiesCollector != null) {
                pOJOPropertiesCollector._collectIgnorals(getName());
                for (PropertyName simpleName : findExplicitNames()) {
                    pOJOPropertiesCollector._collectIgnorals(simpleName.getSimpleName());
                }
            }
            this._setters = null;
            this._ctorParameters = null;
            if (!this._forSerialization) {
                this._fields = null;
            }
        } else if (i3 != 2) {
            if (i3 != 3) {
                this._getters = _removeNonVisible(this._getters);
                this._ctorParameters = _removeNonVisible(this._ctorParameters);
                if (!z2 || this._getters == null) {
                    this._fields = _removeNonVisible(this._fields);
                    this._setters = _removeNonVisible(this._setters);
                }
            } else {
                this._getters = null;
                if (this._forSerialization) {
                    this._fields = null;
                }
            }
        }
        return findAccess;
    }

    public POJOPropertyBuilder withName(PropertyName propertyName) {
        return new POJOPropertyBuilder(this, propertyName);
    }

    public POJOPropertyBuilder withSimpleName(String str) {
        PropertyName withSimpleName = this._name.withSimpleName(str);
        return withSimpleName == this._name ? this : new POJOPropertyBuilder(this, withSimpleName);
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, PropertyName propertyName) {
        this._config = pOJOPropertyBuilder._config;
        this._annotationIntrospector = pOJOPropertyBuilder._annotationIntrospector;
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = propertyName;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
        this._forSerialization = pOJOPropertyBuilder._forSerialization;
    }
}
