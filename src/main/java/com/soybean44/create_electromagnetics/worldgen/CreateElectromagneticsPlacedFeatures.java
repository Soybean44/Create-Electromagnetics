package com.soybean44.create_electromagnetics.worldgen;


import java.util.List;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class CreateElectromagneticsPlacedFeatures {
  public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY = key("lead_ore_placed");


  public static void bootstrap(BootstrapContext<PlacedFeature> context) {
    var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

    register(context, LEAD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(CreateElectromagneticsConfiguredFeatures.LEAD_ORE_KEY),
        CreateElectromagneticsOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
  }

  private static ResourceKey<PlacedFeature> key(String name) {
    return ResourceKey.create(Registries.PLACED_FEATURE, CreateElectromagnetics.asResource(name));
  }

  private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
      List<PlacementModifier> modifiers) {
    context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
  } 
}
