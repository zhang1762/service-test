package com.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDateInfo<T> implements Serializable {

    @Builder.Default
    private int pageNum = 1;

    @Builder.Default
    private int pageSize = 20;

    private Long total;

    private List<T> items;

    public static PageDateInfo of(List items, long total) {
        return PageDateInfo.builder().items(items).total(total).build();
    }

    public static PageDateInfo of(List items, long total, int pageNum, int pageSize) {
        return PageDateInfo.builder().items(items).total(total).pageNum(pageNum).pageSize(pageSize).build();
    }
}
