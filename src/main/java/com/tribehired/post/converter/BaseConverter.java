package com.tribehired.post.converter;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<Source, Target> {
    Target convertTarget(Source source);

    default List<Target> convertTarget(List<Source> sources) {
        if (!CollectionUtils.isEmpty(sources)) {
            return sources.stream().map(model -> this.convertTarget(model)).collect(Collectors.toList());
        }
        return null;
    }

    Source convertSource(Target dto);

    default List<Source> convertSource(List<Target> targets) {
        if (!CollectionUtils.isEmpty(targets)) {
            return targets.stream().map(dto -> this.convertSource(dto)).collect(Collectors.toList());
        }
        return null;
    }
}
