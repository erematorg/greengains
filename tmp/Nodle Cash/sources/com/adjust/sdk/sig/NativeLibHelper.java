package com.adjust.sdk.sig;

import android.content.Context;
import android.util.Log;

class NativeLibHelper implements a {
    static {
        try {
            System.loadLibrary("signer");
        } catch (UnsatisfiedLinkError e3) {
            Log.e("NativeLibHelper", "Signer Library could not be loaded: " + e3.getMessage());
        }
    }

    private native void nOnResume();

    private native byte[] nSign(Context context, Object obj, byte[] bArr, int i3);

    public final void a() {
        nOnResume();
    }

    public final byte[] a(Context context, Object obj, byte[] bArr, int i3) {
        return nSign(context, obj, bArr, i3);
    }
}
