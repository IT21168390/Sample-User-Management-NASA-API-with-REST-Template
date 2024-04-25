package edu.AF.UserManagement.repositories;

import edu.AF.UserManagement.models.User;
import edu.AF.UserManagement.models.UserRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByEmail(String email);

    User findFirstByEmail(String email);

    boolean existsByEmail(String email);
    User findByUserRole(UserRoles role);
    List<User> findAllByUserRole(UserRoles role);
}
