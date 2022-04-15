package issue.Tracker.issueTracker.repository;

import issue.Tracker.issueTracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //void deleteById(Long id);
    @Query
    User findByUsername(String username);
    @Query
    Optional<User> findById(Long id);


}
