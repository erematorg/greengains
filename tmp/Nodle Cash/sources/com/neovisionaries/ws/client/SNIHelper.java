package com.neovisionaries.ws.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

class SNIHelper {
    private static Constructor<?> sSNIHostNameConstructor;
    private static Method sSetServerNamesMethod;

    static {
        try {
            initialize();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static Object createSNIHostName(String str) {
        return Misc.newInstance(sSNIHostNameConstructor, str);
    }

    private static List<Object> createSNIHostNames(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String createSNIHostName : strArr) {
            arrayList.add(createSNIHostName(createSNIHostName));
        }
        return arrayList;
    }

    private static void initialize() throws Exception {
        sSNIHostNameConstructor = Misc.getConstructor("javax.net.ssl.SNIHostName", new Class[]{String.class});
        sSetServerNamesMethod = Misc.getMethod("javax.net.ssl.SSLParameters", "setServerNames", new Class[]{List.class});
    }

    private static void setServerNames(SSLParameters sSLParameters, String[] strArr) {
        Misc.invoke(sSetServerNamesMethod, sSLParameters, createSNIHostNames(strArr));
    }

    public static void setServerNames(Socket socket, String[] strArr) {
        SSLParameters sSLParameters;
        if ((socket instanceof SSLSocket) && strArr != null && (sSLParameters = ((SSLSocket) socket).getSSLParameters()) != null) {
            setServerNames(sSLParameters, strArr);
        }
    }
}
