package pavlo.pro.massage_therapy_api.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pavlo.pro.massage_therapy_api.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
