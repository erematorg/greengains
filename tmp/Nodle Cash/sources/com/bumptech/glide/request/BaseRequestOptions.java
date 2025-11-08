package com.bumptech.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import com.bumptech.glide.load.resource.gif.GifOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.Map;

public abstract class BaseRequestOptions<T extends BaseRequestOptions<T>> implements Cloneable {
    private static final int DISK_CACHE_STRATEGY = 4;
    private static final int ERROR_ID = 32;
    private static final int ERROR_PLACEHOLDER = 16;
    private static final int FALLBACK = 8192;
    private static final int FALLBACK_ID = 16384;
    private static final int IS_CACHEABLE = 256;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    private static final int OVERRIDE = 512;
    private static final int PLACEHOLDER = 64;
    private static final int PLACEHOLDER_ID = 128;
    private static final int PRIORITY = 8;
    private static final int RESOURCE_CLASS = 4096;
    private static final int SIGNATURE = 1024;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int THEME = 32768;
    private static final int TRANSFORMATION = 2048;
    private static final int TRANSFORMATION_ALLOWED = 65536;
    private static final int TRANSFORMATION_REQUIRED = 131072;
    private static final int UNSET = -1;
    private static final int USE_ANIMATION_POOL = 1048576;
    private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    @NonNull
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
    private int errorId;
    @Nullable
    private Drawable errorPlaceholder;
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int fields;
    private boolean isAutoCloneEnabled;
    private boolean isCacheable = true;
    private boolean isLocked;
    private boolean isScaleOnlyOrNoTransform = true;
    private boolean isTransformationAllowed = true;
    private boolean isTransformationRequired;
    private boolean onlyRetrieveFromCache;
    @NonNull
    private Options options = new Options();
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderId;
    @NonNull
    private Priority priority = Priority.NORMAL;
    @NonNull
    private Class<?> resourceClass = Object.class;
    @NonNull
    private Key signature = EmptySignature.obtain();
    private float sizeMultiplier = 1.0f;
    @Nullable
    private Resources.Theme theme;
    @NonNull
    private Map<Class<?>, Transformation<?>> transformations = new CachedHashCodeArrayMap();
    private boolean useAnimationPool;
    private boolean useUnlimitedSourceGeneratorsPool;

    private static boolean isSet(int i3, int i4) {
        return (i3 & i4) != 0;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T optionalScaleOnlyTransform(@androidx.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.scaleOnlyTransform(r2, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalScaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(@androidx.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.scaleOnlyTransform(r2, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    private T self() {
        return this;
    }

    @CheckResult
    @NonNull
    public T apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        if (this.isAutoCloneEnabled) {
            return clone().apply(baseRequestOptions);
        }
        if (isSet(baseRequestOptions.fields, 2)) {
            this.sizeMultiplier = baseRequestOptions.sizeMultiplier;
        }
        if (isSet(baseRequestOptions.fields, 262144)) {
            this.useUnlimitedSourceGeneratorsPool = baseRequestOptions.useUnlimitedSourceGeneratorsPool;
        }
        if (isSet(baseRequestOptions.fields, 1048576)) {
            this.useAnimationPool = baseRequestOptions.useAnimationPool;
        }
        if (isSet(baseRequestOptions.fields, 4)) {
            this.diskCacheStrategy = baseRequestOptions.diskCacheStrategy;
        }
        if (isSet(baseRequestOptions.fields, 8)) {
            this.priority = baseRequestOptions.priority;
        }
        if (isSet(baseRequestOptions.fields, 16)) {
            this.errorPlaceholder = baseRequestOptions.errorPlaceholder;
            this.errorId = 0;
            this.fields &= -33;
        }
        if (isSet(baseRequestOptions.fields, 32)) {
            this.errorId = baseRequestOptions.errorId;
            this.errorPlaceholder = null;
            this.fields &= -17;
        }
        if (isSet(baseRequestOptions.fields, 64)) {
            this.placeholderDrawable = baseRequestOptions.placeholderDrawable;
            this.placeholderId = 0;
            this.fields &= -129;
        }
        if (isSet(baseRequestOptions.fields, 128)) {
            this.placeholderId = baseRequestOptions.placeholderId;
            this.placeholderDrawable = null;
            this.fields &= -65;
        }
        if (isSet(baseRequestOptions.fields, 256)) {
            this.isCacheable = baseRequestOptions.isCacheable;
        }
        if (isSet(baseRequestOptions.fields, 512)) {
            this.overrideWidth = baseRequestOptions.overrideWidth;
            this.overrideHeight = baseRequestOptions.overrideHeight;
        }
        if (isSet(baseRequestOptions.fields, 1024)) {
            this.signature = baseRequestOptions.signature;
        }
        if (isSet(baseRequestOptions.fields, 4096)) {
            this.resourceClass = baseRequestOptions.resourceClass;
        }
        if (isSet(baseRequestOptions.fields, 8192)) {
            this.fallbackDrawable = baseRequestOptions.fallbackDrawable;
            this.fallbackId = 0;
            this.fields &= -16385;
        }
        if (isSet(baseRequestOptions.fields, 16384)) {
            this.fallbackId = baseRequestOptions.fallbackId;
            this.fallbackDrawable = null;
            this.fields &= -8193;
        }
        if (isSet(baseRequestOptions.fields, 32768)) {
            this.theme = baseRequestOptions.theme;
        }
        if (isSet(baseRequestOptions.fields, 65536)) {
            this.isTransformationAllowed = baseRequestOptions.isTransformationAllowed;
        }
        if (isSet(baseRequestOptions.fields, 131072)) {
            this.isTransformationRequired = baseRequestOptions.isTransformationRequired;
        }
        if (isSet(baseRequestOptions.fields, 2048)) {
            this.transformations.putAll(baseRequestOptions.transformations);
            this.isScaleOnlyOrNoTransform = baseRequestOptions.isScaleOnlyOrNoTransform;
        }
        if (isSet(baseRequestOptions.fields, 524288)) {
            this.onlyRetrieveFromCache = baseRequestOptions.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            int i3 = this.fields;
            this.isTransformationRequired = false;
            this.fields = i3 & -133121;
            this.isScaleOnlyOrNoTransform = true;
        }
        this.fields |= baseRequestOptions.fields;
        this.options.putAll(baseRequestOptions.options);
        return selfOrThrowIfLocked();
    }

    @NonNull
    public T autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            return lock();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    @CheckResult
    @NonNull
    public T centerCrop() {
        return transform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T centerInside() {
        return scaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T circleCrop() {
        return transform(DownsampleStrategy.CENTER_INSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    @CheckResult
    @NonNull
    public T decode(@NonNull Class<?> cls) {
        if (this.isAutoCloneEnabled) {
            return clone().decode(cls);
        }
        this.resourceClass = (Class) Preconditions.checkNotNull(cls);
        this.fields |= 4096;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T disallowHardwareConfig() {
        return set(Downsampler.ALLOW_HARDWARE_CONFIG, Boolean.FALSE);
    }

    @CheckResult
    @NonNull
    public T diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy2) {
        if (this.isAutoCloneEnabled) {
            return clone().diskCacheStrategy(diskCacheStrategy2);
        }
        this.diskCacheStrategy = (DiskCacheStrategy) Preconditions.checkNotNull(diskCacheStrategy2);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T dontAnimate() {
        return set(GifOptions.DISABLE_ANIMATION, Boolean.TRUE);
    }

    @CheckResult
    @NonNull
    public T dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().dontTransform();
        }
        this.transformations.clear();
        int i3 = this.fields;
        this.isTransformationRequired = false;
        this.isTransformationAllowed = false;
        this.fields = (i3 & -133121) | 65536;
        this.isScaleOnlyOrNoTransform = true;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T downsample(@NonNull DownsampleStrategy downsampleStrategy) {
        return set(DownsampleStrategy.OPTION, Preconditions.checkNotNull(downsampleStrategy));
    }

    @CheckResult
    @NonNull
    public T encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(compressFormat));
    }

    @CheckResult
    @NonNull
    public T encodeQuality(@IntRange(from = 0, to = 100) int i3) {
        return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(i3));
    }

    public boolean equals(Object obj) {
        if (obj instanceof BaseRequestOptions) {
            return isEquivalentTo((BaseRequestOptions) obj);
        }
        return false;
    }

    @CheckResult
    @NonNull
    public T error(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        this.errorId = 0;
        this.fields = (this.fields | 16) & -33;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T fallback(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        this.fallbackId = 0;
        this.fields = (this.fields | 8192) & -16385;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T fitCenter() {
        return scaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    @CheckResult
    @NonNull
    public T format(@NonNull DecodeFormat decodeFormat) {
        Preconditions.checkNotNull(decodeFormat);
        return set(Downsampler.DECODE_FORMAT, decodeFormat).set(GifOptions.DECODE_FORMAT, decodeFormat);
    }

    @CheckResult
    @NonNull
    public T frame(@IntRange(from = 0) long j2) {
        return set(VideoDecoder.TARGET_FRAME, Long.valueOf(j2));
    }

    @NonNull
    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    @NonNull
    public final Options getOptions() {
        return this.options;
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    @NonNull
    public final Priority getPriority() {
        return this.priority;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    @NonNull
    public final Key getSignature() {
        return this.signature;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    @Nullable
    public final Resources.Theme getTheme() {
        return this.theme;
    }

    @NonNull
    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean getUseAnimationPool() {
        return this.useAnimationPool;
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public int hashCode() {
        return Util.hashCode((Object) this.theme, Util.hashCode((Object) this.signature, Util.hashCode((Object) this.resourceClass, Util.hashCode((Object) this.transformations, Util.hashCode((Object) this.options, Util.hashCode((Object) this.priority, Util.hashCode((Object) this.diskCacheStrategy, Util.hashCode(this.onlyRetrieveFromCache, Util.hashCode(this.useUnlimitedSourceGeneratorsPool, Util.hashCode(this.isTransformationAllowed, Util.hashCode(this.isTransformationRequired, Util.hashCode(this.overrideWidth, Util.hashCode(this.overrideHeight, Util.hashCode(this.isCacheable, Util.hashCode((Object) this.fallbackDrawable, Util.hashCode(this.fallbackId, Util.hashCode((Object) this.placeholderDrawable, Util.hashCode(this.placeholderId, Util.hashCode((Object) this.errorPlaceholder, Util.hashCode(this.errorId, Util.hashCode(this.sizeMultiplier)))))))))))))))))))));
    }

    public final boolean isAutoCloneEnabled() {
        return this.isAutoCloneEnabled;
    }

    public final boolean isDiskCacheStrategySet() {
        return isSet(4);
    }

    public final boolean isEquivalentTo(BaseRequestOptions<?> baseRequestOptions) {
        return Float.compare(baseRequestOptions.sizeMultiplier, this.sizeMultiplier) == 0 && this.errorId == baseRequestOptions.errorId && Util.bothNullOrEqual(this.errorPlaceholder, baseRequestOptions.errorPlaceholder) && this.placeholderId == baseRequestOptions.placeholderId && Util.bothNullOrEqual(this.placeholderDrawable, baseRequestOptions.placeholderDrawable) && this.fallbackId == baseRequestOptions.fallbackId && Util.bothNullOrEqual(this.fallbackDrawable, baseRequestOptions.fallbackDrawable) && this.isCacheable == baseRequestOptions.isCacheable && this.overrideHeight == baseRequestOptions.overrideHeight && this.overrideWidth == baseRequestOptions.overrideWidth && this.isTransformationRequired == baseRequestOptions.isTransformationRequired && this.isTransformationAllowed == baseRequestOptions.isTransformationAllowed && this.useUnlimitedSourceGeneratorsPool == baseRequestOptions.useUnlimitedSourceGeneratorsPool && this.onlyRetrieveFromCache == baseRequestOptions.onlyRetrieveFromCache && this.diskCacheStrategy.equals(baseRequestOptions.diskCacheStrategy) && this.priority == baseRequestOptions.priority && this.options.equals(baseRequestOptions.options) && this.transformations.equals(baseRequestOptions.transformations) && this.resourceClass.equals(baseRequestOptions.resourceClass) && Util.bothNullOrEqual(this.signature, baseRequestOptions.signature) && Util.bothNullOrEqual(this.theme, baseRequestOptions.theme);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    public final boolean isSkipMemoryCacheSet() {
        return isSet(256);
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    public final boolean isTransformationSet() {
        return isSet(2048);
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.overrideWidth, this.overrideHeight);
    }

    @NonNull
    public T lock() {
        this.isLocked = true;
        return self();
    }

    @CheckResult
    @NonNull
    public T onlyRetrieveFromCache(boolean z2) {
        if (this.isAutoCloneEnabled) {
            return clone().onlyRetrieveFromCache(z2);
        }
        this.onlyRetrieveFromCache = z2;
        this.fields |= 524288;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CenterCrop());
    }

    @CheckResult
    @NonNull
    public T optionalCenterInside() {
        return optionalScaleOnlyTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    @CheckResult
    @NonNull
    public T optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, (Transformation<Bitmap>) new CircleCrop());
    }

    @CheckResult
    @NonNull
    public T optionalFitCenter() {
        return optionalScaleOnlyTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T optionalTransform(@androidx.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.optionalTransform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r1
        L_0x000d:
            r1.downsample(r2)
            r2 = 0
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T override(int i3, int i4) {
        if (this.isAutoCloneEnabled) {
            return clone().override(i3, i4);
        }
        this.overrideWidth = i3;
        this.overrideHeight = i4;
        this.fields |= 512;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T placeholder(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        this.placeholderId = 0;
        this.fields = (this.fields | 64) & -129;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T priority(@NonNull Priority priority2) {
        if (this.isAutoCloneEnabled) {
            return clone().priority(priority2);
        }
        this.priority = (Priority) Preconditions.checkNotNull(priority2);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    public T removeOption(@NonNull Option<?> option) {
        if (this.isAutoCloneEnabled) {
            return clone().removeOption(option);
        }
        this.options.remove(option);
        return selfOrThrowIfLocked();
    }

    @NonNull
    public final T selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return self();
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Option, java.lang.Object, com.bumptech.glide.load.Option<Y>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T set(@androidx.annotation.NonNull com.bumptech.glide.load.Option<Y> r2, @androidx.annotation.NonNull Y r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.set(r2, r3)
            return r1
        L_0x000d:
            com.bumptech.glide.util.Preconditions.checkNotNull(r2)
            com.bumptech.glide.util.Preconditions.checkNotNull(r3)
            com.bumptech.glide.load.Options r0 = r1.options
            r0.set(r2, r3)
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.selfOrThrowIfLocked()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.set(com.bumptech.glide.load.Option, java.lang.Object):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T signature(@NonNull Key key) {
        if (this.isAutoCloneEnabled) {
            return clone().signature(key);
        }
        this.signature = (Key) Preconditions.checkNotNull(key);
        this.fields |= 1024;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (this.isAutoCloneEnabled) {
            return clone().sizeMultiplier(f2);
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = f2;
        this.fields |= 2;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T skipMemoryCache(boolean z2) {
        if (this.isAutoCloneEnabled) {
            return clone().skipMemoryCache(true);
        }
        this.isCacheable = !z2;
        this.fields |= 256;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T theme(@Nullable Resources.Theme theme2) {
        if (this.isAutoCloneEnabled) {
            return clone().theme(theme2);
        }
        this.theme = theme2;
        if (theme2 != null) {
            this.fields |= 32768;
            return set(ResourceDrawableDecoder.THEME, theme2);
        }
        this.fields &= -32769;
        return removeOption(ResourceDrawableDecoder.THEME);
    }

    @CheckResult
    @NonNull
    public T timeout(@IntRange(from = 0) int i3) {
        return set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(i3));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T transform(@androidx.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r2, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r1
        L_0x000d:
            r1.downsample(r2)
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    @Deprecated
    public T transforms(@NonNull Transformation<Bitmap>... transformationArr) {
        return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformationArr), true);
    }

    @CheckResult
    @NonNull
    public T useAnimationPool(boolean z2) {
        if (this.isAutoCloneEnabled) {
            return clone().useAnimationPool(z2);
        }
        this.useAnimationPool = z2;
        this.fields |= 1048576;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T useUnlimitedSourceGeneratorsPool(boolean z2) {
        if (this.isAutoCloneEnabled) {
            return clone().useUnlimitedSourceGeneratorsPool(z2);
        }
        this.useUnlimitedSourceGeneratorsPool = z2;
        this.fields |= 262144;
        return selfOrThrowIfLocked();
    }

    private boolean isSet(int i3) {
        return isSet(this.fields, i3);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T scaleOnlyTransform(@androidx.annotation.NonNull com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r1, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2, boolean r3) {
        /*
            r0 = this;
            if (r3 == 0) goto L_0x0007
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.transform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r1, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
            goto L_0x000b
        L_0x0007:
            com.bumptech.glide.request.BaseRequestOptions r0 = r0.optionalTransform((com.bumptech.glide.load.resource.bitmap.DownsampleStrategy) r1, (com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2)
        L_0x000b:
            r1 = 1
            r0.isScaleOnlyOrNoTransform = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.scaleOnlyTransform(com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    public T clone() {
        try {
            T t2 = (BaseRequestOptions) super.clone();
            Options options2 = new Options();
            t2.options = options2;
            options2.putAll(this.options);
            CachedHashCodeArrayMap cachedHashCodeArrayMap = new CachedHashCodeArrayMap();
            t2.transformations = cachedHashCodeArrayMap;
            cachedHashCodeArrayMap.putAll(this.transformations);
            t2.isLocked = false;
            t2.isAutoCloneEnabled = false;
            return t2;
        } catch (CloneNotSupportedException e3) {
            throw new RuntimeException(e3);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T optionalTransform(@androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(@androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r2) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r2, (boolean) r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T optionalTransform(@androidx.annotation.NonNull java.lang.Class<Y> r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 0
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform(r2, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.optionalTransform(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }

    @CheckResult
    @NonNull
    public T transform(@NonNull Transformation<Bitmap>... transformationArr) {
        if (transformationArr.length > 1) {
            return transform((Transformation<Bitmap>) new MultiTransformation((Transformation<T>[]) transformationArr), true);
        }
        if (transformationArr.length == 1) {
            return transform((Transformation<Bitmap>) transformationArr[0]);
        }
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T override(int i3) {
        return override(i3, i3);
    }

    @CheckResult
    @NonNull
    public T error(@DrawableRes int i3) {
        if (this.isAutoCloneEnabled) {
            return clone().error(i3);
        }
        this.errorId = i3;
        this.errorPlaceholder = null;
        this.fields = (this.fields | 32) & -17;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T fallback(@DrawableRes int i3) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(i3);
        }
        this.fallbackId = i3;
        this.fallbackDrawable = null;
        this.fields = (this.fields | 16384) & -8193;
        return selfOrThrowIfLocked();
    }

    @CheckResult
    @NonNull
    public T placeholder(@DrawableRes int i3) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(i3);
        }
        this.placeholderId = i3;
        this.placeholderDrawable = null;
        this.fields = (this.fields | 128) & -65;
        return selfOrThrowIfLocked();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T transform(@androidx.annotation.NonNull com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r3, boolean r4) {
        /*
            r2 = this;
            boolean r0 = r2.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.clone()
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r3, (boolean) r4)
            return r2
        L_0x000d:
            com.bumptech.glide.load.resource.bitmap.DrawableTransformation r0 = new com.bumptech.glide.load.resource.bitmap.DrawableTransformation
            r0.<init>(r3, r4)
            java.lang.Class<android.graphics.Bitmap> r1 = android.graphics.Bitmap.class
            r2.transform(r1, r3, r4)
            java.lang.Class<android.graphics.drawable.Drawable> r1 = android.graphics.drawable.Drawable.class
            r2.transform(r1, r0, r4)
            java.lang.Class<android.graphics.drawable.BitmapDrawable> r1 = android.graphics.drawable.BitmapDrawable.class
            com.bumptech.glide.load.Transformation r0 = r0.asBitmapDrawable()
            r2.transform(r1, r0, r4)
            com.bumptech.glide.load.resource.gif.GifDrawableTransformation r0 = new com.bumptech.glide.load.resource.gif.GifDrawableTransformation
            r0.<init>(r3)
            java.lang.Class<com.bumptech.glide.load.resource.gif.GifDrawable> r3 = com.bumptech.glide.load.resource.gif.GifDrawable.class
            r2.transform(r3, r0, r4)
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.selfOrThrowIfLocked()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Object, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(@androidx.annotation.NonNull java.lang.Class<Y> r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3, boolean r4) {
        /*
            r1 = this;
            boolean r0 = r1.isAutoCloneEnabled
            if (r0 == 0) goto L_0x000d
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.clone()
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform(r2, r3, r4)
            return r1
        L_0x000d:
            com.bumptech.glide.util.Preconditions.checkNotNull(r2)
            com.bumptech.glide.util.Preconditions.checkNotNull(r3)
            java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.Transformation<?>> r0 = r1.transformations
            r0.put(r2, r3)
            int r2 = r1.fields
            r3 = 1
            r1.isTransformationAllowed = r3
            r0 = 67584(0x10800, float:9.4705E-41)
            r0 = r0 | r2
            r1.fields = r0
            r0 = 0
            r1.isScaleOnlyOrNoTransform = r0
            if (r4 == 0) goto L_0x0030
            r4 = 198656(0x30800, float:2.78376E-40)
            r2 = r2 | r4
            r1.fields = r2
            r1.isTransformationRequired = r3
        L_0x0030:
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.selfOrThrowIfLocked()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation, boolean):com.bumptech.glide.request.BaseRequestOptions");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Class<Y>, java.lang.Class] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.load.Transformation<Y>, com.bumptech.glide.load.Transformation] */
    /* JADX WARNING: Unknown variable types count: 2 */
    @androidx.annotation.CheckResult
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <Y> T transform(@androidx.annotation.NonNull java.lang.Class<Y> r2, @androidx.annotation.NonNull com.bumptech.glide.load.Transformation<Y> r3) {
        /*
            r1 = this;
            r0 = 1
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.transform(r2, r3, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.BaseRequestOptions.transform(java.lang.Class, com.bumptech.glide.load.Transformation):com.bumptech.glide.request.BaseRequestOptions");
    }
}
