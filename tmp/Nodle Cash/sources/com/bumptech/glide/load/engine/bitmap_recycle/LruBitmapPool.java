package com.bumptech.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LruBitmapPool implements BitmapPool {
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private static final String TAG = "LruBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private long currentSize;
    private int evictions;
    private int hits;
    private final long initialMaxSize;
    private long maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    public interface BitmapTracker {
        void add(Bitmap bitmap);

        void remove(Bitmap bitmap);
    }

    public static final class NullBitmapTracker implements BitmapTracker {
        public void add(Bitmap bitmap) {
        }

        public void remove(Bitmap bitmap) {
        }
    }

    public static class ThrowingBitmapTracker implements BitmapTracker {
        private final Set<Bitmap> bitmaps = Collections.synchronizedSet(new HashSet());

        private ThrowingBitmapTracker() {
        }

        public void add(Bitmap bitmap) {
            if (!this.bitmaps.contains(bitmap)) {
                this.bitmaps.add(bitmap);
                return;
            }
            throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + "x" + bitmap.getHeight() + "]");
        }

        public void remove(Bitmap bitmap) {
            if (this.bitmaps.contains(bitmap)) {
                this.bitmaps.remove(bitmap);
                return;
            }
            throw new IllegalStateException("Cannot remove bitmap not in tracker");
        }
    }

    public LruBitmapPool(long j2, LruPoolStrategy lruPoolStrategy, Set<Bitmap.Config> set) {
        this.initialMaxSize = j2;
        this.maxSize = j2;
        this.strategy = lruPoolStrategy;
        this.allowedConfigs = set;
        this.tracker = new NullBitmapTracker();
    }

    @TargetApi(26)
    private static void assertNotHardwareConfig(Bitmap.Config config) {
        if (config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    @NonNull
    private static Bitmap createBitmap(int i3, int i4, @Nullable Bitmap.Config config) {
        if (config == null) {
            config = DEFAULT_CONFIG;
        }
        return Bitmap.createBitmap(i3, i4, config);
    }

    private void dump() {
        if (Log.isLoggable(TAG, 2)) {
            dumpUnchecked();
        }
    }

    private void dumpUnchecked() {
        Log.v(TAG, "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + "\nStrategy=" + this.strategy);
    }

    private void evict() {
        trimToSize(this.maxSize);
    }

    @TargetApi(26)
    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add((Object) null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        return Collections.unmodifiableSet(hashSet);
    }

    private static LruPoolStrategy getDefaultStrategy() {
        return new SizeConfigStrategy();
    }

    @Nullable
    private synchronized Bitmap getDirtyOrNull(int i3, int i4, @Nullable Bitmap.Config config) {
        Bitmap bitmap;
        try {
            assertNotHardwareConfig(config);
            bitmap = this.strategy.get(i3, i4, config != null ? config : DEFAULT_CONFIG);
            if (bitmap == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Missing bitmap=" + this.strategy.logBitmap(i3, i4, config));
                }
                this.misses++;
            } else {
                this.hits++;
                this.currentSize -= (long) this.strategy.getSize(bitmap);
                this.tracker.remove(bitmap);
                normalize(bitmap);
            }
            if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Get bitmap=" + this.strategy.logBitmap(i3, i4, config));
            }
            dump();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return bitmap;
    }

    @TargetApi(19)
    private static void maybeSetPreMultiplied(Bitmap bitmap) {
        bitmap.setPremultiplied(true);
    }

    private static void normalize(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        maybeSetPreMultiplied(bitmap);
    }

    private synchronized void trimToSize(long j2) {
        while (this.currentSize > j2) {
            try {
                Bitmap removeLast = this.strategy.removeLast();
                if (removeLast == null) {
                    if (Log.isLoggable(TAG, 5)) {
                        Log.w(TAG, "Size mismatch, resetting");
                        dumpUnchecked();
                    }
                    this.currentSize = 0;
                    return;
                }
                this.tracker.remove(removeLast);
                this.currentSize -= (long) this.strategy.getSize(removeLast);
                this.evictions++;
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Evicting bitmap=" + this.strategy.logBitmap(removeLast));
                }
                dump();
                removeLast.recycle();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void clearMemory() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "clearMemory");
        }
        trimToSize(0);
    }

    public long evictionCount() {
        return (long) this.evictions;
    }

    @NonNull
    public Bitmap get(int i3, int i4, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i3, i4, config);
        if (dirtyOrNull == null) {
            return createBitmap(i3, i4, config);
        }
        dirtyOrNull.eraseColor(0);
        return dirtyOrNull;
    }

    public long getCurrentSize() {
        return this.currentSize;
    }

    @NonNull
    public Bitmap getDirty(int i3, int i4, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i3, i4, config);
        return dirtyOrNull == null ? createBitmap(i3, i4, config) : dirtyOrNull;
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    public long hitCount() {
        return (long) this.hits;
    }

    public long missCount() {
        return (long) this.misses;
    }

    public synchronized void put(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    if (bitmap.isMutable() && ((long) this.strategy.getSize(bitmap)) <= this.maxSize) {
                        if (this.allowedConfigs.contains(bitmap.getConfig())) {
                            int size = this.strategy.getSize(bitmap);
                            this.strategy.put(bitmap);
                            this.tracker.add(bitmap);
                            this.puts++;
                            this.currentSize += (long) size;
                            if (Log.isLoggable(TAG, 2)) {
                                Log.v(TAG, "Put bitmap in pool=" + this.strategy.logBitmap(bitmap));
                            }
                            dump();
                            evict();
                            return;
                        }
                    }
                    if (Log.isLoggable(TAG, 2)) {
                        Log.v(TAG, "Reject bitmap from pool, bitmap: " + this.strategy.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.allowedConfigs.contains(bitmap.getConfig()));
                    }
                    bitmap.recycle();
                    return;
                }
                throw new IllegalStateException("Cannot pool recycled bitmap");
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new NullPointerException("Bitmap must not be null");
        }
    }

    public synchronized void setSizeMultiplier(float f2) {
        this.maxSize = (long) Math.round(((float) this.initialMaxSize) * f2);
        evict();
    }

    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i3) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "trimMemory, level=" + i3);
        }
        if (i3 >= 40 || i3 >= 20) {
            clearMemory();
        } else if (i3 >= 20 || i3 == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }

    public LruBitmapPool(long j2) {
        this(j2, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(long j2, Set<Bitmap.Config> set) {
        this(j2, getDefaultStrategy(), set);
    }
}
