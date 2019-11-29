package com.xiaoma.music.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 用户实体类
 */
@Entity
@Table(name="user")
public class User implements Serializable{
	
	@Id
	@Column(name="uid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uid;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	//与音乐的关系：多对多
	@ManyToMany(targetEntity=Music.class)
	@JoinTable(name="user_music",joinColumns=@JoinColumn(name="uid"),inverseJoinColumns=@JoinColumn(name="music_id"))

	private List<Music> musicList = new ArrayList<Music>();
	
	public List<Music> getMusicList() {
		return musicList;
	}
	public void setMusicList(List<Music> musicList) {
		this.musicList = musicList;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
