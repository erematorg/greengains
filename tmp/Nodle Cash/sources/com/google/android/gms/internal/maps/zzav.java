package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public final class zzav extends zza implements zzax {
    public zzav(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public final Tile zzb(int i3, int i4, int i5) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i3);
        zza.writeInt(i4);
        zza.writeInt(i5);
        Parcel zzJ = zzJ(1, zza);
        Tile tile = (Tile) zzc.zza(zzJ, Tile.CREATOR);
        zzJ.recycle();
        return tile;
    }
}
