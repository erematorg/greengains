package com.reown.android.internal.common.storage.pairing;

import android.database.sqlite.SQLiteException;
import app.cash.sqldelight.async.coroutines.QueryExtensionsKt;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.Pairing;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.push.notifications.PushMessagingService;
import com.reown.android.sdk.storage.data.dao.PairingQueries;
import com.reown.foundation.common.model.Topic;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\f\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010H@¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0010H@¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0001\u0010\u0018\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010\u000e2\b\u0010!\u001a\u0004\u0018\u00010\u00192\b\u0010\"\u001a\u0004\u0018\u00010\u00192\b\u0010#\u001a\u0004\u0018\u00010\u00192\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00102\b\u0010%\u001a\u0004\u0018\u00010\u0019H\u0002¢\u0006\u0002\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/reown/android/internal/common/storage/pairing/PairingStorageRepository;", "Lcom/reown/android/internal/common/storage/pairing/PairingStorageRepositoryInterface;", "pairingQueries", "Lcom/reown/android/sdk/storage/data/dao/PairingQueries;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/PairingQueries;)V", "insertPairing", "", "pairing", "Lcom/reown/android/internal/common/model/Pairing;", "deletePairing", "topic", "Lcom/reown/foundation/common/model/Topic;", "hasTopic", "", "getListOfPairings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getListOfPairingsWithoutRequestReceived", "setRequestReceived", "updateExpiry", "expiry", "Lcom/reown/android/internal/common/model/Expiry;", "getPairingOrNullByTopic", "toPairing", "", "expirySeconds", "", "relay_protocol", "relay_data", "uri", "methods", "is_proposal_received", "peerName", "peerDesc", "peerUrl", "peerIcons", "native", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/reown/android/internal/common/model/Pairing;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PairingStorageRepository implements PairingStorageRepositoryInterface {
    @NotNull
    private final PairingQueries pairingQueries;

    public PairingStorageRepository(@NotNull PairingQueries pairingQueries2) {
        Intrinsics.checkNotNullParameter(pairingQueries2, "pairingQueries");
        this.pairingQueries = pairingQueries2;
    }

    /* access modifiers changed from: private */
    public final Pairing toPairing(String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List<String> list, String str9) {
        String str10 = str;
        long j3 = j2;
        return new Pairing(new Topic(str), new Expiry(j2), (str6 == null || str7 == null || str8 == null || list == null) ? null : new AppMetaData(str7, str8, list, str6, new Redirect(str9, (String) null, false, 6, (DefaultConstructorMarker) null), (String) null, 32, (DefaultConstructorMarker) null), str2, str3, str4, bool != null ? bool.booleanValue() : true, str5);
    }

    public void deletePairing(@NotNull Topic topic) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.pairingQueries.deletePairing(topic.getValue());
    }

    @Nullable
    public Object getListOfPairings(@NotNull Continuation<? super List<Pairing>> continuation) throws SQLiteException {
        return QueryExtensionsKt.awaitAsList(this.pairingQueries.getListOfPairing(new PairingStorageRepository$getListOfPairings$2(this)), continuation);
    }

    @Nullable
    public Object getListOfPairingsWithoutRequestReceived(@NotNull Continuation<? super List<Pairing>> continuation) throws SQLiteException {
        return QueryExtensionsKt.awaitAsList(this.pairingQueries.getListOfPairingsWithoutRequestReceived(new PairingStorageRepository$getListOfPairingsWithoutRequestReceived$2(this)), continuation);
    }

    @Nullable
    public Pairing getPairingOrNullByTopic(@NotNull Topic topic) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return (Pairing) this.pairingQueries.getPairingByTopic(topic.getValue(), new PairingStorageRepository$getPairingOrNullByTopic$1(this)).executeAsOneOrNull();
    }

    public boolean hasTopic(@NotNull Topic topic) {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        return this.pairingQueries.hasTopic(topic.getValue()).executeAsOneOrNull() != null;
    }

    public void insertPairing(@NotNull Pairing pairing) throws SQLiteException {
        Intrinsics.checkNotNullParameter(pairing, "pairing");
        PairingQueries pairingQueries2 = this.pairingQueries;
        String value = pairing.getTopic().getValue();
        long seconds = pairing.getExpiry().getSeconds();
        String relayProtocol = pairing.getRelayProtocol();
        String relayData = pairing.getRelayData();
        String uri = pairing.getUri();
        String methods = pairing.getMethods();
        if (methods == null) {
            methods = Intrinsics.checkNotNullParameter(StringCompanionObject.INSTANCE, "<this>");
        }
        pairingQueries2.insertOrAbortPairing(value, seconds, relayProtocol, relayData, uri, methods, true, Boolean.valueOf(pairing.isProposalReceived()));
    }

    public void setRequestReceived(@NotNull Topic topic) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        this.pairingQueries.setRequestReceived(Boolean.TRUE, topic.getValue());
    }

    public void updateExpiry(@NotNull Topic topic, @NotNull Expiry expiry) throws SQLiteException {
        Intrinsics.checkNotNullParameter(topic, PushMessagingService.KEY_TOPIC);
        Intrinsics.checkNotNullParameter(expiry, "expiry");
        this.pairingQueries.updateOrAbortExpiry(expiry.getSeconds(), topic.getValue());
    }
}
