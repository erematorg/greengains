package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import java.util.Map;

@CheckReturnValue
class MapFieldSchemaLite implements MapFieldSchema {
    private static <K, V> int getSerializedSizeLite(int i3, Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        MapEntryLite mapEntryLite = (MapEntryLite) obj2;
        int i4 = 0;
        if (mapFieldLite.isEmpty()) {
            return 0;
        }
        for (Map.Entry entry : mapFieldLite.entrySet()) {
            i4 += mapEntryLite.computeMessageSize(i3, entry.getKey(), entry.getValue());
        }
        return i4;
    }

    private static <K, V> MapFieldLite<K, V> mergeFromLite(Object obj, Object obj2) {
        MapFieldLite<K, V> mapFieldLite = (MapFieldLite) obj;
        MapFieldLite mapFieldLite2 = (MapFieldLite) obj2;
        if (!mapFieldLite2.isEmpty()) {
            if (!mapFieldLite.isMutable()) {
                mapFieldLite = mapFieldLite.mutableCopy();
            }
            mapFieldLite.mergeFrom(mapFieldLite2);
        }
        return mapFieldLite;
    }

    public Map<?, ?> forMapData(Object obj) {
        return (MapFieldLite) obj;
    }

    public MapEntryLite.Metadata<?, ?> forMapMetadata(Object obj) {
        return ((MapEntryLite) obj).getMetadata();
    }

    public Map<?, ?> forMutableMapData(Object obj) {
        return (MapFieldLite) obj;
    }

    public int getSerializedSize(int i3, Object obj, Object obj2) {
        return getSerializedSizeLite(i3, obj, obj2);
    }

    public boolean isImmutable(Object obj) {
        return !((MapFieldLite) obj).isMutable();
    }

    public Object mergeFrom(Object obj, Object obj2) {
        return mergeFromLite(obj, obj2);
    }

    public Object newMapField(Object obj) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    public Object toImmutable(Object obj) {
        ((MapFieldLite) obj).makeImmutable();
        return obj;
    }
}
