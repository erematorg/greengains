package com.reown.android;

import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.state.b;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.push.notifications.PushMessagingService;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/reown/android/Core;", "", "<init>", "()V", "Listeners", "Model", "Params", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Core {
    @NotNull
    public static final Core INSTANCE = new Core();

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0001\u0001\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/reown/android/Core$Listeners;", "", "PairingPing", "Lcom/reown/android/Core$Listeners$PairingPing;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listeners {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/android/Core$Listeners$PairingPing;", "Lcom/reown/android/Core$Listeners;", "onSuccess", "", "pingSuccess", "Lcom/reown/android/Core$Model$Ping$Success;", "onError", "pingError", "Lcom/reown/android/Core$Model$Ping$Error;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface PairingPing extends Listeners {
            void onError(@NotNull Model.Ping.Error error);

            void onSuccess(@NotNull Model.Ping.Success success);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\t\u0004\u0005\u0006\u0007\b\t\n\u000b\fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\n\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/reown/android/Core$Model;", "", "<init>", "()V", "Error", "Ping", "AppMetaData", "DeletedPairing", "ExpiredPairing", "PairingState", "Pairing", "Namespace", "Message", "Lcom/reown/android/Core$Model$AppMetaData;", "Lcom/reown/android/Core$Model$DeletedPairing;", "Lcom/reown/android/Core$Model$Error;", "Lcom/reown/android/Core$Model$ExpiredPairing;", "Lcom/reown/android/Core$Model$Message;", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "Lcom/reown/android/Core$Model$Namespace;", "Lcom/reown/android/Core$Model$Pairing;", "Lcom/reown/android/Core$Model$PairingState;", "Lcom/reown/android/Core$Model$Ping;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Model {

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003Je\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006)"}, d2 = {"Lcom/reown/android/Core$Model$AppMetaData;", "Lcom/reown/android/Core$Model;", "name", "", "description", "url", "icons", "", "redirect", "appLink", "linkMode", "", "verifyUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getAppLink", "getLinkMode", "()Z", "getVerifyUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AppMetaData extends Model {
            @Nullable
            private final String appLink;
            @NotNull
            private final String description;
            @NotNull
            private final List<String> icons;
            private final boolean linkMode;
            @NotNull
            private final String name;
            @Nullable
            private final String redirect;
            @NotNull
            private final String url;
            @Nullable
            private final String verifyUrl;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ AppMetaData(java.lang.String r13, java.lang.String r14, java.lang.String r15, java.util.List r16, java.lang.String r17, java.lang.String r18, boolean r19, java.lang.String r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
                /*
                    r12 = this;
                    r0 = r21
                    r1 = r0 & 32
                    r2 = 0
                    if (r1 == 0) goto L_0x0009
                    r9 = r2
                    goto L_0x000b
                L_0x0009:
                    r9 = r18
                L_0x000b:
                    r1 = r0 & 64
                    if (r1 == 0) goto L_0x0012
                    r1 = 0
                    r10 = r1
                    goto L_0x0014
                L_0x0012:
                    r10 = r19
                L_0x0014:
                    r0 = r0 & 128(0x80, float:1.794E-43)
                    if (r0 == 0) goto L_0x001a
                    r11 = r2
                    goto L_0x001c
                L_0x001a:
                    r11 = r20
                L_0x001c:
                    r3 = r12
                    r4 = r13
                    r5 = r14
                    r6 = r15
                    r7 = r16
                    r8 = r17
                    r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.android.Core.Model.AppMetaData.<init>(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.String, boolean, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
            }

            public static /* synthetic */ AppMetaData copy$default(AppMetaData appMetaData, String str, String str2, String str3, List list, String str4, String str5, boolean z2, String str6, int i3, Object obj) {
                AppMetaData appMetaData2 = appMetaData;
                int i4 = i3;
                return appMetaData.copy((i4 & 1) != 0 ? appMetaData2.name : str, (i4 & 2) != 0 ? appMetaData2.description : str2, (i4 & 4) != 0 ? appMetaData2.url : str3, (i4 & 8) != 0 ? appMetaData2.icons : list, (i4 & 16) != 0 ? appMetaData2.redirect : str4, (i4 & 32) != 0 ? appMetaData2.appLink : str5, (i4 & 64) != 0 ? appMetaData2.linkMode : z2, (i4 & 128) != 0 ? appMetaData2.verifyUrl : str6);
            }

            @NotNull
            public final String component1() {
                return this.name;
            }

            @NotNull
            public final String component2() {
                return this.description;
            }

            @NotNull
            public final String component3() {
                return this.url;
            }

            @NotNull
            public final List<String> component4() {
                return this.icons;
            }

            @Nullable
            public final String component5() {
                return this.redirect;
            }

            @Nullable
            public final String component6() {
                return this.appLink;
            }

            public final boolean component7() {
                return this.linkMode;
            }

            @Nullable
            public final String component8() {
                return this.verifyUrl;
            }

            @NotNull
            public final AppMetaData copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @Nullable String str4, @Nullable String str5, boolean z2, @Nullable String str6) {
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "description");
                Intrinsics.checkNotNullParameter(str3, "url");
                Intrinsics.checkNotNullParameter(list, "icons");
                return new AppMetaData(str, str2, str3, list, str4, str5, z2, str6);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof AppMetaData)) {
                    return false;
                }
                AppMetaData appMetaData = (AppMetaData) obj;
                return Intrinsics.areEqual((Object) this.name, (Object) appMetaData.name) && Intrinsics.areEqual((Object) this.description, (Object) appMetaData.description) && Intrinsics.areEqual((Object) this.url, (Object) appMetaData.url) && Intrinsics.areEqual((Object) this.icons, (Object) appMetaData.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) appMetaData.redirect) && Intrinsics.areEqual((Object) this.appLink, (Object) appMetaData.appLink) && this.linkMode == appMetaData.linkMode && Intrinsics.areEqual((Object) this.verifyUrl, (Object) appMetaData.verifyUrl);
            }

            @Nullable
            public final String getAppLink() {
                return this.appLink;
            }

            @NotNull
            public final String getDescription() {
                return this.description;
            }

            @NotNull
            public final List<String> getIcons() {
                return this.icons;
            }

            public final boolean getLinkMode() {
                return this.linkMode;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @Nullable
            public final String getRedirect() {
                return this.redirect;
            }

            @NotNull
            public final String getUrl() {
                return this.url;
            }

            @Nullable
            public final String getVerifyUrl() {
                return this.verifyUrl;
            }

            public int hashCode() {
                int j2 = a.j(this.icons, a.i(this.url, a.i(this.description, this.name.hashCode() * 31, 31), 31), 31);
                String str = this.redirect;
                int i3 = 0;
                int hashCode = (j2 + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.appLink;
                int f2 = android.support.v4.media.session.a.f(this.linkMode, (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31);
                String str3 = this.verifyUrl;
                if (str3 != null) {
                    i3 = str3.hashCode();
                }
                return f2 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.name;
                String str2 = this.description;
                String str3 = this.url;
                List<String> list = this.icons;
                String str4 = this.redirect;
                String str5 = this.appLink;
                boolean z2 = this.linkMode;
                String str6 = this.verifyUrl;
                StringBuilder l2 = C0118y.l("AppMetaData(name=", str, ", description=", str2, ", url=");
                l2.append(str3);
                l2.append(", icons=");
                l2.append(list);
                l2.append(", redirect=");
                b.w(l2, str4, ", appLink=", str5, ", linkMode=");
                l2.append(z2);
                l2.append(", verifyUrl=");
                l2.append(str6);
                l2.append(")");
                return l2.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public AppMetaData(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @Nullable String str4, @Nullable String str5, boolean z2, @Nullable String str6) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "description");
                Intrinsics.checkNotNullParameter(str3, "url");
                Intrinsics.checkNotNullParameter(list, "icons");
                this.name = str;
                this.description = str2;
                this.url = str3;
                this.icons = list;
                this.redirect = str4;
                this.appLink = str5;
                this.linkMode = z2;
                this.verifyUrl = str6;
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/android/Core$Model$DeletedPairing;", "Lcom/reown/android/Core$Model;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @Deprecated(message = "DeletedPairing has been deprecated. It will be removed soon.")
        public static final class DeletedPairing extends Model {
            @NotNull
            private final String reason;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DeletedPairing(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "reason");
                this.topic = str;
                this.reason = str2;
            }

            public static /* synthetic */ DeletedPairing copy$default(DeletedPairing deletedPairing, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = deletedPairing.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = deletedPairing.reason;
                }
                return deletedPairing.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final DeletedPairing copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "reason");
                return new DeletedPairing(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DeletedPairing)) {
                    return false;
                }
                DeletedPairing deletedPairing = (DeletedPairing) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) deletedPairing.topic) && Intrinsics.areEqual((Object) this.reason, (Object) deletedPairing.reason);
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.reason.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("DeletedPairing(topic=", this.topic, ", reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/Core$Model$Error;", "Lcom/reown/android/Core$Model;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends Model {
            @NotNull
            private final Throwable throwable;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(@NotNull Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    th = error.throwable;
                }
                return error.copy(th);
            }

            @NotNull
            public final Throwable component1() {
                return this.throwable;
            }

            @NotNull
            public final Error copy(@NotNull Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new Error(th);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Error) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Error) obj).throwable);
            }

            @NotNull
            public final Throwable getThrowable() {
                return this.throwable;
            }

            public int hashCode() {
                return this.throwable.hashCode();
            }

            @NotNull
            public String toString() {
                Throwable th = this.throwable;
                return "Error(throwable=" + th + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/Core$Model$ExpiredPairing;", "Lcom/reown/android/Core$Model;", "pairing", "Lcom/reown/android/Core$Model$Pairing;", "<init>", "(Lcom/reown/android/Core$Model$Pairing;)V", "getPairing", "()Lcom/reown/android/Core$Model$Pairing;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @Deprecated(message = "ExpiredPairing has been deprecated. It will be removed soon.")
        public static final class ExpiredPairing extends Model {
            @NotNull
            private final Pairing pairing;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExpiredPairing(@NotNull Pairing pairing2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                this.pairing = pairing2;
            }

            public static /* synthetic */ ExpiredPairing copy$default(ExpiredPairing expiredPairing, Pairing pairing2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    pairing2 = expiredPairing.pairing;
                }
                return expiredPairing.copy(pairing2);
            }

            @NotNull
            public final Pairing component1() {
                return this.pairing;
            }

            @NotNull
            public final ExpiredPairing copy(@NotNull Pairing pairing2) {
                Intrinsics.checkNotNullParameter(pairing2, "pairing");
                return new ExpiredPairing(pairing2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ExpiredPairing) && Intrinsics.areEqual((Object) this.pairing, (Object) ((ExpiredPairing) obj).pairing);
            }

            @NotNull
            public final Pairing getPairing() {
                return this.pairing;
            }

            public int hashCode() {
                return this.pairing.hashCode();
            }

            @NotNull
            public String toString() {
                Pairing pairing2 = this.pairing;
                return "ExpiredPairing(pairing=" + pairing2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/android/Core$Model$Message;", "Lcom/reown/android/Core$Model;", "<init>", "()V", "Simple", "Notify", "SessionProposal", "SessionRequest", "SessionAuthenticate", "Lcom/reown/android/Core$Model$Message$Notify;", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate;", "Lcom/reown/android/Core$Model$Message$SessionProposal;", "Lcom/reown/android/Core$Model$Message$SessionRequest;", "Lcom/reown/android/Core$Model$Message$Simple;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Message extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/reown/android/Core$Model$Message$Notify;", "Lcom/reown/android/Core$Model$Message;", "title", "", "body", "url", "type", "topic", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getBody", "getUrl", "getType", "getTopic", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Notify extends Message {
                @NotNull
                private final String body;
                @NotNull
                private final String title;
                @NotNull
                private final String topic;
                @NotNull
                private final String type;
                @Nullable
                private final String url;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Notify(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "title");
                    Intrinsics.checkNotNullParameter(str2, "body");
                    Intrinsics.checkNotNullParameter(str4, "type");
                    Intrinsics.checkNotNullParameter(str5, PushMessagingService.KEY_TOPIC);
                    this.title = str;
                    this.body = str2;
                    this.url = str3;
                    this.type = str4;
                    this.topic = str5;
                }

                public static /* synthetic */ Notify copy$default(Notify notify, String str, String str2, String str3, String str4, String str5, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = notify.title;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = notify.body;
                    }
                    String str6 = str2;
                    if ((i3 & 4) != 0) {
                        str3 = notify.url;
                    }
                    String str7 = str3;
                    if ((i3 & 8) != 0) {
                        str4 = notify.type;
                    }
                    String str8 = str4;
                    if ((i3 & 16) != 0) {
                        str5 = notify.topic;
                    }
                    return notify.copy(str, str6, str7, str8, str5);
                }

                @NotNull
                public final String component1() {
                    return this.title;
                }

                @NotNull
                public final String component2() {
                    return this.body;
                }

                @Nullable
                public final String component3() {
                    return this.url;
                }

                @NotNull
                public final String component4() {
                    return this.type;
                }

                @NotNull
                public final String component5() {
                    return this.topic;
                }

                @NotNull
                public final Notify copy(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5) {
                    Intrinsics.checkNotNullParameter(str, "title");
                    Intrinsics.checkNotNullParameter(str2, "body");
                    Intrinsics.checkNotNullParameter(str4, "type");
                    Intrinsics.checkNotNullParameter(str5, PushMessagingService.KEY_TOPIC);
                    return new Notify(str, str2, str3, str4, str5);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Notify)) {
                        return false;
                    }
                    Notify notify = (Notify) obj;
                    return Intrinsics.areEqual((Object) this.title, (Object) notify.title) && Intrinsics.areEqual((Object) this.body, (Object) notify.body) && Intrinsics.areEqual((Object) this.url, (Object) notify.url) && Intrinsics.areEqual((Object) this.type, (Object) notify.type) && Intrinsics.areEqual((Object) this.topic, (Object) notify.topic);
                }

                @NotNull
                public final String getBody() {
                    return this.body;
                }

                @NotNull
                public final String getTitle() {
                    return this.title;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                @NotNull
                public final String getType() {
                    return this.type;
                }

                @Nullable
                public final String getUrl() {
                    return this.url;
                }

                public int hashCode() {
                    int i3 = a.i(this.body, this.title.hashCode() * 31, 31);
                    String str = this.url;
                    return this.topic.hashCode() + a.i(this.type, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
                }

                @NotNull
                public String toString() {
                    String str = this.title;
                    String str2 = this.body;
                    String str3 = this.url;
                    String str4 = this.type;
                    String str5 = this.topic;
                    StringBuilder l2 = C0118y.l("Notify(title=", str, ", body=", str2, ", url=");
                    b.w(l2, str3, ", type=", str4, ", topic=");
                    return A.a.n(l2, str5, ")");
                }
            }

            @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001#B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/reown/android/Core$Model$Message$SessionAuthenticate;", "Lcom/reown/android/Core$Model$Message;", "id", "", "topic", "", "metadata", "Lcom/reown/android/Core$Model$AppMetaData;", "payloadParams", "Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "expiry", "<init>", "(JLjava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;J)V", "getId", "()J", "getTopic", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/Core$Model$AppMetaData;", "getPayloadParams", "()Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "getExpiry", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "PayloadParams", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionAuthenticate extends Message {
                private final long expiry;
                private final long id;
                @NotNull
                private final AppMetaData metadata;
                @NotNull
                private final PayloadParams payloadParams;
                @NotNull
                private final String topic;

                @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b(\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bw\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0004HÆ\u0003J\u0001\u0010+\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0004HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014¨\u00063"}, d2 = {"Lcom/reown/android/Core$Model$Message$SessionAuthenticate$PayloadParams;", "Lcom/reown/android/Core$Model;", "chains", "", "", "domain", "nonce", "aud", "type", "nbf", "exp", "statement", "requestId", "resources", "iat", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getChains", "()Ljava/util/List;", "getDomain", "()Ljava/lang/String;", "getNonce", "getAud", "getType", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "setResources", "(Ljava/util/List;)V", "getIat", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class PayloadParams extends Model {
                    @NotNull
                    private final String aud;
                    @NotNull
                    private final List<String> chains;
                    @NotNull
                    private final String domain;
                    @Nullable
                    private final String exp;
                    @NotNull
                    private final String iat;
                    @Nullable
                    private final String nbf;
                    @NotNull
                    private final String nonce;
                    @Nullable
                    private final String requestId;
                    @Nullable
                    private List<String> resources;
                    @Nullable
                    private final String statement;
                    @Nullable
                    private final String type;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public PayloadParams(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list2, @NotNull String str9) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(list, "chains");
                        Intrinsics.checkNotNullParameter(str, "domain");
                        Intrinsics.checkNotNullParameter(str2, "nonce");
                        Intrinsics.checkNotNullParameter(str3, "aud");
                        Intrinsics.checkNotNullParameter(str9, "iat");
                        this.chains = list;
                        this.domain = str;
                        this.nonce = str2;
                        this.aud = str3;
                        this.type = str4;
                        this.nbf = str5;
                        this.exp = str6;
                        this.statement = str7;
                        this.requestId = str8;
                        this.resources = list2;
                        this.iat = str9;
                    }

                    public static /* synthetic */ PayloadParams copy$default(PayloadParams payloadParams, List list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, List list2, String str9, int i3, Object obj) {
                        PayloadParams payloadParams2 = payloadParams;
                        int i4 = i3;
                        return payloadParams.copy((i4 & 1) != 0 ? payloadParams2.chains : list, (i4 & 2) != 0 ? payloadParams2.domain : str, (i4 & 4) != 0 ? payloadParams2.nonce : str2, (i4 & 8) != 0 ? payloadParams2.aud : str3, (i4 & 16) != 0 ? payloadParams2.type : str4, (i4 & 32) != 0 ? payloadParams2.nbf : str5, (i4 & 64) != 0 ? payloadParams2.exp : str6, (i4 & 128) != 0 ? payloadParams2.statement : str7, (i4 & 256) != 0 ? payloadParams2.requestId : str8, (i4 & 512) != 0 ? payloadParams2.resources : list2, (i4 & 1024) != 0 ? payloadParams2.iat : str9);
                    }

                    @NotNull
                    public final List<String> component1() {
                        return this.chains;
                    }

                    @Nullable
                    public final List<String> component10() {
                        return this.resources;
                    }

                    @NotNull
                    public final String component11() {
                        return this.iat;
                    }

                    @NotNull
                    public final String component2() {
                        return this.domain;
                    }

                    @NotNull
                    public final String component3() {
                        return this.nonce;
                    }

                    @NotNull
                    public final String component4() {
                        return this.aud;
                    }

                    @Nullable
                    public final String component5() {
                        return this.type;
                    }

                    @Nullable
                    public final String component6() {
                        return this.nbf;
                    }

                    @Nullable
                    public final String component7() {
                        return this.exp;
                    }

                    @Nullable
                    public final String component8() {
                        return this.statement;
                    }

                    @Nullable
                    public final String component9() {
                        return this.requestId;
                    }

                    @NotNull
                    public final PayloadParams copy(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable List<String> list2, @NotNull String str9) {
                        Intrinsics.checkNotNullParameter(list, "chains");
                        Intrinsics.checkNotNullParameter(str, "domain");
                        String str10 = str2;
                        Intrinsics.checkNotNullParameter(str10, "nonce");
                        String str11 = str3;
                        Intrinsics.checkNotNullParameter(str11, "aud");
                        String str12 = str9;
                        Intrinsics.checkNotNullParameter(str12, "iat");
                        return new PayloadParams(list, str, str10, str11, str4, str5, str6, str7, str8, list2, str12);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (!(obj instanceof PayloadParams)) {
                            return false;
                        }
                        PayloadParams payloadParams = (PayloadParams) obj;
                        return Intrinsics.areEqual((Object) this.chains, (Object) payloadParams.chains) && Intrinsics.areEqual((Object) this.domain, (Object) payloadParams.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadParams.nonce) && Intrinsics.areEqual((Object) this.aud, (Object) payloadParams.aud) && Intrinsics.areEqual((Object) this.type, (Object) payloadParams.type) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadParams.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payloadParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadParams.resources) && Intrinsics.areEqual((Object) this.iat, (Object) payloadParams.iat);
                    }

                    @NotNull
                    public final String getAud() {
                        return this.aud;
                    }

                    @NotNull
                    public final List<String> getChains() {
                        return this.chains;
                    }

                    @NotNull
                    public final String getDomain() {
                        return this.domain;
                    }

                    @Nullable
                    public final String getExp() {
                        return this.exp;
                    }

                    @NotNull
                    public final String getIat() {
                        return this.iat;
                    }

                    @Nullable
                    public final String getNbf() {
                        return this.nbf;
                    }

                    @NotNull
                    public final String getNonce() {
                        return this.nonce;
                    }

                    @Nullable
                    public final String getRequestId() {
                        return this.requestId;
                    }

                    @Nullable
                    public final List<String> getResources() {
                        return this.resources;
                    }

                    @Nullable
                    public final String getStatement() {
                        return this.statement;
                    }

                    @Nullable
                    public final String getType() {
                        return this.type;
                    }

                    public int hashCode() {
                        int i3 = a.i(this.aud, a.i(this.nonce, a.i(this.domain, this.chains.hashCode() * 31, 31), 31), 31);
                        String str = this.type;
                        int i4 = 0;
                        int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
                        String str2 = this.nbf;
                        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                        String str3 = this.exp;
                        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                        String str4 = this.statement;
                        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                        String str5 = this.requestId;
                        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
                        List<String> list = this.resources;
                        if (list != null) {
                            i4 = list.hashCode();
                        }
                        return this.iat.hashCode() + ((hashCode5 + i4) * 31);
                    }

                    public final void setResources(@Nullable List<String> list) {
                        this.resources = list;
                    }

                    @NotNull
                    public String toString() {
                        List<String> list = this.chains;
                        String str = this.domain;
                        String str2 = this.nonce;
                        String str3 = this.aud;
                        String str4 = this.type;
                        String str5 = this.nbf;
                        String str6 = this.exp;
                        String str7 = this.statement;
                        String str8 = this.requestId;
                        List<String> list2 = this.resources;
                        String str9 = this.iat;
                        StringBuilder sb = new StringBuilder("PayloadParams(chains=");
                        sb.append(list);
                        sb.append(", domain=");
                        sb.append(str);
                        sb.append(", nonce=");
                        b.w(sb, str2, ", aud=", str3, ", type=");
                        b.w(sb, str4, ", nbf=", str5, ", exp=");
                        b.w(sb, str6, ", statement=", str7, ", requestId=");
                        sb.append(str8);
                        sb.append(", resources=");
                        sb.append(list2);
                        sb.append(", iat=");
                        return A.a.n(sb, str9, ")");
                    }
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionAuthenticate(long j2, @NotNull String str, @NotNull AppMetaData appMetaData, @NotNull PayloadParams payloadParams2, long j3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                    Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                    this.id = j2;
                    this.topic = str;
                    this.metadata = appMetaData;
                    this.payloadParams = payloadParams2;
                    this.expiry = j3;
                }

                public static /* synthetic */ SessionAuthenticate copy$default(SessionAuthenticate sessionAuthenticate, long j2, String str, AppMetaData appMetaData, PayloadParams payloadParams2, long j3, int i3, Object obj) {
                    SessionAuthenticate sessionAuthenticate2 = sessionAuthenticate;
                    return sessionAuthenticate.copy((i3 & 1) != 0 ? sessionAuthenticate2.id : j2, (i3 & 2) != 0 ? sessionAuthenticate2.topic : str, (i3 & 4) != 0 ? sessionAuthenticate2.metadata : appMetaData, (i3 & 8) != 0 ? sessionAuthenticate2.payloadParams : payloadParams2, (i3 & 16) != 0 ? sessionAuthenticate2.expiry : j3);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final String component2() {
                    return this.topic;
                }

                @NotNull
                public final AppMetaData component3() {
                    return this.metadata;
                }

                @NotNull
                public final PayloadParams component4() {
                    return this.payloadParams;
                }

                public final long component5() {
                    return this.expiry;
                }

                @NotNull
                public final SessionAuthenticate copy(long j2, @NotNull String str, @NotNull AppMetaData appMetaData, @NotNull PayloadParams payloadParams2, long j3) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                    Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                    return new SessionAuthenticate(j2, str, appMetaData, payloadParams2, j3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionAuthenticate)) {
                        return false;
                    }
                    SessionAuthenticate sessionAuthenticate = (SessionAuthenticate) obj;
                    return this.id == sessionAuthenticate.id && Intrinsics.areEqual((Object) this.topic, (Object) sessionAuthenticate.topic) && Intrinsics.areEqual((Object) this.metadata, (Object) sessionAuthenticate.metadata) && Intrinsics.areEqual((Object) this.payloadParams, (Object) sessionAuthenticate.payloadParams) && this.expiry == sessionAuthenticate.expiry;
                }

                public final long getExpiry() {
                    return this.expiry;
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final AppMetaData getMetadata() {
                    return this.metadata;
                }

                @NotNull
                public final PayloadParams getPayloadParams() {
                    return this.payloadParams;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    int i3 = a.i(this.topic, Long.hashCode(this.id) * 31, 31);
                    int hashCode = this.payloadParams.hashCode();
                    return Long.hashCode(this.expiry) + ((hashCode + ((this.metadata.hashCode() + i3) * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.topic;
                    AppMetaData appMetaData = this.metadata;
                    PayloadParams payloadParams2 = this.payloadParams;
                    long j3 = this.expiry;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticate(id=", ", topic=", str);
                    v2.append(", metadata=");
                    v2.append(appMetaData);
                    v2.append(", payloadParams=");
                    v2.append(payloadParams2);
                    v2.append(", expiry=");
                    v2.append(j3);
                    v2.append(")");
                    return v2.toString();
                }
            }

            @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0017\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¹\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006<"}, d2 = {"Lcom/reown/android/Core$Model$Message$SessionProposal;", "Lcom/reown/android/Core$Model$Message;", "id", "", "pairingTopic", "", "name", "description", "url", "icons", "", "redirect", "requiredNamespaces", "", "Lcom/reown/android/Core$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionProposal extends Message {
                @NotNull
                private final String description;
                @NotNull
                private final List<String> icons;
                private final long id;
                @NotNull
                private final String name;
                @NotNull
                private final Map<String, Namespace.Proposal> optionalNamespaces;
                @NotNull
                private final String pairingTopic;
                @Nullable
                private final Map<String, String> properties;
                @NotNull
                private final String proposerPublicKey;
                @NotNull
                private final String redirect;
                @Nullable
                private final String relayData;
                @NotNull
                private final String relayProtocol;
                @NotNull
                private final Map<String, Namespace.Proposal> requiredNamespaces;
                @NotNull
                private final String url;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionProposal(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "pairingTopic");
                    Intrinsics.checkNotNullParameter(str2, "name");
                    Intrinsics.checkNotNullParameter(str3, "description");
                    Intrinsics.checkNotNullParameter(str4, "url");
                    Intrinsics.checkNotNullParameter(list, "icons");
                    Intrinsics.checkNotNullParameter(str5, "redirect");
                    Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                    Intrinsics.checkNotNullParameter(map2, "optionalNamespaces");
                    Intrinsics.checkNotNullParameter(str6, "proposerPublicKey");
                    Intrinsics.checkNotNullParameter(str7, "relayProtocol");
                    this.id = j2;
                    this.pairingTopic = str;
                    this.name = str2;
                    this.description = str3;
                    this.url = str4;
                    this.icons = list;
                    this.redirect = str5;
                    this.requiredNamespaces = map;
                    this.optionalNamespaces = map2;
                    this.properties = map3;
                    this.proposerPublicKey = str6;
                    this.relayProtocol = str7;
                    this.relayData = str8;
                }

                public static /* synthetic */ SessionProposal copy$default(SessionProposal sessionProposal, long j2, String str, String str2, String str3, String str4, List list, String str5, Map map, Map map2, Map map3, String str6, String str7, String str8, int i3, Object obj) {
                    SessionProposal sessionProposal2 = sessionProposal;
                    int i4 = i3;
                    return sessionProposal.copy((i4 & 1) != 0 ? sessionProposal2.id : j2, (i4 & 2) != 0 ? sessionProposal2.pairingTopic : str, (i4 & 4) != 0 ? sessionProposal2.name : str2, (i4 & 8) != 0 ? sessionProposal2.description : str3, (i4 & 16) != 0 ? sessionProposal2.url : str4, (i4 & 32) != 0 ? sessionProposal2.icons : list, (i4 & 64) != 0 ? sessionProposal2.redirect : str5, (i4 & 128) != 0 ? sessionProposal2.requiredNamespaces : map, (i4 & 256) != 0 ? sessionProposal2.optionalNamespaces : map2, (i4 & 512) != 0 ? sessionProposal2.properties : map3, (i4 & 1024) != 0 ? sessionProposal2.proposerPublicKey : str6, (i4 & 2048) != 0 ? sessionProposal2.relayProtocol : str7, (i4 & 4096) != 0 ? sessionProposal2.relayData : str8);
                }

                public final long component1() {
                    return this.id;
                }

                @Nullable
                public final Map<String, String> component10() {
                    return this.properties;
                }

                @NotNull
                public final String component11() {
                    return this.proposerPublicKey;
                }

                @NotNull
                public final String component12() {
                    return this.relayProtocol;
                }

                @Nullable
                public final String component13() {
                    return this.relayData;
                }

                @NotNull
                public final String component2() {
                    return this.pairingTopic;
                }

                @NotNull
                public final String component3() {
                    return this.name;
                }

                @NotNull
                public final String component4() {
                    return this.description;
                }

                @NotNull
                public final String component5() {
                    return this.url;
                }

                @NotNull
                public final List<String> component6() {
                    return this.icons;
                }

                @NotNull
                public final String component7() {
                    return this.redirect;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> component8() {
                    return this.requiredNamespaces;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> component9() {
                    return this.optionalNamespaces;
                }

                @NotNull
                public final SessionProposal copy(long j2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8) {
                    String str9 = str;
                    Intrinsics.checkNotNullParameter(str9, "pairingTopic");
                    String str10 = str2;
                    Intrinsics.checkNotNullParameter(str10, "name");
                    String str11 = str3;
                    Intrinsics.checkNotNullParameter(str11, "description");
                    String str12 = str4;
                    Intrinsics.checkNotNullParameter(str12, "url");
                    List<String> list2 = list;
                    Intrinsics.checkNotNullParameter(list2, "icons");
                    String str13 = str5;
                    Intrinsics.checkNotNullParameter(str13, "redirect");
                    Map<String, Namespace.Proposal> map4 = map;
                    Intrinsics.checkNotNullParameter(map4, "requiredNamespaces");
                    Map<String, Namespace.Proposal> map5 = map2;
                    Intrinsics.checkNotNullParameter(map5, "optionalNamespaces");
                    String str14 = str6;
                    Intrinsics.checkNotNullParameter(str14, "proposerPublicKey");
                    String str15 = str7;
                    Intrinsics.checkNotNullParameter(str15, "relayProtocol");
                    return new SessionProposal(j2, str9, str10, str11, str12, list2, str13, map4, map5, map3, str14, str15, str8);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionProposal)) {
                        return false;
                    }
                    SessionProposal sessionProposal = (SessionProposal) obj;
                    return this.id == sessionProposal.id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionProposal.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) sessionProposal.name) && Intrinsics.areEqual((Object) this.description, (Object) sessionProposal.description) && Intrinsics.areEqual((Object) this.url, (Object) sessionProposal.url) && Intrinsics.areEqual((Object) this.icons, (Object) sessionProposal.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) sessionProposal.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposal.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposal.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposal.properties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionProposal.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionProposal.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) sessionProposal.relayData);
                }

                @NotNull
                public final String getDescription() {
                    return this.description;
                }

                @NotNull
                public final List<String> getIcons() {
                    return this.icons;
                }

                public final long getId() {
                    return this.id;
                }

                @NotNull
                public final String getName() {
                    return this.name;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
                    return this.optionalNamespaces;
                }

                @NotNull
                public final String getPairingTopic() {
                    return this.pairingTopic;
                }

                @Nullable
                public final Map<String, String> getProperties() {
                    return this.properties;
                }

                @NotNull
                public final String getProposerPublicKey() {
                    return this.proposerPublicKey;
                }

                @NotNull
                public final String getRedirect() {
                    return this.redirect;
                }

                @Nullable
                public final String getRelayData() {
                    return this.relayData;
                }

                @NotNull
                public final String getRelayProtocol() {
                    return this.relayProtocol;
                }

                @NotNull
                public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
                    return this.requiredNamespaces;
                }

                @NotNull
                public final String getUrl() {
                    return this.url;
                }

                public int hashCode() {
                    int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, a.i(this.redirect, a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, a.i(this.pairingTopic, Long.hashCode(this.id) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
                    Map<String, String> map = this.properties;
                    int i3 = 0;
                    int i4 = a.i(this.relayProtocol, a.i(this.proposerPublicKey, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31), 31);
                    String str = this.relayData;
                    if (str != null) {
                        i3 = str.hashCode();
                    }
                    return i4 + i3;
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    String str = this.pairingTopic;
                    String str2 = this.name;
                    String str3 = this.description;
                    String str4 = this.url;
                    List<String> list = this.icons;
                    String str5 = this.redirect;
                    Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                    Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                    Map<String, String> map3 = this.properties;
                    String str6 = this.proposerPublicKey;
                    String str7 = this.relayProtocol;
                    String str8 = this.relayData;
                    StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionProposal(id=", ", pairingTopic=", str);
                    b.w(v2, ", name=", str2, ", description=", str3);
                    v2.append(", url=");
                    v2.append(str4);
                    v2.append(", icons=");
                    v2.append(list);
                    v2.append(", redirect=");
                    v2.append(str5);
                    v2.append(", requiredNamespaces=");
                    v2.append(map);
                    v2.append(", optionalNamespaces=");
                    v2.append(map2);
                    v2.append(", properties=");
                    v2.append(map3);
                    b.w(v2, ", proposerPublicKey=", str6, ", relayProtocol=", str7);
                    return C0118y.j(v2, ", relayData=", str8, ")");
                }
            }

            @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/android/Core$Model$Message$SessionRequest;", "Lcom/reown/android/Core$Model$Message;", "topic", "", "chainId", "peerMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "request", "Lcom/reown/android/Core$Model$Message$SessionRequest$JSONRPCRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/android/Core$Model$Message$SessionRequest$JSONRPCRequest;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRequest", "()Lcom/reown/android/Core$Model$Message$SessionRequest$JSONRPCRequest;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionRequest extends Message {
                @Nullable
                private final String chainId;
                @Nullable
                private final AppMetaData peerMetaData;
                @NotNull
                private final JSONRPCRequest request;
                @NotNull
                private final String topic;

                @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/android/Core$Model$Message$SessionRequest$JSONRPCRequest;", "", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JSONRPCRequest {
                    private final long id;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final String params;

                    public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                        Intrinsics.checkNotNullParameter(str2, "params");
                        this.id = j2;
                        this.method = str;
                        this.params = str2;
                    }

                    public static /* synthetic */ JSONRPCRequest copy$default(JSONRPCRequest jSONRPCRequest, long j2, String str, String str2, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            j2 = jSONRPCRequest.id;
                        }
                        if ((i3 & 2) != 0) {
                            str = jSONRPCRequest.method;
                        }
                        if ((i3 & 4) != 0) {
                            str2 = jSONRPCRequest.params;
                        }
                        return jSONRPCRequest.copy(j2, str, str2);
                    }

                    public final long component1() {
                        return this.id;
                    }

                    @NotNull
                    public final String component2() {
                        return this.method;
                    }

                    @NotNull
                    public final String component3() {
                        return this.params;
                    }

                    @NotNull
                    public final JSONRPCRequest copy(long j2, @NotNull String str, @NotNull String str2) {
                        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
                        Intrinsics.checkNotNullParameter(str2, "params");
                        return new JSONRPCRequest(j2, str, str2);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (!(obj instanceof JSONRPCRequest)) {
                            return false;
                        }
                        JSONRPCRequest jSONRPCRequest = (JSONRPCRequest) obj;
                        return this.id == jSONRPCRequest.id && Intrinsics.areEqual((Object) this.method, (Object) jSONRPCRequest.method) && Intrinsics.areEqual((Object) this.params, (Object) jSONRPCRequest.params);
                    }

                    public final long getId() {
                        return this.id;
                    }

                    @NotNull
                    public final String getMethod() {
                        return this.method;
                    }

                    @NotNull
                    public final String getParams() {
                        return this.params;
                    }

                    public int hashCode() {
                        return this.params.hashCode() + a.i(this.method, Long.hashCode(this.id) * 31, 31);
                    }

                    @NotNull
                    public String toString() {
                        long j2 = this.id;
                        String str = this.method;
                        return C0118y.j(androidx.work.impl.a.v(j2, "JSONRPCRequest(id=", ", method=", str), ", params=", this.params, ")");
                    }
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                    this.topic = str;
                    this.chainId = str2;
                    this.peerMetaData = appMetaData;
                    this.request = jSONRPCRequest;
                }

                public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = sessionRequest.topic;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = sessionRequest.chainId;
                    }
                    if ((i3 & 4) != 0) {
                        appMetaData = sessionRequest.peerMetaData;
                    }
                    if ((i3 & 8) != 0) {
                        jSONRPCRequest = sessionRequest.request;
                    }
                    return sessionRequest.copy(str, str2, appMetaData, jSONRPCRequest);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @Nullable
                public final String component2() {
                    return this.chainId;
                }

                @Nullable
                public final AppMetaData component3() {
                    return this.peerMetaData;
                }

                @NotNull
                public final JSONRPCRequest component4() {
                    return this.request;
                }

                @NotNull
                public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                    return new SessionRequest(str, str2, appMetaData, jSONRPCRequest);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SessionRequest)) {
                        return false;
                    }
                    SessionRequest sessionRequest = (SessionRequest) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) sessionRequest.topic) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionRequest.chainId) && Intrinsics.areEqual((Object) this.peerMetaData, (Object) sessionRequest.peerMetaData) && Intrinsics.areEqual((Object) this.request, (Object) sessionRequest.request);
                }

                @Nullable
                public final String getChainId() {
                    return this.chainId;
                }

                @Nullable
                public final AppMetaData getPeerMetaData() {
                    return this.peerMetaData;
                }

                @NotNull
                public final JSONRPCRequest getRequest() {
                    return this.request;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    int hashCode = this.topic.hashCode() * 31;
                    String str = this.chainId;
                    int i3 = 0;
                    int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
                    AppMetaData appMetaData = this.peerMetaData;
                    if (appMetaData != null) {
                        i3 = appMetaData.hashCode();
                    }
                    return this.request.hashCode() + ((hashCode2 + i3) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.topic;
                    String str2 = this.chainId;
                    AppMetaData appMetaData = this.peerMetaData;
                    JSONRPCRequest jSONRPCRequest = this.request;
                    StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerMetaData=");
                    l2.append(appMetaData);
                    l2.append(", request=");
                    l2.append(jSONRPCRequest);
                    l2.append(")");
                    return l2.toString();
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/android/Core$Model$Message$Simple;", "Lcom/reown/android/Core$Model$Message;", "title", "", "body", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getBody", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Simple extends Message {
                @NotNull
                private final String body;
                @NotNull
                private final String title;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Simple(@NotNull String str, @NotNull String str2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "title");
                    Intrinsics.checkNotNullParameter(str2, "body");
                    this.title = str;
                    this.body = str2;
                }

                public static /* synthetic */ Simple copy$default(Simple simple, String str, String str2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = simple.title;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = simple.body;
                    }
                    return simple.copy(str, str2);
                }

                @NotNull
                public final String component1() {
                    return this.title;
                }

                @NotNull
                public final String component2() {
                    return this.body;
                }

                @NotNull
                public final Simple copy(@NotNull String str, @NotNull String str2) {
                    Intrinsics.checkNotNullParameter(str, "title");
                    Intrinsics.checkNotNullParameter(str2, "body");
                    return new Simple(str, str2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Simple)) {
                        return false;
                    }
                    Simple simple = (Simple) obj;
                    return Intrinsics.areEqual((Object) this.title, (Object) simple.title) && Intrinsics.areEqual((Object) this.body, (Object) simple.body);
                }

                @NotNull
                public final String getBody() {
                    return this.body;
                }

                @NotNull
                public final String getTitle() {
                    return this.title;
                }

                public int hashCode() {
                    return this.body.hashCode() + (this.title.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    return C0118y.g("Simple(title=", this.title, ", body=", this.body, ")");
                }
            }

            public /* synthetic */ Message(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Message() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/Core$Model$Namespace;", "Lcom/reown/android/Core$Model;", "<init>", "()V", "Proposal", "Session", "Lcom/reown/android/Core$Model$Namespace$Proposal;", "Lcom/reown/android/Core$Model$Namespace$Session;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Namespace extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/android/Core$Model$Namespace$Proposal;", "Lcom/reown/android/Core$Model$Namespace;", "chains", "", "", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Proposal extends Namespace {
                @Nullable
                private final List<String> chains;
                @NotNull
                private final List<String> events;
                @NotNull
                private final List<String> methods;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Proposal(List list, List list2, List list3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? null : list, list2, list3);
                }

                public static /* synthetic */ Proposal copy$default(Proposal proposal, List<String> list, List<String> list2, List<String> list3, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = proposal.chains;
                    }
                    if ((i3 & 2) != 0) {
                        list2 = proposal.methods;
                    }
                    if ((i3 & 4) != 0) {
                        list3 = proposal.events;
                    }
                    return proposal.copy(list, list2, list3);
                }

                @Nullable
                public final List<String> component1() {
                    return this.chains;
                }

                @NotNull
                public final List<String> component2() {
                    return this.methods;
                }

                @NotNull
                public final List<String> component3() {
                    return this.events;
                }

                @NotNull
                public final Proposal copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                    Intrinsics.checkNotNullParameter(list2, "methods");
                    Intrinsics.checkNotNullParameter(list3, "events");
                    return new Proposal(list, list2, list3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Proposal)) {
                        return false;
                    }
                    Proposal proposal = (Proposal) obj;
                    return Intrinsics.areEqual((Object) this.chains, (Object) proposal.chains) && Intrinsics.areEqual((Object) this.methods, (Object) proposal.methods) && Intrinsics.areEqual((Object) this.events, (Object) proposal.events);
                }

                @Nullable
                public final List<String> getChains() {
                    return this.chains;
                }

                @NotNull
                public final List<String> getEvents() {
                    return this.events;
                }

                @NotNull
                public final List<String> getMethods() {
                    return this.methods;
                }

                public int hashCode() {
                    List<String> list = this.chains;
                    return this.events.hashCode() + a.j(this.methods, (list == null ? 0 : list.hashCode()) * 31, 31);
                }

                @NotNull
                public String toString() {
                    List<String> list = this.chains;
                    List<String> list2 = this.methods;
                    List<String> list3 = this.events;
                    StringBuilder sb = new StringBuilder("Proposal(chains=");
                    sb.append(list);
                    sb.append(", methods=");
                    sb.append(list2);
                    sb.append(", events=");
                    return C0118y.h(")", list3, sb);
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Proposal(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list2, "methods");
                    Intrinsics.checkNotNullParameter(list3, "events");
                    this.chains = list;
                    this.methods = list2;
                    this.events = list3;
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/android/Core$Model$Namespace$Session;", "Lcom/reown/android/Core$Model$Namespace;", "chains", "", "", "accounts", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Session extends Namespace {
                @NotNull
                private final List<String> accounts;
                @Nullable
                private final List<String> chains;
                @NotNull
                private final List<String> events;
                @NotNull
                private final List<String> methods;

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Session(List list, List list2, List list3, List list4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i3 & 1) != 0 ? null : list, list2, list3, list4);
                }

                public static /* synthetic */ Session copy$default(Session session, List<String> list, List<String> list2, List<String> list3, List<String> list4, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = session.chains;
                    }
                    if ((i3 & 2) != 0) {
                        list2 = session.accounts;
                    }
                    if ((i3 & 4) != 0) {
                        list3 = session.methods;
                    }
                    if ((i3 & 8) != 0) {
                        list4 = session.events;
                    }
                    return session.copy(list, list2, list3, list4);
                }

                @Nullable
                public final List<String> component1() {
                    return this.chains;
                }

                @NotNull
                public final List<String> component2() {
                    return this.accounts;
                }

                @NotNull
                public final List<String> component3() {
                    return this.methods;
                }

                @NotNull
                public final List<String> component4() {
                    return this.events;
                }

                @NotNull
                public final Session copy(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
                    Intrinsics.checkNotNullParameter(list2, "accounts");
                    Intrinsics.checkNotNullParameter(list3, "methods");
                    Intrinsics.checkNotNullParameter(list4, "events");
                    return new Session(list, list2, list3, list4);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Session)) {
                        return false;
                    }
                    Session session = (Session) obj;
                    return Intrinsics.areEqual((Object) this.chains, (Object) session.chains) && Intrinsics.areEqual((Object) this.accounts, (Object) session.accounts) && Intrinsics.areEqual((Object) this.methods, (Object) session.methods) && Intrinsics.areEqual((Object) this.events, (Object) session.events);
                }

                @NotNull
                public final List<String> getAccounts() {
                    return this.accounts;
                }

                @Nullable
                public final List<String> getChains() {
                    return this.chains;
                }

                @NotNull
                public final List<String> getEvents() {
                    return this.events;
                }

                @NotNull
                public final List<String> getMethods() {
                    return this.methods;
                }

                public int hashCode() {
                    List<String> list = this.chains;
                    return this.events.hashCode() + a.j(this.methods, a.j(this.accounts, (list == null ? 0 : list.hashCode()) * 31, 31), 31);
                }

                @NotNull
                public String toString() {
                    List<String> list = this.chains;
                    List<String> list2 = this.accounts;
                    List<String> list3 = this.methods;
                    List<String> list4 = this.events;
                    return "Session(chains=" + list + ", accounts=" + list2 + ", methods=" + list3 + ", events=" + list4 + ")";
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Session(@Nullable List<String> list, @NotNull List<String> list2, @NotNull List<String> list3, @NotNull List<String> list4) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list2, "accounts");
                    Intrinsics.checkNotNullParameter(list3, "methods");
                    Intrinsics.checkNotNullParameter(list4, "events");
                    this.chains = list;
                    this.accounts = list2;
                    this.methods = list3;
                    this.events = list4;
                }
            }

            public /* synthetic */ Namespace(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Namespace() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\fHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J]\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020\f2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u000b\u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006,"}, d2 = {"Lcom/reown/android/Core$Model$Pairing;", "Lcom/reown/android/Core$Model;", "topic", "", "expiry", "", "peerAppMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "relayProtocol", "relayData", "uri", "isActive", "", "registeredMethods", "<init>", "(Ljava/lang/String;JLcom/reown/android/Core$Model$AppMetaData;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getExpiry", "()J", "getPeerAppMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRelayProtocol", "getRelayData", "getUri", "isActive$annotations", "()V", "()Z", "getRegisteredMethods", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Pairing extends Model {
            private final long expiry;
            private final boolean isActive;
            @Nullable
            private final AppMetaData peerAppMetaData;
            @NotNull
            private final String registeredMethods;
            @Nullable
            private final String relayData;
            @NotNull
            private final String relayProtocol;
            @NotNull
            private final String topic;
            @NotNull
            private final String uri;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Pairing(String str, long j2, AppMetaData appMetaData, String str2, String str3, String str4, boolean z2, String str5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, j2, (i3 & 4) != 0 ? null : appMetaData, str2, str3, str4, z2, str5);
            }

            public static /* synthetic */ Pairing copy$default(Pairing pairing, String str, long j2, AppMetaData appMetaData, String str2, String str3, String str4, boolean z2, String str5, int i3, Object obj) {
                Pairing pairing2 = pairing;
                int i4 = i3;
                return pairing.copy((i4 & 1) != 0 ? pairing2.topic : str, (i4 & 2) != 0 ? pairing2.expiry : j2, (i4 & 4) != 0 ? pairing2.peerAppMetaData : appMetaData, (i4 & 8) != 0 ? pairing2.relayProtocol : str2, (i4 & 16) != 0 ? pairing2.relayData : str3, (i4 & 32) != 0 ? pairing2.uri : str4, (i4 & 64) != 0 ? pairing2.isActive : z2, (i4 & 128) != 0 ? pairing2.registeredMethods : str5);
            }

            @Deprecated(message = "isActive has been deprecated. It will be removed soon.")
            public static /* synthetic */ void isActive$annotations() {
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            public final long component2() {
                return this.expiry;
            }

            @Nullable
            public final AppMetaData component3() {
                return this.peerAppMetaData;
            }

            @NotNull
            public final String component4() {
                return this.relayProtocol;
            }

            @Nullable
            public final String component5() {
                return this.relayData;
            }

            @NotNull
            public final String component6() {
                return this.uri;
            }

            public final boolean component7() {
                return this.isActive;
            }

            @NotNull
            public final String component8() {
                return this.registeredMethods;
            }

            @NotNull
            public final Pairing copy(@NotNull String str, long j2, @Nullable AppMetaData appMetaData, @NotNull String str2, @Nullable String str3, @NotNull String str4, boolean z2, @NotNull String str5) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                String str6 = str2;
                Intrinsics.checkNotNullParameter(str6, "relayProtocol");
                String str7 = str4;
                Intrinsics.checkNotNullParameter(str7, "uri");
                String str8 = str5;
                Intrinsics.checkNotNullParameter(str8, "registeredMethods");
                return new Pairing(str, j2, appMetaData, str6, str3, str7, z2, str8);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Pairing)) {
                    return false;
                }
                Pairing pairing = (Pairing) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) pairing.topic) && this.expiry == pairing.expiry && Intrinsics.areEqual((Object) this.peerAppMetaData, (Object) pairing.peerAppMetaData) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) pairing.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) pairing.relayData) && Intrinsics.areEqual((Object) this.uri, (Object) pairing.uri) && this.isActive == pairing.isActive && Intrinsics.areEqual((Object) this.registeredMethods, (Object) pairing.registeredMethods);
            }

            public final long getExpiry() {
                return this.expiry;
            }

            @Nullable
            public final AppMetaData getPeerAppMetaData() {
                return this.peerAppMetaData;
            }

            @NotNull
            public final String getRegisteredMethods() {
                return this.registeredMethods;
            }

            @Nullable
            public final String getRelayData() {
                return this.relayData;
            }

            @NotNull
            public final String getRelayProtocol() {
                return this.relayProtocol;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            @NotNull
            public final String getUri() {
                return this.uri;
            }

            public int hashCode() {
                int D2 = a.D(this.expiry, this.topic.hashCode() * 31, 31);
                AppMetaData appMetaData = this.peerAppMetaData;
                int i3 = 0;
                int i4 = a.i(this.relayProtocol, (D2 + (appMetaData == null ? 0 : appMetaData.hashCode())) * 31, 31);
                String str = this.relayData;
                if (str != null) {
                    i3 = str.hashCode();
                }
                return this.registeredMethods.hashCode() + android.support.v4.media.session.a.f(this.isActive, a.i(this.uri, (i4 + i3) * 31, 31), 31);
            }

            public final boolean isActive() {
                return this.isActive;
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                long j2 = this.expiry;
                AppMetaData appMetaData = this.peerAppMetaData;
                String str2 = this.relayProtocol;
                String str3 = this.relayData;
                String str4 = this.uri;
                boolean z2 = this.isActive;
                String str5 = this.registeredMethods;
                StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(j2, "Pairing(topic=", str, ", expiry=");
                i3.append(", peerAppMetaData=");
                i3.append(appMetaData);
                i3.append(", relayProtocol=");
                i3.append(str2);
                b.w(i3, ", relayData=", str3, ", uri=", str4);
                i3.append(", isActive=");
                i3.append(z2);
                i3.append(", registeredMethods=");
                i3.append(str5);
                i3.append(")");
                return i3.toString();
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Pairing(@NotNull String str, long j2, @Nullable AppMetaData appMetaData, @NotNull String str2, @Nullable String str3, @NotNull String str4, boolean z2, @NotNull String str5) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "relayProtocol");
                Intrinsics.checkNotNullParameter(str4, "uri");
                Intrinsics.checkNotNullParameter(str5, "registeredMethods");
                this.topic = str;
                this.expiry = j2;
                this.peerAppMetaData = appMetaData;
                this.relayProtocol = str2;
                this.relayData = str3;
                this.uri = str4;
                this.isActive = z2;
                this.registeredMethods = str5;
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/reown/android/Core$Model$PairingState;", "Lcom/reown/android/Core$Model;", "isPairingState", "", "<init>", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PairingState extends Model {
            private final boolean isPairingState;

            public PairingState(boolean z2) {
                super((DefaultConstructorMarker) null);
                this.isPairingState = z2;
            }

            public static /* synthetic */ PairingState copy$default(PairingState pairingState, boolean z2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    z2 = pairingState.isPairingState;
                }
                return pairingState.copy(z2);
            }

            public final boolean component1() {
                return this.isPairingState;
            }

            @NotNull
            public final PairingState copy(boolean z2) {
                return new PairingState(z2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof PairingState) && this.isPairingState == ((PairingState) obj).isPairingState;
            }

            public int hashCode() {
                return Boolean.hashCode(this.isPairingState);
            }

            public final boolean isPairingState() {
                return this.isPairingState;
            }

            @NotNull
            public String toString() {
                boolean z2 = this.isPairingState;
                return "PairingState(isPairingState=" + z2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/Core$Model$Ping;", "Lcom/reown/android/Core$Model;", "<init>", "()V", "Success", "Error", "Lcom/reown/android/Core$Model$Ping$Error;", "Lcom/reown/android/Core$Model$Ping$Success;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Ping extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/android/Core$Model$Ping$Error;", "Lcom/reown/android/Core$Model$Ping;", "error", "", "<init>", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends Ping {
                @NotNull
                private final Throwable error;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull Throwable th) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    this.error = th;
                }

                public static /* synthetic */ Error copy$default(Error error2, Throwable th, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        th = error2.error;
                    }
                    return error2.copy(th);
                }

                @NotNull
                public final Throwable component1() {
                    return this.error;
                }

                @NotNull
                public final Error copy(@NotNull Throwable th) {
                    Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                    return new Error(th);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.error, (Object) ((Error) obj).error);
                }

                @NotNull
                public final Throwable getError() {
                    return this.error;
                }

                public int hashCode() {
                    return this.error.hashCode();
                }

                @NotNull
                public String toString() {
                    Throwable th = this.error;
                    return "Error(error=" + th + ")";
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Model$Ping$Success;", "Lcom/reown/android/Core$Model$Ping;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Success extends Ping {
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Success(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    this.topic = str;
                }

                public static /* synthetic */ Success copy$default(Success success, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = success.topic;
                    }
                    return success.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @NotNull
                public final Success copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    return new Success(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Success) && Intrinsics.areEqual((Object) this.topic, (Object) ((Success) obj).topic);
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.topic.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Success(topic=", this.topic, ")");
                }
            }

            public /* synthetic */ Ping(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Ping() {
                super((DefaultConstructorMarker) null);
            }
        }

        public /* synthetic */ Model(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Model() {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0006\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/reown/android/Core$Params;", "", "<init>", "()V", "Ping", "Pair", "Disconnect", "Delete", "RequestReceived", "UpdateMetadata", "Lcom/reown/android/Core$Params$Delete;", "Lcom/reown/android/Core$Params$Disconnect;", "Lcom/reown/android/Core$Params$Pair;", "Lcom/reown/android/Core$Params$Ping;", "Lcom/reown/android/Core$Params$RequestReceived;", "Lcom/reown/android/Core$Params$UpdateMetadata;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Params {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Params$Delete;", "Lcom/reown/android/Core$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Delete extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Delete(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ Delete copy$default(Delete delete, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = delete.topic;
                }
                return delete.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Delete copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Delete(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Delete) && Intrinsics.areEqual((Object) this.topic, (Object) ((Delete) obj).topic);
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.topic.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Delete(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Params$Disconnect;", "Lcom/reown/android/Core$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Disconnect extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Disconnect(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ Disconnect copy$default(Disconnect disconnect, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = disconnect.topic;
                }
                return disconnect.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Disconnect copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Disconnect(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Disconnect) && Intrinsics.areEqual((Object) this.topic, (Object) ((Disconnect) obj).topic);
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.topic.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Disconnect(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Params$Pair;", "Lcom/reown/android/Core$Params;", "uri", "", "<init>", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Pair extends Params {
            @NotNull
            private final String uri;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Pair(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "uri");
                this.uri = str;
            }

            public static /* synthetic */ Pair copy$default(Pair pair, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = pair.uri;
                }
                return pair.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.uri;
            }

            @NotNull
            public final Pair copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "uri");
                return new Pair(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Pair) && Intrinsics.areEqual((Object) this.uri, (Object) ((Pair) obj).uri);
            }

            @NotNull
            public final String getUri() {
                return this.uri;
            }

            public int hashCode() {
                return this.uri.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Pair(uri=", this.uri, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Params$Ping;", "Lcom/reown/android/Core$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Ping extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Ping(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ Ping copy$default(Ping ping, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = ping.topic;
                }
                return ping.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Ping copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new Ping(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Ping) && Intrinsics.areEqual((Object) this.topic, (Object) ((Ping) obj).topic);
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.topic.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Ping(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/android/Core$Params$RequestReceived;", "Lcom/reown/android/Core$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RequestReceived extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RequestReceived(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ RequestReceived copy$default(RequestReceived requestReceived, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = requestReceived.topic;
                }
                return requestReceived.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final RequestReceived copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new RequestReceived(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof RequestReceived) && Intrinsics.areEqual((Object) this.topic, (Object) ((RequestReceived) obj).topic);
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.topic.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("RequestReceived(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/android/Core$Params$UpdateMetadata;", "Lcom/reown/android/Core$Params;", "topic", "", "metadata", "Lcom/reown/android/Core$Model$AppMetaData;", "metaDataType", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "<init>", "(Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/android/internal/common/model/AppMetaDataType;)V", "getTopic", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/Core$Model$AppMetaData;", "getMetaDataType", "()Lcom/reown/android/internal/common/model/AppMetaDataType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UpdateMetadata extends Params {
            @NotNull
            private final AppMetaDataType metaDataType;
            @NotNull
            private final Model.AppMetaData metadata;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UpdateMetadata(@NotNull String str, @NotNull Model.AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                Intrinsics.checkNotNullParameter(appMetaDataType, "metaDataType");
                this.topic = str;
                this.metadata = appMetaData;
                this.metaDataType = appMetaDataType;
            }

            public static /* synthetic */ UpdateMetadata copy$default(UpdateMetadata updateMetadata, String str, Model.AppMetaData appMetaData, AppMetaDataType appMetaDataType, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = updateMetadata.topic;
                }
                if ((i3 & 2) != 0) {
                    appMetaData = updateMetadata.metadata;
                }
                if ((i3 & 4) != 0) {
                    appMetaDataType = updateMetadata.metaDataType;
                }
                return updateMetadata.copy(str, appMetaData, appMetaDataType);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Model.AppMetaData component2() {
                return this.metadata;
            }

            @NotNull
            public final AppMetaDataType component3() {
                return this.metaDataType;
            }

            @NotNull
            public final UpdateMetadata copy(@NotNull String str, @NotNull Model.AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(appMetaData, TtmlNode.TAG_METADATA);
                Intrinsics.checkNotNullParameter(appMetaDataType, "metaDataType");
                return new UpdateMetadata(str, appMetaData, appMetaDataType);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof UpdateMetadata)) {
                    return false;
                }
                UpdateMetadata updateMetadata = (UpdateMetadata) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) updateMetadata.topic) && Intrinsics.areEqual((Object) this.metadata, (Object) updateMetadata.metadata) && this.metaDataType == updateMetadata.metaDataType;
            }

            @NotNull
            public final AppMetaDataType getMetaDataType() {
                return this.metaDataType;
            }

            @NotNull
            public final Model.AppMetaData getMetadata() {
                return this.metadata;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.metadata.hashCode();
                return this.metaDataType.hashCode() + ((hashCode + (this.topic.hashCode() * 31)) * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Model.AppMetaData appMetaData = this.metadata;
                AppMetaDataType appMetaDataType = this.metaDataType;
                return "UpdateMetadata(topic=" + str + ", metadata=" + appMetaData + ", metaDataType=" + appMetaDataType + ")";
            }
        }

        public /* synthetic */ Params(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Params() {
        }
    }

    private Core() {
    }
}
