package com.amplitude.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_IDENTIFYS_TABLE = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_LONG_STORE_TABLE = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);";
    private static final String CREATE_STORE_TABLE = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);";
    private static final String EVENT_FIELD = "event";
    protected static final String EVENT_TABLE_NAME = "events";
    protected static final String IDENTIFY_TABLE_NAME = "identifys";
    private static final String ID_FIELD = "id";
    private static final String KEY_FIELD = "key";
    protected static final String LONG_STORE_TABLE_NAME = "long_store";
    protected static final String STORE_TABLE_NAME = "store";
    private static final String TAG = "com.amplitude.api.DatabaseHelper";
    private static final String VALUE_FIELD = "value";
    static final Map<String, DatabaseHelper> instances = new HashMap();
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    private boolean callResetListenerOnDatabaseReset;
    private DatabaseResetListener databaseResetListener;
    File file;
    private String instanceName;

    public DatabaseHelper(Context context) {
        this(context, (String) null);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x0045=Splitter:B:14:0x0045, B:21:0x007d=Splitter:B:21:0x007d} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0082=Splitter:B:24:0x0082, B:19:0x004f=Splitter:B:19:0x004f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long addEventToTable(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            java.lang.String r0 = "DB: Failed to addEvent: "
            java.lang.String r1 = "addEvent to "
            java.lang.String r2 = "DB: Failed to addEvent: "
            java.lang.String r3 = "addEvent to "
            java.lang.String r4 = "Insert into "
            monitor-enter(r10)
            r5 = -1
            android.database.sqlite.SQLiteDatabase r7 = r10.getWritableDatabase()     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b }
            android.content.ContentValues r8 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b }
            r8.<init>()     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b }
            java.lang.String r9 = "event"
            r8.put(r9, r12)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b }
            long r7 = r10.insertEventContentValuesIntoTable(r7, r11, r8)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b }
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x0045
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            java.lang.String r6 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            r9.<init>(r4)     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            r9.append(r11)     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            java.lang.String r4 = " failed"
            r9.append(r4)     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            java.lang.String r4 = r9.toString()     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            r5.w((java.lang.String) r6, (java.lang.String) r4)     // Catch:{ SQLiteException -> 0x0042, StackOverflowError -> 0x003f }
            goto L_0x0045
        L_0x003c:
            r11 = move-exception
            goto L_0x00b3
        L_0x003f:
            r0 = move-exception
            r5 = r7
            goto L_0x004f
        L_0x0042:
            r2 = move-exception
            r5 = r7
            goto L_0x0082
        L_0x0045:
            r10.close()     // Catch:{ all -> 0x0049 }
            goto L_0x00b1
        L_0x0049:
            r11 = move-exception
            goto L_0x00b7
        L_0x004b:
            r0 = move-exception
            goto L_0x004f
        L_0x004d:
            r2 = move-exception
            goto L_0x0082
        L_0x004f:
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x003c }
            java.lang.String r4 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r7.<init>(r3)     // Catch:{ all -> 0x003c }
            r7.append(r11)     // Catch:{ all -> 0x003c }
            java.lang.String r11 = " failed"
            r7.append(r11)     // Catch:{ all -> 0x003c }
            java.lang.String r11 = r7.toString()     // Catch:{ all -> 0x003c }
            r1.e(r4, r11, r0)     // Catch:{ all -> 0x003c }
            com.amplitude.api.Diagnostics r11 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r1.<init>(r2)     // Catch:{ all -> 0x003c }
            r1.append(r12)     // Catch:{ all -> 0x003c }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x003c }
            r11.logError(r12, r0)     // Catch:{ all -> 0x003c }
            r10.delete()     // Catch:{ all -> 0x003c }
        L_0x007d:
            r10.close()     // Catch:{ all -> 0x0049 }
            r7 = r5
            goto L_0x00b1
        L_0x0082:
            com.amplitude.api.AmplitudeLog r3 = logger     // Catch:{ all -> 0x003c }
            java.lang.String r4 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r7.<init>(r1)     // Catch:{ all -> 0x003c }
            r7.append(r11)     // Catch:{ all -> 0x003c }
            java.lang.String r11 = " failed"
            r7.append(r11)     // Catch:{ all -> 0x003c }
            java.lang.String r11 = r7.toString()     // Catch:{ all -> 0x003c }
            r3.e(r4, r11, r2)     // Catch:{ all -> 0x003c }
            com.amplitude.api.Diagnostics r11 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r1.<init>(r0)     // Catch:{ all -> 0x003c }
            r1.append(r12)     // Catch:{ all -> 0x003c }
            java.lang.String r12 = r1.toString()     // Catch:{ all -> 0x003c }
            r11.logError(r12, r2)     // Catch:{ all -> 0x003c }
            r10.delete()     // Catch:{ all -> 0x003c }
            goto L_0x007d
        L_0x00b1:
            monitor-exit(r10)
            return r7
        L_0x00b3:
            r10.close()     // Catch:{ all -> 0x0049 }
            throw r11     // Catch:{ all -> 0x0049 }
        L_0x00b7:
            monitor-exit(r10)     // Catch:{ all -> 0x0049 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.addEventToTable(java.lang.String, java.lang.String):long");
    }

    private static void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (Utils.isEmptyString(message) || !message.startsWith("Cursor window allocation of")) {
            throw runtimeException;
        }
        throw new CursorWindowAllocationException(message);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00d0, code lost:
        if (r3.isOpen() != false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00d2, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00ed, code lost:
        if (r3.isOpen() != false) goto L_0x00d2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void delete() {
        /*
            r9 = this;
            java.lang.String r0 = "DB: Failed to run databaseReset callback in delete"
            java.lang.String r1 = "databaseReset callback failed during delete"
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            r3 = 0
            r4 = 0
            r5 = 1
            r9.close()     // Catch:{ SecurityException -> 0x005d }
            java.io.File r6 = r9.file     // Catch:{ SecurityException -> 0x005d }
            r6.delete()     // Catch:{ SecurityException -> 0x005d }
            com.amplitude.api.DatabaseResetListener r6 = r9.databaseResetListener
            if (r6 == 0) goto L_0x00b4
            boolean r6 = r9.callResetListenerOnDatabaseReset
            if (r6 == 0) goto L_0x00b4
            r9.callResetListenerOnDatabaseReset = r4
            android.database.sqlite.SQLiteDatabase r3 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0035 }
            com.amplitude.api.DatabaseResetListener r4 = r9.databaseResetListener     // Catch:{ SQLiteException -> 0x0035 }
            r4.onDatabaseReset(r3)     // Catch:{ SQLiteException -> 0x0035 }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00b4
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00b4
        L_0x002e:
            r9.close()
            goto L_0x00b4
        L_0x0033:
            r0 = move-exception
            goto L_0x004d
        L_0x0035:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x0033 }
            r6.e(r2, r1, r4)     // Catch:{ all -> 0x0033 }
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0033 }
            r1.logError(r0, r4)     // Catch:{ all -> 0x0033 }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00b4
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00b4
            goto L_0x002e
        L_0x004d:
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x005a
            boolean r1 = r3.isOpen()
            if (r1 == 0) goto L_0x005a
            r9.close()
        L_0x005a:
            throw r0
        L_0x005b:
            r6 = move-exception
            goto L_0x00b5
        L_0x005d:
            r6 = move-exception
            com.amplitude.api.AmplitudeLog r7 = logger     // Catch:{ all -> 0x005b }
            java.lang.String r8 = "delete failed"
            r7.e(r2, r8, r6)     // Catch:{ all -> 0x005b }
            com.amplitude.api.Diagnostics r6 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x005b }
            java.lang.String r7 = "DB: Failed to delete database"
            r6.logError(r7)     // Catch:{ all -> 0x005b }
            com.amplitude.api.DatabaseResetListener r6 = r9.databaseResetListener
            if (r6 == 0) goto L_0x00b4
            boolean r6 = r9.callResetListenerOnDatabaseReset
            if (r6 == 0) goto L_0x00b4
            r9.callResetListenerOnDatabaseReset = r4
            android.database.sqlite.SQLiteDatabase r3 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x008e }
            com.amplitude.api.DatabaseResetListener r4 = r9.databaseResetListener     // Catch:{ SQLiteException -> 0x008e }
            r4.onDatabaseReset(r3)     // Catch:{ SQLiteException -> 0x008e }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00b4
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00b4
            goto L_0x002e
        L_0x008c:
            r0 = move-exception
            goto L_0x00a6
        L_0x008e:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x008c }
            r6.e(r2, r1, r4)     // Catch:{ all -> 0x008c }
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x008c }
            r1.logError(r0, r4)     // Catch:{ all -> 0x008c }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00b4
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00b4
            goto L_0x002e
        L_0x00a6:
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00b3
            boolean r1 = r3.isOpen()
            if (r1 == 0) goto L_0x00b3
            r9.close()
        L_0x00b3:
            throw r0
        L_0x00b4:
            return
        L_0x00b5:
            com.amplitude.api.DatabaseResetListener r7 = r9.databaseResetListener
            if (r7 == 0) goto L_0x00fe
            boolean r7 = r9.callResetListenerOnDatabaseReset
            if (r7 == 0) goto L_0x00fe
            r9.callResetListenerOnDatabaseReset = r4
            android.database.sqlite.SQLiteDatabase r3 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00d8 }
            com.amplitude.api.DatabaseResetListener r4 = r9.databaseResetListener     // Catch:{ SQLiteException -> 0x00d8 }
            r4.onDatabaseReset(r3)     // Catch:{ SQLiteException -> 0x00d8 }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00fe
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00fe
        L_0x00d2:
            r9.close()
            goto L_0x00fe
        L_0x00d6:
            r0 = move-exception
            goto L_0x00f0
        L_0x00d8:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r7 = logger     // Catch:{ all -> 0x00d6 }
            r7.e(r2, r1, r4)     // Catch:{ all -> 0x00d6 }
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x00d6 }
            r1.logError(r0, r4)     // Catch:{ all -> 0x00d6 }
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00fe
            boolean r0 = r3.isOpen()
            if (r0 == 0) goto L_0x00fe
            goto L_0x00d2
        L_0x00f0:
            r9.callResetListenerOnDatabaseReset = r5
            if (r3 == 0) goto L_0x00fd
            boolean r1 = r3.isOpen()
            if (r1 == 0) goto L_0x00fd
            r9.close()
        L_0x00fd:
            throw r0
        L_0x00fe:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.delete():void");
    }

    @Deprecated
    public static DatabaseHelper getDatabaseHelper(Context context) {
        return getDatabaseHelper(context, (String) null);
    }

    private static String getDatabaseName(String str) {
        return (Utils.isEmptyString(str) || str.equals(Constants.DEFAULT_INSTANCE)) ? "com.amplitude.api" : "com.amplitude.api_".concat(str);
    }

    private synchronized long getEventCountFromTable(String str) {
        long j2;
        SQLiteClosable sQLiteClosable = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            SQLiteStatement compileStatement = readableDatabase.compileStatement("SELECT COUNT(*) FROM " + str);
            j2 = compileStatement.simpleQueryForLong();
            compileStatement.close();
            close();
        } catch (SQLiteException e3) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "getNumberRows for " + str + " failed", e3);
            Diagnostics logger2 = Diagnostics.getLogger();
            logger2.logError("DB: Failed to getNumberRows for table " + str, e3);
            delete();
            if (sQLiteClosable != null) {
                sQLiteClosable.close();
            }
            close();
            j2 = 0;
            return j2;
        } catch (StackOverflowError e4) {
            try {
                AmplitudeLog amplitudeLog2 = logger;
                amplitudeLog2.e(TAG, "getNumberRows for " + str + " failed", e4);
                Diagnostics logger3 = Diagnostics.getLogger();
                logger3.logError("DB: Failed to getNumberRows for table " + str, e4);
                delete();
                if (sQLiteClosable != null) {
                    sQLiteClosable.close();
                }
                close();
                j2 = 0;
                return j2;
            } catch (Throwable th) {
                if (sQLiteClosable != null) {
                    sQLiteClosable.close();
                }
                close();
                throw th;
            }
        }
        return j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b8, code lost:
        if (r7 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bd, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c0, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c2, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:14:0x0043, B:19:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long getNthEventIdFromTable(java.lang.String r13, long r14) {
        /*
            r12 = this;
            java.lang.String r0 = "DB: Failed to getNthEventId from table "
            java.lang.String r1 = "getNthEventId from "
            java.lang.String r2 = "DB: Failed to getNthEventId from table "
            java.lang.String r3 = "getNthEventId from "
            java.lang.String r4 = "SELECT id FROM "
            monitor-enter(r12)
            r5 = -1
            r7 = 0
            android.database.sqlite.SQLiteDatabase r8 = r12.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            r9.<init>(r4)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            r9.append(r13)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            java.lang.String r4 = " LIMIT 1 OFFSET "
            r9.append(r4)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            r10 = 1
            long r14 = r14 - r10
            r9.append(r14)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            java.lang.String r14 = r9.toString()     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            android.database.sqlite.SQLiteStatement r7 = r8.compileStatement(r14)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            long r5 = r7.simpleQueryForLong()     // Catch:{ SQLiteDoneException -> 0x0039 }
            goto L_0x0041
        L_0x0032:
            r13 = move-exception
            goto L_0x00b8
        L_0x0035:
            r14 = move-exception
            goto L_0x004e
        L_0x0037:
            r14 = move-exception
            goto L_0x0082
        L_0x0039:
            r14 = move-exception
            com.amplitude.api.AmplitudeLog r15 = logger     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
            java.lang.String r4 = "com.amplitude.api.DatabaseHelper"
            r15.w((java.lang.String) r4, (java.lang.Throwable) r14)     // Catch:{ SQLiteException -> 0x0037, StackOverflowError -> 0x0035 }
        L_0x0041:
            if (r7 == 0) goto L_0x004a
            r7.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004a
        L_0x0047:
            r13 = move-exception
            goto L_0x00c1
        L_0x004a:
            r12.close()     // Catch:{ all -> 0x0047 }
            goto L_0x00b6
        L_0x004e:
            com.amplitude.api.AmplitudeLog r15 = logger     // Catch:{ all -> 0x0032 }
            java.lang.String r0 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r1.<init>(r3)     // Catch:{ all -> 0x0032 }
            r1.append(r13)     // Catch:{ all -> 0x0032 }
            java.lang.String r3 = " failed"
            r1.append(r3)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0032 }
            r15.e(r0, r1, r14)     // Catch:{ all -> 0x0032 }
            com.amplitude.api.Diagnostics r15 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r0.<init>(r2)     // Catch:{ all -> 0x0032 }
            r0.append(r13)     // Catch:{ all -> 0x0032 }
            java.lang.String r13 = r0.toString()     // Catch:{ all -> 0x0032 }
            r15.logError(r13, r14)     // Catch:{ all -> 0x0032 }
            r12.delete()     // Catch:{ all -> 0x0032 }
            if (r7 == 0) goto L_0x004a
            r7.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004a
        L_0x0082:
            com.amplitude.api.AmplitudeLog r15 = logger     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r3.<init>(r1)     // Catch:{ all -> 0x0032 }
            r3.append(r13)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = " failed"
            r3.append(r1)     // Catch:{ all -> 0x0032 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0032 }
            r15.e(r2, r1, r14)     // Catch:{ all -> 0x0032 }
            com.amplitude.api.Diagnostics r15 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r1.<init>(r0)     // Catch:{ all -> 0x0032 }
            r1.append(r13)     // Catch:{ all -> 0x0032 }
            java.lang.String r13 = r1.toString()     // Catch:{ all -> 0x0032 }
            r15.logError(r13, r14)     // Catch:{ all -> 0x0032 }
            r12.delete()     // Catch:{ all -> 0x0032 }
            if (r7 == 0) goto L_0x004a
            r7.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004a
        L_0x00b6:
            monitor-exit(r12)
            return r5
        L_0x00b8:
            if (r7 == 0) goto L_0x00bd
            r7.close()     // Catch:{ all -> 0x0047 }
        L_0x00bd:
            r12.close()     // Catch:{ all -> 0x0047 }
            throw r13     // Catch:{ all -> 0x0047 }
        L_0x00c1:
            monitor-exit(r12)     // Catch:{ all -> 0x0047 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getNthEventIdFromTable(java.lang.String, long):long");
    }

    private void handleIfCursorRowTooLargeException(IllegalStateException illegalStateException) {
        String message = illegalStateException.getMessage();
        if (Utils.isEmptyString(message) || !message.contains("Couldn't read") || !message.contains("CursorWindow")) {
            throw illegalStateException;
        }
        delete();
    }

    private synchronized void removeEventFromTable(String str, long j2) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id = " + j2, (String[]) null);
        } catch (SQLiteException e3) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "removeEvent from " + str + " failed", e3);
            Diagnostics logger2 = Diagnostics.getLogger();
            logger2.logError("DB: Failed to removeEvent from table " + str, e3);
            delete();
        } catch (StackOverflowError e4) {
            try {
                AmplitudeLog amplitudeLog2 = logger;
                amplitudeLog2.e(TAG, "removeEvent from " + str + " failed", e4);
                Diagnostics logger3 = Diagnostics.getLogger();
                logger3.logError("DB: Failed to removeEvent from table " + str, e4);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    private synchronized void removeEventsFromTable(String str, long j2) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            writableDatabase.delete(str, "id <= " + j2, (String[]) null);
        } catch (SQLiteException e3) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "removeEvents from " + str + " failed", e3);
            Diagnostics logger2 = Diagnostics.getLogger();
            logger2.logError("DB: Failed to removeEvents from table " + str, e3);
            delete();
        } catch (StackOverflowError e4) {
            try {
                AmplitudeLog amplitudeLog2 = logger;
                amplitudeLog2.e(TAG, "removeEvents from " + str + " failed", e4);
                Diagnostics logger3 = Diagnostics.getLogger();
                logger3.logError("DB: Failed to removeEvents from table " + str, e4);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    private void resetDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
        onCreate(sQLiteDatabase);
    }

    public synchronized long addEvent(String str) {
        return addEventToTable(EVENT_TABLE_NAME, str);
    }

    public synchronized long addIdentify(String str) {
        return addEventToTable(IDENTIFY_TABLE_NAME, str);
    }

    public boolean dbFileExists() {
        return this.file.exists();
    }

    public synchronized long deleteKeyFromTable(String str, String str2) {
        long j2;
        try {
            j2 = (long) getWritableDatabase().delete(str, "key=?", new String[]{str2});
            close();
        } catch (SQLiteException e3) {
            AmplitudeLog amplitudeLog = logger;
            amplitudeLog.e(TAG, "deleteKey from " + str + " failed", e3);
            Diagnostics logger2 = Diagnostics.getLogger();
            logger2.logError("DB: Failed to deleteKey: " + str2, e3);
            delete();
            close();
            j2 = -1;
            return j2;
        } catch (StackOverflowError e4) {
            try {
                AmplitudeLog amplitudeLog2 = logger;
                amplitudeLog2.e(TAG, "deleteKey from " + str + " failed", e4);
                Diagnostics logger3 = Diagnostics.getLogger();
                logger3.logError("DB: Failed to deleteKey: " + str2, e4);
                delete();
                close();
                j2 = -1;
                return j2;
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        return j2;
    }

    public synchronized long getEventCount() {
        return getEventCountFromTable(EVENT_TABLE_NAME);
    }

    public synchronized List<JSONObject> getEvents(long j2, long j3) throws JSONException {
        return getEventsFromTable(EVENT_TABLE_NAME, j2, j3);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<org.json.JSONObject> getEventsFromTable(java.lang.String r17, long r18, long r20) throws org.json.JSONException {
        /*
            r16 = this;
            r11 = r16
            r12 = r17
            r0 = r18
            r2 = r20
            java.lang.String r4 = ""
            java.lang.String r5 = "id <= "
            monitor-enter(r16)
            java.util.LinkedList r13 = new java.util.LinkedList     // Catch:{ all -> 0x0096 }
            r13.<init>()     // Catch:{ all -> 0x0096 }
            r14 = 0
            android.database.sqlite.SQLiteDatabase r6 = r16.getReadableDatabase()     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            java.lang.String r7 = "id"
            java.lang.String r8 = "event"
            java.lang.String[] r7 = new java.lang.String[]{r7, r8}     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r8 = 0
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0042
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r10.<init>(r5)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r10.append(r0)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            java.lang.String r0 = r10.toString()     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r5 = r0
            goto L_0x0043
        L_0x0033:
            r0 = move-exception
            goto L_0x015b
        L_0x0036:
            r0 = move-exception
            goto L_0x0099
        L_0x0039:
            r0 = move-exception
            goto L_0x00ba
        L_0x003c:
            r0 = move-exception
            goto L_0x00db
        L_0x003f:
            r0 = move-exception
            goto L_0x011a
        L_0x0042:
            r5 = r14
        L_0x0043:
            java.lang.String r0 = "id ASC"
            int r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x0057
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r1.<init>(r4)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r1.append(r2)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r10 = r1
            goto L_0x0058
        L_0x0057:
            r10 = r14
        L_0x0058:
            r8 = 0
            r9 = 0
            r15 = 0
            r1 = r16
            r2 = r6
            r3 = r17
            r4 = r7
            r6 = r8
            r7 = r9
            r8 = r15
            r9 = r0
            android.database.Cursor r14 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
        L_0x0069:
            boolean r0 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            if (r0 == 0) goto L_0x008e
            r0 = 0
            long r0 = r14.getLong(r0)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r2 = 1
            java.lang.String r2 = r14.getString(r2)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            boolean r3 = com.amplitude.api.Utils.isEmptyString(r2)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            if (r3 == 0) goto L_0x0080
            goto L_0x0069
        L_0x0080:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r3.<init>(r2)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            java.lang.String r2 = "event_id"
            r3.put(r2, r0)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            r13.add(r3)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003c, IllegalStateException -> 0x0039, RuntimeException -> 0x0036 }
            goto L_0x0069
        L_0x008e:
            r14.close()     // Catch:{ all -> 0x0096 }
        L_0x0091:
            r16.close()     // Catch:{ all -> 0x0096 }
            goto L_0x0159
        L_0x0096:
            r0 = move-exception
            goto L_0x0164
        L_0x0099:
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = "DB: Failed to getEventsFromTable "
            r2.append(r3)     // Catch:{ all -> 0x0033 }
            r2.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0033 }
            r1.logError(r2, r0)     // Catch:{ all -> 0x0033 }
            convertIfCursorWindowException(r0)     // Catch:{ all -> 0x0033 }
            if (r14 == 0) goto L_0x0091
            r14.close()     // Catch:{ all -> 0x0096 }
            goto L_0x0091
        L_0x00ba:
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = "DB: Failed to getEventsFromTable "
            r2.append(r3)     // Catch:{ all -> 0x0033 }
            r2.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0033 }
            r1.logError(r2, r0)     // Catch:{ all -> 0x0033 }
            r11.handleIfCursorRowTooLargeException(r0)     // Catch:{ all -> 0x0033 }
            if (r14 == 0) goto L_0x0091
            r14.close()     // Catch:{ all -> 0x0096 }
            goto L_0x0091
        L_0x00db:
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r3.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r4 = "getEvents from "
            r3.append(r4)     // Catch:{ all -> 0x0033 }
            r3.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r4 = " failed"
            r3.append(r4)     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0033 }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x0033 }
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = "DB: Failed to getEventsFromTable "
            r2.append(r3)     // Catch:{ all -> 0x0033 }
            r2.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0033 }
            r1.logError(r2, r0)     // Catch:{ all -> 0x0033 }
            r16.delete()     // Catch:{ all -> 0x0033 }
            if (r14 == 0) goto L_0x0091
            r14.close()     // Catch:{ all -> 0x0096 }
            goto L_0x0091
        L_0x011a:
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r3.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r4 = "getEvents from "
            r3.append(r4)     // Catch:{ all -> 0x0033 }
            r3.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r4 = " failed"
            r3.append(r4)     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0033 }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x0033 }
            com.amplitude.api.Diagnostics r1 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r2.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = "DB: Failed to getEventsFromTable "
            r2.append(r3)     // Catch:{ all -> 0x0033 }
            r2.append(r12)     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0033 }
            r1.logError(r2, r0)     // Catch:{ all -> 0x0033 }
            r16.delete()     // Catch:{ all -> 0x0033 }
            if (r14 == 0) goto L_0x0091
            r14.close()     // Catch:{ all -> 0x0096 }
            goto L_0x0091
        L_0x0159:
            monitor-exit(r16)
            return r13
        L_0x015b:
            if (r14 == 0) goto L_0x0160
            r14.close()     // Catch:{ all -> 0x0096 }
        L_0x0160:
            r16.close()     // Catch:{ all -> 0x0096 }
            throw r0     // Catch:{ all -> 0x0096 }
        L_0x0164:
            monitor-exit(r16)     // Catch:{ all -> 0x0096 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getEventsFromTable(java.lang.String, long, long):java.util.List");
    }

    public synchronized long getIdentifyCount() {
        return getEventCountFromTable(IDENTIFY_TABLE_NAME);
    }

    public synchronized List<JSONObject> getIdentifys(long j2, long j3) throws JSONException {
        return getEventsFromTable(IDENTIFY_TABLE_NAME, j2, j3);
    }

    public synchronized Long getLongValue(String str) {
        return (Long) getValueFromTable(LONG_STORE_TABLE_NAME, str);
    }

    public synchronized long getNthEventId(long j2) {
        return getNthEventIdFromTable(EVENT_TABLE_NAME, j2);
    }

    public synchronized long getNthIdentifyId(long j2) {
        return getNthEventIdFromTable(IDENTIFY_TABLE_NAME, j2);
    }

    public synchronized long getTotalEventCount() {
        return getEventCount() + getIdentifyCount();
    }

    public synchronized String getValue(String str) {
        return (String) getValueFromTable(STORE_TABLE_NAME, str);
    }

    /* JADX WARNING: type inference failed for: r0v29, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r0v31, types: [java.lang.String] */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0081, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0084, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0081 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0015] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c0 A[SYNTHETIC, Splitter:B:50:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00de A[SYNTHETIC, Splitter:B:55:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0114 A[SYNTHETIC, Splitter:B:60:0x0114] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014b A[SYNTHETIC, Splitter:B:65:0x014b] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0154 A[SYNTHETIC, Splitter:B:70:0x0154] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:57:0x00e2=Splitter:B:57:0x00e2, B:52:0x00c4=Splitter:B:52:0x00c4, B:47:0x00a6=Splitter:B:47:0x00a6, B:62:0x0119=Splitter:B:62:0x0119} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object getValueFromTable(java.lang.String r24, java.lang.String r25) {
        /*
            r23 = this;
            r11 = r23
            r12 = r24
            r13 = r25
            java.lang.String r14 = "DB: Failed to getValue: "
            java.lang.String r15 = "getValue from "
            java.lang.String r10 = "DB: Failed to getValue: "
            java.lang.String r9 = "getValue from "
            java.lang.String r8 = "DB: Failed to getValue: "
            java.lang.String r7 = "DB: Failed to getValue: "
            monitor-enter(r23)
            r16 = 0
            android.database.sqlite.SQLiteDatabase r2 = r23.getReadableDatabase()     // Catch:{ SQLiteException -> 0x00a2, StackOverflowError -> 0x009d, IllegalStateException -> 0x0099, RuntimeException -> 0x0095, all -> 0x0081 }
            java.lang.String r0 = "key"
            java.lang.String r1 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}     // Catch:{ SQLiteException -> 0x00a2, StackOverflowError -> 0x009d, IllegalStateException -> 0x0099, RuntimeException -> 0x0095, all -> 0x0081 }
            java.lang.String r5 = "key = ?"
            java.lang.String[] r6 = new java.lang.String[]{r25}     // Catch:{ SQLiteException -> 0x00a2, StackOverflowError -> 0x009d, IllegalStateException -> 0x0099, RuntimeException -> 0x0095, all -> 0x0081 }
            r0 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r1 = r23
            r3 = r24
            r20 = r7
            r7 = r18
            r21 = r8
            r8 = r19
            r22 = r9
            r9 = r0
            r18 = r14
            r14 = r10
            r10 = r17
            android.database.Cursor r1 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0090, StackOverflowError -> 0x008c, IllegalStateException -> 0x0088, RuntimeException -> 0x0084, all -> 0x0081 }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006a, StackOverflowError -> 0x0067, IllegalStateException -> 0x0064, RuntimeException -> 0x0062 }
            if (r0 == 0) goto L_0x0076
            java.lang.String r0 = "store"
            boolean r0 = r12.equals(r0)     // Catch:{ SQLiteException -> 0x006a, StackOverflowError -> 0x0067, IllegalStateException -> 0x0064, RuntimeException -> 0x0062 }
            r2 = 1
            if (r0 == 0) goto L_0x006d
            java.lang.String r0 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x006a, StackOverflowError -> 0x0067, IllegalStateException -> 0x0064, RuntimeException -> 0x0062 }
        L_0x005a:
            r16 = r0
            goto L_0x0076
        L_0x005d:
            r0 = move-exception
            r16 = r1
            goto L_0x0152
        L_0x0062:
            r0 = move-exception
            goto L_0x00a6
        L_0x0064:
            r0 = move-exception
            goto L_0x00c4
        L_0x0067:
            r0 = move-exception
            goto L_0x00e2
        L_0x006a:
            r0 = move-exception
            goto L_0x0119
        L_0x006d:
            long r2 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x006a, StackOverflowError -> 0x0067, IllegalStateException -> 0x0064, RuntimeException -> 0x0062 }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x006a, StackOverflowError -> 0x0067, IllegalStateException -> 0x0064, RuntimeException -> 0x0062 }
            goto L_0x005a
        L_0x0076:
            r1.close()     // Catch:{ all -> 0x007e }
        L_0x0079:
            r23.close()     // Catch:{ all -> 0x007e }
            goto L_0x0150
        L_0x007e:
            r0 = move-exception
            goto L_0x015b
        L_0x0081:
            r0 = move-exception
            goto L_0x0152
        L_0x0084:
            r0 = move-exception
        L_0x0085:
            r1 = r16
            goto L_0x00a6
        L_0x0088:
            r0 = move-exception
        L_0x0089:
            r1 = r16
            goto L_0x00c4
        L_0x008c:
            r0 = move-exception
        L_0x008d:
            r1 = r16
            goto L_0x00e2
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            r1 = r16
            goto L_0x0119
        L_0x0095:
            r0 = move-exception
            r20 = r7
            goto L_0x0085
        L_0x0099:
            r0 = move-exception
            r21 = r8
            goto L_0x0089
        L_0x009d:
            r0 = move-exception
            r22 = r9
            r14 = r10
            goto L_0x008d
        L_0x00a2:
            r0 = move-exception
            r18 = r14
            goto L_0x0091
        L_0x00a6:
            com.amplitude.api.Diagnostics r2 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r4 = r20
            r3.<init>(r4)     // Catch:{ all -> 0x005d }
            r3.append(r13)     // Catch:{ all -> 0x005d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005d }
            r2.logError(r3, r0)     // Catch:{ all -> 0x005d }
            convertIfCursorWindowException(r0)     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007e }
            goto L_0x0079
        L_0x00c4:
            com.amplitude.api.Diagnostics r2 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r4 = r21
            r3.<init>(r4)     // Catch:{ all -> 0x005d }
            r3.append(r13)     // Catch:{ all -> 0x005d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005d }
            r2.logError(r3, r0)     // Catch:{ all -> 0x005d }
            r11.handleIfCursorRowTooLargeException(r0)     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007e }
            goto L_0x0079
        L_0x00e2:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x005d }
            java.lang.String r3 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r5 = r22
            r4.<init>(r5)     // Catch:{ all -> 0x005d }
            r4.append(r12)     // Catch:{ all -> 0x005d }
            java.lang.String r5 = " failed"
            r4.append(r5)     // Catch:{ all -> 0x005d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x005d }
            r2.e(r3, r4, r0)     // Catch:{ all -> 0x005d }
            com.amplitude.api.Diagnostics r2 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r3.<init>(r14)     // Catch:{ all -> 0x005d }
            r3.append(r13)     // Catch:{ all -> 0x005d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005d }
            r2.logError(r3, r0)     // Catch:{ all -> 0x005d }
            r23.delete()     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007e }
            goto L_0x0079
        L_0x0119:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x005d }
            java.lang.String r3 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r4.<init>(r15)     // Catch:{ all -> 0x005d }
            r4.append(r12)     // Catch:{ all -> 0x005d }
            java.lang.String r5 = " failed"
            r4.append(r5)     // Catch:{ all -> 0x005d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x005d }
            r2.e(r3, r4, r0)     // Catch:{ all -> 0x005d }
            com.amplitude.api.Diagnostics r2 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r4 = r18
            r3.<init>(r4)     // Catch:{ all -> 0x005d }
            r3.append(r13)     // Catch:{ all -> 0x005d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005d }
            r2.logError(r3, r0)     // Catch:{ all -> 0x005d }
            r23.delete()     // Catch:{ all -> 0x005d }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007e }
            goto L_0x0079
        L_0x0150:
            monitor-exit(r23)
            return r16
        L_0x0152:
            if (r16 == 0) goto L_0x0157
            r16.close()     // Catch:{ all -> 0x007e }
        L_0x0157:
            r23.close()     // Catch:{ all -> 0x007e }
            throw r0     // Catch:{ all -> 0x007e }
        L_0x015b:
            monitor-exit(r23)     // Catch:{ all -> 0x007e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }

    public synchronized long insertEventContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insert(str, (String) null, contentValues);
    }

    public synchronized long insertKeyValueContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
    }

    public synchronized long insertOrReplaceKeyLongValue(String str, Long l2) {
        long j2;
        if (l2 == null) {
            try {
                j2 = deleteKeyFromTable(LONG_STORE_TABLE_NAME, str);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            j2 = insertOrReplaceKeyValueToTable(LONG_STORE_TABLE_NAME, str, l2);
        }
        return j2;
    }

    public synchronized long insertOrReplaceKeyValue(String str, String str2) {
        long j2;
        if (str2 == null) {
            try {
                j2 = deleteKeyFromTable(STORE_TABLE_NAME, str);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            j2 = insertOrReplaceKeyValueToTable(STORE_TABLE_NAME, str, str2);
        }
        return j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        if (r4.isOpen() != false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0097, code lost:
        if (r4.isOpen() != false) goto L_0x005f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long insertOrReplaceKeyValueToTable(java.lang.String r7, java.lang.String r8, java.lang.Object r9) {
        /*
            r6 = this;
            java.lang.String r0 = "DB: Failed to insertOrReplaceKeyValue "
            java.lang.String r1 = "insertOrReplaceKeyValue in "
            java.lang.String r2 = "DB: Failed to insertOrReplaceKeyValue "
            java.lang.String r3 = "insertOrReplaceKeyValue in "
            monitor-enter(r6)
            r4 = 0
            android.database.sqlite.SQLiteDatabase r4 = r6.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0027, StackOverflowError -> 0x0025 }
            long r7 = r6.insertOrReplaceKeyValueToTable(r4, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0027, StackOverflowError -> 0x0025 }
            if (r4 == 0) goto L_0x009c
            boolean r9 = r4.isOpen()     // Catch:{ all -> 0x001f }
            if (r9 == 0) goto L_0x009c
            r6.close()     // Catch:{ all -> 0x001f }
            goto L_0x009c
        L_0x001f:
            r7 = move-exception
            goto L_0x00aa
        L_0x0022:
            r7 = move-exception
            goto L_0x009e
        L_0x0025:
            r9 = move-exception
            goto L_0x0029
        L_0x0027:
            r9 = move-exception
            goto L_0x0063
        L_0x0029:
            com.amplitude.api.AmplitudeLog r0 = logger     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r5.<init>(r3)     // Catch:{ all -> 0x0022 }
            r5.append(r7)     // Catch:{ all -> 0x0022 }
            java.lang.String r7 = " failed"
            r5.append(r7)     // Catch:{ all -> 0x0022 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0022 }
            r0.e(r1, r7, r9)     // Catch:{ all -> 0x0022 }
            com.amplitude.api.Diagnostics r7 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0022 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r0.<init>(r2)     // Catch:{ all -> 0x0022 }
            r0.append(r8)     // Catch:{ all -> 0x0022 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0022 }
            r7.logError(r8, r9)     // Catch:{ all -> 0x0022 }
            r6.delete()     // Catch:{ all -> 0x0022 }
            if (r4 == 0) goto L_0x009a
            boolean r7 = r4.isOpen()     // Catch:{ all -> 0x001f }
            if (r7 == 0) goto L_0x009a
        L_0x005f:
            r6.close()     // Catch:{ all -> 0x001f }
            goto L_0x009a
        L_0x0063:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r5.<init>(r1)     // Catch:{ all -> 0x0022 }
            r5.append(r7)     // Catch:{ all -> 0x0022 }
            java.lang.String r7 = " failed"
            r5.append(r7)     // Catch:{ all -> 0x0022 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x0022 }
            r2.e(r3, r7, r9)     // Catch:{ all -> 0x0022 }
            com.amplitude.api.Diagnostics r7 = com.amplitude.api.Diagnostics.getLogger()     // Catch:{ all -> 0x0022 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r1.<init>(r0)     // Catch:{ all -> 0x0022 }
            r1.append(r8)     // Catch:{ all -> 0x0022 }
            java.lang.String r8 = r1.toString()     // Catch:{ all -> 0x0022 }
            r7.logError(r8, r9)     // Catch:{ all -> 0x0022 }
            r6.delete()     // Catch:{ all -> 0x0022 }
            if (r4 == 0) goto L_0x009a
            boolean r7 = r4.isOpen()     // Catch:{ all -> 0x001f }
            if (r7 == 0) goto L_0x009a
            goto L_0x005f
        L_0x009a:
            r7 = -1
        L_0x009c:
            monitor-exit(r6)
            return r7
        L_0x009e:
            if (r4 == 0) goto L_0x00a9
            boolean r8 = r4.isOpen()     // Catch:{ all -> 0x001f }
            if (r8 == 0) goto L_0x00a9
            r6.close()     // Catch:{ all -> 0x001f }
        L_0x00a9:
            throw r7     // Catch:{ all -> 0x001f }
        L_0x00aa:
            monitor-exit(r6)     // Catch:{ all -> 0x001f }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.insertOrReplaceKeyValueToTable(java.lang.String, java.lang.String, java.lang.Object):long");
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
        DatabaseResetListener databaseResetListener2 = this.databaseResetListener;
        if (databaseResetListener2 != null && this.callResetListenerOnDatabaseReset) {
            try {
                this.callResetListenerOnDatabaseReset = false;
                databaseResetListener2.onDatabaseReset(sQLiteDatabase);
            } catch (SQLiteException e3) {
                logger.e(TAG, "databaseReset callback failed during onCreate", e3);
                Diagnostics.getLogger().logError("DB: Failed to run databaseReset callback during onCreate", e3);
            } catch (Throwable th) {
                this.callResetListenerOnDatabaseReset = true;
                throw th;
            }
            this.callResetListenerOnDatabaseReset = true;
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i3, int i4) {
        if (i3 > i4) {
            logger.e(TAG, "onUpgrade() with invalid oldVersion and newVersion");
            resetDatabase(sQLiteDatabase);
        } else if (i4 > 1) {
            if (i3 == 1) {
                sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
                if (i4 <= 2) {
                    return;
                }
            } else if (i3 != 2) {
                if (i3 != 3) {
                    AmplitudeLog amplitudeLog = logger;
                    amplitudeLog.e(TAG, "onUpgrade() with unknown oldVersion " + i3);
                    resetDatabase(sQLiteDatabase);
                    return;
                }
                return;
            }
            sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
            sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        }
    }

    public Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    public synchronized void removeEvent(long j2) {
        removeEventFromTable(EVENT_TABLE_NAME, j2);
    }

    public synchronized void removeEvents(long j2) {
        removeEventsFromTable(EVENT_TABLE_NAME, j2);
    }

    public synchronized void removeIdentify(long j2) {
        removeEventFromTable(IDENTIFY_TABLE_NAME, j2);
    }

    public synchronized void removeIdentifys(long j2) {
        removeEventsFromTable(IDENTIFY_TABLE_NAME, j2);
    }

    public void setDatabaseResetListener(DatabaseResetListener databaseResetListener2) {
        this.databaseResetListener = databaseResetListener2;
    }

    public DatabaseHelper(Context context, String str) {
        super(context, getDatabaseName(str), (SQLiteDatabase.CursorFactory) null, 3);
        this.callResetListenerOnDatabaseReset = true;
        this.file = context.getDatabasePath(getDatabaseName(str));
        this.instanceName = Utils.normalizeInstanceName(str);
    }

    public static synchronized DatabaseHelper getDatabaseHelper(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            String normalizeInstanceName = Utils.normalizeInstanceName(str);
            Map<String, DatabaseHelper> map = instances;
            databaseHelper = map.get(normalizeInstanceName);
            if (databaseHelper == null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext(), normalizeInstanceName);
                map.put(normalizeInstanceName, databaseHelper);
            }
        }
        return databaseHelper;
    }

    public synchronized long insertOrReplaceKeyValueToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, Object obj) throws SQLiteException, StackOverflowError {
        long insertKeyValueContentValuesIntoTable;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", str2);
            if (obj instanceof Long) {
                contentValues.put("value", (Long) obj);
            } else {
                contentValues.put("value", (String) obj);
            }
            insertKeyValueContentValuesIntoTable = insertKeyValueContentValuesIntoTable(sQLiteDatabase, str, contentValues);
            if (insertKeyValueContentValuesIntoTable == -1) {
                logger.w(TAG, "Insert failed");
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return insertKeyValueContentValuesIntoTable;
    }
}
