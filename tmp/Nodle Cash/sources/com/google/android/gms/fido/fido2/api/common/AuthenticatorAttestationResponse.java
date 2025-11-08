package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fido.u2f.api.common.SignResponseData;
import com.google.android.gms.internal.fido.zzam;
import com.google.android.gms.internal.fido.zzan;
import com.google.android.gms.internal.fido.zzch;
import java.util.Arrays;

@SafeParcelable.Class(creator = "AuthenticatorAttestationResponseCreator")
@SafeParcelable.Reserved({1})
public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    @NonNull
    public static final Parcelable.Creator<AuthenticatorAttestationResponse> CREATOR = new zzk();
    @SafeParcelable.Field(getter = "getKeyHandle", id = 2)
    @NonNull
    private final byte[] zza;
    @SafeParcelable.Field(getter = "getClientDataJSON", id = 3)
    @NonNull
    private final byte[] zzb;
    @SafeParcelable.Field(getter = "getAttestationObject", id = 4)
    @NonNull
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getTransports", id = 5)
    @NonNull
    private final String[] zzd;

    @SafeParcelable.Constructor
    public AuthenticatorAttestationResponse(@SafeParcelable.Param(id = 2) @NonNull byte[] bArr, @SafeParcelable.Param(id = 3) @NonNull byte[] bArr2, @SafeParcelable.Param(id = 4) @NonNull byte[] bArr3, @SafeParcelable.Param(id = 5) @NonNull String[] strArr) {
        this.zza = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzb = (byte[]) Preconditions.checkNotNull(bArr2);
        this.zzc = (byte[]) Preconditions.checkNotNull(bArr3);
        this.zzd = (String[]) Preconditions.checkNotNull(strArr);
    }

    @NonNull
    public static AuthenticatorAttestationResponse deserializeFromBytes(@NonNull byte[] bArr) {
        return (AuthenticatorAttestationResponse) SafeParcelableSerializer.deserializeFromBytes(bArr, CREATOR);
    }

    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof AuthenticatorAttestationResponse)) {
            return false;
        }
        AuthenticatorAttestationResponse authenticatorAttestationResponse = (AuthenticatorAttestationResponse) obj;
        return Arrays.equals(this.zza, authenticatorAttestationResponse.zza) && Arrays.equals(this.zzb, authenticatorAttestationResponse.zzb) && Arrays.equals(this.zzc, authenticatorAttestationResponse.zzc);
    }

    @NonNull
    public byte[] getAttestationObject() {
        return this.zzc;
    }

    @NonNull
    public byte[] getClientDataJSON() {
        return this.zzb;
    }

    @NonNull
    @Deprecated
    public byte[] getKeyHandle() {
        return this.zza;
    }

    @NonNull
    public String[] getTransports() {
        return this.zzd;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.zza)), Integer.valueOf(Arrays.hashCode(this.zzb)), Integer.valueOf(Arrays.hashCode(this.zzc)));
    }

    @NonNull
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @NonNull
    public String toString() {
        zzam zza2 = zzan.zza(this);
        zzch zzf = zzch.zzf();
        byte[] bArr = this.zza;
        zza2.zzb(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, zzf.zzg(bArr, 0, bArr.length));
        zzch zzf2 = zzch.zzf();
        byte[] bArr2 = this.zzb;
        zza2.zzb("clientDataJSON", zzf2.zzg(bArr2, 0, bArr2.length));
        zzch zzf3 = zzch.zzf();
        byte[] bArr3 = this.zzc;
        zza2.zzb("attestationObject", zzf3.zzg(bArr3, 0, bArr3.length));
        zza2.zzb("transports", Arrays.toString(this.zzd));
        return zza2.toString();
    }

    public void writeToParcel(@NonNull Parcel parcel, int i3) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getAttestationObject(), false);
        SafeParcelWriter.writeStringArray(parcel, 5, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Removed duplicated region for block: B:84:0x01ef A[Catch:{ zzdl | zzdq -> 0x023a, zzdq -> 0x0228, IllegalArgumentException -> 0x0216, zzdl | zzdq -> 0x020d, zzdq -> 0x0185, JSONException -> 0x0015 }] */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject zza() {
        /*
            r18 = this;
            r0 = r18
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0015 }
            r1.<init>()     // Catch:{ JSONException -> 0x0015 }
            byte[] r2 = r0.zzb     // Catch:{ JSONException -> 0x0015 }
            if (r2 == 0) goto L_0x0018
            java.lang.String r3 = "clientDataJSON"
            java.lang.String r2 = com.google.android.gms.common.util.Base64Utils.encodeUrlSafeNoPadding(r2)     // Catch:{ JSONException -> 0x0015 }
            r1.put(r3, r2)     // Catch:{ JSONException -> 0x0015 }
            goto L_0x0018
        L_0x0015:
            r0 = move-exception
            goto L_0x0243
        L_0x0018:
            byte[] r2 = r0.zzc     // Catch:{ JSONException -> 0x0015 }
            if (r2 == 0) goto L_0x0025
            java.lang.String r3 = "attestationObject"
            java.lang.String r2 = com.google.android.gms.common.util.Base64Utils.encodeUrlSafeNoPadding(r2)     // Catch:{ JSONException -> 0x0015 }
            r1.put(r3, r2)     // Catch:{ JSONException -> 0x0015 }
        L_0x0025:
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0015 }
            r2.<init>()     // Catch:{ JSONException -> 0x0015 }
            r3 = 0
            r4 = r3
        L_0x002c:
            java.lang.String[] r5 = r0.zzd     // Catch:{ JSONException -> 0x0015 }
            int r6 = r5.length     // Catch:{ JSONException -> 0x0015 }
            if (r4 >= r6) goto L_0x004f
            r5 = r5[r4]     // Catch:{ JSONException -> 0x0015 }
            com.google.android.gms.fido.common.Transport r6 = com.google.android.gms.fido.common.Transport.HYBRID     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x0015 }
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x0015 }
            if (r5 == 0) goto L_0x0045
            java.lang.String r5 = "hybrid"
            r2.put(r4, r5)     // Catch:{ JSONException -> 0x0015 }
            goto L_0x004c
        L_0x0045:
            java.lang.String[] r5 = r0.zzd     // Catch:{ JSONException -> 0x0015 }
            r5 = r5[r4]     // Catch:{ JSONException -> 0x0015 }
            r2.put(r4, r5)     // Catch:{ JSONException -> 0x0015 }
        L_0x004c:
            int r4 = r4 + 1
            goto L_0x002c
        L_0x004f:
            java.lang.String r4 = "transports"
            r1.put(r4, r2)     // Catch:{ JSONException -> 0x0015 }
            byte[] r0 = r0.zzc     // Catch:{ JSONException -> 0x0015 }
            com.google.android.gms.internal.fido.zzdr r0 = com.google.android.gms.internal.fido.zzdr.zzj(r0)     // Catch:{ zzdl | zzdq -> 0x023a }
            com.google.android.gms.internal.fido.zzdo r0 = r0.zzh()     // Catch:{ zzdl | zzdq -> 0x023a }
            com.google.android.gms.internal.fido.zzbg r0 = r0.zzc()     // Catch:{ zzdq -> 0x0228 }
            java.lang.String r2 = "authData"
            com.google.android.gms.internal.fido.zzdp r2 = com.google.android.gms.internal.fido.zzdr.zzi(r2)     // Catch:{ zzdq -> 0x0228 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ zzdq -> 0x0228 }
            com.google.android.gms.internal.fido.zzdr r0 = (com.google.android.gms.internal.fido.zzdr) r0     // Catch:{ zzdq -> 0x0228 }
            if (r0 == 0) goto L_0x022a
            com.google.android.gms.internal.fido.zzdk r0 = r0.zze()     // Catch:{ zzdq -> 0x0228 }
            com.google.android.gms.internal.fido.zzcz r0 = r0.zzc()     // Catch:{ zzdq -> 0x0228 }
            java.nio.ByteBuffer r2 = r0.zzi()     // Catch:{ JSONException -> 0x0015 }
            int r4 = r2.position()     // Catch:{ IllegalArgumentException -> 0x0216 }
            r5 = 32
            int r4 = r4 + r5
            r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0216 }
            byte r4 = r2.get()     // Catch:{ IllegalArgumentException -> 0x0216 }
            r4 = r4 & 64
            if (r4 == 0) goto L_0x0218
            int r4 = r2.position()     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r4 = r4 + 4
            r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r4 = r2.position()     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r4 = r4 + 16
            r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0216 }
            short r4 = r2.getShort()     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r6 = r2.position()     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r6 = r6 + r4
            r2.position(r6)     // Catch:{ IllegalArgumentException -> 0x0216 }
            int r2 = r2.position()     // Catch:{ zzdl | zzdq -> 0x020d }
            int r4 = r0.zzd()     // Catch:{ zzdl | zzdq -> 0x020d }
            com.google.android.gms.internal.fido.zzcz r2 = r0.zzg(r2, r4)     // Catch:{ zzdl | zzdq -> 0x020d }
            java.io.InputStream r2 = r2.zzh()     // Catch:{ zzdl | zzdq -> 0x020d }
            com.google.android.gms.internal.fido.zzdr r2 = com.google.android.gms.internal.fido.zzdr.zzk(r2)     // Catch:{ zzdl | zzdq -> 0x020d }
            com.google.android.gms.internal.fido.zzdo r2 = r2.zzh()     // Catch:{ zzdl | zzdq -> 0x020d }
            com.google.android.gms.internal.fido.zzbg r4 = r2.zzc()     // Catch:{ JSONException -> 0x0015 }
            r6 = 3
            com.google.android.gms.internal.fido.zzdm r6 = com.google.android.gms.internal.fido.zzdr.zzg(r6)     // Catch:{ JSONException -> 0x0015 }
            java.lang.Object r4 = r4.get(r6)     // Catch:{ JSONException -> 0x0015 }
            com.google.android.gms.internal.fido.zzdr r4 = (com.google.android.gms.internal.fido.zzdr) r4     // Catch:{ JSONException -> 0x0015 }
            com.google.android.gms.internal.fido.zzbg r6 = r2.zzc()     // Catch:{ JSONException -> 0x0015 }
            r7 = 1
            com.google.android.gms.internal.fido.zzdm r9 = com.google.android.gms.internal.fido.zzdr.zzg(r7)     // Catch:{ JSONException -> 0x0015 }
            java.lang.Object r6 = r6.get(r9)     // Catch:{ JSONException -> 0x0015 }
            com.google.android.gms.internal.fido.zzdr r6 = (com.google.android.gms.internal.fido.zzdr) r6     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r9 = "COSE key missing required fields"
            if (r4 == 0) goto L_0x0207
            if (r6 == 0) goto L_0x0207
            com.google.android.gms.internal.fido.zzdm r4 = r4.zzf()     // Catch:{ zzdq -> 0x0185 }
            long r10 = r4.zzc()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdm r4 = r6.zzf()     // Catch:{ zzdq -> 0x0185 }
            long r12 = r4.zzc()     // Catch:{ zzdq -> 0x0185 }
            int r4 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            r14 = 2
            if (r4 == 0) goto L_0x010a
            int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x0107
            r12 = r14
            goto L_0x010a
        L_0x0107:
            r6 = 0
            goto L_0x01db
        L_0x010a:
            com.google.android.gms.internal.fido.zzbg r4 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            r16 = -1
            com.google.android.gms.internal.fido.zzdm r6 = com.google.android.gms.internal.fido.zzdr.zzg(r16)     // Catch:{ zzdq -> 0x0185 }
            java.lang.Object r4 = r4.get(r6)     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdr r4 = (com.google.android.gms.internal.fido.zzdr) r4     // Catch:{ zzdq -> 0x0185 }
            if (r4 == 0) goto L_0x01f9
            com.google.android.gms.internal.fido.zzdm r4 = r4.zzf()     // Catch:{ zzdq -> 0x0185 }
            long r16 = r4.zzc()     // Catch:{ zzdq -> 0x0185 }
            int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            java.lang.String r6 = "COSE coordinates are the wrong size"
            r14 = -2
            if (r4 != 0) goto L_0x0194
            int r4 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r4 != 0) goto L_0x0194
            com.google.android.gms.internal.fido.zzbg r4 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdm r7 = com.google.android.gms.internal.fido.zzdr.zzg(r14)     // Catch:{ zzdq -> 0x0185 }
            java.lang.Object r4 = r4.get(r7)     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdr r4 = (com.google.android.gms.internal.fido.zzdr) r4     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzbg r2 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            r7 = -3
            com.google.android.gms.internal.fido.zzdm r7 = com.google.android.gms.internal.fido.zzdr.zzg(r7)     // Catch:{ zzdq -> 0x0185 }
            java.lang.Object r2 = r2.get(r7)     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdr r2 = (com.google.android.gms.internal.fido.zzdr) r2     // Catch:{ zzdq -> 0x0185 }
            if (r4 == 0) goto L_0x018e
            if (r2 == 0) goto L_0x018e
            com.google.android.gms.internal.fido.zzdk r4 = r4.zze()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzcz r4 = r4.zzc()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdk r2 = r2.zze()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzcz r2 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            int r7 = r4.zzd()     // Catch:{ zzdq -> 0x0185 }
            if (r7 != r5) goto L_0x0188
            int r7 = r2.zzd()     // Catch:{ zzdq -> 0x0185 }
            if (r7 != r5) goto L_0x0188
            java.lang.String r5 = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE"
            byte[] r3 = android.util.Base64.decode(r5, r3)     // Catch:{ zzdq -> 0x0185 }
            byte[] r4 = r4.zzm()     // Catch:{ zzdq -> 0x0185 }
            byte[] r2 = r2.zzm()     // Catch:{ zzdq -> 0x0185 }
            byte[][] r2 = new byte[][]{r3, r4, r2}     // Catch:{ zzdq -> 0x0185 }
            byte[] r6 = com.google.android.gms.internal.fido.zzcl.zza(r2)     // Catch:{ zzdq -> 0x0185 }
            goto L_0x01db
        L_0x0185:
            r0 = move-exception
            goto L_0x01ff
        L_0x0188:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0185 }
            r0.<init>(r6)     // Catch:{ zzdq -> 0x0185 }
            throw r0     // Catch:{ zzdq -> 0x0185 }
        L_0x018e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0185 }
            r0.<init>(r9)     // Catch:{ zzdq -> 0x0185 }
            throw r0     // Catch:{ zzdq -> 0x0185 }
        L_0x0194:
            int r4 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r4 != 0) goto L_0x0107
            r7 = 6
            int r4 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r4 != 0) goto L_0x0107
            com.google.android.gms.internal.fido.zzbg r2 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdm r4 = com.google.android.gms.internal.fido.zzdr.zzg(r14)     // Catch:{ zzdq -> 0x0185 }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzdr r2 = (com.google.android.gms.internal.fido.zzdr) r2     // Catch:{ zzdq -> 0x0185 }
            if (r2 == 0) goto L_0x01d5
            com.google.android.gms.internal.fido.zzdk r2 = r2.zze()     // Catch:{ zzdq -> 0x0185 }
            com.google.android.gms.internal.fido.zzcz r2 = r2.zzc()     // Catch:{ zzdq -> 0x0185 }
            int r4 = r2.zzd()     // Catch:{ zzdq -> 0x0185 }
            if (r4 != r5) goto L_0x01cf
            java.lang.String r4 = "MCowBQYDK2VwAyEA"
            byte[] r3 = android.util.Base64.decode(r4, r3)     // Catch:{ zzdq -> 0x0185 }
            byte[] r2 = r2.zzm()     // Catch:{ zzdq -> 0x0185 }
            byte[][] r2 = new byte[][]{r3, r2}     // Catch:{ zzdq -> 0x0185 }
            byte[] r6 = com.google.android.gms.internal.fido.zzcl.zza(r2)     // Catch:{ zzdq -> 0x0185 }
            goto L_0x01db
        L_0x01cf:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0185 }
            r0.<init>(r6)     // Catch:{ zzdq -> 0x0185 }
            throw r0     // Catch:{ zzdq -> 0x0185 }
        L_0x01d5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0185 }
            r0.<init>(r9)     // Catch:{ zzdq -> 0x0185 }
            throw r0     // Catch:{ zzdq -> 0x0185 }
        L_0x01db:
            java.lang.String r2 = "authenticatorData"
            byte[] r0 = r0.zzm()     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r0 = com.google.android.gms.common.util.Base64Utils.encodeUrlSafeNoPadding(r0)     // Catch:{ JSONException -> 0x0015 }
            r1.put(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r0 = "publicKeyAlgorithm"
            r1.put(r0, r10)     // Catch:{ JSONException -> 0x0015 }
            if (r6 == 0) goto L_0x01f8
            java.lang.String r0 = "publicKey"
            java.lang.String r2 = com.google.android.gms.common.util.Base64Utils.encodeUrlSafeNoPadding(r6)     // Catch:{ JSONException -> 0x0015 }
            r1.put(r0, r2)     // Catch:{ JSONException -> 0x0015 }
        L_0x01f8:
            return r1
        L_0x01f9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0185 }
            r0.<init>(r9)     // Catch:{ zzdq -> 0x0185 }
            throw r0     // Catch:{ zzdq -> 0x0185 }
        L_0x01ff:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "COSE key ill-formed"
            r1.<init>(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            throw r1     // Catch:{ JSONException -> 0x0015 }
        L_0x0207:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            r0.<init>(r9)     // Catch:{ JSONException -> 0x0015 }
            throw r0     // Catch:{ JSONException -> 0x0015 }
        L_0x020d:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "failed to parse COSE key"
            r1.<init>(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            throw r1     // Catch:{ JSONException -> 0x0015 }
        L_0x0216:
            r0 = move-exception
            goto L_0x0220
        L_0x0218:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0216 }
            java.lang.String r1 = "authData does not include credential data"
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x0216 }
            throw r0     // Catch:{ IllegalArgumentException -> 0x0216 }
        L_0x0220:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "ill-formed authenticator data"
            r1.<init>(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            throw r1     // Catch:{ JSONException -> 0x0015 }
        L_0x0228:
            r0 = move-exception
            goto L_0x0232
        L_0x022a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ zzdq -> 0x0228 }
            java.lang.String r1 = "attestation object missing authData"
            r0.<init>(r1)     // Catch:{ zzdq -> 0x0228 }
            throw r0     // Catch:{ zzdq -> 0x0228 }
        L_0x0232:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "authData value has wrong type"
            r1.<init>(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            throw r1     // Catch:{ JSONException -> 0x0015 }
        L_0x023a:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0015 }
            java.lang.String r2 = "failed to parse attestation object"
            r1.<init>(r2, r0)     // Catch:{ JSONException -> 0x0015 }
            throw r1     // Catch:{ JSONException -> 0x0015 }
        L_0x0243:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error encoding AuthenticatorAttestationResponse to JSON object"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse.zza():org.json.JSONObject");
    }
}
