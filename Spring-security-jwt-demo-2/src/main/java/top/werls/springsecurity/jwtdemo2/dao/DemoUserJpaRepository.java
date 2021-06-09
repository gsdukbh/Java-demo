package top.werls.springsecurity.jwtdemo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.werls.springsecurity.jwtdemo2.entity.DemoUser;
/**
 * @author leejiawei
 */
@Repository
public interface DemoUserJpaRepository extends CrudRepository<DemoUser,Long> {

}
