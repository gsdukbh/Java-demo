package top.werls.springsecurity.jwtdemo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.werls.springsecurity.jwtdemo2.entity.BasicUser;
/**
 * @author leejiawei
 */
@Repository
public interface BasicUserJpaRepository extends CrudRepository<BasicUser,Long> {

}
