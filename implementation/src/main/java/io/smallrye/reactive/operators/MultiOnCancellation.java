package io.smallrye.reactive.operators;

import static io.smallrye.reactive.helpers.ParameterValidation.nonNull;

import io.reactivex.Flowable;
import io.smallrye.reactive.Multi;

public class MultiOnCancellation<T> extends MultiOperator<T, T> {
    private final Runnable callback;

    public MultiOnCancellation(Multi<T> upstream, Runnable callback) {
        super(nonNull(upstream, "upstream"));
        this.callback = nonNull(callback, "callback");
    }

    @Override
    protected Flowable<T> flowable() {
        return upstreamAsFlowable().doOnCancel(callback::run);
    }
}