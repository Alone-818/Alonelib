package Alone818.com.alonelib;
import Alone818.com.alonelib.Register.ModBlocks;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import Alone818.com.alonelib.Register.ModCreativeModeTabs;
import Alone818.com.alonelib.Register.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Alonelib.MODID)
public class Alonelib {
    public static final String MODID = "alonelib";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Alonelib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //region ModEventBus

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        //end region
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
     }
}