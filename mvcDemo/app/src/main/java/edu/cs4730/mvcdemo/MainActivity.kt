package edu.cs4730.mvcdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.cs4730.mvcdemo.ui.theme.MvcDemoTheme

/**
 * A "Simple" example of text, image, and a couple of buttons.
 */

class MainActivity : ComponentActivity() {
    //model data, myColors holds all the information.
    private var myColors = ColorList()

    //for the ImageView, which also needs a bitmap and Canvas to draw display the colors
    private lateinit var theColor: Bitmap
    private lateinit var theColorc: Canvas

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theColor = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        theColorc = Canvas(theColor)

        theColorc.drawColor(myColors.getNum())

        setContent {
            MvcDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                ),
                                title = {
                                    Text(stringResource(R.string.app_name))
                                }
                            )
                        },

                        ) { innerPadding ->
                        MvcDemoContent(innerPadding)
                    }
                }
            }
        }
    }

    @Composable
    fun MvcDemoContent(innerPadding: PaddingValues) {
        var colorName by remember { mutableStateOf(myColors.name) }
        //probably should have a remember one for picture, but the text causes a redraw anyway.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text =  colorName,
                modifier = Modifier
                    .padding(8.dp),
            )
            Image(
                bitmap = theColor.asImageBitmap(),
                contentDescription = "it's a drawn pic of the color"
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        myColors.prev()
                        colorName = myColors.name
                        theColorc.drawColor(myColors.getNum())
                    },
                    enabled = !myColors.isFirst,
                    modifier = Modifier
                        .padding(8.dp),
                ) {
                    Text("Previous")
                }
                Button(
                    onClick = {
                        myColors.next()
                        colorName = myColors.name
                        theColorc.drawColor(myColors.getNum())
                    },
                    enabled = !myColors.isLast,
                    modifier = Modifier
                        .padding(8.dp),
                ) {
                    Text("Next")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MvcDemoTheme {
            MvcDemoContent(PaddingValues())
        }
    }

}



