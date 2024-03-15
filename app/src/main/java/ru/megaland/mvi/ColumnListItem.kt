package ru.megaland.mvi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ColumnListItem {

    data class ListItemData(
        val name: String,
        val profession: String
    )

    companion object {
        @Composable
        fun ExecuteLazy(name: String, profession: String, repeat: Int = 1) {
            LazyColumn() {
                items(count = repeat) {
                    SingleItem(name = name, number = it, profession = profession)
                }
            }
        }

        @Composable
        fun ExecuteLazyIndexed() {
            LazyColumn() {
                itemsIndexed(
                    listOf(
                        ListItemData("Arnold", "Actor"),
                        ListItemData("Arnold", "Actor")
                    )
                ) { index, item ->
                    SingleItem(name = item.name, number = index, profession = item.profession)
                }
            }
        }

        @Composable
        fun Execute(name: String, profession: String, repeat: Int = 1) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                repeat(repeat) {
                    Row {
                        SingleItem(name = name, number = it, profession = profession)
                    }
                }
            }
        }

        @Composable
        private fun SingleItem(name: String, number: Int, profession: String) {
            val counter = remember {
                mutableIntStateOf(0)
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        counter.intValue++
                        Log.d("AAA", "ListItem $name$number Click! ")
                    }
                    .pointerInput(Unit) {
                        detectDragGesturesAfterLongPress { change, dragAmount ->
                            Log.d(
                                "AAA",
                                "ListItem $name$number detectDragGesturesAfterLongPress:  $dragAmount ${change.position}"
                            )
                        }
                    },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arn),
                            contentDescription = "A.Sh",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(5.dp)
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text(text = "$name$number", fontWeight = FontWeight.Bold)
                            Text(text = "$profession$number")
                        }
                        Column(
                            modifier = Modifier
                                .background(Color.Red, shape = CircleShape)
                                .padding(horizontal = 10.dp, vertical = 2.dp),
                        ) {

                            Text(
                                text = "${counter.intValue}",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}