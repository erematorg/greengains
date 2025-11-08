package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.collections.MapObjectManager;

public class PolylineManager extends MapObjectManager<Polyline, Collection> implements GoogleMap.OnPolylineClickListener {
    public PolylineManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    public void onPolylineClick(Polyline polyline) {
        Collection collection = (Collection) this.mAllObjects.get(polyline);
        if (collection != null && collection.mPolylineClickListener != null) {
            collection.mPolylineClickListener.onPolylineClick(polyline);
        }
    }

    public void setListenersOnUiThread() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnPolylineClickListener(this);
        }
    }

    public class Collection extends MapObjectManager.Collection {
        /* access modifiers changed from: private */
        public GoogleMap.OnPolylineClickListener mPolylineClickListener;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<PolylineOptions> collection) {
            for (PolylineOptions addPolyline : collection) {
                addPolyline(addPolyline);
            }
        }

        public Polyline addPolyline(PolylineOptions polylineOptions) {
            Polyline addPolyline = PolylineManager.this.mMap.addPolyline(polylineOptions);
            super.add(addPolyline);
            return addPolyline;
        }

        public java.util.Collection<Polyline> getPolylines() {
            return getObjects();
        }

        public void hideAll() {
            for (Polyline visible : getPolylines()) {
                visible.setVisible(false);
            }
        }

        public boolean remove(Polyline polyline) {
            return super.remove(polyline);
        }

        public void setOnPolylineClickListener(GoogleMap.OnPolylineClickListener onPolylineClickListener) {
            this.mPolylineClickListener = onPolylineClickListener;
        }

        public void showAll() {
            for (Polyline visible : getPolylines()) {
                visible.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<PolylineOptions> collection, boolean z2) {
            for (PolylineOptions addPolyline : collection) {
                addPolyline(addPolyline).setVisible(z2);
            }
        }
    }

    public Collection newCollection() {
        return new Collection();
    }

    public void removeObjectFromMap(Polyline polyline) {
        polyline.remove();
    }
}
