package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

final class zzfk implements ValueEncoderContext {
    private boolean zza = false;
    private boolean zzb = false;
    private FieldDescriptor zzc;
    private final zzfg zzd;

    public zzfk(zzfg zzfg) {
        this.zzd = zzfg;
    }

    private final void zzb() {
        if (!this.zza) {
            this.zza = true;
            return;
        }
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    @NonNull
    public final ValueEncoderContext add(double d2) throws IOException {
        zzb();
        this.zzd.zza(this.zzc, d2, this.zzb);
        return this;
    }

    public final void zza(FieldDescriptor fieldDescriptor, boolean z2) {
        this.zza = false;
        this.zzc = fieldDescriptor;
        this.zzb = z2;
    }

    @NonNull
    public final ValueEncoderContext add(float f2) throws IOException {
        zzb();
        this.zzd.zzb(this.zzc, f2, this.zzb);
        return this;
    }

    @NonNull
    public final ValueEncoderContext add(int i3) throws IOException {
        zzb();
        this.zzd.zzd(this.zzc, i3, this.zzb);
        return this;
    }

    @NonNull
    public final ValueEncoderContext add(long j2) throws IOException {
        zzb();
        this.zzd.zze(this.zzc, j2, this.zzb);
        return this;
    }

    @NonNull
    public final ValueEncoderContext add(@Nullable String str) throws IOException {
        zzb();
        this.zzd.zzc(this.zzc, str, this.zzb);
        return this;
    }

    @NonNull
    public final ValueEncoderContext add(boolean z2) throws IOException {
        zzb();
        this.zzd.zzd(this.zzc, z2 ? 1 : 0, this.zzb);
        return this;
    }

    @NonNull
    public final ValueEncoderContext add(@NonNull byte[] bArr) throws IOException {
        zzb();
        this.zzd.zzc(this.zzc, bArr, this.zzb);
        return this;
    }
}
