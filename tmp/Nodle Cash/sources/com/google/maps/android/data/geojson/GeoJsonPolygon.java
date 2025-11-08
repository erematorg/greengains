package com.google.maps.android.data.geojson;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonPolygon implements DataPolygon {
    private static final String GEOMETRY_TYPE = "Polygon";
    private static final int POLYGON_INNER_COORDINATE_INDEX = 1;
    private static final int POLYGON_OUTER_COORDINATE_INDEX = 0;
    private final List<? extends List<LatLng>> mCoordinates;

    public GeoJsonPolygon(List<? extends List<LatLng>> list) {
        if (list != null) {
            this.mCoordinates = list;
            return;
        }
        throw new IllegalArgumentException("Coordinates cannot be null");
    }

    public List<? extends List<LatLng>> getCoordinates() {
        return this.mCoordinates;
    }

    public String getGeometryType() {
        return getType();
    }

    public String getType() {
        return "Polygon";
    }

    public String toString() {
        return C0118y.h("\n}\n", this.mCoordinates, new StringBuilder("Polygon{\n coordinates="));
    }

    public List<? extends List<LatLng>> getGeometryObject() {
        return getCoordinates();
    }

    public ArrayList<ArrayList<LatLng>> getInnerBoundaryCoordinates() {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int i3 = 1; i3 < getCoordinates().size(); i3++) {
            arrayList.add((ArrayList) getCoordinates().get(i3));
        }
        return arrayList;
    }

    public ArrayList<LatLng> getOuterBoundaryCoordinates() {
        return (ArrayList) getCoordinates().get(0);
    }
}
