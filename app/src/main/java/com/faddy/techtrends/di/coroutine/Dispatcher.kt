package com.faddy.techtrends.di.coroutine

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val ttDispatcher: TtDispatchers)

enum class TtDispatchers {
    Default, IO
}
