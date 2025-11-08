package com.google.protobuf;

import com.google.protobuf.MapEntryLite;
import com.google.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CheckReturnValue
final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    /* renamed from: com.google.protobuf.CodedOutputStreamWriter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.WireFormat$FieldType[] r0 = com.google.protobuf.WireFormat.FieldType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$WireFormat$FieldType = r0
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT32     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED64     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedOutputStreamWriter.AnonymousClass1.<clinit>():void");
        }
    }

    private CodedOutputStreamWriter(CodedOutputStream codedOutputStream) {
        CodedOutputStream codedOutputStream2 = (CodedOutputStream) Internal.checkNotNull(codedOutputStream, "output");
        this.output = codedOutputStream2;
        codedOutputStream2.wrapper = this;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream) {
        CodedOutputStreamWriter codedOutputStreamWriter = codedOutputStream.wrapper;
        return codedOutputStreamWriter != null ? codedOutputStreamWriter : new CodedOutputStreamWriter(codedOutputStream);
    }

    private <V> void writeDeterministicBooleanMapEntry(int i3, boolean z2, V v2, MapEntryLite.Metadata<Boolean, V> metadata) throws IOException {
        this.output.writeTag(i3, 2);
        this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Boolean.valueOf(z2), v2));
        MapEntryLite.writeTo(this.output, metadata, Boolean.valueOf(z2), v2);
    }

    private <V> void writeDeterministicIntegerMap(int i3, MapEntryLite.Metadata<Integer, V> metadata, Map<Integer, V> map) throws IOException {
        int size = map.size();
        int[] iArr = new int[size];
        int i4 = 0;
        for (Integer intValue : map.keySet()) {
            iArr[i4] = intValue.intValue();
            i4++;
        }
        Arrays.sort(iArr);
        for (int i5 = 0; i5 < size; i5++) {
            int i6 = iArr[i5];
            V v2 = map.get(Integer.valueOf(i6));
            this.output.writeTag(i3, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Integer.valueOf(i6), v2));
            MapEntryLite.writeTo(this.output, metadata, Integer.valueOf(i6), v2);
        }
    }

    private <V> void writeDeterministicLongMap(int i3, MapEntryLite.Metadata<Long, V> metadata, Map<Long, V> map) throws IOException {
        int size = map.size();
        long[] jArr = new long[size];
        int i4 = 0;
        for (Long longValue : map.keySet()) {
            jArr[i4] = longValue.longValue();
            i4++;
        }
        Arrays.sort(jArr);
        for (int i5 = 0; i5 < size; i5++) {
            long j2 = jArr[i5];
            V v2 = map.get(Long.valueOf(j2));
            this.output.writeTag(i3, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, Long.valueOf(j2), v2));
            MapEntryLite.writeTo(this.output, metadata, Long.valueOf(j2), v2);
        }
    }

    private <K, V> void writeDeterministicMap(int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[metadata.keyType.ordinal()]) {
            case 1:
                V v2 = map.get(Boolean.FALSE);
                if (v2 != null) {
                    writeDeterministicBooleanMapEntry(i3, false, v2, metadata);
                }
                V v3 = map.get(Boolean.TRUE);
                if (v3 != null) {
                    writeDeterministicBooleanMapEntry(i3, true, v3, metadata);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                writeDeterministicIntegerMap(i3, metadata, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                writeDeterministicLongMap(i3, metadata, map);
                return;
            case 12:
                writeDeterministicStringMap(i3, metadata, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + metadata.keyType);
        }
    }

    private <V> void writeDeterministicStringMap(int i3, MapEntryLite.Metadata<String, V> metadata, Map<String, V> map) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        int i4 = 0;
        for (String str : map.keySet()) {
            strArr[i4] = str;
            i4++;
        }
        Arrays.sort(strArr);
        for (int i5 = 0; i5 < size; i5++) {
            String str2 = strArr[i5];
            V v2 = map.get(str2);
            this.output.writeTag(i3, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, str2, v2));
            MapEntryLite.writeTo(this.output, metadata, str2, v2);
        }
    }

    private void writeLazyString(int i3, Object obj) throws IOException {
        if (obj instanceof String) {
            this.output.writeString(i3, (String) obj);
        } else {
            this.output.writeBytes(i3, (ByteString) obj);
        }
    }

    public Writer.FieldOrder fieldOrder() {
        return Writer.FieldOrder.ASCENDING;
    }

    public int getTotalBytesWritten() {
        return this.output.getTotalBytesWritten();
    }

    public void writeBool(int i3, boolean z2) throws IOException {
        this.output.writeBool(i3, z2);
    }

    public void writeBoolList(int i3, List<Boolean> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeBoolSizeNoTag(list.get(i6).booleanValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeBoolNoTag(list.get(i4).booleanValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeBool(i3, list.get(i4).booleanValue());
            i4++;
        }
    }

    public void writeBytes(int i3, ByteString byteString) throws IOException {
        this.output.writeBytes(i3, byteString);
    }

    public void writeBytesList(int i3, List<ByteString> list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            this.output.writeBytes(i3, list.get(i4));
        }
    }

    public void writeDouble(int i3, double d2) throws IOException {
        this.output.writeDouble(i3, d2);
    }

    public void writeDoubleList(int i3, List<Double> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeDoubleSizeNoTag(list.get(i6).doubleValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeDoubleNoTag(list.get(i4).doubleValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeDouble(i3, list.get(i4).doubleValue());
            i4++;
        }
    }

    @Deprecated
    public void writeEndGroup(int i3) throws IOException {
        this.output.writeTag(i3, 4);
    }

    public void writeEnum(int i3, int i4) throws IOException {
        this.output.writeEnum(i3, i4);
    }

    public void writeEnumList(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeEnumSizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeEnumNoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeEnum(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeFixed32(int i3, int i4) throws IOException {
        this.output.writeFixed32(i3, i4);
    }

    public void writeFixed32List(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeFixed32SizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeFixed32NoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeFixed32(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeFixed64(int i3, long j2) throws IOException {
        this.output.writeFixed64(i3, j2);
    }

    public void writeFixed64List(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeFixed64SizeNoTag(list.get(i6).longValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeFixed64NoTag(list.get(i4).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeFixed64(i3, list.get(i4).longValue());
            i4++;
        }
    }

    public void writeFloat(int i3, float f2) throws IOException {
        this.output.writeFloat(i3, f2);
    }

    public void writeFloatList(int i3, List<Float> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeFloatSizeNoTag(list.get(i6).floatValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeFloatNoTag(list.get(i4).floatValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeFloat(i3, list.get(i4).floatValue());
            i4++;
        }
    }

    @Deprecated
    public void writeGroup(int i3, Object obj) throws IOException {
        this.output.writeGroup(i3, (MessageLite) obj);
    }

    @Deprecated
    public void writeGroupList(int i3, List<?> list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            writeGroup(i3, list.get(i4));
        }
    }

    public void writeInt32(int i3, int i4) throws IOException {
        this.output.writeInt32(i3, i4);
    }

    public void writeInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeInt32SizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeInt32NoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeInt32(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeInt64(int i3, long j2) throws IOException {
        this.output.writeInt64(i3, j2);
    }

    public void writeInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeInt64SizeNoTag(list.get(i6).longValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeInt64NoTag(list.get(i4).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeInt64(i3, list.get(i4).longValue());
            i4++;
        }
    }

    public <K, V> void writeMap(int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map) throws IOException {
        if (this.output.isSerializationDeterministic()) {
            writeDeterministicMap(i3, metadata, map);
            return;
        }
        for (Map.Entry next : map.entrySet()) {
            this.output.writeTag(i3, 2);
            this.output.writeUInt32NoTag(MapEntryLite.computeSerializedSize(metadata, next.getKey(), next.getValue()));
            MapEntryLite.writeTo(this.output, metadata, next.getKey(), next.getValue());
        }
    }

    public void writeMessage(int i3, Object obj) throws IOException {
        this.output.writeMessage(i3, (MessageLite) obj);
    }

    public void writeMessageList(int i3, List<?> list) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            writeMessage(i3, list.get(i4));
        }
    }

    public final void writeMessageSetItem(int i3, Object obj) throws IOException {
        if (obj instanceof ByteString) {
            this.output.writeRawMessageSetExtension(i3, (ByteString) obj);
        } else {
            this.output.writeMessageSetExtension(i3, (MessageLite) obj);
        }
    }

    public void writeSFixed32(int i3, int i4) throws IOException {
        this.output.writeSFixed32(i3, i4);
    }

    public void writeSFixed32List(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeSFixed32SizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeSFixed32NoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeSFixed32(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeSFixed64(int i3, long j2) throws IOException {
        this.output.writeSFixed64(i3, j2);
    }

    public void writeSFixed64List(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeSFixed64SizeNoTag(list.get(i6).longValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeSFixed64NoTag(list.get(i4).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeSFixed64(i3, list.get(i4).longValue());
            i4++;
        }
    }

    public void writeSInt32(int i3, int i4) throws IOException {
        this.output.writeSInt32(i3, i4);
    }

    public void writeSInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeSInt32SizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeSInt32NoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeSInt32(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeSInt64(int i3, long j2) throws IOException {
        this.output.writeSInt64(i3, j2);
    }

    public void writeSInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeSInt64SizeNoTag(list.get(i6).longValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeSInt64NoTag(list.get(i4).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeSInt64(i3, list.get(i4).longValue());
            i4++;
        }
    }

    @Deprecated
    public void writeStartGroup(int i3) throws IOException {
        this.output.writeTag(i3, 3);
    }

    public void writeString(int i3, String str) throws IOException {
        this.output.writeString(i3, str);
    }

    public void writeStringList(int i3, List<String> list) throws IOException {
        int i4 = 0;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i4 < list.size()) {
                writeLazyString(i3, lazyStringList.getRaw(i4));
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeString(i3, list.get(i4));
            i4++;
        }
    }

    public void writeUInt32(int i3, int i4) throws IOException {
        this.output.writeUInt32(i3, i4);
    }

    public void writeUInt32List(int i3, List<Integer> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeUInt32SizeNoTag(list.get(i6).intValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeUInt32NoTag(list.get(i4).intValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeUInt32(i3, list.get(i4).intValue());
            i4++;
        }
    }

    public void writeUInt64(int i3, long j2) throws IOException {
        this.output.writeUInt64(i3, j2);
    }

    public void writeUInt64List(int i3, List<Long> list, boolean z2) throws IOException {
        int i4 = 0;
        if (z2) {
            this.output.writeTag(i3, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += CodedOutputStream.computeUInt64SizeNoTag(list.get(i6).longValue());
            }
            this.output.writeUInt32NoTag(i5);
            while (i4 < list.size()) {
                this.output.writeUInt64NoTag(list.get(i4).longValue());
                i4++;
            }
            return;
        }
        while (i4 < list.size()) {
            this.output.writeUInt64(i3, list.get(i4).longValue());
            i4++;
        }
    }

    public void writeGroup(int i3, Object obj, Schema schema) throws IOException {
        this.output.writeGroup(i3, (MessageLite) obj, schema);
    }

    public void writeMessage(int i3, Object obj, Schema schema) throws IOException {
        this.output.writeMessage(i3, (MessageLite) obj, schema);
    }

    public void writeGroupList(int i3, List<?> list, Schema schema) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            writeGroup(i3, list.get(i4), schema);
        }
    }

    public void writeMessageList(int i3, List<?> list, Schema schema) throws IOException {
        for (int i4 = 0; i4 < list.size(); i4++) {
            writeMessage(i3, list.get(i4), schema);
        }
    }
}
