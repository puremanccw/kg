package com.charles.service.dubbo.impl;

import com.charles.facade.dubbo.TestRegistryService;


public class TestRegistryServiceImpl implements TestRegistryService {

	public String hello(String name) {
		return "hello " + name;
	}

}
