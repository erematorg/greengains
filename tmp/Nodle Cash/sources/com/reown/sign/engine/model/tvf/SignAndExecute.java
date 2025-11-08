package com.reown.sign.engine.model.tvf;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0007\u0004\u0005\u0006\u0007\b\t\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u000b"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute;", "", "<init>", "()V", "SuiTransactionResponse", "Effects", "Status", "GasUsed", "Event", "CoinBalanceChange", "ObjectChange", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignAndExecute {
    @NotNull
    public static final SignAndExecute INSTANCE = new SignAndExecute();

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$CoinBalanceChange;", "", "owner", "", "coin_type", "amount", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getOwner", "()Ljava/lang/String;", "getCoin_type", "getAmount", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/sign/engine/model/tvf/SignAndExecute$CoinBalanceChange;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CoinBalanceChange {
        @Nullable
        private final Long amount;
        @Nullable
        private final String coin_type;
        @Nullable
        private final String owner;

        public CoinBalanceChange(@Nullable String str, @Nullable String str2, @Nullable Long l2) {
            this.owner = str;
            this.coin_type = str2;
            this.amount = l2;
        }

        public static /* synthetic */ CoinBalanceChange copy$default(CoinBalanceChange coinBalanceChange, String str, String str2, Long l2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = coinBalanceChange.owner;
            }
            if ((i3 & 2) != 0) {
                str2 = coinBalanceChange.coin_type;
            }
            if ((i3 & 4) != 0) {
                l2 = coinBalanceChange.amount;
            }
            return coinBalanceChange.copy(str, str2, l2);
        }

        @Nullable
        public final String component1() {
            return this.owner;
        }

        @Nullable
        public final String component2() {
            return this.coin_type;
        }

        @Nullable
        public final Long component3() {
            return this.amount;
        }

        @NotNull
        public final CoinBalanceChange copy(@Nullable String str, @Nullable String str2, @Nullable Long l2) {
            return new CoinBalanceChange(str, str2, l2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CoinBalanceChange)) {
                return false;
            }
            CoinBalanceChange coinBalanceChange = (CoinBalanceChange) obj;
            return Intrinsics.areEqual((Object) this.owner, (Object) coinBalanceChange.owner) && Intrinsics.areEqual((Object) this.coin_type, (Object) coinBalanceChange.coin_type) && Intrinsics.areEqual((Object) this.amount, (Object) coinBalanceChange.amount);
        }

        @Nullable
        public final Long getAmount() {
            return this.amount;
        }

        @Nullable
        public final String getCoin_type() {
            return this.coin_type;
        }

        @Nullable
        public final String getOwner() {
            return this.owner;
        }

        public int hashCode() {
            String str = this.owner;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.coin_type;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            Long l2 = this.amount;
            if (l2 != null) {
                i3 = l2.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.owner;
            String str2 = this.coin_type;
            Long l2 = this.amount;
            StringBuilder l3 = C0118y.l("CoinBalanceChange(owner=", str, ", coin_type=", str2, ", amount=");
            l3.append(l2);
            l3.append(")");
            return l3.toString();
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;", "", "status", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Status;", "gas_used", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;", "<init>", "(Lcom/reown/sign/engine/model/tvf/SignAndExecute$Status;Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;)V", "getStatus", "()Lcom/reown/sign/engine/model/tvf/SignAndExecute$Status;", "getGas_used", "()Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Effects {
        @Nullable
        private final GasUsed gas_used;
        @Nullable
        private final Status status;

        public Effects(@Nullable Status status2, @Nullable GasUsed gasUsed) {
            this.status = status2;
            this.gas_used = gasUsed;
        }

        public static /* synthetic */ Effects copy$default(Effects effects, Status status2, GasUsed gasUsed, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                status2 = effects.status;
            }
            if ((i3 & 2) != 0) {
                gasUsed = effects.gas_used;
            }
            return effects.copy(status2, gasUsed);
        }

        @Nullable
        public final Status component1() {
            return this.status;
        }

        @Nullable
        public final GasUsed component2() {
            return this.gas_used;
        }

        @NotNull
        public final Effects copy(@Nullable Status status2, @Nullable GasUsed gasUsed) {
            return new Effects(status2, gasUsed);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Effects)) {
                return false;
            }
            Effects effects = (Effects) obj;
            return Intrinsics.areEqual((Object) this.status, (Object) effects.status) && Intrinsics.areEqual((Object) this.gas_used, (Object) effects.gas_used);
        }

        @Nullable
        public final GasUsed getGas_used() {
            return this.gas_used;
        }

        @Nullable
        public final Status getStatus() {
            return this.status;
        }

        public int hashCode() {
            Status status2 = this.status;
            int i3 = 0;
            int hashCode = (status2 == null ? 0 : status2.hashCode()) * 31;
            GasUsed gasUsed = this.gas_used;
            if (gasUsed != null) {
                i3 = gasUsed.hashCode();
            }
            return hashCode + i3;
        }

        @NotNull
        public String toString() {
            Status status2 = this.status;
            GasUsed gasUsed = this.gas_used;
            return "Effects(status=" + status2 + ", gas_used=" + gasUsed + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$Event;", "", "coinBalanceChange", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$CoinBalanceChange;", "<init>", "(Lcom/reown/sign/engine/model/tvf/SignAndExecute$CoinBalanceChange;)V", "getCoinBalanceChange", "()Lcom/reown/sign/engine/model/tvf/SignAndExecute$CoinBalanceChange;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Event {
        @Nullable
        private final CoinBalanceChange coinBalanceChange;

        public Event(@Nullable CoinBalanceChange coinBalanceChange2) {
            this.coinBalanceChange = coinBalanceChange2;
        }

        public static /* synthetic */ Event copy$default(Event event, CoinBalanceChange coinBalanceChange2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                coinBalanceChange2 = event.coinBalanceChange;
            }
            return event.copy(coinBalanceChange2);
        }

        @Nullable
        public final CoinBalanceChange component1() {
            return this.coinBalanceChange;
        }

        @NotNull
        public final Event copy(@Nullable CoinBalanceChange coinBalanceChange2) {
            return new Event(coinBalanceChange2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Event) && Intrinsics.areEqual((Object) this.coinBalanceChange, (Object) ((Event) obj).coinBalanceChange);
        }

        @Nullable
        public final CoinBalanceChange getCoinBalanceChange() {
            return this.coinBalanceChange;
        }

        public int hashCode() {
            CoinBalanceChange coinBalanceChange2 = this.coinBalanceChange;
            if (coinBalanceChange2 == null) {
                return 0;
            }
            return coinBalanceChange2.hashCode();
        }

        @NotNull
        public String toString() {
            CoinBalanceChange coinBalanceChange2 = this.coinBalanceChange;
            return "Event(coinBalanceChange=" + coinBalanceChange2 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;", "", "computation_cost", "", "storage_cost", "storage_rebate", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "getComputation_cost", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getStorage_cost", "getStorage_rebate", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lcom/reown/sign/engine/model/tvf/SignAndExecute$GasUsed;", "equals", "", "other", "hashCode", "", "toString", "", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class GasUsed {
        @Nullable
        private final Long computation_cost;
        @Nullable
        private final Long storage_cost;
        @Nullable
        private final Long storage_rebate;

        public GasUsed(@Nullable Long l2, @Nullable Long l3, @Nullable Long l4) {
            this.computation_cost = l2;
            this.storage_cost = l3;
            this.storage_rebate = l4;
        }

        public static /* synthetic */ GasUsed copy$default(GasUsed gasUsed, Long l2, Long l3, Long l4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                l2 = gasUsed.computation_cost;
            }
            if ((i3 & 2) != 0) {
                l3 = gasUsed.storage_cost;
            }
            if ((i3 & 4) != 0) {
                l4 = gasUsed.storage_rebate;
            }
            return gasUsed.copy(l2, l3, l4);
        }

        @Nullable
        public final Long component1() {
            return this.computation_cost;
        }

        @Nullable
        public final Long component2() {
            return this.storage_cost;
        }

        @Nullable
        public final Long component3() {
            return this.storage_rebate;
        }

        @NotNull
        public final GasUsed copy(@Nullable Long l2, @Nullable Long l3, @Nullable Long l4) {
            return new GasUsed(l2, l3, l4);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GasUsed)) {
                return false;
            }
            GasUsed gasUsed = (GasUsed) obj;
            return Intrinsics.areEqual((Object) this.computation_cost, (Object) gasUsed.computation_cost) && Intrinsics.areEqual((Object) this.storage_cost, (Object) gasUsed.storage_cost) && Intrinsics.areEqual((Object) this.storage_rebate, (Object) gasUsed.storage_rebate);
        }

        @Nullable
        public final Long getComputation_cost() {
            return this.computation_cost;
        }

        @Nullable
        public final Long getStorage_cost() {
            return this.storage_cost;
        }

        @Nullable
        public final Long getStorage_rebate() {
            return this.storage_rebate;
        }

        public int hashCode() {
            Long l2 = this.computation_cost;
            int i3 = 0;
            int hashCode = (l2 == null ? 0 : l2.hashCode()) * 31;
            Long l3 = this.storage_cost;
            int hashCode2 = (hashCode + (l3 == null ? 0 : l3.hashCode())) * 31;
            Long l4 = this.storage_rebate;
            if (l4 != null) {
                i3 = l4.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            Long l2 = this.computation_cost;
            Long l3 = this.storage_cost;
            Long l4 = this.storage_rebate;
            return "GasUsed(computation_cost=" + l2 + ", storage_cost=" + l3 + ", storage_rebate=" + l4 + ")";
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ>\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$ObjectChange;", "", "type", "", "object_id", "owner", "version", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getType", "()Ljava/lang/String;", "getObject_id", "getOwner", "getVersion", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/reown/sign/engine/model/tvf/SignAndExecute$ObjectChange;", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ObjectChange {
        @Nullable
        private final String object_id;
        @Nullable
        private final String owner;
        @Nullable
        private final String type;
        @Nullable
        private final Long version;

        public ObjectChange(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l2) {
            this.type = str;
            this.object_id = str2;
            this.owner = str3;
            this.version = l2;
        }

        public static /* synthetic */ ObjectChange copy$default(ObjectChange objectChange, String str, String str2, String str3, Long l2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = objectChange.type;
            }
            if ((i3 & 2) != 0) {
                str2 = objectChange.object_id;
            }
            if ((i3 & 4) != 0) {
                str3 = objectChange.owner;
            }
            if ((i3 & 8) != 0) {
                l2 = objectChange.version;
            }
            return objectChange.copy(str, str2, str3, l2);
        }

        @Nullable
        public final String component1() {
            return this.type;
        }

        @Nullable
        public final String component2() {
            return this.object_id;
        }

        @Nullable
        public final String component3() {
            return this.owner;
        }

        @Nullable
        public final Long component4() {
            return this.version;
        }

        @NotNull
        public final ObjectChange copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l2) {
            return new ObjectChange(str, str2, str3, l2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ObjectChange)) {
                return false;
            }
            ObjectChange objectChange = (ObjectChange) obj;
            return Intrinsics.areEqual((Object) this.type, (Object) objectChange.type) && Intrinsics.areEqual((Object) this.object_id, (Object) objectChange.object_id) && Intrinsics.areEqual((Object) this.owner, (Object) objectChange.owner) && Intrinsics.areEqual((Object) this.version, (Object) objectChange.version);
        }

        @Nullable
        public final String getObject_id() {
            return this.object_id;
        }

        @Nullable
        public final String getOwner() {
            return this.owner;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @Nullable
        public final Long getVersion() {
            return this.version;
        }

        public int hashCode() {
            String str = this.type;
            int i3 = 0;
            int hashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.object_id;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.owner;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Long l2 = this.version;
            if (l2 != null) {
                i3 = l2.hashCode();
            }
            return hashCode3 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.type;
            String str2 = this.object_id;
            String str3 = this.owner;
            Long l2 = this.version;
            StringBuilder l3 = C0118y.l("ObjectChange(type=", str, ", object_id=", str2, ", owner=");
            l3.append(str3);
            l3.append(", version=");
            l3.append(l2);
            l3.append(")");
            return l3.toString();
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$Status;", "", "status", "", "<init>", "(Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Status {
        @Nullable
        private final String status;

        public Status(@Nullable String str) {
            this.status = str;
        }

        public static /* synthetic */ Status copy$default(Status status2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = status2.status;
            }
            return status2.copy(str);
        }

        @Nullable
        public final String component1() {
            return this.status;
        }

        @NotNull
        public final Status copy(@Nullable String str) {
            return new Status(str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Status) && Intrinsics.areEqual((Object) this.status, (Object) ((Status) obj).status);
        }

        @Nullable
        public final String getStatus() {
            return this.status;
        }

        public int hashCode() {
            String str = this.status;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return a.l("Status(status=", this.status, ")");
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0017JT\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0013\u0010 \u001a\u00020\f2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/reown/sign/engine/model/tvf/SignAndExecute$SuiTransactionResponse;", "", "digest", "", "effects", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;", "events", "", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$Event;", "object_changes", "Lcom/reown/sign/engine/model/tvf/SignAndExecute$ObjectChange;", "confirmed_local_execution", "", "<init>", "(Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;)V", "getDigest", "()Ljava/lang/String;", "getEffects", "()Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;", "getEvents", "()Ljava/util/List;", "getObject_changes", "getConfirmed_local_execution", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Lcom/reown/sign/engine/model/tvf/SignAndExecute$Effects;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;)Lcom/reown/sign/engine/model/tvf/SignAndExecute$SuiTransactionResponse;", "equals", "other", "hashCode", "", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SuiTransactionResponse {
        @Nullable
        private final Boolean confirmed_local_execution;
        @NotNull
        private final String digest;
        @Nullable
        private final Effects effects;
        @Nullable
        private final List<Event> events;
        @Nullable
        private final List<ObjectChange> object_changes;

        public SuiTransactionResponse(@NotNull String str, @Nullable Effects effects2, @Nullable List<Event> list, @Nullable List<ObjectChange> list2, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "digest");
            this.digest = str;
            this.effects = effects2;
            this.events = list;
            this.object_changes = list2;
            this.confirmed_local_execution = bool;
        }

        public static /* synthetic */ SuiTransactionResponse copy$default(SuiTransactionResponse suiTransactionResponse, String str, Effects effects2, List<Event> list, List<ObjectChange> list2, Boolean bool, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = suiTransactionResponse.digest;
            }
            if ((i3 & 2) != 0) {
                effects2 = suiTransactionResponse.effects;
            }
            Effects effects3 = effects2;
            if ((i3 & 4) != 0) {
                list = suiTransactionResponse.events;
            }
            List<Event> list3 = list;
            if ((i3 & 8) != 0) {
                list2 = suiTransactionResponse.object_changes;
            }
            List<ObjectChange> list4 = list2;
            if ((i3 & 16) != 0) {
                bool = suiTransactionResponse.confirmed_local_execution;
            }
            return suiTransactionResponse.copy(str, effects3, list3, list4, bool);
        }

        @NotNull
        public final String component1() {
            return this.digest;
        }

        @Nullable
        public final Effects component2() {
            return this.effects;
        }

        @Nullable
        public final List<Event> component3() {
            return this.events;
        }

        @Nullable
        public final List<ObjectChange> component4() {
            return this.object_changes;
        }

        @Nullable
        public final Boolean component5() {
            return this.confirmed_local_execution;
        }

        @NotNull
        public final SuiTransactionResponse copy(@NotNull String str, @Nullable Effects effects2, @Nullable List<Event> list, @Nullable List<ObjectChange> list2, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(str, "digest");
            return new SuiTransactionResponse(str, effects2, list, list2, bool);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SuiTransactionResponse)) {
                return false;
            }
            SuiTransactionResponse suiTransactionResponse = (SuiTransactionResponse) obj;
            return Intrinsics.areEqual((Object) this.digest, (Object) suiTransactionResponse.digest) && Intrinsics.areEqual((Object) this.effects, (Object) suiTransactionResponse.effects) && Intrinsics.areEqual((Object) this.events, (Object) suiTransactionResponse.events) && Intrinsics.areEqual((Object) this.object_changes, (Object) suiTransactionResponse.object_changes) && Intrinsics.areEqual((Object) this.confirmed_local_execution, (Object) suiTransactionResponse.confirmed_local_execution);
        }

        @Nullable
        public final Boolean getConfirmed_local_execution() {
            return this.confirmed_local_execution;
        }

        @NotNull
        public final String getDigest() {
            return this.digest;
        }

        @Nullable
        public final Effects getEffects() {
            return this.effects;
        }

        @Nullable
        public final List<Event> getEvents() {
            return this.events;
        }

        @Nullable
        public final List<ObjectChange> getObject_changes() {
            return this.object_changes;
        }

        public int hashCode() {
            int hashCode = this.digest.hashCode() * 31;
            Effects effects2 = this.effects;
            int i3 = 0;
            int hashCode2 = (hashCode + (effects2 == null ? 0 : effects2.hashCode())) * 31;
            List<Event> list = this.events;
            int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
            List<ObjectChange> list2 = this.object_changes;
            int hashCode4 = (hashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
            Boolean bool = this.confirmed_local_execution;
            if (bool != null) {
                i3 = bool.hashCode();
            }
            return hashCode4 + i3;
        }

        @NotNull
        public String toString() {
            String str = this.digest;
            Effects effects2 = this.effects;
            List<Event> list = this.events;
            List<ObjectChange> list2 = this.object_changes;
            Boolean bool = this.confirmed_local_execution;
            return "SuiTransactionResponse(digest=" + str + ", effects=" + effects2 + ", events=" + list + ", object_changes=" + list2 + ", confirmed_local_execution=" + bool + ")";
        }
    }

    private SignAndExecute() {
    }
}
