package com.google.protobuf;

import java.io.IOException;

public class LazyFieldLite {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile ByteString memoizedBytes;
    protected volatile MessageLite value;

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        checkArguments(extensionRegistryLite, byteString);
        this.extensionRegistry = extensionRegistryLite;
        this.delayedBytes = byteString;
    }

    private static void checkArguments(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        if (extensionRegistryLite == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        } else if (byteString == null) {
            throw new NullPointerException("found null ByteString");
        }
    }

    public static LazyFieldLite fromValue(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(messageLite);
        return lazyFieldLite;
    }

    private static MessageLite mergeValueAndBytes(MessageLite messageLite, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        try {
            return messageLite.toBuilder().mergeFrom(byteString, extensionRegistryLite).build();
        } catch (InvalidProtocolBufferException unused) {
            return messageLite;
        }
    }

    public void clear() {
        this.delayedBytes = null;
        this.value = null;
        this.memoizedBytes = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = r2.delayedBytes;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean containsDefaultInstance() {
        /*
            r2 = this;
            com.google.protobuf.ByteString r0 = r2.memoizedBytes
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.EMPTY
            if (r0 == r1) goto L_0x0013
            com.google.protobuf.MessageLite r0 = r2.value
            if (r0 != 0) goto L_0x0011
            com.google.protobuf.ByteString r2 = r2.delayedBytes
            if (r2 == 0) goto L_0x0013
            if (r2 != r1) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r2 = 0
            goto L_0x0014
        L_0x0013:
            r2 = 1
        L_0x0014:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.LazyFieldLite.containsDefaultInstance():boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:15|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.value = r4;
        r3.memoizedBytes = com.google.protobuf.ByteString.EMPTY;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ensureInitialized(com.google.protobuf.MessageLite r4) {
        /*
            r3 = this;
            com.google.protobuf.MessageLite r0 = r3.value
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r3)
            com.google.protobuf.MessageLite r0 = r3.value     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x000e
            monitor-exit(r3)     // Catch:{ all -> 0x000c }
            return
        L_0x000c:
            r4 = move-exception
            goto L_0x0036
        L_0x000e:
            com.google.protobuf.ByteString r0 = r3.delayedBytes     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            if (r0 == 0) goto L_0x0027
            com.google.protobuf.Parser r0 = r4.getParserForType()     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            com.google.protobuf.ByteString r1 = r3.delayedBytes     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            com.google.protobuf.ExtensionRegistryLite r2 = r3.extensionRegistry     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            java.lang.Object r0 = r0.parseFrom((com.google.protobuf.ByteString) r1, (com.google.protobuf.ExtensionRegistryLite) r2)     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            com.google.protobuf.MessageLite r0 = (com.google.protobuf.MessageLite) r0     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            r3.value = r0     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            com.google.protobuf.ByteString r0 = r3.delayedBytes     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            r3.memoizedBytes = r0     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            goto L_0x0034
        L_0x0027:
            r3.value = r4     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.EMPTY     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            r3.memoizedBytes = r0     // Catch:{ InvalidProtocolBufferException -> 0x002e }
            goto L_0x0034
        L_0x002e:
            r3.value = r4     // Catch:{ all -> 0x000c }
            com.google.protobuf.ByteString r4 = com.google.protobuf.ByteString.EMPTY     // Catch:{ all -> 0x000c }
            r3.memoizedBytes = r4     // Catch:{ all -> 0x000c }
        L_0x0034:
            monitor-exit(r3)     // Catch:{ all -> 0x000c }
            return
        L_0x0036:
            monitor-exit(r3)     // Catch:{ all -> 0x000c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.LazyFieldLite.ensureInitialized(com.google.protobuf.MessageLite):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyFieldLite)) {
            return false;
        }
        LazyFieldLite lazyFieldLite = (LazyFieldLite) obj;
        MessageLite messageLite = this.value;
        MessageLite messageLite2 = lazyFieldLite.value;
        return (messageLite == null && messageLite2 == null) ? toByteString().equals(lazyFieldLite.toByteString()) : (messageLite == null || messageLite2 == null) ? messageLite != null ? messageLite.equals(lazyFieldLite.getValue(messageLite.getDefaultInstanceForType())) : getValue(messageLite2.getDefaultInstanceForType()).equals(messageLite2) : messageLite.equals(messageLite2);
    }

    public int getSerializedSize() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes.size();
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString.size();
        }
        if (this.value != null) {
            return this.value.getSerializedSize();
        }
        return 0;
    }

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public int hashCode() {
        return 1;
    }

    public void merge(LazyFieldLite lazyFieldLite) {
        ByteString byteString;
        if (!lazyFieldLite.containsDefaultInstance()) {
            if (containsDefaultInstance()) {
                set(lazyFieldLite);
                return;
            }
            if (this.extensionRegistry == null) {
                this.extensionRegistry = lazyFieldLite.extensionRegistry;
            }
            ByteString byteString2 = this.delayedBytes;
            if (byteString2 != null && (byteString = lazyFieldLite.delayedBytes) != null) {
                this.delayedBytes = byteString2.concat(byteString);
            } else if (this.value == null && lazyFieldLite.value != null) {
                setValue(mergeValueAndBytes(lazyFieldLite.value, this.delayedBytes, this.extensionRegistry));
            } else if (this.value == null || lazyFieldLite.value != null) {
                setValue(this.value.toBuilder().mergeFrom(lazyFieldLite.value).build());
            } else {
                setValue(mergeValueAndBytes(this.value, lazyFieldLite.delayedBytes, lazyFieldLite.extensionRegistry));
            }
        }
    }

    public void mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (containsDefaultInstance()) {
            setByteString(codedInputStream.readBytes(), extensionRegistryLite);
            return;
        }
        if (this.extensionRegistry == null) {
            this.extensionRegistry = extensionRegistryLite;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            setByteString(byteString.concat(codedInputStream.readBytes()), this.extensionRegistry);
            return;
        }
        try {
            setValue(this.value.toBuilder().mergeFrom(codedInputStream, extensionRegistryLite).build());
        } catch (InvalidProtocolBufferException unused) {
        }
    }

    public void set(LazyFieldLite lazyFieldLite) {
        this.delayedBytes = lazyFieldLite.delayedBytes;
        this.value = lazyFieldLite.value;
        this.memoizedBytes = lazyFieldLite.memoizedBytes;
        ExtensionRegistryLite extensionRegistryLite = lazyFieldLite.extensionRegistry;
        if (extensionRegistryLite != null) {
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    public void setByteString(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        checkArguments(extensionRegistryLite, byteString);
        this.delayedBytes = byteString;
        this.extensionRegistry = extensionRegistryLite;
        this.value = null;
        this.memoizedBytes = null;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.delayedBytes = null;
        this.memoizedBytes = null;
        this.value = messageLite;
        return messageLite2;
    }

    public ByteString toByteString() {
        if (this.memoizedBytes != null) {
            return this.memoizedBytes;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            return byteString;
        }
        synchronized (this) {
            try {
                if (this.memoizedBytes != null) {
                    ByteString byteString2 = this.memoizedBytes;
                    return byteString2;
                }
                if (this.value == null) {
                    this.memoizedBytes = ByteString.EMPTY;
                } else {
                    this.memoizedBytes = this.value.toByteString();
                }
                ByteString byteString3 = this.memoizedBytes;
                return byteString3;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void writeTo(Writer writer, int i3) throws IOException {
        if (this.memoizedBytes != null) {
            writer.writeBytes(i3, this.memoizedBytes);
            return;
        }
        ByteString byteString = this.delayedBytes;
        if (byteString != null) {
            writer.writeBytes(i3, byteString);
        } else if (this.value != null) {
            writer.writeMessage(i3, this.value);
        } else {
            writer.writeBytes(i3, ByteString.EMPTY);
        }
    }

    public LazyFieldLite() {
    }
}
