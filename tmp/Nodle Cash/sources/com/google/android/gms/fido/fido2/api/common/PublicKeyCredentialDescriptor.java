package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType;
import com.google.android.gms.internal.fido.zzbc;
import com.google.android.gms.internal.fido.zzh;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PublicKeyCredentialDescriptorCreator")
@SafeParcelable.Reserved({1})
public class PublicKeyCredentialDescriptor extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PublicKeyCredentialDescriptor> CREATOR = new zzam();
    private static final zzbc zza = zzbc.zzk(zzh.zza, zzh.zzb);
    @SafeParcelable.Field(getter = "getTypeAsString", id = 2, type = "java.lang.String")
    @NonNull
    private final PublicKeyCredentialType zzb;
    @SafeParcelable.Field(getter = "getId", id = 3)
    @NonNull
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getTransports", id = 4)
    @Nullable
    private final List zzd;

    public static class UnsupportedPubKeyCredDescriptorException extends Exception {
        public UnsupportedPubKeyCredDescriptorException(@NonNull String str) {
            super(str);
        }

        public UnsupportedPubKeyCredDescriptorException(@NonNull String str, @NonNull Throwable th) {
            super(str, th);
        }
    }

    @SafeParcelable.Constructor
    public PublicKeyCredentialDescriptor(@SafeParcelable.Param(id = 2) @NonNull String str, @SafeParcelable.Param(id = 3) @NonNull byte[] bArr, @SafeParcelable.Param(id = 4) @Nullable List<Transport> list) {
        Preconditions.checkNotNull(str);
        try {
            this.zzb = PublicKeyCredentialType.fromString(str);
            this.zzc = (byte[]) Preconditions.checkNotNull(bArr);
            this.zzd = list;
        } catch (PublicKeyCredentialType.UnsupportedPublicKeyCredTypeException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        r3 = r5.zzd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@androidx.annotation.NonNull java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor r5 = (com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor) r5
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType r0 = r4.zzb
            com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialType r2 = r5.zzb
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0013
            return r1
        L_0x0013:
            byte[] r0 = r4.zzc
            byte[] r2 = r5.zzc
            boolean r0 = java.util.Arrays.equals(r0, r2)
            if (r0 != 0) goto L_0x001e
            return r1
        L_0x001e:
            java.util.List r0 = r4.zzd
            r2 = 1
            if (r0 != 0) goto L_0x0029
            java.util.List r3 = r5.zzd
            if (r3 == 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            return r2
        L_0x0029:
            if (r0 == 0) goto L_0x0041
            java.util.List r3 = r5.zzd
            if (r3 != 0) goto L_0x0030
            goto L_0x0041
        L_0x0030:
            boolean r0 = r0.containsAll(r3)
            if (r0 == 0) goto L_0x0041
            java.util.List r5 = r5.zzd
            java.util.List r4 = r4.zzd
            boolean r4 = r5.containsAll(r4)
            if (r4 == 0) goto L_0x0041
            return r2
        L_0x0041:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor.equals(java.lang.Object):boolean");
    }

    @NonNull
    public byte[] getId() {
        return this.zzc;
    }

    @Nullable
    public List<Transport> getTransports() {
        return this.zzd;
    }

    @NonNull
    public PublicKeyCredentialType getType() {
        return this.zzb;
    }

    @NonNull
    public String getTypeAsString() {
        return this.zzb.toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, Integer.valueOf(Arrays.hashCode(this.zzc)), this.zzd);
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getTypeAsString(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getId(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
