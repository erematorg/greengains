package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public class ModelCache<A, B> {
    private static final int DEFAULT_SIZE = 250;
    private final LruCache<ModelKey<A>, B> cache;

    @VisibleForTesting
    public static final class ModelKey<A> {
        private static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        private int height;
        private A model;
        private int width;

        private ModelKey() {
        }

        public static <A> ModelKey<A> get(A a2, int i3, int i4) {
            ModelKey<A> poll;
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                poll = queue.poll();
            }
            if (poll == null) {
                poll = new ModelKey<>();
            }
            poll.init(a2, i3, i4);
            return poll;
        }

        private void init(A a2, int i3, int i4) {
            this.model = a2;
            this.width = i3;
            this.height = i4;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ModelKey)) {
                return false;
            }
            ModelKey modelKey = (ModelKey) obj;
            return this.width == modelKey.width && this.height == modelKey.height && this.model.equals(modelKey.model);
        }

        public int hashCode() {
            return this.model.hashCode() + (((this.height * 31) + this.width) * 31);
        }

        public void release() {
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                queue.offer(this);
            }
        }
    }

    public ModelCache() {
        this(250);
    }

    public void clear() {
        this.cache.clearMemory();
    }

    @Nullable
    public B get(A a2, int i3, int i4) {
        ModelKey modelKey = ModelKey.get(a2, i3, i4);
        B b3 = this.cache.get(modelKey);
        modelKey.release();
        return b3;
    }

    public void put(A a2, int i3, int i4, B b3) {
        this.cache.put(ModelKey.get(a2, i3, i4), b3);
    }

    public ModelCache(long j2) {
        this.cache = new LruCache<ModelKey<A>, B>(j2) {
            public void onItemEvicted(@NonNull ModelKey<A> modelKey, @Nullable B b3) {
                modelKey.release();
            }
        };
    }
}
