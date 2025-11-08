package com.reown.android.pairing.model;

import com.reown.android.internal.utils.Time;
import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\"\u0011\u0010\u0000\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"pairingExpiry", "", "getPairingExpiry", "()J", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "Expiration")
public final class Expiration {
    public static final long getPairingExpiry() {
        return Time.getFiveMinutesInSeconds() + Time.getCurrentTimeInSeconds();
    }
}
