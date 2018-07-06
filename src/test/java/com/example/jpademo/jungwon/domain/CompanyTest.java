package com.example.jpademo.jungwon.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback(true)
public class CompanyTest {
    public static final Logger log =  LoggerFactory.getLogger(CompanyTest.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Commit
    @Test
    public void test_01() {
        Account account = new Account();
        List<Company> companies = new ArrayList<>();

        IntStream.range(0,10).forEach(i -> {
            companies.add(new Company("테스트 컴퍼니 " + i));
        });
        account.setCompany(companies);
        accountRepository.save(account);
        Account savedAccount = accountRepository.getOne(account.getId());
        savedAccount.getCompany().stream().forEach(c -> {
            System.out.println(c);
        });
    }

    @Test
    public void test_02() {
        accountRepository.findById(4L).get().getCompany().stream().forEach(c -> {
            System.out.println(c);
        });

    }
}