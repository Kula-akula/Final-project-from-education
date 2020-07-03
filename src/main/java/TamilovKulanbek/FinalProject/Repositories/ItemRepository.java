package TamilovKulanbek.FinalProject.Repositories;

import TamilovKulanbek.FinalProject.Entities.Category;
import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long > {
    List<Item> findByCategory_Id(Long categoryId);
    List<Item> findAllByCategory_IdAndStatus(Long id, Status status);
    List<Item> findAllByStatus(Status status);

}
