package issue.Tracker.issueTracker.service;

import issue.Tracker.issueTracker.data.entity.Issue;
import issue.Tracker.issueTracker.data.entity.IssueStatus;
import issue.Tracker.issueTracker.data.entity.User;
import issue.Tracker.issueTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user) {
        return userRepository.saveAndFlush(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserId(Long id) {
        return userRepository.findById(id).get();
    }

}
