package com.reown.android.internal.common.storage.metadata;

import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\tH&J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\tH&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/reown/android/internal/common/storage/metadata/MetadataStorageRepositoryInterface;", "", "insertOrAbortMetadata", "", "topic", "Lcom/reown/foundation/common/model/Topic;", "appMetaData", "Lcom/reown/android/internal/common/model/AppMetaData;", "appMetaDataType", "Lcom/reown/android/internal/common/model/AppMetaDataType;", "updateMetaData", "updateOrAbortMetaDataTopic", "oldTopic", "newTopic", "(Lcom/reown/foundation/common/model/Topic;Lcom/reown/foundation/common/model/Topic;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMetaData", "existsByTopicAndType", "", "type", "getByTopicAndType", "upsertPeerMetadata", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface MetadataStorageRepositoryInterface {
    void deleteMetaData(@NotNull Topic topic);

    boolean existsByTopicAndType(@NotNull Topic topic, @NotNull AppMetaDataType appMetaDataType);

    @Nullable
    AppMetaData getByTopicAndType(@NotNull Topic topic, @NotNull AppMetaDataType appMetaDataType);

    void insertOrAbortMetadata(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType);

    void updateMetaData(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType);

    @Nullable
    Object updateOrAbortMetaDataTopic(@NotNull Topic topic, @NotNull Topic topic2, @NotNull Continuation<? super Unit> continuation);

    void upsertPeerMetadata(@NotNull Topic topic, @NotNull AppMetaData appMetaData, @NotNull AppMetaDataType appMetaDataType);
}
