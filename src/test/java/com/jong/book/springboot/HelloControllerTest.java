package com.jong.book.springboot;

import com.jong.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
컨트롤러를 구현한 후 직접 서버에서 실행하기 전 테스트 코드를 무조건 진행하여 확인해보는 습관을 기르는것이 중요하다.
 */


@RunWith(SpringRunner.class)
/*
@RunWith(SpringRunner.class)?
테스트를 진행 할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행.
여기서는 SpringRunner.class 라는 스프링 실행자를 사용.
스프링부트 테스트와 JUnit 연결자
 */
@WebMvcTest(controllers = HelloController.class) //HelloController 컨트롤러 테스트 진행

public class HelloControllerTest {
    @Autowired // Spring 이 관리하는 Bean 주입
    
    /*
    private MockMvc mvc?
    웹 API 테스트 할때 사용
    스프링 MVC 테스트의 시작
    다양한 REST API 테스트 가능
     */
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        
        mvc.perform(get("/hello")) // /hello 주소로 get 요청 테스트 진행
                .andExpect(status().isOk()) // response status가 200이고 
                .andExpect(content().string(hello)); // 해당 내용이 "hello" 라면 성공
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        /*
        * /hello/dto 에 name과 param을 파라미터로 요청한다
        * JSON응답값이 해당 값과 같으면 성공
        * */
        mvc.perform(get("/hello/dto").param("name", name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is(name)))
                .andExpect((ResultMatcher) jsonPath("$.amount", is(amount)));
    }
}
