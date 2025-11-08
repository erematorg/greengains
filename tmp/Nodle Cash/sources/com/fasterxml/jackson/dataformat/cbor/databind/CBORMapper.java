package com.fasterxml.jackson.dataformat.cbor.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import com.fasterxml.jackson.dataformat.cbor.PackageVersion;

public class CBORMapper extends ObjectMapper {
    private static final long serialVersionUID = 1;

    public static class Builder extends MapperBuilder<CBORMapper, Builder> {
        protected final CBORFactory _streamFactory;

        public Builder(CBORMapper cBORMapper) {
            super(cBORMapper);
            this._streamFactory = cBORMapper.getFactory();
        }

        public Builder configure(CBORGenerator.Feature feature, boolean z2) {
            if (z2) {
                this._streamFactory.enable(feature);
            } else {
                this._streamFactory.disable(feature);
            }
            return this;
        }

        public Builder disable(CBORGenerator.Feature... featureArr) {
            for (CBORGenerator.Feature disable : featureArr) {
                this._streamFactory.disable(disable);
            }
            return this;
        }

        public Builder enable(CBORGenerator.Feature... featureArr) {
            for (CBORGenerator.Feature enable : featureArr) {
                this._streamFactory.enable(enable);
            }
            return this;
        }
    }

    public CBORMapper() {
        this(new CBORFactory());
    }

    public static Builder builder() {
        return new Builder(new CBORMapper());
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public CBORMapper(CBORFactory cBORFactory) {
        super((JsonFactory) cBORFactory);
    }

    public static Builder builder(CBORFactory cBORFactory) {
        return new Builder(new CBORMapper(cBORFactory));
    }

    public CBORMapper copy() {
        _checkInvalidCopy(CBORMapper.class);
        return new CBORMapper(this);
    }

    public CBORFactory getFactory() {
        return (CBORFactory) this._jsonFactory;
    }

    public CBORMapper(CBORMapper cBORMapper) {
        super((ObjectMapper) cBORMapper);
    }
}
