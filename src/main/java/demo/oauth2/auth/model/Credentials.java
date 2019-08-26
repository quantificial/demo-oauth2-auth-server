package demo.oauth2.auth.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="credentials")
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Credentials implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Version
    private Integer version;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
    
    private boolean accountExpired;

    private boolean credentialsExpired;
    
    private boolean accountLocked;
    
    private int loginFailureCount;
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "credentials_authorities",
    	joinColumns = @JoinColumn(name = "credentials_id", referencedColumnName = "id"),
    	inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))    
    private List<Authority> authorities;

    private boolean enabled;


}
