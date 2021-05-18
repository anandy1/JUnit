package com.avi.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


import java.util.List;

import javax.management.RuntimeErrorException;

import org.junit.Test;

public class ListTest {

	@Test
	public void letMockListSizeMethodtest() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void letMockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letMockListGet() {
		List listMock = mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenReturn("Avishek");
		assertEquals("Avishek", listMock.get(0));
		assertEquals("Avishek", listMock.get(1));
	}
	
	@Test
	public void letMockListGet_usingBDD() {
		//Given
		List<String> listMock = mock(List.class);
		given(listMock.get(anyInt())).willReturn("Avishek");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, is("Avishek"));
	}
	
	@Test(expected = RuntimeException.class)
	public void letMockListException() {
		List listMock = mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
		
	}

}
