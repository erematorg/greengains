package com.google.maps.android.clustering.algo;

import androidx.compose.ui.autofill.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> extends AbstractAlgorithm<T> {
    private static final int DEFAULT_MAX_DISTANCE_AT_ZOOM = 100;
    /* access modifiers changed from: private */
    public static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private final Collection<QuadItem<T>> mItems = new LinkedHashSet();
    private int mMaxDistance = 100;
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree(0.0d, 1.0d, 0.0d, 1.0d);

    public static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        /* access modifiers changed from: private */
        public final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        public boolean equals(Object obj) {
            if (!(obj instanceof QuadItem)) {
                return false;
            }
            return ((QuadItem) obj).mClusterItem.equals(this.mClusterItem);
        }

        public Point getPoint() {
            return this.mPoint;
        }

        public LatLng getPosition() {
            return this.mPosition;
        }

        public int getSize() {
            return 1;
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }

        private QuadItem(T t2) {
            this.mClusterItem = t2;
            LatLng position = t2.getPosition();
            this.mPosition = position;
            this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(position);
            this.singletonSet = Collections.singleton(t2);
        }

        public Set<T> getItems() {
            return this.singletonSet;
        }
    }

    private Bounds createBoundsFromSpan(Point point, double d2) {
        double d3 = d2 / 2.0d;
        double d4 = point.f7188x;
        double d5 = d4 - d3;
        double d6 = d4 + d3;
        double d7 = point.f7189y;
        return new Bounds(d5, d6, d7 - d3, d7 + d3);
    }

    private double distanceSquared(Point point, Point point2) {
        Point point3 = point;
        Point point4 = point2;
        double d2 = point3.f7188x;
        double d3 = point4.f7188x;
        double d4 = d2 - d3;
        double d5 = point3.f7189y;
        double d6 = point4.f7189y;
        return a.a(d5, d6, d5 - d6, (d2 - d3) * d4);
    }

    public boolean addItem(T t2) {
        boolean add;
        QuadItem quadItem = new QuadItem(t2);
        synchronized (this.mQuadTree) {
            try {
                add = this.mItems.add(quadItem);
                if (add) {
                    this.mQuadTree.add(quadItem);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return add;
    }

    public boolean addItems(Collection<T> collection) {
        boolean z2 = false;
        for (T addItem : collection) {
            if (addItem(addItem)) {
                z2 = true;
            }
        }
        return z2;
    }

    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    public Collection<QuadItem<T>> getClusteringItems(PointQuadTree<QuadItem<T>> pointQuadTree, float f2) {
        return this.mItems;
    }

    public Set<? extends Cluster<T>> getClusters(float f2) {
        float f3 = f2;
        double pow = (((double) this.mMaxDistance) / Math.pow(2.0d, (double) ((int) f3))) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.mQuadTree) {
            try {
                Iterator<QuadItem<QuadItem<T>>> it = getClusteringItems(this.mQuadTree, f3).iterator();
                while (it.hasNext()) {
                    QuadItem next = it.next();
                    if (!hashSet.contains(next)) {
                        Collection<QuadItem<T>> search = this.mQuadTree.search(createBoundsFromSpan(next.getPoint(), pow));
                        if (search.size() == 1) {
                            hashSet2.add(next);
                            hashSet.add(next);
                            hashMap.put(next, Double.valueOf(0.0d));
                        } else {
                            StaticCluster staticCluster = new StaticCluster(next.mClusterItem.getPosition());
                            hashSet2.add(staticCluster);
                            for (QuadItem next2 : search) {
                                Double d2 = (Double) hashMap.get(next2);
                                Iterator<QuadItem<QuadItem<T>>> it2 = it;
                                double distanceSquared = distanceSquared(next2.getPoint(), next.getPoint());
                                if (d2 != null) {
                                    if (d2.doubleValue() < distanceSquared) {
                                        it = it2;
                                    } else {
                                        ((StaticCluster) hashMap2.get(next2)).remove(next2.mClusterItem);
                                    }
                                }
                                hashMap.put(next2, Double.valueOf(distanceSquared));
                                staticCluster.add(next2.mClusterItem);
                                hashMap2.put(next2, staticCluster);
                                it = it2;
                            }
                            hashSet.addAll(search);
                            it = it;
                        }
                    }
                }
            } finally {
            }
        }
        return hashSet2;
    }

    public Collection<T> getItems() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        synchronized (this.mQuadTree) {
            try {
                for (QuadItem<T> access$100 : this.mItems) {
                    linkedHashSet.add(access$100.mClusterItem);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return linkedHashSet;
    }

    public int getMaxDistanceBetweenClusteredItems() {
        return this.mMaxDistance;
    }

    public boolean removeItem(T t2) {
        boolean remove;
        QuadItem quadItem = new QuadItem(t2);
        synchronized (this.mQuadTree) {
            try {
                remove = this.mItems.remove(quadItem);
                if (remove) {
                    this.mQuadTree.remove(quadItem);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return remove;
    }

    public boolean removeItems(Collection<T> collection) {
        boolean z2;
        synchronized (this.mQuadTree) {
            try {
                z2 = false;
                for (T quadItem : collection) {
                    QuadItem quadItem2 = new QuadItem(quadItem);
                    if (this.mItems.remove(quadItem2)) {
                        this.mQuadTree.remove(quadItem2);
                        z2 = true;
                    }
                }
            } finally {
            }
        }
        return z2;
    }

    public void setMaxDistanceBetweenClusteredItems(int i3) {
        this.mMaxDistance = i3;
    }

    public boolean updateItem(T t2) {
        boolean removeItem;
        synchronized (this.mQuadTree) {
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
