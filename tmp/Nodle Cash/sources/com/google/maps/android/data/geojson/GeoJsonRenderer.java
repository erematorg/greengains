package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Renderer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class GeoJsonRenderer extends Renderer implements Observer {
    private static final Object FEATURE_NOT_ON_MAP = null;

    public GeoJsonRenderer(GoogleMap googleMap, HashMap<GeoJsonFeature, Object> hashMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        super(googleMap, hashMap, markerManager, polygonManager, polylineManager, groundOverlayManager);
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature) {
        redrawFeatureToMap(geoJsonFeature, getMap());
    }

    public void addFeature(GeoJsonFeature geoJsonFeature) {
        super.addFeature(geoJsonFeature);
        if (isLayerOnMap()) {
            geoJsonFeature.addObserver(this);
        }
    }

    public void addLayerToMap() {
        if (!isLayerOnMap()) {
            setLayerVisibility(true);
            Iterator<Feature> it = super.getFeatures().iterator();
            while (it.hasNext()) {
                addFeature((GeoJsonFeature) it.next());
            }
        }
    }

    public void removeFeature(GeoJsonFeature geoJsonFeature) {
        super.removeFeature(geoJsonFeature);
        if (super.getFeatures().contains(geoJsonFeature)) {
            geoJsonFeature.deleteObserver(this);
        }
    }

    public void removeLayerFromMap() {
        if (isLayerOnMap()) {
            for (Feature next : super.getFeatures()) {
                removeFromMap(super.getAllFeatures().get(next));
                next.deleteObserver(this);
            }
            setLayerVisibility(false);
        }
    }

    public void setMap(GoogleMap googleMap) {
        super.setMap(googleMap);
        Iterator<Feature> it = super.getFeatures().iterator();
        while (it.hasNext()) {
            redrawFeatureToMap((GeoJsonFeature) it.next(), googleMap);
        }
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof GeoJsonFeature) {
            GeoJsonFeature geoJsonFeature = (GeoJsonFeature) observable;
            Object obj2 = getAllFeatures().get(geoJsonFeature);
            Object obj3 = FEATURE_NOT_ON_MAP;
            boolean z2 = obj2 != obj3;
            if (z2 && geoJsonFeature.hasGeometry()) {
                redrawFeatureToMap(geoJsonFeature);
            } else if (z2 && !geoJsonFeature.hasGeometry()) {
                removeFromMap(getAllFeatures().get(geoJsonFeature));
                putFeatures(geoJsonFeature, obj3);
            } else if (!z2 && geoJsonFeature.hasGeometry()) {
                addFeature(geoJsonFeature);
            }
        }
    }

    private void redrawFeatureToMap(GeoJsonFeature geoJsonFeature, GoogleMap googleMap) {
        removeFromMap(getAllFeatures().get(geoJsonFeature));
        putFeatures(geoJsonFeature, FEATURE_NOT_ON_MAP);
        if (googleMap != null && geoJsonFeature.hasGeometry()) {
            putFeatures(geoJsonFeature, addGeoJsonFeatureToMap(geoJsonFeature, geoJsonFeature.getGeometry()));
        }
    }
}
