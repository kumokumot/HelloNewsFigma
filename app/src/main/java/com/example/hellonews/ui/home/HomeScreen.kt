/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hellonews.ui.home

import com.example.hellonews.R

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellonews.data.posts
import com.example.hellonews.model.Post
import com.example.hellonews.newscard.NewsCard
import com.example.hellonews.newscard.View
import com.example.hellonews.ui.theme.NewsTheme

/**
 * Displays the Home screen
 *
 * @param posts (List<Post>) placeholder data for the list of news articles
 */
@Composable
fun HomeScreen(
    posts: List<Post>,
) {
    Scaffold() { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        PostList(
            posts = posts,
            modifier = modifier
        )
    }
}

/**
 * Display a list of posts.
 *
 * @param posts (state) the list to display
 * @param modifier modifier for the root element
 */
@Composable
private fun PostList(
    posts: List<Post>,
    modifier: Modifier = Modifier,
) {
    val postTop = posts[0]
    val postsArticle = posts.subList(1, 4)
    val postsAudio = posts.subList(4, 7)

    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    Dialog(showDialog, setShowDialog, title, content)
    val createTapHandler = { cardTitle: String, cardAction: String ->
        {
            title = cardTitle
            content = cardAction
            setShowDialog(true)
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        item { SearchArticlesSection() }
        item { PostListArticleStoriesSection(postTop, postsArticle, createTapHandler) }
        item {
            Spacer(
                Modifier
                    .heightIn(8.dp)
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxWidth()
            )
        }
        item {
            AudioStoriesTitle()
            PostListAudioStories(postsAudio, createTapHandler)
        }
    }
}

/**
 * Display the articles stories section
 *
 * @param postTop post displayed at the top
 * @param posts list of posts to display
 * @param createOnTapped: (String, String) -> () -> Unit, creates a tap handler to open a dialog
 * that contains the given the title and content. Used after import in the demo
 */
@Composable
private fun PostListArticleStoriesSection(
    postTop: Post,
    posts: List<Post>,
    createOnTapped: (String, String) -> () -> Unit

) {
    PostListArticleStories(
        postTop,
        posts,
        createOnTapped
    )
    TextButton(
        content = { Text(text = stringResource(id = R.string.home_more)) },
        onClick = {},
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onBackground,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

/**
 * Searchbar before first section of [PostList]
 */
@Composable
private fun SearchArticlesSection() {
    TextField(
        "",
        {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.cd_search_articles),
                tint = MaterialTheme.colors.onSecondary
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.cd_more_actions),
                tint = MaterialTheme.colors.onSecondary
            )
        },
        placeholder = { Text(stringResource(R.string.cd_search_articles)) },
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = MaterialTheme.colors.onSecondary,
            backgroundColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
    )
}

/**
 * Article section of [PostList]
 *
 * @param postTop (state) highlighted post to display
 * @param posts: List<Post>, (state) posts to display
 * @param createOnTapped: (String, String) -> () -> Unit, creates a tap handler to open a dialog
 * that contains the given the title and content. Used after import in the demo
 *
 */
@Composable
private fun PostListArticleStories(
    postTop: Post,
    posts: List<Post>,
    createOnTapped: (String, String) -> () -> Unit
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var content by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    Dialog(showDialog, setShowDialog, title, content)

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {

        Spacer(modifier = Modifier.size(12.dp))
        NewsCard(
            thumbnail = painterResource(postTop.imageId),
            headline = postTop.title,
            author = postTop.metadata.author.name,
            date = postTop.metadata.date,
            onNewsCardTapped = createOnTapped("Card Tapped", postTop.title),
            onMenuTapped = {},
            view = View.HeroItem
        )
        Spacer(modifier = Modifier.size(12.dp))

        posts.forEach { post ->
            NewsCard(
                thumbnail = painterResource(post.imageId),
                headline = post.title,
                author = post.metadata.author.name,
                date = post.metadata.date,
                view = View.ArticleItem
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

/**
 * Title and button for audio section
 */
@Composable
private fun AudioStoriesTitle() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.home_audio_section_title),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondaryVariant
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            ),
            elevation = ButtonDefaults.elevation(0.dp),
            shape = RoundedCornerShape(100),
            contentPadding = PaddingValues(start = 10.dp, top = 4.dp, bottom = 4.dp, end = 14.dp)
        ) {
            Icon(
                Icons.Outlined.PlayArrow,
                contentDescription = "Play All",
            )
            Spacer(Modifier.size(4.dp))
            Text(stringResource(id = R.string.home_play_all), fontSize = 12.sp)
        }
    }
    Spacer(Modifier.size(12.dp))
}

/**
 * Audio section of [PostList]
 *
 * @param posts: List<Post>, (state) posts to display
 * @param createOnTapped: (String, String) -> () -> Unit, creates a tap handler to open a dialog
 * that contains the given the title and content. Used after import in the demo
 */
@Composable
private fun PostListAudioStories(
    posts: List<Post>,
    createOnTapped: (String, String) -> () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        posts.forEach { post ->
            NewsCard(
                thumbnail = painterResource(post.imageId),
                headline = post.title,
                author = post.metadata.author.name,
                date = post.metadata.date,
                view = View.AudioItem
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Composable
fun Dialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit, title: String, content: String) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(title)
            },
            confirmButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                ) {
                    Text("OK")
                }
            },
            text = {
                Text(content)
            },
        )
    }
}

@Preview("Home screen", device = Devices.PIXEL_4, heightDp = 1450, widthDp = 382)
@Composable
fun PreviewHomeScreen() {
    NewsTheme {
        HomeScreen(posts)
    }
}