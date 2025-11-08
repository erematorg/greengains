package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AnnotationCollector {
    protected static final Annotations NO_ANNOTATIONS = new NoAnnotations();
    protected final Object _data;

    public static class EmptyCollector extends AnnotationCollector {
        public static final EmptyCollector instance = new EmptyCollector((Object) null);

        public EmptyCollector(Object obj) {
            super(obj);
        }

        public AnnotationCollector addOrOverride(Annotation annotation) {
            return new OneCollector(this._data, annotation.annotationType(), annotation);
        }

        public AnnotationMap asAnnotationMap() {
            return new AnnotationMap();
        }

        public Annotations asAnnotations() {
            return AnnotationCollector.NO_ANNOTATIONS;
        }

        public boolean isPresent(Annotation annotation) {
            return false;
        }
    }

    public static class NCollector extends AnnotationCollector {
        protected final HashMap<Class<?>, Annotation> _annotations;

        public NCollector(Object obj, Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            super(obj);
            HashMap<Class<?>, Annotation> hashMap = new HashMap<>();
            this._annotations = hashMap;
            hashMap.put(cls, annotation);
            hashMap.put(cls2, annotation2);
        }

        public AnnotationCollector addOrOverride(Annotation annotation) {
            this._annotations.put(annotation.annotationType(), annotation);
            return this;
        }

        public AnnotationMap asAnnotationMap() {
            AnnotationMap annotationMap = new AnnotationMap();
            for (Annotation add : this._annotations.values()) {
                annotationMap.add(add);
            }
            return annotationMap;
        }

        public Annotations asAnnotations() {
            if (this._annotations.size() != 2) {
                return new AnnotationMap(this._annotations);
            }
            Iterator<Map.Entry<Class<?>, Annotation>> it = this._annotations.entrySet().iterator();
            Map.Entry next = it.next();
            Map.Entry next2 = it.next();
            return new TwoAnnotations((Class) next.getKey(), (Annotation) next.getValue(), (Class) next2.getKey(), (Annotation) next2.getValue());
        }

        public boolean isPresent(Annotation annotation) {
            return this._annotations.containsKey(annotation.annotationType());
        }
    }

    public static class NoAnnotations implements Annotations, Serializable {
        private static final long serialVersionUID = 1;

        public <A extends Annotation> A get(Class<A> cls) {
            return null;
        }

        public boolean has(Class<?> cls) {
            return false;
        }

        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            return false;
        }

        public int size() {
            return 0;
        }
    }

    public static class OneAnnotation implements Annotations, Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> _type;
        private final Annotation _value;

        public OneAnnotation(Class<?> cls, Annotation annotation) {
            this._type = cls;
            this._value = annotation;
        }

        public <A extends Annotation> A get(Class<A> cls) {
            if (this._type == cls) {
                return this._value;
            }
            return null;
        }

        public boolean has(Class<?> cls) {
            return this._type == cls;
        }

        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            for (Class<?> cls : clsArr) {
                if (cls == this._type) {
                    return true;
                }
            }
            return false;
        }

        public int size() {
            return 1;
        }
    }

    public static class OneCollector extends AnnotationCollector {
        private Class<?> _type;
        private Annotation _value;

        public OneCollector(Object obj, Class<?> cls, Annotation annotation) {
            super(obj);
            this._type = cls;
            this._value = annotation;
        }

        public AnnotationCollector addOrOverride(Annotation annotation) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            Class<?> cls = this._type;
            if (cls != annotationType) {
                return new NCollector(this._data, cls, this._value, annotationType, annotation);
            }
            this._value = annotation;
            return this;
        }

        public AnnotationMap asAnnotationMap() {
            return AnnotationMap.of(this._type, this._value);
        }

        public Annotations asAnnotations() {
            return new OneAnnotation(this._type, this._value);
        }

        public boolean isPresent(Annotation annotation) {
            return annotation.annotationType() == this._type;
        }
    }

    public static class TwoAnnotations implements Annotations, Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?> _type1;
        private final Class<?> _type2;
        private final Annotation _value1;
        private final Annotation _value2;

        public TwoAnnotations(Class<?> cls, Annotation annotation, Class<?> cls2, Annotation annotation2) {
            this._type1 = cls;
            this._value1 = annotation;
            this._type2 = cls2;
            this._value2 = annotation2;
        }

        public <A extends Annotation> A get(Class<A> cls) {
            if (this._type1 == cls) {
                return this._value1;
            }
            if (this._type2 == cls) {
                return this._value2;
            }
            return null;
        }

        public boolean has(Class<?> cls) {
            return this._type1 == cls || this._type2 == cls;
        }

        public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
            for (Class<?> cls : clsArr) {
                if (cls == this._type1 || cls == this._type2) {
                    return true;
                }
            }
            return false;
        }

        public int size() {
            return 2;
        }
    }

    public AnnotationCollector(Object obj) {
        this._data = obj;
    }

    public static Annotations emptyAnnotations() {
        return NO_ANNOTATIONS;
    }

    public static AnnotationCollector emptyCollector() {
        return EmptyCollector.instance;
    }

    public abstract AnnotationCollector addOrOverride(Annotation annotation);

    public abstract AnnotationMap asAnnotationMap();

    public abstract Annotations asAnnotations();

    public Object getData() {
        return this._data;
    }

    public abstract boolean isPresent(Annotation annotation);

    public static AnnotationCollector emptyCollector(Object obj) {
        return new EmptyCollector(obj);
    }
}
