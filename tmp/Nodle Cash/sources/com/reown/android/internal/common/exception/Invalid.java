package com.reown.android.internal.common.exception;

import A.a;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.type.Error;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/internal/common/exception/Invalid;", "Lcom/reown/android/internal/common/model/type/Error;", "<init>", "()V", "MethodUnsupported", "RequestExpired", "Lcom/reown/android/internal/common/exception/Invalid$MethodUnsupported;", "Lcom/reown/android/internal/common/exception/Invalid$RequestExpired;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Invalid implements Error {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/android/internal/common/exception/Invalid$MethodUnsupported;", "Lcom/reown/android/internal/common/exception/Invalid;", "method", "", "<init>", "(Ljava/lang/String;)V", "getMethod", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MethodUnsupported extends Invalid {
        private final int code = CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB;
        @NotNull
        private final String message;
        @NotNull
        private final String method;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MethodUnsupported(@NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            this.method = str;
            this.message = c.a("Unsupported Method Requested: ", str);
        }

        public static /* synthetic */ MethodUnsupported copy$default(MethodUnsupported methodUnsupported, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = methodUnsupported.method;
            }
            return methodUnsupported.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.method;
        }

        @NotNull
        public final MethodUnsupported copy(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
            return new MethodUnsupported(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MethodUnsupported) && Intrinsics.areEqual((Object) this.method, (Object) ((MethodUnsupported) obj).method);
        }

        public int getCode() {
            return this.code;
        }

        @NotNull
        public String getMessage() {
            return this.message;
        }

        @NotNull
        public final String getMethod() {
            return this.method;
        }

        public int hashCode() {
            return this.method.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("MethodUnsupported(method=", this.method, ")");
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/reown/android/internal/common/exception/Invalid$RequestExpired;", "Lcom/reown/android/internal/common/exception/Invalid;", "<init>", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RequestExpired extends Invalid {
        @NotNull
        public static final RequestExpired INSTANCE = new RequestExpired();
        private static final int code = 8000;
        @NotNull
        private static final String message = "Request expired";

        private RequestExpired() {
            super((DefaultConstructorMarker) null);
        }

        public int getCode() {
            return code;
        }

        @NotNull
        public String getMessage() {
            return message;
        }
    }

    public /* synthetic */ Invalid(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Invalid() {
    }
}
