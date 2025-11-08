package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExternalTypeHandler {
    private final JavaType _beanType;
    private final Map<String, Object> _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    public static class Builder {
        private final JavaType _beanType;
        private final Map<String, Object> _nameToPropertyIndex = new HashMap();
        private final List<ExtTypedProperty> _properties = new ArrayList();

        public Builder(JavaType javaType) {
            this._beanType = javaType;
        }

        private void _addPropertyIndex(String str, Integer num) {
            Object obj = this._nameToPropertyIndex.get(str);
            if (obj == null) {
                this._nameToPropertyIndex.put(str, num);
            } else if (obj instanceof List) {
                ((List) obj).add(num);
            } else {
                LinkedList linkedList = new LinkedList();
                linkedList.add(obj);
                linkedList.add(num);
                this._nameToPropertyIndex.put(str, linkedList);
            }
        }

        public void addExternal(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, typeDeserializer));
            _addPropertyIndex(settableBeanProperty.getName(), valueOf);
            _addPropertyIndex(typeDeserializer.getPropertyName(), valueOf);
        }

        public ExternalTypeHandler build(BeanPropertyMap beanPropertyMap) {
            int size = this._properties.size();
            ExtTypedProperty[] extTypedPropertyArr = new ExtTypedProperty[size];
            for (int i3 = 0; i3 < size; i3++) {
                ExtTypedProperty extTypedProperty = this._properties.get(i3);
                SettableBeanProperty find = beanPropertyMap.find(extTypedProperty.getTypePropertyName());
                if (find != null) {
                    extTypedProperty.linkTypeProperty(find);
                }
                extTypedPropertyArr[i3] = extTypedProperty;
            }
            return new ExternalTypeHandler(this._beanType, extTypedPropertyArr, this._nameToPropertyIndex, (String[]) null, (TokenBuffer[]) null);
        }
    }

    public static final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final TypeDeserializer _typeDeserializer;
        private SettableBeanProperty _typeProperty;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, TypeDeserializer typeDeserializer) {
            this._property = settableBeanProperty;
            this._typeDeserializer = typeDeserializer;
            this._typePropertyName = typeDeserializer.getPropertyName();
        }

        public String getDefaultTypeId() {
            Class<?> defaultImpl = this._typeDeserializer.getDefaultImpl();
            if (defaultImpl == null) {
                return null;
            }
            return this._typeDeserializer.getTypeIdResolver().idFromValueAndType((Object) null, defaultImpl);
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public SettableBeanProperty getTypeProperty() {
            return this._typeProperty;
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public boolean hasDefaultType() {
            return this._typeDeserializer.hasDefaultImpl();
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }

        public void linkTypeProperty(SettableBeanProperty settableBeanProperty) {
            this._typeProperty = settableBeanProperty;
        }
    }

    public ExternalTypeHandler(JavaType javaType, ExtTypedProperty[] extTypedPropertyArr, Map<String, Object> map, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._beanType = javaType;
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = map;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    private final boolean _handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj, String str2, int i3) throws IOException {
        if (!this._properties[i3].hasTypePropertyName(str)) {
            return false;
        }
        if (obj == null || this._tokens[i3] == null) {
            this._typeIds[i3] = str2;
            return true;
        }
        _deserializeAndSet(jsonParser, deserializationContext, obj, i3, str2);
        this._tokens[i3] = null;
        return true;
    }

    public static Builder builder(JavaType javaType) {
        return new Builder(javaType);
    }

    public final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, int i3, String str) throws IOException {
        JsonParser asParser = this._tokens[i3].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.copyCurrentStructure(asParser);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser2 = bufferForInputBuffering.asParser(jsonParser);
        asParser2.nextToken();
        return this._properties[i3].getProperty().deserialize(asParser2, deserializationContext);
    }

    public final void _deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i3, String str) throws IOException {
        if (str == null) {
            deserializationContext.reportInputMismatch(this._beanType, "Internal error in external Type Id handling: `null` type id passed", new Object[0]);
        }
        JsonParser asParser = this._tokens[i3].asParser(jsonParser);
        if (asParser.nextToken() == JsonToken.VALUE_NULL) {
            this._properties[i3].getProperty().set(obj, (Object) null);
            return;
        }
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.copyCurrentStructure(asParser);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser2 = bufferForInputBuffering.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i3].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    public final Object _deserializeMissingToken(JsonParser jsonParser, DeserializationContext deserializationContext, int i3, String str) throws IOException {
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        bufferForInputBuffering.writeStartArray();
        bufferForInputBuffering.writeString(str);
        bufferForInputBuffering.writeEndArray();
        JsonParser asParser = bufferForInputBuffering.asParser(jsonParser);
        asParser.nextToken();
        return this._properties[i3].getProperty().deserialize(asParser, deserializationContext);
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        int length = this._properties.length;
        for (int i3 = 0; i3 < length; i3++) {
            String str = this._typeIds[i3];
            ExtTypedProperty extTypedProperty = this._properties[i3];
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i3];
                if (tokenBuffer != null) {
                    if (tokenBuffer.firstToken().isScalarValue()) {
                        JsonParser asParser = tokenBuffer.asParser(jsonParser);
                        asParser.nextToken();
                        SettableBeanProperty property = extTypedProperty.getProperty();
                        Object deserializeIfNatural = TypeDeserializer.deserializeIfNatural(asParser, deserializationContext, property.getType());
                        if (deserializeIfNatural != null) {
                            property.set(obj, deserializeIfNatural);
                        }
                    }
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s' (and no 'defaultImpl' specified)", extTypedProperty.getTypePropertyName());
                    } else {
                        str = extTypedProperty.getDefaultTypeId();
                        if (str == null) {
                            deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Invalid default type id for property '%s': `null` returned by TypeIdResolver", extTypedProperty.getTypePropertyName());
                        }
                    }
                }
            } else if (this._tokens[i3] == null) {
                SettableBeanProperty property2 = extTypedProperty.getProperty();
                if (property2.isRequired() || deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    deserializationContext.reportPropertyInputMismatch(obj.getClass(), property2.getName(), "Missing property '%s' for external type id '%s'", property2.getName(), extTypedProperty.getTypePropertyName());
                }
                return obj;
            }
            _deserializeAndSet(jsonParser, deserializationContext, obj, i3, str);
        }
        return obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0090, code lost:
        if (r9._tokens[r0] != null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a1, code lost:
        if (r9._typeIds[r0] != null) goto L_0x00a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handlePropertyValue(com.fasterxml.jackson.core.JsonParser r10, com.fasterxml.jackson.databind.DeserializationContext r11, java.lang.String r12, java.lang.Object r13) throws java.io.IOException {
        /*
            r9 = this;
            java.util.Map<java.lang.String, java.lang.Object> r0 = r9._nameToPropertyIndex
            java.lang.Object r0 = r0.get(r12)
            if (r0 != 0) goto L_0x000a
            r9 = 0
            return r9
        L_0x000a:
            boolean r1 = r0 instanceof java.util.List
            r2 = 1
            if (r1 == 0) goto L_0x006f
            java.util.List r0 = (java.util.List) r0
            java.util.Iterator r13 = r0.iterator()
            java.lang.Object r0 = r13.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r9._properties
            int r3 = r0.intValue()
            r1 = r1[r3]
            boolean r12 = r1.hasTypePropertyName(r12)
            if (r12 == 0) goto L_0x004d
            java.lang.String r11 = r10.getText()
            r10.skipChildren()
            java.lang.String[] r10 = r9._typeIds
            int r12 = r0.intValue()
            r10[r12] = r11
        L_0x0038:
            boolean r10 = r13.hasNext()
            if (r10 == 0) goto L_0x006e
            java.lang.String[] r10 = r9._typeIds
            java.lang.Object r12 = r13.next()
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r10[r12] = r11
            goto L_0x0038
        L_0x004d:
            com.fasterxml.jackson.databind.util.TokenBuffer r10 = r11.bufferAsCopyOfValue(r10)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r11 = r9._tokens
            int r12 = r0.intValue()
            r11[r12] = r10
        L_0x0059:
            boolean r11 = r13.hasNext()
            if (r11 == 0) goto L_0x006e
            com.fasterxml.jackson.databind.util.TokenBuffer[] r11 = r9._tokens
            java.lang.Object r12 = r13.next()
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r11[r12] = r10
            goto L_0x0059
        L_0x006e:
            return r2
        L_0x006f:
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler$ExtTypedProperty[] r1 = r9._properties
            r1 = r1[r0]
            boolean r12 = r1.hasTypePropertyName(r12)
            if (r12 == 0) goto L_0x0093
            java.lang.String[] r12 = r9._typeIds
            java.lang.String r1 = r10.getValueAsString()
            r12[r0] = r1
            r10.skipChildren()
            if (r13 == 0) goto L_0x00b6
            com.fasterxml.jackson.databind.util.TokenBuffer[] r12 = r9._tokens
            r12 = r12[r0]
            if (r12 == 0) goto L_0x00b6
            goto L_0x00a3
        L_0x0093:
            com.fasterxml.jackson.databind.util.TokenBuffer r12 = r11.bufferAsCopyOfValue(r10)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r1 = r9._tokens
            r1[r0] = r12
            if (r13 == 0) goto L_0x00b6
            java.lang.String[] r12 = r9._typeIds
            r12 = r12[r0]
            if (r12 == 0) goto L_0x00b6
        L_0x00a3:
            java.lang.String[] r12 = r9._typeIds
            r8 = r12[r0]
            r1 = 0
            r12[r0] = r1
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r13
            r7 = r0
            r3._deserializeAndSet(r4, r5, r6, r7, r8)
            com.fasterxml.jackson.databind.util.TokenBuffer[] r9 = r9._tokens
            r9[r0] = r1
        L_0x00b6:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.handlePropertyValue(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.String, java.lang.Object):boolean");
    }

    public boolean handleTypePropertyValue(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) throws IOException {
        Object obj2 = this._nameToPropertyIndex.get(str);
        boolean z2 = false;
        if (obj2 == null) {
            return false;
        }
        String text = jsonParser.getText();
        if (obj2 instanceof List) {
            for (Integer intValue : (List) obj2) {
                if (_handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, intValue.intValue())) {
                    z2 = true;
                }
            }
            return z2;
        }
        return _handleTypePropertyValue(jsonParser, deserializationContext, str, obj, text, ((Integer) obj2).intValue());
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }

    public ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._beanType = externalTypeHandler._beanType;
        ExtTypedProperty[] extTypedPropertyArr = externalTypeHandler._properties;
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = extTypedPropertyArr.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, PropertyValueBuffer propertyValueBuffer, PropertyBasedCreator propertyBasedCreator) throws IOException {
        int length = this._properties.length;
        Object[] objArr = new Object[length];
        for (int i3 = 0; i3 < length; i3++) {
            String str = this._typeIds[i3];
            ExtTypedProperty extTypedProperty = this._properties[i3];
            String str2 = str;
            if (str == null) {
                TokenBuffer tokenBuffer = this._tokens[i3];
                if (!(tokenBuffer == null || tokenBuffer.firstToken() == JsonToken.VALUE_NULL)) {
                    if (!extTypedProperty.hasDefaultType()) {
                        deserializationContext.reportPropertyInputMismatch(this._beanType, extTypedProperty.getProperty().getName(), "Missing external type id property '%s'", extTypedProperty.getTypePropertyName());
                        str2 = str;
                    } else {
                        str2 = extTypedProperty.getDefaultTypeId();
                    }
                }
            }
            if (this._tokens[i3] != null) {
                objArr[i3] = _deserialize(jsonParser, deserializationContext, i3, str2);
            } else {
                if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    SettableBeanProperty property = extTypedProperty.getProperty();
                    deserializationContext.reportPropertyInputMismatch(this._beanType, property.getName(), "Missing property '%s' for external type id '%s'", property.getName(), this._properties[i3].getTypePropertyName());
                }
                objArr[i3] = _deserializeMissingToken(jsonParser, deserializationContext, i3, str2);
            }
            SettableBeanProperty property2 = extTypedProperty.getProperty();
            if (property2.getCreatorIndex() >= 0) {
                propertyValueBuffer.assignParameter(property2, objArr[i3]);
                SettableBeanProperty typeProperty = extTypedProperty.getTypeProperty();
                if (typeProperty != null && typeProperty.getCreatorIndex() >= 0) {
                    Object obj = str2;
                    if (!typeProperty.getType().hasRawClass(String.class)) {
                        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
                        bufferForInputBuffering.writeString(str2);
                        Object deserialize = typeProperty.getValueDeserializer().deserialize(bufferForInputBuffering.asParserOnFirstToken(), deserializationContext);
                        bufferForInputBuffering.close();
                        obj = deserialize;
                    }
                    propertyValueBuffer.assignParameter(typeProperty, obj);
                }
            }
        }
        Object build = propertyBasedCreator.build(deserializationContext, propertyValueBuffer);
        for (int i4 = 0; i4 < length; i4++) {
            SettableBeanProperty property3 = this._properties[i4].getProperty();
            if (property3.getCreatorIndex() < 0) {
                property3.set(build, objArr[i4]);
            }
        }
        return build;
    }
}
