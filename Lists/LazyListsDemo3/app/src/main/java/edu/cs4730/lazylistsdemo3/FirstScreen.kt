package edu.cs4730.lazylistsdemo3

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Arrays

@Composable
fun FirstScreen() {
    val values = Arrays.asList(
        "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7",
        "Max OS X", "Linux", "OS/2"
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            //Text("Header")
            MyItem("Header", -1)

        }
        itemsIndexed(values) { index, item ->
            MyItem(item, index)
        }
        item {
            //Text("Footer")
            MyItem("Footer", -1)
        }
    }
}


@Composable
fun MyItem(value: String, index: Int) {
    Box(
        modifier = with(Modifier) {
            height(100.dp)
                .paint(
                    painter = painterResource(id = R.drawable.phone),
                    //tint the image darker.
                    colorFilter = ColorFilter.tint(Color.Gray, blendMode = BlendMode.Darken),
                    contentScale = ContentScale.FillWidth
                )

        })
    {
        // Add more views here!
        val context = LocalContext.current
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    Toast.makeText(context, "value is $value", Toast.LENGTH_SHORT ).show()
                }
            ,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            text = "$index = $value"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyItemPreview() {
    MyItem("Android", 1)
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    FirstScreen()
}