package com.ionspin.kotlin.bignum;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H¦\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\r\u0010\n\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H¦\u0004¢\u0006\u0002\u0010\u0005J\u001d\u0010\r\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0007H&¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u0012H¦\u0004¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u0012H¦\u0004¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H¦\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0016"}, d2 = {"Lcom/ionspin/kotlin/bignum/BitwiseCapable;", "BigType", "", "and", "other", "(Ljava/lang/Object;)Ljava/lang/Object;", "bitAt", "", "position", "", "not", "()Ljava/lang/Object;", "or", "setBitAt", "bit", "(JZ)Ljava/lang/Object;", "shl", "places", "", "(I)Ljava/lang/Object;", "shr", "xor", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface BitwiseCapable<BigType> {
    BigType and(BigType bigtype);

    boolean bitAt(long j2);

    BigType not();

    BigType or(BigType bigtype);

    BigType setBitAt(long j2, boolean z2);

    BigType shl(int i3);

    BigType shr(int i3);

    BigType xor(BigType bigtype);
}
