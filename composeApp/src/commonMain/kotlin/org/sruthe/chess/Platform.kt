package org.sruthe.chess

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform