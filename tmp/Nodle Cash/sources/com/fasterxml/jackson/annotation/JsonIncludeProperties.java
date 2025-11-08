package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonIncludeProperties {

    public static class Value implements JacksonAnnotationValue<JsonIncludeProperties>, Serializable {
        protected static final Value ALL = new Value((Set<String>) null);
        private static final long serialVersionUID = 1;
        protected final Set<String> _included;

        public Value(Set<String> set) {
            this._included = set;
        }

        private static Set<String> _asSet(String[] strArr) {
            if (strArr == null || strArr.length == 0) {
                return Collections.emptySet();
            }
            HashSet hashSet = new HashSet(strArr.length);
            for (String add : strArr) {
                hashSet.add(add);
            }
            return hashSet;
        }

        private static boolean _equals(Set<String> set, Set<String> set2) {
            return set == null ? set2 == null : set.equals(set2);
        }

        public static Value all() {
            return ALL;
        }

        public static Value from(JsonIncludeProperties jsonIncludeProperties) {
            return jsonIncludeProperties == null ? ALL : new Value(_asSet(jsonIncludeProperties.value()));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            return obj.getClass() == getClass() && _equals(this._included, ((Value) obj)._included);
        }

        public Set<String> getIncluded() {
            return this._included;
        }

        public int hashCode() {
            Set<String> set = this._included;
            if (set == null) {
                return 0;
            }
            return set.size();
        }

        public String toString() {
            return String.format("JsonIncludeProperties.Value(included=%s)", new Object[]{this._included});
        }

        public Class<JsonIncludeProperties> valueFor() {
            return JsonIncludeProperties.class;
        }

        public Value withOverrides(Value value) {
            Set<String> included;
            if (value == null || (included = value.getIncluded()) == null) {
                return this;
            }
            if (this._included == null) {
                return value;
            }
            HashSet hashSet = new HashSet();
            for (String next : included) {
                if (this._included.contains(next)) {
                    hashSet.add(next);
                }
            }
            return new Value(hashSet);
        }
    }

    String[] value() default {};
}
