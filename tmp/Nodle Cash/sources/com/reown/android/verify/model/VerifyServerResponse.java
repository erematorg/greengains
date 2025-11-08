package com.reown.android.verify.model;

import androidx.camera.camera2.internal.C0118y;
import com.reown.android.push.notifications.PushMessagingService;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\f\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerResponse;", "T", "", "<init>", "()V", "error", "Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "getError", "()Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "value", "getValue", "()Ljava/lang/Object;", "RegisterAttestation", "ResolveAttestation", "Error", "Lcom/reown/android/verify/model/VerifyServerResponse$RegisterAttestation;", "Lcom/reown/android/verify/model/VerifyServerResponse$ResolveAttestation;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class VerifyServerResponse<T> {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "", "message", "", "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0002HÖ\u0001R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerResponse$RegisterAttestation;", "Lcom/reown/android/verify/model/VerifyServerResponse;", "", "error", "Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "value", "<init>", "(Lcom/reown/android/verify/model/VerifyServerResponse$Error;Ljava/lang/String;)V", "getError", "()Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "getValue", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RegisterAttestation extends VerifyServerResponse<String> {
        @Nullable
        private final Error error;
        @Nullable
        private final String value;

        public RegisterAttestation(@Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str) {
            super((DefaultConstructorMarker) null);
            this.error = error2;
            this.value = str;
        }

        public static /* synthetic */ RegisterAttestation copy$default(RegisterAttestation registerAttestation, Error error2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                error2 = registerAttestation.error;
            }
            if ((i3 & 2) != 0) {
                str = registerAttestation.value;
            }
            return registerAttestation.copy(error2, str);
        }

        @Nullable
        public final Error component1() {
            return this.error;
        }

        @Nullable
        public final String component2() {
            return this.value;
        }

        @NotNull
        public final RegisterAttestation copy(@Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") String str) {
            return new RegisterAttestation(error2, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RegisterAttestation)) {
                return false;
            }
            RegisterAttestation registerAttestation = (RegisterAttestation) obj;
            return Intrinsics.areEqual((Object) this.error, (Object) registerAttestation.error) && Intrinsics.areEqual((Object) this.value, (Object) registerAttestation.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        public int hashCode() {
            Error error2 = this.error;
            int i3 = 0;
            int hashCode = (error2 == null ? 0 : error2.hashCode()) * 31;
            String str = this.value;
            if (str != null) {
                i3 = str.hashCode();
            }
            return hashCode + i3;
        }

        @NotNull
        public String toString() {
            Error error2 = this.error;
            String str = this.value;
            return "RegisterAttestation(error=" + error2 + ", value=" + str + ")";
        }

        @Nullable
        public String getValue() {
            return this.value;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0002HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/reown/android/verify/model/VerifyServerResponse$ResolveAttestation;", "Lcom/reown/android/verify/model/VerifyServerResponse;", "Lcom/reown/android/verify/model/Origin;", "error", "Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "value", "<init>", "(Lcom/reown/android/verify/model/VerifyServerResponse$Error;Lcom/reown/android/verify/model/Origin;)V", "getError", "()Lcom/reown/android/verify/model/VerifyServerResponse$Error;", "getValue", "()Lcom/reown/android/verify/model/Origin;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResolveAttestation extends VerifyServerResponse<Origin> {
        @Nullable
        private final Error error;
        @Nullable
        private final Origin value;

        public ResolveAttestation(@Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") Origin origin) {
            super((DefaultConstructorMarker) null);
            this.error = error2;
            this.value = origin;
        }

        public static /* synthetic */ ResolveAttestation copy$default(ResolveAttestation resolveAttestation, Error error2, Origin origin, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                error2 = resolveAttestation.error;
            }
            if ((i3 & 2) != 0) {
                origin = resolveAttestation.value;
            }
            return resolveAttestation.copy(error2, origin);
        }

        @Nullable
        public final Error component1() {
            return this.error;
        }

        @Nullable
        public final Origin component2() {
            return this.value;
        }

        @NotNull
        public final ResolveAttestation copy(@Nullable @Json(name = "error") Error error2, @Nullable @Json(name = "value") Origin origin) {
            return new ResolveAttestation(error2, origin);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ResolveAttestation)) {
                return false;
            }
            ResolveAttestation resolveAttestation = (ResolveAttestation) obj;
            return Intrinsics.areEqual((Object) this.error, (Object) resolveAttestation.error) && Intrinsics.areEqual((Object) this.value, (Object) resolveAttestation.value);
        }

        @Nullable
        public Error getError() {
            return this.error;
        }

        public int hashCode() {
            Error error2 = this.error;
            int i3 = 0;
            int hashCode = (error2 == null ? 0 : error2.hashCode()) * 31;
            Origin origin = this.value;
            if (origin != null) {
                i3 = origin.hashCode();
            }
            return hashCode + i3;
        }

        @NotNull
        public String toString() {
            Error error2 = this.error;
            Origin origin = this.value;
            return "ResolveAttestation(error=" + error2 + ", value=" + origin + ")";
        }

        @Nullable
        public Origin getValue() {
            return this.value;
        }
    }

    public /* synthetic */ VerifyServerResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Nullable
    public abstract Error getError();

    @Nullable
    public abstract T getValue();

    private VerifyServerResponse() {
    }
}
