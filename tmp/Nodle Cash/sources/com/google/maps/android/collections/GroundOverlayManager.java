package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.maps.android.collections.MapObjectManager;

public class GroundOverlayManager extends MapObjectManager<GroundOverlay, Collection> implements GoogleMap.OnGroundOverlayClickListener {
    public GroundOverlayManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        Collection collection = (Collection) this.mAllObjects.get(groundOverlay);
        if (collection != null && collection.mGroundOverlayClickListener != null) {
            collection.mGroundOverlayClickListener.onGroundOverlayClick(groundOverlay);
        }
    }

    public void setListenersOnUiThread() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnGroundOverlayClickListener(this);
        }
    }

    public class Collection extends MapObjectManager.Collection {
        /* access modifiers changed from: private */
        public GoogleMap.OnGroundOverlayClickListener mGroundOverlayClickListener;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection) {
            for (GroundOverlayOptions addGroundOverlay : collection) {
                addGroundOverlay(addGroundOverlay);
            }
        }

        public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
            GroundOverlay addGroundOverlay = GroundOverlayManager.this.mMap.addGroundOverlay(groundOverlayOptions);
            super.add(addGroundOverlay);
            return addGroundOverlay;
        }

        public java.util.Collection<GroundOverlay> getGroundOverlays() {
            return getObjects();
        }

        public void hideAll() {
            for (GroundOverlay visible : getGroundOverlays()) {
                visible.setVisible(false);
            }
        }

        public boolean remove(GroundOverlay groundOverlay) {
            return super.remove(groundOverlay);
        }

        public void setOnGroundOverlayClickListener(GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
            this.mGroundOverlayClickListener = onGroundOverlayClickListener;
        }

        public void showAll() {
            for (GroundOverlay visible : getGroundOverlays()) {
                visible.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection, boolean z2) {
            for (GroundOverlayOptions addGroundOverlay : collection) {
                addGroundOverlay(addGroundOverlay).setVisible(z2);
            }
        }
    }

    public Collection newCollection() {
        return new Collection();
    }

    public void removeObjectFromMap(GroundOverlay groundOverlay) {
        groundOverlay.remove();
    }
}
