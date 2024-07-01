package com.example.section1.scratch;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AssertTest {
    class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    class Account {
        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("balance only " + balance);
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    class Customer {
        List<Account> accounts = new ArrayList<>();

        void add(Account account) {
            accounts.add(account);
        }

        Iterator<Account> getAccounts() {
            return accounts.iterator();
        }
    }

    private Account account;

    @BeforeEach
    public void createAccount() {
        account = new Account("an account name");
    }

    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        // 일반적인 검증
        assertTrue(account.getBalance() > initialBalance);

        // 기댓값을 명시적으로 지정하는 것이 좋음
        // 햄크레스트 단언
        assertThat(account.getBalance()).isEqualTo(100);
    }

    @Test
    public void equalToTest1() {
        assertThat(new String[] {"a", "b", "c"}).isEqualTo(new String[] {"a", "b"});
    }

    @Test
    public void equalToTest2() {
        assertThat(new String[] {"a"}).isEqualTo(new String[] {"a"});
    }

    @Test
    public void notTest() {
        assertThat(new Account("my big fat acct")).isNotEqualTo("plunderings");
    }

    @Test
    public void notNullTest() {
        // not null 을 자주 검사하는 것은 설계 문제 대부분의 경우 불필요함
        assertThat(account.getName()).isNotNull();

        assertThat(account.getName()).isEqualTo("an account name");
    }

    @Test
    public void isCloseToTest() {
        // 소수점 오류
        // assertThat(2.32 * 3).isEqualTo(6.96);
        assertThat(2.32 * 3).isCloseTo(6.96, Offset.offset(0.0005));
    }

//    @Test
//    public void testWithWorthlessAssertionComment() {
//        account.deposit(50);
//        assertThat(account.getBalance()).isEqualTo(100);
//    }

}
