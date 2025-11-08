package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.json.JSONException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadp  reason: invalid package */
public final class zzadp {
    private static final boolean zza(int i3) {
        return i3 >= 200 && i3 < 300;
    }

    private static void zza(HttpURLConnection httpURLConnection, zzadm<?> zzadm, Type type) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (zza(responseCode)) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            StringBuilder sb = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            String sb2 = sb.toString();
            if (!zza(responseCode)) {
                zzadm.zza((String) zzacs.zza(sb2, String.class));
            } else {
                zzadm.zza((zzacu) zzacs.zza(sb2, type));
            }
            httpURLConnection.disconnect();
            return;
        } catch (SocketTimeoutException unused) {
            zzadm.zza("TIMEOUT");
            httpURLConnection.disconnect();
            return;
        } catch (zzaah | IOException e3) {
            zzadm.zza(e3.getMessage());
            httpURLConnection.disconnect();
            return;
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
        throw th;
    }

    public static void zza(String str, zzadm<?> zzadm, Type type, zzacv zzacv) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(60000);
            zzacv.zza((URLConnection) httpURLConnection);
            zza(httpURLConnection, zzadm, type);
        } catch (SocketTimeoutException unused) {
            zzadm.zza("TIMEOUT");
        } catch (UnknownHostException unused2) {
            zzadm.zza("<<Network Error>>");
        } catch (IOException e3) {
            zzadm.zza(e3.getMessage());
        }
    }

    public static void zza(String str, zzacr zzacr, zzadm<?> zzadm, Type type, zzacv zzacv) {
        BufferedOutputStream bufferedOutputStream;
        try {
            Preconditions.checkNotNull(zzacr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoOutput(true);
            byte[] bytes = zzacr.zza().getBytes(Charset.defaultCharset());
            httpURLConnection.setFixedLengthStreamingMode(bytes.length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setConnectTimeout(60000);
            zzacv.zza((URLConnection) httpURLConnection);
            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream(), bytes.length);
            bufferedOutputStream.write(bytes, 0, bytes.length);
            bufferedOutputStream.close();
            zza(httpURLConnection, zzadm, type);
            return;
        } catch (SocketTimeoutException unused) {
            zzadm.zza("TIMEOUT");
            return;
        } catch (UnknownHostException unused2) {
            zzadm.zza("<<Network Error>>");
            return;
        } catch (IOException | NullPointerException | JSONException e3) {
            zzadm.zza(e3.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
