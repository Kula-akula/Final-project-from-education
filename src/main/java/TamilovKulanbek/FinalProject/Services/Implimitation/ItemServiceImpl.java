package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Item;
import TamilovKulanbek.FinalProject.Repositories.ItemRepository;
import TamilovKulanbek.FinalProject.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item getById(Long id) {
        Optional<Item> optionalItem=itemRepository.findById(id);
        return optionalItem.get();
    }

    @Override
    public Item save(Item object) {
        return itemRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);

    }
}
