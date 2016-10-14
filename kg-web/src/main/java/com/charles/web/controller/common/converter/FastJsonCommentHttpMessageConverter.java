package com.charles.web.controller.common.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.charles.common.annotation.JsonComment;

public class FastJsonCommentHttpMessageConverter extends
		AbstractHttpMessageConverter<Object> {

	public final static Charset UTF8 = Charset.forName("UTF-8");

	private Charset charset = UTF8;

	private SerializerFeature[] serializerFeature;

	public FastJsonCommentHttpMessageConverter() {
		super(new MediaType("application", "jsonc", UTF8));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	public Charset getCharset() {
		return this.charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public SerializerFeature[] getFeatures() {
		return serializerFeature;
	}

	public void setFeatures(SerializerFeature... features) {
		this.serializerFeature = features;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream in = inputMessage.getBody();

		byte[] buf = new byte[1024];
		for (;;) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}

			if (len > 0) {
				baos.write(buf, 0, len);
			}
		}

		byte[] bytes = baos.toByteArray();
		if (charset == UTF8) {
			return JSON.parseObject(bytes, clazz);
		} else {
			return JSON.parseObject(bytes, 0, bytes.length,
					charset.newDecoder(), clazz);
		}
	}
	
	NameFilter filter = new NameFilter() {	 
	    public String process(Object source, String name, Object value) {    	
	    	Field field;
			try {
				field = source.getClass().getDeclaredField(name);
				JsonComment jsonComment = field.getAnnotation(JsonComment.class);
				if(jsonComment != null){
					return name+"("+jsonComment.value()+")";	
				}
				else{
					return name;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return name;
	    } 
	};

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

/*		OutputStream out = outputMessage.getBody();
		
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer serializer = new JSONSerializer(sw);
		serializer.getNameFilters().add(filter);
		serializer.write(obj);

		out.write(sw.toBytes(charset.name()));*/
		
		
		OutputStream out = outputMessage.getBody();
		byte[] bytes;


			String text;
			if (serializerFeature != null) {
				text = JSON.toJSONString(obj, filter, serializerFeature);
			} else {
				text = JSON.toJSONString(obj, filter);
			}
			bytes = text.getBytes(charset);


		out.write(bytes);
		
	}

}
