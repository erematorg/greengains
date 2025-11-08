package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyStatusTypeProtoConverter;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.SecretKeyAccess;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;

public final class KeysetManager {
    @GuardedBy("this")
    private final Keyset.Builder keysetBuilder;

    private KeysetManager(Keyset.Builder builder) {
        this.keysetBuilder = builder;
    }

    private synchronized Keyset.Key createKeysetKey(KeyData keyData, OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int newKeyId;
        newKeyId = newKeyId();
        if (outputPrefixType != OutputPrefixType.UNKNOWN_PREFIX) {
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return (Keyset.Key) Keyset.Key.newBuilder().setKeyData(keyData).setKeyId(newKeyId).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    private synchronized boolean keyIdExists(int i3) {
        for (Keyset.Key keyId : this.keysetBuilder.getKeyList()) {
            if (keyId.getKeyId() == i3) {
                return true;
            }
        }
        return false;
    }

    private synchronized Keyset.Key newKey(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return createKeysetKey(Registry.newKeyData(keyTemplate), keyTemplate.getOutputPrefixType());
    }

    private synchronized int newKeyId() {
        int randKeyId;
        randKeyId = Util.randKeyId();
        while (keyIdExists(randKeyId)) {
            randKeyId = Util.randKeyId();
        }
        return randKeyId;
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle) {
        return new KeysetManager((Keyset.Builder) keysetHandle.getKeyset().toBuilder());
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized KeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, false);
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized int addNewKey(KeyTemplate keyTemplate, boolean z2) throws GeneralSecurityException {
        Keyset.Key newKey;
        try {
            newKey = newKey(keyTemplate);
            this.keysetBuilder.addKey(newKey);
            if (z2) {
                this.keysetBuilder.setPrimaryKeyId(newKey.getKeyId());
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return newKey.getKeyId();
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager delete(int i3) throws GeneralSecurityException {
        if (i3 != this.keysetBuilder.getPrimaryKeyId()) {
            int i4 = 0;
            while (i4 < this.keysetBuilder.getKeyCount()) {
                if (this.keysetBuilder.getKey(i4).getKeyId() == i3) {
                    this.keysetBuilder.removeKey(i4);
                } else {
                    i4++;
                }
            }
            throw new GeneralSecurityException("key not found: " + i3);
        }
        throw new GeneralSecurityException("cannot delete the primary key");
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager destroy(int i3) throws GeneralSecurityException {
        try {
            if (i3 != this.keysetBuilder.getPrimaryKeyId()) {
                int i4 = 0;
                while (i4 < this.keysetBuilder.getKeyCount()) {
                    Keyset.Key key = this.keysetBuilder.getKey(i4);
                    if (key.getKeyId() == i3) {
                        if (!(key.getStatus() == KeyStatusType.ENABLED || key.getStatus() == KeyStatusType.DISABLED)) {
                            if (key.getStatus() != KeyStatusType.DESTROYED) {
                                throw new GeneralSecurityException("cannot destroy key with id " + i3);
                            }
                        }
                        this.keysetBuilder.setKey(i4, (Keyset.Key) ((Keyset.Key.Builder) key.toBuilder()).setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
                    } else {
                        i4++;
                    }
                }
                throw new GeneralSecurityException("key not found: " + i3);
            }
            throw new GeneralSecurityException("cannot destroy the primary key");
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager disable(int i3) throws GeneralSecurityException {
        try {
            if (i3 != this.keysetBuilder.getPrimaryKeyId()) {
                int i4 = 0;
                while (i4 < this.keysetBuilder.getKeyCount()) {
                    Keyset.Key key = this.keysetBuilder.getKey(i4);
                    if (key.getKeyId() == i3) {
                        if (key.getStatus() != KeyStatusType.ENABLED) {
                            if (key.getStatus() != KeyStatusType.DISABLED) {
                                throw new GeneralSecurityException("cannot disable key with id " + i3);
                            }
                        }
                        this.keysetBuilder.setKey(i4, (Keyset.Key) ((Keyset.Key.Builder) key.toBuilder()).setStatus(KeyStatusType.DISABLED).build());
                    } else {
                        i4++;
                    }
                }
                throw new GeneralSecurityException("key not found: " + i3);
            }
            throw new GeneralSecurityException("cannot disable the primary key");
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager enable(int i3) throws GeneralSecurityException {
        int i4 = 0;
        while (i4 < this.keysetBuilder.getKeyCount()) {
            try {
                Keyset.Key key = this.keysetBuilder.getKey(i4);
                if (key.getKeyId() == i3) {
                    KeyStatusType status = key.getStatus();
                    KeyStatusType keyStatusType = KeyStatusType.ENABLED;
                    if (status != keyStatusType) {
                        if (key.getStatus() != KeyStatusType.DISABLED) {
                            throw new GeneralSecurityException("cannot enable key with id " + i3);
                        }
                    }
                    this.keysetBuilder.setKey(i4, (Keyset.Key) ((Keyset.Key.Builder) key.toBuilder()).setStatus(keyStatusType).build());
                } else {
                    i4++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        throw new GeneralSecurityException("key not found: " + i3);
        return this;
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return KeysetHandle.fromKeyset((Keyset) this.keysetBuilder.build());
    }

    @InlineMe(replacement = "this.setPrimary(keyId)")
    @CanIgnoreReturnValue
    @Deprecated
    public synchronized KeysetManager promote(int i3) throws GeneralSecurityException {
        return setPrimary(i3);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized KeysetManager rotate(KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate, true);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager setPrimary(int i3) throws GeneralSecurityException {
        int i4 = 0;
        while (i4 < this.keysetBuilder.getKeyCount()) {
            Keyset.Key key = this.keysetBuilder.getKey(i4);
            if (key.getKeyId() != i3) {
                i4++;
            } else if (key.getStatus().equals(KeyStatusType.ENABLED)) {
                this.keysetBuilder.setPrimaryKeyId(i3);
            } else {
                throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i3);
            }
        }
        throw new GeneralSecurityException("key not found: " + i3);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        addNewKey(keyTemplate.getProto(), false);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyHandle keyHandle) throws GeneralSecurityException {
        try {
            ProtoKey protoKey = (ProtoKey) keyHandle.getKey(SecretKeyAccess.insecureSecretAccess());
            if (!keyIdExists(keyHandle.getId())) {
                this.keysetBuilder.addKey((Keyset.Key) Keyset.Key.newBuilder().setKeyData(protoKey.getProtoKey()).setKeyId(keyHandle.getId()).setStatus(KeyStatusTypeProtoConverter.toProto(keyHandle.getStatus())).setOutputPrefixType(KeyTemplate.toProto(protoKey.getOutputPrefixType())).build());
            } else {
                throw new GeneralSecurityException("Trying to add a key with an ID already contained in the keyset.");
            }
        } catch (ClassCastException e3) {
            throw new UnsupportedOperationException("KeyHandles which contain TinkKeys that are not ProtoKeys are not yet supported.", e3);
        }
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyHandle keyHandle, KeyAccess keyAccess) throws GeneralSecurityException {
        return add(keyHandle);
    }
}
