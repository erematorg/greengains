package com.adjust.sdk.sig;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final int f3345a;

    public c(int i3) {
        this.f3345a = i3;
    }

    public final void a(Context context) {
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        instance.load((KeyStore.LoadStoreParameter) null);
        instance.deleteEntry("key2");
        context.getSharedPreferences("adjust_keys", 0).edit().remove("encrypted_key").apply();
    }

    public final void b(Context context) {
        int i3 = this.f3345a;
        if (i3 >= 23) {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load((KeyStore.LoadStoreParameter) null);
            if (!instance.containsAlias("key2")) {
                KeyGenerator instance2 = KeyGenerator.getInstance("HmacSHA256", "AndroidKeyStore");
                instance2.init(new KeyGenParameterSpec.Builder("key2", 4).build());
                instance2.generateKey();
            }
        } else if (i3 >= 18) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("adjust_keys", 0);
            if (!sharedPreferences.contains("encrypted_key")) {
                Date time = Calendar.getInstance().getTime();
                Calendar instance3 = Calendar.getInstance();
                instance3.add(1, 1);
                KeyPairGeneratorSpec.Builder endDate = new KeyPairGeneratorSpec.Builder(context).setAlias("key2").setSubject(new X500Principal("CN=key2")).setSerialNumber(BigInteger.TEN).setStartDate(time).setEndDate(instance3.getTime());
                if (this.f3345a >= 19) {
                    endDate.setKeySize(1024);
                }
                KeyPairGenerator instance4 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
                instance4.initialize(endDate.build());
                instance4.genKeyPair();
                byte[] bArr = new byte[16];
                new SecureRandom().nextBytes(bArr);
                KeyStore instance5 = KeyStore.getInstance("AndroidKeyStore");
                instance5.load((KeyStore.LoadStoreParameter) null);
                Cipher instance6 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance6.init(1, ((KeyStore.PrivateKeyEntry) instance5.getEntry("key2", (KeyStore.ProtectionParameter) null)).getCertificate().getPublicKey());
                String encodeToString = Base64.encodeToString(instance6.doFinal(bArr), 0);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("encrypted_key", encodeToString);
                edit.apply();
            }
        } else {
            throw new b();
        }
    }

    public final byte[] a(Context context, byte[] bArr) {
        Key key;
        Mac instance = Mac.getInstance("HmacSHA256");
        int i3 = this.f3345a;
        if (i3 >= 23) {
            KeyStore instance2 = KeyStore.getInstance("AndroidKeyStore");
            instance2.load((KeyStore.LoadStoreParameter) null);
            key = instance2.getKey("key2", (char[]) null);
        } else if (i3 >= 18) {
            String string = context.getSharedPreferences("adjust_keys", 0).getString("encrypted_key", (String) null);
            if (string != null) {
                byte[] decode = Base64.decode(string, 0);
                KeyStore instance3 = KeyStore.getInstance("AndroidKeyStore");
                instance3.load((KeyStore.LoadStoreParameter) null);
                Cipher instance4 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance4.init(2, ((KeyStore.PrivateKeyEntry) instance3.getEntry("key2", (KeyStore.ProtectionParameter) null)).getPrivateKey());
                key = new SecretKeySpec(instance4.doFinal(decode), BouncyCastleKeyManagementRepository.AES);
            } else {
                throw new RuntimeException("Failed to find encrypted key in SharedPreferences");
            }
        } else {
            throw new RuntimeException("Unsupported version");
        }
        instance.init(key);
        instance.update(bArr);
        return instance.doFinal();
    }
}
