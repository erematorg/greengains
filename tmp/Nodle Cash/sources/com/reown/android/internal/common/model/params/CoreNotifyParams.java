package com.reown.android.internal.common.model.params;

import A.a;
import com.reown.android.internal.common.model.type.ClientParams;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0007\u0002\u0003\u0004\u0005\u0006\u0007\b\u0001\u0007\t\n\u000b\f\r\u000e\u000f¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "Lcom/reown/android/internal/common/model/type/ClientParams;", "SubscribeParams", "MessageParams", "UpdateParams", "DeleteParams", "WatchSubscriptionsParams", "SubscriptionsChangedParams", "GetNotificationsParams", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$DeleteParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$GetNotificationsParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$MessageParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$SubscribeParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$SubscriptionsChangedParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$UpdateParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams$WatchSubscriptionsParams;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CoreNotifyParams extends ClientParams {

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$DeleteParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "deleteAuth", "", "<init>", "(Ljava/lang/String;)V", "getDeleteAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DeleteParams implements CoreNotifyParams {
        @NotNull
        private final String deleteAuth;

        public DeleteParams(@NotNull @Json(name = "deleteAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "deleteAuth");
            this.deleteAuth = str;
        }

        public static /* synthetic */ DeleteParams copy$default(DeleteParams deleteParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = deleteParams.deleteAuth;
            }
            return deleteParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.deleteAuth;
        }

        @NotNull
        public final DeleteParams copy(@NotNull @Json(name = "deleteAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "deleteAuth");
            return new DeleteParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DeleteParams) && Intrinsics.areEqual((Object) this.deleteAuth, (Object) ((DeleteParams) obj).deleteAuth);
        }

        @NotNull
        public final String getDeleteAuth() {
            return this.deleteAuth;
        }

        public int hashCode() {
            return this.deleteAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("DeleteParams(deleteAuth=", this.deleteAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$GetNotificationsParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "auth", "", "<init>", "(Ljava/lang/String;)V", "getAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class GetNotificationsParams implements CoreNotifyParams {
        @NotNull
        private final String auth;

        public GetNotificationsParams(@NotNull @Json(name = "auth") String str) {
            Intrinsics.checkNotNullParameter(str, "auth");
            this.auth = str;
        }

        public static /* synthetic */ GetNotificationsParams copy$default(GetNotificationsParams getNotificationsParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = getNotificationsParams.auth;
            }
            return getNotificationsParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.auth;
        }

        @NotNull
        public final GetNotificationsParams copy(@NotNull @Json(name = "auth") String str) {
            Intrinsics.checkNotNullParameter(str, "auth");
            return new GetNotificationsParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof GetNotificationsParams) && Intrinsics.areEqual((Object) this.auth, (Object) ((GetNotificationsParams) obj).auth);
        }

        @NotNull
        public final String getAuth() {
            return this.auth;
        }

        public int hashCode() {
            return this.auth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("GetNotificationsParams(auth=", this.auth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$MessageParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "messageAuth", "", "<init>", "(Ljava/lang/String;)V", "getMessageAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MessageParams implements CoreNotifyParams {
        @NotNull
        private final String messageAuth;

        public MessageParams(@NotNull @Json(name = "messageAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "messageAuth");
            this.messageAuth = str;
        }

        public static /* synthetic */ MessageParams copy$default(MessageParams messageParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = messageParams.messageAuth;
            }
            return messageParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.messageAuth;
        }

        @NotNull
        public final MessageParams copy(@NotNull @Json(name = "messageAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "messageAuth");
            return new MessageParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MessageParams) && Intrinsics.areEqual((Object) this.messageAuth, (Object) ((MessageParams) obj).messageAuth);
        }

        @NotNull
        public final String getMessageAuth() {
            return this.messageAuth;
        }

        public int hashCode() {
            return this.messageAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("MessageParams(messageAuth=", this.messageAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$SubscribeParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "subscriptionAuth", "", "<init>", "(Ljava/lang/String;)V", "getSubscriptionAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SubscribeParams implements CoreNotifyParams {
        @NotNull
        private final String subscriptionAuth;

        public SubscribeParams(@NotNull @Json(name = "subscriptionAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "subscriptionAuth");
            this.subscriptionAuth = str;
        }

        public static /* synthetic */ SubscribeParams copy$default(SubscribeParams subscribeParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = subscribeParams.subscriptionAuth;
            }
            return subscribeParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.subscriptionAuth;
        }

        @NotNull
        public final SubscribeParams copy(@NotNull @Json(name = "subscriptionAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "subscriptionAuth");
            return new SubscribeParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SubscribeParams) && Intrinsics.areEqual((Object) this.subscriptionAuth, (Object) ((SubscribeParams) obj).subscriptionAuth);
        }

        @NotNull
        public final String getSubscriptionAuth() {
            return this.subscriptionAuth;
        }

        public int hashCode() {
            return this.subscriptionAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("SubscribeParams(subscriptionAuth=", this.subscriptionAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$SubscriptionsChangedParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "subscriptionsChangedAuth", "", "<init>", "(Ljava/lang/String;)V", "getSubscriptionsChangedAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SubscriptionsChangedParams implements CoreNotifyParams {
        @NotNull
        private final String subscriptionsChangedAuth;

        public SubscriptionsChangedParams(@NotNull @Json(name = "subscriptionsChangedAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "subscriptionsChangedAuth");
            this.subscriptionsChangedAuth = str;
        }

        public static /* synthetic */ SubscriptionsChangedParams copy$default(SubscriptionsChangedParams subscriptionsChangedParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = subscriptionsChangedParams.subscriptionsChangedAuth;
            }
            return subscriptionsChangedParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.subscriptionsChangedAuth;
        }

        @NotNull
        public final SubscriptionsChangedParams copy(@NotNull @Json(name = "subscriptionsChangedAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "subscriptionsChangedAuth");
            return new SubscriptionsChangedParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SubscriptionsChangedParams) && Intrinsics.areEqual((Object) this.subscriptionsChangedAuth, (Object) ((SubscriptionsChangedParams) obj).subscriptionsChangedAuth);
        }

        @NotNull
        public final String getSubscriptionsChangedAuth() {
            return this.subscriptionsChangedAuth;
        }

        public int hashCode() {
            return this.subscriptionsChangedAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("SubscriptionsChangedParams(subscriptionsChangedAuth=", this.subscriptionsChangedAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$UpdateParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "updateAuth", "", "<init>", "(Ljava/lang/String;)V", "getUpdateAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateParams implements CoreNotifyParams {
        @NotNull
        private final String updateAuth;

        public UpdateParams(@NotNull @Json(name = "updateAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "updateAuth");
            this.updateAuth = str;
        }

        public static /* synthetic */ UpdateParams copy$default(UpdateParams updateParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = updateParams.updateAuth;
            }
            return updateParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.updateAuth;
        }

        @NotNull
        public final UpdateParams copy(@NotNull @Json(name = "updateAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "updateAuth");
            return new UpdateParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof UpdateParams) && Intrinsics.areEqual((Object) this.updateAuth, (Object) ((UpdateParams) obj).updateAuth);
        }

        @NotNull
        public final String getUpdateAuth() {
            return this.updateAuth;
        }

        public int hashCode() {
            return this.updateAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("UpdateParams(updateAuth=", this.updateAuth, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/model/params/CoreNotifyParams$WatchSubscriptionsParams;", "Lcom/reown/android/internal/common/model/params/CoreNotifyParams;", "watchSubscriptionsAuth", "", "<init>", "(Ljava/lang/String;)V", "getWatchSubscriptionsAuth", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WatchSubscriptionsParams implements CoreNotifyParams {
        @NotNull
        private final String watchSubscriptionsAuth;

        public WatchSubscriptionsParams(@NotNull @Json(name = "watchSubscriptionsAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "watchSubscriptionsAuth");
            this.watchSubscriptionsAuth = str;
        }

        public static /* synthetic */ WatchSubscriptionsParams copy$default(WatchSubscriptionsParams watchSubscriptionsParams, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = watchSubscriptionsParams.watchSubscriptionsAuth;
            }
            return watchSubscriptionsParams.copy(str);
        }

        @NotNull
        public final String component1() {
            return this.watchSubscriptionsAuth;
        }

        @NotNull
        public final WatchSubscriptionsParams copy(@NotNull @Json(name = "watchSubscriptionsAuth") String str) {
            Intrinsics.checkNotNullParameter(str, "watchSubscriptionsAuth");
            return new WatchSubscriptionsParams(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WatchSubscriptionsParams) && Intrinsics.areEqual((Object) this.watchSubscriptionsAuth, (Object) ((WatchSubscriptionsParams) obj).watchSubscriptionsAuth);
        }

        @NotNull
        public final String getWatchSubscriptionsAuth() {
            return this.watchSubscriptionsAuth;
        }

        public int hashCode() {
            return this.watchSubscriptionsAuth.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("WatchSubscriptionsParams(watchSubscriptionsAuth=", this.watchSubscriptionsAuth, ")");
        }
    }
}
