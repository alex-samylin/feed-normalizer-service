package com.alexsamylin.feednormalizer.converter;

public interface FeedConverter<I, O> {
    O convert(I input);
}

