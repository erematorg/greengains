package com.google.maps.android.data.kml;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.DataPolygon;
import java.util.ArrayList;
import java.util.List;

public class KmlPolygon implements DataPolygon<ArrayList<ArrayList<LatLng>>> {
    public static final String GEOMETRY_TYPE = "Polygon";
    private final List<List<LatLng>> mInnerBoundaryCoordinates;
    private final List<LatLng> mOuterBoundaryCoordinates;

    public KmlPolygon(List<LatLng> list, List<List<LatLng>> list2) {
        if (list != null) {
            this.mOuterBoundaryCoordinates = list;
            this.mInnerBoundaryCoordinates = list2;
            return;
        }
        throw new IllegalArgumentException("Outer boundary coordinates cannot be null");
    }

    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public List<List<LatLng>> getInnerBoundaryCoordinates() {
        return this.mInnerBoundaryCoordinates;
    }

    public List<LatLng> getOuterBoundaryCoordinates() {
        return this.mOuterBoundaryCoordinates;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon{\n outer coordinates=");
        sb.append(this.mOuterBoundaryCoordinates);
        sb.append(",\n inner coordinates=");
        return C0118y.h("\n}\n", this.mInnerBoundaryCoordinates, sb);
    }

    public List<List<LatLng>> getGeometryObject() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mOuterBoundaryCoordinates);
        List<List<LatLng>> list = this.mInnerBoundaryCoordinates;
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }
}
