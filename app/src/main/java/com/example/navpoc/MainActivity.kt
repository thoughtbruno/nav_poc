package com.example.navpoc

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navpoc.ui.theme.NavpocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavpocTheme {
                val controller = rememberNavController()

                NavHost(controller, startDestination = ScreenStart) {
                    composable<ScreenStart> {
                        ScreenStart {
                            controller.navigate(ScreenOne)
                        }
                    }

                    composable<ScreenOne> {
                        ScreenOne {
                            controller.navigate(ScreenTwo(DataScreenTwo("Title", "Subtitle")))
                        }
                    }

                    composable<ScreenTwo>(
                        typeMap = navTypeSerializable<DataScreenTwo>()
                    ) {
                        val route = it.toRoute<ScreenTwo>()

                        ScreenTwo(route.data.title, route.data.subtitle, {
                            controller.navigate(ScreenTwo(DataScreenTwo("Title 3", "Subtitle 3")))
                        }, {
                            controller.popBackStack()
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenStart(
    onClick: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen Start"
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick) {
            Text("Navigate to screen one")
        }
    }
}

@Composable
fun ScreenOne(
    onClick: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen One"
            )
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick) {
            Text("Navigate to screen two")
        }
    }
}

@Composable
fun ScreenTwo(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    onBack: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = title
            )
            Spacer(Modifier.height(20.dp))

            Text(
                text = subtitle
            )
            Spacer(Modifier.height(20.dp))

            Button(onClick) {
                Text("Navigate front")
            }

            Spacer(Modifier.height(20.dp))

            Button(onBack) {
                Text("Navigate back")
            }
        }
    }
}
