package Alone818.com.alonelib.register;

import Alone818.com.alonelib.Alonelib;

import Alone818.com.alonelib.register.ModBlocks;
import Alone818.com.alonelib.register.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * 创造模式标签（Creative Mode Tab）注册器
 *
 * <p>职责：注册本模组在创造模式物品栏中显示的标签页，
 * 自动展示 {@link ModItems} 和 {@link ModBlocks} 中的所有内容。</p>
 */
public class ModCreativeModeTabs {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alonelib.MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB =
            CREATIVE_MODE_TABS.register("tab_main", () -> CreativeModeTab.builder()

                    .icon(() -> new ItemStack(net.minecraft.world.item.Items.STONE))
                    .title(Component.translatable("itemGroup.Alonelib"))
                    .displayItems((params, output) -> {
                        ModBlocks.BLOCKS.getEntries().forEach(e -> output.accept(e.get()));
                        ModItems.ITEMS.getEntries().forEach(e -> output.accept(e.get()));
                    })
                    .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
