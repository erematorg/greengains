package com.google.maps.android.data;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class LineString implements Geometry<List<LatLng>> {
    private static final String GEOMETRY_TYPE = "LineString";
    private final List<LatLng> mCoordinates;

    public LineString(List<LatLng> list) {
        if (list != null) {
            this.mCoordinates = list;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public String toString() {
        return C0118y.h("\n}\n", this.mCoordinates, new StringBuilder("LineString{\n coordinates="));
    }

    public List<LatLng> getGeometryObject() {
        return this.mCoordinates;
    }
}
