package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.apache.commons.text.StringSubstitutor;

@Immutable
public final class VerifiedJwt {
    private final RawJwt rawJwt;

    public VerifiedJwt(RawJwt rawJwt2) {
        this.rawJwt = rawJwt2;
    }

    public Set<String> customClaimNames() {
        return this.rawJwt.customClaimNames();
    }

    public List<String> getAudiences() throws JwtInvalidException {
        return this.rawJwt.getAudiences();
    }

    public Boolean getBooleanClaim(String str) throws JwtInvalidException {
        return this.rawJwt.getBooleanClaim(str);
    }

    public Instant getExpiration() throws JwtInvalidException {
        return this.rawJwt.getExpiration();
    }

    public Instant getIssuedAt() throws JwtInvalidException {
        return this.rawJwt.getIssuedAt();
    }

    public String getIssuer() throws JwtInvalidException {
        return this.rawJwt.getIssuer();
    }

    public String getJsonArrayClaim(String str) throws JwtInvalidException {
        return this.rawJwt.getJsonArrayClaim(str);
    }

    public String getJsonObjectClaim(String str) throws JwtInvalidException {
        return this.rawJwt.getJsonObjectClaim(str);
    }

    public String getJwtId() throws JwtInvalidException {
        return this.rawJwt.getJwtId();
    }

    public Instant getNotBefore() throws JwtInvalidException {
        return this.rawJwt.getNotBefore();
    }

    public Double getNumberClaim(String str) throws JwtInvalidException {
        return this.rawJwt.getNumberClaim(str);
    }

    public String getStringClaim(String str) throws JwtInvalidException {
        return this.rawJwt.getStringClaim(str);
    }

    public String getSubject() throws JwtInvalidException {
        return this.rawJwt.getSubject();
    }

    public String getTypeHeader() throws JwtInvalidException {
        return this.rawJwt.getTypeHeader();
    }

    public boolean hasAudiences() {
        return this.rawJwt.hasAudiences();
    }

    public boolean hasBooleanClaim(String str) {
        return this.rawJwt.hasBooleanClaim(str);
    }

    public boolean hasExpiration() {
        return this.rawJwt.hasExpiration();
    }

    public boolean hasIssuedAt() {
        return this.rawJwt.hasIssuedAt();
    }

    public boolean hasIssuer() {
        return this.rawJwt.hasIssuer();
    }

    public boolean hasJsonArrayClaim(String str) {
        return this.rawJwt.hasJsonArrayClaim(str);
    }

    public boolean hasJsonObjectClaim(String str) {
        return this.rawJwt.hasJsonObjectClaim(str);
    }

    public boolean hasJwtId() {
        return this.rawJwt.hasJwtId();
    }

    public boolean hasNotBefore() {
        return this.rawJwt.hasNotBefore();
    }

    public boolean hasNumberClaim(String str) {
        return this.rawJwt.hasNumberClaim(str);
    }

    public boolean hasStringClaim(String str) {
        return this.rawJwt.hasStringClaim(str);
    }

    public boolean hasSubject() {
        return this.rawJwt.hasSubject();
    }

    public boolean hasTypeHeader() {
        return this.rawJwt.hasTypeHeader();
    }

    public boolean isNullClaim(String str) {
        return this.rawJwt.isNullClaim(str);
    }

    public String toString() {
        return "verified{" + this.rawJwt + StringSubstitutor.DEFAULT_VAR_END;
    }
}
