package com.google.maps.android.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Point;

public class GeoJsonPoint extends Point {
    private final Double mAltitude;

    public GeoJsonPoint(LatLng latLng) {
        this(latLng, (Double) null);
    }

    public Double getAltitude() {
        return this.mAltitude;
    }

    public LatLng getCoordinates() {
        return getGeometryObject();
    }

    public String getType() {
        return getGeometryType();
    }

    public GeoJsonPoint(LatLng latLng, Double d2) {
        super(latLng);
        this.mAltitude = d2;
    }
}
