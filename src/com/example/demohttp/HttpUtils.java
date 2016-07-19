package com.example.demohttp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Log;

/**
 * �������� get
 * 
 * @author YAH
 * 
 */
public class HttpUtils {

	/**
	 * http get����
	 * 
	 * @param urlpath
	 *            �����url
	 * @return
	 */
	public static String sendHttpGetRequest(String urlpath) {
		Log.i("iiiiiiiiii", "======================sendHttpGetRequest��");
		HttpURLConnection conn = null;
		try {
			// һ�㶼��Ҫ����URLEncoder.encode(urlpath, "utf-8"); ��������Ϊ505
			// ����������URLEncoder.encode�������
			String url2 = URLEncoder.encode(urlpath, "utf-8");
			Log.i("iiiiiiiiii", "======================url2==��" + url2);
			URL url = new URL(urlpath);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			conn.setReadTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			int reponseCode = conn.getResponseCode();
			Log.i("iiiiiiiiii", "======================������Ϊ����" + reponseCode);
			while (reponseCode == 200) {
				InputStream is = conn.getInputStream();
				//������õ��ַ���  ��ʽһ��
				// BufferedReader reader = new BufferedReader(
				// new InputStreamReader(is));
				// StringBuilder builder = new StringBuilder();
				// String buff = "";
				// while ((buff = reader.readLine()) != null) {
				// builder.append(buff);
				// }
				// return builder.toString();
				
				//������õ��ַ���  ��ʽ����
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buff = new byte[3072];
				int len = 0;
				while ((len = is.read(buff)) != -1) {
					baos.write(buff, 0, len);
				}
				baos.close();
				is.close();
				byte[] bytearray = baos.toByteArray();
				Log.i("iiiiiiiiii", "======================�������ݽ���Ϊ����"+ new String(bytearray));
				
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
