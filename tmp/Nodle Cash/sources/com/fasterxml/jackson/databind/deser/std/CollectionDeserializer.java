package com.fasterxml.jackson.databind.deser.std;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements ContextualDeserializer {
    private static final long serialVersionUID = -1;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public static final class CollectionReferring extends ReadableObjectId.Referring {
        private final CollectionReferringAccumulator _parent;
        public final List<Object> next = new ArrayList();

        public CollectionReferring(CollectionReferringAccumulator collectionReferringAccumulator, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls) {
            super(unresolvedForwardReference, cls);
            this._parent = collectionReferringAccumulator;
        }

        public void handleResolvedForwardReference(Object obj, Object obj2) throws IOException {
            this._parent.resolveForwardReference(obj, obj2);
        }
    }

    public static class CollectionReferringAccumulator {
        private List<CollectionReferring> _accumulator = new ArrayList();
        private final Class<?> _elementType;
        private final Collection<Object> _result;

        public CollectionReferringAccumulator(Class<?> cls, Collection<Object> collection) {
            this._elementType = cls;
            this._result = collection;
        }

        public void add(Object obj) {
            if (this._accumulator.isEmpty()) {
                this._result.add(obj);
            } else {
                ((CollectionReferring) a.h(this._accumulator, 1)).next.add(obj);
            }
        }

        public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference unresolvedForwardReference) {
            CollectionReferring collectionReferring = new CollectionReferring(this, unresolvedForwardReference, this._elementType);
            this._accumulator.add(collectionReferring);
            return collectionReferring;
        }

        public void resolveForwardReference(Object obj, Object obj2) throws IOException {
            Iterator<CollectionReferring> it = this._accumulator.iterator();
            Collection collection = this._result;
            while (it.hasNext()) {
                CollectionReferring next = it.next();
                if (next.hasId(obj)) {
                    it.remove();
                    collection.add(obj2);
                    collection.addAll(next.next);
                    return;
                }
                collection = next.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, jsonDeserializer, typeDeserializer, valueInstantiator, (JsonDeserializer<Object>) null, (NullValueProvider) null, (Boolean) null);
    }

    public Collection<Object> _deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        Object obj;
        jsonParser.setCurrentValue(collection);
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer.getObjectIdReader() != null) {
            return _deserializeWithObjectId(jsonParser, deserializationContext, collection);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            try {
                if (nextToken != JsonToken.VALUE_NULL) {
                    obj = typeDeserializer == null ? jsonDeserializer.deserialize(jsonParser, deserializationContext) : jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                } else if (!this._skipNullValues) {
                    obj = this._nullProvider.getNullValue(deserializationContext);
                }
                collection.add(obj);
            } catch (Exception e3) {
                if (deserializationContext != null && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                    ClassUtil.throwIfRTE(e3);
                }
                throw JsonMappingException.wrapWithPath((Throwable) e3, (Object) collection, collection.size());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r9 = logicalType();
        r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.Fail;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<java.lang.Object> _deserializeFromString(com.fasterxml.jackson.core.JsonParser r7, com.fasterxml.jackson.databind.DeserializationContext r8, java.lang.String r9) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.Class r4 = r6.handledType()
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x0026
            com.fasterxml.jackson.databind.type.LogicalType r9 = r6.logicalType()
            com.fasterxml.jackson.databind.cfg.CoercionInputShape r0 = com.fasterxml.jackson.databind.cfg.CoercionInputShape.EmptyString
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = r8.findCoercionAction(r9, r4, r0)
            if (r3 == 0) goto L_0x0044
            com.fasterxml.jackson.databind.cfg.CoercionAction r9 = com.fasterxml.jackson.databind.cfg.CoercionAction.Fail
            if (r3 == r9) goto L_0x0044
            java.lang.String r5 = "empty String (\"\")"
            r0 = r6
            r1 = r7
            r2 = r8
            java.lang.Object r6 = r0._deserializeFromEmptyString(r1, r2, r3, r4, r5)
            java.util.Collection r6 = (java.util.Collection) r6
            return r6
        L_0x0026:
            boolean r9 = com.fasterxml.jackson.databind.deser.std.StdDeserializer._isBlank(r9)
            if (r9 == 0) goto L_0x0044
            com.fasterxml.jackson.databind.type.LogicalType r9 = r6.logicalType()
            com.fasterxml.jackson.databind.cfg.CoercionAction r0 = com.fasterxml.jackson.databind.cfg.CoercionAction.Fail
            com.fasterxml.jackson.databind.cfg.CoercionAction r3 = r8.findCoercionFromBlankString(r9, r4, r0)
            if (r3 == r0) goto L_0x0044
            java.lang.String r5 = "blank String (all whitespace)"
            r0 = r6
            r1 = r7
            r2 = r8
            java.lang.Object r6 = r0._deserializeFromEmptyString(r1, r2, r3, r4, r5)
            java.util.Collection r6 = (java.util.Collection) r6
            return r6
        L_0x0044:
            java.util.Collection r9 = r6.createDefaultInstance(r8)
            java.util.Collection r6 = r6.handleNonArray(r7, r8, r9)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.CollectionDeserializer._deserializeFromString(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.String):java.util.Collection");
    }

    public Collection<Object> _deserializeWithObjectId(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        Object obj;
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        jsonParser.setCurrentValue(collection);
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        CollectionReferringAccumulator collectionReferringAccumulator = new CollectionReferringAccumulator(this._containerType.getContentType().getRawClass(), collection);
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            try {
                if (nextToken != JsonToken.VALUE_NULL) {
                    obj = typeDeserializer == null ? jsonDeserializer.deserialize(jsonParser, deserializationContext) : jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                } else if (!this._skipNullValues) {
                    obj = this._nullProvider.getNullValue(deserializationContext);
                }
                collectionReferringAccumulator.add(obj);
            } catch (UnresolvedForwardReference e3) {
                e3.getRoid().appendReferring(collectionReferringAccumulator.handleUnresolvedReference(e3));
            } catch (Exception e4) {
                if (deserializationContext != null && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                    ClassUtil.throwIfRTE(e4);
                }
                throw JsonMappingException.wrapWithPath((Throwable) e4, (Object) collection, collection.size());
            }
        }
    }

    public Collection<Object> createDefaultInstance(DeserializationContext deserializationContext) throws IOException {
        return (Collection) this._valueInstantiator.createUsingDefault(deserializationContext);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public final Collection<Object> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        Object obj;
        Boolean bool = this._unwrapSingle;
        if (bool != Boolean.TRUE && (bool != null || !deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return (Collection) deserializationContext.handleUnexpectedToken(this._containerType, jsonParser);
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        try {
            if (!jsonParser.hasToken(JsonToken.VALUE_NULL)) {
                obj = typeDeserializer == null ? jsonDeserializer.deserialize(jsonParser, deserializationContext) : jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
            } else if (this._skipNullValues) {
                return collection;
            } else {
                obj = this._nullProvider.getNullValue(deserializationContext);
            }
            collection.add(obj);
            return collection;
        } catch (Exception e3) {
            if (!deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                ClassUtil.throwIfRTE(e3);
            }
            throw JsonMappingException.wrapWithPath((Throwable) e3, (Object) Object.class, collection.size());
        }
    }

    public boolean isCachable() {
        return this._valueDeserializer == null && this._valueTypeDeserializer == null && this._delegateDeserializer == null;
    }

    public LogicalType logicalType() {
        return LogicalType.Collection;
    }

    public CollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, TypeDeserializer typeDeserializer, NullValueProvider nullValueProvider, Boolean bool) {
        return new CollectionDeserializer(this._containerType, jsonDeserializer2, typeDeserializer, this._valueInstantiator, jsonDeserializer, nullValueProvider, bool);
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator, JsonDeserializer<Object> jsonDeserializer2, NullValueProvider nullValueProvider, Boolean bool) {
        super(javaType, nullValueProvider, bool);
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = jsonDeserializer2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.std.CollectionDeserializer createContextual(com.fasterxml.jackson.databind.DeserializationContext r8, com.fasterxml.jackson.databind.BeanProperty r9) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r7 = this;
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7._valueInstantiator
            if (r0 == 0) goto L_0x0067
            boolean r0 = r0.canCreateUsingDelegate()
            if (r0 == 0) goto L_0x0035
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7._valueInstantiator
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r8.getConfig()
            com.fasterxml.jackson.databind.JavaType r0 = r0.getDelegateType(r1)
            if (r0 != 0) goto L_0x002f
            com.fasterxml.jackson.databind.JavaType r1 = r7._containerType
            com.fasterxml.jackson.databind.deser.ValueInstantiator r2 = r7._valueInstantiator
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2}
            java.lang.String r3 = "Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r8.reportBadDefinition(r1, r2)
        L_0x002f:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r7.findDeserializer(r8, r0, r9)
        L_0x0033:
            r2 = r0
            goto L_0x0069
        L_0x0035:
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7._valueInstantiator
            boolean r0 = r0.canCreateUsingArrayDelegate()
            if (r0 == 0) goto L_0x0067
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7._valueInstantiator
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r8.getConfig()
            com.fasterxml.jackson.databind.JavaType r0 = r0.getArrayDelegateType(r1)
            if (r0 != 0) goto L_0x0062
            com.fasterxml.jackson.databind.JavaType r1 = r7._containerType
            com.fasterxml.jackson.databind.deser.ValueInstantiator r2 = r7._valueInstantiator
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2}
            java.lang.String r3 = "Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r8.reportBadDefinition(r1, r2)
        L_0x0062:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r7.findDeserializer(r8, r0, r9)
            goto L_0x0033
        L_0x0067:
            r0 = 0
            goto L_0x0033
        L_0x0069:
            java.lang.Class<java.util.Collection> r0 = java.util.Collection.class
            com.fasterxml.jackson.annotation.JsonFormat$Feature r1 = com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY
            java.lang.Boolean r6 = r7.findFormatFeature(r8, r9, r0, r1)
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r7._valueDeserializer
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r7.findConvertingContentDeserializer(r8, r9, r0)
            com.fasterxml.jackson.databind.JavaType r1 = r7._containerType
            com.fasterxml.jackson.databind.JavaType r1 = r1.getContentType()
            if (r0 != 0) goto L_0x0085
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r8.findContextualValueDeserializer(r1, r9)
        L_0x0083:
            r3 = r0
            goto L_0x008a
        L_0x0085:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r8.handleSecondaryContextualization(r0, r9, r1)
            goto L_0x0083
        L_0x008a:
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r0 = r7._valueTypeDeserializer
            if (r0 == 0) goto L_0x0092
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r0 = r0.forProperty(r9)
        L_0x0092:
            r4 = r0
            com.fasterxml.jackson.databind.deser.NullValueProvider r5 = r7.findContentNullProvider(r8, r9, r3)
            java.lang.Boolean r8 = r7._unwrapSingle
            boolean r8 = java.util.Objects.equals(r6, r8)
            if (r8 == 0) goto L_0x00b1
            com.fasterxml.jackson.databind.deser.NullValueProvider r8 = r7._nullProvider
            if (r5 != r8) goto L_0x00b1
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r8 = r7._delegateDeserializer
            if (r2 != r8) goto L_0x00b1
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r8 = r7._valueDeserializer
            if (r3 != r8) goto L_0x00b1
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r8 = r7._valueTypeDeserializer
            if (r4 == r8) goto L_0x00b0
            goto L_0x00b1
        L_0x00b0:
            return r7
        L_0x00b1:
            r1 = r7
            com.fasterxml.jackson.databind.deser.std.CollectionDeserializer r7 = r1.withResolved(r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.CollectionDeserializer.createContextual(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.deser.std.CollectionDeserializer");
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (jsonParser.isExpectedStartArrayToken()) {
            return _deserializeFromArray(jsonParser, deserializationContext, createDefaultInstance(deserializationContext));
        }
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return _deserializeFromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        return handleNonArray(jsonParser, deserializationContext, createDefaultInstance(deserializationContext));
    }

    public CollectionDeserializer(CollectionDeserializer collectionDeserializer) {
        super((ContainerDeserializerBase<?>) collectionDeserializer);
        this._valueDeserializer = collectionDeserializer._valueDeserializer;
        this._valueTypeDeserializer = collectionDeserializer._valueTypeDeserializer;
        this._valueInstantiator = collectionDeserializer._valueInstantiator;
        this._delegateDeserializer = collectionDeserializer._delegateDeserializer;
    }

    public Collection<Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<Object> collection) throws IOException {
        if (jsonParser.isExpectedStartArrayToken()) {
            return _deserializeFromArray(jsonParser, deserializationContext, collection);
        }
        return handleNonArray(jsonParser, deserializationContext, collection);
    }
}
