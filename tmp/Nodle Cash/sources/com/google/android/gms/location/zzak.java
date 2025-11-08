package com.google.android.gms.location;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.core.location.LocationCompat;
import com.google.android.gms.internal.location.zzeo;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.komputing.kbip44.BIP44Kt;

public final class zzak {
    public static final /* synthetic */ int zza = 0;
    private static final DecimalFormat zzb;
    private static final DecimalFormat zzc;
    @GuardedBy("sharedStringBuilder")
    private static final StringBuilder zzd = new StringBuilder();

    static {
        Locale locale = Locale.ROOT;
        zzb = new DecimalFormat(".000000", DecimalFormatSymbols.getInstance(locale));
        DecimalFormat decimalFormat = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(locale));
        zzc = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    public static StringBuilder zza(Location location, StringBuilder sb) {
        sb.ensureCapacity(100);
        String str = null;
        if (location == null) {
            sb.append((String) null);
            return sb;
        }
        sb.append("{");
        sb.append(location.getProvider());
        sb.append(", ");
        if (LocationCompat.isMock(location)) {
            sb.append("mock, ");
        }
        DecimalFormat decimalFormat = zzb;
        sb.append(decimalFormat.format(location.getLatitude()));
        sb.append(",");
        sb.append(decimalFormat.format(location.getLongitude()));
        if (location.hasAccuracy()) {
            sb.append("±");
            sb.append(zzc.format((double) location.getAccuracy()));
            sb.append(BIP44Kt.BIP44_PREFIX);
        }
        if (location.hasAltitude()) {
            sb.append(", alt=");
            DecimalFormat decimalFormat2 = zzc;
            sb.append(decimalFormat2.format(location.getAltitude()));
            if (LocationCompat.hasVerticalAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat2.format((double) LocationCompat.getVerticalAccuracyMeters(location)));
            }
            sb.append(BIP44Kt.BIP44_PREFIX);
        }
        if (location.hasSpeed()) {
            sb.append(", spd=");
            DecimalFormat decimalFormat3 = zzc;
            sb.append(decimalFormat3.format((double) location.getSpeed()));
            if (LocationCompat.hasSpeedAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat3.format((double) LocationCompat.getSpeedAccuracyMetersPerSecond(location)));
            }
            sb.append("m/s");
        }
        if (location.hasBearing()) {
            sb.append(", brg=");
            DecimalFormat decimalFormat4 = zzc;
            sb.append(decimalFormat4.format((double) location.getBearing()));
            if (LocationCompat.hasBearingAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat4.format((double) LocationCompat.getBearingAccuracyDegrees(location)));
            }
            sb.append("°");
        }
        Bundle extras = location.getExtras();
        String string = extras != null ? extras.getString("floorLabel") : null;
        if (string != null) {
            sb.append(", fl=");
            sb.append(string);
        }
        Bundle extras2 = location.getExtras();
        if (extras2 != null) {
            str = extras2.getString("levelId");
        }
        if (str != null) {
            sb.append(", lv=");
            sb.append(str);
        }
        long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        sb.append(", ert=");
        sb.append(zzeo.zza(LocationCompat.getElapsedRealtimeMillis(location) + currentTimeMillis));
        sb.append(AbstractJsonLexerKt.END_OBJ);
        return sb;
    }
}
