package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzax;

final class zzai implements TileProvider {
    final /* synthetic */ TileOverlayOptions zza;
    private final zzax zzb;

    public zzai(TileOverlayOptions tileOverlayOptions) {
        this.zza = tileOverlayOptions;
        this.zzb = tileOverlayOptions.zza;
    }

    @Nullable
    public final Tile getTile(int i3, int i4, int i5) {
        try {
            return this.zzb.zzb(i3, i4, i5);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
