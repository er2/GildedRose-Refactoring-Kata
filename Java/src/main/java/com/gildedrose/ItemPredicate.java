package com.gildedrose;

public interface ItemPredicate {

    boolean appliesTo(Item item);

    default ItemPredicate and(ItemPredicate other) {
        return item -> appliesTo(item) && other.appliesTo(item);
    }

    default Rule then(ItemFunction... functions) {
        return new RuleImpl(this, ItemFunctions.compose(functions).andThen(ItemRules.time()));
    }

    static ItemPredicate matchAll() {
        return item -> true;
    }
}
