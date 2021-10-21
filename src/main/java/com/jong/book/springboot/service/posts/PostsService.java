package com.jong.book.springboot.service.posts;

import com.jong.book.springboot.domain.posts.Posts;
import com.jong.book.springboot.domain.posts.PostsRepository;
import com.jong.book.springboot.web.dto.PostsListResponseDto;
import com.jong.book.springboot.web.dto.PostsResponseDto;
import com.jong.book.springboot.web.dto.PostsSaveRequestDto;
import com.jong.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    update 진행 시 DB에 쿼리를 날리는 부분이 없는데 그 이유는 JPA의 영속성 컨텍스트 떄문이다.
    영속성 컨텍스트란 엔티티를 영구 저장하는 환경으로 일종의 논리적 개념이며
    JPA의 핵심은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐이다.
    트랜잭션 안에서 데이터베이스를 가져오면 영속성컨텍스트가유지된 상태이다
    이때 해당 데이터 값을 변경하면 트랙잭션이 끝나는시점에서 해당 테이블에 변경분을 반영한다.
    즉 엔티티 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다 -> 더티 체킹(dirty checking)
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
    
    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회 기능만 가능하기 때문에 조회 속도가 개선된다. 조회 메소드에만 사용 권장
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
