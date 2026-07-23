package Alone818.com.alonelib.init;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}