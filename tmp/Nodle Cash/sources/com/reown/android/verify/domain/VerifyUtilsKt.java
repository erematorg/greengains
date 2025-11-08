package com.reown.android.verify.domain;

import com.reown.android.internal.common.model.Validation;
import com.reown.utils.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"getValidation", "Lcom/reown/android/internal/common/model/Validation;", "metadataUrl", "", "origin", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class VerifyUtilsKt {
    @NotNull
    public static final Validation getValidation(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "metadataUrl");
        Intrinsics.checkNotNullParameter(str2, "origin");
        return UtilFunctionsKt.compareDomains(str, str2) ? Validation.VALID : Validation.INVALID;
    }
}
