package issue.Tracker.issueTracker.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.*;
import javax.persistence.*;
import javax.validation.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "issue"/*,indexes = { @Index(name = "idx_issue_man",columnList = "created_by"),
        @Index(name = "idx_application_man", columnList = "lastModifiedBy") }*/)
@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Issue extends AbstractEntity implements Serializable {

    //@Column(name = "description")
    //private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    private IssueKind kind;


}