package com.reown.sign.engine.model;

import com.reown.sign.common.exceptions.MessagesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\r\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\r\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !¨\u0006\""}, d2 = {"Lcom/reown/sign/engine/model/ValidationError;", "", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "EmptyNamespaces", "UnsupportedNamespaceKey", "UnsupportedChains", "UserRejected", "UserRejectedChains", "UserRejectedMethods", "UserRejectedEvents", "UnauthorizedMethod", "UnauthorizedEvent", "InvalidSessionRequest", "InvalidEvent", "InvalidExtendRequest", "InvalidSessionProperties", "Lcom/reown/sign/engine/model/ValidationError$EmptyNamespaces;", "Lcom/reown/sign/engine/model/ValidationError$InvalidEvent;", "Lcom/reown/sign/engine/model/ValidationError$InvalidExtendRequest;", "Lcom/reown/sign/engine/model/ValidationError$InvalidSessionProperties;", "Lcom/reown/sign/engine/model/ValidationError$InvalidSessionRequest;", "Lcom/reown/sign/engine/model/ValidationError$UnauthorizedEvent;", "Lcom/reown/sign/engine/model/ValidationError$UnauthorizedMethod;", "Lcom/reown/sign/engine/model/ValidationError$UnsupportedChains;", "Lcom/reown/sign/engine/model/ValidationError$UnsupportedNamespaceKey;", "Lcom/reown/sign/engine/model/ValidationError$UserRejected;", "Lcom/reown/sign/engine/model/ValidationError$UserRejectedChains;", "Lcom/reown/sign/engine/model/ValidationError$UserRejectedEvents;", "Lcom/reown/sign/engine/model/ValidationError$UserRejectedMethods;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ValidationError {
    @NotNull
    private final String message;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$EmptyNamespaces;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class EmptyNamespaces extends ValidationError {
        @NotNull
        public static final EmptyNamespaces INSTANCE = new EmptyNamespaces();

        private EmptyNamespaces() {
            super(MessagesKt.EMPTY_NAMESPACES_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$InvalidEvent;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class InvalidEvent extends ValidationError {
        @NotNull
        public static final InvalidEvent INSTANCE = new InvalidEvent();

        private InvalidEvent() {
            super(MessagesKt.INVALID_EVENT_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$InvalidExtendRequest;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class InvalidExtendRequest extends ValidationError {
        @NotNull
        public static final InvalidExtendRequest INSTANCE = new InvalidExtendRequest();

        private InvalidExtendRequest() {
            super(MessagesKt.INVALID_EXTEND_TIME, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$InvalidSessionProperties;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class InvalidSessionProperties extends ValidationError {
        @NotNull
        public static final InvalidSessionProperties INSTANCE = new InvalidSessionProperties();

        private InvalidSessionProperties() {
            super(MessagesKt.INVALID_SESSION_PROPERTIES, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$InvalidSessionRequest;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class InvalidSessionRequest extends ValidationError {
        @NotNull
        public static final InvalidSessionRequest INSTANCE = new InvalidSessionRequest();

        private InvalidSessionRequest() {
            super(MessagesKt.INVALID_REQUEST_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UnauthorizedEvent;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnauthorizedEvent extends ValidationError {
        @NotNull
        public static final UnauthorizedEvent INSTANCE = new UnauthorizedEvent();

        private UnauthorizedEvent() {
            super(MessagesKt.UNAUTHORIZED_EVENT_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UnauthorizedMethod;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnauthorizedMethod extends ValidationError {
        @NotNull
        public static final UnauthorizedMethod INSTANCE = new UnauthorizedMethod();

        private UnauthorizedMethod() {
            super(MessagesKt.UNAUTHORIZED_METHOD_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UnsupportedChains;", "Lcom/reown/sign/engine/model/ValidationError;", "_message", "", "<init>", "(Ljava/lang/String;)V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnsupportedChains extends ValidationError {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnsupportedChains(@NotNull String str) {
            super(str, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "_message");
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UnsupportedNamespaceKey;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnsupportedNamespaceKey extends ValidationError {
        @NotNull
        public static final UnsupportedNamespaceKey INSTANCE = new UnsupportedNamespaceKey();

        private UnsupportedNamespaceKey() {
            super(MessagesKt.NAMESPACE_KEYS_INVALID_FORMAT, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UserRejected;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserRejected extends ValidationError {
        @NotNull
        public static final UserRejected INSTANCE = new UserRejected();

        private UserRejected() {
            super(MessagesKt.NAMESPACE_KEYS_MISSING_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UserRejectedChains;", "Lcom/reown/sign/engine/model/ValidationError;", "_message", "", "<init>", "(Ljava/lang/String;)V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserRejectedChains extends ValidationError {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UserRejectedChains(@NotNull String str) {
            super(str, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "_message");
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UserRejectedEvents;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserRejectedEvents extends ValidationError {
        @NotNull
        public static final UserRejectedEvents INSTANCE = new UserRejectedEvents();

        private UserRejectedEvents() {
            super(MessagesKt.NAMESPACE_EVENTS_MISSING_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/reown/sign/engine/model/ValidationError$UserRejectedMethods;", "Lcom/reown/sign/engine/model/ValidationError;", "<init>", "()V", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserRejectedMethods extends ValidationError {
        @NotNull
        public static final UserRejectedMethods INSTANCE = new UserRejectedMethods();

        private UserRejectedMethods() {
            super(MessagesKt.NAMESPACE_METHODS_MISSING_MESSAGE, (DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ ValidationError(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    private ValidationError(String str) {
        this.message = str;
    }
}
