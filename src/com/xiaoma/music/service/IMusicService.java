package com.xiaoma.music.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xiaoma.music.entity.Music;
import com.xiaoma.music.entity.PageBean;




/**
 * 音乐service接口
 * @author lenovo
 *
 */
public interface IMusicService {
	/**
	 * 上传音乐
	 * @param music
	 */
	public void uploadMusic(Music music);

	/**
	 * 查找音乐
	 * @param detachedCriteria
	 * @param curPage
	 * @return
	 */
	public PageBean<Music> findMusic(DetachedCriteria detachedCriteria, String curPage);

	/**
	 * 检查音乐是否存在
	 * @param musicname
	 * @return
	 */
	public String musicExist(String musicname);
	
	/**
	 * 添加喜爱音乐
	 * @param userMusic
	 * @return
	 */
	public String addLikeMusic(Long uid,Long musicId);
	
	
	/**
	 * 分页查找个人歌单
	 * @param uid
	 * @param curPage
	 * @return
	 */
	public PageBean<Music> findPersonalMusicByPage(Long uid,String curPage);
	
	/**
	 * 查询个人歌单所有歌曲
	 * @param uid
	 * @return
	 */
	public List<Music> findAllPersonalMusic(Long uid);

}
