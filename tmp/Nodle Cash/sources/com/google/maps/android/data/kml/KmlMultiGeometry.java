package com.google.maps.android.data.kml;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;

public class KmlMultiGeometry extends MultiGeometry {
    public KmlMultiGeometry(ArrayList<Geometry> arrayList) {
        super(arrayList);
    }

    public String toString() {
        return getGeometryType() + "{\n geometries=" + getGeometryObject() + "\n}\n";
    }

    public ArrayList<Geometry> getGeometryObject() {
        return new ArrayList<>(super.getGeometryObject());
    }
}
