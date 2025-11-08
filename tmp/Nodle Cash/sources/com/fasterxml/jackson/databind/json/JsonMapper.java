package com.fasterxml.jackson.databind.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.cfg.PackageVersion;

public class JsonMapper extends ObjectMapper {
    private static final long serialVersionUID = 1;

    public JsonMapper() {
        this(new JsonFactory());
    }

    public static Builder builder() {
        return new Builder(new JsonMapper());
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    public boolean isEnabled(JsonReadFeature jsonReadFeature) {
        return isEnabled(jsonReadFeature.mappedFeature());
    }

    public Builder rebuild() {
        return new Builder(copy());
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public static class Builder extends MapperBuilder<JsonMapper, Builder> {
        public Builder(JsonMapper jsonMapper) {
            super(jsonMapper);
        }

        public Builder configure(JsonReadFeature jsonReadFeature, boolean z2) {
            if (z2) {
                ((JsonMapper) this._mapper).enable(jsonReadFeature.mappedFeature());
            } else {
                ((JsonMapper) this._mapper).disable(jsonReadFeature.mappedFeature());
            }
            return this;
        }

        public Builder disable(JsonReadFeature... jsonReadFeatureArr) {
            for (JsonReadFeature mappedFeature : jsonReadFeatureArr) {
                ((JsonMapper) this._mapper).disable(mappedFeature.mappedFeature());
            }
            return this;
        }

        public Builder enable(JsonReadFeature... jsonReadFeatureArr) {
            for (JsonReadFeature mappedFeature : jsonReadFeatureArr) {
                ((JsonMapper) this._mapper).enable(mappedFeature.mappedFeature());
            }
            return this;
        }

        public Builder configure(JsonWriteFeature jsonWriteFeature, boolean z2) {
            if (z2) {
                ((JsonMapper) this._mapper).enable(jsonWriteFeature.mappedFeature());
            } else {
                ((JsonMapper) this._mapper).disable(jsonWriteFeature.mappedFeature());
            }
            return this;
        }

        public Builder disable(JsonWriteFeature... jsonWriteFeatureArr) {
            for (JsonWriteFeature mappedFeature : jsonWriteFeatureArr) {
                ((JsonMapper) this._mapper).disable(mappedFeature.mappedFeature());
            }
            return this;
        }

        public Builder enable(JsonWriteFeature... jsonWriteFeatureArr) {
            for (JsonWriteFeature mappedFeature : jsonWriteFeatureArr) {
                ((JsonMapper) this._mapper).enable(mappedFeature.mappedFeature());
            }
            return this;
        }
    }

    public JsonMapper(JsonFactory jsonFactory) {
        super(jsonFactory);
    }

    public static Builder builder(JsonFactory jsonFactory) {
        return new Builder(new JsonMapper(jsonFactory));
    }

    public JsonMapper copy() {
        _checkInvalidCopy(JsonMapper.class);
        return new JsonMapper(this);
    }

    public boolean isEnabled(JsonWriteFeature jsonWriteFeature) {
        return isEnabled(jsonWriteFeature.mappedFeature());
    }

    public JsonMapper(JsonMapper jsonMapper) {
        super((ObjectMapper) jsonMapper);
    }
}
