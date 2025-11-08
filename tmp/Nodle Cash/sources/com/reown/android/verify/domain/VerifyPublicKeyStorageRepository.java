package com.reown.android.verify.domain;

import android.database.sqlite.SQLiteException;
import com.reown.android.sdk.storage.data.dao.VerifyPublicKey;
import com.reown.android.sdk.storage.data.dao.VerifyPublicKeyQueries;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/reown/android/verify/domain/VerifyPublicKeyStorageRepository;", "", "queries", "Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;", "<init>", "(Lcom/reown/android/sdk/storage/data/dao/VerifyPublicKeyQueries;)V", "upsertPublicKey", "", "publicKey", "", "expiresAt", "", "getPublicKey", "Lkotlin/Pair;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VerifyPublicKeyStorageRepository {
    @NotNull
    private final VerifyPublicKeyQueries queries;

    public VerifyPublicKeyStorageRepository(@NotNull VerifyPublicKeyQueries verifyPublicKeyQueries) {
        Intrinsics.checkNotNullParameter(verifyPublicKeyQueries, "queries");
        this.queries = verifyPublicKeyQueries;
    }

    @NotNull
    public final Pair<String, Long> getPublicKey() throws SQLiteException {
        VerifyPublicKey executeAsOneOrNull = this.queries.getKey().executeAsOneOrNull();
        return executeAsOneOrNull == null ? new Pair<>(null, null) : new Pair<>(executeAsOneOrNull.getKey(), Long.valueOf(executeAsOneOrNull.getExpires_at()));
    }

    public final void upsertPublicKey(@NotNull String str, long j2) throws SQLiteException {
        Intrinsics.checkNotNullParameter(str, "publicKey");
        this.queries.upsertKey(str, j2);
    }
}
