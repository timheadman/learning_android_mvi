package ru.megaland.mvi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowListLazyIndexed() {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(
            listOf(
                ItemRowListModel(imageId = R.drawable.face_f1, "Lily", "Description"),
                ItemRowListModel(imageId = R.drawable.face_m1, "Fill", "Description"),
                ItemRowListModel(imageId = R.drawable.face_f2, "Mary", "Description"),
                ItemRowListModel(imageId = R.drawable.face_f3, "Mona", "Description"),
                ItemRowListModel(imageId = R.drawable.face_m2, "Bill", "Description"),
                ItemRowListModel(imageId = R.drawable.face_m3, "Kirk", "Description"),
            )
        ) { _, item ->
            RowListItem(item = item)
        }
    }
}

@Composable
private fun RowListItem(item: ItemRowListModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(128.dp)
                .padding(5.dp)
                .clip(CircleShape)
        )
        Text(text = item.title, fontSize = 22.sp)
        Text(text = item.description, fontSize = 22.sp)
    }
}

