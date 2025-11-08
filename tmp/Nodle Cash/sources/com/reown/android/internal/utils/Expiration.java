package com.reown.android.internal.utils;

import kotlin.Metadata;
import kotlin.jvm.JvmName;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\"\u0011\u0010\u0000\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"PROPOSAL_EXPIRY", "", "getPROPOSAL_EXPIRY", "()J", "ACTIVE_SESSION", "getACTIVE_SESSION", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "Expiration")
public final class Expiration {
    public static final long getACTIVE_SESSION() {
        return Time.getWeekInSeconds() + Time.getCurrentTimeInSeconds();
    }

    public static final long getPROPOSAL_EXPIRY() {
        return Time.getFiveMinutesInSeconds() + Time.getCurrentTimeInSeconds();
    }
}
