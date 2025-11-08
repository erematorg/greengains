package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class BeanPropertyMap implements Iterable<SettableBeanProperty>, Serializable {
    private static final long serialVersionUID = 2;
    private final Map<String, List<PropertyName>> _aliasDefs;
    private final Map<String, String> _aliasMapping;
    protected final boolean _caseInsensitive;
    private Object[] _hashArea;
    private int _hashMask;
    private final Locale _locale;
    private final SettableBeanProperty[] _propsInOrder;
    private int _size;
    private int _spillCount;

    public BeanPropertyMap(boolean z2, Collection<SettableBeanProperty> collection, Map<String, List<PropertyName>> map, Locale locale) {
        this._caseInsensitive = z2;
        this._propsInOrder = (SettableBeanProperty[]) collection.toArray(new SettableBeanProperty[collection.size()]);
        this._aliasDefs = map;
        this._locale = locale;
        this._aliasMapping = _buildAliasMapping(map, z2, locale);
        init(collection);
    }

    private Map<String, String> _buildAliasMapping(Map<String, List<PropertyName>> map, boolean z2, Locale locale) {
        if (map == null || map.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (z2) {
                str = str.toLowerCase(locale);
            }
            for (PropertyName simpleName : (List) next.getValue()) {
                String simpleName2 = simpleName.getSimpleName();
                if (z2) {
                    simpleName2 = simpleName2.toLowerCase(locale);
                }
                hashMap.put(simpleName2, str);
            }
        }
        return hashMap;
    }

    private final SettableBeanProperty _find2(String str, int i3, Object obj) {
        if (obj == null) {
            return _findWithAlias(this._aliasMapping.get(str));
        }
        int i4 = this._hashMask + 1;
        int i5 = ((i3 >> 1) + i4) << 1;
        Object obj2 = this._hashArea[i5];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) this._hashArea[i5 + 1];
        }
        if (obj2 != null) {
            int i6 = (i4 + (i4 >> 1)) << 1;
            int i7 = this._spillCount + i6;
            while (i6 < i7) {
                Object obj3 = this._hashArea[i6];
                if (obj3 == str || str.equals(obj3)) {
                    return (SettableBeanProperty) this._hashArea[i6 + 1];
                }
                i6 += 2;
            }
        }
        return _findWithAlias(this._aliasMapping.get(str));
    }

    private SettableBeanProperty _find2ViaAlias(String str, int i3, Object obj) {
        int i4 = this._hashMask + 1;
        int i5 = ((i3 >> 1) + i4) << 1;
        Object obj2 = this._hashArea[i5];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) this._hashArea[i5 + 1];
        }
        if (obj2 == null) {
            return null;
        }
        int i6 = (i4 + (i4 >> 1)) << 1;
        int i7 = this._spillCount + i6;
        while (i6 < i7) {
            Object obj3 = this._hashArea[i6];
            if (obj3 == str || str.equals(obj3)) {
                return (SettableBeanProperty) this._hashArea[i6 + 1];
            }
            i6 += 2;
        }
        return null;
    }

    private final int _findFromOrdered(SettableBeanProperty settableBeanProperty) {
        int length = this._propsInOrder.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (this._propsInOrder[i3] == settableBeanProperty) {
                return i3;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + settableBeanProperty.getName() + "' missing from _propsInOrder");
    }

    private SettableBeanProperty _findWithAlias(String str) {
        if (str == null) {
            return null;
        }
        int _hashCode = _hashCode(str);
        int i3 = _hashCode << 1;
        Object obj = this._hashArea[i3];
        if (str.equals(obj)) {
            return (SettableBeanProperty) this._hashArea[i3 + 1];
        }
        if (obj == null) {
            return null;
        }
        return _find2ViaAlias(str, _hashCode, obj);
    }

    private final int _hashCode(String str) {
        return this._hashMask & str.hashCode();
    }

    private List<SettableBeanProperty> _properties() {
        ArrayList arrayList = new ArrayList(this._size);
        int length = this._hashArea.length;
        for (int i3 = 1; i3 < length; i3 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i3];
            if (settableBeanProperty != null) {
                arrayList.add(settableBeanProperty);
            }
        }
        return arrayList;
    }

    public static BeanPropertyMap construct(MapperConfig<?> mapperConfig, Collection<SettableBeanProperty> collection, Map<String, List<PropertyName>> map, boolean z2) {
        return new BeanPropertyMap(z2, collection, map, mapperConfig.getLocale());
    }

    private static final int findSize(int i3) {
        if (i3 <= 5) {
            return 8;
        }
        if (i3 <= 12) {
            return 16;
        }
        int i4 = 32;
        while (i4 < i3 + (i3 >> 2)) {
            i4 += i4;
        }
        return i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
        r2 = r1.unwrappingDeserializer(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty _rename(com.fasterxml.jackson.databind.deser.SettableBeanProperty r1, com.fasterxml.jackson.databind.util.NameTransformer r2) {
        /*
            r0 = this;
            if (r1 != 0) goto L_0x0003
            return r1
        L_0x0003:
            java.lang.String r0 = r1.getName()
            java.lang.String r0 = r2.transform(r0)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r1.withSimpleName(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r1 = r0.getValueDeserializer()
            if (r1 == 0) goto L_0x001f
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r1.unwrappingDeserializer(r2)
            if (r2 == r1) goto L_0x001f
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r0.withValueDeserializer(r2)
        L_0x001f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap._rename(com.fasterxml.jackson.databind.deser.SettableBeanProperty, com.fasterxml.jackson.databind.util.NameTransformer):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    public BeanPropertyMap assignIndexes() {
        int length = this._hashArea.length;
        int i3 = 0;
        for (int i4 = 1; i4 < length; i4 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i4];
            if (settableBeanProperty != null) {
                settableBeanProperty.assignIndex(i3);
                i3++;
            }
        }
        return this;
    }

    public SettableBeanProperty find(int i3) {
        int length = this._hashArea.length;
        for (int i4 = 1; i4 < length; i4 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i4];
            if (settableBeanProperty != null && i3 == settableBeanProperty.getPropertyIndex()) {
                return settableBeanProperty;
            }
        }
        return null;
    }

    public boolean findDeserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        SettableBeanProperty find = find(str);
        if (find == null) {
            return false;
        }
        try {
            find.deserializeAndSet(jsonParser, deserializationContext, obj);
            return true;
        } catch (Exception e3) {
            wrapAndThrow(e3, obj, str, deserializationContext);
            return true;
        }
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return this._propsInOrder;
    }

    public final String getPropertyName(SettableBeanProperty settableBeanProperty) {
        return this._caseInsensitive ? settableBeanProperty.getName().toLowerCase(this._locale) : settableBeanProperty.getName();
    }

    public boolean hasAliases() {
        return !this._aliasDefs.isEmpty();
    }

    public void init(Collection<SettableBeanProperty> collection) {
        int size = collection.size();
        this._size = size;
        int findSize = findSize(size);
        this._hashMask = findSize - 1;
        int i3 = (findSize >> 1) + findSize;
        Object[] objArr = new Object[(i3 * 2)];
        int i4 = 0;
        for (SettableBeanProperty next : collection) {
            if (next != null) {
                String propertyName = getPropertyName(next);
                int _hashCode = _hashCode(propertyName);
                int i5 = _hashCode << 1;
                if (objArr[i5] != null) {
                    i5 = ((_hashCode >> 1) + findSize) << 1;
                    if (objArr[i5] != null) {
                        i5 = (i3 << 1) + i4;
                        i4 += 2;
                        if (i5 >= objArr.length) {
                            objArr = Arrays.copyOf(objArr, objArr.length + 4);
                        }
                    }
                }
                objArr[i5] = propertyName;
                objArr[i5 + 1] = next;
            }
        }
        this._hashArea = objArr;
        this._spillCount = i4;
    }

    public boolean isCaseInsensitive() {
        return this._caseInsensitive;
    }

    public Iterator<SettableBeanProperty> iterator() {
        return _properties().iterator();
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        ArrayList arrayList = new ArrayList(this._size);
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        boolean z2 = false;
        for (int i3 = 1; i3 < length; i3 += 2) {
            Object[] objArr = this._hashArea;
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) objArr[i3];
            if (settableBeanProperty2 != null) {
                if (z2 || !(z2 = propertyName.equals(objArr[i3 - 1]))) {
                    arrayList.add(settableBeanProperty2);
                } else {
                    this._propsInOrder[_findFromOrdered(settableBeanProperty2)] = null;
                }
            }
        }
        if (z2) {
            init(arrayList);
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't remove");
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        if (nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        ArrayList arrayList = new ArrayList(r0);
        for (SettableBeanProperty settableBeanProperty : this._propsInOrder) {
            if (settableBeanProperty == null) {
                arrayList.add(settableBeanProperty);
            } else {
                arrayList.add(_rename(settableBeanProperty, nameTransformer));
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, (Collection<SettableBeanProperty>) arrayList, this._aliasDefs, this._locale);
    }

    public void replace(SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2) {
        int length = this._hashArea.length;
        for (int i3 = 1; i3 < length; i3 += 2) {
            Object[] objArr = this._hashArea;
            if (objArr[i3] == settableBeanProperty) {
                objArr[i3] = settableBeanProperty2;
                this._propsInOrder[_findFromOrdered(settableBeanProperty)] = settableBeanProperty2;
                return;
            }
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't replace");
    }

    public int size() {
        return this._size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Properties=[");
        Iterator<SettableBeanProperty> it = iterator();
        int i3 = 0;
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            int i4 = i3 + 1;
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(next.getName());
            sb.append('(');
            sb.append(next.getType());
            sb.append(')');
            i3 = i4;
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        if (!this._aliasDefs.isEmpty()) {
            sb.append("(aliases: ");
            sb.append(this._aliasDefs);
            sb.append(")");
        }
        return sb.toString();
    }

    public BeanPropertyMap withCaseInsensitivity(boolean z2) {
        return this._caseInsensitive == z2 ? this : new BeanPropertyMap(this, z2);
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        for (int i3 = 1; i3 < length; i3 += 2) {
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) this._hashArea[i3];
            if (settableBeanProperty2 != null && settableBeanProperty2.getName().equals(propertyName)) {
                return new BeanPropertyMap(this, settableBeanProperty, i3, _findFromOrdered(settableBeanProperty2));
            }
        }
        return new BeanPropertyMap(this, settableBeanProperty, propertyName, _hashCode(propertyName));
    }

    public BeanPropertyMap withoutProperties(Collection<String> collection) {
        return withoutProperties(collection, (Collection<String>) null);
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        boolean z2 = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z2 || !(th instanceof JacksonException)) {
                throw ((IOException) th);
            }
        } else if (!z2) {
            ClassUtil.throwIfRTE(th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, str);
    }

    public BeanPropertyMap withoutProperties(Collection<String> collection, Collection<String> collection2) {
        if ((collection == null || collection.isEmpty()) && collection2 == null) {
            return this;
        }
        ArrayList arrayList = new ArrayList(r0);
        for (SettableBeanProperty settableBeanProperty : this._propsInOrder) {
            if (settableBeanProperty != null && !IgnorePropertiesUtil.shouldIgnore(settableBeanProperty.getName(), collection, collection2)) {
                arrayList.add(settableBeanProperty);
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, (Collection<SettableBeanProperty>) arrayList, this._aliasDefs, this._locale);
    }

    @Deprecated
    public static BeanPropertyMap construct(MapperConfig<?> mapperConfig, Collection<SettableBeanProperty> collection, Map<String, List<PropertyName>> map) {
        return new BeanPropertyMap(mapperConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES), collection, map, mapperConfig.getLocale());
    }

    public SettableBeanProperty find(String str) {
        if (str != null) {
            if (this._caseInsensitive) {
                str = str.toLowerCase(this._locale);
            }
            int hashCode = str.hashCode() & this._hashMask;
            int i3 = hashCode << 1;
            Object obj = this._hashArea[i3];
            if (obj == str || str.equals(obj)) {
                return (SettableBeanProperty) this._hashArea[i3 + 1];
            }
            return _find2(str, hashCode, obj);
        }
        throw new IllegalArgumentException("Cannot pass null property name");
    }

    @Deprecated
    public static BeanPropertyMap construct(Collection<SettableBeanProperty> collection, boolean z2, Map<String, List<PropertyName>> map) {
        return new BeanPropertyMap(z2, collection, map);
    }

    @Deprecated
    public BeanPropertyMap(boolean z2, Collection<SettableBeanProperty> collection, Map<String, List<PropertyName>> map) {
        this(z2, collection, map, Locale.getDefault());
    }

    private BeanPropertyMap(BeanPropertyMap beanPropertyMap, SettableBeanProperty settableBeanProperty, int i3, int i4) {
        this._caseInsensitive = beanPropertyMap._caseInsensitive;
        this._locale = beanPropertyMap._locale;
        this._hashMask = beanPropertyMap._hashMask;
        this._size = beanPropertyMap._size;
        this._spillCount = beanPropertyMap._spillCount;
        this._aliasDefs = beanPropertyMap._aliasDefs;
        this._aliasMapping = beanPropertyMap._aliasMapping;
        Object[] objArr = beanPropertyMap._hashArea;
        this._hashArea = Arrays.copyOf(objArr, objArr.length);
        SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, settableBeanPropertyArr.length);
        this._propsInOrder = settableBeanPropertyArr2;
        this._hashArea[i3] = settableBeanProperty;
        settableBeanPropertyArr2[i4] = settableBeanProperty;
    }

    private BeanPropertyMap(BeanPropertyMap beanPropertyMap, SettableBeanProperty settableBeanProperty, String str, int i3) {
        this._caseInsensitive = beanPropertyMap._caseInsensitive;
        this._locale = beanPropertyMap._locale;
        this._hashMask = beanPropertyMap._hashMask;
        this._size = beanPropertyMap._size;
        this._spillCount = beanPropertyMap._spillCount;
        this._aliasDefs = beanPropertyMap._aliasDefs;
        this._aliasMapping = beanPropertyMap._aliasMapping;
        Object[] objArr = beanPropertyMap._hashArea;
        this._hashArea = Arrays.copyOf(objArr, objArr.length);
        SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        int length = settableBeanPropertyArr.length;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, length + 1);
        this._propsInOrder = settableBeanPropertyArr2;
        settableBeanPropertyArr2[length] = settableBeanProperty;
        int i4 = this._hashMask + 1;
        int i5 = i3 << 1;
        Object[] objArr2 = this._hashArea;
        if (objArr2[i5] != null) {
            i5 = ((i3 >> 1) + i4) << 1;
            if (objArr2[i5] != null) {
                int i6 = this._spillCount;
                i5 = ((i4 + (i4 >> 1)) << 1) + i6;
                this._spillCount = i6 + 2;
                if (i5 >= objArr2.length) {
                    this._hashArea = Arrays.copyOf(objArr2, objArr2.length + 4);
                }
            }
        }
        Object[] objArr3 = this._hashArea;
        objArr3[i5] = str;
        objArr3[i5 + 1] = settableBeanProperty;
    }

    public BeanPropertyMap(BeanPropertyMap beanPropertyMap, boolean z2) {
        this._caseInsensitive = z2;
        this._locale = beanPropertyMap._locale;
        this._aliasDefs = beanPropertyMap._aliasDefs;
        this._aliasMapping = beanPropertyMap._aliasMapping;
        SettableBeanProperty[] settableBeanPropertyArr = beanPropertyMap._propsInOrder;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, settableBeanPropertyArr.length);
        this._propsInOrder = settableBeanPropertyArr2;
        init(Arrays.asList(settableBeanPropertyArr2));
    }
}
