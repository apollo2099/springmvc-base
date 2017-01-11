package com.base.controller;
import javax.servlet.http.HttpServletRequest;

import com.base.common.utils.PageInfo;
import com.base.utils.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller 基类
 */
public class BaseController {

	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 获取request请求对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 获取分页对象信息
	 * @return
	 */
	public PageInfo getPageInfo(){
		HttpServletRequest request = this.getRequest();
		String sEcho = request.getParameter("sEcho");
		String start = request.getParameter("iDisplayStart");// 起始
		String length = request.getParameter("iDisplayLength");// 分页大小size
		PageInfo pageInfo = new PageInfo();
		if(ObjectUtils.isNotEmpty(start)){
			pageInfo.setStart(Integer.parseInt(start));
		}
		if(ObjectUtils.isNotEmpty(length)) {
			pageInfo.setLength(Integer.parseInt(length) + Integer.parseInt(start));
		}
		if(ObjectUtils.isNotEmpty(sEcho)) {
			pageInfo.setsEcho(Integer.parseInt(sEcho));
		}
		return pageInfo;
	}
}
