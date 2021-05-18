package com.avi.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.avi.data.api.TodoService;


public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		when(todoServiceMock.retrieveTodos("dummy")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		//Given

		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		//When
		List<String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		//Then
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void deleteTodosNotRelatedToSpring_usingBDD() {
		//Given

		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		//Then
		verify(todoServiceMock).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance"); //BDD Style
		
		verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
		then(todoServiceMock).should(atLeast(1)).deleteTodo("Learn to Dance");//BDD Style
		
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");//BDD Style
	}
	
	@Test
	public void deleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		//Define Argument Captor
		
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given

		TodoService todoServiceMock = mock(TodoService.class);
		List<java.lang.String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		//Then
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture()); //BDD Style
		
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	@Test
	public void deleteTodosNotRelatedToSpring_usingBDD_multipleArgumentCapture() {
		
		//Define Argument Captor
		
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given

		TodoService todoServiceMock = mock(TodoService.class);
		List<java.lang.String> todos = Arrays.asList("Learn Math", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl = 
				new TodoBusinessImpl(todoServiceMock);
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		//Then
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture()); //BDD Style
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}

}