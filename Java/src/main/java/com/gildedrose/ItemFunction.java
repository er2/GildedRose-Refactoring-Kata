package com.gildedrose;

import java.util.function.UnaryOperator;

public interface ItemFunction extends UnaryOperator<Item> {

    default ItemFunction andThen(ItemFunction other) {
        return x -> other.apply(apply(x));
    }
}
