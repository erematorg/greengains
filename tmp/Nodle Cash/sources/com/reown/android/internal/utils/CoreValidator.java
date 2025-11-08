package com.reown.android.internal.utils;

import com.reown.android.internal.common.model.Expiry;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0007J\u0010\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\n\u0010\u000f\u001a\u00020\u0005*\u00020\u000eR\u000e\u0010\u0010\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/utils/CoreValidator;", "", "<init>", "()V", "isAccountIdCAIP10Compliant", "", "accountId", "", "isChainIdCAIP2Compliant", "chainId", "isNamespaceRegexCompliant", "key", "isExpiryWithinBounds", "userExpiry", "Lcom/reown/android/internal/common/model/Expiry;", "isExpired", "NAMESPACE_REGEX", "REFERENCE_REGEX", "ACCOUNT_ADDRESS_REGEX", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreValidator {
    @NotNull
    private static final String ACCOUNT_ADDRESS_REGEX = "^[-.%a-zA-Z0-9]{1,128}$";
    @NotNull
    public static final CoreValidator INSTANCE = new CoreValidator();
    @NotNull
    private static final String NAMESPACE_REGEX = "^[-a-z0-9]{3,8}$";
    @NotNull
    private static final String REFERENCE_REGEX = "^[-_a-zA-Z0-9]{1,32}$";

    private CoreValidator() {
    }

    public final /* synthetic */ boolean isAccountIdCAIP10Compliant(String str) {
        Intrinsics.checkNotNullParameter(str, "accountId");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
        if (Z2.isEmpty() || Z2.size() != 3) {
            return false;
        }
        return isNamespaceRegexCompliant((String) Z2.get(0)) && new Regex(REFERENCE_REGEX).matches((String) Z2.get(1)) && new Regex(ACCOUNT_ADDRESS_REGEX).matches((String) Z2.get(2));
    }

    public final /* synthetic */ boolean isChainIdCAIP2Compliant(String str) {
        Intrinsics.checkNotNullParameter(str, "chainId");
        List Z2 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null);
        if (Z2.isEmpty() || Z2.size() != 2) {
            return false;
        }
        return isNamespaceRegexCompliant((String) Z2.get(0)) && new Regex(REFERENCE_REGEX).matches((String) Z2.get(1));
    }

    public final /* synthetic */ boolean isExpired(Expiry expiry) {
        Intrinsics.checkNotNullParameter(expiry, "<this>");
        return expiry.getSeconds() < Time.getCurrentTimeInSeconds();
    }

    public final /* synthetic */ boolean isExpiryWithinBounds(Expiry expiry) {
        if (expiry == null) {
            return true;
        }
        return new LongRange(Time.getFiveMinutesInSeconds(), Time.getWeekInSeconds()).contains(expiry.getSeconds() - Time.getCurrentTimeInSeconds());
    }

    public final /* synthetic */ boolean isNamespaceRegexCompliant(String str) {
        Intrinsics.checkNotNullParameter(str, JwtUtilsKt.DID_METHOD_KEY);
        return new Regex(NAMESPACE_REGEX).matches(str);
    }
}
