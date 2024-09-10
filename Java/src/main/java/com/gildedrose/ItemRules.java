package com.gildedrose;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import static com.gildedrose.ItemPredicate.matchAll;

public class ItemRules {

    public static Item apply(Item item) {
        return MASTER_RULE.apply(item);
    }

    private static final List<Rule> ALL_RULES = List.of(
        //                                                                                          quality          time            check
        iff(name(eq("Sulfuras, Hand of Ragnaros")))                                           .then(freezeQuality(), freezeTime()),
        iff(name(eq("Aged Brie")))                                                            .then(improveBy(1),                    maxQuality(50)),
        iff(name(eq("Backstage passes to a TAFKAL80ETC concert")), past())                    .then(expired()),
        iff(name(eq("Backstage passes to a TAFKAL80ETC concert")), sellIn(gte(0).and(lt(5)))) .then(improveBy(3),                    maxQuality(50)),
        iff(name(eq("Backstage passes to a TAFKAL80ETC concert")), sellIn(gte(5).and(lt(10)))).then(improveBy(2),                    maxQuality(50)),
        iff(name(eq("Backstage passes to a TAFKAL80ETC concert")), sellIn(gte(10)))           .then(improveBy(1),                    maxQuality(50)),
        iff(name(startsWith("Conjured")))                                                     .then(depreciateBy(2)),
        iff(matchAll())                                                                       .then(depreciateBy(1))
    );

    private static final Rule MASTER_RULE = Rules.compose(ALL_RULES);

    private static ItemFunction expired() {
        return item -> item.withQuality(0);
    }

    private static Predicate<String> startsWith(String prefix) {
        return name -> name.startsWith(prefix);
    }

    private static ItemPredicate sellIn(IntPredicate predicate) {
        return item -> predicate.test(item.sellIn);
    }

    private static IntPredicate gte(int i) {
        return x -> x >= i;
    }

    private static IntPredicate lt(int i) {
        return x -> x < i;
    }

    private static ItemPredicate past() {
        return item -> item.sellIn < 0;
    }

    /** negate normal passage of time */
    private static ItemFunction freezeTime() {
        return item -> item.withSellIn(item.sellIn + 1);
    }

    private static ItemFunction freezeQuality() {
        return item -> item;
    }

    private static Predicate<String> eq(String s) {
        return s::equals;
    }

    private static ItemPredicate name(Predicate<String> name) {
        return item -> name.test(item.name);
    }

    private static ItemPredicate iff(ItemPredicate... predicates) {
        return ItemPredicates.all(predicates);
    }

    private static ItemFunction improveBy(int improvement) {
        return item -> item.withQuality(item.quality + improvement);
    }

    private static ItemFunction depreciateBy(int qualityIncrement) {
        return item -> item.withQuality(item.quality - qualityIncrement);
    }

    public static ItemFunction time() {
        return item -> item.withSellIn(item.sellIn - 1);
    }

    private static ItemFunction maxQuality(int maxQuality) {
        return item -> item.withQuality(Math.min(item.quality, maxQuality));
    }
}
