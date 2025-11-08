package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JacksonInject {

    public static class Value implements JacksonAnnotationValue<JacksonInject>, Serializable {
        protected static final Value EMPTY = new Value((Object) null, (Boolean) null);
        private static final long serialVersionUID = 1;
        protected final Object _id;
        protected final Boolean _useInput;

        public Value(Object obj, Boolean bool) {
            this._id = obj;
            this._useInput = bool;
        }

        private static boolean _empty(Object obj, Boolean bool) {
            return obj == null && bool == null;
        }

        public static Value construct(Object obj, Boolean bool) {
            if ("".equals(obj)) {
                obj = null;
            }
            return _empty(obj, bool) ? EMPTY : new Value(obj, bool);
        }

        public static Value empty() {
            return EMPTY;
        }

        public static Value forId(Object obj) {
            return construct(obj, (Boolean) null);
        }

        public static Value from(JacksonInject jacksonInject) {
            return jacksonInject == null ? EMPTY : construct(jacksonInject.value(), jacksonInject.useInput().asBoolean());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                Value value = (Value) obj;
                if (OptBoolean.equals(this._useInput, value._useInput)) {
                    Object obj2 = this._id;
                    return obj2 == null ? value._id == null : obj2.equals(value._id);
                }
            }
            return false;
        }

        public Object getId() {
            return this._id;
        }

        public Boolean getUseInput() {
            return this._useInput;
        }

        public boolean hasId() {
            return this._id != null;
        }

        public int hashCode() {
            Object obj = this._id;
            int i3 = 1;
            if (obj != null) {
                i3 = 1 + obj.hashCode();
            }
            Boolean bool = this._useInput;
            return bool != null ? i3 + bool.hashCode() : i3;
        }

        public String toString() {
            return String.format("JacksonInject.Value(id=%s,useInput=%s)", new Object[]{this._id, this._useInput});
        }

        public Class<JacksonInject> valueFor() {
            return JacksonInject.class;
        }

        public boolean willUseInput(boolean z2) {
            Boolean bool = this._useInput;
            return bool == null ? z2 : bool.booleanValue();
        }

        public Value withId(Object obj) {
            if (obj == null) {
                if (this._id == null) {
                    return this;
                }
            } else if (obj.equals(this._id)) {
                return this;
            }
            return new Value(obj, this._useInput);
        }

        public Value withUseInput(Boolean bool) {
            if (bool == null) {
                if (this._useInput == null) {
                    return this;
                }
            } else if (bool.equals(this._useInput)) {
                return this;
            }
            return new Value(this._id, bool);
        }
    }

    OptBoolean useInput() default OptBoolean.DEFAULT;

    String value() default "";
}
