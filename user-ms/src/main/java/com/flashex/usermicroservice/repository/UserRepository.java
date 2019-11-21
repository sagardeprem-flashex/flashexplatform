package com.flashex.usermicroservice.repository;

import com.flashex.usermicroservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
//    public List<User> findByUserNameContaining(String userName);
    public Optional<User> findByUserNameContaining(String userName);
    public Optional<User> deleteByUserNameContaining(String userName);
}
