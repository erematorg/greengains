package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class o extends b implements p {
    public o() {
        super("com.google.android.play.core.integrity.protocol.IIntegrityServiceCallback");
    }

    public final boolean a(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 != 2) {
            return false;
        }
        c.b(parcel);
        b((Bundle) c.a(parcel, Bundle.CREATOR));
        return true;
    }
}
