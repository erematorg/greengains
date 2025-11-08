package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzae;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzam;
import com.google.android.gms.internal.maps.zzap;
import com.google.android.gms.internal.maps.zzau;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzs;
import com.google.android.gms.internal.maps.zzv;
import com.google.android.gms.internal.maps.zzy;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.FeatureLayerOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapColorScheme;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import javax.annotation.Nullable;

public interface IGoogleMapDelegate extends IInterface {
    zzl addCircle(CircleOptions circleOptions) throws RemoteException;

    zzv addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    zzah addMarker(MarkerOptions markerOptions) throws RemoteException;

    void addOnMapCapabilitiesChangedListener(zzal zzal) throws RemoteException;

    zzam addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    zzap addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    zzau addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(@NonNull IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, @Nullable zzd zzd) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i3, @Nullable zzd zzd) throws RemoteException;

    void clear() throws RemoteException;

    @NonNull
    CameraPosition getCameraPosition() throws RemoteException;

    zzs getFeatureLayer(FeatureLayerOptions featureLayerOptions) throws RemoteException;

    zzy getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzat zzat) throws RemoteException;

    zzae getMapCapabilities() throws RemoteException;

    @MapColorScheme
    int getMapColorScheme() throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    @NonNull
    Location getMyLocation() throws RemoteException;

    @NonNull
    IProjectionDelegate getProjection() throws RemoteException;

    @NonNull
    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(@NonNull IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(@NonNull Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(@NonNull Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(@NonNull Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void removeOnMapCapabilitiesChangedListener(zzal zzal) throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z2) throws RemoteException;

    void setContentDescription(@Nullable String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z2) throws RemoteException;

    void setInfoWindowAdapter(@Nullable zzi zzi) throws RemoteException;

    void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(@Nullable ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    void setMapColorScheme(@MapColorScheme int i3) throws RemoteException;

    boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i3) throws RemoteException;

    void setMaxZoomPreference(float f2) throws RemoteException;

    void setMinZoomPreference(float f2) throws RemoteException;

    void setMyLocationEnabled(boolean z2) throws RemoteException;

    void setOnCameraChangeListener(@Nullable zzn zzn) throws RemoteException;

    void setOnCameraIdleListener(@Nullable zzp zzp) throws RemoteException;

    void setOnCameraMoveCanceledListener(@Nullable zzr zzr) throws RemoteException;

    void setOnCameraMoveListener(@Nullable zzt zzt) throws RemoteException;

    void setOnCameraMoveStartedListener(@Nullable zzv zzv) throws RemoteException;

    void setOnCircleClickListener(@Nullable zzx zzx) throws RemoteException;

    void setOnGroundOverlayClickListener(@Nullable zzz zzz) throws RemoteException;

    void setOnIndoorStateChangeListener(@Nullable zzab zzab) throws RemoteException;

    void setOnInfoWindowClickListener(@Nullable zzad zzad) throws RemoteException;

    void setOnInfoWindowCloseListener(@Nullable zzaf zzaf) throws RemoteException;

    void setOnInfoWindowLongClickListener(@Nullable zzah zzah) throws RemoteException;

    void setOnMapClickListener(@Nullable zzan zzan) throws RemoteException;

    void setOnMapLoadedCallback(@Nullable zzap zzap) throws RemoteException;

    void setOnMapLongClickListener(@Nullable zzar zzar) throws RemoteException;

    void setOnMarkerClickListener(@Nullable zzav zzav) throws RemoteException;

    void setOnMarkerDragListener(@Nullable zzax zzax) throws RemoteException;

    void setOnMyLocationButtonClickListener(@Nullable zzaz zzaz) throws RemoteException;

    void setOnMyLocationChangeListener(@Nullable zzbb zzbb) throws RemoteException;

    void setOnMyLocationClickListener(@Nullable zzbd zzbd) throws RemoteException;

    void setOnPoiClickListener(@Nullable zzbf zzbf) throws RemoteException;

    void setOnPolygonClickListener(@Nullable zzbh zzbh) throws RemoteException;

    void setOnPolylineClickListener(@Nullable zzbj zzbj) throws RemoteException;

    void setPadding(int i3, int i4, int i5, int i6) throws RemoteException;

    void setTrafficEnabled(boolean z2) throws RemoteException;

    void setWatermarkEnabled(boolean z2) throws RemoteException;

    void snapshot(zzbw zzbw, @Nullable IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzbw zzbw) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
