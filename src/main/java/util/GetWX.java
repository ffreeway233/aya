package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetWX {

	/**
	 * @param args
	 */
	private static final Logger log = Logger.getLogger(GetWX.class);// 日志文件

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getPro("path"));
	}

	public static String getPro(String pro) {
		GetWX loadProp = new GetWX();
		InputStream in = loadProp.getClass().getResourceAsStream(
				"/util/wx.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(pro);
	}

	public static void setPro(String key, String value)
			throws FileNotFoundException {
		GetWX loadProp = new GetWX();
		Properties prop = new Properties();
		InputStream fis = null;
		OutputStream fos = null;
		File file = null;
		try {
			java.net.URL url = loadProp.getClass().getResource(
					"/util/wx.properties");
			file = new File(url.toURI());
			if (!file.exists())
				file.createNewFile();
			fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();// 一定要在修改值之前关闭fis
			fos = new FileOutputStream(file);
			prop.setProperty(key, value);
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			loadProp = null;
			prop = null;
			file = null;
			fis = null;
			fos = null;
		}
	}
}
