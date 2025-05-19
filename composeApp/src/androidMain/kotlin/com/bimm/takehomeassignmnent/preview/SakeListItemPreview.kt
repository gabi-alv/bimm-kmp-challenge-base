package com.bimm.takehomeassignmnent.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bimm.takehomeassignmnent.sakes.list.model.SakeShopListUiItem
import com.bimm.takehomeassignmnent.sakes.list.view.SakeListItem

@Preview
@Composable
fun SakeListItemPreview() {
    MaterialTheme {
        SakeListItem(
            SakeShopListUiItem(
                id = 1,
                name = "長野県酒類販売(株)",
                description = "Wholesale sake distributor.",
                picture = "https://www.nagano-sake.com/common/images/front/drinks-xxl@2x.jpg",
                rating = 4.2
            ),
            onIntent = {}
        )
    }
}