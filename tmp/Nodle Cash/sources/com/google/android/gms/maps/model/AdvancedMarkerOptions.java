package com.google.android.gms.maps.model;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AdvancedMarkerOptions extends MarkerOptions {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface CollisionBehavior {
        public static final int OPTIONAL_AND_HIDES_LOWER_PRIORITY = 2;
        public static final int REQUIRED = 0;
        public static final int REQUIRED_AND_HIDES_OPTIONAL = 1;
    }

    @NonNull
    public AdvancedMarkerOptions alpha(float f2) {
        super.alpha(f2);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions anchor(float f2, float f3) {
        super.anchor(f2, f3);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions collisionBehavior(@CollisionBehavior int i3) {
        zzd(i3);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions contentDescription(@Nullable String str) {
        super.contentDescription(str);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions draggable(boolean z2) {
        super.draggable(z2);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions flat(boolean z2) {
        super.flat(z2);
        return this;
    }

    public int getCollisionBehavior() {
        return zza();
    }

    @Nullable
    public View getIconView() {
        return zzc();
    }

    @NonNull
    public AdvancedMarkerOptions icon(@Nullable BitmapDescriptor bitmapDescriptor) {
        super.icon(bitmapDescriptor);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions iconView(@Nullable View view) {
        zze(view);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions infoWindowAnchor(float f2, float f3) {
        super.infoWindowAnchor(f2, f3);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions position(@NonNull LatLng latLng) {
        super.position(latLng);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions rotation(float f2) {
        super.rotation(f2);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions snippet(@Nullable String str) {
        super.snippet(str);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions title(@Nullable String str) {
        super.title(str);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions visible(boolean z2) {
        super.visible(z2);
        return this;
    }

    @NonNull
    public AdvancedMarkerOptions zIndex(float f2) {
        super.zIndex(f2);
        return this;
    }
}
