package com.reown.sign.storage.authenticate;

import android.database.sqlite.SQLiteException;
import com.reown.sign.storage.data.dao.authenticatereponse.AuthenticateResponseTopicDaoQueries;
import com.reown.sign.storage.data.dao.authenticatereponse.GetListOfTopics;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH@¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository;", "", "authenticateResponseTopicDaoQueries", "Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;", "<init>", "(Lcom/reown/sign/storage/data/dao/authenticatereponse/AuthenticateResponseTopicDaoQueries;)V", "insertOrAbort", "", "pairingTopic", "", "responseTopic", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResponseTopics", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthenticateResponseTopicRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthenticateResponseTopicRepository.kt\ncom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,24:1\n1563#2:25\n1634#2,3:26\n*S KotlinDebug\n*F\n+ 1 AuthenticateResponseTopicRepository.kt\ncom/reown/sign/storage/authenticate/AuthenticateResponseTopicRepository\n*L\n22#1:25\n22#1:26,3\n*E\n"})
public final class AuthenticateResponseTopicRepository {
    @NotNull
    private final AuthenticateResponseTopicDaoQueries authenticateResponseTopicDaoQueries;

    public AuthenticateResponseTopicRepository(@NotNull AuthenticateResponseTopicDaoQueries authenticateResponseTopicDaoQueries2) {
        Intrinsics.checkNotNullParameter(authenticateResponseTopicDaoQueries2, "authenticateResponseTopicDaoQueries");
        this.authenticateResponseTopicDaoQueries = authenticateResponseTopicDaoQueries2;
    }

    public final /* synthetic */ Object delete(String str, Continuation continuation) throws SQLiteException {
        this.authenticateResponseTopicDaoQueries.deleteByPairingTopic(str);
        return Unit.INSTANCE;
    }

    public final /* synthetic */ Object getResponseTopics(Continuation continuation) throws SQLiteException {
        Iterable<GetListOfTopics> executeAsList = this.authenticateResponseTopicDaoQueries.getListOfTopics().executeAsList();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(executeAsList, 10));
        for (GetListOfTopics responseTopic : executeAsList) {
            arrayList.add(responseTopic.getResponseTopic());
        }
        return arrayList;
    }

    public final /* synthetic */ Object insertOrAbort(String str, String str2, Continuation continuation) throws SQLiteException {
        this.authenticateResponseTopicDaoQueries.insertOrAbort(str, str2);
        return Unit.INSTANCE;
    }
}
