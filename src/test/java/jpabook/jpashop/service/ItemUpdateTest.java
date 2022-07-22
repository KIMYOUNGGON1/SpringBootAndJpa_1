package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        //given
        Book book = em.find(Book.class, 1L);
        //when

        //TT(transactional)
        book.setName("asdfasdf");
        // 변경감지 == dirty checking
        //TT commit 을 하면 JPA가 알아서 변경 내용을 찾아서 update query를 쏜다.

        //then
    }
}
