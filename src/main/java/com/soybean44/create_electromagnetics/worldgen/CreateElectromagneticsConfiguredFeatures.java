package com.soybean44.create_electromagnetics.worldgen;

import static net.minecraft.data.worldgen.features.FeatureUtils.register;

import java.util.List;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class CreateElectromagneticsConfiguredFeatures {
  public static final ResourceKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = key("lead_ore");

  private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
    return ResourceKey.create(Registries.CONFIGURED_FEATURE, CreateElectromagnetics.asResource(name));
  }

  public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
    RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    List<TargetBlockState> LeadTargetStates = List.of(
        OreConfiguration.target(stoneOreReplaceables, CreateElectromagnetics.LEAD_ORE.get()
            .defaultBlockState()));

    ctx.register(LEAD_ORE_KEY, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LeadTargetStates, 12)));

  }
}
