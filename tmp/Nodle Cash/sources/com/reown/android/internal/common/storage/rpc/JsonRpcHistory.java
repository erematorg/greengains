package com.reown.android.internal.common.storage.rpc;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.android.internal.common.json_rpc.model.JsonRpcHistoryRecord;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryQueries;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000fJ\"\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000bJ\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u001d2\u0006\u0010\f\u001a\u00020\rJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00140\u001dJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140\u001dJ\u0010\u0010 \u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001b\u001a\u00020\u000bJ\u0010\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001b\u001a\u00020\u000bJ<\u0010\"\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/reown/android/internal/common/storage/rpc/JsonRpcHistory;", "", "jsonRpcHistoryQueries", "Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;", "logger", "Lcom/reown/foundation/util/Logger;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/JsonRpcHistoryQueries;Lcom/reown/foundation/util/Logger;)V", "setRequest", "", "requestId", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "method", "", "payload", "transportType", "Lcom/reown/android/internal/common/model/TransportType;", "updateRequestWithResponse", "Lcom/reown/android/internal/common/json_rpc/model/JsonRpcHistoryRecord;", "response", "updateRecord", "record", "deleteRecordsByTopic", "", "deleteRecordById", "id", "getListOfPendingRecordsByTopic", "", "getListOfPendingRecords", "getListOfPendingSessionRequests", "getPendingRecordById", "getRecordById", "toRecord", "body", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonRpcHistory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonRpcHistory.kt\ncom/reown/android/internal/common/storage/rpc/JsonRpcHistory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,80:1\n774#2:81\n865#2,2:82\n774#2:84\n865#2,2:85\n*S KotlinDebug\n*F\n+ 1 JsonRpcHistory.kt\ncom/reown/android/internal/common/storage/rpc/JsonRpcHistory\n*L\n59#1:81\n59#1:82,2\n64#1:84\n64#1:85,2\n*E\n"})
public final class JsonRpcHistory {
    @NotNull
    private final JsonRpcHistoryQueries jsonRpcHistoryQueries;
    @NotNull
    private final Logger logger;

    public JsonRpcHistory(@NotNull JsonRpcHistoryQueries jsonRpcHistoryQueries2, @NotNull Logger logger2) {
        Intrinsics.checkNotNullParameter(jsonRpcHistoryQueries2, "jsonRpcHistoryQueries");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        this.jsonRpcHistoryQueries = jsonRpcHistoryQueries2;
        this.logger = logger2;
    }

    /* access modifiers changed from: private */
    public final JsonRpcHistoryRecord toRecord(long j2, String str, String str2, String str3, String str4, TransportType transportType) {
        return new JsonRpcHistoryRecord(j2, str, str2, str3, str4, transportType);
    }

    private final JsonRpcHistoryRecord updateRecord(JsonRpcHistoryRecord jsonRpcHistoryRecord, long j2, String str) {
        if (jsonRpcHistoryRecord.getResponse() != null) {
            Logger logger2 = this.logger;
            logger2.log("Duplicated JsonRpc RequestId: " + j2);
            return null;
        }
        this.jsonRpcHistoryQueries.updateJsonRpcHistory(str, j2);
        return jsonRpcHistoryRecord;
    }

    public final void deleteRecordById(long j2) {
        this.jsonRpcHistoryQueries.deleteJsonRpcHistoryByRequestId(j2);
    }

    public final void deleteRecordsByTopic(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.jsonRpcHistoryQueries.deleteJsonRpcHistory(topic.getValue());
    }

    @NotNull
    public final List<JsonRpcHistoryRecord> getListOfPendingRecords() {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.jsonRpcHistoryQueries.getJsonRpcRecords(new JsonRpcHistory$getListOfPendingRecords$1(this)).executeAsList()) {
            if (((JsonRpcHistoryRecord) next).getResponse() == null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    @NotNull
    public final List<JsonRpcHistoryRecord> getListOfPendingRecordsByTopic(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        ArrayList arrayList = new ArrayList();
        for (Object next : this.jsonRpcHistoryQueries.getJsonRpcRecordsByTopic(topic.getValue(), new JsonRpcHistory$getListOfPendingRecordsByTopic$1(this)).executeAsList()) {
            if (((JsonRpcHistoryRecord) next).getResponse() == null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    @NotNull
    public final List<JsonRpcHistoryRecord> getListOfPendingSessionRequests() {
        return this.jsonRpcHistoryQueries.getPendingSessionRequests(new JsonRpcHistory$getListOfPendingSessionRequests$1(this)).executeAsList();
    }

    @Nullable
    public final JsonRpcHistoryRecord getPendingRecordById(long j2) {
        JsonRpcHistoryRecord jsonRpcHistoryRecord = (JsonRpcHistoryRecord) this.jsonRpcHistoryQueries.getJsonRpcHistoryRecord(j2, new JsonRpcHistory$getPendingRecordById$record$1(this)).executeAsOneOrNull();
        if (jsonRpcHistoryRecord == null || jsonRpcHistoryRecord.getResponse() != null) {
            return null;
        }
        return jsonRpcHistoryRecord;
    }

    @Nullable
    public final JsonRpcHistoryRecord getRecordById(long j2) {
        return (JsonRpcHistoryRecord) this.jsonRpcHistoryQueries.getJsonRpcHistoryRecord(j2, new JsonRpcHistory$getRecordById$1(this)).executeAsOneOrNull();
    }

    public final boolean setRequest(long j2, @NotNull Topic topic, @NotNull String str, @NotNull String str2, @NotNull TransportType transportType) {
        long j3 = j2;
        Topic topic2 = topic;
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        String str3 = str;
        Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.METHOD);
        String str4 = str2;
        Intrinsics.checkNotNullParameter(str2, "payload");
        Intrinsics.checkNotNullParameter(transportType, "transportType");
        try {
            if (this.jsonRpcHistoryQueries.doesJsonRpcNotExist(j2).executeAsOne().booleanValue()) {
                this.jsonRpcHistoryQueries.insertOrAbortJsonRpcHistory(j2, topic.getValue(), str, str2, transportType);
                return this.jsonRpcHistoryQueries.selectLastInsertedRowId().executeAsOne().longValue() > 0;
            }
            Logger logger2 = this.logger;
            logger2.error("Duplicated JsonRpc RequestId: " + j2);
            return false;
        } catch (Exception e3) {
            this.logger.error((Throwable) e3);
            return false;
        }
    }

    @Nullable
    public final JsonRpcHistoryRecord updateRequestWithResponse(long j2, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "response");
        JsonRpcHistoryRecord jsonRpcHistoryRecord = (JsonRpcHistoryRecord) this.jsonRpcHistoryQueries.getJsonRpcHistoryRecord(j2, new JsonRpcHistory$updateRequestWithResponse$record$1(this)).executeAsOneOrNull();
        if (jsonRpcHistoryRecord != null) {
            return updateRecord(jsonRpcHistoryRecord, j2, str);
        }
        this.logger.log("No JsonRpcRequest matching response");
        return null;
    }
}
