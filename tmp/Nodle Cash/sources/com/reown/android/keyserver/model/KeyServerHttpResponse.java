package com.reown.android.keyserver.model;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.app.NotificationCompat;
import com.reown.android.keyserver.model.KeyServerResponse;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0010*\u0004\b\u0000\u0010\u00012\u00020\u0002:\b\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0001\u0006\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "T", "", "<init>", "()V", "status", "", "getStatus", "()Ljava/lang/String;", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "getValue", "()Ljava/lang/Object;", "Companion", "RegisterInvite", "UnregisterInvite", "ResolveInvite", "RegisterIdentity", "UnregisterIdentity", "ResolveIdentity", "Error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveInvite;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterInvite;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class KeyServerHttpResponse<T> {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    public static final String SUCCESS_STATUS = "SUCCESS";

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Companion;", "", "<init>", "()V", "SUCCESS_STATUS", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "", "message", "", "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error {
        @NotNull
        private final String message;
        @NotNull
        private final String name;

        public Error(@NotNull @Json(name = "message") String str, @NotNull @Json(name = "name") String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str2, "name");
            this.message = str;
            this.name = str2;
        }

        public static /* synthetic */ Error copy$default(Error error, String str, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = error.message;
            }
            if ((i3 & 2) != 0) {
                str2 = error.name;
            }
            return error.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.message;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final Error copy(@NotNull @Json(name = "message") String str, @NotNull @Json(name = "name") String str2) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str2, "name");
            return new Error(str, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Error)) {
                return false;
            }
            Error error = (Error) obj;
            return Intrinsics.areEqual((Object) this.message, (Object) error.message) && Intrinsics.areEqual((Object) this.name, (Object) error.name);
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return this.name.hashCode() + (this.message.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return C0118y.g("Error(message=", this.message, ", name=", this.name, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "", "status", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisterIdentity extends KeyServerHttpResponse<String> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @Nullable
        private final String value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RegisterIdentity(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            this.status = str;
            this.error = error2;
            this.value = str2;
        }

        public static /* synthetic */ RegisterIdentity copy$default(RegisterIdentity registerIdentity, String str, Error error2, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = registerIdentity.status;
            }
            if ((i3 & 2) != 0) {
                error2 = registerIdentity.error;
            }
            if ((i3 & 4) != 0) {
                str2 = registerIdentity.value;
            }
            return registerIdentity.copy(str, error2, str2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final RegisterIdentity copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            return new RegisterIdentity(str, error2, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RegisterIdentity)) {
                return false;
            }
            RegisterIdentity registerIdentity = (RegisterIdentity) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) registerIdentity.status) && Intrinsics.areEqual((Object) this.error, (Object) registerIdentity.error) && Intrinsics.areEqual((Object) this.value, (Object) registerIdentity.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            int i3 = 0;
            int hashCode2 = (hashCode + (error2 == null ? 0 : error2.hashCode())) * 31;
            String str = this.value;
            if (str != null) {
                i3 = str.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            String str2 = this.value;
            StringBuilder sb = new StringBuilder("RegisterIdentity(status=");
            sb.append(str);
            sb.append(", error=");
            sb.append(error2);
            sb.append(", value=");
            return a.n(sb, str2, ")");
        }

        @Nullable
        public String getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$RegisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "", "status", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisterInvite extends KeyServerHttpResponse<String> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @Nullable
        private final String value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RegisterInvite(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            this.status = str;
            this.error = error2;
            this.value = str2;
        }

        public static /* synthetic */ RegisterInvite copy$default(RegisterInvite registerInvite, String str, Error error2, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = registerInvite.status;
            }
            if ((i3 & 2) != 0) {
                error2 = registerInvite.error;
            }
            if ((i3 & 4) != 0) {
                str2 = registerInvite.value;
            }
            return registerInvite.copy(str, error2, str2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final RegisterInvite copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            return new RegisterInvite(str, error2, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RegisterInvite)) {
                return false;
            }
            RegisterInvite registerInvite = (RegisterInvite) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) registerInvite.status) && Intrinsics.areEqual((Object) this.error, (Object) registerInvite.error) && Intrinsics.areEqual((Object) this.value, (Object) registerInvite.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            int i3 = 0;
            int hashCode2 = (hashCode + (error2 == null ? 0 : error2.hashCode())) * 31;
            String str = this.value;
            if (str != null) {
                i3 = str.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            String str2 = this.value;
            StringBuilder sb = new StringBuilder("RegisterInvite(status=");
            sb.append(str);
            sb.append(", error=");
            sb.append(error2);
            sb.append(", value=");
            return a.n(sb, str2, ")");
        }

        @Nullable
        public String getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0002HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00042\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveIdentity;", "status", "", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveIdentity;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "()Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveIdentity;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResolveIdentity extends KeyServerHttpResponse<KeyServerResponse.ResolveIdentity> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @NotNull
        private final KeyServerResponse.ResolveIdentity value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResolveIdentity(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @NotNull @Json(name = "value") KeyServerResponse.ResolveIdentity resolveIdentity) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            Intrinsics.checkNotNullParameter(resolveIdentity, "value");
            this.status = str;
            this.error = error2;
            this.value = resolveIdentity;
        }

        public static /* synthetic */ ResolveIdentity copy$default(ResolveIdentity resolveIdentity, String str, Error error2, KeyServerResponse.ResolveIdentity resolveIdentity2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = resolveIdentity.status;
            }
            if ((i3 & 2) != 0) {
                error2 = resolveIdentity.error;
            }
            if ((i3 & 4) != 0) {
                resolveIdentity2 = resolveIdentity.value;
            }
            return resolveIdentity.copy(str, error2, resolveIdentity2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @NotNull
        public final KeyServerResponse.ResolveIdentity component3() {
            return this.value;
        }

        @NotNull
        public final ResolveIdentity copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @NotNull @Json(name = "value") KeyServerResponse.ResolveIdentity resolveIdentity) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            Intrinsics.checkNotNullParameter(resolveIdentity, "value");
            return new ResolveIdentity(str, error2, resolveIdentity);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResolveIdentity)) {
                return false;
            }
            ResolveIdentity resolveIdentity = (ResolveIdentity) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) resolveIdentity.status) && Intrinsics.areEqual((Object) this.error, (Object) resolveIdentity.error) && Intrinsics.areEqual((Object) this.value, (Object) resolveIdentity.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            return this.value.hashCode() + ((hashCode + (error2 == null ? 0 : error2.hashCode())) * 31);
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            KeyServerResponse.ResolveIdentity resolveIdentity = this.value;
            return "ResolveIdentity(status=" + str + ", error=" + error2 + ", value=" + resolveIdentity + ")";
        }

        @NotNull
        public KeyServerResponse.ResolveIdentity getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0002HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00042\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$ResolveInvite;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveInvite;", "status", "", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveInvite;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "()Lcom/reown/android/keyserver/model/KeyServerResponse$ResolveInvite;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResolveInvite extends KeyServerHttpResponse<KeyServerResponse.ResolveInvite> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @NotNull
        private final KeyServerResponse.ResolveInvite value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ResolveInvite(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @NotNull @Json(name = "value") KeyServerResponse.ResolveInvite resolveInvite) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            Intrinsics.checkNotNullParameter(resolveInvite, "value");
            this.status = str;
            this.error = error2;
            this.value = resolveInvite;
        }

        public static /* synthetic */ ResolveInvite copy$default(ResolveInvite resolveInvite, String str, Error error2, KeyServerResponse.ResolveInvite resolveInvite2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = resolveInvite.status;
            }
            if ((i3 & 2) != 0) {
                error2 = resolveInvite.error;
            }
            if ((i3 & 4) != 0) {
                resolveInvite2 = resolveInvite.value;
            }
            return resolveInvite.copy(str, error2, resolveInvite2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @NotNull
        public final KeyServerResponse.ResolveInvite component3() {
            return this.value;
        }

        @NotNull
        public final ResolveInvite copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @NotNull @Json(name = "value") KeyServerResponse.ResolveInvite resolveInvite) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            Intrinsics.checkNotNullParameter(resolveInvite, "value");
            return new ResolveInvite(str, error2, resolveInvite);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResolveInvite)) {
                return false;
            }
            ResolveInvite resolveInvite = (ResolveInvite) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) resolveInvite.status) && Intrinsics.areEqual((Object) this.error, (Object) resolveInvite.error) && Intrinsics.areEqual((Object) this.value, (Object) resolveInvite.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            return this.value.hashCode() + ((hashCode + (error2 == null ? 0 : error2.hashCode())) * 31);
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            KeyServerResponse.ResolveInvite resolveInvite = this.value;
            return "ResolveInvite(status=" + str + ", error=" + error2 + ", value=" + resolveInvite + ")";
        }

        @NotNull
        public KeyServerResponse.ResolveInvite getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterIdentity;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "", "status", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregisterIdentity extends KeyServerHttpResponse<String> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @Nullable
        private final String value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnregisterIdentity(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            this.status = str;
            this.error = error2;
            this.value = str2;
        }

        public static /* synthetic */ UnregisterIdentity copy$default(UnregisterIdentity unregisterIdentity, String str, Error error2, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = unregisterIdentity.status;
            }
            if ((i3 & 2) != 0) {
                error2 = unregisterIdentity.error;
            }
            if ((i3 & 4) != 0) {
                str2 = unregisterIdentity.value;
            }
            return unregisterIdentity.copy(str, error2, str2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final UnregisterIdentity copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            return new UnregisterIdentity(str, error2, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UnregisterIdentity)) {
                return false;
            }
            UnregisterIdentity unregisterIdentity = (UnregisterIdentity) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) unregisterIdentity.status) && Intrinsics.areEqual((Object) this.error, (Object) unregisterIdentity.error) && Intrinsics.areEqual((Object) this.value, (Object) unregisterIdentity.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            int i3 = 0;
            int hashCode2 = (hashCode + (error2 == null ? 0 : error2.hashCode())) * 31;
            String str = this.value;
            if (str != null) {
                i3 = str.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            String str2 = this.value;
            StringBuilder sb = new StringBuilder("UnregisterIdentity(status=");
            sb.append(str);
            sb.append(", error=");
            sb.append(error2);
            sb.append(", value=");
            return a.n(sb, str2, ")");
        }

        @Nullable
        public String getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/android/keyserver/model/KeyServerHttpResponse$UnregisterInvite;", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse;", "", "status", "error", "Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "value", "<init>", "(Ljava/lang/String;Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getError", "()Lcom/reown/android/keyserver/model/KeyServerHttpResponse$Error;", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnregisterInvite extends KeyServerHttpResponse<String> {
        @Nullable
        private final Error error;
        @NotNull
        private final String status;
        @Nullable
        private final String value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnregisterInvite(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            this.status = str;
            this.error = error2;
            this.value = str2;
        }

        public static /* synthetic */ UnregisterInvite copy$default(UnregisterInvite unregisterInvite, String str, Error error2, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = unregisterInvite.status;
            }
            if ((i3 & 2) != 0) {
                error2 = unregisterInvite.error;
            }
            if ((i3 & 4) != 0) {
                str2 = unregisterInvite.value;
            }
            return unregisterInvite.copy(str, error2, str2);
        }

        @NotNull
        public final String component1() {
            return this.status;
        }

        @Nullable
        public final Error component2() {
            return this.error;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final UnregisterInvite copy(@NotNull @Json(name = "status") String str, @Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str2) {
            Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_STATUS);
            return new UnregisterInvite(str, error2, str2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UnregisterInvite)) {
                return false;
            }
            UnregisterInvite unregisterInvite = (UnregisterInvite) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) unregisterInvite.status) && Intrinsics.areEqual((Object) this.error, (Object) unregisterInvite.error) && Intrinsics.areEqual((Object) this.value, (Object) unregisterInvite.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        @NotNull
        public String getStatus() {
            return this.status;
        }

        public int hashCode() {
            int hashCode = this.status.hashCode() * 31;
            Error error2 = this.error;
            int i3 = 0;
            int hashCode2 = (hashCode + (error2 == null ? 0 : error2.hashCode())) * 31;
            String str = this.value;
            if (str != null) {
                i3 = str.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.status;
            Error error2 = this.error;
            String str2 = this.value;
            StringBuilder sb = new StringBuilder("UnregisterInvite(status=");
            sb.append(str);
            sb.append(", error=");
            sb.append(error2);
            sb.append(", value=");
            return a.n(sb, str2, ")");
        }

        @Nullable
        public String getValue() {
            return this.value;
        }
    }

    public /* synthetic */ KeyServerHttpResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Nullable
    public abstract Error getError();

    @NotNull
    public abstract String getStatus();

    @Nullable
    public abstract T getValue();

    private KeyServerHttpResponse() {
    }
}
