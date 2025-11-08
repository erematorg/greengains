package com.appsamurai.storyly.exoplayer2.common;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class BundleListRetriever extends Binder {
    private static final int REPLY_BREAK = 2;
    private static final int REPLY_CONTINUE = 1;
    private static final int REPLY_END_OF_LIST = 0;
    private static final int SUGGESTED_MAX_IPC_SIZE = (Util.SDK_INT >= 30 ? IBinder.getSuggestedMaxIpcSizeBytes() : 65536);
    private final ImmutableList<Bundle> list;

    public BundleListRetriever(List<Bundle> list2) {
        this.list = ImmutableList.copyOf(list2);
    }

    public static ImmutableList<Bundle> getList(IBinder iBinder) {
        int readInt;
        ImmutableList.Builder builder = ImmutableList.builder();
        int i3 = 0;
        int i4 = 1;
        while (i4 != 0) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInt(i3);
                iBinder.transact(1, obtain, obtain2, 0);
                while (true) {
                    readInt = obtain2.readInt();
                    if (readInt != 1) {
                        break;
                    }
                    builder.add((Object) (Bundle) Assertions.checkNotNull(obtain2.readBundle()));
                    i3++;
                }
                obtain2.recycle();
                obtain.recycle();
                i4 = readInt;
            } catch (RemoteException e3) {
                throw new RuntimeException(e3);
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }
        return builder.build();
    }

    public boolean onTransact(int i3, Parcel parcel, @Nullable Parcel parcel2, int i4) throws RemoteException {
        if (i3 != 1) {
            return super.onTransact(i3, parcel, parcel2, i4);
        }
        int i5 = 0;
        if (parcel2 == null) {
            return false;
        }
        int size = this.list.size();
        int readInt = parcel.readInt();
        while (readInt < size && parcel2.dataSize() < SUGGESTED_MAX_IPC_SIZE) {
            parcel2.writeInt(1);
            parcel2.writeBundle(this.list.get(readInt));
            readInt++;
        }
        if (readInt < size) {
            i5 = 2;
        }
        parcel2.writeInt(i5);
        return true;
    }
}
