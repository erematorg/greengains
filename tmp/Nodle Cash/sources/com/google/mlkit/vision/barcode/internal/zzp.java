package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.mlkit_vision_barcode.zzi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzm;
import com.google.android.gms.internal.mlkit_vision_barcode.zzn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzt;
import com.google.android.gms.internal.mlkit_vision_barcode.zzu;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzp implements BarcodeSource {
    private final zzu zza;

    public zzp(zzu zzu) {
        this.zza = zzu;
    }

    @Nullable
    private static Barcode.CalendarDateTime zza(@Nullable zzj zzj) {
        if (zzj == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzj.zza, zzj.zzb, zzj.zzc, zzj.zzd, zzj.zze, zzj.zzf, zzj.zzg, zzj.zzh);
    }

    @Nullable
    public final Rect getBoundingBox() {
        zzu zzu = this.zza;
        if (zzu.zze == null) {
            return null;
        }
        int i3 = 0;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MAX_VALUE;
        int i6 = Integer.MAX_VALUE;
        int i7 = Integer.MIN_VALUE;
        while (true) {
            Point[] pointArr = zzu.zze;
            if (i3 >= pointArr.length) {
                return new Rect(i5, i6, i4, i7);
            }
            Point point = pointArr[i3];
            i5 = Math.min(i5, point.x);
            i4 = Math.max(i4, point.x);
            i6 = Math.min(i6, point.y);
            i7 = Math.max(i7, point.y);
            i3++;
        }
    }

    @Nullable
    public final Barcode.CalendarEvent getCalendarEvent() {
        zzk zzk = this.zza.zzl;
        if (zzk == null) {
            return null;
        }
        return new Barcode.CalendarEvent(zzk.zza, zzk.zzb, zzk.zzc, zzk.zzd, zzk.zze, zza(zzk.zzf), zza(zzk.zzg));
    }

    @Nullable
    public final Barcode.ContactInfo getContactInfo() {
        zzl zzl = this.zza.zzm;
        Barcode.PersonName personName = null;
        if (zzl == null) {
            return null;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.zzp zzp = zzl.zza;
        if (zzp != null) {
            personName = new Barcode.PersonName(zzp.zza, zzp.zzb, zzp.zzc, zzp.zzd, zzp.zze, zzp.zzf, zzp.zzg);
        }
        Barcode.PersonName personName2 = personName;
        String str = zzl.zzb;
        String str2 = zzl.zzc;
        zzq[] zzqArr = zzl.zzd;
        ArrayList arrayList = new ArrayList();
        if (zzqArr != null) {
            for (zzq zzq : zzqArr) {
                if (zzq != null) {
                    arrayList.add(new Barcode.Phone(zzq.zzb, zzq.zza));
                }
            }
        }
        zzn[] zznArr = zzl.zze;
        ArrayList arrayList2 = new ArrayList();
        if (zznArr != null) {
            for (zzn zzn : zznArr) {
                if (zzn != null) {
                    arrayList2.add(new Barcode.Email(zzn.zza, zzn.zzb, zzn.zzc, zzn.zzd));
                }
            }
        }
        String[] strArr = zzl.zzf;
        List asList = strArr != null ? Arrays.asList(strArr) : new ArrayList();
        zzi[] zziArr = zzl.zzg;
        ArrayList arrayList3 = new ArrayList();
        if (zziArr != null) {
            for (zzi zzi : zziArr) {
                if (zzi != null) {
                    arrayList3.add(new Barcode.Address(zzi.zza, zzi.zzb));
                }
            }
        }
        return new Barcode.ContactInfo(personName2, str, str2, arrayList, arrayList2, asList, arrayList3);
    }

    @Nullable
    public final Point[] getCornerPoints() {
        return this.zza.zze;
    }

    @Nullable
    public final String getDisplayValue() {
        return this.zza.zzc;
    }

    @Nullable
    public final Barcode.DriverLicense getDriverLicense() {
        zzm zzm = this.zza.zzn;
        if (zzm == null) {
            return null;
        }
        return new Barcode.DriverLicense(zzm.zza, zzm.zzb, zzm.zzc, zzm.zzd, zzm.zze, zzm.zzf, zzm.zzg, zzm.zzh, zzm.zzi, zzm.zzj, zzm.zzk, zzm.zzl, zzm.zzm, zzm.zzn);
    }

    @Nullable
    public final Barcode.Email getEmail() {
        zzn zzn = this.zza.zzf;
        if (zzn != null) {
            return new Barcode.Email(zzn.zza, zzn.zzb, zzn.zzc, zzn.zzd);
        }
        return null;
    }

    public final int getFormat() {
        return this.zza.zza;
    }

    @Nullable
    public final Barcode.GeoPoint getGeoPoint() {
        zzo zzo = this.zza.zzk;
        if (zzo != null) {
            return new Barcode.GeoPoint(zzo.zza, zzo.zzb);
        }
        return null;
    }

    @Nullable
    public final Barcode.Phone getPhone() {
        zzq zzq = this.zza.zzg;
        if (zzq != null) {
            return new Barcode.Phone(zzq.zzb, zzq.zza);
        }
        return null;
    }

    @Nullable
    public final byte[] getRawBytes() {
        return this.zza.zzo;
    }

    @Nullable
    public final String getRawValue() {
        return this.zza.zzb;
    }

    @Nullable
    public final Barcode.Sms getSms() {
        zzr zzr = this.zza.zzh;
        if (zzr != null) {
            return new Barcode.Sms(zzr.zza, zzr.zzb);
        }
        return null;
    }

    @Nullable
    public final Barcode.UrlBookmark getUrl() {
        zzs zzs = this.zza.zzj;
        if (zzs != null) {
            return new Barcode.UrlBookmark(zzs.zza, zzs.zzb);
        }
        return null;
    }

    public final int getValueType() {
        return this.zza.zzd;
    }

    @Nullable
    public final Barcode.WiFi getWifi() {
        zzt zzt = this.zza.zzi;
        if (zzt != null) {
            return new Barcode.WiFi(zzt.zza, zzt.zzb, zzt.zzc);
        }
        return null;
    }
}
