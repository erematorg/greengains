package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DeviceInfo {
    public static final String OS_NAME = "android";
    private static final String SETTING_ADVERTISING_ID = "advertising_id";
    private static final String SETTING_LIMIT_AD_TRACKING = "limit_ad_tracking";
    public static final String TAG = "com.amplitude.api.DeviceInfo";
    private CachedInfo cachedInfo;
    /* access modifiers changed from: private */
    public Context context;
    private boolean locationListening = true;

    public class CachedInfo {
        /* access modifiers changed from: private */
        public String advertisingId;
        /* access modifiers changed from: private */
        public String brand;
        /* access modifiers changed from: private */
        public String carrier;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public boolean gpsEnabled;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public boolean limitAdTrackingEnabled;
        /* access modifiers changed from: private */
        public String manufacturer;
        /* access modifiers changed from: private */
        public String model;
        /* access modifiers changed from: private */
        public String osName;
        /* access modifiers changed from: private */
        public String osVersion;
        /* access modifiers changed from: private */
        public String versionName;

        private boolean checkGPSEnabled() {
            Class<GooglePlayServicesUtil> cls = GooglePlayServicesUtil.class;
            try {
                String str = GooglePlayServicesUtil.GMS_ERROR_DIALOG;
                Integer num = (Integer) cls.getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke((Object) null, new Object[]{DeviceInfo.this.context});
                return num != null && num.intValue() == 0;
            } catch (NoClassDefFoundError e3) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services Util not found!");
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e3);
                return false;
            } catch (ClassNotFoundException e4) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services Util not found!");
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e4);
                return false;
            } catch (NoSuchMethodException e5) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e5);
                return false;
            } catch (InvocationTargetException e6) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e6);
                return false;
            } catch (IllegalAccessException e7) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e7);
                return false;
            } catch (Exception e8) {
                AmplitudeLog logger = AmplitudeLog.getLogger();
                logger.w(DeviceInfo.TAG, "Error when checking for Google Play Services: " + e8);
                Diagnostics.getLogger().logError("Failed to check GPS enabled", e8);
                return false;
            }
        }

        private String getAdvertisingId() {
            return "Amazon".equals(getManufacturer()) ? getAndCacheAmazonAdvertisingId() : getAndCacheGoogleAdvertisingId();
        }

        private String getAndCacheAmazonAdvertisingId() {
            ContentResolver contentResolver = DeviceInfo.this.context.getContentResolver();
            boolean z2 = false;
            if (Settings.Secure.getInt(contentResolver, DeviceInfo.SETTING_LIMIT_AD_TRACKING, 0) == 1) {
                z2 = true;
            }
            this.limitAdTrackingEnabled = z2;
            String string = Settings.Secure.getString(contentResolver, DeviceInfo.SETTING_ADVERTISING_ID);
            this.advertisingId = string;
            return string;
        }

        private String getAndCacheGoogleAdvertisingId() {
            try {
                Object invoke = AdvertisingIdClient.class.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{DeviceInfo.this.context});
                Boolean bool = (Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", (Class[]) null).invoke(invoke, (Object[]) null);
                this.limitAdTrackingEnabled = bool != null && bool.booleanValue();
                this.advertisingId = (String) invoke.getClass().getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
            } catch (ClassNotFoundException e3) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services SDK not found!");
                Diagnostics.getLogger().logError("Failed to get ADID", e3);
            } catch (InvocationTargetException e4) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                Diagnostics.getLogger().logError("Failed to get ADID", e4);
            } catch (Exception e5) {
                AmplitudeLog.getLogger().e(DeviceInfo.TAG, "Encountered an error connecting to Google Play Services", e5);
                Diagnostics.getLogger().logError("Failed to get ADID", e5);
            }
            return this.advertisingId;
        }

        private String getBrand() {
            return Build.BRAND;
        }

        private String getCarrier() {
            try {
                return ((TelephonyManager) DeviceInfo.this.context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception e3) {
                Diagnostics.getLogger().logError("Failed to get carrier", e3);
                return null;
            }
        }

        private String getCountry() {
            String countryFromLocation = getCountryFromLocation();
            if (!Utils.isEmptyString(countryFromLocation)) {
                return countryFromLocation;
            }
            String countryFromNetwork = getCountryFromNetwork();
            return !Utils.isEmptyString(countryFromNetwork) ? countryFromNetwork : getCountryFromLocale();
        }

        private String getCountryFromLocale() {
            return Locale.getDefault().getCountry();
        }

        private String getCountryFromLocation() {
            Location mostRecentLocation;
            List<Address> fromLocation;
            if (DeviceInfo.this.isLocationListening() && (mostRecentLocation = DeviceInfo.this.getMostRecentLocation()) != null) {
                try {
                    if (Geocoder.isPresent() && (fromLocation = DeviceInfo.this.getGeocoder().getFromLocation(mostRecentLocation.getLatitude(), mostRecentLocation.getLongitude(), 1)) != null) {
                        for (Address next : fromLocation) {
                            if (next != null) {
                                return next.getCountryCode();
                            }
                        }
                    }
                } catch (IOException e3) {
                    Diagnostics.getLogger().logError("Failed to get country from location", e3);
                } catch (NullPointerException e4) {
                    Diagnostics.getLogger().logError("Failed to get country from location", e4);
                } catch (NoSuchMethodError e5) {
                    Diagnostics.getLogger().logError("Failed to get country from location", e5);
                } catch (IllegalArgumentException e6) {
                    Diagnostics.getLogger().logError("Failed to get country from location", e6);
                } catch (IllegalStateException e7) {
                    Diagnostics.getLogger().logError("Failed to get country from location", e7);
                }
            }
            return null;
        }

        private String getCountryFromNetwork() {
            String networkCountryIso;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.context.getSystemService("phone");
                if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null) {
                    return null;
                }
                return networkCountryIso.toUpperCase(Locale.US);
            } catch (Exception e3) {
                Diagnostics.getLogger().logError("Failed to get country from network", e3);
                return null;
            }
        }

        private String getLanguage() {
            return Locale.getDefault().getLanguage();
        }

        private String getManufacturer() {
            return Build.MANUFACTURER;
        }

        private String getModel() {
            return Build.MODEL;
        }

        private String getOsName() {
            return DeviceInfo.OS_NAME;
        }

        private String getOsVersion() {
            return Build.VERSION.RELEASE;
        }

        private String getVersionName() {
            try {
                return DeviceInfo.this.context.getPackageManager().getPackageInfo(DeviceInfo.this.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e3) {
                Diagnostics.getLogger().logError("Failed to get version name", e3);
                return null;
            }
        }

        private CachedInfo() {
            this.advertisingId = getAdvertisingId();
            this.versionName = getVersionName();
            this.osName = getOsName();
            this.osVersion = getOsVersion();
            this.brand = getBrand();
            this.manufacturer = getManufacturer();
            this.model = getModel();
            this.carrier = getCarrier();
            this.country = getCountry();
            this.language = getLanguage();
            this.gpsEnabled = checkGPSEnabled();
        }
    }

    public DeviceInfo(Context context2) {
        this.context = context2;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public String getAdvertisingId() {
        return getCachedInfo().advertisingId;
    }

    public String getBrand() {
        return getCachedInfo().brand;
    }

    public String getCarrier() {
        return getCachedInfo().carrier;
    }

    public String getCountry() {
        return getCachedInfo().country;
    }

    public Geocoder getGeocoder() {
        return new Geocoder(this.context, Locale.ENGLISH);
    }

    public String getLanguage() {
        return getCachedInfo().language;
    }

    public String getManufacturer() {
        return getCachedInfo().manufacturer;
    }

    public String getModel() {
        return getCachedInfo().model;
    }

    public Location getMostRecentLocation() {
        LocationManager locationManager;
        List<String> list;
        Location location;
        Location location2 = null;
        if (!isLocationListening() || (locationManager = (LocationManager) this.context.getSystemService(FirebaseAnalytics.Param.LOCATION)) == null) {
            return null;
        }
        try {
            list = locationManager.getProviders(true);
        } catch (SecurityException e3) {
            Diagnostics.getLogger().logError("Failed to get most recent location", e3);
            list = null;
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String lastKnownLocation : list) {
            try {
                location = locationManager.getLastKnownLocation(lastKnownLocation);
            } catch (Exception e4) {
                AmplitudeLog.getLogger().w(TAG, "Failed to get most recent location");
                Diagnostics.getLogger().logError("Failed to get most recent location", e4);
                location = null;
            }
            if (location != null) {
                arrayList.add(location);
            }
        }
        Iterator it = arrayList.iterator();
        long j2 = -1;
        while (it.hasNext()) {
            Location location3 = (Location) it.next();
            if (location3.getTime() > j2) {
                j2 = location3.getTime();
                location2 = location3;
            }
        }
        return location2;
    }

    public String getOsName() {
        return getCachedInfo().osName;
    }

    public String getOsVersion() {
        return getCachedInfo().osVersion;
    }

    public String getVersionName() {
        return getCachedInfo().versionName;
    }

    public boolean isGooglePlayServicesEnabled() {
        return getCachedInfo().gpsEnabled;
    }

    public boolean isLimitAdTrackingEnabled() {
        return getCachedInfo().limitAdTrackingEnabled;
    }

    public boolean isLocationListening() {
        return this.locationListening;
    }

    public void prefetch() {
        getCachedInfo();
    }

    public void setLocationListening(boolean z2) {
        this.locationListening = z2;
    }
}
