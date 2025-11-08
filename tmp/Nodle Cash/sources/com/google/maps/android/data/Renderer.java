package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.R;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlStyle;
import com.google.maps.android.data.kml.KmlUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int MARKER_ICON_SIZE = 32;
    private static final DecimalFormat sScaleFormat = new DecimalFormat("#.####");
    private final BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    /* access modifiers changed from: private */
    public Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures;
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlayMap;
    private final GroundOverlayManager.Collection mGroundOverlays;
    private ImagesCache mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final Set<String> mMarkerIconUrls;
    private final MarkerManager.Collection mMarkers;
    private int mNumActiveDownloads;
    private final PolygonManager.Collection mPolygons;
    private final PolylineManager.Collection mPolylines;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    public static final class ImagesCache {
        final Map<String, Bitmap> bitmapCache = new HashMap();
        final Map<String, BitmapDescriptor> groundOverlayImagesCache = new HashMap();
        final Map<String, Map<String, BitmapDescriptor>> markerImagesCache = new HashMap();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Renderer(GoogleMap googleMap, Context context, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager, @Nullable ImagesCache imagesCache) {
        this(googleMap, new HashSet(), (GeoJsonPointStyle) null, (GeoJsonLineStringStyle) null, (GeoJsonPolygonStyle) null, new BiMultiMap(), markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.mContext = context;
        this.mStylesRenderer = new HashMap<>();
        this.mImagesCache = imagesCache == null ? new ImagesCache() : imagesCache;
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Geometry addGeoJsonFeatureToMap : list) {
            arrayList.add(addGeoJsonFeatureToMap(geoJsonFeature, addGeoJsonFeatureToMap));
        }
        return arrayList;
    }

    private Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline addPolyline = this.mPolylines.addPolyline(polylineOptions);
        addPolyline.setClickable(polylineOptions.isClickable());
        return addPolyline;
    }

    private void addMarkerIcons(String str, double d2, MarkerOptions markerOptions) {
        BitmapDescriptor cachedMarkerImage = getCachedMarkerImage(str, d2);
        if (cachedMarkerImage != null) {
            markerOptions.icon(cachedMarkerImage);
        } else {
            this.mMarkerIconUrls.add(str);
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z2) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator it = kmlMultiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            arrayList.add(addKmlPlacemarkToMap(kmlPlacemark, (Geometry) it.next(), kmlStyle, kmlStyle2, z2));
        }
        return arrayList;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        for (GeoJsonLineString addLineStringToMap : geoJsonMultiLineString.getLineStrings()) {
            arrayList.add(addLineStringToMap(geoJsonLineStringStyle.toPolylineOptions(), addLineStringToMap));
        }
        return arrayList;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        for (GeoJsonPoint addPointToMap : geoJsonMultiPoint.getPoints()) {
            arrayList.add(addPointToMap(geoJsonPointStyle.toMarkerOptions(), addPointToMap));
        }
        return arrayList;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        for (GeoJsonPolygon addPolygonToMap : geoJsonMultiPolygon.getPolygons()) {
            arrayList.add(addPolygonToMap(geoJsonPolygonStyle.toPolygonOptions(), addPolygonToMap));
        }
        return arrayList;
    }

    private Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMarkers.addMarker(markerOptions);
    }

    private Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        for (List<LatLng> addHole : dataPolygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(addHole);
        }
        Polygon addPolygon = this.mPolygons.addPolygon(polygonOptions);
        addPolygon.setClickable(polygonOptions.isClickable());
        return addPolygon;
    }

    private void createInfoWindow() {
        this.mMarkers.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            public View getInfoContents(Marker marker) {
                View inflate = LayoutInflater.from(Renderer.this.mContext).inflate(R.layout.amu_info_window, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.window);
                if (marker.getSnippet() != null) {
                    textView.setText(Html.fromHtml(marker.getTitle() + "<br>" + marker.getSnippet()));
                } else {
                    textView.setText(Html.fromHtml(marker.getTitle()));
                }
                return inflate;
            }

            public View getInfoWindow(Marker marker) {
                return null;
            }
        });
    }

    public static boolean getPlacemarkVisibility(Feature feature) {
        return !feature.hasProperty("visibility") || Integer.parseInt(feature.getProperty("visibility")) != 0;
    }

    /* access modifiers changed from: private */
    public ArrayList<?> multiObjectHandler(Object obj) {
        for (Object next : getValues()) {
            if (next.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) next;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    private void putMarkerImagesCache(String str, String str2, BitmapDescriptor bitmapDescriptor) {
        Map map = this.mImagesCache.markerImagesCache.get(str);
        if (map == null) {
            map = new HashMap();
            this.mImagesCache.markerImagesCache.put(str, map);
        }
        map.put(str2, bitmapDescriptor);
    }

    private BitmapDescriptor scaleIcon(Bitmap bitmap, double d2) {
        int i3;
        int i4 = (int) (((double) (this.mContext.getResources().getDisplayMetrics().density * 32.0f)) * d2);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < height) {
            i3 = (int) (((float) (height * i4)) / ((float) width));
        } else if (width > height) {
            int i5 = (int) (((float) (width * i4)) / ((float) height));
            i3 = i4;
            i4 = i5;
        } else {
            i3 = i4;
        }
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, i4, i3, false));
    }

    private void setFeatureDefaultStyles(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.mDefaultPointStyle);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet("width")) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle kmlStyle, KmlStyle kmlStyle2) {
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        double iconScale = kmlStyle.isStyleSet("iconScale") ? kmlStyle.getIconScale() : kmlStyle2.isStyleSet("iconScale") ? kmlStyle2.getIconScale() : 1.0d;
        if (kmlStyle.isStyleSet("iconUrl")) {
            addMarkerIcons(kmlStyle.getIconUrl(), iconScale, markerOptions);
        } else if (kmlStyle2.getIconUrl() != null) {
            addMarkerIcons(kmlStyle2.getIconUrl(), iconScale, markerOptions);
        }
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet("width")) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    private void setMarkerInfoWindow(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        boolean hasProperty = kmlPlacemark.hasProperty("name");
        boolean hasProperty2 = kmlPlacemark.hasProperty("description");
        boolean hasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean containsKey = kmlStyle.getBalloonOptions().containsKey("text");
        if (hasBalloonStyle && containsKey) {
            marker.setTitle(KmlUtil.substituteProperties(kmlStyle.getBalloonOptions().get("text"), kmlPlacemark));
            createInfoWindow();
        } else if (hasBalloonStyle && hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        } else if (hasProperty && hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            marker.setSnippet(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        }
    }

    public void addFeature(Feature feature) {
        Object obj = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                removeFromMap(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    obj = addKmlPlacemarkToMap(kmlPlacemark, feature.getGeometry(), getPlacemarkStyle(feature.getId()), kmlPlacemark.getInlineStyle(), getPlacemarkVisibility(feature));
                } else {
                    obj = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put(feature, obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.google.android.gms.maps.model.PolygonOptions} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.google.android.gms.maps.model.PolylineOptions} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.maps.model.MarkerOptions] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object addGeoJsonFeatureToMap(com.google.maps.android.data.Feature r5, com.google.maps.android.data.Geometry r6) {
        /*
            r4 = this;
            java.lang.String r0 = r6.getGeometryType()
            r0.getClass()
            r1 = 0
            r2 = -1
            int r3 = r0.hashCode()
            switch(r3) {
                case -2116761119: goto L_0x0053;
                case -1065891849: goto L_0x0048;
                case -627102946: goto L_0x003d;
                case 77292912: goto L_0x0032;
                case 1267133722: goto L_0x0027;
                case 1806700869: goto L_0x001c;
                case 1950410960: goto L_0x0011;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x005d
        L_0x0011:
            java.lang.String r3 = "GeometryCollection"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x001a
            goto L_0x005d
        L_0x001a:
            r2 = 6
            goto L_0x005d
        L_0x001c:
            java.lang.String r3 = "LineString"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0025
            goto L_0x005d
        L_0x0025:
            r2 = 5
            goto L_0x005d
        L_0x0027:
            java.lang.String r3 = "Polygon"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0030
            goto L_0x005d
        L_0x0030:
            r2 = 4
            goto L_0x005d
        L_0x0032:
            java.lang.String r3 = "Point"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x003b
            goto L_0x005d
        L_0x003b:
            r2 = 3
            goto L_0x005d
        L_0x003d:
            java.lang.String r3 = "MultiLineString"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0046
            goto L_0x005d
        L_0x0046:
            r2 = 2
            goto L_0x005d
        L_0x0048:
            java.lang.String r3 = "MultiPoint"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x0051
            goto L_0x005d
        L_0x0051:
            r2 = 1
            goto L_0x005d
        L_0x0053:
            java.lang.String r3 = "MultiPolygon"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x005c
            goto L_0x005d
        L_0x005c:
            r2 = 0
        L_0x005d:
            switch(r2) {
                case 0: goto L_0x00dc;
                case 1: goto L_0x00cf;
                case 2: goto L_0x00c2;
                case 3: goto L_0x00a6;
                case 4: goto L_0x008a;
                case 5: goto L_0x006e;
                case 6: goto L_0x0061;
                default: goto L_0x0060;
            }
        L_0x0060:
            return r1
        L_0x0061:
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.maps.android.data.geojson.GeoJsonGeometryCollection r6 = (com.google.maps.android.data.geojson.GeoJsonGeometryCollection) r6
            java.util.List r6 = r6.getGeometries()
            java.util.ArrayList r4 = r4.addGeometryCollectionToMap(r5, r6)
            return r4
        L_0x006e:
            boolean r0 = r5 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r0 == 0) goto L_0x0079
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.android.gms.maps.model.PolylineOptions r1 = r5.getPolylineOptions()
            goto L_0x0083
        L_0x0079:
            boolean r0 = r5 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r0 == 0) goto L_0x0083
            com.google.maps.android.data.kml.KmlPlacemark r5 = (com.google.maps.android.data.kml.KmlPlacemark) r5
            com.google.android.gms.maps.model.PolylineOptions r1 = r5.getPolylineOptions()
        L_0x0083:
            com.google.maps.android.data.geojson.GeoJsonLineString r6 = (com.google.maps.android.data.geojson.GeoJsonLineString) r6
            com.google.android.gms.maps.model.Polyline r4 = r4.addLineStringToMap(r1, r6)
            return r4
        L_0x008a:
            boolean r0 = r5 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r0 == 0) goto L_0x0095
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.android.gms.maps.model.PolygonOptions r1 = r5.getPolygonOptions()
            goto L_0x009f
        L_0x0095:
            boolean r0 = r5 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r0 == 0) goto L_0x009f
            com.google.maps.android.data.kml.KmlPlacemark r5 = (com.google.maps.android.data.kml.KmlPlacemark) r5
            com.google.android.gms.maps.model.PolygonOptions r1 = r5.getPolygonOptions()
        L_0x009f:
            com.google.maps.android.data.DataPolygon r6 = (com.google.maps.android.data.DataPolygon) r6
            com.google.android.gms.maps.model.Polygon r4 = r4.addPolygonToMap(r1, r6)
            return r4
        L_0x00a6:
            boolean r0 = r5 instanceof com.google.maps.android.data.geojson.GeoJsonFeature
            if (r0 == 0) goto L_0x00b1
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.android.gms.maps.model.MarkerOptions r1 = r5.getMarkerOptions()
            goto L_0x00bb
        L_0x00b1:
            boolean r0 = r5 instanceof com.google.maps.android.data.kml.KmlPlacemark
            if (r0 == 0) goto L_0x00bb
            com.google.maps.android.data.kml.KmlPlacemark r5 = (com.google.maps.android.data.kml.KmlPlacemark) r5
            com.google.android.gms.maps.model.MarkerOptions r1 = r5.getMarkerOptions()
        L_0x00bb:
            com.google.maps.android.data.geojson.GeoJsonPoint r6 = (com.google.maps.android.data.geojson.GeoJsonPoint) r6
            com.google.android.gms.maps.model.Marker r4 = r4.addPointToMap(r1, r6)
            return r4
        L_0x00c2:
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.maps.android.data.geojson.GeoJsonLineStringStyle r5 = r5.getLineStringStyle()
            com.google.maps.android.data.geojson.GeoJsonMultiLineString r6 = (com.google.maps.android.data.geojson.GeoJsonMultiLineString) r6
            java.util.ArrayList r4 = r4.addMultiLineStringToMap(r5, r6)
            return r4
        L_0x00cf:
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.maps.android.data.geojson.GeoJsonPointStyle r5 = r5.getPointStyle()
            com.google.maps.android.data.geojson.GeoJsonMultiPoint r6 = (com.google.maps.android.data.geojson.GeoJsonMultiPoint) r6
            java.util.ArrayList r4 = r4.addMultiPointToMap(r5, r6)
            return r4
        L_0x00dc:
            com.google.maps.android.data.geojson.GeoJsonFeature r5 = (com.google.maps.android.data.geojson.GeoJsonFeature) r5
            com.google.maps.android.data.geojson.GeoJsonPolygonStyle r5 = r5.getPolygonStyle()
            com.google.maps.android.data.geojson.GeoJsonMultiPolygon r6 = (com.google.maps.android.data.geojson.GeoJsonMultiPolygon) r6
            java.util.ArrayList r4 = r4.addMultiPolygonToMap(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addGeoJsonFeatureToMap(com.google.maps.android.data.Feature, com.google.maps.android.data.Geometry):java.lang.Object");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r6.equals("Point") == false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark r12, com.google.maps.android.data.Geometry r13, com.google.maps.android.data.kml.KmlStyle r14, com.google.maps.android.data.kml.KmlStyle r15, boolean r16) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r3 = r14
            r4 = r15
            r5 = r16
            r2 = 0
            java.lang.String r6 = r13.getGeometryType()
            java.lang.String r7 = "drawOrder"
            boolean r8 = r12.hasProperty(r7)
            r9 = 0
            if (r8 == 0) goto L_0x001e
            java.lang.String r7 = r12.getProperty(r7)     // Catch:{ NumberFormatException -> 0x001d }
            float r9 = java.lang.Float.parseFloat(r7)     // Catch:{ NumberFormatException -> 0x001d }
            goto L_0x001e
        L_0x001d:
            r8 = r2
        L_0x001e:
            r6.getClass()
            r7 = -1
            int r10 = r6.hashCode()
            switch(r10) {
                case 77292912: goto L_0x004c;
                case 89139371: goto L_0x0041;
                case 1267133722: goto L_0x0036;
                case 1806700869: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            r2 = r7
            goto L_0x0055
        L_0x002b:
            java.lang.String r2 = "LineString"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x0034
            goto L_0x0029
        L_0x0034:
            r2 = 3
            goto L_0x0055
        L_0x0036:
            java.lang.String r2 = "Polygon"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x003f
            goto L_0x0029
        L_0x003f:
            r2 = 2
            goto L_0x0055
        L_0x0041:
            java.lang.String r2 = "MultiGeometry"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x004a
            goto L_0x0029
        L_0x004a:
            r2 = 1
            goto L_0x0055
        L_0x004c:
            java.lang.String r10 = "Point"
            boolean r6 = r6.equals(r10)
            if (r6 != 0) goto L_0x0055
            goto L_0x0029
        L_0x0055:
            switch(r2) {
                case 0: goto L_0x00be;
                case 1: goto L_0x00b0;
                case 2: goto L_0x0085;
                case 3: goto L_0x005a;
                default: goto L_0x0058;
            }
        L_0x0058:
            r0 = 0
            return r0
        L_0x005a:
            com.google.android.gms.maps.model.PolylineOptions r1 = r14.getPolylineOptions()
            if (r4 == 0) goto L_0x0064
            r11.setInlineLineStringStyle(r1, r15)
            goto L_0x0075
        L_0x0064:
            boolean r2 = r14.isLineRandomColorMode()
            if (r2 == 0) goto L_0x0075
            int r2 = r1.getColor()
            int r2 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r2)
            r1.color(r2)
        L_0x0075:
            r2 = r13
            com.google.maps.android.data.LineString r2 = (com.google.maps.android.data.LineString) r2
            com.google.android.gms.maps.model.Polyline r0 = r11.addLineStringToMap(r1, r2)
            r0.setVisible(r5)
            if (r8 == 0) goto L_0x0084
            r0.setZIndex(r9)
        L_0x0084:
            return r0
        L_0x0085:
            com.google.android.gms.maps.model.PolygonOptions r1 = r14.getPolygonOptions()
            if (r4 == 0) goto L_0x008f
            r11.setInlinePolygonStyle(r1, r15)
            goto L_0x00a0
        L_0x008f:
            boolean r2 = r14.isPolyRandomColorMode()
            if (r2 == 0) goto L_0x00a0
            int r2 = r1.getFillColor()
            int r2 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r2)
            r1.fillColor(r2)
        L_0x00a0:
            r2 = r13
            com.google.maps.android.data.DataPolygon r2 = (com.google.maps.android.data.DataPolygon) r2
            com.google.android.gms.maps.model.Polygon r0 = r11.addPolygonToMap(r1, r2)
            r0.setVisible(r5)
            if (r8 == 0) goto L_0x00af
            r0.setZIndex(r9)
        L_0x00af:
            return r0
        L_0x00b0:
            r2 = r13
            com.google.maps.android.data.kml.KmlMultiGeometry r2 = (com.google.maps.android.data.kml.KmlMultiGeometry) r2
            r0 = r11
            r1 = r12
            r3 = r14
            r4 = r15
            r5 = r16
            java.util.ArrayList r0 = r0.addMultiGeometryToMap(r1, r2, r3, r4, r5)
            return r0
        L_0x00be:
            com.google.android.gms.maps.model.MarkerOptions r2 = r14.getMarkerOptions()
            if (r4 == 0) goto L_0x00c8
            r11.setInlinePointStyle(r2, r15, r14)
            goto L_0x00d9
        L_0x00c8:
            java.lang.String r4 = r14.getIconUrl()
            if (r4 == 0) goto L_0x00d9
            java.lang.String r4 = r14.getIconUrl()
            double r6 = r14.getIconScale()
            r11.addMarkerIcons(r4, r6, r2)
        L_0x00d9:
            r4 = r13
            com.google.maps.android.data.kml.KmlPoint r4 = (com.google.maps.android.data.kml.KmlPoint) r4
            com.google.android.gms.maps.model.Marker r2 = r11.addPointToMap(r2, r4)
            r2.setVisible(r5)
            r11.setMarkerInfoWindow(r14, r2, r12)
            if (r8 == 0) goto L_0x00eb
            r2.setZIndex(r9)
        L_0x00eb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark, com.google.maps.android.data.Geometry, com.google.maps.android.data.kml.KmlStyle, com.google.maps.android.data.kml.KmlStyle, boolean):java.lang.Object");
    }

    public void assignStyleMap(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2) {
        for (String next : hashMap.keySet()) {
            String str = hashMap.get(next);
            if (hashMap2.containsKey(str)) {
                hashMap2.put(next, hashMap2.get(str));
            }
        }
    }

    public GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.mGroundOverlays.addGroundOverlay(groundOverlayOptions);
    }

    public void cacheBitmap(String str, Bitmap bitmap) {
        this.mImagesCache.bitmapCache.put(str, bitmap);
    }

    public void checkClearBitmapCache() {
        ImagesCache imagesCache;
        if (this.mNumActiveDownloads == 0 && (imagesCache = this.mImagesCache) != null && !imagesCache.bitmapCache.isEmpty()) {
            this.mImagesCache.bitmapCache.clear();
        }
    }

    public void clearStylesRenderer() {
        this.mStylesRenderer.clear();
    }

    public void downloadFinished() {
        this.mNumActiveDownloads--;
        checkClearBitmapCache();
    }

    public void downloadStarted() {
        this.mNumActiveDownloads++;
    }

    public HashMap<? extends Feature, Object> getAllFeatures() {
        return this.mFeatures;
    }

    public BitmapDescriptor getCachedGroundOverlayImage(String str) {
        Bitmap bitmap;
        BitmapDescriptor bitmapDescriptor = this.mImagesCache.groundOverlayImagesCache.get(str);
        if (bitmapDescriptor != null || (bitmap = this.mImagesCache.bitmapCache.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
        this.mImagesCache.groundOverlayImagesCache.put(str, fromBitmap);
        return fromBitmap;
    }

    public BitmapDescriptor getCachedMarkerImage(String str, double d2) {
        Bitmap bitmap;
        String format = sScaleFormat.format(d2);
        Map map = this.mImagesCache.markerImagesCache.get(str);
        BitmapDescriptor bitmapDescriptor = map != null ? (BitmapDescriptor) map.get(format) : null;
        if (bitmapDescriptor != null || (bitmap = this.mImagesCache.bitmapCache.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor scaleIcon = scaleIcon(bitmap, d2);
        putMarkerImagesCache(str, format, scaleIcon);
        return scaleIcon;
    }

    public Feature getContainerFeature(Object obj) {
        BiMultiMap<Feature> biMultiMap = this.mContainerFeatures;
        if (biMultiMap != null) {
            return biMultiMap.getKey(obj);
        }
        return null;
    }

    public ArrayList<KmlContainer> getContainerList() {
        return this.mContainers;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mDefaultLineStringStyle;
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mDefaultPointStyle;
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mDefaultPolygonStyle;
    }

    public Feature getFeature(Object obj) {
        return this.mFeatures.getKey(obj);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlayMap;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    public Set<String> getMarkerIconUrls() {
        return this.mMarkerIconUrls;
    }

    public KmlStyle getPlacemarkStyle(String str) {
        return this.mStylesRenderer.get(str) != null ? this.mStylesRenderer.get(str) : this.mStylesRenderer.get((Object) null);
    }

    public HashMap<String, String> getStyleMaps() {
        return this.mStyleMaps;
    }

    public HashMap<String, KmlStyle> getStylesRenderer() {
        return this.mStylesRenderer;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    public boolean hasFeatures() {
        return this.mFeatures.size() > 0;
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    public void putContainerFeature(Object obj, Feature feature) {
        this.mContainerFeatures.put(feature, obj);
    }

    public void putFeatures(Feature feature, Object obj) {
        this.mFeatures.put(feature, obj);
    }

    public void putStyles() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    public void removeFeature(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            removeFromMap(this.mFeatures.remove(feature));
        }
    }

    public void removeFeatures(HashMap<? extends Feature, Object> hashMap) {
        removeFeatures((Collection) hashMap.values());
    }

    public void removeFromMap(Object obj) {
        if (obj instanceof Marker) {
            this.mMarkers.remove((Marker) obj);
        } else if (obj instanceof Polyline) {
            this.mPolylines.remove((Polyline) obj);
        } else if (obj instanceof Polygon) {
            this.mPolygons.remove((Polygon) obj);
        } else if (obj instanceof GroundOverlay) {
            this.mGroundOverlays.remove((GroundOverlay) obj);
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                removeFromMap(it.next());
            }
        }
    }

    public void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> hashMap) {
        for (GroundOverlay next : hashMap.values()) {
            if (next != null) {
                this.mGroundOverlays.remove(next);
            }
        }
    }

    public void setLayerVisibility(boolean z2) {
        this.mLayerOnMap = z2;
    }

    public void setMap(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    public void setOnFeatureClickListener(final Layer.OnFeatureClickListener onFeatureClickListener) {
        this.mPolygons.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            public void onPolygonClick(Polygon polygon) {
                if (Renderer.this.getFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getFeature(polygon));
                } else if (Renderer.this.getContainerFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getContainerFeature(polygon));
                } else {
                    Layer.OnFeatureClickListener onFeatureClickListener = onFeatureClickListener;
                    Renderer renderer = Renderer.this;
                    onFeatureClickListener.onFeatureClick(renderer.getFeature(renderer.multiObjectHandler(polygon)));
                }
            }
        });
        this.mMarkers.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                if (Renderer.this.getFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getFeature(marker));
                    return false;
                } else if (Renderer.this.getContainerFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getContainerFeature(marker));
                    return false;
                } else {
                    Layer.OnFeatureClickListener onFeatureClickListener = onFeatureClickListener;
                    Renderer renderer = Renderer.this;
                    onFeatureClickListener.onFeatureClick(renderer.getFeature(renderer.multiObjectHandler(marker)));
                    return false;
                }
            }
        });
        this.mPolylines.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
            public void onPolylineClick(Polyline polyline) {
                if (Renderer.this.getFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getFeature(polyline));
                } else if (Renderer.this.getContainerFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Renderer.this.getContainerFeature(polyline));
                } else {
                    Layer.OnFeatureClickListener onFeatureClickListener = onFeatureClickListener;
                    Renderer renderer = Renderer.this;
                    onFeatureClickListener.onFeatureClick(renderer.getFeature(renderer.multiObjectHandler(polyline)));
                }
            }
        });
    }

    public void storeData(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        this.mStyles = hashMap;
        this.mStyleMaps = hashMap2;
        this.mFeatures.putAll(hashMap3);
        this.mContainers = arrayList;
        this.mGroundOverlayMap = hashMap4;
    }

    private void removeFeatures(Collection collection) {
        for (Object next : collection) {
            if (next instanceof Collection) {
                removeFeatures((Collection) next);
            } else if (next instanceof Marker) {
                this.mMarkers.remove((Marker) next);
            } else if (next instanceof Polyline) {
                this.mPolylines.remove((Polyline) next);
            } else if (next instanceof Polygon) {
                this.mPolygons.remove((Polygon) next);
            }
        }
    }

    public void putStyles(HashMap<String, KmlStyle> hashMap) {
        this.mStylesRenderer.putAll(hashMap);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> hashMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this(googleMap, (Set<String>) null, new GeoJsonPointStyle(), new GeoJsonLineStringStyle(), new GeoJsonPolygonStyle(), (BiMultiMap<Feature>) null, markerManager, polygonManager, polylineManager, groundOverlayManager);
        HashMap<? extends Feature, Object> hashMap2 = hashMap;
        this.mFeatures.putAll(hashMap);
        this.mImagesCache = null;
    }

    private Renderer(GoogleMap googleMap, Set<String> set, GeoJsonPointStyle geoJsonPointStyle, GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonPolygonStyle geoJsonPolygonStyle, BiMultiMap<Feature> biMultiMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this.mFeatures = new BiMultiMap<>();
        this.mNumActiveDownloads = 0;
        this.mMap = googleMap;
        this.mLayerOnMap = false;
        this.mMarkerIconUrls = set;
        this.mDefaultPointStyle = geoJsonPointStyle;
        this.mDefaultLineStringStyle = geoJsonLineStringStyle;
        this.mDefaultPolygonStyle = geoJsonPolygonStyle;
        this.mContainerFeatures = biMultiMap;
        if (googleMap != null) {
            this.mMarkers = (markerManager == null ? new MarkerManager(googleMap) : markerManager).newCollection();
            this.mPolygons = (polygonManager == null ? new PolygonManager(googleMap) : polygonManager).newCollection();
            this.mPolylines = (polylineManager == null ? new PolylineManager(googleMap) : polylineManager).newCollection();
            this.mGroundOverlays = (groundOverlayManager == null ? new GroundOverlayManager(googleMap) : groundOverlayManager).newCollection();
            return;
        }
        this.mMarkers = null;
        this.mPolygons = null;
        this.mPolylines = null;
        this.mGroundOverlays = null;
    }
}
