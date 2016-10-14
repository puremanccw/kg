package com.charles.common.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 特殊日志容器
 * @author puremancw
 *
 */
public interface SpecialLogger {
	//controller层日志
    public static final Logger controllerLogger = LoggerFactory.getLogger("controller");
    //daemon
    public static final Logger daemonLogger = LoggerFactory.getLogger("daemon");
}
