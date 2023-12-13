package edu.cs4730.bottombardemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.cs4730.bottombardemo.ui.theme.BottomBarDemoTheme

/**
 * Simple demo using the bottomBar. (call BottomNavigation in widgets).
 * Interesting note, the simple bottomNavigation widget version requires a lot more code then this
 * but the navigation bottomBar version is way more complex then the widget version.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarDemoTheme {
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
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),

        )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}


@Composable
fun MainScreen() {
    var which by remember { mutableIntStateOf(0) }
    val navList = listOf(
        Pair(R.drawable.ic_action_one, "First"),
        Pair(R.drawable.ic_action_second, "Second"),
        Pair(R.drawable.ic_action_third, "Third")
    )

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                for(i in navList.indices) {
                    //to have badges and other things, since code in the advanced directory.  this is simple.
                    NavigationBarItem(selected = which == i,
                        onClick = { which =i },
                        icon = {
                            Icon(
                                painterResource(id = navList[i].first),
                                contentDescription = navList[i].second
                            )
                        })

                }
            }
        },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                when (which) {
                    0 -> {
                        FirstScreen()
                    }

                    1 -> {
                        SecondScreen()
                    }

                    2 -> {
                        ThirdScreen()
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

