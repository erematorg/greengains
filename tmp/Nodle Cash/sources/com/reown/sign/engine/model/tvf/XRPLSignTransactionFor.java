package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.constraintlayout.core.state.b;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor;", "", "<init>", "()V", "TransactionWrapper", "TransactionJson", "SignerWrapper", "Signer", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class XRPLSignTransactionFor {
    @NotNull
    public static final XRPLSignTransactionFor INSTANCE = new XRPLSignTransactionFor();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$Signer;", "", "Account", "", "SigningPubKey", "TxnSignature", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getSigningPubKey", "getTxnSignature", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Signer {
        @Nullable
        private final String Account;
        @Nullable
        private final String SigningPubKey;
        @Nullable
        private final String TxnSignature;

        public Signer(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.Account = str;
            this.SigningPubKey = str2;
            this.TxnSignature = str3;
        }

        public static /* synthetic */ Signer copy$default(Signer signer, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = signer.Account;
            }
            if ((i3 & 2) != 0) {
                str2 = signer.SigningPubKey;
            }
            if ((i3 & 4) != 0) {
                str3 = signer.TxnSignature;
            }
            return signer.copy(str, str2, str3);
        }

        @Nullable
        public final String component1() {
            return this.Account;
        }

        @Nullable
        public final String component2() {
            return this.SigningPubKey;
        }

        @Nullable
        public final String component3() {
            return this.TxnSignature;
        }

        @NotNull
        public final Signer copy(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            return new Signer(str, str2, str3);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Signer)) {
                return false;
            }
            Signer signer = (Signer) obj;
            return Intrinsics.areEqual((Object) this.Account, (Object) signer.Account) && Intrinsics.areEqual((Object) this.SigningPubKey, (Object) signer.SigningPubKey) && Intrinsics.areEqual((Object) this.TxnSignature, (Object) signer.TxnSignature);
        }

        @Nullable
        public final String getAccount() {
            return this.Account;
        }

        @Nullable
        public final String getSigningPubKey() {
            return this.SigningPubKey;
        }

        @Nullable
        public final String getTxnSignature() {
            return this.TxnSignature;
        }

        public int hashCode() {
            String str = this.Account;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.SigningPubKey;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.TxnSignature;
            if (str3 != null) {
                i3 = str3.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.Account;
            String str2 = this.SigningPubKey;
            return a.n(C0118y.l("Signer(Account=", str, ", SigningPubKey=", str2, ", TxnSignature="), this.TxnSignature, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$SignerWrapper;", "", "Signer", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$Signer;", "<init>", "(Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$Signer;)V", "getSigner", "()Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$Signer;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SignerWrapper {
        @Nullable
        private final Signer Signer;

        public SignerWrapper(@Nullable Signer signer) {
            this.Signer = signer;
        }

        public static /* synthetic */ SignerWrapper copy$default(SignerWrapper signerWrapper, Signer signer, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                signer = signerWrapper.Signer;
            }
            return signerWrapper.copy(signer);
        }

        @Nullable
        public final Signer component1() {
            return this.Signer;
        }

        @NotNull
        public final SignerWrapper copy(@Nullable Signer signer) {
            return new SignerWrapper(signer);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SignerWrapper) && Intrinsics.areEqual((Object) this.Signer, (Object) ((SignerWrapper) obj).Signer);
        }

        @Nullable
        public final Signer getSigner() {
            return this.Signer;
        }

        public int hashCode() {
            Signer signer = this.Signer;
            if (signer == null) {
                return 0;
            }
            return signer.hashCode();
        }

        @NotNull
        public String toString() {
            Signer signer = this.Signer;
            return "SignerWrapper(Signer=" + signer + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001By\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010)\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u0001\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u001aR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014¨\u00065"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;", "", "Account", "", "TransactionType", "Amount", "Destination", "Fee", "Flags", "", "LastLedgerSequence", "Sequence", "Signers", "", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$SignerWrapper;", "SigningPubKey", "hash", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getTransactionType", "getAmount", "getDestination", "getFee", "getFlags", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLastLedgerSequence", "getSequence", "getSigners", "()Ljava/util/List;", "getSigningPubKey", "getHash", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TransactionJson {
        @Nullable
        private final String Account;
        @Nullable
        private final String Amount;
        @Nullable
        private final String Destination;
        @Nullable
        private final String Fee;
        @Nullable
        private final Long Flags;
        @Nullable
        private final Long LastLedgerSequence;
        @Nullable
        private final Long Sequence;
        @Nullable
        private final List<SignerWrapper> Signers;
        @Nullable
        private final String SigningPubKey;
        @Nullable
        private final String TransactionType;
        @NotNull
        private final String hash;

        public TransactionJson(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable List<SignerWrapper> list, @Nullable String str6, @NotNull String str7) {
            Intrinsics.checkNotNullParameter(str7, "hash");
            this.Account = str;
            this.TransactionType = str2;
            this.Amount = str3;
            this.Destination = str4;
            this.Fee = str5;
            this.Flags = l2;
            this.LastLedgerSequence = l3;
            this.Sequence = l4;
            this.Signers = list;
            this.SigningPubKey = str6;
            this.hash = str7;
        }

        public static /* synthetic */ TransactionJson copy$default(TransactionJson transactionJson, String str, String str2, String str3, String str4, String str5, Long l2, Long l3, Long l4, List list, String str6, String str7, int i3, Object obj) {
            TransactionJson transactionJson2 = transactionJson;
            int i4 = i3;
            return transactionJson.copy((i4 & 1) != 0 ? transactionJson2.Account : str, (i4 & 2) != 0 ? transactionJson2.TransactionType : str2, (i4 & 4) != 0 ? transactionJson2.Amount : str3, (i4 & 8) != 0 ? transactionJson2.Destination : str4, (i4 & 16) != 0 ? transactionJson2.Fee : str5, (i4 & 32) != 0 ? transactionJson2.Flags : l2, (i4 & 64) != 0 ? transactionJson2.LastLedgerSequence : l3, (i4 & 128) != 0 ? transactionJson2.Sequence : l4, (i4 & 256) != 0 ? transactionJson2.Signers : list, (i4 & 512) != 0 ? transactionJson2.SigningPubKey : str6, (i4 & 1024) != 0 ? transactionJson2.hash : str7);
        }

        @Nullable
        public final String component1() {
            return this.Account;
        }

        @Nullable
        public final String component10() {
            return this.SigningPubKey;
        }

        @NotNull
        public final String component11() {
            return this.hash;
        }

        @Nullable
        public final String component2() {
            return this.TransactionType;
        }

        @Nullable
        public final String component3() {
            return this.Amount;
        }

        @Nullable
        public final String component4() {
            return this.Destination;
        }

        @Nullable
        public final String component5() {
            return this.Fee;
        }

        @Nullable
        public final Long component6() {
            return this.Flags;
        }

        @Nullable
        public final Long component7() {
            return this.LastLedgerSequence;
        }

        @Nullable
        public final Long component8() {
            return this.Sequence;
        }

        @Nullable
        public final List<SignerWrapper> component9() {
            return this.Signers;
        }

        @NotNull
        public final TransactionJson copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable List<SignerWrapper> list, @Nullable String str6, @NotNull String str7) {
            String str8 = str7;
            Intrinsics.checkNotNullParameter(str8, "hash");
            return new TransactionJson(str, str2, str3, str4, str5, l2, l3, l4, list, str6, str8);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TransactionJson)) {
                return false;
            }
            TransactionJson transactionJson = (TransactionJson) obj;
            return Intrinsics.areEqual((Object) this.Account, (Object) transactionJson.Account) && Intrinsics.areEqual((Object) this.TransactionType, (Object) transactionJson.TransactionType) && Intrinsics.areEqual((Object) this.Amount, (Object) transactionJson.Amount) && Intrinsics.areEqual((Object) this.Destination, (Object) transactionJson.Destination) && Intrinsics.areEqual((Object) this.Fee, (Object) transactionJson.Fee) && Intrinsics.areEqual((Object) this.Flags, (Object) transactionJson.Flags) && Intrinsics.areEqual((Object) this.LastLedgerSequence, (Object) transactionJson.LastLedgerSequence) && Intrinsics.areEqual((Object) this.Sequence, (Object) transactionJson.Sequence) && Intrinsics.areEqual((Object) this.Signers, (Object) transactionJson.Signers) && Intrinsics.areEqual((Object) this.SigningPubKey, (Object) transactionJson.SigningPubKey) && Intrinsics.areEqual((Object) this.hash, (Object) transactionJson.hash);
        }

        @Nullable
        public final String getAccount() {
            return this.Account;
        }

        @Nullable
        public final String getAmount() {
            return this.Amount;
        }

        @Nullable
        public final String getDestination() {
            return this.Destination;
        }

        @Nullable
        public final String getFee() {
            return this.Fee;
        }

        @Nullable
        public final Long getFlags() {
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
        public final Long getSequence() {
            return this.Sequence;
        }

        @Nullable
        public final List<SignerWrapper> getSigners() {
            return this.Signers;
        }

        @Nullable
        public final String getSigningPubKey() {
            return this.SigningPubKey;
        }

        @Nullable
        public final String getTransactionType() {
            return this.TransactionType;
        }

        public int hashCode() {
            String str = this.Account;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.TransactionType;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.Amount;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.Destination;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.Fee;
            int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Long l2 = this.Flags;
            int hashCode6 = (hashCode5 + (l2 == null ? 0 : l2.hashCode())) * 31;
            Long l3 = this.LastLedgerSequence;
            int hashCode7 = (hashCode6 + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.Sequence;
            int hashCode8 = (hashCode7 + (l4 == null ? 0 : l4.hashCode())) * 31;
            List<SignerWrapper> list = this.Signers;
            int hashCode9 = (hashCode8 + (list == null ? 0 : list.hashCode())) * 31;
            String str6 = this.SigningPubKey;
            if (str6 != null) {
                i3 = str6.hashCode();
            }
            return this.hash.hashCode() + ((hashCode9 + i3) * 31);
        }

        @NotNull
        public String toString() {
            String str = this.Account;
            String str2 = this.TransactionType;
            String str3 = this.Amount;
            String str4 = this.Destination;
            String str5 = this.Fee;
            Long l2 = this.Flags;
            Long l3 = this.LastLedgerSequence;
            Long l4 = this.Sequence;
            List<SignerWrapper> list = this.Signers;
            String str6 = this.SigningPubKey;
            String str7 = this.hash;
            StringBuilder l5 = C0118y.l("TransactionJson(Account=", str, ", TransactionType=", str2, ", Amount=");
            b.w(l5, str3, ", Destination=", str4, ", Fee=");
            l5.append(str5);
            l5.append(", Flags=");
            l5.append(l2);
            l5.append(", LastLedgerSequence=");
            l5.append(l3);
            l5.append(", Sequence=");
            l5.append(l4);
            l5.append(", Signers=");
            l5.append(list);
            l5.append(", SigningPubKey=");
            l5.append(str6);
            l5.append(", hash=");
            return a.n(l5, str7, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionWrapper;", "", "tx_json", "Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;", "<init>", "(Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;)V", "getTx_json", "()Lcom/reown/sign/engine/model/tvf/XRPLSignTransactionFor$TransactionJson;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class TransactionWrapper {
        @NotNull
        private final TransactionJson tx_json;

        public TransactionWrapper(@NotNull TransactionJson transactionJson) {
            Intrinsics.checkNotNullParameter(transactionJson, "tx_json");
            this.tx_json = transactionJson;
        }

        @NotNull
        public final TransactionJson getTx_json() {
            return this.tx_json;
        }
    }

    private XRPLSignTransactionFor() {
    }
}
