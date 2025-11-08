package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.core.Version;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    public void assignCurrentValue(Object obj) {
        this.delegate.assignCurrentValue(obj);
    }

    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }

    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }

    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public JsonLocation currentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public String currentName() throws IOException {
        return this.delegate.currentName();
    }

    public JsonToken currentToken() {
        return this.delegate.currentToken();
    }

    public int currentTokenId() {
        return this.delegate.currentTokenId();
    }

    public JsonLocation currentTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    public Object currentValue() {
        return this.delegate.currentValue();
    }

    public JsonParser delegate() {
        return this.delegate;
    }

    public JsonParser disable(JsonParser.Feature feature) {
        this.delegate.disable(feature);
        return this;
    }

    public JsonParser enable(JsonParser.Feature feature) {
        this.delegate.enable(feature);
        return this;
    }

    public void finishToken() throws IOException {
        this.delegate.finishToken();
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public String getCurrentName() throws IOException {
        return this.delegate.getCurrentName();
    }

    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    @Deprecated
    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }

    public Object getCurrentValue() {
        return this.delegate.getCurrentValue();
    }

    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    public int getFeatureMask() {
        return this.delegate.getFeatureMask();
    }

    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    public Object getInputSource() {
        return this.delegate.getInputSource();
    }

    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }

    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    public Number getNumberValueExact() throws IOException {
        return this.delegate.getNumberValueExact();
    }

    public Object getObjectId() throws IOException {
        return this.delegate.getObjectId();
    }

    public JsonStreamContext getParsingContext() {
        return this.delegate.getParsingContext();
    }

    public JacksonFeatureSet<StreamReadCapability> getReadCapabilities() {
        return this.delegate.getReadCapabilities();
    }

    public FormatSchema getSchema() {
        return this.delegate.getSchema();
    }

    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    public String getText() throws IOException {
        return this.delegate.getText();
    }

    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    public Object getTypeId() throws IOException {
        return this.delegate.getTypeId();
    }

    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    public boolean hasToken(JsonToken jsonToken) {
        return this.delegate.hasToken(jsonToken);
    }

    public boolean hasTokenId(int i3) {
        return this.delegate.hasTokenId(i3);
    }

    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    public boolean isExpectedNumberIntToken() {
        return this.delegate.isExpectedNumberIntToken();
    }

    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }

    public boolean isExpectedStartObjectToken() {
        return this.delegate.isExpectedStartObjectToken();
    }

    public boolean isNaN() throws IOException {
        return this.delegate.isNaN();
    }

    public JsonToken nextToken() throws IOException {
        return this.delegate.nextToken();
    }

    public JsonToken nextValue() throws IOException {
        return this.delegate.nextValue();
    }

    public void overrideCurrentName(String str) {
        this.delegate.overrideCurrentName(str);
    }

    public JsonParser overrideFormatFeatures(int i3, int i4) {
        this.delegate.overrideFormatFeatures(i3, i4);
        return this;
    }

    public JsonParser overrideStdFeatures(int i3, int i4) {
        this.delegate.overrideStdFeatures(i3, i4);
        return this;
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    public boolean requiresCustomCodec() {
        return this.delegate.requiresCustomCodec();
    }

    public void setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
    }

    public void setCurrentValue(Object obj) {
        this.delegate.setCurrentValue(obj);
    }

    @Deprecated
    public JsonParser setFeatureMask(int i3) {
        this.delegate.setFeatureMask(i3);
        return this;
    }

    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    public JsonParser skipChildren() throws IOException {
        this.delegate.skipChildren();
        return this;
    }

    public Version version() {
        return this.delegate.version();
    }

    public int getText(Writer writer) throws IOException, UnsupportedOperationException {
        return this.delegate.getText(writer);
    }

    public boolean getValueAsBoolean(boolean z2) throws IOException {
        return this.delegate.getValueAsBoolean(z2);
    }

    public double getValueAsDouble(double d2) throws IOException {
        return this.delegate.getValueAsDouble(d2);
    }

    public int getValueAsInt(int i3) throws IOException {
        return this.delegate.getValueAsInt(i3);
    }

    public long getValueAsLong(long j2) throws IOException {
        return this.delegate.getValueAsLong(j2);
    }

    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }
}
