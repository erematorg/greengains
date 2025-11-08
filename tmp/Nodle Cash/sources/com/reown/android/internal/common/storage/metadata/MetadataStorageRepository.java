package com.reown.android.internal.common.storage.metadata;

import android.database.sqlite.SQLiteException;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.MetaDataQueries;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH@¢\u0006\u0002\u0010\u0012J \u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0014\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\rH\u0016J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\rH\u0016JQ\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001b2\b\u0010!\u001a\u0004\u0018\u00010\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepository;", "Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "metaDataQueries", "Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/MetaDataQueries;)V", "insertOrAbortMetadata", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "appMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "appMetaDataType", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "updateMetaData", "updateOrAbortMetaDataTopic", "oldTopic", "newTopic", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertPeerMetadata", "deleteMetaData", "existsByTopicAndType", "", "type", "getByTopicAndType", "toMetadata", "peerName", "", "peerDesc", "peerUrl", "peerIcons", "", "native", "appLink", "linkMode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/reown/android/internal/common/model/AppMetaData;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MetadataStorageRepository implements MetadataStorageRepositoryInterface {
    @NotNull
    private final MetaDataQueries metaDataQueries;

    public MetadataStorageRepository(@NotNull MetaDataQueries metaDataQueries2) {
        Intrinsics.checkNotNullParameter(metaDataQueries2, "metaDataQueries");
        this.metaDataQueries = metaDataQueries2;
    }

    /* access modifiers changed from: private */
    public final AppMetaData toMetadata(String str, String str2, String str3, List<String> list, String str4, String str5, Boolean bool) {
        String str6 = str4;
        return new AppMetaData(str2, str3, list, str, new Redirect(str4, str5, bool != null ? bool.booleanValue() : false), (String) null, 32, (DefaultConstructorMarker) null);
    }

    public void deleteMetaData(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.metaDataQueries.deleteMetaDataFromTopic(topic.getValue());
    }

    public boolean existsByTopicAndType(@NotNull Topic topic, @NotNull AppMetaDataType appMetaDataType) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        return this.metaDataQueries.getIdByTopicAndType(topic.getValue(), appMetaDataType).executeAsOneOrNull() != null;
    }

    @Nullable
    public AppMetaData getByTopicAndType(@NotNull Topic topic, @NotNull AppMetaDataType appMetaDataType) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaDataType, "type");
        return (AppMetaData) this.metaDataQueries.getMetadataByTopicAndType(topic.getValue(), appMetaDataType, new MetadataStorageRepository$getByTopicAndType$1(this)).executeAsOneOrNull();
    }

    public void insertOrAbortMetadata(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaData, "appMetaData");
        Intrinsics.checkNotNullParameter(appMetaDataType, "appMetaDataType");
        MetaDataQueries metaDataQueries2 = this.metaDataQueries;
        String value = topic.getValue();
        String name = appMetaData.getName();
        String description = appMetaData.getDescription();
        String url = appMetaData.getUrl();
        List<String> icons = appMetaData.getIcons();
        Redirect redirect = appMetaData.getRedirect();
        String str = redirect != null ? redirect.getNative() : null;
        Redirect redirect2 = appMetaData.getRedirect();
        String universal = redirect2 != null ? redirect2.getUniversal() : null;
        Redirect redirect3 = appMetaData.getRedirect();
        metaDataQueries2.insertOrAbortMetaData(value, name, description, url, icons, str, appMetaDataType, universal, redirect3 != null ? Boolean.valueOf(redirect3.getLinkMode()) : null);
    }

    public void updateMetaData(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaData, "appMetaData");
        Intrinsics.checkNotNullParameter(appMetaDataType, "appMetaDataType");
        MetaDataQueries metaDataQueries2 = this.metaDataQueries;
        String name = appMetaData.getName();
        String description = appMetaData.getDescription();
        String url = appMetaData.getUrl();
        List<String> icons = appMetaData.getIcons();
        Redirect redirect = appMetaData.getRedirect();
        String str = redirect != null ? redirect.getNative() : null;
        Redirect redirect2 = appMetaData.getRedirect();
        String universal = redirect2 != null ? redirect2.getUniversal() : null;
        Redirect redirect3 = appMetaData.getRedirect();
        metaDataQueries2.updateMetaData(name, description, url, icons, str, appMetaDataType, universal, redirect3 != null ? Boolean.valueOf(redirect3.getLinkMode()) : null, topic.getValue());
    }

    @Nullable
    public Object updateOrAbortMetaDataTopic(@NotNull Topic topic, @NotNull Topic topic2, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        this.metaDataQueries.updateOrAbortMetaDataTopic(topic2.getValue(), topic.getValue());
        return Unit.INSTANCE;
    }

    public void upsertPeerMetadata(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(appMetaData, "appMetaData");
        Intrinsics.checkNotNullParameter(appMetaDataType, "appMetaDataType");
        if (!existsByTopicAndType(topic, appMetaDataType)) {
            insertOrAbortMetadata(topic, appMetaData, appMetaDataType);
        } else {
            updateMetaData(topic, appMetaData, appMetaDataType);
        }
    }
}
