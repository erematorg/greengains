package com.reown.sign.common.exceptions;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"NO_SEQUENCE_FOR_TOPIC_MESSAGE", "", "UNAUTHORIZED_UPDATE_MESSAGE", "UNAUTHORIZED_EXTEND_MESSAGE", "UNAUTHORIZED_EMIT_MESSAGE", "SESSION_IS_NOT_ACKNOWLEDGED_MESSAGE", "EMPTY_NAMESPACES_MESSAGE", "NAMESPACE_CHAINS_MISSING_MESSAGE", "NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE", "NAMESPACE_CHAINS_CAIP_2_MESSAGE", "NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE", "NAMESPACE_KEYS_INVALID_FORMAT", "NAMESPACE_ACCOUNTS_CAIP_10_MESSAGE", "NAMESPACE_METHODS_MISSING_MESSAGE", "NAMESPACE_EVENTS_MISSING_MESSAGE", "NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE", "NAMESPACE_KEYS_MISSING_MESSAGE", "UNAUTHORIZED_METHOD_MESSAGE", "UNAUTHORIZED_EVENT_MESSAGE", "INVALID_EVENT_MESSAGE", "INVALID_REQUEST_MESSAGE", "INVALID_EXTEND_TIME", "INVALID_SESSION_PROPERTIES", "CLIENT_ALREADY_INITIALIZED", "INVALID_SIGN_PARAMS_TYPE", "MISSING_SESSION_AUTHENTICATE_REQUEST", "INVALID_CACAO_EXCEPTION", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class MessagesKt {
    @NotNull
    public static final String CLIENT_ALREADY_INITIALIZED = "SignClient already initialized";
    @NotNull
    public static final String EMPTY_NAMESPACES_MESSAGE = "Session namespaces MUST not be empty";
    @NotNull
    public static final String INVALID_CACAO_EXCEPTION = "Invalid Cacao exception";
    @NotNull
    public static final String INVALID_EVENT_MESSAGE = "Event name and data fields cannot be empty. ChainId must be CAIP-2 compliant";
    @NotNull
    public static final String INVALID_EXTEND_TIME = "Extend time is out of range";
    @NotNull
    public static final String INVALID_REQUEST_MESSAGE = "Request topic, method and params fields cannot be empty. ChainId must be CAIP-2 compliant";
    @NotNull
    public static final String INVALID_SESSION_PROPERTIES = "Invalid Session Properties requested";
    @NotNull
    public static final String INVALID_SIGN_PARAMS_TYPE = "Invalid Sign params type";
    @NotNull
    public static final String MISSING_SESSION_AUTHENTICATE_REQUEST = "Missing session authenticate request, expired";
    @NotNull
    public static final String NAMESPACE_ACCOUNTS_CAIP_10_MESSAGE = "Accounts must be CAIP-10 compliant";
    @NotNull
    public static final String NAMESPACE_ACCOUNTS_WRONG_NAMESPACE_MESSAGE = "Accounts must be defined in matching namespace";
    @NotNull
    public static final String NAMESPACE_CHAINS_CAIP_2_MESSAGE = "Chains must be CAIP-2 compliant";
    @NotNull
    public static final String NAMESPACE_CHAINS_MISSING_MESSAGE = "Chains must not be empty";
    @NotNull
    public static final String NAMESPACE_CHAINS_UNDEFINED_MISSING_MESSAGE = "Chains must not be null";
    @NotNull
    public static final String NAMESPACE_CHAINS_WRONG_NAMESPACE_MESSAGE = "Chains must be defined in matching namespace";
    @NotNull
    public static final String NAMESPACE_EVENTS_MISSING_MESSAGE = "All events must be approved: not all events are approved";
    @NotNull
    public static final String NAMESPACE_KEYS_INVALID_FORMAT = "Invalid namespace id format";
    @NotNull
    public static final String NAMESPACE_KEYS_MISSING_MESSAGE = "All required namespaces must be approved";
    @NotNull
    public static final String NAMESPACE_METHODS_MISSING_MESSAGE = "All required namespaces must be approved: not all methods are approved";
    @NotNull
    public static final String NO_SEQUENCE_FOR_TOPIC_MESSAGE = "Cannot find sequence for given topic: ";
    @NotNull
    public static final String SESSION_IS_NOT_ACKNOWLEDGED_MESSAGE = "Session is not acknowledged, topic: ";
    @NotNull
    public static final String UNAUTHORIZED_EMIT_MESSAGE = "The emit() was called by the unauthorized peer. Must be called by controller client.";
    @NotNull
    public static final String UNAUTHORIZED_EVENT_MESSAGE = "Unauthorized event is not authorized for given chain";
    @NotNull
    public static final String UNAUTHORIZED_EXTEND_MESSAGE = "The extend() was called by the unauthorized peer. Must be called by controller client.";
    @NotNull
    public static final String UNAUTHORIZED_METHOD_MESSAGE = "Unauthorized method is not authorized for given chain";
    @NotNull
    public static final String UNAUTHORIZED_UPDATE_MESSAGE = "The update() was called by the unauthorized peer. Must be called by controller client.";
}
