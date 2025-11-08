package com.reown.android.internal.common.model;

import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Ttl;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0017J\u0011\u0010$\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J\t\u0010(\u001a\u00020\u000fHÆ\u0003Jz\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001¢\u0006\u0002\u0010*J\u0013\u0010+\u001a\u00020\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Lcom/reown/android/internal/common/model/IrnParams;", "", "tag", "Lcom/reown/android/internal/common/model/Tags;", "ttl", "Lcom/reown/foundation/common/model/Ttl;", "correlationId", "", "rpcMethods", "", "", "chainId", "txHashes", "contractAddresses", "prompt", "", "<init>", "(Lcom/reown/android/internal/common/model/Tags;Lcom/reown/foundation/common/model/Ttl;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Z)V", "getTag", "()Lcom/reown/android/internal/common/model/Tags;", "getTtl", "()Lcom/reown/foundation/common/model/Ttl;", "getCorrelationId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRpcMethods", "()Ljava/util/List;", "getChainId", "()Ljava/lang/String;", "getTxHashes", "getContractAddresses", "getPrompt", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lcom/reown/android/internal/common/model/Tags;Lcom/reown/foundation/common/model/Ttl;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Z)Lcom/reown/android/internal/common/model/IrnParams;", "equals", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrnParams {
    @Nullable
    private final String chainId;
    @Nullable
    private final List<String> contractAddresses;
    @Nullable
    private final Long correlationId;
    private final boolean prompt;
    @Nullable
    private final List<String> rpcMethods;
    @NotNull
    private final Tags tag;
    @NotNull
    private final Ttl ttl;
    @Nullable
    private final List<String> txHashes;

    public IrnParams(@NotNull Tags tags, @NotNull Ttl ttl2, @Nullable Long l2, @Nullable List<String> list, @Nullable String str, @Nullable List<String> list2, @Nullable List<String> list3, boolean z2) {
        Intrinsics.checkNotNullParameter(tags, PushMessagingService.KEY_TAG);
        Intrinsics.checkNotNullParameter(ttl2, "ttl");
        this.tag = tags;
        this.ttl = ttl2;
        this.correlationId = l2;
        this.rpcMethods = list;
        this.chainId = str;
        this.txHashes = list2;
        this.contractAddresses = list3;
        this.prompt = z2;
    }

    public static /* synthetic */ IrnParams copy$default(IrnParams irnParams, Tags tags, Ttl ttl2, Long l2, List list, String str, List list2, List list3, boolean z2, int i3, Object obj) {
        IrnParams irnParams2 = irnParams;
        int i4 = i3;
        return irnParams.copy((i4 & 1) != 0 ? irnParams2.tag : tags, (i4 & 2) != 0 ? irnParams2.ttl : ttl2, (i4 & 4) != 0 ? irnParams2.correlationId : l2, (i4 & 8) != 0 ? irnParams2.rpcMethods : list, (i4 & 16) != 0 ? irnParams2.chainId : str, (i4 & 32) != 0 ? irnParams2.txHashes : list2, (i4 & 64) != 0 ? irnParams2.contractAddresses : list3, (i4 & 128) != 0 ? irnParams2.prompt : z2);
    }

    @NotNull
    public final Tags component1() {
        return this.tag;
    }

    @NotNull
    public final Ttl component2() {
        return this.ttl;
    }

    @Nullable
    public final Long component3() {
        return this.correlationId;
    }

    @Nullable
    public final List<String> component4() {
        return this.rpcMethods;
    }

    @Nullable
    public final String component5() {
        return this.chainId;
    }

    @Nullable
    public final List<String> component6() {
        return this.txHashes;
    }

    @Nullable
    public final List<String> component7() {
        return this.contractAddresses;
    }

    public final boolean component8() {
        return this.prompt;
    }

    @NotNull
    public final IrnParams copy(@NotNull Tags tags, @NotNull Ttl ttl2, @Nullable Long l2, @Nullable List<String> list, @Nullable String str, @Nullable List<String> list2, @Nullable List<String> list3, boolean z2) {
        Intrinsics.checkNotNullParameter(tags, PushMessagingService.KEY_TAG);
        Intrinsics.checkNotNullParameter(ttl2, "ttl");
        return new IrnParams(tags, ttl2, l2, list, str, list2, list3, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IrnParams)) {
            return false;
        }
        IrnParams irnParams = (IrnParams) obj;
        return this.tag == irnParams.tag && Intrinsics.areEqual((Object) this.ttl, (Object) irnParams.ttl) && Intrinsics.areEqual((Object) this.correlationId, (Object) irnParams.correlationId) && Intrinsics.areEqual((Object) this.rpcMethods, (Object) irnParams.rpcMethods) && Intrinsics.areEqual((Object) this.chainId, (Object) irnParams.chainId) && Intrinsics.areEqual((Object) this.txHashes, (Object) irnParams.txHashes) && Intrinsics.areEqual((Object) this.contractAddresses, (Object) irnParams.contractAddresses) && this.prompt == irnParams.prompt;
    }

    @Nullable
    public final String getChainId() {
        return this.chainId;
    }

    @Nullable
    public final List<String> getContractAddresses() {
        return this.contractAddresses;
    }

    @Nullable
    public final Long getCorrelationId() {
        return this.correlationId;
    }

    public final boolean getPrompt() {
        return this.prompt;
    }

    @Nullable
    public final List<String> getRpcMethods() {
        return this.rpcMethods;
    }

    @NotNull
    public final Tags getTag() {
        return this.tag;
    }

    @NotNull
    public final Ttl getTtl() {
        return this.ttl;
    }

    @Nullable
    public final List<String> getTxHashes() {
        return this.txHashes;
    }

    public int hashCode() {
        int hashCode = (this.ttl.hashCode() + (this.tag.hashCode() * 31)) * 31;
        Long l2 = this.correlationId;
        int i3 = 0;
        int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
        List<String> list = this.rpcMethods;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.chainId;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list2 = this.txHashes;
        int hashCode5 = (hashCode4 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.contractAddresses;
        if (list3 != null) {
            i3 = list3.hashCode();
        }
        return Boolean.hashCode(this.prompt) + ((hashCode5 + i3) * 31);
    }

    @NotNull
    public String toString() {
        Tags tags = this.tag;
        Ttl ttl2 = this.ttl;
        Long l2 = this.correlationId;
        List<String> list = this.rpcMethods;
        String str = this.chainId;
        List<String> list2 = this.txHashes;
        List<String> list3 = this.contractAddresses;
        boolean z2 = this.prompt;
        return "IrnParams(tag=" + tags + ", ttl=" + ttl2 + ", correlationId=" + l2 + ", rpcMethods=" + list + ", chainId=" + str + ", txHashes=" + list2 + ", contractAddresses=" + list3 + ", prompt=" + z2 + ")";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ IrnParams(com.reown.android.internal.common.model.Tags r13, com.reown.foundation.common.model.Ttl r14, java.lang.Long r15, java.util.List r16, java.lang.String r17, java.util.List r18, java.util.List r19, boolean r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000a
        L_0x0009:
            r6 = r15
        L_0x000a:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0010
            r7 = r2
            goto L_0x0012
        L_0x0010:
            r7 = r16
        L_0x0012:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0018
            r8 = r2
            goto L_0x001a
        L_0x0018:
            r8 = r17
        L_0x001a:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0020
            r9 = r2
            goto L_0x0022
        L_0x0020:
            r9 = r18
        L_0x0022:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0028
            r10 = r2
            goto L_0x002a
        L_0x0028:
            r10 = r19
        L_0x002a:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0031
            r0 = 0
            r11 = r0
            goto L_0x0033
        L_0x0031:
            r11 = r20
        L_0x0033:
            r3 = r12
            r4 = r13
            r5 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.model.IrnParams.<init>(com.reown.android.internal.common.model.Tags, com.reown.foundation.common.model.Ttl, java.lang.Long, java.util.List, java.lang.String, java.util.List, java.util.List, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
