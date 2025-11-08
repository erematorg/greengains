package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.algo.NonHierarchicalDistanceBasedAlgorithm;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.projection.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.ArrayList;
import java.util.Collection;

public class NonHierarchicalViewBasedAlgorithm<T extends ClusterItem> extends NonHierarchicalDistanceBasedAlgorithm<T> implements ScreenBasedAlgorithm<T> {
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private LatLng mMapCenter;
    private int mViewHeight;
    private int mViewWidth;

    public NonHierarchicalViewBasedAlgorithm(int i3, int i4) {
        this.mViewWidth = i3;
        this.mViewHeight = i4;
    }

    private Bounds getVisibleBounds(float f2) {
        LatLng latLng = this.mMapCenter;
        if (latLng == null) {
            return new Bounds(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Point point = PROJECTION.toPoint(latLng);
        double d2 = (double) f2;
        double pow = ((((double) this.mViewWidth) / Math.pow(2.0d, d2)) / 256.0d) / 2.0d;
        double pow2 = ((((double) this.mViewHeight) / Math.pow(2.0d, d2)) / 256.0d) / 2.0d;
        double d3 = point.f7188x;
        double d4 = d3 - pow;
        double d5 = pow + d3;
        double d6 = point.f7189y;
        return new Bounds(d4, d5, d6 - pow2, d6 + pow2);
    }

    public Collection<NonHierarchicalDistanceBasedAlgorithm.QuadItem<T>> getClusteringItems(PointQuadTree<NonHierarchicalDistanceBasedAlgorithm.QuadItem<T>> pointQuadTree, float f2) {
        PointQuadTree<NonHierarchicalDistanceBasedAlgorithm.QuadItem<T>> pointQuadTree2 = pointQuadTree;
        Bounds visibleBounds = getVisibleBounds(f2);
        ArrayList arrayList = new ArrayList();
        double d2 = visibleBounds.minX;
        if (d2 < 0.0d) {
            arrayList.addAll(pointQuadTree2.search(new Bounds(d2 + 1.0d, 1.0d, visibleBounds.minY, visibleBounds.maxY)));
            visibleBounds = new Bounds(0.0d, visibleBounds.maxX, visibleBounds.minY, visibleBounds.maxY);
        }
        double d3 = visibleBounds.maxX;
        if (d3 > 1.0d) {
            arrayList.addAll(pointQuadTree2.search(new Bounds(0.0d, d3 - 1.0d, visibleBounds.minY, visibleBounds.maxY)));
            visibleBounds = new Bounds(visibleBounds.minX, 1.0d, visibleBounds.minY, visibleBounds.maxY);
        }
        arrayList.addAll(pointQuadTree2.search(visibleBounds));
        return arrayList;
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        this.mMapCenter = cameraPosition.target;
    }

    public boolean shouldReclusterOnMapMovement() {
        return true;
    }

    public void updateViewSize(int i3, int i4) {
        this.mViewWidth = i3;
        this.mViewHeight = i4;
    }
}
