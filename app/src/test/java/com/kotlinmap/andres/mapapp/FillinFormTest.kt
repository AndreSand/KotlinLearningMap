package com.kotlinmap.andres.mapapp

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by andres on 10/18/17.
 */

class FillinFormTest {
//    var db = mock(FillinForm::class.java)
//    private val db: FillinForm = mock(FillinForm::class.java)
@Test
@Throws(Exception::class)
fun testMockedHelloWorld() {
    val mockedUtils = mock(FillinForm::class.java)
    `when`(mockedUtils.getHelloWorld()).thenReturn("Hello mocked world!")
    val helloWorld = mockedUtils.getHelloWorld()
    assertEquals("Hello mocked world!", helloWorld)
}
}