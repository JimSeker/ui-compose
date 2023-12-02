package edu.cs4730.lazylistsdemo3

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.ArrayList

/**
 * this example displays, with checkboxes that change other parts of the list.
 * this is kind of a painful example with all the remember as arrays, but I couldn't
 * think of better way to do it.  And I'm trying to replicate the lists from recyclerviews. 
 */

@Composable
fun InterActiveScreen() {
    val state = rememberLazyListState()

    val list = remember { mutableStateListOf<InterActive_DataModel>() }
    list.add(InterActive_DataModel(0, "Linux"))
    list.add(InterActive_DataModel(1, "Windows7"))
    list.add(InterActive_DataModel(2, "Suse"))
    list.add(InterActive_DataModel(3, "Eclipse"))
    list.add(InterActive_DataModel(4, "Ubuntu"))
    list.add(InterActive_DataModel(5, "Solaris"))
    list.add(InterActive_DataModel(6, "Android"))
    list.add(InterActive_DataModel(7, "iPhone"))
    // Initially select one of the items
    list[1].FUSelected(true)
    val checkedBoxes = List(list.size) { remember { mutableStateOf(false) } }
    checkedBoxes[1].value = true

    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(list) { index, entry ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                )

            ) {
                Row {
                    val context = LocalContext.current
                    Checkbox(
                        checked = checkedBoxes[index].value,
                        onCheckedChange = { checked ->
                            Toast.makeText(context, "value is $checked", Toast.LENGTH_SHORT).show()
                            if (checked) { //uncheck the rest.
                                for (i in 0 until list.size) {
                                    checkedBoxes[i].value = false
                                    list[i].FUSelected(false)
                                }
                            }
                            list[index].FUSelected(checked)
                            checkedBoxes[index].value = list[index].isSelected
                        },
                    )
                    Text(
                        text = entry.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InterActiveScreenPreview() {
    InterActiveScreen()
}