package com.fasterxml.jackson.databind.jsontype.impl;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AsDeductionTypeDeserializer extends AsPropertyTypeDeserializer {
    private static final BitSet EMPTY_CLASS_FINGERPRINT = new BitSet(0);
    private static final long serialVersionUID = 1;
    private final Map<String, Integer> fieldBitIndex;
    private final Map<BitSet, String> subtypeFingerprints;

    public AsDeductionTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, JavaType javaType2, DeserializationConfig deserializationConfig, Collection<NamedType> collection) {
        super(javaType, typeIdResolver, (String) null, false, javaType2, (JsonTypeInfo.As) null);
        this.fieldBitIndex = new HashMap();
        this.subtypeFingerprints = buildFingerprints(deserializationConfig, collection);
    }

    private static void prune(List<BitSet> list, int i3) {
        Iterator<BitSet> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().get(i3)) {
                it.remove();
            }
        }
    }

    public Map<BitSet, String> buildFingerprints(DeserializationConfig deserializationConfig, Collection<NamedType> collection) {
        boolean isEnabled = deserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        HashMap hashMap = new HashMap();
        int i3 = 0;
        for (NamedType next : collection) {
            List<BeanPropertyDefinition> findProperties = deserializationConfig.introspect(deserializationConfig.getTypeFactory().constructType((Type) next.getType())).findProperties();
            BitSet bitSet = new BitSet(findProperties.size() + i3);
            for (BeanPropertyDefinition name : findProperties) {
                String name2 = name.getName();
                if (isEnabled) {
                    name2 = name2.toLowerCase();
                }
                Integer num = this.fieldBitIndex.get(name2);
                if (num == null) {
                    num = Integer.valueOf(i3);
                    this.fieldBitIndex.put(name2, Integer.valueOf(i3));
                    i3++;
                }
                bitSet.set(num.intValue());
            }
            String str = (String) hashMap.put(bitSet, next.getType().getName());
            if (str != null) {
                throw new IllegalStateException(C0118y.g("Subtypes ", str, " and ", next.getType().getName(), " have the same signature and cannot be uniquely deduced."));
            }
        }
        return hashMap;
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        JsonToken currentToken = jsonParser.currentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        } else if (currentToken != JsonToken.FIELD_NAME) {
            return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, (TokenBuffer) null, "Unexpected input");
        }
        if (currentToken == JsonToken.END_OBJECT && (str = this.subtypeFingerprints.get(EMPTY_CLASS_FINGERPRINT)) != null) {
            return _deserializeTypedForId(jsonParser, deserializationContext, (TokenBuffer) null, str);
        }
        LinkedList linkedList = new LinkedList(this.subtypeFingerprints.keySet());
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        boolean isEnabled = deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.currentName();
            if (isEnabled) {
                currentName = currentName.toLowerCase();
            }
            bufferForInputBuffering.copyCurrentStructure(jsonParser);
            Integer num = this.fieldBitIndex.get(currentName);
            if (num != null) {
                prune(linkedList, num.intValue());
                if (linkedList.size() == 1) {
                    return _deserializeTypedForId(jsonParser, deserializationContext, bufferForInputBuffering, this.subtypeFingerprints.get(linkedList.get(0)));
                }
            }
            currentToken = jsonParser.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(jsonParser, deserializationContext, bufferForInputBuffering, String.format("Cannot deduce unique subtype of %s (%d candidates match)", new Object[]{ClassUtil.getTypeDescription(this._baseType), Integer.valueOf(linkedList.size())}));
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        return beanProperty == this._property ? this : new AsDeductionTypeDeserializer(this, beanProperty);
    }

    public AsDeductionTypeDeserializer(AsDeductionTypeDeserializer asDeductionTypeDeserializer, BeanProperty beanProperty) {
        super(asDeductionTypeDeserializer, beanProperty);
        this.fieldBitIndex = asDeductionTypeDeserializer.fieldBitIndex;
        this.subtypeFingerprints = asDeductionTypeDeserializer.subtypeFingerprints;
    }
}
