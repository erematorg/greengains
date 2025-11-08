package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilder extends TSFBuilder<JsonFactory, JsonFactoryBuilder> {
    protected CharacterEscapes _characterEscapes;
    protected int _maximumNonEscapedChar;
    protected char _quoteChar;
    protected SerializableString _rootValueSeparator;

    public JsonFactoryBuilder() {
        this._quoteChar = '\"';
        this._rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._maximumNonEscapedChar = 0;
    }

    public JsonFactory build() {
        return new JsonFactory(this);
    }

    public JsonFactoryBuilder characterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public JsonFactoryBuilder highestNonEscapedChar(int i3) {
        this._maximumNonEscapedChar = i3 <= 0 ? 0 : Math.max(127, i3);
        return this;
    }

    public JsonFactoryBuilder quoteChar(char c3) {
        if (c3 <= 127) {
            this._quoteChar = c3;
            return this;
        }
        throw new IllegalArgumentException("Can only use Unicode characters up to 0x7F as quote characters");
    }

    public JsonFactoryBuilder rootValueSeparator(String str) {
        this._rootValueSeparator = str == null ? null : new SerializedString(str);
        return this;
    }

    public CharacterEscapes characterEscapes() {
        return this._characterEscapes;
    }

    public int highestNonEscapedChar() {
        return this._maximumNonEscapedChar;
    }

    public JsonFactoryBuilder rootValueSeparator(SerializableString serializableString) {
        this._rootValueSeparator = serializableString;
        return this;
    }

    public JsonFactoryBuilder configure(JsonReadFeature jsonReadFeature, boolean z2) {
        return z2 ? enable(jsonReadFeature) : disable(jsonReadFeature);
    }

    public char quoteChar() {
        return this._quoteChar;
    }

    public SerializableString rootValueSeparator() {
        return this._rootValueSeparator;
    }

    public JsonFactoryBuilder configure(JsonWriteFeature jsonWriteFeature, boolean z2) {
        return z2 ? enable(jsonWriteFeature) : disable(jsonWriteFeature);
    }

    public JsonFactoryBuilder(JsonFactory jsonFactory) {
        super(jsonFactory);
        this._quoteChar = '\"';
        this._characterEscapes = jsonFactory.getCharacterEscapes();
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
        this._maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
    }

    public JsonFactoryBuilder disable(JsonReadFeature jsonReadFeature) {
        _legacyDisable(jsonReadFeature.mappedFeature());
        return this;
    }

    public JsonFactoryBuilder enable(JsonReadFeature jsonReadFeature) {
        _legacyEnable(jsonReadFeature.mappedFeature());
        return this;
    }

    public JsonFactoryBuilder disable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        _legacyDisable(jsonReadFeature.mappedFeature());
        for (JsonReadFeature mappedFeature : jsonReadFeatureArr) {
            _legacyEnable(mappedFeature.mappedFeature());
        }
        return this;
    }

    public JsonFactoryBuilder enable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        _legacyEnable(jsonReadFeature.mappedFeature());
        enable(jsonReadFeature);
        for (JsonReadFeature mappedFeature : jsonReadFeatureArr) {
            _legacyEnable(mappedFeature.mappedFeature());
        }
        return this;
    }

    public JsonFactoryBuilder disable(JsonWriteFeature jsonWriteFeature) {
        _legacyDisable(jsonWriteFeature.mappedFeature());
        return this;
    }

    public JsonFactoryBuilder disable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        _legacyDisable(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature mappedFeature : jsonWriteFeatureArr) {
            _legacyDisable(mappedFeature.mappedFeature());
        }
        return this;
    }

    public JsonFactoryBuilder enable(JsonWriteFeature jsonWriteFeature) {
        JsonGenerator.Feature mappedFeature = jsonWriteFeature.mappedFeature();
        if (mappedFeature != null) {
            _legacyEnable(mappedFeature);
        }
        return this;
    }

    public JsonFactoryBuilder enable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        _legacyEnable(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature mappedFeature : jsonWriteFeatureArr) {
            _legacyEnable(mappedFeature.mappedFeature());
        }
        return this;
    }
}
