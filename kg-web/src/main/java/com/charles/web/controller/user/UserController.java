package com.charles.web.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.charles.dal.dao.MemberDAO;
import com.charles.dal.dataobject.MemberDO;
import com.charles.engine.redis.RedisUtil;
import com.charles.web.controller.BaseController;
import com.charles.web.controller.common.AjaxResult;

@Controller
public class UserController	extends BaseController {
	
	@Resource
	private MemberDAO memberDAO;
	
	@Resource
	private RedisUtil redisUtil;
//	@RequestMapping("/user")
//	public ModelAndView test(Model model){
//		ModelAndView mav = new ModelAndView();
//		controllerLogger.info("test.......");
//		Long count = memberDAO.queryCount();
//		controllerLogger.info("count:" + count);
//		MemberDO member = memberDAO.queryMemberByUsername("awdas");
//		controllerLogger.info("member:" + member);
//		mav.addObject("username", member.getUsername());
//		mav.addObject("password", member.getPassword());
//		mav.setViewName("user");
//		return mav;
//	}
	@RequestMapping("/user")
	@ResponseBody
	public AjaxResult test(Model model){
		controllerLogger.info("test.......");
		Long count = memberDAO.queryCount();
		controllerLogger.info("count:" + count);
		MemberDO member = memberDAO.queryMemberByUsername("awdas");
		controllerLogger.info("member:" + member);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usesrname", member.getUsername());
		map.put("password", member.getPassword());
//		redisUtil.set("test", "puremancw");
		String str = (String)redisUtil.get("test");
		System.out.println(str);
		map.put("tst", str);
		return AjaxResult.succResult(map);
	}
}
