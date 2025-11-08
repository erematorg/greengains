package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapColorScheme;
import jnr.ffi.provider.jffi.JNINativeInterface;

@SafeParcelable.Class(creator = "GoogleMapOptionsCreator")
@SafeParcelable.Reserved({1})
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzac();
    private static final Integer zza = Integer.valueOf(Color.argb(255, 236, 233, JNINativeInterface.ReleaseStringCritical));
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", id = 2, type = "byte")
    @Nullable
    private Boolean zzb;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", id = 3, type = "byte")
    @Nullable
    private Boolean zzc;
    @SafeParcelable.Field(getter = "getMapType", id = 4)
    private int zzd = -1;
    @SafeParcelable.Field(getter = "getCamera", id = 5)
    @Nullable
    private CameraPosition zze;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", id = 6, type = "byte")
    @Nullable
    private Boolean zzf;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", id = 7, type = "byte")
    @Nullable
    private Boolean zzg;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", id = 8, type = "byte")
    @Nullable
    private Boolean zzh;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", id = 9, type = "byte")
    @Nullable
    private Boolean zzi;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", id = 10, type = "byte")
    @Nullable
    private Boolean zzj;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", id = 11, type = "byte")
    @Nullable
    private Boolean zzk;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLiteModeForParcel", id = 12, type = "byte")
    @Nullable
    private Boolean zzl;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", id = 14, type = "byte")
    @Nullable
    private Boolean zzm;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", id = 15, type = "byte")
    @Nullable
    private Boolean zzn;
    @SafeParcelable.Field(getter = "getMinZoomPreference", id = 16)
    @Nullable
    private Float zzo = null;
    @SafeParcelable.Field(getter = "getMaxZoomPreference", id = 17)
    @Nullable
    private Float zzp = null;
    @SafeParcelable.Field(getter = "getLatLngBoundsForCameraTarget", id = 18)
    @Nullable
    private LatLngBounds zzq = null;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledDuringRotateOrZoomForParcel", id = 19, type = "byte")
    @Nullable
    private Boolean zzr;
    @SafeParcelable.Field(getter = "getBackgroundColor", id = 20)
    @ColorInt
    @Nullable
    private Integer zzs = null;
    @SafeParcelable.Field(getter = "getMapId", id = 21)
    @Nullable
    private String zzt = null;
    @SafeParcelable.Field(defaultValue = "0", getter = "getMapColorScheme", id = 23, type = "int")
    @MapColorScheme
    private int zzu;

    public GoogleMapOptions() {
    }

    @Nullable
    public static GoogleMapOptions createFromAttributes(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        String string;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        int i3 = R.styleable.MapAttrs_mapType;
        if (obtainAttributes.hasValue(i3)) {
            googleMapOptions.mapType(obtainAttributes.getInt(i3, -1));
        }
        int i4 = R.styleable.MapAttrs_zOrderOnTop;
        if (obtainAttributes.hasValue(i4)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(i4, false));
        }
        int i5 = R.styleable.MapAttrs_useViewLifecycle;
        if (obtainAttributes.hasValue(i5)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(i5, false));
        }
        int i6 = R.styleable.MapAttrs_uiCompass;
        if (obtainAttributes.hasValue(i6)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(i6, true));
        }
        int i7 = R.styleable.MapAttrs_uiRotateGestures;
        if (obtainAttributes.hasValue(i7)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(i7, true));
        }
        int i8 = R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom;
        if (obtainAttributes.hasValue(i8)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(i8, true));
        }
        int i9 = R.styleable.MapAttrs_uiScrollGestures;
        if (obtainAttributes.hasValue(i9)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(i9, true));
        }
        int i10 = R.styleable.MapAttrs_uiTiltGestures;
        if (obtainAttributes.hasValue(i10)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(i10, true));
        }
        int i11 = R.styleable.MapAttrs_uiZoomGestures;
        if (obtainAttributes.hasValue(i11)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(i11, true));
        }
        int i12 = R.styleable.MapAttrs_uiZoomControls;
        if (obtainAttributes.hasValue(i12)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(i12, true));
        }
        int i13 = R.styleable.MapAttrs_liteMode;
        if (obtainAttributes.hasValue(i13)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(i13, false));
        }
        int i14 = R.styleable.MapAttrs_uiMapToolbar;
        if (obtainAttributes.hasValue(i14)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(i14, true));
        }
        int i15 = R.styleable.MapAttrs_ambientEnabled;
        if (obtainAttributes.hasValue(i15)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(i15, false));
        }
        int i16 = R.styleable.MapAttrs_cameraMinZoomPreference;
        if (obtainAttributes.hasValue(i16)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(i16, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(i16)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        int i17 = R.styleable.MapAttrs_backgroundColor;
        if (obtainAttributes.hasValue(i17)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes.getColor(i17, zza.intValue())));
        }
        int i18 = R.styleable.MapAttrs_mapId;
        if (obtainAttributes.hasValue(i18) && (string = obtainAttributes.getString(i18)) != null && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        int i19 = R.styleable.MapAttrs_mapColorScheme;
        if (obtainAttributes.hasValue(i19)) {
            googleMapOptions.mapColorScheme(obtainAttributes.getInt(i19, 0));
        }
        googleMapOptions.latLngBoundsForCameraTarget(zzb(context, attributeSet));
        googleMapOptions.camera(zza(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    @Nullable
    public static CameraPosition zza(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i3 = R.styleable.MapAttrs_cameraTargetLat;
        float f2 = obtainAttributes.hasValue(i3) ? obtainAttributes.getFloat(i3, 0.0f) : 0.0f;
        int i4 = R.styleable.MapAttrs_cameraTargetLng;
        LatLng latLng = new LatLng((double) f2, (double) (obtainAttributes.hasValue(i4) ? obtainAttributes.getFloat(i4, 0.0f) : 0.0f));
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        int i5 = R.styleable.MapAttrs_cameraZoom;
        if (obtainAttributes.hasValue(i5)) {
            builder.zoom(obtainAttributes.getFloat(i5, 0.0f));
        }
        int i6 = R.styleable.MapAttrs_cameraBearing;
        if (obtainAttributes.hasValue(i6)) {
            builder.bearing(obtainAttributes.getFloat(i6, 0.0f));
        }
        int i7 = R.styleable.MapAttrs_cameraTilt;
        if (obtainAttributes.hasValue(i7)) {
            builder.tilt(obtainAttributes.getFloat(i7, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    @Nullable
    public static LatLngBounds zzb(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i3 = R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
        Float valueOf = obtainAttributes.hasValue(i3) ? Float.valueOf(obtainAttributes.getFloat(i3, 0.0f)) : null;
        int i4 = R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
        Float valueOf2 = obtainAttributes.hasValue(i4) ? Float.valueOf(obtainAttributes.getFloat(i4, 0.0f)) : null;
        int i5 = R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
        Float valueOf3 = obtainAttributes.hasValue(i5) ? Float.valueOf(obtainAttributes.getFloat(i5, 0.0f)) : null;
        int i6 = R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
        Float valueOf4 = obtainAttributes.hasValue(i6) ? Float.valueOf(obtainAttributes.getFloat(i6, 0.0f)) : null;
        obtainAttributes.recycle();
        if (valueOf == null || valueOf2 == null || valueOf3 == null || valueOf4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng((double) valueOf.floatValue(), (double) valueOf2.floatValue()), new LatLng((double) valueOf3.floatValue(), (double) valueOf4.floatValue()));
    }

    @NonNull
    public GoogleMapOptions ambientEnabled(boolean z2) {
        this.zzn = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions backgroundColor(@ColorInt @Nullable Integer num) {
        this.zzs = num;
        return this;
    }

    @NonNull
    public GoogleMapOptions camera(@Nullable CameraPosition cameraPosition) {
        this.zze = cameraPosition;
        return this;
    }

    @NonNull
    public GoogleMapOptions compassEnabled(boolean z2) {
        this.zzg = Boolean.valueOf(z2);
        return this;
    }

    @Nullable
    public Boolean getAmbientEnabled() {
        return this.zzn;
    }

    @ColorInt
    @Nullable
    public Integer getBackgroundColor() {
        return this.zzs;
    }

    @Nullable
    public CameraPosition getCamera() {
        return this.zze;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        return this.zzg;
    }

    @Nullable
    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzq;
    }

    @Nullable
    public Boolean getLiteMode() {
        return this.zzl;
    }

    @MapColorScheme
    public int getMapColorScheme() {
        return this.zzu;
    }

    @Nullable
    public String getMapId() {
        return this.zzt;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        return this.zzm;
    }

    public int getMapType() {
        return this.zzd;
    }

    @Nullable
    public Float getMaxZoomPreference() {
        return this.zzp;
    }

    @Nullable
    public Float getMinZoomPreference() {
        return this.zzo;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return this.zzk;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return this.zzh;
    }

    @Nullable
    public Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzr;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return this.zzj;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return this.zzc;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return this.zzb;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        return this.zzf;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return this.zzi;
    }

    @NonNull
    public GoogleMapOptions latLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) {
        this.zzq = latLngBounds;
        return this;
    }

    @NonNull
    public GoogleMapOptions liteMode(boolean z2) {
        this.zzl = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapColorScheme(@MapColorScheme int i3) {
        this.zzu = i3;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapId(@NonNull String str) {
        this.zzt = str;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapToolbarEnabled(boolean z2) {
        this.zzm = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapType(int i3) {
        this.zzd = i3;
        return this;
    }

    @NonNull
    public GoogleMapOptions maxZoomPreference(float f2) {
        this.zzp = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions minZoomPreference(float f2) {
        this.zzo = Float.valueOf(f2);
        return this;
    }

    @NonNull
    public GoogleMapOptions rotateGesturesEnabled(boolean z2) {
        this.zzk = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabled(boolean z2) {
        this.zzh = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean z2) {
        this.zzr = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions tiltGesturesEnabled(boolean z2) {
        this.zzj = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.zzd)).add("LiteMode", this.zzl).add("Camera", this.zze).add("CompassEnabled", this.zzg).add("ZoomControlsEnabled", this.zzf).add("ScrollGesturesEnabled", this.zzh).add("ZoomGesturesEnabled", this.zzi).add("TiltGesturesEnabled", this.zzj).add("RotateGesturesEnabled", this.zzk).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzr).add("MapToolbarEnabled", this.zzm).add("AmbientEnabled", this.zzn).add("MinZoomPreference", this.zzo).add("MaxZoomPreference", this.zzp).add("BackgroundColor", this.zzs).add("LatLngBoundsForCameraTarget", this.zzq).add("ZOrderOnTop", this.zzb).add("UseViewLifecycleInFragment", this.zzc).add("mapColorScheme", Integer.valueOf(this.zzu)).toString();
    }

    @NonNull
    public GoogleMapOptions useViewLifecycleInFragment(boolean z2) {
        this.zzc = Boolean.valueOf(z2);
        return this;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByte(parcel, 2, zza.zza(this.zzb));
        SafeParcelWriter.writeByte(parcel, 3, zza.zza(this.zzc));
        SafeParcelWriter.writeInt(parcel, 4, getMapType());
        SafeParcelWriter.writeParcelable(parcel, 5, getCamera(), i3, false);
        SafeParcelWriter.writeByte(parcel, 6, zza.zza(this.zzf));
        SafeParcelWriter.writeByte(parcel, 7, zza.zza(this.zzg));
        SafeParcelWriter.writeByte(parcel, 8, zza.zza(this.zzh));
        SafeParcelWriter.writeByte(parcel, 9, zza.zza(this.zzi));
        SafeParcelWriter.writeByte(parcel, 10, zza.zza(this.zzj));
        SafeParcelWriter.writeByte(parcel, 11, zza.zza(this.zzk));
        SafeParcelWriter.writeByte(parcel, 12, zza.zza(this.zzl));
        SafeParcelWriter.writeByte(parcel, 14, zza.zza(this.zzm));
        SafeParcelWriter.writeByte(parcel, 15, zza.zza(this.zzn));
        SafeParcelWriter.writeFloatObject(parcel, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(parcel, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(parcel, 18, getLatLngBoundsForCameraTarget(), i3, false);
        SafeParcelWriter.writeByte(parcel, 19, zza.zza(this.zzr));
        SafeParcelWriter.writeIntegerObject(parcel, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(parcel, 21, getMapId(), false);
        SafeParcelWriter.writeInt(parcel, 23, getMapColorScheme());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public GoogleMapOptions zOrderOnTop(boolean z2) {
        this.zzb = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomControlsEnabled(boolean z2) {
        this.zzf = Boolean.valueOf(z2);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomGesturesEnabled(boolean z2) {
        this.zzi = Boolean.valueOf(z2);
        return this;
    }

    @SafeParcelable.Constructor
    public GoogleMapOptions(@SafeParcelable.Param(id = 2) byte b3, @SafeParcelable.Param(id = 3) byte b4, @SafeParcelable.Param(id = 4) int i3, @SafeParcelable.Param(id = 5) @Nullable CameraPosition cameraPosition, @SafeParcelable.Param(id = 6) byte b5, @SafeParcelable.Param(id = 7) byte b6, @SafeParcelable.Param(id = 8) byte b7, @SafeParcelable.Param(id = 9) byte b8, @SafeParcelable.Param(id = 10) byte b9, @SafeParcelable.Param(id = 11) byte b10, @SafeParcelable.Param(id = 12) byte b11, @SafeParcelable.Param(id = 14) byte b12, @SafeParcelable.Param(id = 15) byte b13, @SafeParcelable.Param(id = 16) @Nullable Float f2, @SafeParcelable.Param(id = 17) @Nullable Float f3, @SafeParcelable.Param(id = 18) @Nullable LatLngBounds latLngBounds, @SafeParcelable.Param(id = 19) byte b14, @SafeParcelable.Param(id = 20) @ColorInt @Nullable Integer num, @SafeParcelable.Param(id = 21) @Nullable String str, @SafeParcelable.Param(id = 23) @MapColorScheme int i4) {
        this.zzb = zza.zzb(b3);
        this.zzc = zza.zzb(b4);
        this.zzd = i3;
        this.zze = cameraPosition;
        this.zzf = zza.zzb(b5);
        this.zzg = zza.zzb(b6);
        this.zzh = zza.zzb(b7);
        this.zzi = zza.zzb(b8);
        this.zzj = zza.zzb(b9);
        this.zzk = zza.zzb(b10);
        this.zzl = zza.zzb(b11);
        this.zzm = zza.zzb(b12);
        this.zzn = zza.zzb(b13);
        this.zzo = f2;
        this.zzp = f3;
        this.zzq = latLngBounds;
        this.zzr = zza.zzb(b14);
        this.zzs = num;
        this.zzt = str;
        this.zzu = i4;
    }
}
