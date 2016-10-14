package com.charles.engine.mq.handler;

/**
 * 消息处理器
 * 
 * 消息监听器收到消息后，最终交给消息处理器进行处理
 * @author puremancw
 *
 */
public interface MessageHandler {
	
	/**
	 * 处理消息内容
	 * @param request 消息内容
	 * @param request
	 */
	public void handle(Object request);
	
	/**
	 * 处理消息内容
	 * @param request 消息内容
	 * @throws Exception
	 */
	public void handleMessage(Object request) throws Exception;
}
