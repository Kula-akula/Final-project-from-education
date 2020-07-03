package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.BlackList;
import TamilovKulanbek.FinalProject.Repositories.BlackListRepository;
import TamilovKulanbek.FinalProject.Services.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlackListServiceImpl implements BlackListService {
    @Autowired
    private BlackListRepository blackListRepository;

    @Override
    public List<BlackList> getAll() {
        return blackListRepository.findAll();
    }

    @Override
    public BlackList getById(Long id) {
        Optional<BlackList> optionalBlackList=blackListRepository.findById(id);
        return optionalBlackList.orElse(null);
    }

    @Override
    public BlackList save(BlackList object) {
        return blackListRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        blackListRepository.deleteById(id);
    }
}
