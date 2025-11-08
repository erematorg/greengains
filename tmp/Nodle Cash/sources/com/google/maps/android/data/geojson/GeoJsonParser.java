package com.google.maps.android.data.geojson;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeoJsonParser {
    private static final String BOUNDING_BOX = "bbox";
    private static final String FEATURE = "Feature";
    private static final String FEATURE_COLLECTION = "FeatureCollection";
    private static final String FEATURE_COLLECTION_ARRAY = "features";
    private static final String FEATURE_GEOMETRY = "geometry";
    private static final String FEATURE_ID = "id";
    private static final String GEOMETRY_COLLECTION = "GeometryCollection";
    private static final String GEOMETRY_COLLECTION_ARRAY = "geometries";
    private static final String GEOMETRY_COORDINATES_ARRAY = "coordinates";
    private static final String LINESTRING = "LineString";
    private static final String LOG_TAG = "GeoJsonParser";
    private static final String MULTILINESTRING = "MultiLineString";
    private static final String MULTIPOINT = "MultiPoint";
    private static final String MULTIPOLYGON = "MultiPolygon";
    private static final String POINT = "Point";
    private static final String POLYGON = "Polygon";
    private static final String PROPERTIES = "properties";
    private LatLngBounds mBoundingBox = null;
    private final ArrayList<GeoJsonFeature> mGeoJsonFeatures = new ArrayList<>();
    private final JSONObject mGeoJsonFile;

    public static class LatLngAlt {
        public final Double altitude;
        public final LatLng latLng;

        public LatLngAlt(LatLng latLng2, Double d2) {
            this.latLng = latLng2;
            this.altitude = d2;
        }
    }

    public GeoJsonParser(JSONObject jSONObject) {
        this.mGeoJsonFile = jSONObject;
        parseGeoJson();
    }

    private static Geometry createGeometry(String str, JSONArray jSONArray) throws JSONException {
        str.getClass();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -2116761119:
                if (str.equals(MULTIPOLYGON)) {
                    c3 = 0;
                    break;
                }
                break;
            case -1065891849:
                if (str.equals(MULTIPOINT)) {
                    c3 = 1;
                    break;
                }
                break;
            case -627102946:
                if (str.equals(MULTILINESTRING)) {
                    c3 = 2;
                    break;
                }
                break;
            case 77292912:
                if (str.equals(POINT)) {
                    c3 = 3;
                    break;
                }
                break;
            case 1267133722:
                if (str.equals("Polygon")) {
                    c3 = 4;
                    break;
                }
                break;
            case 1806700869:
                if (str.equals(LINESTRING)) {
                    c3 = 5;
                    break;
                }
                break;
            case 1950410960:
                if (str.equals(GEOMETRY_COLLECTION)) {
                    c3 = 6;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                return createMultiPolygon(jSONArray);
            case 1:
                return createMultiPoint(jSONArray);
            case 2:
                return createMultiLineString(jSONArray);
            case 3:
                return createPoint(jSONArray);
            case 4:
                return createPolygon(jSONArray);
            case 5:
                return createLineString(jSONArray);
            case 6:
                return createGeometryCollection(jSONArray);
            default:
                return null;
        }
    }

    private static GeoJsonGeometryCollection createGeometryCollection(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            Geometry parseGeometry = parseGeometry(jSONArray.getJSONObject(i3));
            if (parseGeometry != null) {
                arrayList.add(parseGeometry);
            }
        }
        return new GeoJsonGeometryCollection(arrayList);
    }

    private static GeoJsonLineString createLineString(JSONArray jSONArray) throws JSONException {
        ArrayList<LatLngAlt> parseCoordinatesArray = parseCoordinatesArray(jSONArray);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<LatLngAlt> it = parseCoordinatesArray.iterator();
        while (it.hasNext()) {
            LatLngAlt next = it.next();
            arrayList.add(next.latLng);
            Double d2 = next.altitude;
            if (d2 != null) {
                arrayList2.add(d2);
            }
        }
        return new GeoJsonLineString(arrayList, arrayList2);
    }

    private static GeoJsonMultiLineString createMultiLineString(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            arrayList.add(createLineString(jSONArray.getJSONArray(i3)));
        }
        return new GeoJsonMultiLineString(arrayList);
    }

    private static GeoJsonMultiPoint createMultiPoint(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            arrayList.add(createPoint(jSONArray.getJSONArray(i3)));
        }
        return new GeoJsonMultiPoint(arrayList);
    }

    private static GeoJsonMultiPolygon createMultiPolygon(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            arrayList.add(createPolygon(jSONArray.getJSONArray(i3)));
        }
        return new GeoJsonMultiPolygon(arrayList);
    }

    private static GeoJsonPoint createPoint(JSONArray jSONArray) throws JSONException {
        LatLngAlt parseCoordinate = parseCoordinate(jSONArray);
        return new GeoJsonPoint(parseCoordinate.latLng, parseCoordinate.altitude);
    }

    private static GeoJsonPolygon createPolygon(JSONArray jSONArray) throws JSONException {
        return new GeoJsonPolygon(parseCoordinatesArrays(jSONArray));
    }

    private static boolean isGeometry(String str) {
        return str.matches("Point|MultiPoint|LineString|MultiLineString|Polygon|MultiPolygon|GeometryCollection");
    }

    private static LatLngBounds parseBoundingBox(JSONArray jSONArray) throws JSONException {
        return new LatLngBounds(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), new LatLng(jSONArray.getDouble(3), jSONArray.getDouble(2)));
    }

    private static LatLngAlt parseCoordinate(JSONArray jSONArray) throws JSONException {
        return new LatLngAlt(new LatLng(jSONArray.getDouble(1), jSONArray.getDouble(0)), jSONArray.length() < 3 ? null : Double.valueOf(jSONArray.getDouble(2)));
    }

    private static ArrayList<LatLngAlt> parseCoordinatesArray(JSONArray jSONArray) throws JSONException {
        ArrayList<LatLngAlt> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            arrayList.add(parseCoordinate(jSONArray.getJSONArray(i3)));
        }
        return arrayList;
    }

    private static ArrayList<ArrayList<LatLng>> parseCoordinatesArrays(JSONArray jSONArray) throws JSONException {
        ArrayList<ArrayList<LatLng>> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            ArrayList<LatLngAlt> parseCoordinatesArray = parseCoordinatesArray(jSONArray.getJSONArray(i3));
            ArrayList arrayList2 = new ArrayList();
            Iterator<LatLngAlt> it = parseCoordinatesArray.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().latLng);
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private static GeoJsonFeature parseFeature(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String string = jSONObject.has("id") ? jSONObject.getString("id") : null;
            LatLngBounds parseBoundingBox = jSONObject.has(BOUNDING_BOX) ? parseBoundingBox(jSONObject.getJSONArray(BOUNDING_BOX)) : null;
            Geometry parseGeometry = (!jSONObject.has(FEATURE_GEOMETRY) || jSONObject.isNull(FEATURE_GEOMETRY)) ? null : parseGeometry(jSONObject.getJSONObject(FEATURE_GEOMETRY));
            if (jSONObject.has("properties") && !jSONObject.isNull("properties")) {
                hashMap = parseProperties(jSONObject.getJSONObject("properties"));
            }
            return new GeoJsonFeature(parseGeometry, string, hashMap, parseBoundingBox);
        } catch (JSONException unused) {
            Log.w(LOG_TAG, "Feature could not be successfully parsed " + jSONObject.toString());
            return null;
        }
    }

    private ArrayList<GeoJsonFeature> parseFeatureCollection(JSONObject jSONObject) {
        ArrayList<GeoJsonFeature> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(FEATURE_COLLECTION_ARRAY);
            if (jSONObject.has(BOUNDING_BOX)) {
                this.mBoundingBox = parseBoundingBox(jSONObject.getJSONArray(BOUNDING_BOX));
            }
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                    if (jSONObject2.getString("type").equals(FEATURE)) {
                        GeoJsonFeature parseFeature = parseFeature(jSONObject2);
                        if (parseFeature != null) {
                            arrayList.add(parseFeature);
                        } else {
                            Log.w(LOG_TAG, "Index of Feature in Feature Collection that could not be created: " + i3);
                        }
                    }
                } catch (JSONException unused) {
                    Log.w(LOG_TAG, "Index of Feature in Feature Collection that could not be created: " + i3);
                }
            }
            return arrayList;
        } catch (JSONException unused2) {
            Log.w(LOG_TAG, "Feature Collection could not be created.");
            return arrayList;
        }
    }

    private void parseGeoJson() {
        try {
            String string = this.mGeoJsonFile.getString("type");
            if (string.equals(FEATURE)) {
                GeoJsonFeature parseFeature = parseFeature(this.mGeoJsonFile);
                if (parseFeature != null) {
                    this.mGeoJsonFeatures.add(parseFeature);
                }
            } else if (string.equals(FEATURE_COLLECTION)) {
                this.mGeoJsonFeatures.addAll(parseFeatureCollection(this.mGeoJsonFile));
            } else if (isGeometry(string)) {
                GeoJsonFeature parseGeometryToFeature = parseGeometryToFeature(this.mGeoJsonFile);
                if (parseGeometryToFeature != null) {
                    this.mGeoJsonFeatures.add(parseGeometryToFeature);
                }
            } else {
                Log.w(LOG_TAG, "GeoJSON file could not be parsed.");
            }
        } catch (JSONException unused) {
            Log.w(LOG_TAG, "GeoJSON file could not be parsed.");
        }
    }

    public static Geometry parseGeometry(JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            String string = jSONObject.getString("type");
            if (string.equals(GEOMETRY_COLLECTION)) {
                jSONArray = jSONObject.getJSONArray(GEOMETRY_COLLECTION_ARRAY);
            } else {
                if (isGeometry(string)) {
                    jSONArray = jSONObject.getJSONArray(GEOMETRY_COORDINATES_ARRAY);
                }
                return null;
            }
            return createGeometry(string, jSONArray);
        } catch (JSONException unused) {
        }
    }

    private static GeoJsonFeature parseGeometryToFeature(JSONObject jSONObject) {
        Geometry parseGeometry = parseGeometry(jSONObject);
        if (parseGeometry != null) {
            return new GeoJsonFeature(parseGeometry, (String) null, new HashMap(), (LatLngBounds) null);
        }
        Log.w(LOG_TAG, "Geometry could not be parsed");
        return null;
    }

    private static HashMap<String, String> parseProperties(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.isNull(next) ? null : jSONObject.getString(next));
        }
        return hashMap;
    }

    public LatLngBounds getBoundingBox() {
        return this.mBoundingBox;
    }

    public ArrayList<GeoJsonFeature> getFeatures() {
        return this.mGeoJsonFeatures;
    }
}
