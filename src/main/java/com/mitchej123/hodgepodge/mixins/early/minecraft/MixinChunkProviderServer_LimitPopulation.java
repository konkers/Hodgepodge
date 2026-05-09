package com.mitchej123.hodgepodge.mixins.early.minecraft;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkProviderServer.class)
public class MixinChunkProviderServer_LimitPopulation {

    @Redirect(
            at = @At(
                    target = "Lnet/minecraft/world/chunk/IChunkProvider;populate(Lnet/minecraft/world/chunk/IChunkProvider;II)V",
                    value = "INVOKE"),
            method = "populate(Lnet/minecraft/world/chunk/IChunkProvider;II)V")
    private void hodgepodge$ignoreChunkPopulation(IChunkProvider chunkProvider, IChunkProvider chunkProvider2, int x,
            int z) {

        Chunk chunk = chunkProvider.provideChunk(x, z);

        if (chunk.worldObj.provider.dimensionId == 0 && Math.abs(x) < 12 && Math.abs(z) < 12) {
            chunkProvider.populate(chunkProvider2, x, z);
        }

    }

}
