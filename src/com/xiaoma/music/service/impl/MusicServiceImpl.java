package com.xiaoma.music.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoma.music.dao.IMusicDao;
import com.xiaoma.music.entity.Music;
import com.xiaoma.music.entity.PageBean;
import com.xiaoma.music.entity.User;
import com.xiaoma.music.service.IMusicService;
/**
 * 音乐service实现类
 * @author lenovo
 *
 */
@Service("musicService")
@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
public class MusicServiceImpl implements IMusicService {

	@Autowired
	private IMusicDao musicDao;
	
	/**
	 * 上传音乐
	 */
	@Override
	public void uploadMusic(Music music) {
		
		musicDao.saveMusic(music);
	}
	
	/**
	 * 查找音乐
	 */
	@Override
	public PageBean<Music> findMusic(DetachedCriteria detachedCriteria,
			String curPage) {
		//当前页的判断
		int curPageNum = 1;
		if(StringUtils.isNotBlank(curPage)){
			curPageNum = Integer.parseInt(curPage);
		}
		
		//查询总记录数
		int totalRecords = musicDao.findTotalRecords(detachedCriteria);
		
		//实例化pageBean对象
		PageBean<Music> pageBean = new PageBean<Music>(curPageNum, totalRecords);
		
		//调用dao查询当前页的数据
		List<Music> musicList = musicDao.findAllMusic(detachedCriteria,pageBean.getStartIndex(),pageBean.getPageSize());

		//设置集合到pageBean对象中
		pageBean.setRecords(musicList);
		
		//返回pageBean对象
		return pageBean;
	}
	
	/**
	 * 检查用户个人歌单音乐是否已存在
	 */
	@Override
	public String musicExist(String musicname) {
		
		return musicDao.findRecordsByMusicName(musicname) > 0 ? "true" : "false";
	}
	
	/**
	 * 添加个人喜爱音乐
	 */

	@Override
	public String addLikeMusic(Long uid, Long musicId) {
		//判断要添加的个人喜爱音乐是否已存在
		int count = musicDao.findAddLikeMusicRecords(uid, musicId);
	
		if( count > 0){
		
			return "false";
		};
		
		User user = musicDao.findUserByUid(uid);
		
		List<Music> musicList = user.getMusicList();
		
		Music music = new Music();
		
		music.setMusicId(musicId);
		
		musicList.add(music);
		
		musicDao.addUserMusic(user);
		
		return "true";
	}
	
	
	
	/**
	 * 分页查找个人歌单
	 * @param curPage
	 * @return
	 */
	@Override
	public PageBean<Music> findPersonalMusicByPage(Long uid,String curPage) {
		
		//当前页的判断
		int curPageNum = 1;
		if(StringUtils.isNotBlank(curPage)){
			curPageNum = Integer.parseInt(curPage);
		}
		
		//查询个人歌单总记录数
		int totalRecords = musicDao.findPerSonalMusicTotalRecords(uid);
	
		//实例化pageBean对象
		PageBean<Music> pageBean = new PageBean<Music>(curPageNum, totalRecords);

		//调用dao查询当前页的数据
		List<Music> musicList = musicDao.findPersonalMusicByPage(uid,pageBean.getStartIndex(),pageBean.getPageSize());
		
		pageBean.setRecords(musicList);
		
		return pageBean;
	}
	
	/**
	 * 查询个人歌单所有歌曲
	 */
	@Override
	public List<Music> findAllPersonalMusic(Long uid) {
		
		return musicDao.findAllPersonalMusic(uid);
		
	}
	
	

}
