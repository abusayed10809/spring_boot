package com.example.codejava;

import com.example.codejava.User.UserEntity;
import com.example.codejava.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

//    @Test
//    public void testCreateNewUser(){
//        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail("alexander@gmail.com");
//        userEntity.setPassword("123456");
//        userEntity.setFirstName("Alexander");
//        userEntity.setLastName("Hebb");
//
//        UserEntity savedUserEntity = userRepository.save(userEntity);
//        UserEntity existingUserEntity = testEntityManager.find(UserEntity.class, savedUserEntity.getId());
//        assertThat(existingUserEntity.getEmail()).isEqualTo(userEntity.getEmail());
//    }

//    @Test
//    public void testFindUserByEmail() {
//        String email = "alexander@gmail.com";
//        UserEntity userEntity = userRepository.findByEmail(email);
//        assertThat(userEntity).isNotNull();
//    }
}
