package com.reown.android.verify.domain;

import A.a;
import com.reown.android.internal.common.model.Validation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/android/verify/domain/VerifyResult;", "", "validation", "Lcom/reown/android/internal/common/model/Validation;", "isScam", "", "origin", "", "<init>", "(Lcom/reown/android/internal/common/model/Validation;Ljava/lang/Boolean;Ljava/lang/String;)V", "getValidation", "()Lcom/reown/android/internal/common/model/Validation;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getOrigin", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Lcom/reown/android/internal/common/model/Validation;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/reown/android/verify/domain/VerifyResult;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyResult {
    @Nullable
    private final Boolean isScam;
    @NotNull
    private final String origin;
    @NotNull
    private final Validation validation;

    public VerifyResult(@NotNull Validation validation2, @Nullable Boolean bool, @NotNull String str) {
        Intrinsics.checkNotNullParameter(validation2, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str, "origin");
        this.validation = validation2;
        this.isScam = bool;
        this.origin = str;
    }

    public static /* synthetic */ VerifyResult copy$default(VerifyResult verifyResult, Validation validation2, Boolean bool, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            validation2 = verifyResult.validation;
        }
        if ((i3 & 2) != 0) {
            bool = verifyResult.isScam;
        }
        if ((i3 & 4) != 0) {
            str = verifyResult.origin;
        }
        return verifyResult.copy(validation2, bool, str);
    }

    @NotNull
    public final Validation component1() {
        return this.validation;
    }

    @Nullable
    public final Boolean component2() {
        return this.isScam;
    }

    @NotNull
    public final String component3() {
        return this.origin;
    }

    @NotNull
    public final VerifyResult copy(@NotNull Validation validation2, @Nullable Boolean bool, @NotNull String str) {
        Intrinsics.checkNotNullParameter(validation2, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str, "origin");
        return new VerifyResult(validation2, bool, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerifyResult)) {
            return false;
        }
        VerifyResult verifyResult = (VerifyResult) obj;
        return this.validation == verifyResult.validation && Intrinsics.areEqual((Object) this.isScam, (Object) verifyResult.isScam) && Intrinsics.areEqual((Object) this.origin, (Object) verifyResult.origin);
    }

    @NotNull
    public final String getOrigin() {
        return this.origin;
    }

    @NotNull
    public final Validation getValidation() {
        return this.validation;
    }

    public int hashCode() {
        int hashCode = this.validation.hashCode() * 31;
        Boolean bool = this.isScam;
        return this.origin.hashCode() + ((hashCode + (bool == null ? 0 : bool.hashCode())) * 31);
    }

    @Nullable
    public final Boolean isScam() {
        return this.isScam;
    }

    @NotNull
    public String toString() {
        Validation validation2 = this.validation;
        Boolean bool = this.isScam;
        String str = this.origin;
        StringBuilder sb = new StringBuilder("VerifyResult(validation=");
        sb.append(validation2);
        sb.append(", isScam=");
        sb.append(bool);
        sb.append(", origin=");
        return a.n(sb, str, ")");
    }
}
