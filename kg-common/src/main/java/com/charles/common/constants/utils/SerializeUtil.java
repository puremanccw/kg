package com.charles.common.constants.utils;

import java.io.Serializable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class SerializeUtil {
	
	/**
	 * 序列化成JSON
	 */
	public void writeSerializeJSON() {
		XStream xStream = new XStream(new JettisonMappedXmlDriver());	
	}
	
}
