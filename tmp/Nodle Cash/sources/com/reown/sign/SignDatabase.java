package com.reown.sign;

import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.sign.sign.SignDatabaseImplKt;
import com.reown.sign.storage.data.dao.authenticatereponse.AuthenticateResponseTopicDaoQueries;
import com.reown.sign.storage.data.dao.linkmode.LinkModeDaoQueries;
import com.reown.sign.storage.data.dao.namespace.NamespaceDao;
import com.reown.sign.storage.data.dao.namespace.NamespaceDaoQueries;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDao;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.proposal.ProposalDao;
import com.reown.sign.storage.data.dao.proposal.ProposalDaoQueries;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDao;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDaoQueries;
import com.reown.sign.storage.data.dao.session.SessionDao;
import com.reown.sign.storage.data.dao.session.SessionDaoQueries;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDao;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDaoQueries;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \"2\u00020\u0001:\u0001\"R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#À\u0006\u0003"}, d2 = {"Lcom/reown/sign/SignDatabase;", "Lapp/cash/sqldelight/Transacter;", "authenticateResponseTopicDaoQueries", "Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "getAuthenticateResponseTopicDaoQueries", "()Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "linkModeDaoQueries", "Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "getLinkModeDaoQueries", "()Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "namespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "getNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "optionalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "getOptionalNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "proposalDaoQueries", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "getProposalDaoQueries", "()Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "proposalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "getProposalNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "sessionDaoQueries", "Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "getSessionDaoQueries", "()Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "tempNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "getTempNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "Companion", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SignDatabase extends Transacter {
    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JA\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/SignDatabase$Companion;", "", "<init>", "()V", "Schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "getSchema", "()Lapp/cash/sqldelight/db/SqlSchema;", "invoke", "Lcom/reown/sign/SignDatabase;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "NamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;", "OptionalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;", "ProposalDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;", "ProposalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao$Adapter;", "SessionDaoAdapter", "Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;", "TempNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @NotNull
        public final SqlSchema<QueryResult.Value<Unit>> getSchema() {
            return SignDatabaseImplKt.getSchema(Reflection.getOrCreateKotlinClass(SignDatabase.class));
        }

        @NotNull
        public final SignDatabase invoke(@NotNull SqlDriver sqlDriver, @NotNull NamespaceDao.Adapter adapter, @NotNull OptionalNamespaceDao.Adapter adapter2, @NotNull ProposalDao.Adapter adapter3, @NotNull ProposalNamespaceDao.Adapter adapter4, @NotNull SessionDao.Adapter adapter5, @NotNull TempNamespaceDao.Adapter adapter6) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            Intrinsics.checkNotNullParameter(adapter, "NamespaceDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter2, "OptionalNamespaceDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter3, "ProposalDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter4, "ProposalNamespaceDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter5, "SessionDaoAdapter");
            Intrinsics.checkNotNullParameter(adapter6, "TempNamespaceDaoAdapter");
            return SignDatabaseImplKt.newInstance(Reflection.getOrCreateKotlinClass(SignDatabase.class), sqlDriver, adapter, adapter2, adapter3, adapter4, adapter5, adapter6);
        }
    }

    @NotNull
    AuthenticateResponseTopicDaoQueries getAuthenticateResponseTopicDaoQueries();

    @NotNull
    LinkModeDaoQueries getLinkModeDaoQueries();

    @NotNull
    NamespaceDaoQueries getNamespaceDaoQueries();

    @NotNull
    OptionalNamespaceDaoQueries getOptionalNamespaceDaoQueries();

    @NotNull
    ProposalDaoQueries getProposalDaoQueries();

    @NotNull
    ProposalNamespaceDaoQueries getProposalNamespaceDaoQueries();

    @NotNull
    SessionDaoQueries getSessionDaoQueries();

    @NotNull
    TempNamespaceDaoQueries getTempNamespaceDaoQueries();
}
