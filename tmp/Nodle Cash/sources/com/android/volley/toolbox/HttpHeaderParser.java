package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyLog;
import com.google.common.net.HttpHeaders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.lang3.time.TimeZones;
import org.apache.xerces.impl.xs.SchemaSymbols;

public class HttpHeaderParser {
    private static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String RFC1123_OUTPUT_FORMAT = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
    private static final String RFC1123_PARSE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static List<Header> combineHeaders(List<Header> list, Cache.Entry entry) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            for (Header name : list) {
                treeSet.add(name.getName());
            }
        }
        ArrayList arrayList = new ArrayList(list);
        List<Header> list2 = entry.allResponseHeaders;
        if (list2 != null) {
            if (!list2.isEmpty()) {
                for (Header next : entry.allResponseHeaders) {
                    if (!treeSet.contains(next.getName())) {
                        arrayList.add(next);
                    }
                }
            }
        } else if (!entry.responseHeaders.isEmpty()) {
            for (Map.Entry next2 : entry.responseHeaders.entrySet()) {
                if (!treeSet.contains(next2.getKey())) {
                    arrayList.add(new Header((String) next2.getKey(), (String) next2.getValue()));
                }
            }
        }
        return arrayList;
    }

    public static String formatEpochAsRfc1123(long j2) {
        return newUsGmtFormatter(RFC1123_OUTPUT_FORMAT).format(new Date(j2));
    }

    public static Map<String, String> getCacheHeaders(Cache.Entry entry) {
        if (entry == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        String str = entry.etag;
        if (str != null) {
            hashMap.put(HttpHeaders.IF_NONE_MATCH, str);
        }
        long j2 = entry.lastModified;
        if (j2 > 0) {
            hashMap.put(HttpHeaders.IF_MODIFIED_SINCE, formatEpochAsRfc1123(j2));
        }
        return hashMap;
    }

    private static SimpleDateFormat newUsGmtFormatter(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        return simpleDateFormat;
    }

    @Nullable
    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long j2;
        long j3;
        boolean z2;
        long j4;
        long j5;
        NetworkResponse networkResponse2 = networkResponse;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse2.headers;
        if (map == null) {
            return null;
        }
        String str = map.get(HttpHeaders.DATE);
        long parseDateAsEpoch = str != null ? parseDateAsEpoch(str) : 0;
        String str2 = map.get(HttpHeaders.CACHE_CONTROL);
        int i3 = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            z2 = false;
            j3 = 0;
            j2 = 0;
            while (i3 < split.length) {
                String trim = split[i3].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    z2 = true;
                }
                i3++;
            }
            i3 = 1;
        } else {
            z2 = false;
            j3 = 0;
            j2 = 0;
        }
        String str3 = map.get(HttpHeaders.EXPIRES);
        long parseDateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : 0;
        String str4 = map.get(HttpHeaders.LAST_MODIFIED);
        long parseDateAsEpoch3 = str4 != null ? parseDateAsEpoch(str4) : 0;
        String str5 = map.get(HttpHeaders.ETAG);
        if (i3 != 0) {
            j5 = currentTimeMillis + (j3 * 1000);
            j4 = z2 ? j5 : (j2 * 1000) + j5;
        } else {
            j4 = 0;
            if (parseDateAsEpoch <= 0 || parseDateAsEpoch2 < parseDateAsEpoch) {
                j5 = 0;
            } else {
                j5 = currentTimeMillis + (parseDateAsEpoch2 - parseDateAsEpoch);
                j4 = j5;
            }
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse2.data;
        entry.etag = str5;
        entry.softTtl = j5;
        entry.ttl = j4;
        entry.serverDate = parseDateAsEpoch;
        entry.lastModified = parseDateAsEpoch3;
        entry.responseHeaders = map;
        entry.allResponseHeaders = networkResponse2.allHeaders;
        return entry;
    }

    public static String parseCharset(@Nullable Map<String, String> map, String str) {
        String str2;
        if (!(map == null || (str2 = map.get("Content-Type")) == null)) {
            String[] split = str2.split(";", 0);
            for (int i3 = 1; i3 < split.length; i3++) {
                String[] split2 = split[i3].trim().split(StickyVariantProvider.KEY_VALUE_DELIMITER, 0);
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return newUsGmtFormatter(RFC1123_PARSE_FORMAT).parse(str).getTime();
        } catch (ParseException e3) {
            if (SchemaSymbols.ATTVAL_FALSE_0.equals(str) || "-1".equals(str)) {
                VolleyLog.v("Unable to parse dateStr: %s, falling back to 0", str);
                return 0;
            }
            VolleyLog.e(e3, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    public static List<Header> toAllHeaderList(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new Header((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }

    public static Map<String, String> toHeaderMap(List<Header> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header next : list) {
            treeMap.put(next.getName(), next.getValue());
        }
        return treeMap;
    }

    public static String parseCharset(@Nullable Map<String, String> map) {
        return parseCharset(map, "ISO-8859-1");
    }
}
