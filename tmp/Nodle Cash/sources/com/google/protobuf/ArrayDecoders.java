package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import java.io.IOException;

@CheckReturnValue
final class ArrayDecoders {

    /* renamed from: com.google.protobuf.ArrayDecoders$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|(3:35|36|38)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
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
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FLOAT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT64     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.UINT64     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.INT32     // Catch:{ NoSuchFieldError -> 0x003e }
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
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED64     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.FIXED32     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SFIXED32     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BOOL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT32     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.SINT64     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.BYTES     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.STRING     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.GROUP     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$com$google$protobuf$WireFormat$FieldType     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.MESSAGE     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.AnonymousClass1.<clinit>():void");
        }
    }

    private ArrayDecoders() {
    }

    public static int decodeBoolList(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int decodeVarint64 = decodeVarint64(bArr, i4, registers);
        booleanArrayList.addBoolean(registers.long1 != 0);
        while (decodeVarint64 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, decodeVarint64, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(bArr, decodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        return decodeVarint64;
    }

    public static int decodeBytes(byte[] bArr, int i3, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (i4 > bArr.length - decodeVarint32) {
            throw InvalidProtocolBufferException.truncatedMessage();
        } else if (i4 == 0) {
            registers.object1 = ByteString.EMPTY;
            return decodeVarint32;
        } else {
            registers.object1 = ByteString.copyFrom(bArr, decodeVarint32, i4);
            return decodeVarint32 + i4;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004d A[EDGE_INSN: B:30:0x004d->B:22:0x004d ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int decodeBytesList(int r2, byte[] r3, int r4, int r5, com.google.protobuf.Internal.ProtobufList<?> r6, com.google.protobuf.ArrayDecoders.Registers r7) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r4 = decodeVarint32(r3, r4, r7)
            int r0 = r7.int1
            if (r0 < 0) goto L_0x0053
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L_0x004e
            if (r0 != 0) goto L_0x0014
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.EMPTY
            r6.add(r0)
            goto L_0x001c
        L_0x0014:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFrom(r3, r4, r0)
            r6.add(r1)
        L_0x001b:
            int r4 = r4 + r0
        L_0x001c:
            if (r4 >= r5) goto L_0x004d
            int r0 = decodeVarint32(r3, r4, r7)
            int r1 = r7.int1
            if (r2 == r1) goto L_0x0027
            goto L_0x004d
        L_0x0027:
            int r4 = decodeVarint32(r3, r0, r7)
            int r0 = r7.int1
            if (r0 < 0) goto L_0x0048
            int r1 = r3.length
            int r1 = r1 - r4
            if (r0 > r1) goto L_0x0043
            if (r0 != 0) goto L_0x003b
            com.google.protobuf.ByteString r0 = com.google.protobuf.ByteString.EMPTY
            r6.add(r0)
            goto L_0x001c
        L_0x003b:
            com.google.protobuf.ByteString r1 = com.google.protobuf.ByteString.copyFrom(r3, r4, r0)
            r6.add(r1)
            goto L_0x001b
        L_0x0043:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r2
        L_0x0048:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r2
        L_0x004d:
            return r4
        L_0x004e:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r2
        L_0x0053:
            com.google.protobuf.InvalidProtocolBufferException r2 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.decodeBytesList(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public static double decodeDouble(byte[] bArr, int i3) {
        return Double.longBitsToDouble(decodeFixed64(bArr, i3));
    }

    public static int decodeDoubleList(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        doubleArrayList.addDouble(decodeDouble(bArr, i4));
        int i6 = i4 + 8;
        while (i6 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, i6, registers);
            if (i3 != registers.int1) {
                break;
            }
            doubleArrayList.addDouble(decodeDouble(bArr, decodeVarint32));
            i6 = decodeVarint32 + 8;
        }
        return i6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01fa, code lost:
        r10 = r10 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0205, code lost:
        r10 = r10 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int decodeExtension(int r8, byte[] r9, int r10, int r11, com.google.protobuf.GeneratedMessageLite.ExtendableMessage<?, ?> r12, com.google.protobuf.GeneratedMessageLite.GeneratedExtension<?, ?> r13, com.google.protobuf.UnknownFieldSchema<com.google.protobuf.UnknownFieldSetLite, com.google.protobuf.UnknownFieldSetLite> r14, com.google.protobuf.ArrayDecoders.Registers r15) throws java.io.IOException {
        /*
            com.google.protobuf.FieldSet<com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor> r0 = r12.extensions
            int r2 = r8 >>> 3
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            boolean r8 = r8.isRepeated()
            if (r8 == 0) goto L_0x00ea
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            boolean r8 = r8.isPacked()
            if (r8 == 0) goto L_0x00ea
            int[] r8 = com.google.protobuf.ArrayDecoders.AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType
            com.google.protobuf.WireFormat$FieldType r11 = r13.getLiteType()
            int r11 = r11.ordinal()
            r8 = r8[r11]
            switch(r8) {
                case 1: goto L_0x00da;
                case 2: goto L_0x00ca;
                case 3: goto L_0x00ba;
                case 4: goto L_0x00ba;
                case 5: goto L_0x00aa;
                case 6: goto L_0x00aa;
                case 7: goto L_0x009a;
                case 8: goto L_0x009a;
                case 9: goto L_0x008a;
                case 10: goto L_0x008a;
                case 11: goto L_0x007a;
                case 12: goto L_0x006a;
                case 13: goto L_0x005a;
                case 14: goto L_0x003d;
                default: goto L_0x0023;
            }
        L_0x0023:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Type cannot be packed: "
            r9.<init>(r10)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            com.google.protobuf.WireFormat$FieldType r10 = r10.getLiteType()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x003d:
            com.google.protobuf.IntArrayList r8 = new com.google.protobuf.IntArrayList
            r8.<init>()
            int r9 = decodePackedVarint32List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            com.google.protobuf.Internal$EnumLiteMap r4 = r10.getEnumType()
            r5 = 0
            r1 = r12
            r3 = r8
            r6 = r14
            com.google.protobuf.SchemaUtil.filterUnknownEnumList((java.lang.Object) r1, (int) r2, (java.util.List<java.lang.Integer>) r3, (com.google.protobuf.Internal.EnumLiteMap<?>) r4, r5, r6)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x005a:
            com.google.protobuf.LongArrayList r8 = new com.google.protobuf.LongArrayList
            r8.<init>()
            int r9 = decodePackedSInt64List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x006a:
            com.google.protobuf.IntArrayList r8 = new com.google.protobuf.IntArrayList
            r8.<init>()
            int r9 = decodePackedSInt32List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x007a:
            com.google.protobuf.BooleanArrayList r8 = new com.google.protobuf.BooleanArrayList
            r8.<init>()
            int r9 = decodePackedBoolList(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x008a:
            com.google.protobuf.IntArrayList r8 = new com.google.protobuf.IntArrayList
            r8.<init>()
            int r9 = decodePackedFixed32List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x009a:
            com.google.protobuf.LongArrayList r8 = new com.google.protobuf.LongArrayList
            r8.<init>()
            int r9 = decodePackedFixed64List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x00aa:
            com.google.protobuf.IntArrayList r8 = new com.google.protobuf.IntArrayList
            r8.<init>()
            int r9 = decodePackedVarint32List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x00ba:
            com.google.protobuf.LongArrayList r8 = new com.google.protobuf.LongArrayList
            r8.<init>()
            int r9 = decodePackedVarint64List(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x00ca:
            com.google.protobuf.FloatArrayList r8 = new com.google.protobuf.FloatArrayList
            r8.<init>()
            int r9 = decodePackedFloatList(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x00da:
            com.google.protobuf.DoubleArrayList r8 = new com.google.protobuf.DoubleArrayList
            r8.<init>()
            int r9 = decodePackedDoubleList(r9, r10, r8, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r10 = r13.descriptor
            r0.setField(r10, r8)
            goto L_0x0242
        L_0x00ea:
            com.google.protobuf.WireFormat$FieldType r8 = r13.getLiteType()
            com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.ENUM
            r3 = 0
            if (r8 != r1) goto L_0x0113
            int r10 = decodeVarint32(r9, r10, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            com.google.protobuf.Internal$EnumLiteMap r8 = r8.getEnumType()
            int r9 = r15.int1
            com.google.protobuf.Internal$EnumLite r8 = r8.findValueByNumber(r9)
            if (r8 != 0) goto L_0x010b
            int r8 = r15.int1
            com.google.protobuf.SchemaUtil.storeUnknownEnum(r12, r2, r8, r3, r14)
            return r10
        L_0x010b:
            int r8 = r15.int1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            goto L_0x0230
        L_0x0113:
            int[] r8 = com.google.protobuf.ArrayDecoders.AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType
            com.google.protobuf.WireFormat$FieldType r12 = r13.getLiteType()
            int r12 = r12.ordinal()
            r8 = r8[r12]
            switch(r8) {
                case 1: goto L_0x0227;
                case 2: goto L_0x021e;
                case 3: goto L_0x0213;
                case 4: goto L_0x0213;
                case 5: goto L_0x0208;
                case 6: goto L_0x0208;
                case 7: goto L_0x01fd;
                case 8: goto L_0x01fd;
                case 9: goto L_0x01f2;
                case 10: goto L_0x01f2;
                case 11: goto L_0x01de;
                case 12: goto L_0x01cf;
                case 13: goto L_0x01c0;
                case 14: goto L_0x01b8;
                case 15: goto L_0x01b0;
                case 16: goto L_0x01a8;
                case 17: goto L_0x0161;
                case 18: goto L_0x0124;
                default: goto L_0x0122;
            }
        L_0x0122:
            goto L_0x0230
        L_0x0124:
            com.google.protobuf.Protobuf r8 = com.google.protobuf.Protobuf.getInstance()
            com.google.protobuf.MessageLite r12 = r13.getMessageDefaultInstance()
            java.lang.Class r12 = r12.getClass()
            com.google.protobuf.Schema r2 = r8.schemaFor(r12)
            boolean r8 = r13.isRepeated()
            if (r8 == 0) goto L_0x0146
            int r8 = decodeMessageField(r2, r9, r10, r11, r15)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r13.descriptor
            java.lang.Object r10 = r15.object1
            r0.addRepeatedField(r9, r10)
            goto L_0x0160
        L_0x0146:
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            java.lang.Object r8 = r0.getField(r8)
            if (r8 != 0) goto L_0x0157
            java.lang.Object r8 = r2.newInstance()
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r12 = r13.descriptor
            r0.setField(r12, r8)
        L_0x0157:
            r1 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r15
            int r8 = mergeMessageField(r1, r2, r3, r4, r5, r6)
        L_0x0160:
            return r8
        L_0x0161:
            int r8 = r2 << 3
            r6 = r8 | 4
            com.google.protobuf.Protobuf r8 = com.google.protobuf.Protobuf.getInstance()
            com.google.protobuf.MessageLite r12 = r13.getMessageDefaultInstance()
            java.lang.Class r12 = r12.getClass()
            com.google.protobuf.Schema r2 = r8.schemaFor(r12)
            boolean r8 = r13.isRepeated()
            if (r8 == 0) goto L_0x018d
            r1 = r2
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r6
            r6 = r15
            int r8 = decodeGroupField(r1, r2, r3, r4, r5, r6)
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r9 = r13.descriptor
            java.lang.Object r10 = r15.object1
            r0.addRepeatedField(r9, r10)
            goto L_0x01a7
        L_0x018d:
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            java.lang.Object r8 = r0.getField(r8)
            if (r8 != 0) goto L_0x019e
            java.lang.Object r8 = r2.newInstance()
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r12 = r13.descriptor
            r0.setField(r12, r8)
        L_0x019e:
            r1 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r7 = r15
            int r8 = mergeGroupField(r1, r2, r3, r4, r5, r6, r7)
        L_0x01a7:
            return r8
        L_0x01a8:
            int r10 = decodeString(r9, r10, r15)
            java.lang.Object r3 = r15.object1
            goto L_0x0230
        L_0x01b0:
            int r10 = decodeBytes(r9, r10, r15)
            java.lang.Object r3 = r15.object1
            goto L_0x0230
        L_0x01b8:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Shouldn't reach here."
            r8.<init>(r9)
            throw r8
        L_0x01c0:
            int r10 = decodeVarint64(r9, r10, r15)
            long r8 = r15.long1
            long r8 = com.google.protobuf.CodedInputStream.decodeZigZag64(r8)
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            goto L_0x0230
        L_0x01cf:
            int r10 = decodeVarint32(r9, r10, r15)
            int r8 = r15.int1
            int r8 = com.google.protobuf.CodedInputStream.decodeZigZag32(r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            goto L_0x0230
        L_0x01de:
            int r10 = decodeVarint64(r9, r10, r15)
            long r8 = r15.long1
            r11 = 0
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x01ec
            r8 = 1
            goto L_0x01ed
        L_0x01ec:
            r8 = 0
        L_0x01ed:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r8)
            goto L_0x0230
        L_0x01f2:
            int r8 = decodeFixed32(r9, r10)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
        L_0x01fa:
            int r10 = r10 + 4
            goto L_0x0230
        L_0x01fd:
            long r8 = decodeFixed64(r9, r10)
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
        L_0x0205:
            int r10 = r10 + 8
            goto L_0x0230
        L_0x0208:
            int r10 = decodeVarint32(r9, r10, r15)
            int r8 = r15.int1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            goto L_0x0230
        L_0x0213:
            int r10 = decodeVarint64(r9, r10, r15)
            long r8 = r15.long1
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            goto L_0x0230
        L_0x021e:
            float r8 = decodeFloat(r9, r10)
            java.lang.Float r3 = java.lang.Float.valueOf(r8)
            goto L_0x01fa
        L_0x0227:
            double r8 = decodeDouble(r9, r10)
            java.lang.Double r3 = java.lang.Double.valueOf(r8)
            goto L_0x0205
        L_0x0230:
            boolean r8 = r13.isRepeated()
            if (r8 == 0) goto L_0x023c
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            r0.addRepeatedField(r8, r3)
            goto L_0x0241
        L_0x023c:
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r8 = r13.descriptor
            r0.setField(r8, r3)
        L_0x0241:
            r9 = r10
        L_0x0242:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.decodeExtension(int, byte[], int, int, com.google.protobuf.GeneratedMessageLite$ExtendableMessage, com.google.protobuf.GeneratedMessageLite$GeneratedExtension, com.google.protobuf.UnknownFieldSchema, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public static int decodeExtensionOrUnknownField(int i3, byte[] bArr, int i4, int i5, Object obj, MessageLite messageLite, UnknownFieldSchema<UnknownFieldSetLite, UnknownFieldSetLite> unknownFieldSchema, Registers registers) throws IOException {
        GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber = registers.extensionRegistry.findLiteExtensionByNumber(messageLite, i3 >>> 3);
        if (findLiteExtensionByNumber == null) {
            return decodeUnknownField(i3, bArr, i4, i5, MessageSchema.getMutableUnknownFields(obj), registers);
        }
        GeneratedMessageLite.ExtendableMessage extendableMessage = (GeneratedMessageLite.ExtendableMessage) obj;
        extendableMessage.ensureExtensionsAreMutable();
        return decodeExtension(i3, bArr, i4, i5, extendableMessage, findLiteExtensionByNumber, unknownFieldSchema, registers);
    }

    public static int decodeFixed32(byte[] bArr, int i3) {
        return ((bArr[i3 + 3] & 255) << Ascii.CAN) | (bArr[i3] & 255) | ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3 + 2] & 255) << 16);
    }

    public static int decodeFixed32List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        intArrayList.addInt(decodeFixed32(bArr, i4));
        int i6 = i4 + 4;
        while (i6 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, i6, registers);
            if (i3 != registers.int1) {
                break;
            }
            intArrayList.addInt(decodeFixed32(bArr, decodeVarint32));
            i6 = decodeVarint32 + 4;
        }
        return i6;
    }

    public static long decodeFixed64(byte[] bArr, int i3) {
        return ((((long) bArr[i3 + 7]) & 255) << 56) | (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 1]) & 255) << 8) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 6]) & 255) << 48);
    }

    public static int decodeFixed64List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        longArrayList.addLong(decodeFixed64(bArr, i4));
        int i6 = i4 + 8;
        while (i6 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, i6, registers);
            if (i3 != registers.int1) {
                break;
            }
            longArrayList.addLong(decodeFixed64(bArr, decodeVarint32));
            i6 = decodeVarint32 + 8;
        }
        return i6;
    }

    public static float decodeFloat(byte[] bArr, int i3) {
        return Float.intBitsToFloat(decodeFixed32(bArr, i3));
    }

    public static int decodeFloatList(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        floatArrayList.addFloat(decodeFloat(bArr, i4));
        int i6 = i4 + 4;
        while (i6 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, i6, registers);
            if (i3 != registers.int1) {
                break;
            }
            floatArrayList.addFloat(decodeFloat(bArr, decodeVarint32));
            i6 = decodeVarint32 + 4;
        }
        return i6;
    }

    public static int decodeGroupField(Schema schema, byte[] bArr, int i3, int i4, int i5, Registers registers) throws IOException {
        Object newInstance = schema.newInstance();
        int mergeGroupField = mergeGroupField(newInstance, schema, bArr, i3, i4, i5, registers);
        schema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return mergeGroupField;
    }

    public static int decodeGroupList(Schema schema, int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        int i6 = (i3 & -8) | 4;
        int decodeGroupField = decodeGroupField(schema, bArr, i4, i5, i6, registers);
        protobufList.add(registers.object1);
        while (decodeGroupField < i5) {
            int decodeVarint32 = decodeVarint32(bArr, decodeGroupField, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeGroupField = decodeGroupField(schema, bArr, decodeVarint32, i5, i6, registers);
            protobufList.add(registers.object1);
        }
        return decodeGroupField;
    }

    public static int decodeMessageField(Schema schema, byte[] bArr, int i3, int i4, Registers registers) throws IOException {
        Object newInstance = schema.newInstance();
        int mergeMessageField = mergeMessageField(newInstance, schema, bArr, i3, i4, registers);
        schema.makeImmutable(newInstance);
        registers.object1 = newInstance;
        return mergeMessageField;
    }

    public static int decodeMessageList(Schema<?> schema, int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        int decodeMessageField = decodeMessageField(schema, bArr, i4, i5, registers);
        protobufList.add(registers.object1);
        while (decodeMessageField < i5) {
            int decodeVarint32 = decodeVarint32(bArr, decodeMessageField, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeMessageField = decodeMessageField(schema, bArr, decodeVarint32, i5, registers);
            protobufList.add(registers.object1);
        }
        return decodeMessageField;
    }

    public static int decodePackedBoolList(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, registers);
            booleanArrayList.addBoolean(registers.long1 != 0);
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedDoubleList(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            doubleArrayList.addDouble(decodeDouble(bArr, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed32List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            intArrayList.addInt(decodeFixed32(bArr, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFixed64List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            longArrayList.addLong(decodeFixed64(bArr, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedFloatList(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            floatArrayList.addFloat(decodeFloat(bArr, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt32List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            decodeVarint32 = decodeVarint32(bArr, decodeVarint32, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedSInt64List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint32List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            decodeVarint32 = decodeVarint32(bArr, decodeVarint32, registers);
            intArrayList.addInt(registers.int1);
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodePackedVarint64List(byte[] bArr, int i3, Internal.ProtobufList<?> protobufList, Registers registers) throws IOException {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1 + decodeVarint32;
        while (decodeVarint32 < i4) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        if (decodeVarint32 == i4) {
            return decodeVarint32;
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int decodeSInt32List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i4, registers);
        intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        while (decodeVarint32 < i5) {
            int decodeVarint322 = decodeVarint32(bArr, decodeVarint32, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(bArr, decodeVarint322, registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(registers.int1));
        }
        return decodeVarint32;
    }

    public static int decodeSInt64List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint64 = decodeVarint64(bArr, i4, registers);
        longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        while (decodeVarint64 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, decodeVarint64, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(bArr, decodeVarint32, registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(registers.long1));
        }
        return decodeVarint64;
    }

    public static int decodeString(byte[] bArr, int i3, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (i4 == 0) {
            registers.object1 = "";
            return decodeVarint32;
        } else {
            registers.object1 = new String(bArr, decodeVarint32, i4, Internal.UTF_8);
            return decodeVarint32 + i4;
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0044 A[EDGE_INSN: B:21:0x0044->B:17:0x0044 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001d  */
    public static int decodeStringList(int r4, byte[] r5, int r6, int r7, com.google.protobuf.Internal.ProtobufList<?> r8, com.google.protobuf.ArrayDecoders.Registers r9) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r6 = decodeVarint32(r5, r6, r9)
            int r0 = r9.int1
            if (r0 < 0) goto L_0x0045
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0010
            r8.add(r1)
            goto L_0x001b
        L_0x0010:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.protobuf.Internal.UTF_8
            r2.<init>(r5, r6, r0, r3)
            r8.add(r2)
        L_0x001a:
            int r6 = r6 + r0
        L_0x001b:
            if (r6 >= r7) goto L_0x0044
            int r0 = decodeVarint32(r5, r6, r9)
            int r2 = r9.int1
            if (r4 == r2) goto L_0x0026
            goto L_0x0044
        L_0x0026:
            int r6 = decodeVarint32(r5, r0, r9)
            int r0 = r9.int1
            if (r0 < 0) goto L_0x003f
            if (r0 != 0) goto L_0x0034
            r8.add(r1)
            goto L_0x001b
        L_0x0034:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.protobuf.Internal.UTF_8
            r2.<init>(r5, r6, r0, r3)
            r8.add(r2)
            goto L_0x001a
        L_0x003f:
            com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r4
        L_0x0044:
            return r6
        L_0x0045:
            com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.decodeStringList(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        r2 = r7 + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (com.google.protobuf.Utf8.isValidUtf8(r6, r7, r2) == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r9.add(new java.lang.String(r6, r7, r0, com.google.protobuf.Internal.UTF_8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        throw com.google.protobuf.InvalidProtocolBufferException.invalidUtf8();
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0059 A[EDGE_INSN: B:28:0x0059->B:23:0x0059 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int decodeStringListRequireUtf8(int r5, byte[] r6, int r7, int r8, com.google.protobuf.Internal.ProtobufList<?> r9, com.google.protobuf.ArrayDecoders.Registers r10) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            int r7 = decodeVarint32(r6, r7, r10)
            int r0 = r10.int1
            if (r0 < 0) goto L_0x005f
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0010
            r9.add(r1)
            goto L_0x0023
        L_0x0010:
            int r2 = r7 + r0
            boolean r3 = com.google.protobuf.Utf8.isValidUtf8(r6, r7, r2)
            if (r3 == 0) goto L_0x005a
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.protobuf.Internal.UTF_8
            r3.<init>(r6, r7, r0, r4)
            r9.add(r3)
        L_0x0022:
            r7 = r2
        L_0x0023:
            if (r7 >= r8) goto L_0x0059
            int r0 = decodeVarint32(r6, r7, r10)
            int r2 = r10.int1
            if (r5 == r2) goto L_0x002e
            goto L_0x0059
        L_0x002e:
            int r7 = decodeVarint32(r6, r0, r10)
            int r0 = r10.int1
            if (r0 < 0) goto L_0x0054
            if (r0 != 0) goto L_0x003c
            r9.add(r1)
            goto L_0x0023
        L_0x003c:
            int r2 = r7 + r0
            boolean r3 = com.google.protobuf.Utf8.isValidUtf8(r6, r7, r2)
            if (r3 == 0) goto L_0x004f
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.protobuf.Internal.UTF_8
            r3.<init>(r6, r7, r0, r4)
            r9.add(r3)
            goto L_0x0022
        L_0x004f:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.invalidUtf8()
            throw r5
        L_0x0054:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r5
        L_0x0059:
            return r7
        L_0x005a:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.invalidUtf8()
            throw r5
        L_0x005f:
            com.google.protobuf.InvalidProtocolBufferException r5 = com.google.protobuf.InvalidProtocolBufferException.negativeSize()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.decodeStringListRequireUtf8(int, byte[], int, int, com.google.protobuf.Internal$ProtobufList, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public static int decodeStringRequireUtf8(byte[] bArr, int i3, Registers registers) throws InvalidProtocolBufferException {
        int decodeVarint32 = decodeVarint32(bArr, i3, registers);
        int i4 = registers.int1;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if (i4 == 0) {
            registers.object1 = "";
            return decodeVarint32;
        } else {
            registers.object1 = Utf8.decodeUtf8(bArr, decodeVarint32, i4);
            return decodeVarint32 + i4;
        }
    }

    public static int decodeUnknownField(int i3, byte[] bArr, int i4, int i5, UnknownFieldSetLite unknownFieldSetLite, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i3) != 0) {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                int decodeVarint64 = decodeVarint64(bArr, i4, registers);
                unknownFieldSetLite.storeField(i3, Long.valueOf(registers.long1));
                return decodeVarint64;
            } else if (tagWireType == 1) {
                unknownFieldSetLite.storeField(i3, Long.valueOf(decodeFixed64(bArr, i4)));
                return i4 + 8;
            } else if (tagWireType == 2) {
                int decodeVarint32 = decodeVarint32(bArr, i4, registers);
                int i6 = registers.int1;
                if (i6 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                } else if (i6 <= bArr.length - decodeVarint32) {
                    if (i6 == 0) {
                        unknownFieldSetLite.storeField(i3, ByteString.EMPTY);
                    } else {
                        unknownFieldSetLite.storeField(i3, ByteString.copyFrom(bArr, decodeVarint32, i6));
                    }
                    return decodeVarint32 + i6;
                } else {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } else if (tagWireType == 3) {
                UnknownFieldSetLite newInstance = UnknownFieldSetLite.newInstance();
                int i7 = (i3 & -8) | 4;
                int i8 = 0;
                while (true) {
                    if (i4 >= i5) {
                        break;
                    }
                    int decodeVarint322 = decodeVarint32(bArr, i4, registers);
                    int i9 = registers.int1;
                    if (i9 == i7) {
                        i8 = i9;
                        i4 = decodeVarint322;
                        break;
                    }
                    i8 = i9;
                    i4 = decodeUnknownField(i9, bArr, decodeVarint322, i5, newInstance, registers);
                }
                if (i4 > i5 || i8 != i7) {
                    throw InvalidProtocolBufferException.parseFailure();
                }
                unknownFieldSetLite.storeField(i3, newInstance);
                return i4;
            } else if (tagWireType == 5) {
                unknownFieldSetLite.storeField(i3, Integer.valueOf(decodeFixed32(bArr, i4)));
                return i4 + 4;
            } else {
                throw InvalidProtocolBufferException.invalidTag();
            }
        } else {
            throw InvalidProtocolBufferException.invalidTag();
        }
    }

    public static int decodeVarint32(byte[] bArr, int i3, Registers registers) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 < 0) {
            return decodeVarint32(b3, bArr, i4, registers);
        }
        registers.int1 = b3;
        return i4;
    }

    public static int decodeVarint32List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i4, registers);
        intArrayList.addInt(registers.int1);
        while (decodeVarint32 < i5) {
            int decodeVarint322 = decodeVarint32(bArr, decodeVarint32, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(bArr, decodeVarint322, registers);
            intArrayList.addInt(registers.int1);
        }
        return decodeVarint32;
    }

    public static int decodeVarint64(byte[] bArr, int i3, Registers registers) {
        int i4 = i3 + 1;
        long j2 = (long) bArr[i3];
        if (j2 < 0) {
            return decodeVarint64(j2, bArr, i4, registers);
        }
        registers.long1 = j2;
        return i4;
    }

    public static int decodeVarint64List(int i3, byte[] bArr, int i4, int i5, Internal.ProtobufList<?> protobufList, Registers registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint64 = decodeVarint64(bArr, i4, registers);
        longArrayList.addLong(registers.long1);
        while (decodeVarint64 < i5) {
            int decodeVarint32 = decodeVarint32(bArr, decodeVarint64, registers);
            if (i3 != registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(bArr, decodeVarint32, registers);
            longArrayList.addLong(registers.long1);
        }
        return decodeVarint64;
    }

    public static int mergeGroupField(Object obj, Schema schema, byte[] bArr, int i3, int i4, int i5, Registers registers) throws IOException {
        int parseMessage = ((MessageSchema) schema).parseMessage(obj, bArr, i3, i4, i5, registers);
        registers.object1 = obj;
        return parseMessage;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int mergeMessageField(java.lang.Object r6, com.google.protobuf.Schema r7, byte[] r8, int r9, int r10, com.google.protobuf.ArrayDecoders.Registers r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = decodeVarint32(r9, r8, r0, r11)
            int r9 = r11.int1
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.mergeFrom(r1, r2, r3, r4, r5)
            r11.object1 = r6
            return r9
        L_0x001e:
            com.google.protobuf.InvalidProtocolBufferException r6 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.ArrayDecoders.mergeMessageField(java.lang.Object, com.google.protobuf.Schema, byte[], int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    public static int skipField(int i3, byte[] bArr, int i4, int i5, Registers registers) throws InvalidProtocolBufferException {
        if (WireFormat.getTagFieldNumber(i3) != 0) {
            int tagWireType = WireFormat.getTagWireType(i3);
            if (tagWireType == 0) {
                return decodeVarint64(bArr, i4, registers);
            }
            if (tagWireType == 1) {
                return i4 + 8;
            }
            if (tagWireType == 2) {
                return decodeVarint32(bArr, i4, registers) + registers.int1;
            }
            if (tagWireType == 3) {
                int i6 = (i3 & -8) | 4;
                int i7 = 0;
                while (i4 < i5) {
                    i4 = decodeVarint32(bArr, i4, registers);
                    i7 = registers.int1;
                    if (i7 == i6) {
                        break;
                    }
                    i4 = skipField(i7, bArr, i4, i5, registers);
                }
                if (i4 <= i5 && i7 == i6) {
                    return i4;
                }
                throw InvalidProtocolBufferException.parseFailure();
            } else if (tagWireType == 5) {
                return i4 + 4;
            } else {
                throw InvalidProtocolBufferException.invalidTag();
            }
        } else {
            throw InvalidProtocolBufferException.invalidTag();
        }
    }

    public static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        public Registers() {
            this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        }

        public Registers(ExtensionRegistryLite extensionRegistryLite) {
            extensionRegistryLite.getClass();
            this.extensionRegistry = extensionRegistryLite;
        }
    }

    public static int decodeVarint32(int i3, byte[] bArr, int i4, Registers registers) {
        int i5 = i3 & 127;
        int i6 = i4 + 1;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            registers.int1 = i5 | (b3 << 7);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 7);
        int i8 = i4 + 2;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            registers.int1 = i7 | (b4 << Ascii.SO);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.SO);
        int i10 = i4 + 3;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            registers.int1 = i9 | (b5 << Ascii.NAK);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.NAK);
        int i12 = i4 + 4;
        byte b6 = bArr[i10];
        if (b6 >= 0) {
            registers.int1 = i11 | (b6 << Ascii.FS);
            return i12;
        }
        int i13 = i11 | ((b6 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i14 = i12 + 1;
            if (bArr[i12] < 0) {
                i12 = i14;
            } else {
                registers.int1 = i13;
                return i14;
            }
        }
    }

    public static int decodeVarint64(long j2, byte[] bArr, int i3, Registers registers) {
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        long j3 = (j2 & 127) | (((long) (b3 & Byte.MAX_VALUE)) << 7);
        int i5 = 7;
        while (b3 < 0) {
            int i6 = i4 + 1;
            byte b4 = bArr[i4];
            i5 += 7;
            j3 |= ((long) (b4 & Byte.MAX_VALUE)) << i5;
            byte b5 = b4;
            i4 = i6;
            b3 = b5;
        }
        registers.long1 = j3;
        return i4;
    }
}
