package com.gildedrose;

public class RuleImpl implements Rule {

    public final ItemPredicate itemPredicate;
    public final ItemFunction functions;

    public RuleImpl(ItemPredicate itemPredicate, ItemFunction... functions) {
        this.itemPredicate = itemPredicate;
        this.functions = ItemFunctions.compose(functions);
    }

    @Override
    public boolean appliesTo(Item item) {
        return itemPredicate.appliesTo(item);
    }

    @Override
    public Item apply(Item item) {
        return functions.apply(item);
    }
}
