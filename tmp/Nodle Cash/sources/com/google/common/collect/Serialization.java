package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.collect.Multiset;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
final class Serialization {

    public static final class FieldSetter<T> {
        private final Field field;

        public void set(T t2, Object obj) {
            try {
                this.field.set(t2, obj);
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }

        private FieldSetter(Field field2) {
            this.field = field2;
            field2.setAccessible(true);
        }

        public void set(T t2, int i3) {
            try {
                this.field.set(t2, Integer.valueOf(i3));
            } catch (IllegalAccessException e3) {
                throw new AssertionError(e3);
            }
        }
    }

    private Serialization() {
    }

    public static <T> FieldSetter<T> getFieldSetter(Class<T> cls, String str) {
        try {
            return new FieldSetter<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e3) {
            throw new AssertionError(e3);
        }
    }

    public static <K, V> void populateMap(Map<K, V> map, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        populateMap(map, objectInputStream, objectInputStream.readInt());
    }

    public static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        populateMultimap(multimap, objectInputStream, objectInputStream.readInt());
    }

    public static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        populateMultiset(multiset, objectInputStream, objectInputStream.readInt());
    }

    public static int readCount(ObjectInputStream objectInputStream) throws IOException {
        return objectInputStream.readInt();
    }

    public static <K, V> void writeMap(Map<K, V> map, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(map.size());
        for (Map.Entry next : map.entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeObject(next.getValue());
        }
    }

    public static <K, V> void writeMultimap(Multimap<K, V> multimap, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(multimap.asMap().size());
        for (Map.Entry next : multimap.asMap().entrySet()) {
            objectOutputStream.writeObject(next.getKey());
            objectOutputStream.writeInt(((Collection) next.getValue()).size());
            for (Object writeObject : (Collection) next.getValue()) {
                objectOutputStream.writeObject(writeObject);
            }
        }
    }

    public static <E> void writeMultiset(Multiset<E> multiset, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(multiset.entrySet().size());
        for (Multiset.Entry next : multiset.entrySet()) {
            objectOutputStream.writeObject(next.getElement());
            objectOutputStream.writeInt(next.getCount());
        }
    }

    public static <K, V> void populateMap(Map<K, V> map, ObjectInputStream objectInputStream, int i3) throws IOException, ClassNotFoundException {
        for (int i4 = 0; i4 < i3; i4++) {
            map.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    public static <K, V> void populateMultimap(Multimap<K, V> multimap, ObjectInputStream objectInputStream, int i3) throws IOException, ClassNotFoundException {
        for (int i4 = 0; i4 < i3; i4++) {
            Collection<V> collection = multimap.get(objectInputStream.readObject());
            int readInt = objectInputStream.readInt();
            for (int i5 = 0; i5 < readInt; i5++) {
                collection.add(objectInputStream.readObject());
            }
        }
    }

    public static <E> void populateMultiset(Multiset<E> multiset, ObjectInputStream objectInputStream, int i3) throws IOException, ClassNotFoundException {
        for (int i4 = 0; i4 < i3; i4++) {
            multiset.add(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }
}
