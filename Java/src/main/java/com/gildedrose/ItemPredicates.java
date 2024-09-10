package com.gildedrose;

import java.util.Arrays;

public class ItemPredicates {
    public static ItemPredicate all(ItemPredicate... predicates) {
        return Arrays.stream(predicates)
            .reduce(ItemPredicate::and)
            .orElse(i -> false);
    }
}
