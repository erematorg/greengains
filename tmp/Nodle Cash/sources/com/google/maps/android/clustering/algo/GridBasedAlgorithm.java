package com.google.maps.android.clustering.algo;

import androidx.collection.LongSparseArray;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.projection.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GridBasedAlgorithm<T extends ClusterItem> extends AbstractAlgorithm<T> {
    private static final int DEFAULT_GRID_SIZE = 100;
    private int mGridSize = 100;
    private final Set<T> mItems = Collections.synchronizedSet(new HashSet());

    private static long getCoord(long j2, double d2, double d3) {
        return (long) (Math.floor(d3) + (Math.floor(d2) * ((double) j2)));
    }

    public boolean addItem(T t2) {
        return this.mItems.add(t2);
    }

    public boolean addItems(Collection<T> collection) {
        return this.mItems.addAll(collection);
    }

    public void clearItems() {
        this.mItems.clear();
    }

    public Set<? extends Cluster<T>> getClusters(float f2) {
        Iterator<T> it;
        long j2;
        long ceil = (long) Math.ceil((Math.pow(2.0d, (double) f2) * 256.0d) / ((double) this.mGridSize));
        SphericalMercatorProjection sphericalMercatorProjection = new SphericalMercatorProjection((double) ceil);
        HashSet hashSet = new HashSet();
        LongSparseArray longSparseArray = new LongSparseArray();
        synchronized (this.mItems) {
            try {
                Iterator<T> it2 = this.mItems.iterator();
                while (it2.hasNext()) {
                    ClusterItem clusterItem = (ClusterItem) it2.next();
                    Point point = sphericalMercatorProjection.toPoint(clusterItem.getPosition());
                    long coord = getCoord(ceil, point.f7188x, point.f7189y);
                    StaticCluster staticCluster = (StaticCluster) longSparseArray.get(coord);
                    if (staticCluster == null) {
                        it = it2;
                        j2 = ceil;
                        staticCluster = new StaticCluster(sphericalMercatorProjection.toLatLng(new com.google.maps.android.geometry.Point(Math.floor(point.f7188x) + 0.5d, Math.floor(point.f7189y) + 0.5d)));
                        longSparseArray.put(coord, staticCluster);
                        hashSet.add(staticCluster);
                    } else {
                        it = it2;
                        j2 = ceil;
                    }
                    staticCluster.add(clusterItem);
                    it2 = it;
                    ceil = j2;
                }
            } finally {
            }
        }
        return hashSet;
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    public int getMaxDistanceBetweenClusteredItems() {
        return this.mGridSize;
    }

    public boolean removeItem(T t2) {
        return this.mItems.remove(t2);
    }

    public boolean removeItems(Collection<T> collection) {
        return this.mItems.removeAll(collection);
    }

    public void setMaxDistanceBetweenClusteredItems(int i3) {
        this.mGridSize = i3;
    }

    public boolean updateItem(T t2) {
        boolean removeItem;
        synchronized (this.mItems) {
            try {
                removeItem = removeItem(t2);
                if (removeItem) {
                    removeItem = addItem(t2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return removeItem;
    }
}
