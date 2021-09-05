package com.jong.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 모든 필드에 대해서 get() 메소드 자동 생성
@RequiredArgsConstructor // 선언된 모든 final 변수에 대해서 생성자를 생성해준다.
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
