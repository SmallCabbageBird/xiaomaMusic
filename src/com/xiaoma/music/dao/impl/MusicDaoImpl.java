package com.xiaoma.music.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.xiaoma.music.dao.IMusicDao;
import com.xiaoma.music.entity.Music;
import com.xiaoma.music.entity.User;


/**
 * 音乐dao实现类
 * @author lenovo
 *
 */
@Repository("musicDao")
public class MusicDaoImpl implements IMusicDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * 查询满足要求的总记录数
	 */
	@Override
	public int findTotalRecords(DetachedCriteria detachedCriteria) {
		//设置统计查询 条件
		detachedCriteria.setProjection(Projections.count("musicId"));
		//查询
		List<Long> count = (List<Long>) hibernateTemplate.findByCriteria(detachedCriteria);
		//判断返回值
		return count.isEmpty() ? 0 : count.get(0).intValue();
	}
	
	
	/**
	 * 查询所有音乐
	 */
	@Override
	public List<Music> findAllMusic(DetachedCriteria detachedCriteria, int startIndex,
			int pageSize) {
		//清除统计查询条件
		detachedCriteria.setProjection(null);
		//分页查询
		return (List<Music>) hibernateTemplate.findByCriteria(detachedCriteria,startIndex,pageSize);
		
	}

	


	/**
	 * 保存中间表数据
	 */
	@Override
	public void addUserMusic(User user) {
		
		hibernateTemplate.save(user);
	}


	/**
	 * 通过uid查找用户
	 */
	@Override
	public User findUserByUid(Long uid) {
		
		return hibernateTemplate.get(User.class,uid);
	}

	/**
	 * 通过音乐名查找记录数
	 * @return 
	 */
	@Override
	public int findRecordsByMusicName(String musicname) {
		
		String hql = "select count(music_name) from Music where musicName = ?";
		
		List<Long> count = (List<Long>) hibernateTemplate.find(hql,musicname);
		
		return count.isEmpty() ?  0 : count.get(0).intValue();
	}

	/**
	 * 保存音乐
	 */
	@Override
	public void saveMusic(Music music) {
		
		hibernateTemplate.save(music);
		
	}

	/**
	 * 查找要添加的音乐在数据库中的记录数
	 * @param uid
	 * @param musicId
	 * @return
	 */
	@Override
	public int findAddLikeMusicRecords(Long uid, Long musicId) {
		

		String hql = "select count(m) from User u inner join u.musicList m where u.uid = ? and m.musicId = ?";
		
		List<Long> count = (List<Long>) hibernateTemplate.find(hql, uid,musicId);
	
		return count.isEmpty() ? 0 : count.get(0).intValue();
	}
	
	/**
	 * 查找个人歌单总记录数
	 * @param uid
	 * @return
	 */
	public int findPerSonalMusicTotalRecords(Long uid){
		
		String hql = "select count(m) from User u inner join u.musicList m where u.uid = ?";
		
		 List<Long> count = (List<Long>) hibernateTemplate.find(hql,uid);
	
		 return count.isEmpty() ? 0 : count.get(0).intValue();
	}

	/**
	 * 分页查询个人歌单数据集合
	 * @param uid
	 * @param curPage
	 * @param totalRecords
	 * @return
	 */
	@Override
	public List<Music> findPersonalMusicByPage(Long uid, int startIndex,
			int pageSize) {
		
		String hql = "select m from User u inner join u.musicList m where u.uid = ?";
		
		hibernateTemplate.setFetchSize(startIndex);
		
		hibernateTemplate.setMaxResults(pageSize);
		
		return (List<Music>) hibernateTemplate.find(hql,uid);
	}

	/**
	 * 查询个人歌单所有歌曲
	 * @param uid
	 * @return
	 */
	@Override
	public List<Music> findAllPersonalMusic(Long uid) {
		
		String hql = "select m from User u inner join u.musicList m where u.uid = ?";
		
		return (List<Music>) hibernateTemplate.find(hql, uid);
	}

}
