package com.reown.android.push.notifications;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.reown.android.Core;
import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.storage.push_messages.PushMessagesRepository;
import com.reown.android.push.client.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 +2\u00020\u0001:\u0002*+B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H&J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H&J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0016H&J\f\u0010!\u001a\u00020\"*\u00020\u0016H\u0002J\f\u0010#\u001a\u00020\"*\u00020\u0016H\u0002J\u001c\u0010$\u001a\u00020\u0012*\u00020\u00162\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0002J\f\u0010'\u001a\u00020\u0012*\u00020\u0016H\u0002J\u000e\u0010(\u001a\u00020\"*\u0004\u0018\u00010)H\u0002R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f¨\u0006,"}, d2 = {"Lcom/reown/android/push/notifications/PushMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "<init>", "()V", "decryptMessageUseCases", "", "", "Lcom/reown/android/push/notifications/DecryptMessageUseCaseInterface;", "getDecryptMessageUseCases", "()Ljava/util/Map;", "decryptMessageUseCases$delegate", "Lkotlin/Lazy;", "pushMessagesRepository", "Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "getPushMessagesRepository", "()Lcom/reown/android/internal/common/storage/push_messages/PushMessagesRepository;", "pushMessagesRepository$delegate", "onNewToken", "", "token", "onMessageReceived", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "onMessage", "Lcom/reown/android/Core$Model$Message;", "originalMessage", "newToken", "registeringFailed", "throwable", "", "onDefaultBehavior", "onError", "defaultMessage", "isLegacyNotification", "", "isEncryptedNotification", "decryptNotification", "tag", "encryptedMessage", "prepareSimpleNotification", "isValid", "Lcom/google/firebase/messaging/RemoteMessage$Notification;", "MessageFlags", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMessagingService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessagingService.kt\ncom/reown/android/push/notifications/PushMessagingService\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Koin.kt\norg/koin/core/Koin\n+ 5 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,130:1\n1563#2:131\n1634#2,3:132\n774#2:135\n865#2,2:136\n1563#2:138\n1634#2,3:139\n2756#2:142\n1#3:143\n1#3:144\n124#4,4:145\n124#4,4:150\n142#5:149\n142#5:154\n*S KotlinDebug\n*F\n+ 1 PushMessagingService.kt\ncom/reown/android/push/notifications/PushMessagingService\n*L\n42#1:131\n42#1:132,3\n43#1:135\n43#1:136,2\n44#1:138\n44#1:139,3\n45#1:142\n45#1:143\n19#1:145,4\n21#1:150,4\n19#1:149\n21#1:154\n*E\n"})
public abstract class PushMessagingService extends FirebaseMessagingService {
    @NotNull
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    @Deprecated
    public static final String KEY_BLOB = "blob";
    @NotNull
    @Deprecated
    public static final String KEY_BODY = "body";
    @NotNull
    @Deprecated
    public static final String KEY_FLAGS = "flags";
    @NotNull
    @Deprecated
    public static final String KEY_MESSAGE = "message";
    @NotNull
    @Deprecated
    public static final String KEY_TAG = "tag";
    @NotNull
    @Deprecated
    public static final String KEY_TITLE = "title";
    @NotNull
    @Deprecated
    public static final String KEY_TOPIC = "topic";
    @NotNull
    private final Lazy decryptMessageUseCases$delegate = LazyKt.lazy(new a(3));
    @NotNull
    private final Lazy pushMessagesRepository$delegate = LazyKt.lazy(new a(4));

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/reown/android/push/notifications/PushMessagingService$Companion;", "", "<init>", "()V", "KEY_TOPIC", "", "KEY_TAG", "KEY_MESSAGE", "KEY_BLOB", "KEY_FLAGS", "KEY_TITLE", "KEY_BODY", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/reown/android/push/notifications/PushMessagingService$MessageFlags;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "SIGN", "AUTH", "CHAT", "NOTIFY", "ENCRYPTED", "Companion", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum MessageFlags {
        SIGN(2),
        AUTH(4),
        CHAT(8),
        NOTIFY(16),
        ENCRYPTED(r0.value + 1);
        
        @NotNull
        public static final Companion Companion = null;
        private final int value;

        @SourceDebugExtension({"SMAP\nPushMessagingService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessagingService.kt\ncom/reown/android/push/notifications/PushMessagingService$MessageFlags$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n1#2:131\n*E\n"})
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/reown/android/push/notifications/PushMessagingService$MessageFlags$Companion;", "", "<init>", "()V", "findMessageFlag", "Lcom/reown/android/push/notifications/PushMessagingService$MessageFlags;", "value", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final MessageFlags findMessageFlag(@NotNull String str) {
                MessageFlags messageFlags;
                Intrinsics.checkNotNullParameter(str, "value");
                MessageFlags[] values = MessageFlags.values();
                int length = values.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        messageFlags = null;
                        break;
                    }
                    messageFlags = values[i3];
                    if (messageFlags.getValue() == Integer.parseInt(str)) {
                        break;
                    }
                    i3++;
                }
                if (messageFlags != null) {
                    return messageFlags;
                }
                throw new IllegalArgumentException("Invalid value for MessageFlags");
            }

            private Companion() {
            }
        }

        static {
            MessageFlags[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) $values);
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        private MessageFlags(int i3) {
            this.value = i3;
        }

        @NotNull
        public static EnumEntries<MessageFlags> getEntries() {
            return $ENTRIES;
        }

        public final int getValue() {
            return this.value;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.reown.android.push.notifications.PushMessagingService$MessageFlags[] r0 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.reown.android.push.notifications.PushMessagingService$MessageFlags r1 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.ENCRYPTED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.reown.android.push.notifications.PushMessagingService$MessageFlags r1 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.CHAT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.reown.android.push.notifications.PushMessagingService$MessageFlags r1 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.NOTIFY     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.reown.android.push.notifications.PushMessagingService$MessageFlags r1 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.SIGN     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.reown.android.push.notifications.PushMessagingService$MessageFlags r1 = com.reown.android.push.notifications.PushMessagingService.MessageFlags.AUTH     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reown.android.push.notifications.PushMessagingService.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final Map decryptMessageUseCases_delegate$lambda$0() {
        Koin koin = KoinApplicationKt.getWcKoinApp().getKoin();
        return MapsKt.toMap((Map) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Map.class), QualifierKt.named(AndroidCommonDITags.DECRYPT_USE_CASES), (Function0<? extends ParametersHolder>) null));
    }

    private final void decryptNotification(RemoteMessage remoteMessage, String str, String str2) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new PushMessagingService$decryptNotification$1(this, str, remoteMessage, str2, (Continuation<? super PushMessagingService$decryptNotification$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Map<String, DecryptMessageUseCaseInterface> getDecryptMessageUseCases() {
        return (Map) this.decryptMessageUseCases$delegate.getValue();
    }

    private final PushMessagesRepository getPushMessagesRepository() {
        return (PushMessagesRepository) this.pushMessagesRepository$delegate.getValue();
    }

    private final boolean isEncryptedNotification(RemoteMessage remoteMessage) {
        return remoteMessage.getData().containsKey(KEY_TOPIC) && remoteMessage.getData().containsKey(KEY_TAG) && remoteMessage.getData().containsKey(KEY_MESSAGE);
    }

    private final boolean isLegacyNotification(RemoteMessage remoteMessage) {
        return remoteMessage.getData().containsKey(KEY_TOPIC) && remoteMessage.getData().containsKey(KEY_BLOB) && remoteMessage.getData().containsKey(KEY_FLAGS);
    }

    private final boolean isValid(RemoteMessage.Notification notification) {
        return (notification == null || notification.getTitle() == null || notification.getBody() == null) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b A[Catch:{ Exception -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052 A[Catch:{ Exception -> 0x0036 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void prepareSimpleNotification(com.google.firebase.messaging.RemoteMessage r6) {
        /*
            r5 = this;
            java.lang.String r0 = "getString(...)"
            java.lang.String r1 = "title"
            java.util.Map r2 = r6.getData()     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = "getData(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = "blob"
            java.lang.Object r2 = kotlin.collections.MapsKt.getValue(r2, r3)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0036 }
            byte[] r2 = org.bouncycastle.util.encoders.Base64.decode((java.lang.String) r2)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = "decode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r2 = kotlin.text.StringsKt.decodeToString(r2)     // Catch:{ Exception -> 0x0036 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0036 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0036 }
            boolean r2 = r3.has(r1)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r4 = "body"
            if (r2 == 0) goto L_0x0038
            boolean r2 = r3.has(r4)     // Catch:{ Exception -> 0x0036 }
            if (r2 == 0) goto L_0x0038
            goto L_0x0039
        L_0x0036:
            r0 = move-exception
            goto L_0x005a
        L_0x0038:
            r3 = 0
        L_0x0039:
            if (r3 == 0) goto L_0x0052
            com.reown.android.Core$Model$Message$Simple r2 = new com.reown.android.Core$Model$Message$Simple     // Catch:{ Exception -> 0x0036 }
            java.lang.String r1 = r3.getString(r1)     // Catch:{ Exception -> 0x0036 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)     // Catch:{ Exception -> 0x0036 }
            java.lang.String r3 = r3.getString(r4)     // Catch:{ Exception -> 0x0036 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)     // Catch:{ Exception -> 0x0036 }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x0036 }
            r5.onMessage(r2, r6)     // Catch:{ Exception -> 0x0036 }
            goto L_0x005d
        L_0x0052:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0036 }
            java.lang.String r1 = "Invalid message format"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0036 }
            throw r0     // Catch:{ Exception -> 0x0036 }
        L_0x005a:
            r5.onError(r0, r6)
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.push.notifications.PushMessagingService.prepareSimpleNotification(com.google.firebase.messaging.RemoteMessage):void");
    }

    /* access modifiers changed from: private */
    public static final PushMessagesRepository pushMessagesRepository_delegate$lambda$1() {
        return (PushMessagesRepository) KoinApplicationKt.getWcKoinApp().getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(PushMessagesRepository.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    public abstract void newToken(@NotNull String str);

    public abstract void onDefaultBehavior(@NotNull RemoteMessage remoteMessage);

    public abstract void onError(@NotNull Throwable th, @NotNull RemoteMessage remoteMessage);

    public abstract void onMessage(@NotNull Core.Model.Message message, @NotNull RemoteMessage remoteMessage);

    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, KEY_MESSAGE);
        super.onMessageReceived(remoteMessage);
        try {
            if (isLegacyNotification(remoteMessage)) {
                MessageFlags.Companion companion = MessageFlags.Companion;
                Map data = remoteMessage.getData();
                Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                Object value = MapsKt.getValue(data, KEY_FLAGS);
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                int i3 = WhenMappings.$EnumSwitchMapping$0[companion.findMessageFlag((String) value).ordinal()];
                if (i3 != 1) {
                    if (!(i3 == 2 || i3 == 3 || i3 == 4)) {
                        if (i3 != 5) {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    prepareSimpleNotification(remoteMessage);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                String valueOf = String.valueOf(Tags.NOTIFY_MESSAGE.getId());
                Map data2 = remoteMessage.getData();
                Intrinsics.checkNotNullExpressionValue(data2, "getData(...)");
                Object value2 = MapsKt.getValue(data2, KEY_BLOB);
                Intrinsics.checkNotNullExpressionValue(value2, "getValue(...)");
                decryptNotification(remoteMessage, valueOf, (String) value2);
                Unit unit2 = Unit.INSTANCE;
            } else if (isEncryptedNotification(remoteMessage)) {
                Iterable<Number> notificationTags = getPushMessagesRepository().getNotificationTags();
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(notificationTags, 10));
                for (Number intValue : notificationTags) {
                    arrayList.add(String.valueOf(intValue.intValue()));
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    Map data3 = remoteMessage.getData();
                    Intrinsics.checkNotNullExpressionValue(data3, "getData(...)");
                    if (Intrinsics.areEqual((Object) (String) next, MapsKt.getValue(data3, KEY_TAG))) {
                        arrayList2.add(next);
                    }
                }
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    if (Intrinsics.areEqual((Object) str, (Object) String.valueOf(Tags.SESSION_REQUEST.getId()))) {
                        str = String.valueOf(Tags.SESSION_PROPOSE.getId());
                    }
                    arrayList3.add(str);
                }
                Iterator it3 = arrayList3.iterator();
                while (it3.hasNext()) {
                    Map data4 = remoteMessage.getData();
                    Intrinsics.checkNotNullExpressionValue(data4, "getData(...)");
                    Object value3 = MapsKt.getValue(data4, KEY_MESSAGE);
                    Intrinsics.checkNotNullExpressionValue(value3, "getValue(...)");
                    decryptNotification(remoteMessage, (String) it3.next(), (String) value3);
                }
            } else {
                RemoteMessage.Notification notification = remoteMessage.getNotification();
                if (notification == null || !isValid(notification)) {
                    onDefaultBehavior(remoteMessage);
                    Unit unit3 = Unit.INSTANCE;
                    return;
                }
                RemoteMessage.Notification notification2 = remoteMessage.getNotification();
                if (notification2 != null) {
                    String title = notification2.getTitle();
                    Intrinsics.checkNotNull(title);
                    String body = notification2.getBody();
                    Intrinsics.checkNotNull(body);
                    onMessage(new Core.Model.Message.Simple(title, body), remoteMessage);
                    Unit unit4 = Unit.INSTANCE;
                    return;
                }
                throw new IllegalArgumentException("Required value was null.");
            }
        } catch (Exception e3) {
            onError(e3, remoteMessage);
            Unit unit5 = Unit.INSTANCE;
        }
    }

    public void onNewToken(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, SchemaSymbols.ATTVAL_TOKEN);
        super.onNewToken(str);
        newToken(str);
    }

    public abstract void registeringFailed(@NotNull String str, @NotNull Throwable th);
}
