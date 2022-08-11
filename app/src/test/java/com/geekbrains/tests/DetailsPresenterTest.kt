package com.geekbrains.tests

import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


internal class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter(viewContract)
    }

    @Test
    fun setCounter_Test() {
        val cnt = 5
        presenter.setCounter(cnt)
        assertEquals(presenter.getCounter(), cnt)
    }

    @Test
    fun onIncrement_Test() {
        presenter.onIncrement()
        verify(viewContract, times(1)).setCount(presenter.getCounter())
        assertEquals(presenter.getCounter(), 1)
    }

    @Test
    fun onDecrement_Test() {
        presenter.onDecrement()
        verify(viewContract, times(1)).setCount(presenter.getCounter())
        assertEquals(presenter.getCounter(), -1)
    }

    @Test
    fun onAttach_Test() {
        presenter.onAttach()
        assertEquals(presenter.view, viewContract)
    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        assertNull(presenter.view)
    }
}