package com.reown.android.pulse.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\t\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/reown/android/pulse/model/EventType;", "", "<init>", "()V", "ERROR", "", "SUCCESS", "INIT", "TRACK", "Error", "Track", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EventType {
    @NotNull
    public static final String ERROR = "ERROR";
    @NotNull
    public static final String INIT = "INIT";
    @NotNull
    public static final EventType INSTANCE = new EventType();
    @NotNull
    public static final String SUCCESS = "SUCCESS";
    @NotNull
    public static final String TRACK = "TRACE";

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/reown/android/pulse/model/EventType$Error;", "", "<init>", "()V", "NO_WSS_CONNECTION", "", "NO_INTERNET_CONNECTION", "MALFORMED_PAIRING_URI", "PAIRING_SUBSCRIPTION_FAILURE", "PAIRING_URI_EXPIRED", "PAIRING_EXPIRED", "PROPOSAL_EXPIRED", "SESSION_APPROVE_FAILURE", "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE", "REQUIRED_NAMESPACE_VALIDATION_FAILURE", "OPTIONAL_NAMESPACE_VALIDATION_FAILURE", "SESSION_PROPERTIES_VALIDATION_FAILURE", "MISSING_SESSION_AUTH_REQUEST", "SESSION_AUTH_REQUEST_EXPIRED", "CHAINS_CAIP2_COMPLIANT_FAILURE", "CHAINS_EVM_COMPLIANT_FAILURE", "INVALID_CACAO", "SUBSCRIBE_AUTH_SESSION_TOPIC_FAILURE", "AUTHENTICATED_SESSION_APPROVE_PUBLISH_FAILURE", "AUTHENTICATED_SESSION_EXPIRED", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error {
        @NotNull
        public static final String AUTHENTICATED_SESSION_APPROVE_PUBLISH_FAILURE = "AUTHENTICATED_SESSION_APPROVE_PUBLISH_FAILURE";
        @NotNull
        public static final String AUTHENTICATED_SESSION_EXPIRED = "AUTHENTICATED_SESSION_EXPIRED";
        @NotNull
        public static final String CHAINS_CAIP2_COMPLIANT_FAILURE = "CHAINS_CAIP2_COMPLIANT_FAILURE";
        @NotNull
        public static final String CHAINS_EVM_COMPLIANT_FAILURE = "CHAINS_EVM_COMPLIANT_FAILURE";
        @NotNull
        public static final Error INSTANCE = new Error();
        @NotNull
        public static final String INVALID_CACAO = "INVALID_CACAO";
        @NotNull
        public static final String MALFORMED_PAIRING_URI = "MALFORMED_PAIRING_URI";
        @NotNull
        public static final String MISSING_SESSION_AUTH_REQUEST = "MISSING_SESSION_AUTH_REQUEST";
        @NotNull
        public static final String NO_INTERNET_CONNECTION = "NO_INTERNET_CONNECTION";
        @NotNull
        public static final String NO_WSS_CONNECTION = "NO_WSS_CONNECTION";
        @NotNull
        public static final String OPTIONAL_NAMESPACE_VALIDATION_FAILURE = "OPTIONAL_NAMESPACE_VALIDATION_FAILURE";
        @NotNull
        public static final String PAIRING_EXPIRED = "PAIRING_EXPIRED";
        @NotNull
        public static final String PAIRING_SUBSCRIPTION_FAILURE = "FAILED_TO_SUBSCRIBE_TO_PAIRING_TOPIC";
        @NotNull
        public static final String PAIRING_URI_EXPIRED = "PAIRING_URI_EXPIRED";
        @NotNull
        public static final String PROPOSAL_EXPIRED = "PROPOSAL_EXPIRED";
        @NotNull
        public static final String REQUIRED_NAMESPACE_VALIDATION_FAILURE = "REQUIRED_NAMESPACE_VALIDATION_FAILURE";
        @NotNull
        public static final String SESSION_APPROVE_FAILURE = "SESSION_APPROVE_FAILURE";
        @NotNull
        public static final String SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE = "SESSION_APPROVE_NAMESPACE_VALIDATION_FAILURE";
        @NotNull
        public static final String SESSION_AUTH_REQUEST_EXPIRED = "SESSION_AUTH_REQUEST_EXPIRED";
        @NotNull
        public static final String SESSION_PROPERTIES_VALIDATION_FAILURE = "SESSION_PROPERTIES_VALIDATION_FAILURE";
        @NotNull
        public static final String SUBSCRIBE_AUTH_SESSION_TOPIC_FAILURE = "SUBSCRIBE_AUTH_SESSION_TOPIC_FAILURE";

        private Error() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/reown/android/pulse/model/EventType$Track;", "", "<init>", "()V", "MODAL_CREATED", "", "MODAL_LOADED", "MODAL_OPEN", "MODAL_CLOSE", "CLICK_ALL_WALLETS", "CLICK_NETWORKS", "SWITCH_NETWORK", "SELECT_WALLET", "CONNECT_SUCCESS", "CONNECT_ERROR", "DISCONNECT_SUCCESS", "DISCONNECT_ERROR", "CLICK_WALLET_HELP", "CLICK_NETWORK_HELP", "CLICK_GET_WALLET", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Track {
        @NotNull
        public static final String CLICK_ALL_WALLETS = "CLICK_ALL_WALLETS";
        @NotNull
        public static final String CLICK_GET_WALLET = "CLICK_GET_WALLET";
        @NotNull
        public static final String CLICK_NETWORKS = "CLICK_NETWORKS";
        @NotNull
        public static final String CLICK_NETWORK_HELP = "CLICK_NETWORK_HELP";
        @NotNull
        public static final String CLICK_WALLET_HELP = "CLICK_WALLET_HELP";
        @NotNull
        public static final String CONNECT_ERROR = "CONNECT_ERROR";
        @NotNull
        public static final String CONNECT_SUCCESS = "CONNECT_SUCCESS";
        @NotNull
        public static final String DISCONNECT_ERROR = "DISCONNECT_ERROR";
        @NotNull
        public static final String DISCONNECT_SUCCESS = "DISCONNECT_SUCCESS";
        @NotNull
        public static final Track INSTANCE = new Track();
        @NotNull
        public static final String MODAL_CLOSE = "MODAL_CLOSE";
        @NotNull
        public static final String MODAL_CREATED = "MODAL_CREATED";
        @NotNull
        public static final String MODAL_LOADED = "MODAL_LOADED";
        @NotNull
        public static final String MODAL_OPEN = "MODAL_OPEN";
        @NotNull
        public static final String SELECT_WALLET = "SELECT_WALLET";
        @NotNull
        public static final String SWITCH_NETWORK = "SWITCH_NETWORK";

        private Track() {
        }
    }

    private EventType() {
    }
}
