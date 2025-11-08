package com.reown.sign.sign;

import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.sign.SignDatabase;
import com.reown.sign.sign.SignDatabaseImpl;
import com.reown.sign.storage.data.dao.namespace.NamespaceDao;
import com.reown.sign.storage.data.dao.optionalnamespaces.OptionalNamespaceDao;
import com.reown.sign.storage.data.dao.proposal.ProposalDao;
import com.reown.sign.storage.data.dao.proposalnamespace.ProposalNamespaceDao;
import com.reown.sign.storage.data.dao.session.SessionDao;
import com.reown.sign.storage.data.dao.temp.TempNamespaceDao;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aJ\u0010\b\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\"*\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "Lkotlin/reflect/KClass;", "Lcom/reown/sign/SignDatabase;", "getSchema", "(Lkotlin/reflect/KClass;)Lapp/cash/sqldelight/db/SqlSchema;", "newInstance", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "NamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;", "OptionalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;", "ProposalDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;", "ProposalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao$Adapter;", "SessionDaoAdapter", "Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;", "TempNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class SignDatabaseImplKt {
    @NotNull
    public static final SqlSchema<QueryResult.Value<Unit>> getSchema(@NotNull KClass<SignDatabase> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return SignDatabaseImpl.Schema.INSTANCE;
    }

    @NotNull
    public static final SignDatabase newInstance(@NotNull KClass<SignDatabase> kClass, @NotNull SqlDriver sqlDriver, @NotNull NamespaceDao.Adapter adapter, @NotNull OptionalNamespaceDao.Adapter adapter2, @NotNull ProposalDao.Adapter adapter3, @NotNull ProposalNamespaceDao.Adapter adapter4, @NotNull SessionDao.Adapter adapter5, @NotNull TempNamespaceDao.Adapter adapter6) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "NamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter2, "OptionalNamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter3, "ProposalDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter4, "ProposalNamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter5, "SessionDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter6, "TempNamespaceDaoAdapter");
        return new SignDatabaseImpl(sqlDriver, adapter, adapter2, adapter3, adapter4, adapter5, adapter6);
    }
}
