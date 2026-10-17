package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.AppLanguage
import com.example.data.repository.WarThunderRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("War Thunder Intel", appName)
  }

  @Test
  fun `repository loads initial intelligence data and handles bilingual language toggle`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val repo = WarThunderRepository(context)

    // Verify initial vehicles loaded
    assertTrue(repo.vehicles.value.isNotEmpty())
    val gripen = repo.vehicles.value.find { it.id == "jas39c_gripen" }
    assertTrue(gripen != null)
    assertEquals("JAS39C Gripen", gripen?.name)

    // Verify language toggle
    repo.setLanguage(AppLanguage.AR)
    assertEquals(AppLanguage.AR, repo.currentLanguage.value)

    repo.setLanguage(AppLanguage.EN)
    assertEquals(AppLanguage.EN, repo.currentLanguage.value)
  }
}
