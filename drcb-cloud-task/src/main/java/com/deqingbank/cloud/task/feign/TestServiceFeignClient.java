package com.deqingbank.cloud.task.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="cloud-service-test")
public interface TestServiceFeignClient {

/*	@GetMapping("/zkt/test")
	public void test(); 
*/	
	@GetMapping("/zkt/{ipaddr}")
	public void test(@PathVariable("ipaddr") String ipaddr);
}


class TestServiceFeignClientFallBack implements TestServiceFeignClient{

	@Override
	public void test(String ipaddr) {
		System.out.println("fallback run!");
	}
	
}