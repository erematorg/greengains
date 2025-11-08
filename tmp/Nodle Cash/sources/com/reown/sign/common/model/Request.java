package com.reown.sign.common.model;

import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00028\u0000\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000e\u0010#\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010$\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000eHÆ\u0003J`\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00028\u00002\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\n\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lcom/reown/sign/common/model/Request;", "T", "", "id", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "method", "", "chainId", "params", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "<init>", "(JLcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/reown/android/internal/common/model/Expiry;Lcom/reown/android/internal/common/model/TransportType;)V", "getId", "()J", "getTopic", "()Lcom/reown/foundation/common/model/Topic;", "getMethod", "()Ljava/lang/String;", "getChainId", "getParams", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getExpiry", "()Lcom/reown/android/internal/common/model/Expiry;", "getTransportType", "()Lcom/reown/android/internal/common/model/TransportType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JLcom/reown/foundation/common/model/Topic;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/reown/android/internal/common/model/Expiry;Lcom/reown/android/internal/common/model/TransportType;)Lcom/reown/sign/common/model/Request;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Request<T> {
    @Nullable
    private final String chainId;
    @Nullable
    private final Expiry expiry;
    private final long id;
    @NotNull
    private final String method;
    private final T params;
    @NotNull
    private final Topic topic;
    @Nullable
    private final TransportType transportType;

    public Request(long j2, @NotNull Topic topic2, @NotNull String str, @Nullable String str2, T t2, @Nullable Expiry expiry2, @Nullable TransportType transportType2) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        this.id = j2;
        this.topic = topic2;
        this.method = str;
        this.chainId = str2;
        this.params = t2;
        this.expiry = expiry2;
        this.transportType = transportType2;
    }

    public static /* synthetic */ Request copy$default(Request request, long j2, Topic topic2, String str, String str2, Object obj, Expiry expiry2, TransportType transportType2, int i3, Object obj2) {
        Request request2 = request;
        return request.copy((i3 & 1) != 0 ? request2.id : j2, (i3 & 2) != 0 ? request2.topic : topic2, (i3 & 4) != 0 ? request2.method : str, (i3 & 8) != 0 ? request2.chainId : str2, (i3 & 16) != 0 ? request2.params : obj, (i3 & 32) != 0 ? request2.expiry : expiry2, (i3 & 64) != 0 ? request2.transportType : transportType2);
    }

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final Topic component2() {
        return this.topic;
    }

    @NotNull
    public final String component3() {
        return this.method;
    }

    @Nullable
    public final String component4() {
        return this.chainId;
    }

    public final T component5() {
        return this.params;
    }

    @Nullable
    public final Expiry component6() {
        return this.expiry;
    }

    @Nullable
    public final TransportType component7() {
        return this.transportType;
    }

    @NotNull
    public final Request<T> copy(long j2, @NotNull Topic topic2, @NotNull String str, @Nullable String str2, T t2, @Nullable Expiry expiry2, @Nullable TransportType transportType2) {
        Intrinsics.checkNotNullParameter(topic2, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        return new Request(j2, topic2, str, str2, t2, expiry2, transportType2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Request)) {
            return false;
        }
        Request request = (Request) obj;
        return this.id == request.id && Intrinsics.areEqual((Object) this.topic, (Object) request.topic) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.chainId, (Object) request.chainId) && Intrinsics.areEqual((Object) this.params, (Object) request.params) && Intrinsics.areEqual((Object) this.expiry, (Object) request.expiry) && this.transportType == request.transportType;
    }

    @Nullable
    public final String getChainId() {
        return this.chainId;
    }

    @Nullable
    public final Expiry getExpiry() {
        return this.expiry;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    public final T getParams() {
        return this.params;
    }

    @NotNull
    public final Topic getTopic() {
        return this.topic;
    }

    @Nullable
    public final TransportType getTransportType() {
        return this.transportType;
    }

    public int hashCode() {
        int i3 = a.i(this.method, (this.topic.hashCode() + (Long.hashCode(this.id) * 31)) * 31, 31);
        String str = this.chainId;
        int i4 = 0;
        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
        T t2 = this.params;
        int hashCode2 = (hashCode + (t2 == null ? 0 : t2.hashCode())) * 31;
        Expiry expiry2 = this.expiry;
        int hashCode3 = (hashCode2 + (expiry2 == null ? 0 : expiry2.hashCode())) * 31;
        TransportType transportType2 = this.transportType;
        if (transportType2 != null) {
            i4 = transportType2.hashCode();
        }
        return hashCode3 + i4;
    }

    @NotNull
    public String toString() {
        long j2 = this.id;
        Topic topic2 = this.topic;
        String str = this.method;
        String str2 = this.chainId;
        T t2 = this.params;
        Expiry expiry2 = this.expiry;
        TransportType transportType2 = this.transportType;
        StringBuilder sb = new StringBuilder("Request(id=");
        sb.append(j2);
        sb.append(", topic=");
        sb.append(topic2);
        b.w(sb, ", method=", str, ", chainId=", str2);
        sb.append(", params=");
        sb.append(t2);
        sb.append(", expiry=");
        sb.append(expiry2);
        sb.append(", transportType=");
        sb.append(transportType2);
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Request(long j2, Topic topic2, String str, String str2, Object obj, Expiry expiry2, TransportType transportType2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, topic2, str, str2, obj, (i3 & 32) != 0 ? null : expiry2, transportType2);
    }
}
