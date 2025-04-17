package com.soybean44.create_electromagnetics.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import com.soybean44.create_electromagnetics.CreateElectromagnetics;

public class CreateElectromagneticsBlockStateProvider extends BlockStateProvider {
    public CreateElectromagneticsBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CreateElectromagnetics.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CreateElectromagnetics.LEAD_ORE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
