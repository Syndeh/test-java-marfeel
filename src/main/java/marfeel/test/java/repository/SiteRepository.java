package marfeel.test.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import marfeel.test.java.model.Site;

@Repository
@Component
public interface SiteRepository extends JpaRepository<Site, Long> {

}
