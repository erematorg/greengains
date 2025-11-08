package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzae;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.internal.maps.zzal;
import com.google.android.gms.internal.maps.zzam;
import com.google.android.gms.internal.maps.zzao;
import com.google.android.gms.internal.maps.zzap;
import com.google.android.gms.internal.maps.zzat;
import com.google.android.gms.internal.maps.zzau;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzr;
import com.google.android.gms.internal.maps.zzs;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.internal.maps.zzv;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzy;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.FeatureLayerOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final zzl addCircle(CircleOptions circleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, circleOptions);
        Parcel zzJ = zzJ(35, zza);
        zzl zzb = zzk.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final zzv addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, groundOverlayOptions);
        Parcel zzJ = zzJ(12, zza);
        zzv zzb = zzu.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final zzah addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, markerOptions);
        Parcel zzJ = zzJ(11, zza);
        zzah zzb = zzag.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final void addOnMapCapabilitiesChangedListener(zzal zzal) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzal);
        zzc(110, zza);
    }

    public final zzam addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, polygonOptions);
        Parcel zzJ = zzJ(10, zza);
        zzam zzb = zzal.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final zzap addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, polylineOptions);
        Parcel zzJ = zzJ(9, zza);
        zzap zzb = zzao.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final zzau addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, tileOverlayOptions);
        Parcel zzJ = zzJ(13, zza);
        zzau zzb = zzat.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(5, zza);
    }

    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zzg(zza, zzd);
        zzc(6, zza);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i3, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(i3);
        zzc.zzg(zza, zzd);
        zzc(7, zza);
    }

    public final void clear() throws RemoteException {
        zzc(14, zza());
    }

    public final CameraPosition getCameraPosition() throws RemoteException {
        Parcel zzJ = zzJ(1, zza());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(zzJ, CameraPosition.CREATOR);
        zzJ.recycle();
        return cameraPosition;
    }

    public final zzs getFeatureLayer(FeatureLayerOptions featureLayerOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, featureLayerOptions);
        Parcel zzJ = zzJ(112, zza);
        zzs zzb = zzr.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final zzy getFocusedBuilding() throws RemoteException {
        Parcel zzJ = zzJ(44, zza());
        zzy zzb = zzx.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final void getMapAsync(zzat zzat) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzat);
        zzc(53, zza);
    }

    public final zzae getMapCapabilities() throws RemoteException {
        Parcel zzJ = zzJ(109, zza());
        zzae zzb = zzad.zzb(zzJ.readStrongBinder());
        zzJ.recycle();
        return zzb;
    }

    public final int getMapColorScheme() throws RemoteException {
        Parcel zzJ = zzJ(114, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final int getMapType() throws RemoteException {
        Parcel zzJ = zzJ(15, zza());
        int readInt = zzJ.readInt();
        zzJ.recycle();
        return readInt;
    }

    public final float getMaxZoomLevel() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final float getMinZoomLevel() throws RemoteException {
        Parcel zzJ = zzJ(3, zza());
        float readFloat = zzJ.readFloat();
        zzJ.recycle();
        return readFloat;
    }

    public final Location getMyLocation() throws RemoteException {
        Parcel zzJ = zzJ(23, zza());
        Location location = (Location) zzc.zza(zzJ, Location.CREATOR);
        zzJ.recycle();
        return location;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IProjectionDelegate getProjection() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 26
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzJ(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0012
            r0 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IProjectionDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IProjectionDelegate
            if (r2 == 0) goto L_0x0020
            r0 = r1
            com.google.android.gms.maps.internal.IProjectionDelegate r0 = (com.google.android.gms.maps.internal.IProjectionDelegate) r0
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbu r1 = new com.google.android.gms.maps.internal.zzbu
            r1.<init>(r0)
            r0 = r1
        L_0x0026:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getProjection():com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 25
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzJ(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0012
            r0 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IUiSettingsDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IUiSettingsDelegate
            if (r2 == 0) goto L_0x0020
            r0 = r1
            com.google.android.gms.maps.internal.IUiSettingsDelegate r0 = (com.google.android.gms.maps.internal.IUiSettingsDelegate) r0
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzca r1 = new com.google.android.gms.maps.internal.zzca
            r1.<init>(r0)
            r0 = r1
        L_0x0026:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isBuildingsEnabled() throws RemoteException {
        Parcel zzJ = zzJ(40, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isIndoorEnabled() throws RemoteException {
        Parcel zzJ = zzJ(19, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isMyLocationEnabled() throws RemoteException {
        Parcel zzJ = zzJ(21, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final boolean isTrafficEnabled() throws RemoteException {
        Parcel zzJ = zzJ(17, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(4, zza);
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(54, zza);
    }

    public final void onDestroy() throws RemoteException {
        zzc(57, zza());
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(81, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzc(82, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzc(58, zza());
    }

    public final void onPause() throws RemoteException {
        zzc(56, zza());
    }

    public final void onResume() throws RemoteException {
        zzc(55, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzJ = zzJ(60, zza);
        if (zzJ.readInt() != 0) {
            bundle.readFromParcel(zzJ);
        }
        zzJ.recycle();
    }

    public final void onStart() throws RemoteException {
        zzc(101, zza());
    }

    public final void onStop() throws RemoteException {
        zzc(102, zza());
    }

    public final void removeOnMapCapabilitiesChangedListener(zzal zzal) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzal);
        zzc(111, zza);
    }

    public final void resetMinMaxZoomPreference() throws RemoteException {
        zzc(94, zza());
    }

    public final void setBuildingsEnabled(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(41, zza);
    }

    public final void setContentDescription(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(61, zza);
    }

    public final boolean setIndoorEnabled(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        Parcel zzJ = zzJ(20, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final void setInfoWindowAdapter(zzi zzi) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzi);
        zzc(33, zza);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLngBounds);
        zzc(95, zza);
    }

    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iLocationSourceDelegate);
        zzc(24, zza);
    }

    public final void setMapColorScheme(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(113, zza);
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, mapStyleOptions);
        Parcel zzJ = zzJ(91, zza);
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }

    public final void setMapType(int i3) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zzc(16, zza);
    }

    public final void setMaxZoomPreference(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(93, zza);
    }

    public final void setMinZoomPreference(float f2) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f2);
        zzc(92, zza);
    }

    public final void setMyLocationEnabled(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(22, zza);
    }

    public final void setOnCameraChangeListener(zzn zzn) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzn);
        zzc(27, zza);
    }

    public final void setOnCameraIdleListener(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzp);
        zzc(99, zza);
    }

    public final void setOnCameraMoveCanceledListener(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzr);
        zzc(98, zza);
    }

    public final void setOnCameraMoveListener(zzt zzt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzt);
        zzc(97, zza);
    }

    public final void setOnCameraMoveStartedListener(zzv zzv) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzv);
        zzc(96, zza);
    }

    public final void setOnCircleClickListener(zzx zzx) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzx);
        zzc(89, zza);
    }

    public final void setOnGroundOverlayClickListener(zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzz);
        zzc(83, zza);
    }

    public final void setOnIndoorStateChangeListener(zzab zzab) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzab);
        zzc(45, zza);
    }

    public final void setOnInfoWindowClickListener(zzad zzad) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzad);
        zzc(32, zza);
    }

    public final void setOnInfoWindowCloseListener(zzaf zzaf) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaf);
        zzc(86, zza);
    }

    public final void setOnInfoWindowLongClickListener(zzah zzah) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzah);
        zzc(84, zza);
    }

    public final void setOnMapClickListener(zzan zzan) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzan);
        zzc(28, zza);
    }

    public final void setOnMapLoadedCallback(zzap zzap) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzap);
        zzc(42, zza);
    }

    public final void setOnMapLongClickListener(zzar zzar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzar);
        zzc(29, zza);
    }

    public final void setOnMarkerClickListener(zzav zzav) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzav);
        zzc(30, zza);
    }

    public final void setOnMarkerDragListener(zzax zzax) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzax);
        zzc(31, zza);
    }

    public final void setOnMyLocationButtonClickListener(zzaz zzaz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaz);
        zzc(37, zza);
    }

    public final void setOnMyLocationChangeListener(zzbb zzbb) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbb);
        zzc(36, zza);
    }

    public final void setOnMyLocationClickListener(zzbd zzbd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbd);
        zzc(107, zza);
    }

    public final void setOnPoiClickListener(zzbf zzbf) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbf);
        zzc(80, zza);
    }

    public final void setOnPolygonClickListener(zzbh zzbh) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbh);
        zzc(85, zza);
    }

    public final void setOnPolylineClickListener(zzbj zzbj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbj);
        zzc(87, zza);
    }

    public final void setPadding(int i3, int i4, int i5, int i6) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zza.writeInt(i4);
        zza.writeInt(i5);
        zza.writeInt(i6);
        zzc(39, zza);
    }

    public final void setTrafficEnabled(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(18, zza);
    }

    public final void setWatermarkEnabled(boolean z2) throws RemoteException {
        Parcel zza = zza();
        int i3 = zzc.zza;
        zza.writeInt(z2 ? 1 : 0);
        zzc(51, zza);
    }

    public final void snapshot(zzbw zzbw, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbw);
        zzc.zzg(zza, iObjectWrapper);
        zzc(38, zza);
    }

    public final void snapshotForTest(zzbw zzbw) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbw);
        zzc(71, zza);
    }

    public final void stopAnimation() throws RemoteException {
        zzc(8, zza());
    }

    public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
        Parcel zzJ = zzJ(59, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}
