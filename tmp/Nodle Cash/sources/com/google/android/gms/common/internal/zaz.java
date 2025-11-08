package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;
import androidx.compose.animation.core.a;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

public final class zaz extends RemoteCreator {
    private static final zaz zaa = new zaz();

    private zaz() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zaa(Context context, int i3, int i4) throws RemoteCreator.RemoteCreatorException {
        zaz zaz = zaa;
        try {
            zax zax = new zax(1, i3, i4, (Scope[]) null);
            return (View) ObjectWrapper.unwrap(((zam) zaz.getRemoteCreatorInstance(context)).zae(ObjectWrapper.wrap(context), zax));
        } catch (Exception e3) {
            throw new RemoteCreator.RemoteCreatorException(a.r("Could not get button with size ", i3, " and color ", i4), e3);
        }
    }

    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return queryLocalInterface instanceof zam ? (zam) queryLocalInterface : new zam(iBinder);
    }
}
