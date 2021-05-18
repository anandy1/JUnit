package com.avi.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.avi.data.api.TodoService;
import com.avi.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService toaServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(toaServiceStub);
		List<String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		assertEquals(2, filteredTodos.size());
	}

}
 