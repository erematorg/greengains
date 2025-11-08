package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORParser;

public class CBORFactoryBuilder extends TSFBuilder<CBORFactory, CBORFactoryBuilder> {
    protected int _formatGeneratorFeatures;
    protected int _formatParserFeatures;

    public CBORFactoryBuilder() {
        this._formatParserFeatures = CBORFactory.DEFAULT_CBOR_PARSER_FEATURE_FLAGS;
        this._formatGeneratorFeatures = CBORFactory.DEFAULT_CBOR_GENERATOR_FEATURE_FLAGS;
    }

    public CBORFactoryBuilder configure(CBORParser.Feature feature, boolean z2) {
        return z2 ? enable(feature) : disable(feature);
    }

    public CBORFactoryBuilder disable(CBORParser.Feature feature) {
        this._formatParserFeatures = (~feature.getMask()) & this._formatParserFeatures;
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder enable(CBORParser.Feature feature) {
        this._formatParserFeatures = feature.getMask() | this._formatParserFeatures;
        return (CBORFactoryBuilder) _this();
    }

    public int formatGeneratorFeaturesMask() {
        return this._formatGeneratorFeatures;
    }

    public int formatParserFeaturesMask() {
        return this._formatParserFeatures;
    }

    public CBORFactory build() {
        return new CBORFactory(this);
    }

    public CBORFactoryBuilder configure(CBORGenerator.Feature feature, boolean z2) {
        return z2 ? enable(feature) : disable(feature);
    }

    public CBORFactoryBuilder disable(CBORParser.Feature feature, CBORParser.Feature... featureArr) {
        this._formatParserFeatures = (~feature.getMask()) & this._formatParserFeatures;
        for (CBORParser.Feature mask : featureArr) {
            this._formatParserFeatures = (~mask.getMask()) & this._formatParserFeatures;
        }
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder enable(CBORParser.Feature feature, CBORParser.Feature... featureArr) {
        this._formatParserFeatures = feature.getMask() | this._formatParserFeatures;
        for (CBORParser.Feature mask : featureArr) {
            this._formatParserFeatures = mask.getMask() | this._formatParserFeatures;
        }
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder(CBORFactory cBORFactory) {
        super(cBORFactory);
        this._formatParserFeatures = cBORFactory._formatParserFeatures;
        this._formatGeneratorFeatures = cBORFactory._formatGeneratorFeatures;
    }

    public CBORFactoryBuilder disable(CBORGenerator.Feature feature) {
        this._formatGeneratorFeatures = (~feature.getMask()) & this._formatGeneratorFeatures;
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder enable(CBORGenerator.Feature feature) {
        this._formatGeneratorFeatures = feature.getMask() | this._formatGeneratorFeatures;
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder disable(CBORGenerator.Feature feature, CBORGenerator.Feature... featureArr) {
        this._formatGeneratorFeatures = (~feature.getMask()) & this._formatGeneratorFeatures;
        for (CBORGenerator.Feature mask : featureArr) {
            this._formatGeneratorFeatures = (~mask.getMask()) & this._formatGeneratorFeatures;
        }
        return (CBORFactoryBuilder) _this();
    }

    public CBORFactoryBuilder enable(CBORGenerator.Feature feature, CBORGenerator.Feature... featureArr) {
        this._formatGeneratorFeatures = feature.getMask() | this._formatGeneratorFeatures;
        for (CBORGenerator.Feature mask : featureArr) {
            this._formatGeneratorFeatures = mask.getMask() | this._formatGeneratorFeatures;
        }
        return (CBORFactoryBuilder) _this();
    }
}
