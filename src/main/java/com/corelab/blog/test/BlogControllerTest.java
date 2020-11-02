package com.corelab.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {

	// url : http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}

}
