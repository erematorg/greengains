package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

public class CBORFactory extends JsonFactory {
    static final int DEFAULT_CBOR_GENERATOR_FEATURE_FLAGS = CBORGenerator.Feature.collectDefaults();
    static final int DEFAULT_CBOR_PARSER_FEATURE_FLAGS = CBORParser.Feature.collectDefaults();
    public static final String FORMAT_NAME = "CBOR";
    private static final long serialVersionUID = 1;
    protected int _formatGeneratorFeatures;
    protected int _formatParserFeatures;

    public CBORFactory() {
        this((ObjectCodec) null);
    }

    private final CBORGenerator _createCBORGenerator(IOContext iOContext, int i3, int i4, ObjectCodec objectCodec, OutputStream outputStream) throws IOException {
        CBORGenerator cBORGenerator = new CBORGenerator(iOContext, i3, i4, this._objectCodec, outputStream);
        if (CBORGenerator.Feature.WRITE_TYPE_HEADER.enabledIn(i4)) {
            cBORGenerator.writeTag(CBORConstants.TAG_ID_SELF_DESCRIBE);
        }
        return cBORGenerator;
    }

    public static CBORFactoryBuilder builder() {
        return new CBORFactoryBuilder();
    }

    public IOContext _createContext(ContentReference contentReference, boolean z2) {
        return super._createContext(contentReference, z2);
    }

    public Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        return (Writer) _nonByteTarget();
    }

    public <T> T _nonByteSource() {
        throw new UnsupportedOperationException("Can not create parser for non-byte-based source");
    }

    public <T> T _nonByteTarget() {
        throw new UnsupportedOperationException("Can not create generator for non-byte-based target");
    }

    public boolean canHandleBinaryNatively() {
        return true;
    }

    public boolean canUseCharArrays() {
        return false;
    }

    public final CBORFactory configure(CBORParser.Feature feature, boolean z2) {
        if (z2) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public CBORFactory disable(CBORParser.Feature feature) {
        this._formatParserFeatures = (~feature.getMask()) & this._formatParserFeatures;
        return this;
    }

    public CBORFactory enable(CBORParser.Feature feature) {
        this._formatParserFeatures = feature.getMask() | this._formatParserFeatures;
        return this;
    }

    public int getFormatGeneratorFeatures() {
        return this._formatGeneratorFeatures;
    }

    public String getFormatName() {
        return FORMAT_NAME;
    }

    public int getFormatParserFeatures() {
        return this._formatParserFeatures;
    }

    public Class<CBORParser.Feature> getFormatReadFeatureType() {
        return CBORParser.Feature.class;
    }

    public Class<CBORGenerator.Feature> getFormatWriteFeatureType() {
        return CBORGenerator.Feature.class;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        return CBORParserBootstrapper.hasCBORFormat(inputAccessor);
    }

    public final boolean isEnabled(CBORParser.Feature feature) {
        return (this._formatParserFeatures & feature.getMask()) != 0;
    }

    public Object readResolve() {
        return new CBORFactory(this, this._objectCodec);
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public CBORFactory(ObjectCodec objectCodec) {
        super(objectCodec);
        this._formatParserFeatures = DEFAULT_CBOR_PARSER_FEATURE_FLAGS;
        this._formatGeneratorFeatures = DEFAULT_CBOR_GENERATOR_FEATURE_FLAGS;
    }

    public CBORGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        return (CBORGenerator) _nonByteTarget();
    }

    public CBORGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        return _createCBORGenerator(iOContext, this._generatorFeatures, this._formatGeneratorFeatures, this._objectCodec, outputStream);
    }

    public CBORFactory copy() {
        _checkInvalidCopy(CBORFactory.class);
        return new CBORFactory(this, (ObjectCodec) null);
    }

    public CBORFactory disable(CBORGenerator.Feature feature) {
        this._formatGeneratorFeatures = (~feature.getMask()) & this._formatGeneratorFeatures;
        return this;
    }

    public CBORFactory enable(CBORGenerator.Feature feature) {
        this._formatGeneratorFeatures = feature.getMask() | this._formatGeneratorFeatures;
        return this;
    }

    public final boolean isEnabled(CBORGenerator.Feature feature) {
        return (this._formatGeneratorFeatures & feature.getMask()) != 0;
    }

    public CBORFactoryBuilder rebuild() {
        return new CBORFactoryBuilder(this);
    }

    public CBORParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException {
        return new CBORParserBootstrapper(iOContext, inputStream).constructParser(this._factoryFeatures, this._parserFeatures, this._formatParserFeatures, this._objectCodec, this._byteSymbolCanonicalizer);
    }

    public final CBORFactory configure(CBORGenerator.Feature feature, boolean z2) {
        if (z2) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public CBORGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(outputStream), false);
        return _createCBORGenerator(_createContext, this._generatorFeatures, this._formatGeneratorFeatures, this._objectCodec, _decorate(outputStream, _createContext));
    }

    public JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException {
        return (JsonParser) _nonByteSource();
    }

    public CBORFactory(CBORFactory cBORFactory, ObjectCodec objectCodec) {
        super((JsonFactory) cBORFactory, objectCodec);
        this._formatParserFeatures = cBORFactory._formatParserFeatures;
        this._formatGeneratorFeatures = cBORFactory._formatGeneratorFeatures;
    }

    public JsonParser _createParser(char[] cArr, int i3, int i4, IOContext iOContext, boolean z2) throws IOException {
        return (JsonParser) _nonByteSource();
    }

    public CBORParser _createParser(byte[] bArr, int i3, int i4, IOContext iOContext) throws IOException {
        return new CBORParserBootstrapper(iOContext, bArr, i3, i4).constructParser(this._factoryFeatures, this._parserFeatures, this._formatParserFeatures, this._objectCodec, this._byteSymbolCanonicalizer);
    }

    public CBORParser createParser(File file) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(file), true);
        return _createParser(_decorate((InputStream) new FileInputStream(file), _createContext), _createContext);
    }

    public CBORGenerator createGenerator(OutputStream outputStream) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(outputStream), false);
        return _createCBORGenerator(_createContext, this._generatorFeatures, this._formatGeneratorFeatures, this._objectCodec, _decorate(outputStream, _createContext));
    }

    public CBORFactory(CBORFactoryBuilder cBORFactoryBuilder) {
        super((TSFBuilder<?, ?>) cBORFactoryBuilder, false);
        this._formatParserFeatures = cBORFactoryBuilder.formatParserFeaturesMask();
        this._formatGeneratorFeatures = cBORFactoryBuilder.formatGeneratorFeaturesMask();
    }

    public CBORParser createParser(URL url) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(url), true);
        return _createParser(_decorate(_optimizedStreamFromURL(url), _createContext), _createContext);
    }

    public CBORParser createParser(InputStream inputStream) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(inputStream), false);
        return _createParser(_decorate(inputStream, _createContext), _createContext);
    }

    public CBORParser createParser(byte[] bArr) throws IOException {
        return createParser(bArr, 0, bArr.length);
    }

    public CBORParser createParser(byte[] bArr, int i3, int i4) throws IOException {
        InputStream decorate;
        IOContext _createContext = _createContext(_createContentReference(bArr, i3, i4), true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator == null || (decorate = inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) == null) {
            return _createParser(bArr, i3, i4, _createContext);
        }
        return _createParser(decorate, _createContext);
    }
}
