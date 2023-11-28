package edu.cs4730.drawerdemosimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.cs4730.drawerdemosimple.ui.theme.DrawerDemoSimpleTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawerDemoSimpleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    // opens drawer
                    drawerState.open()
                }
            }) {
                Icon(
                    // internal hamburger menu
                    Icons.Rounded.Menu,
                    contentDescription = "MenuButton"
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(DrawerState(initialValue = DrawerValue.Closed))
}

@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var which by remember { mutableIntStateOf(1) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "First item") },
                    selected = which == 1,
                    onClick = {
                        which = 1
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Second item") },
                    selected = which == 2,
                    onClick = {
                        which = 2
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Third item") },
                    selected = which == 3,
                    onClick = {
                        which = 3
                        scope.launch { drawerState.close() }
                    }
                )
                // ...other drawer items
            }
        },
        gesturesEnabled = true,
    ) { //content = is this part here.
        Scaffold(
            topBar = {
                TopBar(drawerState)
            },
            content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
                Column(
                    modifier = Modifier.padding(padding),
                ) {
                    Text(
                        text = "Screen is $which"
                    )
                    //top open the drawer, call this:
                    // scope.launch { drawerState.open() }
                    when (which) {
                        1 -> FirstScreen()
                        2 -> SecondScreen()
                        3 -> ThirdScreen()
                        else -> FirstScreen()
                    }
                }

            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}