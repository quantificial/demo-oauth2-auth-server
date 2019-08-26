package demo.oauth2.auth.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.oauth2.auth.model.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials,String> {
    public Credentials findByName(String name);
}
