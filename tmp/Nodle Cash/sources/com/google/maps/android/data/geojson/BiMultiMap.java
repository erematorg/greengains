package com.google.maps.android.data.geojson;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BiMultiMap<K> extends HashMap<K, Object> {
    private final Map<Object, K> mValuesToKeys = new HashMap();

    public void clear() {
        super.clear();
        this.mValuesToKeys.clear();
    }

    public K getKey(Object obj) {
        return this.mValuesToKeys.get(obj);
    }

    public Object put(K k2, Object obj) {
        if (obj instanceof Collection) {
            return put(k2, (Collection) obj);
        }
        this.mValuesToKeys.put(obj, k2);
        return super.put(k2, obj);
    }

    public void putAll(Map<? extends K, ?> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public Object remove(Object obj) {
        Object remove = super.remove(obj);
        if (remove instanceof Collection) {
            for (Object remove2 : (Collection) remove) {
                this.mValuesToKeys.remove(remove2);
            }
        } else {
            this.mValuesToKeys.remove(remove);
        }
        return remove;
    }

    public BiMultiMap<K> clone() {
        BiMultiMap<K> biMultiMap = new BiMultiMap<>();
        biMultiMap.putAll((Map) super.clone());
        return biMultiMap;
    }

    public Object put(K k2, Collection collection) {
        for (Object put : collection) {
            this.mValuesToKeys.put(put, k2);
        }
        return super.put(k2, collection);
    }
}
