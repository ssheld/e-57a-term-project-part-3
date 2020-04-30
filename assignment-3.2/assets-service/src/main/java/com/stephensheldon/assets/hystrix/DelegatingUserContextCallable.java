package com.stephensheldon.assets.hystrix;

import com.stephensheldon.assets.utils.UserContext;
import com.stephensheldon.assets.utils.UserContextHolder;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;

/**
 * Author: Stephen Sheldon
 **/

public final class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;

    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate,
                                         UserContext userContext) {
        Assert.notNull(delegate, "delegate cannot be null");
        Assert.notNull(userContext, "userContext cannot be null");
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<>(delegate, userContext);
    }
}