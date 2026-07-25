package Alone818.com.alonelib.Register;

import Alone818.com.alonelib.Alonelib;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * 创造模式标签（Creative Mode Tab）注册器
 *
 * <p>职责：注册本模组在创造模式物品栏中显示的标签页，
 * 定义标签页的图标、名称以及其中包含的物品列表。</p>
 *
 * <p>使用方法：在 {@link Alonelib} 主类构造方法中调用 {@link #register(IEventBus)}。</p>
 */
public class ModCreativeModeTabs {

    // ==================== 注册器 ====================

    /**
     * 创造模式标签延迟注册器。
     *
     * <p>使用 {@link Registries#CREATIVE_MODE_TAB} 注册表，
     * 以模组的 MODID 作为命名空间。</p>
     */
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Alonelib.MODID);

    // ==================== 标签页实例 ====================

    /**
     * 本模组的创造模式标签页。
     *
     * <p>注册名为 "tutorial"，作为内部标识符。
     * 标签页展示本模组的所有物品和方块，方便玩家在创造模式中查找。</p>
     *
     * <p>配置说明：</p>
     * <ul>
     *   <li>{@code icon}：标签页在物品栏中显示的图标（此处使用石头作为占位）</li>
     *   <li>{@code title}：标签页的显示名称，通过本地化键 "Alonelib" 在语言文件中定义</li>
     *   <li>{@code displayItems}：标签页中展示的物品列表，可接受多个回调</li>
     * </ul>
     */
    public static final RegistryObject<CreativeModeTab> TUTORIAL =
            CREATIVE_MODE_TABS.register("tutorial", () -> CreativeModeTab.builder()

                    // 设置标签页在创造模式物品栏中显示的图标
                    // 此处使用石头（Items.STONE）作为占位图标，可替换为模组的专属物品
                    .icon(() -> new ItemStack(Items.STONE))

                    // 设置标签页的显示标题
                    // 使用可本地化的文本组件，对应语言文件中的翻译键
                    .title(Component.translatable("Alonelib"))

                    // 定义该标签页中展示的物品内容
                    // 可多次调用此方法，向标签页中添加不同分类的物品
                    .displayItems((itemDisplayParameters, output) -> {
                        // 此处可添加更多分类的物品展示逻辑
                    })

                    // 向标签页中添加本模组的示例物品和方块
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_MATERIAL.get());            // 粗材料
                        output.accept(ModBlocks.RAW_MATERIAL_BLOCK.get());    // 粗材料块
                    })

                    // 构建最终的 CreativeModeTab 实例
                    .build()
    );

    // ==================== 入口方法 ====================

    /**
     * 将创造模式标签注册器挂载到模组事件总线。
     *
     * <p>必须在 {@link Alonelib} 主类构造方法中调用此方法，
     * 否则创造模式标签页无法在游戏中生效。</p>
     *
     * @param eventBus Forge 事件总线实例
     */
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
