package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;

@Alpha
public interface PrivateKey {
    Key getPublicKey();
}
