package container

import react.*
import react.redux.rConnect
import component.*
import data.*
import hoc.withDisplayName
import redux.FullState

val appContainer =
    rConnect<FullState, RProps, AppProps>(
        { state, _ ->
            lessons = state.lessons
            students = state.students
            isLoad = state.isLoad
        },
        {
            pure = false  // side effect of React Route
        }
    )(
        withDisplayName(
            "MyApp",
            fApp()
        )
            .unsafeCast<RClass<AppProps>>()
    )