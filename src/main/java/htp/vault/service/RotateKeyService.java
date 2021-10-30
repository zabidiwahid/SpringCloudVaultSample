package com.hashicorp.vault.spring.demo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.RawTransitKey;
import org.springframework.vault.support.TransitKeyType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/update")
public class RotateKeyService {
	
  private OrderInfoRepository repository;

  @Inject
  public void setRepository(OrderInfoRepository repository) {
		this.repository = repository;

  }
  
  @PostMapping
  public ResponseEntity update() {
	  
	
    List<OrderInfo> ordersInfo = repository.findAll();
    
    List<OrderInfo> updatedOrder = new ArrayList<OrderInfo>();

    VaultOperations vaultOperations = BeanUtil.getBean(VaultOperations.class);
    
    for(OrderInfo o : ordersInfo){
    	vaultOperations.opsForTransit().rotate("order");
    	String cipherText = vaultOperations.opsForTransit().rewrap("order", o.getCustomerName());
    	o.setCustomerName(cipherText);
    	repository.save(o);
    	updatedOrder.add(o);
    }

	
      return new ResponseEntity<>(updatedOrder.toString(), HttpStatus.OK);
  }
  
  @GetMapping
  public ResponseEntity getKeyName(){
	  VaultOperations vaultOperations = BeanUtil.getBean(VaultOperations.class);
	  RawTransitKey ordersKey = vaultOperations.opsForTransit().exportKey("order", TransitKeyType.ENCRYPTION_KEY);
	  return new ResponseEntity<>(ordersKey, HttpStatus.OK);
  }
}


