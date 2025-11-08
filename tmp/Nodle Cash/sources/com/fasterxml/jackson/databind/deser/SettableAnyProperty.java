package com.fasterxml.jackson.databind.deser;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class SettableAnyProperty implements Serializable {
    private static final long serialVersionUID = 1;
    protected final KeyDeserializer _keyDeserializer;
    protected final BeanProperty _property;
    protected final AnnotatedMember _setter;
    protected final boolean _setterIsField;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public static class AnySetterReferring extends ReadableObjectId.Referring {
        private final SettableAnyProperty _parent;
        private final Object _pojo;
        private final String _propName;

        public AnySetterReferring(SettableAnyProperty settableAnyProperty, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls, Object obj, String str) {
            super(unresolvedForwardReference, cls);
            this._parent = settableAnyProperty;
            this._pojo = obj;
            this._propName = str;
        }

        public void handleResolvedForwardReference(Object obj, Object obj2) throws IOException {
            if (hasId(obj)) {
                this._parent.set(this._pojo, this._propName, obj2);
                return;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj.toString() + "] that wasn't previously registered.");
        }
    }

    public static class JsonNodeFieldAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;
        protected final JsonNodeFactory _nodeFactory;

        public JsonNodeFieldAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, JsonDeserializer<Object> jsonDeserializer, JsonNodeFactory jsonNodeFactory) {
            super(beanProperty, annotatedMember, javaType, (KeyDeserializer) null, jsonDeserializer, (TypeDeserializer) null);
            this._nodeFactory = jsonNodeFactory;
        }

        public void _set(Object obj, Object obj2, Object obj3) throws Exception {
            setProperty(obj, (String) obj2, (JsonNode) obj3);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
        }

        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
            setProperty(obj, str, (JsonNode) deserialize(jsonParser, deserializationContext));
        }

        public void setProperty(Object obj, String str, JsonNode jsonNode) throws IOException {
            ObjectNode objectNode;
            AnnotatedField annotatedField = (AnnotatedField) this._setter;
            Object value = annotatedField.getValue(obj);
            if (value == null) {
                objectNode = this._nodeFactory.objectNode();
                annotatedField.setValue(obj, objectNode);
            } else if (value instanceof ObjectNode) {
                objectNode = (ObjectNode) value;
            } else {
                String propertyName = getPropertyName();
                String nameOf = ClassUtil.nameOf(value.getClass());
                throw JsonMappingException.from((DeserializationContext) null, "Value \"any-setter\" '" + propertyName + "' not `ObjectNode` but " + nameOf);
            }
            objectNode.set(str, jsonNode);
        }

        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return this;
        }
    }

    public static class MapFieldAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;
        protected final ValueInstantiator _valueInstantiator;

        public MapFieldAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
            super(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
            this._valueInstantiator = valueInstantiator;
        }

        public Map<Object, Object> _createAndSetMap(DeserializationContext deserializationContext, AnnotatedField annotatedField, Object obj, Object obj2) throws IOException {
            ValueInstantiator valueInstantiator = this._valueInstantiator;
            if (valueInstantiator != null) {
                Map<Object, Object> map = (Map) valueInstantiator.createUsingDefault(deserializationContext);
                annotatedField.setValue(obj, map);
                return map;
            }
            throw JsonMappingException.from(deserializationContext, C0118y.g("Cannot create an instance of ", ClassUtil.nameOf(this._type.getRawClass()), " for use as \"any-setter\" '", this._property.getName(), "'"));
        }

        public void _set(Object obj, Object obj2, Object obj3) throws Exception {
            AnnotatedField annotatedField = (AnnotatedField) this._setter;
            Map<Object, Object> map = (Map) annotatedField.getValue(obj);
            if (map == null) {
                map = _createAndSetMap((DeserializationContext) null, annotatedField, obj, obj2);
            }
            map.put(obj2, obj3);
        }

        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MapFieldAnyProperty(this._property, this._setter, this._type, this._keyDeserializer, jsonDeserializer, this._valueTypeDeserializer, this._valueInstantiator);
        }
    }

    public static class MethodAnyProperty extends SettableAnyProperty implements Serializable {
        private static final long serialVersionUID = 1;

        public MethodAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
            super(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
        }

        public void _set(Object obj, Object obj2, Object obj3) throws Exception {
            ((AnnotatedMethod) this._setter).callOnWith(obj, obj2, obj3);
        }

        public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
            return new MethodAnyProperty(this._property, this._setter, this._type, this._keyDeserializer, jsonDeserializer, this._valueTypeDeserializer);
        }
    }

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        this._property = beanProperty;
        this._setter = annotatedMember;
        this._type = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._keyDeserializer = keyDeserializer;
        this._setterIsField = annotatedMember instanceof AnnotatedField;
    }

    public static SettableAnyProperty constructForJsonNodeField(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        return new JsonNodeFieldAnyProperty(beanProperty, annotatedMember, javaType, jsonDeserializer, deserializationContext.getNodeFactory());
    }

    public static SettableAnyProperty constructForMapField(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        Class rawType = annotatedMember.getRawType();
        if (rawType == Map.class) {
            rawType = LinkedHashMap.class;
        }
        return new MapFieldAnyProperty(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer, JDKValueInstantiators.findStdValueInstantiator(deserializationContext.getConfig(), rawType));
    }

    public static SettableAnyProperty constructForMethod(DeserializationContext deserializationContext, BeanProperty beanProperty, AnnotatedMember annotatedMember, JavaType javaType, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        return new MethodAnyProperty(beanProperty, annotatedMember, javaType, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    private String getClassName() {
        return ClassUtil.nameOf(this._setter.getDeclaringClass());
    }

    public abstract void _set(Object obj, Object obj2, Object obj3) throws Exception;

    public void _throwAsIOE(Exception exc, Object obj, Object obj2) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            String classNameOf = ClassUtil.classNameOf(obj2);
            StringBuilder sb = new StringBuilder("Problem deserializing \"any-property\" '");
            sb.append(obj);
            sb.append("' of class " + getClassName() + " (expected type: ");
            sb.append(this._type);
            sb.append("; actual type: ");
            sb.append(classNameOf);
            sb.append(")");
            String exceptionMessage = ClassUtil.exceptionMessage(exc);
            if (exceptionMessage != null) {
                sb.append(", problem: ");
                sb.append(exceptionMessage);
            } else {
                sb.append(" (no error message provided)");
            }
            throw new JsonMappingException((Closeable) null, sb.toString(), (Throwable) exc);
        }
        ClassUtil.throwIfIOE(exc);
        ClassUtil.throwIfRTE(exc);
        Throwable rootCause = ClassUtil.getRootCause(exc);
        throw new JsonMappingException((Closeable) null, ClassUtil.exceptionMessage(rootCause), rootCause);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return this._valueDeserializer.getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        return typeDeserializer != null ? this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer) : this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        try {
            KeyDeserializer keyDeserializer = this._keyDeserializer;
            set(obj, keyDeserializer == null ? str : keyDeserializer.deserializeKey(str, deserializationContext), deserialize(jsonParser, deserializationContext));
        } catch (UnresolvedForwardReference e3) {
            if (this._valueDeserializer.getObjectIdReader() != null) {
                e3.getRoid().appendReferring(new AnySetterReferring(this, e3, this._type.getRawClass(), obj, str));
                return;
            }
            throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", (Throwable) e3);
        }
    }

    public void fixAccess(DeserializationConfig deserializationConfig) {
        this._setter.fixAccess(deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public String getPropertyName() {
        return this._property.getName();
    }

    public JavaType getType() {
        return this._type;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public Object readResolve() {
        AnnotatedMember annotatedMember = this._setter;
        if (annotatedMember != null && annotatedMember.getAnnotated() != null) {
            return this;
        }
        throw new IllegalArgumentException("Missing method/field (broken JDK (de)serialization?)");
    }

    public void set(Object obj, Object obj2, Object obj3) throws IOException {
        try {
            _set(obj, obj2, obj3);
        } catch (IOException e3) {
            throw e3;
        } catch (Exception e4) {
            _throwAsIOE(e4, obj2, obj3);
        }
    }

    public String toString() {
        return a.n(new StringBuilder("[any property on class "), getClassName(), "]");
    }

    public abstract SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer);
}
