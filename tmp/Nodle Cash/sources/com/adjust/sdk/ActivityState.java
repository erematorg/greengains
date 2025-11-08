package com.adjust.sdk;

import com.amplitude.api.Constants;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;

public class ActivityState implements Serializable, Cloneable {
    private static final int ORDER_ID_MAXCOUNT = 10;
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 9039439291143138148L;
    protected String adid = null;
    protected boolean askingAttribution = false;
    protected long clickTime = 0;
    protected long clickTimeHuawei = 0;
    protected long clickTimeMeta = 0;
    protected long clickTimeSamsung = 0;
    protected long clickTimeServer = 0;
    protected long clickTimeServerXiaomi = 0;
    protected long clickTimeVivo = 0;
    protected long clickTimeXiaomi = 0;
    protected boolean enabled = true;
    protected int eventCount = 0;
    protected Boolean googlePlayInstant = null;
    protected long installBegin = 0;
    protected long installBeginHuawei = 0;
    protected long installBeginSamsung = 0;
    protected long installBeginServer = 0;
    protected long installBeginServerXiaomi = 0;
    protected long installBeginVivo = 0;
    protected long installBeginXiaomi = 0;
    protected String installReferrer = null;
    protected String installReferrerHuawei = null;
    protected String installReferrerHuaweiAppGallery = null;
    protected String installReferrerMeta = null;
    protected String installReferrerSamsung = null;
    protected String installReferrerVivo = null;
    protected String installReferrerXiaomi = null;
    protected String installVersion = null;
    protected String installVersionVivo = null;
    protected String installVersionXiaomi = null;
    protected Boolean isClickMeta = null;
    protected boolean isGdprForgotten = false;
    protected boolean isThirdPartySharingDisabled = false;
    protected boolean isThirdPartySharingDisabledForCoppa = false;
    protected long lastActivity = -1;
    protected long lastInterval = -1;
    private transient ILogger logger = AdjustFactory.getLogger();
    protected LinkedList<String> orderIds = null;
    protected String pushToken = null;
    protected int sessionCount = 0;
    protected long sessionLength = -1;
    protected int subsessionCount = -1;
    protected long timeSpent = -1;
    protected boolean updatePackages = false;
    protected String uuid = Util.createUuid();

    static {
        ObjectStreamField objectStreamField = r1;
        Class<String> cls = String.class;
        ObjectStreamField objectStreamField2 = new ObjectStreamField("uuid", cls);
        ObjectStreamField objectStreamField3 = r2;
        Class cls2 = Boolean.TYPE;
        ObjectStreamField objectStreamField4 = new ObjectStreamField("enabled", cls2);
        ObjectStreamField objectStreamField5 = r3;
        ObjectStreamField objectStreamField6 = new ObjectStreamField("isGdprForgotten", cls2);
        ObjectStreamField objectStreamField7 = r4;
        ObjectStreamField objectStreamField8 = new ObjectStreamField("isThirdPartySharingDisabled", cls2);
        ObjectStreamField objectStreamField9 = r5;
        ObjectStreamField objectStreamField10 = new ObjectStreamField("askingAttribution", cls2);
        ObjectStreamField objectStreamField11 = r6;
        Class cls3 = Integer.TYPE;
        ObjectStreamField objectStreamField12 = new ObjectStreamField("eventCount", cls3);
        ObjectStreamField objectStreamField13 = r7;
        ObjectStreamField objectStreamField14 = new ObjectStreamField("sessionCount", cls3);
        ObjectStreamField objectStreamField15 = r9;
        ObjectStreamField objectStreamField16 = new ObjectStreamField("subsessionCount", cls3);
        ObjectStreamField objectStreamField17 = r9;
        Class cls4 = Long.TYPE;
        ObjectStreamField objectStreamField18 = new ObjectStreamField("sessionLength", cls4);
        ObjectStreamField objectStreamField19 = r10;
        ObjectStreamField objectStreamField20 = new ObjectStreamField("timeSpent", cls4);
        ObjectStreamField objectStreamField21 = r11;
        ObjectStreamField objectStreamField22 = new ObjectStreamField("lastActivity", cls4);
        ObjectStreamField objectStreamField23 = r12;
        ObjectStreamField objectStreamField24 = objectStreamField;
        ObjectStreamField objectStreamField25 = new ObjectStreamField("lastInterval", cls4);
        ObjectStreamField objectStreamField26 = r0;
        ObjectStreamField objectStreamField27 = new ObjectStreamField("updatePackages", cls2);
        ObjectStreamField objectStreamField28 = objectStreamField3;
        Class cls5 = cls4;
        ObjectStreamField objectStreamField29 = r0;
        ObjectStreamField objectStreamField30 = objectStreamField5;
        ObjectStreamField objectStreamField31 = new ObjectStreamField("orderIds", LinkedList.class);
        Class cls6 = cls2;
        ObjectStreamField objectStreamField32 = r0;
        ObjectStreamField objectStreamField33 = objectStreamField7;
        ObjectStreamField objectStreamField34 = new ObjectStreamField("pushToken", cls);
        Class<String> cls7 = cls;
        ObjectStreamField objectStreamField35 = r0;
        ObjectStreamField objectStreamField36 = new ObjectStreamField(Constants.AMP_TRACKING_OPTION_ADID, cls7);
        ObjectStreamField objectStreamField37 = r0;
        ObjectStreamField objectStreamField38 = new ObjectStreamField("clickTime", cls5);
        ObjectStreamField objectStreamField39 = r0;
        ObjectStreamField objectStreamField40 = new ObjectStreamField("installBegin", cls5);
        ObjectStreamField objectStreamField41 = r0;
        ObjectStreamField objectStreamField42 = new ObjectStreamField("installReferrer", cls7);
        ObjectStreamField objectStreamField43 = r0;
        ObjectStreamField objectStreamField44 = objectStreamField11;
        Class<Boolean> cls8 = Boolean.class;
        ObjectStreamField objectStreamField45 = new ObjectStreamField("googlePlayInstant", cls8);
        ObjectStreamField objectStreamField46 = r0;
        ObjectStreamField objectStreamField47 = new ObjectStreamField("clickTimeServer", cls5);
        ObjectStreamField objectStreamField48 = r0;
        ObjectStreamField objectStreamField49 = new ObjectStreamField("installBeginServer", cls5);
        ObjectStreamField objectStreamField50 = r0;
        ObjectStreamField objectStreamField51 = new ObjectStreamField("installVersion", cls7);
        ObjectStreamField objectStreamField52 = r0;
        ObjectStreamField objectStreamField53 = new ObjectStreamField("clickTimeHuawei", cls5);
        ObjectStreamField objectStreamField54 = r0;
        ObjectStreamField objectStreamField55 = new ObjectStreamField("installBeginHuawei", cls5);
        ObjectStreamField objectStreamField56 = r0;
        ObjectStreamField objectStreamField57 = new ObjectStreamField("installReferrerHuawei", cls7);
        ObjectStreamField objectStreamField58 = r0;
        ObjectStreamField objectStreamField59 = new ObjectStreamField("installReferrerHuaweiAppGallery", cls7);
        ObjectStreamField objectStreamField60 = r0;
        ObjectStreamField objectStreamField61 = new ObjectStreamField("isThirdPartySharingDisabledForCoppa", cls6);
        ObjectStreamField objectStreamField62 = r0;
        ObjectStreamField objectStreamField63 = new ObjectStreamField("clickTimeXiaomi", cls5);
        ObjectStreamField objectStreamField64 = r0;
        ObjectStreamField objectStreamField65 = new ObjectStreamField("installBeginXiaomi", cls5);
        ObjectStreamField objectStreamField66 = r0;
        ObjectStreamField objectStreamField67 = new ObjectStreamField("installReferrerXiaomi", cls7);
        ObjectStreamField objectStreamField68 = r0;
        ObjectStreamField objectStreamField69 = new ObjectStreamField("clickTimeServerXiaomi", cls5);
        ObjectStreamField objectStreamField70 = r0;
        ObjectStreamField objectStreamField71 = new ObjectStreamField("installBeginServerXiaomi", cls5);
        ObjectStreamField objectStreamField72 = r0;
        ObjectStreamField objectStreamField73 = new ObjectStreamField("installVersionXiaomi", cls7);
        ObjectStreamField objectStreamField74 = r0;
        ObjectStreamField objectStreamField75 = new ObjectStreamField("clickTimeSamsung", cls5);
        ObjectStreamField objectStreamField76 = r0;
        ObjectStreamField objectStreamField77 = new ObjectStreamField("installBeginSamsung", cls5);
        ObjectStreamField objectStreamField78 = r0;
        ObjectStreamField objectStreamField79 = new ObjectStreamField("installReferrerSamsung", cls7);
        ObjectStreamField objectStreamField80 = r0;
        ObjectStreamField objectStreamField81 = new ObjectStreamField("clickTimeVivo", cls5);
        ObjectStreamField objectStreamField82 = r0;
        ObjectStreamField objectStreamField83 = new ObjectStreamField("installBeginVivo", cls5);
        ObjectStreamField objectStreamField84 = r0;
        ObjectStreamField objectStreamField85 = new ObjectStreamField("installReferrerVivo", cls7);
        ObjectStreamField objectStreamField86 = r0;
        ObjectStreamField objectStreamField87 = new ObjectStreamField("installVersionVivo", cls7);
        ObjectStreamField objectStreamField88 = r0;
        ObjectStreamField objectStreamField89 = new ObjectStreamField("installReferrerMeta", cls7);
        ObjectStreamField objectStreamField90 = r0;
        ObjectStreamField objectStreamField91 = new ObjectStreamField("clickTimeMeta", cls5);
        ObjectStreamField objectStreamField92 = r0;
        ObjectStreamField objectStreamField93 = new ObjectStreamField("isClickMeta", cls8);
        serialPersistentFields = new ObjectStreamField[]{objectStreamField24, objectStreamField28, objectStreamField30, objectStreamField33, objectStreamField9, objectStreamField44, objectStreamField13, objectStreamField15, objectStreamField17, objectStreamField19, objectStreamField21, objectStreamField23, objectStreamField26, objectStreamField29, objectStreamField32, objectStreamField35, objectStreamField37, objectStreamField39, objectStreamField41, objectStreamField43, objectStreamField46, objectStreamField48, objectStreamField50, objectStreamField52, objectStreamField54, objectStreamField56, objectStreamField58, objectStreamField60, objectStreamField62, objectStreamField64, objectStreamField66, objectStreamField68, objectStreamField70, objectStreamField72, objectStreamField74, objectStreamField76, objectStreamField78, objectStreamField80, objectStreamField82, objectStreamField84, objectStreamField86, objectStreamField88, objectStreamField90, objectStreamField92};
    }

    private void readObject(ObjectInputStream objectInputStream) {
        ObjectInputStream.GetField readFields = objectInputStream.readFields();
        this.eventCount = Util.readIntField(readFields, "eventCount", 0);
        this.sessionCount = Util.readIntField(readFields, "sessionCount", 0);
        this.subsessionCount = Util.readIntField(readFields, "subsessionCount", -1);
        this.sessionLength = Util.readLongField(readFields, "sessionLength", -1);
        this.timeSpent = Util.readLongField(readFields, "timeSpent", -1);
        this.lastActivity = Util.readLongField(readFields, "lastActivity", -1);
        this.lastInterval = Util.readLongField(readFields, "lastInterval", -1);
        this.uuid = Util.readStringField(readFields, "uuid", (String) null);
        this.enabled = Util.readBooleanField(readFields, "enabled", true);
        this.isGdprForgotten = Util.readBooleanField(readFields, "isGdprForgotten", false);
        this.isThirdPartySharingDisabled = Util.readBooleanField(readFields, "isThirdPartySharingDisabled", false);
        this.isThirdPartySharingDisabledForCoppa = Util.readBooleanField(readFields, "isThirdPartySharingDisabledForCoppa", false);
        this.askingAttribution = Util.readBooleanField(readFields, "askingAttribution", false);
        this.updatePackages = Util.readBooleanField(readFields, "updatePackages", false);
        this.orderIds = (LinkedList) Util.readObjectField(readFields, "orderIds", null);
        this.pushToken = Util.readStringField(readFields, "pushToken", (String) null);
        this.adid = Util.readStringField(readFields, Constants.AMP_TRACKING_OPTION_ADID, (String) null);
        this.clickTime = Util.readLongField(readFields, "clickTime", -1);
        this.installBegin = Util.readLongField(readFields, "installBegin", -1);
        this.installReferrer = Util.readStringField(readFields, "installReferrer", (String) null);
        this.googlePlayInstant = (Boolean) Util.readObjectField(readFields, "googlePlayInstant", null);
        this.clickTimeServer = Util.readLongField(readFields, "clickTimeServer", -1);
        this.installBeginServer = Util.readLongField(readFields, "installBeginServer", -1);
        this.installVersion = Util.readStringField(readFields, "installVersion", (String) null);
        this.clickTimeHuawei = Util.readLongField(readFields, "clickTimeHuawei", -1);
        this.installBeginHuawei = Util.readLongField(readFields, "installBeginHuawei", -1);
        this.installReferrerHuawei = Util.readStringField(readFields, "installReferrerHuawei", (String) null);
        this.installReferrerHuaweiAppGallery = Util.readStringField(readFields, "installReferrerHuaweiAppGallery", (String) null);
        this.clickTimeXiaomi = Util.readLongField(readFields, "clickTimeXiaomi", -1);
        this.installBeginXiaomi = Util.readLongField(readFields, "installBeginXiaomi", -1);
        this.installReferrerXiaomi = Util.readStringField(readFields, "installReferrerXiaomi", (String) null);
        this.clickTimeServerXiaomi = Util.readLongField(readFields, "clickTimeServerXiaomi", -1);
        this.installBeginServerXiaomi = Util.readLongField(readFields, "installBeginServerXiaomi", -1);
        this.installVersionXiaomi = Util.readStringField(readFields, "installVersionXiaomi", (String) null);
        this.clickTimeSamsung = Util.readLongField(readFields, "clickTimeSamsung", -1);
        this.installBeginSamsung = Util.readLongField(readFields, "installBeginSamsung", -1);
        this.installReferrerSamsung = Util.readStringField(readFields, "installReferrerSamsung", (String) null);
        this.clickTimeVivo = Util.readLongField(readFields, "clickTimeVivo", -1);
        this.installBeginVivo = Util.readLongField(readFields, "installBeginVivo", -1);
        this.installReferrerVivo = Util.readStringField(readFields, "installReferrerVivo", (String) null);
        this.installVersionVivo = Util.readStringField(readFields, "installVersionVivo", (String) null);
        this.installReferrerMeta = Util.readStringField(readFields, "installReferrerMeta", (String) null);
        this.clickTimeMeta = Util.readLongField(readFields, "clickTimeMeta", -1);
        this.isClickMeta = (Boolean) Util.readObjectField(readFields, "isClickMeta", null);
        if (this.uuid == null) {
            this.uuid = Util.createUuid();
        }
    }

    private static String stamp(long j2) {
        Calendar.getInstance().setTimeInMillis(j2);
        return Util.formatString("%02d:%02d:%02d", 11, 12, 13);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
    }

    public void addOrderId(String str) {
        if (this.orderIds == null) {
            this.orderIds = new LinkedList<>();
        }
        if (this.orderIds.size() >= 10) {
            this.orderIds.removeLast();
        }
        this.orderIds.addFirst(str);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityState activityState = (ActivityState) obj;
        return Util.equalString(this.uuid, activityState.uuid) && Util.equalBoolean(Boolean.valueOf(this.enabled), Boolean.valueOf(activityState.enabled)) && Util.equalBoolean(Boolean.valueOf(this.isGdprForgotten), Boolean.valueOf(activityState.isGdprForgotten)) && Util.equalBoolean(Boolean.valueOf(this.isThirdPartySharingDisabled), Boolean.valueOf(activityState.isThirdPartySharingDisabled)) && Util.equalBoolean(Boolean.valueOf(this.isThirdPartySharingDisabledForCoppa), Boolean.valueOf(activityState.isThirdPartySharingDisabledForCoppa)) && Util.equalBoolean(Boolean.valueOf(this.askingAttribution), Boolean.valueOf(activityState.askingAttribution)) && Util.equalInt(Integer.valueOf(this.eventCount), Integer.valueOf(activityState.eventCount)) && Util.equalInt(Integer.valueOf(this.sessionCount), Integer.valueOf(activityState.sessionCount)) && Util.equalInt(Integer.valueOf(this.subsessionCount), Integer.valueOf(activityState.subsessionCount)) && Util.equalLong(Long.valueOf(this.sessionLength), Long.valueOf(activityState.sessionLength)) && Util.equalLong(Long.valueOf(this.timeSpent), Long.valueOf(activityState.timeSpent)) && Util.equalLong(Long.valueOf(this.lastInterval), Long.valueOf(activityState.lastInterval)) && Util.equalBoolean(Boolean.valueOf(this.updatePackages), Boolean.valueOf(activityState.updatePackages)) && Util.equalObject(this.orderIds, activityState.orderIds) && Util.equalString(this.pushToken, activityState.pushToken) && Util.equalString(this.adid, activityState.adid) && Util.equalLong(Long.valueOf(this.clickTime), Long.valueOf(activityState.clickTime)) && Util.equalLong(Long.valueOf(this.installBegin), Long.valueOf(activityState.installBegin)) && Util.equalString(this.installReferrer, activityState.installReferrer) && Util.equalBoolean(this.googlePlayInstant, activityState.googlePlayInstant) && Util.equalLong(Long.valueOf(this.clickTimeServer), Long.valueOf(activityState.clickTimeServer)) && Util.equalLong(Long.valueOf(this.installBeginServer), Long.valueOf(activityState.installBeginServer)) && Util.equalString(this.installVersion, activityState.installVersion) && Util.equalLong(Long.valueOf(this.clickTimeHuawei), Long.valueOf(activityState.clickTimeHuawei)) && Util.equalLong(Long.valueOf(this.installBeginHuawei), Long.valueOf(activityState.installBeginHuawei)) && Util.equalString(this.installReferrerHuawei, activityState.installReferrerHuawei) && Util.equalString(this.installReferrerHuaweiAppGallery, activityState.installReferrerHuaweiAppGallery) && Util.equalLong(Long.valueOf(this.clickTimeXiaomi), Long.valueOf(activityState.clickTimeXiaomi)) && Util.equalLong(Long.valueOf(this.installBeginXiaomi), Long.valueOf(activityState.installBeginXiaomi)) && Util.equalString(this.installReferrerXiaomi, activityState.installReferrerXiaomi) && Util.equalLong(Long.valueOf(this.clickTimeServerXiaomi), Long.valueOf(activityState.clickTimeServerXiaomi)) && Util.equalLong(Long.valueOf(this.installBeginServerXiaomi), Long.valueOf(activityState.installBeginServerXiaomi)) && Util.equalString(this.installVersionXiaomi, activityState.installVersionXiaomi) && Util.equalLong(Long.valueOf(this.clickTimeSamsung), Long.valueOf(activityState.clickTimeSamsung)) && Util.equalLong(Long.valueOf(this.installBeginSamsung), Long.valueOf(activityState.installBeginSamsung)) && Util.equalString(this.installReferrerSamsung, activityState.installReferrerSamsung) && Util.equalLong(Long.valueOf(this.clickTimeVivo), Long.valueOf(activityState.clickTimeVivo)) && Util.equalLong(Long.valueOf(this.installBeginVivo), Long.valueOf(activityState.installBeginVivo)) && Util.equalString(this.installReferrerVivo, activityState.installReferrerVivo) && Util.equalString(this.installVersionVivo, activityState.installVersionVivo) && Util.equalString(this.installReferrerMeta, activityState.installReferrerMeta) && Util.equalLong(Long.valueOf(this.clickTimeMeta), Long.valueOf(activityState.clickTimeMeta)) && Util.equalBoolean(this.isClickMeta, activityState.isClickMeta);
    }

    public boolean findOrderId(String str) {
        LinkedList<String> linkedList = this.orderIds;
        if (linkedList == null) {
            return false;
        }
        return linkedList.contains(str);
    }

    public int hashCode() {
        return Util.hashBoolean(this.isClickMeta, Util.hashLong(Long.valueOf(this.clickTimeMeta), Util.hashString(this.installReferrerMeta, Util.hashString(this.installVersionVivo, Util.hashString(this.installReferrerVivo, Util.hashLong(Long.valueOf(this.installBeginVivo), Util.hashLong(Long.valueOf(this.clickTimeVivo), Util.hashString(this.installReferrerSamsung, Util.hashLong(Long.valueOf(this.installBeginSamsung), Util.hashLong(Long.valueOf(this.clickTimeSamsung), Util.hashString(this.installVersionXiaomi, Util.hashLong(Long.valueOf(this.installBeginServerXiaomi), Util.hashLong(Long.valueOf(this.clickTimeServerXiaomi), Util.hashString(this.installReferrerXiaomi, Util.hashLong(Long.valueOf(this.installBeginXiaomi), Util.hashLong(Long.valueOf(this.clickTimeXiaomi), Util.hashString(this.installReferrerHuaweiAppGallery, Util.hashString(this.installReferrerHuawei, Util.hashLong(Long.valueOf(this.installBeginHuawei), Util.hashLong(Long.valueOf(this.clickTimeHuawei), Util.hashString(this.installVersion, Util.hashLong(Long.valueOf(this.installBeginServer), Util.hashLong(Long.valueOf(this.clickTimeServer), Util.hashBoolean(this.googlePlayInstant, Util.hashString(this.installReferrer, Util.hashLong(Long.valueOf(this.installBegin), Util.hashLong(Long.valueOf(this.clickTime), Util.hashString(this.adid, Util.hashString(this.pushToken, Util.hashObject(this.orderIds, Util.hashBoolean(Boolean.valueOf(this.updatePackages), Util.hashLong(Long.valueOf(this.lastInterval), Util.hashLong(Long.valueOf(this.timeSpent), Util.hashLong(Long.valueOf(this.sessionLength), (((((Util.hashBoolean(Boolean.valueOf(this.askingAttribution), Util.hashBoolean(Boolean.valueOf(this.isThirdPartySharingDisabledForCoppa), Util.hashBoolean(Boolean.valueOf(this.isThirdPartySharingDisabled), Util.hashBoolean(Boolean.valueOf(this.isGdprForgotten), Util.hashBoolean(Boolean.valueOf(this.enabled), Util.hashString(this.uuid, 17)))))) * 37) + this.eventCount) * 37) + this.sessionCount) * 37) + this.subsessionCount))))))))))))))))))))))))))))))))));
    }

    public void resetSessionAttributes(long j2) {
        this.subsessionCount = 1;
        this.sessionLength = 0;
        this.timeSpent = 0;
        this.lastActivity = j2;
        this.lastInterval = -1;
    }

    public String toString() {
        return Util.formatString("ec:%d sc:%d ssc:%d sl:%.1f ts:%.1f la:%s uuid:%s", Integer.valueOf(this.eventCount), Integer.valueOf(this.sessionCount), Integer.valueOf(this.subsessionCount), Double.valueOf(((double) this.sessionLength) / 1000.0d), Double.valueOf(((double) this.timeSpent) / 1000.0d), stamp(this.lastActivity), this.uuid);
    }
}
