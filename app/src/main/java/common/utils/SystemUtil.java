package common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.DisplayMetrics;

import com.suli.myutils.GlobalContext;

import java.util.Locale;

/**
 * Created by suli on 2015/4/25.
 */
public class SystemUtil {

    /**
     * @return 获取系统版本号
     */
    public static String getVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getModel() {
        return Build.MODEL;
    }


    /**
     * @return 获取应用版本号
     */
    public static String getVersionName() {
        PackageManager packageManager = GlobalContext.getInstance().getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(GlobalContext.getInstance().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getScreenResolution() {
        DisplayMetrics dm = GlobalContext.getInstance().getResources().getDisplayMetrics();
        return dm.widthPixels + "*" + dm.heightPixels;
    }

    /**
     * @return 获取MAC地址
     */
    public static String getMac() {
        WifiManager wifiManager = (WifiManager) GlobalContext.getInstance().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }

    public static String getLanguage() {
        Locale locale = GlobalContext.getInstance().getResources().getConfiguration().locale;
        return locale.getLanguage();
    }
}
