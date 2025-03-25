package org.study.studyblogapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestController {

	@GetMapping("/1")
	public String test1(){
		return "jo";
	}
}
