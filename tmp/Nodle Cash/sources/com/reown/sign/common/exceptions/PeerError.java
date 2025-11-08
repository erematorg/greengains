package com.reown.sign.common.exceptions;

import A.a;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.core.DefaultLoadControl;
import com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.reown.android.internal.common.model.type.Error;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError;", "Lcom/reown/android/internal/common/model/type/Error;", "<init>", "()V", "Invalid", "Unauthorized", "EIP1193", "CAIP25", "Failure", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "Lcom/reown/sign/common/exceptions/PeerError$EIP1193;", "Lcom/reown/sign/common/exceptions/PeerError$Failure;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PeerError implements Error {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u000b\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000eB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u000b\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "Lcom/reown/sign/common/exceptions/PeerError;", "<init>", "()V", "UserRejected", "UserRejectedChains", "UserRejectedMethods", "UserRejectedEvents", "UnsupportedChains", "UnsupportedMethods", "UnsupportedEvents", "UnsupportedAccounts", "UnsupportedNamespaceKey", "InvalidSessionPropertiesObject", "EmptySessionNamespaces", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$EmptySessionNamespaces;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$InvalidSessionPropertiesObject;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedAccounts;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedChains;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedEvents;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedMethods;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedNamespaceKey;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejected;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedChains;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedEvents;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedMethods;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class CAIP25 extends PeerError {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$EmptySessionNamespaces;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class EmptySessionNamespaces extends CAIP25 {
            private final int code = 5201;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public EmptySessionNamespaces(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ EmptySessionNamespaces copy$default(EmptySessionNamespaces emptySessionNamespaces, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = emptySessionNamespaces.message;
                }
                return emptySessionNamespaces.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final EmptySessionNamespaces copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new EmptySessionNamespaces(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof EmptySessionNamespaces) && Intrinsics.areEqual((Object) this.message, (Object) ((EmptySessionNamespaces) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("EmptySessionNamespaces(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$InvalidSessionPropertiesObject;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class InvalidSessionPropertiesObject extends CAIP25 {
            private final int code = 5200;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public InvalidSessionPropertiesObject(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ InvalidSessionPropertiesObject copy$default(InvalidSessionPropertiesObject invalidSessionPropertiesObject, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = invalidSessionPropertiesObject.message;
                }
                return invalidSessionPropertiesObject.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final InvalidSessionPropertiesObject copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new InvalidSessionPropertiesObject(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof InvalidSessionPropertiesObject) && Intrinsics.areEqual((Object) this.message, (Object) ((InvalidSessionPropertiesObject) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("InvalidSessionPropertiesObject(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedAccounts;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnsupportedAccounts extends CAIP25 {
            private final int code = 5103;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UnsupportedAccounts(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UnsupportedAccounts copy$default(UnsupportedAccounts unsupportedAccounts, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = unsupportedAccounts.message;
                }
                return unsupportedAccounts.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UnsupportedAccounts copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UnsupportedAccounts(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnsupportedAccounts) && Intrinsics.areEqual((Object) this.message, (Object) ((UnsupportedAccounts) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UnsupportedAccounts(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedChains;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnsupportedChains extends CAIP25 {
            private final int code = 5100;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UnsupportedChains(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UnsupportedChains copy$default(UnsupportedChains unsupportedChains, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = unsupportedChains.message;
                }
                return unsupportedChains.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UnsupportedChains copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UnsupportedChains(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnsupportedChains) && Intrinsics.areEqual((Object) this.message, (Object) ((UnsupportedChains) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UnsupportedChains(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedEvents;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnsupportedEvents extends CAIP25 {
            private final int code = 5102;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UnsupportedEvents(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UnsupportedEvents copy$default(UnsupportedEvents unsupportedEvents, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = unsupportedEvents.message;
                }
                return unsupportedEvents.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UnsupportedEvents copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UnsupportedEvents(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnsupportedEvents) && Intrinsics.areEqual((Object) this.message, (Object) ((UnsupportedEvents) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UnsupportedEvents(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedMethods;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnsupportedMethods extends CAIP25 {
            private final int code = 5101;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UnsupportedMethods(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UnsupportedMethods copy$default(UnsupportedMethods unsupportedMethods, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = unsupportedMethods.message;
                }
                return unsupportedMethods.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UnsupportedMethods copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UnsupportedMethods(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnsupportedMethods) && Intrinsics.areEqual((Object) this.message, (Object) ((UnsupportedMethods) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UnsupportedMethods(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UnsupportedNamespaceKey;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnsupportedNamespaceKey extends CAIP25 {
            private final int code = 5104;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UnsupportedNamespaceKey(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UnsupportedNamespaceKey copy$default(UnsupportedNamespaceKey unsupportedNamespaceKey, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = unsupportedNamespaceKey.message;
                }
                return unsupportedNamespaceKey.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UnsupportedNamespaceKey copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UnsupportedNamespaceKey(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UnsupportedNamespaceKey) && Intrinsics.areEqual((Object) this.message, (Object) ((UnsupportedNamespaceKey) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UnsupportedNamespaceKey(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejected;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UserRejected extends CAIP25 {
            private final int code = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UserRejected(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UserRejected copy$default(UserRejected userRejected, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = userRejected.message;
                }
                return userRejected.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UserRejected copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UserRejected(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UserRejected) && Intrinsics.areEqual((Object) this.message, (Object) ((UserRejected) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UserRejected(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedChains;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UserRejectedChains extends CAIP25 {
            private final int code = PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UserRejectedChains(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UserRejectedChains copy$default(UserRejectedChains userRejectedChains, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = userRejectedChains.message;
                }
                return userRejectedChains.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UserRejectedChains copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UserRejectedChains(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UserRejectedChains) && Intrinsics.areEqual((Object) this.message, (Object) ((UserRejectedChains) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UserRejectedChains(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedEvents;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UserRejectedEvents extends CAIP25 {
            private final int code = 5003;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UserRejectedEvents(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UserRejectedEvents copy$default(UserRejectedEvents userRejectedEvents, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = userRejectedEvents.message;
                }
                return userRejectedEvents.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UserRejectedEvents copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UserRejectedEvents(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UserRejectedEvents) && Intrinsics.areEqual((Object) this.message, (Object) ((UserRejectedEvents) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UserRejectedEvents(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$CAIP25$UserRejectedMethods;", "Lcom/reown/sign/common/exceptions/PeerError$CAIP25;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UserRejectedMethods extends CAIP25 {
            private final int code = PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UserRejectedMethods(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UserRejectedMethods copy$default(UserRejectedMethods userRejectedMethods, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = userRejectedMethods.message;
                }
                return userRejectedMethods.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UserRejectedMethods copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UserRejectedMethods(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UserRejectedMethods) && Intrinsics.areEqual((Object) this.message, (Object) ((UserRejectedMethods) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UserRejectedMethods(message=", this.message, ")");
            }
        }

        public /* synthetic */ CAIP25(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CAIP25() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$EIP1193;", "Lcom/reown/sign/common/exceptions/PeerError;", "<init>", "()V", "UserRejectedRequest", "Lcom/reown/sign/common/exceptions/PeerError$EIP1193$UserRejectedRequest;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class EIP1193 extends PeerError {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$EIP1193$UserRejectedRequest;", "Lcom/reown/sign/common/exceptions/PeerError$EIP1193;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UserRejectedRequest extends EIP1193 {
            private final int code = PlaybackException.ERROR_CODE_DECODER_INIT_FAILED;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UserRejectedRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ UserRejectedRequest copy$default(UserRejectedRequest userRejectedRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = userRejectedRequest.message;
                }
                return userRejectedRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final UserRejectedRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new UserRejectedRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UserRejectedRequest) && Intrinsics.areEqual((Object) this.message, (Object) ((UserRejectedRequest) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UserRejectedRequest(message=", this.message, ")");
            }
        }

        public /* synthetic */ EIP1193(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private EIP1193() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Failure;", "Lcom/reown/sign/common/exceptions/PeerError;", "<init>", "()V", "SessionSettlementFailed", "NoSessionForTopic", "Lcom/reown/sign/common/exceptions/PeerError$Failure$NoSessionForTopic;", "Lcom/reown/sign/common/exceptions/PeerError$Failure$SessionSettlementFailed;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Failure extends PeerError {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Failure$NoSessionForTopic;", "Lcom/reown/sign/common/exceptions/PeerError$Failure;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NoSessionForTopic extends Failure {
            private final int code = 7001;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public NoSessionForTopic(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ NoSessionForTopic copy$default(NoSessionForTopic noSessionForTopic, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = noSessionForTopic.message;
                }
                return noSessionForTopic.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final NoSessionForTopic copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new NoSessionForTopic(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof NoSessionForTopic) && Intrinsics.areEqual((Object) this.message, (Object) ((NoSessionForTopic) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("NoSessionForTopic(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Failure$SessionSettlementFailed;", "Lcom/reown/sign/common/exceptions/PeerError$Failure;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionSettlementFailed extends Failure {
            private final int code = AacUtil.AAC_HE_V2_MAX_RATE_BYTES_PER_SECOND;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionSettlementFailed(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Invalid Session Settle Request: ", str);
            }

            public static /* synthetic */ SessionSettlementFailed copy$default(SessionSettlementFailed sessionSettlementFailed, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionSettlementFailed.reason;
                }
                return sessionSettlementFailed.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final SessionSettlementFailed copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new SessionSettlementFailed(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof SessionSettlementFailed) && Intrinsics.areEqual((Object) this.reason, (Object) ((SessionSettlementFailed) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("SessionSettlementFailed(reason=", this.reason, ")");
            }
        }

        public /* synthetic */ Failure(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Failure() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "Lcom/reown/sign/common/exceptions/PeerError;", "<init>", "()V", "Method", "Event", "UpdateRequest", "ExtendRequest", "SessionSettleRequest", "Lcom/reown/sign/common/exceptions/PeerError$Invalid$Event;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid$ExtendRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid$Method;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid$SessionSettleRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid$UpdateRequest;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Invalid extends PeerError {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid$Event;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Event extends Invalid {
            private final int code = 1002;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Event(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Invalid event request: ", str);
            }

            public static /* synthetic */ Event copy$default(Event event, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = event.reason;
                }
                return event.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final Event copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new Event(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Event) && Intrinsics.areEqual((Object) this.reason, (Object) ((Event) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Event(reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid$ExtendRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExtendRequest extends Invalid {
            private final int code = 1004;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExtendRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Invalid session extend request: ", str);
            }

            public static /* synthetic */ ExtendRequest copy$default(ExtendRequest extendRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = extendRequest.reason;
                }
                return extendRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final ExtendRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new ExtendRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ExtendRequest) && Intrinsics.areEqual((Object) this.reason, (Object) ((ExtendRequest) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("ExtendRequest(reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid$Method;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Method extends Invalid {
            private final int code = 1001;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Method(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Invalid session request: ", str);
            }

            public static /* synthetic */ Method copy$default(Method method, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = method.reason;
                }
                return method.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final Method copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new Method(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Method) && Intrinsics.areEqual((Object) this.reason, (Object) ((Method) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Method(reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid$SessionSettleRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionSettleRequest extends Invalid {
            private final int code = 1005;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionSettleRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ SessionSettleRequest copy$default(SessionSettleRequest sessionSettleRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionSettleRequest.message;
                }
                return sessionSettleRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final SessionSettleRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new SessionSettleRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof SessionSettleRequest) && Intrinsics.areEqual((Object) this.message, (Object) ((SessionSettleRequest) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("SessionSettleRequest(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Invalid$UpdateRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Invalid;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UpdateRequest extends Invalid {
            private final int code = 1003;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UpdateRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Invalid update namespace request: ", str);
            }

            public static /* synthetic */ UpdateRequest copy$default(UpdateRequest updateRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = updateRequest.reason;
                }
                return updateRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final UpdateRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new UpdateRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UpdateRequest) && Intrinsics.areEqual((Object) this.reason, (Object) ((UpdateRequest) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UpdateRequest(reason=", this.reason, ")");
            }
        }

        public /* synthetic */ Invalid(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Invalid() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "Lcom/reown/sign/common/exceptions/PeerError;", "<init>", "()V", "Method", "Event", "UpdateRequest", "ExtendRequest", "Chain", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Chain;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Event;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$ExtendRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Method;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$UpdateRequest;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Unauthorized extends PeerError {

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\tHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Chain;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Chain extends Unauthorized {
            private final int code = AuthApiStatusCodes.AUTH_URL_RESOLUTION;
            @NotNull
            private final String message;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Chain(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                this.message = str;
            }

            public static /* synthetic */ Chain copy$default(Chain chain, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = chain.message;
                }
                return chain.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.message;
            }

            @NotNull
            public final Chain copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                return new Chain(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Chain) && Intrinsics.areEqual((Object) this.message, (Object) ((Chain) obj).message);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Chain(message=", this.message, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Event;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Event extends Unauthorized {
            private final int code = 3002;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Event(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Unauthorized event request: ", str);
            }

            public static /* synthetic */ Event copy$default(Event event, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = event.reason;
                }
                return event.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final Event copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new Event(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Event) && Intrinsics.areEqual((Object) this.reason, (Object) ((Event) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Event(reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$ExtendRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "sequence", "", "<init>", "(Ljava/lang/String;)V", "getSequence", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExtendRequest extends Unauthorized {
            private final int code = 3004;
            @NotNull
            private final String message;
            @NotNull
            private final String sequence;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExtendRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sequence");
                this.sequence = str;
                this.message = a.l("Unauthorized ", str, " extend request");
            }

            public static /* synthetic */ ExtendRequest copy$default(ExtendRequest extendRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = extendRequest.sequence;
                }
                return extendRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.sequence;
            }

            @NotNull
            public final ExtendRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "sequence");
                return new ExtendRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof ExtendRequest) && Intrinsics.areEqual((Object) this.sequence, (Object) ((ExtendRequest) obj).sequence);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getSequence() {
                return this.sequence;
            }

            public int hashCode() {
                return this.sequence.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("ExtendRequest(sequence=", this.sequence, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$Method;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Method extends Unauthorized {
            private final int code = 3001;
            @NotNull
            private final String message;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Method(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.reason = str;
                this.message = c.a("Unauthorized session request: ", str);
            }

            public static /* synthetic */ Method copy$default(Method method, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = method.reason;
                }
                return method.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.reason;
            }

            @NotNull
            public final Method copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new Method(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Method) && Intrinsics.areEqual((Object) this.reason, (Object) ((Method) obj).reason);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("Method(reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/common/exceptions/PeerError$Unauthorized$UpdateRequest;", "Lcom/reown/sign/common/exceptions/PeerError$Unauthorized;", "sequence", "", "<init>", "(Ljava/lang/String;)V", "getSequence", "()Ljava/lang/String;", "message", "getMessage", "code", "", "getCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UpdateRequest extends Unauthorized {
            private final int code = 3003;
            @NotNull
            private final String message;
            @NotNull
            private final String sequence;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public UpdateRequest(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sequence");
                this.sequence = str;
                this.message = a.l("Unauthorized update ", str, " namespace request");
            }

            public static /* synthetic */ UpdateRequest copy$default(UpdateRequest updateRequest, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = updateRequest.sequence;
                }
                return updateRequest.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.sequence;
            }

            @NotNull
            public final UpdateRequest copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "sequence");
                return new UpdateRequest(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof UpdateRequest) && Intrinsics.areEqual((Object) this.sequence, (Object) ((UpdateRequest) obj).sequence);
            }

            public int getCode() {
                return this.code;
            }

            @NotNull
            public String getMessage() {
                return this.message;
            }

            @NotNull
            public final String getSequence() {
                return this.sequence;
            }

            public int hashCode() {
                return this.sequence.hashCode();
            }

            @NotNull
            public String toString() {
                return a.l("UpdateRequest(sequence=", this.sequence, ")");
            }
        }

        public /* synthetic */ Unauthorized(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Unauthorized() {
            super((DefaultConstructorMarker) null);
        }
    }

    public /* synthetic */ PeerError(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PeerError() {
    }
}
