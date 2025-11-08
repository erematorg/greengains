package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

class EngineKey implements Key {
    private int hashCode;
    private final int height;
    private final Object model;
    private final Options options;
    private final Class<?> resourceClass;
    private final Key signature;
    private final Class<?> transcodeClass;
    private final Map<Class<?>, Transformation<?>> transformations;
    private final int width;

    public EngineKey(Object obj, Key key, int i3, int i4, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options2) {
        this.model = Preconditions.checkNotNull(obj);
        this.signature = (Key) Preconditions.checkNotNull(key, "Signature must not be null");
        this.width = i3;
        this.height = i4;
        this.transformations = (Map) Preconditions.checkNotNull(map);
        this.resourceClass = (Class) Preconditions.checkNotNull(cls, "Resource class must not be null");
        this.transcodeClass = (Class) Preconditions.checkNotNull(cls2, "Transcode class must not be null");
        this.options = (Options) Preconditions.checkNotNull(options2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EngineKey)) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        return this.model.equals(engineKey.model) && this.signature.equals(engineKey.signature) && this.height == engineKey.height && this.width == engineKey.width && this.transformations.equals(engineKey.transformations) && this.resourceClass.equals(engineKey.resourceClass) && this.transcodeClass.equals(engineKey.transcodeClass) && this.options.equals(engineKey.options);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode2 = this.model.hashCode();
            this.hashCode = hashCode2;
            int hashCode3 = ((((this.signature.hashCode() + (hashCode2 * 31)) * 31) + this.width) * 31) + this.height;
            this.hashCode = hashCode3;
            int hashCode4 = this.transformations.hashCode() + (hashCode3 * 31);
            this.hashCode = hashCode4;
            int hashCode5 = this.resourceClass.hashCode() + (hashCode4 * 31);
            this.hashCode = hashCode5;
            int hashCode6 = this.transcodeClass.hashCode() + (hashCode5 * 31);
            this.hashCode = hashCode6;
            this.hashCode = this.options.hashCode() + (hashCode6 * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return "EngineKey{model=" + this.model + ", width=" + this.width + ", height=" + this.height + ", resourceClass=" + this.resourceClass + ", transcodeClass=" + this.transcodeClass + ", signature=" + this.signature + ", hashCode=" + this.hashCode + ", transformations=" + this.transformations + ", options=" + this.options + AbstractJsonLexerKt.END_OBJ;
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
