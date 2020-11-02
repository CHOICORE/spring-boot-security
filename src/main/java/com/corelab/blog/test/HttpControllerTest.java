package com.corelab.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	@GetMapping("/http/get")
	public String getTest() {
		return "Request Method : GET";
	}

	@GetMapping("/http/post")
	public String postTest() {
		return "Request Method : PORT";
	}

	@GetMapping("/http/put")
	public String putTest() {
		return "Request Method : PUT";
	}

	@GetMapping("/http/delete")
	public String deleteTest() {
		return "Request Method : DELETE";
	}
}
