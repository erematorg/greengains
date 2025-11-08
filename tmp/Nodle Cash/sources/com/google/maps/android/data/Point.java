package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;

public class Point implements Geometry {
    private static final String GEOMETRY_TYPE = "Point";
    private final LatLng mCoordinates;

    public Point(LatLng latLng) {
        if (latLng != null) {
            this.mCoordinates = latLng;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public String toString() {
        return "Point{\n coordinates=" + this.mCoordinates + "\n}\n";
    }

    public LatLng getGeometryObject() {
        return this.mCoordinates;
    }
}
