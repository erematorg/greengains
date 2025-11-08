package com.google.protobuf;

import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

public class MapEntryLite<K, V> {
    private static final int KEY_FIELD_NUMBER = 1;
    private static final int VALUE_FIELD_NUMBER = 2;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    /* renamed from: com.google.protobuf.MapEntryLite$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.GROUP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MapEntryLite.AnonymousClass1.<clinit>():void");
        }
    }

    public static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final WireFormat.FieldType keyType;
        public final WireFormat.FieldType valueType;

        public Metadata(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v2) {
            this.keyType = fieldType;
            this.defaultKey = k2;
            this.valueType = fieldType2;
            this.defaultValue = v2;
        }
    }

    private MapEntryLite(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v2) {
        this.metadata = new Metadata<>(fieldType, k2, fieldType2, v2);
        this.key = k2;
        this.value = v2;
    }

    public static <K, V> int computeSerializedSize(Metadata<K, V> metadata2, K k2, V v2) {
        return FieldSet.computeElementSize(metadata2.keyType, 1, k2) + FieldSet.computeElementSize(metadata2.valueType, 2, v2);
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(WireFormat.FieldType fieldType, K k2, WireFormat.FieldType fieldType2, V v2) {
        return new MapEntryLite<>(fieldType, k2, fieldType2, v2);
    }

    public static <T> T parseField(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, WireFormat.FieldType fieldType, T t2) throws IOException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()];
        if (i3 == 1) {
            MessageLite.Builder builder = ((MessageLite) t2).toBuilder();
            codedInputStream.readMessage(builder, extensionRegistryLite);
            return builder.buildPartial();
        } else if (i3 == 2) {
            return Integer.valueOf(codedInputStream.readEnum());
        } else {
            if (i3 != 3) {
                return FieldSet.readPrimitiveField(codedInputStream, fieldType, true);
            }
            throw new RuntimeException("Groups are not allowed in maps.");
        }
    }

    public static <K, V> void writeTo(CodedOutputStream codedOutputStream, Metadata<K, V> metadata2, K k2, V v2) throws IOException {
        FieldSet.writeElement(codedOutputStream, metadata2.keyType, 1, k2);
        FieldSet.writeElement(codedOutputStream, metadata2.valueType, 2, v2);
    }

    public int computeMessageSize(int i3, K k2, V v2) {
        return CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, k2, v2)) + CodedOutputStream.computeTagSize(i3);
    }

    public K getKey() {
        return this.key;
    }

    public Metadata<K, V> getMetadata() {
        return this.metadata;
    }

    public V getValue() {
        return this.value;
    }

    public Map.Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return parseEntry(byteString.newCodedInput(), this.metadata, extensionRegistryLite);
    }

    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        Metadata<K, V> metadata2 = this.metadata;
        K k2 = metadata2.defaultKey;
        V v2 = metadata2.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.makeTag(1, this.metadata.keyType.getWireType())) {
                k2 = parseField(codedInputStream, extensionRegistryLite, this.metadata.keyType, k2);
            } else if (readTag == WireFormat.makeTag(2, this.metadata.valueType.getWireType())) {
                v2 = parseField(codedInputStream, extensionRegistryLite, this.metadata.valueType, v2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(pushLimit);
        mapFieldLite.put(k2, v2);
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i3, K k2, V v2) throws IOException {
        codedOutputStream.writeTag(i3, 2);
        codedOutputStream.writeUInt32NoTag(computeSerializedSize(this.metadata, k2, v2));
        writeTo(codedOutputStream, this.metadata, k2, v2);
    }

    public static <K, V> Map.Entry<K, V> parseEntry(CodedInputStream codedInputStream, Metadata<K, V> metadata2, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        K k2 = metadata2.defaultKey;
        V v2 = metadata2.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.makeTag(1, metadata2.keyType.getWireType())) {
                k2 = parseField(codedInputStream, extensionRegistryLite, metadata2.keyType, k2);
            } else if (readTag == WireFormat.makeTag(2, metadata2.valueType.getWireType())) {
                v2 = parseField(codedInputStream, extensionRegistryLite, metadata2.valueType, v2);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(k2, v2);
    }

    private MapEntryLite(Metadata<K, V> metadata2, K k2, V v2) {
        this.metadata = metadata2;
        this.key = k2;
        this.value = v2;
    }
}
