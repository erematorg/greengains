package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction;", "", "<init>", "()V", "TransactionWrapper", "TransactionJson", "TakerPays", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class XRPLSignTransaction {
    @NotNull
    public static final XRPLSignTransaction INSTANCE = new XRPLSignTransaction();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;", "", "currency", "", "issuer", "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCurrency", "()Ljava/lang/String;", "getIssuer", "getValue", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TakerPays {
        @Nullable
        private final String currency;
        @Nullable
        private final String issuer;
        @Nullable
        private final String value;

        public TakerPays(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.currency = str;
            this.issuer = str2;
            this.value = str3;
        }

        public static /* synthetic */ TakerPays copy$default(TakerPays takerPays, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = takerPays.currency;
            }
            if ((i3 & 2) != 0) {
                str2 = takerPays.issuer;
            }
            if ((i3 & 4) != 0) {
                str3 = takerPays.value;
            }
            return takerPays.copy(str, str2, str3);
        }

        @Nullable
        public final String component1() {
            return this.currency;
        }

        @Nullable
        public final String component2() {
            return this.issuer;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @NotNull
        public final TakerPays copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            return new TakerPays(str, str2, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TakerPays)) {
                return false;
            }
            TakerPays takerPays = (TakerPays) obj;
            return Intrinsics.areEqual((Object) this.currency, (Object) takerPays.currency) && Intrinsics.areEqual((Object) this.issuer, (Object) takerPays.issuer) && Intrinsics.areEqual((Object) this.value, (Object) takerPays.value);
        }

        @Nullable
        public final String getCurrency() {
            return this.currency;
        }

        @Nullable
        public final String getIssuer() {
            return this.issuer;
        }

        @Nullable
        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            String str = this.currency;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.issuer;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.value;
            if (str3 != null) {
                i3 = str3.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.currency;
            String str2 = this.issuer;
            return a.n(C0118y.l("TakerPays(currency=", str, ", issuer=", str2, ", value="), this.value, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J¨\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\bHÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001e\u0010\u0018R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001f\u0010\u0018R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b \u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0016¨\u0006<"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;", "", "Account", "", "Expiration", "", "Fee", "Flags", "", "OfferSequence", "Sequence", "LastLedgerSequence", "SigningPubKey", "TakerGets", "TakerPays", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;", "TransactionType", "TxnSignature", "hash", "<init>", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getExpiration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFee", "getFlags", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOfferSequence", "getSequence", "getLastLedgerSequence", "getSigningPubKey", "getTakerGets", "getTakerPays", "()Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;", "getTransactionType", "getTxnSignature", "getHash", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TakerPays;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;", "equals", "", "other", "hashCode", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TransactionJson {
        @Nullable
        private final String Account;
        @Nullable
        private final Long Expiration;
        @Nullable
        private final String Fee;
        @Nullable
        private final Integer Flags;
        @Nullable
        private final Long LastLedgerSequence;
        @Nullable
        private final Long OfferSequence;
        @Nullable
        private final Long Sequence;
        @Nullable
        private final String SigningPubKey;
        @Nullable
        private final String TakerGets;
        @Nullable
        private final TakerPays TakerPays;
        @Nullable
        private final String TransactionType;
        @Nullable
        private final String TxnSignature;
        @NotNull
        private final String hash;

        public TransactionJson(@Nullable String str, @Nullable Long l2, @Nullable String str2, @Nullable Integer num, @Nullable Long l3, @Nullable Long l4, @Nullable Long l5, @Nullable String str3, @Nullable String str4, @Nullable TakerPays takerPays, @Nullable String str5, @Nullable String str6, @NotNull String str7) {
            Intrinsics.checkNotNullParameter(str7, "hash");
            this.Account = str;
            this.Expiration = l2;
            this.Fee = str2;
            this.Flags = num;
            this.OfferSequence = l3;
            this.Sequence = l4;
            this.LastLedgerSequence = l5;
            this.SigningPubKey = str3;
            this.TakerGets = str4;
            this.TakerPays = takerPays;
            this.TransactionType = str5;
            this.TxnSignature = str6;
            this.hash = str7;
        }

        public static /* synthetic */ TransactionJson copy$default(TransactionJson transactionJson, String str, Long l2, String str2, Integer num, Long l3, Long l4, Long l5, String str3, String str4, TakerPays takerPays, String str5, String str6, String str7, int i3, Object obj) {
            TransactionJson transactionJson2 = transactionJson;
            int i4 = i3;
            return transactionJson.copy((i4 & 1) != 0 ? transactionJson2.Account : str, (i4 & 2) != 0 ? transactionJson2.Expiration : l2, (i4 & 4) != 0 ? transactionJson2.Fee : str2, (i4 & 8) != 0 ? transactionJson2.Flags : num, (i4 & 16) != 0 ? transactionJson2.OfferSequence : l3, (i4 & 32) != 0 ? transactionJson2.Sequence : l4, (i4 & 64) != 0 ? transactionJson2.LastLedgerSequence : l5, (i4 & 128) != 0 ? transactionJson2.SigningPubKey : str3, (i4 & 256) != 0 ? transactionJson2.TakerGets : str4, (i4 & 512) != 0 ? transactionJson2.TakerPays : takerPays, (i4 & 1024) != 0 ? transactionJson2.TransactionType : str5, (i4 & 2048) != 0 ? transactionJson2.TxnSignature : str6, (i4 & 4096) != 0 ? transactionJson2.hash : str7);
        }

        @Nullable
        public final String component1() {
            return this.Account;
        }

        @Nullable
        public final TakerPays component10() {
            return this.TakerPays;
        }

        @Nullable
        public final String component11() {
            return this.TransactionType;
        }

        @Nullable
        public final String component12() {
            return this.TxnSignature;
        }

        @NotNull
        public final String component13() {
            return this.hash;
        }

        @Nullable
        public final Long component2() {
            return this.Expiration;
        }

        @Nullable
        public final String component3() {
            return this.Fee;
        }

        @Nullable
        public final Integer component4() {
            return this.Flags;
        }

        @Nullable
        public final Long component5() {
            return this.OfferSequence;
        }

        @Nullable
        public final Long component6() {
            return this.Sequence;
        }

        @Nullable
        public final Long component7() {
            return this.LastLedgerSequence;
        }

        @Nullable
        public final String component8() {
            return this.SigningPubKey;
        }

        @Nullable
        public final String component9() {
            return this.TakerGets;
        }

        @NotNull
        public final TransactionJson copy(@Nullable String str, @Nullable Long l2, @Nullable String str2, @Nullable Integer num, @Nullable Long l3, @Nullable Long l4, @Nullable Long l5, @Nullable String str3, @Nullable String str4, @Nullable TakerPays takerPays, @Nullable String str5, @Nullable String str6, @NotNull String str7) {
            String str8 = str7;
            Intrinsics.checkNotNullParameter(str8, "hash");
            return new TransactionJson(str, l2, str2, num, l3, l4, l5, str3, str4, takerPays, str5, str6, str8);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TransactionJson)) {
                return false;
            }
            TransactionJson transactionJson = (TransactionJson) obj;
            return Intrinsics.areEqual((Object) this.Account, (Object) transactionJson.Account) && Intrinsics.areEqual((Object) this.Expiration, (Object) transactionJson.Expiration) && Intrinsics.areEqual((Object) this.Fee, (Object) transactionJson.Fee) && Intrinsics.areEqual((Object) this.Flags, (Object) transactionJson.Flags) && Intrinsics.areEqual((Object) this.OfferSequence, (Object) transactionJson.OfferSequence) && Intrinsics.areEqual((Object) this.Sequence, (Object) transactionJson.Sequence) && Intrinsics.areEqual((Object) this.LastLedgerSequence, (Object) transactionJson.LastLedgerSequence) && Intrinsics.areEqual((Object) this.SigningPubKey, (Object) transactionJson.SigningPubKey) && Intrinsics.areEqual((Object) this.TakerGets, (Object) transactionJson.TakerGets) && Intrinsics.areEqual((Object) this.TakerPays, (Object) transactionJson.TakerPays) && Intrinsics.areEqual((Object) this.TransactionType, (Object) transactionJson.TransactionType) && Intrinsics.areEqual((Object) this.TxnSignature, (Object) transactionJson.TxnSignature) && Intrinsics.areEqual((Object) this.hash, (Object) transactionJson.hash);
        }

        @Nullable
        public final String getAccount() {
            return this.Account;
        }

        @Nullable
        public final Long getExpiration() {
            return this.Expiration;
        }

        @Nullable
        public final String getFee() {
            return this.Fee;
        }

        @Nullable
        public final Integer getFlags() {
            return this.Flags;
        }

        @NotNull
        public final String getHash() {
            return this.hash;
        }

        @Nullable
        public final Long getLastLedgerSequence() {
            return this.LastLedgerSequence;
        }

        @Nullable
        public final Long getOfferSequence() {
            return this.OfferSequence;
        }

        @Nullable
        public final Long getSequence() {
            return this.Sequence;
        }

        @Nullable
        public final String getSigningPubKey() {
            return this.SigningPubKey;
        }

        @Nullable
        public final String getTakerGets() {
            return this.TakerGets;
        }

        @Nullable
        public final TakerPays getTakerPays() {
            return this.TakerPays;
        }

        @Nullable
        public final String getTransactionType() {
            return this.TransactionType;
        }

        @Nullable
        public final String getTxnSignature() {
            return this.TxnSignature;
        }

        public int hashCode() {
            String str = this.Account;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            Long l2 = this.Expiration;
            int hashCode2 = (hashCode + (l2 == null ? 0 : l2.hashCode())) * 31;
            String str2 = this.Fee;
            int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num = this.Flags;
            int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
            Long l3 = this.OfferSequence;
            int hashCode5 = (hashCode4 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.Sequence;
            int hashCode6 = (hashCode5 + (l4 == null ? 0 : l4.hashCode())) * 31;
            Long l5 = this.LastLedgerSequence;
            int hashCode7 = (hashCode6 + (l5 == null ? 0 : l5.hashCode())) * 31;
            String str3 = this.SigningPubKey;
            int hashCode8 = (hashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.TakerGets;
            int hashCode9 = (hashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
            TakerPays takerPays = this.TakerPays;
            int hashCode10 = (hashCode9 + (takerPays == null ? 0 : takerPays.hashCode())) * 31;
            String str5 = this.TransactionType;
            int hashCode11 = (hashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.TxnSignature;
            if (str6 != null) {
                i3 = str6.hashCode();
            }
            return this.hash.hashCode() + ((hashCode11 + i3) * 31);
        }

        @NotNull
        public String toString() {
            String str = this.Account;
            Long l2 = this.Expiration;
            String str2 = this.Fee;
            Integer num = this.Flags;
            Long l3 = this.OfferSequence;
            Long l4 = this.Sequence;
            Long l5 = this.LastLedgerSequence;
            String str3 = this.SigningPubKey;
            String str4 = this.TakerGets;
            TakerPays takerPays = this.TakerPays;
            String str5 = this.TransactionType;
            String str6 = this.TxnSignature;
            String str7 = this.hash;
            StringBuilder sb = new StringBuilder("TransactionJson(Account=");
            sb.append(str);
            sb.append(", Expiration=");
            sb.append(l2);
            sb.append(", Fee=");
            sb.append(str2);
            sb.append(", Flags=");
            sb.append(num);
            sb.append(", OfferSequence=");
            sb.append(l3);
            sb.append(", Sequence=");
            sb.append(l4);
            sb.append(", LastLedgerSequence=");
            sb.append(l5);
            sb.append(", SigningPubKey=");
            sb.append(str3);
            sb.append(", TakerGets=");
            sb.append(str4);
            sb.append(", TakerPays=");
            sb.append(takerPays);
            sb.append(", TransactionType=");
            b.w(sb, str5, ", TxnSignature=", str6, ", hash=");
            return a.n(sb, str7, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionWrapper;", "", "tx_json", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;", "<init>", "(Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;)V", "getTx_json", "()Lcom/reown/sign/engine/model/tvf/XRPLSignTransaction$TransactionJson;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TransactionWrapper {
        @NotNull
        private final TransactionJson tx_json;

        public TransactionWrapper(@NotNull TransactionJson transactionJson) {
            Intrinsics.checkNotNullParameter(transactionJson, "tx_json");
            this.tx_json = transactionJson;
        }

        public static /* synthetic */ TransactionWrapper copy$default(TransactionWrapper transactionWrapper, TransactionJson transactionJson, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                transactionJson = transactionWrapper.tx_json;
            }
            return transactionWrapper.copy(transactionJson);
        }

        @NotNull
        public final TransactionJson component1() {
            return this.tx_json;
        }

        @NotNull
        public final TransactionWrapper copy(@NotNull TransactionJson transactionJson) {
            Intrinsics.checkNotNullParameter(transactionJson, "tx_json");
            return new TransactionWrapper(transactionJson);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TransactionWrapper) && Intrinsics.areEqual((Object) this.tx_json, (Object) ((TransactionWrapper) obj).tx_json);
        }

        @NotNull
        public final TransactionJson getTx_json() {
            return this.tx_json;
        }

        public int hashCode() {
            return this.tx_json.hashCode();
        }

        @NotNull
        public String toString() {
            TransactionJson transactionJson = this.tx_json;
            return "TransactionWrapper(tx_json=" + transactionJson + ")";
        }
    }

    private XRPLSignTransaction() {
    }
}
