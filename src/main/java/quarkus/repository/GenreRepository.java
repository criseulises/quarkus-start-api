package quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.entities.Genre;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {

    public PanacheQuery<Genre> findPage(int pageNumber){
        Page page = new Page(pageNumber -1, 5);
        var query = findAll(Sort.descending("createdAt"));
        query.page(page);
        return query;
    }

}
