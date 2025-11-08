package com.google.crypto.tink.integration.android;

import A.a;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Hex;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class SharedPrefKeysetReader implements KeysetReader {
    private final String keysetName;
    private final SharedPreferences sharedPreferences;

    public SharedPrefKeysetReader(Context context, String str, String str2) throws IOException {
        if (str != null) {
            this.keysetName = str;
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
            } else {
                this.sharedPreferences = applicationContext.getSharedPreferences(str2, 0);
            }
        } else {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
    }

    private byte[] readPref() throws IOException {
        try {
            String string = this.sharedPreferences.getString(this.keysetName, (String) null);
            if (string != null) {
                return Hex.decode(string);
            }
            String str = this.keysetName;
            throw new FileNotFoundException("can't read keyset; the pref value " + str + " does not exist");
        } catch (ClassCastException | IllegalArgumentException unused) {
            throw new CharConversionException(a.l("can't read keyset; the pref value ", this.keysetName, " is not a valid hex string"));
        }
    }

    public Keyset read() throws IOException {
        return Keyset.parseFrom(readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }

    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }
}
