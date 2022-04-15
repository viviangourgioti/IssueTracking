package issue.Tracker.issueTracker.service;

import issue.Tracker.issueTracker.data.entity.Issue;
import issue.Tracker.issueTracker.data.entity.IssueStatus;
import issue.Tracker.issueTracker.data.entity.User;
import issue.Tracker.issueTracker.exception;
import issue.Tracker.issueTracker.repository.IssueRepository;
import issue.Tracker.issueTracker.repository.UserRepository;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private final IssueRepository issueRepository;

    @Autowired
    private final UserRepository userRepository;

    public IssueService(IssueRepository issueRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.userRepository = userRepository;
    }


    public Issue add(Issue issue) {
        return issueRepository.saveAndFlush(issue);
    }

    public Issue edit(Long id,Issue issue) {
        var currentIssue = issueRepository.findById(id).get();
        BeanUtils.copyProperties(issue,currentIssue);
        return issueRepository.saveAndFlush(currentIssue);
    }

    public boolean delete(Long id) {
        if (!issueRepository.existsById(id)) {
            throw new exception("ISSUE_NOT_FOUND");
        } else {
            issueRepository.deleteById(id);
        }
        return true;
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    /*public List<Issue> findAssignTo(User assignTo) {
        return issueRepository.findByAssignTo(assignTo);
    }*/

    public List<Issue> findStatus(IssueStatus status) {
        return issueRepository.findByStatus(status);
    }

    public Issue find(Long id) {
        if (!issueRepository.existsById(id)) {
            throw new exception("ISSUE_NOT_FOUND");
        }
        return issueRepository.findById(id).get();//.get();
    }

    public List<Issue> findByAssignandIssueStatus(Long id, IssueStatus status) {
        //List<Issue> issues= issueRepository.findByAssignTo(id);
        Optional<User> user = userRepository.findById(id);
        return issueRepository.findByAssignToAndStatus(user,status);
    }
}
