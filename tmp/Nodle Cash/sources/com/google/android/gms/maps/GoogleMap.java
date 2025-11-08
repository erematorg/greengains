package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzau;
import com.google.android.gms.internal.maps.zzv;
import com.google.android.gms.internal.maps.zzy;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzab;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzaf;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbh;
import com.google.android.gms.maps.internal.zzbj;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.internal.zzn;
import com.google.android.gms.maps.internal.zzp;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzt;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.internal.zzz;
import com.google.android.gms.maps.model.AdvancedMarker;
import com.google.android.gms.maps.model.AdvancedMarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.FeatureLayer;
import com.google.android.gms.maps.model.FeatureLayerOptions;
import com.google.android.gms.maps.model.FeatureType;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapCapabilities;
import com.google.android.gms.maps.model.MapColorScheme;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import java.util.HashMap;
import java.util.Map;

public class GoogleMap {
    @NonNull
    public static final String DEMO_MAP_ID = "demo_map_id";
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate zza;
    private MapCapabilities zzb;
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private UiSettings zze;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        @Nullable
        View getInfoContents(@NonNull Marker marker);

        @Nullable
        View getInfoWindow(@NonNull Marker marker);
    }

    @Deprecated
    public interface OnCameraChangeListener {
        void onCameraChange(@NonNull CameraPosition cameraPosition);
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onCameraMoveStarted(int i3);
    }

    public interface OnCircleClickListener {
        void onCircleClick(@NonNull Circle circle);
    }

    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(@NonNull GroundOverlay groundOverlay);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(@NonNull IndoorBuilding indoorBuilding);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(@NonNull Marker marker);
    }

    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(@NonNull Marker marker);
    }

    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(@NonNull Marker marker);
    }

    public interface OnMapCapabilitiesChangedListener {
        void onMapCapabilitiesChanged(@NonNull MapCapabilities mapCapabilities);
    }

    public interface OnMapClickListener {
        void onMapClick(@NonNull LatLng latLng);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(@NonNull LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(@NonNull Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(@NonNull Marker marker);

        void onMarkerDragEnd(@NonNull Marker marker);

        void onMarkerDragStart(@NonNull Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(@NonNull Location location);
    }

    public interface OnMyLocationClickListener {
        void onMyLocationClick(@NonNull Location location);
    }

    public interface OnPoiClickListener {
        void onPoiClick(@NonNull PointOfInterest pointOfInterest);
    }

    public interface OnPolygonClickListener {
        void onPolygonClick(@NonNull Polygon polygon);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(@NonNull Polyline polyline);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(@Nullable Bitmap bitmap);
    }

    public GoogleMap(@NonNull IGoogleMapDelegate iGoogleMapDelegate) {
        this.zza = (IGoogleMapDelegate) Preconditions.checkNotNull(iGoogleMapDelegate);
    }

    @NonNull
    public final Circle addCircle(@NonNull CircleOptions circleOptions) {
        try {
            Preconditions.checkNotNull(circleOptions, "CircleOptions must not be null.");
            return new Circle(this.zza.addCircle(circleOptions));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Nullable
    public final GroundOverlay addGroundOverlay(@NonNull GroundOverlayOptions groundOverlayOptions) {
        try {
            Preconditions.checkNotNull(groundOverlayOptions, "GroundOverlayOptions must not be null.");
            zzv addGroundOverlay = this.zza.addGroundOverlay(groundOverlayOptions);
            if (addGroundOverlay != null) {
                return new GroundOverlay(addGroundOverlay);
            }
            return null;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Nullable
    public final Marker addMarker(@NonNull MarkerOptions markerOptions) {
        if (markerOptions instanceof AdvancedMarkerOptions) {
            markerOptions.zzf(1);
        }
        try {
            Preconditions.checkNotNull(markerOptions, "MarkerOptions must not be null.");
            zzah addMarker = this.zza.addMarker(markerOptions);
            if (addMarker != null) {
                return markerOptions.zzb() == 1 ? new AdvancedMarker(addMarker) : new Marker(addMarker);
            }
            return null;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void addOnMapCapabilitiesChangedListener(@NonNull OnMapCapabilitiesChangedListener onMapCapabilitiesChangedListener) {
        try {
            zzs zzs = new zzs(this, onMapCapabilitiesChangedListener);
            this.zzc.put(onMapCapabilitiesChangedListener, zzs);
            this.zza.addOnMapCapabilitiesChangedListener(zzs);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public final Polygon addPolygon(@NonNull PolygonOptions polygonOptions) {
        try {
            Preconditions.checkNotNull(polygonOptions, "PolygonOptions must not be null");
            return new Polygon(this.zza.addPolygon(polygonOptions));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public final Polyline addPolyline(@NonNull PolylineOptions polylineOptions) {
        try {
            Preconditions.checkNotNull(polylineOptions, "PolylineOptions must not be null");
            return new Polyline(this.zza.addPolyline(polylineOptions));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Nullable
    public final TileOverlay addTileOverlay(@NonNull TileOverlayOptions tileOverlayOptions) {
        try {
            Preconditions.checkNotNull(tileOverlayOptions, "TileOverlayOptions must not be null.");
            zzau addTileOverlay = this.zza.addTileOverlay(tileOverlayOptions);
            if (addTileOverlay != null) {
                return new TileOverlay(addTileOverlay);
            }
            return null;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate) {
        try {
            Preconditions.checkNotNull(cameraUpdate, "CameraUpdate must not be null.");
            this.zza.animateCamera(cameraUpdate.zza());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void clear() {
        try {
            this.zza.clear();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public final CameraPosition getCameraPosition() {
        try {
            return this.zza.getCameraPosition();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public FeatureLayer getFeatureLayer(@NonNull FeatureLayerOptions featureLayerOptions) {
        String featureType = featureLayerOptions.getFeatureType();
        String datasetId = featureLayerOptions.getDatasetId();
        boolean equals = featureType.equals(FeatureType.DATASET);
        FeatureLayer featureLayer = equals ? (FeatureLayer) this.zzd.get(datasetId) : (FeatureLayer) this.zzd.get(featureType);
        if (featureLayer == null) {
            try {
                featureLayer = new FeatureLayer(this.zza.getFeatureLayer(featureLayerOptions));
                if (equals) {
                    this.zzd.put(datasetId, featureLayer);
                } else {
                    this.zzd.put(featureType, featureLayer);
                }
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        }
        return featureLayer;
    }

    @Nullable
    public IndoorBuilding getFocusedBuilding() {
        try {
            zzy focusedBuilding = this.zza.getFocusedBuilding();
            if (focusedBuilding != null) {
                return new IndoorBuilding(focusedBuilding);
            }
            return null;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public MapCapabilities getMapCapabilities() {
        if (this.zzb == null) {
            try {
                this.zzb = new MapCapabilities(this.zza.getMapCapabilities());
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        }
        return this.zzb;
    }

    @MapColorScheme
    public int getMapColorScheme() {
        try {
            return this.zza.getMapColorScheme();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final int getMapType() {
        try {
            return this.zza.getMapType();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final float getMaxZoomLevel() {
        try {
            return this.zza.getMaxZoomLevel();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final float getMinZoomLevel() {
        try {
            return this.zza.getMinZoomLevel();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.zza.getMyLocation();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public final Projection getProjection() {
        try {
            return new Projection(this.zza.getProjection());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @NonNull
    public final UiSettings getUiSettings() {
        try {
            if (this.zze == null) {
                this.zze = new UiSettings(this.zza.getUiSettings());
            }
            return this.zze;
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final boolean isBuildingsEnabled() {
        try {
            return this.zza.isBuildingsEnabled();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final boolean isIndoorEnabled() {
        try {
            return this.zza.isIndoorEnabled();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.zza.isMyLocationEnabled();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.zza.isTrafficEnabled();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void moveCamera(@NonNull CameraUpdate cameraUpdate) {
        try {
            Preconditions.checkNotNull(cameraUpdate, "CameraUpdate must not be null.");
            this.zza.moveCamera(cameraUpdate.zza());
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void removeOnMapCapabilitiesChangedListener(@NonNull OnMapCapabilitiesChangedListener onMapCapabilitiesChangedListener) {
        try {
            if (this.zzc.containsKey(onMapCapabilitiesChangedListener)) {
                this.zza.removeOnMapCapabilitiesChangedListener((zzal) this.zzc.get(onMapCapabilitiesChangedListener));
                this.zzc.remove(onMapCapabilitiesChangedListener);
            }
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public void resetMinMaxZoomPreference() {
        try {
            this.zza.resetMinMaxZoomPreference();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setBuildingsEnabled(boolean z2) {
        try {
            this.zza.setBuildingsEnabled(z2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setContentDescription(@Nullable String str) {
        try {
            this.zza.setContentDescription(str);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final boolean setIndoorEnabled(boolean z2) {
        try {
            return this.zza.setIndoorEnabled(z2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setInfoWindowAdapter(@Nullable InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            try {
                this.zza.setInfoWindowAdapter((zzi) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setInfoWindowAdapter(new zzf(this, infoWindowAdapter));
        }
    }

    public void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) {
        try {
            this.zza.setLatLngBoundsForCameraTarget(latLngBounds);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setLocationSource(@Nullable LocationSource locationSource) {
        if (locationSource == null) {
            try {
                this.zza.setLocationSource((ILocationSourceDelegate) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setLocationSource(new zzt(this, locationSource));
        }
    }

    public void setMapColorScheme(@MapColorScheme int i3) {
        try {
            this.zza.setMapColorScheme(i3);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions) {
        try {
            return this.zza.setMapStyle(mapStyleOptions);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setMapType(int i3) {
        try {
            this.zza.setMapType(i3);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public void setMaxZoomPreference(float f2) {
        try {
            this.zza.setMaxZoomPreference(f2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public void setMinZoomPreference(float f2) {
        try {
            this.zza.setMinZoomPreference(f2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void setMyLocationEnabled(boolean z2) {
        try {
            this.zza.setMyLocationEnabled(z2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    @Deprecated
    public final void setOnCameraChangeListener(@Nullable OnCameraChangeListener onCameraChangeListener) {
        if (onCameraChangeListener == null) {
            try {
                this.zza.setOnCameraChangeListener((zzn) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCameraChangeListener(new zzu(this, onCameraChangeListener));
        }
    }

    public final void setOnCameraIdleListener(@Nullable OnCameraIdleListener onCameraIdleListener) {
        if (onCameraIdleListener == null) {
            try {
                this.zza.setOnCameraIdleListener((zzp) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCameraIdleListener(new zzy(this, onCameraIdleListener));
        }
    }

    public final void setOnCameraMoveCanceledListener(@Nullable OnCameraMoveCanceledListener onCameraMoveCanceledListener) {
        if (onCameraMoveCanceledListener == null) {
            try {
                this.zza.setOnCameraMoveCanceledListener((zzr) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCameraMoveCanceledListener(new zzx(this, onCameraMoveCanceledListener));
        }
    }

    public final void setOnCameraMoveListener(@Nullable OnCameraMoveListener onCameraMoveListener) {
        if (onCameraMoveListener == null) {
            try {
                this.zza.setOnCameraMoveListener((zzt) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCameraMoveListener(new zzw(this, onCameraMoveListener));
        }
    }

    public final void setOnCameraMoveStartedListener(@Nullable OnCameraMoveStartedListener onCameraMoveStartedListener) {
        if (onCameraMoveStartedListener == null) {
            try {
                this.zza.setOnCameraMoveStartedListener((com.google.android.gms.maps.internal.zzv) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCameraMoveStartedListener(new zzv(this, onCameraMoveStartedListener));
        }
    }

    public final void setOnCircleClickListener(@Nullable OnCircleClickListener onCircleClickListener) {
        if (onCircleClickListener == null) {
            try {
                this.zza.setOnCircleClickListener((zzx) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnCircleClickListener(new zzn(this, onCircleClickListener));
        }
    }

    public final void setOnGroundOverlayClickListener(@Nullable OnGroundOverlayClickListener onGroundOverlayClickListener) {
        if (onGroundOverlayClickListener == null) {
            try {
                this.zza.setOnGroundOverlayClickListener((zzz) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnGroundOverlayClickListener(new zzm(this, onGroundOverlayClickListener));
        }
    }

    public final void setOnIndoorStateChangeListener(@Nullable OnIndoorStateChangeListener onIndoorStateChangeListener) {
        if (onIndoorStateChangeListener == null) {
            try {
                this.zza.setOnIndoorStateChangeListener((zzab) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnIndoorStateChangeListener(new zzk(this, onIndoorStateChangeListener));
        }
    }

    public final void setOnInfoWindowClickListener(@Nullable OnInfoWindowClickListener onInfoWindowClickListener) {
        if (onInfoWindowClickListener == null) {
            try {
                this.zza.setOnInfoWindowClickListener((zzad) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnInfoWindowClickListener(new zzc(this, onInfoWindowClickListener));
        }
    }

    public final void setOnInfoWindowCloseListener(@Nullable OnInfoWindowCloseListener onInfoWindowCloseListener) {
        if (onInfoWindowCloseListener == null) {
            try {
                this.zza.setOnInfoWindowCloseListener((zzaf) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnInfoWindowCloseListener(new zze(this, onInfoWindowCloseListener));
        }
    }

    public final void setOnInfoWindowLongClickListener(@Nullable OnInfoWindowLongClickListener onInfoWindowLongClickListener) {
        if (onInfoWindowLongClickListener == null) {
            try {
                this.zza.setOnInfoWindowLongClickListener((com.google.android.gms.maps.internal.zzah) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnInfoWindowLongClickListener(new zzd(this, onInfoWindowLongClickListener));
        }
    }

    public final void setOnMapClickListener(@Nullable OnMapClickListener onMapClickListener) {
        if (onMapClickListener == null) {
            try {
                this.zza.setOnMapClickListener((zzan) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMapClickListener(new zzz(this, onMapClickListener));
        }
    }

    public void setOnMapLoadedCallback(@Nullable OnMapLoadedCallback onMapLoadedCallback) {
        if (onMapLoadedCallback == null) {
            try {
                this.zza.setOnMapLoadedCallback((zzap) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMapLoadedCallback(new zzj(this, onMapLoadedCallback));
        }
    }

    public final void setOnMapLongClickListener(@Nullable OnMapLongClickListener onMapLongClickListener) {
        if (onMapLongClickListener == null) {
            try {
                this.zza.setOnMapLongClickListener((zzar) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMapLongClickListener(new zzaa(this, onMapLongClickListener));
        }
    }

    public final void setOnMarkerClickListener(@Nullable OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null) {
            try {
                this.zza.setOnMarkerClickListener((zzav) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMarkerClickListener(new zza(this, onMarkerClickListener));
        }
    }

    public final void setOnMarkerDragListener(@Nullable OnMarkerDragListener onMarkerDragListener) {
        if (onMarkerDragListener == null) {
            try {
                this.zza.setOnMarkerDragListener((zzax) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMarkerDragListener(new zzb(this, onMarkerDragListener));
        }
    }

    public final void setOnMyLocationButtonClickListener(@Nullable OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        if (onMyLocationButtonClickListener == null) {
            try {
                this.zza.setOnMyLocationButtonClickListener((zzaz) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMyLocationButtonClickListener(new zzh(this, onMyLocationButtonClickListener));
        }
    }

    @Deprecated
    public final void setOnMyLocationChangeListener(@Nullable OnMyLocationChangeListener onMyLocationChangeListener) {
        if (onMyLocationChangeListener == null) {
            try {
                this.zza.setOnMyLocationChangeListener((zzbb) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMyLocationChangeListener(new zzg(this, onMyLocationChangeListener));
        }
    }

    public final void setOnMyLocationClickListener(@Nullable OnMyLocationClickListener onMyLocationClickListener) {
        if (onMyLocationClickListener == null) {
            try {
                this.zza.setOnMyLocationClickListener((zzbd) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnMyLocationClickListener(new zzi(this, onMyLocationClickListener));
        }
    }

    public final void setOnPoiClickListener(@Nullable OnPoiClickListener onPoiClickListener) {
        if (onPoiClickListener == null) {
            try {
                this.zza.setOnPoiClickListener((zzbf) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnPoiClickListener(new zzr(this, onPoiClickListener));
        }
    }

    public final void setOnPolygonClickListener(@Nullable OnPolygonClickListener onPolygonClickListener) {
        if (onPolygonClickListener == null) {
            try {
                this.zza.setOnPolygonClickListener((zzbh) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnPolygonClickListener(new zzo(this, onPolygonClickListener));
        }
    }

    public final void setOnPolylineClickListener(@Nullable OnPolylineClickListener onPolylineClickListener) {
        if (onPolylineClickListener == null) {
            try {
                this.zza.setOnPolylineClickListener((zzbj) null);
            } catch (RemoteException e3) {
                throw new RuntimeRemoteException(e3);
            }
        } else {
            this.zza.setOnPolylineClickListener(new zzp(this, onPolylineClickListener));
        }
    }

    public final void setPadding(int i3, int i4, int i5, int i6) {
        try {
            this.zza.setPadding(i3, i4, i5, i6);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void setTrafficEnabled(boolean z2) {
        try {
            this.zza.setTrafficEnabled(z2);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback) {
        Preconditions.checkNotNull(snapshotReadyCallback, "Callback must not be null.");
        snapshot(snapshotReadyCallback, (Bitmap) null);
    }

    public final void stopAnimation() {
        try {
            this.zza.stopAnimation();
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void snapshot(@NonNull SnapshotReadyCallback snapshotReadyCallback, @Nullable Bitmap bitmap) {
        Preconditions.checkNotNull(snapshotReadyCallback, "Callback must not be null.");
        try {
            this.zza.snapshot(new zzq(this, snapshotReadyCallback), (ObjectWrapper) (bitmap != null ? ObjectWrapper.wrap(bitmap) : null));
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate, int i3, @Nullable CancelableCallback cancelableCallback) {
        zzab zzab;
        try {
            Preconditions.checkNotNull(cameraUpdate, "CameraUpdate must not be null.");
            IGoogleMapDelegate iGoogleMapDelegate = this.zza;
            IObjectWrapper zza2 = cameraUpdate.zza();
            if (cancelableCallback == null) {
                zzab = null;
            } else {
                zzab = new zzab(cancelableCallback);
            }
            iGoogleMapDelegate.animateCameraWithDurationAndCallback(zza2, i3, zzab);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }

    public final void animateCamera(@NonNull CameraUpdate cameraUpdate, @Nullable CancelableCallback cancelableCallback) {
        zzab zzab;
        try {
            Preconditions.checkNotNull(cameraUpdate, "CameraUpdate must not be null.");
            IGoogleMapDelegate iGoogleMapDelegate = this.zza;
            IObjectWrapper zza2 = cameraUpdate.zza();
            if (cancelableCallback == null) {
                zzab = null;
            } else {
                zzab = new zzab(cancelableCallback);
            }
            iGoogleMapDelegate.animateCameraWithCallback(zza2, zzab);
        } catch (RemoteException e3) {
            throw new RuntimeRemoteException(e3);
        }
    }
}
