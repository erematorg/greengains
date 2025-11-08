package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaw;

final class zzaj extends zzaw {
    final /* synthetic */ TileProvider zza;

    public zzaj(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zza = tileProvider;
    }

    @Nullable
    public final Tile zzb(int i3, int i4, int i5) {
        return this.zza.getTile(i3, i4, i5);
    }
}
