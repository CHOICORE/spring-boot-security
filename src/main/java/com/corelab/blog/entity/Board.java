package com.corelab.blog.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob
	private String content;

	@ColumnDefault("0")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user; // FK를 가지고 있는 오브젝트를 선언하고 어노테이션 부착

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "board") // FK키 아님(연관관계의 주인 X) Reply Entity에 선언된 Board Entity의 ID가 FK
	private List<Reply> reply;

	@CreationTimestamp
	private Timestamp createDate;

	@UpdateTimestamp
	private Timestamp updateDate;

}
