package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public abstract class AccessorNamingStrategy {

    public static class Base extends AccessorNamingStrategy implements Serializable {
        private static final long serialVersionUID = 1;

        public String findNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
            return null;
        }

        public String findNameForMutator(AnnotatedMethod annotatedMethod, String str) {
            return null;
        }

        public String findNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
            return null;
        }

        public String modifyFieldName(AnnotatedField annotatedField, String str) {
            return str;
        }
    }

    public static abstract class Provider implements Serializable {
        private static final long serialVersionUID = 1;

        public abstract AccessorNamingStrategy forBuilder(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, BeanDescription beanDescription);

        public abstract AccessorNamingStrategy forPOJO(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass);

        public abstract AccessorNamingStrategy forRecord(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass);
    }

    public abstract String findNameForIsGetter(AnnotatedMethod annotatedMethod, String str);

    public abstract String findNameForMutator(AnnotatedMethod annotatedMethod, String str);

    public abstract String findNameForRegularGetter(AnnotatedMethod annotatedMethod, String str);

    public abstract String modifyFieldName(AnnotatedField annotatedField, String str);
}
