package com.charles.engine.groovy;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.util.List;


public class ParseFileUtil {
	
	public List<ParseDetail> parseFile(String groovyFilePath, String targetFilePath) throws Exception {
		FileParseResult fileParseResult = new FileParseResult();
		
		fileParseResult.setTargetFile(new File(targetFilePath));
		
		GroovyClassLoader cl = new GroovyClassLoader();
		@SuppressWarnings("unchecked")
		Class<FileParse> groovyClass = cl.parseClass(new File(groovyFilePath));
		FileParse fileParse = groovyClass.newInstance();
		fileParse.parserFile(fileParseResult);
		return fileParseResult.getDetailList();
		
	}
}
