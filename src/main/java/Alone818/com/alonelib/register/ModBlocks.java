package Alone818.com.alonelib.register;

import Alone818.com.alonelib.Alonelib;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

/**
 * 方块注册器
 *
 * <p>职责：统一注册本模组的所有方块（Block），并为每个方块自动注册对应的
 * {@link net.minecraft.world.item.BlockItem}，使方块能够被玩家手持及放置。</p>
 *
 * <p>使用方法：在 {@link Alonelib} 主类的构造方法中调用 {@link #register(IEventBus)}。</p>
 */
public class ModBlocks {

    // ==================== 注册器 ====================

    /**
     * 方块类型延迟注册器。
     *
     * <p>使用 {@link ForgeRegistries#BLOCKS} 注册表，以模组的 MODID 作为命名空间。</p>
     */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Alonelib.MODID);

    // ==================== 方块实例 ====================

    /**
     * 示例方块——粗材料块。
     *
     * <p>注册名为 "raw_material_block"，采用铁块的属性作为模板
     *（硬度、抗爆性、发光等），继承该方块的所有物理特性。</p>
     *
     * <p>对应的 BlockItem 会自动注册为同名 "raw_material_block"，
     * 使玩家可以在创造模式物品栏中看到并放置该方块。</p>
     *
     * <p>资源路径：assets/{@code Alonelib}/models/block/raw_material_block.json</p>
     */
    public static final RegistryObject<Block> RAW_MATERIAL_BLOCK = registerBlock(
            "raw_material_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK))
    );

    // ==================== 内部注册方法 ====================

    /**
     * 通用方块注册方法。
     *
     * <p>执行流程：
     * <ol>
     *   <li>向 {@link #BLOCKS} 注册器注册方块本身；</li>
     *   <li>自动注册对应的 {@link net.minecraft.world.item.BlockItem}（见 {@link #registerBlockItem}）；</li>
     *   <li>返回方块的 {@link RegistryObject}，方便其他类引用。</li>
     * </ol></p>
     *
     * @param name 方块的注册名（将同步用于 BlockItem 的注册名）
     * @param block 方块的创建函数（Supplier），延迟执行以符合 Forge 的注册时机要求
     * @param <T> 方块类型（任意 {@link Block} 子类）
     * @return 方块的 {@link RegistryObject}
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        // 向方块注册器声明该方块
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        // 为该方块自动注册 BlockItem，使其可被玩家手持、放置
        registerBlockItem(name, toReturn);
        // 返回注册结果，供外部引用方块实例
        return toReturn;
    }

    /**
     * 为已注册的方块创建并注册对应的 {@link net.minecraft.world.item.BlockItem}。
     *
     * <p>说明：在 Minecraft 中，方块（Block）和物品（Item）是两个独立的注册表。
     * 玩家在物品栏中持有的是 BlockItem，使用它才能放置出对应的方块。</p>
     *
     * <p>调用时机：在 {@link #registerBlock(String, Supplier)} 中自动调用，
     * 确保每个方块都拥有对应的 BlockItem。</p>
     *
     * @param name 注册名（与对应方块保持一致）
     * @param block 方块的 {@link RegistryObject}，用于通过 {@link RegistryObject#get()} 获取方块实例
     * @param <T> 方块类型（任意 {@link Block} 子类）
     * @return BlockItem 的 {@link RegistryObject}
     */
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        // 在物品注册器中注册 BlockItem
        // block.get() 获取注册器已声明的方块实例，
        // 使玩家在背包中持有该物品时，能放置出对应的方块
        return ModItems.ITEMS.register(
                name,
                () -> new BlockItem(block.get(), new Item.Properties())
        );
    }

    // ==================== 入口方法 ====================

    /**
     * 将方块注册器挂载到模组事件总线。
     *
     * <p>必须在 {@link Alonelib} 主类构造方法中调用此方法，
     * 否则方块无法被注册到游戏中。</p>
     *
     * @param eventBus Forge 事件总线实例
     */
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
