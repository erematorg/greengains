package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;

public class ScreenBasedAlgorithmAdapter<T extends ClusterItem> extends AbstractAlgorithm<T> implements ScreenBasedAlgorithm<T> {
    private Algorithm<T> mAlgorithm;

    public ScreenBasedAlgorithmAdapter(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    public boolean addItem(T t2) {
        return this.mAlgorithm.addItem(t2);
    }

    public boolean addItems(Collection<T> collection) {
        return this.mAlgorithm.addItems(collection);
    }

    public void clearItems() {
        this.mAlgorithm.clearItems();
    }

    public Set<? extends Cluster<T>> getClusters(float f2) {
        return this.mAlgorithm.getClusters(f2);
    }

    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    public int getMaxDistanceBetweenClusteredItems() {
        return this.mAlgorithm.getMaxDistanceBetweenClusteredItems();
    }

    public void onCameraChange(CameraPosition cameraPosition) {
    }

    public boolean removeItem(T t2) {
        return this.mAlgorithm.removeItem(t2);
    }

    public boolean removeItems(Collection<T> collection) {
        return this.mAlgorithm.removeItems(collection);
    }

    public void setMaxDistanceBetweenClusteredItems(int i3) {
        this.mAlgorithm.setMaxDistanceBetweenClusteredItems(i3);
    }

    public boolean shouldReclusterOnMapMovement() {
        return false;
    }

    public boolean updateItem(T t2) {
        return this.mAlgorithm.updateItem(t2);
    }
}
