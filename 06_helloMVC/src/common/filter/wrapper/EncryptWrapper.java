package common.filter.wrapper;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		
		String value = "";
		
		// 패스워드 일때만 암호화 처리
		if(name !=null && name.equals("password")) {
			super.getParameter(name); // client value
			value = getSha2(super.getParameter(name));
			System.out.println("암호화된 비번 : " + value);
			return value;
		}else {
			return super.getParameter(name);
		}
	}
	
	private String getSha2(String val) {
		
		String enPwd = "";
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes =
		val.getBytes(Charset.forName("UTF-8"));
		
		md.update(bytes); // 암화화
		enPwd = Base64.getEncoder().encodeToString(md.digest()); // 암호화한 바이트를 엔코딩
		
		return enPwd;
	}
	
}
