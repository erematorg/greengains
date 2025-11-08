package com.reown.android.internal.common.exception;

import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.reown.android.internal.common.model.type.Error;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/android/internal/common/exception/Reason;", "Lcom/reown/android/internal/common/model/type/Error;", "<init>", "()V", "UserDisconnected", "Lcom/reown/android/internal/common/exception/Reason$UserDisconnected;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Reason implements Error {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/reown/android/internal/common/exception/Reason$UserDisconnected;", "Lcom/reown/android/internal/common/exception/Reason;", "<init>", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserDisconnected extends Reason {
        @NotNull
        public static final UserDisconnected INSTANCE = new UserDisconnected();
        private static final int code = PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
        @NotNull
        private static final String message = MessagesKt.DISCONNECT_MESSAGE;

        private UserDisconnected() {
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

    public /* synthetic */ Reason(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Reason() {
    }
}
