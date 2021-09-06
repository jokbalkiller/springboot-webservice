package com.jong.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
보통 DAO라고 불리는 DB 접근자
JPA에서는 Repository라고 부르며 인터페이스로 생성
extends JpaRepository<Entity 타입, PK 타입>을 상속하면 기본적인 CRUD 메서드가 자동으로 생성된다.
주의점은 둘은 아무 밀접한 관계임으로 유지 보수 및 성능을 위해 Entity와 같은 폴더 안에 위치해야한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
