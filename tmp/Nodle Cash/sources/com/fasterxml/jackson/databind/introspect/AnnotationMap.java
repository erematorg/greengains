package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;

public final class AnnotationMap implements Annotations {
    protected HashMap<Class<?>, Annotation> _annotations;

    public AnnotationMap() {
    }

    public static AnnotationMap merge(AnnotationMap annotationMap, AnnotationMap annotationMap2) {
        HashMap<Class<?>, Annotation> hashMap;
        HashMap<Class<?>, Annotation> hashMap2;
        if (annotationMap == null || (hashMap = annotationMap._annotations) == null || hashMap.isEmpty()) {
            return annotationMap2;
        }
        if (annotationMap2 == null || (hashMap2 = annotationMap2._annotations) == null || hashMap2.isEmpty()) {
            return annotationMap;
        }
        HashMap hashMap3 = new HashMap();
        for (Annotation next : annotationMap2._annotations.values()) {
            hashMap3.put(next.annotationType(), next);
        }
        for (Annotation next2 : annotationMap._annotations.values()) {
            hashMap3.put(next2.annotationType(), next2);
        }
        return new AnnotationMap(hashMap3);
    }

    public static AnnotationMap of(Class<?> cls, Annotation annotation) {
        HashMap hashMap = new HashMap(4);
        hashMap.put(cls, annotation);
        return new AnnotationMap(hashMap);
    }

    public final boolean _add(Annotation annotation) {
        if (this._annotations == null) {
            this._annotations = new HashMap<>();
        }
        Annotation put = this._annotations.put(annotation.annotationType(), annotation);
        return put == null || !put.equals(annotation);
    }

    public boolean add(Annotation annotation) {
        return _add(annotation);
    }

    public boolean addIfNotPresent(Annotation annotation) {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        if (hashMap != null && hashMap.containsKey(annotation.annotationType())) {
            return false;
        }
        _add(annotation);
        return true;
    }

    public Iterable<Annotation> annotations() {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        return (hashMap == null || hashMap.size() == 0) ? Collections.emptyList() : this._annotations.values();
    }

    public <A extends Annotation> A get(Class<A> cls) {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        if (hashMap == null) {
            return null;
        }
        return (Annotation) hashMap.get(cls);
    }

    public boolean has(Class<?> cls) {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        if (hashMap == null) {
            return false;
        }
        return hashMap.containsKey(cls);
    }

    public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
        if (this._annotations != null) {
            for (Class<? extends Annotation> containsKey : clsArr) {
                if (this._annotations.containsKey(containsKey)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        if (hashMap == null) {
            return 0;
        }
        return hashMap.size();
    }

    public String toString() {
        HashMap<Class<?>, Annotation> hashMap = this._annotations;
        return hashMap == null ? "[null]" : hashMap.toString();
    }

    public AnnotationMap(HashMap<Class<?>, Annotation> hashMap) {
        this._annotations = hashMap;
    }
}
