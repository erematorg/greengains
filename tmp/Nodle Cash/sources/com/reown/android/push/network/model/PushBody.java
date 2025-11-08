package com.reown.android.push.network.model;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import com.reown.android.internal.common.di.CoreNetworkModuleKt;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ8\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/reown/android/push/network/model/PushBody;", "", "clientId", "", "token", "type", "enableEncrypted", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getClientId", "()Ljava/lang/String;", "getToken", "getType", "getEnableEncrypted", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/push/network/model/PushBody;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushBody {
    @NotNull
    private final String clientId;
    @Nullable
    private final Boolean enableEncrypted;
    @NotNull
    private final String token;
    @NotNull
    private final String type;

    public PushBody(@NotNull @Json(name = "client_id") String str, @NotNull @Json(name = "token") String str2, @NotNull @Json(name = "type") String str3, @Nullable @Json(name = "always_raw") Boolean bool) {
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(str2, SchemaSymbols.ATTVAL_TOKEN);
        Intrinsics.checkNotNullParameter(str3, "type");
        this.clientId = str;
        this.token = str2;
        this.type = str3;
        this.enableEncrypted = bool;
    }

    public static /* synthetic */ PushBody copy$default(PushBody pushBody, String str, String str2, String str3, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = pushBody.clientId;
        }
        if ((i3 & 2) != 0) {
            str2 = pushBody.token;
        }
        if ((i3 & 4) != 0) {
            str3 = pushBody.type;
        }
        if ((i3 & 8) != 0) {
            bool = pushBody.enableEncrypted;
        }
        return pushBody.copy(str, str2, str3, bool);
    }

    @NotNull
    public final String component1() {
        return this.clientId;
    }

    @NotNull
    public final String component2() {
        return this.token;
    }

    @NotNull
    public final String component3() {
        return this.type;
    }

    @Nullable
    public final Boolean component4() {
        return this.enableEncrypted;
    }

    @NotNull
    public final PushBody copy(@NotNull @Json(name = "client_id") String str, @NotNull @Json(name = "token") String str2, @NotNull @Json(name = "type") String str3, @Nullable @Json(name = "always_raw") Boolean bool) {
        Intrinsics.checkNotNullParameter(str, CoreNetworkModuleKt.KEY_CLIENT_ID);
        Intrinsics.checkNotNullParameter(str2, SchemaSymbols.ATTVAL_TOKEN);
        Intrinsics.checkNotNullParameter(str3, "type");
        return new PushBody(str, str2, str3, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PushBody)) {
            return false;
        }
        PushBody pushBody = (PushBody) obj;
        return Intrinsics.areEqual((Object) this.clientId, (Object) pushBody.clientId) && Intrinsics.areEqual((Object) this.token, (Object) pushBody.token) && Intrinsics.areEqual((Object) this.type, (Object) pushBody.type) && Intrinsics.areEqual((Object) this.enableEncrypted, (Object) pushBody.enableEncrypted);
    }

    @NotNull
    public final String getClientId() {
        return this.clientId;
    }

    @Nullable
    public final Boolean getEnableEncrypted() {
        return this.enableEncrypted;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int i3 = a.i(this.type, a.i(this.token, this.clientId.hashCode() * 31, 31), 31);
        Boolean bool = this.enableEncrypted;
        return i3 + (bool == null ? 0 : bool.hashCode());
    }

    @NotNull
    public String toString() {
        String str = this.clientId;
        String str2 = this.token;
        String str3 = this.type;
        Boolean bool = this.enableEncrypted;
        StringBuilder l2 = C0118y.l("PushBody(clientId=", str, ", token=", str2, ", type=");
        l2.append(str3);
        l2.append(", enableEncrypted=");
        l2.append(bool);
        l2.append(")");
        return l2.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PushBody(String str, String str2, String str3, Boolean bool, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i3 & 4) != 0 ? "fcm" : str3, bool);
    }
}
