package com.tiket.messagebroker.consumer.springbootkafkaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiket.messagebroker.consumer.springbootkafkaconsumer.config.RedisVO;

@RestController
@RequestMapping("/redis")
@CacheConfig(cacheNames="redisCache")
public class RedisController {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value="/set", method = RequestMethod.POST)
	public RedisVO insert(@RequestBody RedisVO redisVO) {
		mongoTemplate.insert(redisVO, "rediscollection");
		return redisVO;
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	@Cacheable(keyGenerator="keyGenerator")
	public RedisVO get(@PathVariable Integer id) {
		System.out.println("in redis vo get");
		return mongoTemplate.findById(id, RedisVO.class, "rediscollection");
	}
	
	@RequestMapping(value="/update", method = RequestMethod.PUT)
	@CachePut(keyGenerator="keyGenerator")
	public RedisVO Update(@RequestBody RedisVO redisVO) {
		System.out.println("in redis vo update");
		mongoTemplate.save(redisVO, "rediscollection");
		return redisVO;
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.DELETE)
	@CacheEvict(keyGenerator="keyGenerator")
	public String delete(@PathVariable Integer id) {
		System.out.println("in redis vo delete");
		Query q = new Query();
		q.addCriteria(Criteria.where("keyID").is(id));
		mongoTemplate.findAndRemove(q, RedisVO.class, "rediscollection");
		return "delete with id = "+id;
	}
	
	
	
}
