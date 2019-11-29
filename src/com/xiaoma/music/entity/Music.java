package com.xiaoma.music.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 音乐实体类
 * @author lenovo
 *
 */
@Entity
@Table(name="music")
public class Music implements Serializable{
	
	@Id
	@Column(name="music_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long musicId;
	
	@Column(name="music_name")
	private String musicName;
	
	@Column(name="music_path")
	private String musicPath;

	//与用户的关系：多对多
	@ManyToMany(targetEntity=User.class)
	@JoinTable(name="user_music",joinColumns=@JoinColumn(name="music_id"),inverseJoinColumns=@JoinColumn(name="uid"))
	
	private List<User> userList ;
	
	
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Long getMusicId() {
		return musicId;
	}
	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getMusicPath() {
		return musicPath;
	}
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	@Override
	public String toString() {
		return "Music [musicId=" + musicId + ", musicName=" + musicName
				+ ", musicPath=" + musicPath + "]";
	}
	
	
	
	
	
}
