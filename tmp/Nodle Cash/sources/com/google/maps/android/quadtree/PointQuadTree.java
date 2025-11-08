package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PointQuadTree<T extends Item> {
    private static final int MAX_DEPTH = 40;
    private static final int MAX_ELEMENTS = 50;
    private final Bounds mBounds;
    private List<PointQuadTree<T>> mChildren;
    private final int mDepth;
    private Set<T> mItems;

    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double d2, double d3, double d4, double d5) {
        this(new Bounds(d2, d3, d4, d5));
    }

    private void insert(double d2, double d3, T t2) {
        List<PointQuadTree<T>> list = this.mChildren;
        if (list != null) {
            Bounds bounds = this.mBounds;
            if (d3 < bounds.midY) {
                if (d2 < bounds.midX) {
                    list.get(0).insert(d2, d3, t2);
                } else {
                    list.get(1).insert(d2, d3, t2);
                }
            } else if (d2 < bounds.midX) {
                list.get(2).insert(d2, d3, t2);
            } else {
                list.get(3).insert(d2, d3, t2);
            }
        } else {
            if (this.mItems == null) {
                this.mItems = new LinkedHashSet();
            }
            this.mItems.add(t2);
            if (this.mItems.size() > 50 && this.mDepth < 40) {
                split();
            }
        }
    }

    private void split() {
        ArrayList arrayList = new ArrayList(4);
        this.mChildren = arrayList;
        Bounds bounds = this.mBounds;
        arrayList.add(new PointQuadTree(bounds.minX, bounds.midX, bounds.minY, bounds.midY, this.mDepth + 1));
        List<PointQuadTree<T>> list = this.mChildren;
        Bounds bounds2 = this.mBounds;
        list.add(new PointQuadTree(bounds2.midX, bounds2.maxX, bounds2.minY, bounds2.midY, this.mDepth + 1));
        List<PointQuadTree<T>> list2 = this.mChildren;
        Bounds bounds3 = this.mBounds;
        list2.add(new PointQuadTree(bounds3.minX, bounds3.midX, bounds3.midY, bounds3.maxY, this.mDepth + 1));
        List<PointQuadTree<T>> list3 = this.mChildren;
        Bounds bounds4 = this.mBounds;
        list3.add(new PointQuadTree(bounds4.midX, bounds4.maxX, bounds4.midY, bounds4.maxY, this.mDepth + 1));
        Set<T> set = this.mItems;
        this.mItems = null;
        for (T t2 : set) {
            insert(t2.getPoint().f7188x, t2.getPoint().f7189y, t2);
        }
    }

    public void add(T t2) {
        Point point = t2.getPoint();
        if (this.mBounds.contains(point.f7188x, point.f7189y)) {
            insert(point.f7188x, point.f7189y, t2);
        }
    }

    public void clear() {
        this.mChildren = null;
        Set<T> set = this.mItems;
        if (set != null) {
            set.clear();
        }
    }

    public boolean remove(T t2) {
        Point point = t2.getPoint();
        if (!this.mBounds.contains(point.f7188x, point.f7189y)) {
            return false;
        }
        return remove(point.f7188x, point.f7189y, t2);
    }

    public Collection<T> search(Bounds bounds) {
        ArrayList arrayList = new ArrayList();
        search(bounds, arrayList);
        return arrayList;
    }

    public PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double d2, double d3, double d4, double d5, int i3) {
        this(new Bounds(d2, d3, d4, d5), i3);
    }

    private void search(Bounds bounds, Collection<T> collection) {
        if (this.mBounds.intersects(bounds)) {
            List<PointQuadTree<T>> list = this.mChildren;
            if (list != null) {
                for (PointQuadTree<T> search : list) {
                    search.search(bounds, collection);
                }
            } else if (this.mItems == null) {
            } else {
                if (bounds.contains(this.mBounds)) {
                    collection.addAll(this.mItems);
                    return;
                }
                for (T t2 : this.mItems) {
                    if (bounds.contains(t2.getPoint())) {
                        collection.add(t2);
                    }
                }
            }
        }
    }

    private PointQuadTree(Bounds bounds, int i3) {
        this.mChildren = null;
        this.mBounds = bounds;
        this.mDepth = i3;
    }

    private boolean remove(double d2, double d3, T t2) {
        List<PointQuadTree<T>> list = this.mChildren;
        if (list != null) {
            Bounds bounds = this.mBounds;
            if (d3 < bounds.midY) {
                if (d2 < bounds.midX) {
                    return list.get(0).remove(d2, d3, t2);
                }
                return list.get(1).remove(d2, d3, t2);
            } else if (d2 < bounds.midX) {
                return list.get(2).remove(d2, d3, t2);
            } else {
                return list.get(3).remove(d2, d3, t2);
            }
        } else {
            Set<T> set = this.mItems;
            if (set == null) {
                return false;
            }
            return set.remove(t2);
        }
    }
}
