package com.google.crypto.tink;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import com.google.errorprone.annotations.InlineMe;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

public final class JsonKeysetWriter implements KeysetWriter {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final OutputStream outputStream;

    private JsonKeysetWriter(OutputStream outputStream2) {
        this.outputStream = outputStream2;
    }

    private JsonObject toJson(Keyset keyset) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("primaryKeyId", (Number) Long.valueOf(toUnsignedLong(keyset.getPrimaryKeyId())));
        JsonArray jsonArray = new JsonArray();
        for (Keyset.Key json : keyset.getKeyList()) {
            jsonArray.add((JsonElement) toJson(json));
        }
        jsonObject.add(JwtUtilsKt.DID_METHOD_KEY, jsonArray);
        return jsonObject;
    }

    private long toUnsignedLong(int i3) {
        return ((long) i3) & 4294967295L;
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(file))")
    @Deprecated
    public static KeysetWriter withFile(File file) throws IOException {
        return withOutputStream(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream2) {
        return new JsonKeysetWriter(outputStream2);
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.File", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(new File(path)))")
    @Deprecated
    public static KeysetWriter withPath(String str) throws IOException {
        return withOutputStream(new FileOutputStream(new File(str)));
    }

    public void write(Keyset keyset) throws IOException {
        try {
            OutputStream outputStream2 = this.outputStream;
            String jsonElement = toJson(keyset).toString();
            Charset charset = UTF_8;
            outputStream2.write(jsonElement.getBytes(charset));
            this.outputStream.write(System.lineSeparator().getBytes(charset));
            this.outputStream.close();
        } catch (JsonParseException e3) {
            throw new IOException(e3);
        } catch (Throwable th) {
            this.outputStream.close();
            throw th;
        }
    }

    @RequiresApi(26)
    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(path.toFile()))")
    @Deprecated
    public static KeysetWriter withPath(Path path) throws IOException {
        return withOutputStream(new FileOutputStream(path.toFile()));
    }

    private JsonObject toJson(Keyset.Key key) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("keyData", toJson(key.getKeyData()));
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, key.getStatus().name());
        jsonObject.addProperty("keyId", (Number) Long.valueOf(toUnsignedLong(key.getKeyId())));
        jsonObject.addProperty("outputPrefixType", key.getOutputPrefixType().name());
        return jsonObject;
    }

    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        OutputStream outputStream2 = this.outputStream;
        String jsonElement = toJson(encryptedKeyset).toString();
        Charset charset = UTF_8;
        outputStream2.write(jsonElement.getBytes(charset));
        this.outputStream.write(System.lineSeparator().getBytes(charset));
        this.outputStream.close();
    }

    private JsonObject toJson(KeyData keyData) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("typeUrl", keyData.getTypeUrl());
        jsonObject.addProperty("value", Base64.encode(keyData.getValue().toByteArray()));
        jsonObject.addProperty("keyMaterialType", keyData.getKeyMaterialType().name());
        return jsonObject;
    }

    private JsonObject toJson(EncryptedKeyset encryptedKeyset) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("encryptedKeyset", Base64.encode(encryptedKeyset.getEncryptedKeyset().toByteArray()));
        jsonObject.add("keysetInfo", toJson(encryptedKeyset.getKeysetInfo()));
        return jsonObject;
    }

    private JsonObject toJson(KeysetInfo keysetInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("primaryKeyId", (Number) Long.valueOf(toUnsignedLong(keysetInfo.getPrimaryKeyId())));
        JsonArray jsonArray = new JsonArray();
        for (KeysetInfo.KeyInfo json : keysetInfo.getKeyInfoList()) {
            jsonArray.add((JsonElement) toJson(json));
        }
        jsonObject.add("keyInfo", jsonArray);
        return jsonObject;
    }

    private JsonObject toJson(KeysetInfo.KeyInfo keyInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("typeUrl", keyInfo.getTypeUrl());
        jsonObject.addProperty(NotificationCompat.CATEGORY_STATUS, keyInfo.getStatus().name());
        jsonObject.addProperty("keyId", (Number) Long.valueOf(toUnsignedLong(keyInfo.getKeyId())));
        jsonObject.addProperty("outputPrefixType", keyInfo.getOutputPrefixType().name());
        return jsonObject;
    }
}
