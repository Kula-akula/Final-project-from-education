package TamilovKulanbek.FinalProject.Services.Implimitation;

import TamilovKulanbek.FinalProject.Entities.Set;
import TamilovKulanbek.FinalProject.Repositories.SetRepository;
import TamilovKulanbek.FinalProject.Services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {
    private final SetRepository setRepository;

    @Autowired
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public List<Set> getAll() {
        return setRepository.findAll();
    }

    @Override
    public Set getById(Long id) {
        Optional<Set> optionalSet=setRepository.findById(id);
        return optionalSet.get();
    }

    @Override
    public Set save(Set object) {
        return setRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        setRepository.deleteById(id);
    }
}
