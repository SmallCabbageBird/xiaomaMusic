package com.xiaoma.music.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xiaoma.music.entity.Music;
import com.xiaoma.music.entity.PageBean;
import com.xiaoma.music.entity.User;
import com.xiaoma.music.service.IMusicService;
import com.xiaoma.music.service.IUserService;
import com.xiaoma.music.utils.PrintUtil;

/**
 * 用户动作类
 * @author lenovo
 *
 */
@Controller("userAction")
@Scope("prototype")
@ParentPackage("myDefault")
@Namespace("/user")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMusicService musicService;
	
	@Override
	public User getModel() {

		return user;
	}
	
	
	
	/**
	 * 去到注册页面
	 */
	@Action(value="toRegisterUI",results={
			@Result(name="register",type="redirect",location="/jsp/user/register.jsp")
	})
	public String toRegisterUI(){
		
		try {
			
			return "register";	
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
	}
	
	
	/**
	 * 注册ajax判断用户名是否存在
	 */
	@Action(value="ajaxCheckUserName",results={
			@Result(name="register",type="stream"),
	})
	public String ajaxCheckUserName(){
		
		try {
			String username = user.getUsername();
		
			String flag = userService.checkUserName(username);
			
			HttpServletResponse response = ServletActionContext.getResponse();
			
			PrintUtil.Print(response.getWriter(), flag);
			
			return "register";
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return "erro";
		}
		
		
		
	}
	
	
	/**
	 * 注册
	 */
	@Action(value="register",results={
			@Result(name="register",type="dispatcher",location="/jsp/user/register.jsp"),
	})
	public String register(){
		
		try {
			
			String username = user.getUsername();
			
			String flag = userService.checkUserName(username);
			
			if("true".equals(flag)){
				return "register";
			}
			
			userService.register(user);
			
			return SUCCESS;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
	}
	
	
	
	
	/**
	 * 去到主页(需要先将pageBean放入值栈,所以用转发)
	 */
	private String curPage;
	@Action(value="toIndexUI",results={
			@Result(name="index",type="dispatcher",location="/jsp/index.jsp")
	})
	public String toIndexUI(){
		
		try {
			
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Music.class);//from Music
			
			//调用service方法分页查询音乐列表		
			PageBean<Music> pageBean =	musicService.findMusic(detachedCriteria,curPage);
			
			//把pageBean对象压入值栈顶
			ActionContext.getContext().getValueStack().push(pageBean);
			
			return "index";
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
		
	}
	
	
	
	/**
	 * 去到登陆页面
	 * @return
	 */
	@Action(value="toLoginUI",results={
			@Result(name="login",type="redirect",location="/jsp/user/login.jsp")
	})
	public String toLoginUI(){
		
		try {
			
			return LOGIN;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
	}
	
	
	/**
	 * 登陆
	 * @return
	 */
	@Action(value="login",results={
			@Result(name="default",type="redirect",location="/jsp/default.jsp"),
			@Result(name="login",type="dispatcher",location="/jsp/user/login.jsp")//用转发,页面的引入外部文件的样式会全部找不到
	})
	public String login(){
		
		try {
	
			User dbUser = userService.login(user);
			
			if(dbUser != null){
				
				HttpSession session = ServletActionContext.getRequest().getSession();
					
				session.setAttribute("user",dbUser);
				
				return "default";
			}
			
			ServletActionContext.getRequest().setAttribute("flag","false");
			
			
			return LOGIN;
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
	}
	
	/**
	 * 退出登陆(退出后还留在首页所以用转发,因为要显示音乐信息)
	 */
	@Action(value="loginOut",results={
			@Result(name="default",type="dispatcher",location="/jsp/default.jsp")
	})
	public String loginOut(){
		
		try {
			
			HttpSession session = ServletActionContext.getRequest().getSession();
			
			session.removeAttribute("user");
			
			return "default";
			
		} catch (Exception e) {

			e.printStackTrace();
			
			return "erro";
		}
		
		
	}



	
	
	
	//=====================================\
	
	public String getCurPage() {
		return curPage;
	}
	
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

}
