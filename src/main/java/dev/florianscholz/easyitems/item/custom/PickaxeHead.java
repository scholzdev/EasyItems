package dev.florianscholz.easyitems.item.custom;

public class PickaxeHead extends AbstractPickaxeHead {

    private final PickaxeHeadType type;

    public PickaxeHead(PickaxeHeadType type) {
        super(type.stats().tier(), type.getProperties());

        this.type = type;
    }

    public Properties getProperties() {
        return this.type.getProperties();
    }
}
