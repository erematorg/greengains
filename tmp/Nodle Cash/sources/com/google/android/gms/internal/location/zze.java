package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.checkerframework.dataflow.qual.Pure;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nClientIdentity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientIdentity.kt\ncom/google/android/gms/libs/identity/ClientIdentity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,598:1\n335#1:600\n339#1:601\n1#2:599\n1747#3,3:602\n12271#4,2:605\n12474#4,2:607\n13309#4,2:609\n*S KotlinDebug\n*F\n+ 1 ClientIdentity.kt\ncom/google/android/gms/libs/identity/ClientIdentity\n*L\n255#1:600\n264#1:601\n278#1:602,3\n284#1:605,2\n292#1:607,2\n301#1:609,2\n*E\n"})
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0018\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001eBS\b\u0017\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\f\u0010\rBc\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\f\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010 \u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b \u0010!J\u0015\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b%\u0010&J+\u0010)\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040'\"\u00020\u0004H\u0007¢\u0006\u0004\b)\u0010*J+\u0010+\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040'\"\u00020\u0004H\u0007¢\u0006\u0004\b+\u0010*JC\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0004\b,\u0010-J+\u0010.\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\"2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040'\"\u00020\u0004H\u0007¢\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b2\u00101J+\u00103\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\"2\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040'\"\u00020\u0004H\u0007¢\u0006\u0004\b3\u0010/J\u0017\u00104\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\"H\u0007¢\u0006\u0004\b4\u00101J\u000f\u00105\u001a\u00020\u0000H\u0007¢\u0006\u0004\b5\u00106J\u0011\u00107\u001a\u0004\u0018\u00010\u0000H\u0007¢\u0006\u0004\b7\u00106J-\u0010:\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\"2\b\u00108\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\tH\u0007¢\u0006\u0004\b=\u0010>J\u001f\u0010=\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u00042\u0006\u0010A\u001a\u00020@H\u0007¢\u0006\u0004\b=\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0018\u0010E\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00048GX\u0004¢\u0006\f\n\u0004\b\u0006\u0010G\u001a\u0004\bH\u0010\u0014R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8GX\u0004¢\u0006\f\n\u0004\b\n\u0010I\u001a\u0004\bJ\u0010KR,\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\bM\u0012\b\b?\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00020L8G¢\u0006\u0006\u001a\u0004\bN\u0010OR,\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\bM\u0012\b\b?\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00100L8G¢\u0006\u0006\u001a\u0004\bP\u0010OR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\u00008\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010QR-\u0010R\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\bM\u0012\b\b?\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00170L8Ç\u0002¢\u0006\u0006\u001a\u0004\bR\u0010OR\u0011\u0010S\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0011\u0010U\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\bU\u0010TR\u0011\u0010V\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\bV\u0010TR\u0011\u0010W\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\bW\u0010TR\u0011\u0010X\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\bX\u0010TR-\u0010Y\u001a\u001d\u0012\u0013\u0012\u00110\"¢\u0006\f\bM\u0012\b\b?\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00170L8Ç\u0002¢\u0006\u0006\u001a\u0004\bY\u0010OR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00048GX\u0004¢\u0006\f\n\u0004\b\u0007\u0010G\u001a\u0004\bZ\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048GX\u0004¢\u0006\f\n\u0004\b\u0005\u0010G\u001a\u0004\b[\u0010\u0014R\u0017\u0010\u000e\u001a\u00020\u00028G¢\u0006\f\n\u0004\b\u000e\u0010\\\u001a\u0004\b]\u0010\u001bR\u0011\u0010_\u001a\u00020\u00008G¢\u0006\u0006\u001a\u0004\b^\u00106R\u001a\u0010\u0003\u001a\u00020\u00028GX\u0004¢\u0006\f\n\u0004\b\u0003\u0010\\\u001a\u0004\b`\u0010\u001bR\u0011\u0010d\u001a\u00020a8G¢\u0006\u0006\u001a\u0004\bb\u0010c¨\u0006f"}, d2 = {"Lcom/google/android/gms/libs/identity/ClientIdentity;", "Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;", "", "uid", "", "packageName", "attributionTag", "listenerId", "", "Lcom/google/android/gms/common/Feature;", "clientFeatures", "impersonator", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/google/android/gms/libs/identity/ClientIdentity;)V", "pid", "clientSdkVersion", "Lcom/google/android/gms/libs/identity/ClientType;", "clientType", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Lcom/google/android/gms/libs/identity/ClientType;Lcom/google/android/gms/libs/identity/ClientIdentity;)V", "toString", "()Ljava/lang/String;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Landroid/os/Parcel;", "dest", "flags", "", "writeToParcel", "(Landroid/os/Parcel;I)V", "Landroid/content/Context;", "context", "Lcom/google/android/gms/libs/identity/Impersonator;", "asImpersonator", "(Landroid/content/Context;)Lcom/google/android/gms/libs/identity/Impersonator;", "", "permissions", "checkAnyPermissions", "(Landroid/content/Context;[Ljava/lang/String;)Z", "checkPermissions", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/google/android/gms/libs/identity/Impersonator;)Lcom/google/android/gms/libs/identity/ClientIdentity;", "enforceAnyPermissions", "(Landroid/content/Context;[Ljava/lang/String;)V", "enforceFirstParty", "(Landroid/content/Context;)V", "enforcePackageName", "enforcePermissions", "enforceZeroParty", "forAggregation", "()Lcom/google/android/gms/libs/identity/ClientIdentity;", "getImpersonator", "impersonatee", "impersonateeListenerId", "impersonate", "(Landroid/content/Context;Lcom/google/android/gms/libs/identity/ClientIdentity;Ljava/lang/String;)Lcom/google/android/gms/libs/identity/ClientIdentity;", "feature", "supportsFeature", "(Lcom/google/android/gms/common/Feature;)Z", "name", "", "version", "(Ljava/lang/String;J)Z", "_clientSdkVersion", "Ljava/lang/Integer;", "_clientType", "Lcom/google/android/gms/libs/identity/ClientType;", "Ljava/lang/String;", "getAttributionTag", "Ljava/util/List;", "getClientFeatures", "()Ljava/util/List;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "getClientSdkVersion", "()Lkotlin/jvm/functions/Function1;", "getClientType", "Lcom/google/android/gms/libs/identity/ClientIdentity;", "isFirstParty", "isImpersonatedIdentity", "()Z", "isMyProcess", "isMyUid", "isMyUser", "isSystemServer", "isZeroParty", "getListenerId", "getPackageName", "I", "getPid", "getRealIdentity", "realIdentity", "getUid", "Landroid/os/UserHandle;", "getUserHandle", "()Landroid/os/UserHandle;", "userHandle", "Companion", "java.com.google.android.gms.libs.identity_identity"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SafeParcelable.Class(creator = "ClientIdentityCreator")
@SafeParcelable.Reserved({2, 5})
public final class zze extends AbstractSafeParcelable {
    @NotNull
    @JvmField
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    @NotNull
    public static final zzd zza = new zzd((DefaultConstructorMarker) null);
    @SafeParcelable.Field(getter = "getUid", id = 1)
    private final int zzb;
    @NotNull
    @SafeParcelable.Field(getter = "getPackageName", id = 3)
    private final String zzc;
    @SafeParcelable.Field(getter = "getAttributionTag", id = 4)
    @Nullable
    private final String zzd;
    @SafeParcelable.Field(getter = "getListenerId", id = 6)
    @Nullable
    private final String zze;
    @NotNull
    @SafeParcelable.Field(getter = "getClientFeatures", id = 8)
    private final List zzf;
    @SafeParcelable.Field(getter = "getImpersonator", id = 7)
    @Nullable
    private final zze zzg;

    static {
        Process.myUid();
        Process.myPid();
    }

    @SafeParcelable.Constructor
    @Deprecated(level = DeprecationLevel.ERROR, message = "only for Parcelable.Creator")
    public zze(@SafeParcelable.Param(id = 1) int i3, @NotNull @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable String str2, @SafeParcelable.Param(id = 6) @Nullable String str3, @SafeParcelable.Param(id = 8) @Nullable List list, @SafeParcelable.Param(id = 7) @Nullable zze zze2) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        if (zze2 == null || !zze2.zza()) {
            this.zzb = i3;
            this.zzc = str;
            this.zzd = str2;
            List list2 = null;
            this.zze = str3 == null ? zze2 != null ? zze2.zze : null : str3;
            if (list == null) {
                list = zze2 != null ? zze2.zzf : list2;
                if (list == null) {
                    list = zzex.zzi();
                    Intrinsics.checkNotNullExpressionValue(list, "of(...)");
                }
            }
            Intrinsics.checkNotNullParameter(list, "<this>");
            zzex zzj = zzex.zzj(list);
            Intrinsics.checkNotNullExpressionValue(zzj, "copyOf(...)");
            this.zzf = zzj;
            this.zzg = zze2;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zze) {
            zze zze2 = (zze) obj;
            return this.zzb == zze2.zzb && Intrinsics.areEqual((Object) this.zzc, (Object) zze2.zzc) && Intrinsics.areEqual((Object) this.zzd, (Object) zze2.zzd) && Intrinsics.areEqual((Object) this.zze, (Object) zze2.zze) && Intrinsics.areEqual((Object) this.zzg, (Object) zze2.zzg) && Intrinsics.areEqual((Object) this.zzf, (Object) zze2.zzf);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze, this.zzg});
    }

    @NotNull
    public final String toString() {
        int length = this.zzc.length() + 18;
        String str = this.zzd;
        int i3 = 0;
        StringBuilder sb = new StringBuilder(length + (str != null ? str.length() : 0));
        sb.append(this.zzb);
        sb.append("/");
        sb.append(this.zzc);
        String str2 = this.zzd;
        if (str2 != null) {
            sb.append("[");
            if (StringsKt__StringsJVMKt.startsWith$default(str2, this.zzc, false, 2, (Object) null)) {
                sb.append(str2, this.zzc.length(), str2.length());
            } else {
                sb.append(str2);
            }
            sb.append("]");
        }
        if (this.zze != null) {
            sb.append("/");
            String str3 = this.zze;
            if (str3 != null) {
                i3 = str3.hashCode();
            }
            sb.append(Integer.toHexString(i3));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final void writeToParcel(@NotNull Parcel parcel, int i3) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        int i4 = this.zzb;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i4);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i3, false);
        SafeParcelWriter.writeTypedList(parcel, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final boolean zza() {
        return this.zzg != null;
    }
}
