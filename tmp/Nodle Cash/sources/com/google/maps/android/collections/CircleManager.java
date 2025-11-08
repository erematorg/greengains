package com.google.maps.android.collections;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.maps.android.collections.MapObjectManager;

public class CircleManager extends MapObjectManager<Circle, Collection> implements GoogleMap.OnCircleClickListener {
    public CircleManager(@NonNull GoogleMap googleMap) {
        super(googleMap);
    }

    public void onCircleClick(Circle circle) {
        Collection collection = (Collection) this.mAllObjects.get(circle);
        if (collection != null && collection.mCircleClickListener != null) {
            collection.mCircleClickListener.onCircleClick(circle);
        }
    }

    public void setListenersOnUiThread() {
        GoogleMap googleMap = this.mMap;
        if (googleMap != null) {
            googleMap.setOnCircleClickListener(this);
        }
    }

    public class Collection extends MapObjectManager.Collection {
        /* access modifiers changed from: private */
        public GoogleMap.OnCircleClickListener mCircleClickListener;

        public Collection() {
            super();
        }

        public void addAll(java.util.Collection<CircleOptions> collection) {
            for (CircleOptions addCircle : collection) {
                addCircle(addCircle);
            }
        }

        public Circle addCircle(CircleOptions circleOptions) {
            Circle addCircle = CircleManager.this.mMap.addCircle(circleOptions);
            super.add(addCircle);
            return addCircle;
        }

        public java.util.Collection<Circle> getCircles() {
            return getObjects();
        }

        public void hideAll() {
            for (Circle visible : getCircles()) {
                visible.setVisible(false);
            }
        }

        public boolean remove(Circle circle) {
            return super.remove(circle);
        }

        public void setOnCircleClickListener(GoogleMap.OnCircleClickListener onCircleClickListener) {
            this.mCircleClickListener = onCircleClickListener;
        }

        public void showAll() {
            for (Circle visible : getCircles()) {
                visible.setVisible(true);
            }
        }

        public void addAll(java.util.Collection<CircleOptions> collection, boolean z2) {
            for (CircleOptions addCircle : collection) {
                addCircle(addCircle).setVisible(z2);
            }
        }
    }

    public Collection newCollection() {
        return new Collection();
    }

    public void removeObjectFromMap(Circle circle) {
        circle.remove();
    }
}
