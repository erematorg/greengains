package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.annotation.RestrictTo;
import org.apache.commons.lang3.ClassUtils;

@RestrictTo({RestrictTo.Scope.LIBRARY})
public interface IEngagementSignalsCallback extends IInterface {
    public static final String DESCRIPTOR = "android$support$customtabs$IEngagementSignalsCallback".replace('$', ClassUtils.PACKAGE_SEPARATOR_CHAR);

    public static class Default implements IEngagementSignalsCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onGreatestScrollPercentageIncreased(int i3, Bundle bundle) throws RemoteException {
        }

        public void onSessionEnded(boolean z2, Bundle bundle) throws RemoteException {
        }

        public void onVerticalScrollEvent(boolean z2, Bundle bundle) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IEngagementSignalsCallback {
        static final int TRANSACTION_onGreatestScrollPercentageIncreased = 3;
        static final int TRANSACTION_onSessionEnded = 4;
        static final int TRANSACTION_onVerticalScrollEvent = 2;

        public static class Proxy implements IEngagementSignalsCallback {
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IEngagementSignalsCallback.DESCRIPTOR;
            }

            public void onGreatestScrollPercentageIncreased(int i3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEngagementSignalsCallback.DESCRIPTOR);
                    obtain.writeInt(i3);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSessionEnded(boolean z2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEngagementSignalsCallback.DESCRIPTOR);
                    obtain.writeInt(z2 ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onVerticalScrollEvent(boolean z2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IEngagementSignalsCallback.DESCRIPTOR);
                    obtain.writeInt(z2 ? 1 : 0);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, IEngagementSignalsCallback.DESCRIPTOR);
        }

        public static IEngagementSignalsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IEngagementSignalsCallback.DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IEngagementSignalsCallback)) ? new Proxy(iBinder) : (IEngagementSignalsCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
            String str = IEngagementSignalsCallback.DESCRIPTOR;
            if (i3 >= 1 && i3 <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i3 == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            boolean z2 = false;
            if (i3 == 2) {
                if (parcel.readInt() != 0) {
                    z2 = true;
                }
                onVerticalScrollEvent(z2, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            } else if (i3 == 3) {
                onGreatestScrollPercentageIncreased(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            } else if (i3 != 4) {
                return super.onTransact(i3, parcel, parcel2, i4);
            } else {
                if (parcel.readInt() != 0) {
                    z2 = true;
                }
                onSessionEnded(z2, (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
            }
            return true;
        }
    }

    public static class _Parcel {
        /* access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t2, int i3) {
            if (t2 != null) {
                parcel.writeInt(1);
                t2.writeToParcel(parcel, i3);
                return;
            }
            parcel.writeInt(0);
        }
    }

    void onGreatestScrollPercentageIncreased(int i3, Bundle bundle) throws RemoteException;

    void onSessionEnded(boolean z2, Bundle bundle) throws RemoteException;

    void onVerticalScrollEvent(boolean z2, Bundle bundle) throws RemoteException;
}
