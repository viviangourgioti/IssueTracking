package issue.Tracker.issueTracker.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@MappedSuperclass
@Getter
@Setter
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "issue_id")
    private Long id;

    @Column(name="description")
    private String description;

    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "lastModifiedDate")
    @LastModifiedDate
    private Date lastModifiedDate;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "user_id")
    //@Column(name = "createdBy_id")
    private User createdBy;

    //    @JsonBackReference(value = "assignTo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "assignTo", referencedColumnName = "user_id")
    //@Column(name = "assignTo_id")
    private User assignTo;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "lastModifiedBy", referencedColumnName = "user_id")
    @ManyToOne
    //@Column(name = "lastModifiedBy_id")
    private User lastModifiedBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCreatedDate(), that.getCreatedDate()) && Objects.equals(getLastModifiedDate(), that.getLastModifiedDate()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getAssignTo(), that.getAssignTo()) && Objects.equals(getLastModifiedBy(), that.getLastModifiedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getCreatedDate(), getLastModifiedDate(), getCreatedBy(), getAssignTo(), getLastModifiedBy());
    }
}
