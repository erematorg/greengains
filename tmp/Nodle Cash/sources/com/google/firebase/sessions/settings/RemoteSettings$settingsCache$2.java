package com.google.firebase.sessions.settings;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/google/firebase/sessions/settings/SettingsCache;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RemoteSettings$settingsCache$2 extends Lambda implements Function0<SettingsCache> {
    final /* synthetic */ DataStore<Preferences> $dataStore;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteSettings$settingsCache$2(DataStore<Preferences> dataStore) {
        super(0);
        this.$dataStore = dataStore;
    }

    @NotNull
    public final SettingsCache invoke() {
        return new SettingsCache(this.$dataStore);
    }
}
