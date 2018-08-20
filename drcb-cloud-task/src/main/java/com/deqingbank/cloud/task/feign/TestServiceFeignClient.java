package com.deqingbank.cloud.task.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cloud-service-test")
public interface TestServiceFeignClient {

/*	@GetMapping("/zkt/test")
	public void test();
*/	
	@GetMapping("/zkt/{ipaddr}")
	public void test(@PathVariable("ipaddr") String ipaddr);
}
