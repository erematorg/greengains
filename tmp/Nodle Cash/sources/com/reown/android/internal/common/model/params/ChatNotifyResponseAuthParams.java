package com.reown.android.internal.common.model.params;

import A.a;
import com.reown.android.internal.common.model.type.ClientParams;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/params/ChatNotifyResponseAuthParams;", "", "ResponseAuth", "Auth", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ChatNotifyResponseAuthParams {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/ChatNotifyResponseAuthParams$Auth;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "auth", "", "<init>", "(Ljava/lang/String;)V", "getAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Auth implements ClientParams {
        @NotNull
        private final String auth;

        public Auth(@NotNull @Json(name = "auth") String str) {
            Intrinsics.checkNotNullParameter(str, "auth");
            this.auth = str;
        }

        public static /* synthetic */ Auth copy$default(Auth auth2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = auth2.auth;
            }
            return auth2.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.auth;
        }

        @NotNull
        public final Auth copy(@NotNull @Json(name = "auth") String str) {
            Intrinsics.checkNotNullParameter(str, "auth");
            return new Auth(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Auth) && Intrinsics.areEqual((Object) this.auth, (Object) ((Auth) obj).auth);
        }

        @NotNull
        public final String getAuth() {
            return this.auth;
        }

        public int hashCode() {
            return this.auth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("Auth(auth=", this.auth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/ChatNotifyResponseAuthParams$ResponseAuth;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "responseAuth", "", "<init>", "(Ljava/lang/String;)V", "getResponseAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResponseAuth implements ClientParams {
        @NotNull
        private final String responseAuth;

        public ResponseAuth(@NotNull @Json(name = "responseAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "responseAuth");
            this.responseAuth = str;
        }

        public static /* synthetic */ ResponseAuth copy$default(ResponseAuth responseAuth2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = responseAuth2.responseAuth;
            }
            return responseAuth2.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.responseAuth;
        }

        @NotNull
        public final ResponseAuth copy(@NotNull @Json(name = "responseAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "responseAuth");
            return new ResponseAuth(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ResponseAuth) && Intrinsics.areEqual((Object) this.responseAuth, (Object) ((ResponseAuth) obj).responseAuth);
        }

        @NotNull
        public final String getResponseAuth() {
            return this.responseAuth;
        }

        public int hashCode() {
            return this.responseAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("ResponseAuth(responseAuth=", this.responseAuth, ")");
        }
    }
}
