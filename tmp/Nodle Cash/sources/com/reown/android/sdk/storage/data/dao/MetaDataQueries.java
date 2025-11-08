package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.ColumnAdapter;
import app.cash.sqldelight.Query;
import app.cash.sqldelight.TransacterImpl;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlCursor;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlPreparedStatement;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reown.android.internal.common.di.b;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.sdk.storage.data.dao.MetaData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0002'(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JÕ\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2¬\u0001\u0010\u0010\u001a§\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\r0\u0016¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0019\u0012\u0015\u0012\u0013\u0018\u00010\u001a¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u0002H\n0\u0011J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u001c0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ_\u0010\u001f\u001a\u00020 2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a¢\u0006\u0002\u0010!J_\u0010\"\u001a\u00020 2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\r2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020 2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010%\u001a\u00020\rJ\u000e\u0010&\u001a\u00020 2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "Lapp/cash/sqldelight/TransacterImpl;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "MetaDataAdapter", "Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;", "<init>", "(Lapp/cash/sqldelight/db/SqlDriver;Lcom/reown/android/sdk/storage/data/dao/MetaData$Adapter;)V", "getMetadataByTopicAndType", "Lapp/cash/sqldelight/Query;", "T", "", "sequence_topic", "", "type", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "mapper", "Lkotlin/Function7;", "Lkotlin/ParameterName;", "name", "description", "url", "", "icons", "native", "app_link", "", "link_mode", "Lcom/reown/android/sdk/storage/data/dao/GetMetadataByTopicAndType;", "getIdByTopicAndType", "", "insertOrAbortMetaData", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Ljava/lang/String;Ljava/lang/Boolean;)V", "updateMetaData", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "updateOrAbortMetaDataTopic", "sequence_topic_", "deleteMetaDataFromTopic", "GetMetadataByTopicAndTypeQuery", "GetIdByTopicAndTypeQuery", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MetaDataQueries extends TransacterImpl {
    /* access modifiers changed from: private */
    @NotNull
    public final MetaData.Adapter MetaDataAdapter;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\u0004\b\u0001\u0010\u00182\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00170\tH\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries$GetIdByTopicAndTypeQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "sequence_topic", "", "type", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Lkotlin/jvm/functions/Function1;)V", "getSequence_topic", "()Ljava/lang/String;", "getType", "()Lcom/reown/android/internal/common/model/AppMetaDataType;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetIdByTopicAndTypeQuery<T> extends Query<T> {
        @NotNull
        private final String sequence_topic;
        final /* synthetic */ MetaDataQueries this$0;
        @NotNull
        private final AppMetaDataType type;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetIdByTopicAndTypeQuery(@NotNull MetaDataQueries metaDataQueries, @NotNull String str, @NotNull AppMetaDataType appMetaDataType, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "sequence_topic");
            Intrinsics.checkNotNullParameter(appMetaDataType, "type");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = metaDataQueries;
            this.sequence_topic = str;
            this.type = appMetaDataType;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetIdByTopicAndTypeQuery getIdByTopicAndTypeQuery, MetaDataQueries metaDataQueries, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getIdByTopicAndTypeQuery.sequence_topic);
            sqlPreparedStatement.bindString(1, metaDataQueries.MetaDataAdapter.getTypeAdapter().encode(getIdByTopicAndTypeQuery.type));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"MetaData"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(1008864655, "SELECT id\nFROM MetaData\nWHERE sequence_topic = ? AND type = ?", function1, 2, new l(this, this.this$0, 0));
        }

        @NotNull
        public final String getSequence_topic() {
            return this.sequence_topic;
        }

        @NotNull
        public final AppMetaDataType getType() {
            return this.type;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"MetaData"}, listener);
        }

        @NotNull
        public String toString() {
            return "MetaData.sq:getIdByTopicAndType";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0017\"\u0004\b\u0001\u0010\u00182\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00170\tH\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries$GetMetadataByTopicAndTypeQuery;", "T", "", "Lapp/cash/sqldelight/Query;", "sequence_topic", "", "type", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "mapper", "Lkotlin/Function1;", "Lapp/cash/sqldelight/db/SqlCursor;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;Ljava/lang/String;Lcom/reown/android/internal/common/model/AppMetaDataType;Lkotlin/jvm/functions/Function1;)V", "getSequence_topic", "()Ljava/lang/String;", "getType", "()Lcom/reown/android/internal/common/model/AppMetaDataType;", "addListener", "", "listener", "Lapp/cash/sqldelight/Query$Listener;", "removeListener", "execute", "Lapp/cash/sqldelight/db/QueryResult;", "R", "toString", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class GetMetadataByTopicAndTypeQuery<T> extends Query<T> {
        @NotNull
        private final String sequence_topic;
        final /* synthetic */ MetaDataQueries this$0;
        @NotNull
        private final AppMetaDataType type;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public GetMetadataByTopicAndTypeQuery(@NotNull MetaDataQueries metaDataQueries, @NotNull String str, @NotNull AppMetaDataType appMetaDataType, Function1<? super SqlCursor, ? extends T> function1) {
            super(function1);
            Intrinsics.checkNotNullParameter(str, "sequence_topic");
            Intrinsics.checkNotNullParameter(appMetaDataType, "type");
            Intrinsics.checkNotNullParameter(function1, "mapper");
            this.this$0 = metaDataQueries;
            this.sequence_topic = str;
            this.type = appMetaDataType;
        }

        /* access modifiers changed from: private */
        public static final Unit execute$lambda$0(GetMetadataByTopicAndTypeQuery getMetadataByTopicAndTypeQuery, MetaDataQueries metaDataQueries, SqlPreparedStatement sqlPreparedStatement) {
            Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$executeQuery");
            sqlPreparedStatement.bindString(0, getMetadataByTopicAndTypeQuery.sequence_topic);
            sqlPreparedStatement.bindString(1, metaDataQueries.MetaDataAdapter.getTypeAdapter().encode(getMetadataByTopicAndTypeQuery.type));
            return Unit.INSTANCE;
        }

        public void addListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().addListener(new String[]{"MetaData"}, listener);
        }

        @NotNull
        public <R> QueryResult<R> execute(@NotNull Function1<? super SqlCursor, ? extends QueryResult<R>> function1) {
            Intrinsics.checkNotNullParameter(function1, "mapper");
            return this.this$0.getDriver().executeQuery(922585187, "SELECT name, description, url, icons, native, app_link, link_mode\nFROM MetaData\nWHERE sequence_topic = ? AND type = ?", function1, 2, new l(this, this.this$0, 1));
        }

        @NotNull
        public final String getSequence_topic() {
            return this.sequence_topic;
        }

        @NotNull
        public final AppMetaDataType getType() {
            return this.type;
        }

        public void removeListener(@NotNull Query.Listener listener) {
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.this$0.getDriver().removeListener(new String[]{"MetaData"}, listener);
        }

        @NotNull
        public String toString() {
            return "MetaData.sq:getMetadataByTopicAndType";
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MetaDataQueries(@NotNull SqlDriver sqlDriver, @NotNull MetaData.Adapter adapter) {
        super(sqlDriver);
        Intrinsics.checkNotNullParameter(sqlDriver, "driver");
        Intrinsics.checkNotNullParameter(adapter, "MetaDataAdapter");
        this.MetaDataAdapter = adapter;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteMetaDataFromTopic$lambda$10(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("MetaData");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit deleteMetaDataFromTopic$lambda$9(String str, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final long getIdByTopicAndType$lambda$2(SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        Long l2 = sqlCursor.getLong(0);
        Intrinsics.checkNotNull(l2);
        return l2.longValue();
    }

    /* access modifiers changed from: private */
    public static final Object getMetadataByTopicAndType$lambda$0(Function7 function7, MetaDataQueries metaDataQueries, SqlCursor sqlCursor) {
        Intrinsics.checkNotNullParameter(sqlCursor, "cursor");
        String string = sqlCursor.getString(0);
        Intrinsics.checkNotNull(string);
        String string2 = sqlCursor.getString(1);
        Intrinsics.checkNotNull(string2);
        String string3 = sqlCursor.getString(2);
        Intrinsics.checkNotNull(string3);
        ColumnAdapter<List<String>, String> iconsAdapter = metaDataQueries.MetaDataAdapter.getIconsAdapter();
        String string4 = sqlCursor.getString(3);
        Intrinsics.checkNotNull(string4);
        return function7.invoke(string, string2, string3, iconsAdapter.decode(string4), sqlCursor.getString(4), sqlCursor.getString(5), sqlCursor.getBoolean(6));
    }

    /* access modifiers changed from: private */
    public static final GetMetadataByTopicAndType getMetadataByTopicAndType$lambda$1(String str, String str2, String str3, List list, String str4, String str5, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "description");
        Intrinsics.checkNotNullParameter(str3, "url");
        Intrinsics.checkNotNullParameter(list, "icons");
        return new GetMetadataByTopicAndType(str, str2, str3, list, str4, str5, bool);
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortMetaData$lambda$3(String str, String str2, String str3, String str4, MetaDataQueries metaDataQueries, List list, String str5, AppMetaDataType appMetaDataType, String str6, Boolean bool, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        sqlPreparedStatement.bindString(2, str3);
        sqlPreparedStatement.bindString(3, str4);
        sqlPreparedStatement.bindString(4, metaDataQueries.MetaDataAdapter.getIconsAdapter().encode(list));
        sqlPreparedStatement.bindString(5, str5);
        sqlPreparedStatement.bindString(6, metaDataQueries.MetaDataAdapter.getTypeAdapter().encode(appMetaDataType));
        sqlPreparedStatement.bindString(7, str6);
        sqlPreparedStatement.bindBoolean(8, bool);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit insertOrAbortMetaData$lambda$4(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("MetaData");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateMetaData$lambda$5(String str, String str2, String str3, MetaDataQueries metaDataQueries, List list, String str4, AppMetaDataType appMetaDataType, String str5, Boolean bool, String str6, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        sqlPreparedStatement.bindString(2, str3);
        sqlPreparedStatement.bindString(3, metaDataQueries.MetaDataAdapter.getIconsAdapter().encode(list));
        sqlPreparedStatement.bindString(4, str4);
        sqlPreparedStatement.bindString(5, metaDataQueries.MetaDataAdapter.getTypeAdapter().encode(appMetaDataType));
        sqlPreparedStatement.bindString(6, str5);
        sqlPreparedStatement.bindBoolean(7, bool);
        sqlPreparedStatement.bindString(8, str6);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateMetaData$lambda$6(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("MetaData");
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateOrAbortMetaDataTopic$lambda$7(String str, String str2, SqlPreparedStatement sqlPreparedStatement) {
        Intrinsics.checkNotNullParameter(sqlPreparedStatement, "$this$execute");
        sqlPreparedStatement.bindString(0, str);
        sqlPreparedStatement.bindString(1, str2);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit updateOrAbortMetaDataTopic$lambda$8(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "emit");
        function1.invoke("MetaData");
        return Unit.INSTANCE;
    }

    public final void deleteMetaDataFromTopic(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        getDriver().execute(-118414128, "DELETE FROM MetaData\nWHERE sequence_topic = ?", 1, new d(str, 2));
        notifyQueries(-118414128, new k(2));
    }

    @NotNull
    public final Query<Long> getIdByTopicAndType(@NotNull String str, @NotNull AppMetaDataType appMetaDataType) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        return new GetIdByTopicAndTypeQuery(this, str, appMetaDataType, new k(1));
    }

    @NotNull
    public final <T> Query<T> getMetadataByTopicAndType(@NotNull String str, @NotNull AppMetaDataType appMetaDataType, @NotNull Function7<? super String, ? super String, ? super String, ? super List<String>, ? super String, ? super String, ? super Boolean, ? extends T> function7) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        Intrinsics.checkNotNullParameter(function7, "mapper");
        return new GetMetadataByTopicAndTypeQuery(this, str, appMetaDataType, new l(function7, this, 3));
    }

    public final void insertOrAbortMetaData(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull List<String> list, @Nullable String str5, @NotNull AppMetaDataType appMetaDataType, @Nullable String str6, @Nullable Boolean bool) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, "sequence_topic");
        String str8 = str2;
        Intrinsics.checkNotNullParameter(str8, "name");
        String str9 = str3;
        Intrinsics.checkNotNullParameter(str9, "description");
        String str10 = str4;
        Intrinsics.checkNotNullParameter(str10, "url");
        List<String> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        AppMetaDataType appMetaDataType2 = appMetaDataType;
        Intrinsics.checkNotNullParameter(appMetaDataType2, "type");
        getDriver().execute(-203069208, "INSERT OR ABORT INTO MetaData(sequence_topic, name, description, url, icons, native, type, app_link, link_mode)\nVALUES (?,?,?,?,?,?,?,?,?)", 9, new j(str7, str8, str9, str10, this, (List) list2, str5, appMetaDataType2, str6, bool));
        notifyQueries(-203069208, new k(0));
    }

    public final void updateMetaData(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list, @Nullable String str4, @NotNull AppMetaDataType appMetaDataType, @Nullable String str5, @Nullable Boolean bool, @NotNull String str6) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, "name");
        String str8 = str2;
        Intrinsics.checkNotNullParameter(str8, "description");
        String str9 = str3;
        Intrinsics.checkNotNullParameter(str9, "url");
        List<String> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "icons");
        AppMetaDataType appMetaDataType2 = appMetaDataType;
        Intrinsics.checkNotNullParameter(appMetaDataType2, "type");
        String str10 = str6;
        Intrinsics.checkNotNullParameter(str10, "sequence_topic");
        getDriver().execute(-1013678221, "UPDATE MetaData\nSET name = ?, description = ?, url = ?, icons = ?, native = ?, type = ?, app_link = ?, link_mode = ?\nWHERE sequence_topic = ?", 9, new j(str7, str8, str9, this, (List) list2, str4, appMetaDataType2, str5, bool, str10));
        notifyQueries(-1013678221, new k(3));
    }

    public final void updateOrAbortMetaDataTopic(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        Intrinsics.checkNotNullParameter(str2, "sequence_topic_");
        getDriver().execute(1035703095, "UPDATE OR ABORT MetaData\nSET sequence_topic = ?\nWHERE sequence_topic = ?", 2, new l(str, str2, 4));
        notifyQueries(1035703095, new b(29));
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [kotlin.jvm.functions.Function7, java.lang.Object] */
    @NotNull
    public final Query<GetMetadataByTopicAndType> getMetadataByTopicAndType(@NotNull String str, @NotNull AppMetaDataType appMetaDataType) {
        Intrinsics.checkNotNullParameter(str, "sequence_topic");
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        return getMetadataByTopicAndType(str, appMetaDataType, new Object());
    }
}
