package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzbv;

final class zzq extends zzbv {
    final /* synthetic */ GoogleMap.SnapshotReadyCallback zza;

    public zzq(GoogleMap googleMap, GoogleMap.SnapshotReadyCallback snapshotReadyCallback) {
        this.zza = snapshotReadyCallback;
    }

    public final void zzb(Bitmap bitmap) throws RemoteException {
        this.zza.onSnapshotReady(bitmap);
    }

    public final void zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zza.onSnapshotReady((Bitmap) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
