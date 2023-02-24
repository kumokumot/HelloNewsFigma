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

package com.example.hellonews.data

import com.example.hellonews.model.PostAuthor
import com.example.hellonews.R
import com.example.hellonews.model.Post
import com.example.hellonews.model.Metadata

// Articles author consists of news outlets instead of individuals
val seasonal = PostAuthor("Seasonal Sagas", "")
val morning = PostAuthor("Morning Break", "")
val culture = PostAuthor("Science Culture", "")
val animal = PostAuthor("The Bees", "")
val egg = PostAuthor("Cracking the Egg", "")
val cinematic = PostAuthor("Light Labs", "")


val post1 = Post(
    id = "84eb677660d9",
    title = "The Wonderful Architectures of This Winter Season",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = seasonal,
        date = "20 days ago",
        readTimeMinutes = 5
    ),
    imageId = R.drawable.thumbnail_1,
)

val post2 = Post(
    id = "7446d8dfd7dc",
    title = "Creating Butterflies With New Transparent Ink",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = culture,
        date = "1 month ago",
        readTimeMinutes = 10
    ),
    imageId = R.drawable.thumbnail_2,
)

val post3 = Post(
    id = "ac552dcc1741",
    title = "Timeless Architecture, Through Decades and Seasons",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = seasonal,
        date = "10 days ago",
        readTimeMinutes = 15
    ),
    imageId = R.drawable.thumbnail_3,
)

val post4 = Post(
    id = "dc523f0ed25c",
    title = "The New Method to Making Breakfast Crepes",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = morning,
        date = "1 day ago",
        readTimeMinutes = 15
    ),
    imageId = R.drawable.thumbnail_4,
)

val post5 = Post(
    id = "55db18283aca",
    title = "Wondering Hour: A Childhood Story, Grizzly Bears, and A Sunset in the Distance",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = animal,
        date = "July 24, 2021",
        readTimeMinutes = 20
    ),
    imageId = R.drawable.thumbnail_5,
)

val post6 = Post(
    id = "55db18283aca",
    title = "The Stories and Adventures That We Carry Into 2022",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = egg,
        date = "18 hours ago",
        readTimeMinutes = 23
    ),
    imageId = R.drawable.thumbnail_6,
)

val post7 = Post(
    id = "55db18283aca",
    title = "Episode 11 - Science Based Tools For Sleep, Alertness, and Learning",
    subtitle = "",
    url = "",
    metadata = Metadata(
        author = cinematic,
        date = "1 day ago",
        readTimeMinutes = 45
    ),
    imageId = R.drawable.thumbnail_7,
)

val posts: List<Post> =
    listOf(
        post1,
        post2,
        post3,
        post4,
        post5,
        post6,
        post7
    )