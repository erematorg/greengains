package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzb;
import com.google.android.gms.internal.auth.zzc;

public abstract class zza extends zzb implements zzb {
    public zza() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    public final boolean zza(int i3, Parcel parcel, Parcel parcel2, int i4) throws RemoteException {
        if (i3 != 1) {
            boolean z2 = false;
            if (i3 != 2) {
                return false;
            }
            int i5 = zzc.zza;
            int readInt = parcel.readInt();
            zzc.zzb(parcel);
            if (readInt != 0) {
                z2 = true;
            }
            zzc(z2);
        } else {
            zzc.zzb(parcel);
            zzb((Account) zzc.zza(parcel, Account.CREATOR));
        }
        return true;
    }
}
