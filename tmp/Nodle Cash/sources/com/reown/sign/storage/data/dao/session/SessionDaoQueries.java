package com.reown.sign.storage.data.dao.session;

import androidx.compose.material.pullrefresh.e;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.QueryKt;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.f;
import com.reown.sign.engine.use_case.calls.g;
import com.reown.sign.storage.data.dao.proposal.b;
import com.reown.sign.storage.data.dao.session.SessionDao;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0005/0123B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJÛ\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\t\"\b\b\u0000\u0010\f*\u00020\r2Â\u0002\u0010\u000e\u001a½\u0002\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001d\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\u0015\u0012\u0013\u0018\u00010 ¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(!\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\f0\u000fJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020#0\tJã\u0002\u0010$\u001a\b\u0012\u0004\u0012\u0002H\f0\t\"\b\b\u0000\u0010\f*\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132Â\u0002\u0010\u000e\u001a½\u0002\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001d\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001f\u0012\u0015\u0012\u0013\u0018\u00010 ¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(!\u0012!\u0012\u001f\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\f0\u000fJ\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\t2\u0006\u0010\u0014\u001a\u00020\u0013J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\u0013J\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130\t2\u0006\u0010\u001d\u001a\u00020\u0013J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\t2\u0006\u0010\u0014\u001a\u00020\u0013J\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\u0013J\u0001\u0010*\u001a\u00020+2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001c\u001a\u00020\u001b2\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u001eJ\u0016\u0010,\u001a\u00020+2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010-\u001a\u00020+2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010.\u001a\u00020+2\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "SessionDaoAdapter", "Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;)V", "lastInsertedRow", "Lapp/cash/sqldelight/Query;", "", "getListOfSessionDaos", "T", "", "mapper", "Lkotlin/Function13;", "Lkotlin/ParameterName;", "name", "id", "", "topic", "expiry", "relay_protocol", "relay_data", "controller_key", "self_participant", "peer_participant", "", "is_acknowledged", "pairingTopic", "", "properties", "Lcom/reown/android/internal/common/model/TransportType;", "transport_type", "scoped_properties", "Lcom/reown/sign/storage/data/dao/session/GetListOfSessionDaos;", "getSessionByTopic", "Lcom/reown/sign/storage/data/dao/session/GetSessionByTopic;", "getSessionIdByTopic", "getAllSessionTopicsByPairingTopic", "hasTopic", "getExpiry", "insertOrAbortSession", "", "acknowledgeSession", "updateSessionExpiry", "deleteSession", "GetSessionByTopicQuery", "GetSessionIdByTopicQuery", "GetAllSessionTopicsByPairingTopicQuery", "HasTopicQuery", "GetExpiryQuery", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionDaoQueries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionDaoQueries.kt\ncom/reown/sign/storage/data/dao/session/SessionDaoQueries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,352:1\n1#2:353\n*E\n"})
public final class SessionDaoQueries extends TransacterImpl {
    @NotNull
    private final SessionDao.Adapter SessionDaoAdapter;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries$GetAllSessionTopicsByPairingTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "pairingTopic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getPairingTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetAllSessionTopicsByPairingTopicQuery<T> extends Query<T> {
        @NotNull
        private final String pairingTopic;
        final /* synthetic */ SessionDaoQueries this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetAllSessionTopicsByPairingTopicQuery(@NotNull SessionDaoQueries sessionDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "pairingTopic");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = sessionDaoQueries;
            this.pairingTopic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetAllSessionTopicsByPairingTopicQuery getAllSessionTopicsByPairingTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getAllSessionTopicsByPairingTopicQuery.pairingTopic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-2000327269, "SELECT topic\nFROM SessionDao\nWHERE pairingTopic = ?", function1, 1, new c(this, 0));
        }

        @NotNull
        public final String getPairingTopic() {
            return this.pairingTopic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "SessionDao.sq:getAllSessionTopicsByPairingTopic";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries$GetExpiryQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetExpiryQuery<T> extends Query<T> {
        final /* synthetic */ SessionDaoQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetExpiryQuery(@NotNull SessionDaoQueries sessionDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = sessionDaoQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetExpiryQuery getExpiryQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getExpiryQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1203125751, "SELECT expiry\nFROM SessionDao\nWHERE ? = topic", function1, 1, new c(this, 1));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "SessionDao.sq:getExpiry";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries$GetSessionByTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetSessionByTopicQuery<T> extends Query<T> {
        final /* synthetic */ SessionDaoQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetSessionByTopicQuery(@NotNull SessionDaoQueries sessionDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = sessionDaoQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetSessionByTopicQuery getSessionByTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getSessionByTopicQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-725267354, "SELECT sd.id, sd.topic, sd.expiry, sd.relay_protocol, sd.relay_data, sd.controller_key, sd.self_participant, sd.peer_participant, sd.is_acknowledged, sd.pairingTopic, sd.properties, sd.transport_type, sd.scoped_properties\nFROM SessionDao sd\nWHERE topic = ?", function1, 1, new c(this, 2));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "SessionDao.sq:getSessionByTopic";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries$GetSessionIdByTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetSessionIdByTopicQuery<T> extends Query<T> {
        final /* synthetic */ SessionDaoQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetSessionIdByTopicQuery(@NotNull SessionDaoQueries sessionDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = sessionDaoQueries;
            this.topic = str;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetSessionIdByTopicQuery getSessionIdByTopicQuery, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getSessionIdByTopicQuery.topic);
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(-1738352821, "SELECT id\nFROM SessionDao\nWHERE topic = ?", function1, 1, new c(this, 3));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "SessionDao.sq:getSessionIdByTopic";
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J.\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0004\b\u0001\u0010\u00142\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00130\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries$HasTopicQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "topic", "", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getTopic", "()Ljava/lang/String;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class HasTopicQuery<T> extends Query<T> {
        final /* synthetic */ SessionDaoQueries this$0;
        @NotNull
        private final String topic;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public HasTopicQuery(@NotNull SessionDaoQueries sessionDaoQueries, @NotNull String str, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = sessionDaoQueries;
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
            this.this$0.getDriver().addListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(156224295, "SELECT topic\nFROM SessionDao\nWHERE ? = topic", function1, 1, new c(this, 4));
        }

        @NotNull
        public final String getTopic() {
            return this.topic;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"SessionDao"}, listener);
        }

        @NotNull
        public String toString() {
            return "SessionDao.sq:hasTopic";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionDaoQueries(@NotNull SqlDriver sqlDriver, @NotNull SessionDao.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "SessionDaoAdapter");
        this.SessionDaoAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit acknowledgeSession$lambda$20(boolean z2, String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindBoolean(0, Boolean.valueOf(z2));
        sqlPreparedStatement.bindString(1, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit acknowledgeSession$lambda$21(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("SessionDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteSession$lambda$24(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteSession$lambda$25(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("SessionDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final String getAllSessionTopicsByPairingTopic$lambda$12(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        return string;
    }

    /* access modifiers changed from: private */
    public static final long getExpiry$lambda$14(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        return l2.longValue();
    }

    /* access modifiers changed from: private */
    public static final Object getListOfSessionDaos$lambda$4(Function13 function13, SessionDaoQueries sessionDaoQueries, SqlCursor sqlCursor) {
        SessionDaoQueries sessionDaoQueries2 = sessionDaoQueries;
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        Long l2 = sqlCursor2.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor2.getString(1);
        Intrinsics.checkNotNull(string);
        Long l3 = sqlCursor2.getLong(2);
        Intrinsics.checkNotNull(l3);
        String string2 = sqlCursor2.getString(3);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(4);
        String string4 = sqlCursor2.getString(5);
        String string5 = sqlCursor2.getString(6);
        Intrinsics.checkNotNull(string5);
        String string6 = sqlCursor2.getString(7);
        Boolean bool = sqlCursor2.getBoolean(8);
        Intrinsics.checkNotNull(bool);
        String string7 = sqlCursor2.getString(9);
        Intrinsics.checkNotNull(string7);
        String string8 = sqlCursor2.getString(10);
        Map decode = string8 != null ? sessionDaoQueries2.SessionDaoAdapter.getPropertiesAdapter().decode(string8) : null;
        String string9 = sqlCursor2.getString(11);
        TransportType decode2 = string9 != null ? sessionDaoQueries2.SessionDaoAdapter.getTransport_typeAdapter().decode(string9) : null;
        String string10 = sqlCursor2.getString(12);
        return function13.invoke(l2, string, l3, string2, string3, string4, string5, string6, bool, string7, decode, decode2, string10 != null ? sessionDaoQueries2.SessionDaoAdapter.getScoped_propertiesAdapter().decode(string10) : null);
    }

    /* access modifiers changed from: private */
    public static final GetListOfSessionDaos getListOfSessionDaos$lambda$5(long j2, String str, long j3, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, Map map, TransportType transportType, Map map2) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, PushMessagingService.KEY_TOPIC);
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "relay_protocol");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "self_participant");
        String str11 = str7;
        Intrinsics.checkNotNullParameter(str11, "pairingTopic");
        return new GetListOfSessionDaos(j2, str8, j3, str9, str3, str4, str10, str6, z2, str11, map, transportType, map2);
    }

    /* access modifiers changed from: private */
    public static final GetSessionByTopic getSessionByTopic$lambda$10(long j2, String str, long j3, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, Map map, TransportType transportType, Map map2) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, "topic_");
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "relay_protocol");
        String str10 = str5;
        Intrinsics.checkNotNullParameter(str10, "self_participant");
        String str11 = str7;
        Intrinsics.checkNotNullParameter(str11, "pairingTopic");
        return new GetSessionByTopic(j2, str8, j3, str9, str3, str4, str10, str6, z2, str11, map, transportType, map2);
    }

    /* access modifiers changed from: private */
    public static final Object getSessionByTopic$lambda$9(Function13 function13, SessionDaoQueries sessionDaoQueries, SqlCursor sqlCursor) {
        SessionDaoQueries sessionDaoQueries2 = sessionDaoQueries;
        SqlCursor sqlCursor2 = sqlCursor;
        Intrinsics.checkNotNullParameter(sqlCursor2, "cursor");
        Long l2 = sqlCursor2.getLong(0);
        Intrinsics.checkNotNull(l2);
        String string = sqlCursor2.getString(1);
        Intrinsics.checkNotNull(string);
        Long l3 = sqlCursor2.getLong(2);
        Intrinsics.checkNotNull(l3);
        String string2 = sqlCursor2.getString(3);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor2.getString(4);
        String string4 = sqlCursor2.getString(5);
        String string5 = sqlCursor2.getString(6);
        Intrinsics.checkNotNull(string5);
        String string6 = sqlCursor2.getString(7);
        Boolean bool = sqlCursor2.getBoolean(8);
        Intrinsics.checkNotNull(bool);
        String string7 = sqlCursor2.getString(9);
        Intrinsics.checkNotNull(string7);
        String string8 = sqlCursor2.getString(10);
        Map decode = string8 != null ? sessionDaoQueries2.SessionDaoAdapter.getPropertiesAdapter().decode(string8) : null;
        String string9 = sqlCursor2.getString(11);
        TransportType decode2 = string9 != null ? sessionDaoQueries2.SessionDaoAdapter.getTransport_typeAdapter().decode(string9) : null;
        String string10 = sqlCursor2.getString(12);
        return function13.invoke(l2, string, l3, string2, string3, string4, string5, string6, bool, string7, decode, decode2, string10 != null ? sessionDaoQueries2.SessionDaoAdapter.getScoped_propertiesAdapter().decode(string10) : null);
    }

    /* access modifiers changed from: private */
    public static final long getSessionIdByTopic$lambda$11(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        return l2.longValue();
    }

    /* access modifiers changed from: private */
    public static final String hasTopic$lambda$13(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        return string;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortSession$lambda$18(String str, String str2, long j2, String str3, String str4, String str5, String str6, String str7, boolean z2, Map map, TransportType transportType, Map map2, SessionDaoQueries sessionDaoQueries, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        sqlPreparedStatement.bindLong(2, Long.valueOf(j2));
        sqlPreparedStatement.bindString(3, str3);
        sqlPreparedStatement.bindString(4, str4);
        sqlPreparedStatement.bindString(5, str5);
        sqlPreparedStatement.bindString(6, str6);
        sqlPreparedStatement.bindString(7, str7);
        sqlPreparedStatement.bindBoolean(8, Boolean.valueOf(z2));
        String str8 = null;
        sqlPreparedStatement.bindString(9, map != null ? sessionDaoQueries.SessionDaoAdapter.getPropertiesAdapter().encode(map) : null);
        sqlPreparedStatement.bindString(10, transportType != null ? sessionDaoQueries.SessionDaoAdapter.getTransport_typeAdapter().encode(transportType) : null);
        if (map2 != null) {
            str8 = sessionDaoQueries.SessionDaoAdapter.getScoped_propertiesAdapter().encode(map2);
        }
        sqlPreparedStatement.bindString(11, str8);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortSession$lambda$19(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("SessionDao");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final long lastInsertedRow$lambda$0(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        return l2.longValue();
    }

    /* access modifiers changed from: private */
    public static final Unit updateSessionExpiry$lambda$22(long j2, String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindLong(0, Long.valueOf(j2));
        sqlPreparedStatement.bindString(1, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateSessionExpiry$lambda$23(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("SessionDao");
        return Unit.INSTANCE;
    }

    public final void acknowledgeSession(boolean z2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(4315724, "UPDATE OR ABORT SessionDao\nSET is_acknowledged = ?\nWHERE topic = ?", 2, new e(z2, str));
        notifyQueries(4315724, new g(19));
    }

    public final void deleteSession(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(-1835895495, "DELETE FROM SessionDao\nWHERE topic = ?", 1, new c(str, 5));
        notifyQueries(-1835895495, new g(25));
    }

    @NotNull
    public final Query<String> getAllSessionTopicsByPairingTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "pairingTopic");
        return new GetAllSessionTopicsByPairingTopicQuery(this, str, new g(21));
    }

    @NotNull
    public final Query<Long> getExpiry(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new GetExpiryQuery(this, str, new g(26));
    }

    @NotNull
    public final <T> Query<T> getListOfSessionDaos(@NotNull Function13<? super Long, ? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super Map<String, String>, ? super TransportType, ? super Map<String, String>, ? extends T> function13) {
        Intrinsics.checkNotNullParameter(function13, "mapper");
        return QueryKt.Query(-1635953346, new String[]{"SessionDao"}, getDriver(), "SessionDao.sq", "getListOfSessionDaos", "SELECT sd.id, sd.topic, sd.expiry, sd.relay_protocol, sd.relay_data, sd.controller_key, sd.self_participant, sd.peer_participant, sd.is_acknowledged, sd.pairingTopic, sd.properties, sd.transport_type, sd.scoped_properties\nFROM SessionDao sd", new b(function13, this, 0));
    }

    @NotNull
    public final <T> Query<T> getSessionByTopic(@NotNull String str, @NotNull Function13<? super Long, ? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Boolean, ? super String, ? super Map<String, String>, ? super TransportType, ? super Map<String, String>, ? extends T> function13) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(function13, "mapper");
        return new GetSessionByTopicQuery(this, str, new b(function13, this, 1));
    }

    @NotNull
    public final Query<Long> getSessionIdByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new GetSessionIdByTopicQuery(this, str, new g(18));
    }

    @NotNull
    public final Query<String> hasTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return new HasTopicQuery(this, str, new g(23));
    }

    public final void insertOrAbortSession(@NotNull String str, @NotNull String str2, long j2, @NotNull String str3, @Nullable String str4, @Nullable String str5, @NotNull String str6, @Nullable String str7, boolean z2, @Nullable Map<String, String> map, @Nullable TransportType transportType, @Nullable Map<String, String> map2) {
        String str8 = str;
        Intrinsics.checkNotNullParameter(str8, PushMessagingService.KEY_TOPIC);
        String str9 = str2;
        Intrinsics.checkNotNullParameter(str9, "pairingTopic");
        String str10 = str3;
        Intrinsics.checkNotNullParameter(str10, "relay_protocol");
        String str11 = str6;
        Intrinsics.checkNotNullParameter(str11, "self_participant");
        SqlDriver driver = getDriver();
        a aVar = r1;
        a aVar2 = new a(str8, str9, j2, str10, str4, str5, str11, str7, z2, map, transportType, map2, this);
        driver.execute(1649562452, "INSERT OR ABORT INTO SessionDao(topic, pairingTopic, expiry, relay_protocol, relay_data, controller_key, self_participant, peer_participant, is_acknowledged, properties, transport_type, scoped_properties)\nVALUES (?,  ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?)", 12, aVar);
        notifyQueries(1649562452, new g(20));
    }

    @NotNull
    public final Query<Long> lastInsertedRow() {
        return QueryKt.Query(1635110010, new String[]{"SessionDao"}, getDriver(), "SessionDao.sq", "lastInsertedRow", "SELECT id\nFROM SessionDao\nWHERE id = (SELECT MAX(id) FROM SessionDao)", new g(22));
    }

    public final void updateSessionExpiry(long j2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        getDriver().execute(1760992622, "UPDATE OR ABORT SessionDao\nSET expiry = ?\nWHERE topic = ?", 2, new f(j2, str, 3));
        notifyQueries(1760992622, new g(24));
    }

    @NotNull
    public final Query<GetListOfSessionDaos> getListOfSessionDaos() {
        return getListOfSessionDaos(new b(4));
    }

    @NotNull
    public final Query<GetSessionByTopic> getSessionByTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_TOPIC);
        return getSessionByTopic(str, new b(3));
    }
}
