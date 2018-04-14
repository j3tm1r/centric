package com.jxhem.uicentric.datalayer.net

object Model {

    data class Comment(
            val name: String, val email: String,
            val postId: Long, val id: Long,
            val body: String
    ) {
        companion object {

        }
    }
}