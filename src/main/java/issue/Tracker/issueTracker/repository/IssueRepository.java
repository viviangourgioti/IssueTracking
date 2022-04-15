package issue.Tracker.issueTracker.repository;

import issue.Tracker.issueTracker.data.entity.Issue;
import issue.Tracker.issueTracker.data.entity.IssueStatus;
import issue.Tracker.issueTracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue,Long> {

    @Query
    List<Issue> findByAssignTo(Long id);

    //@Query
    //void deleteById(Long id);

    @Query
    List<Issue> findByStatus(IssueStatus status);

    @Query("select a from Issue a where a.assignTo.id =  :#{#user.id} and a.status = :#{#status}")
    List<Issue> findByAssignToAndStatus(@Param("user") Optional<User> user, @Param("status") IssueStatus status);

    //@Query
    //Optional<Issue> findById(Long id);
    // μήπως να βάλω id αντι για ολο το user
}
