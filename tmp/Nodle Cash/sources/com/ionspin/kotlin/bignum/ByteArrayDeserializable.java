package com.ionspin.kotlin.bignum;

import com.ionspin.kotlin.bignum.BigNumber;
import com.ionspin.kotlin.bignum.integer.Sign;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u001d\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\t\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Lcom/ionspin/kotlin/bignum/ByteArrayDeserializable;", "BigType", "Lcom/ionspin/kotlin/bignum/BigNumber;", "", "fromByteArray", "source", "", "sign", "Lcom/ionspin/kotlin/bignum/integer/Sign;", "([BLcom/ionspin/kotlin/bignum/integer/Sign;)Lcom/ionspin/kotlin/bignum/BigNumber;", "fromUByteArray", "Lkotlin/UByteArray;", "fromUByteArray-rto03Yo", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface ByteArrayDeserializable<BigType extends BigNumber<BigType>> {
    @NotNull
    BigType fromByteArray(@NotNull byte[] bArr, @NotNull Sign sign);

    @NotNull
    /* renamed from: fromUByteArray-rto03Yo  reason: not valid java name */
    BigType m8270fromUByteArrayrto03Yo(@NotNull byte[] bArr, @NotNull Sign sign);
}
