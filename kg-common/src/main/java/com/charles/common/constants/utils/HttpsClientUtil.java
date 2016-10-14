package com.charles.common.constants.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.sun.net.ssl.internal.ssl.Provider;

public class HttpsClientUtil {
	
	private static class TrustAnyHostnameVerifierOld implements com.sun.net.ssl.HostnameVerifier {
		
		private TrustAnyHostnameVerifierOld() {
			
		}
		
		TrustAnyHostnameVerifierOld(TrustAnyHostnameVerifierOld trustAnyHostnameVerifierOld) {
			this();
		}
		
		public boolean verify(String arg0, String arg1) {
			return true;
		}
	}
	
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
		
		private TrustAnyHostnameVerifier() {
			
		}
		
		TrustAnyHostnameVerifier(TrustAnyHostnameVerifier trustAnyHostnameVerifier) {
			this();
		}
	}
	
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}
		
		private TrustAnyTrustManager() {
			
		}
		
		TrustAnyTrustManager(TrustAnyTrustManager trustAnyTrustManager) {
			this();
		}
	}
	
	private String url;
	
	private String requestDate;
	private String responseData;
	protected String httpmethod = "GET";
	protected String inEncode = "UTF-8";
	protected String outEncode = "UTF-8";
	protected String contenttype = "application/x-java-serialized-object";
	
	public HttpsClientUtil(String _url) {
		url = _url;
	}
	
	protected void connect() {
		OutputStream os = null;
		InputStream is = null;
		try{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, 
					new TrustManager[] {new TrustAnyTrustManager(null) }, 
					new SecureRandom());
			URL _url = new URL(url + (httpmethod.equalsIgnoreCase("POST") ? "" : ("?" + requestDate)));
			HttpURLConnection httpURLConn = (HttpURLConnection) _url.openConnection();
			if(httpURLConn instanceof com.sun.net.ssl.HttpsURLConnection) {
				com.sun.net.ssl.HttpsURLConnection httpsURLConn = (com.sun.net.ssl.HttpsURLConnection)httpURLConn;
				httpsURLConn.setHostnameVerifier(new TrustAnyHostnameVerifierOld(null));
				httpsURLConn.setSSLSocketFactory(sc.getSocketFactory());
			} else if(httpURLConn instanceof HttpsURLConnection) {
				HttpsURLConnection httpsURLConn = (HttpsURLConnection)httpURLConn;
				httpsURLConn.setHostnameVerifier(new TrustAnyHostnameVerifier(null));
				httpsURLConn.setSSLSocketFactory(sc.getSocketFactory());
			}
			httpURLConn.setRequestProperty("Content-type", contenttype);
			httpURLConn.setRequestMethod(httpmethod);
			httpURLConn.setDoInput(true);
			httpURLConn.setDoOutput(true);
			httpURLConn.setUseCaches(true);
			if(httpmethod.equalsIgnoreCase("POST")) {
				os = httpURLConn.getOutputStream();
				os.write(responseData.getBytes(outEncode));
			}
			is = httpURLConn.getInputStream();
			int i = 0;
			ByteArrayOutputStream fileByteStream = new ByteArrayOutputStream();
			while((i = is.read()) != -1) {
				fileByteStream.write(i);
			}
			responseData = fileByteStream.toString(inEncode);
			httpURLConn.disconnect();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if(os != null) {
					os.close();
				}
			} catch(Exception e) {
				
			}
			try {
				if(is != null) {
					is.close();
				}
			} catch(Exception e) {
				
			}
		}
		return;
	}
	
	
	public String perform(String _requestData) {
		this.requestDate = _requestData;
		connect();
		return responseData;
	}
	
	static {
		Security.addProvider(new Provider());
	}
}
