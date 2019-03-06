package com.example.android.myapplication

/**
 * This annotation allows us to open some classes for mocking purposes while they are final in
 * release builds.
 */
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting