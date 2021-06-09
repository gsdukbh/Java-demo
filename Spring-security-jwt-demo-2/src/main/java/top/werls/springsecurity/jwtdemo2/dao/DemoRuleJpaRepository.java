package top.werls.springsecurity.jwtdemo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import top.werls.springsecurity.jwtdemo2.entity.DemoRules;

/**
 * @author leejiawei
 */
@Repository
public interface DemoRuleJpaRepository extends CrudRepository<DemoRules,Long> {
}
