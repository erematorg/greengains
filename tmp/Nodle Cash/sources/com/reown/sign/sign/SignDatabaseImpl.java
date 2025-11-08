package com.reown.sign.sign;

import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.AfterVersion;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import com.reown.sign.SignDatabase;
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
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00013B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020,X\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u000200X\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u00064"}, d2 = {"Lcom/reown/sign/sign/SignDatabaseImpl;", "Lapp/cash/sqldelight/TransacterImpl;", "Lcom/reown/sign/SignDatabase;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "NamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;", "OptionalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;", "ProposalDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;", "ProposalNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao$Adapter;", "SessionDaoAdapter", "Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;", "TempNamespaceDaoAdapter", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/sign/storage/data/dao/namespace/NamespaceDao$Adapter;Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDao$Adapter;Lcom/reown/sign/storage/data/dao/proposal/ProposalDao$Adapter;Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDao$Adapter;Lcom/reown/sign/storage/data/dao/session/SessionDao$Adapter;Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDao$Adapter;)V", "authenticateResponseTopicDaoQueries", "Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "getAuthenticateResponseTopicDaoQueries", "()Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "linkModeDaoQueries", "Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "getLinkModeDaoQueries", "()Lcom/reown/sign/storage/data/dao/linkmode/LinkModeDaoQueries;", "namespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "getNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/namespace/NamespaceDaoQueries;", "optionalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "getOptionalNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/optionalnamespaces/OptionalNamespaceDaoQueries;", "proposalDaoQueries", "Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "getProposalDaoQueries", "()Lcom/reown/sign/storage/data/dao/proposal/ProposalDaoQueries;", "proposalNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "getProposalNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/proposalnamespace/ProposalNamespaceDaoQueries;", "sessionDaoQueries", "Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "getSessionDaoQueries", "()Lcom/reown/sign/storage/data/dao/session/SessionDaoQueries;", "tempNamespaceDaoQueries", "Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "getTempNamespaceDaoQueries", "()Lcom/reown/sign/storage/data/dao/temp/TempNamespaceDaoQueries;", "Schema", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class SignDatabaseImpl extends TransacterImpl implements SignDatabase {
    @NotNull
    private final AuthenticateResponseTopicDaoQueries authenticateResponseTopicDaoQueries;
    @NotNull
    private final LinkModeDaoQueries linkModeDaoQueries;
    @NotNull
    private final NamespaceDaoQueries namespaceDaoQueries;
    @NotNull
    private final OptionalNamespaceDaoQueries optionalNamespaceDaoQueries;
    @NotNull
    private final ProposalDaoQueries proposalDaoQueries;
    @NotNull
    private final ProposalNamespaceDaoQueries proposalNamespaceDaoQueries;
    @NotNull
    private final SessionDaoQueries sessionDaoQueries;
    @NotNull
    private final TempNamespaceDaoQueries tempNamespaceDaoQueries;

    @SourceDebugExtension({"SMAP\nSignDatabaseImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignDatabaseImpl.kt\ncom/reown/sign/sign/SignDatabaseImpl$Schema\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,908:1\n3829#2:909\n4344#2,2:910\n1056#3:912\n1869#3,2:913\n*S KotlinDebug\n*F\n+ 1 SignDatabaseImpl.kt\ncom/reown/sign/sign/SignDatabaseImpl$Schema\n*L\n893#1:909\n893#1:910,2\n894#1:912\n895#1:913,2\n*E\n"})
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0012\u0010\u0013JA\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lcom/reown/sign/sign/SignDatabaseImpl$Schema;", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "<init>", "()V", "version", "", "getVersion", "()J", "create", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "create-0iQ1-z0", "(Lapp/cash/sqldelight/db/SqlDriver;)Ljava/lang/Object;", "migrateInternal", "oldVersion", "newVersion", "migrateInternal-ElmaSbI", "(Lapp/cash/sqldelight/db/SqlDriver;JJ)Ljava/lang/Object;", "migrate", "callbacks", "", "Lapp/cash/sqldelight/db/AfterVersion;", "migrate-zeHU3Mk", "(Lapp/cash/sqldelight/db/SqlDriver;JJ[Lapp/cash/sqldelight/db/AfterVersion;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Schema implements SqlSchema<QueryResult.Value<Unit>> {
        @NotNull
        public static final Schema INSTANCE = new Schema();

        private Schema() {
        }

        /* renamed from: migrateInternal-ElmaSbI  reason: not valid java name */
        private final Object m8895migrateInternalElmaSbI(SqlDriver sqlDriver, long j2, long j3) {
            if (j2 <= 1 && j3 > 1) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS JsonRpcHistoryDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver2 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver3 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "DROP TABLE IF EXISTS PairingDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE JsonRpcHistoryDao(\n  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n  request_id INTEGER UNIQUE NOT NULL,\n  topic TEXT NOT NULL,\n  method TEXT NOT NULL,\n  body TEXT NOT NULL,\n  response TEXT\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE MetaDataDao(\n\tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\tsequence_topic TEXT NOT NULL,\n  \tname TEXT NOT NULL,\n  \tdescription TEXT NOT NULL,\n  \turl TEXT NOT NULL,\n  \ticons TEXT NOT NULL,\n  \ttype TEXT NOT NULL,\n  \tUNIQUE(sequence_topic, type)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE NamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE PairingDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    topic TEXT UNIQUE NOT NULL,\n    expiry INTEGER NOT NULL,\n    relay_protocol TEXT NOT NULL,\n    relay_data TEXT,\n    uri TEXT NOT NULL,\n    is_active INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 2 && j3 > 2) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS JsonRpcHistoryDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver4 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver5 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "DROP TABLE IF EXISTS PairingDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "CREATE TABLE JsonRpcHistoryDao(\n  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n  request_id INTEGER UNIQUE NOT NULL,\n  topic TEXT NOT NULL,\n  method TEXT NOT NULL,\n  body TEXT NOT NULL,\n  response TEXT\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "CREATE TABLE MetaDataDao(\n\tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\tsequence_topic TEXT NOT NULL,\n  \tname TEXT NOT NULL,\n  \tdescription TEXT NOT NULL,\n  \turl TEXT NOT NULL,\n  \ticons TEXT NOT NULL,\n  \ttype TEXT NOT NULL,\n  \tUNIQUE(sequence_topic, type)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "CREATE TABLE NamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "CREATE TABLE PairingDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    topic TEXT UNIQUE NOT NULL,\n    expiry INTEGER NOT NULL,\n    relay_protocol TEXT NOT NULL,\n    relay_data TEXT,\n    uri TEXT NOT NULL,\n    is_active INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "CREATE TABLE ProposalNamespaceExtensionsDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver4, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver5, (Integer) null, "CREATE TABLE TempNamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL,\n    request_id INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 3 && j3 > 3) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS JsonRpcHistoryDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver6 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver7 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "DROP TABLE IF EXISTS PairingDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "CREATE TABLE JsonRpcHistoryDao(\n  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n  request_id INTEGER UNIQUE NOT NULL,\n  topic TEXT NOT NULL,\n  method TEXT NOT NULL,\n  body TEXT NOT NULL,\n  response TEXT\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "CREATE TABLE MetaDataDao(\n\tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\tsequence_topic TEXT NOT NULL,\n  \tname TEXT NOT NULL,\n  \tdescription TEXT NOT NULL,\n  \turl TEXT NOT NULL,\n  \ticons TEXT NOT NULL,\n  \ttype TEXT NOT NULL,\n  \tUNIQUE(sequence_topic, type)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "CREATE TABLE NamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "CREATE TABLE PairingDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    topic TEXT UNIQUE NOT NULL,\n    expiry INTEGER NOT NULL,\n    relay_protocol TEXT NOT NULL,\n    relay_data TEXT,\n    uri TEXT NOT NULL,\n    is_active INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "CREATE TABLE ProposalNamespaceExtensionsDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver6, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    request_id INTEGER PRIMARY KEY NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver7, (Integer) null, "CREATE TABLE TempNamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL,\n    request_id INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 4 && j3 > 4) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS JsonRpcHistoryDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver8 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver9 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "DROP TABLE IF EXISTS PairingDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "CREATE TABLE JsonRpcHistoryDao(\n  id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n  request_id INTEGER UNIQUE NOT NULL,\n  topic TEXT NOT NULL,\n  method TEXT NOT NULL,\n  body TEXT NOT NULL,\n  response TEXT\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "CREATE TABLE MetaDataDao(\n\tid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n\tsequence_topic TEXT NOT NULL,\n  \tname TEXT NOT NULL,\n  \tdescription TEXT NOT NULL,\n  \turl TEXT NOT NULL,\n  \tnative TEXT,\n    icons TEXT NOT NULL,\n    type TEXT NOT NULL,\n  \tUNIQUE(sequence_topic, type)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "CREATE TABLE NamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "CREATE TABLE PairingDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    topic TEXT UNIQUE NOT NULL,\n    expiry INTEGER NOT NULL,\n    relay_protocol TEXT NOT NULL,\n    relay_data TEXT,\n    uri TEXT NOT NULL,\n    is_active INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "CREATE TABLE ProposalNamespaceExtensionsDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver8, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    request_id INTEGER PRIMARY KEY NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver9, (Integer) null, "CREATE TABLE TempNamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL,\n    request_id INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 5 && j3 > 5) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS JsonRpcHistoryDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver10 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "DROP TABLE IF EXISTS MetaDataDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver11 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "DROP TABLE IF EXISTS PairingDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "CREATE TABLE NamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "CREATE TABLE ProposalNamespaceExtensionsDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    chains TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver10, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    request_id INTEGER PRIMARY KEY NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver11, (Integer) null, "CREATE TABLE TempNamespaceExtensionsDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    namespace_key TEXT NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT  NOT NULL,\n    request_id INTEGER NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 6 && j3 > 6) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS NamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver12 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "DROP TABLE IF EXISTS SessionDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver sqlDriver13 = sqlDriver;
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "DROP TABLE IF EXISTS ProposalNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "DROP TABLE IF EXISTS TempNamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "DROP TABLE IF EXISTS NamespaceExtensionsDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "DROP TABLE IF EXISTS OptionalNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "DROP TABLE IF EXISTS ProposalDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL,\n   properties TEXT\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    request_id INTEGER PRIMARY KEY NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver13, (Integer) null, "CREATE TABLE OptionalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver12, (Integer) null, "CREATE TABLE ProposalDao (\n   request_id INTEGER PRIMARY KEY NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   name TEXT NOT NULL,\n   description TEXT NOT NULL,\n   url TEXT NOT NULL,\n   icons TEXT NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   proposer_key TEXT NOT NULL,\n   properties TEXT\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 7 && j3 > 7) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE ProposalDao ADD COLUMN redirect TEXT NOT NULL DEFAULT \"\"", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 8 && j3 > 8) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE IF EXISTS ProposalDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE ProposalDao (\n   request_id INTEGER PRIMARY KEY NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   name TEXT NOT NULL,\n   description TEXT NOT NULL,\n   url TEXT NOT NULL,\n   icons TEXT NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   proposer_key TEXT NOT NULL,\n   properties TEXT,\n   redirect TEXT NOT NULL DEFAULT \"\",\n   expiry INTEGER\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 9 && j3 > 9) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE AuthenticateResponseTopicDao (\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   responseTopic TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 10 && j3 > 10) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE SessionDao ADD COLUMN transport_type TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE LinkModeDao (\n    app_link TEXT NOT NULL UNIQUE\n)", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 11 && j3 > 11) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE SessionDao ADD COLUMN scoped_properties TEXT", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE ProposalDao ADD COLUMN scoped_properties TEXT", 0, (Function1) null, 8, (Object) null);
            }
            if (j2 <= 12 && j3 > 12) {
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "CREATE TABLE TempNamespaceDao_new(\n    request_id INTEGER NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "INSERT INTO TempNamespaceDao_new \nSELECT request_id, session_id, topic, key, chains, accounts, methods, events, isAcknowledged \nFROM TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "DROP TABLE TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
                SqlDriver.DefaultImpls.execute$default(sqlDriver, (Integer) null, "ALTER TABLE TempNamespaceDao_new RENAME TO TempNamespaceDao", 0, (Function1) null, 8, (Object) null);
            }
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }

        public /* bridge */ /* synthetic */ QueryResult create(SqlDriver sqlDriver) {
            return QueryResult.Value.m8089boximpl(m8896create0iQ1z0(sqlDriver));
        }

        @NotNull
        /* renamed from: create-0iQ1-z0  reason: not valid java name */
        public Object m8896create0iQ1z0(@NotNull SqlDriver sqlDriver) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            SqlDriver sqlDriver2 = sqlDriver;
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE AuthenticateResponseTopicDao (\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   responseTopic TEXT NOT NULL\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver sqlDriver3 = sqlDriver;
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE LinkModeDao (\n    app_link TEXT NOT NULL UNIQUE\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE NamespaceDao(\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    request_id INTEGER NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE OptionalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE ProposalDao (\n   request_id INTEGER PRIMARY KEY NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   name TEXT NOT NULL,\n   description TEXT NOT NULL,\n   url TEXT NOT NULL,\n   icons TEXT NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   proposer_key TEXT NOT NULL,\n   properties TEXT,\n   redirect TEXT NOT NULL DEFAULT \"\",\n   expiry INTEGER,\n   scoped_properties TEXT\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE ProposalNamespaceDao (\n    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n    session_id INTEGER NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    UNIQUE(session_id, key)\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver2, (Integer) null, "CREATE TABLE SessionDao(\n   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n   topic TEXT UNIQUE NOT NULL,\n   pairingTopic TEXT NOT NULL,\n   expiry INTEGER NOT NULL,\n   relay_protocol TEXT NOT NULL,\n   relay_data TEXT,\n   controller_key TEXT,\n   self_participant TEXT NOT NULL,\n   peer_participant TEXT,\n   is_acknowledged INTEGER NOT NULL,\n   properties TEXT,\n   transport_type TEXT,\n   scoped_properties TEXT\n)", 0, (Function1) null, 8, (Object) null);
            SqlDriver.DefaultImpls.execute$default(sqlDriver3, (Integer) null, "CREATE TABLE TempNamespaceDao(\n    request_id INTEGER NOT NULL,\n    session_id INTEGER NOT NULL,\n    topic TEXT NOT NULL,\n    key TEXT NOT NULL,\n    chains TEXT,\n    accounts TEXT NOT NULL,\n    methods TEXT NOT NULL,\n    events TEXT NOT NULL,\n    isAcknowledged INTEGER DEFAULT 0\n)", 0, (Function1) null, 8, (Object) null);
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }

        public long getVersion() {
            return 13;
        }

        public /* bridge */ /* synthetic */ QueryResult migrate(SqlDriver sqlDriver, long j2, long j3, AfterVersion[] afterVersionArr) {
            return QueryResult.Value.m8089boximpl(m8897migratezeHU3Mk(sqlDriver, j2, j3, afterVersionArr));
        }

        @NotNull
        /* renamed from: migrate-zeHU3Mk  reason: not valid java name */
        public Object m8897migratezeHU3Mk(@NotNull SqlDriver sqlDriver, long j2, long j3, @NotNull AfterVersion... afterVersionArr) {
            Intrinsics.checkNotNullParameter(sqlDriver, "driver");
            Intrinsics.checkNotNullParameter(afterVersionArr, "callbacks");
            ArrayList arrayList = new ArrayList();
            for (AfterVersion afterVersion : afterVersionArr) {
                long afterVersion2 = afterVersion.getAfterVersion();
                if (j2 <= afterVersion2 && afterVersion2 < j3) {
                    arrayList.add(afterVersion);
                }
            }
            long j4 = j2;
            for (AfterVersion afterVersion3 : CollectionsKt.sortedWith(arrayList, new SignDatabaseImpl$Schema$migratezeHU3Mk$$inlined$sortedBy$1())) {
                INSTANCE.m8895migrateInternalElmaSbI(sqlDriver, j4, afterVersion3.getAfterVersion() + 1);
                afterVersion3.getBlock().invoke(sqlDriver);
                j4 = afterVersion3.getAfterVersion() + 1;
            }
            if (j4 < j3) {
                m8895migrateInternalElmaSbI(sqlDriver, j4, j3);
            }
            return QueryResult.Companion.m8087getUnitmlRZEE();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignDatabaseImpl(@NotNull SqlDriver sqlDriver, @NotNull NamespaceDao.Adapter adapter, @NotNull OptionalNamespaceDao.Adapter adapter2, @NotNull ProposalDao.Adapter adapter3, @NotNull ProposalNamespaceDao.Adapter adapter4, @NotNull SessionDao.Adapter adapter5, @NotNull TempNamespaceDao.Adapter adapter6) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "NamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter2, "OptionalNamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter3, "ProposalDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter4, "ProposalNamespaceDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter5, "SessionDaoAdapter");
        Intrinsics.checkNotNullParameter(adapter6, "TempNamespaceDaoAdapter");
        this.authenticateResponseTopicDaoQueries = new AuthenticateResponseTopicDaoQueries(sqlDriver);
        this.linkModeDaoQueries = new LinkModeDaoQueries(sqlDriver);
        this.namespaceDaoQueries = new NamespaceDaoQueries(sqlDriver, adapter);
        this.optionalNamespaceDaoQueries = new OptionalNamespaceDaoQueries(sqlDriver, adapter2);
        this.proposalDaoQueries = new ProposalDaoQueries(sqlDriver, adapter3);
        this.proposalNamespaceDaoQueries = new ProposalNamespaceDaoQueries(sqlDriver, adapter4);
        this.sessionDaoQueries = new SessionDaoQueries(sqlDriver, adapter5);
        this.tempNamespaceDaoQueries = new TempNamespaceDaoQueries(sqlDriver, adapter6);
    }

    @NotNull
    public AuthenticateResponseTopicDaoQueries getAuthenticateResponseTopicDaoQueries() {
        return this.authenticateResponseTopicDaoQueries;
    }

    @NotNull
    public LinkModeDaoQueries getLinkModeDaoQueries() {
        return this.linkModeDaoQueries;
    }

    @NotNull
    public NamespaceDaoQueries getNamespaceDaoQueries() {
        return this.namespaceDaoQueries;
    }

    @NotNull
    public OptionalNamespaceDaoQueries getOptionalNamespaceDaoQueries() {
        return this.optionalNamespaceDaoQueries;
    }

    @NotNull
    public ProposalDaoQueries getProposalDaoQueries() {
        return this.proposalDaoQueries;
    }

    @NotNull
    public ProposalNamespaceDaoQueries getProposalNamespaceDaoQueries() {
        return this.proposalNamespaceDaoQueries;
    }

    @NotNull
    public SessionDaoQueries getSessionDaoQueries() {
        return this.sessionDaoQueries;
    }

    @NotNull
    public TempNamespaceDaoQueries getTempNamespaceDaoQueries() {
        return this.tempNamespaceDaoQueries;
    }
}
