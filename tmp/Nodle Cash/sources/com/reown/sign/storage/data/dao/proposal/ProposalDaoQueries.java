package com.reown.sign.storage.data.dao.proposal;

import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.sign.engine.use_case.calls.g;
import com.reown.sign.storage.data.dao.proposal.ProposalDao;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002'(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007Jå\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2Ä\u0002\u0010\u000e\u001a¿\u0002\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0017¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010\f\u001a\u00020\rJÝ\u0002\u0010!\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2Ä\u0002\u0010\u000e\u001a¿\u0002\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0017¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\n0\u000fJ\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\tJå\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2Ä\u0002\u0010\u000e\u001a¿\u0002\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0017¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u0002H\n0\u000fJ\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010\u0014\u001a\u00020\rJ\u0001\u0010#\u001a\u00020$2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00172\u0006\u0010\u0019\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\r2\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\r2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00102\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010\u001b¢\u0006\u0002\u0010%J\u000e\u0010&\u001a\u00020$2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "ProposalDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;)V", "getProposalByKey", "Lapp/cash/sqldelight/Query;", "T", "", "proposer_key", "", "mapper", "Lkotlin/Function13;", "", "Lkotlin/ParameterName;", "name", "request_id", "pairingTopic", "description", "url", "", "icons", "relay_protocol", "relay_data", "", "properties", "redirect", "expiry", "scoped_properties", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDao;", "getListOfProposalDaos", "getProposalByPairingTopic", "insertOrAbortSession", "", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;Ljava/util/Map;)V", "deleteProposal", "GetProposalByKeyQuery", "GetProposalByPairingTopicQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProposalDaoQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProposalDaoQueries.kt\ncom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,278:1\n1#2:279\n*E\n"})
public final class ProposalDaoQueries extends TransacterImpl {
    @NotNull
    private final ProposalDao.Adapter ProposalDaoAdapter;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries$GetProposalByKeyQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "proposer_key", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getProposer_key", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetProposalByKeyQuery<T> extends Query<T> {
        @NotNull
        private final String proposer_key;
        final /* synthetic */ ProposalDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetProposalByKeyQuery(@NotNull ProposalDaoQueries proposalDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "proposer_key");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = proposalDaoQueries;
            this.proposer_key = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetProposalByKeyQuery getProposalByKeyQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getProposalByKeyQuery.proposer_key);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"ProposalDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-1015209366, "SELECT pd.request_id, pd.pairingTopic, pd.name, pd.description, pd.url, pd.icons, pd.relay_protocol, pd.relay_data, pd.proposer_key, pd.properties, pd.redirect, pd.expiry, pd.scoped_properties\nFROM ProposalDao pd\nWHERE proposer_key = ?", function1, 1, new d(this, 0));
        }

        @NotNull
        public final String getProposer_key() {
            return this.proposer_key;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"ProposalDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "ProposalDao.sq:getProposalByKey";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries$GetProposalByPairingTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "pairingTopic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getPairingTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetProposalByPairingTopicQuery<T> extends Query<T> {
        @NotNull
        private final String pairingTopic;
        final /* synthetic */ ProposalDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetProposalByPairingTopicQuery(@NotNull ProposalDaoQueries proposalDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = proposalDaoQueries;
            this.pairingTopic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetProposalByPairingTopicQuery getProposalByPairingTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getProposalByPairingTopicQuery.pairingTopic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"ProposalDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1614390172, "SELECT pd.request_id, pd.pairingTopic, pd.name, pd.description, pd.url, pd.icons, pd.relay_protocol, pd.relay_data, pd.proposer_key, pd.properties, pd.redirect, pd.expiry, pd.scoped_properties\nFROM ProposalDao pd\nWHERE pairingTopic = ?", function1, 1, new d(this, 1));
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"ProposalDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "ProposalDao.sq:getProposalByPairingTopic";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProposalDaoQueries(@NotNull SqlDriver sqlDriver, @NotNull ProposalDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "ProposalDaoAdapter");
        this.ProposalDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteProposal$lambda$16(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteProposal$lambda$17(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("ProposalDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Object getListOfProposalDaos$lambda$6(Function13 function13, ProposalDaoQueries proposalDaoQueries, SqlCursor sqlCursor) {
        ProposalDaoQueries proposalDaoQueries2 = proposalDaoQueries;
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        Long l2 = sqlCursor2.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor2.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        ColumnAdapter<List<String>, String> iconsAdapter = proposalDaoQueries2.ProposalDaoAdapter.getIconsAdapter();
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        List<String> decode = iconsAdapter.decode(string5);
        String string6 = sqlCursor2.getString(6);
        Intrinsics.checkNotNull(string6);
        String string7 = sqlCursor2.getString(7);
        String string8 = sqlCursor2.getString(8);
        Intrinsics.checkNotNull(string8);
        String string9 = sqlCursor2.getString(9);
        Map decode2 = string9 != null ? proposalDaoQueries2.ProposalDaoAdapter.getPropertiesAdapter().decode(string9) : null;
        String string10 = sqlCursor2.getString(10);
        Intrinsics.checkNotNull(string10);
        Long l3 = sqlCursor2.getLong(11);
        String string11 = sqlCursor2.getString(12);
        return function13.invoke(l2, string, string2, string3, string4, decode, string6, string7, string8, decode2, string10, l3, string11 != null ? proposalDaoQueries2.ProposalDaoAdapter.getScoped_propertiesAdapter().decode(string11) : null);
    }

    /* access modifiers changed from: private */
    public static final ProposalDao getListOfProposalDaos$lambda$7(long j2, String str, String str2, String str3, String str4, List list, String str5, String str6, String str7, Map map, String str8, Long l2, Map map2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "pairingTopic");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "name");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "description");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "url");
        List list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "relay_protocol");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "proposer_key");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "redirect");
        return new ProposalDao(j2, str9, str10, str11, str12, list2, str13, str6, str14, map, str15, l2, map2);
    }

    /* access modifiers changed from: private */
    public static final Object getProposalByKey$lambda$2(Function13 function13, ProposalDaoQueries proposalDaoQueries, SqlCursor sqlCursor) {
        ProposalDaoQueries proposalDaoQueries2 = proposalDaoQueries;
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        Long l2 = sqlCursor2.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor2.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        ColumnAdapter<List<String>, String> iconsAdapter = proposalDaoQueries2.ProposalDaoAdapter.getIconsAdapter();
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        List<String> decode = iconsAdapter.decode(string5);
        String string6 = sqlCursor2.getString(6);
        Intrinsics.checkNotNull(string6);
        String string7 = sqlCursor2.getString(7);
        String string8 = sqlCursor2.getString(8);
        Intrinsics.checkNotNull(string8);
        String string9 = sqlCursor2.getString(9);
        Map decode2 = string9 != null ? proposalDaoQueries2.ProposalDaoAdapter.getPropertiesAdapter().decode(string9) : null;
        String string10 = sqlCursor2.getString(10);
        Intrinsics.checkNotNull(string10);
        Long l3 = sqlCursor2.getLong(11);
        String string11 = sqlCursor2.getString(12);
        return function13.invoke(l2, string, string2, string3, string4, decode, string6, string7, string8, decode2, string10, l3, string11 != null ? proposalDaoQueries2.ProposalDaoAdapter.getScoped_propertiesAdapter().decode(string11) : null);
    }

    /* access modifiers changed from: private */
    public static final ProposalDao getProposalByKey$lambda$3(long j2, String str, String str2, String str3, String str4, List list, String str5, String str6, String str7, Map map, String str8, Long l2, Map map2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "pairingTopic");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "name");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "description");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "url");
        List list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "relay_protocol");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "proposer_key_");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "redirect");
        return new ProposalDao(j2, str9, str10, str11, str12, list2, str13, str6, str14, map, str15, l2, map2);
    }

    /* access modifiers changed from: private */
    public static final Object getProposalByPairingTopic$lambda$10(Function13 function13, ProposalDaoQueries proposalDaoQueries, SqlCursor sqlCursor) {
        ProposalDaoQueries proposalDaoQueries2 = proposalDaoQueries;
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        Long l2 = sqlCursor2.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor2.getString(1);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor2.getString(2);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(3);
        Intrinsics.checkNotNull(string3);
        String string4 = sqlCursor2.getString(4);
        Intrinsics.checkNotNull(string4);
        ColumnAdapter<List<String>, String> iconsAdapter = proposalDaoQueries2.ProposalDaoAdapter.getIconsAdapter();
        String string5 = sqlCursor2.getString(5);
        Intrinsics.checkNotNull(string5);
        List<String> decode = iconsAdapter.decode(string5);
        String string6 = sqlCursor2.getString(6);
        Intrinsics.checkNotNull(string6);
        String string7 = sqlCursor2.getString(7);
        String string8 = sqlCursor2.getString(8);
        Intrinsics.checkNotNull(string8);
        String string9 = sqlCursor2.getString(9);
        Map decode2 = string9 != null ? proposalDaoQueries2.ProposalDaoAdapter.getPropertiesAdapter().decode(string9) : null;
        String string10 = sqlCursor2.getString(10);
        Intrinsics.checkNotNull(string10);
        Long l3 = sqlCursor2.getLong(11);
        String string11 = sqlCursor2.getString(12);
        return function13.invoke(l2, string, string2, string3, string4, decode, string6, string7, string8, decode2, string10, l3, string11 != null ? proposalDaoQueries2.ProposalDaoAdapter.getScoped_propertiesAdapter().decode(string11) : null);
    }

    /* access modifiers changed from: private */
    public static final ProposalDao getProposalByPairingTopic$lambda$11(long j2, String str, String str2, String str3, String str4, List list, String str5, String str6, String str7, Map map, String str8, Long l2, Map map2) {
        String str9 = str;
        Intrinsics.checkNotNullParameter(str9, "pairingTopic_");
        String str10 = str2;
        Intrinsics.checkNotNullParameter(str10, "name");
        String str11 = str3;
        Intrinsics.checkNotNullParameter(str11, "description");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "url");
        List list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "relay_protocol");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "proposer_key");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "redirect");
        return new ProposalDao(j2, str9, str10, str11, str12, list2, str13, str6, str14, map, str15, l2, map2);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortSession$lambda$14(Long l2, String str, String str2, String str3, String str4, ProposalDaoQueries proposalDaoQueries, List list, String str5, String str6, String str7, Map map, String str8, Long l3, Map map2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, l2);
        sqlPreparedStatement.bindString(1, str);
        sqlPreparedStatement.bindString(2, str2);
        sqlPreparedStatement.bindString(3, str3);
        sqlPreparedStatement.bindString(4, str4);
        sqlPreparedStatement.bindString(5, proposalDaoQueries.ProposalDaoAdapter.getIconsAdapter().encode(list));
        sqlPreparedStatement.bindString(6, str5);
        sqlPreparedStatement.bindString(7, str6);
        sqlPreparedStatement.bindString(8, str7);
        String str9 = null;
        sqlPreparedStatement.bindString(9, map != null ? proposalDaoQueries.ProposalDaoAdapter.getPropertiesAdapter().encode(map) : null);
        sqlPreparedStatement.bindString(10, str8);
        sqlPreparedStatement.bindLong(11, l3);
        if (map2 != null) {
            str9 = proposalDaoQueries.ProposalDaoAdapter.getScoped_propertiesAdapter().encode(map2);
        }
        sqlPreparedStatement.bindString(12, str9);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortSession$lambda$15(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("ProposalDao");
        return Unit.INSTANCE;
    }

    public final void deleteProposal(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "proposer_key");
        getDriver().execute(-1977294937, "DELETE FROM ProposalDao\nWHERE proposer_key = ?", 1, new d(str, 2));
        notifyQueries(-1977294937, new g(13));
    }

    @NotNull
    public final <T> Query<T> getListOfProposalDaos(@NotNull Function13<? super Long, ? super String, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? super String, ? super String, ? super Map<String, String>, ? super String, ? super Long, ? super Map<String, String>, ? extends T> function13) {
        Intrinsics.checkNotNullParameter(function13, "mapper");
        return QueryKt.Query(1540279732, new String[]{"ProposalDao"}, getDriver(), "ProposalDao.sq", "getListOfProposalDaos", "SELECT pd.request_id, pd.pairingTopic, pd.name, pd.description, pd.url, pd.icons, pd.relay_protocol, pd.relay_data, pd.proposer_key, pd.properties, pd.redirect, pd.expiry, pd.scoped_properties\nFROM ProposalDao pd", new a(function13, this, 2));
    }

    @NotNull
    public final <T> Query<T> getProposalByKey(@NotNull String str, @NotNull Function13<? super Long, ? super String, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? super String, ? super String, ? super Map<String, String>, ? super String, ? super Long, ? super Map<String, String>, ? extends T> function13) {
        Intrinsics.checkNotNullParameter(str, "proposer_key");
        Intrinsics.checkNotNullParameter(function13, "mapper");
        return new GetProposalByKeyQuery(this, str, new a(function13, this, 1));
    }

    @NotNull
    public final <T> Query<T> getProposalByPairingTopic(@NotNull String str, @NotNull Function13<? super Long, ? super String, ? super String, ? super String, ? super String, ? super List<String>, ? super String, ? super String, ? super String, ? super Map<String, String>, ? super String, ? super Long, ? super Map<String, String>, ? extends T> function13) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        Intrinsics.checkNotNullParameter(function13, "mapper");
        return new GetProposalByPairingTopicQuery(this, str, new a(function13, this, 0));
    }

    public final void insertOrAbortSession(@Nullable Long l2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @NotNull String str5, @Nullable String str6, @NotNull String str7, @Nullable Map<String, String> map, @NotNull String str8, @Nullable Long l3, @Nullable Map<String, String> map2) {
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
        Intrinsics.checkNotNullParameter(str13, "relay_protocol");
        String str14 = str7;
        Intrinsics.checkNotNullParameter(str14, "proposer_key");
        String str15 = str8;
        Intrinsics.checkNotNullParameter(str15, "redirect");
        SqlDriver driver = getDriver();
        c cVar = r1;
        c cVar2 = new c(l2, str9, str10, str11, str12, this, list2, str13, str6, str14, map, str15, l3, map2);
        driver.execute(1381780556, "INSERT OR ABORT INTO ProposalDao(request_id, pairingTopic, name, description, url, icons, relay_protocol, relay_data, proposer_key, properties, redirect, expiry, scoped_properties)\nVALUES (?,?,?,?,?,?,?,?,?,?,?, ?, ?)", 13, cVar);
        notifyQueries(1381780556, new g(14));
    }

    @NotNull
    public final Query<ProposalDao> getListOfProposalDaos() {
        return getListOfProposalDaos(new b(2));
    }

    @NotNull
    public final Query<ProposalDao> getProposalByKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "proposer_key");
        return getProposalByKey(str, new b(1));
    }

    @NotNull
    public final Query<ProposalDao> getProposalByPairingTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        return getProposalByPairingTopic(str, new b(0));
    }
}
