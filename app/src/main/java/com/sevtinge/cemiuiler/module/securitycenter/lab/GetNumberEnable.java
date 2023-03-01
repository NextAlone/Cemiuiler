package com.sevtinge.cemiuiler.module.securitycenter.lab;

import com.sevtinge.cemiuiler.module.base.BaseHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.io.File;

public class GetNumberEnable extends BaseHook {

    Class<?> mLab;
    Class<?> mStableVer;

    @Override
    public void init() {
        String appVersionName = getPackageVersion(lpparam);
        if (appVersionName.startsWith("7.4.9")) {
            mLab = findClassIfExists("pa.r");
        }else{
            mLab = findClassIfExists("com.miui.permcenter.q");
        }
        mStableVer = findClassIfExists("miui.os.Build");

        findAndHookMethod(mLab, "i", new MethodHook() {
            @Override
            protected void before(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });

        /*findAndHookMethod(mLab, "f", new MethodHook() {
            @Override
            protected void before(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });*/
        //findAndHookMethod(mStableVer, "IS_STABLE_VERSION", new MethodHook() {
        //    @Override
        //    protected void before(MethodHookParam param) throws Throwable {
        //        param.setResult(true);
        //    }
        //});
    }

    private static String getPackageVersion(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            Class<?> parserCls = XposedHelpers.findClass("android.content.pm.PackageParser", lpparam.classLoader);
            Object parser = parserCls.newInstance();
            File apkPath = new File(lpparam.appInfo.sourceDir);
            Object pkg = XposedHelpers.callMethod(parser, "parsePackage", apkPath, 0);
            String versionName = (String) XposedHelpers.getObjectField(pkg, "mVersionName");
            int versionCode = XposedHelpers.getIntField(pkg, "mVersionCode");
            XposedBridge.log("Cemiuiler: " + String.format("%s (%d", versionName, versionCode));
            return String.format("%s (%d", versionName, versionCode);
        } catch (Throwable e) {
            XposedBridge.log("Cemiuiler: Unknown Version.");
            XposedBridge.log(e);
            return "null";
        }
    }
}