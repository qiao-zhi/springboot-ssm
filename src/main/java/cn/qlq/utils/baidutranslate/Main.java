package cn.qlq.utils.baidutranslate;

public class Main {

	// 在平台申请的APP_ID 详见
	// http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
	private static final String APP_ID = "";
	private static final String SECURITY_KEY = "";

	public static void main(String[] args) {
		TransApi api = new TransApi(APP_ID, SECURITY_KEY);

		String query = "you are my son!";
		System.out.println(api.getTransResult(query, "auto", "auto"));
	}

}
