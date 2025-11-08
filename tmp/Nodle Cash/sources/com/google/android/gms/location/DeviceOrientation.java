package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.location.zzer;
import com.google.common.primitives.SignedBytes;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.dataflow.qual.Pure;
import org.msgpack.core.MessagePack;

@SafeParcelable.Class(creator = "DeviceOrientationCreator")
@SafeParcelable.Reserved({2, 3})
public class DeviceOrientation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DeviceOrientation> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getAttitude", id = 1)
    private final float[] zza;
    @SafeParcelable.Field(getter = "getHeadingDegrees", id = 4)
    private final float zzb;
    @SafeParcelable.Field(getter = "getHeadingErrorDegrees", id = 5)
    private final float zzc;
    @SafeParcelable.Field(getter = "getElapsedRealtimeNs", id = 6)
    private final long zzd;
    @SafeParcelable.Field(getter = "getFieldMask", id = 7)
    private final byte zze;
    @SafeParcelable.Field(getter = "getConservativeHeadingErrorVonMisesKappa", id = 8)
    private final float zzf;
    @SafeParcelable.Field(getter = "getConservativeHeadingErrorDegrees", id = 9)
    private final float zzg;

    @SafeParcelable.Constructor
    public DeviceOrientation(@SafeParcelable.Param(id = 1) float[] fArr, @SafeParcelable.Param(id = 4) float f2, @SafeParcelable.Param(id = 5) float f3, @SafeParcelable.Param(id = 6) long j2, @SafeParcelable.Param(id = 7) byte b3, @SafeParcelable.Param(id = 8) float f4, @SafeParcelable.Param(id = 9) float f5) {
        zzj(fArr);
        boolean z2 = true;
        zzer.zza(f2 >= 0.0f && f2 < 360.0f);
        zzer.zza(f3 >= 0.0f && f3 <= 180.0f);
        zzer.zza(f5 >= 0.0f && f5 <= 180.0f);
        zzer.zza(j2 < 0 ? false : z2);
        this.zza = fArr;
        this.zzb = f2;
        this.zzc = f3;
        this.zzf = f4;
        this.zzg = f5;
        this.zzd = j2;
        this.zze = (byte) (((byte) (((byte) (b3 | 16)) | 4)) | 8);
    }

    /* access modifiers changed from: private */
    public static void zzj(float[] fArr) {
        boolean z2 = true;
        zzer.zzb(fArr != null && fArr.length == 4, "Input attitude array should be of length 4.");
        if (Float.isNaN(fArr[0]) || Float.isNaN(fArr[1]) || Float.isNaN(fArr[2]) || Float.isNaN(fArr[3])) {
            z2 = false;
        }
        zzer.zzb(z2, "Input attitude cannot contain NaNs.");
    }

    @Pure
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceOrientation)) {
            return false;
        }
        DeviceOrientation deviceOrientation = (DeviceOrientation) obj;
        return Float.compare(this.zzb, deviceOrientation.zzb) == 0 && Float.compare(this.zzc, deviceOrientation.zzc) == 0 && (zza() == deviceOrientation.zza() && (!zza() || Float.compare(this.zzf, deviceOrientation.zzf) == 0)) && (hasConservativeHeadingErrorDegrees() == deviceOrientation.hasConservativeHeadingErrorDegrees() && (!hasConservativeHeadingErrorDegrees() || Float.compare(getConservativeHeadingErrorDegrees(), deviceOrientation.getConservativeHeadingErrorDegrees()) == 0)) && this.zzd == deviceOrientation.zzd && Arrays.equals(this.zza, deviceOrientation.zza);
    }

    @NonNull
    @Pure
    public float[] getAttitude() {
        return (float[]) this.zza.clone();
    }

    @Pure
    public float getConservativeHeadingErrorDegrees() {
        return this.zzg;
    }

    @Pure
    public long getElapsedRealtimeNs() {
        return this.zzd;
    }

    @Pure
    public float getHeadingDegrees() {
        return this.zzb;
    }

    @Pure
    public float getHeadingErrorDegrees() {
        return this.zzc;
    }

    @Pure
    public boolean hasConservativeHeadingErrorDegrees() {
        return (this.zze & SignedBytes.MAX_POWER_OF_TWO) != 0;
    }

    @Pure
    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzb), Float.valueOf(this.zzc), Float.valueOf(this.zzg), Long.valueOf(this.zzd), this.zza, Byte.valueOf(this.zze));
    }

    @NonNull
    @Pure
    public String toString() {
        StringBuilder sb = new StringBuilder("DeviceOrientation[attitude=");
        sb.append(Arrays.toString(this.zza));
        sb.append(", headingDegrees=");
        sb.append(this.zzb);
        sb.append(", headingErrorDegrees=");
        sb.append(this.zzc);
        if (hasConservativeHeadingErrorDegrees()) {
            sb.append(", conservativeHeadingErrorDegrees=");
            sb.append(this.zzg);
        }
        sb.append(", elapsedRealtimeNs=");
        return a.q(sb, this.zzd, AbstractJsonLexerKt.END_LIST);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloatArray(parcel, 1, getAttitude(), false);
        SafeParcelWriter.writeFloat(parcel, 4, getHeadingDegrees());
        SafeParcelWriter.writeFloat(parcel, 5, getHeadingErrorDegrees());
        SafeParcelWriter.writeLong(parcel, 6, getElapsedRealtimeNs());
        SafeParcelWriter.writeByte(parcel, 7, this.zze);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 9, getConservativeHeadingErrorDegrees());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final boolean zza() {
        return (this.zze & 32) != 0;
    }

    public final /* synthetic */ float[] zzc() {
        return this.zza;
    }

    public final /* synthetic */ float zzd() {
        return this.zzb;
    }

    public final /* synthetic */ float zze() {
        return this.zzc;
    }

    public final /* synthetic */ long zzf() {
        return this.zzd;
    }

    public final /* synthetic */ byte zzg() {
        return this.zze;
    }

    public final /* synthetic */ float zzh() {
        return this.zzf;
    }

    public final /* synthetic */ float zzi() {
        return this.zzg;
    }

    public static final class Builder {
        private final float[] zza;
        private float zzb;
        private float zzc;
        private long zzd;
        private byte zze = 0;
        private float zzf;
        private float zzg;

        public Builder(@NonNull DeviceOrientation deviceOrientation) {
            DeviceOrientation.zzj(deviceOrientation.zzc());
            this.zza = Arrays.copyOf(deviceOrientation.zzc(), deviceOrientation.zzc().length);
            setHeadingDegrees(deviceOrientation.zzd());
            setHeadingErrorDegrees(deviceOrientation.zze());
            setConservativeHeadingErrorDegrees(deviceOrientation.zzi());
            setElapsedRealtimeNs(deviceOrientation.zzf());
            this.zzf = deviceOrientation.zzh();
            this.zze = deviceOrientation.zzg();
        }

        @NonNull
        public DeviceOrientation build() {
            return new DeviceOrientation(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        @NonNull
        public Builder clearConservativeHeadingErrorDegrees() {
            this.zzg = 180.0f;
            this.zzf = 0.0f;
            this.zze = (byte) (((byte) (this.zze & -65)) & MessagePack.Code.MAP32);
            return this;
        }

        @NonNull
        public Builder setAttitude(@NonNull float[] fArr) {
            DeviceOrientation.zzj(fArr);
            System.arraycopy(fArr, 0, this.zza, 0, fArr.length);
            return this;
        }

        @NonNull
        public Builder setConservativeHeadingErrorDegrees(float f2) {
            float f3 = 0.0f;
            boolean z2 = false;
            if (f2 >= 0.0f && f2 <= 180.0f) {
                z2 = true;
            }
            zzer.zzb(z2, "conservativeHeadingErrorDegrees should be between 0 and 180.");
            this.zzg = f2;
            this.zze = (byte) (this.zze | SignedBytes.MAX_POWER_OF_TWO);
            Parcelable.Creator<DeviceOrientation> creator = DeviceOrientation.CREATOR;
            if (f2 < 180.0f) {
                f3 = (float) (2.0d / (1.0d - Math.cos(Math.toRadians((double) f2))));
            }
            this.zzf = f3;
            this.zze = (byte) (this.zze | 32);
            return this;
        }

        @NonNull
        public Builder setElapsedRealtimeNs(long j2) {
            zzer.zzb(j2 >= 0, "elapsedRealtimeNs should be greater than or equal to 0.");
            this.zzd = j2;
            return this;
        }

        @NonNull
        public Builder setHeadingDegrees(float f2) {
            boolean z2 = false;
            if (f2 >= 0.0f && f2 < 360.0f) {
                z2 = true;
            }
            zzer.zzb(z2, "headingDegrees should be greater than or equal to 0 and less than 360.");
            this.zzb = f2;
            return this;
        }

        @NonNull
        public Builder setHeadingErrorDegrees(float f2) {
            boolean z2 = false;
            if (f2 >= 0.0f && f2 <= 180.0f) {
                z2 = true;
            }
            zzer.zzb(z2, "headingErrorDegrees should be between 0 and 180.");
            this.zzc = f2;
            return this;
        }

        public Builder(@NonNull float[] fArr, float f2, float f3, long j2) {
            DeviceOrientation.zzj(fArr);
            this.zza = Arrays.copyOf(fArr, fArr.length);
            setHeadingDegrees(f2);
            setHeadingErrorDegrees(f3);
            setElapsedRealtimeNs(j2);
            this.zzf = 0.0f;
            this.zzg = 180.0f;
            this.zze = 0;
        }
    }
}
