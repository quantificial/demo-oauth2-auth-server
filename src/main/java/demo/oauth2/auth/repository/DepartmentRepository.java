package demo.oauth2.auth.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    public Department findByName(String name);
}
