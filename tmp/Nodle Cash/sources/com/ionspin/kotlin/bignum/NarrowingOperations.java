package com.ionspin.kotlin.bignum;

import com.ionspin.kotlin.bignum.BigNumber;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\"\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\"\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\"\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b \u0010!\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"Lcom/ionspin/kotlin/bignum/NarrowingOperations;", "BigType", "Lcom/ionspin/kotlin/bignum/BigNumber;", "", "byteValue", "", "exactRequired", "", "doubleValue", "", "floatValue", "", "intValue", "", "longValue", "", "shortValue", "", "ubyteValue", "Lkotlin/UByte;", "ubyteValue-Wa3L5BU", "(Z)B", "uintValue", "Lkotlin/UInt;", "uintValue-OGnWXxg", "(Z)I", "ulongValue", "Lkotlin/ULong;", "ulongValue-I7RO_PI", "(Z)J", "ushortValue", "Lkotlin/UShort;", "ushortValue-BwKQO78", "(Z)S", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface NarrowingOperations<BigType extends BigNumber<BigType>> {

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ byte byteValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.byteValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: byteValue");
        }

        public static /* synthetic */ double doubleValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.doubleValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: doubleValue");
        }

        public static /* synthetic */ float floatValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.floatValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: floatValue");
        }

        public static /* synthetic */ int intValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.intValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: intValue");
        }

        public static /* synthetic */ long longValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.longValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: longValue");
        }

        public static /* synthetic */ short shortValue$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.shortValue(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: shortValue");
        }

        /* renamed from: ubyteValue-Wa3L5BU$default  reason: not valid java name */
        public static /* synthetic */ byte m8276ubyteValueWa3L5BU$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.m8272ubyteValueWa3L5BU(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ubyteValue-Wa3L5BU");
        }

        /* renamed from: uintValue-OGnWXxg$default  reason: not valid java name */
        public static /* synthetic */ int m8277uintValueOGnWXxg$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.m8273uintValueOGnWXxg(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: uintValue-OGnWXxg");
        }

        /* renamed from: ulongValue-I7RO_PI$default  reason: not valid java name */
        public static /* synthetic */ long m8278ulongValueI7RO_PI$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.m8274ulongValueI7RO_PI(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ulongValue-I7RO_PI");
        }

        /* renamed from: ushortValue-BwKQO78$default  reason: not valid java name */
        public static /* synthetic */ short m8279ushortValueBwKQO78$default(NarrowingOperations narrowingOperations, boolean z2, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    z2 = false;
                }
                return narrowingOperations.m8275ushortValueBwKQO78(z2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ushortValue-BwKQO78");
        }
    }

    byte byteValue(boolean z2);

    double doubleValue(boolean z2);

    float floatValue(boolean z2);

    int intValue(boolean z2);

    long longValue(boolean z2);

    short shortValue(boolean z2);

    /* renamed from: ubyteValue-Wa3L5BU  reason: not valid java name */
    byte m8272ubyteValueWa3L5BU(boolean z2);

    /* renamed from: uintValue-OGnWXxg  reason: not valid java name */
    int m8273uintValueOGnWXxg(boolean z2);

    /* renamed from: ulongValue-I7RO_PI  reason: not valid java name */
    long m8274ulongValueI7RO_PI(boolean z2);

    /* renamed from: ushortValue-BwKQO78  reason: not valid java name */
    short m8275ushortValueBwKQO78(boolean z2);
}
