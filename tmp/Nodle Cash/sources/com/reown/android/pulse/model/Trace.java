package com.reown.android.pulse.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/reown/android/pulse/model/Trace;", "", "<init>", "()V", "Pairing", "Session", "SessionAuthenticate", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Trace {
    @NotNull
    public static final Trace INSTANCE = new Trace();

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/reown/android/pulse/model/Trace$Pairing;", "", "<init>", "()V", "PAIRING_STARTED", "", "PAIRING_URI_VALIDATION_SUCCESS", "PAIRING_URI_NOT_EXPIRED", "STORE_NEW_PAIRING", "EXISTING_PAIRING", "PAIRING_NOT_EXPIRED", "EMIT_STORED_PAIRING", "EMIT_SESSION_PROPOSAL", "SUBSCRIBING_PAIRING_TOPIC", "SUBSCRIBE_PAIRING_TOPIC_SUCCESS", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Pairing {
        @NotNull
        public static final String EMIT_SESSION_PROPOSAL = "emit_session_proposal";
        @NotNull
        public static final String EMIT_STORED_PAIRING = "emit_inactive_pairing";
        @NotNull
        public static final String EXISTING_PAIRING = "existing_pairing";
        @NotNull
        public static final Pairing INSTANCE = new Pairing();
        @NotNull
        public static final String PAIRING_NOT_EXPIRED = "pairing_not_expired";
        @NotNull
        public static final String PAIRING_STARTED = "pairing_started";
        @NotNull
        public static final String PAIRING_URI_NOT_EXPIRED = "pairing_uri_not_expired";
        @NotNull
        public static final String PAIRING_URI_VALIDATION_SUCCESS = "pairing_uri_validation_success";
        @NotNull
        public static final String STORE_NEW_PAIRING = "store_new_pairing";
        @NotNull
        public static final String SUBSCRIBE_PAIRING_TOPIC_SUCCESS = "subscribe_pairing_topic_success";
        @NotNull
        public static final String SUBSCRIBING_PAIRING_TOPIC = "subscribing_pairing_topic";

        private Pairing() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/reown/android/pulse/model/Trace$Session;", "", "<init>", "()V", "SESSION_APPROVE_STARTED", "", "PROPOSAL_NOT_EXPIRED", "SESSION_NAMESPACE_VALIDATION_SUCCESS", "CREATE_SESSION_TOPIC", "PUBLISHING_SESSION_APPROVE", "STORE_SESSION", "SESSION_APPROVE_SUCCESS", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Session {
        @NotNull
        public static final String CREATE_SESSION_TOPIC = "create_session_topic";
        @NotNull
        public static final Session INSTANCE = new Session();
        @NotNull
        public static final String PROPOSAL_NOT_EXPIRED = "proposal_not_expired";
        @NotNull
        public static final String PUBLISHING_SESSION_APPROVE = "publishing_session_approve";
        @NotNull
        public static final String SESSION_APPROVE_STARTED = "session_approve_started";
        @NotNull
        public static final String SESSION_APPROVE_SUCCESS = "session_approve_publish_success";
        @NotNull
        public static final String SESSION_NAMESPACE_VALIDATION_SUCCESS = "session_namespaces_validation_success";
        @NotNull
        public static final String STORE_SESSION = "store_session";

        private Session() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/reown/android/pulse/model/Trace$SessionAuthenticate;", "", "<init>", "()V", "SESSION_AUTHENTICATE_APPROVE_STARTED", "", "AUTHENTICATED_SESSION_NOT_EXPIRED", "CHAINS_CAIP2_COMPLIANT", "CHAINS_EVM_COMPLIANT", "CREATE_AUTHENTICATED_SESSION_TOPIC", "CACAOS_VERIFIED", "STORE_AUTHENTICATED_SESSION", "SUBSCRIBING_AUTHENTICATED_SESSION_TOPIC", "SUBSCRIBE_AUTHENTICATED_SESSION_TOPIC_SUCCESS", "PUBLISHING_AUTHENTICATED_SESSION_APPROVE", "AUTHENTICATED_SESSION_APPROVE_PUBLISH_SUCCESS", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SessionAuthenticate {
        @NotNull
        public static final String AUTHENTICATED_SESSION_APPROVE_PUBLISH_SUCCESS = "authenticated_session_approve_publish_success";
        @NotNull
        public static final String AUTHENTICATED_SESSION_NOT_EXPIRED = "authenticated_session_not_expired";
        @NotNull
        public static final String CACAOS_VERIFIED = "cacaos_verified";
        @NotNull
        public static final String CHAINS_CAIP2_COMPLIANT = "chains_caip2_compliant";
        @NotNull
        public static final String CHAINS_EVM_COMPLIANT = "chains_evm_compliant";
        @NotNull
        public static final String CREATE_AUTHENTICATED_SESSION_TOPIC = "create_authenticated_session_topic";
        @NotNull
        public static final SessionAuthenticate INSTANCE = new SessionAuthenticate();
        @NotNull
        public static final String PUBLISHING_AUTHENTICATED_SESSION_APPROVE = "publishing_authenticated_session_approve";
        @NotNull
        public static final String SESSION_AUTHENTICATE_APPROVE_STARTED = "authenticated_session_approve_started";
        @NotNull
        public static final String STORE_AUTHENTICATED_SESSION = "store_authenticated_session";
        @NotNull
        public static final String SUBSCRIBE_AUTHENTICATED_SESSION_TOPIC_SUCCESS = "subscribe_authenticated_session_topic_success";
        @NotNull
        public static final String SUBSCRIBING_AUTHENTICATED_SESSION_TOPIC = "subscribing_authenticated_session_topic";

        private SessionAuthenticate() {
        }
    }

    private Trace() {
    }
}
