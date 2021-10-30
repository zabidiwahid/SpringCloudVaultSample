package com.hashicorp.vault.spring.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPIController {

	private OrderRepository repository;

	@Inject
	public void setRepository(OrderRepository repository) {
		this.repository = repository;

	}
//api post dia akan save pkai jpa 
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addOrder(@RequestBody Order order) {
		return new ResponseEntity<>(repository.save(order), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Order>> getAllOrders() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteAllOrders() {
		VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
		repository.deleteAll();
		vaultOps.opsForTransit().deleteKey("order");;

	}

}
