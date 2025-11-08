package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;

public final class SerializationConfig extends MapperConfigBase<SerializationFeature, SerializationConfig> implements Serializable {
    protected static final PrettyPrinter DEFAULT_PRETTY_PRINTER = new DefaultPrettyPrinter();
    private static final int SER_FEATURE_DEFAULTS = MapperConfig.collectFeatureDefaults(SerializationFeature.class);
    private static final long serialVersionUID = 1;
    protected final PrettyPrinter _defaultPrettyPrinter;
    protected final FilterProvider _filterProvider;
    protected final int _formatWriteFeatures;
    protected final int _formatWriteFeaturesToChange;
    protected final int _generatorFeatures;
    protected final int _generatorFeaturesToChange;
    protected final int _serFeatures;

    public SerializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides, DatatypeFeatures datatypeFeatures) {
        super(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, datatypeFeatures);
        this._serFeatures = SER_FEATURE_DEFAULTS;
        this._filterProvider = null;
        this._defaultPrettyPrinter = DEFAULT_PRETTY_PRINTER;
        this._generatorFeatures = 0;
        this._generatorFeaturesToChange = 0;
        this._formatWriteFeatures = 0;
        this._formatWriteFeaturesToChange = 0;
    }

    private SerializationConfig _withJsonWriteFeatures(FormatFeature... formatFeatureArr) {
        JsonGenerator.Feature mappedFeature;
        int i3 = this._generatorFeatures;
        int i4 = this._generatorFeaturesToChange;
        int i5 = this._formatWriteFeatures;
        int i6 = this._formatWriteFeaturesToChange;
        int i7 = i5;
        int i8 = i6;
        int i9 = i3;
        int i10 = i4;
        for (JsonWriteFeature jsonWriteFeature : formatFeatureArr) {
            int mask = jsonWriteFeature.getMask();
            i7 |= mask;
            i8 |= mask;
            if ((jsonWriteFeature instanceof JsonWriteFeature) && (mappedFeature = jsonWriteFeature.mappedFeature()) != null) {
                int mask2 = mappedFeature.getMask();
                i9 |= mask2;
                i10 |= mask2;
            }
        }
        if (this._formatWriteFeatures == i7 && this._formatWriteFeaturesToChange == i8 && this._generatorFeatures == i9 && this._generatorFeaturesToChange == i10) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i9, i10, i7, i8);
    }

    private SerializationConfig _withoutJsonWriteFeatures(FormatFeature... formatFeatureArr) {
        JsonGenerator.Feature mappedFeature;
        int i3 = this._generatorFeatures;
        int i4 = this._generatorFeaturesToChange;
        int i5 = this._formatWriteFeatures;
        int i6 = this._formatWriteFeaturesToChange;
        int i7 = i5;
        int i8 = i6;
        int i9 = i3;
        int i10 = i4;
        for (JsonWriteFeature jsonWriteFeature : formatFeatureArr) {
            int mask = jsonWriteFeature.getMask();
            i7 &= ~mask;
            i8 |= mask;
            if ((jsonWriteFeature instanceof JsonWriteFeature) && (mappedFeature = jsonWriteFeature.mappedFeature()) != null) {
                int mask2 = mappedFeature.getMask();
                i9 &= ~mask2;
                i10 |= mask2;
            }
        }
        if (this._formatWriteFeatures == i7 && this._formatWriteFeaturesToChange == i8 && this._generatorFeatures == i9 && this._generatorFeaturesToChange == i10) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i9, i10, i7, i8);
    }

    public PrettyPrinter constructDefaultPrettyPrinter() {
        PrettyPrinter prettyPrinter = this._defaultPrettyPrinter;
        return prettyPrinter instanceof Instantiatable ? (PrettyPrinter) ((Instantiatable) prettyPrinter).createInstance() : prettyPrinter;
    }

    public PrettyPrinter getDefaultPrettyPrinter() {
        return this._defaultPrettyPrinter;
    }

    public FilterProvider getFilterProvider() {
        return this._filterProvider;
    }

    public final int getSerializationFeatures() {
        return this._serFeatures;
    }

    @Deprecated
    public JsonInclude.Include getSerializationInclusion() {
        JsonInclude.Include valueInclusion = getDefaultPropertyInclusion().getValueInclusion();
        return valueInclusion == JsonInclude.Include.USE_DEFAULTS ? JsonInclude.Include.ALWAYS : valueInclusion;
    }

    public final boolean hasSerializationFeatures(int i3) {
        return (this._serFeatures & i3) == i3;
    }

    public void initialize(JsonGenerator jsonGenerator) {
        PrettyPrinter constructDefaultPrettyPrinter;
        if (SerializationFeature.INDENT_OUTPUT.enabledIn(this._serFeatures) && jsonGenerator.getPrettyPrinter() == null && (constructDefaultPrettyPrinter = constructDefaultPrettyPrinter()) != null) {
            jsonGenerator.setPrettyPrinter(constructDefaultPrettyPrinter);
        }
        boolean enabledIn = SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._serFeatures);
        int i3 = this._generatorFeaturesToChange;
        if (i3 != 0 || enabledIn) {
            int i4 = this._generatorFeatures;
            if (enabledIn) {
                int mask = JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.getMask();
                i4 |= mask;
                i3 |= mask;
            }
            jsonGenerator.overrideStdFeatures(i4, i3);
        }
        int i5 = this._formatWriteFeaturesToChange;
        if (i5 != 0) {
            jsonGenerator.overrideFormatFeatures(this._formatWriteFeatures, i5);
        }
    }

    public BeanDescription introspect(JavaType javaType) {
        return getClassIntrospector().forSerialization(this, javaType, this);
    }

    public final boolean isEnabled(SerializationFeature serializationFeature) {
        return (this._serFeatures & serializationFeature.getMask()) != 0;
    }

    public boolean useRootWrapping() {
        PropertyName propertyName = this._rootName;
        return propertyName != null ? !propertyName.isEmpty() : isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
    }

    public SerializationConfig withDefaultPrettyPrinter(PrettyPrinter prettyPrinter) {
        return this._defaultPrettyPrinter == prettyPrinter ? this : new SerializationConfig(this, prettyPrinter);
    }

    public SerializationConfig withFeatures(SerializationFeature... serializationFeatureArr) {
        int i3 = this._serFeatures;
        int i4 = i3;
        for (SerializationFeature mask : serializationFeatureArr) {
            i4 |= mask.getMask();
        }
        if (i4 == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, i4, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig withFilters(FilterProvider filterProvider) {
        return filterProvider == this._filterProvider ? this : new SerializationConfig(this, filterProvider);
    }

    @Deprecated
    public SerializationConfig withPropertyInclusion(JsonInclude.Value value) {
        this._configOverrides.setDefaultInclusion(value);
        return this;
    }

    public SerializationConfig without(SerializationFeature serializationFeature) {
        int i3 = this._serFeatures & (~serializationFeature.getMask());
        if (i3 == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, i3, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig withoutFeatures(SerializationFeature... serializationFeatureArr) {
        int i3 = this._serFeatures;
        int i4 = i3;
        for (SerializationFeature mask : serializationFeatureArr) {
            i4 &= ~mask.getMask();
        }
        if (i4 == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, i4, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public final SerializationConfig _with(DatatypeFeatures datatypeFeatures) {
        return new SerializationConfig(this, datatypeFeatures);
    }

    public final SerializationConfig _withBase(BaseSettings baseSettings) {
        return this._base == baseSettings ? this : new SerializationConfig(this, baseSettings);
    }

    public final SerializationConfig _withMapperFeatures(long j2) {
        return new SerializationConfig(this, j2, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public final boolean isEnabled(JsonGenerator.Feature feature, JsonFactory jsonFactory) {
        if ((feature.getMask() & this._generatorFeaturesToChange) != 0) {
            return (this._generatorFeatures & feature.getMask()) != 0;
        }
        return jsonFactory.isEnabled(feature);
    }

    public SerializationConfig withRootName(PropertyName propertyName) {
        if (propertyName == null) {
            if (this._rootName == null) {
                return this;
            }
        } else if (propertyName.equals(this._rootName)) {
            return this;
        }
        return new SerializationConfig(this, propertyName);
    }

    public SerializationConfig withView(Class<?> cls) {
        return this._view == cls ? this : new SerializationConfig(this, cls);
    }

    public SerializationConfig without(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        int i3 = (~serializationFeature.getMask()) & this._serFeatures;
        int i4 = i3;
        for (SerializationFeature mask : serializationFeatureArr) {
            i4 &= ~mask.getMask();
        }
        if (i4 == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, i4, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig with(SubtypeResolver subtypeResolver) {
        return subtypeResolver == this._subtypeResolver ? this : new SerializationConfig(this, subtypeResolver);
    }

    public SerializationConfig with(ContextAttributes contextAttributes) {
        return contextAttributes == this._attributes ? this : new SerializationConfig(this, contextAttributes);
    }

    public SerializationConfig withFeatures(JsonGenerator.Feature... featureArr) {
        int i3 = this._generatorFeatures;
        int i4 = this._generatorFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (JsonGenerator.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i5 |= mask2;
            i6 |= mask2;
        }
        if (this._generatorFeatures == i5 && this._generatorFeaturesToChange == i6) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i5, i6, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig withoutFeatures(JsonGenerator.Feature... featureArr) {
        int i3 = this._generatorFeatures;
        int i4 = this._generatorFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (JsonGenerator.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i5 &= ~mask2;
            i6 |= mask2;
        }
        if (this._generatorFeatures == i5 && this._generatorFeaturesToChange == i6) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i5, i6, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public final boolean isEnabled(DatatypeFeature datatypeFeature) {
        return this._datatypeFeatures.isEnabled(datatypeFeature);
    }

    public SerializationConfig with(DateFormat dateFormat) {
        SerializationConfig serializationConfig = (SerializationConfig) super.with(dateFormat);
        if (dateFormat == null) {
            return serializationConfig.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return serializationConfig.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public SerializationConfig without(JsonGenerator.Feature feature) {
        int i3 = this._generatorFeatures & (~feature.getMask());
        int mask = this._generatorFeaturesToChange | feature.getMask();
        if (this._generatorFeatures == i3 && this._generatorFeaturesToChange == mask) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, i3, mask, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    @Deprecated
    public SerializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides) {
        this(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, DatatypeFeatures.defaultFeatures());
    }

    public SerializationConfig with(SerializationFeature serializationFeature) {
        int mask = this._serFeatures | serializationFeature.getMask();
        if (mask == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, mask, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig withFeatures(FormatFeature... formatFeatureArr) {
        if (formatFeatureArr.length > 0 && (formatFeatureArr[0] instanceof JsonWriteFeature)) {
            return _withJsonWriteFeatures(formatFeatureArr);
        }
        int i3 = this._formatWriteFeatures;
        int i4 = this._formatWriteFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i5 |= mask2;
            i6 |= mask2;
        }
        if (this._formatWriteFeatures == i5 && this._formatWriteFeaturesToChange == i6) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, i5, i6);
    }

    public SerializationConfig without(FormatFeature formatFeature) {
        if (formatFeature instanceof JsonWriteFeature) {
            return _withoutJsonWriteFeatures(formatFeature);
        }
        int i3 = this._formatWriteFeatures & (~formatFeature.getMask());
        int mask = this._formatWriteFeaturesToChange | formatFeature.getMask();
        if (this._formatWriteFeatures == i3 && this._formatWriteFeaturesToChange == mask) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, i3, mask);
    }

    public SerializationConfig withoutFeatures(FormatFeature... formatFeatureArr) {
        if (formatFeatureArr.length > 0 && (formatFeatureArr[0] instanceof JsonWriteFeature)) {
            return _withoutJsonWriteFeatures(formatFeatureArr);
        }
        int i3 = this._formatWriteFeatures;
        int i4 = this._formatWriteFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i5 &= ~mask2;
            i6 |= mask2;
        }
        if (this._formatWriteFeatures == i5 && this._formatWriteFeaturesToChange == i6) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, i5, i6);
    }

    public SerializationConfig(SerializationConfig serializationConfig, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides) {
        super(serializationConfig, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    public SerializationConfig with(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        int mask = serializationFeature.getMask() | this._serFeatures;
        int i3 = mask;
        for (SerializationFeature mask2 : serializationFeatureArr) {
            i3 |= mask2.getMask();
        }
        if (i3 == this._serFeatures) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, i3, this._generatorFeatures, this._generatorFeaturesToChange, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig with(JsonGenerator.Feature feature) {
        int mask = this._generatorFeatures | feature.getMask();
        int mask2 = this._generatorFeaturesToChange | feature.getMask();
        if (this._generatorFeatures == mask && this._generatorFeaturesToChange == mask2) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, mask, mask2, this._formatWriteFeatures, this._formatWriteFeaturesToChange);
    }

    public SerializationConfig with(FormatFeature formatFeature) {
        if (formatFeature instanceof JsonWriteFeature) {
            return _withJsonWriteFeatures(formatFeature);
        }
        int mask = this._formatWriteFeatures | formatFeature.getMask();
        int mask2 = this._formatWriteFeaturesToChange | formatFeature.getMask();
        if (this._formatWriteFeatures == mask && this._formatWriteFeaturesToChange == mask2) {
            return this;
        }
        return new SerializationConfig(this, this._mapperFeatures, this._serFeatures, this._generatorFeatures, this._generatorFeaturesToChange, mask, mask2);
    }

    private SerializationConfig(SerializationConfig serializationConfig, SubtypeResolver subtypeResolver) {
        super(serializationConfig, subtypeResolver);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    private SerializationConfig(SerializationConfig serializationConfig, long j2, int i3, int i4, int i5, int i6, int i7) {
        super(serializationConfig, j2);
        this._serFeatures = i3;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = i4;
        this._generatorFeaturesToChange = i5;
        this._formatWriteFeatures = i6;
        this._formatWriteFeaturesToChange = i7;
    }

    private SerializationConfig(SerializationConfig serializationConfig, BaseSettings baseSettings) {
        super(serializationConfig, baseSettings);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    private SerializationConfig(SerializationConfig serializationConfig, FilterProvider filterProvider) {
        super(serializationConfig);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    private SerializationConfig(SerializationConfig serializationConfig, Class<?> cls) {
        super(serializationConfig, cls);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    private SerializationConfig(SerializationConfig serializationConfig, PropertyName propertyName) {
        super(serializationConfig, propertyName);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    public SerializationConfig(SerializationConfig serializationConfig, ContextAttributes contextAttributes) {
        super(serializationConfig, contextAttributes);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    public SerializationConfig(SerializationConfig serializationConfig, SimpleMixInResolver simpleMixInResolver) {
        super(serializationConfig, simpleMixInResolver);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    public SerializationConfig(SerializationConfig serializationConfig, PrettyPrinter prettyPrinter) {
        super(serializationConfig);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = prettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }

    public SerializationConfig(SerializationConfig serializationConfig, DatatypeFeatures datatypeFeatures) {
        super(serializationConfig, datatypeFeatures);
        this._serFeatures = serializationConfig._serFeatures;
        this._filterProvider = serializationConfig._filterProvider;
        this._defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        this._generatorFeatures = serializationConfig._generatorFeatures;
        this._generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        this._formatWriteFeatures = serializationConfig._formatWriteFeatures;
        this._formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
}
