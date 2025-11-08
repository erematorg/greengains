package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import com.bumptech.glide.util.Util;

class AttributeStrategy implements LruPoolStrategy {
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final KeyPool keyPool = new KeyPool();

    @VisibleForTesting
    public static class Key implements Poolable {
        private Bitmap.Config config;
        private int height;
        private final KeyPool pool;
        private int width;

        public Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            return this.width == key.width && this.height == key.height && this.config == key.config;
        }

        public int hashCode() {
            int i3 = ((this.width * 31) + this.height) * 31;
            Bitmap.Config config2 = this.config;
            return i3 + (config2 != null ? config2.hashCode() : 0);
        }

        public void init(int i3, int i4, Bitmap.Config config2) {
            this.width = i3;
            this.height = i4;
            this.config = config2;
        }

        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            return AttributeStrategy.getBitmapString(this.width, this.height, this.config);
        }
    }

    @VisibleForTesting
    public static class KeyPool extends BaseKeyPool<Key> {
        public Key get(int i3, int i4, Bitmap.Config config) {
            Key key = (Key) get();
            key.init(i3, i4, config);
            return key;
        }

        public Key create() {
            return new Key(this);
        }
    }

    private static String getBitmapString(Bitmap bitmap) {
        return getBitmapString(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
    }

    public Bitmap get(int i3, int i4, Bitmap.Config config) {
        return this.groupedMap.get(this.keyPool.get(i3, i4, config));
    }

    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    public String logBitmap(Bitmap bitmap) {
        return getBitmapString(bitmap);
    }

    public void put(Bitmap bitmap) {
        this.groupedMap.put(this.keyPool.get(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig()), bitmap);
    }

    public Bitmap removeLast() {
        return this.groupedMap.removeLast();
    }

    public String toString() {
        return "AttributeStrategy:\n  " + this.groupedMap;
    }

    public static String getBitmapString(int i3, int i4, Bitmap.Config config) {
        StringBuilder k2 = C0118y.k(i3, i4, "[", "x", "], ");
        k2.append(config);
        return k2.toString();
    }

    public String logBitmap(int i3, int i4, Bitmap.Config config) {
        return getBitmapString(i3, i4, config);
    }
}
