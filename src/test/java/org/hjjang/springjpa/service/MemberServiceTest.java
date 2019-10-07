package org.hjjang.springjpa.service;

import org.hjjang.springjpa.domain.Member;
import org.hjjang.springjpa.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false)
    public void join() throws Exception{
        //givin
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.joib(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
        //
    }

    @Test(expected = IllegalStateException.class)
    public void dupMemberThrow() throws Exception{
        //givin
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.joib(member1);
        memberService.joib(member2);

//        try {
//
//        }catch (IllegalStateException e){
//            return;
//        }


        //then
        fail("Exception throw!");
        //
    }
}