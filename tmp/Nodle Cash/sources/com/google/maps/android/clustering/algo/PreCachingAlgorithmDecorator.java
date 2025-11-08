package com.google.maps.android.clustering.algo;

import androidx.collection.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PreCachingAlgorithmDecorator<T extends ClusterItem> extends AbstractAlgorithm<T> {
    private final Algorithm<T> mAlgorithm;
    private final LruCache<Integer, Set<? extends Cluster<T>>> mCache = new LruCache<>(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();

    public class PrecacheRunnable implements Runnable {
        private final int mZoom;

        public PrecacheRunnable(int i3) {
            this.mZoom = i3;
        }

        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException unused) {
            }
            Set unused2 = PreCachingAlgorithmDecorator.this.getClustersInternal(this.mZoom);
        }
    }

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    private void clearCache() {
        this.mCache.evictAll();
    }

    /* access modifiers changed from: private */
    public Set<? extends Cluster<T>> getClustersInternal(int i3) {
        this.mCacheLock.readLock().lock();
        Set<? extends Cluster<T>> set = this.mCache.get(Integer.valueOf(i3));
        this.mCacheLock.readLock().unlock();
        if (set == null) {
            this.mCacheLock.writeLock().lock();
            set = this.mCache.get(Integer.valueOf(i3));
            if (set == null) {
                set = this.mAlgorithm.getClusters((float) i3);
                this.mCache.put(Integer.valueOf(i3), set);
            }
            this.mCacheLock.writeLock().unlock();
        }
        return set;
    }

    public boolean addItem(T t2) {
        boolean addItem = this.mAlgorithm.addItem(t2);
        if (addItem) {
            clearCache();
        }
        return addItem;
    }

    public boolean addItems(Collection<T> collection) {
        boolean addItems = this.mAlgorithm.addItems(collection);
        if (addItems) {
            clearCache();
        }
        return addItems;
    }

    public void clearItems() {
        this.mAlgorithm.clearItems();
        clearCache();
    }

    public Set<? extends Cluster<T>> getClusters(float f2) {
        int i3 = (int) f2;
        Set<? extends Cluster<T>> clustersInternal = getClustersInternal(i3);
        int i4 = i3 + 1;
        if (this.mCache.get(Integer.valueOf(i4)) == null) {
            new Thread(new PrecacheRunnable(i4)).start();
        }
        int i5 = i3 - 1;
        if (this.mCache.get(Integer.valueOf(i5)) == null) {
            new Thread(new PrecacheRunnable(i5)).start();
        }
        return clustersInternal;
    }

    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    public int getMaxDistanceBetweenClusteredItems() {
        return this.mAlgorithm.getMaxDistanceBetweenClusteredItems();
    }

    public boolean removeItem(T t2) {
        boolean removeItem = this.mAlgorithm.removeItem(t2);
        if (removeItem) {
            clearCache();
        }
        return removeItem;
    }

    public boolean removeItems(Collection<T> collection) {
        boolean removeItems = this.mAlgorithm.removeItems(collection);
        if (removeItems) {
            clearCache();
        }
        return removeItems;
    }

    public void setMaxDistanceBetweenClusteredItems(int i3) {
        this.mAlgorithm.setMaxDistanceBetweenClusteredItems(i3);
        clearCache();
    }

    public boolean updateItem(T t2) {
        boolean updateItem = this.mAlgorithm.updateItem(t2);
        if (updateItem) {
            clearCache();
        }
        return updateItem;
    }
}
