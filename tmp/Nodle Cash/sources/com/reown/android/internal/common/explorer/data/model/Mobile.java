package com.reown.android.internal.common.explorer.data.model;

import androidx.camera.camera2.internal.C0118y;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/android/internal/common/explorer/data/model/Mobile;", "", "native", "", "universal", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getNative", "()Ljava/lang/String;", "getUniversal", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Mobile {
    @Nullable

    /* renamed from: native  reason: not valid java name */
    private final String f69native;
    @Nullable
    private final String universal;

    public Mobile(@Nullable String str, @Nullable String str2) {
        this.f69native = str;
        this.universal = str2;
    }

    public static /* synthetic */ Mobile copy$default(Mobile mobile, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = mobile.f69native;
        }
        if ((i3 & 2) != 0) {
            str2 = mobile.universal;
        }
        return mobile.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.f69native;
    }

    @Nullable
    public final String component2() {
        return this.universal;
    }

    @NotNull
    public final Mobile copy(@Nullable String str, @Nullable String str2) {
        return new Mobile(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mobile)) {
            return false;
        }
        Mobile mobile = (Mobile) obj;
        return Intrinsics.areEqual((Object) this.f69native, (Object) mobile.f69native) && Intrinsics.areEqual((Object) this.universal, (Object) mobile.universal);
    }

    @Nullable
    public final String getNative() {
        return this.f69native;
    }

    @Nullable
    public final String getUniversal() {
        return this.universal;
    }

    public int hashCode() {
        String str = this.f69native;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.universal;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode + i3;
    }

    @NotNull
    public String toString() {
        return C0118y.g("Mobile(native=", this.f69native, ", universal=", this.universal, ")");
    }
}
