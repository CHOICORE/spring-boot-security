package com.corelab.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.corelab.blog.entity.User;
import com.corelab.blog.model.RoleType;
import com.corelab.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;

	@Transactional // 더티 체킹
	@PutMapping("/dummy/user/{id}")
	public User updateuser(@PathVariable long id, @RequestBody User user) {
		System.out.println("USER ID : " + id);
		System.out.println("USER PASSWORD : " + user.getPassword());
		System.out.println("USER EMAIL : " + user.getEmail());

		User data = userRepository.findById(id).orElseGet(() -> {
			return null;
		});
		data.setEmail(user.getEmail());
		data.setPassword(user.getPassword());
		System.out.println(data.toString());
		//userRepository.save(data);
		return null;
	}

	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		List<User> paging = page.getContent();
		return paging;
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable Long id) {

//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("존재하지 않는 유저입니다.");
//			}
//		});
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		});
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("존재하지 않는 유저입니다. " + id);
		});
		return user;
	}

	// url : http://localhost/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(user.toString());
		if (user.getRole() == null) {
			user.setRole(RoleType.USER);
		}
		userRepository.save(user);
		return "회원 가입 완료";
	}

}
