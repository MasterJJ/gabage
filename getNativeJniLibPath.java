private static final Map<String, String> ABI_TO_INSTRUCTION_SET_MAP
			= new HashMap<String, String>();
	static {
		ABI_TO_INSTRUCTION_SET_MAP.put("armeabi", "arm");
		ABI_TO_INSTRUCTION_SET_MAP.put("armeabi-v7a", "arm");
		ABI_TO_INSTRUCTION_SET_MAP.put("mips", "mips");
		ABI_TO_INSTRUCTION_SET_MAP.put("mips64", "mips64");
		ABI_TO_INSTRUCTION_SET_MAP.put("x86", "x86");
		ABI_TO_INSTRUCTION_SET_MAP.put("x86_64", "x86_64");
		ABI_TO_INSTRUCTION_SET_MAP.put("arm64-v8a", "arm64");
	}


	public static String getNativeLibraryDirectory(Context context) {
		int sdk_level = android.os.Build.VERSION.SDK_INT;

		if (sdk_level >= Build.VERSION_CODES.GINGERBREAD) {
			try {
				return (String) ApplicationInfo.class.getField("nativeLibraryRootDir").get(context.getApplicationInfo());
			} catch (Exception e) {
				// e.printStackTrace();
				return context.getApplicationInfo().nativeLibraryDir;
			}
		}
		else if (sdk_level >= Build.VERSION_CODES.DONUT) {
			return context.getApplicationInfo().dataDir + "/lib";
		}

		return "/data/data/" + context.getPackageName() + "/lib";
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private static String getSupported_Abis() {
		int sdk_level = android.os.Build.VERSION.SDK_INT;
		String abi = "";

		if (sdk_level < Build.VERSION_CODES.LOLLIPOP) {
			abi = Build.CPU_ABI;
		} else {
			abi = Build.SUPPORTED_ABIS[0];
		}

		// convert to abi cpufeature for nick
		abi = ABI_TO_INSTRUCTION_SET_MAP.get(abi);
		return abi;
	}


	/*
	*    get App LibPath
	*    ex data/data/package/lib/cpufeature or data/data/package/lib/cpufeature
	*    @param  context
	*    @return libfull path
	* */
	public static String getAppVpnLibPath(final Context context) {
		// get lib path
		String packageLibPath = getNativeLibraryDirectory(context);
		String ret = packageLibPath;

		// get cpu feature
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ret += File.separator + getSupported_Abis();
		}


		return ret;
	}