package io.github.adainish.disablepixelmongrass;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.battles.BattleStartTypes;
import com.pixelmonmod.pixelmon.api.events.PixelmonBlockStartingBattleEvent;
import com.pixelmonmod.pixelmon.api.events.PixelmonBlockTriggeredBattleEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("disablepixelmongrass")
public class DisablePixelmonGrass {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public DisablePixelmonGrass() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartedEvent event) {
        // do something when the server starts
        LOGGER.info("Disabling Pixelmon Grass");
        Pixelmon.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void onGrass(PixelmonBlockTriggeredBattleEvent event) {
        if (event.startType.equals(BattleStartTypes.PUGRASSDOUBLE) || event.startType.equals(BattleStartTypes.PUGRASSSINGLE))
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onGrassSpawn(PixelmonBlockStartingBattleEvent event)
    {
        if (event.startType.equals(BattleStartTypes.PUGRASSDOUBLE) || event.startType.equals(BattleStartTypes.PUGRASSSINGLE))
            event.setCanceled(true);
    }

}
