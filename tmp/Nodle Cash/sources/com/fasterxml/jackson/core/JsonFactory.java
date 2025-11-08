package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.json.async.NonBlockingByteBufferJsonParser;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.JacksonFeature;
import java.io.CharArrayReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;

public class JsonFactory extends TokenStreamFactory implements Versioned, Serializable {
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    public static final char DEFAULT_QUOTE_CHAR = '\"';
    public static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    public static final String FORMAT_NAME_JSON = "JSON";
    private static final long serialVersionUID = 2;
    protected final transient ByteQuadsCanonicalizer _byteSymbolCanonicalizer;
    protected CharacterEscapes _characterEscapes;
    protected int _factoryFeatures;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected int _maximumNonEscapedChar;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected final char _quoteChar;
    protected final transient CharsToNameCanonicalizer _rootCharSymbols;
    protected SerializableString _rootValueSeparator;

    public enum Feature implements JacksonFeature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true),
        USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);
        
        private final boolean _defaultState;

        private Feature(boolean z2) {
            this._defaultState = z2;
        }

        public static int collectDefaults() {
            int i3 = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i3 |= feature.getMask();
                }
            }
            return i3;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i3) {
            return (getMask() & i3) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this((ObjectCodec) null);
    }

    private final boolean _isJSONFactory() {
        return getFormatName() == FORMAT_NAME_JSON;
    }

    private final void _requireJSONFactory(String str) {
        if (!_isJSONFactory()) {
            throw new UnsupportedOperationException(String.format(str, new Object[]{getFormatName()}));
        }
    }

    public static TSFBuilder<?, ?> builder() {
        return new JsonFactoryBuilder();
    }

    public void _checkInvalidCopy(Class<?> cls) {
        if (getClass() != cls) {
            throw new IllegalStateException("Failed copy(): " + getClass().getName() + " (version: " + version() + ") does not override copy(); it has to");
        }
    }

    public ContentReference _createContentReference(Object obj) {
        return ContentReference.construct(!canHandleBinaryNatively(), obj);
    }

    public IOContext _createContext(ContentReference contentReference, boolean z2) {
        if (contentReference == null) {
            contentReference = ContentReference.unknown();
        }
        return new IOContext(_getBufferRecycler(), contentReference, z2);
    }

    public JsonGenerator _createGenerator(Writer writer, IOContext iOContext) throws IOException {
        WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer, this._quoteChar);
        int i3 = this._maximumNonEscapedChar;
        if (i3 > 0) {
            writerBasedJsonGenerator.setHighestNonEscapedChar(i3);
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            writerBasedJsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator;
    }

    public IOContext _createNonBlockingContext(Object obj) {
        return new IOContext(_getBufferRecycler(), _createContentReference(obj), false);
    }

    public JsonParser _createParser(InputStream inputStream, IOContext iOContext) throws IOException {
        try {
            return new ByteSourceJsonBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
        } catch (IOException | RuntimeException e3) {
            if (iOContext.isResourceManaged()) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    e3.addSuppressed(e4);
                }
            }
            throw e3;
        }
    }

    public JsonGenerator _createUTF8Generator(OutputStream outputStream, IOContext iOContext) throws IOException {
        UTF8JsonGenerator uTF8JsonGenerator = new UTF8JsonGenerator(iOContext, this._generatorFeatures, this._objectCodec, outputStream, this._quoteChar);
        int i3 = this._maximumNonEscapedChar;
        if (i3 > 0) {
            uTF8JsonGenerator.setHighestNonEscapedChar(i3);
        }
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            uTF8JsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            uTF8JsonGenerator.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator;
    }

    public Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        return jsonEncoding == JsonEncoding.UTF8 ? new UTF8Writer(iOContext, outputStream) : new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.decorate(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.InputStream _decorate(java.io.InputStream r1, com.fasterxml.jackson.core.io.IOContext r2) throws java.io.IOException {
        /*
            r0 = this;
            com.fasterxml.jackson.core.io.InputDecorator r0 = r0._inputDecorator
            if (r0 == 0) goto L_0x000b
            java.io.InputStream r0 = r0.decorate((com.fasterxml.jackson.core.io.IOContext) r2, (java.io.InputStream) r1)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory._decorate(java.io.InputStream, com.fasterxml.jackson.core.io.IOContext):java.io.InputStream");
    }

    public BufferRecycler _getBufferRecycler() {
        return Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.enabledIn(this._factoryFeatures) ? BufferRecyclers.getBufferRecycler() : new BufferRecycler();
    }

    public boolean canHandleBinaryNatively() {
        return false;
    }

    public boolean canParseAsync() {
        return _isJSONFactory();
    }

    public boolean canUseCharArrays() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r1 = getFormatName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean canUseSchema(com.fasterxml.jackson.core.FormatSchema r2) {
        /*
            r1 = this;
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r1.getFormatName()
            if (r1 == 0) goto L_0x0015
            java.lang.String r2 = r2.getSchemaType()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0015
            r0 = 1
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory.canUseSchema(com.fasterxml.jackson.core.FormatSchema):boolean");
    }

    @Deprecated
    public final JsonFactory configure(Feature feature, boolean z2) {
        return z2 ? enable(feature) : disable(feature);
    }

    public JsonFactory copy() {
        _checkInvalidCopy(JsonFactory.class);
        return new JsonFactory(this, (ObjectCodec) null);
    }

    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(outputStream), false);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            return _createUTF8Generator(_decorate(outputStream, _createContext), _createContext);
        }
        return _createGenerator(_decorate(_createWriter(outputStream, jsonEncoding, _createContext), _createContext), _createContext);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(outputStream, jsonEncoding);
    }

    @Deprecated
    public JsonParser createJsonParser(File file) throws IOException, JsonParseException {
        return createParser(file);
    }

    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        _requireJSONFactory("Non-blocking source not (yet?) supported for this format (%s)");
        return new NonBlockingJsonParser(_createNonBlockingContext((Object) null), this._parserFeatures, this._byteSymbolCanonicalizer.makeChild(this._factoryFeatures));
    }

    public JsonParser createNonBlockingByteBufferParser() throws IOException {
        _requireJSONFactory("Non-blocking source not (yet?) supported for this format (%s)");
        return new NonBlockingByteBufferJsonParser(_createNonBlockingContext((Object) null), this._parserFeatures, this._byteSymbolCanonicalizer.makeChild(this._factoryFeatures));
    }

    public JsonParser createParser(File file) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(_createContentReference(file), true);
        return _createParser(_decorate(_fileInputStream(file), _createContext), _createContext);
    }

    @Deprecated
    public JsonFactory disable(Feature feature) {
        this._factoryFeatures = (~feature.getMask()) & this._factoryFeatures;
        return this;
    }

    @Deprecated
    public JsonFactory enable(Feature feature) {
        this._factoryFeatures = feature.getMask() | this._factoryFeatures;
        return this;
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public int getFormatGeneratorFeatures() {
        return 0;
    }

    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return FORMAT_NAME_JSON;
        }
        return null;
    }

    public int getFormatParserFeatures() {
        return 0;
    }

    public Class<? extends FormatFeature> getFormatReadFeatureType() {
        return null;
    }

    public Class<? extends FormatFeature> getFormatWriteFeatureType() {
        return null;
    }

    public final int getGeneratorFeatures() {
        return this._generatorFeatures;
    }

    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }

    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }

    public final int getParserFeatures() {
        return this._parserFeatures;
    }

    public String getRootValueSeparator() {
        SerializableString serializableString = this._rootValueSeparator;
        if (serializableString == null) {
            return null;
        }
        return serializableString.getValue();
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        if (getClass() == JsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }

    public MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }

    public final boolean isEnabled(Feature feature) {
        return (this._factoryFeatures & feature.getMask()) != 0;
    }

    public Object readResolve() {
        return new JsonFactory(this, this._objectCodec);
    }

    public TSFBuilder<?, ?> rebuild() {
        _requireJSONFactory("Factory implementation for format (%s) MUST override `rebuild()` method");
        return new JsonFactoryBuilder(this);
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    public boolean requiresPropertyOrdering() {
        return false;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    @Deprecated
    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }

    @Deprecated
    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }

    public JsonFactory setRootValueSeparator(String str) {
        this._rootValueSeparator = str == null ? null : new SerializedString(str);
        return this;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
        this._quoteChar = '\"';
    }

    public ContentReference _createContentReference(Object obj, int i3, int i4) {
        return ContentReference.construct(!canHandleBinaryNatively(), obj, i3, i4);
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z2) {
        return z2 ? enable(feature) : disable(feature);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        return createGenerator(writer);
    }

    @Deprecated
    public JsonParser createJsonParser(URL url) throws IOException, JsonParseException {
        return createParser(url);
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this._parserFeatures = (~feature.getMask()) & this._parserFeatures;
        return this;
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this._parserFeatures = feature.getMask() | this._parserFeatures;
        return this;
    }

    public final boolean isEnabled(JsonParser.Feature feature) {
        return (this._parserFeatures & feature.getMask()) != 0;
    }

    @Deprecated
    public IOContext _createContext(Object obj, boolean z2) {
        return new IOContext(_getBufferRecycler(), _createContentReference(obj), z2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.decorate(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.Reader _decorate(java.io.Reader r1, com.fasterxml.jackson.core.io.IOContext r2) throws java.io.IOException {
        /*
            r0 = this;
            com.fasterxml.jackson.core.io.InputDecorator r0 = r0._inputDecorator
            if (r0 == 0) goto L_0x000b
            java.io.Reader r0 = r0.decorate((com.fasterxml.jackson.core.io.IOContext) r2, (java.io.Reader) r1)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory._decorate(java.io.Reader, com.fasterxml.jackson.core.io.IOContext):java.io.Reader");
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z2) {
        return z2 ? enable(feature) : disable(feature);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Deprecated
    public JsonParser createJsonParser(InputStream inputStream) throws IOException, JsonParseException {
        return createParser(inputStream);
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (~feature.getMask()) & this._generatorFeatures;
        return this;
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this._generatorFeatures = feature.getMask() | this._generatorFeatures;
        return this;
    }

    public final boolean isEnabled(StreamReadFeature streamReadFeature) {
        return (this._parserFeatures & streamReadFeature.mappedFeature().getMask()) != 0;
    }

    @Deprecated
    public JsonParser createJsonParser(Reader reader) throws IOException, JsonParseException {
        return createParser(reader);
    }

    public JsonParser createParser(URL url) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(_createContentReference(url), true);
        return _createParser(_decorate(_optimizedStreamFromURL(url), _createContext), _createContext);
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (this._generatorFeatures & feature.getMask()) != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.decorate(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.DataInput _decorate(java.io.DataInput r1, com.fasterxml.jackson.core.io.IOContext r2) throws java.io.IOException {
        /*
            r0 = this;
            com.fasterxml.jackson.core.io.InputDecorator r0 = r0._inputDecorator
            if (r0 == 0) goto L_0x000b
            java.io.DataInput r0 = r0.decorate((com.fasterxml.jackson.core.io.IOContext) r2, (java.io.DataInput) r1)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory._decorate(java.io.DataInput, com.fasterxml.jackson.core.io.IOContext):java.io.DataInput");
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr) throws IOException, JsonParseException {
        return createParser(bArr);
    }

    public final boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return (this._generatorFeatures & streamWriteFeature.mappedFeature().getMask()) != 0;
    }

    public JsonParser _createParser(Reader reader, IOContext iOContext) throws IOException {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures));
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr, int i3, int i4) throws IOException, JsonParseException {
        return createParser(bArr, i3, i4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.decorate(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.OutputStream _decorate(java.io.OutputStream r1, com.fasterxml.jackson.core.io.IOContext r2) throws java.io.IOException {
        /*
            r0 = this;
            com.fasterxml.jackson.core.io.OutputDecorator r0 = r0._outputDecorator
            if (r0 == 0) goto L_0x000b
            java.io.OutputStream r0 = r0.decorate((com.fasterxml.jackson.core.io.IOContext) r2, (java.io.OutputStream) r1)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory._decorate(java.io.OutputStream, com.fasterxml.jackson.core.io.IOContext):java.io.OutputStream");
    }

    public JsonGenerator createGenerator(OutputStream outputStream) throws IOException {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Deprecated
    public JsonParser createJsonParser(String str) throws IOException, JsonParseException {
        return createParser(str);
    }

    public JsonParser createParser(InputStream inputStream) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(_createContentReference(inputStream), false);
        return _createParser(_decorate(inputStream, _createContext), _createContext);
    }

    public JsonParser _createParser(char[] cArr, int i3, int i4, IOContext iOContext, boolean z2) throws IOException {
        return new ReaderBasedJsonParser(iOContext, this._parserFeatures, (Reader) null, this._objectCodec, this._rootCharSymbols.makeChild(this._factoryFeatures), cArr, i3, i3 + i4, z2);
    }

    public JsonGenerator createGenerator(Writer writer) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(writer), false);
        return _createGenerator(_decorate(writer, _createContext), _createContext);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.decorate(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.Writer _decorate(java.io.Writer r1, com.fasterxml.jackson.core.io.IOContext r2) throws java.io.IOException {
        /*
            r0 = this;
            com.fasterxml.jackson.core.io.OutputDecorator r0 = r0._outputDecorator
            if (r0 == 0) goto L_0x000b
            java.io.Writer r0 = r0.decorate((com.fasterxml.jackson.core.io.IOContext) r2, (java.io.Writer) r1)
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.JsonFactory._decorate(java.io.Writer, com.fasterxml.jackson.core.io.IOContext):java.io.Writer");
    }

    public JsonParser createParser(Reader reader) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(_createContentReference(reader), false);
        return _createParser(_decorate(reader, _createContext), _createContext);
    }

    public JsonParser _createParser(byte[] bArr, int i3, int i4, IOContext iOContext) throws IOException {
        return new ByteSourceJsonBootstrapper(iOContext, bArr, i3, i4).constructParser(this._parserFeatures, this._objectCodec, this._byteSymbolCanonicalizer, this._rootCharSymbols, this._factoryFeatures);
    }

    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        OutputStream _fileOutputStream = _fileOutputStream(file);
        IOContext _createContext = _createContext(_createContentReference(_fileOutputStream), true);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            return _createUTF8Generator(_decorate(_fileOutputStream, _createContext), _createContext);
        }
        return _createGenerator(_decorate(_createWriter(_fileOutputStream, jsonEncoding, _createContext), _createContext), _createContext);
    }

    public JsonFactory(JsonFactory jsonFactory, ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = objectCodec;
        this._factoryFeatures = jsonFactory._factoryFeatures;
        this._parserFeatures = jsonFactory._parserFeatures;
        this._generatorFeatures = jsonFactory._generatorFeatures;
        this._inputDecorator = jsonFactory._inputDecorator;
        this._outputDecorator = jsonFactory._outputDecorator;
        this._characterEscapes = jsonFactory._characterEscapes;
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
        this._maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
        this._quoteChar = jsonFactory._quoteChar;
    }

    public JsonParser _createParser(DataInput dataInput, IOContext iOContext) throws IOException {
        _requireJSONFactory("InputData source not (yet?) supported for this format (%s)");
        int skipUTF8BOM = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        return new UTF8DataInputJsonParser(iOContext, this._parserFeatures, dataInput, this._objectCodec, this._byteSymbolCanonicalizer.makeChild(this._factoryFeatures), skipUTF8BOM);
    }

    public JsonParser createParser(byte[] bArr) throws IOException, JsonParseException {
        InputStream decorate;
        IOContext _createContext = _createContext(_createContentReference(bArr), true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator == null || (decorate = inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) == null) {
            return _createParser(bArr, 0, bArr.length, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonParser createParser(byte[] bArr, int i3, int i4) throws IOException, JsonParseException {
        InputStream decorate;
        _checkRangeBoundsForByteArray(bArr, i3, i4);
        IOContext _createContext = _createContext(_createContentReference(bArr, i3, i4), true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator == null || (decorate = inputDecorator.decorate(_createContext, bArr, i3, i4)) == null) {
            return _createParser(bArr, i3, i4, _createContext);
        }
        return _createParser(decorate, _createContext);
    }

    public JsonGenerator createGenerator(DataOutput dataOutput, JsonEncoding jsonEncoding) throws IOException {
        return createGenerator(_createDataOutputWrapper(dataOutput), jsonEncoding);
    }

    public JsonGenerator createGenerator(DataOutput dataOutput) throws IOException {
        return createGenerator(_createDataOutputWrapper(dataOutput), JsonEncoding.UTF8);
    }

    public JsonParser createParser(String str) throws IOException, JsonParseException {
        int length = str.length();
        if (this._inputDecorator != null || length > 32768 || !canUseCharArrays()) {
            return createParser((Reader) new StringReader(str));
        }
        IOContext _createContext = _createContext(_createContentReference(str), true);
        char[] allocTokenBuffer = _createContext.allocTokenBuffer(length);
        str.getChars(0, length, allocTokenBuffer, 0);
        return _createParser(allocTokenBuffer, 0, length, _createContext, true);
    }

    public JsonFactory(JsonFactoryBuilder jsonFactoryBuilder) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = null;
        this._factoryFeatures = jsonFactoryBuilder._factoryFeatures;
        this._parserFeatures = jsonFactoryBuilder._streamReadFeatures;
        this._generatorFeatures = jsonFactoryBuilder._streamWriteFeatures;
        this._inputDecorator = jsonFactoryBuilder._inputDecorator;
        this._outputDecorator = jsonFactoryBuilder._outputDecorator;
        this._characterEscapes = jsonFactoryBuilder._characterEscapes;
        this._rootValueSeparator = jsonFactoryBuilder._rootValueSeparator;
        this._maximumNonEscapedChar = jsonFactoryBuilder._maximumNonEscapedChar;
        this._quoteChar = jsonFactoryBuilder._quoteChar;
    }

    public JsonParser createParser(char[] cArr) throws IOException {
        return createParser(cArr, 0, cArr.length);
    }

    public JsonParser createParser(char[] cArr, int i3, int i4) throws IOException {
        _checkRangeBoundsForCharArray(cArr, i3, i4);
        if (this._inputDecorator != null) {
            return createParser((Reader) new CharArrayReader(cArr, i3, i4));
        }
        return _createParser(cArr, i3, i4, _createContext(_createContentReference(cArr, i3, i4), true), false);
    }

    public JsonParser createParser(DataInput dataInput) throws IOException {
        IOContext _createContext = _createContext(_createContentReference(dataInput), false);
        return _createParser(_decorate(dataInput, _createContext), _createContext);
    }

    public JsonFactory(TSFBuilder<?, ?> tSFBuilder, boolean z2) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._byteSymbolCanonicalizer = ByteQuadsCanonicalizer.createRoot();
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this._objectCodec = null;
        this._factoryFeatures = tSFBuilder._factoryFeatures;
        this._parserFeatures = tSFBuilder._streamReadFeatures;
        this._generatorFeatures = tSFBuilder._streamWriteFeatures;
        this._inputDecorator = tSFBuilder._inputDecorator;
        this._outputDecorator = tSFBuilder._outputDecorator;
        this._characterEscapes = null;
        this._rootValueSeparator = null;
        this._maximumNonEscapedChar = 0;
        this._quoteChar = '\"';
    }
}
