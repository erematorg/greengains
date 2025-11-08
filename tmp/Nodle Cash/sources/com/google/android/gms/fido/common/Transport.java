package com.google.android.gms.fido.common;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public enum Transport implements ReflectedParcelable {
    BLUETOOTH_CLASSIC("bt"),
    BLUETOOTH_LOW_ENERGY("ble"),
    NFC("nfc"),
    USB("usb"),
    INTERNAL("internal"),
    HYBRID("cable");
    
    @NonNull
    public static final Parcelable.Creator<Transport> CREATOR = null;
    private final String zzb;

    public static class UnsupportedTransportException extends Exception {
        public UnsupportedTransportException(@NonNull String str) {
            super(str);
        }
    }

    static {
        CREATOR = new zza();
    }

    private Transport(String str) {
        this.zzb = str;
    }

    @NonNull
    public static Transport fromString(@NonNull String str) throws UnsupportedTransportException {
        for (Transport transport : values()) {
            if (str.equals(transport.zzb)) {
                return transport;
            }
        }
        if (str.equals("hybrid")) {
            return HYBRID;
        }
        throw new UnsupportedTransportException(a.l("Transport ", str, " not supported"));
    }

    @NonNull
    public static List<Transport> parseTransports(@NonNull JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        HashSet hashSet = new HashSet(jSONArray.length());
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            String string = jSONArray.getString(i3);
            if (string != null && !string.isEmpty()) {
                try {
                    hashSet.add(fromString(string));
                } catch (UnsupportedTransportException unused) {
                    Log.w("Transport", "Ignoring unrecognized transport ".concat(string));
                }
            }
        }
        return new ArrayList(hashSet);
    }

    public int describeContents() {
        return 0;
    }

    @NonNull
    public String toString() {
        return this.zzb;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        parcel.writeString(this.zzb);
    }
}
