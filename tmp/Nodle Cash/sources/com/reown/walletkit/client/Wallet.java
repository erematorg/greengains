package com.reown.walletkit.client;

import androidx.annotation.Keep;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.animation.core.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.b;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reown.android.Core;
import com.reown.android.CoreInterface;
import com.reown.android.cacao.SignatureInterface;
import com.reown.android.push.notifications.PushMessagingService;
import io.nodle.cash.ui.feature.chat.ConversationActivity;
import java.net.URI;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.abi.datatypes.Address;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/reown/walletkit/client/Wallet;", "", "<init>", "()V", "Listeners", "Params", "Model", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Wallet {
    @NotNull
    public static final Wallet INSTANCE = new Wallet();

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0001\u0001\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Listeners;", "", "SessionPing", "Lcom/reown/walletkit/client/Wallet$Listeners$SessionPing;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listeners {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Listeners$SessionPing;", "Lcom/reown/walletkit/client/Wallet$Listeners;", "onSuccess", "", "pingSuccess", "Lcom/reown/walletkit/client/Wallet$Model$Ping$Success;", "onError", "pingError", "Lcom/reown/walletkit/client/Wallet$Model$Ping$Error;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface SessionPing extends Listeners {
            void onError(@NotNull Model.Ping.Error error);

            void onSuccess(@NotNull Model.Ping.Success success);
        }
    }

    @Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:+\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-.B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u00010/0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^¨\u0006_"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model;", "", "<init>", "()V", "Ping", "Call", "Error", "Transaction", "SolanaTransaction", "Transactions", "InitialTransaction", "FeeEstimatedTransaction", "FundingMetadata", "InitialTransactionMetadata", "EstimatedFees", "PrepareSuccess", "ExecuteSuccess", "Amount", "TransactionFee", "Route", "RouteSig", "TransactionDetails", "SolanaTransactionDetails", "TransactionsDetails", "PrepareError", "Status", "ConnectionState", "ExpiredProposal", "ExpiredRequest", "SessionProposal", "SessionAuthenticate", "VerifyContext", "Validation", "SessionRequest", "SettledSessionResponse", "SessionUpdateResponse", "SessionDelete", "Namespace", "JsonRpcResponse", "PayloadParams", "PayloadAuthRequestParams", "SessionEvent", "Event", "Cacao", "Session", "PendingSessionRequest", "Message", "Lcom/reown/walletkit/client/Wallet$Model$Amount;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Header;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload;", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "Lcom/reown/walletkit/client/Wallet$Model$Call;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "Lcom/reown/walletkit/client/Wallet$Model$Error;", "Lcom/reown/walletkit/client/Wallet$Model$EstimatedFees;", "Lcom/reown/walletkit/client/Wallet$Model$Event;", "Lcom/reown/walletkit/client/Wallet$Model$ExecuteSuccess;", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredProposal;", "Lcom/reown/walletkit/client/Wallet$Model$ExpiredRequest;", "Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;", "Lcom/reown/walletkit/client/Wallet$Model$FundingMetadata;", "Lcom/reown/walletkit/client/Wallet$Model$InitialTransaction;", "Lcom/reown/walletkit/client/Wallet$Model$InitialTransactionMetadata;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "Lcom/reown/walletkit/client/Wallet$Model$Namespace;", "Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "Lcom/reown/walletkit/client/Wallet$Model$PayloadParams;", "Lcom/reown/walletkit/client/Wallet$Model$PendingSessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Ping;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess;", "Lcom/reown/walletkit/client/Wallet$Model$Route;", "Lcom/reown/walletkit/client/Wallet$Model$RouteSig;", "Lcom/reown/walletkit/client/Wallet$Model$Session;", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;", "Lcom/reown/walletkit/client/Wallet$Model$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest$JSONRPCRequest;", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;", "Lcom/reown/walletkit/client/Wallet$Model$SolanaTransactionDetails;", "Lcom/reown/walletkit/client/Wallet$Model$Status;", "Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;", "Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;", "Lcom/reown/walletkit/client/Wallet$Model$Transactions;", "Lcom/reown/walletkit/client/Wallet$Model$TransactionsDetails;", "Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Model {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006#"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Amount;", "Lcom/reown/walletkit/client/Wallet$Model;", "symbol", "", "amount", "unit", "formatted", "formattedAlt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSymbol", "()Ljava/lang/String;", "setSymbol", "(Ljava/lang/String;)V", "getAmount", "setAmount", "getUnit", "setUnit", "getFormatted", "setFormatted", "getFormattedAlt", "setFormattedAlt", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Amount extends Model {
            @NotNull
            private String amount;
            @NotNull
            private String formatted;
            @NotNull
            private String formattedAlt;
            @NotNull
            private String symbol;
            @NotNull
            private String unit;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Amount(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "symbol");
                Intrinsics.checkNotNullParameter(str2, "amount");
                Intrinsics.checkNotNullParameter(str3, "unit");
                Intrinsics.checkNotNullParameter(str4, "formatted");
                Intrinsics.checkNotNullParameter(str5, "formattedAlt");
                this.symbol = str;
                this.amount = str2;
                this.unit = str3;
                this.formatted = str4;
                this.formattedAlt = str5;
            }

            public static /* synthetic */ Amount copy$default(Amount amount2, String str, String str2, String str3, String str4, String str5, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = amount2.symbol;
                }
                if ((i3 & 2) != 0) {
                    str2 = amount2.amount;
                }
                String str6 = str2;
                if ((i3 & 4) != 0) {
                    str3 = amount2.unit;
                }
                String str7 = str3;
                if ((i3 & 8) != 0) {
                    str4 = amount2.formatted;
                }
                String str8 = str4;
                if ((i3 & 16) != 0) {
                    str5 = amount2.formattedAlt;
                }
                return amount2.copy(str, str6, str7, str8, str5);
            }

            @NotNull
            public final String component1() {
                return this.symbol;
            }

            @NotNull
            public final String component2() {
                return this.amount;
            }

            @NotNull
            public final String component3() {
                return this.unit;
            }

            @NotNull
            public final String component4() {
                return this.formatted;
            }

            @NotNull
            public final String component5() {
                return this.formattedAlt;
            }

            @NotNull
            public final Amount copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
                Intrinsics.checkNotNullParameter(str, "symbol");
                Intrinsics.checkNotNullParameter(str2, "amount");
                Intrinsics.checkNotNullParameter(str3, "unit");
                Intrinsics.checkNotNullParameter(str4, "formatted");
                Intrinsics.checkNotNullParameter(str5, "formattedAlt");
                return new Amount(str, str2, str3, str4, str5);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Amount)) {
                    return false;
                }
                Amount amount2 = (Amount) obj;
                return Intrinsics.areEqual((Object) this.symbol, (Object) amount2.symbol) && Intrinsics.areEqual((Object) this.amount, (Object) amount2.amount) && Intrinsics.areEqual((Object) this.unit, (Object) amount2.unit) && Intrinsics.areEqual((Object) this.formatted, (Object) amount2.formatted) && Intrinsics.areEqual((Object) this.formattedAlt, (Object) amount2.formattedAlt);
            }

            @NotNull
            public final String getAmount() {
                return this.amount;
            }

            @NotNull
            public final String getFormatted() {
                return this.formatted;
            }

            @NotNull
            public final String getFormattedAlt() {
                return this.formattedAlt;
            }

            @NotNull
            public final String getSymbol() {
                return this.symbol;
            }

            @NotNull
            public final String getUnit() {
                return this.unit;
            }

            public int hashCode() {
                return this.formattedAlt.hashCode() + a.i(this.formatted, a.i(this.unit, a.i(this.amount, this.symbol.hashCode() * 31, 31), 31), 31);
            }

            public final void setAmount(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.amount = str;
            }

            public final void setFormatted(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.formatted = str;
            }

            public final void setFormattedAlt(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.formattedAlt = str;
            }

            public final void setSymbol(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.symbol = str;
            }

            public final void setUnit(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.unit = str;
            }

            @NotNull
            public String toString() {
                String str = this.symbol;
                String str2 = this.amount;
                String str3 = this.unit;
                String str4 = this.formatted;
                String str5 = this.formattedAlt;
                StringBuilder l2 = C0118y.l("Amount(symbol=", str, ", amount=", str2, ", unit=");
                b.w(l2, str3, ", formatted=", str4, ", formattedAlt=");
                return A.a.n(l2, str5, ")");
            }
        }

        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001:\u0003\u001c\u001d\u001eB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Cacao;", "Lcom/reown/walletkit/client/Wallet$Model;", "header", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Header;", "payload", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload;", "signature", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$Cacao$Header;Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload;Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;)V", "getHeader", "()Lcom/reown/walletkit/client/Wallet$Model$Cacao$Header;", "getPayload", "()Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload;", "getSignature", "()Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Signature", "Header", "Payload", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Cacao extends Model {
            @NotNull
            private final Header header;
            @NotNull
            private final Payload payload;
            @NotNull
            private final Signature signature;

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Cacao$Header;", "Lcom/reown/walletkit/client/Wallet$Model;", "t", "", "<init>", "(Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Header extends Model {
                @NotNull

                /* renamed from: t  reason: collision with root package name */
                private final String f7634t;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Header(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "t");
                    this.f7634t = str;
                }

                public static /* synthetic */ Header copy$default(Header header, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = header.f7634t;
                    }
                    return header.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.f7634t;
                }

                @NotNull
                public final Header copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "t");
                    return new Header(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Header) && Intrinsics.areEqual((Object) this.f7634t, (Object) ((Header) obj).f7634t);
                }

                @NotNull
                public final String getT() {
                    return this.f7634t;
                }

                public int hashCode() {
                    return this.f7634t.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Header(t=", this.f7634t, ")");
                }
            }

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 32\u00020\u0001:\u00013Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0012¨\u00064"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload;", "Lcom/reown/walletkit/client/Wallet$Model;", "iss", "", "domain", "aud", "version", "nonce", "iat", "nbf", "exp", "statement", "requestId", "resources", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getIss", "()Ljava/lang/String;", "getDomain", "getAud", "getVersion", "getNonce", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "()Ljava/util/List;", "address", "getAddress", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Payload extends Model {
                @NotNull
                private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
                @NotNull
                @Deprecated
                public static final String ISS_DELIMITER = ":";
                @Deprecated
                public static final int ISS_POSITION_OF_ADDRESS = 4;
                @NotNull
                private final String aud;
                @NotNull
                private final String domain;
                @Nullable
                private final String exp;
                @NotNull
                private final String iat;
                @NotNull
                private final String iss;
                @Nullable
                private final String nbf;
                @NotNull
                private final String nonce;
                @Nullable
                private final String requestId;
                @Nullable
                private final List<String> resources;
                @Nullable
                private final String statement;
                @NotNull
                private final String version;

                @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Cacao$Payload$Companion;", "", "<init>", "()V", "ISS_DELIMITER", "", "ISS_POSITION_OF_ADDRESS", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Payload(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable List<String> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "iss");
                    Intrinsics.checkNotNullParameter(str2, "domain");
                    Intrinsics.checkNotNullParameter(str3, "aud");
                    Intrinsics.checkNotNullParameter(str4, "version");
                    Intrinsics.checkNotNullParameter(str5, "nonce");
                    Intrinsics.checkNotNullParameter(str6, "iat");
                    this.iss = str;
                    this.domain = str2;
                    this.aud = str3;
                    this.version = str4;
                    this.nonce = str5;
                    this.iat = str6;
                    this.nbf = str7;
                    this.exp = str8;
                    this.statement = str9;
                    this.requestId = str10;
                    this.resources = list;
                }

                public static /* synthetic */ Payload copy$default(Payload payload, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, List list, int i3, Object obj) {
                    Payload payload2 = payload;
                    int i4 = i3;
                    return payload.copy((i4 & 1) != 0 ? payload2.iss : str, (i4 & 2) != 0 ? payload2.domain : str2, (i4 & 4) != 0 ? payload2.aud : str3, (i4 & 8) != 0 ? payload2.version : str4, (i4 & 16) != 0 ? payload2.nonce : str5, (i4 & 32) != 0 ? payload2.iat : str6, (i4 & 64) != 0 ? payload2.nbf : str7, (i4 & 128) != 0 ? payload2.exp : str8, (i4 & 256) != 0 ? payload2.statement : str9, (i4 & 512) != 0 ? payload2.requestId : str10, (i4 & 1024) != 0 ? payload2.resources : list);
                }

                @NotNull
                public final String component1() {
                    return this.iss;
                }

                @Nullable
                public final String component10() {
                    return this.requestId;
                }

                @Nullable
                public final List<String> component11() {
                    return this.resources;
                }

                @NotNull
                public final String component2() {
                    return this.domain;
                }

                @NotNull
                public final String component3() {
                    return this.aud;
                }

                @NotNull
                public final String component4() {
                    return this.version;
                }

                @NotNull
                public final String component5() {
                    return this.nonce;
                }

                @NotNull
                public final String component6() {
                    return this.iat;
                }

                @Nullable
                public final String component7() {
                    return this.nbf;
                }

                @Nullable
                public final String component8() {
                    return this.exp;
                }

                @Nullable
                public final String component9() {
                    return this.statement;
                }

                @NotNull
                public final Payload copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable List<String> list) {
                    Intrinsics.checkNotNullParameter(str, "iss");
                    Intrinsics.checkNotNullParameter(str2, "domain");
                    String str11 = str3;
                    Intrinsics.checkNotNullParameter(str11, "aud");
                    String str12 = str4;
                    Intrinsics.checkNotNullParameter(str12, "version");
                    String str13 = str5;
                    Intrinsics.checkNotNullParameter(str13, "nonce");
                    String str14 = str6;
                    Intrinsics.checkNotNullParameter(str14, "iat");
                    return new Payload(str, str2, str11, str12, str13, str14, str7, str8, str9, str10, list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Payload)) {
                        return false;
                    }
                    Payload payload = (Payload) obj;
                    return Intrinsics.areEqual((Object) this.iss, (Object) payload.iss) && Intrinsics.areEqual((Object) this.domain, (Object) payload.domain) && Intrinsics.areEqual((Object) this.aud, (Object) payload.aud) && Intrinsics.areEqual((Object) this.version, (Object) payload.version) && Intrinsics.areEqual((Object) this.nonce, (Object) payload.nonce) && Intrinsics.areEqual((Object) this.iat, (Object) payload.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payload.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payload.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payload.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payload.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payload.resources);
                }

                @NotNull
                public final String getAddress() {
                    return (String) StringsKt__StringsKt.split$default((CharSequence) this.iss, new String[]{":"}, false, 0, 6, (Object) null).get(4);
                }

                @NotNull
                public final String getAud() {
                    return this.aud;
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

                @NotNull
                public final String getIss() {
                    return this.iss;
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

                @NotNull
                public final String getVersion() {
                    return this.version;
                }

                public int hashCode() {
                    int i3 = a.i(this.iat, a.i(this.nonce, a.i(this.version, a.i(this.aud, a.i(this.domain, this.iss.hashCode() * 31, 31), 31), 31), 31), 31);
                    String str = this.nbf;
                    int i4 = 0;
                    int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
                    String str2 = this.exp;
                    int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                    String str3 = this.statement;
                    int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                    String str4 = this.requestId;
                    int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                    List<String> list = this.resources;
                    if (list != null) {
                        i4 = list.hashCode();
                    }
                    return hashCode4 + i4;
                }

                @NotNull
                public String toString() {
                    String str = this.iss;
                    String str2 = this.domain;
                    String str3 = this.aud;
                    String str4 = this.version;
                    String str5 = this.nonce;
                    String str6 = this.iat;
                    String str7 = this.nbf;
                    String str8 = this.exp;
                    String str9 = this.statement;
                    String str10 = this.requestId;
                    List<String> list = this.resources;
                    StringBuilder l2 = C0118y.l("Payload(iss=", str, ", domain=", str2, ", aud=");
                    b.w(l2, str3, ", version=", str4, ", nonce=");
                    b.w(l2, str5, ", iat=", str6, ", nbf=");
                    b.w(l2, str7, ", exp=", str8, ", statement=");
                    b.w(l2, str9, ", requestId=", str10, ", resources=");
                    return C0118y.h(")", list, l2);
                }
            }

            @Keep
            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "Lcom/reown/walletkit/client/Wallet$Model;", "Lcom/reown/android/cacao/SignatureInterface;", "t", "", "s", "m", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getT", "()Ljava/lang/String;", "getS", "getM", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Signature extends Model implements SignatureInterface {
                @Nullable

                /* renamed from: m  reason: collision with root package name */
                private final String f7635m;
                @NotNull

                /* renamed from: s  reason: collision with root package name */
                private final String f7636s;
                @NotNull

                /* renamed from: t  reason: collision with root package name */
                private final String f7637t;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Signature(@NotNull String str, @NotNull String str2, @Nullable String str3) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "t");
                    Intrinsics.checkNotNullParameter(str2, "s");
                    this.f7637t = str;
                    this.f7636s = str2;
                    this.f7635m = str3;
                }

                public static /* synthetic */ Signature copy$default(Signature signature, String str, String str2, String str3, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = signature.f7637t;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = signature.f7636s;
                    }
                    if ((i3 & 4) != 0) {
                        str3 = signature.f7635m;
                    }
                    return signature.copy(str, str2, str3);
                }

                @NotNull
                public final String component1() {
                    return this.f7637t;
                }

                @NotNull
                public final String component2() {
                    return this.f7636s;
                }

                @Nullable
                public final String component3() {
                    return this.f7635m;
                }

                @NotNull
                public final Signature copy(@NotNull String str, @NotNull String str2, @Nullable String str3) {
                    Intrinsics.checkNotNullParameter(str, "t");
                    Intrinsics.checkNotNullParameter(str2, "s");
                    return new Signature(str, str2, str3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Signature)) {
                        return false;
                    }
                    Signature signature = (Signature) obj;
                    return Intrinsics.areEqual((Object) this.f7637t, (Object) signature.f7637t) && Intrinsics.areEqual((Object) this.f7636s, (Object) signature.f7636s) && Intrinsics.areEqual((Object) this.f7635m, (Object) signature.f7635m);
                }

                @Nullable
                public String getM() {
                    return this.f7635m;
                }

                @NotNull
                public String getS() {
                    return this.f7636s;
                }

                @NotNull
                public String getT() {
                    return this.f7637t;
                }

                public int hashCode() {
                    int i3 = a.i(this.f7636s, this.f7637t.hashCode() * 31, 31);
                    String str = this.f7635m;
                    return i3 + (str == null ? 0 : str.hashCode());
                }

                @NotNull
                public String toString() {
                    String str = this.f7637t;
                    String str2 = this.f7636s;
                    return A.a.n(C0118y.l("Signature(t=", str, ", s=", str2, ", m="), this.f7635m, ")");
                }

                /* JADX INFO: this call moved to the top of the method (can break code semantics) */
                public /* synthetic */ Signature(String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                    this(str, str2, (i3 & 4) != 0 ? null : str3);
                }
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Cacao(@NotNull Header header2, @NotNull Payload payload2, @NotNull Signature signature2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(header2, "header");
                Intrinsics.checkNotNullParameter(payload2, "payload");
                Intrinsics.checkNotNullParameter(signature2, "signature");
                this.header = header2;
                this.payload = payload2;
                this.signature = signature2;
            }

            public static /* synthetic */ Cacao copy$default(Cacao cacao, Header header2, Payload payload2, Signature signature2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    header2 = cacao.header;
                }
                if ((i3 & 2) != 0) {
                    payload2 = cacao.payload;
                }
                if ((i3 & 4) != 0) {
                    signature2 = cacao.signature;
                }
                return cacao.copy(header2, payload2, signature2);
            }

            @NotNull
            public final Header component1() {
                return this.header;
            }

            @NotNull
            public final Payload component2() {
                return this.payload;
            }

            @NotNull
            public final Signature component3() {
                return this.signature;
            }

            @NotNull
            public final Cacao copy(@NotNull Header header2, @NotNull Payload payload2, @NotNull Signature signature2) {
                Intrinsics.checkNotNullParameter(header2, "header");
                Intrinsics.checkNotNullParameter(payload2, "payload");
                Intrinsics.checkNotNullParameter(signature2, "signature");
                return new Cacao(header2, payload2, signature2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Cacao)) {
                    return false;
                }
                Cacao cacao = (Cacao) obj;
                return Intrinsics.areEqual((Object) this.header, (Object) cacao.header) && Intrinsics.areEqual((Object) this.payload, (Object) cacao.payload) && Intrinsics.areEqual((Object) this.signature, (Object) cacao.signature);
            }

            @NotNull
            public final Header getHeader() {
                return this.header;
            }

            @NotNull
            public final Payload getPayload() {
                return this.payload;
            }

            @NotNull
            public final Signature getSignature() {
                return this.signature;
            }

            public int hashCode() {
                int hashCode = this.payload.hashCode();
                return this.signature.hashCode() + ((hashCode + (this.header.hashCode() * 31)) * 31);
            }

            @NotNull
            public String toString() {
                Header header2 = this.header;
                Payload payload2 = this.payload;
                Signature signature2 = this.signature;
                return "Cacao(header=" + header2 + ", payload=" + payload2 + ", signature=" + signature2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Call;", "Lcom/reown/walletkit/client/Wallet$Model;", "to", "", "value", "input", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTo", "()Ljava/lang/String;", "getValue", "getInput", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Call extends Model {
            @NotNull
            private final String input;
            @NotNull
            private final String to;
            @NotNull
            private final String value;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Call(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str2, "value");
                Intrinsics.checkNotNullParameter(str3, "input");
                this.to = str;
                this.value = str2;
                this.input = str3;
            }

            public static /* synthetic */ Call copy$default(Call call, String str, String str2, String str3, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = call.to;
                }
                if ((i3 & 2) != 0) {
                    str2 = call.value;
                }
                if ((i3 & 4) != 0) {
                    str3 = call.input;
                }
                return call.copy(str, str2, str3);
            }

            @NotNull
            public final String component1() {
                return this.to;
            }

            @NotNull
            public final String component2() {
                return this.value;
            }

            @NotNull
            public final String component3() {
                return this.input;
            }

            @NotNull
            public final Call copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str2, "value");
                Intrinsics.checkNotNullParameter(str3, "input");
                return new Call(str, str2, str3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Call)) {
                    return false;
                }
                Call call = (Call) obj;
                return Intrinsics.areEqual((Object) this.to, (Object) call.to) && Intrinsics.areEqual((Object) this.value, (Object) call.value) && Intrinsics.areEqual((Object) this.input, (Object) call.input);
            }

            @NotNull
            public final String getInput() {
                return this.input;
            }

            @NotNull
            public final String getTo() {
                return this.to;
            }

            @NotNull
            public final String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.input.hashCode() + a.i(this.value, this.to.hashCode() * 31, 31);
            }

            @NotNull
            public String toString() {
                String str = this.to;
                String str2 = this.value;
                return A.a.n(C0118y.l("Call(to=", str, ", value=", str2, ", input="), this.input, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0015B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ConnectionState;", "Lcom/reown/walletkit/client/Wallet$Model;", "isAvailable", "", "reason", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "<init>", "(ZLcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;)V", "()Z", "getReason", "()Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Reason", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ConnectionState extends Model {
            private final boolean isAvailable;
            @Nullable
            private final Reason reason;

            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "ConnectionClosed", "ConnectionFailed", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason$ConnectionFailed;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static abstract class Reason extends Model {

                @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason$ConnectionClosed;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class ConnectionClosed extends Reason {
                    @NotNull
                    private final String message;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public ConnectionClosed(@NotNull String str) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        this.message = str;
                    }

                    public static /* synthetic */ ConnectionClosed copy$default(ConnectionClosed connectionClosed, String str, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            str = connectionClosed.message;
                        }
                        return connectionClosed.copy(str);
                    }

                    @NotNull
                    public final String component1() {
                        return this.message;
                    }

                    @NotNull
                    public final ConnectionClosed copy(@NotNull String str) {
                        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                        return new ConnectionClosed(str);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return (obj instanceof ConnectionClosed) && Intrinsics.areEqual((Object) this.message, (Object) ((ConnectionClosed) obj).message);
                    }

                    @NotNull
                    public final String getMessage() {
                        return this.message;
                    }

                    public int hashCode() {
                        return this.message.hashCode();
                    }

                    @NotNull
                    public String toString() {
                        return A.a.l("ConnectionClosed(message=", this.message, ")");
                    }
                }

                @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason$ConnectionFailed;", "Lcom/reown/walletkit/client/Wallet$Model$ConnectionState$Reason;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class ConnectionFailed extends Reason {
                    @NotNull
                    private final Throwable throwable;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public ConnectionFailed(@NotNull Throwable th) {
                        super((DefaultConstructorMarker) null);
                        Intrinsics.checkNotNullParameter(th, "throwable");
                        this.throwable = th;
                    }

                    public static /* synthetic */ ConnectionFailed copy$default(ConnectionFailed connectionFailed, Throwable th, int i3, Object obj) {
                        if ((i3 & 1) != 0) {
                            th = connectionFailed.throwable;
                        }
                        return connectionFailed.copy(th);
                    }

                    @NotNull
                    public final Throwable component1() {
                        return this.throwable;
                    }

                    @NotNull
                    public final ConnectionFailed copy(@NotNull Throwable th) {
                        Intrinsics.checkNotNullParameter(th, "throwable");
                        return new ConnectionFailed(th);
                    }

                    public boolean equals(@Nullable Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return (obj instanceof ConnectionFailed) && Intrinsics.areEqual((Object) this.throwable, (Object) ((ConnectionFailed) obj).throwable);
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
                        return "ConnectionFailed(throwable=" + th + ")";
                    }
                }

                public /* synthetic */ Reason(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Reason() {
                    super((DefaultConstructorMarker) null);
                }
            }

            public ConnectionState(boolean z2, @Nullable Reason reason2) {
                super((DefaultConstructorMarker) null);
                this.isAvailable = z2;
                this.reason = reason2;
            }

            public static /* synthetic */ ConnectionState copy$default(ConnectionState connectionState, boolean z2, Reason reason2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    z2 = connectionState.isAvailable;
                }
                if ((i3 & 2) != 0) {
                    reason2 = connectionState.reason;
                }
                return connectionState.copy(z2, reason2);
            }

            public final boolean component1() {
                return this.isAvailable;
            }

            @Nullable
            public final Reason component2() {
                return this.reason;
            }

            @NotNull
            public final ConnectionState copy(boolean z2, @Nullable Reason reason2) {
                return new ConnectionState(z2, reason2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ConnectionState)) {
                    return false;
                }
                ConnectionState connectionState = (ConnectionState) obj;
                return this.isAvailable == connectionState.isAvailable && Intrinsics.areEqual((Object) this.reason, (Object) connectionState.reason);
            }

            @Nullable
            public final Reason getReason() {
                return this.reason;
            }

            public int hashCode() {
                int hashCode = Boolean.hashCode(this.isAvailable) * 31;
                Reason reason2 = this.reason;
                return hashCode + (reason2 == null ? 0 : reason2.hashCode());
            }

            public final boolean isAvailable() {
                return this.isAvailable;
            }

            @NotNull
            public String toString() {
                boolean z2 = this.isAvailable;
                Reason reason2 = this.reason;
                return "ConnectionState(isAvailable=" + z2 + ", reason=" + reason2 + ")";
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ ConnectionState(boolean z2, Reason reason2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(z2, (i3 & 2) != 0 ? null : reason2);
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Error;", "Lcom/reown/walletkit/client/Wallet$Model;", "throwable", "", "<init>", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$EstimatedFees;", "Lcom/reown/walletkit/client/Wallet$Model;", "maxFeePerGas", "", "maxPriorityFeePerGas", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getMaxFeePerGas", "()Ljava/lang/String;", "getMaxPriorityFeePerGas", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class EstimatedFees extends Model {
            @NotNull
            private final String maxFeePerGas;
            @NotNull
            private final String maxPriorityFeePerGas;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public EstimatedFees(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "maxFeePerGas");
                Intrinsics.checkNotNullParameter(str2, "maxPriorityFeePerGas");
                this.maxFeePerGas = str;
                this.maxPriorityFeePerGas = str2;
            }

            public static /* synthetic */ EstimatedFees copy$default(EstimatedFees estimatedFees, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = estimatedFees.maxFeePerGas;
                }
                if ((i3 & 2) != 0) {
                    str2 = estimatedFees.maxPriorityFeePerGas;
                }
                return estimatedFees.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.maxFeePerGas;
            }

            @NotNull
            public final String component2() {
                return this.maxPriorityFeePerGas;
            }

            @NotNull
            public final EstimatedFees copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "maxFeePerGas");
                Intrinsics.checkNotNullParameter(str2, "maxPriorityFeePerGas");
                return new EstimatedFees(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof EstimatedFees)) {
                    return false;
                }
                EstimatedFees estimatedFees = (EstimatedFees) obj;
                return Intrinsics.areEqual((Object) this.maxFeePerGas, (Object) estimatedFees.maxFeePerGas) && Intrinsics.areEqual((Object) this.maxPriorityFeePerGas, (Object) estimatedFees.maxPriorityFeePerGas);
            }

            @NotNull
            public final String getMaxFeePerGas() {
                return this.maxFeePerGas;
            }

            @NotNull
            public final String getMaxPriorityFeePerGas() {
                return this.maxPriorityFeePerGas;
            }

            public int hashCode() {
                return this.maxPriorityFeePerGas.hashCode() + (this.maxFeePerGas.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("EstimatedFees(maxFeePerGas=", this.maxFeePerGas, ", maxPriorityFeePerGas=", this.maxPriorityFeePerGas, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Event;", "Lcom/reown/walletkit/client/Wallet$Model;", "topic", "", "name", "data", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getName", "getData", "getChainId", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Event extends Model {
            @NotNull
            private final String chainId;
            @NotNull
            private final String data;
            @NotNull
            private final String name;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Event(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "name");
                Intrinsics.checkNotNullParameter(str3, "data");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                this.topic = str;
                this.name = str2;
                this.data = str3;
                this.chainId = str4;
            }

            public static /* synthetic */ Event copy$default(Event event, String str, String str2, String str3, String str4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = event.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = event.name;
                }
                if ((i3 & 4) != 0) {
                    str3 = event.data;
                }
                if ((i3 & 8) != 0) {
                    str4 = event.chainId;
                }
                return event.copy(str, str2, str3, str4);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.name;
            }

            @NotNull
            public final String component3() {
                return this.data;
            }

            @NotNull
            public final String component4() {
                return this.chainId;
            }

            @NotNull
            public final Event copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "name");
                Intrinsics.checkNotNullParameter(str3, "data");
                Intrinsics.checkNotNullParameter(str4, "chainId");
                return new Event(str, str2, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Event)) {
                    return false;
                }
                Event event = (Event) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) event.topic) && Intrinsics.areEqual((Object) this.name, (Object) event.name) && Intrinsics.areEqual((Object) this.data, (Object) event.data) && Intrinsics.areEqual((Object) this.chainId, (Object) event.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getData() {
                return this.data;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.chainId.hashCode() + a.i(this.data, a.i(this.name, this.topic.hashCode() * 31, 31), 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                String str2 = this.name;
                return android.support.v4.media.session.a.r(C0118y.l("Event(topic=", str, ", name=", str2, ", data="), this.data, ", chainId=", this.chainId, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ExecuteSuccess;", "Lcom/reown/walletkit/client/Wallet$Model;", "initialTxHash", "", "initialTxReceipt", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getInitialTxHash", "()Ljava/lang/String;", "getInitialTxReceipt", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExecuteSuccess extends Model {
            @NotNull
            private final String initialTxHash;
            @NotNull
            private final String initialTxReceipt;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExecuteSuccess(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "initialTxHash");
                Intrinsics.checkNotNullParameter(str2, "initialTxReceipt");
                this.initialTxHash = str;
                this.initialTxReceipt = str2;
            }

            public static /* synthetic */ ExecuteSuccess copy$default(ExecuteSuccess executeSuccess, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = executeSuccess.initialTxHash;
                }
                if ((i3 & 2) != 0) {
                    str2 = executeSuccess.initialTxReceipt;
                }
                return executeSuccess.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.initialTxHash;
            }

            @NotNull
            public final String component2() {
                return this.initialTxReceipt;
            }

            @NotNull
            public final ExecuteSuccess copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "initialTxHash");
                Intrinsics.checkNotNullParameter(str2, "initialTxReceipt");
                return new ExecuteSuccess(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExecuteSuccess)) {
                    return false;
                }
                ExecuteSuccess executeSuccess = (ExecuteSuccess) obj;
                return Intrinsics.areEqual((Object) this.initialTxHash, (Object) executeSuccess.initialTxHash) && Intrinsics.areEqual((Object) this.initialTxReceipt, (Object) executeSuccess.initialTxReceipt);
            }

            @NotNull
            public final String getInitialTxHash() {
                return this.initialTxHash;
            }

            @NotNull
            public final String getInitialTxReceipt() {
                return this.initialTxReceipt;
            }

            public int hashCode() {
                return this.initialTxReceipt.hashCode() + (this.initialTxHash.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("ExecuteSuccess(initialTxHash=", this.initialTxHash, ", initialTxReceipt=", this.initialTxReceipt, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ExpiredProposal;", "Lcom/reown/walletkit/client/Wallet$Model;", "pairingTopic", "", "proposerPublicKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPairingTopic", "()Ljava/lang/String;", "getProposerPublicKey", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExpiredProposal extends Model {
            @NotNull
            private final String pairingTopic;
            @NotNull
            private final String proposerPublicKey;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExpiredProposal(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
                this.pairingTopic = str;
                this.proposerPublicKey = str2;
            }

            public static /* synthetic */ ExpiredProposal copy$default(ExpiredProposal expiredProposal, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = expiredProposal.pairingTopic;
                }
                if ((i3 & 2) != 0) {
                    str2 = expiredProposal.proposerPublicKey;
                }
                return expiredProposal.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component2() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final ExpiredProposal copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, "proposerPublicKey");
                return new ExpiredProposal(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExpiredProposal)) {
                    return false;
                }
                ExpiredProposal expiredProposal = (ExpiredProposal) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) expiredProposal.pairingTopic) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) expiredProposal.proposerPublicKey);
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            public int hashCode() {
                return this.proposerPublicKey.hashCode() + (this.pairingTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("ExpiredProposal(pairingTopic=", this.pairingTopic, ", proposerPublicKey=", this.proposerPublicKey, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$ExpiredRequest;", "Lcom/reown/walletkit/client/Wallet$Model;", "topic", "", "id", "", "<init>", "(Ljava/lang/String;J)V", "getTopic", "()Ljava/lang/String;", "getId", "()J", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ExpiredRequest extends Model {
            private final long id;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ExpiredRequest(@NotNull String str, long j2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
                this.id = j2;
            }

            public static /* synthetic */ ExpiredRequest copy$default(ExpiredRequest expiredRequest, String str, long j2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = expiredRequest.topic;
                }
                if ((i3 & 2) != 0) {
                    j2 = expiredRequest.id;
                }
                return expiredRequest.copy(str, j2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            public final long component2() {
                return this.id;
            }

            @NotNull
            public final ExpiredRequest copy(@NotNull String str, long j2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new ExpiredRequest(str, j2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ExpiredRequest)) {
                    return false;
                }
                ExpiredRequest expiredRequest = (ExpiredRequest) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) expiredRequest.topic) && this.id == expiredRequest.id;
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return Long.hashCode(this.id) + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(this.id, "ExpiredRequest(topic=", this.topic, ", id=");
                i3.append(")");
                return i3.toString();
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003Jc\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u001a\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011¨\u00063"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;", "Lcom/reown/walletkit/client/Wallet$Model;", "from", "", "to", "value", "gasLimit", "input", "nonce", "maxFeePerGas", "maxPriorityFeePerGas", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "setFrom", "(Ljava/lang/String;)V", "getTo", "setTo", "getValue", "setValue", "getGasLimit", "setGasLimit", "getInput", "setInput", "getNonce", "setNonce", "getMaxFeePerGas", "setMaxFeePerGas", "getMaxPriorityFeePerGas", "setMaxPriorityFeePerGas", "getChainId", "setChainId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class FeeEstimatedTransaction extends Model {
            @NotNull
            private String chainId;
            @NotNull
            private String from;
            @NotNull
            private String gasLimit;
            @NotNull
            private String input;
            @NotNull
            private String maxFeePerGas;
            @NotNull
            private String maxPriorityFeePerGas;
            @NotNull
            private String nonce;
            @NotNull
            private String to;
            @NotNull
            private String value;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FeeEstimatedTransaction(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "gasLimit");
                Intrinsics.checkNotNullParameter(str5, "input");
                Intrinsics.checkNotNullParameter(str6, "nonce");
                Intrinsics.checkNotNullParameter(str7, "maxFeePerGas");
                Intrinsics.checkNotNullParameter(str8, "maxPriorityFeePerGas");
                Intrinsics.checkNotNullParameter(str9, "chainId");
                this.from = str;
                this.to = str2;
                this.value = str3;
                this.gasLimit = str4;
                this.input = str5;
                this.nonce = str6;
                this.maxFeePerGas = str7;
                this.maxPriorityFeePerGas = str8;
                this.chainId = str9;
            }

            public static /* synthetic */ FeeEstimatedTransaction copy$default(FeeEstimatedTransaction feeEstimatedTransaction, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i3, Object obj) {
                FeeEstimatedTransaction feeEstimatedTransaction2 = feeEstimatedTransaction;
                int i4 = i3;
                return feeEstimatedTransaction.copy((i4 & 1) != 0 ? feeEstimatedTransaction2.from : str, (i4 & 2) != 0 ? feeEstimatedTransaction2.to : str2, (i4 & 4) != 0 ? feeEstimatedTransaction2.value : str3, (i4 & 8) != 0 ? feeEstimatedTransaction2.gasLimit : str4, (i4 & 16) != 0 ? feeEstimatedTransaction2.input : str5, (i4 & 32) != 0 ? feeEstimatedTransaction2.nonce : str6, (i4 & 64) != 0 ? feeEstimatedTransaction2.maxFeePerGas : str7, (i4 & 128) != 0 ? feeEstimatedTransaction2.maxPriorityFeePerGas : str8, (i4 & 256) != 0 ? feeEstimatedTransaction2.chainId : str9);
            }

            @NotNull
            public final String component1() {
                return this.from;
            }

            @NotNull
            public final String component2() {
                return this.to;
            }

            @NotNull
            public final String component3() {
                return this.value;
            }

            @NotNull
            public final String component4() {
                return this.gasLimit;
            }

            @NotNull
            public final String component5() {
                return this.input;
            }

            @NotNull
            public final String component6() {
                return this.nonce;
            }

            @NotNull
            public final String component7() {
                return this.maxFeePerGas;
            }

            @NotNull
            public final String component8() {
                return this.maxPriorityFeePerGas;
            }

            @NotNull
            public final String component9() {
                return this.chainId;
            }

            @NotNull
            public final FeeEstimatedTransaction copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, @NotNull String str9) {
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "gasLimit");
                String str10 = str5;
                Intrinsics.checkNotNullParameter(str10, "input");
                String str11 = str6;
                Intrinsics.checkNotNullParameter(str11, "nonce");
                String str12 = str7;
                Intrinsics.checkNotNullParameter(str12, "maxFeePerGas");
                String str13 = str8;
                Intrinsics.checkNotNullParameter(str13, "maxPriorityFeePerGas");
                String str14 = str9;
                Intrinsics.checkNotNullParameter(str14, "chainId");
                return new FeeEstimatedTransaction(str, str2, str3, str4, str10, str11, str12, str13, str14);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FeeEstimatedTransaction)) {
                    return false;
                }
                FeeEstimatedTransaction feeEstimatedTransaction = (FeeEstimatedTransaction) obj;
                return Intrinsics.areEqual((Object) this.from, (Object) feeEstimatedTransaction.from) && Intrinsics.areEqual((Object) this.to, (Object) feeEstimatedTransaction.to) && Intrinsics.areEqual((Object) this.value, (Object) feeEstimatedTransaction.value) && Intrinsics.areEqual((Object) this.gasLimit, (Object) feeEstimatedTransaction.gasLimit) && Intrinsics.areEqual((Object) this.input, (Object) feeEstimatedTransaction.input) && Intrinsics.areEqual((Object) this.nonce, (Object) feeEstimatedTransaction.nonce) && Intrinsics.areEqual((Object) this.maxFeePerGas, (Object) feeEstimatedTransaction.maxFeePerGas) && Intrinsics.areEqual((Object) this.maxPriorityFeePerGas, (Object) feeEstimatedTransaction.maxPriorityFeePerGas) && Intrinsics.areEqual((Object) this.chainId, (Object) feeEstimatedTransaction.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getFrom() {
                return this.from;
            }

            @NotNull
            public final String getGasLimit() {
                return this.gasLimit;
            }

            @NotNull
            public final String getInput() {
                return this.input;
            }

            @NotNull
            public final String getMaxFeePerGas() {
                return this.maxFeePerGas;
            }

            @NotNull
            public final String getMaxPriorityFeePerGas() {
                return this.maxPriorityFeePerGas;
            }

            @NotNull
            public final String getNonce() {
                return this.nonce;
            }

            @NotNull
            public final String getTo() {
                return this.to;
            }

            @NotNull
            public final String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.chainId.hashCode() + a.i(this.maxPriorityFeePerGas, a.i(this.maxFeePerGas, a.i(this.nonce, a.i(this.input, a.i(this.gasLimit, a.i(this.value, a.i(this.to, this.from.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31);
            }

            public final void setChainId(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.chainId = str;
            }

            public final void setFrom(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.from = str;
            }

            public final void setGasLimit(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.gasLimit = str;
            }

            public final void setInput(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.input = str;
            }

            public final void setMaxFeePerGas(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.maxFeePerGas = str;
            }

            public final void setMaxPriorityFeePerGas(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.maxPriorityFeePerGas = str;
            }

            public final void setNonce(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.nonce = str;
            }

            public final void setTo(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.to = str;
            }

            public final void setValue(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.value = str;
            }

            @NotNull
            public String toString() {
                String str = this.from;
                String str2 = this.to;
                String str3 = this.value;
                String str4 = this.gasLimit;
                String str5 = this.input;
                String str6 = this.nonce;
                String str7 = this.maxFeePerGas;
                String str8 = this.maxPriorityFeePerGas;
                String str9 = this.chainId;
                StringBuilder l2 = C0118y.l("FeeEstimatedTransaction(from=", str, ", to=", str2, ", value=");
                b.w(l2, str3, ", gasLimit=", str4, ", input=");
                b.w(l2, str5, ", nonce=", str6, ", maxFeePerGas=");
                b.w(l2, str7, ", maxPriorityFeePerGas=", str8, ", chainId=");
                return A.a.n(l2, str9, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020\tHÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006)"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$FundingMetadata;", "Lcom/reown/walletkit/client/Wallet$Model;", "chainId", "", "tokenContract", "symbol", "amount", "bridgingFee", "decimals", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getChainId", "()Ljava/lang/String;", "setChainId", "(Ljava/lang/String;)V", "getTokenContract", "setTokenContract", "getSymbol", "setSymbol", "getAmount", "setAmount", "getBridgingFee", "setBridgingFee", "getDecimals", "()I", "setDecimals", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class FundingMetadata extends Model {
            @NotNull
            private String amount;
            @NotNull
            private String bridgingFee;
            @NotNull
            private String chainId;
            private int decimals;
            @NotNull
            private String symbol;
            @NotNull
            private String tokenContract;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FundingMetadata(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, int i3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "chainId");
                Intrinsics.checkNotNullParameter(str2, "tokenContract");
                Intrinsics.checkNotNullParameter(str3, "symbol");
                Intrinsics.checkNotNullParameter(str4, "amount");
                Intrinsics.checkNotNullParameter(str5, "bridgingFee");
                this.chainId = str;
                this.tokenContract = str2;
                this.symbol = str3;
                this.amount = str4;
                this.bridgingFee = str5;
                this.decimals = i3;
            }

            public static /* synthetic */ FundingMetadata copy$default(FundingMetadata fundingMetadata, String str, String str2, String str3, String str4, String str5, int i3, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    str = fundingMetadata.chainId;
                }
                if ((i4 & 2) != 0) {
                    str2 = fundingMetadata.tokenContract;
                }
                String str6 = str2;
                if ((i4 & 4) != 0) {
                    str3 = fundingMetadata.symbol;
                }
                String str7 = str3;
                if ((i4 & 8) != 0) {
                    str4 = fundingMetadata.amount;
                }
                String str8 = str4;
                if ((i4 & 16) != 0) {
                    str5 = fundingMetadata.bridgingFee;
                }
                String str9 = str5;
                if ((i4 & 32) != 0) {
                    i3 = fundingMetadata.decimals;
                }
                return fundingMetadata.copy(str, str6, str7, str8, str9, i3);
            }

            @NotNull
            public final String component1() {
                return this.chainId;
            }

            @NotNull
            public final String component2() {
                return this.tokenContract;
            }

            @NotNull
            public final String component3() {
                return this.symbol;
            }

            @NotNull
            public final String component4() {
                return this.amount;
            }

            @NotNull
            public final String component5() {
                return this.bridgingFee;
            }

            public final int component6() {
                return this.decimals;
            }

            @NotNull
            public final FundingMetadata copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, int i3) {
                Intrinsics.checkNotNullParameter(str, "chainId");
                Intrinsics.checkNotNullParameter(str2, "tokenContract");
                Intrinsics.checkNotNullParameter(str3, "symbol");
                Intrinsics.checkNotNullParameter(str4, "amount");
                Intrinsics.checkNotNullParameter(str5, "bridgingFee");
                return new FundingMetadata(str, str2, str3, str4, str5, i3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FundingMetadata)) {
                    return false;
                }
                FundingMetadata fundingMetadata = (FundingMetadata) obj;
                return Intrinsics.areEqual((Object) this.chainId, (Object) fundingMetadata.chainId) && Intrinsics.areEqual((Object) this.tokenContract, (Object) fundingMetadata.tokenContract) && Intrinsics.areEqual((Object) this.symbol, (Object) fundingMetadata.symbol) && Intrinsics.areEqual((Object) this.amount, (Object) fundingMetadata.amount) && Intrinsics.areEqual((Object) this.bridgingFee, (Object) fundingMetadata.bridgingFee) && this.decimals == fundingMetadata.decimals;
            }

            @NotNull
            public final String getAmount() {
                return this.amount;
            }

            @NotNull
            public final String getBridgingFee() {
                return this.bridgingFee;
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            public final int getDecimals() {
                return this.decimals;
            }

            @NotNull
            public final String getSymbol() {
                return this.symbol;
            }

            @NotNull
            public final String getTokenContract() {
                return this.tokenContract;
            }

            public int hashCode() {
                return Integer.hashCode(this.decimals) + a.i(this.bridgingFee, a.i(this.amount, a.i(this.symbol, a.i(this.tokenContract, this.chainId.hashCode() * 31, 31), 31), 31), 31);
            }

            public final void setAmount(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.amount = str;
            }

            public final void setBridgingFee(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.bridgingFee = str;
            }

            public final void setChainId(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.chainId = str;
            }

            public final void setDecimals(int i3) {
                this.decimals = i3;
            }

            public final void setSymbol(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.symbol = str;
            }

            public final void setTokenContract(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.tokenContract = str;
            }

            @NotNull
            public String toString() {
                String str = this.chainId;
                String str2 = this.tokenContract;
                String str3 = this.symbol;
                String str4 = this.amount;
                String str5 = this.bridgingFee;
                int i3 = this.decimals;
                StringBuilder l2 = C0118y.l("FundingMetadata(chainId=", str, ", tokenContract=", str2, ", symbol=");
                b.w(l2, str3, ", amount=", str4, ", bridgingFee=");
                l2.append(str5);
                l2.append(", decimals=");
                l2.append(i3);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006#"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$InitialTransaction;", "Lcom/reown/walletkit/client/Wallet$Model;", "from", "", "to", "value", "input", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "setFrom", "(Ljava/lang/String;)V", "getTo", "setTo", "getValue", "setValue", "getInput", "setInput", "getChainId", "setChainId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class InitialTransaction extends Model {
            @NotNull
            private String chainId;
            @NotNull
            private String from;
            @NotNull
            private String input;
            @NotNull
            private String to;
            @NotNull
            private String value;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public InitialTransaction(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "input");
                Intrinsics.checkNotNullParameter(str5, "chainId");
                this.from = str;
                this.to = str2;
                this.value = str3;
                this.input = str4;
                this.chainId = str5;
            }

            public static /* synthetic */ InitialTransaction copy$default(InitialTransaction initialTransaction, String str, String str2, String str3, String str4, String str5, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = initialTransaction.from;
                }
                if ((i3 & 2) != 0) {
                    str2 = initialTransaction.to;
                }
                String str6 = str2;
                if ((i3 & 4) != 0) {
                    str3 = initialTransaction.value;
                }
                String str7 = str3;
                if ((i3 & 8) != 0) {
                    str4 = initialTransaction.input;
                }
                String str8 = str4;
                if ((i3 & 16) != 0) {
                    str5 = initialTransaction.chainId;
                }
                return initialTransaction.copy(str, str6, str7, str8, str5);
            }

            @NotNull
            public final String component1() {
                return this.from;
            }

            @NotNull
            public final String component2() {
                return this.to;
            }

            @NotNull
            public final String component3() {
                return this.value;
            }

            @NotNull
            public final String component4() {
                return this.input;
            }

            @NotNull
            public final String component5() {
                return this.chainId;
            }

            @NotNull
            public final InitialTransaction copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "input");
                Intrinsics.checkNotNullParameter(str5, "chainId");
                return new InitialTransaction(str, str2, str3, str4, str5);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof InitialTransaction)) {
                    return false;
                }
                InitialTransaction initialTransaction = (InitialTransaction) obj;
                return Intrinsics.areEqual((Object) this.from, (Object) initialTransaction.from) && Intrinsics.areEqual((Object) this.to, (Object) initialTransaction.to) && Intrinsics.areEqual((Object) this.value, (Object) initialTransaction.value) && Intrinsics.areEqual((Object) this.input, (Object) initialTransaction.input) && Intrinsics.areEqual((Object) this.chainId, (Object) initialTransaction.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getFrom() {
                return this.from;
            }

            @NotNull
            public final String getInput() {
                return this.input;
            }

            @NotNull
            public final String getTo() {
                return this.to;
            }

            @NotNull
            public final String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.chainId.hashCode() + a.i(this.input, a.i(this.value, a.i(this.to, this.from.hashCode() * 31, 31), 31), 31);
            }

            public final void setChainId(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.chainId = str;
            }

            public final void setFrom(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.from = str;
            }

            public final void setInput(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.input = str;
            }

            public final void setTo(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.to = str;
            }

            public final void setValue(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.value = str;
            }

            @NotNull
            public String toString() {
                String str = this.from;
                String str2 = this.to;
                String str3 = this.value;
                String str4 = this.input;
                String str5 = this.chainId;
                StringBuilder l2 = C0118y.l("InitialTransaction(from=", str, ", to=", str2, ", value=");
                b.w(l2, str3, ", input=", str4, ", chainId=");
                return A.a.n(l2, str5, ")");
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J;\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u0006HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006%"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$InitialTransactionMetadata;", "Lcom/reown/walletkit/client/Wallet$Model;", "symbol", "", "amount", "decimals", "", "tokenContract", "transferTo", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getSymbol", "()Ljava/lang/String;", "setSymbol", "(Ljava/lang/String;)V", "getAmount", "setAmount", "getDecimals", "()I", "setDecimals", "(I)V", "getTokenContract", "setTokenContract", "getTransferTo", "setTransferTo", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class InitialTransactionMetadata extends Model {
            @NotNull
            private String amount;
            private int decimals;
            @NotNull
            private String symbol;
            @NotNull
            private String tokenContract;
            @NotNull
            private String transferTo;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public InitialTransactionMetadata(@NotNull String str, @NotNull String str2, int i3, @NotNull String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "symbol");
                Intrinsics.checkNotNullParameter(str2, "amount");
                Intrinsics.checkNotNullParameter(str3, "tokenContract");
                Intrinsics.checkNotNullParameter(str4, "transferTo");
                this.symbol = str;
                this.amount = str2;
                this.decimals = i3;
                this.tokenContract = str3;
                this.transferTo = str4;
            }

            public static /* synthetic */ InitialTransactionMetadata copy$default(InitialTransactionMetadata initialTransactionMetadata, String str, String str2, int i3, String str3, String str4, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    str = initialTransactionMetadata.symbol;
                }
                if ((i4 & 2) != 0) {
                    str2 = initialTransactionMetadata.amount;
                }
                String str5 = str2;
                if ((i4 & 4) != 0) {
                    i3 = initialTransactionMetadata.decimals;
                }
                int i5 = i3;
                if ((i4 & 8) != 0) {
                    str3 = initialTransactionMetadata.tokenContract;
                }
                String str6 = str3;
                if ((i4 & 16) != 0) {
                    str4 = initialTransactionMetadata.transferTo;
                }
                return initialTransactionMetadata.copy(str, str5, i5, str6, str4);
            }

            @NotNull
            public final String component1() {
                return this.symbol;
            }

            @NotNull
            public final String component2() {
                return this.amount;
            }

            public final int component3() {
                return this.decimals;
            }

            @NotNull
            public final String component4() {
                return this.tokenContract;
            }

            @NotNull
            public final String component5() {
                return this.transferTo;
            }

            @NotNull
            public final InitialTransactionMetadata copy(@NotNull String str, @NotNull String str2, int i3, @NotNull String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, "symbol");
                Intrinsics.checkNotNullParameter(str2, "amount");
                Intrinsics.checkNotNullParameter(str3, "tokenContract");
                Intrinsics.checkNotNullParameter(str4, "transferTo");
                return new InitialTransactionMetadata(str, str2, i3, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof InitialTransactionMetadata)) {
                    return false;
                }
                InitialTransactionMetadata initialTransactionMetadata = (InitialTransactionMetadata) obj;
                return Intrinsics.areEqual((Object) this.symbol, (Object) initialTransactionMetadata.symbol) && Intrinsics.areEqual((Object) this.amount, (Object) initialTransactionMetadata.amount) && this.decimals == initialTransactionMetadata.decimals && Intrinsics.areEqual((Object) this.tokenContract, (Object) initialTransactionMetadata.tokenContract) && Intrinsics.areEqual((Object) this.transferTo, (Object) initialTransactionMetadata.transferTo);
            }

            @NotNull
            public final String getAmount() {
                return this.amount;
            }

            public final int getDecimals() {
                return this.decimals;
            }

            @NotNull
            public final String getSymbol() {
                return this.symbol;
            }

            @NotNull
            public final String getTokenContract() {
                return this.tokenContract;
            }

            @NotNull
            public final String getTransferTo() {
                return this.transferTo;
            }

            public int hashCode() {
                return this.transferTo.hashCode() + a.i(this.tokenContract, a.c(this.decimals, a.i(this.amount, this.symbol.hashCode() * 31, 31), 31), 31);
            }

            public final void setAmount(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.amount = str;
            }

            public final void setDecimals(int i3) {
                this.decimals = i3;
            }

            public final void setSymbol(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.symbol = str;
            }

            public final void setTokenContract(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.tokenContract = str;
            }

            public final void setTransferTo(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.transferTo = str;
            }

            @NotNull
            public String toString() {
                String str = this.symbol;
                String str2 = this.amount;
                int i3 = this.decimals;
                String str3 = this.tokenContract;
                String str4 = this.transferTo;
                StringBuilder l2 = C0118y.l("InitialTransactionMetadata(symbol=", str, ", amount=", str2, ", decimals=");
                l2.append(i3);
                l2.append(", tokenContract=");
                l2.append(str3);
                l2.append(", transferTo=");
                return A.a.n(l2, str4, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tXD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "id", "", "getId", "()J", "jsonrpc", "", "getJsonrpc", "()Ljava/lang/String;", "JsonRpcResult", "JsonRpcError", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcResult;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class JsonRpcResponse extends Model {
            @NotNull
            private final String jsonrpc;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcError;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "id", "", "code", "", "message", "", "<init>", "(JILjava/lang/String;)V", "getId", "()J", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcError extends JsonRpcResponse {
                private final int code;
                private final long id;
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JsonRpcError(long j2, int i3, @NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.id = j2;
                    this.code = i3;
                    this.message = str;
                }

                public static /* synthetic */ JsonRpcError copy$default(JsonRpcError jsonRpcError, long j2, int i3, String str, int i4, Object obj) {
                    if ((i4 & 1) != 0) {
                        j2 = jsonRpcError.id;
                    }
                    if ((i4 & 2) != 0) {
                        i3 = jsonRpcError.code;
                    }
                    if ((i4 & 4) != 0) {
                        str = jsonRpcError.message;
                    }
                    return jsonRpcError.copy(j2, i3, str);
                }

                public final long component1() {
                    return this.id;
                }

                public final int component2() {
                    return this.code;
                }

                @NotNull
                public final String component3() {
                    return this.message;
                }

                @NotNull
                public final JsonRpcError copy(long j2, int i3, @NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new JsonRpcError(j2, i3, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcError)) {
                        return false;
                    }
                    JsonRpcError jsonRpcError = (JsonRpcError) obj;
                    return this.id == jsonRpcError.id && this.code == jsonRpcError.code && Intrinsics.areEqual((Object) this.message, (Object) jsonRpcError.message);
                }

                public final int getCode() {
                    return this.code;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode() + a.c(this.code, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    int i3 = this.code;
                    String str = this.message;
                    StringBuilder sb = new StringBuilder("JsonRpcError(id=");
                    sb.append(j2);
                    sb.append(", code=");
                    sb.append(i3);
                    return C0118y.j(sb, ", message=", str, ")");
                }
            }

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse$JsonRpcResult;", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "id", "", "result", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getResult", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JsonRpcResult extends JsonRpcResponse {
                private final long id;
                @Nullable
                private final String result;

                public JsonRpcResult(long j2, @Nullable String str) {
                    super((DefaultConstructorMarker) null);
                    this.id = j2;
                    this.result = str;
                }

                public static /* synthetic */ JsonRpcResult copy$default(JsonRpcResult jsonRpcResult, long j2, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = jsonRpcResult.id;
                    }
                    if ((i3 & 2) != 0) {
                        str = jsonRpcResult.result;
                    }
                    return jsonRpcResult.copy(j2, str);
                }

                public final long component1() {
                    return this.id;
                }

                @Nullable
                public final String component2() {
                    return this.result;
                }

                @NotNull
                public final JsonRpcResult copy(long j2, @Nullable String str) {
                    return new JsonRpcResult(j2, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof JsonRpcResult)) {
                        return false;
                    }
                    JsonRpcResult jsonRpcResult = (JsonRpcResult) obj;
                    return this.id == jsonRpcResult.id && Intrinsics.areEqual((Object) this.result, (Object) jsonRpcResult.result);
                }

                public long getId() {
                    return this.id;
                }

                @Nullable
                public final String getResult() {
                    return this.result;
                }

                public int hashCode() {
                    int hashCode = Long.hashCode(this.id) * 31;
                    String str = this.result;
                    return hashCode + (str == null ? 0 : str.hashCode());
                }

                @NotNull
                public String toString() {
                    StringBuilder v2 = androidx.work.impl.a.v(this.id, "JsonRpcResult(id=", ", result=", this.result);
                    v2.append(")");
                    return v2.toString();
                }
            }

            public /* synthetic */ JsonRpcResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public abstract long getId();

            @NotNull
            public final String getJsonrpc() {
                return this.jsonrpc;
            }

            private JsonRpcResponse() {
                super((DefaultConstructorMarker) null);
                this.jsonrpc = "2.0";
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Message;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Simple", "SessionProposal", "SessionRequest", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest$JSONRPCRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Message$Simple;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Message extends Model {

            @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0015\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u0017\u00100\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¹\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u000108HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\t\u0010;\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u001f\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010!R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006<"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Message$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "id", "", "pairingTopic", "", "name", "description", "url", "icons", "", "redirect", "requiredNamespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

            @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "topic", "", "chainId", "peerMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "request", "Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest$JSONRPCRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest$JSONRPCRequest;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRequest", "()Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest$JSONRPCRequest;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class SessionRequest extends Message {
                @Nullable
                private final String chainId;
                @Nullable
                private final Core.Model.AppMetaData peerMetaData;
                @NotNull
                private final JSONRPCRequest request;
                @NotNull
                private final String topic;

                @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Message$SessionRequest$JSONRPCRequest;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
                public static final class JSONRPCRequest extends Message {
                    private final long id;
                    @NotNull
                    private final String method;
                    @NotNull
                    private final String params;

                    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                    public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                        super((DefaultConstructorMarker) null);
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
                public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                    this.topic = str;
                    this.chainId = str2;
                    this.peerMetaData = appMetaData;
                    this.request = jSONRPCRequest;
                }

                public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, Core.Model.AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, int i3, Object obj) {
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
                public final Core.Model.AppMetaData component3() {
                    return this.peerMetaData;
                }

                @NotNull
                public final JSONRPCRequest component4() {
                    return this.request;
                }

                @NotNull
                public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
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
                public final Core.Model.AppMetaData getPeerMetaData() {
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
                    Core.Model.AppMetaData appMetaData = this.peerMetaData;
                    if (appMetaData != null) {
                        i3 = appMetaData.hashCode();
                    }
                    return this.request.hashCode() + ((hashCode2 + i3) * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.topic;
                    String str2 = this.chainId;
                    Core.Model.AppMetaData appMetaData = this.peerMetaData;
                    JSONRPCRequest jSONRPCRequest = this.request;
                    StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerMetaData=");
                    l2.append(appMetaData);
                    l2.append(", request=");
                    l2.append(jSONRPCRequest);
                    l2.append(")");
                    return l2.toString();
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Message$Simple;", "Lcom/reown/walletkit/client/Wallet$Model$Message;", "title", "", "body", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getBody", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Namespace;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Proposal", "Session", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Namespace extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "Lcom/reown/walletkit/client/Wallet$Model$Namespace;", "chains", "", "", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getMethods", "getEvents", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003JK\u0010\u0013\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "Lcom/reown/walletkit/client/Wallet$Model$Namespace;", "chains", "", "", "accounts", "methods", "events", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getAccounts", "getMethods", "getEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bw\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0004HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0001\u0010)\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u00061"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "Lcom/reown/walletkit/client/Wallet$Model;", "chains", "", "", "domain", "nonce", "aud", "type", "iat", "nbf", "exp", "statement", "requestId", "resources", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getChains", "()Ljava/util/List;", "getDomain", "()Ljava/lang/String;", "getNonce", "getAud", "getType", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PayloadAuthRequestParams extends Model {
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
            private final List<String> resources;
            @Nullable
            private final String statement;
            @Nullable
            private final String type;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PayloadAuthRequestParams(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "chains");
                Intrinsics.checkNotNullParameter(str, "domain");
                Intrinsics.checkNotNullParameter(str2, "nonce");
                Intrinsics.checkNotNullParameter(str3, "aud");
                Intrinsics.checkNotNullParameter(str5, "iat");
                this.chains = list;
                this.domain = str;
                this.nonce = str2;
                this.aud = str3;
                this.type = str4;
                this.iat = str5;
                this.nbf = str6;
                this.exp = str7;
                this.statement = str8;
                this.requestId = str9;
                this.resources = list2;
            }

            public static /* synthetic */ PayloadAuthRequestParams copy$default(PayloadAuthRequestParams payloadAuthRequestParams, List list, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List list2, int i3, Object obj) {
                PayloadAuthRequestParams payloadAuthRequestParams2 = payloadAuthRequestParams;
                int i4 = i3;
                return payloadAuthRequestParams.copy((i4 & 1) != 0 ? payloadAuthRequestParams2.chains : list, (i4 & 2) != 0 ? payloadAuthRequestParams2.domain : str, (i4 & 4) != 0 ? payloadAuthRequestParams2.nonce : str2, (i4 & 8) != 0 ? payloadAuthRequestParams2.aud : str3, (i4 & 16) != 0 ? payloadAuthRequestParams2.type : str4, (i4 & 32) != 0 ? payloadAuthRequestParams2.iat : str5, (i4 & 64) != 0 ? payloadAuthRequestParams2.nbf : str6, (i4 & 128) != 0 ? payloadAuthRequestParams2.exp : str7, (i4 & 256) != 0 ? payloadAuthRequestParams2.statement : str8, (i4 & 512) != 0 ? payloadAuthRequestParams2.requestId : str9, (i4 & 1024) != 0 ? payloadAuthRequestParams2.resources : list2);
            }

            @NotNull
            public final List<String> component1() {
                return this.chains;
            }

            @Nullable
            public final String component10() {
                return this.requestId;
            }

            @Nullable
            public final List<String> component11() {
                return this.resources;
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

            @NotNull
            public final String component6() {
                return this.iat;
            }

            @Nullable
            public final String component7() {
                return this.nbf;
            }

            @Nullable
            public final String component8() {
                return this.exp;
            }

            @Nullable
            public final String component9() {
                return this.statement;
            }

            @NotNull
            public final PayloadAuthRequestParams copy(@NotNull List<String> list, @NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @NotNull String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable List<String> list2) {
                Intrinsics.checkNotNullParameter(list, "chains");
                Intrinsics.checkNotNullParameter(str, "domain");
                String str10 = str2;
                Intrinsics.checkNotNullParameter(str10, "nonce");
                String str11 = str3;
                Intrinsics.checkNotNullParameter(str11, "aud");
                String str12 = str5;
                Intrinsics.checkNotNullParameter(str12, "iat");
                return new PayloadAuthRequestParams(list, str, str10, str11, str4, str12, str6, str7, str8, str9, list2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PayloadAuthRequestParams)) {
                    return false;
                }
                PayloadAuthRequestParams payloadAuthRequestParams = (PayloadAuthRequestParams) obj;
                return Intrinsics.areEqual((Object) this.chains, (Object) payloadAuthRequestParams.chains) && Intrinsics.areEqual((Object) this.domain, (Object) payloadAuthRequestParams.domain) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadAuthRequestParams.nonce) && Intrinsics.areEqual((Object) this.aud, (Object) payloadAuthRequestParams.aud) && Intrinsics.areEqual((Object) this.type, (Object) payloadAuthRequestParams.type) && Intrinsics.areEqual((Object) this.iat, (Object) payloadAuthRequestParams.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadAuthRequestParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadAuthRequestParams.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payloadAuthRequestParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadAuthRequestParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadAuthRequestParams.resources);
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
                int i5 = a.i(this.iat, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
                String str2 = this.nbf;
                int hashCode = (i5 + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.exp;
                int hashCode2 = (hashCode + (str3 == null ? 0 : str3.hashCode())) * 31;
                String str4 = this.statement;
                int hashCode3 = (hashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31;
                String str5 = this.requestId;
                int hashCode4 = (hashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31;
                List<String> list = this.resources;
                if (list != null) {
                    i4 = list.hashCode();
                }
                return hashCode4 + i4;
            }

            @NotNull
            public String toString() {
                List<String> list = this.chains;
                String str = this.domain;
                String str2 = this.nonce;
                String str3 = this.aud;
                String str4 = this.type;
                String str5 = this.iat;
                String str6 = this.nbf;
                String str7 = this.exp;
                String str8 = this.statement;
                String str9 = this.requestId;
                List<String> list2 = this.resources;
                StringBuilder sb = new StringBuilder("PayloadAuthRequestParams(chains=");
                sb.append(list);
                sb.append(", domain=");
                sb.append(str);
                sb.append(", nonce=");
                b.w(sb, str2, ", aud=", str3, ", type=");
                b.w(sb, str4, ", iat=", str5, ", nbf=");
                b.w(sb, str6, ", exp=", str7, ", statement=");
                b.w(sb, str8, ", requestId=", str9, ", resources=");
                return C0118y.h(")", list2, sb);
            }
        }

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0003J\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u000202HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00064"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PayloadParams;", "Lcom/reown/walletkit/client/Wallet$Model;", "type", "", "chainId", "domain", "aud", "version", "nonce", "iat", "nbf", "exp", "statement", "requestId", "resources", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getType", "()Ljava/lang/String;", "getChainId", "getDomain", "getAud", "getVersion", "getNonce", "getIat", "getNbf", "getExp", "getStatement", "getRequestId", "getResources", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PayloadParams extends Model {
            @NotNull
            private final String aud;
            @NotNull
            private final String chainId;
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
            private final List<String> resources;
            @Nullable
            private final String statement;
            @NotNull
            private final String type;
            @NotNull
            private final String version;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PayloadParams(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable List<String> list) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "type");
                Intrinsics.checkNotNullParameter(str2, "chainId");
                Intrinsics.checkNotNullParameter(str3, "domain");
                Intrinsics.checkNotNullParameter(str4, "aud");
                Intrinsics.checkNotNullParameter(str5, "version");
                Intrinsics.checkNotNullParameter(str6, "nonce");
                Intrinsics.checkNotNullParameter(str7, "iat");
                this.type = str;
                this.chainId = str2;
                this.domain = str3;
                this.aud = str4;
                this.version = str5;
                this.nonce = str6;
                this.iat = str7;
                this.nbf = str8;
                this.exp = str9;
                this.statement = str10;
                this.requestId = str11;
                this.resources = list;
            }

            public static /* synthetic */ PayloadParams copy$default(PayloadParams payloadParams, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, List list, int i3, Object obj) {
                PayloadParams payloadParams2 = payloadParams;
                int i4 = i3;
                return payloadParams.copy((i4 & 1) != 0 ? payloadParams2.type : str, (i4 & 2) != 0 ? payloadParams2.chainId : str2, (i4 & 4) != 0 ? payloadParams2.domain : str3, (i4 & 8) != 0 ? payloadParams2.aud : str4, (i4 & 16) != 0 ? payloadParams2.version : str5, (i4 & 32) != 0 ? payloadParams2.nonce : str6, (i4 & 64) != 0 ? payloadParams2.iat : str7, (i4 & 128) != 0 ? payloadParams2.nbf : str8, (i4 & 256) != 0 ? payloadParams2.exp : str9, (i4 & 512) != 0 ? payloadParams2.statement : str10, (i4 & 1024) != 0 ? payloadParams2.requestId : str11, (i4 & 2048) != 0 ? payloadParams2.resources : list);
            }

            @NotNull
            public final String component1() {
                return this.type;
            }

            @Nullable
            public final String component10() {
                return this.statement;
            }

            @Nullable
            public final String component11() {
                return this.requestId;
            }

            @Nullable
            public final List<String> component12() {
                return this.resources;
            }

            @NotNull
            public final String component2() {
                return this.chainId;
            }

            @NotNull
            public final String component3() {
                return this.domain;
            }

            @NotNull
            public final String component4() {
                return this.aud;
            }

            @NotNull
            public final String component5() {
                return this.version;
            }

            @NotNull
            public final String component6() {
                return this.nonce;
            }

            @NotNull
            public final String component7() {
                return this.iat;
            }

            @Nullable
            public final String component8() {
                return this.nbf;
            }

            @Nullable
            public final String component9() {
                return this.exp;
            }

            @NotNull
            public final PayloadParams copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable List<String> list) {
                Intrinsics.checkNotNullParameter(str, "type");
                String str12 = str2;
                Intrinsics.checkNotNullParameter(str12, "chainId");
                String str13 = str3;
                Intrinsics.checkNotNullParameter(str13, "domain");
                String str14 = str4;
                Intrinsics.checkNotNullParameter(str14, "aud");
                String str15 = str5;
                Intrinsics.checkNotNullParameter(str15, "version");
                String str16 = str6;
                Intrinsics.checkNotNullParameter(str16, "nonce");
                String str17 = str7;
                Intrinsics.checkNotNullParameter(str17, "iat");
                return new PayloadParams(str, str12, str13, str14, str15, str16, str17, str8, str9, str10, str11, list);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PayloadParams)) {
                    return false;
                }
                PayloadParams payloadParams = (PayloadParams) obj;
                return Intrinsics.areEqual((Object) this.type, (Object) payloadParams.type) && Intrinsics.areEqual((Object) this.chainId, (Object) payloadParams.chainId) && Intrinsics.areEqual((Object) this.domain, (Object) payloadParams.domain) && Intrinsics.areEqual((Object) this.aud, (Object) payloadParams.aud) && Intrinsics.areEqual((Object) this.version, (Object) payloadParams.version) && Intrinsics.areEqual((Object) this.nonce, (Object) payloadParams.nonce) && Intrinsics.areEqual((Object) this.iat, (Object) payloadParams.iat) && Intrinsics.areEqual((Object) this.nbf, (Object) payloadParams.nbf) && Intrinsics.areEqual((Object) this.exp, (Object) payloadParams.exp) && Intrinsics.areEqual((Object) this.statement, (Object) payloadParams.statement) && Intrinsics.areEqual((Object) this.requestId, (Object) payloadParams.requestId) && Intrinsics.areEqual((Object) this.resources, (Object) payloadParams.resources);
            }

            @NotNull
            public final String getAud() {
                return this.aud;
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
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

            @NotNull
            public final String getType() {
                return this.type;
            }

            @NotNull
            public final String getVersion() {
                return this.version;
            }

            public int hashCode() {
                int i3 = a.i(this.iat, a.i(this.nonce, a.i(this.version, a.i(this.aud, a.i(this.domain, a.i(this.chainId, this.type.hashCode() * 31, 31), 31), 31), 31), 31), 31);
                String str = this.nbf;
                int i4 = 0;
                int hashCode = (i3 + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.exp;
                int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                String str3 = this.statement;
                int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                String str4 = this.requestId;
                int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                List<String> list = this.resources;
                if (list != null) {
                    i4 = list.hashCode();
                }
                return hashCode4 + i4;
            }

            @NotNull
            public String toString() {
                String str = this.type;
                String str2 = this.chainId;
                String str3 = this.domain;
                String str4 = this.aud;
                String str5 = this.version;
                String str6 = this.nonce;
                String str7 = this.iat;
                String str8 = this.nbf;
                String str9 = this.exp;
                String str10 = this.statement;
                String str11 = this.requestId;
                List<String> list = this.resources;
                StringBuilder l2 = C0118y.l("PayloadParams(type=", str, ", chainId=", str2, ", domain=");
                b.w(l2, str3, ", aud=", str4, ", version=");
                b.w(l2, str5, ", nonce=", str6, ", iat=");
                b.w(l2, str7, ", nbf=", str8, ", exp=");
                b.w(l2, str9, ", statement=", str10, ", requestId=");
                l2.append(str11);
                l2.append(", resources=");
                l2.append(list);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PendingSessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model;", "requestId", "", "topic", "", "method", "chainId", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRequestId", "()J", "getTopic", "()Ljava/lang/String;", "getMethod", "getChainId", "getParams", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PendingSessionRequest extends Model {
            @Nullable
            private final String chainId;
            @NotNull
            private final String method;
            @NotNull
            private final String params;
            private final long requestId;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PendingSessionRequest(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str4, "params");
                this.requestId = j2;
                this.topic = str;
                this.method = str2;
                this.chainId = str3;
                this.params = str4;
            }

            public static /* synthetic */ PendingSessionRequest copy$default(PendingSessionRequest pendingSessionRequest, long j2, String str, String str2, String str3, String str4, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = pendingSessionRequest.requestId;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = pendingSessionRequest.topic;
                }
                String str5 = str;
                if ((i3 & 4) != 0) {
                    str2 = pendingSessionRequest.method;
                }
                String str6 = str2;
                if ((i3 & 8) != 0) {
                    str3 = pendingSessionRequest.chainId;
                }
                String str7 = str3;
                if ((i3 & 16) != 0) {
                    str4 = pendingSessionRequest.params;
                }
                return pendingSessionRequest.copy(j3, str5, str6, str7, str4);
            }

            public final long component1() {
                return this.requestId;
            }

            @NotNull
            public final String component2() {
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

            @NotNull
            public final String component5() {
                return this.params;
            }

            @NotNull
            public final PendingSessionRequest copy(long j2, @NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, FirebaseAnalytics.Param.METHOD);
                Intrinsics.checkNotNullParameter(str4, "params");
                return new PendingSessionRequest(j2, str, str2, str3, str4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PendingSessionRequest)) {
                    return false;
                }
                PendingSessionRequest pendingSessionRequest = (PendingSessionRequest) obj;
                return this.requestId == pendingSessionRequest.requestId && Intrinsics.areEqual((Object) this.topic, (Object) pendingSessionRequest.topic) && Intrinsics.areEqual((Object) this.method, (Object) pendingSessionRequest.method) && Intrinsics.areEqual((Object) this.chainId, (Object) pendingSessionRequest.chainId) && Intrinsics.areEqual((Object) this.params, (Object) pendingSessionRequest.params);
            }

            @Nullable
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getMethod() {
                return this.method;
            }

            @NotNull
            public final String getParams() {
                return this.params;
            }

            public final long getRequestId() {
                return this.requestId;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int i3 = a.i(this.method, a.i(this.topic, Long.hashCode(this.requestId) * 31, 31), 31);
                String str = this.chainId;
                return this.params.hashCode() + ((i3 + (str == null ? 0 : str.hashCode())) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.requestId;
                String str = this.topic;
                String str2 = this.method;
                String str3 = this.chainId;
                String str4 = this.params;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "PendingSessionRequest(requestId=", ", topic=", str);
                b.w(v2, ", method=", str2, ", chainId=", str3);
                return C0118y.j(v2, ", params=", str4, ")");
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Ping;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Success", "Error", "Lcom/reown/walletkit/client/Wallet$Model$Ping$Error;", "Lcom/reown/walletkit/client/Wallet$Model$Ping$Success;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Ping extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Ping$Error;", "Lcom/reown/walletkit/client/Wallet$Model$Ping;", "error", "", "<init>", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Ping$Success;", "Lcom/reown/walletkit/client/Wallet$Model$Ping;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "NoRoutesAvailable", "InsufficientFunds", "InsufficientGasFunds", "AssetNotSupported", "Unknown", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError$AssetNotSupported;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError$InsufficientFunds;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError$InsufficientGasFunds;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError$NoRoutesAvailable;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError$Unknown;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class PrepareError extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError$AssetNotSupported;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class AssetNotSupported extends PrepareError {
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public AssetNotSupported(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.message = str;
                }

                public static /* synthetic */ AssetNotSupported copy$default(AssetNotSupported assetNotSupported, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = assetNotSupported.message;
                    }
                    return assetNotSupported.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.message;
                }

                @NotNull
                public final AssetNotSupported copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new AssetNotSupported(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof AssetNotSupported) && Intrinsics.areEqual((Object) this.message, (Object) ((AssetNotSupported) obj).message);
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("AssetNotSupported(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError$InsufficientFunds;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class InsufficientFunds extends PrepareError {
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public InsufficientFunds(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.message = str;
                }

                public static /* synthetic */ InsufficientFunds copy$default(InsufficientFunds insufficientFunds, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = insufficientFunds.message;
                    }
                    return insufficientFunds.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.message;
                }

                @NotNull
                public final InsufficientFunds copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new InsufficientFunds(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof InsufficientFunds) && Intrinsics.areEqual((Object) this.message, (Object) ((InsufficientFunds) obj).message);
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("InsufficientFunds(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError$InsufficientGasFunds;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class InsufficientGasFunds extends PrepareError {
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public InsufficientGasFunds(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.message = str;
                }

                public static /* synthetic */ InsufficientGasFunds copy$default(InsufficientGasFunds insufficientGasFunds, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = insufficientGasFunds.message;
                    }
                    return insufficientGasFunds.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.message;
                }

                @NotNull
                public final InsufficientGasFunds copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new InsufficientGasFunds(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof InsufficientGasFunds) && Intrinsics.areEqual((Object) this.message, (Object) ((InsufficientGasFunds) obj).message);
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("InsufficientGasFunds(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError$NoRoutesAvailable;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class NoRoutesAvailable extends PrepareError {
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public NoRoutesAvailable(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.message = str;
                }

                public static /* synthetic */ NoRoutesAvailable copy$default(NoRoutesAvailable noRoutesAvailable, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = noRoutesAvailable.message;
                    }
                    return noRoutesAvailable.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.message;
                }

                @NotNull
                public final NoRoutesAvailable copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new NoRoutesAvailable(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof NoRoutesAvailable) && Intrinsics.areEqual((Object) this.message, (Object) ((NoRoutesAvailable) obj).message);
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("NoRoutesAvailable(message=", this.message, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareError$Unknown;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareError;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Unknown extends PrepareError {
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Unknown(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.message = str;
                }

                public static /* synthetic */ Unknown copy$default(Unknown unknown, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = unknown.message;
                    }
                    return unknown.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.message;
                }

                @NotNull
                public final Unknown copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new Unknown(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Unknown) && Intrinsics.areEqual((Object) this.message, (Object) ((Unknown) obj).message);
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Unknown(message=", this.message, ")");
                }
            }

            public /* synthetic */ PrepareError(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private PrepareError() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Available", "NotRequired", "Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess$Available;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess$NotRequired;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class PrepareSuccess extends Model {

            @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0010HÆ\u0003J[\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess$Available;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess;", "orchestratorId", "", "checkIn", "", "transactions", "", "Lcom/reown/walletkit/client/Wallet$Model$Transactions;", "initialTransaction", "Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "initialTransactionMetadata", "Lcom/reown/walletkit/client/Wallet$Model$InitialTransactionMetadata;", "funding", "Lcom/reown/walletkit/client/Wallet$Model$FundingMetadata;", "transactionsDetails", "Lcom/reown/walletkit/client/Wallet$Model$TransactionsDetails;", "<init>", "(Ljava/lang/String;JLjava/util/List;Lcom/reown/walletkit/client/Wallet$Model$Transaction;Lcom/reown/walletkit/client/Wallet$Model$InitialTransactionMetadata;Ljava/util/List;Lcom/reown/walletkit/client/Wallet$Model$TransactionsDetails;)V", "getOrchestratorId", "()Ljava/lang/String;", "getCheckIn", "()J", "getTransactions", "()Ljava/util/List;", "getInitialTransaction", "()Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "getInitialTransactionMetadata", "()Lcom/reown/walletkit/client/Wallet$Model$InitialTransactionMetadata;", "getFunding", "getTransactionsDetails", "()Lcom/reown/walletkit/client/Wallet$Model$TransactionsDetails;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Available extends PrepareSuccess {
                private final long checkIn;
                @NotNull
                private final List<FundingMetadata> funding;
                @NotNull
                private final Transaction initialTransaction;
                @NotNull
                private final InitialTransactionMetadata initialTransactionMetadata;
                @NotNull
                private final String orchestratorId;
                @NotNull
                private final List<Transactions> transactions;
                @NotNull
                private final TransactionsDetails transactionsDetails;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Available(@NotNull String str, long j2, @NotNull List<? extends Transactions> list, @NotNull Transaction transaction, @NotNull InitialTransactionMetadata initialTransactionMetadata2, @NotNull List<FundingMetadata> list2, @NotNull TransactionsDetails transactionsDetails2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "orchestratorId");
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    Intrinsics.checkNotNullParameter(transaction, "initialTransaction");
                    Intrinsics.checkNotNullParameter(initialTransactionMetadata2, "initialTransactionMetadata");
                    Intrinsics.checkNotNullParameter(list2, "funding");
                    Intrinsics.checkNotNullParameter(transactionsDetails2, "transactionsDetails");
                    this.orchestratorId = str;
                    this.checkIn = j2;
                    this.transactions = list;
                    this.initialTransaction = transaction;
                    this.initialTransactionMetadata = initialTransactionMetadata2;
                    this.funding = list2;
                    this.transactionsDetails = transactionsDetails2;
                }

                public static /* synthetic */ Available copy$default(Available available, String str, long j2, List list, Transaction transaction, InitialTransactionMetadata initialTransactionMetadata2, List list2, TransactionsDetails transactionsDetails2, int i3, Object obj) {
                    Available available2 = available;
                    return available.copy((i3 & 1) != 0 ? available2.orchestratorId : str, (i3 & 2) != 0 ? available2.checkIn : j2, (i3 & 4) != 0 ? available2.transactions : list, (i3 & 8) != 0 ? available2.initialTransaction : transaction, (i3 & 16) != 0 ? available2.initialTransactionMetadata : initialTransactionMetadata2, (i3 & 32) != 0 ? available2.funding : list2, (i3 & 64) != 0 ? available2.transactionsDetails : transactionsDetails2);
                }

                @NotNull
                public final String component1() {
                    return this.orchestratorId;
                }

                public final long component2() {
                    return this.checkIn;
                }

                @NotNull
                public final List<Transactions> component3() {
                    return this.transactions;
                }

                @NotNull
                public final Transaction component4() {
                    return this.initialTransaction;
                }

                @NotNull
                public final InitialTransactionMetadata component5() {
                    return this.initialTransactionMetadata;
                }

                @NotNull
                public final List<FundingMetadata> component6() {
                    return this.funding;
                }

                @NotNull
                public final TransactionsDetails component7() {
                    return this.transactionsDetails;
                }

                @NotNull
                public final Available copy(@NotNull String str, long j2, @NotNull List<? extends Transactions> list, @NotNull Transaction transaction, @NotNull InitialTransactionMetadata initialTransactionMetadata2, @NotNull List<FundingMetadata> list2, @NotNull TransactionsDetails transactionsDetails2) {
                    Intrinsics.checkNotNullParameter(str, "orchestratorId");
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    Intrinsics.checkNotNullParameter(transaction, "initialTransaction");
                    InitialTransactionMetadata initialTransactionMetadata3 = initialTransactionMetadata2;
                    Intrinsics.checkNotNullParameter(initialTransactionMetadata3, "initialTransactionMetadata");
                    List<FundingMetadata> list3 = list2;
                    Intrinsics.checkNotNullParameter(list3, "funding");
                    TransactionsDetails transactionsDetails3 = transactionsDetails2;
                    Intrinsics.checkNotNullParameter(transactionsDetails3, "transactionsDetails");
                    return new Available(str, j2, list, transaction, initialTransactionMetadata3, list3, transactionsDetails3);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Available)) {
                        return false;
                    }
                    Available available = (Available) obj;
                    return Intrinsics.areEqual((Object) this.orchestratorId, (Object) available.orchestratorId) && this.checkIn == available.checkIn && Intrinsics.areEqual((Object) this.transactions, (Object) available.transactions) && Intrinsics.areEqual((Object) this.initialTransaction, (Object) available.initialTransaction) && Intrinsics.areEqual((Object) this.initialTransactionMetadata, (Object) available.initialTransactionMetadata) && Intrinsics.areEqual((Object) this.funding, (Object) available.funding) && Intrinsics.areEqual((Object) this.transactionsDetails, (Object) available.transactionsDetails);
                }

                public final long getCheckIn() {
                    return this.checkIn;
                }

                @NotNull
                public final List<FundingMetadata> getFunding() {
                    return this.funding;
                }

                @NotNull
                public final Transaction getInitialTransaction() {
                    return this.initialTransaction;
                }

                @NotNull
                public final InitialTransactionMetadata getInitialTransactionMetadata() {
                    return this.initialTransactionMetadata;
                }

                @NotNull
                public final String getOrchestratorId() {
                    return this.orchestratorId;
                }

                @NotNull
                public final List<Transactions> getTransactions() {
                    return this.transactions;
                }

                @NotNull
                public final TransactionsDetails getTransactionsDetails() {
                    return this.transactionsDetails;
                }

                public int hashCode() {
                    int j2 = a.j(this.transactions, a.D(this.checkIn, this.orchestratorId.hashCode() * 31, 31), 31);
                    int hashCode = this.initialTransactionMetadata.hashCode();
                    return this.transactionsDetails.hashCode() + a.j(this.funding, (hashCode + ((this.initialTransaction.hashCode() + j2) * 31)) * 31, 31);
                }

                @NotNull
                public String toString() {
                    String str = this.orchestratorId;
                    long j2 = this.checkIn;
                    List<Transactions> list = this.transactions;
                    Transaction transaction = this.initialTransaction;
                    InitialTransactionMetadata initialTransactionMetadata2 = this.initialTransactionMetadata;
                    List<FundingMetadata> list2 = this.funding;
                    TransactionsDetails transactionsDetails2 = this.transactionsDetails;
                    StringBuilder i3 = com.appsamurai.storyly.exoplayer2.core.source.chunk.a.i(j2, "Available(orchestratorId=", str, ", checkIn=");
                    i3.append(", transactions=");
                    i3.append(list);
                    i3.append(", initialTransaction=");
                    i3.append(transaction);
                    i3.append(", initialTransactionMetadata=");
                    i3.append(initialTransactionMetadata2);
                    i3.append(", funding=");
                    i3.append(list2);
                    i3.append(", transactionsDetails=");
                    i3.append(transactionsDetails2);
                    i3.append(")");
                    return i3.toString();
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess$NotRequired;", "Lcom/reown/walletkit/client/Wallet$Model$PrepareSuccess;", "initialTransaction", "Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$Transaction;)V", "getInitialTransaction", "()Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class NotRequired extends PrepareSuccess {
                @NotNull
                private final Transaction initialTransaction;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public NotRequired(@NotNull Transaction transaction) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(transaction, "initialTransaction");
                    this.initialTransaction = transaction;
                }

                public static /* synthetic */ NotRequired copy$default(NotRequired notRequired, Transaction transaction, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        transaction = notRequired.initialTransaction;
                    }
                    return notRequired.copy(transaction);
                }

                @NotNull
                public final Transaction component1() {
                    return this.initialTransaction;
                }

                @NotNull
                public final NotRequired copy(@NotNull Transaction transaction) {
                    Intrinsics.checkNotNullParameter(transaction, "initialTransaction");
                    return new NotRequired(transaction);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof NotRequired) && Intrinsics.areEqual((Object) this.initialTransaction, (Object) ((NotRequired) obj).initialTransaction);
                }

                @NotNull
                public final Transaction getInitialTransaction() {
                    return this.initialTransaction;
                }

                public int hashCode() {
                    return this.initialTransaction.hashCode();
                }

                @NotNull
                public String toString() {
                    Transaction transaction = this.initialTransaction;
                    return "NotRequired(initialTransaction=" + transaction + ")";
                }
            }

            public /* synthetic */ PrepareSuccess(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private PrepareSuccess() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Route;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Eip155", "Solana", "Lcom/reown/walletkit/client/Wallet$Model$Route$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$Route$Solana;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Route extends Model {

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Route$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$Route;", "transactionDetails", "", "Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;", "<init>", "(Ljava/util/List;)V", "getTransactionDetails", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Eip155 extends Route {
                @NotNull
                private final List<TransactionDetails> transactionDetails;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Eip155(@NotNull List<TransactionDetails> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "transactionDetails");
                    this.transactionDetails = list;
                }

                public static /* synthetic */ Eip155 copy$default(Eip155 eip155, List<TransactionDetails> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = eip155.transactionDetails;
                    }
                    return eip155.copy(list);
                }

                @NotNull
                public final List<TransactionDetails> component1() {
                    return this.transactionDetails;
                }

                @NotNull
                public final Eip155 copy(@NotNull List<TransactionDetails> list) {
                    Intrinsics.checkNotNullParameter(list, "transactionDetails");
                    return new Eip155(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Eip155) && Intrinsics.areEqual((Object) this.transactionDetails, (Object) ((Eip155) obj).transactionDetails);
                }

                @NotNull
                public final List<TransactionDetails> getTransactionDetails() {
                    return this.transactionDetails;
                }

                public int hashCode() {
                    return this.transactionDetails.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Eip155(transactionDetails=", this.transactionDetails, ")");
                }
            }

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Route$Solana;", "Lcom/reown/walletkit/client/Wallet$Model$Route;", "solanaTransactionDetails", "", "Lcom/reown/walletkit/client/Wallet$Model$SolanaTransactionDetails;", "<init>", "(Ljava/util/List;)V", "getSolanaTransactionDetails", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Solana extends Route {
                @NotNull
                private final List<SolanaTransactionDetails> solanaTransactionDetails;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Solana(@NotNull List<SolanaTransactionDetails> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "solanaTransactionDetails");
                    this.solanaTransactionDetails = list;
                }

                public static /* synthetic */ Solana copy$default(Solana solana, List<SolanaTransactionDetails> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = solana.solanaTransactionDetails;
                    }
                    return solana.copy(list);
                }

                @NotNull
                public final List<SolanaTransactionDetails> component1() {
                    return this.solanaTransactionDetails;
                }

                @NotNull
                public final Solana copy(@NotNull List<SolanaTransactionDetails> list) {
                    Intrinsics.checkNotNullParameter(list, "solanaTransactionDetails");
                    return new Solana(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Solana) && Intrinsics.areEqual((Object) this.solanaTransactionDetails, (Object) ((Solana) obj).solanaTransactionDetails);
                }

                @NotNull
                public final List<SolanaTransactionDetails> getSolanaTransactionDetails() {
                    return this.solanaTransactionDetails;
                }

                public int hashCode() {
                    return this.solanaTransactionDetails.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Solana(solanaTransactionDetails=", this.solanaTransactionDetails, ")");
                }
            }

            public /* synthetic */ Route(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Route() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$RouteSig;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Eip155", "Solana", "Lcom/reown/walletkit/client/Wallet$Model$RouteSig$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$RouteSig$Solana;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class RouteSig extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$RouteSig$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$RouteSig;", "signatures", "", "", "<init>", "(Ljava/util/List;)V", "getSignatures", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Eip155 extends RouteSig {
                @NotNull
                private final List<String> signatures;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Eip155(@NotNull List<String> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "signatures");
                    this.signatures = list;
                }

                public static /* synthetic */ Eip155 copy$default(Eip155 eip155, List<String> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = eip155.signatures;
                    }
                    return eip155.copy(list);
                }

                @NotNull
                public final List<String> component1() {
                    return this.signatures;
                }

                @NotNull
                public final Eip155 copy(@NotNull List<String> list) {
                    Intrinsics.checkNotNullParameter(list, "signatures");
                    return new Eip155(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Eip155) && Intrinsics.areEqual((Object) this.signatures, (Object) ((Eip155) obj).signatures);
                }

                @NotNull
                public final List<String> getSignatures() {
                    return this.signatures;
                }

                public int hashCode() {
                    return this.signatures.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Eip155(signatures=", this.signatures, ")");
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$RouteSig$Solana;", "Lcom/reown/walletkit/client/Wallet$Model$RouteSig;", "signatures", "", "", "<init>", "(Ljava/util/List;)V", "getSignatures", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Solana extends RouteSig {
                @NotNull
                private final List<String> signatures;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Solana(@NotNull List<String> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "signatures");
                    this.signatures = list;
                }

                public static /* synthetic */ Solana copy$default(Solana solana, List<String> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = solana.signatures;
                    }
                    return solana.copy(list);
                }

                @NotNull
                public final List<String> component1() {
                    return this.signatures;
                }

                @NotNull
                public final Solana copy(@NotNull List<String> list) {
                    Intrinsics.checkNotNullParameter(list, "signatures");
                    return new Solana(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Solana) && Intrinsics.areEqual((Object) this.signatures, (Object) ((Solana) obj).signatures);
                }

                @NotNull
                public final List<String> getSignatures() {
                    return this.signatures;
                }

                public int hashCode() {
                    return this.signatures.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Solana(signatures=", this.signatures, ")");
                }
            }

            public /* synthetic */ RouteSig(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private RouteSig() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\u0017\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u000eHÆ\u0003Jw\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b2\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014¨\u0006/"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Session;", "Lcom/reown/walletkit/client/Wallet$Model;", "pairingTopic", "", "topic", "expiry", "", "requiredNamespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "optionalNamespaces", "namespaces", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "metaData", "Lcom/reown/android/Core$Model$AppMetaData;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/reown/android/Core$Model$AppMetaData;)V", "getPairingTopic$annotations", "()V", "getPairingTopic", "()Ljava/lang/String;", "getTopic", "getExpiry", "()J", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getNamespaces", "getMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "redirect", "getRedirect", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Session extends Model {
            private final long expiry;
            @Nullable
            private final Core.Model.AppMetaData metaData;
            @NotNull
            private final Map<String, Namespace.Session> namespaces;
            @Nullable
            private final Map<String, Namespace.Proposal> optionalNamespaces;
            @NotNull
            private final String pairingTopic;
            @Nullable
            private final String redirect;
            @NotNull
            private final Map<String, Namespace.Proposal> requiredNamespaces;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Session(@NotNull String str, @NotNull String str2, long j2, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable Core.Model.AppMetaData appMetaData) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                Intrinsics.checkNotNullParameter(map3, "namespaces");
                String str3 = null;
                this.pairingTopic = str;
                this.topic = str2;
                this.expiry = j2;
                this.requiredNamespaces = map;
                this.optionalNamespaces = map2;
                this.namespaces = map3;
                this.metaData = appMetaData;
                this.redirect = appMetaData != null ? appMetaData.getRedirect() : str3;
            }

            public static /* synthetic */ Session copy$default(Session session, String str, String str2, long j2, Map map, Map map2, Map map3, Core.Model.AppMetaData appMetaData, int i3, Object obj) {
                Session session2 = session;
                return session.copy((i3 & 1) != 0 ? session2.pairingTopic : str, (i3 & 2) != 0 ? session2.topic : str2, (i3 & 4) != 0 ? session2.expiry : j2, (i3 & 8) != 0 ? session2.requiredNamespaces : map, (i3 & 16) != 0 ? session2.optionalNamespaces : map2, (i3 & 32) != 0 ? session2.namespaces : map3, (i3 & 64) != 0 ? session2.metaData : appMetaData);
            }

            @Deprecated(message = "Pairing topic is deprecated")
            public static /* synthetic */ void getPairingTopic$annotations() {
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component2() {
                return this.topic;
            }

            public final long component3() {
                return this.expiry;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component4() {
                return this.requiredNamespaces;
            }

            @Nullable
            public final Map<String, Namespace.Proposal> component5() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final Map<String, Namespace.Session> component6() {
                return this.namespaces;
            }

            @Nullable
            public final Core.Model.AppMetaData component7() {
                return this.metaData;
            }

            @NotNull
            public final Session copy(@NotNull String str, @NotNull String str2, long j2, @NotNull Map<String, Namespace.Proposal> map, @Nullable Map<String, Namespace.Proposal> map2, @NotNull Map<String, Namespace.Session> map3, @Nullable Core.Model.AppMetaData appMetaData) {
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(map, "requiredNamespaces");
                Map<String, Namespace.Session> map4 = map3;
                Intrinsics.checkNotNullParameter(map4, "namespaces");
                return new Session(str, str2, j2, map, map2, map4, appMetaData);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Session)) {
                    return false;
                }
                Session session = (Session) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) session.pairingTopic) && Intrinsics.areEqual((Object) this.topic, (Object) session.topic) && this.expiry == session.expiry && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) session.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) session.optionalNamespaces) && Intrinsics.areEqual((Object) this.namespaces, (Object) session.namespaces) && Intrinsics.areEqual((Object) this.metaData, (Object) session.metaData);
            }

            public final long getExpiry() {
                return this.expiry;
            }

            @Nullable
            public final Core.Model.AppMetaData getMetaData() {
                return this.metaData;
            }

            @NotNull
            public final Map<String, Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, Namespace.Proposal> getOptionalNamespaces() {
                return this.optionalNamespaces;
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @Nullable
            public final String getRedirect() {
                return this.redirect;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> getRequiredNamespaces() {
                return this.requiredNamespaces;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int d2 = b.d(this.requiredNamespaces, a.D(this.expiry, a.i(this.topic, this.pairingTopic.hashCode() * 31, 31), 31), 31);
                Map<String, Namespace.Proposal> map = this.optionalNamespaces;
                int i3 = 0;
                int d3 = b.d(this.namespaces, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31);
                Core.Model.AppMetaData appMetaData = this.metaData;
                if (appMetaData != null) {
                    i3 = appMetaData.hashCode();
                }
                return d3 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.pairingTopic;
                String str2 = this.topic;
                long j2 = this.expiry;
                Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                Map<String, Namespace.Session> map3 = this.namespaces;
                Core.Model.AppMetaData appMetaData = this.metaData;
                StringBuilder l2 = C0118y.l("Session(pairingTopic=", str, ", topic=", str2, ", expiry=");
                l2.append(j2);
                l2.append(", requiredNamespaces=");
                l2.append(map);
                l2.append(", optionalNamespaces=");
                l2.append(map2);
                l2.append(", namespaces=");
                l2.append(map3);
                l2.append(", metaData=");
                l2.append(appMetaData);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001 B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006!"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Model;", "id", "", "pairingTopic", "", "participant", "Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;", "payloadParams", "Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "<init>", "(JLjava/lang/String;Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;)V", "getId", "()J", "getPairingTopic", "()Ljava/lang/String;", "getParticipant", "()Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;", "getPayloadParams", "()Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Participant", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionAuthenticate extends Model {
            private final long id;
            @NotNull
            private final String pairingTopic;
            @NotNull
            private final Participant participant;
            @NotNull
            private final PayloadAuthRequestParams payloadParams;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionAuthenticate$Participant;", "Lcom/reown/walletkit/client/Wallet$Model;", "publicKey", "", "metadata", "Lcom/reown/android/Core$Model$AppMetaData;", "<init>", "(Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;)V", "getPublicKey", "()Ljava/lang/String;", "getMetadata", "()Lcom/reown/android/Core$Model$AppMetaData;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Participant extends Model {
                @Nullable
                private final Core.Model.AppMetaData metadata;
                @NotNull
                private final String publicKey;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Participant(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "publicKey");
                    this.publicKey = str;
                    this.metadata = appMetaData;
                }

                public static /* synthetic */ Participant copy$default(Participant participant, String str, Core.Model.AppMetaData appMetaData, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = participant.publicKey;
                    }
                    if ((i3 & 2) != 0) {
                        appMetaData = participant.metadata;
                    }
                    return participant.copy(str, appMetaData);
                }

                @NotNull
                public final String component1() {
                    return this.publicKey;
                }

                @Nullable
                public final Core.Model.AppMetaData component2() {
                    return this.metadata;
                }

                @NotNull
                public final Participant copy(@NotNull String str, @Nullable Core.Model.AppMetaData appMetaData) {
                    Intrinsics.checkNotNullParameter(str, "publicKey");
                    return new Participant(str, appMetaData);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Participant)) {
                        return false;
                    }
                    Participant participant = (Participant) obj;
                    return Intrinsics.areEqual((Object) this.publicKey, (Object) participant.publicKey) && Intrinsics.areEqual((Object) this.metadata, (Object) participant.metadata);
                }

                @Nullable
                public final Core.Model.AppMetaData getMetadata() {
                    return this.metadata;
                }

                @NotNull
                public final String getPublicKey() {
                    return this.publicKey;
                }

                public int hashCode() {
                    int hashCode = this.publicKey.hashCode() * 31;
                    Core.Model.AppMetaData appMetaData = this.metadata;
                    return hashCode + (appMetaData == null ? 0 : appMetaData.hashCode());
                }

                @NotNull
                public String toString() {
                    String str = this.publicKey;
                    Core.Model.AppMetaData appMetaData = this.metadata;
                    return "Participant(publicKey=" + str + ", metadata=" + appMetaData + ")";
                }
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionAuthenticate(long j2, @NotNull String str, @NotNull Participant participant2, @NotNull PayloadAuthRequestParams payloadAuthRequestParams) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(participant2, "participant");
                Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
                this.id = j2;
                this.pairingTopic = str;
                this.participant = participant2;
                this.payloadParams = payloadAuthRequestParams;
            }

            public static /* synthetic */ SessionAuthenticate copy$default(SessionAuthenticate sessionAuthenticate, long j2, String str, Participant participant2, PayloadAuthRequestParams payloadAuthRequestParams, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = sessionAuthenticate.id;
                }
                long j3 = j2;
                if ((i3 & 2) != 0) {
                    str = sessionAuthenticate.pairingTopic;
                }
                String str2 = str;
                if ((i3 & 4) != 0) {
                    participant2 = sessionAuthenticate.participant;
                }
                Participant participant3 = participant2;
                if ((i3 & 8) != 0) {
                    payloadAuthRequestParams = sessionAuthenticate.payloadParams;
                }
                return sessionAuthenticate.copy(j3, str2, participant3, payloadAuthRequestParams);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.pairingTopic;
            }

            @NotNull
            public final Participant component3() {
                return this.participant;
            }

            @NotNull
            public final PayloadAuthRequestParams component4() {
                return this.payloadParams;
            }

            @NotNull
            public final SessionAuthenticate copy(long j2, @NotNull String str, @NotNull Participant participant2, @NotNull PayloadAuthRequestParams payloadAuthRequestParams) {
                Intrinsics.checkNotNullParameter(str, "pairingTopic");
                Intrinsics.checkNotNullParameter(participant2, "participant");
                Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
                return new SessionAuthenticate(j2, str, participant2, payloadAuthRequestParams);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionAuthenticate)) {
                    return false;
                }
                SessionAuthenticate sessionAuthenticate = (SessionAuthenticate) obj;
                return this.id == sessionAuthenticate.id && Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionAuthenticate.pairingTopic) && Intrinsics.areEqual((Object) this.participant, (Object) sessionAuthenticate.participant) && Intrinsics.areEqual((Object) this.payloadParams, (Object) sessionAuthenticate.payloadParams);
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getPairingTopic() {
                return this.pairingTopic;
            }

            @NotNull
            public final Participant getParticipant() {
                return this.participant;
            }

            @NotNull
            public final PayloadAuthRequestParams getPayloadParams() {
                return this.payloadParams;
            }

            public int hashCode() {
                int i3 = a.i(this.pairingTopic, Long.hashCode(this.id) * 31, 31);
                return this.payloadParams.hashCode() + ((this.participant.hashCode() + i3) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.pairingTopic;
                Participant participant2 = this.participant;
                PayloadAuthRequestParams payloadAuthRequestParams = this.payloadParams;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "SessionAuthenticate(id=", ", pairingTopic=", str);
                v2.append(", participant=");
                v2.append(participant2);
                v2.append(", payloadParams=");
                v2.append(payloadAuthRequestParams);
                v2.append(")");
                return v2.toString();
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Success", "Error", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete$Success;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SessionDelete extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionDelete$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "error", "", "<init>", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SessionDelete {
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

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionDelete$Success;", "Lcom/reown/walletkit/client/Wallet$Model$SessionDelete;", "topic", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Success extends SessionDelete {
                @NotNull
                private final String reason;
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Success(@NotNull String str, @NotNull String str2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str2, "reason");
                    this.topic = str;
                    this.reason = str2;
                }

                public static /* synthetic */ Success copy$default(Success success, String str, String str2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = success.topic;
                    }
                    if ((i3 & 2) != 0) {
                        str2 = success.reason;
                    }
                    return success.copy(str, str2);
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
                public final Success copy(@NotNull String str, @NotNull String str2) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(str2, "reason");
                    return new Success(str, str2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Success)) {
                        return false;
                    }
                    Success success = (Success) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) success.topic) && Intrinsics.areEqual((Object) this.reason, (Object) success.reason);
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
                    return C0118y.g("Success(topic=", this.topic, ", reason=", this.reason, ")");
                }
            }

            public /* synthetic */ SessionDelete(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SessionDelete() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;", "Lcom/reown/walletkit/client/Wallet$Model;", "name", "", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getData", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionEvent extends Model {
            @NotNull
            private final String data;
            @NotNull
            private final String name;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionEvent(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "data");
                this.name = str;
                this.data = str2;
            }

            public static /* synthetic */ SessionEvent copy$default(SessionEvent sessionEvent, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionEvent.name;
                }
                if ((i3 & 2) != 0) {
                    str2 = sessionEvent.data;
                }
                return sessionEvent.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.name;
            }

            @NotNull
            public final String component2() {
                return this.data;
            }

            @NotNull
            public final SessionEvent copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "name");
                Intrinsics.checkNotNullParameter(str2, "data");
                return new SessionEvent(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionEvent)) {
                    return false;
                }
                SessionEvent sessionEvent = (SessionEvent) obj;
                return Intrinsics.areEqual((Object) this.name, (Object) sessionEvent.name) && Intrinsics.areEqual((Object) this.data, (Object) sessionEvent.data);
            }

            @NotNull
            public final String getData() {
                return this.data;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            public int hashCode() {
                return this.data.hashCode() + (this.name.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("SessionEvent(name=", this.name, ", data=", this.data, ")");
            }
        }

        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B«\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f\u0012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u0015\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u0017\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0003JÇ\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fHÆ\u0001J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u000107HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u001f\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u001f\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001f¨\u0006;"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionProposal;", "Lcom/reown/walletkit/client/Wallet$Model;", "pairingTopic", "", "name", "description", "url", "icons", "", "Ljava/net/URI;", "redirect", "requiredNamespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Proposal;", "optionalNamespaces", "properties", "proposerPublicKey", "relayProtocol", "relayData", "scopedProperties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getPairingTopic", "()Ljava/lang/String;", "getName", "getDescription", "getUrl", "getIcons", "()Ljava/util/List;", "getRedirect", "getRequiredNamespaces", "()Ljava/util/Map;", "getOptionalNamespaces", "getProperties", "getProposerPublicKey", "getRelayProtocol", "getRelayData", "getScopedProperties", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionProposal extends Model {
            @NotNull
            private final String description;
            @NotNull
            private final List<URI> icons;
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
            @Nullable
            private final Map<String, String> scopedProperties;
            @NotNull
            private final String url;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionProposal(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
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
                this.scopedProperties = map4;
            }

            public static /* synthetic */ SessionProposal copy$default(SessionProposal sessionProposal, String str, String str2, String str3, String str4, List list, String str5, Map map, Map map2, Map map3, String str6, String str7, String str8, Map map4, int i3, Object obj) {
                SessionProposal sessionProposal2 = sessionProposal;
                int i4 = i3;
                return sessionProposal.copy((i4 & 1) != 0 ? sessionProposal2.pairingTopic : str, (i4 & 2) != 0 ? sessionProposal2.name : str2, (i4 & 4) != 0 ? sessionProposal2.description : str3, (i4 & 8) != 0 ? sessionProposal2.url : str4, (i4 & 16) != 0 ? sessionProposal2.icons : list, (i4 & 32) != 0 ? sessionProposal2.redirect : str5, (i4 & 64) != 0 ? sessionProposal2.requiredNamespaces : map, (i4 & 128) != 0 ? sessionProposal2.optionalNamespaces : map2, (i4 & 256) != 0 ? sessionProposal2.properties : map3, (i4 & 512) != 0 ? sessionProposal2.proposerPublicKey : str6, (i4 & 1024) != 0 ? sessionProposal2.relayProtocol : str7, (i4 & 2048) != 0 ? sessionProposal2.relayData : str8, (i4 & 4096) != 0 ? sessionProposal2.scopedProperties : map4);
            }

            @NotNull
            public final String component1() {
                return this.pairingTopic;
            }

            @NotNull
            public final String component10() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String component11() {
                return this.relayProtocol;
            }

            @Nullable
            public final String component12() {
                return this.relayData;
            }

            @Nullable
            public final Map<String, String> component13() {
                return this.scopedProperties;
            }

            @NotNull
            public final String component2() {
                return this.name;
            }

            @NotNull
            public final String component3() {
                return this.description;
            }

            @NotNull
            public final String component4() {
                return this.url;
            }

            @NotNull
            public final List<URI> component5() {
                return this.icons;
            }

            @NotNull
            public final String component6() {
                return this.redirect;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component7() {
                return this.requiredNamespaces;
            }

            @NotNull
            public final Map<String, Namespace.Proposal> component8() {
                return this.optionalNamespaces;
            }

            @Nullable
            public final Map<String, String> component9() {
                return this.properties;
            }

            @NotNull
            public final SessionProposal copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<URI> list, @NotNull String str5, @NotNull Map<String, Namespace.Proposal> map, @NotNull Map<String, Namespace.Proposal> map2, @Nullable Map<String, String> map3, @NotNull String str6, @NotNull String str7, @Nullable String str8, @Nullable Map<String, String> map4) {
                String str9 = str;
                Intrinsics.checkNotNullParameter(str9, "pairingTopic");
                String str10 = str2;
                Intrinsics.checkNotNullParameter(str10, "name");
                String str11 = str3;
                Intrinsics.checkNotNullParameter(str11, "description");
                String str12 = str4;
                Intrinsics.checkNotNullParameter(str12, "url");
                List<URI> list2 = list;
                Intrinsics.checkNotNullParameter(list2, "icons");
                String str13 = str5;
                Intrinsics.checkNotNullParameter(str13, "redirect");
                Map<String, Namespace.Proposal> map5 = map;
                Intrinsics.checkNotNullParameter(map5, "requiredNamespaces");
                Map<String, Namespace.Proposal> map6 = map2;
                Intrinsics.checkNotNullParameter(map6, "optionalNamespaces");
                String str14 = str6;
                Intrinsics.checkNotNullParameter(str14, "proposerPublicKey");
                String str15 = str7;
                Intrinsics.checkNotNullParameter(str15, "relayProtocol");
                return new SessionProposal(str9, str10, str11, str12, list2, str13, map5, map6, map3, str14, str15, str8, map4);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionProposal)) {
                    return false;
                }
                SessionProposal sessionProposal = (SessionProposal) obj;
                return Intrinsics.areEqual((Object) this.pairingTopic, (Object) sessionProposal.pairingTopic) && Intrinsics.areEqual((Object) this.name, (Object) sessionProposal.name) && Intrinsics.areEqual((Object) this.description, (Object) sessionProposal.description) && Intrinsics.areEqual((Object) this.url, (Object) sessionProposal.url) && Intrinsics.areEqual((Object) this.icons, (Object) sessionProposal.icons) && Intrinsics.areEqual((Object) this.redirect, (Object) sessionProposal.redirect) && Intrinsics.areEqual((Object) this.requiredNamespaces, (Object) sessionProposal.requiredNamespaces) && Intrinsics.areEqual((Object) this.optionalNamespaces, (Object) sessionProposal.optionalNamespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionProposal.properties) && Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionProposal.proposerPublicKey) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionProposal.relayProtocol) && Intrinsics.areEqual((Object) this.relayData, (Object) sessionProposal.relayData) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionProposal.scopedProperties);
            }

            @NotNull
            public final String getDescription() {
                return this.description;
            }

            @NotNull
            public final List<URI> getIcons() {
                return this.icons;
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

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            @NotNull
            public final String getUrl() {
                return this.url;
            }

            public int hashCode() {
                int d2 = b.d(this.optionalNamespaces, b.d(this.requiredNamespaces, a.i(this.redirect, a.j(this.icons, a.i(this.url, a.i(this.description, a.i(this.name, this.pairingTopic.hashCode() * 31, 31), 31), 31), 31), 31), 31), 31);
                Map<String, String> map = this.properties;
                int i3 = 0;
                int i4 = a.i(this.relayProtocol, a.i(this.proposerPublicKey, (d2 + (map == null ? 0 : map.hashCode())) * 31, 31), 31);
                String str = this.relayData;
                int hashCode = (i4 + (str == null ? 0 : str.hashCode())) * 31;
                Map<String, String> map2 = this.scopedProperties;
                if (map2 != null) {
                    i3 = map2.hashCode();
                }
                return hashCode + i3;
            }

            @NotNull
            public String toString() {
                String str = this.pairingTopic;
                String str2 = this.name;
                String str3 = this.description;
                String str4 = this.url;
                List<URI> list = this.icons;
                String str5 = this.redirect;
                Map<String, Namespace.Proposal> map = this.requiredNamespaces;
                Map<String, Namespace.Proposal> map2 = this.optionalNamespaces;
                Map<String, String> map3 = this.properties;
                String str6 = this.proposerPublicKey;
                String str7 = this.relayProtocol;
                String str8 = this.relayData;
                Map<String, String> map4 = this.scopedProperties;
                StringBuilder l2 = C0118y.l("SessionProposal(pairingTopic=", str, ", name=", str2, ", description=");
                b.w(l2, str3, ", url=", str4, ", icons=");
                l2.append(list);
                l2.append(", redirect=");
                l2.append(str5);
                l2.append(", requiredNamespaces=");
                l2.append(map);
                l2.append(", optionalNamespaces=");
                l2.append(map2);
                l2.append(", properties=");
                l2.append(map3);
                l2.append(", proposerPublicKey=");
                l2.append(str6);
                l2.append(", relayProtocol=");
                b.w(l2, str7, ", relayData=", str8, ", scopedProperties=");
                l2.append(map4);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001\u001eB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionRequest;", "Lcom/reown/walletkit/client/Wallet$Model;", "topic", "", "chainId", "peerMetaData", "Lcom/reown/android/Core$Model$AppMetaData;", "request", "Lcom/reown/walletkit/client/Wallet$Model$SessionRequest$JSONRPCRequest;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/reown/android/Core$Model$AppMetaData;Lcom/reown/walletkit/client/Wallet$Model$SessionRequest$JSONRPCRequest;)V", "getTopic", "()Ljava/lang/String;", "getChainId", "getPeerMetaData", "()Lcom/reown/android/Core$Model$AppMetaData;", "getRequest", "()Lcom/reown/walletkit/client/Wallet$Model$SessionRequest$JSONRPCRequest;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "JSONRPCRequest", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionRequest extends Model {
            @Nullable
            private final String chainId;
            @Nullable
            private final Core.Model.AppMetaData peerMetaData;
            @NotNull
            private final JSONRPCRequest request;
            @NotNull
            private final String topic;

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionRequest$JSONRPCRequest;", "Lcom/reown/walletkit/client/Wallet$Model;", "id", "", "method", "", "params", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getMethod", "()Ljava/lang/String;", "getParams", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class JSONRPCRequest extends Model {
                private final long id;
                @NotNull
                private final String method;
                @NotNull
                private final String params;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public JSONRPCRequest(long j2, @NotNull String str, @NotNull String str2) {
                    super((DefaultConstructorMarker) null);
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
            public SessionRequest(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(jSONRPCRequest, "request");
                this.topic = str;
                this.chainId = str2;
                this.peerMetaData = appMetaData;
                this.request = jSONRPCRequest;
            }

            public static /* synthetic */ SessionRequest copy$default(SessionRequest sessionRequest, String str, String str2, Core.Model.AppMetaData appMetaData, JSONRPCRequest jSONRPCRequest, int i3, Object obj) {
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
            public final Core.Model.AppMetaData component3() {
                return this.peerMetaData;
            }

            @NotNull
            public final JSONRPCRequest component4() {
                return this.request;
            }

            @NotNull
            public final SessionRequest copy(@NotNull String str, @Nullable String str2, @Nullable Core.Model.AppMetaData appMetaData, @NotNull JSONRPCRequest jSONRPCRequest) {
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
            public final Core.Model.AppMetaData getPeerMetaData() {
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
                Core.Model.AppMetaData appMetaData = this.peerMetaData;
                if (appMetaData != null) {
                    i3 = appMetaData.hashCode();
                }
                return this.request.hashCode() + ((hashCode2 + i3) * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                String str2 = this.chainId;
                Core.Model.AppMetaData appMetaData = this.peerMetaData;
                JSONRPCRequest jSONRPCRequest = this.request;
                StringBuilder l2 = C0118y.l("SessionRequest(topic=", str, ", chainId=", str2, ", peerMetaData=");
                l2.append(appMetaData);
                l2.append(", request=");
                l2.append(jSONRPCRequest);
                l2.append(")");
                return l2.toString();
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Result", "Error", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse$Result;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SessionUpdateResponse extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SessionUpdateResponse {
                @NotNull
                private final String errorMessage;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    this.errorMessage = str;
                }

                public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = error.errorMessage;
                    }
                    return error.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.errorMessage;
                }

                @NotNull
                public final Error copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    return new Error(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
                }

                @NotNull
                public final String getErrorMessage() {
                    return this.errorMessage;
                }

                public int hashCode() {
                    return this.errorMessage.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Error(errorMessage=", this.errorMessage, ")");
                }
            }

            @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse$Result;", "Lcom/reown/walletkit/client/Wallet$Model$SessionUpdateResponse;", "topic", "", "namespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getTopic", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends SessionUpdateResponse {
                @NotNull
                private final Map<String, Namespace.Session> namespaces;
                @NotNull
                private final String topic;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(map, "namespaces");
                    this.topic = str;
                    this.namespaces = map;
                }

                public static /* synthetic */ Result copy$default(Result result, String str, Map<String, Namespace.Session> map, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = result.topic;
                    }
                    if ((i3 & 2) != 0) {
                        map = result.namespaces;
                    }
                    return result.copy(str, map);
                }

                @NotNull
                public final String component1() {
                    return this.topic;
                }

                @NotNull
                public final Map<String, Namespace.Session> component2() {
                    return this.namespaces;
                }

                @NotNull
                public final Result copy(@NotNull String str, @NotNull Map<String, Namespace.Session> map) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                    Intrinsics.checkNotNullParameter(map, "namespaces");
                    return new Result(str, map);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Result)) {
                        return false;
                    }
                    Result result = (Result) obj;
                    return Intrinsics.areEqual((Object) this.topic, (Object) result.topic) && Intrinsics.areEqual((Object) this.namespaces, (Object) result.namespaces);
                }

                @NotNull
                public final Map<String, Namespace.Session> getNamespaces() {
                    return this.namespaces;
                }

                @NotNull
                public final String getTopic() {
                    return this.topic;
                }

                public int hashCode() {
                    return this.namespaces.hashCode() + (this.topic.hashCode() * 31);
                }

                @NotNull
                public String toString() {
                    String str = this.topic;
                    Map<String, Namespace.Session> map = this.namespaces;
                    return "Result(topic=" + str + ", namespaces=" + map + ")";
                }
            }

            public /* synthetic */ SessionUpdateResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SessionUpdateResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Result", "Error", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse$Result;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class SettledSessionResponse extends Model {

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "errorMessage", "", "<init>", "(Ljava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends SettledSessionResponse {
                @NotNull
                private final String errorMessage;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    this.errorMessage = str;
                }

                public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = error.errorMessage;
                    }
                    return error.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.errorMessage;
                }

                @NotNull
                public final Error copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "errorMessage");
                    return new Error(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.errorMessage, (Object) ((Error) obj).errorMessage);
                }

                @NotNull
                public final String getErrorMessage() {
                    return this.errorMessage;
                }

                public int hashCode() {
                    return this.errorMessage.hashCode();
                }

                @NotNull
                public String toString() {
                    return A.a.l("Error(errorMessage=", this.errorMessage, ")");
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse$Result;", "Lcom/reown/walletkit/client/Wallet$Model$SettledSessionResponse;", "session", "Lcom/reown/walletkit/client/Wallet$Model$Session;", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$Session;)V", "getSession", "()Lcom/reown/walletkit/client/Wallet$Model$Session;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends SettledSessionResponse {
                @NotNull
                private final Session session;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(@NotNull Session session2) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(session2, "session");
                    this.session = session2;
                }

                public static /* synthetic */ Result copy$default(Result result, Session session2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        session2 = result.session;
                    }
                    return result.copy(session2);
                }

                @NotNull
                public final Session component1() {
                    return this.session;
                }

                @NotNull
                public final Result copy(@NotNull Session session2) {
                    Intrinsics.checkNotNullParameter(session2, "session");
                    return new Result(session2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Result) && Intrinsics.areEqual((Object) this.session, (Object) ((Result) obj).session);
                }

                @NotNull
                public final Session getSession() {
                    return this.session;
                }

                public int hashCode() {
                    return this.session.hashCode();
                }

                @NotNull
                public String toString() {
                    Session session2 = this.session;
                    return "Result(session=" + session2 + ")";
                }
            }

            public /* synthetic */ SettledSessionResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private SettledSessionResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;", "Lcom/reown/walletkit/client/Wallet$Model;", "from", "", "chainId", "versionedTransaction", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getChainId", "getVersionedTransaction", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SolanaTransaction extends Model {
            @NotNull
            private final String chainId;
            @NotNull
            private final String from;
            @NotNull
            private final String versionedTransaction;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SolanaTransaction(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, "chainId");
                Intrinsics.checkNotNullParameter(str3, "versionedTransaction");
                this.from = str;
                this.chainId = str2;
                this.versionedTransaction = str3;
            }

            public static /* synthetic */ SolanaTransaction copy$default(SolanaTransaction solanaTransaction, String str, String str2, String str3, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = solanaTransaction.from;
                }
                if ((i3 & 2) != 0) {
                    str2 = solanaTransaction.chainId;
                }
                if ((i3 & 4) != 0) {
                    str3 = solanaTransaction.versionedTransaction;
                }
                return solanaTransaction.copy(str, str2, str3);
            }

            @NotNull
            public final String component1() {
                return this.from;
            }

            @NotNull
            public final String component2() {
                return this.chainId;
            }

            @NotNull
            public final String component3() {
                return this.versionedTransaction;
            }

            @NotNull
            public final SolanaTransaction copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, "chainId");
                Intrinsics.checkNotNullParameter(str3, "versionedTransaction");
                return new SolanaTransaction(str, str2, str3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SolanaTransaction)) {
                    return false;
                }
                SolanaTransaction solanaTransaction = (SolanaTransaction) obj;
                return Intrinsics.areEqual((Object) this.from, (Object) solanaTransaction.from) && Intrinsics.areEqual((Object) this.chainId, (Object) solanaTransaction.chainId) && Intrinsics.areEqual((Object) this.versionedTransaction, (Object) solanaTransaction.versionedTransaction);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getFrom() {
                return this.from;
            }

            @NotNull
            public final String getVersionedTransaction() {
                return this.versionedTransaction;
            }

            public int hashCode() {
                return this.versionedTransaction.hashCode() + a.i(this.chainId, this.from.hashCode() * 31, 31);
            }

            @NotNull
            public String toString() {
                String str = this.from;
                String str2 = this.chainId;
                return A.a.n(C0118y.l("SolanaTransaction(from=", str, ", chainId=", str2, ", versionedTransaction="), this.versionedTransaction, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$SolanaTransactionDetails;", "Lcom/reown/walletkit/client/Wallet$Model;", "transaction", "Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;", "transactionHashToSign", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;Ljava/lang/String;)V", "getTransaction", "()Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;", "setTransaction", "(Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;)V", "getTransactionHashToSign", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SolanaTransactionDetails extends Model {
            @NotNull
            private SolanaTransaction transaction;
            @NotNull
            private final String transactionHashToSign;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SolanaTransactionDetails(@NotNull SolanaTransaction solanaTransaction, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(solanaTransaction, ConversationActivity.MODE_TRANSACTION);
                Intrinsics.checkNotNullParameter(str, "transactionHashToSign");
                this.transaction = solanaTransaction;
                this.transactionHashToSign = str;
            }

            public static /* synthetic */ SolanaTransactionDetails copy$default(SolanaTransactionDetails solanaTransactionDetails, SolanaTransaction solanaTransaction, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    solanaTransaction = solanaTransactionDetails.transaction;
                }
                if ((i3 & 2) != 0) {
                    str = solanaTransactionDetails.transactionHashToSign;
                }
                return solanaTransactionDetails.copy(solanaTransaction, str);
            }

            @NotNull
            public final SolanaTransaction component1() {
                return this.transaction;
            }

            @NotNull
            public final String component2() {
                return this.transactionHashToSign;
            }

            @NotNull
            public final SolanaTransactionDetails copy(@NotNull SolanaTransaction solanaTransaction, @NotNull String str) {
                Intrinsics.checkNotNullParameter(solanaTransaction, ConversationActivity.MODE_TRANSACTION);
                Intrinsics.checkNotNullParameter(str, "transactionHashToSign");
                return new SolanaTransactionDetails(solanaTransaction, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SolanaTransactionDetails)) {
                    return false;
                }
                SolanaTransactionDetails solanaTransactionDetails = (SolanaTransactionDetails) obj;
                return Intrinsics.areEqual((Object) this.transaction, (Object) solanaTransactionDetails.transaction) && Intrinsics.areEqual((Object) this.transactionHashToSign, (Object) solanaTransactionDetails.transactionHashToSign);
            }

            @NotNull
            public final SolanaTransaction getTransaction() {
                return this.transaction;
            }

            @NotNull
            public final String getTransactionHashToSign() {
                return this.transactionHashToSign;
            }

            public int hashCode() {
                return this.transactionHashToSign.hashCode() + (this.transaction.hashCode() * 31);
            }

            public final void setTransaction(@NotNull SolanaTransaction solanaTransaction) {
                Intrinsics.checkNotNullParameter(solanaTransaction, "<set-?>");
                this.transaction = solanaTransaction;
            }

            @NotNull
            public String toString() {
                SolanaTransaction solanaTransaction = this.transaction;
                String str = this.transactionHashToSign;
                return "SolanaTransactionDetails(transaction=" + solanaTransaction + ", transactionHashToSign=" + str + ")";
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Status;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Completed", "Error", "Lcom/reown/walletkit/client/Wallet$Model$Status$Completed;", "Lcom/reown/walletkit/client/Wallet$Model$Status$Error;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Status extends Model {

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Status$Completed;", "Lcom/reown/walletkit/client/Wallet$Model$Status;", "createdAt", "", "<init>", "(J)V", "getCreatedAt", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Completed extends Status {
                private final long createdAt;

                public Completed(long j2) {
                    super((DefaultConstructorMarker) null);
                    this.createdAt = j2;
                }

                public static /* synthetic */ Completed copy$default(Completed completed, long j2, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = completed.createdAt;
                    }
                    return completed.copy(j2);
                }

                public final long component1() {
                    return this.createdAt;
                }

                @NotNull
                public final Completed copy(long j2) {
                    return new Completed(j2);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Completed) && this.createdAt == ((Completed) obj).createdAt;
                }

                public final long getCreatedAt() {
                    return this.createdAt;
                }

                public int hashCode() {
                    return Long.hashCode(this.createdAt);
                }

                @NotNull
                public String toString() {
                    return androidx.compose.ui.autofill.a.j("Completed(createdAt=", this.createdAt, ")");
                }
            }

            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Status$Error;", "Lcom/reown/walletkit/client/Wallet$Model$Status;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends Status {
                @NotNull
                private final String reason;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(@NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, "reason");
                    this.reason = str;
                }

                public static /* synthetic */ Error copy$default(Error error, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        str = error.reason;
                    }
                    return error.copy(str);
                }

                @NotNull
                public final String component1() {
                    return this.reason;
                }

                @NotNull
                public final Error copy(@NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, "reason");
                    return new Error(str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.reason, (Object) ((Error) obj).reason);
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
                    return A.a.l("Error(reason=", this.reason, ")");
                }
            }

            public /* synthetic */ Status(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Status() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000f¨\u0006+"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "Lcom/reown/walletkit/client/Wallet$Model;", "from", "", "to", "value", "gasLimit", "input", "nonce", "chainId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "setFrom", "(Ljava/lang/String;)V", "getTo", "setTo", "getValue", "setValue", "getGasLimit", "setGasLimit", "getInput", "setInput", "getNonce", "setNonce", "getChainId", "setChainId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Transaction extends Model {
            @NotNull
            private String chainId;
            @NotNull
            private String from;
            @NotNull
            private String gasLimit;
            @NotNull
            private String input;
            @NotNull
            private String nonce;
            @NotNull
            private String to;
            @NotNull
            private String value;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Transaction(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "gasLimit");
                Intrinsics.checkNotNullParameter(str5, "input");
                Intrinsics.checkNotNullParameter(str6, "nonce");
                Intrinsics.checkNotNullParameter(str7, "chainId");
                this.from = str;
                this.to = str2;
                this.value = str3;
                this.gasLimit = str4;
                this.input = str5;
                this.nonce = str6;
                this.chainId = str7;
            }

            public static /* synthetic */ Transaction copy$default(Transaction transaction, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = transaction.from;
                }
                if ((i3 & 2) != 0) {
                    str2 = transaction.to;
                }
                String str8 = str2;
                if ((i3 & 4) != 0) {
                    str3 = transaction.value;
                }
                String str9 = str3;
                if ((i3 & 8) != 0) {
                    str4 = transaction.gasLimit;
                }
                String str10 = str4;
                if ((i3 & 16) != 0) {
                    str5 = transaction.input;
                }
                String str11 = str5;
                if ((i3 & 32) != 0) {
                    str6 = transaction.nonce;
                }
                String str12 = str6;
                if ((i3 & 64) != 0) {
                    str7 = transaction.chainId;
                }
                return transaction.copy(str, str8, str9, str10, str11, str12, str7);
            }

            @NotNull
            public final String component1() {
                return this.from;
            }

            @NotNull
            public final String component2() {
                return this.to;
            }

            @NotNull
            public final String component3() {
                return this.value;
            }

            @NotNull
            public final String component4() {
                return this.gasLimit;
            }

            @NotNull
            public final String component5() {
                return this.input;
            }

            @NotNull
            public final String component6() {
                return this.nonce;
            }

            @NotNull
            public final String component7() {
                return this.chainId;
            }

            @NotNull
            public final Transaction copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7) {
                Intrinsics.checkNotNullParameter(str, "from");
                Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
                Intrinsics.checkNotNullParameter(str3, "value");
                Intrinsics.checkNotNullParameter(str4, "gasLimit");
                Intrinsics.checkNotNullParameter(str5, "input");
                Intrinsics.checkNotNullParameter(str6, "nonce");
                Intrinsics.checkNotNullParameter(str7, "chainId");
                return new Transaction(str, str2, str3, str4, str5, str6, str7);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Transaction)) {
                    return false;
                }
                Transaction transaction = (Transaction) obj;
                return Intrinsics.areEqual((Object) this.from, (Object) transaction.from) && Intrinsics.areEqual((Object) this.to, (Object) transaction.to) && Intrinsics.areEqual((Object) this.value, (Object) transaction.value) && Intrinsics.areEqual((Object) this.gasLimit, (Object) transaction.gasLimit) && Intrinsics.areEqual((Object) this.input, (Object) transaction.input) && Intrinsics.areEqual((Object) this.nonce, (Object) transaction.nonce) && Intrinsics.areEqual((Object) this.chainId, (Object) transaction.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final String getFrom() {
                return this.from;
            }

            @NotNull
            public final String getGasLimit() {
                return this.gasLimit;
            }

            @NotNull
            public final String getInput() {
                return this.input;
            }

            @NotNull
            public final String getNonce() {
                return this.nonce;
            }

            @NotNull
            public final String getTo() {
                return this.to;
            }

            @NotNull
            public final String getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.chainId.hashCode() + a.i(this.nonce, a.i(this.input, a.i(this.gasLimit, a.i(this.value, a.i(this.to, this.from.hashCode() * 31, 31), 31), 31), 31), 31);
            }

            public final void setChainId(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.chainId = str;
            }

            public final void setFrom(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.from = str;
            }

            public final void setGasLimit(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.gasLimit = str;
            }

            public final void setInput(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.input = str;
            }

            public final void setNonce(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.nonce = str;
            }

            public final void setTo(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.to = str;
            }

            public final void setValue(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.value = str;
            }

            @NotNull
            public String toString() {
                String str = this.from;
                String str2 = this.to;
                String str3 = this.value;
                String str4 = this.gasLimit;
                String str5 = this.input;
                String str6 = this.nonce;
                String str7 = this.chainId;
                StringBuilder l2 = C0118y.l("Transaction(from=", str, ", to=", str2, ", value=");
                b.w(l2, str3, ", gasLimit=", str4, ", input=");
                b.w(l2, str5, ", nonce=", str6, ", chainId=");
                return A.a.n(l2, str7, ")");
            }
        }

        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;", "Lcom/reown/walletkit/client/Wallet$Model;", "feeEstimatedTransaction", "Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;", "transactionFee", "Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;", "transactionHashToSign", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;Ljava/lang/String;)V", "getFeeEstimatedTransaction", "()Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;", "setFeeEstimatedTransaction", "(Lcom/reown/walletkit/client/Wallet$Model$FeeEstimatedTransaction;)V", "getTransactionFee", "()Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;", "setTransactionFee", "(Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;)V", "getTransactionHashToSign", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class TransactionDetails extends Model {
            @NotNull
            private FeeEstimatedTransaction feeEstimatedTransaction;
            @NotNull
            private TransactionFee transactionFee;
            @NotNull
            private final String transactionHashToSign;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public TransactionDetails(@NotNull FeeEstimatedTransaction feeEstimatedTransaction2, @NotNull TransactionFee transactionFee2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(feeEstimatedTransaction2, "feeEstimatedTransaction");
                Intrinsics.checkNotNullParameter(transactionFee2, "transactionFee");
                Intrinsics.checkNotNullParameter(str, "transactionHashToSign");
                this.feeEstimatedTransaction = feeEstimatedTransaction2;
                this.transactionFee = transactionFee2;
                this.transactionHashToSign = str;
            }

            public static /* synthetic */ TransactionDetails copy$default(TransactionDetails transactionDetails, FeeEstimatedTransaction feeEstimatedTransaction2, TransactionFee transactionFee2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    feeEstimatedTransaction2 = transactionDetails.feeEstimatedTransaction;
                }
                if ((i3 & 2) != 0) {
                    transactionFee2 = transactionDetails.transactionFee;
                }
                if ((i3 & 4) != 0) {
                    str = transactionDetails.transactionHashToSign;
                }
                return transactionDetails.copy(feeEstimatedTransaction2, transactionFee2, str);
            }

            @NotNull
            public final FeeEstimatedTransaction component1() {
                return this.feeEstimatedTransaction;
            }

            @NotNull
            public final TransactionFee component2() {
                return this.transactionFee;
            }

            @NotNull
            public final String component3() {
                return this.transactionHashToSign;
            }

            @NotNull
            public final TransactionDetails copy(@NotNull FeeEstimatedTransaction feeEstimatedTransaction2, @NotNull TransactionFee transactionFee2, @NotNull String str) {
                Intrinsics.checkNotNullParameter(feeEstimatedTransaction2, "feeEstimatedTransaction");
                Intrinsics.checkNotNullParameter(transactionFee2, "transactionFee");
                Intrinsics.checkNotNullParameter(str, "transactionHashToSign");
                return new TransactionDetails(feeEstimatedTransaction2, transactionFee2, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof TransactionDetails)) {
                    return false;
                }
                TransactionDetails transactionDetails = (TransactionDetails) obj;
                return Intrinsics.areEqual((Object) this.feeEstimatedTransaction, (Object) transactionDetails.feeEstimatedTransaction) && Intrinsics.areEqual((Object) this.transactionFee, (Object) transactionDetails.transactionFee) && Intrinsics.areEqual((Object) this.transactionHashToSign, (Object) transactionDetails.transactionHashToSign);
            }

            @NotNull
            public final FeeEstimatedTransaction getFeeEstimatedTransaction() {
                return this.feeEstimatedTransaction;
            }

            @NotNull
            public final TransactionFee getTransactionFee() {
                return this.transactionFee;
            }

            @NotNull
            public final String getTransactionHashToSign() {
                return this.transactionHashToSign;
            }

            public int hashCode() {
                int hashCode = this.transactionFee.hashCode();
                return this.transactionHashToSign.hashCode() + ((hashCode + (this.feeEstimatedTransaction.hashCode() * 31)) * 31);
            }

            public final void setFeeEstimatedTransaction(@NotNull FeeEstimatedTransaction feeEstimatedTransaction2) {
                Intrinsics.checkNotNullParameter(feeEstimatedTransaction2, "<set-?>");
                this.feeEstimatedTransaction = feeEstimatedTransaction2;
            }

            public final void setTransactionFee(@NotNull TransactionFee transactionFee2) {
                Intrinsics.checkNotNullParameter(transactionFee2, "<set-?>");
                this.transactionFee = transactionFee2;
            }

            @NotNull
            public String toString() {
                FeeEstimatedTransaction feeEstimatedTransaction2 = this.feeEstimatedTransaction;
                TransactionFee transactionFee2 = this.transactionFee;
                String str = this.transactionHashToSign;
                StringBuilder sb = new StringBuilder("TransactionDetails(feeEstimatedTransaction=");
                sb.append(feeEstimatedTransaction2);
                sb.append(", transactionFee=");
                sb.append(transactionFee2);
                sb.append(", transactionHashToSign=");
                return A.a.n(sb, str, ")");
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;", "Lcom/reown/walletkit/client/Wallet$Model;", "fee", "Lcom/reown/walletkit/client/Wallet$Model$Amount;", "localFee", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$Amount;Lcom/reown/walletkit/client/Wallet$Model$Amount;)V", "getFee", "()Lcom/reown/walletkit/client/Wallet$Model$Amount;", "setFee", "(Lcom/reown/walletkit/client/Wallet$Model$Amount;)V", "getLocalFee", "setLocalFee", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class TransactionFee extends Model {
            @NotNull
            private Amount fee;
            @NotNull
            private Amount localFee;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public TransactionFee(@NotNull Amount amount, @NotNull Amount amount2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(amount, "fee");
                Intrinsics.checkNotNullParameter(amount2, "localFee");
                this.fee = amount;
                this.localFee = amount2;
            }

            public static /* synthetic */ TransactionFee copy$default(TransactionFee transactionFee, Amount amount, Amount amount2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    amount = transactionFee.fee;
                }
                if ((i3 & 2) != 0) {
                    amount2 = transactionFee.localFee;
                }
                return transactionFee.copy(amount, amount2);
            }

            @NotNull
            public final Amount component1() {
                return this.fee;
            }

            @NotNull
            public final Amount component2() {
                return this.localFee;
            }

            @NotNull
            public final TransactionFee copy(@NotNull Amount amount, @NotNull Amount amount2) {
                Intrinsics.checkNotNullParameter(amount, "fee");
                Intrinsics.checkNotNullParameter(amount2, "localFee");
                return new TransactionFee(amount, amount2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof TransactionFee)) {
                    return false;
                }
                TransactionFee transactionFee = (TransactionFee) obj;
                return Intrinsics.areEqual((Object) this.fee, (Object) transactionFee.fee) && Intrinsics.areEqual((Object) this.localFee, (Object) transactionFee.localFee);
            }

            @NotNull
            public final Amount getFee() {
                return this.fee;
            }

            @NotNull
            public final Amount getLocalFee() {
                return this.localFee;
            }

            public int hashCode() {
                return this.localFee.hashCode() + (this.fee.hashCode() * 31);
            }

            public final void setFee(@NotNull Amount amount) {
                Intrinsics.checkNotNullParameter(amount, "<set-?>");
                this.fee = amount;
            }

            public final void setLocalFee(@NotNull Amount amount) {
                Intrinsics.checkNotNullParameter(amount, "<set-?>");
                this.localFee = amount;
            }

            @NotNull
            public String toString() {
                Amount amount = this.fee;
                Amount amount2 = this.localFee;
                return "TransactionFee(fee=" + amount + ", localFee=" + amount2 + ")";
            }
        }

        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Transactions;", "Lcom/reown/walletkit/client/Wallet$Model;", "<init>", "()V", "Eip155", "Solana", "Lcom/reown/walletkit/client/Wallet$Model$Transactions$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$Transactions$Solana;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class Transactions extends Model {

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Transactions$Eip155;", "Lcom/reown/walletkit/client/Wallet$Model$Transactions;", "transactions", "", "Lcom/reown/walletkit/client/Wallet$Model$Transaction;", "<init>", "(Ljava/util/List;)V", "getTransactions", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Eip155 extends Transactions {
                @NotNull
                private final List<Transaction> transactions;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Eip155(@NotNull List<Transaction> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    this.transactions = list;
                }

                public static /* synthetic */ Eip155 copy$default(Eip155 eip155, List<Transaction> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = eip155.transactions;
                    }
                    return eip155.copy(list);
                }

                @NotNull
                public final List<Transaction> component1() {
                    return this.transactions;
                }

                @NotNull
                public final Eip155 copy(@NotNull List<Transaction> list) {
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    return new Eip155(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Eip155) && Intrinsics.areEqual((Object) this.transactions, (Object) ((Eip155) obj).transactions);
                }

                @NotNull
                public final List<Transaction> getTransactions() {
                    return this.transactions;
                }

                public int hashCode() {
                    return this.transactions.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Eip155(transactions=", this.transactions, ")");
                }
            }

            @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Transactions$Solana;", "Lcom/reown/walletkit/client/Wallet$Model$Transactions;", "transactions", "", "Lcom/reown/walletkit/client/Wallet$Model$SolanaTransaction;", "<init>", "(Ljava/util/List;)V", "getTransactions", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Solana extends Transactions {
                @NotNull
                private final List<SolanaTransaction> transactions;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Solana(@NotNull List<SolanaTransaction> list) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    this.transactions = list;
                }

                public static /* synthetic */ Solana copy$default(Solana solana, List<SolanaTransaction> list, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        list = solana.transactions;
                    }
                    return solana.copy(list);
                }

                @NotNull
                public final List<SolanaTransaction> component1() {
                    return this.transactions;
                }

                @NotNull
                public final Solana copy(@NotNull List<SolanaTransaction> list) {
                    Intrinsics.checkNotNullParameter(list, "transactions");
                    return new Solana(list);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof Solana) && Intrinsics.areEqual((Object) this.transactions, (Object) ((Solana) obj).transactions);
                }

                @NotNull
                public final List<SolanaTransaction> getTransactions() {
                    return this.transactions;
                }

                public int hashCode() {
                    return this.transactions.hashCode();
                }

                @NotNull
                public String toString() {
                    return org.spongycastle.asn1.pkcs.a.b("Solana(transactions=", this.transactions, ")");
                }
            }

            public /* synthetic */ Transactions(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Transactions() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BC\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003JQ\u0010'\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001c¨\u00060"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$TransactionsDetails;", "Lcom/reown/walletkit/client/Wallet$Model;", "route", "", "Lcom/reown/walletkit/client/Wallet$Model$Route;", "initialDetails", "Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;", "bridgeFees", "Lcom/reown/walletkit/client/Wallet$Model$TransactionFee;", "localBridgeTotal", "Lcom/reown/walletkit/client/Wallet$Model$Amount;", "localFulfilmentTotal", "localTotal", "<init>", "(Ljava/util/List;Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;Ljava/util/List;Lcom/reown/walletkit/client/Wallet$Model$Amount;Lcom/reown/walletkit/client/Wallet$Model$Amount;Lcom/reown/walletkit/client/Wallet$Model$Amount;)V", "getRoute", "()Ljava/util/List;", "setRoute", "(Ljava/util/List;)V", "getInitialDetails", "()Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;", "setInitialDetails", "(Lcom/reown/walletkit/client/Wallet$Model$TransactionDetails;)V", "getBridgeFees", "setBridgeFees", "getLocalBridgeTotal", "()Lcom/reown/walletkit/client/Wallet$Model$Amount;", "setLocalBridgeTotal", "(Lcom/reown/walletkit/client/Wallet$Model$Amount;)V", "getLocalFulfilmentTotal", "setLocalFulfilmentTotal", "getLocalTotal", "setLocalTotal", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class TransactionsDetails extends Model {
            @NotNull
            private List<TransactionFee> bridgeFees;
            @NotNull
            private TransactionDetails initialDetails;
            @NotNull
            private Amount localBridgeTotal;
            @NotNull
            private Amount localFulfilmentTotal;
            @NotNull
            private Amount localTotal;
            @NotNull
            private List<? extends Route> route;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public TransactionsDetails(@NotNull List<? extends Route> list, @NotNull TransactionDetails transactionDetails, @NotNull List<TransactionFee> list2, @NotNull Amount amount, @NotNull Amount amount2, @NotNull Amount amount3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "route");
                Intrinsics.checkNotNullParameter(transactionDetails, "initialDetails");
                Intrinsics.checkNotNullParameter(list2, "bridgeFees");
                Intrinsics.checkNotNullParameter(amount, "localBridgeTotal");
                Intrinsics.checkNotNullParameter(amount2, "localFulfilmentTotal");
                Intrinsics.checkNotNullParameter(amount3, "localTotal");
                this.route = list;
                this.initialDetails = transactionDetails;
                this.bridgeFees = list2;
                this.localBridgeTotal = amount;
                this.localFulfilmentTotal = amount2;
                this.localTotal = amount3;
            }

            public static /* synthetic */ TransactionsDetails copy$default(TransactionsDetails transactionsDetails, List<? extends Route> list, TransactionDetails transactionDetails, List<TransactionFee> list2, Amount amount, Amount amount2, Amount amount3, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    list = transactionsDetails.route;
                }
                if ((i3 & 2) != 0) {
                    transactionDetails = transactionsDetails.initialDetails;
                }
                TransactionDetails transactionDetails2 = transactionDetails;
                if ((i3 & 4) != 0) {
                    list2 = transactionsDetails.bridgeFees;
                }
                List<TransactionFee> list3 = list2;
                if ((i3 & 8) != 0) {
                    amount = transactionsDetails.localBridgeTotal;
                }
                Amount amount4 = amount;
                if ((i3 & 16) != 0) {
                    amount2 = transactionsDetails.localFulfilmentTotal;
                }
                Amount amount5 = amount2;
                if ((i3 & 32) != 0) {
                    amount3 = transactionsDetails.localTotal;
                }
                return transactionsDetails.copy(list, transactionDetails2, list3, amount4, amount5, amount3);
            }

            @NotNull
            public final List<Route> component1() {
                return this.route;
            }

            @NotNull
            public final TransactionDetails component2() {
                return this.initialDetails;
            }

            @NotNull
            public final List<TransactionFee> component3() {
                return this.bridgeFees;
            }

            @NotNull
            public final Amount component4() {
                return this.localBridgeTotal;
            }

            @NotNull
            public final Amount component5() {
                return this.localFulfilmentTotal;
            }

            @NotNull
            public final Amount component6() {
                return this.localTotal;
            }

            @NotNull
            public final TransactionsDetails copy(@NotNull List<? extends Route> list, @NotNull TransactionDetails transactionDetails, @NotNull List<TransactionFee> list2, @NotNull Amount amount, @NotNull Amount amount2, @NotNull Amount amount3) {
                Intrinsics.checkNotNullParameter(list, "route");
                Intrinsics.checkNotNullParameter(transactionDetails, "initialDetails");
                Intrinsics.checkNotNullParameter(list2, "bridgeFees");
                Intrinsics.checkNotNullParameter(amount, "localBridgeTotal");
                Intrinsics.checkNotNullParameter(amount2, "localFulfilmentTotal");
                Intrinsics.checkNotNullParameter(amount3, "localTotal");
                return new TransactionsDetails(list, transactionDetails, list2, amount, amount2, amount3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof TransactionsDetails)) {
                    return false;
                }
                TransactionsDetails transactionsDetails = (TransactionsDetails) obj;
                return Intrinsics.areEqual((Object) this.route, (Object) transactionsDetails.route) && Intrinsics.areEqual((Object) this.initialDetails, (Object) transactionsDetails.initialDetails) && Intrinsics.areEqual((Object) this.bridgeFees, (Object) transactionsDetails.bridgeFees) && Intrinsics.areEqual((Object) this.localBridgeTotal, (Object) transactionsDetails.localBridgeTotal) && Intrinsics.areEqual((Object) this.localFulfilmentTotal, (Object) transactionsDetails.localFulfilmentTotal) && Intrinsics.areEqual((Object) this.localTotal, (Object) transactionsDetails.localTotal);
            }

            @NotNull
            public final List<TransactionFee> getBridgeFees() {
                return this.bridgeFees;
            }

            @NotNull
            public final TransactionDetails getInitialDetails() {
                return this.initialDetails;
            }

            @NotNull
            public final Amount getLocalBridgeTotal() {
                return this.localBridgeTotal;
            }

            @NotNull
            public final Amount getLocalFulfilmentTotal() {
                return this.localFulfilmentTotal;
            }

            @NotNull
            public final Amount getLocalTotal() {
                return this.localTotal;
            }

            @NotNull
            public final List<Route> getRoute() {
                return this.route;
            }

            public int hashCode() {
                int hashCode = this.initialDetails.hashCode();
                int j2 = a.j(this.bridgeFees, (hashCode + (this.route.hashCode() * 31)) * 31, 31);
                int hashCode2 = this.localFulfilmentTotal.hashCode();
                return this.localTotal.hashCode() + ((hashCode2 + ((this.localBridgeTotal.hashCode() + j2) * 31)) * 31);
            }

            public final void setBridgeFees(@NotNull List<TransactionFee> list) {
                Intrinsics.checkNotNullParameter(list, "<set-?>");
                this.bridgeFees = list;
            }

            public final void setInitialDetails(@NotNull TransactionDetails transactionDetails) {
                Intrinsics.checkNotNullParameter(transactionDetails, "<set-?>");
                this.initialDetails = transactionDetails;
            }

            public final void setLocalBridgeTotal(@NotNull Amount amount) {
                Intrinsics.checkNotNullParameter(amount, "<set-?>");
                this.localBridgeTotal = amount;
            }

            public final void setLocalFulfilmentTotal(@NotNull Amount amount) {
                Intrinsics.checkNotNullParameter(amount, "<set-?>");
                this.localFulfilmentTotal = amount;
            }

            public final void setLocalTotal(@NotNull Amount amount) {
                Intrinsics.checkNotNullParameter(amount, "<set-?>");
                this.localTotal = amount;
            }

            public final void setRoute(@NotNull List<? extends Route> list) {
                Intrinsics.checkNotNullParameter(list, "<set-?>");
                this.route = list;
            }

            @NotNull
            public String toString() {
                List<? extends Route> list = this.route;
                TransactionDetails transactionDetails = this.initialDetails;
                List<TransactionFee> list2 = this.bridgeFees;
                Amount amount = this.localBridgeTotal;
                Amount amount2 = this.localFulfilmentTotal;
                Amount amount3 = this.localTotal;
                return "TransactionsDetails(route=" + list + ", initialDetails=" + transactionDetails + ", bridgeFees=" + list2 + ", localBridgeTotal=" + amount + ", localFulfilmentTotal=" + amount2 + ", localTotal=" + amount3 + ")";
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$Validation;", "", "<init>", "(Ljava/lang/String;I)V", "VALID", "INVALID", "UNKNOWN", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public enum Validation {
            VALID,
            INVALID,
            UNKNOWN;

            static {
                Validation[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
            }

            @NotNull
            public static EnumEntries<Validation> getEntries() {
                return $ENTRIES;
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\t\u0010\u0014¨\u0006#"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "Lcom/reown/walletkit/client/Wallet$Model;", "id", "", "origin", "", "validation", "Lcom/reown/walletkit/client/Wallet$Model$Validation;", "verifyUrl", "isScam", "", "<init>", "(JLjava/lang/String;Lcom/reown/walletkit/client/Wallet$Model$Validation;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()J", "getOrigin", "()Ljava/lang/String;", "getValidation", "()Lcom/reown/walletkit/client/Wallet$Model$Validation;", "getVerifyUrl", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(JLjava/lang/String;Lcom/reown/walletkit/client/Wallet$Model$Validation;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/walletkit/client/Wallet$Model$VerifyContext;", "equals", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class VerifyContext extends Model {
            private final long id;
            @Nullable
            private final Boolean isScam;
            @NotNull
            private final String origin;
            @NotNull
            private final Validation validation;
            @NotNull
            private final String verifyUrl;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public VerifyContext(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "origin");
                Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
                Intrinsics.checkNotNullParameter(str2, "verifyUrl");
                this.id = j2;
                this.origin = str;
                this.validation = validation2;
                this.verifyUrl = str2;
                this.isScam = bool;
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
                    str2 = verifyContext.verifyUrl;
                }
                String str4 = str2;
                if ((i3 & 16) != 0) {
                    bool = verifyContext.isScam;
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
                return this.verifyUrl;
            }

            @Nullable
            public final Boolean component5() {
                return this.isScam;
            }

            @NotNull
            public final VerifyContext copy(long j2, @NotNull String str, @NotNull Validation validation2, @NotNull String str2, @Nullable Boolean bool) {
                Intrinsics.checkNotNullParameter(str, "origin");
                Intrinsics.checkNotNullParameter(validation2, org.apache.xerces.impl.Constants.VALIDATION_FEATURE);
                Intrinsics.checkNotNullParameter(str2, "verifyUrl");
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
                return this.id == verifyContext.id && Intrinsics.areEqual((Object) this.origin, (Object) verifyContext.origin) && this.validation == verifyContext.validation && Intrinsics.areEqual((Object) this.verifyUrl, (Object) verifyContext.verifyUrl) && Intrinsics.areEqual((Object) this.isScam, (Object) verifyContext.isScam);
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
            public final String getVerifyUrl() {
                return this.verifyUrl;
            }

            public int hashCode() {
                int i3 = a.i(this.verifyUrl, (this.validation.hashCode() + a.i(this.origin, Long.hashCode(this.id) * 31, 31)) * 31, 31);
                Boolean bool = this.isScam;
                return i3 + (bool == null ? 0 : bool.hashCode());
            }

            @Nullable
            public final Boolean isScam() {
                return this.isScam;
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                String str = this.origin;
                Validation validation2 = this.validation;
                String str2 = this.verifyUrl;
                Boolean bool = this.isScam;
                StringBuilder v2 = androidx.work.impl.a.v(j2, "VerifyContext(id=", ", origin=", str);
                v2.append(", validation=");
                v2.append(validation2);
                v2.append(", verifyUrl=");
                v2.append(str2);
                v2.append(", isScam=");
                v2.append(bool);
                v2.append(")");
                return v2.toString();
            }
        }

        public /* synthetic */ Model(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Model() {
        }
    }

    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0018\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0001\u0018\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123¨\u00064"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params;", "", "<init>", "()V", "Init", "Pair", "SessionApprove", "ApproveSessionAuthenticate", "RejectSessionAuthenticate", "SessionReject", "SessionUpdate", "SessionExtend", "SessionEmit", "SessionRequestResponse", "SessionDisconnect", "FormatMessage", "FormatAuthMessage", "Ping", "AuthRequestResponse", "DecryptMessage", "GetSmartAccountAddress", "PrepareSendTransactions", "DoSendTransactions", "PrepareSendTransactionsResult", "DoSendTransactionsResult", "WaitForUserOperationReceipt", "OwnerSignature", "Account", "Lcom/reown/walletkit/client/Wallet$Params$Account;", "Lcom/reown/walletkit/client/Wallet$Params$ApproveSessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse;", "Lcom/reown/walletkit/client/Wallet$Params$DecryptMessage;", "Lcom/reown/walletkit/client/Wallet$Params$DoSendTransactions;", "Lcom/reown/walletkit/client/Wallet$Params$DoSendTransactionsResult;", "Lcom/reown/walletkit/client/Wallet$Params$FormatAuthMessage;", "Lcom/reown/walletkit/client/Wallet$Params$FormatMessage;", "Lcom/reown/walletkit/client/Wallet$Params$GetSmartAccountAddress;", "Lcom/reown/walletkit/client/Wallet$Params$Init;", "Lcom/reown/walletkit/client/Wallet$Params$OwnerSignature;", "Lcom/reown/walletkit/client/Wallet$Params$Pair;", "Lcom/reown/walletkit/client/Wallet$Params$Ping;", "Lcom/reown/walletkit/client/Wallet$Params$PrepareSendTransactions;", "Lcom/reown/walletkit/client/Wallet$Params$PrepareSendTransactionsResult;", "Lcom/reown/walletkit/client/Wallet$Params$RejectSessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Params$SessionApprove;", "Lcom/reown/walletkit/client/Wallet$Params$SessionDisconnect;", "Lcom/reown/walletkit/client/Wallet$Params$SessionEmit;", "Lcom/reown/walletkit/client/Wallet$Params$SessionExtend;", "Lcom/reown/walletkit/client/Wallet$Params$SessionReject;", "Lcom/reown/walletkit/client/Wallet$Params$SessionRequestResponse;", "Lcom/reown/walletkit/client/Wallet$Params$SessionUpdate;", "Lcom/reown/walletkit/client/Wallet$Params$WaitForUserOperationReceipt;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Params {

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$Account;", "Lcom/reown/walletkit/client/Wallet$Params;", "address", "", "<init>", "(Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Account extends Params {
            @NotNull
            private final String address;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Account(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
                this.address = str;
            }

            public static /* synthetic */ Account copy$default(Account account, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = account.address;
                }
                return account.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.address;
            }

            @NotNull
            public final Account copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
                return new Account(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Account) && Intrinsics.areEqual((Object) this.address, (Object) ((Account) obj).address);
            }

            @NotNull
            public final String getAddress() {
                return this.address;
            }

            public int hashCode() {
                return this.address.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("Account(address=", this.address, ")");
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$ApproveSessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Params;", "id", "", "auths", "", "Lcom/reown/walletkit/client/Wallet$Model$Cacao;", "<init>", "(JLjava/util/List;)V", "getId", "()J", "getAuths", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ApproveSessionAuthenticate extends Params {
            @NotNull
            private final List<Model.Cacao> auths;
            private final long id;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ApproveSessionAuthenticate(long j2, @NotNull List<Model.Cacao> list) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "auths");
                this.id = j2;
                this.auths = list;
            }

            public static /* synthetic */ ApproveSessionAuthenticate copy$default(ApproveSessionAuthenticate approveSessionAuthenticate, long j2, List<Model.Cacao> list, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = approveSessionAuthenticate.id;
                }
                if ((i3 & 2) != 0) {
                    list = approveSessionAuthenticate.auths;
                }
                return approveSessionAuthenticate.copy(j2, list);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final List<Model.Cacao> component2() {
                return this.auths;
            }

            @NotNull
            public final ApproveSessionAuthenticate copy(long j2, @NotNull List<Model.Cacao> list) {
                Intrinsics.checkNotNullParameter(list, "auths");
                return new ApproveSessionAuthenticate(j2, list);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof ApproveSessionAuthenticate)) {
                    return false;
                }
                ApproveSessionAuthenticate approveSessionAuthenticate = (ApproveSessionAuthenticate) obj;
                return this.id == approveSessionAuthenticate.id && Intrinsics.areEqual((Object) this.auths, (Object) approveSessionAuthenticate.auths);
            }

            @NotNull
            public final List<Model.Cacao> getAuths() {
                return this.auths;
            }

            public final long getId() {
                return this.id;
            }

            public int hashCode() {
                return this.auths.hashCode() + (Long.hashCode(this.id) * 31);
            }

            @NotNull
            public String toString() {
                long j2 = this.id;
                List<Model.Cacao> list = this.auths;
                return "ApproveSessionAuthenticate(id=" + j2 + ", auths=" + list + ")";
            }
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse;", "Lcom/reown/walletkit/client/Wallet$Params;", "<init>", "()V", "id", "", "getId", "()J", "Result", "Error", "Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse$Result;", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static abstract class AuthRequestResponse extends Params {

            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse$Error;", "Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse;", "id", "", "code", "", "message", "", "<init>", "(JILjava/lang/String;)V", "getId", "()J", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Error extends AuthRequestResponse {
                private final int code;
                private final long id;
                @NotNull
                private final String message;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Error(long j2, int i3, @NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    this.id = j2;
                    this.code = i3;
                    this.message = str;
                }

                public static /* synthetic */ Error copy$default(Error error, long j2, int i3, String str, int i4, Object obj) {
                    if ((i4 & 1) != 0) {
                        j2 = error.id;
                    }
                    if ((i4 & 2) != 0) {
                        i3 = error.code;
                    }
                    if ((i4 & 4) != 0) {
                        str = error.message;
                    }
                    return error.copy(j2, i3, str);
                }

                public final long component1() {
                    return this.id;
                }

                public final int component2() {
                    return this.code;
                }

                @NotNull
                public final String component3() {
                    return this.message;
                }

                @NotNull
                public final Error copy(long j2, int i3, @NotNull String str) {
                    Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
                    return new Error(j2, i3, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Error)) {
                        return false;
                    }
                    Error error = (Error) obj;
                    return this.id == error.id && this.code == error.code && Intrinsics.areEqual((Object) this.message, (Object) error.message);
                }

                public final int getCode() {
                    return this.code;
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                public int hashCode() {
                    return this.message.hashCode() + a.c(this.code, Long.hashCode(this.id) * 31, 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    int i3 = this.code;
                    String str = this.message;
                    StringBuilder sb = new StringBuilder("Error(id=");
                    sb.append(j2);
                    sb.append(", code=");
                    sb.append(i3);
                    return C0118y.j(sb, ", message=", str, ")");
                }
            }

            @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse$Result;", "Lcom/reown/walletkit/client/Wallet$Params$AuthRequestResponse;", "id", "", "signature", "Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "issuer", "", "<init>", "(JLcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;Ljava/lang/String;)V", "getId", "()J", "getSignature", "()Lcom/reown/walletkit/client/Wallet$Model$Cacao$Signature;", "getIssuer", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Result extends AuthRequestResponse {
                private final long id;
                @NotNull
                private final String issuer;
                @NotNull
                private final Model.Cacao.Signature signature;

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                public Result(long j2, @NotNull Model.Cacao.Signature signature2, @NotNull String str) {
                    super((DefaultConstructorMarker) null);
                    Intrinsics.checkNotNullParameter(signature2, "signature");
                    Intrinsics.checkNotNullParameter(str, "issuer");
                    this.id = j2;
                    this.signature = signature2;
                    this.issuer = str;
                }

                public static /* synthetic */ Result copy$default(Result result, long j2, Model.Cacao.Signature signature2, String str, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        j2 = result.id;
                    }
                    if ((i3 & 2) != 0) {
                        signature2 = result.signature;
                    }
                    if ((i3 & 4) != 0) {
                        str = result.issuer;
                    }
                    return result.copy(j2, signature2, str);
                }

                public final long component1() {
                    return this.id;
                }

                @NotNull
                public final Model.Cacao.Signature component2() {
                    return this.signature;
                }

                @NotNull
                public final String component3() {
                    return this.issuer;
                }

                @NotNull
                public final Result copy(long j2, @NotNull Model.Cacao.Signature signature2, @NotNull String str) {
                    Intrinsics.checkNotNullParameter(signature2, "signature");
                    Intrinsics.checkNotNullParameter(str, "issuer");
                    return new Result(j2, signature2, str);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Result)) {
                        return false;
                    }
                    Result result = (Result) obj;
                    return this.id == result.id && Intrinsics.areEqual((Object) this.signature, (Object) result.signature) && Intrinsics.areEqual((Object) this.issuer, (Object) result.issuer);
                }

                public long getId() {
                    return this.id;
                }

                @NotNull
                public final String getIssuer() {
                    return this.issuer;
                }

                @NotNull
                public final Model.Cacao.Signature getSignature() {
                    return this.signature;
                }

                public int hashCode() {
                    int hashCode = this.signature.hashCode();
                    return this.issuer.hashCode() + ((hashCode + (Long.hashCode(this.id) * 31)) * 31);
                }

                @NotNull
                public String toString() {
                    long j2 = this.id;
                    Model.Cacao.Signature signature2 = this.signature;
                    String str = this.issuer;
                    StringBuilder sb = new StringBuilder("Result(id=");
                    sb.append(j2);
                    sb.append(", signature=");
                    sb.append(signature2);
                    return C0118y.j(sb, ", issuer=", str, ")");
                }
            }

            public /* synthetic */ AuthRequestResponse(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public abstract long getId();

            private AuthRequestResponse() {
                super((DefaultConstructorMarker) null);
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$DecryptMessage;", "Lcom/reown/walletkit/client/Wallet$Params;", "topic", "", "encryptedMessage", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getEncryptedMessage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class DecryptMessage extends Params {
            @NotNull
            private final String encryptedMessage;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DecryptMessage(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
                this.topic = str;
                this.encryptedMessage = str2;
            }

            public static /* synthetic */ DecryptMessage copy$default(DecryptMessage decryptMessage, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = decryptMessage.topic;
                }
                if ((i3 & 2) != 0) {
                    str2 = decryptMessage.encryptedMessage;
                }
                return decryptMessage.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final String component2() {
                return this.encryptedMessage;
            }

            @NotNull
            public final DecryptMessage copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(str2, "encryptedMessage");
                return new DecryptMessage(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DecryptMessage)) {
                    return false;
                }
                DecryptMessage decryptMessage = (DecryptMessage) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) decryptMessage.topic) && Intrinsics.areEqual((Object) this.encryptedMessage, (Object) decryptMessage.encryptedMessage);
            }

            @NotNull
            public final String getEncryptedMessage() {
                return this.encryptedMessage;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                return this.encryptedMessage.hashCode() + (this.topic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("DecryptMessage(topic=", this.topic, ", encryptedMessage=", this.encryptedMessage, ")");
            }
        }

        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\bHÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$DoSendTransactions;", "Lcom/reown/walletkit/client/Wallet$Params;", "owner", "Lcom/reown/walletkit/client/Wallet$Params$Account;", "signatures", "", "Lcom/reown/walletkit/client/Wallet$Params$OwnerSignature;", "doSendTransactionParams", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Params$Account;Ljava/util/List;Ljava/lang/String;)V", "getOwner", "()Lcom/reown/walletkit/client/Wallet$Params$Account;", "getSignatures", "()Ljava/util/List;", "getDoSendTransactionParams", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class DoSendTransactions extends Params {
            @NotNull
            private final String doSendTransactionParams;
            @NotNull
            private final Account owner;
            @NotNull
            private final List<OwnerSignature> signatures;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DoSendTransactions(@NotNull Account account, @NotNull List<OwnerSignature> list, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(account, "owner");
                Intrinsics.checkNotNullParameter(list, "signatures");
                Intrinsics.checkNotNullParameter(str, "doSendTransactionParams");
                this.owner = account;
                this.signatures = list;
                this.doSendTransactionParams = str;
            }

            public static /* synthetic */ DoSendTransactions copy$default(DoSendTransactions doSendTransactions, Account account, List<OwnerSignature> list, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    account = doSendTransactions.owner;
                }
                if ((i3 & 2) != 0) {
                    list = doSendTransactions.signatures;
                }
                if ((i3 & 4) != 0) {
                    str = doSendTransactions.doSendTransactionParams;
                }
                return doSendTransactions.copy(account, list, str);
            }

            @NotNull
            public final Account component1() {
                return this.owner;
            }

            @NotNull
            public final List<OwnerSignature> component2() {
                return this.signatures;
            }

            @NotNull
            public final String component3() {
                return this.doSendTransactionParams;
            }

            @NotNull
            public final DoSendTransactions copy(@NotNull Account account, @NotNull List<OwnerSignature> list, @NotNull String str) {
                Intrinsics.checkNotNullParameter(account, "owner");
                Intrinsics.checkNotNullParameter(list, "signatures");
                Intrinsics.checkNotNullParameter(str, "doSendTransactionParams");
                return new DoSendTransactions(account, list, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof DoSendTransactions)) {
                    return false;
                }
                DoSendTransactions doSendTransactions = (DoSendTransactions) obj;
                return Intrinsics.areEqual((Object) this.owner, (Object) doSendTransactions.owner) && Intrinsics.areEqual((Object) this.signatures, (Object) doSendTransactions.signatures) && Intrinsics.areEqual((Object) this.doSendTransactionParams, (Object) doSendTransactions.doSendTransactionParams);
            }

            @NotNull
            public final String getDoSendTransactionParams() {
                return this.doSendTransactionParams;
            }

            @NotNull
            public final Account getOwner() {
                return this.owner;
            }

            @NotNull
            public final List<OwnerSignature> getSignatures() {
                return this.signatures;
            }

            public int hashCode() {
                return this.doSendTransactionParams.hashCode() + a.j(this.signatures, this.owner.hashCode() * 31, 31);
            }

            @NotNull
            public String toString() {
                Account account = this.owner;
                List<OwnerSignature> list = this.signatures;
                String str = this.doSendTransactionParams;
                StringBuilder sb = new StringBuilder("DoSendTransactions(owner=");
                sb.append(account);
                sb.append(", signatures=");
                sb.append(list);
                sb.append(", doSendTransactionParams=");
                return A.a.n(sb, str, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$DoSendTransactionsResult;", "Lcom/reown/walletkit/client/Wallet$Params;", "userOperationHash", "", "<init>", "(Ljava/lang/String;)V", "getUserOperationHash", "()Ljava/lang/String;", "setUserOperationHash", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class DoSendTransactionsResult extends Params {
            @NotNull
            private String userOperationHash;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public DoSendTransactionsResult(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "userOperationHash");
                this.userOperationHash = str;
            }

            public static /* synthetic */ DoSendTransactionsResult copy$default(DoSendTransactionsResult doSendTransactionsResult, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = doSendTransactionsResult.userOperationHash;
                }
                return doSendTransactionsResult.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.userOperationHash;
            }

            @NotNull
            public final DoSendTransactionsResult copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "userOperationHash");
                return new DoSendTransactionsResult(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof DoSendTransactionsResult) && Intrinsics.areEqual((Object) this.userOperationHash, (Object) ((DoSendTransactionsResult) obj).userOperationHash);
            }

            @NotNull
            public final String getUserOperationHash() {
                return this.userOperationHash;
            }

            public int hashCode() {
                return this.userOperationHash.hashCode();
            }

            public final void setUserOperationHash(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.userOperationHash = str;
            }

            @NotNull
            public String toString() {
                return A.a.l("DoSendTransactionsResult(userOperationHash=", this.userOperationHash, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$FormatAuthMessage;", "Lcom/reown/walletkit/client/Wallet$Params;", "payloadParams", "Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "issuer", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;Ljava/lang/String;)V", "getPayloadParams", "()Lcom/reown/walletkit/client/Wallet$Model$PayloadAuthRequestParams;", "getIssuer", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class FormatAuthMessage extends Params {
            @NotNull
            private final String issuer;
            @NotNull
            private final Model.PayloadAuthRequestParams payloadParams;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FormatAuthMessage(@NotNull Model.PayloadAuthRequestParams payloadAuthRequestParams, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "issuer");
                this.payloadParams = payloadAuthRequestParams;
                this.issuer = str;
            }

            public static /* synthetic */ FormatAuthMessage copy$default(FormatAuthMessage formatAuthMessage, Model.PayloadAuthRequestParams payloadAuthRequestParams, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    payloadAuthRequestParams = formatAuthMessage.payloadParams;
                }
                if ((i3 & 2) != 0) {
                    str = formatAuthMessage.issuer;
                }
                return formatAuthMessage.copy(payloadAuthRequestParams, str);
            }

            @NotNull
            public final Model.PayloadAuthRequestParams component1() {
                return this.payloadParams;
            }

            @NotNull
            public final String component2() {
                return this.issuer;
            }

            @NotNull
            public final FormatAuthMessage copy(@NotNull Model.PayloadAuthRequestParams payloadAuthRequestParams, @NotNull String str) {
                Intrinsics.checkNotNullParameter(payloadAuthRequestParams, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "issuer");
                return new FormatAuthMessage(payloadAuthRequestParams, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FormatAuthMessage)) {
                    return false;
                }
                FormatAuthMessage formatAuthMessage = (FormatAuthMessage) obj;
                return Intrinsics.areEqual((Object) this.payloadParams, (Object) formatAuthMessage.payloadParams) && Intrinsics.areEqual((Object) this.issuer, (Object) formatAuthMessage.issuer);
            }

            @NotNull
            public final String getIssuer() {
                return this.issuer;
            }

            @NotNull
            public final Model.PayloadAuthRequestParams getPayloadParams() {
                return this.payloadParams;
            }

            public int hashCode() {
                return this.issuer.hashCode() + (this.payloadParams.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                Model.PayloadAuthRequestParams payloadAuthRequestParams = this.payloadParams;
                String str = this.issuer;
                return "FormatAuthMessage(payloadParams=" + payloadAuthRequestParams + ", issuer=" + str + ")";
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$FormatMessage;", "Lcom/reown/walletkit/client/Wallet$Params;", "payloadParams", "Lcom/reown/walletkit/client/Wallet$Model$PayloadParams;", "issuer", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Model$PayloadParams;Ljava/lang/String;)V", "getPayloadParams", "()Lcom/reown/walletkit/client/Wallet$Model$PayloadParams;", "getIssuer", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class FormatMessage extends Params {
            @NotNull
            private final String issuer;
            @NotNull
            private final Model.PayloadParams payloadParams;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public FormatMessage(@NotNull Model.PayloadParams payloadParams2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "issuer");
                this.payloadParams = payloadParams2;
                this.issuer = str;
            }

            public static /* synthetic */ FormatMessage copy$default(FormatMessage formatMessage, Model.PayloadParams payloadParams2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    payloadParams2 = formatMessage.payloadParams;
                }
                if ((i3 & 2) != 0) {
                    str = formatMessage.issuer;
                }
                return formatMessage.copy(payloadParams2, str);
            }

            @NotNull
            public final Model.PayloadParams component1() {
                return this.payloadParams;
            }

            @NotNull
            public final String component2() {
                return this.issuer;
            }

            @NotNull
            public final FormatMessage copy(@NotNull Model.PayloadParams payloadParams2, @NotNull String str) {
                Intrinsics.checkNotNullParameter(payloadParams2, "payloadParams");
                Intrinsics.checkNotNullParameter(str, "issuer");
                return new FormatMessage(payloadParams2, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FormatMessage)) {
                    return false;
                }
                FormatMessage formatMessage = (FormatMessage) obj;
                return Intrinsics.areEqual((Object) this.payloadParams, (Object) formatMessage.payloadParams) && Intrinsics.areEqual((Object) this.issuer, (Object) formatMessage.issuer);
            }

            @NotNull
            public final String getIssuer() {
                return this.issuer;
            }

            @NotNull
            public final Model.PayloadParams getPayloadParams() {
                return this.payloadParams;
            }

            public int hashCode() {
                return this.issuer.hashCode() + (this.payloadParams.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                Model.PayloadParams payloadParams2 = this.payloadParams;
                String str = this.issuer;
                return "FormatMessage(payloadParams=" + payloadParams2 + ", issuer=" + str + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$GetSmartAccountAddress;", "Lcom/reown/walletkit/client/Wallet$Params;", "owner", "Lcom/reown/walletkit/client/Wallet$Params$Account;", "<init>", "(Lcom/reown/walletkit/client/Wallet$Params$Account;)V", "getOwner", "()Lcom/reown/walletkit/client/Wallet$Params$Account;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class GetSmartAccountAddress extends Params {
            @NotNull
            private final Account owner;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public GetSmartAccountAddress(@NotNull Account account) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(account, "owner");
                this.owner = account;
            }

            public static /* synthetic */ GetSmartAccountAddress copy$default(GetSmartAccountAddress getSmartAccountAddress, Account account, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    account = getSmartAccountAddress.owner;
                }
                return getSmartAccountAddress.copy(account);
            }

            @NotNull
            public final Account component1() {
                return this.owner;
            }

            @NotNull
            public final GetSmartAccountAddress copy(@NotNull Account account) {
                Intrinsics.checkNotNullParameter(account, "owner");
                return new GetSmartAccountAddress(account);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof GetSmartAccountAddress) && Intrinsics.areEqual((Object) this.owner, (Object) ((GetSmartAccountAddress) obj).owner);
            }

            @NotNull
            public final Account getOwner() {
                return this.owner;
            }

            public int hashCode() {
                return this.owner.hashCode();
            }

            @NotNull
            public String toString() {
                Account account = this.owner;
                return "GetSmartAccountAddress(owner=" + account + ")";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$Init;", "Lcom/reown/walletkit/client/Wallet$Params;", "core", "Lcom/reown/android/CoreInterface;", "<init>", "(Lcom/reown/android/CoreInterface;)V", "getCore", "()Lcom/reown/android/CoreInterface;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Init extends Params {
            @NotNull
            private final CoreInterface core;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Init(@NotNull CoreInterface coreInterface) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(coreInterface, "core");
                this.core = coreInterface;
            }

            public static /* synthetic */ Init copy$default(Init init, CoreInterface coreInterface, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    coreInterface = init.core;
                }
                return init.copy(coreInterface);
            }

            @NotNull
            public final CoreInterface component1() {
                return this.core;
            }

            @NotNull
            public final Init copy(@NotNull CoreInterface coreInterface) {
                Intrinsics.checkNotNullParameter(coreInterface, "core");
                return new Init(coreInterface);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof Init) && Intrinsics.areEqual((Object) this.core, (Object) ((Init) obj).core);
            }

            @NotNull
            public final CoreInterface getCore() {
                return this.core;
            }

            public int hashCode() {
                return this.core.hashCode();
            }

            @NotNull
            public String toString() {
                CoreInterface coreInterface = this.core;
                return "Init(core=" + coreInterface + ")";
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$OwnerSignature;", "Lcom/reown/walletkit/client/Wallet$Params;", "address", "", "signature", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getSignature", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OwnerSignature extends Params {
            @NotNull
            private final String address;
            @NotNull
            private final String signature;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public OwnerSignature(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
                Intrinsics.checkNotNullParameter(str2, "signature");
                this.address = str;
                this.signature = str2;
            }

            public static /* synthetic */ OwnerSignature copy$default(OwnerSignature ownerSignature, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = ownerSignature.address;
                }
                if ((i3 & 2) != 0) {
                    str2 = ownerSignature.signature;
                }
                return ownerSignature.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.address;
            }

            @NotNull
            public final String component2() {
                return this.signature;
            }

            @NotNull
            public final OwnerSignature copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, Address.TYPE_NAME);
                Intrinsics.checkNotNullParameter(str2, "signature");
                return new OwnerSignature(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof OwnerSignature)) {
                    return false;
                }
                OwnerSignature ownerSignature = (OwnerSignature) obj;
                return Intrinsics.areEqual((Object) this.address, (Object) ownerSignature.address) && Intrinsics.areEqual((Object) this.signature, (Object) ownerSignature.signature);
            }

            @NotNull
            public final String getAddress() {
                return this.address;
            }

            @NotNull
            public final String getSignature() {
                return this.signature;
            }

            public int hashCode() {
                return this.signature.hashCode() + (this.address.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("OwnerSignature(address=", this.address, ", signature=", this.signature, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$Pair;", "Lcom/reown/walletkit/client/Wallet$Params;", "uri", "", "<init>", "(Ljava/lang/String;)V", "getUri", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u000bJ$\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$Ping;", "Lcom/reown/walletkit/client/Wallet$Params;", "sessionTopic", "", "timeout", "Lkotlin/time/Duration;", "<init>", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSessionTopic", "()Ljava/lang/String;", "getTimeout-UwyO8pc", "()J", "J", "component1", "component2", "component2-UwyO8pc", "copy", "copy-HG0u8IE", "(Ljava/lang/String;J)Lcom/reown/walletkit/client/Wallet$Params$Ping;", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Ping extends Params {
            @NotNull
            private final String sessionTopic;
            private final long timeout;

            public /* synthetic */ Ping(String str, long j2, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, j2);
            }

            /* renamed from: copy-HG0u8IE$default  reason: not valid java name */
            public static /* synthetic */ Ping m8898copyHG0u8IE$default(Ping ping, String str, long j2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = ping.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    j2 = ping.timeout;
                }
                return ping.m8900copyHG0u8IE(str, j2);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            /* renamed from: component2-UwyO8pc  reason: not valid java name */
            public final long m8899component2UwyO8pc() {
                return this.timeout;
            }

            @NotNull
            /* renamed from: copy-HG0u8IE  reason: not valid java name */
            public final Ping m8900copyHG0u8IE(@NotNull String str, long j2) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                return new Ping(str, j2, (DefaultConstructorMarker) null);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Ping)) {
                    return false;
                }
                Ping ping = (Ping) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) ping.sessionTopic) && Duration.m10319equalsimpl0(this.timeout, ping.timeout);
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            /* renamed from: getTimeout-UwyO8pc  reason: not valid java name */
            public final long m8901getTimeoutUwyO8pc() {
                return this.timeout;
            }

            public int hashCode() {
                return Duration.m10335hashCodeimpl(this.timeout) + (this.sessionTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("Ping(sessionTopic=", this.sessionTopic, ", timeout=", Duration.m10354toStringimpl(this.timeout), ")");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            private Ping(String str, long j2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                this.sessionTopic = str;
                this.timeout = j2;
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ Ping(java.lang.String r1, long r2, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
                /*
                    r0 = this;
                    r4 = r4 & 2
                    if (r4 == 0) goto L_0x000e
                    kotlin.time.Duration$Companion r2 = kotlin.time.Duration.Companion
                    r2 = 30
                    kotlin.time.DurationUnit r3 = kotlin.time.DurationUnit.SECONDS
                    long r2 = kotlin.time.DurationKt.toDuration((int) r2, (kotlin.time.DurationUnit) r3)
                L_0x000e:
                    r4 = 0
                    r0.<init>(r1, r2, r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.reown.walletkit.client.Wallet.Params.Ping.<init>(java.lang.String, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$PrepareSendTransactions;", "Lcom/reown/walletkit/client/Wallet$Params;", "calls", "", "Lcom/reown/walletkit/client/Wallet$Model$Call;", "owner", "Lcom/reown/walletkit/client/Wallet$Params$Account;", "<init>", "(Ljava/util/List;Lcom/reown/walletkit/client/Wallet$Params$Account;)V", "getCalls", "()Ljava/util/List;", "getOwner", "()Lcom/reown/walletkit/client/Wallet$Params$Account;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PrepareSendTransactions extends Params {
            @NotNull
            private final List<Model.Call> calls;
            @NotNull
            private final Account owner;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PrepareSendTransactions(@NotNull List<Model.Call> list, @NotNull Account account) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "calls");
                Intrinsics.checkNotNullParameter(account, "owner");
                this.calls = list;
                this.owner = account;
            }

            public static /* synthetic */ PrepareSendTransactions copy$default(PrepareSendTransactions prepareSendTransactions, List<Model.Call> list, Account account, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    list = prepareSendTransactions.calls;
                }
                if ((i3 & 2) != 0) {
                    account = prepareSendTransactions.owner;
                }
                return prepareSendTransactions.copy(list, account);
            }

            @NotNull
            public final List<Model.Call> component1() {
                return this.calls;
            }

            @NotNull
            public final Account component2() {
                return this.owner;
            }

            @NotNull
            public final PrepareSendTransactions copy(@NotNull List<Model.Call> list, @NotNull Account account) {
                Intrinsics.checkNotNullParameter(list, "calls");
                Intrinsics.checkNotNullParameter(account, "owner");
                return new PrepareSendTransactions(list, account);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PrepareSendTransactions)) {
                    return false;
                }
                PrepareSendTransactions prepareSendTransactions = (PrepareSendTransactions) obj;
                return Intrinsics.areEqual((Object) this.calls, (Object) prepareSendTransactions.calls) && Intrinsics.areEqual((Object) this.owner, (Object) prepareSendTransactions.owner);
            }

            @NotNull
            public final List<Model.Call> getCalls() {
                return this.calls;
            }

            @NotNull
            public final Account getOwner() {
                return this.owner;
            }

            public int hashCode() {
                return this.owner.hashCode() + (this.calls.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                List<Model.Call> list = this.calls;
                Account account = this.owner;
                return "PrepareSendTransactions(calls=" + list + ", owner=" + account + ")";
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$PrepareSendTransactionsResult;", "Lcom/reown/walletkit/client/Wallet$Params;", "hash", "", "doSendTransactionParams", "eip712Domain", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getHash", "()Ljava/lang/String;", "setHash", "(Ljava/lang/String;)V", "getDoSendTransactionParams", "setDoSendTransactionParams", "getEip712Domain", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class PrepareSendTransactionsResult extends Params {
            @NotNull
            private String doSendTransactionParams;
            @NotNull
            private final String eip712Domain;
            @NotNull
            private String hash;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public PrepareSendTransactionsResult(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "hash");
                Intrinsics.checkNotNullParameter(str2, "doSendTransactionParams");
                Intrinsics.checkNotNullParameter(str3, "eip712Domain");
                this.hash = str;
                this.doSendTransactionParams = str2;
                this.eip712Domain = str3;
            }

            public static /* synthetic */ PrepareSendTransactionsResult copy$default(PrepareSendTransactionsResult prepareSendTransactionsResult, String str, String str2, String str3, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = prepareSendTransactionsResult.hash;
                }
                if ((i3 & 2) != 0) {
                    str2 = prepareSendTransactionsResult.doSendTransactionParams;
                }
                if ((i3 & 4) != 0) {
                    str3 = prepareSendTransactionsResult.eip712Domain;
                }
                return prepareSendTransactionsResult.copy(str, str2, str3);
            }

            @NotNull
            public final String component1() {
                return this.hash;
            }

            @NotNull
            public final String component2() {
                return this.doSendTransactionParams;
            }

            @NotNull
            public final String component3() {
                return this.eip712Domain;
            }

            @NotNull
            public final PrepareSendTransactionsResult copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
                Intrinsics.checkNotNullParameter(str, "hash");
                Intrinsics.checkNotNullParameter(str2, "doSendTransactionParams");
                Intrinsics.checkNotNullParameter(str3, "eip712Domain");
                return new PrepareSendTransactionsResult(str, str2, str3);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof PrepareSendTransactionsResult)) {
                    return false;
                }
                PrepareSendTransactionsResult prepareSendTransactionsResult = (PrepareSendTransactionsResult) obj;
                return Intrinsics.areEqual((Object) this.hash, (Object) prepareSendTransactionsResult.hash) && Intrinsics.areEqual((Object) this.doSendTransactionParams, (Object) prepareSendTransactionsResult.doSendTransactionParams) && Intrinsics.areEqual((Object) this.eip712Domain, (Object) prepareSendTransactionsResult.eip712Domain);
            }

            @NotNull
            public final String getDoSendTransactionParams() {
                return this.doSendTransactionParams;
            }

            @NotNull
            public final String getEip712Domain() {
                return this.eip712Domain;
            }

            @NotNull
            public final String getHash() {
                return this.hash;
            }

            public int hashCode() {
                return this.eip712Domain.hashCode() + a.i(this.doSendTransactionParams, this.hash.hashCode() * 31, 31);
            }

            public final void setDoSendTransactionParams(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.doSendTransactionParams = str;
            }

            public final void setHash(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.hash = str;
            }

            @NotNull
            public String toString() {
                String str = this.hash;
                String str2 = this.doSendTransactionParams;
                return A.a.n(C0118y.l("PrepareSendTransactionsResult(hash=", str, ", doSendTransactionParams=", str2, ", eip712Domain="), this.eip712Domain, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$RejectSessionAuthenticate;", "Lcom/reown/walletkit/client/Wallet$Params;", "id", "", "reason", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getReason", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RejectSessionAuthenticate extends Params {
            private final long id;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public RejectSessionAuthenticate(long j2, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "reason");
                this.id = j2;
                this.reason = str;
            }

            public static /* synthetic */ RejectSessionAuthenticate copy$default(RejectSessionAuthenticate rejectSessionAuthenticate, long j2, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    j2 = rejectSessionAuthenticate.id;
                }
                if ((i3 & 2) != 0) {
                    str = rejectSessionAuthenticate.reason;
                }
                return rejectSessionAuthenticate.copy(j2, str);
            }

            public final long component1() {
                return this.id;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final RejectSessionAuthenticate copy(long j2, @NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "reason");
                return new RejectSessionAuthenticate(j2, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RejectSessionAuthenticate)) {
                    return false;
                }
                RejectSessionAuthenticate rejectSessionAuthenticate = (RejectSessionAuthenticate) obj;
                return this.id == rejectSessionAuthenticate.id && Intrinsics.areEqual((Object) this.reason, (Object) rejectSessionAuthenticate.reason);
            }

            public final long getId() {
                return this.id;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode() + (Long.hashCode(this.id) * 31);
            }

            @NotNull
            public String toString() {
                StringBuilder v2 = androidx.work.impl.a.v(this.id, "RejectSessionAuthenticate(id=", ", reason=", this.reason);
                v2.append(")");
                return v2.toString();
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0017\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003Je\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001f\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006 "}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionApprove;", "Lcom/reown/walletkit/client/Wallet$Params;", "proposerPublicKey", "", "namespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "properties", "scopedProperties", "relayProtocol", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;)V", "getProposerPublicKey", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "getProperties", "getScopedProperties", "getRelayProtocol", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionApprove extends Params {
            @NotNull
            private final Map<String, Model.Namespace.Session> namespaces;
            @Nullable
            private final Map<String, String> properties;
            @NotNull
            private final String proposerPublicKey;
            @Nullable
            private final String relayProtocol;
            @Nullable
            private final Map<String, String> scopedProperties;

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ SessionApprove(String str, Map map, Map map2, Map map3, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, map, (i3 & 4) != 0 ? null : map2, (i3 & 8) != 0 ? null : map3, (i3 & 16) != 0 ? null : str2);
            }

            public static /* synthetic */ SessionApprove copy$default(SessionApprove sessionApprove, String str, Map<String, Model.Namespace.Session> map, Map<String, String> map2, Map<String, String> map3, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionApprove.proposerPublicKey;
                }
                if ((i3 & 2) != 0) {
                    map = sessionApprove.namespaces;
                }
                Map<String, Model.Namespace.Session> map4 = map;
                if ((i3 & 4) != 0) {
                    map2 = sessionApprove.properties;
                }
                Map<String, String> map5 = map2;
                if ((i3 & 8) != 0) {
                    map3 = sessionApprove.scopedProperties;
                }
                Map<String, String> map6 = map3;
                if ((i3 & 16) != 0) {
                    str2 = sessionApprove.relayProtocol;
                }
                return sessionApprove.copy(str, map4, map5, map6, str2);
            }

            @NotNull
            public final String component1() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> component2() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, String> component3() {
                return this.properties;
            }

            @Nullable
            public final Map<String, String> component4() {
                return this.scopedProperties;
            }

            @Nullable
            public final String component5() {
                return this.relayProtocol;
            }

            @NotNull
            public final SessionApprove copy(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new SessionApprove(str, map, map2, map3, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionApprove)) {
                    return false;
                }
                SessionApprove sessionApprove = (SessionApprove) obj;
                return Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionApprove.proposerPublicKey) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionApprove.namespaces) && Intrinsics.areEqual((Object) this.properties, (Object) sessionApprove.properties) && Intrinsics.areEqual((Object) this.scopedProperties, (Object) sessionApprove.scopedProperties) && Intrinsics.areEqual((Object) this.relayProtocol, (Object) sessionApprove.relayProtocol);
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @Nullable
            public final Map<String, String> getProperties() {
                return this.properties;
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            @Nullable
            public final String getRelayProtocol() {
                return this.relayProtocol;
            }

            @Nullable
            public final Map<String, String> getScopedProperties() {
                return this.scopedProperties;
            }

            public int hashCode() {
                int d2 = b.d(this.namespaces, this.proposerPublicKey.hashCode() * 31, 31);
                Map<String, String> map = this.properties;
                int i3 = 0;
                int hashCode = (d2 + (map == null ? 0 : map.hashCode())) * 31;
                Map<String, String> map2 = this.scopedProperties;
                int hashCode2 = (hashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
                String str = this.relayProtocol;
                if (str != null) {
                    i3 = str.hashCode();
                }
                return hashCode2 + i3;
            }

            @NotNull
            public String toString() {
                String str = this.proposerPublicKey;
                Map<String, Model.Namespace.Session> map = this.namespaces;
                Map<String, String> map2 = this.properties;
                Map<String, String> map3 = this.scopedProperties;
                String str2 = this.relayProtocol;
                StringBuilder sb = new StringBuilder("SessionApprove(proposerPublicKey=");
                sb.append(str);
                sb.append(", namespaces=");
                sb.append(map);
                sb.append(", properties=");
                sb.append(map2);
                sb.append(", scopedProperties=");
                sb.append(map3);
                sb.append(", relayProtocol=");
                return A.a.n(sb, str2, ")");
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionApprove(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.proposerPublicKey = str;
                this.namespaces = map;
                this.properties = map2;
                this.scopedProperties = map3;
                this.relayProtocol = str2;
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionDisconnect;", "Lcom/reown/walletkit/client/Wallet$Params;", "sessionTopic", "", "<init>", "(Ljava/lang/String;)V", "getSessionTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionDisconnect extends Params {
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionDisconnect(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                this.sessionTopic = str;
            }

            public static /* synthetic */ SessionDisconnect copy$default(SessionDisconnect sessionDisconnect, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionDisconnect.sessionTopic;
                }
                return sessionDisconnect.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final SessionDisconnect copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                return new SessionDisconnect(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof SessionDisconnect) && Intrinsics.areEqual((Object) this.sessionTopic, (Object) ((SessionDisconnect) obj).sessionTopic);
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.sessionTopic.hashCode();
            }

            @NotNull
            public String toString() {
                return A.a.l("SessionDisconnect(sessionTopic=", this.sessionTopic, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionEmit;", "Lcom/reown/walletkit/client/Wallet$Params;", "topic", "", "event", "Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;", "chainId", "<init>", "(Ljava/lang/String;Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "getEvent", "()Lcom/reown/walletkit/client/Wallet$Model$SessionEvent;", "getChainId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionEmit extends Params {
            @NotNull
            private final String chainId;
            @NotNull
            private final Model.SessionEvent event;
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionEmit(@NotNull String str, @NotNull Model.SessionEvent sessionEvent, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(sessionEvent, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkNotNullParameter(str2, "chainId");
                this.topic = str;
                this.event = sessionEvent;
                this.chainId = str2;
            }

            public static /* synthetic */ SessionEmit copy$default(SessionEmit sessionEmit, String str, Model.SessionEvent sessionEvent, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionEmit.topic;
                }
                if ((i3 & 2) != 0) {
                    sessionEvent = sessionEmit.event;
                }
                if ((i3 & 4) != 0) {
                    str2 = sessionEmit.chainId;
                }
                return sessionEmit.copy(str, sessionEvent, str2);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final Model.SessionEvent component2() {
                return this.event;
            }

            @NotNull
            public final String component3() {
                return this.chainId;
            }

            @NotNull
            public final SessionEmit copy(@NotNull String str, @NotNull Model.SessionEvent sessionEvent, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                Intrinsics.checkNotNullParameter(sessionEvent, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkNotNullParameter(str2, "chainId");
                return new SessionEmit(str, sessionEvent, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionEmit)) {
                    return false;
                }
                SessionEmit sessionEmit = (SessionEmit) obj;
                return Intrinsics.areEqual((Object) this.topic, (Object) sessionEmit.topic) && Intrinsics.areEqual((Object) this.event, (Object) sessionEmit.event) && Intrinsics.areEqual((Object) this.chainId, (Object) sessionEmit.chainId);
            }

            @NotNull
            public final String getChainId() {
                return this.chainId;
            }

            @NotNull
            public final Model.SessionEvent getEvent() {
                return this.event;
            }

            @NotNull
            public final String getTopic() {
                return this.topic;
            }

            public int hashCode() {
                int hashCode = this.event.hashCode();
                return this.chainId.hashCode() + ((hashCode + (this.topic.hashCode() * 31)) * 31);
            }

            @NotNull
            public String toString() {
                String str = this.topic;
                Model.SessionEvent sessionEvent = this.event;
                String str2 = this.chainId;
                StringBuilder sb = new StringBuilder("SessionEmit(topic=");
                sb.append(str);
                sb.append(", event=");
                sb.append(sessionEvent);
                sb.append(", chainId=");
                return A.a.n(sb, str2, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionExtend;", "Lcom/reown/walletkit/client/Wallet$Params;", "topic", "", "<init>", "(Ljava/lang/String;)V", "getTopic", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionExtend extends Params {
            @NotNull
            private final String topic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionExtend(@NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                this.topic = str;
            }

            public static /* synthetic */ SessionExtend copy$default(SessionExtend sessionExtend, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionExtend.topic;
                }
                return sessionExtend.copy(str);
            }

            @NotNull
            public final String component1() {
                return this.topic;
            }

            @NotNull
            public final SessionExtend copy(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
                return new SessionExtend(str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof SessionExtend) && Intrinsics.areEqual((Object) this.topic, (Object) ((SessionExtend) obj).topic);
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
                return A.a.l("SessionExtend(topic=", this.topic, ")");
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionReject;", "Lcom/reown/walletkit/client/Wallet$Params;", "proposerPublicKey", "", "reason", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getProposerPublicKey", "()Ljava/lang/String;", "getReason", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionReject extends Params {
            @NotNull
            private final String proposerPublicKey;
            @NotNull
            private final String reason;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionReject(@NotNull String str, @NotNull String str2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(str2, "reason");
                this.proposerPublicKey = str;
                this.reason = str2;
            }

            public static /* synthetic */ SessionReject copy$default(SessionReject sessionReject, String str, String str2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionReject.proposerPublicKey;
                }
                if ((i3 & 2) != 0) {
                    str2 = sessionReject.reason;
                }
                return sessionReject.copy(str, str2);
            }

            @NotNull
            public final String component1() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String component2() {
                return this.reason;
            }

            @NotNull
            public final SessionReject copy(@NotNull String str, @NotNull String str2) {
                Intrinsics.checkNotNullParameter(str, "proposerPublicKey");
                Intrinsics.checkNotNullParameter(str2, "reason");
                return new SessionReject(str, str2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionReject)) {
                    return false;
                }
                SessionReject sessionReject = (SessionReject) obj;
                return Intrinsics.areEqual((Object) this.proposerPublicKey, (Object) sessionReject.proposerPublicKey) && Intrinsics.areEqual((Object) this.reason, (Object) sessionReject.reason);
            }

            @NotNull
            public final String getProposerPublicKey() {
                return this.proposerPublicKey;
            }

            @NotNull
            public final String getReason() {
                return this.reason;
            }

            public int hashCode() {
                return this.reason.hashCode() + (this.proposerPublicKey.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                return C0118y.g("SessionReject(proposerPublicKey=", this.proposerPublicKey, ", reason=", this.reason, ")");
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionRequestResponse;", "Lcom/reown/walletkit/client/Wallet$Params;", "sessionTopic", "", "jsonRpcResponse", "Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "<init>", "(Ljava/lang/String;Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;)V", "getSessionTopic", "()Ljava/lang/String;", "getJsonRpcResponse", "()Lcom/reown/walletkit/client/Wallet$Model$JsonRpcResponse;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionRequestResponse extends Params {
            @NotNull
            private final Model.JsonRpcResponse jsonRpcResponse;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionRequestResponse(@NotNull String str, @NotNull Model.JsonRpcResponse jsonRpcResponse2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(jsonRpcResponse2, "jsonRpcResponse");
                this.sessionTopic = str;
                this.jsonRpcResponse = jsonRpcResponse2;
            }

            public static /* synthetic */ SessionRequestResponse copy$default(SessionRequestResponse sessionRequestResponse, String str, Model.JsonRpcResponse jsonRpcResponse2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionRequestResponse.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    jsonRpcResponse2 = sessionRequestResponse.jsonRpcResponse;
                }
                return sessionRequestResponse.copy(str, jsonRpcResponse2);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final Model.JsonRpcResponse component2() {
                return this.jsonRpcResponse;
            }

            @NotNull
            public final SessionRequestResponse copy(@NotNull String str, @NotNull Model.JsonRpcResponse jsonRpcResponse2) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(jsonRpcResponse2, "jsonRpcResponse");
                return new SessionRequestResponse(str, jsonRpcResponse2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionRequestResponse)) {
                    return false;
                }
                SessionRequestResponse sessionRequestResponse = (SessionRequestResponse) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) sessionRequestResponse.sessionTopic) && Intrinsics.areEqual((Object) this.jsonRpcResponse, (Object) sessionRequestResponse.jsonRpcResponse);
            }

            @NotNull
            public final Model.JsonRpcResponse getJsonRpcResponse() {
                return this.jsonRpcResponse;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.jsonRpcResponse.hashCode() + (this.sessionTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                String str = this.sessionTopic;
                Model.JsonRpcResponse jsonRpcResponse2 = this.jsonRpcResponse;
                return "SessionRequestResponse(sessionTopic=" + str + ", jsonRpcResponse=" + jsonRpcResponse2 + ")";
            }
        }

        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$SessionUpdate;", "Lcom/reown/walletkit/client/Wallet$Params;", "sessionTopic", "", "namespaces", "", "Lcom/reown/walletkit/client/Wallet$Model$Namespace$Session;", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getSessionTopic", "()Ljava/lang/String;", "getNamespaces", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SessionUpdate extends Params {
            @NotNull
            private final Map<String, Model.Namespace.Session> namespaces;
            @NotNull
            private final String sessionTopic;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SessionUpdate(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                this.sessionTopic = str;
                this.namespaces = map;
            }

            public static /* synthetic */ SessionUpdate copy$default(SessionUpdate sessionUpdate, String str, Map<String, Model.Namespace.Session> map, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = sessionUpdate.sessionTopic;
                }
                if ((i3 & 2) != 0) {
                    map = sessionUpdate.namespaces;
                }
                return sessionUpdate.copy(str, map);
            }

            @NotNull
            public final String component1() {
                return this.sessionTopic;
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> component2() {
                return this.namespaces;
            }

            @NotNull
            public final SessionUpdate copy(@NotNull String str, @NotNull Map<String, Model.Namespace.Session> map) {
                Intrinsics.checkNotNullParameter(str, "sessionTopic");
                Intrinsics.checkNotNullParameter(map, "namespaces");
                return new SessionUpdate(str, map);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SessionUpdate)) {
                    return false;
                }
                SessionUpdate sessionUpdate = (SessionUpdate) obj;
                return Intrinsics.areEqual((Object) this.sessionTopic, (Object) sessionUpdate.sessionTopic) && Intrinsics.areEqual((Object) this.namespaces, (Object) sessionUpdate.namespaces);
            }

            @NotNull
            public final Map<String, Model.Namespace.Session> getNamespaces() {
                return this.namespaces;
            }

            @NotNull
            public final String getSessionTopic() {
                return this.sessionTopic;
            }

            public int hashCode() {
                return this.namespaces.hashCode() + (this.sessionTopic.hashCode() * 31);
            }

            @NotNull
            public String toString() {
                String str = this.sessionTopic;
                Map<String, Model.Namespace.Session> map = this.namespaces;
                return "SessionUpdate(sessionTopic=" + str + ", namespaces=" + map + ")";
            }
        }

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/reown/walletkit/client/Wallet$Params$WaitForUserOperationReceipt;", "Lcom/reown/walletkit/client/Wallet$Params;", "owner", "Lcom/reown/walletkit/client/Wallet$Params$Account;", "userOperationHash", "", "<init>", "(Lcom/reown/walletkit/client/Wallet$Params$Account;Ljava/lang/String;)V", "getOwner", "()Lcom/reown/walletkit/client/Wallet$Params$Account;", "setOwner", "(Lcom/reown/walletkit/client/Wallet$Params$Account;)V", "getUserOperationHash", "()Ljava/lang/String;", "setUserOperationHash", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "walletkit_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class WaitForUserOperationReceipt extends Params {
            @NotNull
            private Account owner;
            @NotNull
            private String userOperationHash;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public WaitForUserOperationReceipt(@NotNull Account account, @NotNull String str) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(account, "owner");
                Intrinsics.checkNotNullParameter(str, "userOperationHash");
                this.owner = account;
                this.userOperationHash = str;
            }

            public static /* synthetic */ WaitForUserOperationReceipt copy$default(WaitForUserOperationReceipt waitForUserOperationReceipt, Account account, String str, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    account = waitForUserOperationReceipt.owner;
                }
                if ((i3 & 2) != 0) {
                    str = waitForUserOperationReceipt.userOperationHash;
                }
                return waitForUserOperationReceipt.copy(account, str);
            }

            @NotNull
            public final Account component1() {
                return this.owner;
            }

            @NotNull
            public final String component2() {
                return this.userOperationHash;
            }

            @NotNull
            public final WaitForUserOperationReceipt copy(@NotNull Account account, @NotNull String str) {
                Intrinsics.checkNotNullParameter(account, "owner");
                Intrinsics.checkNotNullParameter(str, "userOperationHash");
                return new WaitForUserOperationReceipt(account, str);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof WaitForUserOperationReceipt)) {
                    return false;
                }
                WaitForUserOperationReceipt waitForUserOperationReceipt = (WaitForUserOperationReceipt) obj;
                return Intrinsics.areEqual((Object) this.owner, (Object) waitForUserOperationReceipt.owner) && Intrinsics.areEqual((Object) this.userOperationHash, (Object) waitForUserOperationReceipt.userOperationHash);
            }

            @NotNull
            public final Account getOwner() {
                return this.owner;
            }

            @NotNull
            public final String getUserOperationHash() {
                return this.userOperationHash;
            }

            public int hashCode() {
                return this.userOperationHash.hashCode() + (this.owner.hashCode() * 31);
            }

            public final void setOwner(@NotNull Account account) {
                Intrinsics.checkNotNullParameter(account, "<set-?>");
                this.owner = account;
            }

            public final void setUserOperationHash(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.userOperationHash = str;
            }

            @NotNull
            public String toString() {
                Account account = this.owner;
                String str = this.userOperationHash;
                return "WaitForUserOperationReceipt(owner=" + account + ", userOperationHash=" + str + ")";
            }
        }

        public /* synthetic */ Params(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Params() {
        }
    }

    private Wallet() {
    }
}
