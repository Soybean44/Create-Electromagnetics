package com.soybean44.create_electromagnetics.worldgen;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class CreateElectromagneticsBiomeModifiers {
  public static final ResourceKey<BiomeModifier> LEAD_ORE = key("lead_ore");

  private static ResourceKey<BiomeModifier> key(String name) {
    return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CreateElectromagnetics.asResource(name));
  }

  public static void bootstrap(BootstrapContext<BiomeModifier> ctx) {
    HolderGetter<Biome> biomeLookup = ctx.lookup(Registries.BIOME);
    HolderGetter<PlacedFeature> placedFeatures = ctx.lookup(Registries.PLACED_FEATURE);
    HolderSet<Biome> isOverworld = biomeLookup.getOrThrow(BiomeTags.IS_OVERWORLD);

    Holder<PlacedFeature> leadOre = placedFeatures.getOrThrow(CreateElectromagneticsPlacedFeatures.LEAD_ORE_PLACED_KEY);

    ctx.register(LEAD_ORE, addOre(isOverworld, leadOre));
  }

  private static AddFeaturesBiomeModifier addOre(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) {
    return new AddFeaturesBiomeModifier(biomes, HolderSet.direct(feature), Decoration.UNDERGROUND_ORES);
  }
}
