package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class StaticCluster<T extends ClusterItem> implements Cluster<T> {
    private final LatLng mCenter;
    private final List<T> mItems = new ArrayList();

    public StaticCluster(LatLng latLng) {
        this.mCenter = latLng;
    }

    public boolean add(T t2) {
        return this.mItems.add(t2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StaticCluster)) {
            return false;
        }
        StaticCluster staticCluster = (StaticCluster) obj;
        return staticCluster.mCenter.equals(this.mCenter) && staticCluster.mItems.equals(this.mItems);
    }

    public Collection<T> getItems() {
        return this.mItems;
    }

    public LatLng getPosition() {
        return this.mCenter;
    }

    public int getSize() {
        return this.mItems.size();
    }

    public int hashCode() {
        return this.mItems.hashCode() + this.mCenter.hashCode();
    }

    public boolean remove(T t2) {
        return this.mItems.remove(t2);
    }

    public String toString() {
        return "StaticCluster{mCenter=" + this.mCenter + ", mItems.size=" + this.mItems.size() + AbstractJsonLexerKt.END_OBJ;
    }
}
