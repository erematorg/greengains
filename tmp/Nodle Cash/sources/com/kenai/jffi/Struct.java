package com.kenai.jffi;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Struct extends Aggregate {
    private static final Map<List<Type>, StructReference> structCache = new ConcurrentHashMap();
    private static final ReferenceQueue<Struct> structReferenceQueue = new ReferenceQueue<>();
    private final Type[] fields;

    public static final class StructReference extends WeakReference<Struct> {
        List<Type> fieldsList;

        private StructReference(Struct struct, ReferenceQueue<? super Struct> referenceQueue, List<Type> list) {
            super(struct, referenceQueue);
            this.fieldsList = list;
        }
    }

    private Struct(Foreign foreign, Type... typeArr) {
        super(foreign, foreign.newStruct(Type.nativeHandles(typeArr), false));
        this.fields = (Type[]) typeArr.clone();
    }

    public static Struct newStruct(Type... typeArr) {
        List asList = Arrays.asList(typeArr);
        StructReference structReference = structCache.get(asList);
        Struct struct = structReference != null ? (Struct) structReference.get() : null;
        if (struct != null) {
            return struct;
        }
        while (true) {
            ReferenceQueue<Struct> referenceQueue = structReferenceQueue;
            StructReference structReference2 = (StructReference) referenceQueue.poll();
            if (structReference2 != null) {
                structCache.remove(structReference2.fieldsList);
            } else {
                Map<List<Type>, StructReference> map = structCache;
                Struct struct2 = new Struct(Foreign.getInstance(), typeArr);
                map.put(asList, new StructReference(referenceQueue, asList));
                return struct2;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Struct.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        return Arrays.equals(this.fields, ((Struct) obj).fields);
    }

    public int hashCode() {
        return Arrays.hashCode(this.fields) + (super.hashCode() * 31);
    }

    @Deprecated
    public Struct(Type... typeArr) {
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(typeArr), false));
        this.fields = (Type[]) typeArr.clone();
    }
}
