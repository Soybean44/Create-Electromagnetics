package com.soybean44.create_electromagnetics;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(CreateElectromagnetics.MODID)
public class CreateElectromagnetics {
  // Define mod id in a common place for everything to reference
  public static final String MODID = "create_electromagnetics";
  // Directly reference a slf4j logger
  public static final Logger LOGGER = LogUtils.getLogger();

  public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

  public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
      .create(Registries.CREATIVE_MODE_TAB, MODID);

  public static final DeferredBlock<Block> LEAD_ORE = BLOCKS.registerSimpleBlock("lead_ore",
      BlockBehaviour.Properties.of()
          .mapColor(MapColor.STONE)
          .sound(SoundType.STONE));
  public static final DeferredItem<BlockItem> LEAD_ORE_ITEM = ITEMS.registerSimpleBlockItem("lead_ore", LEAD_ORE);

  // Creates a creative tab with the id "createelectromagnetics:example_tab" for
  // the example item, that is placed after the combat tab
  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS
      .register("create_electromagnetics_tab", () -> CreativeModeTab.builder()
          .title(Component.translatable("itemGroup.create_electromagnetics")) // The language key for the title of your
                                                                              // CreativeModeTab
          .withTabsBefore(CreativeModeTabs.COMBAT)
          .icon(() -> LEAD_ORE_ITEM.get().getDefaultInstance())
          .displayItems((parameters, output) -> {
            output.accept(LEAD_ORE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is
                                                // preferred over the event
          }).build());

  // The constructor for the mod class is the first code that is run when your mod
  // is loaded.
  // FML will recognize some parameter types like IEventBus or ModContainer and
  // pass them in automatically.
  public CreateElectromagnetics(IEventBus modEventBus, ModContainer modContainer) {
    // Register our mod's ModConfigSpec so that FML can create and load the config
    // file for us
    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    // Register the commonSetup method for modloading
    modEventBus.addListener(this::commonSetup);

    // Register ourselves for server and other game events we are interested in.
    // Note that this is necessary if and only if we want *this* class
    // (CreateElectromagnetics) to respond directly to events.
    // Do not add this line if there are no @SubscribeEvent-annotated functions in
    // this class, like onServerStarting() below.
    NeoForge.EVENT_BUS.register(this);

    // Register the Deferred Register to the mod event bus so blocks get registered
    BLOCKS.register(modEventBus);
    // Register the Deferred Register to the mod event bus so items get registered
    ITEMS.register(modEventBus);
    // Register the Deferred Register to the mod event bus so tabs get registered
    CREATIVE_MODE_TABS.register(modEventBus);
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    // Some common setup code
    LOGGER.info("HELLO FROM COMMON SETUP");

    if (Config.logDirtBlock)
      LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

    LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

    Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
  }

  public static ResourceLocation asResource(String path) {
    return ResourceLocation.fromNamespaceAndPath(MODID, path);
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    LOGGER.info("HELLO from server starting");
  }

  // You can use EventBusSubscriber to automatically register all static methods
  // in the class annotated with @SubscribeEvent
  @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
  public static class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
      // Some client setup code
      LOGGER.info("HELLO FROM CLIENT SETUP");
      LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
  }
}
