package com.jong.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
1. 어노테이션 순서도 중요도 순으로 클레스와 가까이 붙어야 유지 보수가 편하다
2. Entity 클레스는 Setter를 선언하지 않는다. 굳이 필요하다면 해당 목적과 의도를 나타낼 수 있는 메서드를 생성한다.
 */
@Getter // 멤버 get() 메서드 자동 추가
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크가 될 클레스임을 나타낸다.
public class Posts {
    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 길이 500으로 제한
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;


    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
