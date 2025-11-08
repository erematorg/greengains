package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JacksonStdImpl
final class UntypedObjectDeserializerNR extends StdDeserializer<Object> {
    protected static final Object[] NO_OBJECTS = new Object[0];
    private static final long serialVersionUID = 1;
    public static final UntypedObjectDeserializerNR std = new UntypedObjectDeserializerNR();
    protected final boolean _nonMerging;

    public UntypedObjectDeserializerNR() {
        this(false);
    }

    private Object _deserializeAnyScalar(JsonParser jsonParser, DeserializationContext deserializationContext, int i3) throws IOException {
        switch (i3) {
            case 6:
                return jsonParser.getText();
            case 7:
                return deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS) ? jsonParser.getBigIntegerValue() : jsonParser.getNumberValue();
            case 8:
                return deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS) ? jsonParser.getDecimalValue() : jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
            default:
                return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
        }
    }

    private Object _deserializeNR(JsonParser jsonParser, DeserializationContext deserializationContext, Scope scope) throws IOException {
        Object obj;
        Object obj2;
        boolean hasSomeOfFeatures = deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS);
        boolean isEnabled = deserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
        Scope scope2 = scope;
        while (true) {
            if (scope2.isObject()) {
                String nextFieldName = jsonParser.nextFieldName();
                while (true) {
                    if (nextFieldName != null) {
                        JsonToken nextToken = jsonParser.nextToken();
                        if (nextToken == null) {
                            nextToken = JsonToken.NOT_AVAILABLE;
                        }
                        int id = nextToken.id();
                        if (id == 1) {
                            scope2 = scope2.childObject(nextFieldName);
                        } else if (id != 3) {
                            switch (id) {
                                case 6:
                                    obj2 = jsonParser.getText();
                                    break;
                                case 7:
                                    if (!hasSomeOfFeatures) {
                                        obj2 = jsonParser.getNumberValue();
                                        break;
                                    } else {
                                        obj2 = _coerceIntegral(jsonParser, deserializationContext);
                                        break;
                                    }
                                case 8:
                                    if (!deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                                        obj2 = jsonParser.getNumberValue();
                                        break;
                                    } else {
                                        obj2 = jsonParser.getDecimalValue();
                                        break;
                                    }
                                case 9:
                                    obj2 = Boolean.TRUE;
                                    break;
                                case 10:
                                    obj2 = Boolean.FALSE;
                                    break;
                                case 11:
                                    obj2 = null;
                                    break;
                                case 12:
                                    obj2 = jsonParser.getEmbeddedObject();
                                    break;
                                default:
                                    return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                            }
                            scope2.putValue(nextFieldName, obj2);
                        } else {
                            scope2 = scope2.childArray(nextFieldName);
                        }
                        nextFieldName = jsonParser.nextFieldName();
                    } else if (scope2 == scope) {
                        return scope2.finishRootObject();
                    } else {
                        scope2 = scope2.finishBranchObject();
                    }
                }
            } else {
                while (true) {
                    JsonToken nextToken2 = jsonParser.nextToken();
                    if (nextToken2 == null) {
                        nextToken2 = JsonToken.NOT_AVAILABLE;
                    }
                    switch (nextToken2.id()) {
                        case 1:
                            scope2 = scope2.childObject();
                            continue;
                        case 3:
                            scope2 = scope2.childArray();
                            continue;
                        case 4:
                            if (scope2 == scope) {
                                return scope2.finishRootArray(isEnabled);
                            }
                            scope2 = scope2.finishBranchArray(isEnabled);
                            continue;
                        case 6:
                            obj = jsonParser.getText();
                            break;
                        case 7:
                            if (!hasSomeOfFeatures) {
                                obj = jsonParser.getNumberValue();
                                break;
                            } else {
                                obj = _coerceIntegral(jsonParser, deserializationContext);
                                break;
                            }
                        case 8:
                            if (!deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                                obj = jsonParser.getNumberValue();
                                break;
                            } else {
                                obj = jsonParser.getDecimalValue();
                                break;
                            }
                        case 9:
                            obj = Boolean.TRUE;
                            break;
                        case 10:
                            obj = Boolean.FALSE;
                            break;
                        case 11:
                            obj = null;
                            break;
                        case 12:
                            obj = jsonParser.getEmbeddedObject();
                            break;
                        default:
                            return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
                    }
                    scope2.addValue(obj);
                }
            }
        }
    }

    private Object _deserializeObjectAtName(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object obj;
        Scope rootObjectScope = Scope.rootObjectScope(deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES));
        String currentName = jsonParser.currentName();
        while (currentName != null) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == null) {
                nextToken = JsonToken.NOT_AVAILABLE;
            }
            int id = nextToken.id();
            if (id == 1) {
                obj = _deserializeNR(jsonParser, deserializationContext, rootObjectScope.childObject());
            } else if (id == 2) {
                return rootObjectScope.finishRootObject();
            } else {
                obj = id != 3 ? _deserializeAnyScalar(jsonParser, deserializationContext, nextToken.id()) : _deserializeNR(jsonParser, deserializationContext, rootObjectScope.childArray());
            }
            rootObjectScope.putValue(currentName, obj);
            currentName = jsonParser.nextFieldName();
        }
        return rootObjectScope.finishRootObject();
    }

    private void _squashDups(Map<String, Object> map, String str, Object obj, Object obj2) {
        if (obj instanceof List) {
            ((List) obj).add(obj2);
            map.put(str, obj);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        map.put(str, arrayList);
    }

    public static UntypedObjectDeserializerNR instance(boolean z2) {
        return z2 ? new UntypedObjectDeserializerNR(true) : std;
    }

    public Object _mapObjectWithDups(JsonParser jsonParser, DeserializationContext deserializationContext, Map<String, Object> map, String str, Object obj, Object obj2, String str2) throws IOException {
        boolean isEnabled = deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES);
        if (isEnabled) {
            _squashDups(map, str, obj, obj2);
        }
        while (str2 != null) {
            jsonParser.nextToken();
            Object deserialize = deserialize(jsonParser, deserializationContext);
            Object put = map.put(str2, deserialize);
            if (put != null && isEnabled) {
                _squashDups(map, str2, put, deserialize);
            }
            str2 = jsonParser.nextFieldName();
        }
        return map;
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        switch (jsonParser.currentTokenId()) {
            case 1:
                return _deserializeNR(jsonParser, deserializationContext, Scope.rootObjectScope(deserializationContext.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES)));
            case 2:
                return Scope.emptyMap();
            case 3:
                return _deserializeNR(jsonParser, deserializationContext, Scope.rootArrayScope());
            case 5:
                return _deserializeObjectAtName(jsonParser, deserializationContext);
            case 6:
                return jsonParser.getText();
            case 7:
                if (deserializationContext.hasSomeOfFeatures(StdDeserializer.F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                }
                return jsonParser.getNumberValue();
            case 8:
                if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonParser.getDecimalValue();
                }
                return jsonParser.getNumberValue();
            case 9:
                return Boolean.TRUE;
            case 10:
                return Boolean.FALSE;
            case 11:
                return null;
            case 12:
                return jsonParser.getEmbeddedObject();
            default:
                return deserializationContext.handleUnexpectedToken(getValueType(deserializationContext), jsonParser);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        int currentTokenId = jsonParser.currentTokenId();
        return (currentTokenId == 1 || currentTokenId == 3 || currentTokenId == 5) ? typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext) : _deserializeAnyScalar(jsonParser, deserializationContext, jsonParser.currentTokenId());
    }

    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        if (this._nonMerging) {
            return Boolean.FALSE;
        }
        return null;
    }

    public UntypedObjectDeserializerNR(boolean z2) {
        super((Class<?>) Object.class);
        this._nonMerging = z2;
    }

    public static final class Scope {
        private Scope _child;
        private String _deferredKey;
        private boolean _isObject;
        private List<Object> _list;
        private Map<String, Object> _map;
        private final Scope _parent;
        private boolean _squashDups;

        private Scope(Scope scope) {
            this._parent = scope;
            this._isObject = false;
            this._squashDups = false;
        }

        private void _putValueHandleDups(String str, Object obj) {
            Map<String, Object> map = this._map;
            if (map == null) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                this._map = linkedHashMap;
                linkedHashMap.put(str, obj);
                return;
            }
            Object put = map.put(str, obj);
            if (put == null) {
                return;
            }
            if (put instanceof List) {
                ((List) put).add(obj);
                this._map.put(str, put);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(put);
            arrayList.add(obj);
            this._map.put(str, arrayList);
        }

        public static List<Object> emptyList() {
            return new ArrayList(2);
        }

        public static Map<String, Object> emptyMap() {
            return new LinkedHashMap(2);
        }

        private Scope resetAsArray() {
            this._isObject = false;
            return this;
        }

        private Scope resetAsObject(boolean z2) {
            this._isObject = true;
            this._squashDups = z2;
            return this;
        }

        public static Scope rootArrayScope() {
            return new Scope((Scope) null);
        }

        public static Scope rootObjectScope(boolean z2) {
            return new Scope((Scope) null, true, z2);
        }

        public void addValue(Object obj) {
            if (this._list == null) {
                this._list = new ArrayList();
            }
            this._list.add(obj);
        }

        public Scope childArray() {
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this);
            }
            return scope.resetAsArray();
        }

        public Scope childObject() {
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this, true, this._squashDups);
            }
            return scope.resetAsObject(this._squashDups);
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.List<java.lang.Object>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.util.List<java.lang.Object>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.util.List<java.lang.Object>} */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x000e, code lost:
            if (r2 == false) goto L_0x0016;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
            r0 = r0.toArray(com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.NO_OBJECTS);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
            r1._list = null;
            r2 = r0;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.Scope finishBranchArray(boolean r2) {
            /*
                r1 = this;
                java.util.List<java.lang.Object> r0 = r1._list
                if (r0 != 0) goto L_0x000e
                if (r2 == 0) goto L_0x0009
                java.lang.Object[] r2 = com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.NO_OBJECTS
                goto L_0x001a
            L_0x0009:
                java.util.List r2 = emptyList()
                goto L_0x001a
            L_0x000e:
                if (r2 == 0) goto L_0x0016
                java.lang.Object[] r2 = com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.NO_OBJECTS
                java.lang.Object[] r0 = r0.toArray(r2)
            L_0x0016:
                r2 = 0
                r1._list = r2
                r2 = r0
            L_0x001a:
                com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope r0 = r1._parent
                boolean r0 = r0.isObject()
                if (r0 == 0) goto L_0x0029
                com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope r1 = r1._parent
                com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope r1 = r1.putDeferredValue(r2)
                return r1
            L_0x0029:
                com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope r0 = r1._parent
                r0.addValue(r2)
                com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope r1 = r1._parent
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.Scope.finishBranchArray(boolean):com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR$Scope");
        }

        public Scope finishBranchObject() {
            Object obj = this._map;
            if (obj == null) {
                obj = new LinkedHashMap();
            } else {
                this._map = null;
            }
            if (this._parent.isObject()) {
                return this._parent.putDeferredValue(obj);
            }
            this._parent.addValue(obj);
            return this._parent;
        }

        public Object finishRootArray(boolean z2) {
            List<Object> list = this._list;
            return list == null ? z2 ? UntypedObjectDeserializerNR.NO_OBJECTS : emptyList() : z2 ? list.toArray(UntypedObjectDeserializerNR.NO_OBJECTS) : list;
        }

        public Object finishRootObject() {
            Map<String, Object> map = this._map;
            return map == null ? emptyMap() : map;
        }

        public boolean isObject() {
            return this._isObject;
        }

        public Scope putDeferredValue(Object obj) {
            String str = this._deferredKey;
            Objects.requireNonNull(str);
            this._deferredKey = null;
            if (this._squashDups) {
                _putValueHandleDups(str, obj);
                return this;
            }
            if (this._map == null) {
                this._map = new LinkedHashMap();
            }
            this._map.put(str, obj);
            return this;
        }

        public void putValue(String str, Object obj) {
            if (this._squashDups) {
                _putValueHandleDups(str, obj);
                return;
            }
            if (this._map == null) {
                this._map = new LinkedHashMap();
            }
            this._map.put(str, obj);
        }

        public Scope childArray(String str) {
            this._deferredKey = str;
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this);
            }
            return scope.resetAsArray();
        }

        public Scope childObject(String str) {
            this._deferredKey = str;
            Scope scope = this._child;
            if (scope == null) {
                return new Scope(this, true, this._squashDups);
            }
            return scope.resetAsObject(this._squashDups);
        }

        private Scope(Scope scope, boolean z2, boolean z3) {
            this._parent = scope;
            this._isObject = z2;
            this._squashDups = z3;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (r0 != 5) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object deserialize(com.fasterxml.jackson.core.JsonParser r5, com.fasterxml.jackson.databind.DeserializationContext r6, java.lang.Object r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r4._nonMerging
            if (r0 == 0) goto L_0x0009
            java.lang.Object r4 = r4.deserialize(r5, r6)
            return r4
        L_0x0009:
            int r0 = r5.currentTokenId()
            r1 = 1
            if (r0 == r1) goto L_0x003d
            r1 = 2
            if (r0 == r1) goto L_0x003c
            r1 = 3
            if (r0 == r1) goto L_0x001d
            r1 = 4
            if (r0 == r1) goto L_0x003c
            r1 = 5
            if (r0 == r1) goto L_0x0046
            goto L_0x006f
        L_0x001d:
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
            if (r0 != r1) goto L_0x0026
            return r7
        L_0x0026:
            boolean r0 = r7 instanceof java.util.Collection
            if (r0 == 0) goto L_0x006f
            r0 = r7
            java.util.Collection r0 = (java.util.Collection) r0
        L_0x002d:
            java.lang.Object r1 = r4.deserialize(r5, r6)
            r0.add(r1)
            com.fasterxml.jackson.core.JsonToken r1 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r2 = com.fasterxml.jackson.core.JsonToken.END_ARRAY
            if (r1 != r2) goto L_0x002d
        L_0x003c:
            return r7
        L_0x003d:
            com.fasterxml.jackson.core.JsonToken r0 = r5.nextToken()
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.END_OBJECT
            if (r0 != r1) goto L_0x0046
            return r7
        L_0x0046:
            boolean r0 = r7 instanceof java.util.Map
            if (r0 == 0) goto L_0x006f
            r0 = r7
            java.util.Map r0 = (java.util.Map) r0
            java.lang.String r1 = r5.currentName()
        L_0x0051:
            r5.nextToken()
            java.lang.Object r2 = r0.get(r1)
            if (r2 == 0) goto L_0x005f
            java.lang.Object r3 = r4.deserialize(r5, r6, r2)
            goto L_0x0063
        L_0x005f:
            java.lang.Object r3 = r4.deserialize(r5, r6)
        L_0x0063:
            if (r3 == r2) goto L_0x0068
            r0.put(r1, r3)
        L_0x0068:
            java.lang.String r1 = r5.nextFieldName()
            if (r1 != 0) goto L_0x0051
            return r7
        L_0x006f:
            java.lang.Object r4 = r4.deserialize(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializerNR.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext, java.lang.Object):java.lang.Object");
    }
}
