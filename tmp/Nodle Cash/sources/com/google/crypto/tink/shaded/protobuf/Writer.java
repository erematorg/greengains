package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
interface Writer {

    public enum FieldOrder {
        ASCENDING,
        DESCENDING
    }

    FieldOrder fieldOrder();

    void writeBool(int i3, boolean z2) throws IOException;

    void writeBoolList(int i3, List<Boolean> list, boolean z2) throws IOException;

    void writeBytes(int i3, ByteString byteString) throws IOException;

    void writeBytesList(int i3, List<ByteString> list) throws IOException;

    void writeDouble(int i3, double d2) throws IOException;

    void writeDoubleList(int i3, List<Double> list, boolean z2) throws IOException;

    @Deprecated
    void writeEndGroup(int i3) throws IOException;

    void writeEnum(int i3, int i4) throws IOException;

    void writeEnumList(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeFixed32(int i3, int i4) throws IOException;

    void writeFixed32List(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeFixed64(int i3, long j2) throws IOException;

    void writeFixed64List(int i3, List<Long> list, boolean z2) throws IOException;

    void writeFloat(int i3, float f2) throws IOException;

    void writeFloatList(int i3, List<Float> list, boolean z2) throws IOException;

    @Deprecated
    void writeGroup(int i3, Object obj) throws IOException;

    @Deprecated
    void writeGroup(int i3, Object obj, Schema schema) throws IOException;

    @Deprecated
    void writeGroupList(int i3, List<?> list) throws IOException;

    @Deprecated
    void writeGroupList(int i3, List<?> list, Schema schema) throws IOException;

    void writeInt32(int i3, int i4) throws IOException;

    void writeInt32List(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeInt64(int i3, long j2) throws IOException;

    void writeInt64List(int i3, List<Long> list, boolean z2) throws IOException;

    <K, V> void writeMap(int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) throws IOException;

    void writeMessage(int i3, Object obj) throws IOException;

    void writeMessage(int i3, Object obj, Schema schema) throws IOException;

    void writeMessageList(int i3, List<?> list) throws IOException;

    void writeMessageList(int i3, List<?> list, Schema schema) throws IOException;

    void writeMessageSetItem(int i3, Object obj) throws IOException;

    void writeSFixed32(int i3, int i4) throws IOException;

    void writeSFixed32List(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeSFixed64(int i3, long j2) throws IOException;

    void writeSFixed64List(int i3, List<Long> list, boolean z2) throws IOException;

    void writeSInt32(int i3, int i4) throws IOException;

    void writeSInt32List(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeSInt64(int i3, long j2) throws IOException;

    void writeSInt64List(int i3, List<Long> list, boolean z2) throws IOException;

    @Deprecated
    void writeStartGroup(int i3) throws IOException;

    void writeString(int i3, String str) throws IOException;

    void writeStringList(int i3, List<String> list) throws IOException;

    void writeUInt32(int i3, int i4) throws IOException;

    void writeUInt32List(int i3, List<Integer> list, boolean z2) throws IOException;

    void writeUInt64(int i3, long j2) throws IOException;

    void writeUInt64List(int i3, List<Long> list, boolean z2) throws IOException;
}
