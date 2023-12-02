package edu.cs4730.lazylistsdemo3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import edu.cs4730.lazylistsdemo3.ui.theme.LazyListsDemo3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyListsDemo3Theme {
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
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopBar()
        },
    ) { padding ->
        var pagerState = rememberPagerState(pageCount = { 4 })
        var tabIndex by remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
        LaunchedEffect(pagerState) {
            // When the pager changes, we also need to change the tab correctly.
            snapshotFlow { pagerState.currentPage }.collect { page ->
                tabIndex = page
            }
        }

        val tabs = listOf("Simple", "simple2", "Interactive list", "PhoneBook list")
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
        ) {
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            coroutineScope.launch {
                                // Call scroll to on pagerState, when the tab is clicked.
                                pagerState.scrollToPage(index)
                            }
                        },
                    )
                }
            }
            //VerticalPager for vertical paging, otherwise the same.
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> SimpleScreen()
                    1 -> FirstScreen()
                    2 -> InterActiveScreen()
                    3 -> PhonebookScreen()
                    else -> FirstScreen()
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
