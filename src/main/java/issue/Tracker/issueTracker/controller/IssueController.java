package issue.Tracker.issueTracker.controller;

import issue.Tracker.issueTracker.data.entity.Issue;
import issue.Tracker.issueTracker.data.entity.IssueStatus;
import issue.Tracker.issueTracker.service.IssueService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addIssue(@Valid @RequestBody Issue issue){

        var savedIssue = issueService.add(issue);

        //Get the location of the new resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedIssue.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeIssue(@RequestParam("issue") Long id) {
        //Issue issue = issueService.find(id);
        issueService.delete(id);
        return "redirct:/list";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIssue(@PathVariable Long id, @Valid @RequestBody Issue updatedIssue)
    {
        issueService.edit(id,updatedIssue);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable Long id){
        var issue = issueService.find(id);
        return ResponseEntity.ok(issue);
    }

    @GetMapping("")
    public ResponseEntity<List<Issue>> getIssues(){
        List<Issue> issues = issueService.findAll();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/issue/{id}/filter/{status}")
    public ResponseEntity<List<Issue>> getFilteredIssues(@PathVariable Long id, @PathVariable String status){
        var issues = issueService.findByAssignandIssueStatus(id, IssueStatus.valueOf(status));
        return ResponseEntity.ok(issues);
    }

}
