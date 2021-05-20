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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.avi.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectionMockTest {

	@Rule // We can use @Rule instead of @RunWith. In this case we can defile/use more that one JUnit runner
	public MockitoRule MockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		when(todoServiceMock.retrieveTodos("dummy")).thenReturn(todos);
		
		List<String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {
		//Given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		//When
		List<String> filteredTodos = 
				todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		//Then
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void deleteTodosNotRelatedToSpring_usingBDD() {
		//Given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
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
		
		//Given

		List<java.lang.String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		//When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		//Then
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture()); //BDD Style
		
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	@Test
	public void deleteTodosNotRelatedToSpring_usingBDD_multipleArgumentCapture() {
		
		//Given

		List<java.lang.String> todos = Arrays.asList("Learn Math", "Learn Spring", 
				"Learn to Dance");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
		
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		//Then
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture()); //BDD Style
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}

}