package com.springDemo.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class UsersApplicationTests {

	@Test
	void testCreateUser() {
		User user = new User("John", "Doe", "Male", 30, "johndoe@example.com", "123-456-7890");
		assertEquals("John", user.getFirstName());
		assertEquals("Doe", user.getLastName());
		assertEquals("Male", user.getGender());
		assertEquals(30, user.getAge());
		assertEquals("johndoe@example.com", user.getEmailAddress());
		assertEquals("123-456-7890", user.getPhoneNumbers());
	}

	@Test
	void testSetFirstName() {
		User user = new User();
		user.setFirstName("Jane");
		assertEquals("Jane", user.getFirstName());
	}

	@Test
	void testSetLastName() {
		User user = new User();
		user.setLastName("Doe");
		assertEquals("Doe", user.getLastName());
	}

	@Test
	void testSetGender() {
		User user = new User();
		user.setGender("Female");
		assertEquals("Female", user.getGender());
	}

	@Test
	void testSetAge() {
		User user = new User();
		user.setAge(20);
		assertEquals(20, user.getAge());
	}

	@Test
	void testSetEmailAddress() {
		User user = new User();
		user.setEmailAddress("janedoe@example.com");
		assertEquals("janedoe@example.com", user.getEmailAddress());
	}

	@Test
	void testSetPhoneNumbers() {
		User user = new User();
		user.setPhoneNumbers("123-456-7890");
		assertEquals("123-456-7890", user.getPhoneNumbers());
	}

	// controller tests
	@Autowired
	private UserController userController;

	@Test
	public void testListUsers() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setGender("Male");
		user.setAge(30);
		user.setEmailAddress("john.doe@gmail.com");
		user.setPhoneNumbers(Arrays.toString(new String[]{"123-456-7890", "987-654-3210"}));
		User createdUser = userController.createUser(user);
		List<User> users = userController.listUsers();
		assert(users.size() > 0);
	}

	@Test
	public void testGetUser() {
		User user = userController.getUser(1L);
		assert(user != null);
	}

	@Test
	public void testCreateUserClass() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setGender("Male");
		user.setAge(30);
		user.setEmailAddress("john.doe@gmail.com");
		user.setPhoneNumbers(Arrays.toString(new String[]{"123-456-7890", "987-654-3210"}));
		User createdUser = userController.createUser(user);
		assert(createdUser != null);
	}

	@Test
	public void testUpdateUser() {
		// creating user to test update on
		User user_ = new User();
		user_.setFirstName("John");
		user_.setLastName("Doe");
		user_.setGender("Male");
		user_.setAge(30);
		user_.setEmailAddress("john.doe@gmail.com");
		user_.setPhoneNumbers(Arrays.toString(new String[]{"123-456-7890", "987-654-3210"}));
		User createdUser = userController.createUser(user_);

		User user = userController.getUser(1L);
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmailAddress("jane.doe@gmail.com");
		User updatedUser = userController.updateUser(1L, user);
		assert(updatedUser != null);
		assert(updatedUser.getFirstName().equals("Jane"));
	}

	@Test
	public void testDeleteUser() {
		userController.deleteUser(1L);
		User user = userController.getUser(1L);
		assert(user == null);
	}
}