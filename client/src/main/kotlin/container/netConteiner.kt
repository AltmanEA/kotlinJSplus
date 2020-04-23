package container

import component.*
import data.State
import hoc.withDisplayName
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.FetchState
import redux.RAction
import redux.WrapperAction
import redux.postState

interface NetDispatchProps : RProps {
    var load: () -> Unit
}

interface NetStateProps : RProps {
    var save: () -> Unit
}

val netContainer =
    rConnect<
            State,
            RAction,
            WrapperAction,
            RProps,
            NetStateProps,
            NetDispatchProps,
            NetProps>(
        { state, _ ->
            save = { postState(state) }
        },
        { dispatch, _ ->
            load = { dispatch(FetchState) }
        }
    )(
        withDisplayName(
            "NetService",
            fNet()
        )
            .unsafeCast<RClass<NetProps>>()
    )