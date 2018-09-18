package com.deqingbank.cloud.task.feign;

//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="cloud-service-test",fallback=TestServiceFeignClientFallBack.class)
public interface TestServiceFeignClient {
	
	public static final String t = "";

/*	@GetMapping("/zkt/test")
	public void test(); 
*/	
	@GetMapping("/zkt/ip/{ipaddr}")
	public String test(@PathVariable("ipaddr") String ipaddr);
}

@Component
class TestServiceFeignClientFallBack implements TestServiceFeignClient{

	@Override
	public String test(String ipaddr) {
		return "failed!";
	}
	
}