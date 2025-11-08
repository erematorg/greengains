package com.google.maps.android.collections;

import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.collections.MapObjectManager;

public class MarkerManager extends MapObjectManager<Marker, Collection> implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.InfoWindowAdapter {
    public MarkerManager(GoogleMap googleMap) {
        super(googleMap);
    }

    public View getInfoContents(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection == null || collection.mInfoWindowAdapter == null) {
            return null;
        }
        return collection.mInfoWindowAdapter.getInfoContents(marker);
    }

    public View getInfoWindow(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection == null || collection.mInfoWindowAdapter == null) {
            return null;
        }
        return collection.mInfoWindowAdapter.getInfoWindow(marker);
    }

    public void onInfoWindowClick(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection != null && collection.mInfoWindowClickListener != null) {
            collection.mInfoWindowClickListener.onInfoWindowClick(marker);
        }
    }

    public boolean onMarkerClick(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection == null || collection.mMarkerClickListener == null) {
            return false;
        }
        return collection.mMarkerClickListener.onMarkerClick(marker);
    }

    public void onMarkerDrag(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDrag(marker);
        }
    }

    public void onMarkerDragEnd(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragEnd(marker);
        }
    }

    public void onMarkerDragStart(Marker marker) {
        Collection collection = (Collection) this.mAllObjects.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragStart(marker);
        }
    }

    public void setListenersOnUiThread() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnInfoWindowClickListener(this);
            this.mMap.setOnMarkerClickListener(this);
            this.mMap.setOnMarkerDragListener(this);
            this.mMap.setInfoWindowAdapter(this);
        }
    }

    public class Collection extends MapObjectManager.Collection {
        /* access modifiers changed from: private */
        public GoogleMap.InfoWindowAdapter mInfoWindowAdapter;
        /* access modifiers changed from: private */
        public GoogleMap.OnInfoWindowClickListener mInfoWindowClickListener;
        /* access modifiers changed from: private */
        public GoogleMap.OnMarkerClickListener mMarkerClickListener;
        /* access modifiers changed from: private */
        public GoogleMap.OnMarkerDragListener mMarkerDragListener;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<MarkerOptions> collection) {
            for (MarkerOptions addMarker : collection) {
                addMarker(addMarker);
            }
        }

        public Marker addMarker(MarkerOptions markerOptions) {
            Marker addMarker = MarkerManager.this.mMap.addMarker(markerOptions);
            super.add(addMarker);
            return addMarker;
        }

        public java.util.Collection<Marker> getMarkers() {
            return getObjects();
        }

        public void hideAll() {
            for (Marker visible : getMarkers()) {
                visible.setVisible(false);
            }
        }

        public boolean remove(Marker marker) {
            return super.remove(marker);
        }

        public void setInfoWindowAdapter(GoogleMap.InfoWindowAdapter infoWindowAdapter) {
            this.mInfoWindowAdapter = infoWindowAdapter;
        }

        public void setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener onInfoWindowClickListener) {
            this.mInfoWindowClickListener = onInfoWindowClickListener;
        }

        public void setOnMarkerClickListener(GoogleMap.OnMarkerClickListener onMarkerClickListener) {
            this.mMarkerClickListener = onMarkerClickListener;
        }

        public void setOnMarkerDragListener(GoogleMap.OnMarkerDragListener onMarkerDragListener) {
            this.mMarkerDragListener = onMarkerDragListener;
        }

        public void showAll() {
            for (Marker visible : getMarkers()) {
                visible.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<MarkerOptions> collection, boolean z2) {
            for (MarkerOptions addMarker : collection) {
                addMarker(addMarker).setVisible(z2);
            }
        }
    }

    public Collection newCollection() {
        return new Collection();
    }

    public void removeObjectFromMap(Marker marker) {
        marker.remove();
    }
}
