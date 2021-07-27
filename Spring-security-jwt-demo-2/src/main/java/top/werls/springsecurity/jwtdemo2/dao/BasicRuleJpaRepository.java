package top.werls.springsecurity.jwtdemo2.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.werls.springsecurity.jwtdemo2.entity.BasicRules;

/**
 * @author leejiawei
 */
@Repository
public interface BasicRuleJpaRepository extends CrudRepository<BasicRules,Long> {
}
