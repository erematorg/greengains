package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
interface HpkeKem {
    byte[] decapsulate(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey) throws GeneralSecurityException;

    HpkeKemEncapOutput encapsulate(byte[] bArr) throws GeneralSecurityException;

    byte[] getKemId() throws GeneralSecurityException;
}
