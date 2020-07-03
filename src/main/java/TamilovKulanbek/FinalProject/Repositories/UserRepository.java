package TamilovKulanbek.FinalProject.Repositories;

import TamilovKulanbek.FinalProject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAndIsActive(String email, Integer isActive);

    User findByEmail(String email);



    List<User> findByFirstNameAndLastName(String lastName, String firsName);
}
