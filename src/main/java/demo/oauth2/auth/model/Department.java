package demo.oauth2.auth.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude="credentialsSet")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Department {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    
    
    //@OneToMany( mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @OneToMany(targetEntity = Credentials.class, mappedBy = "department", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<Credentials> credentialsSet = new HashSet<>(0);
    
    
    //private Set<RoomEntity> rooms = new HashSet<>(0);
    
}
