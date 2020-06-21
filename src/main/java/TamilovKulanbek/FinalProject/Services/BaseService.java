package TamilovKulanbek.FinalProject.Services;


import java.util.List;

public interface BaseService<T> {
    List<T>  getAll();

    T getById(Long id);

    T save(T object);

    void deleteById(Long id);

}
