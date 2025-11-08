package com.google.crypto.tink.tinkkey;

import com.google.crypto.tink.KeyTemplate;
import com.google.errorprone.annotations.Immutable;

@Immutable
@Deprecated
public interface TinkKey {
    KeyTemplate getKeyTemplate();

    boolean hasSecret();
}
