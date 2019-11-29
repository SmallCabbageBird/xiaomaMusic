package com.xiaoma.music.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xiaoma.music.entity.Music;
import com.xiaoma.music.entity.User;


/**
 * 音乐dao接口
 * @author lenovo
 *
 */
public interface IMusicDao {

	/**
	 * 查找符合条件的总记录数
	 * @param detachedCriteria
	 * @return
	 */
	public int findTotalRecords(DetachedCriteria detachedCriteria);
	
	/**
	 * 查询所有音乐
	 * @param detachedCriteria
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public  List<Music> findAllMusic(DetachedCriteria detachedCriteria, int startIndex,
			int pageSize);
	

	
	/**
	 * 通过uid查找用户
	 * @param uid
	 * @return
	 */
	public User findUserByUid(Long uid);
	
	/**
	 * 通过音乐名查找记录数
	 * @param musicname
	 */
	public int findRecordsByMusicName(String musicname);
	
	/**
	 * 保存音乐
	 * @param music
	 */
	public void saveMusic(Music music);
	
	/**
	 * 查找要添加的音乐在数据库中的记录数
	 * @param uid
	 * @param musicId
	 * @return
	 */
	public int findAddLikeMusicRecords(Long uid, Long musicId);
	
	
	/**
	 * 添加中间表数据
	 * @param uid
	 * @param musicId
	 */
	public void addUserMusic(User user);
	
	/**
	 * 查找个人歌单总记录数
	 * @param uid
	 * @return
	 */
	public int findPerSonalMusicTotalRecords(Long uid);
	
	/**
	 * 分页查询个人歌单数据集合
	 * @param uid
	 * @param curPage
	 * @param totalRecords
	 * @return
	 */
	public List<Music> findPersonalMusicByPage(Long uid, int startIndex,
			int pageSize);

	
	
	/**
	 * 查询个人歌单所有歌曲
	 * @param uid
	 * @return
	 */
	public List<Music> findAllPersonalMusic(Long uid);
}
