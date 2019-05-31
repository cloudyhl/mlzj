package com.mlzj.mongodb.behavior.dao;

import com.mlzj.mongodb.behavior.SimpleUserBehavior;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yhl
 * @date 2019/5/24
 */
public interface BehaviorDao extends MongoRepository<SimpleUserBehavior, Long> {
}
