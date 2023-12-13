package edu.cs4730.screendemosimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.cs4730.screendemosimple.ui.theme.ScreenDemoSimpleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenDemoSimpleTheme {
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


@Composable
fun MainScreen() {
    var which by remember { mutableIntStateOf(1) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .wrapContentSize(Alignment.Center)
    ) {

        Text("Demo of switching between 2 different 'screens' with a button.")
        OutlinedButton(modifier =  Modifier.padding(8.dp),
            onClick = {
                which = if (which == 2) 1 else 2
            }) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                text = "Switch screens"
            )
        }
        if (which == 1)
            FirstScreen()
        else
            SecondScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ScreenDemoSimpleTheme {
        MainScreen()
    }
}