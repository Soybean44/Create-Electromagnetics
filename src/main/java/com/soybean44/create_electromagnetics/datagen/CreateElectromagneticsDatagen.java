package com.soybean44.create_electromagnetics.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = CreateElectromagnetics.MODID)
public class CreateElectromagneticsDatagen {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput output = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


    generator.addProvider(event.includeClient(), new CreateElectromagneticsBlockStateProvider(output, existingFileHelper));

    generator.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
          List.of(new LootTableProvider.SubProviderEntry(CreateElectromagneticsBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

    BlockTagsProvider blockTagsProvider = new CreateElectromagneticsBlockTagProvider(output, lookupProvider, existingFileHelper);
    generator.addProvider(event.includeServer(), blockTagsProvider);


    generator.addProvider(event.includeServer(), new CreateElectromagneticsDatapackProvider(output, lookupProvider));
  }

}
