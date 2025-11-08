package com.reown.foundation.common.model;

import A.a;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonClass(generateAdapter = false)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/reown/foundation/common/model/SubscriptionId;", "", "id", "", "<init>", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "foundation"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubscriptionId {
    @NotNull
    private final String id;

    public SubscriptionId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
    }

    public static /* synthetic */ SubscriptionId copy$default(SubscriptionId subscriptionId, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = subscriptionId.id;
        }
        return subscriptionId.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final SubscriptionId copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new SubscriptionId(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SubscriptionId) && Intrinsics.areEqual((Object) this.id, (Object) ((SubscriptionId) obj).id);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @NotNull
    public String toString() {
        return a.l("SubscriptionId(id=", this.id, ")");
    }
}
