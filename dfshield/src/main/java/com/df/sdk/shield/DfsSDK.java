package com.df.sdk.shield;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.df.sdk.shield.ifs.IDfsDeviceInfoProvider;
import com.df.sdk.shield.ifs.IDfsPkgProvider;
import com.df.sdk.shield.ifs.IDfsPrivacyPolicy;
import com.turingfd.sdk.pri_mini.ITuringDeviceInfoProvider;
import com.turingfd.sdk.pri_mini.ITuringPkgProvider;
import com.turingfd.sdk.pri_mini.TuringSDK;

import java.util.ArrayList;
import java.util.List;



public final class DfsSDK {

    private static final Integer DEFAULT_CHANNEL = 400065; // 你的默认渠道值
    private static final String DEFAULT_HOST_URL = "https://www.turingfraud.net"; // 你的默认URL

    public static int createConf(Context context) {
        return createConf(context, DEFAULT_CHANNEL, DEFAULT_HOST_URL);
    }

    public static int createConf(Context context, Integer channel) {
        return createConf(context, channel, DEFAULT_HOST_URL);
    }

    public static int createConf(Context context, Integer channel, String hostUrl) {
        return TuringSDK.createConf(context, new IDfsPrivacyPolicy() {
                    @Override
                    public boolean userAgreement() {
                        return true;
                    }
                })
                .channel(channel)
                .hostUrl(hostUrl)
                .turingDeviceInfoProvider(new ITuringDeviceInfoProvider() {
                    @SuppressLint("HardwareIds")
                    @Override
                    public String getImei() {
                        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        return tm.getDeviceId();
                    }

                    @SuppressLint("HardwareIds")
                    @Override
                    public String getImsi() {
                        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        return tm.getSubscriberId();
                    }

                    @Override
                    public String getAndroidId() {
                        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                    }
                })
                .turingPkgProvider(new ITuringPkgProvider() {
                    @Override
                    public List<String> getPkgs() {
                        List<String> pkgs = new ArrayList<>();
                        try {
                            List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
                            for (PackageInfo packageInfo : packageInfoList) {
                                pkgs.add(packageInfo.packageName);
                            }
                        } catch (Throwable ignore) {
                        }
                        return pkgs;
                    }
                })
                .enableClickRisk()
                .enableDRM()
                .build()
                .init();
    }

    public static int createConf(Context context, Integer channel, String hostUrl, IDfsPrivacyPolicy privacyPolicy, IDfsDeviceInfoProvider deviceInfoProvider, IDfsPkgProvider provider) {
        return TuringSDK.createConf(context, privacyPolicy)
                .channel(channel)
                .hostUrl(hostUrl)
                .turingDeviceInfoProvider(deviceInfoProvider)
                .turingPkgProvider(provider)
                .enableClickRisk()
                .enableDRM()
                .build()
                .init();
    }

    public static int createConf(Context context, Integer channel, IDfsPrivacyPolicy privacyPolicy, IDfsDeviceInfoProvider deviceInfoProvider, IDfsPkgProvider provider) {
        return TuringSDK.createConf(context, privacyPolicy)
                .channel(channel)
                .hostUrl(DEFAULT_HOST_URL)
                .turingDeviceInfoProvider(deviceInfoProvider)
                .turingPkgProvider(provider)
                .enableClickRisk()
                .enableDRM()
                .build()
                .init();
    }
}
