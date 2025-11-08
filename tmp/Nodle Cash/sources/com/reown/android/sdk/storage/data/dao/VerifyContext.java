package com.reown.android.sdk.storage.data.dao;

import androidx.compose.animation.core.a;
import app.cash.sqldelight.ColumnAdapter;
import com.reown.android.internal.common.model.Validation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.impl.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\"B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\t\u0010\u0014¨\u0006#"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyContext;", "", "id", "", "origin", "", "validation", "Lcom/reown/android/internal/common/model/Validation;", "verify_url", "is_scam", "", "<init>", "(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getOrigin", "()Ljava/lang/String;", "getValidation", "()Lcom/reown/android/internal/common/model/Validation;", "getVerify_url", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(JLjava/lang/String;Lcom/reown/android/internal/common/model/Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/sdk/storage/data/dao/VerifyContext;", "equals", "other", "hashCode", "", "toString", "Adapter", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyContext {
    private final long id;
    @Nullable
    private final Boolean is_scam;
    @NotNull
    private final String origin;
    @NotNull
    private final Validation validation;
    @NotNull
    private final String verify_url;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/VerifyContext$Adapter;", "", "validationAdapter", "Lapp/cash/sqldelight/ColumnAdapter;", "Lcom/reown/android/internal/common/model/Validation;", "", "<init>", "(Lapp/cash/sqldelight/ColumnAdapter;)V", "getValidationAdapter", "()Lapp/cash/sqldelight/ColumnAdapter;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Adapter {
        @NotNull
        private final ColumnAdapter<Validation, String> validationAdapter;

        public Adapter(@NotNull ColumnAdapter<Validation, String> columnAdapter) {
            Intrinsics.checkNotNullParameter(columnAdapter, "validationAdapter");
            this.validationAdapter = columnAdapter;
        }

        @NotNull
        public final ColumnAdapter<Validation, String> getValidationAdapter() {
            return this.validationAdapter;
        }
    }

    public VerifyContext(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(validation2, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str2, "verify_url");
        this.id = j2;
        this.origin = str;
        this.validation = validation2;
        this.verify_url = str2;
        this.is_scam = bool;
    }

    public static /* synthetic */ VerifyContext copy$default(VerifyContext verifyContext, long j2, String str, Validation validation2, String str2, Boolean bool, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j2 = verifyContext.id;
        }
        long j3 = j2;
        if ((i3 & 2) != 0) {
            str = verifyContext.origin;
        }
        String str3 = str;
        if ((i3 & 4) != 0) {
            validation2 = verifyContext.validation;
        }
        Validation validation3 = validation2;
        if ((i3 & 8) != 0) {
            str2 = verifyContext.verify_url;
        }
        String str4 = str2;
        if ((i3 & 16) != 0) {
            bool = verifyContext.is_scam;
        }
        return verifyContext.copy(j3, str3, validation3, str4, bool);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.origin;
    }

    @NotNull
    public final Validation component3() {
        return this.validation;
    }

    @NotNull
    public final String component4() {
        return this.verify_url;
    }

    @Nullable
    public final Boolean component5() {
        return this.is_scam;
    }

    @NotNull
    public final VerifyContext copy(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "origin");
        Intrinsics.checkNotNullParameter(validation2, Constants.VALIDATION_FEATURE);
        Intrinsics.checkNotNullParameter(str2, "verify_url");
        return new VerifyContext(j2, str, validation2, str2, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerifyContext)) {
            return false;
        }
        VerifyContext verifyContext = (VerifyContext) obj;
        return this.id == verifyContext.id && Intrinsics.areEqual((Object) this.origin, (Object) verifyContext.origin) && this.validation == verifyContext.validation && Intrinsics.areEqual((Object) this.verify_url, (Object) verifyContext.verify_url) && Intrinsics.areEqual((Object) this.is_scam, (Object) verifyContext.is_scam);
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getOrigin() {
        return this.origin;
    }

    @NotNull
    public final Validation getValidation() {
        return this.validation;
    }

    @NotNull
    public final String getVerify_url() {
        return this.verify_url;
    }

    public int hashCode() {
        int i3 = a.i(this.verify_url, (this.validation.hashCode() + a.i(this.origin, Long.hashCode(this.id) * 31, 31)) * 31, 31);
        Boolean bool = this.is_scam;
        return i3 + (bool == null ? 0 : bool.hashCode());
    }

    @Nullable
    public final Boolean is_scam() {
        return this.is_scam;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        String str = this.origin;
        Validation validation2 = this.validation;
        String str2 = this.verify_url;
        Boolean bool = this.is_scam;
        StringBuilder v2 = androidx.work.impl.a.v(j2, "VerifyContext(id=", ", origin=", str);
        v2.append(", validation=");
        v2.append(validation2);
        v2.append(", verify_url=");
        v2.append(str2);
        v2.append(", is_scam=");
        v2.append(bool);
        v2.append(")");
        return v2.toString();
    }
}
