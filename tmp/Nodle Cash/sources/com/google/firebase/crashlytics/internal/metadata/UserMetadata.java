package com.google.firebase.crashlytics.internal.metadata;

import U1.b;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

public class UserMetadata {
    public static final String INTERNAL_KEYDATA_FILENAME = "internal-keys";
    public static final String KEYDATA_FILENAME = "keys";
    @VisibleForTesting
    public static final int MAX_ATTRIBUTES = 64;
    @VisibleForTesting
    public static final int MAX_ATTRIBUTE_SIZE = 1024;
    @VisibleForTesting
    public static final int MAX_INTERNAL_KEY_SIZE = 8192;
    @VisibleForTesting
    public static final int MAX_ROLLOUT_ASSIGNMENTS = 128;
    public static final String ROLLOUTS_STATE_FILENAME = "rollouts-state";
    public static final String USERDATA_FILENAME = "user-data";
    /* access modifiers changed from: private */
    public final CrashlyticsBackgroundWorker backgroundWorker;
    private final SerializeableKeysMap customKeys = new SerializeableKeysMap(false);
    private final SerializeableKeysMap internalKeys = new SerializeableKeysMap(true);
    /* access modifiers changed from: private */
    public final MetaDataStore metaDataStore;
    private final RolloutAssignmentList rolloutsState = new RolloutAssignmentList(128);
    /* access modifiers changed from: private */
    public String sessionIdentifier;
    private final AtomicMarkableReference<String> userId = new AtomicMarkableReference<>((Object) null, false);

    public class SerializeableKeysMap {
        private final boolean isInternal;
        final AtomicMarkableReference<KeysMap> map;
        private final AtomicReference<Callable<Void>> queuedSerializer = new AtomicReference<>((Object) null);

        public SerializeableKeysMap(boolean z2) {
            this.isInternal = z2;
            this.map = new AtomicMarkableReference<>(new KeysMap(64, z2 ? 8192 : 1024), false);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Void lambda$scheduleSerializationTaskIfNeeded$0() throws Exception {
            this.queuedSerializer.set((Object) null);
            serializeIfMarked();
            return null;
        }

        private void scheduleSerializationTaskIfNeeded() {
            a aVar = new a(this, 0);
            AtomicReference<Callable<Void>> atomicReference = this.queuedSerializer;
            while (!atomicReference.compareAndSet((Object) null, aVar)) {
                if (atomicReference.get() != null) {
                    return;
                }
            }
            UserMetadata.this.backgroundWorker.submit(aVar);
        }

        private void serializeIfMarked() {
            Map<String, String> map2;
            synchronized (this) {
                try {
                    if (this.map.isMarked()) {
                        map2 = this.map.getReference().getKeys();
                        AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                        atomicMarkableReference.set(atomicMarkableReference.getReference(), false);
                    } else {
                        map2 = null;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (map2 != null) {
                UserMetadata.this.metaDataStore.writeKeyData(UserMetadata.this.sessionIdentifier, map2, this.isInternal);
            }
        }

        public Map<String, String> getKeys() {
            return this.map.getReference().getKeys();
        }

        public boolean setKey(String str, String str2) {
            synchronized (this) {
                try {
                    if (!this.map.getReference().setKey(str, str2)) {
                        return false;
                    }
                    AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                    atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
                    scheduleSerializationTaskIfNeeded();
                    return true;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        public void setKeys(Map<String, String> map2) {
            synchronized (this) {
                this.map.getReference().setKeys(map2);
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
            }
            scheduleSerializationTaskIfNeeded();
        }
    }

    public UserMetadata(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        this.sessionIdentifier = str;
        this.metaDataStore = new MetaDataStore(fileStore);
        this.backgroundWorker = crashlyticsBackgroundWorker;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$setUserId$0() throws Exception {
        serializeUserDataIfNeeded();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateRolloutsState$1(List list) throws Exception {
        this.metaDataStore.writeRolloutState(this.sessionIdentifier, list);
        return null;
    }

    public static UserMetadata loadFromExistingSession(String str, FileStore fileStore, CrashlyticsBackgroundWorker crashlyticsBackgroundWorker) {
        MetaDataStore metaDataStore2 = new MetaDataStore(fileStore);
        UserMetadata userMetadata = new UserMetadata(str, fileStore, crashlyticsBackgroundWorker);
        userMetadata.customKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, false));
        userMetadata.internalKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, true));
        userMetadata.userId.set(metaDataStore2.readUserId(str), false);
        userMetadata.rolloutsState.updateRolloutAssignmentList(metaDataStore2.readRolloutsState(str));
        return userMetadata;
    }

    @Nullable
    public static String readUserId(String str, FileStore fileStore) {
        return new MetaDataStore(fileStore).readUserId(str);
    }

    private void serializeUserDataIfNeeded() {
        boolean z2;
        String str;
        synchronized (this.userId) {
            try {
                z2 = false;
                if (this.userId.isMarked()) {
                    str = getUserId();
                    this.userId.set(str, false);
                    z2 = true;
                } else {
                    str = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z2) {
            this.metaDataStore.writeUserData(this.sessionIdentifier, str);
        }
    }

    public Map<String, String> getCustomKeys() {
        return this.customKeys.getKeys();
    }

    public Map<String, String> getInternalKeys() {
        return this.internalKeys.getKeys();
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutsState() {
        return this.rolloutsState.getReportRolloutsState();
    }

    @Nullable
    public String getUserId() {
        return this.userId.getReference();
    }

    public boolean setCustomKey(String str, String str2) {
        return this.customKeys.setKey(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.customKeys.setKeys(map);
    }

    public boolean setInternalKey(String str, String str2) {
        return this.internalKeys.setKey(str, str2);
    }

    public void setNewSession(String str) {
        synchronized (this.sessionIdentifier) {
            try {
                this.sessionIdentifier = str;
                Map<String, String> keys = this.customKeys.getKeys();
                List<RolloutAssignment> rolloutAssignmentList = this.rolloutsState.getRolloutAssignmentList();
                if (getUserId() != null) {
                    this.metaDataStore.writeUserData(str, getUserId());
                }
                if (!keys.isEmpty()) {
                    this.metaDataStore.writeKeyData(str, keys);
                }
                if (!rolloutAssignmentList.isEmpty()) {
                    this.metaDataStore.writeRolloutState(str, rolloutAssignmentList);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void setUserId(String str) {
        String sanitizeString = KeysMap.sanitizeString(str, 1024);
        synchronized (this.userId) {
            try {
                if (!CommonUtils.nullSafeEquals(sanitizeString, this.userId.getReference())) {
                    this.userId.set(sanitizeString, true);
                    this.backgroundWorker.submit(new a(this, 1));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    @CanIgnoreReturnValue
    public boolean updateRolloutsState(List<RolloutAssignment> list) {
        synchronized (this.rolloutsState) {
            try {
                if (!this.rolloutsState.updateRolloutAssignmentList(list)) {
                    return false;
                }
                this.backgroundWorker.submit(new b(this, this.rolloutsState.getRolloutAssignmentList(), 1));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
