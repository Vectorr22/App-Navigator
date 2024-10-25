package com.vector.appnavigator.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loto.viewModels.LoteriaViewModel
import com.example.loto.views.LoteriaView
import com.vector.appnavigator.ui.components.DiscountScreen
import com.vector.appnavigator.ui.components.DoggyScreen
import com.vector.appnavigator.ui.components.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    corroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Sites", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "Home screen")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("HomeScreen")
                        corroutineScope.launch { drawerState.close() }
                    }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "Doggy Age Converter")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("DoggyAgeConverter")
                        corroutineScope.launch { drawerState.close() }
                    }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "Discounts")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("Discount")
                        corroutineScope.launch { drawerState.close() }
                    }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "Lottery")
                    },
                    selected = false,
                    onClick = {
                        navController.navigate("Lottery")
                        corroutineScope.launch { drawerState.close() }
                    }
                )
            }

        }
    ) {
        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable("HomeScreen") {
                HomeScreen()
            }
            composable("DoggyAgeConverter") {
                DoggyScreen()
            }
            composable("Discount") {
                DiscountScreen()
            }
            composable("Lottery") {
                LoteriaView(viewModels = LoteriaViewModel())
            }

        }
    }

}
