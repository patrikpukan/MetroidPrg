package tech.pukan.metroidprg.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pukan.metroidprg.core.designsystem.theme.MetroidPrgTheme
import tech.pukan.metroidprg.feature.timetables.HomeScreen

@Composable
fun MetroidPrgApp() {
    MetroidPrgTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen()
        }
    }
}
