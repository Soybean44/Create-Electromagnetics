package com.soybean44.create_electromagnetics.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;
import com.soybean44.create_electromagnetics.worldgen.CreateElectromagneticsConfiguredFeatures;
import com.soybean44.create_electromagnetics.worldgen.CreateElectromagneticsPlacedFeatures;
import com.soybean44.create_electromagnetics.worldgen.CreateElectromagneticsBiomeModifiers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;

import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class CreateElectromagneticsDatapackProvider extends DatapackBuiltinEntriesProvider {
  private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
      .add(Registries.CONFIGURED_FEATURE, CreateElectromagneticsConfiguredFeatures::bootstrap)
      .add(Registries.PLACED_FEATURE, CreateElectromagneticsPlacedFeatures::bootstrap)
      .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CreateElectromagneticsBiomeModifiers::bootstrap);

  public CreateElectromagneticsDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries, BUILDER, Set.of(CreateElectromagnetics.MODID));
  }

  @Override
  public String getName() {
    return "Create Electromagnetics' Generated Registry Entries";
  }
}
