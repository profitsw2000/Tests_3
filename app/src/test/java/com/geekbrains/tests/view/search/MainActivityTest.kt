package com.geekbrains.tests.view.search

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.details.DetailsActivity
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    //проверка, что активити создаётся
    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            assertNotNull(it)
        }
    }

    //проверка на состояние активити
    @Test
    fun activity_isResumed() {
        assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    //проверка прогресс бара
    @Test
    fun progressBar_isInvisible() {
        scenario.onActivity {
            val progressBar = it.findViewById<ProgressBar>(com.geekbrains.tests.R.id.progressBar)
            assertEquals(View.GONE, progressBar.visibility)
        }
    }

    //проверка, что EditText видим
    @Test
    fun editText_isVisible() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(com.geekbrains.tests.R.id.searchEditText)
            assertEquals(View.VISIBLE, editText.visibility)
        }
    }

    //проверка, что ввод текста у EditText работает корректно
    @Test
    fun editText_isTypedText() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(com.geekbrains.tests.R.id.searchEditText)
            editText.setText("test", TextView.BufferType.EDITABLE)
            assertEquals("test", editText.text.toString())
        }
    }

    //проверка правильности передачи введённого текста
    @Test
    fun editText_getRightText() {
        scenario.onActivity {
            val text = "test"
            val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
            val editText = it.findViewById<EditText>(com.geekbrains.tests.R.id.searchEditText)
            editText.setText("test", TextView.BufferType.EDITABLE)
            editText.onEditorAction(EditorInfo.IME_ACTION_NEXT)
            toast.show()
            assertEquals(ShadowToast.getTextOfLatestToast(),text)
        }
    }

    //проверка, что кнопка To Details видима
    @Test
    fun buttonToDetails_isVisible() {
        scenario.onActivity {
            val button = it.findViewById<Button>(com.geekbrains.tests.R.id.toDetailsActivityButton)
            assertEquals(View.VISIBLE, button.visibility)
        }
    }

    //проверка, что кнопка To Details запускает активити DetailsActivity
    @Test
    fun buttonToDetails_isWorking() {
        scenario.onActivity {
            val button = it.findViewById<Button>(com.geekbrains.tests.R.id.toDetailsActivityButton)
            button.performClick()
            val expectedIntent = Intent(context, DetailsActivity::class.java)
            val actual: Intent = shadowOf(RuntimeEnvironment.application).getNextStartedActivity()
            assertEquals(expectedIntent.component, actual.component)
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}