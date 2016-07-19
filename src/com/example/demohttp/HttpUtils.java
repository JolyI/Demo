package com.example.demohttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Log;

/**
 * 网络请求 get
 * 
 * @author YAH
 * 
 */
public class HttpUtils {

	/**
	 * http get请求
	 * 
	 * @param urlpath
	 *            请求的url
	 * @return
	 */
	public static String sendHttpGetRequest(String urlpath) {
		Log.i("iiiiiiiiii", "======================sendHttpGetRequest：");
		HttpURLConnection conn = null;
		try {
			// 一般都需要进行URLEncoder.encode(urlpath, "utf-8"); 若返回码为505
			// 可以试着用URLEncoder.encode这个方法
			String url2 = URLEncoder.encode(urlpath, "utf-8");
			Log.i("iiiiiiiiii", "======================url2==：" + url2);
			URL url = new URL(urlpath);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			conn.setReadTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			int reponseCode = conn.getResponseCode();
			Log.i("iiiiiiiiii", "======================返回码为：：" + reponseCode);
			while (reponseCode == 200) {
				InputStream is = conn.getInputStream();
				//解析获得的字符串  方式一：
				// BufferedReader reader = new BufferedReader(
				// new InputStreamReader(is));
				// StringBuilder builder = new StringBuilder();
				// String buff = "";
				// while ((buff = reader.readLine()) != null) {
				// builder.append(buff);
				// }
				// return builder.toString();
				
				//解析获得的字符串  方式二：
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buff = new byte[3072];
				int len = 0;
				while ((len = is.read(buff)) != -1) {
					baos.write(buff, 0, len);
				}
				baos.close();
				is.close();
				byte[] bytearray = baos.toByteArray();
				Log.i("iiiiiiiiii", "======================返回数据解析为：："+ new String(bytearray));
				
				return new String(bytearray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}
}
