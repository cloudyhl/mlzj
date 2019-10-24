package com.mlzj.common.utils;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author yhl
 * @date 2019/9/24
 */
public class Operation<T> {


    private static final Operation<?> EMPTY = new Operation<>();

    private final T value;

    private Operation() {
        this.value = null;
    }


    public static <T> Operation<T> empty() {
        @SuppressWarnings("unchecked")
        Operation<T> t = (Operation<T>) EMPTY;
        return t;
    }


    private Operation(T value) {
        this.value = Objects.requireNonNull(value);
    }


    public static <T> Operation<T> of(T value) {
        return new Operation<>(value);
    }


    public static <T> Operation<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }


    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }


    public boolean isPresent() {
        return value != null;
    }


    public Operation<T> ifPresent(Consumer<? super T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
        return this;
    }

    public Operation<T> handle(Consumer<? super T> consumer){
        consumer.accept(value);
        return this;
    }


    public Operation<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }


    public <U> Operation<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Operation.ofNullable(mapper.apply(value));
        }
    }

    public <U> Operation<U> flatMap(Function<? super T, Operation<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent()) {
            return empty();
        } else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }


    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Operation)) {
            return false;
        }

        Operation<?> other = (Operation<?>) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return value != null
                ? String.format("Operation[%s]", value)
                : "Operation.empty";
    }
}
