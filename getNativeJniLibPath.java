public static String getNativeLibraryDirectory(Context context) {
    int sdk_level = android.os.Build.VERSION.SDK_INT;

    if (sdk_level >= Build.VERSION_CODES.GINGERBREAD) {
        try {
            String secondary = (String) ApplicationInfo.class.getField("nativeLibraryRootDir").get(context.getApplicationInfo());
            return secondary;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    else if (sdk_level >= Build.VERSION_CODES.DONUT) {
        return context.getApplicationInfo().dataDir + "/lib";
    }

    return "/data/data/" + context.getPackageName() + "/lib";
}