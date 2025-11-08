package com.google.maps.android.data;

import java.util.ArrayList;
import java.util.List;

public class MultiGeometry implements Geometry {
    private String geometryType = "MultiGeometry";
    private List<Geometry> mGeometries;

    public MultiGeometry(List<? extends Geometry> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Geometry add : list) {
                arrayList.add(add);
            }
            this.mGeometries = arrayList;
            return;
        }
        throw new IllegalArgumentException("Geometries cannot be null");
    }

    public String getGeometryType() {
        return this.geometryType;
    }

    public void setGeometryType(String str) {
        this.geometryType = str;
    }

    public String toString() {
        String str = this.geometryType.equals("MultiPoint") ? "LineStrings=" : "Geometries=";
        if (this.geometryType.equals("MultiLineString")) {
            str = "points=";
        }
        if (this.geometryType.equals("MultiPolygon")) {
            str = "Polygons=";
        }
        return getGeometryType() + "{" + "\n ".concat(str) + getGeometryObject() + "\n}\n";
    }

    public List<Geometry> getGeometryObject() {
        return this.mGeometries;
    }
}
