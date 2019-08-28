package demo.oauth2.auth.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;
import demo.oauth2.auth.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization,String> {
    public Organization findByName(String name);
}
