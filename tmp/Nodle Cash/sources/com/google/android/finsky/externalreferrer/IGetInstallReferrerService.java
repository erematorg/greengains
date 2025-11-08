package com.google.android.finsky.externalreferrer;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.a.a;
import com.google.android.a.b;
import com.google.android.a.c;

public interface IGetInstallReferrerService extends IInterface {

    public static abstract class Stub extends b implements IGetInstallReferrerService {

        public static class Proxy extends a implements IGetInstallReferrerService {
            public Proxy(IBinder iBinder) {
                super(iBinder);
            }

            public final Bundle c(Bundle bundle) throws RemoteException {
                Parcel a2 = a();
                c.b(a2, bundle);
                Parcel b3 = b(a2);
                Bundle bundle2 = (Bundle) c.a(b3, Bundle.CREATOR);
                b3.recycle();
                return bundle2;
            }
        }

        public static IGetInstallReferrerService b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
            return queryLocalInterface instanceof IGetInstallReferrerService ? (IGetInstallReferrerService) queryLocalInterface : new Proxy(iBinder);
        }

        public final boolean a(int i3, Parcel parcel, Parcel parcel2) throws RemoteException {
            if (i3 != 1) {
                return false;
            }
            Bundle c3 = c((Bundle) c.a(parcel, Bundle.CREATOR));
            parcel2.writeNoException();
            c.c(parcel2, c3);
            return true;
        }
    }

    Bundle c(Bundle bundle) throws RemoteException;
}
