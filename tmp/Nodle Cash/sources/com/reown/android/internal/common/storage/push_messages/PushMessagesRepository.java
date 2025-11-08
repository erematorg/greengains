package com.reown.android.internal.common.storage.push_messages;

import android.database.sqlite.SQLiteException;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.sdk.storage.data.dao.PushMessageQueries;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J.\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\bH@¢\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "", "pushMessageQueries", "Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PushMessageQueries;)V", "notificationTags", "", "", "getNotificationTags", "()Ljava/util/List;", "_arePushNotificationsEnabled", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "arePushNotificationsEnabled", "Lkotlinx/coroutines/flow/StateFlow;", "getArePushNotificationsEnabled", "()Lkotlinx/coroutines/flow/StateFlow;", "enablePushNotifications", "", "insertPushMessage", "id", "", "topic", "blob", "tag", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doesPushMessageExist", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePushMessagesByTopic", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMessagesRepository {
    @NotNull
    private final MutableStateFlow<Boolean> _arePushNotificationsEnabled;
    @NotNull
    private final StateFlow<Boolean> arePushNotificationsEnabled;
    @NotNull
    private final List<Integer> notificationTags = CollectionsKt.listOf(Integer.valueOf(Tags.SESSION_PROPOSE.getId()), Integer.valueOf(Tags.SESSION_REQUEST.getId()), Integer.valueOf(Tags.NOTIFY_MESSAGE.getId()), Integer.valueOf(Tags.SESSION_AUTHENTICATE.getId()));
    /* access modifiers changed from: private */
    @NotNull
    public final PushMessageQueries pushMessageQueries;

    public PushMessagesRepository(@NotNull PushMessageQueries pushMessageQueries2) {
        Intrinsics.checkNotNullParameter(pushMessageQueries2, "pushMessageQueries");
        this.pushMessageQueries = pushMessageQueries2;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._arePushNotificationsEnabled = MutableStateFlow;
        this.arePushNotificationsEnabled = FlowKt.asStateFlow(MutableStateFlow);
    }

    @Nullable
    public final Object deletePushMessagesByTopic(@NotNull String str, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new PushMessagesRepository$deletePushMessagesByTopic$2(this, str, (Continuation<? super PushMessagesRepository$deletePushMessagesByTopic$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    @Nullable
    public final Object doesPushMessageExist(@NotNull String str, @NotNull Continuation<? super Boolean> continuation) throws SQLiteException {
        return BuildersKt.withContext(Dispatchers.getIO(), new PushMessagesRepository$doesPushMessageExist$2(this, str, (Continuation<? super PushMessagesRepository$doesPushMessageExist$2>) null), continuation);
    }

    public final void enablePushNotifications() {
        this._arePushNotificationsEnabled.setValue(Boolean.TRUE);
    }

    @NotNull
    public final StateFlow<Boolean> getArePushNotificationsEnabled() {
        return this.arePushNotificationsEnabled;
    }

    @NotNull
    public final List<Integer> getNotificationTags() {
        return this.notificationTags;
    }

    @Nullable
    public final Object insertPushMessage(@NotNull String str, @NotNull String str2, @NotNull String str3, int i3, @NotNull Continuation<? super Unit> continuation) throws SQLiteException {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new PushMessagesRepository$insertPushMessage$2(this, str, str2, str3, i3, (Continuation<? super PushMessagesRepository$insertPushMessage$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
