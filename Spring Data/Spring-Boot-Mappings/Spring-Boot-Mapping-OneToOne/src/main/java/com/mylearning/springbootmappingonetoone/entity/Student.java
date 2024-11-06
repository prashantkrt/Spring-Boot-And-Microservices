package com.mylearning.springbootmappingonetoone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Student_Table")
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String about;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL )
    private Laptop laptop;
}

//@OneToOne(cascade = CascadeType.ALL) Propagates all operations (persist, merge, remove, refresh, detach) from the parent to the child.
//@OneToOne(cascade = CascadeType.PERSIST) When you save (persist) the parent entity, the associated child entities are also saved.
//@OneToOne(cascade = CascadeType.MERGE) When you update (merge) the parent entity, the associated child entities are also updated if they are detached entities.
//@OneToOne(cascade = CascadeType.REFRESH) If the parent entity is refreshed, its associated child entities are also reloaded from the database, discarding any unsaved changes.
//@OneToOne(cascade = CascadeType.REMOVE) When you delete (remove) the parent entity, the associated child entities are also deleted.
//@OneToOne(cascade = CascadeType.DETACH) When you detach the parent entity from the persistence context, its associated child entities are also detached, meaning they are no longer managed by the entity manager.
//@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) multiple values
