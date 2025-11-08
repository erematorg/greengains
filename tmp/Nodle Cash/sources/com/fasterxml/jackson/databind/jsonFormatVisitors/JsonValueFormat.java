package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.xerces.impl.xs.SchemaSymbols;

public enum JsonValueFormat {
    COLOR("color"),
    DATE("date"),
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE("phone"),
    REGEX("regex"),
    STYLE(TtmlNode.TAG_STYLE),
    TIME(SchemaSymbols.ATTVAL_TIME),
    URI("uri"),
    UTC_MILLISEC("utc-millisec"),
    UUID("uuid");
    
    private final String _desc;

    private JsonValueFormat(String str) {
        this._desc = str;
    }

    @JsonValue
    public String toString() {
        return this._desc;
    }
}
