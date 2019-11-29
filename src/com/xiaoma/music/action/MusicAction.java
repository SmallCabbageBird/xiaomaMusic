package com.xiaoma.music.action;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.xiaoma.music.utils.UuidUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.xiaoma.music.utils.PrintUtil;

/**
 * 音乐动作类
 * @author lenovo
 *
 */
@Controller("musicAction")
@Scope("prototype")
@ParentPackage("myDefault")
@Namespace("/music")
public class MusicAction  extends ActionSupport implements ModelDriven<Music> {
	
	private Music music = new Music();
	@Autowired
	private IMusicService musicService;
	private String curPage; //页数
	
	@Override
	public Music getModel() {
		
		return music;
	}
	
	
	/**
	 * 去到上传音乐页面
	 */
	@Action(value="toUpLoadMusicUI",results={
			@Result(name="toUpLoadMusicUI",type="redirect",location="/jsp/music/upload.jsp")
	})
	public String toUpLoadMusicUI(){
		
		return "toUpLoadMusicUI";
	}
	
	
	/**
	 * 上传音乐
	 */
	private File upload;//上传文件
	private String uploadFileName;//上传文件名
	private String upLoadFlag;//上传成功标记字符串
	@Action(value="upLoadMusic",results={
			@Result(name="upLoadMusic",type="dispatcher",location="/jsp/music/upload.jsp")
	})
	public String upLoadMusic(){
		
	
		try {
			
			//需要在webroot目录下创建一个music目录
			String type = uploadFileName.substring(uploadFileName.lastIndexOf("."));
			
			String uuid = UuidUtil.getUuid();
	
			if("true".equals(musicService.musicExist(uploadFileName.replace(type,"")))){
				
				upLoadFlag = "音乐名已存在";
				
				return "upLoadMusic";
			}
			
			
			//输入流
			FileInputStream input = new FileInputStream(upload);
			
			//文件夹名称
			String directory = "/music";
			
			//文件夹绝对路径
			String target = ServletActionContext.getServletContext().getRealPath(directory);
			
			
			//音乐文件存储路径
			String path = target + "/" + uuid + type; 
			
			//输出流
			FileOutputStream output = new FileOutputStream(path);
			
			IOUtils.copy(input, output);
			
			upLoadFlag = "true";
			
			//将音乐拷贝到项目文件夹中
			//input = new FileInputStream(upload);//用IOUtils工具类使用后的流必须赋值
			//output = new FileOutputStream("D:\\develop\\就业班\\xiaomaMusic-V1.0\\WebRoot\\music\\" + uuid + type);
			//IOUtils.copy(input, output);
			//关闭资源
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(input);
			
			
			
			//调用service层上传音乐方法传递数据
			Music music = new Music();
			//设置音乐名
			music.setMusicName(uploadFileName.replace(type,""));
			//设置音乐路径
			String musicPath = "music/" + uuid + type;
			music.setMusicPath(musicPath);
			//调用service层传递对象
			musicService.uploadMusic(music);
			
			return "upLoadMusic";
		  
		} catch (Exception e) {
					
			e.printStackTrace();
			
			return "erro";
		}
		
	}
	
	
	
	/**
	 * 添加个人喜爱歌单(ajax)
	 */

	@Action(value="addLikeMusic",results={
			@Result(name="addLikeMusic",type="stream")
	})
	public String addLikeMusic(){
		try {	
			//获得音乐id
			Long musicId = music.getMusicId();  
			
			if(musicId != 0){
				
				//获得用户id
				User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
				
				Long uid = user.getUid();
			
				//调用service层添加音乐
				String flag = musicService.addLikeMusic(uid,musicId);
				
				PrintUtil.Print(ServletActionContext.getResponse().getWriter(), flag);
				
				return "addLikeMusic";
				
			}
			
			return "erro";
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "erro";
		}
	}
	
	/**
	 * 个人歌单
	 */
	private List<Music> musicList;
	@Action(value="toPersonalMusicUI",results={
			@Result(name="toPersonalMusicUI",type="dispatcher",location="/jsp/music/personalMusic.jsp")
	})
	public String toPersonalMusicUI(){
		
	
		try {
			//获得用户id
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			
			Long uid = user.getUid();
			
			//查询个人歌单所有歌曲
			musicList = musicService.findAllPersonalMusic(uid);
			
			return "toPersonalMusicUI";
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "erro";
		}
	}
	
	/**
	 * 按条件分页查找所有音乐
	 * @return
	 */
	
	
	@Action(value="findAllMusic",results={
			@Result(name="index",type="dispatcher",location="/jsp/index.jsp")
	})
	public String findAllMusic(){
		
		try {
			String musicName = music.getMusicName(); 
			
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Music.class);
			
			if(StringUtils.isNotBlank(musicName)){
				/*//解码编码
				byte []b = musicName.getBytes("ISO8859-1");
				musicName = new String(b,"UTF-8");
				//重新放入
				music.setMusicName(musicName);*/
				//添加查询条件
				detachedCriteria.add(Restrictions.like("musicName","%" + musicName + "%"));
			}

			
			//调用service返回pageBean对象
			PageBean<Music> pageBean = musicService.findMusic(detachedCriteria, curPage);
			
			//把pageBean对象压入值栈顶
			ActionContext.getContext().getValueStack().push(pageBean);
			
			return "index";
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "erro";
		}
		
		
	}
	

	
	/**
	 * 去到播放音乐页面
	 * @return
	 */
	@Action(value="toPlayMusicUI",results={
			@Result(name="toPlayMusicUI",type="dispatcher",location="/jsp/music/play.jsp")
	})
	public String toPlayMusicUI(){
		
		  try {
			  String musicName = music.getMusicName();
			  
			/*  byte[]bytes = musicName.getBytes("ISO8859-1");
			  
			  music.setMusicName(new String(bytes,"UTF-8"));
			  */
			return "toPlayMusicUI";
		
		  } catch (Exception e) {
			
			e.printStackTrace();
			
			return "erro";
		  }
	}
	
	//==============================================
	
	
	
	
	

	public String getUpLoadFlag() {
		return upLoadFlag;
	}



	public List<Music> getMusicList() {
		return musicList;
	}


	public void setMusicList(List<Music> musicList) {
		this.musicList = musicList;
	}


	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public void setUpLoadFlag(String upLoadFlag) {
		this.upLoadFlag = upLoadFlag;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
}
