package com.inditex.storage.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface AbstractListConverter<S, T> extends Converter<S, T> {

    default List<T> convertObjects(Collection<S> sourceObjects) {
        if (sourceObjects == null || sourceObjects.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceObjects.stream()
                .map(this::convert)
                .toList();
    }
}