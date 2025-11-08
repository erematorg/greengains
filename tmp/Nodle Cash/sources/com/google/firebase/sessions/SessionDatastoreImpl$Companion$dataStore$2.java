package com.google.firebase.sessions;

import android.util.Log;
import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/datastore/preferences/core/Preferences;", "ex", "Landroidx/datastore/core/CorruptionException;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SessionDatastoreImpl$Companion$dataStore$2 extends Lambda implements Function1<CorruptionException, Preferences> {
    public static final SessionDatastoreImpl$Companion$dataStore$2 INSTANCE = new SessionDatastoreImpl$Companion$dataStore$2();

    public SessionDatastoreImpl$Companion$dataStore$2() {
        super(1);
    }

    @NotNull
    public final Preferences invoke(@NotNull CorruptionException corruptionException) {
        Intrinsics.checkNotNullParameter(corruptionException, "ex");
        Log.w("FirebaseSessionsRepo", "CorruptionException in sessions DataStore in " + ProcessDetailsProvider.INSTANCE.getProcessName$com_google_firebase_firebase_sessions() + ClassUtils.PACKAGE_SEPARATOR_CHAR, corruptionException);
        return PreferencesFactory.createEmpty();
    }
}
