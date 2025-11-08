package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;
import javax.annotation.Nullable;

public interface zzax extends IInterface {
    @Nullable
    Tile zzb(int i3, int i4, int i5) throws RemoteException;
}
