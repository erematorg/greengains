package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.MetaData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0002-.B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J¶\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0002\u0010\f\u001a\u0002\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001c¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\n0\rJ\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u001f0\tJ¶\u0002\u0010 \u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0002\u0010\f\u001a\u0002\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001c¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\n0\rJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\tJ¾\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000e2\u0002\u0010\f\u001a\u0002\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001c¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u0002H\n0\rJ\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\t2\u0006\u0010\u0011\u001a\u00020\u000eJ\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\u0006\u0010\u0011\u001a\u00020\u000eJO\u0010%\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010(J\u000e\u0010)\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u000eJ\u001d\u0010*\u001a\u00020&2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0002\u0010+J\u0016\u0010,\u001a\u00020&2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "MetaDataAdapter", "Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;)V", "getListOfPairing", "Lapp/cash/sqldelight/Query;", "T", "", "mapper", "Lkotlin/Function12;", "", "Lkotlin/ParameterName;", "name", "topic", "", "expiry", "relay_protocol", "relay_data", "uri", "methods", "", "is_proposal_received", "description", "url", "", "icons", "native", "Lcom/reown/android/sdk/storage/data/dao/GetListOfPairing;", "getListOfPairingsWithoutRequestReceived", "Lcom/reown/android/sdk/storage/data/dao/GetListOfPairingsWithoutRequestReceived;", "getPairingByTopic", "Lcom/reown/android/sdk/storage/data/dao/GetPairingByTopic;", "hasTopic", "insertOrAbortPairing", "", "is_active", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Boolean;)V", "deletePairing", "setRequestReceived", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "updateOrAbortExpiry", "GetPairingByTopicQuery", "HasTopicQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPairingQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PairingQueries.kt\ncom/reown/android/sdk/storage/data/dao/PairingQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,298:1\n1#2:299\n*E\n"})
public final class PairingQueries extends TransacterImpl {
    @NotNull
    private final MetaData.Adapter MetaDataAdapter;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PairingQueries$GetPairingByTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PairingQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetPairingByTopicQuery<T> extends Query<T> {
        final /* synthetic */ PairingQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetPairingByTopicQuery(@NotNull PairingQueries pairingQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = pairingQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetPairingByTopicQuery getPairingByTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getPairingByTopicQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"Pairing", "MetaData"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-308819346, "SELECT pd.topic, pd.expiry, pd.relay_protocol, pd.relay_data, pd.uri, pd.methods, pd.is_proposal_received, mdd_peer.name, mdd_peer.description, mdd_peer.url, mdd_peer.icons, mdd_peer.native\nFROM Pairing pd\n    LEFT JOIN MetaData mdd_peer ON pd.topic = mdd_peer.sequence_topic AND mdd_peer.type = \"PEER\"\nWHERE ? = topic", function1, 1, new c(this, 6));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"Pairing", "MetaData"}, listener);
        }

        @NotNull
        public String toString() {
            return "Pairing.sq:getPairingByTopic";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/PairingQueries$HasTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PairingQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class HasTopicQuery<T> extends Query<T> {
        final /* synthetic */ PairingQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HasTopicQuery(@NotNull PairingQueries pairingQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = pairingQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(HasTopicQuery hasTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, hasTopicQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"Pairing"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(844624749, "SELECT topic\nFROM Pairing\nWHERE ? = topic", function1, 1, new c(this, 7));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"Pairing"}, listener);
        }

        @NotNull
        public String toString() {
            return "Pairing.sq:hasTopic";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairingQueries(@NotNull SqlDriver sqlDriver, @NotNull MetaData.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "MetaDataAdapter");
        this.MetaDataAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deletePairing$lambda$12(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deletePairing$lambda$13(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Pairing");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getListOfPairing$lambda$1(Function12 function12, PairingQueries pairingQueries, SqlCursor sqlCursor) {
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        String string = sqlCursor2.getString(0);
        Intrinsics.checkNotNull(string);
        Long l2 = sqlCursor2.getLong(1);
        Intrinsics.checkNotNull(l2);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        Boolean bool = sqlCursor2.getBoolean(6);
        String string6 = sqlCursor2.getString(7);
        String string7 = sqlCursor2.getString(8);
        String string8 = sqlCursor2.getString(9);
        String string9 = sqlCursor2.getString(10);
        return function12.invoke(string, l2, string2, string3, string4, string5, bool, string6, string7, string8, string9 != null ? pairingQueries.MetaDataAdapter.getIconsAdapter().decode(string9) : null, sqlCursor2.getString(11));
    }

    /* access modifiers changed from: private */
    public static final GetListOfPairing getListOfPairing$lambda$2(String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List list, String str9) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "relay_protocol");
        String str11 = str4;
        Intrinsics.checkNotNullParameter(str11, "uri");
        String str12 = str5;
        Intrinsics.checkNotNullParameter(str12, "methods");
        return new GetListOfPairing(str, j2, str10, str3, str11, str12, bool, str6, str7, str8, list, str9);
    }

    /* access modifiers changed from: private */
    public static final Object getListOfPairingsWithoutRequestReceived$lambda$4(Function12 function12, PairingQueries pairingQueries, SqlCursor sqlCursor) {
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        String string = sqlCursor2.getString(0);
        Intrinsics.checkNotNull(string);
        Long l2 = sqlCursor2.getLong(1);
        Intrinsics.checkNotNull(l2);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        Boolean bool = sqlCursor2.getBoolean(6);
        String string6 = sqlCursor2.getString(7);
        String string7 = sqlCursor2.getString(8);
        String string8 = sqlCursor2.getString(9);
        String string9 = sqlCursor2.getString(10);
        return function12.invoke(string, l2, string2, string3, string4, string5, bool, string6, string7, string8, string9 != null ? pairingQueries.MetaDataAdapter.getIconsAdapter().decode(string9) : null, sqlCursor2.getString(11));
    }

    /* access modifiers changed from: private */
    public static final GetListOfPairingsWithoutRequestReceived getListOfPairingsWithoutRequestReceived$lambda$5(String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List list, String str9) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "relay_protocol");
        String str11 = str4;
        Intrinsics.checkNotNullParameter(str11, "uri");
        String str12 = str5;
        Intrinsics.checkNotNullParameter(str12, "methods");
        return new GetListOfPairingsWithoutRequestReceived(str, j2, str10, str3, str11, str12, bool, str6, str7, str8, list, str9);
    }

    /* access modifiers changed from: private */
    public static final Object getPairingByTopic$lambda$7(Function12 function12, PairingQueries pairingQueries, SqlCursor sqlCursor) {
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        String string = sqlCursor2.getString(0);
        Intrinsics.checkNotNull(string);
        Long l2 = sqlCursor2.getLong(1);
        Intrinsics.checkNotNull(l2);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        Boolean bool = sqlCursor2.getBoolean(6);
        String string6 = sqlCursor2.getString(7);
        String string7 = sqlCursor2.getString(8);
        String string8 = sqlCursor2.getString(9);
        String string9 = sqlCursor2.getString(10);
        return function12.invoke(string, l2, string2, string3, string4, string5, bool, string6, string7, string8, string9 != null ? pairingQueries.MetaDataAdapter.getIconsAdapter().decode(string9) : null, sqlCursor2.getString(11));
    }

    /* access modifiers changed from: private */
    public static final GetPairingByTopic getPairingByTopic$lambda$8(String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List list, String str9) {
        Intrinsics.checkNotNullParameter(str, "topic_");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "relay_protocol");
        String str11 = str4;
        Intrinsics.checkNotNullParameter(str11, "uri");
        String str12 = str5;
        Intrinsics.checkNotNullParameter(str12, "methods");
        return new GetPairingByTopic(str, j2, str10, str3, str11, str12, bool, str6, str7, str8, list, str9);
    }

    /* access modifiers changed from: private */
    public static final String hasTopic$lambda$9(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        return string;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortPairing$lambda$10(String str, long j2, String str2, String str3, String str4, String str5, boolean z2, Boolean bool, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindLong(1, Long.valueOf(j2));
        sqlPreparedStatement.bindString(2, str2);
        sqlPreparedStatement.bindString(3, str3);
        sqlPreparedStatement.bindString(4, str4);
        sqlPreparedStatement.bindString(5, str5);
        sqlPreparedStatement.bindBoolean(6, Boolean.valueOf(z2));
        sqlPreparedStatement.bindBoolean(7, bool);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortPairing$lambda$11(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Pairing");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit setRequestReceived$lambda$14(Boolean bool, String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindBoolean(0, bool);
        sqlPreparedStatement.bindString(1, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit setRequestReceived$lambda$15(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Pairing");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateOrAbortExpiry$lambda$16(long j2, String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateOrAbortExpiry$lambda$17(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("Pairing");
        return Unit.INSTANCE;
    }

    public final void deletePairing(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-507308827, "DELETE FROM Pairing\nWHERE ? = topic", 1, new d(str, 3));
        notifyQueries(-507308827, new k(8));
    }

    @NotNull
    public final <T> Query<T> getListOfPairing(@NotNull Function12<? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? extends T> function12) {
        Intrinsics.checkNotNullParameter(function12, "mapper");
        return QueryKt.Query(117081845, new String[]{"Pairing", "MetaData"}, getDriver(), "Pairing.sq", "getListOfPairing", "SELECT pd.topic, pd.expiry, pd.relay_protocol, pd.relay_data, pd.uri, pd.methods, pd.is_proposal_received, mdd_peer.name, mdd_peer.description, mdd_peer.url, mdd_peer.icons, mdd_peer.native\nFROM Pairing pd\n    LEFT JOIN MetaData mdd_peer ON pd.topic = mdd_peer.sequence_topic AND mdd_peer.type = \"PEER\"", new o(function12, this, 2));
    }

    @NotNull
    public final <T> Query<T> getListOfPairingsWithoutRequestReceived(@NotNull Function12<? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? extends T> function12) {
        Intrinsics.checkNotNullParameter(function12, "mapper");
        return QueryKt.Query(-188987034, new String[]{"Pairing", "MetaData"}, getDriver(), "Pairing.sq", "getListOfPairingsWithoutRequestReceived", "SELECT pd.topic, pd.expiry, pd.relay_protocol, pd.relay_data, pd.uri, pd.methods, pd.is_proposal_received, mdd_peer.name, mdd_peer.description, mdd_peer.url, mdd_peer.icons, mdd_peer.native\nFROM Pairing pd\n    LEFT JOIN MetaData mdd_peer ON pd.topic = mdd_peer.sequence_topic AND mdd_peer.type = \"PEER\"\nWHERE pd.is_proposal_received = 0", new o(function12, this, 1));
    }

    @NotNull
    public final <T> Query<T> getPairingByTopic(@NotNull String str, @NotNull Function12<? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? extends T> function12) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function12, "mapper");
        return new GetPairingByTopicQuery(this, str, new o(function12, this, 0));
    }

    @NotNull
    public final Query<String> hasTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new HasTopicQuery(this, str, new k(6));
    }

    public final void insertOrAbortPairing(@NotNull String str, long j2, @NotNull String str2, @Nullable String str3, @NotNull String str4, @NotNull String str5, boolean z2, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        String str6 = str2;
        Intrinsics.checkNotNullParameter(str6, "relay_protocol");
        String str7 = str4;
        Intrinsics.checkNotNullParameter(str7, "uri");
        String str8 = str5;
        Intrinsics.checkNotNullParameter(str8, "methods");
        getDriver().execute(1243533004, "INSERT OR ABORT INTO Pairing(topic, expiry, relay_protocol,  relay_data, uri, methods, is_active, is_proposal_received)\nVALUES (?,?,?,?,?,?, ?, ?)", 8, new m(str, j2, str6, str3, str7, str8, z2, bool));
        notifyQueries(1243533004, new k(5));
    }

    public final void setRequestReceived(@Nullable Boolean bool, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(822624134, "UPDATE OR ABORT Pairing\nSET is_proposal_received = ?\nWHERE topic = ?", 2, new l(bool, str, 5));
        notifyQueries(822624134, new k(7));
    }

    public final void updateOrAbortExpiry(long j2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-227929953, "UPDATE OR ABORT Pairing\nSET expiry = ?\nWHERE ? = topic", 2, new f(j2, str, 1));
        notifyQueries(-227929953, new k(4));
    }

    @NotNull
    public final Query<GetListOfPairing> getListOfPairing() {
        return getListOfPairing(new n(0));
    }

    @NotNull
    public final Query<GetListOfPairingsWithoutRequestReceived> getListOfPairingsWithoutRequestReceived() {
        return getListOfPairingsWithoutRequestReceived(new n(2));
    }

    @NotNull
    public final Query<GetPairingByTopic> getPairingByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return getPairingByTopic(str, new n(1));
    }
}
